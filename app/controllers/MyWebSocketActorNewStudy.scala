package controllers

import java.io.{File, FileInputStream, InputStream}

import Models.ReadExcelJava
import akka.actor.{PoisonPill, Actor, ActorRef}
import anorm._
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive

import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
import play.api.db.DB
import play.libs.Json
import play.api.libs.json._
import collection.JavaConversions._
import scala.collection.JavaConverters._

/**
 * Created by staamneh on 12/3/2015.
 */
case class Abstraction(userName: String, studyName: String, studyLocation: String, portriat: Boolean, public: Boolean, primaryExp: String, secondadryExp: List[Int]
                       ,primaryRes: String, secondadryRes: List[Int],StaticBefore: Int, StaticAfter: Int, covarite: Int, explColNo: Int, respColNo: Int, baseLine: String);

class MyWebSocketActorNewStudy(out: ActorRef) extends Actor {
  def receive = {
    case msg: String =>
      println("We receive a message using websocket ..............")
      context.become(checkStudyFolder(msg))

      //application/vnd.google-apps.spreadsheet

      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(msg)
    val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(msg)
    val service: Drive = GoogleDrive.buildService(googleCredential)

      val name = GoogleDrive.generateFileNameFromInputStream(new FileInputStream(new File("C:\\Users\\staamneh\\Downloads\\salah.xlsx")));
      GoogleDrive.insertFile(service,"Taamneh, Salah", "sdss", null, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", name);
      PlusSample.salah();

  }

  def checkStudyFolder(userName: String): Actor.Receive = {
    case folderId: String =>
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      var subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, folderId, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      // send the number of subjects
      out ! subjects.size.toString
      var sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subjects.head._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
      var nameOfSession = ""
      var first = true;
      for((key, value) <- sessions)
      {
        if(first)
          nameOfSession =  (value.getTitle.replaceFirst("(\\d*\\s*)", ""))
        else
          nameOfSession = nameOfSession + "," + (value.getTitle.replaceFirst("(\\d*\\s*)", ""))

        first= false;
      }
      println(nameOfSession)
      // send the session names to choise the baseline
      out ! nameOfSession
      context.become(explVar(userName,subjects.toList, sessions.toList))
  }

  def explVar(userName: String, studyFolder: List[(String, com.google.api.services.drive.model.File)], sessionFolder: List[(String, com.google.api.services.drive.model.File)]): Actor.Receive = {
    case "BACK" =>
      context.become(checkStudyFolder(userName))
    case expl: String =>
      println("Message receieve in expla: " +  expl)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      //var sessions = GoogleDrive.returnFilesInFolder(service, studyFolder.get(1)._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
      var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionFolder.get(1)._1, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> expl).apply().head
        //SQL("select  signal_code, signal_extension  from signals where owner ={user} \nunion \nselect  signal_code, signal_extension  from signals where  (owner = 'cplsubjectbook@gmail.com'  And signal_extension  not in (select signal_extension from signals where owner ={user} ));").on('user -> username)
        var temp = rowOption1[String]("SIGNAL_EXTENSION")
        for((name, info) <- signals)
        {
          if(info.getFileExtension.equals(temp))
          {
            //var js  = GoogleDrive.DownloadSignal(userName, info.getId, SharedData.GOOGLE_DRIVE, 1 ,8, 9, 2, null );
            val tt: ReadExcelJava = new ReadExcelJava;
            val input: InputStream = GoogleDrive.downloadFileByFileId(service, info.getId)
            val fileName: String = GoogleDrive.generateFileNameFromInputStream(input)
            val w = tt.fromExcelInputTempForCreateStudy(1, fileName, 9, 2)
            println("ssss" + w);
            // send the header of the selected file
            out ! w.toString
            context.become(resVar(userName,studyFolder, sessionFolder))
          }
        }

      }
    // var js = GoogleDrive.DownloadSignal(userName, file_location, SharedData.GOOGLE_DRIVE, 1 ,8, 9, 2, null );
    //out ! Signals.size.toString
  }
  def resVar(userName: String, studyFolder: List[(String, com.google.api.services.drive.model.File)], sessionFolder: List[(String, com.google.api.services.drive.model.File)]): Actor.Receive = {
    case "BACK" =>
      context.become(explVar(userName,studyFolder, sessionFolder))
    case resp: String =>
      println("Message receieve in response  is: " +  resp)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      //var sessions = GoogleDrive.returnFilesInFolder(service, studyFolder.get(1)._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
      var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionFolder.get(1)._1, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      DB.withConnection { implicit c =>
        val rowOption1 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> resp).apply().head
        //SQL("select  signal_code, signal_extension  from signals where owner ={user} \nunion \nselect  signal_code, signal_extension  from signals where  (owner = 'cplsubjectbook@gmail.com'  And signal_extension  not in (select signal_extension from signals where owner ={user} ));").on('user -> username)
        var temp = rowOption1[String]("SIGNAL_EXTENSION")
        for((name, info) <- signals)
        {
          if(info.getFileExtension.equals(temp))
          {
            //var js  = GoogleDrive.DownloadSignal(userName, info.getId, SharedData.GOOGLE_DRIVE, 1 ,8, 9, 2, null );
            val tt: ReadExcelJava = new ReadExcelJava;
            val input: InputStream = GoogleDrive.downloadFileByFileId(service, info.getId)
            val fileName: String = GoogleDrive.generateFileNameFromInputStream(input)
            val w = tt.fromExcelInputTempForCreateStudy(1, fileName, 9, 2)
            println("ssss" + w);
            // send the header of the selected file
            out ! w.toString

            context.become(beforeSubmission(userName,studyFolder, sessionFolder))
          }
        }

      }

  }

  def beforeSubmission(userName: String, studyFolder: List[(String, com.google.api.services.drive.model.File)], sessionFolder: List[(String, com.google.api.services.drive.model.File)]): Actor.Receive = {
    case "BACK" =>
      context.become(resVar(userName,studyFolder, sessionFolder))
    case resp: String =>


      println("Message receieve in covar  is: " +  resp)

     // val json: JsValue = Json.parse(resp)

      val json = Json.parse (resp)

      println(json.get("studyName"));


      val newOb= json.get("secondadryExp");
      var expList: List[Int] = List.empty[Int]

       for(i <- 1 to newOb.size())
        {
          expList = expList.::(newOb.get(i.toString).asInt())
          //println(newOb.get(i.toString));
        }


      val newOc= json.get("secondadryRes");
      var resList: List[Int] = List.empty[Int]

      for(i <- 1 to newOc.size())
      {
        resList = resList.::(newOc.get(i.toString).asInt())
        //println(newOb.get(i.toString));
      }


      println(resList)
      println(expList)
      println(json.get("portrait").asBoolean())
      println(json.get("public").asBoolean())
      println( json.get("primaryExp").asInt())
      println(json.get("StaticBefore").asInt())
      println(json.get("StaticAfter").asInt())
      println(json.get("covarite").asInt())


      //AddNewStudy.parseJson(resp);

      //val people = (json \ "data").validate[List[FBUser]].get

/*      var explExt = ""
      var resExt =""
      DB.withConnection { implicit c =>
        val rowOption1 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryExp").asInt()).apply().head

        val rowOption2 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryRes").asInt()).apply().head

        explExt = rowOption1[String]("SIGNAL_EXTENSION")

        println("6666666") + explExt  + "-----" + json.get("primaryExp").asInt() ;
        resExt = rowOption2[String]("SIGNAL_EXTENSION")


      }*/


      println("Before Chosinnnngng -----------")
      val forpor = Abstraction(userName, json.get("studyName").asText(), json.get("studyLocation").asText(), json.get("portrait").asBoolean(), json.get("public").asBoolean()
        , "perspiration", expList, "sim2", resList,json.get("StaticBefore").asInt(), json.get("StaticAfter").asInt(),
        json.get("covarite").asInt(), json.get("explanColNo").asInt(), json.get("respColNo").asInt(), json.get("baseLineFolder").asText());

      println("Before Chosinnnngng ")

      Global.CreateSTudyAbstractWay(forpor)

     println(forpor);



      context.self ! PoisonPill

  }

}
