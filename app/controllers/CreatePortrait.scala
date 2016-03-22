package controllers

import java.io.{IOException, InputStream}
import java.text.DecimalFormat
import java.util
import java.util.{Scanner, Iterator}
import akka.actor.{ActorRef, Props, Actor}
import akka.routing.RoundRobinPool
import controllers.CreatePortraitMessages._
import controllers.CreatingStudyMessages._

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

/**
 * Created by staamneh on 6/12/2015.
 */



object CreatePortraitMessages {
  case class ChildDone(SubjectName: String);
  case class SubjectID(name: String)
  case class Sai(value : TreeMap[String, BarPercentage] )
  case class Perf(subjectName: String, value: TreeMap[String, Double])
  case class PsycoMsg(subjectName: String, value : TreeMap[String, Double])
  case class Stress (subjectName: String, value: TreeMap[String, BarPercentage])
  case class Gender(subjectName: String, g: String)
  case class StartOfLoadedDrive(value: Double)
  case class Start(startTime: Long, studyNo: Int);
  case object FindLoadedPoint;
  case object FindRadar;
  case class FindPoint(service: Drive, subject: String);
  case class CreatePortraitForAsubject(service: Drive, subject:String, subName: String, username: String, failureThreshold : Double,  bio_code: Int )
  case class CreatePortraitForAsubjectAbstraction(service: Drive, subject:String, subName: String, username: String, topology: Abstraction, descriptor: java.util.TreeMap[String, String])
  case class CreatePortraitForAsubjectAbstractionWithSummary(service: Drive, subject:String, subName: String, username: String, topology: Abstraction, studyno: Int)
}



class CreatePortrait(folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, studyNo: Int)  extends Actor {


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
  var googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
  var service: Drive = GoogleDrive.buildService(googleCredential)


 // val failureThreshold: Double = GoogleDrive.findLoadedDrivingPoint(folder_id, username)  //TODO: parallize this
  var failureThreshold: Double = 0.0;


  // get the names of subjects folders
  val subjects =GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'")


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


      startTimeStudy = System.nanoTime
      for ((subName, subject) <- titlteWithId) {
        //al myActor= context.actorOf(Props[FindFailurePoint])
        Global.routerForPortrait ! FindPoint(service , subject)
      }

    case FindRadar =>
      for ((subName, subject) <- titlteWithId) {
        //al myActor= context.actorOf(Props[FindFailurePoint])

        val definedVar = DataBaseOperations.listOfFileExtensionPortrait(username )
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
    case Start =>
       startTimePortrait = System.nanoTime
      Logger.info("Failure Point: "  +  failureThreshold)
      for ((subName, subject) <- titlteWithId) {
        //val myActor= context.actorOf(Props(new ScanSubjectPortrait))
        Global.routerForPortrait ! CreatePortraitForAsubject(service , subject , subName, username, failureThreshold ,  bio_code )
      }
     // each subActor will tell once he finished proceessing a subject so that we know when everything is done
    case ChildDone(name) =>
      doneSubjects+=1;
      Logger.info("One subject is done......portrait                 : "+ name + "   " +  doneSubjects   + "/"  + subjects.size() )
      //context.stop(sender())
      if(doneSubjects == subjects.size()) {
        Logger.info("All the subjects have been scaned now we are goining to create the portrit string")
        // set up the gender
        var i = 0;
        queryString = generateQueryStringTemp(allBarsForAllSubjs, allPerformanceForAllSubs, allPsychometricForAllSubs, studyName, genderList, traits, studyNo);
        Logger.info(queryString);

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


  def generateQueryStringTemp(allBarsForAllSubjs:Map[String,TreeMap[String, BarPercentage]], allPerformanceForAllSubs: Map[String,TreeMap[String, Double]], allPsychometricForAllSubs: Map[String,TreeMap[String, Double]], studyName: String, genderP: TreeMap[String, String],  traitsP: TreeMap[String, String], studyNo: Int): String = {



    Logger.info("We are in fun: generateQueryString")
    var queryString: String = ""
    var tai: String = ""
    var sBars = "";
    var examsNames ="";
    var grades ="";
    var genders = "";
    var subjectNames = ""
    var triats = ""
    var subjectCounter =0;
    var finalQueryString=""
    var subjectPerPAge = 50;
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


      if(subjectCounter %subjectPerPAge ==0  && subjectCounter >0)
        {
          if(subjectCounter ==subjectPerPAge)
            queryString =  "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
          else
            queryString =  queryString + "~"+ "subjects=" + subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames

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
    if(subjectCounter % subjectPerPAge != 0  && subjectCounter > subjectPerPAge)
      {
        queryString =  queryString +  "~"+ "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
      }
    else
      queryString =  "subjects=" + subjectCounter%subjectPerPAge + "&cols=3&hideButton=yes&title=" + studyName +"&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames

   // queryString = queryString + "&genders=" + genders + "&exams=" + sessionNOP + "&traits=" + triats + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjectNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames
    return queryString
  }



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


}
