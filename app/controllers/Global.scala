package controllers

import java.util
import java.util.UUID

import Models.StudyTopology
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool
import controllers.CreatingStudyMessages.{ScanStudy, ScanStudyAbstract}
import controllers.FindNewMultiModal
import play.api.{GlobalSettings, Logger}

/**
 * Created by staamneh on 6/17/2015.
 */
object Global extends GlobalSettings {

  var Salah = 99;
  val system = ActorSystem("salah")
  //var systemForVideos = ActorSystem("videos")
  val leader = system.actorOf(Props[CreatingStudy])
  val routerForPortrait: ActorRef = system.actorOf(RoundRobinPool(3).props(Props[ScanSubjectPortrait]), "routerForPortrait")
  val routerForPortraitAbstraction: ActorRef = system.actorOf(RoundRobinPool(3).props(Props[ScanSubjectPortraitAbstraction]), "routerForPortrait2")

  val routerForVideos: ActorRef = system.actorOf(RoundRobinPool(8).props(Props[ScanSubject]), "routerForVideos" + UUID.randomUUID)


   def startScanningVideos(studyNo : Int, studyLoc: String): Unit ={
     var videoHead = system.actorOf(Props[ScanVideosForStudy])
     println("what is goning onnnnnnn")
     videoHead ! ScanVidoeInStudy(studyNo, studyLoc)
   }


  def startScanningDataSet(studyNo : Int): Unit ={
    var videoHead = system.actorOf(Props[ScanVideosForStudy])
    println("what is goning onnnnnnn")
    videoHead ! ScanStudyToGenerateDataSet(studyNo)
  }


  def startCreatingStudy(folder_id: String, studyName: String, username: String, studyTopology: StudyTopology, bio_code: Int, psycho_code: Int, physio_code: Int, perf_code: Int, createPortrait: Int, shareStudy: Int) {
    leader ! ScanStudy (folder_id, studyName, username, studyTopology, bio_code, psycho_code, physio_code, perf_code, createPortrait, shareStudy)

  }


  def CreateSTudyAbstractWay(topology : Abstraction) {
    println("THIS IS ABSTRACTION")
    leader ! ScanStudyAbstract(topology)
  }


}
