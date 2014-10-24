package Models

import java.io.{InputStream, IOException, FileNotFoundException, FileInputStream}
import java.util
import akka.actor._
import scala.concurrent.Future
import akka.pattern.ask
import scala.concurrent.duration._
import akka.actor.Props
import akka.util.Timeout
import org.apache.poi.ss.usermodel.{Cell, Row}
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import play.api.libs.json.{JsArray, Json}
import play.libs.Akka

import scala.concurrent.Await

/**
 * Created by staamneh on 9/8/2014.
 */
class ReadExcelScala(taskP: String , subjectP : String, loc: String) {
  var task : String = taskP;
  var subject : String = subjectP;
  var file_location: String = loc;

  var excelFile = "RI_" + subject + "-001.Q_EDA";

  def fromExcelInputParallel(input: InputStream) : JsArray = {

    /*val workbook: XSSFWorkbook = new XSSFWorkbook(input)
    val sheet: XSSFSheet = workbook.getSheetAt(0)*/




    val workbook: XSSFWorkbook = new XSSFWorkbook(input)
    val sheet: XSSFSheet = workbook.getSheetAt(0);
    val length = sheet.getLastRowNum;
    println("Length:    " +length)
    val num1 : Int = length/8 + 9;
    var num2: Int = 0;
    if(length%4 != 0)
      num2 = length - 3*num1;
    else
      num2= num1

    val myActor = Akka.system.actorOf(Props(new SplittedExcel(sheet, 9, num1)));
    val myActor2 = Akka.system.actorOf(Props(new SplittedExcel(sheet, num1+1, 2*num1)));
    val myActor3 = Akka.system.actorOf(Props(new SplittedExcel(sheet,2*num1+1 , 3*num1)));
    val myActor4 = Akka.system.actorOf(Props(new SplittedExcel(sheet, 3*num1+1, 4*num1)));
    val myActor5 = Akka.system.actorOf(Props(new SplittedExcel(sheet, 4*num1+1, 5*num1)));
    val myActor6 = Akka.system.actorOf(Props(new SplittedExcel(sheet, 5*num1+1, 6*num1)));
    val myActor7 = Akka.system.actorOf(Props(new SplittedExcel(sheet,6*num1+1 , 7*num1)));
    val myActor8 = Akka.system.actorOf(Props(new SplittedExcel(sheet, 7*num1+1, length)));
    //myActor ! "ss";
    implicit val timeout = Timeout (200 seconds)
    val future = myActor ? "START"
    val future2 = myActor2 ? "START"
    val future3 = myActor3 ? "START"
    val future4 = myActor4 ? "START"
    val future5 = myActor5 ? "START"
    val future6 = myActor6 ? "START"
    val future7 = myActor7 ? "START"
    val future8 = myActor8 ? "START"
    val result = Await.result(future, timeout.duration).asInstanceOf[JsArray]
    val result2 = Await.result(future2, timeout.duration).asInstanceOf[JsArray]
    val result3 = Await.result(future3, timeout.duration).asInstanceOf[JsArray]
    val result4 = Await.result(future4, timeout.duration).asInstanceOf[JsArray]
    val result5 = Await.result(future5, timeout.duration).asInstanceOf[JsArray]
    val result6 = Await.result(future6, timeout.duration).asInstanceOf[JsArray]
    val result7 = Await.result(future7, timeout.duration).asInstanceOf[JsArray]
    val result8 = Await.result(future8, timeout.duration).asInstanceOf[JsArray]
    /* val future3 = myActor3 ? "START"
   val result3 = Await.result(future3, timeout.duration).asInstanceOf[JsArray]

   val future4 = myActor4 ? "START"
   val result4 = Await.result(future4, timeout.duration).asInstanceOf[JsArray]*/

     //result.+:(result2.+:(result3.+:(result4)));
    //result;
     var js: JsArray = result++(result2)++(result3)++(result4)++(result5)++(result6)++(result7)++(result8) ;
    js;
  }


    def fromExcelInput(input: InputStream) : JsArray = {
    var js = Json.arr();
    var i =0;
    var num, time, EDA, Ankle : Double =0;
    try {
      var fileName = file_location;

      //val file: FileInputStream = new FileInputStream(fileName)
      val workbook: XSSFWorkbook = new XSSFWorkbook(input)
      val sheet: XSSFSheet = workbook.getSheetAt(0)
       // sheet.groupRow(0, sheet.getLastRowNum())
      val rowIterator: util.Iterator[Row] = sheet.iterator
      while (rowIterator.hasNext) {
        val row: Row = rowIterator.next
        val cellIterator: util.Iterator[Cell] = row.cellIterator
        i=0;
        num =0;
        while (cellIterator.hasNext) {
          val cell: Cell = cellIterator.next
          if (i >0) {   // to avoid the first column
            cell.getCellType match {
              case Cell.CELL_TYPE_BOOLEAN =>
                //print(cell.getBooleanCellValue + "\t\t")
                num = cell.getNumericCellValue;
              //break //todo: break is not supported
              case Cell.CELL_TYPE_NUMERIC =>
                //print(cell.getNumericCellValue + "\t\t")
                num = cell.getNumericCellValue;
              //break //todo: break is not supported
              case Cell.CELL_TYPE_STRING =>
                //print(cell.getStringCellValue + "\t\t")
              //break //todo: break is not supported
              case Cell.CELL_TYPE_FORMULA =>
                //print(cell.getNumericCellValue + "\t\t")
                num = cell.getNumericCellValue;
              case _ => print("")
            }
          }
          if(i == 1)
            time = num;
          if(i==2)
            EDA = num;
          if(i ==3)
            Ankle = num;
          i= i + 1;
        }
        js = js.:+(Json.obj(
          "c" ->  Json.arr(
            Json.obj(
              "v" -> (time),
              "f" -> ""
            ),
            Json.obj(
              "v" -> (EDA),
              "f" -> ""
            ),
            Json.obj(
              "v" -> (Ankle),
              "f" -> ""
            )
          )
        ));
        println("")
      }

      //file.close
      js;
      //file.close
    }
    /*catch {
      case e: FileNotFoundException => {
        e.printStackTrace
      }
      case e:  IOException => {
        e.printStackTrace
      }
    }*/
  }


  def fromExcel: JsArray = {
    var js = Json.arr();
    var i =0;
    var num, time, EDA, Ankle : Double =0;
    try {
      var fileName = file_location;
      //val file: FileInputStream = new FileInputStream("C:\\Users\\staamneh\\Desktop\\sal\\src\\main\\scala\\test.xlsx")
     /* var fileName =  " ";
      if (task == "BL")
       fileName = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\" + subject + "\\BaseLine Dexterity\\"+ excelFile;
      if (task == "DV")
        fileName = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\" + subject + "\\Direct View\\"+ excelFile;
      if (task == "MV")
        fileName = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\" + subject + "\\Monitor View\\"+ excelFile;
      if (task == "BR")
        fileName = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\" + subject + "\\BaseLine Reading\\" + excelFile;
      if (task == "DR")
        fileName = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\DataSource\\" + subject + "\\Difficult Reading\\" + excelFile;

      println(fileName); */


      val file: FileInputStream = new FileInputStream(fileName)
      val workbook: XSSFWorkbook = new XSSFWorkbook(file)
      val sheet: XSSFSheet = workbook.getSheetAt(0)
      val rowIterator: util.Iterator[Row] = sheet.iterator
      while (rowIterator.hasNext) {
        val row: Row = rowIterator.next
        val cellIterator: util.Iterator[Cell] = row.cellIterator
        i=0;
        num =0;
        while (cellIterator.hasNext) {
          val cell: Cell = cellIterator.next
          if (i >0) {   // to avoid the first column
            cell.getCellType match {
              case Cell.CELL_TYPE_BOOLEAN =>
                print(cell.getBooleanCellValue + "\t\t")
                num = cell.getNumericCellValue;
              //break //todo: break is not supported
              case Cell.CELL_TYPE_NUMERIC =>
                print(cell.getNumericCellValue + "\t\t")
                num = cell.getNumericCellValue;
              //break //todo: break is not supported
              case Cell.CELL_TYPE_STRING =>
                print(cell.getStringCellValue + "\t\t")
              //break //todo: break is not supported
              case Cell.CELL_TYPE_FORMULA =>
                print(cell.getNumericCellValue + "\t\t")
                num = cell.getNumericCellValue;
              case _ => print("")
            }
          }
          if(i == 1)
            time = num;
          if(i==2)
            EDA = num;
          if(i ==3)
            Ankle = num;
          i= i + 1;
        }
        js = js.:+(Json.obj(
          "c" ->  Json.arr(
            Json.obj(
              "v" -> (time),
              "f" -> ""
            ),
            Json.obj(
              "v" -> (EDA),
              "f" -> ""
            ),
            Json.obj(
              "v" -> (Ankle),
              "f" -> ""
            )
          )
        ));
        println("")
      }
      file.close
      js;
    }
    /*catch {
      case e: FileNotFoundException => {
        e.printStackTrace
      }
      case e:  IOException => {
        e.printStackTrace
      }
    }*/
  }

}
