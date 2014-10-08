package controllers

//import Models.{DB, Person, ReadExcelScala}

import java.io.File
import java.util.Date

import Models.{ReadExcelScala}
import Models.UserLogin
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


case class UserLogin(username: String, password: String);
case class NewUser(fullName: String, email: String, password: String, retypePassword: String);
case class Sharing(email: String, role: Int, message: String,studyId: Int);
case class Subject(subject_id: String,  study_id: Int,subject_DOB: Date,subject_gender: String, STAI: Int, PAS: Int, NAS: Int);
case class Study(study_name: String, study_type: Int);
case class NewSession(subject_id: String,study_id: Int, run_no: Int,session_name: String, signal_type:Int);
//case class Registration(user_registration_fullName: String, user_registration_email: String, user_registration_password: String, user_registration_retypePassword: String);
object Application extends Controller {
  val upload_loc = "C:\\\\Users\\\\staamneh\\\\Desktop\\\\CPL-Lab\\\\System Desgin\\\\first_play\\\\target\\\\web\\\\public\\\\main\\\\Uploaded\\\\";
  val userForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserLogin.apply)(UserLogin.unapply)
  )

  def Login = Action {
    //Ok(views.html.user(userForm))
    Ok(views.html.login(userForm))
  }

  def authentication = Action {
    implicit request =>
      userForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.login(formWithErrors))
        },
        contact => {

          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
          DB.withConnection { implicit c =>
            val rowOption  =
              SQL("select count(*) as c from USER where username ={un} AND password = {ps}")
                .on('un -> contact.username, 'ps -> contact.password).apply().head
            val studies  =
              SQL("select  distinct(study_name), study_id as c from study where study_owner ={un} OR study_id in (select study_id from privilege where username  = {un} );")
                .on('un -> contact.username)
            val med = studies().map(row =>
              row[String]("study_name") -> row[Int]("study_id")
            ).toList
            //var str: String = "";
            //for (name <- med) str = str + name;
            val ctr = rowOption[Long]("c")
            if (ctr >0 ) Ok(views.html.displayStudies(contact.username,med)).withSession(
              "connected" -> contact.username)//Ok(views.html.index(contact.username.toString))
            else Ok(views.html.login(userForm))
          }

        }
      )
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
      var sessionsPerSubject : Map[String, List[( String, String)]]= Map();
      DB.withConnection { implicit c =>
        /*val subjects  =
          SQL("select distinct(subject_id) from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq order by subject_id;")
            .on('study_no -> studyNo)
        val subectList = subjects().map(row =>
          row[String]("subject_id")).toList

        for(sub <- subectList)
        {*/
        val sessions  =
          SQL("select session_name, signal_loc from subject, session where study_id ={study_no} AND subject.subject_seq = session.subject_seq AND subject_id ={subject_id} order by subject_id, session_name ;")
            .on('study_no -> studyNo, 'subject_id -> SubjectId)
        val med = sessions().map(row =>
          (  row[String]("session_name") -> row[String]("signal_loc"))
        ).toList

        var tt : List[(String, String)] = med;
        sessionsPerSubject = sessionsPerSubject + (SubjectId -> tt);
      }
      //}
      Ok(views.html.subject(SubjectId, sessionsPerSubject,studyNo, username));
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
          BadRequest("   hh")
        },
        contact => {
          DB.withConnection { implicit c =>
            val rowOption  =
              SQL("select max(study_id) as c from study;").apply().head
            var ctr = rowOption[Long]("c");
            ctr = ctr+1;

            val id: Option[Long] =
              SQL("insert into study values({study_id},{study_name},SYSDATE, {study_type}, {user});")
                .on('study_id -> ctr , 'study_name -> contact.study_name, 'study_type -> contact.study_type, 'user -> username).executeInsert()
            Redirect(routes.Application.displayStudies());

          }

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


  /*val personFrom : Form[Person] = Form {
   mapping(
   "name" -> text
   )(Person.apply)(Person.unapply)
  }*/
  /*def addPerson = Action { implicit request =>
    val person = personFrom.bindFromRequest.get
    DB.save(person)
    Redirect(routes.Application.index())
  }*/
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

  /*def getPersons = Action {
    val persons = DB.query[Person].fetch
    Ok(Json.toJson(persons))
  }*/
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
  def test(task: String, subject: String, studyId: Int) = Action {
    var file_location = "";

    DB.withConnection { implicit c =>

      val rowOption1  =
        SQL("select subject_seq from subject where subject_id={sub_id} AND study_id={study_id};").on('sub_id -> subject, 'study_id-> studyId).apply().head
      val seq = rowOption1[Long]("subject_seq");

      val rowOption2  =
        SQL("select signal_loc from session  where subject_seq ={seq} AND session_name ={sess_name} AND run_no =1;").on('seq -> seq, 'sess_name-> task).apply().head
      file_location = rowOption2[String]("signal_loc");



    }

    var newjs: ReadExcelScala = new ReadExcelScala(task, subject,file_location );
    var js = newjs.fromExcel;

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

  def displayFortest = Action {

       Ok("Test");

  }



  //TODO fucntion that return the single as josn, the input will be stuy id, subject id, session id, and signal id
  //this mehtod will be clled when a suer clikc on the single, and then a jquery file or "coffescript" that is assinged to taht link
  // will be executed and called this link using get and the name of the method
}

