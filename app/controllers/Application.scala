package controllers

//import Models.{DB, Person, ReadExcelScala}

import java.io.File
import java.util.Date

import Models.{MyClass, GoogleDrive, ReadExcelScala, UserLogin}
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import play.api.Play.current
import play.api._
import anorm._
import play.api.db.DB
import play.api.data.Form
import play.api.data.Forms._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.iteratee.Enumerator
import play.api.libs.json._
import play.api.mvc._
import play.Logger;



case class UserLogin(username: String, password: String);
case class NewUser(fullName: String, email: String, password: String, retypePassword: String);
case class Sharing(email: String, role: Int, message: String,studyId: Int);
case class Subject(subject_id: String,  study_id: Int,subject_DOB: Date,subject_gender: String, STAI: Int, PAS: Int, NAS: Int);
case class Study(study_name: String, study_type: Int);
// this class is used for mapping between the html form and scala formatt
case class NewStudy(study_name: String,
                    study_type: Int=1,
                    folder_id: String,
                    url: String,
                    numSubj: Int,
                    numSess: Int,
                    numRun: Int,
                    bio: Int,
                    psychometric: Int,
                    physiology: Int,
                    observation: Int
                     );
case class Biography(var Age: Int=0,Height: Int=0, Weight: Int=0,Gender: Int=0, Ethnicity: Int=0,Bio_other: Int=0);
case class Psychometric(SAI: Int=0,PA: Int=0, NA: Int=0, STAI_other: Int=0, TA: Int=0, AB: Int=0,Trait_Other: Int=0);
case class Physiology(EDA: Int=0, Motion: Int=0, GSR_Finger: Int=0,Breathing_Belt: Int=0,Breathing_Thermal: Int=0,Heart_Rate: Int=0,Perspiration: Int=0)
case class Observation(Accelerometer: Int=0,Obser_other: Int=0,Video_Face: Int=0, Video_Room: Int=0,Video_Theater: Int=0,FACS: Int=0, Obser2d_other: Int=0)
case class Performance(Perfro_name: Int=0, Per_min: Int=0, Per_mix: Int=0 )

// this class is used to gather all the information about the study in one place
class StudyTopology(bio:Biography, psycho:Psychometric, physio:Physiology, oberv:Observation, perf:Performance, noOfSubj:Int,noOfSess: Int,noOfRn:Int )
{
  var biography: Biography =bio;
  var psychometric: Psychometric =psycho;
  var physiology: Physiology= physio;
  var observation: Observation= oberv;
  var performance: Performance= perf;
  var noOfSubjects: Int = noOfSubj;
  var noOfSession: Int =noOfSess;
  var noOfRun: Int=noOfRn;
}

case class NewSession(subject_id: String,study_id: Int, run_no: Int,session_name: String, signal_type:Int);
//case class Registration(user_registration_fullName: String, user_registration_email: String, user_registration_password: String, user_registration_retypePassword: String);
object Application extends Controller {


  val GOOGLE_DRIVE = 1;
  val LOCALSERVER = 2;
  val upload_loc = "C:\\\\Users\\\\staamneh\\\\Desktop\\\\CPL-Lab\\\\System Desgin\\\\first_play\\\\target\\\\web\\\\public\\\\main\\\\Uploaded\\\\";
  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserLogin.apply)(UserLogin.unapply)
  )

  def Login = Action {
    //Ok(views.html.login(userForm))
    var obj : GoogleDrive = new GoogleDrive();
    Redirect(obj.getAuthorizationUrl())
  }

  def authentication = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      /*userForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.login(formWithErrors))
        },
        contact => {*/

          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
          DB.withConnection { implicit c =>
            val rowOption  =
              /*SQL("select count(*) as c from USER where username ={un} AND password = {ps}")
                .on('un -> contact.username, 'ps -> contact.password).apply().head */
              SQL("select count(*) as c from credential  where userid ={un}")
                .on('un -> username).apply().head
            val studies  =
              SQL("select  distinct(study_name), study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege where username  = {un} );")
                .on('un -> username)
            val med = studies().map(row =>
              row[String]("study_name") -> row[Int]("study_id")
            ).toList
            //var str: String = "";
            //for (name <- med) str = str + name;
            val ctr = rowOption[Long]("c")
            if (ctr >0 ) Ok(views.html.displayStudies(username,med)).withSession("connected" -> username)//Ok(views.html.index(contact.username.toString))
            else Ok(views.html.login(userForm))
          }

        //}
      //)
  }

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
      //var str: String = "";
      //for (name <- med) str = str + name;
    Ok(views.html.displayStudies(username,med))
    }
  }

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
        /*val subjects  =
          SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
            .on('study_no -> studyNo)
        val subectList = subjects().map(row =>
          row[String]("subject_id")).toList

        for(sub <- subectList)
        {*/
        val sessions  =
          SQL("select distinct(session_name) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} order by session_name ;")
            .on('study_no -> studyNo, 'subject_id -> SubjectId)
        val med = sessions().map(row =>
          (  row[String]("session_name"))
        ).toList

        var tt : List[String] = med;
        sessionsPerSubject = sessionsPerSubject + (SubjectId -> tt);

        val signals  =
          SQL("select distinct(signal_signal_type) from  session where subject_seq = (select subject_seq from subject where subject_id ={sub_id}  AND study_id ={study_no});")
            .on('study_no -> studyNo, 'sub_id -> SubjectId)
        val med2 = signals().map(row =>
          (  row[Int]("signal_signal_type"))
        ).toList
        temp= med2;
      }
      //}
      Ok(views.html.subject(SubjectId, sessionsPerSubject,studyNo, username, temp));
      //Ok(views.html.subject())
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

          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
          DB.withConnection { implicit c =>
            val id: Option[Long] =
              SQL("insert into user(fullname, username, password, email) values ({fn}, {un}, {pass}, {em})")
                .on('fn -> contact.fullName , 'un -> contact.email, 'pass -> contact.password, 'em -> contact.email).executeInsert()
            //var str: String = "";
            //for (name <- med) str = str + name;

            Ok(views.html.login(userForm))
          }

        }
      )

  }


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

        var tt : List[(String, String)] = med;
        sessionsPerSubject = sessionsPerSubject + (sub ->tt);
      }
    }
    Ok(views.html.studyModel(username, sessionsPerSubject, studyNo));
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
          //BadRequest(views.html.editStudy("SomeThing Wrong Happened!",username));
          BadRequest("SomeThing Wrong Happened!");
        },
        contact => {
          println(contact.email + contact.role + contact.message)
          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
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

  def logout = Action {
    Redirect(routes.Application.Login()).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

   /*
     It will give the user the opporunity to upload more files under any subfolder
    */

  def sendRequest(StudyName: String) =TODO


  def index = Action {
    Ok(views.html.index("Stress Book"))
  }


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
          //SQL("select distinct(subject_id), DOB from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
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
        //Ok(views.html.editStudy("", username, studyId, subjectsList));
        Ok(views.html.EditSubject(Subject_id,username ,study_no,subjectsList ));
      }
  }


  val newStudy = Form(
    mapping(
      "study_name" -> nonEmptyText,
      "study_type" -> number
    )(Study.apply)(Study.unapply)
  )
  def insertStudy = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
      newStudy.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("The study has not been created correctly")
        },
        contact => {


          var gDrive : GoogleDrive = new GoogleDrive();

          // specify the source of the study
          /*if(contact.study_type == LOCALSERVER)
          {
            GoogleDrive.FindStudyLocalServer(contact.study_name,"", username,0);
            println("The user select Local server")
          }
          else
          {
            gDrive.FindStudy(" ", contact.study_name, username);
          }*/
          Redirect(routes.Application.displayStudies());
        }
      )



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

          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
          DB.withConnection { implicit c =>
            val rowOption  =
              SQL("select max(subject_seq) as c from subject;").apply().head
              var ctr = rowOption[Long]("c");
              ctr = ctr+1;

            val id: Option[Long] =
              SQL("insert into subject values({seq},{subject_id},{study_id},null, null, SYSDATE ,10,10,10);")
                .on('seq -> ctr , 'subject_id -> contact.subject_id, 'study_id -> contact.study_id).executeInsert()
            //var str: String = "";
            //for (name <- med) str = str + name;
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

            val id: Option[Long] =
              //INSERT INTO session VALUES(1,1,1,'BL', 'C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\S001\\BaseLine Dexterity\\RI_S001-001.Q_EDA', 1);
              SQL("insert into session values({seq},{sess_no},0 ,{sess_name}, 'sssssss',1);")
                .on('seq -> seq, 'sess_name -> contact.session_name, 'sess_no -> ctr).executeInsert()
            //var str: String = "";
            //for (name <- med) str = str + name;
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

            var video_loc = upload_loc;
            request.body.file("signal_loc").map { video =>
              val videoFilename = video.filename
              val contentType = video.contentType.get
              video_loc = video_loc + video.filename;
              //video.ref.moveTo(new File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\target\\web\\public\\main\\images" + video.filename))
              video.ref.moveTo(new File(video_loc));
            }.getOrElse {
              Redirect(routes.Application.index())
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

    /*val app = Play.application
    var file = Play.application.getFile("/target/web/public/main/images/v.mp4")
    val source = scala.io.Source.fromFile(file)(scala.io.Codec.ISO8859)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    //Ok(byteArray).as("image/jpeg")
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = Enumerator(byteArray)
    ).as("video/mp4")
    */
  }


  def findImage(img_no : Int) = Action{

    val app = Play.application
    var file = Play.application.getFile("/target/web/public/main/images/" + img_no + ".jpg")
    val source = scala.io.Source.fromFile(file)(scala.io.Codec.ISO8859)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    //Ok(byteArray).as("image/jpeg")
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = Enumerator(byteArray)
    )


  }
  //def test(fileName: String) = Action {
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

    /*var newjs: ReadExcelScala = new ReadExcelScala(task, subject,file_location );
    var js = newjs.fromExcel;*/

   println("File Location: " + file_location);

    var gDrive : GoogleDrive = new GoogleDrive();
    var js = gDrive.DownloadSignal(username, file_location, sourceType);

      //val dataContent: Enumerator[Array[Byte]] = Enumerator.

    Ok(Json.obj(
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
    ))
    //Ok(file_location);
  }


  def ReciveOuthData(state: String, code :String) = Action {

       var gDrive : GoogleDrive = new GoogleDrive(code);
       Redirect(routes.Application.authentication()).withSession(
      "connected" -> gDrive.getUserEmail());
      //Ok(views.html.googlePicker());
  }

  def ShowDriveDialog = Action {

    Ok(views.html.googlePicker());
  }

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
  def test = Action {
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
      var bio: Biography = BiographyCode(contact.bio);
      var psycho : Psychometric = PsychometricCode(contact.psychometric);
      var physio : Physiology = PhysiologyCode(contact.physiology);
      var obser: Observation = ObservationCode(contact.observation);
      var studyToplogy: StudyTopology = new StudyTopology(bio,psycho,physio, obser,new Performance(), contact.numSubj, contact.numSess, contact.numRun);

      //GenerateStudyNoGD(contact.study_name, username);
      var gDrive : GoogleDrive = new GoogleDrive();
      var report = " "

      // specify the source of the study
      if(contact.study_type == LOCALSERVER)
      {
        GoogleDrive.FindStudyLocalServer(contact.study_name,contact.url, username,0);
      }
      else
      {
        println("Kareem 21/10" + contact.folder_id)
        report = gDrive.FindStudy(contact.folder_id, contact.study_name, username, studyToplogy);
      }
      //Redirect(routes.Application.displayStudies());
      Ok(report)

     // Ok(contact.observation.toString());

    }
    )
  }

  /*
class StudyTopology(bio:Biography, psycho:Psychometric, physio:Physiology, oberv:Observation, perf:Performance)
   */

// this function take code that specify what the user has selected and translate to scala format
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
              println("Age has been selected");
            case 2 => height =1;
              println("Height has been selected");
            case 3 =>weight =1
              println("weight has been selected");
            case 4 =>gender =1
              println("gender has been selected");
            case 5 => ethnicity=1
              println("ethnicity has been selected");
            case 6 =>bio_other=1
              println("bio_other has been selected");
          }
        }
       count+=1;
        value = value/10;
      }
      var bio: Biography = new Biography(age, height, weight, gender, ethnicity, bio_other);;
      bio;
    }

 def PsychometricCode(code: Int) : Psychometric =
    {
      var SAI: Int=0; var PA: Int=0; var NA: Int=0; var STAI_other: Int=0; var TA: Int=0; var AB: Int=0; var Trait_Other: Int=0;
      var value: Int = code;
      var count: Int =1 ;
      while(value > 0 )
      {
        var option= value %10;
        if(option == 1)
        {
          count match {
            case 1 => SAI =1;
              println("SAI has been selected");
            case 2 => PA =1;
              println("PA has been selected");
            case 3 =>NA =1
              println("NA has been selected");
            case 4 =>STAI_other =1
              println("STAI_other has been selected");
            case 5 => TA=1
              println("TA has been selected");
            case 6 =>AB=1
              println("AB has been selected");
            case 7 =>Trait_Other=1
              println("Trait_Other has been selected");
          }
        }
        count+=1;
        value = value/10;
      }
      var psycho = new Psychometric(SAI, PA, NA, STAI_other, TA, AB, Trait_Other);
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



  def CreateStudy = Action {
    implicit request =>
      var username: String = "";
      request.session.get("connected").map { user =>
        username = user;
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
       Ok(views.html.CreateStudy(username));
  }

  //////  TO BE CALLED FROM GOOGLE DRIVE   //////
  def storeCredentials(userID: String, accessToken: String, refreshToken: String): Unit= {

    DB.withConnection { implicit c =>
      val rowOption = SQL("select count(*) as c from credential where userId={userID};").on('userID -> userID).apply().head
      var ctr = rowOption[Long]("c");

      if (ctr == 0) {
      val id: Option[Long] =
        SQL("insert into credential values({userid},{access_token},{refresh_token});")
          .on('userid -> userID, 'access_token -> accessToken, 'refresh_token -> refreshToken).executeInsert()
    }
    }
  }

  def getStoredCredentials(userID: String): String= {
    var access = "";
    var refresh= "";
    DB.withConnection { implicit c =>
      val id=
        SQL("select access_token, refresh_token from credential where userId={sub_id};")
          .on('sub_id -> userID).apply().head;
       access = id[String]("access_token");
       refresh = id[String]("refresh_token");


    }
    /*val cred: Credential = new Credential(access);
    cred.setAccessToken(access);
    cred.setRefreshToken(refresh);*/
    return access;
  }


  def GenerateStudyNoGD(StudyName: String, username: String, study_type : Int): Int = {

    DB.withConnection { implicit c =>
      Logger.debug("Study: " + StudyName + "has been created" );
      val rowOption = SQL("select coalesce(max(study_id),0) as c from study;").apply().head
      var ctr = rowOption[Long]("c");
      ctr = ctr + 1;
      //println("Yes   :" + ctr)
      val id: Option[Long] =
        SQL("insert into study values({study_id},{study_name},SYSDATE, {study_type}, {user});")
          .on('study_id -> ctr , 'study_name -> StudyName, 'study_type -> study_type, 'user -> username).executeInsert()
      ctr.toInt;
    }
  }
  def InsertSubjectGD(subject: String,studyId :Int ): Unit = {
    //println("Salah Taa   :" + subject)
    DB.withConnection { implicit c =>
      val rowOption  =
        SQL("select coalesce(max(subject_seq),0) as c from subject;").apply().head
      var ctr = rowOption[Long]("c");
      ctr = ctr+1;
      //println("Salah CTR   :" + ctr)
      val id: Option[Long] =
        SQL("insert into subject values({seq},{subject_id},{study_id},null, null, SYSDATE ,10,10,10);")
          .on('seq -> ctr , 'subject_id -> subject, 'study_id -> studyId).executeInsert()

    }

  }


  def InsertSessionGD(subject: String, studyId: Int, session_no : Int, session_name: String, signal_type: Int, url: String): Unit= {

          DB.withConnection { implicit c =>
            val rowOption1  =
              SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
            val seq = rowOption1[Long]("subject_seq");

            val rowOption2  =
              SQL("select coalesce(max(session_no),0) as c from session where subject_seq={seq};").on('seq -> seq).apply().head
            var ctr = rowOption2[Long]("c");
            ctr = ctr+1;
            //println(ctr)
            println("Fucckkkkkkkkkkkkkkkk-------------");
            val id: Option[Long] =
            //INSERT INTO session VALUES(1,1,1,'BL', 'C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\S001\\BaseLine Dexterity\\RI_S001-001.Q_EDA', 1);
              SQL("insert into session values({seq},{sess_no},1 ,{sess_name}, {url},{signal_type});")
                .on('seq -> seq, 'sess_name -> session_name, 'sess_no -> session_no,'url -> url, 'signal_type -> signal_type).executeInsert()


          }




  }




  //TODO fucntion that return the single as josn, the input will be stuy id, subject id, session id, and signal id
  //this mehtod will be clled when a suer clikc on the single, and then a jquery file or "coffescript" that is assinged to taht link
  // will be executed and called this link using get and the name of the method
}

