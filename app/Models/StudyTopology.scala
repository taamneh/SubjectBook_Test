package Models

import java.util.Date

/**
 * Created by staamneh on 10/27/2014.
 */
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
                    public: Int,
                    portrait: Int,
                    bio: Int,
                    psychometric: Int,
                    physiology: Int,
                    observation: Int
                     );
case class Biography(var Age: Int=0,Height: Int=0, Weight: Int=0,Gender: Int=0, Ethnicity: Int=0,Bio_other: Int=0);
case class Psychometric(SAI: Int=0,TAI: Int=0, PA: Int=0, NA: Int=0,  Other: Int=0);
case class Physiology(EDA: Int=0, Motion: Int=0, GSR_Finger: Int=0,Breathing_Belt: Int=0,Breathing_Thermal: Int=0,Heart_Rate: Int=0,Perspiration: Int=0)
case class Observation(Accelerometer: Int=0,Obser_other: Int=0,Video_Face: Int=0, Video_Room: Int=0,Video_Theater: Int=0,FACS: Int=0, Obser2d_other: Int=0)
case class Performance(Perfro_name: Int=0, Per_min: Int=0, Per_mix: Int=0 )
case class NewUser(fullName: String, email: String, password: String, retypePassword: String);
case class UserLogin(username: String, password: String);
case class deleteStudyData (study_id: Int);

// this class is used to gather all the information about the study in one place


case class NewSession(subject_id: String,study_id: Int, run_no: Int,session_name: String, signal_type:Int);
//case class Registration(user_registration_fullName: String, user_registration_email: String, user_registration_password: String, user_registration_retypePassword: String);
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
