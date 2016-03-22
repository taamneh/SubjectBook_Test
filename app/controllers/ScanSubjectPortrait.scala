package controllers
import java.io.{IOException, InputStream}
import java.text.DecimalFormat
import java.util
import java.util.{Scanner, Iterator}
import Models._
import akka.actor.Actor
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.services.drive.model.{ChildList, File}
import controllers.CreatePortraitMessages._
import org.json.simple.{JSONArray, JSONObject}
import play.Logger
import scala.collection.{JavaConverters, JavaConversions}
import scala.util.Random
import scala.util.control.Breaks._

import scala.collection.JavaConverters._

import com.google.api.services.drive.Drive

import scala.collection.immutable.TreeMap
import play.api.libs.json.{JsObject, JsArray, Json}

/**
 * Created by staamneh on 6/16/2015.
 */

case class FindNewMultiModal(service: Drive, subject: String, name:String, difninedVar: Map[String, Int]);

case class RadarForSorting(sub: String, x: JsObject)
class ScanSubjectPortrait extends Actor {
 // private val GOOGLE_DRIVE: Int = 1
 // private val LOCALSERVER: Int = 2

  var SubjectNames: List[String] = List()  /// to be deleted


  private class SessionsBar {
    var name: String = null
    var location: String = null

    def this(a: String, b: String) {
      this()
      name = a
      location = b
    }
  }

  def receive = {

    case FindNewMultiModal(service, subject, subName, definedVar)=>

      var js: JsArray = Json.arr();
      var sent= false;
      var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      // get all the session folder inside the current subject
      var titleWithIdSession: TreeMap[String, String] = TreeMap.empty
      var maxValPers = -1000.0; var maxValEda = -1000.0; var maxValHrv = -1000.0;

      for (i <- 0 until sessions.size()) {
        titleWithIdSession += GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle.replaceFirst("(\\d*\\s*)", "") -> sessions.get(i)
      }
      // Iterate over the session folder for the current subject
      val name = "Session"
      var counter =1;
      var tempMap: TreeMap[String, Map[String, Double]] = TreeMap.empty[String, Map[String, Double]]
      for ((sessionName, sessionId) <- titleWithIdSession) {
        var nameNoNumber =sessionName.replaceFirst("(\\d*\\s*)", "").toLowerCase
        if(!nameNoNumber.equals("bl") && !nameNoNumber.equals("pd") && ! nameNoNumber.equals("nd")) {
            var innerMap: TreeMap[String, Double] = TreeMap.empty[String, Double]

            counter = counter + 1;
            var file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
            var Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
            import scala.collection.JavaConversions._

            // just in case there is no such signal in the session


          /*innerMap += "Perspiration" -> 0
            innerMap += "EDA" -> 0
            innerMap += "HRV" -> */


            for (signalId <- Signals) {
              val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
              val SessionName: String = file.getTitle

              if (!file2.getTitle.contains("~")) {


                var extension = file2.getFileExtension
                if(definedVar.contains(extension)){
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, file2.getId)
                  val x = ReadExcelJava.findMeanFromExcel(GoogleDrive.generateFileNameFromInputStream(input))
                  innerMap += extension-> x.mean
                }


                /*if (SignalType.isPerspiration(extension) || SignalType.isNPerspiration(extension)) {
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, file2.getId)
                  val x = ReadExcelJava.findMeanFromExcel(GoogleDrive.generateFileNameFromInputStream(input))
                  innerMap += extension-> x.mean
                }
                else if (SignalType.isEda(extension)) {
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, file2.getId)
                  val x = ReadExcelJava.findMeanFromExcel(GoogleDrive.generateFileNameFromInputStream(input))
                  // jsTemp = jsTemp.:+(Json.obj("axis" -> "EDA", "value" -> x.mean))
                  innerMap += extension -> x.mean
                }
                else if (SignalType.isHRV(extension)) {
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, file2.getId)
                  val x = ReadExcelJava.findMeanFromExcel(GoogleDrive.generateFileNameFromInputStream(input))
                  // jsTemp = jsTemp.:+(Json.obj("axis" -> "HRV", "value" -> x.mean))
                  innerMap += extension -> x.mean
                }*/


              }

            }
            tempMap += sessionName -> innerMap
        }
      }

      var maxes: TreeMap[String, Double] = TreeMap.empty
       for((key, value) <- tempMap)
         {
           for((key2,value2)<- value)
             {

               if(maxes.contains(key2)){
                 if( maxes(key2) < value2)
                   maxes += key2 -> value2
               }
               else
                 maxes += key2 -> value2

               /*if(key2.equals("Perspiration") && value2 > maxValPers) maxValPers = value2.toDouble
               else  if(key2.equals("EDA") && value2 > maxValEda) maxValEda = value2.toDouble
               else  if(key2.equals("HRV") && value2 > maxValHrv) maxValHrv = value2.toDouble*/
             }
         }



       for((key, value) <- tempMap)
       {
         var jsTemp: JsArray = Json.arr();
         // just to take care of situations where no singla avaialbe

         for((exte, fnl) <- maxes)
         {
           if(! value.contains(exte))
             jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> 0.0))
           else
             {
               if(maxes(exte) > 0)
                 jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> new DecimalFormat("#.####").format(value(exte)/maxes(exte)).toDouble*10000))
               else
                 jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> fnl.toDouble))
             }
         }

         /*for((key2,value2)<- value)
         {

           if(maxes.contains(key2)  ){
             if(maxes(key2) > 0)
               jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> new DecimalFormat("#.####").format(value2/maxes(key2)).toDouble*10000))
             else
               jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> value2.toDouble))
           }
           else
             jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> value2.toDouble))

         }*/


          /* if(key2.equals("Perspiration") ){
             if(maxValPers == 0) jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> value2.toDouble))
             else jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> new DecimalFormat("#.####").format(value2/maxValPers).toDouble*10000))
           }
           //jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> new DecimalFormat("#.####").format(value2/maxValPers).toDouble))
           else  if(key2.equals("EDA") ){
             if(maxValEda == 0) jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> value2.toDouble))
               else jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> new DecimalFormat("#.####").format(value2/maxValEda).toDouble*10000))
           }
           else  if(key2.equals("HRV") ){
             if(maxValHrv ==0) jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" ->value2.toDouble))
             else jsTemp = jsTemp.:+(Json.obj("axis" -> key2, "value" -> new DecimalFormat("#.####").format(value2/maxValHrv).toDouble*10000))
           }*/

         /*js = js.:+(Json.obj(
           "className" -> key,
           "axes" -> jsTemp
         ));*/


         js = js.:+(Json.arr(
           Json.obj(  "className" -> key,
           "axes" -> jsTemp )
         ));
       }



      //println(js);


      DataBaseOperations.InsertSubjectRadar(subName, js.toString())
      sender() !  RadarForSorting(subName,Json.obj(subName -> js))

    case FindPoint(service, subject) =>

      var sent= false;
      /*var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      // get all the session folder inside the current subject
      var titleWithIdSession: TreeMap[String, String] = TreeMap.empty

      for (i <- 0 until sessions.size()) {
        titleWithIdSession += GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle -> sessions.get(i)
      }

      */

      val titleWithIdSession = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);

      // Iterate over the session folder for the current subject
      for ((sessionId ,sessionInfo) <- titleWithIdSession) {

       // var file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
        //var Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        var Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
        import scala.collection.JavaConversions._
        for (( signalId, fileInfo) <- Signals) {
          //val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
          val SessionName: String = sessionInfo.getTitle
          if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")) {
            if (!fileInfo.getTitle.contains("~")) {
              var extension = fileInfo.getFileExtension
              if (SignalType.isActivity(extension)) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val temp: util.ArrayList[Activity] = ReadExcelJava.readActivity(input)
                import scala.collection.JavaConversions._
                for (ac <- temp) {
                  if (ac.actionType == 5) {
                    //startofLoadedDriveSum += ac.startTime
                    //startofLoadedDriveCtr += 1
                    sent = true;
                    sender() ! StartOfLoadedDrive(ac.startTime)
                  }
                }
              }
            }
          }
        }
      }

      if(!sent)
        sender() !  StartOfLoadedDrive(-100.0)
    case CreatePortraitForAsubject(service , subject , subName, username, failureThreshold ,  bio_code ) => {
      var baseLineSignalsStress: util.ArrayList[String] = new util.ArrayList()
      var baseLineSignalsPerformance: util.ArrayList[String] = new util.ArrayList()
      val file0 = GoogleDrive.waitUntilGetDGFile(service, subject)


      //var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      val titleWithIdSession = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);

      // get all the session folder inside the current subject
    /*  var titleWithIdSession = new TreeMap[String, String]


      for (i <- 0 until sessions.size())
        titleWithIdSession += GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle() -> sessions.get(i)

     */
      sender() ! SubjectID(file0.getTitle())

      var signalsForIndicator: List[SessionsBar] = List()
      var signalsForPerformance: List[SessionsBar] = List()



      //Iterator itSession = titleWithIdSession.entrySet().iterator();

      var ld1FileName: String = null;
      var ld1SessionName: String = null;
      var fdFilenmae: String = null;
      var fdSessionName: String = null;
      var fdActList: util.ArrayList[Activity] = null

      for (( sessionId, sessionInfo) <- titleWithIdSession) {

        //val file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
        //var signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
        import scala.collection.JavaConversions._
        for ((signalId, signalInfo) <- signals) {
          //val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
          val SessionName: String = sessionInfo.getTitle
          if (!signalInfo.getTitle.contains("~")) {
            val extension = signalInfo.getFileExtension
            if (extension.equalsIgnoreCase("sim2")) {
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd")) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                baseLineSignalsPerformance.add(GoogleDrive.generateFileNameFromInputStream(input))
                if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1")) {
                  //signalsForPerformance.add(new GoogleDrive.SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId))
                  signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
                }
              }
              else if (!org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl"))
                signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
              //signalsForPerformance.add(new GoogleDrive.SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId))
            }
            if (SignalType.isPerspiration(extension) || SignalType.isNPerspiration(extension)) {
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd")) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                baseLineSignalsStress.add(GoogleDrive.generateFileNameFromInputStream(input))
              }
              else if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                fdFilenmae = GoogleDrive.generateFileNameFromInputStream(input)
                fdSessionName = SessionName
              }
              else {
                if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1")) {
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                  ld1FileName = GoogleDrive.generateFileNameFromInputStream(input)
                  ld1SessionName = SessionName
                }
                //else signalsForIndicator.add(new GoogleDrive.SessionsBar(SessionName, file2.getId))
                else signalsForIndicator = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicator
              }
            }
            if (SignalType.isActivity(extension) && org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")) {
              fdActList = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, signalInfo.getId))
            }
          }
        }
      }
      // Get the stress indicator for all sessions of the current subject
      //double threshold =  tws.threshold;
      var temp: MeanAndSizeOfSignal = null
      var threshold: Double = .0
      if (ld1FileName != null) {
        temp = ReadExcelJava.findMeanFromExcel(ld1FileName)
        threshold = temp.mean
      }
      else {
        threshold = 0
      }
      val tws = AddNewStudy.getStressThreshold(baseLineSignalsStress, threshold)
      var tt: TreeMap[String, BarPercentage] = getPortraitStateIndiactors(username, threshold, signalsForIndicator, SharedData.GOOGLE_DRIVE, 4)
      //tt.put("0TL",  new BarPercentage(0.0, 100, 0.0));
      if (ld1SessionName != null) //tt.put(ld1SessionName, new BarPercentage(0.0, 100, 0.0))
        tt += ld1SessionName -> new BarPercentage(0.0, 100, 0.0)
      //System.out.println("Threshold: " + failureThreshold + "*********************")
      val bp: BarPercentage = ReadExcelJava.findBarFromExcelWithActivity(failureThreshold, fdFilenmae, fdActList)
      //System.out.println("Normal: " + bp.normal + ">>>>>  " + bp.stressed)
      tt += fdSessionName -> bp

      sender() ! Stress(subName,tt)

      SubjectNames = file0.getTitle :: SubjectNames
      /////////////////////////////Calculate for Dr. P///////////////////////////////////////////
            /*double max =0;
      for( SessionsBar tem : signalsForIndicator) {
        InputStream input = downloadFileByFileId(service, tem.location);
        MeanAndSizeOfSignal t = ReadExcelJava.findMeanFromExcel(generateFileNameFromInputStream(input));
        double mean = t.mean;
        if(mean-threshold > max) max= mean-threshold;
      }generateGenderForPortrait

      maxes.add(max);
      names.add(file0.getTitle());*/
      ///////////////////////////////////////////////////////////////////////////////////////////
      // Get the perfromance for all sessions of the current subject
      var tt2 = getPortraitPerformance(username,  AddNewStudy.getPerformanceThreshold(baseLineSignalsPerformance), signalsForPerformance, SharedData.GOOGLE_DRIVE, 4)
      tt2 += "TL" -> 0.0
      sender() ! Perf(subName,tt2)
      sender() ! PsycoMsg(subName, generatePsychometricForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'")))
      sender() ! Gender(subName, generateGenderForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), bio_code))
      sender() ! ChildDone(subName)
    }
  }

  def returnFilesInFolder(service: Drive, folderId: String, query: String): List[String] = {
    var result: List[String] = List()
    val request= service.children.list(folderId)
    val randomGenerator = new Random();
    request.setQ(query)
    do {
      try {
        var exit: Boolean = false

        for(n <- 1 to 7 if !exit){
          try {
            val children: ChildList = request.execute
            import scala.collection.JavaConversions._
            for (child <- children.getItems) {
              //result(child.getId)
              result = child.getId :: result
            }
            request.setPageToken(children.getNextPageToken)
            exit = true;
          }
          catch {
            case e:GoogleJsonResponseException =>
              println("An error inside returnFilesInforder in Class ScanSubjectPortrait" + e.getMessage)
              if (((e.getDetails.getErrors.get(0).getReason == "rateLimitExceeded") || (e.getDetails.getErrors.get(0).getReason == "userRateLimitExceeded"))) {
                try {
                  Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001))
                }
                catch {
                  case e2: InterruptedException =>
                    Logger.error("got interrupted!" + e2)
                }
              }
            case e: Exception => println("An error inside returnFilesInforder in Class ScanSubjectPortrait" + e.getMessage)
          }
        }

      }
      catch {
        case e: IOException => {
          Logger.error("An error occurred in returnFilesInFolder in ScanSubjectPortriat function: " + e.getMessage)
          request.setPageToken(null)
        }
      }
    } while (request.getPageToken != null && request.getPageToken.length > 0)
    return result
  }

  def generateGenderForPortrait(service: Drive, infos: List[String], bio_code: Int): String = {
    var gender: String = ""
    import scala.collection.JavaConversions._
    for (info <- infos) {
      var fileInfo: File = null
          //fileInfo = service.files.get(info).execute
      fileInfo = GoogleDrive.waitUntilGetDGFile(service, info)
      if (!fileInfo.getTitle.contains("~")) //continue //todo: continue is not supported
      {
        val extension: String = fileInfo.getFileExtension
        if (SignalType.isInfo(extension)) {
          val input: InputStream = GoogleDrive.downloadFileByFileId(service, fileInfo.getId)
          if (input != null) {
            val scanner: Scanner = new Scanner(input)
            val bio: Biography = AuxiliaryMethods.BiographyCode(bio_code)
            breakable {
              while (scanner.hasNextLine) {
               // if (bio.Gender == 1) {
                  var subjectGender: String = scanner.nextLine.toString
                  if (subjectGender.toLowerCase == "male") subjectGender = "M"
                  else if (subjectGender.toLowerCase == "female") subjectGender = "F"
                  gender = subjectGender
                  break //todo: break is not supported
               // }
                break //todo: break is not supported
              }
            }
            scanner.close
          }
        }
      }
    }

    return gender
  }

  def getPerformanceThreshold (list: util.ArrayList[String]) : Double = {


    val max: Double = 0
    var sum: Double = 0
    var counter: Double = 0
    import scala.collection.JavaConversions._
    for (fileName <- list) {
      val temp: MeanAndSizeOfSignal = ReadExcelJava.findAbsoluteMeanFromExcel(fileName)
      sum = sum + (temp.mean * temp.size)
      counter += temp.size
    }
    return sum / counter
  }


  def getPortraitPerformance(username: String, max: Double, url: List[SessionsBar], sourceType: Int, signalType: Int) : TreeMap[String, Double] = {

    val all: util.ArrayList[InputStream] = new util.ArrayList[InputStream]
    val signal: TreeMap[String, String] = new TreeMap[String, String]
    val resl: util.ArrayList[TreeMap[String, BarPercentage]] = new util.ArrayList[TreeMap[String, BarPercentage]]
    var sessionCutoff: TreeMap[String, Double] = new TreeMap[String, Double]
    if (sourceType == SharedData.LOCALSERVER) {
      return sessionCutoff
    }
    else {
      try {
        val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
        val service: Drive = GoogleDrive.buildService(googleCredential)
        var i: Int = 0
        for(i <- 0 until url.size){
          {
            val input: InputStream = GoogleDrive.downloadFileByFileId(service, url(i).location)
            //sessionCutoff.put(url.get(i).name, ReadExcelJava.findPerformanceFromExcel(max, generateFileNameFromInputStream(input)))
            val temp = ReadExcelJava.findPerformanceFromExcel(max, GoogleDrive.generateFileNameFromInputStream(input))
            sessionCutoff += url(i).name -> temp
          }
        }
      }
      catch {
        case e: IndexOutOfBoundsException => {
          Logger.error("In Bar method-Outer loop: index out of bound")
        }
      }
      return sessionCutoff
    }
  }

  //@throws(classOf[IOException])
  //@throws(classOf[Exception])
  def generatePsychometricForPortrait(service: Drive, infos: List[String]): TreeMap[String, Double] = {
    var sessionCutoff: TreeMap[String, Double] = new TreeMap[String, Double]
    val gender: String = ""
    import scala.collection.JavaConversions._
    for (info <- infos) {
      var fileInfo: File = null

          //fileInfo= service.files.get(info).execute
          fileInfo = GoogleDrive.waitUntilGetDGFile(service, info)

      if (! fileInfo.getTitle.contains("~"))
      {
        val extension: String = fileInfo.getFileExtension
        if (SignalType.isBar(extension)) {
          val input: InputStream = GoogleDrive.downloadFileByFileId(service, fileInfo.getId)
          if (input != null) {
            val tmp = ReadExcelJava.findTotalNASA(11, GoogleDrive.generateFileNameFromInputStream(input))
            if (tmp.size() > 0) sessionCutoff += "ND" -> tmp(0)  //sessionCutoff.put("ND", tmp(0))
            if (tmp.size() > 1) sessionCutoff += "LD1" -> tmp(1)   //sessionCutoff.put("LD1", tmp(1))
            if (tmp.size() > 2) sessionCutoff += "LD2" -> tmp(2)   //sessionCutoff.put("LD2", tmp(2))
            if (tmp.size() > 3) sessionCutoff += "LD3" -> tmp(3)   //sessionCutoff.put("LD3", tmp(3))
            if (tmp.size() > 4) sessionCutoff += "LD4" -> tmp(4)   //sessionCutoff.put("LD4", tmp(4))
            if (tmp.size() > 5) sessionCutoff += "FD" -> tmp(5)  //sessionCutoff.put("FD", tmp(5))
          }
          //break //todo: break is not supported
        }
      }
    }
    return sessionCutoff
  }


 // @throws(classOf[IOException])
 // @throws(classOf[Exception])
  def getPortraitStateIndiactors(username :String, max: Double, url: List[SessionsBar], sourceType: Int, signalType: Int) : TreeMap[String, BarPercentage] = {
    val all: util.ArrayList[InputStream] = new util.ArrayList[InputStream]
    val signal: TreeMap[String, String] = new TreeMap[String, String]
    val resl: util.ArrayList[TreeMap[String, BarPercentage]] = new util.ArrayList[TreeMap[String, BarPercentage]]
    var sessionCutoff: TreeMap[String, BarPercentage] = new TreeMap[String, BarPercentage]
    if (sourceType == SharedData.LOCALSERVER) {
      return sessionCutoff
    }
    else {
      try {
        val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
        val service: Drive = GoogleDrive.buildService(googleCredential)
        var i: Int = 0
        while (i < url.size) {
          {
            val input: InputStream = GoogleDrive.downloadFileByFileId(service, url(i).location)
            // sessionCutoff.put(url.get(i).name, ReadExcelJava.findBarFromExcel(max, generateFileNameFromInputStream(input)))
            sessionCutoff += url(i).name -> ReadExcelJava.findBarFromExcel(max, GoogleDrive.generateFileNameFromInputStream(input))
          }
          ({
            i += 1; i - 1
          })
        }
      }
      catch {
        case e: IndexOutOfBoundsException => {
          Logger.error("In Bar method-Outer loop: index out of bound")
        }
      }
      sessionCutoff
    }
  }
}
