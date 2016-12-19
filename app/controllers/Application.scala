package controllers

//import Models.{DB, Person, ReadExcelScala}

import java.io._
import controllers.ProductionSide
import java.net.{JarURLConnection, URL}
import java.nio.file.Path
import java.util
import java.util.{Collections, Date, Scanner, UUID}

import akka.actor.{ActorSystem, Props}
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive
import controllers.SharedData.MinMax

import collection.JavaConversions._
import scala.collection.JavaConverters._
import Models._
import akka.actor.FSM.Failure
import org.apache.poi.util.IOUtils
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
import play.api._
import anorm._
import play.api.libs.json.{JsArray, JsValue, Json}
import play.api.db.DB
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Concurrent, Enumerator}
import play.api.mvc._
import play.Logger

import scala.collection.immutable.TreeMap
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import akka.actor._
import akka.dispatch.ExecutionContexts
import akka.routing.RoundRobinPool
import com.google.api.client.http.FileContent
import com.google.api.services.drive.model.{Change, ParentList, ParentReference}
import org.apache.poi.hssf.usermodel.{HSSFSheet, HSSFWorkbook}
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormatter
import play.libs.Akka
//import org.mortbay.jetty.MimeTypes
import org.springframework.format.annotation.DateTimeFormat
import play.api.libs.Codecs

import scala.util.control.Breaks._
import scala.util.control.NonFatal


object Application extends Controller {

  val GOOGLE_DRIVE = 1;
  val LOCAL_SERVER = 2;


  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(Models.UserLogin.apply)(Models.UserLogin.unapply)
  )

  object MyWebSocketActorNewStudy {
    def props(out: ActorRef) = Props(new MyWebSocketActorNewStudy(out))
  }



  def socket = WebSocket.acceptWithActor[String, String] { request => out =>
  MyWebSocketActorNewStudy.props(out)
}

  def Front = Action {
    Ok(views.html.HelpPage())
  }

  def setUpStudy = Action {
    Ok(views.html.SetupStudy())
  }

  def howToCreateNewStudy = Action {
    Ok(views.html.HowToCreateStudy())
  }


  def RealTime = Action {
    implicit request =>
      var username: String = "";
      var temp = List.empty[(String, Int, Int)]
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      Ok(views.html.RealTime(username));
  }


  def showSignalRealTime(fileId: String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      GoogleDrive.UpdatingRealTime(username, fileId)
      Ok(views.html.RealTimeChart(username, fileId));
  }


  def showSignalRealTime2(fileId: String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      Ok(GoogleDrive.UpdatingRealTime(username, fileId).toJSONString);
  }

  case class NewDataType(dataDec: Option[String], dataExtension :String, dataType : Int, yTitle: Option[String], frameRate:Option[Int] , first_row: Option[Int], first_col: Option[Int])
  case class ExistingDataType(code: Int, dataDec: Option[String], dataExtension :String, dataType : Int, yTitle: Option[String], frameRate:Option[Int] , first_row: Option[Int], first_col: Option[Int], min_yvalue:  Option[Float], max_yvalue:  Option[Float], log : Int)
  case class NewPsychometric(pName: String, min : Int, max: Int)
  case class NewTopSummary(code: String, studyId: Int)
  case class NewDescriptor(code: String, studyId: Int)
  case class ExistingPsychometric(code: Int, pName: String, min :Int, max : Int )
  case class EditHideSubjectMdl(studyId: Int, subjectID: String, hide :Int, badSubject: Int )
  case class AddMoreSubjectsMdl(studyNo: Int, subjectNo: Int)


  val addPsychometric = Form(
    mapping(
      "pName" -> nonEmptyText,
      "min" -> number,
      "max" -> number
    )(NewPsychometric.apply)(NewPsychometric.unapply)
  )

  val addNewTopSummary = Form(
    mapping(
      "code" -> nonEmptyText,
      "studyId" -> number
    )(NewTopSummary.apply)(NewTopSummary.unapply)
  )

  val addMoreSubjects = Form(
    mapping(
      "subjectNo" -> number,
      "studyNo" -> number
    )(AddMoreSubjectsMdl.apply)(AddMoreSubjectsMdl.unapply)
  )


  val addDescriptor = Form(
    mapping(
      "subjectNo" -> nonEmptyText,
      "studyId" -> number
    )(NewDescriptor.apply)(NewDescriptor.unapply)
  )

  val editPsychometric = Form(
    mapping(
      "code" -> number,
      "pName" -> nonEmptyText,
      "min" -> number,
      "max" -> number
    )(ExistingPsychometric.apply)(ExistingPsychometric.unapply)
  )

  val editHideSubjectMdl = Form(
    mapping(
      "studyId" -> number,
      "subjectID" -> nonEmptyText,
      "hide" -> number,
      "badSubject" -> number
    )(EditHideSubjectMdl.apply)(EditHideSubjectMdl.unapply)
  )


  val addDataType = Form(
    mapping(
      "dataDec" -> optional(text),
      "dataExtension" -> nonEmptyText,
      "dataType" -> number,
      "yTitle" -> optional(text),
      "frameRate" -> optional(number),
      "first_row" -> optional(number) ,
      "first_col" -> optional(number)
    )(NewDataType.apply)(NewDataType.unapply)
  )

  import play.api.data.format.Formats._
  val editDataType = Form(
    mapping(
      "code" -> number,
      "dataDec" -> optional(text),
      "dataExtension" -> nonEmptyText,
      "dataType" -> number,
      "yTitle" -> optional(text),
      "frameRate" -> optional(number),
      "first_row" -> optional(number) ,
      "first_col" -> optional(number),
      "min_yvalue" -> optional(of[Float]),
      "max_yvalue" -> optional(of[Float]),
      "log" -> number
    )(ExistingDataType.apply)(ExistingDataType.unapply)
  )

  def showAddDataType = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

    Ok(views.html.AddNewDataType(username));
  }


  def CreateStudyTest()= Action{
    implicit request =>
      var str = Abstraction("cplsubjectbook@gmail.com",1,"Using Test-Salah","0BzuoB3uc0tTVS0hmSWkzcU9pM2s",false,false,"pp",List("peda"),"res2",List(),12,12,130,2,4,
      "Normal-Drive",null)

    Global.CreateSTudyAbstractWay(str);
      Ok("");
  }

  def ProcessNewDataType  = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      addDataType.bindFromRequest.fold(
        formWithErrors => {
          //BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Ok(views.html.AddNewDataType(username, "The data type has not been created correctly, please check the data you entered!"));
        },
        contact => {
          DB.withConnection { implicit c =>
            val rate = contact.frameRate match {
              case Some(x) => x
              case _ => 1
            }
            val row = contact.first_row match {
              case Some(x) => x
              case _ => 1
            }
            val col = contact.first_col match {
              case Some(x) => x
              case _ => 1
            }
            val yt = contact.yTitle match {
              case Some(x) => x
              case _ => ""
            }

            val id: Option[Long] =
              SQL("insert into signals(signal_desc ,signal_extension ,data_type,ytitle,frame_rate, first_row, first_col, owner) values ({desc}, {ext}, {type}, {title}, {frame},{frow},{fcol}, {user})")
                .on('desc -> contact.dataDec , 'ext -> contact.dataExtension, 'type -> contact.dataType, 'title -> yt, 'frame -> rate, 'user -> username, 'frow ->row, 'fcol -> col).executeInsert()

            //Ok(views.html.ShowStudies(username, med)).withSession("connected" -> username)
            Redirect(routes.Application.showAllDataTypes()).withSession(
              "connected" -> username);
          }
        }
      )
  }

  def EditDataType  = Action {
    implicit request =>

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      editDataType.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Redirect(routes.Application.showAllDataTypes()).withSession(
            "connected" -> username);
        },
        contact => {
          val rate = contact.frameRate match {
            case Some(x) => x
            case _ => 1
          }
          val row = contact.first_row match {
            case Some(x) => x
            case _ => 1
          }
          val col = contact.first_col match {
            case Some(x) => x
            case _ => 1
          }
          DB.withConnection { implicit c =>
                     val id: Int =
              SQL("update signals set signal_desc = {desc}, signal_extension ={ext}, data_type= {type}, ytitle ={title}, frame_rate={frame}, first_row ={fr}, first_col= {fc}, min_yvalue={mn} , max_yvalue={mx}, isLogarithmic = {log}  WHERE signal_code = {code}")
                .on('desc -> contact.dataDec , 'ext -> contact.dataExtension, 'type -> contact.dataType, 'title -> contact.yTitle, 'frame -> rate, 'code -> contact.code, 'fr -> row, 'fc -> col, 'mn->contact.min_yvalue ,  'mx -> contact.max_yvalue, 'log -> contact.log).executeUpdate()
            SQL("update session set signal_json = null WHERE signal_signal_code  = {code}")
              .on('code -> contact.code).executeUpdate()
            Redirect(routes.Application.showAllDataTypes()).withSession(
              "connected" -> username);
          }
        }
      )
  }

  def showAllDataTypes = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val studies  =
          SQL("select  signal_code, signal_desc, signal_extension, data_type, ytitle, frame_rate, first_row, first_col, min_yvalue, max_yvalue, isLogarithmic  from signals where ( owner ={user} );").on('user -> username)
        //SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
        val med = studies().map(row =>
          (row[Int]("signal_code"),row[Option[String]]("signal_desc"), row[String]("signal_extension") , row[Int]("data_type") , row[Option[String]]("ytitle"), row[Int]("frame_rate"),  row[Int]("first_row"),  row[Int]("first_col"), row[Option[java.math.BigDecimal]]("min_yvalue"),  row[Option[java.math.BigDecimal]]("max_yvalue"), row[Int]("isLogarithmic"))
        ).toList

        Ok(views.html.ShowDataTypes(username,med)).withSession("connected" -> username)
      }
  }

  def showAllPsychometric = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val studies  =
          SQL("select  p_code, p_name, min_value, max_value from psychometric  where ( owner ={user} );").on('user -> username)
        //SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
        val med = studies().map(row =>
          (row[Int]("p_code"), row[String]("p_name"),row[Int]("min_value"), row[Int]("max_value"))
        ).toList
        Ok(views.html.ShowAllPsychometric(username,med)).withSession("connected" -> username)
      }
  }


  def EditPsychometric  = Action {
    implicit request =>

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      editPsychometric.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Redirect(routes.Application.EditPsychometric()).withSession(
            "connected" -> username);
        },
        contact => {
          DB.withConnection { implicit c =>
            val id: Int =
              SQL("update psychometric set p_name = {name}, min_value ={min}, max_value= {max} WHERE p_code = {code}")
                .on('name -> contact.pName , 'min -> contact.min, 'max -> contact.max, 'code -> contact.code).executeUpdate()
            Redirect(routes.Application.showAllPsychometric()).withSession(
              "connected" -> username);
          }
        }
      )
  }


  def showAllSubjectToHide(studyNo : Int) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val studies  =
          SQL("select  subject_id , hide, replicated from subject  where study_id={std} order by subject_id;").on('std -> studyNo)
        //SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
        val med = studies().map(row =>
          (row[String]("subject_id"), row[Int]("hide"), row[Int]("replicated"))
        ).toList
        Ok(views.html.HideSubjects(username,studyNo,med)).withSession("connected" -> username)
      }
  }

  def EditHideSubject  = Action {
    implicit request =>

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      editHideSubjectMdl.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          /*Redirect(routes.Application.showAllSubjectToHide(1)).withSession(
            "connected" -> username);*/
          Ok("Basdddd")
        },
        contact => {
          DB.withConnection { implicit c =>

            if(contact.badSubject == 8  ){
              AuxiliaryMethods.replicateOneSubject(contact.studyId, contact.subjectID)
            }

            val id: Int =
              SQL("update subject set hide = {h}, replicated={bad} WHERE study_id = {sId} AND subject_id= {subId}")
                .on('h -> contact.hide , 'bad -> contact.badSubject, 'sId -> contact.studyId, 'subId -> contact.subjectID).executeUpdate()
            Redirect(routes.Application.showAllSubjectToHide(contact.studyId)).withSession(
              "connected" -> username);
          }
        }
      )
  }


  def AddMoreSubjects  = Action {
    implicit request =>

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
        addMoreSubjects.bindFromRequest.fold(
          formWithErrors => {
            BadRequest("The Study Has not been creted Correctly please check that data you entered!")
            Ok(" Bad Request ")
          },
          contact => {
            DB.withConnection { implicit c =>
              Redirect(routes.Application.showAllSubjectToHide(contact.studyNo)).withSession(
                "connected" -> username);
            }
          }
        )
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

  }
  //addMoreSubjects
  def showAddPsychometric = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      Ok(views.html.AddNewPsychometric(username));
  }


  def showAddTopSummary(studyNo: Int)= Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      Ok(views.html.AddTopSummary(username,studyNo));

  }

  def showAddDescriptor(studyNo: Int)= Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      Ok(views.html.experimentalDesign(username,studyNo));

  }

  def showPyramid(studyNo: Int) = Action  {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      // if the studty has topology that means it is ongoing study and need to be updated
      DB.withConnection { implicit c =>
        val fileLocation =
          SQL("select STUDY_TOPOLOGY from study where study_id ={id} ;").on('id -> studyNo).apply().head
        val typ = fileLocation[Option[String]]("STUDY_TOPOLOGY");
        typ match  {
        case Some(x) =>
          val futureInt: Future[Unit] = scala.concurrent.Future {
            AuxiliaryMethods.UpdateStudy(studyNo)
          }
        case None =>
      }
      }
      Ok(views.html.ShowPyramid(username,studyNo));
  }


  def addNewSubject(studyNo : Int) = Action {
    implicit request =>
      var username: String = "";
      var desc = ""
      var studyLoc = ""
      var arms  =  TreeMap.empty[Int, String]
      var age : List[String] = List.empty
      var gender : List[String] = List.empty
      var temp = List.empty[(Option[String], Int, Int)]
      request.session.get("connected").map { user =>
        username = user;
        DB.withConnection { implicit c =>
          val rowOption1 =
            SQL("select study_topology, study_location from study where study_id={study_id};").on('study_id -> studyNo).apply().head
          desc = rowOption1[String]("study_topology");
          studyLoc = rowOption1[String]("study_location");


        }

        val json = play.libs.Json.parse (desc)
        desc = json.get("descriptor").toString
        val subjectsNo : Int = json.get("subjectNo").asInt();
        val descMap  = DataBaseOperations.fromJsonToMapDescriptorMultipleExperiments(desc)
        val armsList= DataBaseOperations.getArmsFromDescriptorMultipleExperiments(desc)
        println(desc)
        var blockingVarTemp  = json.get("blockingVar")
        val newOb= json.get("secondadryExp");
        var expList: List[Int] = List.empty[Int]
        var expListExtention: List[String] = List.empty[String]
        val newOc = json.get("secondadryRes");
        var resList: List[Int] = List.empty[Int]
        var resListExtentions: List[String] = List.empty[String]

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

        /*for (i <- 1 to blockingVarTemp.size()) {
          if(blockingVarTemp.get(i.toString).toString.equals("\"1\"")){
            age = List("Young", "Old")
           }
          else{
            gender = List("Male", "Female")
          }
        }*/

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


        /////////////////////
        var totolNoOfSubject = 4
        ////////////////

        var armsWithblockingVar : Map[(Int, String,String, String), Int] = Map.empty

        var ageAndGender = "0,0"
        armsList match {
          case Some(data) =>

            if(data.size ==0){

              if (age && gender) {
                ageAndGender = "1,1"
                var localModulus = totolNoOfSubject % 4
                armsWithblockingVar += (0, "0", "Young", "Male") -> ((totolNoOfSubject / 4) + (1% (localModulus +1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (0, "0", "Young", "Female") -> ((totolNoOfSubject / 4) + (1% (localModulus +1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (0, "0", "Old", "Male") -> ((totolNoOfSubject / 4) + (1% (localModulus +1)))
                if(localModulus > 0) localModulus = localModulus -1
                armsWithblockingVar += (0, "0", "Old", "Female") -> ((totolNoOfSubject / 4) + (1% (localModulus +1)))
              }
              else if (age) {
                ageAndGender = "1,0"
                armsWithblockingVar += (0, "0", "Young", "") -> totolNoOfSubject / 2
                armsWithblockingVar += (0, "0", "Old", "") -> (totolNoOfSubject - totolNoOfSubject / 2)
              }
              else if (gender) {
                ageAndGender = "0,1"
                armsWithblockingVar += (0, "0", "", "Male") -> totolNoOfSubject / 2
                armsWithblockingVar += (0, "0", "", "Female") -> (totolNoOfSubject - totolNoOfSubject / 2)
              }
              else {
                armsWithblockingVar += (0, "0", "", "") -> totolNoOfSubject
              }

            }
            else{
              var no =0;
              var modulusArms = totolNoOfSubject % data.size
              for((key, value) <- data) {   // if there is any group in the study

                var noOfsub = totolNoOfSubject / data.size
                if (modulusArms> 0) { // to distribute the remaning subjects
                  noOfsub = noOfsub + 1
                  modulusArms = modulusArms -1
                }
                no = no + 1
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

        }


        ////////////////////////////////////


        var str = ""
        // calcualte the number of unique intervension ... . . . . .
        // <achrom, show if not other, order, arm, fixed>
        var noOfIntervension =0
        var  ctr =1;
        descMap match {
          case Some(data) =>
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
            }
        }




        val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(username)
        val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(username)
        val service: Drive = GoogleDrive.buildService(googleCredential)

        val metadata = GoogleDrive.returnFilesInFolderJustForTest2(service, studyLoc, "mimeType != 'application/vnd.google-apps.folder'")

        /*  var j: Integer = subjectsNo+1
          var num = String.format("%1$02d", j)
          var subjectName = "Subject" + num

          var file2 = insertFile(service,subjectName, "application/vnd.google-apps.folder", studyLoc,null )
          */



        /*  var tempForBen = TreeMap.empty[(String,String, String, String), String]
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

              DataBaseOperations.InsertSubjectGD(subjectName, studyNo, 111, 11, 11)

              //pins.println(bv._2 + ","  + bv._3 + "," + bv._4 + "," + subjectName)
              tempForBen += (bv._2 ,bv._3, bv._4, subjectName) -> subjectName



              var file2 = insertFile(service,subjectName, "application/vnd.google-apps.folder", file.getId,null )


              writerAllSubjects.println(subjectName +"\t"+ file2.getId)
              /////////////////////  add .info fil
              var  writer : PrintWriter = new PrintWriter("C:\\temp\\info.txt", "UTF-8");

              if(bv._2.equalsIgnoreCase("0"))
                writer.println(bv._3 + "\n" + bv._4)
              else
                writer.println(bv._2 + "\n" + bv._3 + "\n" + bv._4)



              writer.close();
              var fileContent: java.io.File = new java.io.File("C:\\temp\\info.txt")
              var mediaContent : FileContent = new FileContent("text/plain", fileContent);

              var filetemp = insertFile(service,subjectName, "text/plain", file2.getId,mediaContent )

              ///////////////////////////////////


              var ctr = 0;
              descMap match {
                case Some(data) =>
                  for ((key, value) <- data) {
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

                        //TODO try to get the baseline and replace zero with one if the session is baseline
                        // primary explantory var
                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryExp").asInt(), "NA", 0, false, 2)
                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryRes").asInt(), "NA", 0, false, 1)

                        expList.foreach(x =>

                          if (x != json.get("primaryExp").asInt()) {
                            DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, x, "NA", 0, false, 0)
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
                      DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryExp").asInt(), "NA", 0, false, 2)
                      DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, json.get("primaryRes").asInt(), "NA", 0, false, 1)

                      expList.foreach(x =>

                        if (x != json.get("primaryExp").asInt()) {
                          DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, x, "NA", 0, false, 0)
                        }

                      )
                    }
                    session_no += 1
                  }

              }

              //out ! subjectName
             // out ! "Subject " + num
            }
          }
  */




        for ((signalId, signalInfo) <- metadata) {


          if (!signalInfo.getTitle.contains("~") && signalInfo.getFileExtension != null) {
            val extension: String = signalInfo.getFileExtension

            println(signalInfo.getTitle + "  " + signalInfo.getFileExtension)
            if(extension.equalsIgnoreCase("txt") && signalInfo.getTitle.contains("pins")){

              println(signalId)

              var input = GoogleDrive.downloadFileByFileId(service, signalId)

              /* var scanner: Scanner = null
               var line: String = ""
               scanner = new Scanner(input)
               while (scanner.hasNextLine) {
                 line = scanner.nextLine();
                 println(line)
               }*/


              var scanner : java.util.Scanner  = new java.util.Scanner(input,"UTF-8").useDelimiter("\\A");
              var theString : String = if( scanner.hasNext()) scanner.next()
              else "";
              System.out.println(theString);
              scanner.close();


              theString = theString + "Group 3,Young,Male,Subject06"


              var  writer : PrintWriter = new PrintWriter("C:\\temp\\subjectListTemp.txt");
              writer.println(theString)
              writer.close();
              var fileContent: java.io.File = new java.io.File("C:\\temp\\subjectListTemp.txt")
              var mediaContent :FileContent  = new FileContent("text/plain", fileContent);

              var updatedFile  = service.files().update(signalId, signalInfo, mediaContent).execute();
            }
            if(extension.equalsIgnoreCase("csv")){
              var input = GoogleDrive.downloadFileByFileId(service, signalId)


              var scanner: Scanner = null
              var line: String = ""
              scanner = new Scanner(input)
              while (scanner.hasNextLine) {
                line = scanner.nextLine();
                if(line.contains("PendingSubject"))
                  println(line)
              }

            }

          }
        }



        //TreeMap[Int,String]
        Ok(views.html.addNewSubjectRealTime(username, studyNo, arms, List(), List()));
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }


  }


  def drp(studyNo: Int) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
       var location  = AuxiliaryMethods.ReportDataInDrPFormatNew(studyNo) ;
      Ok.sendFile(
        content = new java.io.File(location),
        fileName = _ => "AllData.xls"
      )
  }

  def ProcessNewPsychometric  = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      addPsychometric.bindFromRequest.fold(
        formWithErrors => {
          //BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Ok(views.html.AddNewPsychometric(username, "The data type has not been created correctly, please check the data you entered!"));
        },
        contact => {

          DB.withConnection { implicit c =>




            val id: Option[Long] =
              SQL("insert into psychometric(p_name ,min_value ,max_value, owner) values ( {name}, {min}, {max}, {user})")
                .on('name -> contact.pName , 'min -> contact.min, 'max -> contact.max, 'user -> username).executeInsert()

            //Ok(views.html.ShowStudies(username, med)).withSession("connected" -> username)
            Redirect(routes.Application.showAllPsychometric()).withSession(
              "connected" -> username);
          }
        }
      )
  }
  def ProcessNewTopSummary = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      addNewTopSummary.bindFromRequest.fold(
        formWithErrors => {
          //BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Ok(views.html.AddNewPsychometric(username, "The data type has not been created correctly, please check the data you entered!"));
        },
        contact => {

          DB.withConnection { implicit c =>


            val id: Int = SQL("update study set top_summary = {code} where study_id = {id}")
                .on( 'code -> contact.code, 'id -> contact.studyId).executeUpdate()

            Ok(views.html.ShowTopSummary(username, contact.code, contact.studyId))

          }
        }
      )
  }
  def ProcessNewDescriptor = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      addDescriptor.bindFromRequest.fold(
        formWithErrors => {
          //BadRequest("The Study Has not been creted Correctly please check that data you entered!")
          Ok(views.html.AddNewPsychometric(username, "The data type has not been created correctly, please check the data you entered!"));
        },
        contact => {

          DB.withConnection { implicit c =>


            val id: Int = SQL("update study set study_descriptor = {code} where study_id = {id}")
              .on( 'code -> contact.code, 'id -> contact.studyId).executeUpdate()

            Ok(views.html.ShowTopSummary(username, contact.code, contact.studyId))

          }
        }
      )
  }


  /**
   * It is called when the user visit our main page. It then generates the URL for the google permission screen and redirect the user to it.
   * When the user hits Accept It is gonna be redirected to what we specified in our google console (i.e, REDIRECT URIS: http://stressbook.ddns.net/display),
   * which in turn will call ReciveOuthData. The redirect Url will contain the code and state that is needed to access user Drive
   */

  def Main = Action {
    Redirect(GoogleDrive.getAuthorizationUrl())
  }

  /**
   * This will retrieve the state and code from the query string in the URL and then get credential by call google API
   */

  def ReceiveOauthData(state: String, code :String) = Action {
    Redirect(routes.Application.authentication()).withSession(
      "connected" -> GoogleDrive.getUserEmail(code));
  }
  /**
   * This function will be called from "ReceiveOauthData" after getting the credential, and it will redirect the user to his list of studies
   */

  def authentication = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val studies  =
          SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
        val med = studies().map(row =>
          row[String]("study_name") -> row[String]("study_owner") -> row[Int]("study_id")
        ).toList
        Ok(views.html.ShowStudies(username,med)).withSession("connected" -> username)
      }
  }

  /**
   * This Method is called when the user hit My model from any window, and it will display all the studies that the user created or share with other people
   */
  def displayStudies() = Action
  {

    implicit request =>
      var username: String = "";
      var videoIdList:  Map[String, List[ String]]= Map()
      request.session.get("connected").map { user =>
        username = user;
        DB.withConnection { implicit c =>
          val studies  =
            SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
          val med = studies().map(row =>
            row[String]("study_name") -> row[String]("study_owner") -> row[Int]("study_id")
          ).toList
          Ok(views.html.ShowStudies(username,med))
        }
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
        Logger.error("Trying to show studies without log in ")
        Redirect(GoogleDrive.getAuthorizationUrl())
      }

  }

  /**
    * this function will be called by Muhsin app to notify us that a new subject started
    *
    * @param loc: the location of the study on google drive
    * @param subjectid The name of the subject
    * @return
    */
  def startNewSubject(studyLoc: String, subjectLocation: String, subjectid: String) = Action
  {
    implicit request =>
      var studyNo = -1L
      DB.withConnection { implicit c =>


        val NumberofStudy = SQL("select study_id, study_owner from study where study_location ={loc};").on('loc -> studyLoc).apply().head

        val userName = NumberofStudy[String]("study_owner");
        studyNo = NumberofStudy[Long]("study_id")
        if(studyNo != -1){

          val rowOption1 =
            SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subjectid, 'study_id -> studyNo).apply().head
          val seq = rowOption1[Long]("subject_seq");

          val id1: Int = SQL("update subject set in_progress = 9 where study_id = {id}").on('id -> studyNo).executeUpdate()
          val id2: Int = SQL("update subject set in_progress = 8, subject_location= {subLoc}, DOB= NOW()  where study_id = {id} and subject_seq ={seq} ").on('seq -> seq, 'id -> studyNo, 'subLoc ->subjectLocation).executeUpdate()
          /*val userDefinedExtension = DataBaseOperations.listOfFileExtension(userName)
          val googleCredential: GoogleCredential = GoogleDrive.getStoredCredentials(userName)
          val acc: AccessRefreshString = DataBaseOperations.getStoredCredentials(userName)
          val service: Drive = GoogleDrive.buildService(googleCredential)
          val sessions = GoogleDrive.returnFilesInFolderJustForTest2(service, subjectLocation, "mimeType = 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);

          for ((sessionId, sessionInfo) <- sessions) {

            var file: com.google.api.services.drive.model.File = sessionInfo
            var sessionName: String = sessionInfo.getTitle
            sessionName = sessionName.replaceAll("\\s+", "")

            //TODO to also update the delted file to null
            val Signals = GoogleDrive.returnFilesInFolderJustForTest2(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'").asScala.mapValues(x => x);
            var signal_code: Int = 1
              for ((signalId, signalInfo) <- Signals) {
                //
              }



          }*/

        }


        /*link = rowOption3[String]("c");
        studyName = rowOption3[String]("study_name");

        if(link == "") { // if the study locatio was not set create a new one
          studyLoc = ProductionSide.videosLocattion+ studyNo+ studyName
          val id: Int = SQL("update study set study_location_on_server = {code} where study_id = {id}").on('code -> studyLoc, 'id -> studyNo).executeUpdate()
        }
        else
          studyLoc = link

          */
      }

      println("My friend muhsin just wanted to let me know that a new subject is being procseesd")
           Ok("How are you my friend Muhsin ------------------ ")


  }

  /**
   * This Function is called once the user select a study to show from the list of studies
   */

  def showStudy(studyNo: Int) = Action
  {
    implicit request =>
      var username: String = "";
      var parameterList: String= "";
      var study_name: String ="";
      var summary: String ="";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      Logger.info("Show Study Number: " + studyNo)



      var sessionsPerSubject : Map[String, List[( String)]]= Map();
      var subectList : List[( String)] = List();
      DB.withConnection { implicit c =>
        val subjects  =
          SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
            .on('study_no -> studyNo)
        subectList = subjects().map(row =>
          row[String]("subject_id")).toList

        val portrait = SQL("select  distinct(study_name) as study_name, coalesce(portrait_string,'') as portrait , coalesce(top_summary,'') as summary from study where study_id={study_id};").on('study_id -> studyNo).apply().head
        parameterList = portrait[String]("portrait");
        summary = portrait[String]("summary");

        //parameterList = parameterList.substring(0, parameterList.indexOf('~'))
        /*val allpages = parameterList.split("~")
        parameterList = allpages(pageNo-1)

        println("*****************************" + pageNo)*/




        study_name =  portrait[String]("study_name");

      }
      if(summary != "")
        Ok(views.html.ShowTopSummary(username, summary, studyNo))
      else
        Ok(views.html.UnderConstruction(username,  studyNo));
  }
  def showStudySkipTop(studyNo: Int) = Action
  {
    implicit request =>
      var username: String = "";
      var parameterList: String= "";
      var study_name: String ="";
      var summary: String ="";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      Logger.info("Show Study Number: " + studyNo)
      var sessionsPerSubject : Map[String, List[( String)]]= Map();
      var subectList : List[( String)] = List();
      DB.withConnection { implicit c =>
        val subjects  =
          //SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
        SQL("select distinct(subject_id) from subject where study_id ={study_no} and replicated = 9 order by subject_id;")
            .on('study_no -> studyNo)
        subectList = subjects().map(row =>
          row[String]("subject_id")).toList

        val portrait = SQL("select  distinct(study_name) as study_name, coalesce(portrait_string,'') as portrait , coalesce(top_summary,'') as summary from study where study_id={study_id};").on('study_id -> studyNo).apply().head
        parameterList = portrait[String]("portrait");
        study_name =  portrait[String]("study_name");

      }
        Ok(views.html.ShowSubject(username, subectList, studyNo,study_name, parameterList));
  }
  /**
   *  This method is called once the user select one subject to be displayed
   */

  def displaySubject(studyNo: Int, SubjectId: String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {

        Unauthorized("Oops, you are not connected")
      }

      var remote = request.remoteAddress

      /*AuxiliaryMethods.RebuildRalTimeStudy(27);
      AuxiliaryMethods.RebuildRalTimeStudy(28);
      AuxiliaryMethods.RebuildRalTimeStudy(30);
      AuxiliaryMethods.RebuildRalTimeStudy(31);
      AuxiliaryMethods.RebuildRalTimeStudy(35);
      AuxiliaryMethods.RebuildRalTimeStudy(37);
      AuxiliaryMethods.RebuildRalTimeStudy(39);
      AuxiliaryMethods.RebuildRalTimeStudy(40);*/

      println("Help Page :    " + remote)
      //AuxiliaryMethods.MalcomREportDeleteAFter ("Taamneh")
      println("calling jjjjj")
      //ReportDataInDrPFormat(2);
      Logger.info("Show Subject Number: " + SubjectId + " For Study: " +studyNo);
      var signalsPerSession: Map[String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)]] = Map();
      var signalsPerSessionOldName: Map[String, (String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)])] = Map();
      var videosPerSession:  Map[String, List[ (String, String, String)]]= Map()
      var inProgressSubjects : List[String] = List();
      var audioPerSession: Map[String, List[ (String, String, String)]]= Map()


      var subjectsList = List.empty[String]
      var subjectsListWithProgress = List.empty[(String, Long, String)]
      var generalList = List.empty[(Int, Int, Int)]
      var sessionsPerSubjectMenu : Map[String, List[( String)]]= Map();
      var videoIdList:  Map[String, List[ String]]= Map()
      var sourceType: Int = 1;
      var studyName = "";
      var subName = "";
      var isThere = 0L;
      //var inProgress = false;
      var hide = 9;
      DB.withConnection { implicit c =>
       subName =   SubjectId
      if(SubjectId == "")
        {
          val findSubjName =  SQL("select subject_id, hide, in_progress  from subject  where study_id={study_id} and replicated=9 order by subject_id;").on('study_id-> studyNo).apply().head
          subName =   findSubjName[String]("subject_id");


          //hide = findSubjName[Int]("hide");
        }


        var remote = request.remoteAddress
        val showHelp =  SQL("select count(*) as sal  from help  where ip={ipAddress} ;").on('ipAddress-> remote).apply().head
        isThere =   showHelp[Long]("sal");

        if(isThere == 0){
          SQL("insert into help values ({ipAddress})")
            .on('ipAddress -> remote).executeInsert()
        }

          val findSubjName =  SQL("select hide, in_progress from subject  where study_id={study_id} AND subject_id = {sId} order by subject_id;").on('study_id-> studyNo, 'sId -> subName).apply().head
           hide = findSubjName[Int]("hide");
        var prg = findSubjName[Long]("in_progress");

        /*println("Salah aldeen taamneh do you understand what does that mean" + prg)
        if(prg == 8)
          inProgress = true;*/


        val signalsTemp =
          SQL("select session_name, signal_seq, data_type, ytitle, min_yvalue , max_yvalue, isLogarithmic  from  session, signals where  signal_signal_code = signal_code AND is_general=0  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no}) order by order_to_show desc, session_name, signal_signal_code;")
            .on('study_no -> studyNo, 'sub_id -> subName)
        val x = signalsTemp().map(row => (row[String]("session_name"),  row[Int]("signal_seq"), row[Int]("data_type"), row[Option[String]]("ytitle"), row[Option[java.math.BigDecimal]]("min_yvalue") , row[Option[java.math.BigDecimal]]("max_yvalue"), row[Int]("isLogarithmic"))).toList
        signalsPerSession = x.groupBy(x => x._1);
        signalsPerSession = TreeMap(signalsPerSession.toSeq:_*).toMap  // to sort the map\
        var mpVedio :TreeMap[String, String]= TreeMap.empty;
        val mp = DataBaseOperations.getDescriptorAsMap(studyNo)

        mp match {
          case Some(data) =>

          var counter =0;
          mp match {
            case Some(something) =>
              signalsPerSessionOldName = signalsPerSession.map(v =>
                if(something.contains(v._1.replaceFirst("(\\d*\\s*)", ""))){
                   something.get(v._1.replaceFirst("(\\d*\\s*)", "")) match {
                    case Some(sN) =>
                      val pattern = "(\\d*)".r
                      val b = pattern.findFirstIn(v._1) match {
                        case Some(s) => s
                        case None => ""
                      }
                      mpVedio += v._1 -> (b+sN._1).toString
                      b+sN._1 -> (v._1, v._2)
                    case None =>
                      mpVedio += v._1 -> v._1
                      v._1 -> (v._1, v._2)
                  }
                }
                else{
                    mpVedio += v._1 -> v._1
                    v._1 -> (v._1, v._2)
                  }
          )
            case None=>  //
              signalsPerSessionOldName = signalsPerSession.map(v =>
                v._1 -> (v._1, v._2)
              )
          }

        case None =>
          signalsPerSessionOldName = signalsPerSession.map(v =>
            v._1 -> (v._1, v._2)
          )
        }
        signalsPerSessionOldName = TreeMap(signalsPerSessionOldName.toSeq:_*).toMap  // to sort the map\
        val videosTemp = SQL("select session_name,signal_loc, file_name from session, signals  where signal_signal_code = signal_code AND data_type = 2 AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});").
          on('video -> SignalType.getVideoCode, 'study_no -> studyNo, 'sub_id -> subName)

        val vListTemp = videosTemp().map(row =>  (row[String]("session_name"), row[String]("signal_loc"), row[String]("file_name")) ).toList
        videosPerSession = vListTemp.groupBy(x=> x._1);



        val audioTemp = SQL("select session_name,signal_loc, file_name from session, signals  where signal_signal_code = signal_code AND data_type = 10 AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});").
          on('video -> SignalType.getVideoCode, 'study_no -> studyNo, 'sub_id -> subName)
        val aListTemp = audioTemp().map(row =>  (row[String]("session_name"), row[String]("signal_loc"), row[String]("file_name")) ).toList
        audioPerSession = aListTemp.groupBy(x=> x._1);
        if(hide == 8){

          for((k,v) <- videosPerSession){
            videosPerSession += k -> List((k , "0BzuoB3uc0tTVS3VsaXFWNURpejg", ""))
          }
        }
          //videosPerSession = Map();

        // we send the study type just to decide wich player should we use
        val studyType  =
          SQL("select study_type, study_name from study where study_id={study_id};").on('study_id-> studyNo).apply().head
        sourceType = studyType[Int]("study_type");
        studyName = studyType[String]("study_name");

        val subjectsTemp  = SQL("""(select subject.subject_id, session.subject_seq, count(signal_loc), coalesce(subject.group_name,'') as w
          from session, subject
          where session.subject_seq = subject.subject_seq AND signal_loc != 'NA'
          AND study_id= {study_no} AND replicated = 9
          group by session.subject_seq)
          Union
          (select subject.subject_id, session.subject_seq, 0,  coalesce(subject.group_name,'') as w
          from session, subject
          where session.subject_seq = subject.subject_seq
          AND study_id= {study_no} AND replicated = 9
          AND session.subject_seq not in (select  session.subject_seq
                          from session, subject
                          where session.subject_seq = subject.subject_seq AND signal_loc != 'NA'
                          AND study_id= {study_no} AND replicated = 9
                          group by session.subject_seq)
          group by session.subject_seq)
          order by subject_id;""").on('study_no -> studyNo)


        subjectsListWithProgress = subjectsTemp().map(row =>  (row[String]("subject_id"), row[Long]("count(signal_loc)"), row[String]("w"))).toList


        val id: Int = SQL("update subject set in_progress = 9  where study_id = {id} and TIMESTAMPDIFF(day, DOB, now()) >= 1;").on( 'id -> studyNo).executeUpdate()


        val inProgressList = SQL("select subject_id from subject where study_id = {study_no} and in_progress =8;").
          on( 'study_no -> studyNo)

         inProgressSubjects = inProgressList().map(row =>  row[String]("subject_id")).toList
        val general = SQL("select signal_seq, signal_code , data_type from session, signals where signal_signal_code = signal_code AND is_general=1  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no}) order by signal_code desc;").on('sub_id -> subName, 'study_no -> studyNo);
        generalList = general().map(row => (row[Int]("signal_seq") ,row[Int]("signal_code"), row[Int]("data_type"))).toList

      }
      /*if(inProgress) {
        Ok(views.html.ShowSignalsRealTime(subName, signalsPerSessionOldName, studyNo, username, videosPerSession, sourceType, subjectsListWithProgress, generalList, studyName, audioPerSession, inProgressSubjects))
      }
      else*/
        Ok(views.html.ShowSignals(subName, signalsPerSessionOldName ,studyNo, username, videosPerSession,sourceType, subjectsListWithProgress, generalList, studyName, audioPerSession, inProgressSubjects, isThere.toInt))
  }



  def displaySubjectInProgress(studyNo: Int) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {

        Unauthorized("Oops, you are not connected")
      }
      var signalsPerSession: Map[String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)]] = Map();
      var signalsPerSessionOldName: Map[String, (String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)])] = Map();
      var videosPerSession:  Map[String, List[ (String, String, String)]]= Map()
      var inProgressSubjects : List[String] = List();
      var audioPerSession: Map[String, List[ (String, String, String)]]= Map()

      var subjectsList = List.empty[String]
      var subjectsListWithProgress = List.empty[(String, Long, String)]
      var generalList = List.empty[(Int, Int, Int)]
      var sessionsPerSubjectMenu : Map[String, List[( String)]]= Map();
      var videoIdList:  Map[String, List[ String]]= Map()
      var sourceType: Int = 1;
      var studyName = "";
      var subName = "";
      var currentSession = ("", "")
      var inProgress = false;
      var hide = 9;
      DB.withConnection { implicit c =>
        val newSubjects =  SQL("select count(subject_id) as sal from subject where study_id={study_id} AND in_progress = 8").on('study_id-> studyNo).apply().head
        var count = newSubjects[Long]("sal");
        if(count >= 1) {

          val findSubjName = SQL("select hide, in_progress, subject_id, subject_location from subject  where study_id={study_id} AND in_progress = 8 order by subject_id;").on('study_id -> studyNo, 'sId -> subName).apply().head
          hide = findSubjName[Int]("hide");
          var prg = findSubjName[Long]("in_progress");
          var subLocation = findSubjName[String]("subject_location");
          subName = findSubjName[String]("subject_id");


          //update the data for this particulare subject
          currentSession = AuxiliaryMethods.UpdateStudyForOneSubject(studyNo, subLocation)
          println(currentSession)
          if(currentSession._1.equals("DONESUBJECTSTOP")){
            val id: Int = SQL("update subject set in_progress = 9  where study_id = {id} and subject_id ={subId} ").on('subId -> currentSession._2, 'id -> studyNo).executeUpdate()
            prg =9;
          }
          if (prg == 8)
            inProgress = true;
          val signalsTemp =
            SQL("select session_name, signal_seq, data_type, ytitle, min_yvalue , max_yvalue, isLogarithmic  from  session, signals where  signal_signal_code = signal_code AND is_general=0  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no}) order by order_to_show desc, session_name, signal_signal_code;")
              .on('study_no -> studyNo, 'sub_id -> subName)
          val x = signalsTemp().map(row => (row[String]("session_name"), row[Int]("signal_seq"), row[Int]("data_type"), row[Option[String]]("ytitle"), row[Option[java.math.BigDecimal]]("min_yvalue"), row[Option[java.math.BigDecimal]]("max_yvalue"), row[Int]("isLogarithmic"))).toList
          signalsPerSession = x.groupBy(x => x._1);
          signalsPerSession = TreeMap(signalsPerSession.toSeq: _*).toMap // to sort the map\
          var mpVedio: TreeMap[String, String] = TreeMap.empty;
          val mp = DataBaseOperations.getDescriptorAsMap(studyNo)

          mp match {
            case Some(data) =>

              var counter = 0;
              mp match {
                case Some(something) =>
                  signalsPerSessionOldName = signalsPerSession.map(v =>
                    if (something.contains(v._1.replaceFirst("(\\d*\\s*)", ""))) {
                      something.get(v._1.replaceFirst("(\\d*\\s*)", "")) match {
                        case Some(sN) =>
                          val pattern = "(\\d*)".r
                          val b = pattern.findFirstIn(v._1) match {
                            case Some(s) => s
                            case None => ""
                          }
                          mpVedio += v._1 -> (b + sN._1).toString
                          b + sN._1 ->(v._1, v._2)
                        case None =>
                          mpVedio += v._1 -> v._1
                          v._1 ->(v._1, v._2)
                      }
                    }
                    else {
                      mpVedio += v._1 -> v._1
                      v._1 ->(v._1, v._2)
                    }
                  )
                case None => //
                  signalsPerSessionOldName = signalsPerSession.map(v =>
                    v._1 ->(v._1, v._2)
                  )
              }

            case None =>
              signalsPerSessionOldName = signalsPerSession.map(v =>
                v._1 ->(v._1, v._2)
              )
          }
          signalsPerSessionOldName = TreeMap(signalsPerSessionOldName.toSeq: _*).toMap // to sort the map\
          val videosTemp = SQL("select session_name,signal_loc, file_name from session, signals  where signal_signal_code = signal_code AND data_type = 2 AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});").
              on('video -> SignalType.getVideoCode, 'study_no -> studyNo, 'sub_id -> subName)

          val vListTemp = videosTemp().map(row => (row[String]("session_name"), row[String]("signal_loc"), row[String]("file_name"))).toList
          videosPerSession = vListTemp.groupBy(x => x._1);



          val audioTemp = SQL("select session_name,signal_loc, file_name from session, signals  where signal_signal_code = signal_code AND data_type = 10 AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});").
            on('video -> SignalType.getVideoCode, 'study_no -> studyNo, 'sub_id -> subName)
          val aListTemp = audioTemp().map(row => (row[String]("session_name"), row[String]("signal_loc"), row[String]("file_name"))).toList
          audioPerSession = aListTemp.groupBy(x => x._1);
          if (hide == 8) {

            for ((k, v) <- videosPerSession) {
              videosPerSession += k -> List((k, "0BzuoB3uc0tTVS3VsaXFWNURpejg", ""))
            }
          }


        }
        // we send the study type just to decide wich player should we use
        val studyType  =
          SQL("select study_type, study_name from study where study_id={study_id};").on('study_id-> studyNo).apply().head
        sourceType = studyType[Int]("study_type");
        studyName = studyType[String]("study_name");

        val subjectsTemp  = SQL("""(select subject.subject_id, session.subject_seq, count(signal_loc), coalesce(subject.group_name,'') as w
          from session, subject
          where session.subject_seq = subject.subject_seq AND signal_loc != 'NA'
          AND study_id= {study_no} AND replicated = 9
          group by session.subject_seq)
          Union
          (select subject.subject_id, session.subject_seq, 0,  coalesce(subject.group_name,'') as w
          from session, subject
          where session.subject_seq = subject.subject_seq
          AND study_id= {study_no} AND replicated = 9
          AND session.subject_seq not in (select  session.subject_seq
                          from session, subject
                          where session.subject_seq = subject.subject_seq AND signal_loc != 'NA'
                          AND study_id= {study_no} AND replicated = 9
                          group by session.subject_seq)
          group by session.subject_seq)
          order by subject_id;""").on('study_no -> studyNo)


        subjectsListWithProgress = subjectsTemp().map(row =>  (row[String]("subject_id"), row[Long]("count(signal_loc)"), row[String]("w"))).toList



        val inProgressList = SQL("select subject_id from subject where study_id = {study_no} and in_progress =8;").
          on( 'study_no -> studyNo)

        inProgressSubjects = inProgressList().map(row =>  row[String]("subject_id")).toList
        val general = SQL("select signal_seq, signal_code , data_type from session, signals where signal_signal_code = signal_code AND is_general=1  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no}) order by signal_code desc;").on('sub_id -> subName, 'study_no -> studyNo);
        generalList = general().map(row => (row[Int]("signal_seq") ,row[Int]("signal_code"), row[Int]("data_type"))).toList

      }
      Ok(views.html.ShowSignalsRealTime(subName, signalsPerSessionOldName, studyNo, username, videosPerSession, sourceType, subjectsListWithProgress, generalList, studyName, audioPerSession, inProgressSubjects, currentSession._1, currentSession._2))

  }
  /**
   *  LogOut
   */

  def logout = Action {
    Redirect(routes.Application.Main()).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  /**
   *  Called once creating new study, and will redirect the user to multi-step study creating
   */

  def ShowCreateStudyForm = Action {
    implicit request =>
      var username: String = "";
      var temp = List.empty[(Option[String], Int, Int)]
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select  SIGNAL_DESC , signal_code, data_type  from signals where owner ={user};").on('user -> username)
        //SQL("select  signal_code, signal_extension  from signals where owner ={user} \nunion \nselect  signal_code, signal_extension  from signals where  (owner = 'cplsubjectbook@gmail.com'  And signal_extension  not in (select signal_extension from signals where owner ={user} ));").on('user -> username)

        temp = rowOption1().map( row => (row[Option[String]]("SIGNAL_DESC"), row[Int]("signal_code"), row[Int]("data_type"))).toList




      }

      Ok(views.html.ShowCreateStudy(username, temp));
  }
  def ShowInitiateStudy = Action {
    implicit request =>
      var username: String = "";
      var temp = List.empty[(Option[String], Int, Int)]
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select  SIGNAL_DESC , signal_code, data_type  from signals where owner ={user};").on('user -> username)
        //SQL("select  signal_code, signal_extension  from signals where owner ={user} \nunion \nselect  signal_code, signal_extension  from signals where  (owner = 'cplsubjectbook@gmail.com'  And signal_extension  not in (select signal_extension from signals where owner ={user} ));").on('user -> username)

        temp = rowOption1().map( row => (row[Option[String]]("SIGNAL_DESC"), row[Int]("signal_code"), row[Int]("data_type"))).toList
      }

      Ok(views.html.IntiateStudy(username, temp));
  }
  /**
   *  Called whne user finish entering all the fields and hit enter to create new study
   */
  val newStudy2 = Form(
    mapping(
      "study_name" -> nonEmptyText,
      "study_type" -> number,
      "folder_id" -> nonEmptyText,
      "url" -> nonEmptyText,
      "numSubj" ->number,
      "numSess" ->number,
      "numRun" -> number,
      "public" -> number,
      "portrait" -> number,
      "bio" ->number,
      "psychometric" -> number,
      "physiology" -> number,
      "observation" -> number
    )(NewStudy.apply)(NewStudy.unapply)
  )
  def InsertNewStudy = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      newStudy2.bindFromRequest.fold(
        formWithErrors => {
          println(request.body)
            Redirect(routes.Application.displayStudies())
          //BadRequest("The Study Has not been creted Correctly please check that data you entered!")
        },
        contact => {  // This not going to be called every thing is done through mywebsocket
          Redirect(routes.Application.displayStudies())
        }
      )
  }


  def InitiateNewStudy = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      newStudy2.bindFromRequest.fold(
        formWithErrors => {
          println(request.body)
          Redirect(routes.Application.displayStudies())
        },
        contact => {  // This not going to be called every thing is done through mywebsocket
          Redirect(routes.Application.displayStudies())

        }
      )
  }


  val deleteMe = Form(
    mapping(
      "study_id" -> number
    )(deleteStudyData.apply)(deleteStudyData.unapply)
  )
  def deleteStudy = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      deleteMe.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("The Study Has not been creted Correctly please check that data you entered!")
        },
        contact => {

          DB.withConnection { implicit c =>

            DataBaseOperations.deleteStudy(contact.study_id)
            val studies =
              SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
            val med = studies().map(row =>
              row[String]("study_name") -> row[String]("study_owner") -> row[Int]("study_id")
            ).toList

            Ok(views.html.ShowStudies(username, med)).withSession("connected" -> username)
          }
        }
      )
  }

  def infoForPortrait (studyId: Int) = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>


        // s  = GoogleDrive.GetSubjectInfo(username, file_location, sourceType, signal_type,bio);
        var json = Json.parse("");
        Ok(json);

        Ok(s);
      }
  }

  /**
   *  This is called when the user choose on of the tabs (i.e, session)
   */

  def GetSignal(task: String, subject: String, studyId: Int, signal_type: Int, signal_sequence: Int) = Action {
    implicit request =>
      var subjectSeq : Long = 0
      var jsonFromDB = ""
      var signal_desc = ""
      var file_location = "";
      var isFdn = false;
      var frameRate =  1;
      var signalCode = 1;
      var first_row = 1;
      var first_col =1;
      var islog = 0;
      var minY = 0.0;
      var study_owner = "";
      var activityFile : String = null;
      var baslineFile :String = null;
      var baselineSessionName : String = ""
      var sourceType = 0;
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      println("Recived A signal")
      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id -> studyId).apply().head
        subjectSeq = rowOption1[Long]("subject_seq");



        ///////////////////////////////
        val test =
          SQL("select signal_json   from session  where  subject_seq ={seq}  AND run_no =1 And signal_seq= {signal_seq};").on('seq -> subjectSeq, 'signal_seq -> signal_sequence).apply().head
        val tempSignal = test[Option[String]]("signal_json");
        jsonFromDB = tempSignal match{
          case Some(str) =>
            val source = scala.io.Source.fromFile(str)
            val lines = try source.mkString
              catch{
                case ex: IOException => {
                  null
                }
              }
            finally source.close()
            lines
          case None =>
            val rowOption2  =
              SQL("select SIGNAL_DESC ,signal_code, signal_loc, frame_rate, first_row, first_col, islogarithmic, coalesce(min_yvalue,0) as c  from session, signals  where signal_signal_code = signal_code AND subject_seq ={seq}  AND run_no =1 And signal_seq= {signal_seq};").on('seq -> subjectSeq,  'signal_seq -> signal_sequence).apply().head
            file_location = rowOption2[String]("signal_loc");
            frameRate = rowOption2[Int]("frame_rate");
            first_row = rowOption2[Int]("first_row");
            first_col = rowOption2[Int]("first_col");
            signalCode = rowOption2[Int]("signal_code");
            signal_desc = rowOption2[String]("SIGNAL_DESC");
            islog = rowOption2[Int]("islogarithmic");
            minY = rowOption2[Double]("c");



            val rowOption3  =
              SQL("select study_type, study_owner from study where study_id={study_id};").on('study_id-> studyId).apply().head
            sourceType = rowOption3[Int]("study_type");
            study_owner = rowOption3[String]("study_owner");

            val activity2 =
            //SQL("select signal_loc from session where subject_seq={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_code={activity};").on('seq -> seq, 'sess_name-> task, 'activity -> SignalType.getActivityCode).apply().headOption
              SQL("select signal_loc from session where subject_seq={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_code in (select signal_code from signals where owner = {owner} AND data_type = 6);").on('seq -> subjectSeq, 'sess_name-> task, 'owner -> study_owner).apply().headOption


            println(subjectSeq + "  " + task + "  ")
            activity2 match {
              case firstDay if firstDay.size >0 =>
                activityFile = firstDay.head[String]("signal_loc");
              case None=>
                activityFile = null

            }
            val testForfailure =
            //SQL("select signal_loc from session where subject_seq={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_code={activity};").on('seq -> seq, 'sess_name-> task, 'activity -> SignalType.getActivityCode).apply().headOption
              SQL("select session_name from session where subject_seq={seq} AND session_name Like '%FDN%' AND run_no =1;").on('seq -> subjectSeq).apply().headOption

            testForfailure match {
              case firstDay if firstDay.size >0 =>
                isFdn =true;
              case None=>
                isFdn = false;
            }
            val blSignal  = SQL("select signal_loc, SESSION_NAME from session where subject_seq={seq} AND  run_no =1 And signal_signal_code ={sc} AND is_baseline =1 ;").on('seq -> subjectSeq, 'sc -> signalCode).apply().headOption
            blSignal match {
              case firstDay if firstDay.size >0 =>
                baslineFile = firstDay.head[String]("signal_loc");
                baselineSessionName = firstDay.head[String]("SESSION_NAME").replaceFirst("(\\d*\\s*)", "") ;
              case None=>
                baslineFile = null;
            }
            // this is to declare that not json was found
            null
        }
      }


      //This is only for failure drive in toyota project
      if(jsonFromDB== null) {

        if(file_location.equals("NA")){
          Ok("{ \"NOTTHERE\" : \"" +  signal_desc + "\"}")

        }
        else {


          if (task.toLowerCase.contains("failure") || task.toLowerCase.contains("fdl") || task.toLowerCase.contains("fdn"))
            baslineFile = null;

          var desc: java.util.TreeMap[String, String] = null
          if (signal_type == 4 || baslineFile != null) {


            desc = DataBaseOperations.getDescriptorAsMapJava(studyId)
            //TODO this code should be remove becuase it is not practical to call it every time We can let the user provide us with the baselinename
            // we can send the session schema to user at the beginign and users provide use with it.
            if (desc != null) {
              if (desc.contains(baselineSessionName.replaceFirst("(\\d*\\s*)", "")))
                baselineSessionName = desc(baselineSessionName.replaceFirst("(\\d*\\s*)", ""))
              if (isFdn) desc.put("FailureDrive", "FDN")
              else desc.put("FailureDrive", "FDL")
            }

          }

          var js = GoogleDrive.DownloadSignal(study_owner, file_location, sourceType, signal_type, frameRate, first_row, first_col, activityFile, baslineFile, baselineSessionName, desc, islog, minY);
          if (js == null)
            Ok("");
          else {
            var destination: Path = null
            val id: UUID = UUID.randomUUID
            val fileNmae = "\\jsonFiles\\" + id + ".txt";
            val pw = new PrintWriter(new File(fileNmae))
            DataBaseOperations.UpdateSignalJson(subjectSeq, signal_sequence, fileNmae)
            pw.write(js.toJSONString)
            pw.close
            println("Sent a signal " + request.queryString)
            Ok(js.toJSONString)

          }
        }

      }
      else{
        println("Sent a signal " + request.queryString)
        Ok(jsonFromDB)
      }


  }


  def showRadar (studyNo: Int, SubjectId: String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      Ok(views.html.ShowRadarForOneSubject(SubjectId, studyNo, username));
  }
  def Getdummy() = Action {
    implicit request =>
      var file_location = "";
      var frameRate =  1;
      var first_row = 1;
      var first_col =1;
      var study_owner = "";
      var activityFile : String = null;
      var sourceType = 0;
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      //var js = GoogleDrive.DownloadSignal(username, file_location, sourceType, signal_type);
      var js = Json.arr();

      var temp ="";

      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select radar from study where study_id= 2").apply().head
          temp = rowOption1[String]("radar");
         //js = temp.
      }

        Ok(temp)
  }


  def getRadar( studyId: Int, subject: String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      //var js = GoogleDrive.DownloadSignal(username, file_location, sourceType, signal_type);
      var js = Json.arr();

      var temp ="";

      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select radar_value from subject where study_id= {studyNo} AND subject_id= {sub}").on('studyNo -> studyId, 'sub-> subject).apply().head
        temp = rowOption1[String]("radar_value");
        //js = temp.
      }

      println(temp);

      Ok(temp)
  }


  def getInfo (task: String, subject: String, studyId: Int, signal_seq: Int) = Action {
    implicit request =>
      var file_location = "";
      var study_owner = "";
      var sourceType = 0;
      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1  =
          SQL("select subject_seq, subject_location from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");
        var bio_code = rowOption1[String]("subject_location");

        val rowOption4  =
          SQL("select study_type, study_owner from study  where study_id ={study_id};").on('study_id-> studyId).apply().head
        study_owner = rowOption4[String]("study_owner");
        sourceType = rowOption4[Int]("study_type");

          val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq}  AND run_no =1 And signal_seq= {signal_seq};").on('seq -> seq, 'signal_seq -> signal_seq).apply().head
          file_location = rowOption2[String]("signal_loc");



          //val bio: Biography = AuxiliaryMethods.BiographyCode(bio_code);
          s  = QueryStudy.GetSubjectInfo2(study_owner, file_location, sourceType, 3,null);
          if(s == null) // in case the file was deleted by the user..
            Ok("{}");
          else
            Ok(Json.parse(s));
      }

  }


  def getPRF (task: String, subject: String, studyId: Int, signal_type: Int) = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1  =
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");

        val check  =
          SQL("select coalesce(count(session_name),0) as c from session where subject_seq={seq} AND session_name='PRF';").on('seq -> seq).apply().head
        var ctr = check[Long]("c");


        if(ctr == 1)
        {
          val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ='PRF' AND run_no =1 And signal_signal_code= {signal_type};").on('seq -> seq, 'sess_name-> task, 'signal_type -> signal_type).apply().head
          file_location = rowOption2[String]("signal_loc");

          val rowOption3  =
            SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
          sourceType = rowOption3[Int]("study_type");



          s  =QueryStudy.GetSubjectPRF(username, file_location, sourceType);
          var json = Json.parse(s);
          Ok(json);
        }
        else
        {
          Ok(s);
        }
      }

  }


  def getPsycho (task: String, subject: String, studyId: Int, signal_seq: Int) = Action {
    implicit request =>
      var file_location = "";
      var study_owner = "";
      var sourceType = 0;

      //var s:String = "{\"Dummy\": \"Dummy\"}";
      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1  =
          SQL("select subject_seq, psycho from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");
        var psycho_code = rowOption1[Int]("psycho");

        val rowOption4  =
          SQL("select study_owner, study_type from study  where study_id ={study_id};").on('study_id-> studyId).apply().head
        study_owner = rowOption4[String]("study_owner");
        sourceType = rowOption4[Int]("study_type");


              val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq} AND  run_no =1 And signal_seq = {signal_seq};").on('seq -> seq,  'signal_seq -> signal_seq).apply().head
          file_location = rowOption2[String]("signal_loc");

          val pyc: Psychometric = AuxiliaryMethods.PsychometricCode(psycho_code);


        var lst = DataBaseOperations.listOfPsychometric(study_owner);
        var newLst : java.util.TreeMap[String, SharedData.MinMax] = new java.util.TreeMap[String, SharedData.MinMax]();

        for((k,v) <- lst){
          newLst += k ->new SharedData.MinMax(v._1, v._2)
        }

        Ok(QueryStudy.GetSubjectPm2(study_owner, file_location, sourceType, 4,pyc, newLst).toJSONString)
      }

  }


  def getPortraitInfo (studyId: Int) = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1 =
          SQL("select portrait_string from study where study_id={study_id};").on('study_id -> studyId).apply().head
        val parameterList = rowOption1[String]("portrait_string");
        Ok(parameterList);

      }
  }


  def file (task: String, subject: String, studyId: Int, signal_type: Int, signal_sequence: Int) = Action {

    implicit request =>
      var username: String = "";
      var file_location: String= "";
      //var out : OutputStream = ;
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1  =
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");

        val rowOption2  =
          SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ={sess_name} AND run_no =1 And signal_seq= {signal_seq};").on('seq -> seq, 'sess_name-> task, 'signal_seq -> signal_sequence).apply().head
        file_location = rowOption2[String]("signal_loc");

      }
      val input :InputStream =GoogleDrive.downloadFileToClient(username, file_location);
      val dataContent: Enumerator[Array[Byte]] = Enumerator.fromStream(input)

      Result(
        header = ResponseHeader(200, Map(CONTENT_TYPE ->"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),
        body = dataContent
      )

  }



  def zip (studyId: Int) = Action  {

    implicit request =>
      var username: String = "";
      var locations: List[String] = List()
      var subjects: List[String] = List()
      var sessions: List[String] = List()

      var all: List[((String, String), String)] = List();
      var seq :Seq[Int] = null
      ;
      //var out : OutputStream = ;
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>

        val rowOption1  =
          SQL("select subject_seq from subject where study_id={study_id};").on('study_id-> studyId)
        seq = rowOption1().map(row => row[Int]("subject_seq")).toSeq


        //TODO remove the fixed number 1,2,3,4....


        val rowOption2  =
          SQL("select signal_loc ,subject_id, session_name from session, subject where session.subject_seq  = subject.subject_seq and session.subject_seq in ({seq}) AND signal_signal_code in (1,2,3,4,5,6,7,8) AND run_no =1 order by subject_id;").on('seq -> seq)
        locations = rowOption2().map(row => row[String]("signal_loc")).toList
        subjects = rowOption2().map(row => row[String]("subject_id")).toList
        sessions = rowOption2().map(row => row[String]("session_name")).toList
        //all = rowOption2().map(row => row[String]("signal_loc") -> row[String]("subject_id") -> row[String]("session_id")).toList
      }

      //val x: java.util.List[String] = ListBuffer(List("3", "3"): _*)

      val input :java.util.ArrayList[ZipFileEntry] =GoogleDrive.downloadFileToClientWholeStudy(username, locations.asJava, subjects.asJava, sessions.asJava);
      import play.api.libs.iteratee._
      import java.util.zip._

      val r = new java.util.Random()

      val enumerator = Enumerator.outputStream { os =>
        val zip = new ZipOutputStream(os);
        Range(0, input.size()).map { i =>
          //zip.putNextEntry(new ZipEntry("test-zip/README-"+i+".xlsx"))
          zip.putNextEntry(new ZipEntry( input.get(i).subjectName+ "/" + input.get(i).fileName+i+".xlsx"))
          //zip.write("Here are 100000 random numbers:\n".map(_.toByte).toArray)
          zip.write(IOUtils.toByteArray(input.get(i).data));
          // Let's do 100 writes of 1'000 numbers
          /*Range(0, 100).map { j =>
            zip.write((Range(0, 1000).map(_=>r.nextLong).map(_.toString).mkString("\n")).map(_.toByte).toArray);
          }*/
          zip.closeEntry()
        }
        zip.close()
      }
      Ok.chunked(enumerator >>> Enumerator.eof).withHeaders(
        "Content-Type"->"application/zip",
        "Content-Disposition"->"attachment; filename=test.zip"
      )
  }

  def outputStream(a: java.io.OutputStream => Unit): Enumerator[Array[Byte]] = {
    Concurrent.unicast[Array[Byte]] { channel =>
      val outputStream = new java.io.OutputStream(){
        override def close() {
          channel.end()
        }
        override def flush() {}
        override def write(value: Int) {
          channel.push(Array(value.toByte))
        }
        override def write(buffer: Array[Byte]) {
          write(buffer, 0, buffer.length)
        }
        override def write(buffer: Array[Byte], start: Int, count: Int) {
          channel.push(buffer.slice(start, start+count))
        }
      }
      a(outputStream)
    }
  }


  def ShowDriveDialog = Action {

    Ok(views.html.googlePicker());
  }



  val shareForm = Form(
    mapping(
      "email" -> nonEmptyText,
      "role" -> number,
      "message" -> nonEmptyText,
      "studyId"-> number
    )(Sharing.apply)(Sharing.unapply)
  )

  // Ajax call from inside editStudy html
  def shareMyStudy =Action
  {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      shareForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("SomeThing Wrong Happened!");
        },
        contact => {
          DB.withConnection { implicit c =>
            val rowOption  =
              SQL("select count(*) as c from privilege where username ={un} AND study_id = {sid} AND permission_type= {per}")
                .on('un -> contact.email, 'sid -> contact.studyId , 'per -> contact.role).apply().head

            val ctr = rowOption[Long]("c")
            if (ctr >0 )
            {
              Ok("The user already has the permission!");
            }
            else {
              val rowOption2  =
                SQL("select count(*) as c from privilege where username ={un} AND study_id = {sid}")
                  .on('un -> contact.email, 'sid -> contact.studyId , 'per -> contact.role).apply().head

              val ctr = rowOption2[Long]("c")
              if (ctr >0 )
              {
                val id: Int =
                  SQL("update privilege set permission_type = {per}")
                    .on( 'per -> contact.role).executeUpdate()
                Ok("The permission has been updated");
              }
              else {
                val id: Option[Long] =
                  SQL("insert into privilege(username,study_id, permission_type) values ({un}, {sid}, {per})")
                    .on('un -> contact.email, 'sid -> contact.studyId, 'per -> contact.role).executeInsert()
                Ok("Thank you, your study has been shared!");
              }
            }
          }

        }
      )

  }


  /*
    It will give the user the opporunity to upload more files under any subfolder
   */

  def sendRequest(StudyName: String) =TODO

  def video = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/JUST.pdf"),
      inline = true
    )
  }


  def signalDataExample = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/RI_S001-001.csv"),
      fileName = _ => "singal.csv"
    )
  }

  def InfoExample = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/S002.info"),
      fileName = _ => "info.txt"
    )
  }

  def PychometricExample = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/S001.pm"),
      fileName = _ => "pm.txt"
    )
  }


  def tabularExample = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/T088.bar"),
      fileName = _ => "tabular.csv"
    )
  }

  def videoExample = Action {
    Ok.sendFile(
      content = Play.application.getFile("/public/mov_bbb.mp4"),
      inline = true
    )
  }


  def findImage(img_no : Int) = Action{

    val app = Play.application
    val file = Play.application.getFile("/public/" + img_no + ".jpg")
    val source = scala.io.Source.fromFile(file)(scala.io.Codec.ISO8859)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = Enumerator(byteArray)
    )
  }

  def getVideo () = Action {
    implicit request =>

      val range = request.headers.get(RANGE).map(_.substring("bytes=".length).split("-") match {
        case x if x.length == 1 => (x.head.toLong, -1l)
        case x => (x(0).toLong, x(1).toLong)
      })
      println("Range: "  +range);

      var file_location = "";
      var sourceType = 0;


      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      //val file = new java.io.File("C:\\Users\\staamneh\\Desktop\\sim2MP4\\Subject02\\1Baseline\\Subject02_Baseline.avi.mp4")

      val file = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\22\\md.mp4").toURI.toURL
        lazy val (length, resourceData, resourceRange) = {

         // val targetStream = new FileInputStream(file);
         val targetStream = file.openStream()
          val total = targetStream.available
         //val total = file.length()
          val resourceRange = range match {
            case None =>
              (0, total - 1)
            case Some((start: Long, end: Long)) =>
              println("Start: " + start + " end: " + end)
              (targetStream.skip(start), if (end == -1l) total - 1 else end)
          }
          println("Streamleng: " + targetStream.available())
          (total, Enumerator.fromStream(targetStream), resourceRange)
        }

        println("resource :" + resourceRange)
        val basicHeader = Map(
          CONTENT_TYPE -> "video/mp4",
          ACCEPT_RANGES -> "bytes"
        )

        val header = resourceRange match {
          case (start: Long, end: Long) => ResponseHeader(PARTIAL_CONTENT,
            basicHeader ++ Map(
              CONTENT_LENGTH -> (end - start + 1).toString,
              CONTENT_RANGE -> "bytes %d-%d/%d".format(start, end, length),
              CONNECTION -> "keep-alive"
            ))
          case _ => ResponseHeader(OK, basicHeader ++ Map(
            CONTENT_LENGTH -> length.toString))
        }
        SimpleResult(header = header, body = resourceData)
  }



  def getVideo2 (studyNo: Int, subjectId : String, sessionName: String ) = Action {
    implicit request =>


      var file_location = "";
      var sourceType = 0;
      // var s:String = "{\"Dummy\": \"Dummy\"}";

      var s:String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      val link = "C:\\Users\\staamneh\\Desktop\\sim2MP4\\" + subjectId + "\\" + sessionName + "\\" + subjectId + "_" + sessionName.replaceFirst("(\\d*\\s*)", "") +".avi.mp4"
      //val file = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\24\\md.mp4")
      val path =  "C:\\\\Users\\\\staamneh\\\\Desktop\\\\CPL-Lab\\\\videosCliping\\\\24\\\\"
      val fileName= "md.mp4";
      val file = new java.io.File(path+fileName)

      import Play.current

       val timeZoneCode = "GMT"


      //Dateformatter is immutable and threadsafe
       val df: DateTimeFormatter = org.joda.time.format.DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss '" + timeZoneCode + "'").withLocale(java.util.Locale.ENGLISH).withZone(DateTimeZone.forID(timeZoneCode))

      //Dateformatter is immutable and threadsafe
       val dfp: DateTimeFormatter =
      org.joda.time.format.DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss").withLocale(java.util.Locale.ENGLISH).withZone(DateTimeZone.forID(timeZoneCode))

       val parsableTimezoneCode = " " + timeZoneCode

       lazy val defaultCharSet = current.configuration.getString("default.charset").getOrElse("utf-8")

       def addCharsetIfNeeded(mimeType: String): String =
        if (play.api.libs.MimeTypes.isText(mimeType))
          "; charset=" + defaultCharSet
        else ""


      def parseDate(date: String): Option[java.util.Date] = try {
        //jodatime does not parse timezones, so we handle that manually
        val d = dfp.parseDateTime(date.replace(parsableTimezoneCode, "")).toDate
        Some(d)
      } catch {
        case NonFatal(_) => None
      }


      val resourceName = Option(path + "/" + fileName).map(name => if (name.startsWith("/")) name else ("/" + name)).get
      //val resourceName = path+fileName
      // -- Range handling
      var range = request.headers.get(RANGE).map(_.substring("bytes=".length).split("-") match {
        case x if x.length == 1 => (x.head.toLong, -1l)
        case x => (x(0).toLong, x(1).toLong)
      })

      //range = Option(0l, -1l)
      println("Range: " +range)



      if (new File(resourceName).isDirectory || !new File(resourceName).getCanonicalPath.startsWith(new File(path).getCanonicalPath)) {
        NotFound
      } else {


        println("ResourceName: "+ resourceName)

        //val gzippedResource = Play.resource(resourceName + ".gz")
        val gzippedResource: Option[URL] = None

        println("gzip: "+ gzippedResource)

        val resource : Option[(URL, Boolean)] = {
          gzippedResource.map(_ -> true)
            .filter(_ => request.headers.get(ACCEPT_ENCODING).map(_.split(',').exists(_.trim == "gzip" && Play.isProd
              && range.isEmpty)).getOrElse(false)).orElse(Some(file.toURI.toURL).map(_ -> false))
        }

        println("gzip: "+ gzippedResource)
        println("resource: " + resource)

        val etagMap = new java.util.concurrent.ConcurrentHashMap[String, String]().asScala

        val lastModifieds = new java.util.concurrent.ConcurrentHashMap[String, String]().asScala


        def cacheableResult[A <: Result](url: java.net.URL, r: A) = {
          // Add Etag if we are able to compute it
          val taggedResponse = etagFor(url).map(etag => r.withHeaders(ETAG -> etag)).getOrElse(r)
          val lastModifiedResponse = lastModifiedFor(url).map(lastModified => taggedResponse.withHeaders(LAST_MODIFIED -> lastModified)).getOrElse(taggedResponse)

          // Add Cache directive if configured
          val cachedResponse = lastModifiedResponse.withHeaders(CACHE_CONTROL -> {
            Play.configuration.getString("\"assets.cache." + resourceName + "\"").getOrElse(Play.mode match {
              case Mode.Prod => Play.configuration.getString("assets.defaultCache").getOrElse("max-age=3600")
              case _ => "no-cache"
            })
          })
          cachedResponse
        }


        def lastModifiedFor(resource: java.net.URL): Option[String] = {
          def formatLastModified(lastModified: Long): String = df.print(lastModified)

          def maybeLastModified(resource: java.net.URL): Option[Long] = {
            resource.getProtocol match {
              case "file" => Some(new File(resource.getPath).lastModified)
              case "jar" => {
                Option(resource.openConnection)
                  .map(_.asInstanceOf[JarURLConnection].getJarEntry.getTime)
                  .filterNot(_ == -1)
              }
              case _ => None
            }
          }

          def cachedLastModified(resource: java.net.URL)(orElseAction: => Option[String]): Option[String] =
            lastModifieds.get(resource.toExternalForm).orElse(orElseAction)

          def setAndReturnLastModified(resource: java.net.URL): Option[String] = {
            val mlm = maybeLastModified(resource).map(formatLastModified)
            mlm.foreach(lastModifieds.put(resource.toExternalForm, _))
            mlm
          }

          if (true) //(Play.isProd) // we always cache DataDirAssets
            cachedLastModified(resource) {
              setAndReturnLastModified(resource)
            }
          else
            setAndReturnLastModified(resource)
        }

        def etagFor(resource: java.net.URL): Option[String] = {
          etagMap.get(resource.toExternalForm).filter(_ => Play.isProd).orElse {
            val maybeEtag = lastModifiedFor(resource).map(_ + " -> " + resource.toExternalForm).map("\"" + Codecs.sha1(_) + "\"")
            maybeEtag.foreach(etagMap.put(resource.toExternalForm, _))
            maybeEtag
          }
        }



        def maybeNotModified(url: java.net.URL) = {
          request.headers.get(IF_NONE_MATCH).flatMap { ifNoneMatch =>
            etagFor(url).filter(_ == ifNoneMatch)
          }
        }.map(_ => cacheableResult(url, NotModified)).orElse {
          request.headers.get(IF_MODIFIED_SINCE).flatMap(parseDate).flatMap { ifModifiedSince =>
            lastModifiedFor(url).flatMap(parseDate).filterNot(lastModified => lastModified.after(ifModifiedSince))
          }.map(_ => NotModified.withHeaders(
            DATE -> df.print({ new java.util.Date }.getTime)))
        }

        println("Are we there!!!!!!!!")
        resource.map {
          case (url, _) if new File(url.getFile).isDirectory => NotFound

          case (url, isGzipped) => {

            lazy val (length, resourceData, resourceRange) = {
              val stream = url.openStream()
              try {
                val total = stream.available
                val resourceRange = range.map(_ match {
                  case (start: Long, end: Long) =>
                    (stream.skip(start), if (end == -1l) total - 1 else end)
                })
                (total, Enumerator.fromStream(stream), resourceRange)
              } catch {
                case e: Throwable => {
                  (-1, Enumerator[Array[Byte]](), None)
                }
              }
            }

            if (length == -1) {
              NotFound
            } else {

              maybeNotModified(url).getOrElse {
                // Prepare a streamed response
                val basicHeader = Map(
                  CONTENT_TYPE -> play.api.libs.MimeTypes.forFileName(fileName).map(m => m + addCharsetIfNeeded(m)).getOrElse(BINARY),
                  ACCEPT_RANGES -> "bytes",
                  DATE -> df.print({ new java.util.Date }.getTime)
                )
                val header = resourceRange match {
                  case Some((start: Long, end: Long)) => ResponseHeader(PARTIAL_CONTENT,
                    basicHeader ++ Map(
                      CONTENT_LENGTH -> (end - start + 1).toString,
                      CONTENT_RANGE -> "bytes %d-%d/%d".format(start, end, length),
                      CONNECTION -> "keep-alive"
                    ))
                  case None => ResponseHeader(OK, basicHeader ++ Map(
                    CONTENT_LENGTH -> length.toString)
                  )
                }
                //val response = SimpleResult(header = header, resourceData)
                val response = SimpleResult(header = header, body = resourceData)

                // If there is a gzipped version, even if the client isn't accepting gzip, we need to specify the
                // Vary header so proxy servers will cache both the gzip and the non gzipped version
                val gzippedResponse = (gzippedResource.isDefined, isGzipped) match {
                  case (true, true) => response.withHeaders(VARY -> ACCEPT_ENCODING, CONTENT_ENCODING -> "gzip")
                  case (true, false) => response.withHeaders(VARY -> ACCEPT_ENCODING)
                  case _ =>
                    println("----------------------------")
                    response
                }
                cacheableResult(url, gzippedResponse)
              }
            }
          }
        }.getOrElse(
          NotFound)
      }
  }


  def getVideo3 (studyNo: Int, subjectId : String, sessionName: String, fileName: String ) = Action{
    implicit request =>

      val range = request.headers.get(RANGE).map(_.substring("bytes=".length).split("-") match {
        case x if x.length == 1 => (x.head.toLong, -1l)
        case x => (x(0).toLong, x(1).toLong)
      })

      println("Range: " + range);

      var file_location = "";
      var sourceType = 0;
      // var s:String = "{\"Dummy\": \"Dummy\"}";

      var s: String = "{}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      /*val system = ActorSystem("salah")
      val sys = ActorSystem("videoAkkSystem")
      val myExecutionContext: ExecutionContext = sys.dispatchers.lookup("akka.actor.my-context");
      */

      var link = "";
      DB.withConnection { implicit c =>
        val rowOption3 =
          SQL("select coalesce(study_location_on_server, '') as c from study where study_id={study_id};").on('study_id -> studyNo).apply().head
        link = rowOption3[String]("c");
      }
      if(link == "") NotFound
      else {

        var contentType = "video/mp4"

        if(fileName.toLowerCase.contains("mp3"))
          contentType = "audio/mpeg"
        var loc =
          if(new java.io.File(link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName).exists())
            link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName
          else if(new java.io.File(link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".mp3").exists()) {
            contentType = "audio/mpeg"
            link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".mp3"
          }
          else if(new java.io.File(link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".mp4").exists())
            link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".mp4"
          else if(new java.io.File(link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".avi.mp4").exists())
            link + "\\" + subjectId + "\\" + sessionName + "\\" + fileName + ".avi.mp4"
          else ""

        try {
          val file = new java.io.File(loc).toURI.toURL
          lazy val (length, resourceData, resourceRange) = {

            // val targetStream = new FileInputStream(file);
            val targetStream = file.openStream()
            val total = targetStream.available
            val resourceRange = range match {
              case None =>
                (0, total - 1)
              case Some((start: Long, end: Long)) =>
                println("Start: " + start + " end: " + end)
                (targetStream.skip(start), if (end == -1l) total - 1 else end)
            }
            Logger.info("Streamleng: " + targetStream.available())
            (total, Enumerator.fromStream(targetStream), resourceRange)
          }

          val basicHeader = Map(
            CONTENT_TYPE -> contentType,
            ACCEPT_RANGES -> "bytes"
          )


          val header = resourceRange match {
            case (start: Long, end: Long) => ResponseHeader(PARTIAL_CONTENT,
              basicHeader ++ Map(
                CONTENT_LENGTH -> (end - start + 1).toString,
                CONTENT_RANGE -> "bytes %d-%d/%d".format(start, end, length),
                CONNECTION -> "close"
              ))
            case _ => ResponseHeader(OK, basicHeader ++ Map(
              CONTENT_LENGTH -> length.toString))
          }
          SimpleResult(header = header, body = resourceData)
        }
          catch {
            case e: FileNotFoundException =>
              println("File Was not found ")
              NotFound
              }
        }





  }

  def test (studyNo: Int) = Action {
    implicit request =>

      //AuxiliaryMethods.RebuildRealTimeStudy(30)
      //Ok(views.html.testVideoFromDrive());
      Ok(views.html.showRadar());

  }



  // This fucntion need to be used sparingly, and no interface should be exposued to user, this will read user's video and isntall them on our server
  def updateVidoefromServer (studyNo : Int) = Action {
    implicit request =>
      // GoogleDrive.downloadVideo("sim2dc@gmail.com", "0B00ugPsj4f4RRHA5ZnBud25tWlU")
      var link = "";
      var studyName= ""
      var studyLoc = ""
      DB.withConnection { implicit c =>

        val rowOption3 =
          SQL("select coalesce(study_location_on_server, '') as c, study_name from study where study_id={study_id};").on('study_id -> studyNo).apply().head

        link = rowOption3[String]("c");
        studyName = rowOption3[String]("study_name");

        println("link " + link)
        println("study name " + studyName)

        // if the study has no video folder craete a new one
        if(link == "") {
          studyLoc = ProductionSide.videosLocattion + studyNo + studyName
          val id: Int = SQL("update study set study_location_on_server = {code} where study_id = {id}").on('code -> studyLoc, 'id -> studyNo).executeUpdate()
        }
        else
          studyLoc = link
      }
     // AuxiliaryMethods.downlaodVideosFromStudy(studyNo, studyLoc);
      Global.startScanningVideos(studyNo, studyLoc);
      Ok("every thing is fine");

  }








}


