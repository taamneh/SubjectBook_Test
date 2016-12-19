package controllers

import java.io.InputStream
import java.net.SocketTimeoutException
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import controllers.CreatingStudyMessages._
import play.api.libs.json.{JsString, JsObject, JsArray}


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
case class SubjectFolderRealTime(subjectId: String, subjectName: String, service: Drive, study_no: Int, filesExtensions: Map[String, Int], explExt:String, expListExtention: List[String], resExt: String, resListExtentions: List[String])
case class DownloadVideo(userName: String, subject: String, studyLoc: String, studyNo: Int, subjectName: String)

class ScanSubject extends Actor{



  def inActitivity(point : Double, actionType: Int, activities : java.util.ArrayList[Activity]): Boolean = {
    import scala.collection.JavaConversions._
    for (act <- activities) {
      if (Math.floor(point) >= Math.floor(act.startTime) && Math.floor(point) <= Math.floor(act.endTime) && actionType == act.actionType) {
        return true
      }
    }
    return false
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


  def getStudyDescriptor (service : Drive, loc : String) : String=
  {

    var desc : String =  null
    loc match {
      case location =>
        var allSessions : List[SessionDescription] = null
        import scala.collection.JavaConversions._
        val input: InputStream = GoogleDrive.downloadFileByFileId(service, location)
        val tt: ReadExcelJava = new ReadExcelJava
        allSessions = tt.getStudyDescription(4, GoogleDrive.generateFileNameFromInputStream(input)).toList

        if(allSessions != null){
          var array: JsArray = new JsArray
          var listObj : Seq[JsObject] = Seq.empty[JsObject]
          for(as <- allSessions){


            var sessionType = "3";
            var me = "0"
            if(as.getShow== true){
              sessionType = "2"
            }
            if(as.getMutualEx == true)
              me = "1"

            array = array.append( JsObject(Seq("sessionName" -> JsString(as.getName), "acronym" -> JsString(as.getDesiredName),
              "sessionType" ->JsString(sessionType), "arm" ->JsString(me) )))

            //var x : String = Seq(ww)

            println("WWWWWWWWWWWWWWWWWWfffff" + array.toString())
          }
          desc = array.toString()
        }

        input.close()
    }


   desc
  }
  def receive = {
    case ScanStudyAbstract(topology) =>
      var study_no: Int = 0

      var descLoc :String = null;
      if(topology.sourceType ==SharedData.GOOGLE_DRIVE) {  // if we are reading from google drive
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

        //TODO replace  returnFilesInFolder with returnFilesInFolderJustForTest2
        val subjects = GoogleDrive.returnFilesInFolder(service, topology.studyLocation, "mimeType = 'application/vnd.google-apps.folder'")
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, topology.studyLocation, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

        var descriptor = topology.descriptor


        println("FZOOOOOOOOOOOOOOOOOOOOOOOO  " + descriptor);
        // if the user choose not to provide experimental desing we serach for file with .descriptor extension......
        if(descriptor ==  null || descriptor.equals("") || descriptor.equals("null")){

          descriptor = null;
          println("Fucnking nuillllllllllllllllllllllllllllllllllllll")
          // searach if this study has descriptor
          for ((signalId, signalInfo) <- signals) {
            //val file2: File = GoogleDrive.waitUntilGetDGFile(service, signalId)
            if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {
              val extension = signalInfo.getFileExtension

              if(extension.equalsIgnoreCase("descriptor")) {

                // code to translate the descitpt ro json code to save in in our database and aslo provdie code to convernt from json to java
                descLoc = signalInfo.getId
                descriptor = getStudyDescriptor(service, descLoc);
              }

            }
          }
        }


        if (subjects.size > 0) {
          var share = 0;
          if (topology.public)
            share = 1;
          study_no = DataBaseOperations.GenerateStudyNoGD(topology.studyName, topology.userName, SharedData.GOOGLE_DRIVE, share, descriptor, topology.studyLocation, null)
        }
        sender() ! SubjectsInStudyAbstract(subjects.toList, topology, study_no)

      }
      else{
        var file = new java.io.File(topology.studyLocation);
        if (file.exists && file.isDirectory) {
          val temp = file.listFiles.filter(_.isDirectory).toList
          val subjects = temp.map(x=> x.getAbsolutePath)
          println("%%%%%%%%%%%%%" + subjects)
          if (subjects.size > 0) {
            var share = 0;
            if (topology.public)
              share = 1;

            // TODO search for descirptor on server
            study_no = DataBaseOperations.GenerateStudyNoGD(topology.studyName, topology.userName, SharedData.LOCALSERVER, share, null, topology.studyLocation, null)
          }
          sender() ! SubjectsInStudyAbstract(subjects, topology, study_no)
        } else {
          List[File]()
        }

      }

    case SubjectFolderRealTime(subjectId, subjectName, service, study_no, userDefinedExtension, explExt, expListExtention, resExt, resListExtentions) =>
      println("Subject : " +  subjectName)
      //println("sss  "  + subject   +  " Data Modified : " + subjectInfo.getl)
      // This  call asks one of ScanSubject Actor to scan a particulre subect and save its content to our database
      //TODO when a new study comes in we should send a priority message to scan that study.
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subjectId, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      var session_no: Int = 1
      //val file0: com.google.api.services.drive.model.File = GoogleDrive.waitUntilGetDGFile(service, subject)

      import scala.collection.JavaConversions._
      for ((sessionId, sessionInfo) <- sessions) {

        println("session : "  + sessionInfo.getTitle  )
        var file: com.google.api.services.drive.model.File = sessionInfo
        var sessionName: String = sessionInfo.getTitle

        sessionName = sessionName.replaceAll("\\s+", "")

        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var signal_code: Int = 1
        breakable {
          for ((signalId, signalInfo) <- Signals) {
            var order = 0;
            var isBl = false;
            var shouldICommit = true;
            if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {
              val extension: String = signalInfo.getFileExtension
              println("extension  "  + extension  )
              // TODO in case there is NO primary explantory varialbe make the secondary to appear first
              if((expListExtention.contains(extension.toLowerCase) || explExt.equalsIgnoreCase(extension) || extension.equalsIgnoreCase("sp") ||
                extension.equalsIgnoreCase("tp") || /*extension.equalsIgnoreCase("mp4")*/  extension.equalsIgnoreCase("avi")|| extension.equalsIgnoreCase("bar")|| resExt.equalsIgnoreCase(extension) || resListExtentions.contains(extension.toLowerCase) )
                && !extension.equalsIgnoreCase("sim2") ) {
                signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                  case Some(x) => x
                  case None => 0 // should never reach here
                }
              }
              else
                shouldICommit = false //todo: continue is not supported
              if (shouldICommit) {

                var insert = false
                if(extension.equalsIgnoreCase("avi")) insert = true;

                println("Now going to insert signal  " + extension  + "code  " +  signal_code)
                DataBaseOperations.UpdateSessionGD(subjectName, study_no, 1, sessionName, signal_code, signalInfo.getId, 0, isBl, order, insert, signalInfo.getTitle)
              }
            }
          }
        }
        session_no += 1

      }

      val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subjectId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        //var fileInfo: File = getFolderId(service, info)

        if (fileInfo.getTitle.contains("~") || fileInfo.getFileExtension == null) break //todo: continue is not supported
        val extension: String = fileInfo.getFileExtension
        if (userDefinedExtension.contains(extension.toLowerCase)) {
          var signal_code = userDefinedExtension.get(extension.toLowerCase) match {
            case Some(x) => x
          }
          DataBaseOperations.UpdateSessionGD(subjectName, study_no, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false, 0, false, fileInfo.getTitle)
        }
      }
    case SubjectFolderAbstract(subject, service, study_no, topology, userDefinedExtension) =>
      if(topology.sourceType ==SharedData.GOOGLE_DRIVE) {
        Logger.info("We started reading a new subject for Study No = " + study_no)
        var report: String = "";
        val file0: File = getFolderId(service, subject);
        DataBaseOperations.InsertSubjectGD(file0.getTitle, study_no, subject, 11, 11)
        val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var session_no: Int = 1

        import scala.collection.JavaConversions._
        for ((sessionId, sessionInfo) <- sessions) {
          var file: File = sessionInfo
          var sessionName: String = sessionInfo.getTitle
          sessionName = sessionName.replaceAll("\\s+", "")
          val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
          var signal_code: Int = 1
          
          breakable {
            for ((signalId, signalInfo) <- Signals) {
              var order = 0;

              var isBl = false;
              var shouldICommit = true;

              if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {
                val extension: String = signalInfo.getFileExtension
                println("extension  "  + extension  )

                if (extension.equalsIgnoreCase(topology.primaryExp)) {
                  order = 2;
                }
               // TODO in case there is NO primary explantory varialbe make the secondary to appear first
                else if (extension.equalsIgnoreCase(topology.primaryRes)) {
                  order = 1;
                }

                else if (extension.equalsIgnoreCase("FACS") || extension.equalsIgnoreCase("expression")){
                  order = -1;
                }


                if (sessionName.replaceFirst("(\\d*\\s*)", "").equalsIgnoreCase(topology.baseLine) && (extension.equalsIgnoreCase(topology.primaryExp) || topology.secondadryExp.contains(extension))) {
                  isBl = true;
                }
                if (userDefinedExtension.contains(extension.toLowerCase) && !extension.equalsIgnoreCase("sim2") ) {
                  signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                    case Some(x) => x
                    case None =>  0  // should never reach here
                  }
                }
                else
                  shouldICommit = false //todo: continue is not supported
                if (shouldICommit)
                  DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, sessionName, signal_code, signalInfo.getId, 0, isBl, order, signalInfo.getTitle)
              }
            }
          }
          session_no += 1

        }


        //Check the missing Signal
        val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        import scala.collection.JavaConversions._
        for ((info, fileInfo) <- infos) {
          //var fileInfo: File = getFolderId(service, info)

          if (fileInfo.getTitle.contains("~") || fileInfo.getFileExtension == null) break //todo: continue is not supported
          val extension: String = fileInfo.getFileExtension
          if (userDefinedExtension.contains(extension.toLowerCase)) {
            var signal_code = userDefinedExtension.get(extension.toLowerCase) match {
              case Some(x) => x
            }
            DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false, 0, fileInfo.getTitle)
          }
        }
      }

      else{
        Logger.info("We started reading a new subject for Study No = " + study_no)
        var report: String = "";
        //val file0: File = getFolderId(service, subject);
        var subjectTitle = subject.substring(subject.lastIndexOf("\\")+1)
        DataBaseOperations.InsertSubjectGD(subjectTitle, study_no, subject, 11, 11)
        //val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        val sessions = returnFileFromFolderLocalMachine(subject, true);
        var session_no: Int = 1

        import scala.collection.JavaConversions._
        for (session <- sessions) {
         // var file: File = sessionInfo
          var sessionName: String = session.substring(session.lastIndexOf("\\")+1)
          sessionName = sessionName.replaceAll("\\s+", "")
          //val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
          val Signals = returnFileFromFolderLocalMachine(session, false)
          var signal_code: Int = 1
          breakable {
            for (signal <- Signals) {
              var order = 0;

              var isBl = false;

              var shouldICommit = true;
              var signalTitle = signal.substring(signal.lastIndexOf("\\")+1)
              if (!signalTitle.contains("~") ) {
                val extension: String = signal.substring(signal.lastIndexOf(".")+1)
                if (extension.equalsIgnoreCase(topology.primaryExp)) {
                  order = 2;
                }
                else if (extension.equalsIgnoreCase(topology.primaryRes)) {
                  order = 1;
                }
                if (sessionName.replaceFirst("(\\d*\\s*)", "").equalsIgnoreCase(topology.baseLine) && extension.equalsIgnoreCase(topology.primaryExp)) {
                  isBl = true;
                }
                if (userDefinedExtension.contains(extension.toLowerCase) && !extension.equalsIgnoreCase("sim2")) {
                  signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                    case Some(x) => x
                    case None => 0  // should never be implemetned
                  }
                }
                else
                  shouldICommit = false //todo: continue is not supported
                if (shouldICommit)
                  DataBaseOperations.InsertSessionGD(subjectTitle, study_no, session_no, sessionName, signal_code, signal, 0, isBl, order )
              }
            }
          }
          session_no += 1

        }


        //Check the missing Signal
       // val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        val infos = returnFileFromFolderLocalMachine(subject, false)
        import scala.collection.JavaConversions._
        for (info <- infos) {
          //var fileInfo: File = getFolderId(service, info)
          var infoTitle = info.substring(info.lastIndexOf("\\")+1)

          if (infoTitle.contains("~")) break //todo: continue is not supported
          val extension: String = info.substring(subject.lastIndexOf(".")+1)
          if (userDefinedExtension.contains(extension)) {
            var signal_code = userDefinedExtension.get(extension) match {
              case Some(x) => x
            }
            DataBaseOperations.InsertSessionGD(infoTitle, study_no, session_no, "GENERAL", signal_code, info, 1, false, 0)
          }
        }

      }

      sender() ! OneSubjectDoneAbstract(study_no)



    case SubjectFolderAbstractExport(subjectTitle, subject, service, study_no) =>

      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      var tempList : java.util.List[EntryForExcel] = new java.util.ArrayList[EntryForExcel]()
      import scala.collection.JavaConversions._
      for ((sessionId, sessionInfo) <- sessions) {
        var minFrmes: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var ppSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;



        var brSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var hrSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var hrvSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var edaSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;


        var speed: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var acc: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var braking: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var stering: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var laneOffest: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
        var lanePosition: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;


        var activtityFile: java.util.ArrayList[Activity] = null;

        var file: com.google.api.services.drive.model.File = sessionInfo
        var sessionName: String = sessionInfo.getTitle
        sessionName = sessionName.replaceFirst("(\\d*\\s*)", "")

        var stimulusName: String = sessionName match {

          case _ => sessionName
          /*case "Baseline" => "No-Multitasking"
          case "PracticeDrive" => "No-Multitasking"
          case "NormalDrive" => "No-Multitasking"
          case "MotoricDrive" => "Motoric Load"
          case "CognitiveDrive" => "Cognitive Load"
          case "FinalDrive" => "Motoric Load"
          case _ => "No-Multitasking"*/
        }
        sessionName = sessionName.toLowerCase match {
          case x if x.contains("bl") => "1"
          case x if x.contains("pd") => "2"
          case x if x.contains("rd") => "3"
          case x if x.contains("ns") => "4"
          case x if x.contains("cs") => "5"
          case x if x.contains("es") => "6"
          case x if x.contains("ms") => "7"
          case x if x.contains("fdn") => "8"
          case x if x.contains("fdl") => "8"
          case _ => sessionName
        }


        // if(!sessionName.equals("BL") && !sessionName.equals("PD") ) {
        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var signal_code: Int = 1
        var vidoeCount = 0;
        breakable {
          for ((signalId, signalInfo) <- Signals) {
            if (((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar"))) {

              var extension: String = signalInfo.getFileExtension
              //println("extension  " + extension + "   Name: " + signalInfo.getTitle)
              if (signalInfo.getTitle.contains(".bar.xlsx") || signalInfo.getTitle.contains(".bar")) // to hnalde muhsin issue with .bar extension
              {
                extension = "bar"
              }
              // TODO in case there is NO primary explantory varialbe make the secondary to appear first
              if (extension.equalsIgnoreCase("pp")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                ppSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 2);
                if (minFrmes == null) minFrmes = ppSignal
                else {
                  if (ppSignal.size() < minFrmes.size()) minFrmes = ppSignal;
                }
              }
              else if (extension.equalsIgnoreCase("np")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                ppSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 2);
                if (minFrmes == null) minFrmes = ppSignal
                else {
                  if (ppSignal.size() < minFrmes.size()) minFrmes = ppSignal;
                }
              }
              else if (extension.equalsIgnoreCase("res2")) {
                var input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                var fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                speed = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 1);
                acc = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 2);
                braking = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 3);
                stering = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 4);



                //println("Lane + " + lanePosition.size())
                var name = ReadExcelJava.getHeaderNameFromExel(fileName2, 5)

                var ttlist = name.keySet().toArray()
                if(ttlist(0).toString.toLowerCase().contains("offset")){

                  println("Kos o5tak men youm")
                  laneOffest = name.get(ttlist(0))
                  lanePosition = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 6);
                }
                else
                {
                  laneOffest = null
                  lanePosition = name.get(ttlist(0))
                }

                //laneOffest = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 5);



                if (minFrmes == null) minFrmes = speed
                else {
                  if (speed.size() < minFrmes.size()) minFrmes = speed;
                }
              }


           /* if (extension.equalsIgnoreCase("peda")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                edaSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                if (minFrmes == null) minFrmes = edaSignal;
                else {
                  if (edaSignal.size() < minFrmes.size()) minFrmes = edaSignal;
                }
              }
              else if (extension.equalsIgnoreCase("hr")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                hrSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                if (minFrmes == null) minFrmes = hrSignal;
                else {
                  if (hrSignal.size() < minFrmes.size()) minFrmes = hrSignal;
                }
              }

              else if (extension.equalsIgnoreCase("br")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                brSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                if (minFrmes == null) minFrmes = brSignal;
                else {
                  if (brSignal.size() < minFrmes.size()) minFrmes = brSignal;
                }
              }

              else if (extension.equalsIgnoreCase("hrv")) {
                val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                hrvSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                if (minFrmes == null) minFrmes = hrvSignal;
                else {
                  if (hrvSignal.size() < minFrmes.size()) minFrmes = hrvSignal;
                }
              }
              */
              else if (extension.equalsIgnoreCase("stm")) {
                activtityFile = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, signalId));
              }
            }
          }
        }

        if(minFrmes != null){
          var emptySignal = new java.util.TreeMap[java.lang.Double, java.lang.Double]
          for ((time, value) <- minFrmes) {
            emptySignal.put(time, -111111.1)
          }

          if (activtityFile == null) {

            if (edaSignal == null)
              edaSignal = emptySignal
            if (ppSignal == null)
              ppSignal = emptySignal
            if (hrSignal == null)
              hrSignal = emptySignal
            if (hrvSignal == null)
              hrvSignal = emptySignal
            if (brSignal == null)
              brSignal = emptySignal
            if (speed == null)
              speed = emptySignal
            if (acc == null)
              acc = emptySignal
            if (stering == null)
              stering = emptySignal
            if (braking == null)
              braking = emptySignal
            if (laneOffest == null )
              laneOffest = emptySignal
            if(lanePosition == null )
              lanePosition = emptySignal

            //if(ppSignal != null && edaSignal !=null && hrSignal!= null && brSignal != null && hrvSignal !=null  && speed !=null && acc!=null && stering != null & braking!= null && laneOffest != null){
            var ctr = 0;

            var ppList = ppSignal.keySet().toArray()
            var edaList = edaSignal.keySet().toArray()
            var hrList = hrSignal.keySet().toArray()
            var brList = brSignal.keySet().toArray()
            var hrvList = hrvSignal.keySet().toArray()
            var speedList = speed.keySet().toArray()
            var accList = acc.keySet().toArray()
            var steeringList = stering.keySet().toArray()
            var brakeList = braking.keySet().toArray()
            var laneList = laneOffest.keySet().toArray()
            var lanePositionList= lanePosition.keySet().toArray()
            var gSize = minFrmes.size();

           println("AAAAAAA  " + minFrmes.lastEntry())

            var index =0;
            for ((time, value) <- minFrmes) {



              tempList.add(new EntryForExcel(time, sessionName, "0", "0", ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)),  brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(index)), acc.get(accList(index)), braking.get(brakeList(index)), stering.get(steeringList(index)), laneOffest.get(laneList( math.floor(ctr.toDouble * (laneOffest.size().toDouble/gSize.toDouble)).toInt)), lanePosition.get(lanePositionList(Math.floor(ctr.toDouble * lanePosition.size().toDouble / gSize.toDouble).toInt))));
              ctr = ctr + 1;


              index = math.floor(ctr.toDouble * (speed.size().toDouble/gSize.toDouble)).toInt

             // if(index >= speedList.size)
               // index = speedList.size -1

            }
            //}

          }
          else /*if (activtityFile != null && ppSignal != null && edaSignal !=null && hrSignal!= null && brSignal != null && hrvSignal !=null && speed !=null && acc!=null && stering != null & braking!= null && laneOffest != null)*/ {
            if (edaSignal == null)
              edaSignal = emptySignal
            if (ppSignal == null)
              ppSignal = emptySignal
            if (hrSignal == null)
              hrSignal = emptySignal
            if (hrvSignal == null)
              hrvSignal = emptySignal
            if (brSignal == null)
              brSignal = emptySignal
            if (speed == null)
              speed = emptySignal
            if (acc == null)
              acc = emptySignal
            if (stering == null)
              stering = emptySignal
            if (braking == null)
              braking = emptySignal
            if (laneOffest == null)
              laneOffest = emptySignal
            if(lanePosition == null )
              lanePosition = emptySignal



            var ctr = 0;
            var ppList = ppSignal.keySet().toArray()
            var edaList = edaSignal.keySet().toArray()
            var hrList = hrSignal.keySet().toArray()
            var brList = brSignal.keySet().toArray()
            var hrvList = hrvSignal.keySet().toArray()

            var speedList = speed.keySet().toArray()
            var accList = acc.keySet().toArray()
            var steeringList = stering.keySet().toArray()
            var brakeList = braking.keySet().toArray()
            var laneList = laneOffest.keySet().toArray()
            var lanePositionList= lanePosition.keySet().toArray()


            var gSize = minFrmes.size();
            var distinctAnno: TreeMap[Int, String] = TreeMap.empty
            import scala.collection.JavaConversions._
            for (temp <- activtityFile) {
              if (!distinctAnno.contains(temp.actionType)) {
                distinctAnno += temp.actionType -> temp.label
              }
            }

            var index =0;


            var frac = (speed.size().toDouble / gSize.toDouble) % Math.floor(speed.size() / gSize).toInt
            var totalFrac = frac
            println("Frac :    " + frac)
            for ((time, value) <- minFrmes) {
              var stimulus = "0"
              var stimulusFailure = "0"
              for ((code, name) <- distinctAnno) {
                {
                  if (!name.toLowerCase().contains("light"))
                    if (inActitivity(time, code, activtityFile)) {
                      if (name.toLowerCase().contains("failure"))
                        stimulusFailure = "6"
                      else
                        stimulus = name.toLowerCase match {
                          case "emotional questions" => "3"
                          case "answer" => "3"
                          case "texting" => "4"
                          case "mathematical questions" => "2"
                          case "analytical questions" => "1"
                          case "texting and emotional questions" => "5"

                          case _ => name
                        }
                    }
                }
              }

              tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)),  brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(index)), acc.get(accList(index)), braking.get(brakeList(index)), stering.get(steeringList(index)),  laneOffest.get(laneList( math.floor(ctr.toDouble * (laneOffest.size().toDouble/gSize.toDouble)).toInt)), lanePosition.get(lanePositionList(Math.floor(ctr.toDouble * lanePosition.size().toDouble / gSize.toDouble).toInt))));
              ctr = ctr + 1;

              index = math.floor(ctr.toDouble * (speed.size().toDouble/gSize.toDouble)).toInt

              //if(index >= speedList.size)
               // index = speedList.size -1
            }
          }
        }

      }

        println("length       " + tempList.size())
      sender() ! OneSubjectDoneDataSet(study_no, subjectTitle, tempList)
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

            //println("We found the baseline folder")
            // save the explaontory varialbe file in somewere
            breakable {
              for ((signalId,signalInfo) <- Signals) {
                if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {

                  val extension: String = signalInfo.getFileExtension

                  if (extension.equalsIgnoreCase(topology.primaryExp)) {

                   // println("We are saving the primary singal in baseline")
                      val input: InputStream = GoogleDrive.downloadFileByFileId(service, signalInfo.getId)
                      val name = GoogleDrive.generateFileNameFromInputStream(input)
                     blSignal = ReadExcelJava.getAllSignalFromExcelAbstraction( name, topology.explColNo)
                  }
                  else if(extension.equalsIgnoreCase(topology.primaryRes)){

                    //println("We are saving the Response signal in baseline")
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
            if(SignalType.isActivity(signalInfo.getFileExtension)){  // works for both .activity or .stm
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
        Logger.debug("Shoooooooooooooooooooof this subject has no response file+ " +  file0.getTitle)
      }
      sender() ! OneSubjectPointsDone(study_no, totoalSubjectPointsWithResponse, numberOfPoints+1);



    case SubjectFolder(subject, service, study_no, studyTopology ,bio_code, psycho_code, physio_code, perf_code, userDefinedExtension)=>
      Logger.info("We started reading a new subject for Study No = " + study_no)
      var report : String ="";
      val file0: File =getFolderId(service, subject);
      DataBaseOperations.InsertSubjectGD(file0.getTitle, study_no, subject, psycho_code, physio_code)
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

            if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {

              val extension: String = signalInfo.getFileExtension

              if(userDefinedExtension.contains(extension)){
                signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
              }

              else shouldICommit= false //todo: continue is not supported
              if(shouldICommit)
                DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, sessionName, signal_code, signalInfo.getId, 0, false,0, signalInfo.getTitle)
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

        if (fileInfo.getTitle.contains("~") || fileInfo.getFileExtension == null) break //todo: continue is not supported
        val extension: String = fileInfo.getFileExtension


        if(userDefinedExtension.contains(extension)){
          var signal_code = userDefinedExtension.get(extension) match { case Some(x) => x}
          DataBaseOperations.InsertSessionGD(file0.getTitle, study_no, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false,0, fileInfo.getTitle)
        }

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
        study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, SharedData.GOOGLE_DRIVE, shareStudy, null, folder_id, null)
      }
      sender() ! SubjectsInStudy(subjects.toList,folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, study_no)


    case DownloadVideo(userName, subject, studyLoc, studyNo, subjectName) =>

      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      // This  call asks one of ScanSubject Actor to scan a particulre subect and save its content to our database
      //TODO when a new study comes in we should send a priority message to scan that study.
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      var session_no: Int = 1
      import scala.collection.JavaConversions._
      for ((sessionId, sessionInfo) <- sessions) {
        // println("session : "  + sessionInfo.getTitle  )
        var file: com.google.api.services.drive.model.File = sessionInfo
        var sessionName: String = sessionInfo.getTitle

        sessionName = sessionName.replaceAll("\\s+", "")

        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var signal_code: Int = 1
        breakable {
          for ((signalId, signalInfo) <- Signals) {

            if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null)) {
              var extension: String = signalInfo.getFileExtension

              if (extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("wav") || extension.equalsIgnoreCase("mp3")) {

                val option1 = studyLoc + "\\" + subjectName + "\\" + sessionInfo.getTitle.replaceAll("\\s+", "") + "\\" + signalInfo.getTitle
                val option2 = studyLoc + "\\" + subjectName + "\\" + sessionInfo.getTitle.replaceAll("\\s+", "") + "\\" + signalInfo.getTitle + "." + "mp3"
                val option3 = studyLoc + "\\" + subjectName + "\\" + sessionInfo.getTitle.replaceAll("\\s+", "") + "\\" + signalInfo.getTitle + "." + "mp4"

                if (!(new java.io.File(option1).exists() /*|| new java.io.File(option2).exists()*/ || new java.io.File(option3).exists())) {

                  if (!new java.io.File(studyLoc + "\\" + subjectName + "\\" + sessionInfo.getTitle.replaceAll("\\s+", "")).exists())
                    new java.io.File(studyLoc + "\\" + subjectName + "\\" + sessionInfo.getTitle.replaceAll("\\s+", "")).mkdirs();

                  GoogleDrive.downloadVideo(userName, signalId, option1)
                }

              }


            }
          }
        }
        session_no += 1

      }
      sender ! OneSubjectDoneAbstract(studyNo)
  }

  def getFolderId (service: Drive, folder: String) :File ={
    var file0: File = null;
    val randomGenerator = new Random();
    return GoogleDrive.waitUntilGetDGFile(service, folder)
  }

  def returnFileFromFolderLocalMachine (folder: String, isFolder :Boolean) : List[String]= {

    if(isFolder) {
      var file = new java.io.File(folder);
      if (file.exists && file.isDirectory) {
        val temp = file.listFiles.filter(_.isDirectory).toList
        val subjects = temp.map(x => x.getAbsolutePath)
        return subjects

      }
    }
    else{
      var file = new java.io.File(folder);
      if (file.exists && file.isDirectory) {
        val temp = file.listFiles.filter(_.isFile).toList
        val subjects = temp.map(x => x.getAbsolutePath)
        return subjects
      }
    }
    return List.empty  // this is the folder was not correct

  }
}
