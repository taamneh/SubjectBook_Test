package controllers

import java.io._

import Models.{Activity, ReadExcelJava, _}
import play.api.mvc.Controller

import scala.util.control.Breaks._
import java.util
import java.util.{Collections, Random, Scanner}

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive

import collection.JavaConversions._
import scala.collection.JavaConverters._
import Models._
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool
import play.api.Play.current
import anorm._
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import play.api.db.DB

import scala.collection.immutable.TreeMap
import com.google.api.client.http.FileContent
import com.google.api.services.drive.model.{FileList, ParentReference}
import play.Logger

/**
  * Created by staamneh on 10/27/2014.
  *
  *
  *
  */


object AuxiliaryMethods extends Controller {

  // this is one time function
  def changeDataBaseForAllUsers (): Unit ={

    DB.withConnection { implicit c =>
      val users = SQL("select userid from credential;")
      var allUsers = users().map(row =>
        row[String]("userid")).toList

      for (user <- allUsers) {

        val isThere =
          SQL("select count(*) as co from signals where signal_extension='mp3' and owner = {owner} ;").on('owner -> user).apply().head
        var ct = isThere[Long]("co");

        if (ct == 0) {
          SQL("insert into signals(signal_desc ,signal_extension ,data_type,ytitle,frame_rate, first_row, first_col, owner) values (  'audio2', 'mp3', 10, '', 8,9, 2, {user})")
            .on('user -> user).executeInsert()
        }

      }
    }


  }


  def ReportDataInDrPFormatNew (studyNo: Int) : String ={
    Global.startScanningDataSet(studyNo);
    ""
   /* println("what is going on guys .........")
    var userName = "";
    var loc = "";

    //var lst = List("Subject01","Subject41", "Subject50", "Subject51", "Subject44", "Subject37", "Subject12", "Subject9", "Subject66" )
    var lst : List[String]= List()

    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      loc = fileLocation[String]("STUDY_LOCATION");
      userName = fileLocation[String]("study_owner");

      val vaildSubjects = SQL("select subject_id from subject where replicated =8 AND study_id ={id};").on('id -> studyNo)
      lst = vaildSubjects().map(row =>  (row[String]("subject_id"))).toList
    }

    var allData: java.util.TreeMap[String, java.util.List[EntryForExcel]] = new util.TreeMap[String, java.util.List[EntryForExcel]]()
    val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
    val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
    val service: Drive = GoogleDrive.buildService(googleCredential)

    val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")
    var ctr= 0;

    for ((subject, subjectInfo) <- subjects ) {

      println("Subject  : "  + subjectInfo.getTitle )
      if(!lst.contains(subjectInfo.getTitle)) {
        val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var tempList : java.util.List[EntryForExcel] = new util.ArrayList[EntryForExcel]()
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


          var activtityFile: util.ArrayList[Activity] = null;

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

                else if (extension.equalsIgnoreCase("res2")) {
                  var input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                  var fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                  speed = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 1);
                  acc = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 2);
                  braking = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 3);
                  stering = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 4);
                  laneOffest = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 5);

                  if (minFrmes == null) minFrmes = speed
                  else {
                    if (speed.size() < minFrmes.size()) minFrmes = speed;
                  }
                }

                else if (extension.equalsIgnoreCase("peda")) {
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
            if (laneOffest == null)
              laneOffest = emptySignal

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
            var gSize = minFrmes.size();

            for ((time, value) <- minFrmes) {
              tempList.add(new EntryForExcel(time, sessionName, "No-Multitasking", "NA", ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(ctr * Math.floor(speed.size() / gSize).toInt)), acc.get(accList(ctr * Math.floor(acc.size() / gSize).toInt)), braking.get(brakeList(ctr * Math.floor(braking.size() / gSize).toInt)), stering.get(steeringList(ctr * Math.floor(stering.size() / gSize).toInt)), laneOffest.get(laneList(ctr * Math.floor(laneOffest.size() / gSize).toInt))));
              ctr = ctr + 1;
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

            var gSize = minFrmes.size();
            var distinctAnno: TreeMap[Int, String] = TreeMap.empty
            import scala.collection.JavaConversions._
            for (temp <- activtityFile) {
              if (!distinctAnno.contains(temp.actionType)) {
                distinctAnno += temp.actionType -> temp.label
              }
            }
            for ((time, value) <- minFrmes) {
              var stimulus = "No-Multitasking"
              var stimulusFailure = "NA"
              for ((code, name) <- distinctAnno) {
                {
                  if (!name.toLowerCase().contains("light"))
                    if (inActitivity(time, code, activtityFile)) {
                      if (name.toLowerCase().contains("failure"))
                        stimulusFailure = "F"
                      else
                        stimulus = name
                    }
                }
              }
              //tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, value));
              //tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, ppSignal.get(ppList(ctr + ppSignal.size()/gSize)),  edaSignal.get(edaList(ctr + edaSignal.size()/gSize)), hrSignal.get(hrList(ctr + hrSignal.size()/gSize)),brSignal.get(brList(ctr + brSignal.size()/gSize)),  hrvSignal.get(hrvList(ctr + hrvSignal.size()/gSize)) ));
              tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(ctr * Math.floor(speed.size() / gSize).toInt)), acc.get(accList(ctr * Math.floor(acc.size() / gSize).toInt)), braking.get(brakeList(ctr * Math.floor(braking.size() / gSize).toInt)), stering.get(steeringList(ctr * Math.floor(stering.size() / gSize).toInt)), laneOffest.get(laneList(ctr * Math.floor(laneOffest.size() / gSize).toInt))));
              ctr = ctr + 1;
            }
          }
        }
         // }
        }
        if(tempList.size() > 0)
          allData += subjectInfo.getTitle -> tempList
      }
    }
    ReadExcelJava.createExcelFile(allData, "Time" + "\t" + "Cleaned" + "\t" +"Drive" + "\t" +  "Secondary" + "\t" + "Failure event" + "\t" + "Speed"  + "\t" + "Acceleration"  + "\t" + "Braking"  + "\t" + "Steering"  + "\t" + "LaneOffset" + "\t" + "PalmEDA" + "\t" + "Heart Rate" + "\t" + "Breating Rate" + "\t" + "HRV"  )
    "C:\\temp\\new.xls"
    */
  }
  def ReportDataInDrPFormat (studyNo: Int) : String ={

    println("what is going on guys .........")
    var userName = "";
    var loc = "";

    //var lst = List("Subject01","Subject41", "Subject50", "Subject51", "Subject44", "Subject37", "Subject12", "Subject9", "Subject66" )
    var lst : List[String]= List()

    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      loc = fileLocation[String]("STUDY_LOCATION");
      userName = fileLocation[String]("study_owner");

      val vaildSubjects = SQL("select subject_id from subject where replicated =8 AND study_id ={id};").on('id -> studyNo)
      lst = vaildSubjects().map(row =>  (row[String]("subject_id"))).toList
    }


    val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
    val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
    val service: Drive = GoogleDrive.buildService(googleCredential)

    val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")
    var ctr= 0;

    val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)

    var  writer : PrintWriter = new PrintWriter(ProductionSide.tempDownloading+ studyNo + ".csv", "UTF-8");

    var header= "Time" + ","  +"Drive" + "," +  "Secondary" + "," + "Failure event";

    var nameOfVar : List[String] = List.empty

    var body ="";

    /*for ((subject, subjectInfo) <- subjects ) {

      println("Subject  : "  + subjectInfo.getTitle )
      if(!lst.contains(subjectInfo.getTitle)) {
        val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var tempList : java.util.List[EntryForExcel] = new util.ArrayList[EntryForExcel]()
        import scala.collection.JavaConversions._
        for ((sessionId, sessionInfo) <- sessions) {
          var minFrmes: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;

          // key here is (sessionname, var. name
          var allData : TreeMap[(String, String), java.util.TreeMap[java.lang.Double, java.lang.Double] ]= TreeMap.empty


          var activtityFile: util.ArrayList[Activity] = null;

          var file: com.google.api.services.drive.model.File = sessionInfo
          var sessionName: String = sessionInfo.getTitle
          sessionName = sessionName.replaceFirst("(\\d*\\s*)", "")

          var stimulusName: String = sessionName match {
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


                if (userDefinedExtension.contains(extension.toLowerCase))
                  {
                    val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                    val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                    val signal  = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                    allData += (sessionName,extension) ->  signal

                    if(!nameOfVar.contains(extension)){
                      nameOfVar = extension :: nameOfVar
                    }

                    if (minFrmes == null) minFrmes = signal
                    else {
                      if (signal.size() < minFrmes.size()) minFrmes = signal;
                    }
                  }
                // TODO in case there is NO primary explantory varialbe make the secondary to appear first

               /* else if (extension.equalsIgnoreCase("res2")) {
                  var input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                  var fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)
                  var speed = ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 1);
                  allData += "speed" ->  speed
                  if(!nameOfVar.contains("speed")){
                    nameOfVar = "speed" :: nameOfVar
                  }

                  allData += "acceleration" ->ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 2);

                  if(!nameOfVar.contains("acceleration")){
                    nameOfVar = "acceleration" :: nameOfVar
                  }
                  allData += "braking" ->ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 3);

                  if(!nameOfVar.contains("braking")){
                    nameOfVar = "braking" :: nameOfVar
                  }
                  allData += "steering" ->ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 4);

                  if(!nameOfVar.contains("steering")){
                    nameOfVar = "steering" :: nameOfVar
                  }
                  allData += "laneOffset" ->ReadExcelJava.getAllSignalFromExcelAbstractionNoDeletion(fileName2, 5);

                  if(!nameOfVar.contains("laneOffset")){
                    nameOfVar = "laneOffset" :: nameOfVar
                  }

                  if (minFrmes == null) minFrmes = speed
                  else {
                    if (speed.size() < minFrmes.size()) minFrmes = speed;
                  }
                }*/

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

              /*if (edaSignal == null)
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
              var gSize = minFrmes.size();*/

              for ((time, value) <- minFrmes) {
                for(i <- 0 to allData.size){
                  header = header + allData
                }


                tempList.add(new EntryForExcel(time, sessionName, "No-Multitasking", "NA", ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(ctr * Math.floor(speed.size() / gSize).toInt)), acc.get(accList(ctr * Math.floor(acc.size() / gSize).toInt)), braking.get(brakeList(ctr * Math.floor(braking.size() / gSize).toInt)), stering.get(steeringList(ctr * Math.floor(stering.size() / gSize).toInt)), laneOffest.get(laneList(ctr * Math.floor(laneOffest.size() / gSize).toInt))));
                ctr = ctr + 1;
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

              var gSize = minFrmes.size();
              var distinctAnno: TreeMap[Int, String] = TreeMap.empty
              import scala.collection.JavaConversions._
              for (temp <- activtityFile) {
                if (!distinctAnno.contains(temp.actionType)) {
                  distinctAnno += temp.actionType -> temp.label
                }
              }
              for ((time, value) <- minFrmes) {
                var stimulus = "No-Multitasking"
                var stimulusFailure = "NA"
                for ((code, name) <- distinctAnno) {
                  {
                    if (!name.toLowerCase().contains("light"))
                      if (inActitivity(time, code, activtityFile)) {
                        if (name.toLowerCase().contains("failure"))
                          stimulusFailure = "F"
                        else
                          stimulus = name
                      }
                  }
                }
                //tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, value));
                //tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, ppSignal.get(ppList(ctr + ppSignal.size()/gSize)),  edaSignal.get(edaList(ctr + edaSignal.size()/gSize)), hrSignal.get(hrList(ctr + hrSignal.size()/gSize)),brSignal.get(brList(ctr + brSignal.size()/gSize)),  hrvSignal.get(hrvList(ctr + hrvSignal.size()/gSize)) ));
                tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, ppSignal.get(ppList(ctr * Math.floor(ppSignal.size() / gSize).toInt)), edaSignal.get(edaList(ctr * Math.floor(edaSignal.size() / gSize).toInt)), hrSignal.get(hrList(ctr * Math.floor(hrSignal.size() / gSize).toInt)), brSignal.get(brList(ctr * Math.floor(brSignal.size() / gSize).toInt)), hrvSignal.get(hrvList(ctr * Math.floor(hrvSignal.size() / gSize).toInt)), speed.get(speedList(ctr * Math.floor(speed.size() / gSize).toInt)), acc.get(accList(ctr * Math.floor(acc.size() / gSize).toInt)), braking.get(brakeList(ctr * Math.floor(braking.size() / gSize).toInt)), stering.get(steeringList(ctr * Math.floor(stering.size() / gSize).toInt)), laneOffest.get(laneList(ctr * Math.floor(laneOffest.size() / gSize).toInt))));
                ctr = ctr + 1;
              }
            }
          }
          // }
        }
        if(tempList.size() > 0)
          allData += subjectInfo.getTitle -> tempList
      }
    }
    ReadExcelJava.createExcelFile(allData, "Time" + "\t" + "Cleaned" + "\t" +"Drive" + "\t" +  "Secondary" + "\t" + "Failure event" + "\t" + "Speed"  + "\t" + "Acceleration"  + "\t" + "Braking"  + "\t" + "Steering"  + "\t" + "LaneOffset" + "\t" + "PalmEDA" + "\t" + "Heart Rate" + "\t" + "Breating Rate" + "\t" + "HRV"  )
    */
    "C:\\temp\\new.xls"




   /* println("what is going on guys .........")
    var userName = "";
    var loc = "";

    //var lst = List("Subject01","Subject41", "Subject50", "Subject51", "Subject44", "Subject37", "Subject12", "Subject9", "Subject66" )
    var lst : List[String]= List()

    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      loc = fileLocation[String]("STUDY_LOCATION");
      userName = fileLocation[String]("study_owner");

      val vaildSubjects = SQL("select subject_id from subject where replicated =8 AND study_id ={id};").on('id -> studyNo)
      lst = vaildSubjects().map(row =>  (row[String]("subject_id"))).toList
    }

    var allData: java.util.TreeMap[String, java.util.List[EntryForExcel]] = new util.TreeMap[String, java.util.List[EntryForExcel]]()
    val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
    val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
    val service: Drive = GoogleDrive.buildService(googleCredential)

    val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")


    var  writer : PrintWriter = new PrintWriter("C:\\temp\\allData.csv", "UTF-8");
    var  videos : PrintWriter = new PrintWriter("C:\\temp\\allvideos.csv", "UTF-8");
    writer.println("SubjectID" + "\t" + "time" + "\t" + "Drive" + "\t" + "Activity" + "\t" + "Failure" + "\t" + "Perspiration");
    videos.println("SubjectID" + "," + "folderLocation" + "," + "task" + "," + "length" + "," + "start" + "," + "nubmerOfVideoFiles");
    var ctr= 0;

    for ((subject, subjectInfo) <- subjects ) {
      if(!lst.contains(subjectInfo.getTitle)) {
        val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var tempList : java.util.List[EntryForExcel] = new util.ArrayList[EntryForExcel]()

        import scala.collection.JavaConversions._
        for ((sessionId, sessionInfo) <- sessions) {

          var ppSignal: java.util.TreeMap[java.lang.Double, java.lang.Double] = null;
          var activtityFile: util.ArrayList[Activity] = null;

          var file: com.google.api.services.drive.model.File = sessionInfo
          var sessionName: String = sessionInfo.getTitle

          sessionName = sessionName.replaceFirst("(\\d*\\s*)", "")

          var stimulusName: String = sessionName match {
            case "Baseline" => "No-Multitasking"
            case "PracticeDrive" => "No-Multitasking"
            case "NormalDrive" => "No-Multitasking"
            case "MotoricDrive" => "Motoric Load"
            case "CognitiveDrive" => "Cognitive Load"
            case "FinalDrive" => "Motoric Load"
            case _ => "No-Multitasking"
          }
          sessionName = sessionName match {
            case "Baseline" => "1"
            case "PracticeDrive" => "2"
            case "NormalDrive" => "3"
            case "CognitiveDrive" => "4"
            case "MotoricDrive" => "5"
            case "FinalDrive" => "6"
            case _ => sessionName
          }

          if(!sessionName.equals("BL") && !sessionName.equals("PD") ) {
            val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
            var signal_code: Int = 1
            var vidoeCount= 0;
            breakable {
              for ((signalId, signalInfo) <- Signals) {
                if ( ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar"))) {
                  var extension: String = signalInfo.getFileExtension
                  println("extension  " + extension + "   Name: " + signalInfo.getTitle)
                  if (signalInfo.getTitle.contains(".bar.xlsx") || signalInfo.getTitle.contains(".bar")) // to hnalde muhsin issue with .bar extension
                  {
                    extension = "bar"
                  }
                  // TODO in case there is NO primary explantory varialbe make the secondary to appear first
                  if (extension.equalsIgnoreCase("pp")) {
                    val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                    val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)

                    ppSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                  }

                  if (extension.equalsIgnoreCase("stm")) {
                    activtityFile = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, signalId));

                  }
                  if (extension.equalsIgnoreCase("avi")) {
                    vidoeCount =  vidoeCount +1;
                  }
                }
              }
            }
            if(ppSignal != null)
              videos.println(subjectInfo.getTitle() + "," + "C:\\Users\\staamneh\\sim2GD\\Sim2\\" + subjectInfo.getTitle() + "," + sessionInfo.getTitle + "," + ppSignal.lastKey() + "," + " " + "," + vidoeCount);
            else
              videos.println(subjectInfo.getTitle() + "," + "C:\\Users\\staamneh\\sim2GD\\Sim2\\" + subjectInfo.getTitle() + "," + sessionInfo.getTitle + "," + 0 + "," + " " + "," + vidoeCount);


            if (activtityFile == null && ppSignal != null) {
              for ((time, value) <- ppSignal) {
                writer.println(subjectInfo.getTitle() + "\t" + time + "\t" + sessionName + "\t" + "No-Multitasking" + "\t" + "NA" + "\t" + value);
              }
            }
            else if (activtityFile != null && ppSignal != null) {
              var distinctAnno: TreeMap[Int, String] = TreeMap.empty

              import scala.collection.JavaConversions._
              for (temp <- activtityFile) {
                if (!distinctAnno.contains(temp.actionType)) {
                  distinctAnno += temp.actionType -> temp.label
                }
              }
              for ((time, value) <- ppSignal) {
                var stimulus = "No-Multitasking"
                var stimulusFailure = "NA"
                for ((code, name) <- distinctAnno) {
                  {
                    if (!name.toLowerCase().contains("light"))
                      if (inActitivity(time, code, activtityFile)) {
                        if (name.toLowerCase().contains("failure"))
                          stimulusFailure = "F"
                        else
                          stimulus = stimulusName
                      }
                  }

                }

                writer.println(subjectInfo.getTitle() + "\t" + time + "\t" + sessionName + "\t" + stimulus + "\t" + stimulusFailure + "\t" + value);
                tempList.add(new EntryForExcel(time, sessionName, stimulus, stimulusFailure, value));
              }
            }
          }
          if(sessionName.equals("BL") || sessionName.equals("PD") ) {
            val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
            var signal_code: Int = 1
            var vidoeCount= 0;
            breakable {
              for ((signalId, signalInfo) <- Signals) {
                if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar")) {
                  var extension: String = signalInfo.getFileExtension
                  // TODO in case there is NO primary explantory varialbe make the secondary to appear first
                  if (extension.equalsIgnoreCase("pp")) {
                    val input2: InputStream = GoogleDrive.downloadFileByFileId(service, signalId)
                    val fileName2: String = GoogleDrive.generateFileNameFromInputStream(input2)

                    ppSignal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                  }

                  if (extension.equalsIgnoreCase("avi")) {
                    vidoeCount =  vidoeCount +1;
                  }
                }
              }
            }
            if(ppSignal != null)
              videos.println(subjectInfo.getTitle() + "," + "C:\\Users\\staamneh\\sim2GD\\Sim2\\" + subjectInfo.getTitle() + "," + sessionInfo.getTitle + "," + ppSignal.lastKey() + "," + " " + "," + vidoeCount);
            else
              videos.println(subjectInfo.getTitle() + "," + "C:\\Users\\staamneh\\sim2GD\\Sim2\\" + subjectInfo.getTitle() + "," + sessionInfo.getTitle + "," + 0 + "," + " " + "," + vidoeCount);
          }


        }

        if(tempList.size() > 0)
          allData += subjectInfo.getTitle -> tempList
      }

    }

    ReadExcelJava.createExcelFile(allData, "Time" + "\t" + "Drive" + "\t" + "Stimulus" + "\t" + "Failure" + "\t" + "Perspiration")
    writer.close();
    videos.close();
    "C:\\temp\\new.xls"

    */

  }

  def inActitivity(point : Double, actionType: Int, activities : java.util.ArrayList[Activity]): Boolean = {
    import scala.collection.JavaConversions._
    for (act <- activities) {
      if (Math.floor(point) >= Math.floor(act.startTime) && Math.floor(point) <= Math.floor(act.endTime) && actionType == act.actionType) {
        return true
      }
    }
    return false
  }

  // This fucntion will be called once a new subject started and it will check the completion progress file to see what is the current session..
  def UpdateStudyForOneSubject(studyNo : Int, subject: String): (String, String) = {

    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      val loc = fileLocation[String]("STUDY_LOCATION");
      val userName = fileLocation[String]("study_owner");
      val topology = fileLocation[String]("STUDY_TOPOLOGY");
      val json = play.libs.Json.parse (topology)

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
      }

      val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      val subjectName =service.files().get(subject).execute().getTitle;

      var currentSesion = "";
      var dataType = "";
      // This  call asks one of ScanSubject Actor to scan a particulre subect and save its content to our database
      //TODO when a new study comes in we should send a priority message to scan that study.
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      var session_no = 1
      for ((sessionId, sessionInfo) <- sessions) {
        var file: com.google.api.services.drive.model.File = sessionInfo
        var sessionName: String = sessionInfo.getTitle
        sessionName = sessionName.replaceAll("\\s+", "")

        //TODO to also update the delted file to null
        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var signal_code: Int = 1
        breakable {
          for ((signalId, signalInfo) <- Signals) {
            println(signalInfo.getFileExtension)
            var order = 0;
            var isBl = false;
            var shouldICommit = true;
            if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar") ) {
              var extension: String = signalInfo.getFileExtension
              println("extension  "  + extension   + "   Name: " + signalInfo.getTitle)
              if(signalInfo.getTitle.contains(".bar.xlsx")  || signalInfo.getTitle.contains(".bar") ) // to hnalde muhsin issue with .bar extension
              {
                extension = "bar"
              }
              // TODO in case there is NO primary explantory varialbe make the secondary to appear first
              if((expListExtention.contains(extension.toLowerCase) || explExt.equalsIgnoreCase(extension) || extension.equalsIgnoreCase("sp") ||
                extension.equalsIgnoreCase("tp") || extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi")  || extension.equalsIgnoreCase("wav") || extension.equalsIgnoreCase("stm") /*|| extension.equalsIgnoreCase("bar")*/|| resExt.equalsIgnoreCase(extension) || resListExtentions.contains(extension.toLowerCase) )
                && !extension.equalsIgnoreCase("sim2") && !extension.equalsIgnoreCase("bar") ) {
                signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                  case Some(x) => x
                  case None => 0 // should never reach here
                }
              }
              else
                shouldICommit = false //todo: continue is not supported
              if (shouldICommit) {
                var insert = false
                if(extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("wav") || extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("mp3") ) {
                  insert = true;
                  checkIfThisAnewVideo(userName , studyNo, signalId, subjectName , sessionInfo.getTitle, signalInfo.getTitle, extension)
                }
                println("Now going to insert signal  " + extension  + "code  " +  signal_code)
                DataBaseOperations.UpdateSessionGD(subjectName, studyNo, 1, sessionName, signal_code, signalInfo.getId, 0, isBl, order, insert, signalInfo.getTitle)
              }
            }
          }
        }
        session_no += 1
      }
      val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        //var fileInfo: File = getFolderId(service, info)

        //if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar") ) {
        if ((fileInfo.getTitle.contains("~") || fileInfo.getFileExtension == null) && !fileInfo.getTitle.contains(".bar")) break //todo: continue is not supported
        var extension: String = fileInfo.getFileExtension

        if(fileInfo.getTitle.contains(".bar.xlsx")  || fileInfo.getTitle.contains(".bar") ) // to hnalde muhsin issue with .bar extension
        {
          extension = "bar"
        }
        if (userDefinedExtension.contains(extension.toLowerCase)) {
          var signal_code = userDefinedExtension.get(extension.toLowerCase) match {
            case Some(x) => x
          }
          DataBaseOperations.UpdateSessionGD(subjectName, studyNo, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false, 0, false, fileInfo.getTitle)
        }
      }

      val files = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType != 'application/vnd.google-apps.folder'")
      var allSessions: List[String] = List.empty
      var group = ""
      // update the study meta data (i.e., pins, completion progress)
      for ((fileID, fileInfo) <- files) {
        var extension: String = fileInfo.getFileExtension
        if (extension.equalsIgnoreCase("csv")) {
          //var writerAllSubjects: PrintWriter = new PrintWriter(ProductionSide.tempDownloading + "Completion_progress.csv", "UTF-8");
          var input = GoogleDrive.downloadFileByFileId(service, fileID)
          var scanner: Scanner = null
          var line: String = ""
          scanner = new Scanner(input)
          while (scanner.hasNextLine) {
            line = scanner.nextLine();
            var tokens = line.split("\\s+");

            if (tokens(0).equalsIgnoreCase("PendingSubject") ) {
              if (tokens.size >= 2) {
                if (tokens(1).equalsIgnoreCase(subjectName)) {
                  if (tokens.size >= 3)
                    currentSesion = tokens(2)
                  if (tokens.size >= 4)
                    dataType = tokens(3)
                }
                else // this mean the subject is over
                {
                  return ("DONESUBJECTSTOP", subjectName)
                }

              }
              else
                return ("DONESUBJECTSTOP", subjectName)
            }

          }
        }
      }
      return (currentSesion, dataType)


    }

  }
  def UpdateStudy(studyNo : Int) = {

    //MalcomREportDeleteAFter("")
    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      val loc = fileLocation[String]("STUDY_LOCATION");
      val userName = fileLocation[String]("study_owner");
      val topology = fileLocation[String]("STUDY_TOPOLOGY");
      val json = play.libs.Json.parse (topology)

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
      }

      val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")
      println(userDefinedExtension)
      for ((subject, subjectInfo) <- subjects) {
        println("Subject : " +  subjectInfo.getTitle)

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


          //TODO to also update the delted file to null
          val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
          var signal_code: Int = 1
          breakable {
            for ((signalId, signalInfo) <- Signals) {
              println(signalInfo.getFileExtension)
              var order = 0;
              var isBl = false;
              var shouldICommit = true;
              if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar") ) {
                var extension: String = signalInfo.getFileExtension
                println("extension  "  + extension   + "   Name: " + signalInfo.getTitle)
                if(signalInfo.getTitle.contains(".bar.xlsx")  || signalInfo.getTitle.contains(".bar") ) // to hnalde muhsin issue with .bar extension
                {
                  extension = "bar"
                }
                // TODO in case there is NO primary explantory varialbe make the secondary to appear first
                if((expListExtention.contains(extension.toLowerCase) || explExt.equalsIgnoreCase(extension) || extension.equalsIgnoreCase("sp") ||
                  extension.equalsIgnoreCase("tp") ||  extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("mp3")  || extension.equalsIgnoreCase("wav") || extension.equalsIgnoreCase("stm") /*|| extension.equalsIgnoreCase("bar")*/|| resExt.equalsIgnoreCase(extension) || resListExtentions.contains(extension.toLowerCase) )
                  && !extension.equalsIgnoreCase("sim2") && !extension.equalsIgnoreCase("bar") ) {
                  signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                    case Some(x) => x
                    case None => 0 // should never reach here
                  }
                }
                else
                  shouldICommit = false //todo: continue is not supported
                if (shouldICommit) {

                  var insert = false
                  if(extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("wav") || extension.equalsIgnoreCase("mp3") || extension.equalsIgnoreCase("mp4")) {
                    insert = true;
                    println("Checking fileeeee")
                    checkIfThisAnewVideo(userName , studyNo, signalId, subjectInfo.getTitle , sessionInfo.getTitle, signalInfo.getTitle, extension)
                  }
                  //if(extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("wav") ) insert = true;

                  println("Now going to insert signal  " + extension  + "code  " +  signal_code)
                  DataBaseOperations.UpdateSessionGD(subjectInfo.getTitle, studyNo, 1, sessionName, signal_code, signalInfo.getId, 0, isBl, order, insert, signalInfo.getTitle)
                }
              }
            }
          }
          session_no += 1

        }
        val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        import scala.collection.JavaConversions._
        for ((info, fileInfo) <- infos) {
          //var fileInfo: File = getFolderId(service, info)

          //if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) || signalInfo.getTitle.contains(".bar") ) {
          if ((fileInfo.getTitle.contains("~") || fileInfo.getFileExtension == null) && !fileInfo.getTitle.contains(".bar")) break //todo: continue is not supported
          var extension: String = fileInfo.getFileExtension

          if(fileInfo.getTitle.contains(".bar.xlsx")  || fileInfo.getTitle.contains(".bar") ) // to hnalde muhsin issue with .bar extension
          {
            extension = "bar"
          }
          if (userDefinedExtension.contains(extension.toLowerCase)) {
            var signal_code = userDefinedExtension.get(extension.toLowerCase) match {
              case Some(x) => x
            }
            DataBaseOperations.UpdateSessionGD(subjectInfo.getTitle, studyNo, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false, 0, false, fileInfo.getTitle)
          }
        }
      }


    }


  }


  // this function will be called to see if we need to conver the new video into the required format in real time
  def checkIfThisAnewVideo(userName: String , studyNo: Int, signalID: String, subjectName : String, sessionName : String, signalName : String, extension: String): Unit ={


    println("We are in check for videosssss")
    var link = "";
    var studyName= ""
    var studyLoc = ""

    var ct =0L;
    DB.withConnection { implicit c =>

      //TODO we should allow the same file to be there more than once
      val isThere =
        SQL("select count(signal_loc) as co from session where signal_loc={loc} and subject_seq in (select subject_seq from subject where study_id = {sN});").on('loc -> signalID, 'sN -> studyNo).apply().head
      ct = isThere[Long]("co");

      val rowOption3 =
        SQL("select coalesce(study_location_on_server, '') as c, study_name from study where study_id={study_id};").on('study_id -> studyNo).apply().head

      link = rowOption3[String]("c");
      studyName = rowOption3[String]("study_name");

      if(link == "") { // if the study locatio was not set create a new one
        studyLoc = ProductionSide.videosLocattion+ studyNo+ studyName
        val id: Int = SQL("update study set study_location_on_server = {code} where study_id = {id}").on('code -> studyLoc, 'id -> studyNo).executeUpdate()
      }
      else
        studyLoc = link
    }

    //if(ct == 0){
      val option1 = studyLoc + "\\" + subjectName + "\\" + sessionName.replaceAll("\\s+", "") + "\\" + signalName  // if the original file was alraedy mp4 or mp3
      val option2 = studyLoc + "\\" + subjectName + "\\" + sessionName.replaceAll("\\s+", "") + "\\" + signalName + "." + "mp4"   // if the file was converted from avi to mp4
      val option3 = studyLoc + "\\" + subjectName + "\\" + sessionName.replaceAll("\\s+", "") + "\\" + signalName + "." + "mp3"  // if the file was converted from wav to mp3
      if(!(new java.io.File(option1).exists()) && !(new java.io.File(option2).exists()) && !(new java.io.File(option3).exists()))
      {
        if( ! new java.io.File(studyLoc + "\\" + subjectName + "\\" + sessionName.replaceAll("\\s+", "")).exists())
          new java.io.File(studyLoc + "\\" + subjectName + "\\" + sessionName.replaceAll("\\s+", "")).mkdirs();
        GoogleDrive.downloadVideo(userName, signalID, option1)
        if(extension.equalsIgnoreCase("avi"))
          Runtime.getRuntime.exec("cmd /c start " + ProductionSide.videosLocattion + "\\realtimeconverting.bat " + "\"" +option1 + "\"" + " " + "\"" + option1 +"\"" )
      }
   // }


  }

  // this will iteratea over a study folder and download all the videos inside into the local file
  /* def downlaodVideosFromStudy(studyNo : Int, studyLoc: String) = {
     //MalcomREportDeleteAFter("")
     var userName = ""
     var studyName =""
     var loc = ""
     DB.withConnection { implicit c =>
       val fileLocation =
         SQL("select study_owner,study_name, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
        loc = fileLocation[String]("STUDY_LOCATION");
        userName = fileLocation[String]("study_owner");
        studyName = fileLocation[String]("study_name");


     }


       val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
       val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
       val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
       val service: Drive = GoogleDrive.buildService(googleCredential)

       val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")

       var system = ActorSystem("videos")
       val router: ActorRef = system.actorOf(RoundRobinPool(16).props(Props[ScanSubject]), "routerForVideos..")


       for ((subject, subjectInfo) <- subjects) {
         println("Subject : " +  subjectInfo.getTitle)
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


               if ((!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) ) {
                 var extension: String = signalInfo.getFileExtension

                 if(extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("mp4")){

                     val option1 = studyLoc + "\\" + subjectInfo.getTitle + "\\" + sessionInfo.getTitle + "\\" + signalInfo.getTitle + "." + extension
                     val option2 = studyLoc + "\\" + subjectInfo.getTitle + "\\" + sessionInfo.getTitle + "\\" + signalInfo.getTitle + "." + extension + "." + "mp4"
                     val option3 = studyLoc + "\\" + subjectInfo.getTitle + "\\" + sessionInfo.getTitle + "\\" + signalInfo.getTitle + "." + "mp4"

                    if(!(new java.io.File(option1).exists() || new java.io.File(option2).exists() || new java.io.File(option3).exists()))
                      {

                        if( ! new java.io.File(studyLoc + "\\" + subjectInfo.getTitle + "\\" + sessionInfo.getTitle).exists())
                            new java.io.File(studyLoc + "\\" + subjectInfo.getTitle + "\\" + sessionInfo.getTitle).mkdirs();
                            //GoogleDrive.downloadVideo(userName, signalId, option1)
                            router ! DownloadVideo(userName, signalId, option1, studyNo)
                      }

                 }


               }
             }
           }
           session_no += 1

         }


       }

     // call a script to conver all non mp4 video

     println("studyLocati:  " +  studyLoc)
     //Runtime.getRuntime.exec("cmd /c start " + Application.videosLocattion + "\\ConvertToMP4AndDelete.bat \"" + studyLoc + "\\\" 1" )

   }*/

  def OneTimeFunctionTOsaveSignalFileName(studyNo : Int) = {
    //MalcomREportDeleteAFter("")
    var userName = ""
    var studyName =""

    var numberOfretry = 5;
    var loc = ""
    var signalLocatinList : List[( String)] = List();
    DB.withConnection { implicit c =>
      val fileLocation =
        SQL("select study_owner,study_name, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      loc = fileLocation[String]("STUDY_LOCATION");
      userName = fileLocation[String]("study_owner");
      studyName = fileLocation[String]("study_name");


      val signalLoc= SQL("select signal_loc from session where subject_seq in (select subject_seq from subject where study_id= {id} ) and signal_loc != 'NA';").on('id -> studyNo)
      signalLocatinList =   signalLoc().map(row =>
        row[String]("signal_loc")).toList


      val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      for(loc <- signalLocatinList){
        var n: Int = 1
        val randomGenerator: Random = new Random

        var exit = false;
        while (n <= numberOfretry && !exit)
        {
          try {

            val file = service.files().get(loc).execute();
            println("Location :" +  file.getTitle);

            val id: Int = SQL("update session set file_name = {name} where signal_loc ={location}")
              .on( 'name -> file.getTitle, 'location -> loc).executeUpdate()
            exit = true
          }
          catch {
            case e: GoogleJsonResponseException =>
              Logger.error("returnFilesInFolder" + e.getMessage)
              if ((e.getDetails.getErrors.get(0).getReason == "rateLimitExceeded" || e.getDetails.getErrors.get(0).getReason == "userRateLimitExceeded")) {
                Logger.error("in returnFilesInFoldertest2 rate limit exceeded(salah) " + e)
                try {
                  Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001) * n)
                }
                catch {
                  case e2: InterruptedException =>
                    Logger.error("got interrupted!" + e2)
                }
              }
            case e: IOException =>
              System.out.println("An error occurred: " + e)

          }

        }




      }
    }

  }


  def cleanOneSubject(studyNo : Int, subjectID: String) = {


    DB.withConnection { implicit c =>
      val fileLocation = SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      val loc = fileLocation[String]("STUDY_LOCATION");
      val userName = fileLocation[String]("study_owner");
      val topology = fileLocation[String]("STUDY_TOPOLOGY");

      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)



      var file =  insertFile(service, "Deleted" + subjectID, "application/vnd.google-apps.folder", null, null)

      val files = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType != 'application/vnd.google-apps.folder'")
      var  writerAllSubjects : PrintWriter = new PrintWriter("C:\\temp\\Completion_progress.csv", "UTF-8");


      for((fileID, fileInfo) <- files){
        var extension: String = fileInfo.getFileExtension
        if(extension.equalsIgnoreCase("csv")){
          var input = GoogleDrive.downloadFileByFileId(service, fileID)
          var scanner: Scanner = null
          var line: String = ""
          scanner = new Scanner(input)
          while (scanner.hasNextLine) {
            line = scanner.nextLine();

            var tokens = line.split("\\s+");

            if(tokens(0).equalsIgnoreCase(subjectID))
              writerAllSubjects.println(subjectID +"\t"+ tokens(1))
            else
              writerAllSubjects.println(line);
          }

        }

        writerAllSubjects.close();
        var fileContent = new java.io.File("C:\\temp\\Completion_progress.csv")
        var mediaContent = new FileContent("text/csv", fileContent);
        val fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setTitle(fileInfo.getTitle)
        fileMetadata.setMimeType("text/csv");

        service.files().update(fileID, fileMetadata, mediaContent).execute();



      }

      val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")

      for ((subject, subjectInfo) <- subjects) {

        if (subjectInfo.getTitle.equals(subjectID)) {
          val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

          var session_no: Int = 1
          //val file0: com.google.api.services.drive.model.File = GoogleDrive.waitUntilGetDGFile(service, subject)

          import scala.collection.JavaConversions._
          for ((sessionId, sessionInfo) <- sessions) {


            // find the parent for each session
            var par = service.files().get(sessionId)
              .setFields("parents")
              .execute();


            var  previousParents : StringBuilder = new StringBuilder();
            for( parent <- par.getParents()) {
              previousParents.append(parent.getId);
              previousParents.append(',');
            }

            // move the session folder to the deleted on and remove the old parents
            par = service.files().update(sessionId, null)
              .setAddParents(file.getId)
              .setRemoveParents(previousParents.toString())
              .setFields("id, parents")
              .execute();


            session_no += 1

          }

          val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
          import scala.collection.JavaConversions._
          for ((info, fileInfo) <- infos) {
            var extension: String = fileInfo.getFileExtension



            if(extension!= ""){

              var par = service.files().get(info)
                .setFields("parents")
                .execute();


              var  previousParents : StringBuilder = new StringBuilder();
              for( parent <- par.getParents()) {
                previousParents.append(parent.getId);
                previousParents.append(',');
              }

              // move the session folder to the deleted on and remove the old parents
              par = service.files().update(info, null)
                .setAddParents(file.getId)
                .setRemoveParents(previousParents.toString())
                .setFields("id, parents")
                .execute();


            }

          }
        }
      }


    }


  }
  def replicateOneSubject(studyNo : Int, subjectID: String) = {


    DB.withConnection { implicit c =>
      val fileLocation = SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      val loc = fileLocation[String]("STUDY_LOCATION");
      val userName = fileLocation[String]("study_owner");
      val topology = fileLocation[String]("STUDY_TOPOLOGY");
      val subjectCount = SQL("select count(*) as c from subject where study_id = {id};").on('id -> studyNo).apply().head


      val json = play.libs.Json.parse (topology)
      var desc = json.get("descriptor").toString
      val descMap  = DataBaseOperations.fromJsonToMapDescriptorMultipleExperiments(desc)
      val newOb= json.get("secondadryExp");
      var expList: List[Int] = List.empty[Int]
      var expListExtention: List[String] = List.empty[String]
      val newOc = json.get("secondadryRes");
      var resList: List[Int] = List.empty[Int]
      var resListExtentions: List[String] = List.empty[String]

      var explExt = ""
      var resExt =""

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




      var j: Long = subjectCount[Long]("c")
      var x: Integer = new Integer(j.toInt + 1)
      var num = java.lang.String.format("%1$02d", x)

      var subjectName = "Subject" + num


      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)


      // create subject folder
      var file = insertFile(service, subjectName, "application/vnd.google-apps.folder", loc, null)

      var oldSubjectID = "";

      // retrieve all files inside the study folder
      val files = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType != 'application/vnd.google-apps.folder'")


      var allSessions: List[String] = List.empty
      var group = ""
      // update the study meta data (i.e., pins, completion progress)
      for ((fileID, fileInfo) <- files) {
        var extension: String = fileInfo.getFileExtension
        if (extension.equalsIgnoreCase("csv")) {
          var writerAllSubjects: PrintWriter = new PrintWriter("C:\\temp\\Completion_progress.csv", "UTF-8");
          var input = GoogleDrive.downloadFileByFileId(service, fileID)
          var scanner: Scanner = null
          var line: String = ""
          scanner = new Scanner(input)
          while (scanner.hasNextLine) {
            line = scanner.nextLine();
            var tokens = line.split("\\s+");

            if (!tokens(0).equalsIgnoreCase("PendingSubject")) {
              if (tokens(0).equalsIgnoreCase(subjectID))
                oldSubjectID = tokens(1)

              writerAllSubjects.println(line);
            }
          }
          writerAllSubjects.println(subjectName + "\t" + file.getId);
          writerAllSubjects.println("PendingSubject");

          writerAllSubjects.close();
          var fileContent = new java.io.File("C:\\temp\\Completion_progress.csv")
          var mediaContent = new FileContent("text/csv", fileContent);
          val fileMetadata = new com.google.api.services.drive.model.File();
          fileMetadata.setTitle(fileInfo.getTitle)
          fileMetadata.setMimeType("text/csv");

          service.files().update(fileID, fileMetadata, mediaContent).execute();
        }
        else if (fileInfo.getTitle.contains("pins")) {
          var writerAllSubjects: PrintWriter = new PrintWriter("C:\\temp\\pins.txt", "UTF-8");
          var input = GoogleDrive.downloadFileByFileId(service, fileID)
          var scanner: Scanner = null
          var line: String = ""
          scanner = new Scanner(input)
          while (scanner.hasNextLine) {
            line = scanner.nextLine();
            var tokens = line.split(",");

            if (tokens.size > 3) {
              println(tokens(3));
              if (tokens(3).equalsIgnoreCase(subjectID)) {
                writerAllSubjects.println(tokens(0) + "," + tokens(1) + "," + tokens(2) + "," + subjectName);
                group = tokens(0);
              }

            }

            writerAllSubjects.println(line);
          }
          writerAllSubjects.close();


          var fileContent = new java.io.File("C:\\temp\\pins.txt")
          var mediaContent = new FileContent("text/plain", fileContent);
          val fileMetadata = new com.google.api.services.drive.model.File();
          fileMetadata.setTitle(fileInfo.getTitle)
          fileMetadata.setMimeType("text/plain");
          service.files().update(fileID, fileMetadata, mediaContent).execute();
        }

      }

      // create session folders
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, oldSubjectID, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      var session_no: Int = 1
      import scala.collection.JavaConversions._
      for ((sessionId, sessionInfo) <- sessions) {
        // insert session
        var file3 = insertFile(service, sessionInfo.getTitle, "application/vnd.google-apps.folder", file.getId, null)
        session_no += 1
        allSessions = sessionInfo.getTitle :: allSessions
      }

      // create subject info
      val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, oldSubjectID, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        var extension: String = fileInfo.getFileExtension

        println("Extension   " + extension);
        var writerSubjectInfo: PrintWriter = new PrintWriter("C:\\temp\\info.txt", "UTF-8");
        if (extension == "") {
          println("What is going on guys are you serious?")
          var input = GoogleDrive.downloadFileByFileId(service, info)
          var scanner: Scanner = null
          var line: String = ""
          scanner = new Scanner(input)

          while (scanner.hasNextLine) {
            line = scanner.nextLine();
            writerSubjectInfo.println(line);
          }
          writerSubjectInfo.close();

          var fileContent: java.io.File = new java.io.File("C:\\temp\\info.txt")
          var mediaContent: FileContent = new FileContent("text/plain", fileContent);

          var filetemp = insertFile(service, subjectName, "text/plain", file.getId, mediaContent)
        }
      }

      // add to the database

      if(group.equalsIgnoreCase("0")){
        DataBaseOperations.InsertSubjectGD(subjectName, studyNo, file.getId, 11, 11)

      }
      else{
        DataBaseOperations.InsertSubjectGD(subjectName, studyNo, file.getId, 11, 11, group)
      }

      var isBal = false;
      for(sessionName <- allSessions) {

        descMap match {
          case Some(data) =>
            for ((key, value) <- data) {
              var isBal = false

              if(value._6 ==1 && sessionName.contains(key))
                isBal = true
            }
        }


        // primary explantory var
        DataBaseOperations.InsertSessionGD(subjectName, studyNo, session_no, sessionName, json.get("primaryExp").asInt(), "NA", 0, isBal, 2)
        DataBaseOperations.InsertSessionGD(subjectName, studyNo, session_no, sessionName, json.get("primaryRes").asInt(), "NA", 0, isBal, 1)

        expList.foreach(x =>

          if (x != json.get("primaryExp").asInt()) {
            DataBaseOperations.InsertSessionGD(subjectName, studyNo, session_no, sessionName, x, "NA", 0, isBal, 0)
          }

        )
      }
    }
  }



  def RebuildRealTimeStudy (studyNo : Int) = {
    DB.withConnection { implicit c =>
      val fileLocation = SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
      val loc = fileLocation[String]("STUDY_LOCATION");
      val userName = fileLocation[String]("study_owner");
      val topology = fileLocation[String]("STUDY_TOPOLOGY");

      val json = play.libs.Json.parse(topology)
      val desc = json.get("descriptor").toString
      val descMap = DataBaseOperations.fromJsonToMapDescriptorMultipleExperiments(desc)
      val newOb = json.get("secondadryExp");
      var expList: List[Int] = List.empty[Int]
      var expListExtention: List[String] = List.empty[String]
      val newOc = json.get("secondadryRes");
      var resList: List[Int] = List.empty[Int]
      var resListExtentions: List[String] = List.empty[String]

      var explExt = ""
      var resExt = ""

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
      resExt = rowOption2[String]("SIGNAL_EXTENSION")



      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)
      val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")


      for ((subject, subjectInfo) <- subjects) {


        val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var tempList: java.util.List[EntryForExcel] = new util.ArrayList[EntryForExcel]()
        var session_no = 1
        for ((sessionId, sessionInfo) <- sessions) {


          descMap match {
            case Some(data) =>
              for ((key, value) <- data) {
                if (sessionInfo.getTitle.contains(key.replaceAll("\\s+", "")))
                {
                  var isBal = false
                  if (value._6 == 1) {
                    // check if this session is intra
                    println("Is baseline: " + value._6 + "  Name : " + key)
                    isBal = true
                  }

                  DataBaseOperations.InsertSessionGD(subjectInfo.getTitle, studyNo, session_no, sessionInfo.getTitle, json.get("primaryExp").asInt(), "NA", 0, isBal, 2)
                  if (value._6 != 0 ||  key.replaceAll("\\s+", "").equals("PracticeDrive")) // if this session is baseline there is no response variable
                    DataBaseOperations.InsertSessionGD(subjectInfo.getTitle, studyNo, session_no, sessionInfo.getTitle, json.get("primaryRes").asInt(), "NA", 0, isBal, 1)

                  expList.foreach(x =>

                    if (x != json.get("primaryExp").asInt()) {
                      DataBaseOperations.InsertSessionGD(subjectInfo.getTitle, studyNo, session_no, sessionInfo.getTitle, x, "NA", 0, isBal, 0)
                    }

                  )
                  session_no += 1
                }
              }
          }
        }


      }
    }





  }


  // we can use this to generate reports about the missing data >>>
  def MalcomREportDeleteAFter (study : String) ={

    val userName = "cplsubjectbook@gmail.com"
    val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
    val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
    val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
    val service: Drive = GoogleDrive.buildService(googleCredential)

    val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, "0BzuoB3uc0tTVeGRreXVZNXdsQlk", "mimeType = 'application/vnd.google-apps.folder'")


    // GoogleDrive.retrieveAllChanges(service, null)

    var  writer : PrintWriter = new PrintWriter("C:\\temp\\malcom.txt", "UTF-8");


    //writer.println(descMap.size);

    writer.println("Subject" + "\t" + "Sesson" + "\t" + "pp" + "\t" + "hr" + "\t" + "br" + "\t" + "hrv" + "\t" + "facs" + "\t"  + "peda" + "\t" + "res2" + "\t"  + "stm" + "\t" + "avi1" + "\t" + "avi2" + "\t" + "avi3" )

    for ((subject, subjectInfo) <- subjects) {

      //println()
      //TODO when a new study comes in we should send a priority message to scan that study.
      val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

      var ordered:  TreeMap[String, String]= TreeMap.empty

      for ((sessionId, sessionInfo) <- sessions) {
        ordered += sessionInfo.getTitle.replaceFirst("(\\d*\\s*)", "") -> sessionId
      }



      var session_no: Int = 1
      //val file0: com.google.api.services.drive.model.File = GoogleDrive.waitUntilGetDGFile(service, subject)

      import scala.collection.JavaConversions._
      for ((name, id) <- ordered) {

        writer.print(subjectInfo.getTitle + "\t" + name + "\t")
        var pp ,hr, br, hrv, facs, avi, peda, res2, stm , avi1, avi2, avi3 =0;



        println("sesion  "  + name  )
        // var file: com.google.api.services.drive.model.File = sessionInfo
        var sessionName: String = name
        // sessionName = sessionName.replaceAll("\\s+", "")


        val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, id, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
        var signal_code: Int = 1

        breakable {
          for ((signalId, signalInfo) <- Signals) {
            var order = 0;
            var isBl = false;
            var shouldICommit = true;
            if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null ) {

              val extension: String = signalInfo.getFileExtension


              if(extension.equalsIgnoreCase("pp"))
                pp = pp+1
              else if (extension.equalsIgnoreCase("hr"))
                hr = hr +1
              else if (extension.equalsIgnoreCase("br"))
                br = br +1
              else if (extension.equalsIgnoreCase("hrv"))
                hrv = hrv +1
              else if (extension.equalsIgnoreCase("facs"))
                facs = facs +1
              else if (extension.equalsIgnoreCase("peda"))
                peda = peda +1
              else if (extension.equalsIgnoreCase("res2"))
                res2 = res2 +1
              else if (extension.equalsIgnoreCase("stm"))
                stm = stm +1


              if(signalInfo.getTitle.contains("avi1"))
                avi1 =avi1+ 1
              else if(signalInfo.getTitle.contains("avi2"))
                avi2 =avi2+ 1
              else if(signalInfo.getTitle.contains("avi3"))
                avi3 =avi3+ 1

              // TODO in case there is NO primary explantory varialbe make the secondary to appear first

              /*  if((expListExtention.contains(extension.toLowerCase) || explExt.equalsIgnoreCase(extension) || extension.equalsIgnoreCase("sp") ||
                  extension.equalsIgnoreCase("tp") || extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("bar")|| resExt.equalsIgnoreCase(extension) || resListExtentions.contains(extension.toLowerCase) )
                  && !extension.equalsIgnoreCase("sim2")) {
                  signal_code = userDefinedExtension.get(extension.toLowerCase) match {
                    case Some(x) => x
                    case None => 0 // should never reach here
                  }
                }
                else
                  shouldICommit = false //todo: continue is not supported

                  */


            }
          }

          writer.println(pp + "\t" + hr + "\t" + br + "\t" + hrv + "\t" + facs + "\t"  + peda + "\t" + res2 + "\t"  + stm + "\t" + avi1 + "\t" + avi2 + "\t" + avi3 )
        }
        session_no += 1

      }

      /*val infos = GoogleDrive.returnFilesInFolderJustForTest2(service, subject, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
      import scala.collection.JavaConversions._
      for ((info, fileInfo) <- infos) {
        //var fileInfo: File = getFolderId(service, info)

        if (fileInfo.getTitle.contains("~")) break //todo: continue is not supported
        val extension: String = fileInfo.getFileExtension
        if (userDefinedExtension.contains(extension.toLowerCase)) {
          var signal_code = userDefinedExtension.get(extension.toLowerCase) match {
            case Some(x) => x
          }
          DataBaseOperations.UpdateSessionGD(subjectInfo.getTitle, studyNo, session_no, "GENERAL", signal_code, fileInfo.getId, 1, false, 0)
        }
      }*/

    }


    writer.close();


  }

  def insertFile (service: Drive, name: String, mimeTyp: String, parentId: String, mediaContent : FileContent ): com.google.api.services.drive.model.File = {

    val fileMetadata = new com.google.api.services.drive.model.File();
    fileMetadata.setTitle(name)
    fileMetadata.setMimeType(mimeTyp);
    if( parentId != null){
      val parent: ParentReference = new ParentReference()
      parent.setId(parentId)
      fileMetadata.setParents(Collections.singletonList(parent));
    }
    val file =
      if(mediaContent != null)
        service.files().insert(fileMetadata, mediaContent).setFields("id").execute();
      else
        service.files().insert(fileMetadata).setFields("id").execute();

    file

    // should add exception handling
  }

  def BiographyCode(code: Int) : Biography =
  {
    var age: Int=0; var height: Int=0; var weight: Int=0;var gender: Int=0; var ethnicity: Int=0; var bio_other: Int=0;
    var value: Int = code;
    var count: Int =1 ;
    while(value > 0 )
    {
      var option= value %10;
      if(option == 1)
      {
        count match {
          case 1 => age =1;
          // println("Age has been selected");
          case 2 => height =1;
          //println("Height has been selected");
          case 3 =>weight =1
          //println("weight has been selected");
          case 4 =>gender =1
          //println("gender has been selected");
          case 5 => ethnicity=1
          //println("ethnicity has been selected");
          case 6 =>bio_other=1
          //println("bio_other has been selected");
        }
      }
      count+=1;
      value = value/10;
    }
    var bio: Biography = new Biography(age, height, weight, gender, ethnicity, bio_other);;
    bio;
  }

  // this function will accept code and bio and it will check if the bio item has been selected or not


  def PsychometricCode(code: Int) : Psychometric =
  {
    //var SAI: Int=0; var PA: Int=0; var NA: Int=0; var STAI_other: Int=0; var TA: Int=0; var AB: Int=0; var Trait_Other: Int=0;
    var SAI: Int=0; var TAI: Int=0; var PA: Int=0; var NA: Int=0; var Other: Int=0;
    var value: Int = code;
    var count: Int =1 ;
    while(value > 0 )
    {
      var option= value %10;
      if(option == 1)
      {
        count match {
          case 1 => SAI = 1;
          // println("SAI has been selected");
          case 2 => TAI = 1;
          //println("TAI has been selected");
          case 3 => PA = 1
          //println("PA has been selected");
          case 4 => NA = 1
          //println("NA has been selected");
          case 5 => Other = 1
          //println("Other has been selected");
        }
      }
      count+=1;
      value = value/10;
    }
    var psycho = new Psychometric(SAI,TAI, PA, NA, Other);
    psycho;
  }

  def PhysiologyCode(code: Int) : Physiology =
  {
    var EDA: Int=0; var Motion: Int=0; var GSR_Finger: Int=0; var Breathing_Belt: Int=0; var Breathing_Thermal: Int=0; var Heart_Rate: Int=0; var Perspiration: Int=0;
    var value: Int = code;
    var count: Int =1 ;
    while(value > 0 )
    {
      var option= value %10;
      if(option == 1)
      {
        count match {
          case 1 => EDA =1;
            println("EDA_Ankle has been selected");
          case 2 => Motion =1;
            println("EDA_Palm has been selected");
          case 3 =>GSR_Finger =1
            println("GSR_Finger has been selected");
          case 4 =>Breathing_Belt =1
            println("Breathing_Belt has been selected");
          case 5 => Breathing_Thermal=1
            println("Breathing_Thermal has been selected");
          case 6 =>Heart_Rate=1
            println("Heart_Rate has been selected");
          case 7 =>Perspiration=1
            println("Perspiration  has been selected");
        }
      }
      count+=1;
      value = value/10;
    }
    var physio = new Physiology(EDA, Motion, GSR_Finger, Breathing_Belt, Breathing_Thermal, Heart_Rate, Perspiration);
    physio;
  }

  def ObservationCode(code: Int) : Observation =
  {
    var Accelerometer: Int=0; var Obser_other: Int=0; var Video_Face: Int=0; var Video_Room: Int=0; var Video_Theater: Int=0; var FACS: Int=0; var Obser2d_other: Int=0
    var value: Int = code;
    var count: Int =1 ;
    while(value > 0 )
    {
      var option= value %10;
      if(option == 1)
      {
        count match {
          case 1 => Accelerometer =1;
            println("Accelerometer has been selected");
          case 2 => Obser_other =1;
            println("Obser_other has been selected");
          case 3 =>Video_Face =1
            println("Video_Face has been selected");
          case 4 =>Video_Room =1
            println("Video_Room has been selected");
          case 5 => Video_Theater=1
            println("Video_Theater has been selected");
          case 6 =>FACS=1
            println("FACS has been selected");
          case 7 =>Obser2d_other=1
            println("Obser2d_other  has been selected");
        }
      }
      count+=1;
      value = value/10;
    }
    var obser = new Observation(Accelerometer, Obser_other, Video_Face, Video_Room, Video_Theater, FACS, Obser2d_other);
    obser;
  }

}
