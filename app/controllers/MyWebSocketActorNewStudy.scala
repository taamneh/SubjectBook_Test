package controllers

import java.io._
import java.util.{Collections, Random}

import Models.ReadExcelJava
import akka.actor.{Actor, ActorRef, PoisonPill}
import akka.event.slf4j.Logger
import anorm._
import com.fasterxml.jackson.databind.JsonNode
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.FileContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.{File, ParentReference, Permission}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
import play.api.db.DB
import play.libs.Json
import play.api.libs.json._
import play.Logger

import collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.immutable.TreeMap
import scala.util.control.Breaks._

/**
 * Created by staamneh on 12/3/2015.
 */

case class Abstraction(userName: String, sourceType: Int, studyName: String, studyLocation: String, portriat: Boolean, public: Boolean, primaryExp: String, secondadryExp: List[String]
                       ,primaryRes: String, secondadryRes: List[String],StaticBefore: Int, StaticAfter: Int, covarite: Int, explColNo: Int, respColNo: Int, baseLine: String, descriptor: String);



class MyWebSocketActorNewStudy(out: ActorRef) extends Actor {

  var perm = List.empty[String]
  override def postStop() = {
   println("A websocket was colosed");
  }
  def receive = {
    case "FILESTRUCTURE" =>
      println("Recived a Filestructure message");
      context.become(createFileStrcture)
    case msg: String =>
      println("We receive a message using websocket .............." + msg)
      context.become(checkStudyFolder(msg))
      //application/vnd.google-apps.spreadsheet
    //val name = GoogleDrive.generateFileNameFromInputStream(new FileInputStream(new File("C:\\Users\\staamneh\\Downloads\\salah.xlsx")));
      //GoogleDrive.insertFile(service,"Taamneh, Salah", "sdss", null, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", name);
      //PlusSample.salah();

  }



  def permutation(str: String): List[String] = {
    perm = List.empty[String]
    permutation("", str)
  }


  def permutation(prefix: String, str: String): List[String] = {
    val n = str.length
    if(n==0) perm =  perm :+ prefix
    else
      {
        for(i<- 0 to n-1){
          permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n))
        }
      }
    perm
  }

  def createFileStrcture: Actor.Receive = {
    case resp: String =>
      println(" All    : " +  resp)
      val json = Json.parse (resp)
      val user= json.get("userName").asText();
      val subjectsNo : Int = json.get("subjectNo").asInt();
      val studyName = json.get("studyName").asText();
      val desc = json.get("descriptor").toString
      //val descMap  = DataBaseOperations.fromJsonToMapDescriptor(desc)
      val descMap  = DataBaseOperations.fromJsonToMapDescriptorMultipleExperiments(desc)
      val armsList= DataBaseOperations.getArmsFromDescriptorMultipleExperiments(desc)

      val newOb= json.get("secondadryExp");
      var expList: List[Int] = List.empty[Int]
      var expListExtention: List[String] = List.empty[String]
      val newOc = json.get("secondadryRes");
      var resList: List[Int] = List.empty[Int]
      var resListExtentions: List[String] = List.empty[String]
      var blockingVarTemp  = json.get("blockingVar")
      var blockingVar : Map[(String, String), Int] = Map.empty


      var explExt = ""
      var resExt =""
      DB.withConnection { implicit c =>

        for (i <- 1 to newOb.size()) {
          expList = expList.::(newOb.get(i.toString).asInt())
          val row =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> newOb.get(i.toString).asInt()).apply().head

          val ext = row[String]("SIGNAL_EXTENSION")
          expListExtention = expListExtention.::(ext)
        }

        for (i <- 1 to newOc.size()) {
          resList = resList.::(newOc.get(i.toString).asInt())
          val row =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> newOc.get(i.toString).asInt()).apply().head

          val ext = row[String]("SIGNAL_EXTENSION")
          resListExtentions = resListExtentions.::(ext)
        }

        val rowOption1 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryExp").asInt()).apply().head
        //TODO convernt the secondary explantory and response variables to extensions .... we might need to upgrade to the newest anorm lib
        val rowOption2 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryRes").asInt()).apply().head
        explExt = rowOption1[String]("SIGNAL_EXTENSION")
        resExt  = rowOption2[String]("SIGNAL_EXTENSION")
      }

      ///////////////////// This to check if any blocking var was selected or not
      var age = false
      var gender = false
      for (i <- 1 to blockingVarTemp.size()) {
        if(blockingVarTemp.get(i.toString).toString.equals("\"1\"")){
          age = true
        }
        else{
          gender = true
        }
      }

      ////////////////

      var  pins : PrintWriter = new PrintWriter("C:\\temp\\pins.txt", "UTF-8");

      var armsWithblockingVar : Map[(Int, String,String, String), Int] = Map.empty

      var ageAndGender = "0,0"
      armsList match {
        case Some(data) =>

          if(data.size ==0){

            if (age && gender) {
              ageAndGender = "1,1"
              var localModulus = subjectsNo % 4
              armsWithblockingVar += (0, "0", "Young", "Male") -> ((subjectsNo / 4) + (1% (localModulus +1)))
              if(localModulus > 0) localModulus = localModulus -1
              armsWithblockingVar += (0, "0", "Young", "Female") -> ((subjectsNo / 4) + (1% (localModulus +1)))
              if(localModulus > 0) localModulus = localModulus -1
              armsWithblockingVar += (0, "0", "Old", "Male") -> ((subjectsNo / 4) + (1% (localModulus +1)))
              if(localModulus > 0) localModulus = localModulus -1
              armsWithblockingVar += (0, "0", "Old", "Female") -> ((subjectsNo / 4) + (1% (localModulus +1)))
            }
            else if (age) {
              ageAndGender = "1,0"
              armsWithblockingVar += (0, "0", "Young", "") -> subjectsNo / 2
              armsWithblockingVar += (0, "0", "Old", "") -> (subjectsNo - subjectsNo / 2)
            }
            else if (gender) {
              ageAndGender = "0,1"
              armsWithblockingVar += (0, "0", "", "Male") -> subjectsNo / 2
              armsWithblockingVar += (0, "0", "", "Female") -> (subjectsNo - subjectsNo / 2)
            }
            else {
              armsWithblockingVar += (0, "0", "", "") -> subjectsNo
            }

          }
          else{
            var no =0;
            var modulusArms = subjectsNo % data.size
            for((key, value) <- data) {   // if there is any group in the study

              var noOfsub = subjectsNo / data.size
              if (modulusArms> 0) { // to distribute the remaning subjects
                noOfsub = noOfsub + 1
                modulusArms = modulusArms -1
              }
              no = no + 1
              if (no == data.size) {
                //var temp : Int = (subjectsNo / data.size)
                //noOfsub = subjectsNo - (data.size - 1) * temp
                pins.print(value)
              }
              else
                pins.print(value + ",")

              if (age && gender) {
                ageAndGender = "1,1"
                var localModulus = noOfsub % 4
                armsWithblockingVar += (key, value, "Young", "Male") -> ((noOfsub / 4) + (1 % (localModulus+1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (key, value, "Young", "Female") -> ((noOfsub / 4) + (1 % (localModulus+1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (key, value, "Old", "Male") -> ((noOfsub / 4) + (1 % (localModulus+1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (key, value, "Old", "Female") -> ((noOfsub / 4) + (1 % (localModulus+1)))


              }
              else if (age) {
                ageAndGender = "1,0"
                armsWithblockingVar += (key, value, "Young", "") -> noOfsub / 2
                armsWithblockingVar += (key, value, "Old", "") -> (noOfsub - noOfsub / 2)
              }
              else if (gender) {
                ageAndGender = "0,1"
                armsWithblockingVar += (key, value, "", "Male") -> noOfsub / 2
                armsWithblockingVar += (key, value, "", "Female") -> (noOfsub - noOfsub / 2)
              }
              else {
                armsWithblockingVar += (key, value, "", "") -> noOfsub
              }

            }
          }



          pins.println()
      }


      pins.println(ageAndGender)

     println(armsWithblockingVar)



      var  writer : PrintWriter = new PrintWriter("C:\\temp\\StudyPsychometric.txt", "UTF-8");


      var str = ""
      // calcualte the number of unique intervension ... . . . . .
      // <achrom, show if not other, order, arm, fixed>
      var noOfIntervension =0
      var  ctr =1;
      descMap match {
        case Some(data) =>
          writer.println(data.size)
          println("The list of sessions       "   + data)
           data.foreach { x =>
             if (!x._2._5 && (x._2._4 == 0)) // we include the session in randomatization only if first, the session type is not fixed, second if the session belongs to all subjects
              {
                noOfIntervension = noOfIntervension + 1
                str = str+ctr;
                ctr= ctr +1
              }
             else if(x._2._5)
               ctr = ctr+1;

             writer.println("\"" + x._1.toString.replaceAll("\\s+", "") + "\":" + x._2._6.toString)
           }
      }


      println(" What is going on bro")
      /*for(i <- 1 to noOfIntervension)
        str = str + i  // we build the string that we want to be randomized
        */
     // permutation(str);  // to randomize the sessions ... . . . . . . .


      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(user)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(user)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      // createa a study folder
      var file =  insertFile(service, studyName.toString, "application/vnd.google-apps.folder", null, null)


      println(" after inserting the study folder")

      val newPermission: Permission = new Permission
      newPermission.setValue("")
      newPermission.setType("anyone")
      newPermission.setRole("reader")
      service.permissions.insert(file.getId, newPermission).execute

      var study_no = DataBaseOperations.GenerateStudyNoGD(studyName, user, SharedData.GOOGLE_DRIVE, 1, DataBaseOperations.fromJsonToMapDescriptorMultipleExperimentsSave(desc).toString, file.getId, resp)

      val subjectLevel = json.get("subjectLevelPy");
      val subjectLevelPost = json.get("sessionLevelPostPy");
      val sessionLevel = json.get("sessionLevelPy");




  println(" 1 @")

      writer.println("\"BACKGROUND\"" + ":"  + "0")
      for (i <- 1 to subjectLevel.size()) {
        writer.println(subjectLevel.get(i.toString).toString + ":"  + "0")
      }

      for (i <- 1 to sessionLevel.size()) {
        writer.println(sessionLevel.get(i.toString).toString + ":"  + "1")
      }

      for (i <- 1 to subjectLevelPost.size()) {
        writer.println(subjectLevelPost.get(i.toString).toString + ":"  + "2")
      }

      writer.close();

      var fileContent: java.io.File = new java.io.File("C:\\temp\\StudyPsychometric.txt")
      var mediaContent : FileContent = new FileContent("text/plain", fileContent);

      // create file for Muhsin
      var filetemp = insertFile(service,studyName + ".txt", "text/plain", file.getId,mediaContent )

      println(" 2 @")


     /* fileContent= new java.io.File("C:\\temp\\Muhsin.bar")
      mediaContent  = new FileContent("application/octet-stream", fileContent);
      var fl = insertFile(service,"muhsin.bar", "application/octet-stream", file.getId,mediaContent )*/


      var  writerAllSubjects : PrintWriter = new PrintWriter("C:\\temp\\Completion_progress.csv", "UTF-8");



      println(" 3 @")

      var tempForBen = TreeMap.empty[(String,String, String, String), String]
      var globableSubjecNo = 0;
      for((bv,numOfSub) <- armsWithblockingVar) { // go over arms by deafult we hae only one arm
          var countLocalArms = 0;
          // calculate the
          descMap match {
            case Some(data) =>
              data.foreach { x =>
                if ((x._2._4 == bv._1) && bv._1 != 0)  // find the count of all session that belong to the current arm
                  countLocalArms = countLocalArms + 1
              }

          }
          //countLocalArms = countLocalArms + noOfIntervension
          var newStr = str
          for(i <- 0 until  countLocalArms)
            newStr = newStr + (i + ctr)  // we build the string that we want to be randomized
          var temPerm = permutation(newStr);  // to randomize the sessions ... . . . . . . .

          println("str: " + str)
          println("newStr: " + newStr)
          println("Current Perumtation:  " + temPerm)


          println(" 4 @")


          var pointerToPerim = 0;  // we start getting the randomization form the beginning for each group
          var indexOfArm = 0;
          for( i<- 1 to numOfSub ){

              globableSubjecNo = globableSubjecNo +1
              var session_no: Int = 1
              if (temPerm.size < pointerToPerim + 1)
                pointerToPerim = 0;

              var rand = temPerm.get(pointerToPerim)
              pointerToPerim = pointerToPerim + 1

              var j: Integer = globableSubjecNo
              var num = String.format("%1$02d", j)

              var subjectName = "Subject" + num



              //pins.println(bv._2 + ","  + bv._3 + "," + bv._4 + "," + subjectName)
              tempForBen += (bv._2 ,bv._3, bv._4, subjectName) -> subjectName



              var file2 = insertFile(service,subjectName, "application/vnd.google-apps.folder", file.getId,null )


              writerAllSubjects.println(subjectName +"\t"+ file2.getId)
               /////////////////////  add .info fil
                var  writer : PrintWriter = new PrintWriter("C:\\temp\\info.txt", "UTF-8");

              if(bv._2.equalsIgnoreCase("0")){
                DataBaseOperations.InsertSubjectGD(subjectName, study_no, file2.getId, 11, 11)
                writer.println(bv._3 + "\n" + bv._4)
              }
              else{
                writer.println(bv._2 + "\n" + bv._3 + "\n" + bv._4)
                DataBaseOperations.InsertSubjectGD(subjectName, study_no,  file2.getId, 11, 11, bv._2)

              }




                writer.close();
                var fileContent: java.io.File = new java.io.File("C:\\temp\\info.txt")
                var mediaContent : FileContent = new FileContent("text/plain", fileContent);

               var filetemp = insertFile(service,subjectName, "text/plain", file2.getId,mediaContent )

                ///////////////////////////////////


              var ctr = 0;
              descMap match {
                case Some(data) =>
                  for ((key, value) <- data) {
                      var isBal = false


                       if(value._6 ==1) { // check if this session is intra
                        println("Is baseline: " + value._6  + "  Name : " + key)
                        isBal = true
                      }

                      var sessionName = key
                      sessionName = sessionName.replaceAll("\\s+", "")
                      if (value._4 !=0) {  // IF this session belong to a particulare group not all
                        if (value._4 == bv._1) {

                          if(value._5) {   // if fixed
                            sessionName = value._3 + sessionName
                          }
                         // if (value._2) { // if not other nor basline (3 or 0)
                          else {
                            sessionName = rand.charAt(ctr).toString + sessionName
                            ctr = ctr + 1;
                          }

                          var file3 = insertFile(service,sessionName, "application/vnd.google-apps.folder", file2.getId,null )



                          // primary explantory var
                          DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryExp").asInt(), "NA", 0, isBal, 2)
                          if(value._6!=0) // if this session is baseline there is no response variable
                            DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryRes").asInt(), "NA", 0, isBal, 1)

                          expList.foreach(x =>

                            if (x != json.get("primaryExp").asInt()) {
                              DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, x, "NA", 0, isBal, 0)
                            }

                          )
                        }

                      }
                      else {  // if this session belong to all subjects
                        if(value._5)// if the roder is fixed
                          {
                            sessionName = value._3 + sessionName
                          }
                        else  {
                          sessionName = rand.charAt(ctr).toString + sessionName
                          ctr = ctr + 1;
                        }
                        var file3 = insertFile(service,sessionName, "application/vnd.google-apps.folder", file2.getId,null )

                        //TODO try to get the baseline and replace zero with one if the session is baseline
                        // primary explantory var
                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryExp").asInt(), "NA", 0, isBal, 2)
                        if(value._6!=0) // if this session is baseline there is no response variable
                            DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryRes").asInt(), "NA", 0, isBal, 1)

                        expList.foreach(x =>

                          if (x != json.get("primaryExp").asInt()) {
                            DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, x, "NA", 0, isBal, 0)
                          }

                        )
                      }
                      session_no += 1
                    }

                }

            //out ! subjectName
            out ! "Subject " + num
        }
      }

      //

      for((key, value) <-  tempForBen)
        {
          pins.println(key._1 + ","  + key._2 + "," + key._3 + "," + key._4)
        }
      pins.close();
      writerAllSubjects.println("PendingSubject");
      writerAllSubjects.close();
      fileContent= new java.io.File("C:\\temp\\pins.txt")
      mediaContent  = new FileContent("text/plain", fileContent);
      filetemp = insertFile(service,studyName+"pins.txt", "text/plain", file.getId,mediaContent )



      fileContent = new java.io.File("C:\\temp\\Completion_progress.csv")
      mediaContent = new FileContent("text/csv", fileContent);
      filetemp = insertFile(service,"completion_progress.csv", "text/csv", file.getId,mediaContent )

  }

  def insertFile (service: Drive, name: String, mimeTyp: String, parentId: String, mediaContent : FileContent ): com.google.api.services.drive.model.File = {
    val randomGenerator: Random = new Random
    for(n <- 1 to 6) {
      try {
        val fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setTitle(name)
        fileMetadata.setMimeType(mimeTyp);
          if (parentId != null) {
          val parent: ParentReference = new ParentReference()
          parent.setId(parentId)
          fileMetadata.setParents(Collections.singletonList(parent));
        }
        val file =
          if (mediaContent != null)
            service.files().insert(fileMetadata, mediaContent).setFields("id").execute();
          else
            service.files().insert(fileMetadata).setFields("id").execute();
        return file
      } catch {
        case e: GoogleJsonResponseException =>
         // Logger.error("While inserting a file" + e.getMessage)

          if ((e.getDetails.getErrors.get(0).getReason == "rateLimitExceeded" || e.getDetails.getErrors.get(0).getReason == "userRateLimitExceeded")) {
            play.Logger.error("While inserting a file rate limit exceeded(salah) " + e)
            try {
              Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001) * n)
            }
            catch {
              case e2: InterruptedException => {
                play.Logger.error("got interrupted!" + e2)
              }
            }
          }
        case e: IOException => {
          println("error*******************************")
        }
      }
    }
    null
    // should add exception handling
  }

  def checkStudyFolder(userName: String): Actor.Receive = {

    case folderId: String =>
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      var subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, folderId, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x=>x);
      // send the number of subjects
      out ! subjects.size.toString
      println("SubjectName   " + subjects.head._2.getTitle)
      var sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subjects.head._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
      var nameOfSession = ""
      var first = true;

      var newTree : TreeMap[String, String] = TreeMap.empty[String, String ]
      for((key, value) <- sessions)
      {
        newTree += value.getTitle.replaceFirst("(\\d*\\s*)", "") -> value.getTitle.replaceFirst("(\\d*\\s*)", "")
      }

      for((key, value) <- newTree)
      {
        if(first)
          nameOfSession =  key //(value.getTitle.replaceFirst("(\\d*\\s*)", ""))
        else
          nameOfSession = nameOfSession + "," + key //(value.getTitle.replaceFirst("(\\d*\\s*)", ""))

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
    case "QUICK" =>

      println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ")
      context.become(beforeSubmissionQuick(userName, studyFolder, sessionFolder))

    case expl: String =>
      println("Message receieve in expla: " +  expl)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      var found = false;

      //var sessions = GoogleDrive.returnFilesInFolder(service, studyFolder.get(1)._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
      var tries = 0;
      val r = scala.util.Random
      while(!found && tries < 5) {
       // println(studyFolder.size - 1);
        var theNo = r.nextInt(studyFolder.size);
        var sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, studyFolder.get(theNo)._1, "mimeType = 'application/vnd.google-apps.folder'").asScala.toList
        println("The  NNNNN: " + theNo )
        theNo = r.nextInt(sessions.size);
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessions.get(theNo)._1, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

        DB.withConnection { implicit c =>

          val rowOption1 =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> expl).apply().head
          var temp = rowOption1[String]("SIGNAL_EXTENSION")
          for ((name, info) <- signals) {
            println("signal" + info.getTitle);
            if (info.getFileExtension.equalsIgnoreCase(temp)) {
              val tt: ReadExcelJava = new ReadExcelJava;
              val input: InputStream = GoogleDrive.downloadFileByFileId(service, info.getId)
              val fileName: String = GoogleDrive.generateFileNameFromInputStream(input)
              val w = tt.fromExcelInputTempForCreateStudy(1, fileName, 9, 2)
              println("ssss" + w);
              // send the header of the selected file
              out ! w.toString
              found = true;
              context.become(resVar(userName, studyFolder, sessionFolder))
            }
          }



        }
        tries = tries +1
      }
      if (!found)
        out ! "WRONG"
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
      var found = false;

      var tries = 0;
      val r = scala.util.Random
      while(!found && tries < 5) {
        var theNo = r.nextInt(sessionFolder.size);
        //var sessions = GoogleDrive.returnFilesInFolder(service, studyFolder.get(1)._1, "mimeType = 'application/vnd.google-apps.folder'").asScala
        var signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionFolder.get(theNo)._1, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        DB.withConnection { implicit c =>
          val rowOption1 =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> resp).apply().head
          //SQL("select  signal_code, signal_extension  from signals where owner ={user} \nunion \nselect  signal_code, signal_extension  from signals where  (owner = 'cplsubjectbook@gmail.com'  And signal_extension  not in (select signal_extension from signals where owner ={user} ));").on('user -> username)
          var temp = rowOption1[String]("SIGNAL_EXTENSION")

          for ((name, info) <- signals) {
            if (info.getFileExtension.equalsIgnoreCase(temp)) {
              //var js  = GoogleDrive.DownloadSignal(userName, info.getId, SharedData.GOOGLE_DRIVE, 1 ,8, 9, 2, null );
              val tt: ReadExcelJava = new ReadExcelJava;
              val input: InputStream = GoogleDrive.downloadFileByFileId(service, info.getId)
              val fileName: String = GoogleDrive.generateFileNameFromInputStream(input)
              val w = tt.fromExcelInputTempForCreateStudy(1, fileName, 9, 2)
              println("ssss" + w);
              // send the header of the selected file
              found = true;
              out ! w.toString
              context.become(beforeSubmission(userName, studyFolder, sessionFolder))
            }
          }

          tries = tries + 1
        }
      }
        if(!found)
          out ! "WRONG"  // this should be sent to client when the signal he chose is not there



  }
  def beforeSubmissionQuick(userName: String, studyFolder: List[(String, com.google.api.services.drive.model.File)], sessionFolder: List[(String, com.google.api.services.drive.model.File)]): Actor.Receive = {
    case resp: String =>
      val json = Json.parse (resp)

      println("333333333" + json)

      var str =Abstraction(userName,json.get("sourceType").asInt(),  json.get("studyName").asText(), json.get("studyLocation").asText(), false, false   , null, List(), null, List(),12, 12,
        130, 2, 2, null, null);


      println("@@@@@@@@@" + str)
     /* var str = Abstraction(userName,1,"Using Test-Salah","0BzuoB3uc0tTVS0hmSWkzcU9pM2s",false,false,"pp",List("peda"),"res2",List(),12,12,130,2,4,
        "Normal-Drive",null)*/

      Global.CreateSTudyAbstractWay(str);
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
      var expListExtention: List[String] = List.empty[String]
      val newOc = json.get("secondadryRes");
      var resList: List[Int] = List.empty[Int]
      var resListExtentions: List[String] = List.empty[String]

      DB.withConnection { implicit c =>

        for (i <- 1 to newOb.size()) {
          expList = expList.::(newOb.get(i.toString).asInt())
          val row =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> newOb.get(i.toString).asInt()).apply().head

          val ext = row[String]("SIGNAL_EXTENSION")
          expListExtention = expListExtention.::(ext)
        }




        for (i <- 1 to newOc.size()) {
          resList = resList.::(newOc.get(i.toString).asInt())
          val row =
            SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> newOc.get(i.toString).asInt()).apply().head

          val ext = row[String]("SIGNAL_EXTENSION")
          resListExtentions = resListExtentions.::(ext)
        }
      }


      var explExt = ""
      var resExt =""
      DB.withConnection { implicit c =>
        val rowOption1 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryExp").asInt()).apply().head

        //TODO convernt the secondary explantory and response variables to extensions .... we might need to upgrade to the newest anorm lib

        val rowOption2 =
          SQL("select SIGNAL_EXTENSION from signals where SIGNAL_CODE ={code};").on('code -> json.get("primaryRes").asInt()).apply().head

        explExt = rowOption1[String]("SIGNAL_EXTENSION")
        resExt  = rowOption2[String]("SIGNAL_EXTENSION")
       // println("6666666") + explExt  + "-----" + resExt ;
     }
      println(json.get("descriptor").toString);


      val forpor = Abstraction(userName,json.get("sourceType").asInt(),  json.get("studyName").asText(), json.get("studyLocation").asText(), json.get("portrait").asBoolean(), json.get("public").asBoolean()
        , explExt, expListExtention, resExt, resListExtentions,json.get("StaticBefore").asInt(), json.get("StaticAfter").asInt(),
        json.get("covarite").asInt(), json.get("explanColNo").asInt(), json.get("respColNo").asInt(), json.get("baseLineFolder").asText(), json.get("descriptor").toString);

      /*val forpor = Abstraction(userName, json.get("studyName").asText(), "C:\\Users\\staamneh\\Downloads\\study", json.get("portrait").asBoolean(), json.get("public").asBoolean()
        , explExt, expList, resExt, resList,json.get("StaticBefore").asInt(), json.get("StaticAfter").asInt(),
        json.get("covarite").asInt(), json.get("explanColNo").asInt(), json.get("respColNo").asInt(), json.get("baseLineFolder").asText());*/



      Global.CreateSTudyAbstractWay(forpor)

      play.Logger.info("The study json received from users is: " + forpor
      )
      (forpor);
      context.self ! PoisonPill
  }

}
