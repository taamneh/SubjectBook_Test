package controllers

import java.io.InputStream
import java.net.SocketTimeoutException
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException

import scala.collection.immutable.TreeMap
import util.control.Breaks._

import Models._
import akka.actor.{Props, Actor}
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.{Permission, File}
import play.Logger
import util.Random
import scala.collection.JavaConversions._
import scala.concurrent.duration._
import com.google.api.client.http.HttpResponseException;
import play.api.libs.concurrent.Execution.Implicits._
import scala.collection.JavaConverters._

/**
 * Created by staamneh on 6/11/2015.
 */
case class ProcessThisSubject (service: Drive, study_no: Int, studyTopology: StudyTopology ,bio_code: Int, Psycho_code: Int, physio_code: Int, perf_code: Int)
case class SubjectsInStudy(subjects: List[String], folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, perf_code: Int, createPortrait: Int, study_no: Int)
case class SubjectsInStudyAbstract(subjects: List[String], topology: Abstraction, study_no: Int)
class ScanSubject extends Actor{

  private val GOOGLE_DRIVE: Int = 1
  private val LOCALSERVER: Int = 2

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

  def receive = {
    case SubjectFolderAbstractSummary(subject, service, study_no, topology, userDefinedExtension) =>
      var pointsForAllSession = Map.empty[String, Map[String, Double]]
      var totoalSubjectPointsWithResponse : Map[String, Map[String,  Map[String, Double]]] = Map.empty


      var numberOfPoints = 0;
      var sessionNo =0;

      var sessionFileNames = Map.empty[String, (scala.collection.Map[java.lang.Double, java.lang.Double], scala.collection.Map[java.lang.Double, java.lang.Double],java.util.ArrayList[Activity]) ]
      var blSignal: scala.collection.Map[java.lang.Double, java.lang.Double] = null; // to save the baseline signal
       var blSignalSecondary: scala.collection.Map[java.lang.Double, java.lang.Double] = null; // to save the baseline signal
      val file0: File =getFolderId(service, subject);
      var activity : java.util.ArrayList[Activity] = null;

      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      var session_no: Int = 1

      // iterate over sessions
      for (( sessionId, sessionInfo) <- sessions) {

        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
        var file :File = sessionInfo
        var sessionName: String = sessionInfo.getTitle
        sessionName = sessionName.replaceFirst("(\\d*\\s*)", "")

        if(sessionName.equalsIgnoreCase(topology.baseLine))
          {

            println("We found the baseline folder")
            // save the explaontory varialbe file in somewere
            breakable {
              for ((signalId,signalInfo) <- Signals) {
                if (!signalInfo.getTitle.contains("~")) {

                  val extension: String = signalInfo.getFileExtension

                  if (extension.equalsIgnoreCase(topology.primaryExp)) {

                    println("We are saving the primary singal in baseline")
                      val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                      val name = GoogleDrive.generateFileNameFromInputStream(input)
                     blSignal = ReadExcelJava.getAllSignalFromExcelAbstraction( name, topology.explColNo)
                  }
                  else if(extension.equalsIgnoreCase(topology.primaryRes)){

                    println("We are saving the Response signal in baseline")
                    val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                    val name = GoogleDrive.generateFileNameFromInputStream(input)
                    blSignalSecondary = ReadExcelJava.getAllSignalFromExcelAbstraction( name, topology.respColNo)
                  }
                }
              }
            }
          }
        else if(! sessionName.equalsIgnoreCase("fdl") && !sessionName.equalsIgnoreCase("fdn")){  //This is onaly a special case for driving study
          var thereIsAc = false;
          var fileName ="";
          var fileNameSecondary ="";

          for((signalId,signalInfo) <- Signals){ // iterate over signals
            if(SignalType.isActivity(signalInfo.getFileExtension)){
              thereIsAc = true
              activity = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, signalId))
            }
            else if(signalInfo.getFileExtension.equalsIgnoreCase(topology.primaryExp)){
              val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
              fileName = GoogleDrive.generateFileNameFromInputStream(input)
            }
            else if(signalInfo.getFileExtension.equalsIgnoreCase(topology.primaryRes)){


              val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
              fileNameSecondary = GoogleDrive.generateFileNameFromInputStream(input)
            }
          }
          if(thereIsAc && fileName != "" && fileNameSecondary != ""){ // only if there is a explor and response variables
            sessionFileNames +=  sessionName -> (ReadExcelJava.getAllSignalFromExcelAbstraction(fileName, topology.explColNo).asScala.mapValues(x=>x),
              ReadExcelJava.getAllSignalFromExcelAbstraction(fileNameSecondary, topology.respColNo).asScala.mapValues(x=>x), activity); // for each session that has activity file
          }

        }
      }


      // we only do caculation if there is a baseline for explanatory and response varialbes
      if( blSignal!=null && blSignalSecondary!=null ){
        for((name, data) <- sessionFileNames){

          var pointsPerSession = Map.empty[String, Double]
          var pointsPerSessionResponse = Map.empty[String, Double]
          var pointsInBL = Map.empty[String, Double];
          var pointsInBLResponse = Map.empty[String, Double];
          var pointNoTemp = 0;
          var previous =0.0;
          for(act <- data._3) { // find the corresponding point in basline
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

    case SubjectFolderAbstract(subject, service, study_no, topology, userDefinedExtension) =>
      Logger.info("We started reading a new subject for Study No = " + study_no)
      var report : String ="";
      val file0: File =getFolderId(service, subject);
      DataBaseOperations.InsertSubjectGD(file0.getTitle, study_no, 111, 11, 11)
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      var session_no: Int = 1

      import scala.collection.JavaConversions._
      for (( sessionId, sessionInfo) <- sessions) {

        var file :File = sessionInfo
        var sessionName: String = sessionInfo.getTitle
        sessionName = sessionName.replaceAll("\\s+", "")

        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
        var signal_code: Int = 1
        breakable {
          for ((signalId,signalInfo) <- Signals) {

            var shouldICommit = true;

            if (!signalInfo.getTitle.contains("~")) {

              val extension: String = signalInfo.getFileExtension

              if(userDefinedExtension.contains(extension)){
                signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
              }

              else shouldICommit= false //todo: continue is not supported
              if(shouldICommit)
                DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, sessionName, signal_code, signalInfo.getId, 0)
            }
          }
        }
        session_no += 1

      }


      //Check the missing Signal
      val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        //var fileInfo: File = getFolderId(service, info)

        if (fileInfo.getTitle.contains("~")) break //todo: continue is not supported
        val extension: String = fileInfo.getFileExtension
        if(userDefinedExtension.contains(extension)){
          var signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "GENERAL", signal_code, fileInfo.getId, 1)
        }
      }

      sender() ! OneSubjectDoneAbstract(study_no)


    case SubjectFolder(subject, service, study_no, studyTopology ,bio_code, psycho_code, physio_code, perf_code, userDefinedExtension)=>
      Logger.info("We started reading a new subject for Study No = " + study_no)
      var report : String ="";
      val file0: File =getFolderId(service, subject);
      DataBaseOperations.InsertSubjectGD(file0.getTitle, study_no, bio_code, psycho_code, physio_code)
      //val sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'")
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      var session_no: Int = 1
      if (sessions.size > studyTopology.noOfSession) report = report + "Subject <" + file0.getTitle + "> Has more sessions than that specified\n"
      else if (sessions.size < studyTopology.noOfSession) report = report + "Subject<" + file0.getTitle + "> Has fewer sessions than that specified\n"
      import scala.collection.JavaConversions._
      for (( sessionId, sessionInfo) <- sessions) {
        var eda: Int = 0
        var motion: Int = 0
        var perspiration: Int = 0
        var thermal_breath: Int = 0
        var belt_breath: Int = 0
        var heart_rate: Int = 0
        val gsr: Int = 0

        //var file0 : File= getFolderId(service, subject)
        //var file :File = getFolderId(service,sessionId)
        var file :File = sessionInfo
        var sessionName: String = sessionInfo.getTitle
        sessionName = sessionName.replaceAll("\\s+", "")

        //val Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'")
        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
        var signal_code: Int = 1
        import scala.collection.JavaConversions._
        breakable {
          for ((signalId,signalInfo) <- Signals) {

            var shouldICommit = true;
            /*var passed: Boolean = false
            var file2: File = null
            while (!passed) {
              try {
                //file2 = service.files.get(signalId).execute
                file2 = getFolderId(service,signalId)
                passed = true
              }
              catch {
                case e: SocketTimeoutException => {
                  Logger.debug("We have socketTimeOutException Salah while search for signal")
                }
              }
            }*/
            if (!signalInfo.getTitle.contains("~")) {

              val extension: String = signalInfo.getFileExtension
              /*if (SignalType.isEda(extension)) {
                if (studyTopology.physiology.EDA != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getEDACode
                eda = 1
              }
              else if (SignalType.isMotion(extension)) {
                if (studyTopology.physiology.Motion != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getMotionCode
                motion = 1
              }
              else if (SignalType.isPerspiration(extension)) {
                if (studyTopology.physiology.Perspiration != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getPerspirationCode
                perspiration = 1
              }
              else if (SignalType.isBreathing(extension)) {
                if (studyTopology.physiology.Breathing_Thermal != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getBreathingCode
                thermal_breath = 1
              }
              else if (SignalType.isHeartRate(extension)) {
                if (studyTopology.physiology.Heart_Rate != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getHeartRateCode
                heart_rate = 1
              }
              else if (SignalType.isBeltBreathing(extension)) {
                if (studyTopology.physiology.Breathing_Belt != 1) shouldICommit= false //todo: continue is not supported
                signal_type = SignalType.getBeltBreathingCode
                belt_breath = 1
              }
              else if (SignalType.isSimulation(extension)) {
                signal_type = SignalType.getSimulationCode
              }
              else if (SignalType.isNPerspiration(extension)) signal_type = SignalType.getNPerspirationCode
              else if (SignalType.isTemperature(extension)) signal_type = SignalType.getTemperatureCode
              else if (SignalType.isVideo(extension)) signal_type = SignalType.getVideoCode
              else if (SignalType.isActivity(extension)) signal_type = SignalType.getActivityCode
              else if (SignalType.isHRV(extension)) signal_type = SignalType.getHRV
              else if (SignalType.isExpression(extension)) signal_type = SignalType.getExpression
              else if(SignalType.isEyeTracking(extension)) signal_type = SignalType.getEyeTrackingCode
              */
              if(userDefinedExtension.contains(extension)){
                signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
              }

              else shouldICommit= false //todo: continue is not supported
              if(shouldICommit)
                DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, sessionName, signal_code, signalInfo.getId, 0)
            }
          }
        }
        session_no += 1
        if (studyTopology.physiology.EDA == 1 && eda != 1) {
          report = report + "     Session <" + file.getTitle + ">: EDA signal was not found\n"
        }
        if (studyTopology.physiology.Motion == 1 && motion != 1) {
          report = report + "     Session <" + file.getTitle + ">: Motion signal was not found\n"
        }
        if (studyTopology.physiology.GSR_Finger == 1 && gsr != 1) {
          report = report + "     Session <" + file.getTitle + ">: GSR_Finger signal was not found\n"
        }
        if (studyTopology.physiology.Heart_Rate == 1 && heart_rate != 1) {
          report = report + "     Session <" + file.getTitle + ">: heart_rate signal was not found\n"
        }
        if (studyTopology.physiology.Perspiration == 1 && perspiration != 1) {
          report = report + "     Session <" + file.getTitle + ">: perspiration signal was not found\n"
        }
        if (studyTopology.physiology.Breathing_Belt == 1 && belt_breath != 1) {
          report = report + "     Session <" + file.getTitle + ">: Breathing_Belt signal was not found\n"
        }
        if (studyTopology.physiology.Breathing_Thermal == 1 && thermal_breath != 1) {
          report = report + "     Session <" + file.getTitle + ">: Breathing_Thermal signal was not found\n"
        }
      }

      //Check the missing Signal
      // searching for .Info and .pm files that reside in the root folde rof the subject
      //val infos = GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'")
      val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        //var fileInfo: File = getFolderId(service, info)

        if (fileInfo.getTitle.contains("~")) break //todo: continue is not supported
        val extension: String = fileInfo.getFileExtension


        if(userDefinedExtension.contains(extension)){
          var signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "GENERAL", signal_code, fileInfo.getId, 1)
        }
       /* if (SignalType.isInfo(extension)) {
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "INFO", SignalType.getInfoCode, fileInfo.getId, 1)
        }
        else if (SignalType.isPsychometric(extension)) {
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "PM", SignalType.getPsychometricCode, fileInfo.getId, 1)
        }
        else if (SignalType.isPerfromance(extension)) {
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "PRF", SignalType.getPerformanceCode, fileInfo.getId, 1)
        }
        else if (SignalType.isBar(extension)) {
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "BAR", SignalType.getBarChatCode, fileInfo.getId, 1)
        }
        else if (SignalType.isPerspiration(extension)) {
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, " ", SignalType.getPerspirationCode, fileInfo.getId, 1)
        }*/
      }

      sender() ! OneSubjectDone(study_no)
    case ScanStudy (folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, shareStudy) =>
      var report: String = ""
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(username)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      /////share the folder with every body. Later it has to be some parameter to specify it the user want to make public or not
      if (shareStudy == 1) {
        //val fl: File = service.files.get(folder_id).execute
        val fl: File = GoogleDrive.waitUntilGetDGFile(service, folder_id)

        val newPermission: Permission = new Permission
        newPermission.setValue("")
        newPermission.setType("anyone")
        newPermission.setRole("reader")
        service.permissions.insert(fl.getId, newPermission).execute
      }
      val subjects = GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'")
      //test number of subjects
      if (subjects.size > studyTopology.noOfSubjects) report = report + "Your selected " + studyTopology.noOfSubjects + " as the number of subjects in your study but you have: " + subjects.size + "\n"
      else if (subjects.size < studyTopology.noOfSubjects) report = report + "You have fewer subjects than the number you have selected\n"
      var study_no: Int = 0
      if (subjects.size > 0) {
        study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, GOOGLE_DRIVE, shareStudy)
      }
      sender() ! SubjectsInStudy(subjects.toList,folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, study_no)

      /*if (createPortrait == 1) {
        Logger.info("Now we start creating the portrait")
        val startTime: Long = System.nanoTime
        var query: String = ""
        if(studyName.toLowerCase().contains("toyota")){
          val portrait = context.actorOf(Props(new  CreatePortrait(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, study_no)))
            portrait ! FindLoadedPoint
           // portrait ! FindRadar
        }else{
          query = AddNewStudy.studyToPortrat(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, study_no, DataBaseOperations.listOfFileExtensionPortrait(username))

          val portrait = context.actorOf(Props(new  CreatePortrait(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, study_no)))
        //  portrait ! FindRadar
          //TODO implement study to portrat in scala using actors
        }


      }*/


    case ScanStudyAbstract(topology) =>
      var report: String = ""
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(topology.userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(topology.userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      /////share the folder with every body. Later it has to be some parameter to specify it the user want to make public or not
      if (topology.public) {
        //val fl: File = service.files.get(folder_id).execute
        val fl: File = GoogleDrive.waitUntilGetDGFile(service, topology.studyLocation)

        val newPermission: Permission = new Permission
        newPermission.setValue("")
        newPermission.setType("anyone")
        newPermission.setRole("reader")
        service.permissions.insert(fl.getId, newPermission).execute
      }
      val subjects = GoogleDrive.returnFilesInFolder(service, topology.studyLocation, "mimeType = 'application/vnd.google-apps.folder'")
      //test number of subjects
      //if (subjects.size > studyTopology.noOfSubjects) report = report + "Your selected " + studyTopology.noOfSubjects + " as the number of subjects in your study but you have: " + subjects.size + "\n"
      //else if (subjects.size < studyTopology.noOfSubjects) report = report + "You have fewer subjects than the number you have selected\n"
      var study_no: Int = 0
      if (subjects.size > 0) {
        var share = 0;
        if(topology.public)
          share = 1;
        study_no = DataBaseOperations.GenerateStudyNoGD(topology.studyName, topology.userName, GOOGLE_DRIVE, share)
      }
      sender() ! SubjectsInStudyAbstract(subjects.toList, topology,  study_no)
  }

  def getFolderId (service: Drive, folder: String) :File ={
    var file0: File = null;
    val randomGenerator = new Random();
    return GoogleDrive.waitUntilGetDGFile(service, folder)
  }
}