package controllers

/**
 * Created by staamneh on 12/3/2015.
 */


import java.io.{IOException, InputStream}
import java.text.DecimalFormat
import java.util
import java.util.{Scanner, Iterator}
import akka.actor.{ActorRef, Props, Actor}
import akka.routing.RoundRobinPool

import scala.collection.JavaConverters._

import Models._
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.{ChildList, File}
import play.Logger
import com.google.api.services.drive.model._
import com.google.api.services.drive.model.File

import scala.collection.immutable.TreeMap
import scala.util.control.Breaks._
import play.api.libs.json.{JsString, JsArray, Json, JsObject}
import controllers.CreatePortraitMessages._
import controllers.CreatingStudyMessages._



object CreatePortraitAbstraction
{



}

class CreatePortraitAbstraction(abst :Abstraction, studyNo: Int)  extends Actor {


  //val routerForPortrait: ActorRef = context.actorOf(RoundRobinPool(16).props(Props[ScanSubjectPortrait]), "routerForPortrait")
  var queryString,grades, sBars, subjNames, gender, examsNames = ""
  var traits: TreeMap[String, String]  = TreeMap.empty
  var genderList : TreeMap[String, String] = TreeMap.empty  // to solve the problem of unordered recieved gender
  var subjectList : TreeMap[String, String] = TreeMap.empty // to solve the problem of unordered recieved subjectname
  var sessionNO: Int = 1
  var flg1: Int = 0
  var startTimePortrait: Long =0;
  var startTimeStudy: Long =0;
  var titleWithIdSession: Map[String, String] = Map.empty
  var googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(abst.userName)
  var service: Drive = GoogleDrive.buildService(googleCredential)


  // val failureThreshold: Double = GoogleDrive.findLoadedDrivingPoint(folder_id, username)  //TODO: parallize this
  var failureThreshold: Double = 0.0;
  // get the names of subjects folders
  val subjects =GoogleDrive.returnFilesInFolder(service, abst.studyLocation, "mimeType = 'application/vnd.google-apps.folder'")
  var titlteWithId: TreeMap[String, String] = new TreeMap[String, String]
  // put subject folder name and subject id in a tree map to sort them. {
  service = GoogleDrive.buildService(googleCredential)
  for (i <- 0 until subjects.size())  {
    titlteWithId+= GoogleDrive.waitUntilGetDGFile(service,subjects.get(i)).getTitle -> subjects.get(i)
  }

  for ((subName, subject) <- titlteWithId) {
    traits += subName -> "0"
  }

  var subject: String = null
  var sessionId: String = null
  var extension: String = null
  var studyDescriptor : Option[TreeMap[String,(String,Boolean,Int, Boolean)] ] = None
  var allBarsForAllSubjs: Map[String,  TreeMap[String, BarPercentage]] = TreeMap.empty
  var allPerformanceForAllSubs: Map[String, TreeMap[String, Double]] =TreeMap.empty
  var allPsychometricForAllSubs: Map[String, TreeMap[String, Double]] = TreeMap.empty

  var forRadar : TreeMap[String, JsObject] = TreeMap.empty[String, JsObject]
  var SubjectNames: List[String] = List()
  var maxes: List[Double] = List()
  var names: List[String] = List()
  var file0: File = null
  var file: File = null
  var startOfLoadedDriveSum: Double = 0.0
  var startOfLoadedDriveCtr: Double = 0.0



  var doneSubjects =0
  var doneSubjectsForPoints =0
  var ctr =0;
  var js: JsArray = Json.arr();

  def receive = {
    case FindLoadedPoint =>

      //startTimeStudy = System.nanoTime
      for ((subName, subject) <- titlteWithId) {
        //al myActor= context.actorOf(Props[FindFailurePoint])
        Global.routerForPortrait ! FindPoint(service , subject)
      }

    case FindRadar =>
      for ((subName, subject) <- titlteWithId) {
        //al myActor= context.actorOf(Props[FindFailurePoint])

        val definedVar = DataBaseOperations.listOfFileExtensionPortrait(abst.userName )
        Global.routerForPortrait ! FindNewMultiModal(service, subject, subName, definedVar);
      }


    //case x: JsObject =>
    case RadarForSorting(sub, x) =>
      ctr = ctr +1;

      forRadar += sub -> x
      //js = js.+:(x)
      if(ctr == titlteWithId.size){


        for((k,v)<- forRadar){
          js = js.:+(v)
        }

        //println(js);
        DataBaseOperations.InsertStudyRadar(studyNo, js.toString());
      }

    // else
    //println("got one salah")



    case StartOfLoadedDrive(x) =>

      if(! x.equals(-100.0)) {
        startOfLoadedDriveSum = startOfLoadedDriveSum + x;
        startOfLoadedDriveCtr = startOfLoadedDriveCtr + 1;
      }
      doneSubjectsForPoints +=1;
      Logger.info("One point is done                  " +  doneSubjectsForPoints  + "/"  + titlteWithId.size )
      if(doneSubjectsForPoints ==titlteWithId.size) {
        failureThreshold = startOfLoadedDriveSum / startOfLoadedDriveCtr
        self ! Start
      }
    //context.stop(sender())

    // case start will create a different actor for each subject, where each actor will calculate the grade, stress, sai, and gender and send it back here
    case Start(sTime, sNo) =>
      startTimePortrait = System.nanoTime
      startTimeStudy = sTime

      var sessionNamesMapping : java.util.TreeMap[String, String] = null;
      studyDescriptor = DataBaseOperations.getDescriptorAsMap(sNo)
      sessionNamesMapping= studyDescriptor match {
        case Some(x) =>
          var newMap : java.util.TreeMap[String, String] = new java.util.TreeMap[String, String];
          for((key,value) <- x){
            newMap put(key , value._1)
          }
          newMap
        case None => null
        case null => null
      }



      Logger.info("Failure Point: "  +  failureThreshold)
      for ((subName, subject) <- titlteWithId) {
        //val myActor= context.actorOf(Props(new ScanSubjectPortrait))
        Global.routerForPortraitAbstraction ! CreatePortraitForAsubjectAbstraction(service , subject , subName, abst.userName, abst, sessionNamesMapping)
      }
    // each subActor will tell once he finished proceessing a subject so that we know when everything is done
    case ChildDone(name) =>
      doneSubjects+=1;
      Logger.info("One subject is done......portrait                 : "+ name + "   " +  doneSubjects   + "/"  + subjects.size() )
      if(doneSubjects == subjects.size()) {
        Logger.info("All the subjects have been scaned now we are goining to create the portrit string")
        // set up the gender
        var i = 0;



        studyDescriptor match{
          case Some(descriptiveStudy) =>
            queryString = generateQueryStringTemp(allBarsForAllSubjs, allPerformanceForAllSubs, allPsychometricForAllSubs, abst.studyName, genderList, traits, studyNo, descriptiveStudy);
          case None =>
            queryString = generateQueryStringTempWithoutDescriptive(allBarsForAllSubjs, allPerformanceForAllSubs, allPsychometricForAllSubs, abst.studyName, genderList, traits, studyNo);
        }


        Logger.info(queryString);

        // to notify the sender that we finished creating the subjectportrait and top summary can start working.
      //  context.parent ! DoneCreatingPortrait(studyNo)

        DataBaseOperations.InsertStudyPortraitString(studyNo, queryString);
        Logger.info("Time spent in creating the portrait is :" + (System.nanoTime - startTimePortrait)/60000000000.0)
        Logger.info("Time spent in creating the whole study is :" + (System.nanoTime - startTimeStudy)/60000000000.0)
        queryString;

        context.stop(self)
      }
    case Stress(subjectName, x) =>
      allBarsForAllSubjs +=  subjectName-> x
    case Gender(subjectName, x) =>
      genderList += subjectName -> x
    case Perf(subjectName, x) =>
      allPerformanceForAllSubs += subjectName -> x
    case PsycoMsg(subjectName, x) =>
      allPsychometricForAllSubs += subjectName -> x
    case SubjectID(x) =>
      subjectList += x -> "Dummy"
  }


// This fucntion will try to read the study descriptor (i.e., ".descriptor" file)and return it as a Map



  def checkIfSessionThere( descriptiveStudy :TreeMap[String,(String,Boolean,Int, Boolean)], search: String): Boolean =
  {
    for((key, value) <- descriptiveStudy)
      {
        if(value._1.equalsIgnoreCase(search) && value._2)
          return true;
      }

    return false;
  }

  def isMutual( descriptiveStudy :TreeMap[String,(String,Boolean,Int, Boolean)], search: String): Boolean =
  {
    for((key, value) <- descriptiveStudy)
    {
      if(key.equalsIgnoreCase(search) && value._4)
        return true;
      if(value._1.equalsIgnoreCase(search) && value._4)
        return true;

    }

    return false;
  }

  // to generate a portrait for each 40 subject In order to avoid the problem of lengthy link
  def generateQueryStringTemp(allBarsForAllSubjs:Map[String,TreeMap[String, BarPercentage]], allPerformanceForAllSubs: Map[String,TreeMap[String, Double]], allPsychometricForAllSubs: Map[String,TreeMap[String, Double]], studyName: String, genderP: TreeMap[String, String],  traitsP: TreeMap[String, String], studyNo: Int, descriptiveStudy :TreeMap[String,(String,Boolean,Int, Boolean)]): String = {

    var perfSeq = Seq.empty[Double]
    for((key,value) <- allPerformanceForAllSubs){
      for((key2,value2) <- value){
        perfSeq = perfSeq :+ value2;
      }
    }

    var saifSeq = Seq.empty[Double]
    for((key,value) <- allPsychometricForAllSubs){
      for((key2,value2) <- value){
        saifSeq = saifSeq :+ value2;
      }
    }

    val perfPercentil = "&grade_Percentile=" + computePercentile(perfSeq, 25, true) + "," + computePercentile(perfSeq, 50, true) + "," + computePercentile(perfSeq, 75, true)
    val saiPercenil = "&SAIs_Percentile=" + computePercentile(saifSeq, 25, true) + "," + computePercentile(saifSeq, 50, true) + "," + computePercentile(saifSeq, 75, true)

    Logger.info("We are in fun: generateQueryString")
    var queryString: String = ""
    var tai: String = ""
    var sBars = "" ;
    var examsNames ="" ;
    var grades ="" ;
    var genders = "" ;
    var subjectNames = ""
    var triats = ""
    var subjectCounter =0 ;
    var finalQueryString=""
    var subjectPerPAge = 40 ;
    var standardSessionListOrdered: TreeMap[String, String] = new TreeMap[String, String]






    // to generate a list of all available session and sorting them so that we are able to create a uniform seesion list for all subjects
    for ((key1,value1) <- allBarsForAllSubjs) {
      import scala.collection.JavaConversions._
      for ((key2, value2) <- value1) {
        if (!standardSessionListOrdered.containsValue(key2.replaceFirst("(\\d*\\s*)", ""))){
          if( descriptiveStudy.contains(key2.replaceFirst("(\\d*\\s*)", "")) ) {
            if (descriptiveStudy(key2.replaceFirst("(\\d*\\s*)", ""))._2)
              standardSessionListOrdered += descriptiveStudy(key2.replaceFirst("(\\d*\\s*)", ""))._3+key2 -> key2.replaceFirst("(\\d*\\s*)", "")
          }
          else
              if( checkIfSessionThere(descriptiveStudy,key2.replaceFirst("(\\d*\\s*)", "") ) )
              standardSessionListOrdered += key2 -> key2.replaceFirst("(\\d*\\s*)", "")

        }
      }
    }

    println(standardSessionListOrdered)


    // calucate the number of mutual execulsive sessions
    var numES = 0;
    for((key, value) <- descriptiveStudy)
      {
        if(value._4)
          numES +=1;

      }

    if(numES > 0){
      numES = numES -1;
    }
    val sessionNOP = standardSessionListOrdered.size - numES;
    var itr: Int = 0
    import scala.collection.JavaConversions._
    for ((subject, value) <- allBarsForAllSubjs) {


      if(subjectCounter %subjectPerPAge ==0  && subjectCounter >0)
      {
        if(subjectCounter ==subjectPerPAge)
          queryString =  "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil + saiPercenil
        else
          queryString =  queryString + "~"+ "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil +saiPercenil

        sBars = ""
        examsNames = ""
        grades = ""
        tai =  ""
        subjectNames =  ""
        triats =   ""
        genders = ""
      }

      subjectCounter +=1;

      subjectNames = subjectNames + subject + ","
      triats = triats + traitsP(subject) +  ","
      genders = genders + genderP(subject) + ","

      val prf: TreeMap[String, Double] = allPerformanceForAllSubs(subject)
      val sai_list: TreeMap[String, Double] = allPsychometricForAllSubs(subject)
      itr += 1
      var isFirst: Boolean = true
      if (value.size > 0) {
        import scala.collection.JavaConversions._
        for ((sessionNameWithNumber, sessionSimplifiedName) <- standardSessionListOrdered) { // we start going over the global session

        var sName = sessionSimplifiedName
          if(descriptiveStudy.contains(sessionSimplifiedName))
            sName = descriptiveStudy(sessionSimplifiedName)._1

          var isthere: Boolean = false
          val perf_temp: Long = 0
          var performance: String = "NA"
          var sai_temp: String = "NA"
          var theOne: BarPercentage = null
          import scala.collection.JavaConversions._
          for ((sessionName, stressBar) <- value) { // check if the current sujbect has the global session
            if (sessionName.replaceFirst("(\\d*\\s*)", "") == sessionSimplifiedName) {
              isthere = true
              theOne = stressBar
              if (prf.containsKey(sessionSimplifiedName) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "tl")) {
                //performance = Long.toString(prf.get(entry.getValue).round)
                performance = prf.get(sessionSimplifiedName) match {
                  case Some(x) => x.round.toString
                  case None => "NA"
                }
              }
              var neededKey: String = sName
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "fd")) neededKey = "FD"
              if (sai_list.containsKey(neededKey) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(neededKey, "tl")) {
                // sai_temp = Long.toString(sai_list.get(neededKey).round)
                sai_temp =  {sai_list.get(neededKey)  match {case Some(x) => x.round}}.toString
              }
            }
          }
          if (isthere) { //This means that the current subject has a session similar to the current session in globabl session
            if (isFirst) {
              sBars = sBars + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + sName //descriptiveStudy(sessionSimplifiedName)._1//sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + performance
              tai = tai + sai_temp
              isFirst = false
            }
            else {
              sBars = sBars + ":" + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + "," + sName //descriptiveStudy(sessionSimplifiedName)._1 //sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + "," + performance
              tai = tai + ":" + sai_temp
            }
          }
          //else if(!descriptiveStudy(sessionSimplifiedName)._4){   // this to handle the situation where there is a missing session in this subject
          else if(!isMutual(descriptiveStudy,sessionSimplifiedName )){
            // if (!org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionSimplifiedName, "fd")) {
            if (isFirst) {
              examsNames = examsNames + sName //descriptiveStudy(sessionSimplifiedName)._1 // sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
              grades = grades + "NA"
              tai = tai + "NA"
              isFirst = false
            }
            else {
              examsNames = examsNames + "," + sName //descriptiveStudy(sessionSimplifiedName)._1 //sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
              sBars = sBars + ":"
              grades = grades + ",NA"
              tai = tai + ":NA"
            }
            // }
          }
        }
      }
      else {
      }
      sBars = sBars + ";"
      examsNames = examsNames + ";"
      grades = grades + ";"
      tai = tai + ";"
    }
    if(subjectCounter % subjectPerPAge != 0  && subjectCounter > subjectPerPAge)
    {
      queryString =  queryString +  "~"+ "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil +saiPercenil
    }
    else
      queryString =  "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil + saiPercenil

    // queryString = queryString + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
    return queryString
  }

  // to generate a portrait for each 40 subject In order to avoid the problem of lengthy link
  def generateQueryStringTempWithoutDescriptive(allBarsForAllSubjs:Map[String,TreeMap[String, BarPercentage]], allPerformanceForAllSubs: Map[String,TreeMap[String, Double]], allPsychometricForAllSubs: Map[String,TreeMap[String, Double]], studyName: String, genderP: TreeMap[String, String],  traitsP: TreeMap[String, String], studyNo: Int): String = {

    var perfSeq = Seq.empty[Double]
    for((key,value) <- allPerformanceForAllSubs){
      for((key2,value2) <- value){
        perfSeq = perfSeq :+ value2;
      }
    }

    var saifSeq = Seq.empty[Double]
    for((key,value) <- allPsychometricForAllSubs){
      for((key2,value2) <- value){
        saifSeq = saifSeq :+ value2;
      }
    }

    val perfPercentil = "&grade_Percentile=" + computePercentile(perfSeq, 25, true) + "," + computePercentile(perfSeq, 50, true) + "," + computePercentile(perfSeq, 75, true)
    val saiPercenil = "&SAIs_Percentile=" + computePercentile(saifSeq, 25, true) + "," + computePercentile(saifSeq, 50, true) + "," + computePercentile(saifSeq, 75, true)

    Logger.info("We are in fun: generateQueryString")
    var queryString: String = ""
    var tai: String = ""
    var sBars = "" ;
    var examsNames ="" ;
    var grades ="" ;
    var genders = "" ;
    var subjectNames = ""
    var triats = ""
    var subjectCounter =0 ;
    var finalQueryString=""
    var subjectPerPAge = 40 ;
    var standardSessionListOrdered: TreeMap[String, String] = new TreeMap[String, String]

    // to generate a list of all available session and sorting them so that we are able to create a uniform seesion list for all subjects
    for ((key1,value1) <- allBarsForAllSubjs) {
      import scala.collection.JavaConversions._
      for ((key2, value2) <- value1) {
        if (!standardSessionListOrdered.containsValue(key2.replaceFirst("(\\d*\\s*)", ""))) {
            standardSessionListOrdered += key2 -> key2.replaceFirst("(\\d*\\s*)", "")
        }
      }
    }

    println(standardSessionListOrdered)
    val sessionNOP = standardSessionListOrdered.size;
    var itr: Int = 0
    import scala.collection.JavaConversions._
    for ((subject, value) <- allBarsForAllSubjs) {
      if(subjectCounter %subjectPerPAge ==0  && subjectCounter >0)
      {
        if(subjectCounter ==subjectPerPAge)
          queryString =  "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil + saiPercenil
        else
          queryString =  queryString + "~"+ "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil +saiPercenil

        sBars = ""
        examsNames = ""
        grades = ""
        tai =  ""
        subjectNames =  ""
        triats =   ""
        genders = ""
      }

      subjectCounter +=1;
      subjectNames = subjectNames + subject + ","
      triats = triats + traitsP(subject) +  ","
      genders = genders + genderP(subject) + ","

      val prf: TreeMap[String, Double] = allPerformanceForAllSubs(subject)
      val sai_list: TreeMap[String, Double] = allPsychometricForAllSubs(subject)
      itr += 1
      var isFirst: Boolean = true
      if (value.size > 0) {
        import scala.collection.JavaConversions._
        for ((sessionNameWithNumber, sessionSimplifiedName) <- standardSessionListOrdered) { // we start going over the global session
        var isthere: Boolean = false
          val perf_temp: Long = 0
          var performance: String = "NA"
          var sai_temp: String = "NA"
          var theOne: BarPercentage = null
          import scala.collection.JavaConversions._
          for ((sessionName, stressBar) <- value) { // check if the current sujbect has the global session
            if (sessionName.replaceFirst("(\\d*\\s*)", "") == sessionSimplifiedName) {
              isthere = true
              theOne = stressBar
              if (prf.containsKey(sessionSimplifiedName) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "tl")) {
                //performance = Long.toString(prf.get(entry.getValue).round)
                performance = prf.get(sessionSimplifiedName) match {
                  case Some(x) => x.round.toString
                  case None => "NA"
                }
              }
              var neededKey: String = sessionSimplifiedName
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "fd")) neededKey = "FD"
              if (sai_list.containsKey(neededKey) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(neededKey, "tl")) {
                // sai_temp = Long.toString(sai_list.get(neededKey).round)
                sai_temp =  {sai_list.get(neededKey)  match {case Some(x) => x.round}}.toString
              }
            }
          }
          if (isthere) { //This means that the current subject has a session similar to the current session in globabl session
            if (isFirst) {
              sBars = sBars + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + performance
              tai = tai + sai_temp
              isFirst = false
            }
            else {
              sBars = sBars + ":" + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + "," + sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + "," + performance
              tai = tai + ":" + sai_temp
            }
          }
          else {   // this to handle the situation where there is a missing session in this subject
            // if (!org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionSimplifiedName, "fd")) {
            if (isFirst) {
              examsNames = examsNames +  sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
              grades = grades + "NA"
              tai = tai + "NA"
              isFirst = false
            }
            else {
              examsNames = examsNames + "," + sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
              sBars = sBars + ":"
              grades = grades + ",NA"
              tai = tai + ":NA"
            }
            // }
          }
        }
      }
      else {
      }
      sBars = sBars + ";"
      examsNames = examsNames + ";"
      grades = grades + ";"
      tai = tai + ";"
    }
    if(subjectCounter % subjectPerPAge != 0  && subjectCounter > subjectPerPAge)
    {
      queryString =  queryString +  "~"+ "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil +saiPercenil
    }
    else
      queryString =  "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames + perfPercentil + saiPercenil

    // queryString = queryString + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
    return queryString
  }

  // this is to generate a single portrait for the whole study.
  def generateQueryString(allBarsForAllSubjs:Map[String,TreeMap[String, BarPercentage]], allPerformanceForAllSubs: Map[String,TreeMap[String, Double]], allPsychometricForAllSubs: Map[String,TreeMap[String, Double]], noOfSub: Int, studyName: String, gender: String,  traitsP: String, subjNamesP: String, studyNo: Int): String = {

    Logger.info("We are in fun: generateQueryString")
    var queryString: String = "subjects=" + noOfSub + "&cols=3&hideButton=yes&title=" + studyName
    var tai: String = ""
    var sBars = "";
    var examsNames ="";
    var grades ="";
    var standardSessionListOrdered: TreeMap[String, String] = new TreeMap[String, String]

    // to generate a list of all available session and sorting them so that we are able to create a uniform seesion list for all subjects
    for ((key1,value1) <- allBarsForAllSubjs) {
      import scala.collection.JavaConversions._
      for ((key2, value2) <- value1) {
        if (!standardSessionListOrdered.containsValue(key2.replaceFirst("(\\d*\\s*)", ""))) {
          standardSessionListOrdered += key2 -> key2.replaceFirst("(\\d*\\s*)", "")
        }
      }
    }

    val sessionNOP = standardSessionListOrdered.size - 1
    var itr: Int = 0
    import scala.collection.JavaConversions._
    for ((subject, value) <- allBarsForAllSubjs) {
      // val prf: TreeMap[String, Double] = allPerformanceForAllSubs.get(itr)
      val prf: TreeMap[String, Double] = allPerformanceForAllSubs(subject)
      //val sai_list: TreeMap[String, Double] = allPsychometricForAllSubs.get(itr)
      val sai_list: TreeMap[String, Double] = allPsychometricForAllSubs(subject)
      itr += 1
      var isFirst: Boolean = true
      if (value.size > 0) {
        import scala.collection.JavaConversions._
        for ((sessionNameWithNumber, sessionSimplifiedName) <- standardSessionListOrdered) { // we start going over the global session
        var isthere: Boolean = false
          val perf_temp: Long = 0
          var performance: String = "NA"
          var sai_temp: String = "NA"
          var theOne: BarPercentage = null
          import scala.collection.JavaConversions._
          for ((sessionName, stressBar) <- value) { // check if the current sujbect has the global session
            if (sessionName.replaceFirst("(\\d*\\s*)", "") == sessionSimplifiedName) {
              isthere = true
              theOne = stressBar
              if (prf.containsKey(sessionSimplifiedName) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "tl")) {
                //performance = Long.toString(prf.get(entry.getValue).round)
                performance = prf.get(sessionSimplifiedName) match {
                  case Some(x) => x.round.toString
                  case None => "NA"
                }
              }
              var neededKey: String = sessionSimplifiedName
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionNameWithNumber, "fd")) neededKey = "FD"
              if (sai_list.containsKey(neededKey) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(neededKey, "tl")) {
                // sai_temp = Long.toString(sai_list.get(neededKey).round)
                sai_temp =  {sai_list.get(neededKey)  match {case Some(x) => x.round}}.toString
              }
            }
          }
          if (isthere) {
            if (isFirst) {
              sBars = sBars + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + performance
              tai = tai + sai_temp
              isFirst = false
            }
            else {
              sBars = sBars + ":" + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed)
              examsNames = examsNames + "," + sessionNameWithNumber.replaceFirst("(\\d*\\s*)", "")
              grades = grades + "," + performance
              tai = tai + ":" + sai_temp
            }
          }
          else {   // this to handle the situation where there is a missing session in this subject
            if (!org.apache.commons.lang3.StringUtils.containsIgnoreCase(sessionSimplifiedName, "fd")) {
              if (isFirst) {
                examsNames = examsNames + sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
                grades = grades + "NA"
                tai = tai + "NA"
                isFirst = false
              }
              else {
                examsNames = examsNames + "," + sessionSimplifiedName.replaceFirst("(\\d*\\s*)", "")
                sBars = sBars + ":"
                grades = grades + ",NA"
                tai = tai + ":NA"
              }
            }
          }
        }
      }
      else {
      }
      sBars = sBars + ";"
      examsNames = examsNames + ";"
      grades = grades + ";"
      tai = tai + ";"
    }
    queryString = queryString + "&genders=" + gender + "&exams=" + sessionNOP + "&traits=" + traitsP + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNamesP + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
    return queryString
  }

  def computePercentile(vals: Seq[Double], tile: Double, unsorted: Boolean = true): Double = {
    assert(tile >= 0 && tile <= 100)
    if (vals.isEmpty) Double.NaN
    else {
      assert(vals.nonEmpty)
      // Make sure the list is sorted, if that's what we've been told
      if (!unsorted && vals.length >= 2) vals.sliding(2).foreach(l => assert(l(0) <= l(1))) else {}
      // NIST method; data to be sorted in ascending order
      val r =
        if (unsorted) vals.sorted
        else vals
      val length = r.length
      if (length == 1) r.head
      else {
        val n = (tile / 100d) * (length - 1)
        val k = math.floor(n).toInt
        val d = n - k
        if (k <= 0) r.head
        else {
          val last = length
          if (k + 1 >= length) {
            r.last
          } else {
            r(k) + d * (r(k + 1) - r(k))
          }
        }
      }
    }
  }


}
