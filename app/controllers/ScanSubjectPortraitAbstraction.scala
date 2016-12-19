package controllers

/**
 * Created by staamneh on 12/4/2015.
 */

import java.io.{IOException, InputStream}
import java.text.DecimalFormat
import java.util
import java.util.{Scanner, Iterator}
import Models._
import akka.actor.Actor
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.services.drive.model.{ChildList, File}
import org.json.simple.{JSONArray, JSONObject}
import play.Logger
import scala.collection.{JavaConverters, JavaConversions}
import scala.util.Random
import scala.util.control.Breaks._

import com.google.api.services.drive.Drive

import scala.collection.immutable.TreeMap
import play.api.libs.json.{JsObject, JsArray, Json}

import controllers.CreatePortraitMessages._

import scala.collection.JavaConverters._
import scala.collection.immutable.TreeMap


class ScanSubjectPortraitAbstraction extends Actor {


  var SubjectNames: List[String] = List()

  /// to be deleted


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

    case FindNewMultiModal(service, subject, subName, definedVar) =>

      var js: JsArray = Json.arr();
      var sent = false;
      var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      // get all the session folder inside the current subject
      var titleWithIdSession: TreeMap[String, String] = TreeMap.empty
      var maxValPers = -1000.0;
      var maxValEda = -1000.0;
      var maxValHrv = -1000.0;

      for (i <- 0 until sessions.size()) {
        titleWithIdSession += GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle.replaceFirst("(\\d*\\s*)", "") -> sessions.get(i)
      }
      // Iterate over the session folder for the current subject
      val name = "Session"
      var counter = 1;
      var tempMap: TreeMap[String, Map[String, Double]] = TreeMap.empty[String, Map[String, Double]]
      for ((sessionName, sessionId) <- titleWithIdSession) {
        var nameNoNumber = sessionName.replaceFirst("(\\d*\\s*)", "").toLowerCase
        if (!nameNoNumber.equals("bl") && !nameNoNumber.equals("pd") && !nameNoNumber.equals("nd")) {
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

            if (!file2.getTitle.contains("~") && file2.getFileExtension != null) {


              var extension = file2.getFileExtension
              if (definedVar.contains(extension)) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, file2.getId)
                val x = ReadExcelJava.findMeanFromExcel(GoogleDrive.generateFileNameFromInputStream(input))
                innerMap += extension -> x.mean
              }

            }

          }
          tempMap += sessionName -> innerMap
        }
      }

      var maxes: TreeMap[String, Double] = TreeMap.empty
      for ((key, value) <- tempMap) {
        for ((key2, value2) <- value) {

          if (maxes.contains(key2)) {
            if (maxes(key2) < value2)
              maxes += key2 -> value2
          }
          else
            maxes += key2 -> value2
        }
      }



      for ((key, value) <- tempMap) {
        var jsTemp: JsArray = Json.arr();
        // just to take care of situations where no singla avaialbe

        for ((exte, fnl) <- maxes) {
          if (!value.contains(exte))
            jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> 0.0))
          else {
            if (maxes(exte) > 0)
              jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> new DecimalFormat("#.####").format(value(exte) / maxes(exte)).toDouble * 10000))
            else
              jsTemp = jsTemp.:+(Json.obj("axis" -> exte, "value" -> fnl.toDouble))
          }
        }



        js = js.:+(Json.arr(
          Json.obj("className" -> key,
            "axes" -> jsTemp)
        ));
      }



     // println(js);


      DataBaseOperations.InsertSubjectRadar(subName, js.toString())
      sender() ! RadarForSorting(subName, Json.obj(subName -> js))

    case FindPoint(service, subject) =>
      var sent = false;

      val titleWithIdSession = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      // Iterate over the session folder for the current subject
      for ((sessionId, sessionInfo) <- titleWithIdSession) {

        // var file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
        //var Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        var Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        import scala.collection.JavaConversions._
        for ((signalId, fileInfo) <- Signals) {
          //val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
          val SessionName: String = sessionInfo.getTitle
          if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")) {
            if (!fileInfo.getTitle.contains("~") && fileInfo.getFileExtension != null) {
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

      if (!sent)
        sender() ! StartOfLoadedDrive(-100.0)

    /*case CreatePortraitForAsubjectAbstractionWithSummary(service, subject, subName, userName, abst,study_no ) => {


      ///////////
      var pointsForAllSession = Map.empty[String, Map[String, Double]]
      var totoalSubjectPointsWithResponse : Map[String, Map[String,  Map[String, Double]]] = Map.empty
      var numberOfPoints = 0;
      var sessionNo =0;

      var sessionFileNames = Map.empty[String, (scala.collection.Map[java.lang.Double, java.lang.Double], scala.collection.Map[java.lang.Double, java.lang.Double],java.util.ArrayList[Activity]) ]
      var blSignal: scala.collection.Map[java.lang.Double, java.lang.Double] = null; // to save the baseline signal
      var blSignalSecondary: scala.collection.Map[java.lang.Double, java.lang.Double] = null; // to save the baseline signal
      var activity : java.util.ArrayList[Activity] = null;
      var session_no: Int = 1
      //////////////

      var baseLineSignalsStress: util.ArrayList[String] = new util.ArrayList()
      var baseLineSignalsPerformance: util.ArrayList[String] = new util.ArrayList()
      val file0 = GoogleDrive.waitUntilGetDGFile(service, subject)

      //var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      val titleWithIdSession = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      sender() ! SubjectID(file0.getTitle())

      var signalsForIndicator: List[SessionsBar] = List()
      var signalsForPerformance: List[SessionsBar] = List()



      //Iterator itSession = titleWithIdSession.entrySet().iterator();

      var ld1FileName: String = null;
      var ld1SessionName: String = null;
      var fdFilenmae: String = null;
      var fdSessionName: String = null;
      var fdActList: util.ArrayList[Activity] = null

      for ((sessionId, sessionInfo) <- titleWithIdSession) {

        //val file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
        //var signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        import scala.collection.JavaConversions._
        for ((signalId, signalInfo) <- signals) {

            var SessionName: String = sessionInfo.getTitle
            SessionName = SessionName.replaceFirst("(\\d*\\s*)", "")
            if(SessionName.equalsIgnoreCase(abst.baseLine))
            {

                if (!signalInfo.getTitle.contains("~")) {
                  val extension = signalInfo.getFileExtension
                  if (extension.equalsIgnoreCase(abst.primaryRes)) {
                    val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                    val name = GoogleDrive.generateFileNameFromInputStream(input)
                    blSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(name, abst.explColNo)

                    if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, abst.baseLine)) {
                      val name2 = GoogleDrive.generateFileNameFromInputStream(input)
                      baseLineSignalsPerformance.add(name2)
                    }
                    else {
                      signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
                    }
                  }
                  if (extension.equals(abst.primaryExp)) {
                    val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                    val name = GoogleDrive.generateFileNameFromInputStream(input)
                    blSignalSecondary = ReadExcelJava.getAllSignalFromExcelAbstraction(name, abst.respColNo)
                    if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, abst.baseLine)) {
                      val name2 = GoogleDrive.generateFileNameFromInputStream(input)
                      baseLineSignalsStress.add(name2)
                    }
                    else {
                      signalsForIndicator = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicator
                    }
                  }

                }

          }
            else if(! SessionName.equalsIgnoreCase("fdl") && !SessionName.equalsIgnoreCase("fdn")){  //This is onaly a special case for driving study
            var thereIsAc = false;
              var fileName ="";
              var fileNameSecondary ="";

              for((signalId,signalInfo) <- signals){ // iterate over signals
                if(SignalType.isActivity(signalInfo.getFileExtension)){  // works for both .activity or .stm
                  thereIsAc = true
                  activity = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, signalId))
                }
                else if(signalInfo.getFileExtension.equalsIgnoreCase(abst.primaryExp)){
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                  fileName = GoogleDrive.generateFileNameFromInputStream(input)
                  signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
                }
                else if(signalInfo.getFileExtension.equalsIgnoreCase(abst.primaryRes)){
                  val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                  fileNameSecondary = GoogleDrive.generateFileNameFromInputStream(input)
                  signalsForIndicator = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicator
                }
              }
              if(thereIsAc && fileName != "" && fileNameSecondary != ""){ // only if there is a explor and response variables
                sessionFileNames +=  SessionName -> (ReadExcelJava.getAllSignalFromExcelAbstraction(fileName, abst.explColNo).asScala.mapValues(x=>x),
                  ReadExcelJava.getAllSignalFromExcelAbstraction(fileNameSecondary, abst.respColNo).asScala.mapValues(x=>x), activity); // for each session that has activity file
              }

            }
          else
          {
            for((signalId,signalInfo) <- signals){

              if (!signalInfo.getTitle.contains("~")) {
                val extension = signalInfo.getFileExtension
                if (extension.equalsIgnoreCase(abst.primaryRes)) {

                    signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance

                }
                if (extension.equals(abst.primaryExp)) {

                    signalsForIndicator = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicator

                }

              }

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


      if(baseLineSignalsStress.size() >0){
        temp = ReadExcelJava.findMeanFromExcel(baseLineSignalsStress.get(0))
        threshold = temp.mean
      }

      var tt: TreeMap[String, BarPercentage] = getPortraitStateIndiactors(abst.userName, threshold, signalsForIndicator, GOOGLE_DRIVE, 4)



      sender() ! Stress(subName, tt)

      SubjectNames = file0.getTitle :: SubjectNames

      // Get the perfromance for all sessions of the current subject
      var tempTS = AddNewStudy.getPerformanceThresholdAbstraction(baseLineSignalsPerformance, abst.respColNo)
      // println("Sho 6le3 threadshold  "  +tempTS );

      var tt2 = getPortraitPerformance(abst.userName,tempTS, signalsForPerformance, GOOGLE_DRIVE, 4, abst.respColNo)
      tt2 += "TL" -> 0.0
      sender() ! Perf(subName, tt2)
      sender() ! PsycoMsg(subName, generatePsychometricForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'")))
      sender() ! Gender(subName, generateGenderForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), 111))
      sender() ! ChildDone(subName)

      /////
      // we only do caculation if there is a baseline for explanatory and response varialbes
      if( blSignal!=null && blSignalSecondary!=null ){
        for((name, data) <- sessionFileNames){

          var pointsPerSession = Map.empty[String, Double]
          var pointsPerSessionResponse = Map.empty[String, Double]
          var pointsInBL = Map.empty[String, Double];
          var pointsInBLResponse = Map.empty[String, Double];
          var pointNoTemp = 0;
          var previous =0.0;
          for(act  <- data._3) { // find the corresponding point in basline
            //////////////////////before the start point unitl the beinging of the current start point ///////////////
            pointsInBL += pointNoTemp.toString -> findMeanOfInterval(blSignal, previous,act.startTime, false )
            pointsInBLResponse += pointNoTemp.toString -> findMeanOfInterval(blSignalSecondary, previous,act.startTime, true )
            pointNoTemp = pointNoTemp + 1;
            previous = act.endTime
            //////////////////////////////////////////////////////////////////////////////////////////
            pointsInBL += pointNoTemp.toString -> findMeanOfInterval(blSignal, act.startTime,act.endTime, false )
            pointsInBLResponse += pointNoTemp.toString -> findMeanOfInterval(blSignalSecondary, act.startTime,act.endTime, true )
            pointNoTemp = pointNoTemp + 1;
          }
          ////////////////////from the last end time to the end
          pointsInBL += pointNoTemp.toString -> findMeanOfInterval(blSignal, previous, -1, false)
          pointsInBLResponse += pointNoTemp.toString -> findMeanOfInterval(blSignalSecondary, previous,-1, true )

          numberOfPoints = pointNoTemp


          ///////////////////// the explantory interval
          var pointNo = 0;
          var previous2 =0.0;
          for(act <-  data._3) {
            ///////////////////////////////   for the interval between points
            pointsInBL.get(pointNo.toString)  match
            { case Some(x) =>
              pointsPerSession += pointNo.toString -> (Math.log(findMeanOfInterval(data._1, previous2, act.startTime, false)) - Math.log(x)) // distract the value from the correspoingd value in baseline
            case None =>
            }

            pointsInBLResponse.get(pointNo.toString)  match
            { case Some(x) =>
              pointsPerSessionResponse += pointNo.toString -> (Math.log(findMeanOfInterval(data._2, previous2, act.startTime, true)) - Math.log(x)) // distract the value from the correspoingd value in baseline
            case None =>
            }

            pointNo = pointNo + 1;
            previous2 = act.endTime
            pointsInBL.get(pointNo.toString)  match
            {
              case Some(x) =>
                pointsPerSession += pointNo.toString ->  (Math.log(findMeanOfInterval(data._1, act.startTime, act.endTime, false)) - Math.log(x)) // distract the value from the correspoingd value in baseline
              case None =>
            }
            pointsInBLResponse.get(pointNo.toString)  match
            { case Some(x) =>
              pointsPerSessionResponse += pointNo.toString -> (Math.log(findMeanOfInterval(data._2,  act.startTime, act.endTime, true)) - Math.log(x)) // distract the value from the correspoingd value in baseline
            case None =>
            }

            pointNo = pointNo + 1;
          }
          ////////////////////from the last end time to the end
          pointsInBL.get(pointNo.toString)  match
          {
            case Some(x) =>
              pointsPerSession += pointNo.toString -> (Math.log(findMeanOfInterval(data._1, previous2, -1, false)) - Math.log(x)) // distract the value from the correspoingd value in baseline
            case None =>
          }
          pointsInBLResponse.get(pointNo.toString)  match
          { case Some(x) =>
            pointsPerSessionResponse += pointNo.toString -> (Math.log(findMeanOfInterval(data._2,  previous2, -1, true)) - Math.log(x)) // distract the value from the correspoingd value in baseline
          case None =>
          }

          totoalSubjectPointsWithResponse += name -> Map("E"->pointsPerSession, "R" -> pointsPerSessionResponse)
          //pointsForAllSession += name ->pointsPerSession
        }

      }
      else{
        Logger.debug("Shoooooooooooooooooooof this subject has no sim2 files+ " +  file0.getTitle)
      }
      sender() ! OneSubjectPointsDone(study_no, totoalSubjectPointsWithResponse, numberOfPoints+1);

      /////





    }*/
    case CreatePortraitForAsubjectAbstraction(service, subject, subName, userName, abst, descriptor) => {

      var baseLineSignalsStress: util.ArrayList[String] = new util.ArrayList()
      var tempbaseLineSignalsStress: util.ArrayList[String] = new util.ArrayList()// this is used to handle situation when np is measured not pp

      var baseLineSignalsPerformance: util.ArrayList[String] = new util.ArrayList()
      val file0 = GoogleDrive.waitUntilGetDGFile(service, subject)

      //var sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")

      val titleWithIdSession = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      sender() ! SubjectID(file0.getTitle())

      var signalsForIndicator: List[SessionsBar] = List()
      var signalsForIndicatorTemp: List[SessionsBar] = List()

      var signalsForPerformance: List[SessionsBar] = List()



      //Iterator itSession = titleWithIdSession.entrySet().iterator();

      var ld1FileName: String = null;
      var ld1SessionName: String = null;
      var fdFilenmae: String = null;
      var fdSessionName: String = null;
      var fdActList: util.ArrayList[Activity] = null
      var sessionsWithStreesBL: TreeMap[String, BarPercentage] = TreeMap.empty

      for ((sessionId, sessionInfo) <- titleWithIdSession) {

        //val file = GoogleDrive.waitUntilGetDGFile(service, sessionId)
        //var signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        import scala.collection.JavaConversions._
        for ((signalId, signalInfo) <- signals) {
          //val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
          val SessionName: String = sessionInfo.getTitle
          if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {
            val extension = signalInfo.getFileExtension




            if (extension.equalsIgnoreCase(abst.primaryRes)) {
              //println("*************************+" + abst.primaryRes)
              if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, abst.baseLine)) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                baseLineSignalsPerformance.add(GoogleDrive.generateFileNameFromInputStream(input))
                signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
              }
              else {
                signalsForPerformance = new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), signalInfo.getId) :: signalsForPerformance
                //signalsForPerformance.add(new GoogleDrive.SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId))
              }
            }

             if(extension.equalsIgnoreCase(abst.primaryExp)) {
                if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, abst.baseLine)) {
                    val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                    baseLineSignalsStress.add(GoogleDrive.generateFileNameFromInputStream(input))
                    sessionsWithStreesBL += SessionName -> new BarPercentage(0, 100, 0)
                }
                else {
                  //else signalsForIndicator.add(new GoogleDrive.SessionsBar(SessionName, file2.getId))
                    signalsForIndicator = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicator
                }
              }

            if(extension.equalsIgnoreCase("np") ) {  // this is to keep track of "NP" and we check later if no PP we use this insted
              if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, abst.baseLine)) {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                tempbaseLineSignalsStress.add(GoogleDrive.generateFileNameFromInputStream(input))
                sessionsWithStreesBL += SessionName -> new BarPercentage(0, 100, 0)
                  }
                else {
                //else signalsForIndicator.add(new GoogleDrive.SessionsBar(SessionName, file2.getId))
                signalsForIndicatorTemp = new SessionsBar(SessionName, signalInfo.getId) :: signalsForIndicatorTemp
              }

              }

            }
          }
        }
        // Get the stress indicator for all sessions of the current subject
        //double threshold =  tws.threshold;
        var temp: MeanAndSizeOfSignal = null
        var threshold: Double = .0


      if(baseLineSignalsStress.size() >0){
        temp = ReadExcelJava.findMeanFromExcel(baseLineSignalsStress.get(0))
        threshold = temp.mean
      }
      else if (tempbaseLineSignalsStress.size() >0){
        signalsForIndicator = signalsForIndicatorTemp
        temp = ReadExcelJava.findMeanFromExcel(tempbaseLineSignalsStress.get(0))
        threshold = temp.mean

      }

        var sessionsWithStrees: TreeMap[String, BarPercentage] = getPortraitStateIndiactors(abst.userName, threshold, signalsForIndicator, SharedData.GOOGLE_DRIVE, 4)

        for((key, value) <- sessionsWithStreesBL){
          sessionsWithStrees += key -> value
        }

        sender() ! Stress(subName, sessionsWithStrees)

        SubjectNames = file0.getTitle :: SubjectNames
        /////////////////////////////Calculate for Dr. P///////////////////////////////////////////
        /*double max =0;
        for( SessionsBar tem : signalsForIndicator) {
          InputStream input = downloadFileByFileId(service, tem.location);
          MeanAndSizeOfSignal t = ReadExcelJava.findMeanFromExcel(generateFileNameFromInputStream(input));
          double mean = t.mean;
          if(mean-threshold > max) max= mean-threshold;
        }

        maxes.add(max);
        names.add(file0.getTitle());*/
        ///////////////////////////////////////////////////////////////////////////////////////////
        // Get the perfromance for all sessions of the current subject
      var tempTS = AddNewStudy.getPerformanceThresholdAbstraction(baseLineSignalsPerformance, abst.respColNo)
         // println("Sho 6le3 threadshold  "  +tempTS );

        var tt2 = getPortraitPerformance(abst.userName,tempTS, signalsForPerformance, SharedData.GOOGLE_DRIVE, 4, abst.respColNo)
        //tt2 += "TL" -> 0.0
        sender() ! Perf(subName, tt2)

        sender() ! PsycoMsg(subName, generatePsychometricForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), descriptor))
        sender() ! Gender(subName, generateGenderForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), 111))
        sender() ! ChildDone(subName)
      }
    }


      def returnFilesInFolder(service: Drive, folderId: String, query: String): List[String] = {
        var result: List[String] = List()
        val request = service.children.list(folderId)
        val randomGenerator = new Random();
        request.setQ(query)
        do {
          try {
            var exit: Boolean = false

            for (n <- 1 to 7 if !exit) {
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
                case e: GoogleJsonResponseException =>
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
          if (!fileInfo.getTitle.contains("~") && fileInfo.getFileExtension != null) //continue //todo: continue is not supported
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

      def getPerformanceThreshold(list: util.ArrayList[String]): Double = {


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


      def getPortraitPerformance(username: String, max: Double, url: List[SessionsBar], sourceType: Int, signalType: Int, responseColNum: Int): TreeMap[String, Double] = {

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
            for (i <- 0 until url.size) {
              {
                val input: InputStream = GoogleDrive.downloadFileByFileId(service, url(i).location)
                //sessionCutoff.put(url.get(i).name, ReadExcelJava.findPerformanceFromExcel(max, generateFileNameFromInputStream(input)))
                val temp = ReadExcelJava.findPerformanceFromExcelAbstraction(max, GoogleDrive.generateFileNameFromInputStream(input), responseColNum)
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
      def generatePsychometricForPortrait(service: Drive, infos: List[String], descriptor : java.util.TreeMap[String, String]): TreeMap[String, Double] = {

        var sessionCutoff: TreeMap[String, Double] = new TreeMap[String, Double]
        val gender: String = ""
        import scala.collection.JavaConversions._
        for (info <- infos) {
          var fileInfo: File = null

          //fileInfo= service.files.get(info).execute
          fileInfo = GoogleDrive.waitUntilGetDGFile(service, info)

          if (!fileInfo.getTitle.contains("~") && fileInfo.getFileExtension != null) {
            val extension: String = fileInfo.getFileExtension
            if (SignalType.isBar(extension)) {
              val input: InputStream = GoogleDrive.downloadFileByFileId(service, fileInfo.getId)
              if (input != null) {
                //val tmp = ReadExcelJava.findTotalNASA(11, GoogleDrive.generateFileNameFromInputStream(input))
                    val tmp = ReadExcelJava.findTotalNASAWithHeader(11, GoogleDrive.generateFileNameFromInputStream(input),descriptor)
                    for((key, value) <- tmp)
                    {
                      sessionCutoff += key -> value
                    }
                //sessionCutoff = tmp;

                /*if (tmp.size() > 0) sessionCutoff += "ND" -> tmp(0) //sessionCutoff.put("ND", tmp(0))
                if (tmp.size() > 1) sessionCutoff += "LD1" -> tmp(1) //sessionCutoff.put("LD1", tmp(1))
                if (tmp.size() > 2) sessionCutoff += "LD2" -> tmp(2) //sessionCutoff.put("LD2", tmp(2))
                if (tmp.size() > 3) sessionCutoff += "LD3" -> tmp(3) //sessionCutoff.put("LD3", tmp(3))
                if (tmp.size() > 4) sessionCutoff += "LD4" -> tmp(4) //sessionCutoff.put("LD4", tmp(4))
                if (tmp.size() > 5) sessionCutoff += "FD" -> tmp(5) //sessionCutoff.put("FD", tmp(5))*/

                /*if (tmp.size() > 0) sessionCutoff += "Practice-Drive" -> tmp(0) //sessionCutoff.put("ND", tmp(0))
                if (tmp.size() > 1) sessionCutoff += "Motoric-Drive" -> tmp(1) //sessionCutoff.put("LD1", tmp(1))
                if (tmp.size() > 2) sessionCutoff += "Cognitive-Drive" -> tmp(2) //sessionCutoff.put("LD2", tmp(2))
                if (tmp.size() > 3) sessionCutoff += "Cognitive-Failure-Drive" -> tmp(3) //sessionCutoff.put("LD3", tmp(3))
                if (tmp.size() > 4) sessionCutoff += "Motoric-Failure-Drive" -> tmp(4) //sessionCutoff.put("LD4", tmp(4))
                if (tmp.size() > 5) sessionCutoff += "Normal-Failure-Drive" -> tmp(5) //sessionCutoff.put("FD", tmp(5))*/
              }
              //break //todo: break is not supported
            }
          }
        }
        return sessionCutoff
      }


  def findMeanOfInterval(interval: scala.collection.Map[java.lang.Double, java.lang.Double], start: Double, end: Double, abs: Boolean ): Double =
  {
    var tempMean = 0.0;
    val tempFirst= end match {
      case -1 => interval.filter {case (k,v) => (k >= start) }
      case _ => interval.filter {case (k,v) => (k >= start) && (k < end) }
    }

    //tempFirst.map{case (k,v) => tempMean = tempMean + v}
    if(abs)
      tempFirst.map{case (k,v) => tempMean = tempMean + Math.abs(v)}
    else
      tempMean= tempFirst.foldLeft(0.0)(_+_._2) // sum all the number

    tempMean = tempMean/ tempFirst.size

    return tempMean
    /*pointsInBL += pointNoTemp.toString -> tempMean
    pointNoTemp = pointNoTemp + 1;
    previous = act.endTime*/
  }

      // @throws(classOf[IOException])
      // @throws(classOf[Exception])
      def getPortraitStateIndiactors(username: String, max: Double, url: List[SessionsBar], sourceType: Int, signalType: Int): TreeMap[String, BarPercentage] = {
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
                i += 1;
                i - 1
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


