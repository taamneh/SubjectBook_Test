package controllers

//import Models.{DB, Person, ReadExcelScala}

import java.io.{InputStream, File}
import java.util.Date

import Models._
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.drive.Drive
import play.api.Play.current
import play.api._
import anorm._
import play.api.libs.json.{JsArray, Json}
import play.api.db.DB
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.Enumerator
import play.api.libs.json._
import play.api.mvc._
import play.Logger

import scala.concurrent.{ExecutionContext, Future}
;


case class NewUser(fullName: String, email: String, password: String, retypePassword: String);
case class UserLogin(username: String, password: String);

object Application extends Controller {


  val GOOGLE_DRIVE = 1;
  val LOCAL_SERVER = 2;
  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserLogin.apply)(UserLogin.unapply)
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
            val rowOption  =
              SQL("select count(*) as c from credential  where userid ={un}")
                .on('un -> username).apply().head
            val studies  =
              SQL("select  distinct(study_name), study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege where username  = {un} );")
                .on('un -> username)
            val med = studies().map(row =>
              row[String]("study_name") -> row[Int]("study_id")
            ).toList
            val ctr = rowOption[Long]("c")
            if (ctr >0 ) 
              Ok(views.html.displayStudies(username,med)).withSession("connected" -> username)//Ok(views.html.index(contact.username.toString))
            else
              Ok(views.html.login(userForm))
          }
  }

  /**
   * This Method is called when the user hit My model from any window, and it will display all the studies that the user created or share with other people
   */
  def displayStudies() = Action
  {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
       username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
    DB.withConnection { implicit c =>
      val studies  =
        SQL("select  distinct(study_name), study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege where username  = {un} );")
          .on('un -> username)
      val med = studies().map(row =>
        row[String]("study_name") -> row[Int]("study_id")
      ).toList
    Ok(views.html.displayStudies(username,med))
    }
  }

  /**
   * This Function is called once the user select a study to show from the list of studies
   */

  def showStudy(studyNo: Int) = Action
  {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      var sessionsPerSubject : Map[String, List[( String, String)]]= Map();
      DB.withConnection { implicit c =>
        val subjects  =
          SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
            .on('study_no -> studyNo)
        val subectList = subjects().map(row =>
          row[String]("subject_id")).toList

        for(sub <- subectList)
        {
          val sessions  =
            SQL("select session_name, signal_loc from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} order by subject_id, session_name ;")
              .on('study_no -> 1, 'subject_id -> sub)
          val med = sessions().map(row =>
            (  row[String]("session_name") -> row[String]("signal_loc"))
          ).toList

          val tt : List[(String, String)] = med;
          sessionsPerSubject = sessionsPerSubject + (sub ->tt);
        }
      }
      Ok(views.html.studyModel(username, sessionsPerSubject, studyNo));
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
      var sessionsPerSubject : Map[String, List[ String]]= Map();
      var temp: List[Int] =List();
      DB.withConnection { implicit c =>
        val sessions  =
          SQL("select distinct(session_name) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} AND session_name not in( 'INFO', 'PM') order by session_name;")
            .on('study_no -> studyNo, 'subject_id -> SubjectId)
        val med = sessions().map(row =>
          (  row[String]("session_name"))
        ).toList

        val tt : List[String] = med;
        sessionsPerSubject = sessionsPerSubject + (SubjectId -> tt);

        val signals  =
          SQL("select distinct(signal_signal_type) from  session where signal_signal_type not in (9,10,11) AND subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});")
            .on('study_no -> studyNo, 'sub_id -> SubjectId)
        val med2 = signals().map(row =>
          (  row[Int]("signal_signal_type"))
        ).toList
        temp= med2;
      }
      Ok(views.html.subject(SubjectId, sessionsPerSubject,studyNo, username, temp));
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
      Ok(views.html.CreateStudy(username));
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
            report = GoogleDrive.FindStudy(contact.folder_id, contact.study_name, username, studyToplogy,contact.bio,contact.psychometric, contact.physiology, contact.observation);
          }
          //Redirect(routes.Application.displayStudies());
          Ok(report)
        }
      )
  }
  /**
   *  This is called when the user choose on of the tabs (i.e, session)
   */

  def GetSignal(task: String, subject: String, studyId: Int, signal_type: Int) = Action {
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

        val rowOption1  =
          SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
        val seq = rowOption1[Long]("subject_seq");

        val rowOption2  =
          SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ={sess_name} AND run_no =1 And signal_signal_type= {signal_type};").on('seq -> seq, 'sess_name-> task, 'signal_type -> signal_type).apply().head
        file_location = rowOption2[String]("signal_loc");

        val rowOption3  =
          SQL("select study_type from study where study_id={study_id};").on('study_id-> studyId).apply().head
        sourceType = rowOption3[Int]("study_type");
      }


      var js = GoogleDrive.DownloadSignal(username, file_location, sourceType, signal_type);



      //val js: Future[JsArray] = GoogleDrive.DownloadSignal(username, file_location, sourceType, signal_type);

     /* Ok(Json.obj(
   "cols" -> Json.arr(
     Json.obj(
       "id" -> "",
       "label" -> "Times",
       "patter" -> 31,
       "type" -> "number"
     ),
     Json.obj(
       "id" -> "",
       "label" -> "EDA Palm",
       "patter" -> 31,
       "type" -> "number"
     ),
     Json.obj(
       "id" -> "",
       "label" -> "EDA Ankle",
       "patter" -> 31,
       "type" -> "number"
     )
   ),
   "rows" -> js
 ))*/
 Ok(js)

  }

  def ShowDriveDialog = Action {

    Ok(views.html.googlePicker());
  }

  val registerForm = Form(
    mapping(
      "fullName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "retypePassword" -> nonEmptyText
    )(NewUser.apply)(NewUser.unapply)
  )

  def Register = Action {
    Ok(views.html.registration(registerForm))
  }

  def submitRegistration = Action {
    implicit request =>
      registerForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.registration(formWithErrors))
        },
        contact => {
          DB.withConnection { implicit c =>
            val id: Option[Long] =
              SQL("insert into user(fullname, username, password, email) values ({fn}, {un}, {pass}, {em})")
                .on('fn -> contact.fullName , 'un -> contact.email, 'pass -> contact.password, 'em -> contact.email).executeInsert()
            Ok(views.html.login(userForm))
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


  def file = Action {
    Ok.sendFile(
      content = Play.application.getFile("/target/web/public/main/images/c64.pdf"),
      inline = true
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



  def getInfo (task: String, subject: String, studyId: Int, signal_type: Int) = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var s:String = "{\"Dummy\": \"Dummy\"}";

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


      s  = GoogleDrive.GetSubjectInfo(username, file_location, sourceType, signal_type,bio);
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
      var sourceType = 0;
      var s:String = "{\"Dummy\": \"Dummy\"}";

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


          s  = GoogleDrive.GetSubjectPm(username, file_location, sourceType, signal_type,pyc);
          var json = Json.parse(s);
          Ok(json);
        }
        else
        {
          Ok(s);
        }
      }

  }

  def getVideo () = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var s:String = "{\"Dummy\": \"Dummy\"}";

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

  def getVideo2 () = Action {
    implicit request =>
      var file_location = "";
      var sourceType = 0;
      var s:String = "{\"Dummy\": \"Dummy\"}";

      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }

      GoogleDrive.GetVideoSize(username, "0B2TDTGk9sqZLNDRMbnNPMktwUHc", sourceType, 8)
      GoogleDrive.GetVideoUrl("0B2TDTGk9sqZLNDRMbnNPMktwUHc");
      Ok(views.html.testVideoFromDrive());
  }









}

