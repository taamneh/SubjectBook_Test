package controllers

import Models.{Observation, Physiology, Psychometric, Biography}
import play.api.mvc.Controller

/**
 * Created by staamneh on 10/27/2014.
 */
object AuxiliaryMethods extends Controller {

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

  // this function will accept code and bio and it will check if the bio item has been selected or not


  def PsychometricCode(code: Int) : Psychometric =
  {
    //var SAI: Int=0; var PA: Int=0; var NA: Int=0; var STAI_other: Int=0; var TA: Int=0; var AB: Int=0; var Trait_Other: Int=0;
    var SAI: Int=0; var TAI: Int=0; var PA: Int=0; var NA: Int=0; var Other: Int=0;
    var value: Int = code;
    var count: Int =1 ;
    while(value > 0 )
    {
      var option= value %10;
      if(option == 1)
      {
        count match {
          case 1 => SAI = 1;
            println("SAI has been selected");
          case 2 => TAI = 1;
            println("TAI has been selected");
          case 3 => PA = 1
            println("PA has been selected");
          case 4 => NA = 1
            println("NA has been selected");
          case 5 => Other = 1
            println("Other has been selected");
        }
      }
      count+=1;
      value = value/10;
    }
    var psycho = new Psychometric(SAI,TAI, PA, NA, Other);
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

}
