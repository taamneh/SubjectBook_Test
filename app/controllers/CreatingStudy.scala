package controllers

import Models.StudyTopology
import akka.actor.{Props, ActorRef, Actor}
import akka.routing.RoundRobinPool
import anorm._
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive
import controllers.Application._
import org.rosuda.REngine.{REXP, REXPMismatchException}
import org.rosuda.REngine.Rserve.{RserveException, RConnection}
import play.api.db.DB

import play.api.libs.json.Json
import scala.collection.immutable.TreeMap
import scala.concurrent.duration._
import com.google.api.services.drive.model.{Permission, File}
import play.Logger
import scala.collection.JavaConversions._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.JsObject
import play.api.libs.json.{JsArray, Json}
import play.api.Play.current
import controllers.CreatingStudyMessages._
import controllers.CreatePortraitMessages._

/**
 * Created by staamneh on 6/11/2015.
 */


object CreatingStudyMessages {

  case class SubjectFolder(subject: String, service: Drive, study_no: Int, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, perf_code: Int, filesExtensions: Map[String, Int])

  case class SubjectFolderAbstract(subject: String, service: Drive, study_no: Int, topology: Abstraction, filesExtensions: Map[String, Int])

  case class SubjectFolderAbstractSummary(subject: String, service: Drive, study_no: Int, topology: Abstraction, filesExtensions: Map[String, Int])

  case object IsFinished;

  case class ScanStudy(folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, perf_code: Int, createPortrait: Int, shareStudy: Int)

  case class ScanStudyAbstract(topology: Abstraction)

  case class startCreatingPortrait(folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, perf_code: Int, createPortrait: Int, studyNo: Int)

  case class SaveCreatePortrait(folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, Psycho_code: Int, physio_code: Int, studyNo: Int)

  case class SaveCreatePortraitAbstract(topology: Abstraction, studyNo: Int)

  case class OneSubjectDone(StudyNo: Int);

  case class OneSubjectDoneAbstract(StudyNo: Int);

  case class OneSubjectPointsDone(StudyNo: Int, sessionTemp: Map[String, Map[String, Map[String, Double]]], noOfPoints: Int)

  case object DoneFindingFailurePoint;

  case class DoneCreatingPortrait(studyNo: Int);
}



class CreatingStudy extends Actor{
  var folderType = 1;
  var js: JsArray = Json.arr();
  var startTimeEachStudy: Map[Int, Long] = Map.empty
  var finshedSubjectByStudy :Map[Int,(Int, Int)] = Map.empty
  var finshedSubjectByStudyPoints :Map[Int,(Int, Int)] = Map.empty // study number , (totoal subject, done ones)

  var totoalSubjectPointsWithResponseAll : TreeMap[Int, TreeMap[(String, String, String), List[Double]]] = TreeMap.empty
  var createPortraitOrderes :Map[Int,SaveCreatePortrait] = Map.empty
  var createPortraitOrderesAbstract :Map[Int,SaveCreatePortraitAbstract] = Map.empty
  var createSummaryOrderedAbstract : Map[Int,(List[String], Abstraction)] = Map.empty
  val router: ActorRef = context.actorOf(RoundRobinPool(6).props(Props[ScanSubject]), "router2")
  def receive = {

    case ScanStudyAbstract(topology) =>
      Logger.info("Now we start creating a new study wih ABSRACTION called : " + topology.studyName + topology.studyLocation)
      // We ask one child actor to scan the study and return back the subjects it has
      router ! ScanStudyAbstract(topology)

    // This message is return back by the a ScanSubject Actor with a list of subjects
    case SubjectsInStudyAbstract(subjects, topology, study_no) =>


        startTimeEachStudy += study_no -> System.nanoTime;
        val userDefinedExtension = DataBaseOperations.listOfFileExtension(topology.userName)
        val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(topology.userName)
        val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(topology.userName)
        val service: Drive = GoogleDrive.buildService(googleCredential)
        finshedSubjectByStudy += study_no ->(subjects.size, 0)

        if (topology.portriat) {

          createPortraitOrderesAbstract += study_no -> SaveCreatePortraitAbstract(topology, study_no)
          createSummaryOrderedAbstract += study_no -> (subjects, topology)
        }

        for (subject <- subjects) {
          // This  call asks one of ScanSubject Actor to scan a particulre subect and save its content to our database
          //TODO when a new study comes in we should send a priority message to scan that study.
          router ! SubjectFolderAbstract(subject, service, study_no, topology, userDefinedExtension)
        }





      // we recieve this from ScanSbuject when a subject has already been scaned
    case OneSubjectDoneAbstract(st) =>
      finshedSubjectByStudy += st ->(finshedSubjectByStudy(st)._1, finshedSubjectByStudy(st)._2 + 1)
      //println(finshedSubjectByStudy);

      if (finshedSubjectByStudy(st)._1 == finshedSubjectByStudy(st)._2 && createPortraitOrderesAbstract.contains(st)) {

        finshedSubjectByStudy -= st;
        Logger.info("Now we start creating the portrait")
        val startTime: Long = System.nanoTime
        var query: String = ""


        val temp = createPortraitOrderesAbstract(st)
        val portrait = context.actorOf(Props(new CreatePortraitAbstraction(temp.topology, temp.studyNo)))
        //portrait ! FindLoadedPoint

        val sTime = startTimeEachStudy.get(st) match {
          case Some(x) => x
          case None => System.nanoTime()
        }

        portrait ! Start(sTime, st);
        startTimeEachStudy -= st;
        //portrait ! FindRadar

        //TODO make sure to kill the portrait child if if did not kill himeslef
      }

      // we recieve this when we finish creating the portrait and we are now going to create the top summary
    case DoneCreatingPortrait(study_no) =>
    // This  call asks one of ScanSubject Actor to scan a particulre subect and return Back results
      Logger.info("We Are no going to create summary for study: " + study_no )
      val topology = createSummaryOrderedAbstract(study_no)._2
      val subjects = createSummaryOrderedAbstract(study_no)._1
      val userDefinedExtension = DataBaseOperations.listOfFileExtension(topology.userName)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(topology.userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(topology.userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      finshedSubjectByStudyPoints += study_no ->(subjects.size, 0)
      for (subject <- subjects) {
        router ! SubjectFolderAbstractSummary(subject, service, study_no, topology, userDefinedExtension)
      }

    // we recieve this when we finsish the calculation for a sbuejct for the sake of study summary
    case OneSubjectPointsDone(st, lst, points) =>
      finshedSubjectByStudyPoints += st ->(finshedSubjectByStudyPoints(st)._1, finshedSubjectByStudyPoints(st)._2 + 1)
      Logger.info("One subject fininshed calculating the points: " + finshedSubjectByStudyPoints(st)._1 + "/" + finshedSubjectByStudyPoints(st)._2)

      var totSubResp : TreeMap[(String, String, String), List[Double]] = totoalSubjectPointsWithResponseAll.get(st) match{
        case Some(x) => x
        case None =>
          totoalSubjectPointsWithResponseAll += st -> TreeMap.empty
          TreeMap.empty
      }



      for ((sess, varsList) <- lst) {
        for ((varName, ptsList) <- varsList) {
          for ((pntName, value) <- ptsList) {
            //totoalSubjectPointsWithResponse.get((sess, varName, pntName)) match {
            totSubResp.get((sess, varName, pntName)) match {
              case Some(listOfNum) =>
                val newList = value :: listOfNum
                //totoalSubjectPointsWithResponse += (sess, varName, pntName) -> newList
                totSubResp += (sess, varName, pntName) -> newList
              case None =>
                //totoalSubjectPointsWithResponse += (sess, varName, pntName) -> List(value)
                totSubResp += (sess, varName, pntName) -> List(value)

            }
          }

        }
      }
      if (finshedSubjectByStudyPoints(st)._1 == finshedSubjectByStudyPoints(st)._2) {

        // delete the data that belong to the current study ---
        totoalSubjectPointsWithResponseAll -= st;
        finshedSubjectByStudyPoints -= st;
        createSummaryOrderedAbstract -= st;

        //TODO remove +2 and find out which subjects who do not have sim2 files
        //println(totoalSubjectPointsWithResponse)
        // code for R language
        var connection: RConnection = null
        connection = new RConnection
        var sumry: TreeMap[(String), TreeMap[String, Double]] = TreeMap.empty

        for ((name, allNum) <- totSubResp) {
          val v = allNum.toString.replace("List", "c")
          try {
            connection.eval("salah=t.test(" + v + ")")
            val mean = connection.eval("salah[[3]]").asDouble()

            sumry.get(name._3) match {
              case Some(pointMap) =>
                var newMap = pointMap;

                newMap += name._1 + "-" + name._2 -> mean
                sumry += name._3 -> newMap

              case None => // no point
                sumry += name._3 -> TreeMap(name._1 + "-" + name._2 -> mean)
            }
            //System.out.println(name + mean.toString)
          }
          catch {
            case e: RserveException => {

              println("Error happend in R  " + e.getMessage)
              //e.printStackTrace
            }
            case e: REXPMismatchException => {
              println("Error happend in R REXPM " + e.getMessage)
              //e.printStackTrace
            }
          }

        }
        connection.close();
        var topSummary = "["
        var begin = true;
        var i = 1;
        for ((pnt, other) <- sumry) {
          var isFirst = true;
          if (begin)
            topSummary = topSummary + """{"data":["""
          else
            topSummary = topSummary + """,{"data":["""
          begin = false;
          for ((name, value) <- other) {
            var sg = 0;
            if (value <= 0.05)
              sg = 100;
            if (isFirst)
              topSummary = topSummary + """{"yVal":"""" + name + """","count":""" + sg + """}"""
            else
              topSummary = topSummary + """,{"yVal":"""" + name + """","count":""" + sg + """}"""
            isFirst = false;

          }

          topSummary = topSummary + """],"name":"Series#""" + i + """"}"""
          i += 1;
        }
        topSummary = topSummary + "]"
        println(topSummary);

        //val str = """[{"data":[{"yVal":"LD2-EDA","count":0},{"yVal":"LD2-Steering","count":100},{"yVal":"LD3-EDA","count":100},{"yVal":"LD3-Steering","count":100},{"yVal":"LD4-EDA","count":100}],"name":"Series#1"},{"data":[{"yVal":"LD2-EDA","count":100},{"yVal":"LD2-Steering","count":100},{"yVal":"LD3-EDA","count":100},{"yVal":"LD3-Steering","count":100},{"yVal":"LD4-EDA","count":100}],"name":"Series#2"},{"data":[{"yVal":"LD2-EDA","count":100},{"yVal":"LD2-Steering","count":100},{"yVal":"LD3-EDA","count":100},{"yVal":"LD3-Steering","count":100},{"yVal":"LD4-EDA","count":100}],"name":"Series#3"}]"""
        DB.withConnection { implicit c =>
          val id: Int = SQL("update study set top_summary = {code} where study_id = {id}")
            .on('code -> topSummary, 'id -> st).executeUpdate()
        }
      }
      else{
        totoalSubjectPointsWithResponseAll += st -> totSubResp
      }
    // each new study send ScanStudy message to this Actor
    case ScanStudy(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, shareStudy) =>
      Logger.info("Now we start creating a new study called : " + studyName)
      router ! ScanStudy(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, shareStudy)


    // this message is sent by ScanSujbect actor telling how many subjects we have
    case SubjectsInStudy(subjects, folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, study_no) =>
      val userDefinedExtension = DataBaseOperations.listOfFileExtension(username)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(username)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      finshedSubjectByStudy += study_no ->(subjects.size, 0)
      if (createPortrait == 1)
        createPortraitOrderes += study_no -> SaveCreatePortrait(folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, study_no)

      for (subject <- subjects) {
        router ! SubjectFolder(subject, service, study_no, studyTopology, bio_code, psycho_code, physio_code, perf_code, userDefinedExtension)
      }




    case OneSubjectDone(st) =>
      finshedSubjectByStudy += st ->(finshedSubjectByStudy(st)._1, finshedSubjectByStudy(st)._2 + 1)
      //println(finshedSubjectByStudy);

      if (finshedSubjectByStudy(st)._1 == finshedSubjectByStudy(st)._2 && createPortraitOrderes.contains(st)) {

        finshedSubjectByStudy -= st // to delete the related data
        Logger.info("Now we start creating the portrait")
        val startTime: Long = System.nanoTime
        var query: String = ""
        if (createPortraitOrderes(st).studyName.toLowerCase().contains("toyota")) {
          //DataBaseOperations.InsertStudyPortraitString(st, "subjects=50&cols=3&hideButton=yes&title=" + createPortraitOrderes(st).studyName + "&genders=M,F,M,F,M,F,M,F,M,F,M,F,M,F,M,F,M,F,M,F,M,F,M,F,F,M,F,M,F,F,M,F,M,F,M,M,F,M,F,M,F,M,F,M,F,M,M,M,M,F,&exams=5&traits=0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,&SAIs=27:65:21:44:18;58:77:72:58:88;30:54:39:81:42;52:88:58:76:102;24:35:24:38:27;30:83:51:77:108;25:91:25:82:64;64:74:34:46:80;16:53:30:89:23;17:35:34:76:59;35:76:63:70:37;20:49:36:68:80;14:31:23:20:20;19:69:52:64:72;21:92:65:53:25;19:40:19:80:102;21:75:48:71:64;43:73:58:93:94;52:74:56:80:69;14:83:53:79:108;36:77:50:72:51;19:70:26:70:88;6:41:15:39:14;13:67:23:109:97;38:90:35:114:119;42:65:67:NA:67;48:81:69:115:120;37:90:82:37:71;49:58:42:87:93;40:86:62:104:92;67:65:58:68:53;33:68:35:25:95;28:70:67:96:22;18:53:8:15:45;20:60:30:106:74;29:75:39:112:90;6:86:19:120:120;12:15:20:106:52;30:71:38:85:103;31:77:56:67:66;14:39:28:94:107;36:57:23:64:76;45:103:54:93:97;51:71:56:98:51;65:82:64:99:101;47:75:76:116:62;60:101:58:111:58;50:85:48:94:111;8:37:6:30:56;10:63:19:29:13;&grades=26,29,29,50,33;34,38,38,49,NA;29,42,52,67,32;23,36,25,52,59;28,26,30,52,12;21,38,42,45,51;22,25,21,40,21;46,32,49,49,59;37,31,33,54,37;14,15,13,32,38;23,17,17,33,18;20,23,21,35,32;42,40,40,51,30;47,42,52,51,55;26,25,22,37,18;16,33,14,44,48;43,41,36,54,30;28,37,34,48,49;34,32,39,34,19;28,55,47,49,62;22,42,43,46,31;29,40,30,40,60;35,36,35,39,28;26,29,28,48,66;28,44,40,56,71;27,29,25,NA,11;25,40,33,52,69;19,25,35,35,41;35,34,33,62,57;29,16,28,58,75;36,34,25,64,46;30,47,40,47,70;25,31,NA,57,NA;20,22,13,57,64;20,24,18,57,23;34,31,15,48,39;39,31,25,52,57;26,32,33,50,34;23,32,31,52,55;21,45,50,61,35;19,41,33,58,73;35,41,35,54,34;22,25,31,51,56;28,18,46,56,17;37,50,43,45,56;37,52,50,70,42;31,40,47,67,27;26,31,29,47,33;23,37,25,NA,NA;24,24,26,43,34;&sBars=0,100,0:0,24.29,75.71:0,46.82,53.18:0,54.63,45.37:0,49.7,50.3;0,100,0:0,54.33,45.67:0,49.35,50.65:0,52.66,47.34:0,49.36,50.64;0,100,0:0,40.88,59.12:0,27.77,72.23:0,17.97,82.03:0,40.82,59.18;0,100,0:0,63.24,36.76:0,55.67,44.33:0,47.41,52.59:0,41.05,58.95;0,100,0:0,54.12,45.88:0,29.44,70.56:0,27.73,72.27:0,45.98,54.02;0,100,0:0,27.46,72.54:0,16.88,83.12:0,30.18,69.82:0,24.07,75.93;0,100,0:0,48.62,51.38:0,37.13,62.87:0,66.45,33.55:0,56.28,43.72;0,100,0:0,55.49,44.51:0,83.43,16.57:0,73.11,26.89:0,66.47,33.53;0,100,0:0,63.8,36.2:0,47.08,52.92:0,53,47:0,60.16,39.84;0,100,0:0,51.41,48.59:0,65.15,34.85:0,64.9,35.1:0,33.85,66.15;0,100,0:0,64,36:0,63.35,36.65:0,70.67,29.33:0,59.96,40.04;0,100,0:0,52.93,47.07:0,53.91,46.09:0,60.88,39.12:0,46.12,53.88;0,100,0:0,48.91,51.09:0,54.21,45.79:0,42.48,57.52:0,83.05,16.95;0,100,0:0,57.2,42.8:0,62.12,37.88:0,49.15,50.85:0,76.97,23.03;0,100,0:0,73.8,26.2:0,65.31,34.69:0,75.21,24.79:0,41.71,58.29;0,100,0:0,57.13,42.87:0,52.33,47.67:0,59.22,40.78:0,34.4,65.6;0,100,0:0,56.72,43.28:0,91,9:0,88.49,11.51:0,68.27,31.73;0,100,0:0,49.41,50.59:0,54.5,45.5:0,47.43,52.57:0,33.33,66.67;0,100,0:0,54.08,45.92:0,44.54,55.46:0,56.69,43.31:0,69.62,30.38;0,100,0:0,50.61,49.39:0,48.14,51.86:0,39.91,60.09:0,40.4,59.6;0,100,0:0,59.9,40.1:0,41.11,58.89:0,47.14,52.86:0,37.63,62.37;0,100,0:0,68.16,31.84:0,71.64,28.36:0,82,18:0,46.82,53.18;0,100,0:0,56.41,43.59:0,53.01,46.99:0,42.29,57.71:0,64.18,35.82;0,100,0:0,68.99,31.01:0,60.26,39.74:0,48,52:0,34.13,65.87;0,100,0:0,21.46,78.54:0,32.51,67.49:0,21.66,78.34:0,18.68,81.32;0,100,0:0,51.02,48.98:0,49.5,50.5::0,69.59,30.41;0,100,0:0,52.19,47.81:0,46.52,53.48:0,49.36,50.64:0,39.72,60.28;0,100,0:0,42.92,57.08:0,48.62,51.38:0,39.5,60.5:0,68.18,31.82;0,100,0:0,46.82,53.18:0,45.26,54.74:0,38.24,61.76:0,58.27,41.73;0,100,0:0,51.69,48.31:0,45.93,54.07:0,42.94,57.06:0,47.78,52.22;0,100,0:0,60.32,39.68:0,72.54,27.46:0,31.66,68.34:0,65.9,34.1;0,100,0:0,48.19,51.81:0,60.74,39.26:0,54.89,45.11:0,15.59,84.41;0,100,0:0,23.57,76.43:0,47.48,52.52:0,40.95,59.05:0,45.69,54.31;0,100,0:0,62.18,37.82:0,53.89,46.11:0,41.37,58.63:0,35.95,64.05;0,100,0:0,37,63:0,47.83,52.17:0,29.44,70.56:0,73.2,26.8;0,100,0:0,23.87,76.13:0,37.52,62.48:0,31.42,68.58:0,69.42,30.58;0,100,0:0,46.9,53.1:0,49.9,50.1:0,34.07,65.93:0,35.62,64.38;0,100,0:0,19.7,80.3:0,29.47,70.53:0,3.29,96.71:0,89.66,10.34;0,100,0:0,61.02,38.98:0,54.12,45.88:0,62.24,37.76:0,39.78,60.22;0,100,0:0,57.02,42.98:0,51.42,48.58:0,54.5,45.5:0,58.03,41.97;0,100,0:0,89.19,10.81:0,68.21,31.79:0,62.41,37.59:0,62.78,37.22;0,100,0:0,14.82,85.18:0,32.7,67.3:0,52.49,47.51:0,64.67,35.33;0,100,0:0,53.13,46.87:0,52.34,47.66:0,54.48,45.52:0,30.52,69.48;0,100,0:0,50.28,49.72:0,40.47,59.53:0,34.94,65.06:0,67.94,32.06;0,100,0:0,11.28,88.72:0,27.41,72.59:0,49.67,50.33:0,77.54,22.46;0,100,0:0,4.13,95.87:0,3.67,96.33:0,5.19,94.81:0,60.51,39.49;0,100,0:0,66.93,33.07:0,70.63,29.37:0,42.99,57.01:0,70.45,29.55;0,100,0:0,53.29,46.71:0,50.7,49.3:0,89.29,10.71:0,81.96,18.04;0,100,0:0,47.76,52.24:0,20.73,79.27:0,59.88,40.12:0,61.93,38.07;0,100,0:0,51.28,48.72:0,62.13,37.87:0,61.07,38.93:0,59.5,40.5;&namesSubjects=T001,T002,T003,T004,T005,T006,T007,T008,T009,T010,T011,T012,T013,T014,T015,T016,T017,T018,T019,T020,T021,T022,T023,T024,T025,T026,T027,T028,T029,T031,T032,T033,T034,T035,T036,T038,T039,T040,T041,T042,T043,T044,T045,T046,T047,T050,T051,T054,T055,T060,&titleGrades=ST&studyNo=" + createPortraitOrderes(st).studyNo +"&exLinks=http://subjectbook.times.uh.edu/displaySubject&namesExams=LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;~subjects=18&cols=3&hideButton=yes&title=" + createPortraitOrderes(st).studyName+ "&genders=M,F,F,F,M,F,M,F,M,F,F,F,M,M,M,M,F,M,&exams=5&traits=0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,&SAIs=25:50:33:61:72;14:66:45:71:28;34:78:42:55:69;6:68:20:60:31;23:29:27:25:NA;19:60:27:39:38;62:68:66:96:103;34:51:19:NA:112;40:43:39:46:20;17:97:30:91:48;92:87:80:84:64;45:79:54:88:14;21:25:21:48:49;27:86:73:80:74;35:44:66:78:93;20:64:35:80:73;32:68:44:82:38;16:71:50:53:101;&grades=31,42,41,49,44;15,15,11,NA,21;25,13,16,33,15;21,21,15,20,13;29,20,31,44,37;NA,0,0,0,2;28,20,26,57,61;21,23,28,NA,31;25,45,47,56,55;28,35,30,42,21;23,50,35,44,31;24,22,22,47,20;22,25,26,49,51;35,35,35,48,47;33,35,30,40,45;NA,39,42,66,41;28,33,37,39,20;20,39,30,60,36;&sBars=0,100,0:0,27.66,72.34:0,17.39,82.61:0,31.52,68.48:0,25.66,74.34;0,100,0:0,57.32,42.68:0,42.77,57.23:0,67.96,32.04:0,55.97,44.03;0,100,0:0,57.19,42.81:0,57.29,42.71:0,66.67,33.33:0,55.97,44.03;0,100,0:0,52,48:0,40.48,59.52:0,37.83,62.17:0,52,48;0,100,0:0,41.75,58.25:0,44.85,55.15:0,46.11,53.89:0,47.67,52.33;0,100,0:0,48.79,51.21:0,60.52,39.48:0,37.43,62.57:0,60.58,39.42;0,100,0:0,78.94,21.06:0,71.98,28.02:0,32.17,67.83:0,30.31,69.69;0,100,0:0,65.53,34.47:0,55.97,44.03::0,64.26,35.74;0,100,0:0,58.21,41.79:0,55.14,44.86:0,60.18,39.82:0,48.34,51.66;0,100,0:0,56.9,43.1:0,63.75,36.25:0,57.43,42.57:0,70.68,29.32;0,100,0:0,36.88,63.12:0,37.32,62.68:0,39.24,60.76:0,65.65,34.35;0,100,0:0,35.22,64.78:0,42.62,57.38:0,47.76,52.24:0,60.25,39.75;0,100,0:0,36.69,63.31:0,37.55,62.45:0,42.39,57.61:0,55.58,44.42;0,100,0:0,43.3,56.7:0,76.29,23.71:0,38.64,61.36:0,39.49,60.51;0,100,0:0,46.45,53.55:0,48.14,51.86:0,64.28,35.72:0,53.95,46.05;0,100,0:0,47.83,52.17:0,40.9,59.1:0,47,53:0,30.77,69.23;0,100,0:0,57.12,42.88:0,47.69,52.31:0,57.87,42.13:0,54.92,45.08;0,100,0:0,52.93,47.07:0,54.16,45.84:0,47.63,52.37:0,48.84,51.16;&namesSubjects=T061,T062,T064,T066,T068,T073,T074,T075,T076,T077,T079,T080,T081,T082,T083,T084,T086,T088,&titleGrades=ST&studyNo=" + createPortraitOrderes(st).studyNo +"&exLinks=http://subjectbook.times.uh.edu/displaySubject&namesExams=LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDL;LD1,LD2,LD3,LD4,FDN;LD1,LD2,LD3,LD4,FDL;");
          val temp = createPortraitOrderes(st)
          val portrait = context.actorOf(Props(new CreatePortrait(temp.folder_id, temp.studyName, temp.username, temp.studyTopology, temp.bio_code, temp.physio_code, temp.physio_code, temp.studyNo)))
          portrait ! FindLoadedPoint
          //portrait ! FindRadar
        } else {
          val temp = createPortraitOrderes(st)
          query = AddNewStudy.studyToPortrat(temp.folder_id, temp.studyName, temp.username, temp.studyTopology, temp.bio_code, temp.physio_code, temp.physio_code, st, DataBaseOperations.listOfFileExtensionPortrait(temp.username))

          //TODO implement study to portrat in scala using actors
        }
      }



  }

}
