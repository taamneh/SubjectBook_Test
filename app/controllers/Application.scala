package controllers

//import Models.{DB, Person, ReadExcelScala}

import java.io.{InputStream, File, OutputStream}
import java.util.Date
import scala.collection.JavaConverters._
import Models._
import org.apache.poi.util.IOUtils
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
import play.api._
import anorm._
import play.api.libs.json.{JsArray, Json}
import play.api.db.DB
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Concurrent, Enumerator}
import play.api.mvc._
import play.Logger
import scala.collection.immutable.TreeMap

import scala.concurrent.{ExecutionContext, Future}
;




object Application extends Controller {


  val GOOGLE_DRIVE = 1;
  val LOCAL_SERVER = 2;
  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(Models.UserLogin.apply)(Models.UserLogin.unapply)
  )

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
   * This Function is called once the user select a study to show from the list of studies
   */

  def showStudy(studyNo: Int) = Action
  {
    implicit request =>
      var username: String = "";
      var parameterList: String= "";
      var study_name: String ="";
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

        val portrait = SQL("select  distinct(study_name) as study_name, coalesce(portrait_string,'') as portrait from study where study_id={study_id};").on('study_id -> studyNo).apply().head
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
      Logger.info("Show Subject Number: " + SubjectId + " For Study: " +studyNo);
      var sessionsPerSubjectNew : Map[String, List[(String, List[(Int, Int)])]]= Map();
      var intermidate: List[(String, List[(Int, Int)])]= List();
      var sessionsPerSubjectMenu : Map[String, List[( String)]]= Map();
      var signalTypes: List[Int] =List();
      var videoIdList:  Map[String, List[ String]]= Map()
      var sourceType: Int = 1;
      var studyName = "";
      DB.withConnection { implicit c =>
        val sessions  =
          SQL("select distinct(session_name), session_no from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} AND session_name not in( 'INFO', 'PM', 'PRF' ) order by session_name desc;")
            .on('study_no -> studyNo, 'subject_id -> SubjectId)

        val med = sessions().map(row =>
          (  row[String]("session_name"))
        ).toList
        var medSess = sessions().map(row =>
          (  row[Int]("session_no") -> row[String]("session_name"))
        ).toList

        val signals  =
          SQL("select signal_signal_type from  session where  session_name not in( 'INFO', 'PM', 'PRF')  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});")
            .on('study_no -> studyNo, 'sub_id -> SubjectId)
        val med2 = signals().map(row =>
          (  row[Int]("signal_signal_type"))
        ).toList
        signalTypes= med2;

        for(sess <- medSess) {
          val signals2 =
            SQL("select signal_seq, signal_signal_type from  session where  session_no={sess_no} AND session_name not in( 'INFO', 'PM', 'PRF')  AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no}) order by signal_signal_type;")
              .on('sess_no -> sess._1, 'study_no -> studyNo, 'sub_id -> SubjectId)
          val med3 = signals2().map(row =>
            (row[Int]("signal_seq") -> row[Int]("signal_signal_type"))
          ).toList
          val signalNum: List[(Int, Int)]= med3;

          intermidate = intermidate.::(sess._2, signalNum);
        }

        sessionsPerSubjectNew = sessionsPerSubjectNew + (SubjectId -> intermidate);
        for(sess <- med) {
          val videoIds =
            SQL("select signal_loc from session  where signal_signal_type = {video} AND session_name = {sess_id} AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});")
              .on('video -> SignalType.getVideoCode,'sess_id -> sess, 'study_no -> studyNo, 'sub_id -> SubjectId)
          val vList = videoIds().map(row =>
            (row[String]("signal_loc"))
          ).toList
          videoIdList = videoIdList + (sess -> vList);
        }

        // we send the study tybe just to decide wich player should we use
        val studyType  =
          SQL("select study_type from study where study_id={study_id};").on('study_id-> studyNo).apply().head
        sourceType = studyType[Int]("study_type");


        val stName  =
          SQL("select study_name from study where study_id={study_id};").on('study_id-> studyNo).apply().head
        studyName = stName[String]("study_name");

        //for menu sake

        val subjects  =
          SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
            .on('study_no -> studyNo)
        val subectList = subjects().map(row =>
          row[String]("subject_id")).toList
        subectList.sorted;


        for(sub <- subectList)
        {
          val sessions  =
            SQL("select distinct session_name  from subject, session where study_id ={study_no} AND session_name not in( 'INFO', 'PM') AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} order by session_name asc;")
              .on('study_no -> studyNo, 'subject_id -> sub)
              .on('study_no -> studyNo, 'subject_id -> sub)
          val med = sessions().map(row =>
            (  row[String]("session_name"))
          ).toList

          val tt : List[(String)] = med;
          sessionsPerSubjectMenu = sessionsPerSubjectMenu + (sub ->tt);
        }
      }

      val sortedMap = TreeMap(sessionsPerSubjectMenu.toSeq:_*)
      Ok(views.html.ShowSignals(SubjectId, sessionsPerSubjectNew,studyNo, username, signalTypes, videoIdList,sourceType, sortedMap, studyName))
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
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      Ok(views.html.ShowCreateStudy(username));
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
          BadRequest("The Study Has not been creted Correctly please check that data you entered!")
        },
        contact => {
          val bio: Biography = AuxiliaryMethods.BiographyCode(contact.bio);
          val psycho : Psychometric = AuxiliaryMethods.PsychometricCode(contact.psychometric);
          val physio : Physiology = AuxiliaryMethods.PhysiologyCode(contact.physiology);
          val obser: Observation = AuxiliaryMethods.ObservationCode(contact.observation);
          val studyToplogy: StudyTopology = new StudyTopology(bio,psycho,physio, obser,new Performance(), contact.numSubj, contact.numSess, contact.numRun);

          //val gDrive : GoogleDrive = new GoogleDrive();
          var report = " "

          // specify the source of the study
          if(contact.study_type == LOCAL_SERVER)
          {
            GoogleDrive.FindStudyLocalServer(contact.study_name,contact.url, username,contact.numSess, studyToplogy,contact.bio,contact.psychometric, contact.physiology, contact.observation);
          }
          else
          {
            println("Kareem 21/10" + contact.folder_id)
            report = GoogleDrive.FindStudy(contact.folder_id, contact.study_name, username, studyToplogy,contact.bio,contact.psychometric, contact.physiology, contact.observation,contact.portrait, contact.public);
            //println(query);
          }
          //Redirect(routes.Application.displayStudies());


          DB.withConnection { implicit c =>


            val rowOption  =
              SQL("select count(*) as c from credential  where userid ={un}")
                .on('un -> username).apply().head
            val studies  =
              SQL("select  distinct(study_name), study_owner, study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege );").on('un -> username)
            val med = studies().map(row =>
              row[String]("study_name") -> row[String]("study_owner") -> row[Int]("study_id")
            ).toList
            val ctr = rowOption[Long]("c")
            if (ctr >0 &&  report != null) {

              /*val id: Option[Long] =
                SQL("insert into privilege values ({s_id}, 1, {user})")
                  .on('s_id ->  ctr, 'user -> username).executeInsert()*/

              //Ok(views.html.displayStudies(username,med, report)).withSession("connected" -> username)//Ok(views.html.index(contact.username.toString))
              Ok(views.html.ShowStudies(username, med)).withSession("connected" -> username) //Ok(views.html.index(contact.username.toString))
            }
            else
              Ok("The study has not been created. Please make sure that the folder you chose follow the required format")
            //Ok(views.html.login(userForm))
          }

          //Ok(report)
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
          Ok(contact.study_id.toString());
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
      var file_location = "";
      var study_owner = "";
      var activityFile : String = null;
      var sourceType = 0;
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

        val rowOption2  =
          SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ={sess_name} AND run_no =1 And signal_seq= {signal_seq};").on('seq -> seq, 'sess_name-> task, 'signal_seq -> signal_sequence).apply().head
        file_location = rowOption2[String]("signal_loc");

        val rowOption3  =
          SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
        sourceType = rowOption3[Int]("study_type");

        val rowOption4  =
          SQL("select study_owner from study  where study_id ={study_id};").on('study_id-> studyId).apply().head
        study_owner = rowOption4[String]("study_owner");

        // to test if this signal has accompanied activity file....
        val activity  =
          SQL("select coalesce(count(signal_loc),0) as c from session where subject_seq={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_type={activity};").on('seq -> seq, 'sess_name-> task, 'activity -> SignalType.getActivityCode).apply().head
        var ctr = activity[Long]("c");



        if(ctr == 1) {
          val activity2 =
            SQL("select signal_loc from session where subject_seq={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_type={activity};").on('seq -> seq, 'sess_name-> task, 'activity -> SignalType.getActivityCode).apply().head
          activityFile = activity2[String]("signal_loc");
        }



      }
      //var js = GoogleDrive.DownloadSignal(username, file_location, sourceType, signal_type);
      var js = GoogleDrive.DownloadSignal(study_owner, file_location, sourceType, signal_type,activityFile );
      if(js == null)
        Ok("");
      else
        Ok(js.toJSONString)
  }
  def getInfo (task: String, subject: String, studyId: Int, signal_type: Int) = Action {
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
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");

        val rowOption4  =
          SQL("select study_owner from study  where study_id ={study_id};").on('study_id-> studyId).apply().head
        study_owner = rowOption4[String]("study_owner");

        val check  =
          SQL("select coalesce(count(session_name),0) as c from session where subject_seq={seq} AND session_name='INFO';").on('seq -> seq).apply().head
        var ctr = check[Long]("c");
        println(ctr);

        if(ctr == 1)
        {
          val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ='INFO' AND run_no =1 And signal_signal_type= {signal_type};").on('seq -> seq, 'sess_name-> task, 'signal_type -> signal_type).apply().head
          file_location = rowOption2[String]("signal_loc");

          val rowOption3  =
            SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
          sourceType = rowOption3[Int]("study_type");

          // get the bio code to decide which biography informaiton we need to show
          val rowOption4  =
            SQL(" select bio_code  from subject where subject_seq ={se};").on('se-> seq).apply().head
          var bio_code = rowOption4[Int]("bio_code");



          val bio: Biography = AuxiliaryMethods.BiographyCode(bio_code);

          s  = GoogleDrive.GetSubjectInfo(study_owner, file_location, sourceType, signal_type,bio);
          if(s == null) // in case the file was deleted by the user..
            Ok("{}");
          else
            Ok(Json.parse(s));
        }
        else
        {
          Ok(s);
        }
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
        println(ctr);

        if(ctr == 1)
        {
          val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ='PRF' AND run_no =1 And signal_signal_type= {signal_type};").on('seq -> seq, 'sess_name-> task, 'signal_type -> signal_type).apply().head
          file_location = rowOption2[String]("signal_loc");

          val rowOption3  =
            SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
          sourceType = rowOption3[Int]("study_type");



          s  = GoogleDrive.GetSubjectPRF(username, file_location, sourceType);
          var json = Json.parse(s);
          Ok(json);
        }
        else
        {
          Ok(s);
        }
      }

  }



  def getPsycho (task: String, subject: String, studyId: Int, signal_type: Int) = Action {
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
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");

        val rowOption4  =
          SQL("select study_owner from study  where study_id ={study_id};").on('study_id-> studyId).apply().head
        study_owner = rowOption4[String]("study_owner");

        val check  =
          SQL("select coalesce(count(session_name),0) as c from session where subject_seq={seq} AND session_name='PM';").on('seq -> seq).apply().head
        var ctr = check[Long]("c");
        println(ctr);

        if(ctr == 1)
        {
          val rowOption2  =
            SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ='PM' AND run_no =1 And signal_signal_type= {signal_type};").on('seq -> seq, 'sess_name-> task, 'signal_type -> signal_type).apply().head
          file_location = rowOption2[String]("signal_loc");

          val rowOption3  =
            SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
          sourceType = rowOption3[Int]("study_type");

          // get the bio code to decide which biography informaiton we need to show
          val rowOption4  =
            SQL(" select psycho  from subject where subject_seq ={se};").on('se-> seq).apply().head
          var psycho_code = rowOption4[Int]("psycho");

          val pyc: Psychometric = AuxiliaryMethods.PsychometricCode(psycho_code);


          s  = GoogleDrive.GetSubjectPm(study_owner, file_location, sourceType, signal_type,pyc);
          var json = Json.parse(s);
          Ok(json);
        }
        else
        {
          Ok(s);
        }
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



      //IOUtils.copy(input,out);
      //val zip = new ZipOutputStream(out);
      Result(
        header = ResponseHeader(200, Map(CONTENT_TYPE ->"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),
        body = dataContent
      )


    //Ok("Salah Taamneh");
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

        println(seq);

        //TODO remove the fixed number 1,2,3,4....


        val rowOption2  =
          SQL("select signal_loc ,subject_id, session_name from session, subject where session.subject_seq  = subject.subject_seq and session.subject_seq in ({seq}) AND signal_signal_type in (1,2,3,4,5,6,7,8) AND run_no =1 order by subject_id;").on('seq -> seq)
        locations = rowOption2().map(row => row[String]("signal_loc")).toList
        subjects = rowOption2().map(row => row[String]("subject_id")).toList
        sessions = rowOption2().map(row => row[String]("session_name")).toList
        //all = rowOption2().map(row => row[String]("signal_loc") -> row[String]("subject_id") -> row[String]("session_id")).toList
      }

      //val x: java.util.List[String] = ListBuffer(List("3", "3"): _*)

      val input :java.util.ArrayList[ZipFileEntry] =GoogleDrive.downloadFileToClientWholeStudy(username, locations.asJava, subjects.asJava, sessions.asJava);
      //java.util.ArrayList[Int]()
      //val excelFilesList: mutable.Buffer[String] = input.asScala;
      //val dataContent: Enumerator[Array[Byte]] = Enumerator.fromStream(input.get(0))

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
      Ok.stream(enumerator >>> Enumerator.eof).withHeaders(
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




  //*****************************************************************

  def ShowDriveDialog = Action {

    Ok(views.html.googlePicker());
  }



  val registerForm = Form(
    mapping(
      "fullName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "retypePassword" -> nonEmptyText
    )(Models.NewUser.apply)(Models.NewUser.unapply)
  )

  def Register = Action {
    //Ok(views.html.registration(registerForm))
    Ok(" ! ")
  }

  def submitRegistration = Action {
    implicit request =>
      registerForm.bindFromRequest.fold(
        formWithErrors => {
          //BadRequest(views.html.registration(formWithErrors))
          BadRequest(" ")
        },
        contact => {
          DB.withConnection { implicit c =>
            val id: Option[Long] =
              SQL("insert into user(fullname, username, password, email) values ({fn}, {un}, {pass}, {em})")
                .on('fn -> contact.fullName , 'un -> contact.email, 'pass -> contact.password, 'em -> contact.email).executeInsert()
            //Ok(views.html.login(userForm))
            Ok(" ")
          }

        }
      )

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
          println(contact.email + contact.role + contact.message)
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


  def editStudy(studyId: Int) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val subjects =
          SQL("select distinct(subject_id), DOB from subject where study_id ={study_no} order by subject_id;").on('study_no -> studyId)
        val subjectsList = subjects().map(row =>
          row[String]("subject_id") -> row[Date]("DOB")).toList
        Ok(views.html.editStudy("", username, studyId, subjectsList));
      }
  }

  def editSubject(study_no: Int, Subject_id :String) = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      DB.withConnection { implicit c =>
        val subjects =
          SQL("select session_no, session_name, singal_desc  from session, signal  where subject_seq = (select subject_seq from subject where subject_id ={sub_id} AND study_id ={study_no})AND signal_signal_type = signal_type;").on('sub_id -> Subject_id ,'study_no -> study_no)
        val subjectsList = subjects().map(row =>
          (row[Int]("session_no"),row[String]("session_name"),row[String]("singal_desc"))).toList
        Ok(views.html.EditSubject(Subject_id,username ,study_no,subjectsList ));
      }
  }


  val newSubject = Form(
    mapping(
      "subject_id" -> nonEmptyText,
      "study_id" -> number,
      "subject_DOB" -> date,
      "subject_gender" -> nonEmptyText,
      "STAI"-> number,
      "PAS"-> number,
      "NAS"-> number
    )(Subject.apply)(Subject.unapply)
  )
  def insertSubject = Action {
    implicit request =>
      newSubject.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("   hh")
        },
        contact => {
          DB.withConnection { implicit c =>
            val rowOption  =
              SQL("select max(subject_seq) as c from subject;").apply().head
            var ctr = rowOption[Long]("c");
            ctr = ctr+1;

            val id: Option[Long] =
              SQL("insert into subject values({seq},{subject_id},{study_id},null, null, SYSDATE ,10,10,10,0,0,0);")
                .on('seq -> ctr , 'subject_id -> contact.subject_id, 'study_id -> contact.study_id).executeInsert()
            Redirect(routes.Application.editStudy(contact.study_id));
          }
        }
      )
  }
  val newSession = Form(
    mapping(
      "subject_id" -> nonEmptyText,
      "study_id" -> number,
      "run_no" -> number,
      "session_name" -> nonEmptyText,
      "signal_type"-> number
    )(NewSession.apply)(NewSession.unapply)
  )
  def InsertSession = Action {
    implicit request =>
      newSession.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("hh")
        },
        contact => {
          DB.withConnection { implicit c =>
            val rowOption1  =
              SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> contact.subject_id, 'study_id-> contact.study_id).apply().head
            val seq = rowOption1[Long]("subject_seq");

            val rowOption2  =
              SQL("select coalesce(max(session_no),0) as c from session where subject_seq={seq};").on('seq -> seq).apply().head
            var ctr = rowOption2[Long]("c");
            ctr = ctr+1;
            println(ctr)

            val id: Option[Long] =
              SQL("insert into session values({seq},{sess_no},0 ,{sess_name}, 'sssssss',1);")
                .on('seq -> seq, 'sess_name -> contact.session_name, 'sess_no -> ctr).executeInsert()
            Ok("fdfdfsad");
          }

        }
      )

  }

  def uploadFile = Action(parse.multipartFormData) { implicit request =>
    newSession.bindFromRequest.fold(
      formWithErrors => {
        BadRequest("hh")
      },
      contact => {

        //val contactId = Contact.save(contact)
        //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
        DB.withConnection { implicit c =>
          val rowOption1  =
            SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> contact.subject_id, 'study_id-> contact.study_id).apply().head
          val seq = rowOption1[Long]("subject_seq");

          val rowOption2  =
            SQL("select coalesce(max(session_no),0) as c from session where subject_seq={seq};").on('seq -> seq).apply().head
          var ctr = rowOption2[Long]("c");
          ctr = ctr+1;
          println(ctr)
          val upload_loc = ""
          var video_loc = upload_loc;
          request.body.file("signal_loc").map { video =>
            val videoFilename = video.filename
            val contentType = video.contentType.get
            video_loc = video_loc + video.filename;
            //video.ref.moveTo(new File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\target\\web\\public\\main\\images" + video.filename))
            video.ref.moveTo(new File(video_loc));
          }.getOrElse {
            Redirect(routes.Application.Main())
          }
          val id: Option[Long] =
          //INSERT INTO session VALUES(1,1,1,'BL', 'C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\S001\\BaseLine Dexterity\\RI_S001-001.Q_EDA', 1);
            SQL("insert into session values({seq},{sess_no},{run_no} ,{sess_name},{loc},{signal_type});")
              .on('seq -> seq, 'sess_name -> contact.session_name, 'sess_no -> ctr,'run_no-> contact.run_no ,'loc -> video_loc, 'signal_type -> contact.signal_type).executeInsert()
          //Ok("File has been uploaded" +contact.session_name + " " + contact.signal_type +  " "  + contact.study_id  )
          Redirect(routes.Application.editSubject(contact.study_id, contact.subject_id));
        }
      }
    )
  }






  def video = Action {
    Ok.sendFile(
      content = Play.application.getFile("/target/web/public/main/images/v.avi"),
      inline = true
    )
  }


  def findImage(img_no : Int) = Action{

    val app = Play.application
    val file = Play.application.getFile("/target/web/public/main/images/" + img_no + ".jpg")
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


      //val v = videos.where(_.id === id).single
      //val file = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\target\\web\\public\\main\\images\\v.avi")
      //val file = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\target\\web\\public\\main\\images\\v.avi")
      import ExecutionContext.Implicits.global
      //val fileContent: Enumerator[Array[Byte]] = Enumerator.fromFile(file)

      //Logger.info("Here is the link " + GoogleDrive.GetVideoUrl(username, "0B2TDTGk9sqZLZGlGVEI2anYzNEU", sourceType, 8));


      var input: InputStream = GoogleDrive.GetVideoInputStream(username, "0B2TDTGk9sqZLNDRMbnNPMktwUHc", sourceType, 8);
      val fileContent: Enumerator[Array[Byte]] = Enumerator.fromStream(GoogleDrive.GetVideoInputStream(username, "0B2TDTGk9sqZLNDRMbnNPMktwUHc", sourceType, 8));
      Result(
        header = ResponseHeader(200, Map(
          CONTENT_LENGTH -> GoogleDrive.GetVideoSize(username, "0B2TDTGk9sqZLNDRMbnNPMktwUHc", sourceType, 8),
          CONTENT_RANGE -> s"bytes */${GoogleDrive.GetVideoSize(username, "0B2TDTGk9sqZLNDRMbnNPMktwUHc", sourceType, 8)}",
          ACCEPT_RANGES -> "bytes",
          CONTENT_TYPE -> "video/mp4",
          PRAGMA -> "public",
          CONTENT_TRANSFER_ENCODING -> "binary",
          CONTENT_DISPOSITION -> "attachment"
        )),
        body = fileContent
      )


  }

  def test () = Action {
    implicit request =>
      Ok("Taamneh");
  }









}

