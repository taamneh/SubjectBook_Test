package controllers

import Models.{Biography, Psychometric, Physiology}
import anorm._
import play.Logger
import play.api.db.DB
import play.api.mvc.Controller
import play.api.Play.current
import play.Logger

/**
 * Created by staamneh on 10/27/2014.
 * These methods are designed to be called from class GoogleDrive to make changes to database once creating a new study, the reason for  have in scala
 * is because scala has anorm which make it easer to deal with DB
 */

case class AccessRefreshString (Access: String, refresh: String)
object DataBaseOperations extends Controller{


  def storeCredentials(userID: String, accessToken: String, refreshToken: String): Unit= {

    DB.withConnection { implicit c =>
      val rowOption = SQL("select count(*) as c from credential where userId={userID};").on('userID -> userID).apply().head
      var ctr = rowOption[Long]("c");

      if (ctr == 0) {
        val id: Option[Long] =
          SQL("insert into credential values({userid},{access_token},{refresh_token});")
            .on('userid -> userID, 'access_token -> accessToken, 'refresh_token -> refreshToken).executeInsert()
      }
      else
      {
        val id: Int =
          SQL("update credential set access_token = {access},refresh_token = {refresh} WHERE userId={user};")
            .on( 'access -> accessToken, 'refresh -> refreshToken, 'user -> userID).executeUpdate()
        Logger.info("access and refresh token has been updated in the database for: " +  userID );
        Logger.info(id.toString);
      }
    }
  }

  def getStoredCredentials(userID: String): AccessRefreshString= {
    var access = "";
    var refresh= "";
    DB.withConnection { implicit c =>
      val id=
        SQL("select access_token, refresh_token from credential where userId={sub_id};")
          .on('sub_id -> userID).apply().head;
      access = id[String]("access_token");
      refresh = id[String]("refresh_token");


    }
    var cred = new AccessRefreshString(access, refresh);
    /*val cred: Credential = new Credential(access);
    cred.setAccessToken(access);
    cred.setRefreshToken(refresh);*/
    return cred;
  }

  def GenerateStudyNoGD(StudyName: String, username: String, study_type : Int): Int = {

    DB.withConnection { implicit c =>
      Logger.debug("Study: " + StudyName + "has been created" );
      val rowOption = SQL("select coalesce(max(study_id),0) as c from study;").apply().head
      var ctr = rowOption[Long]("c");
      ctr = ctr + 1;
      Logger.info("Study:" + StudyName + "has been created");
      val id: Option[Long] =
        SQL("insert into study values({study_id},{study_name},SYSDATE, {study_type}, {user});")
          .on('study_id -> ctr , 'study_name -> StudyName, 'study_type -> study_type, 'user -> username).executeInsert()
      ctr.toInt;
    }
  }

  def InsertSubjectGD(subject: String,studyId :Int,bio_code:Int, psycho:Int,physio: Int): Unit = {
    //println("Salah Taa   :" + subject)
    DB.withConnection { implicit c =>
      val rowOption  =
        SQL("select coalesce(max(subject_seq),0) as c from subject;").apply().head
      var ctr = rowOption[Long]("c");
      ctr = ctr+1;
      //println("Salah CTR   :" + ctr)
      val id: Option[Long] =
        SQL("insert into subject values({seq},{subject_id},{study_id},null, null, SYSDATE ,10,10,10,{a}, {b}, {c});")
          .on('seq -> ctr , 'subject_id -> subject, 'study_id -> studyId, 'a->bio_code ,'b ->psycho ,'c ->physio ).executeInsert()
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
      val id: Option[Long] =
      //INSERT INTO session VALUES(1,1,1,'BL', 'C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\S001\\BaseLine Dexterity\\RI_S001-001.Q_EDA', 1);
        SQL("insert into session values({seq},{sess_no},1 ,{sess_name}, {url},{signal_type});")
          .on('seq -> seq, 'sess_name -> session_name, 'sess_no -> session_no,'url -> url, 'signal_type -> signal_type).executeInsert()
    }
  }

}
