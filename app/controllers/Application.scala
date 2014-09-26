package controllers

//import Models.{DB, Person, ReadExcelScala}
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
//case class Registration(user_registration_fullName: String, user_registration_email: String, user_registration_password: String, user_registration_retypePassword: String);
object Application extends Controller {

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
      Ok(views.html.subject(username, sessionsPerSubject,studyNo));
      //Ok(views.html.subject())
  }

  def displayFortest = Action {
    Ok("Tests")
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
          BadRequest(views.html.editStudy("SomeThing Wrong Happened!",username));
        },
        contact => {
          println(contact.email + contact.role + contact.message)
          //val contactId = Contact.save(contact)
          //Redirect(routes.Application.showContact(contactId)).flashing("success" -> "Contact saved!")
          DB.withConnection { implicit c =>
            val id: Option[Long] =
              SQL("insert into privilege(username,study_id, permission_type) values ({un}, {sid}, {per})")
                .on('un -> contact.email , 'sid -> contact.studyId , 'per -> contact.role).executeInsert()
            //var str: String = "";
            //for (name <- med) str = str + name;

            Ok(views.html.editStudy("Thank you, your study has been shared!",username,contact.studyId));
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
    Ok(views.html.editStudy("",username, studyId));

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
  def test(task: String, subject: String) = Action {
    val temp = """{
      "cols": [
      {"id":"","label":"Time","pattern":"","type":"number"},
      {"id":"","label":"Signal","pattern":"","type":"number"}
      ],
      "rows": [
      {"c":[{"v":1,"f":null},{"v":90,"f":null}]},
      {"c":[{"v":2,"f":null},{"v":106,"f":null}]},
      {"c":[{"v":3,"f":null},{"v":160,"f":null}]},
      {"c":[{"v":4,"f":null},{"v":180,"f":null}]},
      {"c":[{"v":5,"f":null},{"v":300,"f":null}]},
      {"c":[{"v":6,"f":null},{"v":90,"f":null}]},
      {"c":[{"v":7,"f":null},{"v":106,"f":null}]},
      {"c":[{"v":8,"f":null},{"v":160,"f":null}]},
      {"c":[{"v":9,"f":null},{"v":180,"f":null}]},
      {"c":[{"v":10,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":250,"f":null},{"v":300,"f":null}]}
    ]
    }
   """
    //Ok(Json.parse(temp))

   // var js = Json.arr();
    var newjs: ReadExcelScala = new ReadExcelScala(task, subject);
    var js = newjs.fromExcel;

    /*DB.withConnection { implicit c =>
      val id: Option[Long] =
        SQL("insert into USER(username, password) values ({name}, {country})")
          .on('name -> "kareeem", 'country -> "Taamneh").executeInsert()
    }*/

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
  }



  //TODO fucntion that return the single as josn, the input will be stuy id, subject id, session id, and signal id
  //this mehtod will be clled when a suer clikc on the single, and then a jquery file or "coffescript" that is assinged to taht link
  // will be executed and called this link using get and the name of the method
}