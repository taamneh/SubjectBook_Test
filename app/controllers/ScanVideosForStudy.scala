package controllers

import java.io.PrintWriter
import java.util.UUID

import akka.actor.{Actor, ActorRef, ActorSystem, Kill, PoisonPill, Props}
import akka.routing.RoundRobinPool
import anorm._
import com.google.api.services.drive.model.{File, Permission}
import play.api.db.DB
import play.api.Play.current
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import play.api.libs.json.{JsArray, JsObject, JsString}

import util.control.Breaks._
import java.util

import Models.{EntryForExcel, _}
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.{File, Permission}
import controllers.CreatePortraitMessages.Start
import controllers.CreatingStudyMessages.OneSubjectDoneAbstract
import play.Logger

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._




/**
  * Created by staamneh on 9/29/2016.
  */


case class ScanVidoeInStudy(studyNo : Int, studyLoc: String)
case class ScanStudyToGenerateDataSet(studyNo: Int);
case class OneSubjectDoneDataSet(st: Int, subject: String,  data : java.util.List[EntryForExcel] )
case class SubjectFolderAbstractExport(subjectTitle : String, subject: String, service: Drive, study_no: Int)
class ScanVideosForStudy extends Actor{


  var studyLoc = ""
  var finshedSubjectByStudy :Map[Int,(Int, Int)] = Map.empty
  var finshedSubjectByStudyDataSet :Map[Int,(Int, Int)] = Map.empty
  var byStudyallData: java.util.TreeMap[Int, java.util.TreeMap[String, java.util.List[EntryForExcel]]] = new  java.util.TreeMap[Int, java.util.TreeMap[String, java.util.List[EntryForExcel]]]

  def receive = {

    case ScanStudyToGenerateDataSet(studyNo: Int) =>
      var userName = "";
      var loc = "";
      var lst : List[String]= List()

      DB.withConnection { implicit c =>
        val fileLocation =
          SQL("select study_owner, STUDY_LOCATION, STUDY_TOPOLOGY   from study where study_id ={id} ;").on('id -> studyNo).apply().head
        loc = fileLocation[String]("STUDY_LOCATION");
        userName = fileLocation[String]("study_owner");

        val vaildSubjects = SQL("select subject_id from subject where replicated =8 AND study_id ={id};").on('id -> studyNo)
        lst = vaildSubjects().map(row =>  (row[String]("subject_id"))).toList
      }

      var allData: java.util.TreeMap[String, java.util.List[EntryForExcel]] = new java.util.TreeMap[String, java.util.List[EntryForExcel]]()
      val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
      val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
      val service: Drive = GoogleDrive.buildService(googleCredential)

      val subjects = GoogleDrive.returnFilesInFolderJustForTest2(service, loc, "mimeType = 'application/vnd.google-apps.folder'")
      var ctr= 0;

      finshedSubjectByStudyDataSet += studyNo ->(subjects.size, 0)
      for ((subject, subjectInfo) <- subjects ) {

       // if(subjectInfo.getTitle.equals("T001")) {
        println("Subject  : " + subjectInfo.getTitle)
        if (!lst.contains(subjectInfo.getTitle) ) {
          Global.routerForVideos ! SubjectFolderAbstractExport(subjectInfo.getTitle, subject, service, studyNo)

          //  break();
        }
       // }
      }


    case ScanVidoeInStudy(studyNo : Int, studyLoc: String) =>
      if(! finshedSubjectByStudy.contains(studyNo)) { // to prvent doing the same job twice at the same time
        //MalcomREportDeleteAFter("")
        this.studyLoc = studyLoc
        var userName = ""
        var studyName = ""
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

        finshedSubjectByStudy += studyNo ->(subjects.size, 0)


        for ((subject, subjectInfo) <- subjects) {

          Global.routerForVideos ! DownloadVideo(userName, subject, studyLoc, studyNo, subjectInfo.getTitle)
          println("Subject : " + subjectInfo.getTitle)


        }
        // call a script to conver all non mp4 video
        println("studyLocati:  " + studyLoc)
      }

    case OneSubjectDoneDataSet(st, subject, data) =>
      println("Done collecting data for one subject")
      finshedSubjectByStudyDataSet += st ->(finshedSubjectByStudyDataSet(st)._1, finshedSubjectByStudyDataSet(st)._2 + 1)
      println(finshedSubjectByStudyDataSet);

      if(byStudyallData.contains(st)){
        var x = byStudyallData(st)
        x += subject -> data
        byStudyallData(st) +=  subject -> data
      }
      else{
       //byStudyallData += st -> (subject -> data)
        var x :  java.util.TreeMap[String, java.util.List[EntryForExcel]] = new java.util.TreeMap[String, java.util.List[EntryForExcel]]()
        x.put(subject, data)
        byStudyallData.put(st, x)
      }
      println("What the fuck is going on")

      var  writer : PrintWriter = new PrintWriter("C:\\Users\\staamneh\\Dropbox\\temp\\"+ subject + ".csv", "UTF-8");
      //writer.println("Time" + ","  +"Drive" + "," +  "Secondary" + "," + "Failure event" + "," + "Palm EDA"  + "," + "Heart Rate"  + "," + "Breathing Rate"  + "," + "HRV" );
      writer.println("Time" + ","  +"Drive" + "," +  "Secondary" + "," + "Failure event" + "," + "Perinasal Perspiration" + "," + "Speed"  + "," + "Acceleration"  + "," + "Braking"  + "," + "Steering"  + "," + "LaneOffset" + "," + "Lane Position" );


      for (entry: EntryForExcel <- data)
       {
         var brake = ""
         if(entry.getBrake == -111111.1)
           brake = ""
         else if (entry.getBrake == 0){
           brake="0"
         }
         else{
           brake =  Math.exp(entry.getBrake).toString
           if(Math.exp(entry.getBrake) < 0) brake="0"
         }
         var speed = ""
         if(entry.getSpeed == -111111.1)
            speed = ""
         else{
           speed = entry.getSpeed.toString
         }

         var acc = ""
         if(entry.getAcc == -111111.1)
           acc = ""
         else{
           acc = entry.getAcc.toString
         }

         var steering = ""
         if(entry.getSteering == -111111.1)
           steering = ""
         else{
           steering = entry.getSteering.toString
         }

         var lane = ""
         if(entry.getLaneOffset == -111111.1)
           lane = ""
         else{
           lane = entry.getLaneOffset.toString
         }

         var lanePostion =""
         if(entry.getLanePosition == -111111.1)
           lanePostion = ""
         else{
           lanePostion = entry.getLanePosition.toString
         }



       // writer.println(entry.getTime + "," +entry.getName + "," + entry.getStimulus+ "," + entry.getFailure + "," + entry.getPeda + "," + entry.getHr + "," +  entry.getbr() + "," + entry.getHrv);
         writer.println(entry.getTime + "," +entry.getName + "," + entry.getStimulus+ "," + entry.getFailure + "," + entry.getPersperation+ "," + speed + "," + acc + "," +  brake + "," + steering + "," + lane  + "," +lanePostion);
       }
      writer.close()

      if (finshedSubjectByStudyDataSet(st)._1 == finshedSubjectByStudyDataSet(st)._2) {

       // ReadExcelJava.createExcelFile(byStudyallData(st), "Time" + "\t" + "Cleaned" + "\t" +"Drive" + "\t" +  "Secondary" + "\t" + "Failure event" + "\t" + "Speed"  + "\t" + "Acceleration"  + "\t" + "Braking"  + "\t" + "Steering"  + "\t" + "LaneOffset" /*+ "\t" + "PalmEDA" + "\t" + "Heart Rate" + "\t" + "Breating Rate" + "\t" + "HRV" */ )
        //context.parent ! "C:\\temp\\new.xls"
          finshedSubjectByStudyDataSet -= st;
        if(finshedSubjectByStudyDataSet.size ==0){
          self ! PoisonPill
        }
        //TODO make sure to kill the portrait child if if did not kill himeslef
      }

    case OneSubjectDoneAbstract(st) =>
      finshedSubjectByStudy += st ->(finshedSubjectByStudy(st)._1, finshedSubjectByStudy(st)._2 + 1)
      println(finshedSubjectByStudy);

      if (finshedSubjectByStudy(st)._1 == finshedSubjectByStudy(st)._2) {
            finshedSubjectByStudy -= st;
        Runtime.getRuntime.exec("cmd /c start " + ProductionSide.videosLocattion + "\\ConvertToMP4AndDelete.bat \"" + studyLoc + "\\\" 1" )
        if(finshedSubjectByStudy.size ==0){
          self ! PoisonPill
        }
        //TODO make sure to kill the portrait child if if did not kill himeslef
      }



  }

}
