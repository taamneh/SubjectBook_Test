package Models

import java.io.{InputStream, IOException, FileNotFoundException, FileInputStream}
import java.util
import akka.actor.SupervisorStrategy.Stop
import akka.actor._
import org.apache.poi.openxml4j.opc.OPCPackage
import scala.concurrent.{ExecutionContext, Future, Await}
import akka.pattern.ask
import scala.concurrent.duration._
import akka.actor.Props
import akka.util.Timeout
import org.apache.poi.ss.usermodel.{Cell, Row}
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import play.api.libs.json.{JsObject, JsArray, Json}
import play.libs.Akka
import play.Logger
import ExecutionContext.Implicits.global

/**
 * Created by staamneh on 9/8/2014.
 */
class SplitExcelActor( var sheet: XSSFSheet, fromRow: Int, toRow: Int, frameRate: Int) extends Actor {

  //var state = 10;
  def receive = {
    case "STOP" => Stop
    case "START" => {

      Logger.info("one SplitExcelActor has been lunched")
      var frameRateItr = 0;
      var js = Json.arr();
      var i = 0;
      var rowNo = fromRow;
      var num, time, EDA, Ankle: Double = 0;
      try {

        //val rowIterator: util.Iterator[Row] = sheetPublic.iterator
        var row: Row = null
        var j =0;
        var cellIterator: util.Iterator[Cell] = null
        var cell: Cell = null
        var isRowEmpty = false;

        var tempFirst =0.0; var tempSecond =0.0; var tempThird =0.0;
        // we noly allow four empty spaces

        while (rowNo < toRow ) {


          var tempFirst =0.0; var tempSecond =0.0; var tempThird =0.0;
          var numOfBlanks = 0;
          row = sheet.getRow(rowNo);



          frameRateItr += 1;
          cellIterator = row.cellIterator
          i = 0;
          num = 0;
          while (cellIterator.hasNext) {

            cell = cellIterator.next
            if (i > 0) {
              // to avoid the first column
              cell.getCellType match {
                case Cell.CELL_TYPE_BOOLEAN =>
                  num = cell.getNumericCellValue;
                  if (i == 1)
                    tempFirst = num;
                  if (i == 2)
                    tempSecond= num;
                  if (i == 3)
                    tempThird = num;
                case Cell.CELL_TYPE_NUMERIC =>
                  num = cell.getNumericCellValue;
                  if (i == 1)
                    tempFirst = num;
                  if (i == 2)
                    tempSecond= num;
                  if (i == 3)
                    tempThird = num;
                case Cell.CELL_TYPE_STRING =>
                case Cell.CELL_TYPE_FORMULA =>
                  num = cell.getNumericCellValue;
                  if (i == 1)
                    tempFirst = num;
                  if (i == 2)
                    tempSecond= num;
                  if (i == 3)
                    tempThird = num;
                case Cell.CELL_TYPE_BLANK =>
                 // print("ttt")

                  isRowEmpty = true;
                  numOfBlanks =numOfBlanks + 1;
                case _ => print("cc")
                  isRowEmpty = true;
              }
            }
            i = i + 1;
           /*if (!isRowEmpty)
           {
              if (i == 1)
                time = tempFirst;
              if (i == 2)
                EDA += tempSecond;
              if (i == 3)
                Ankle += tempThird;
              i = i + 1;
         }*/
         }

          if(!(tempFirst==0.0 && tempSecond==0.0 && tempThird ==0.0)) {
            time = tempFirst;
            EDA += tempSecond;
            Ankle += tempThird;
            if (frameRateItr >= frameRate) {
              var divisor = frameRate;
             // print(time + " " + EDA + " " + Ankle + " - ");
              js = js.:+(Json.obj(
                "c" -> Json.arr(
                  Json.obj(
                    "v" -> (time),
                    "f" -> ""
                  ),
                  Json.obj(
                    "v" -> ((EDA / divisor)),
                    "f" -> ""
                  ),
                  Json.obj(
                    "v" -> ((Ankle / divisor)),
                    "f" -> ""
                  )
                )
              ));
              frameRateItr = 0
              EDA = 0;
              Ankle = 0;
            }
          }
          rowNo += 1;
        }
        sender() ! js;
      }
    }
  }


}
class ReadExcelScala(taskP: String , subjectP : String, loc: String, signal_type: Int) {

  var frameRate = signal_type match {
    case 1 => 32
    case 2 => 32
    case 3 => 1
    case _ => 1
  }

  var task : String = taskP;
  var subject : String = subjectP;
  var file_location: String = loc;
  var excelFile = "RI_" + subject + "-001.Q_EDA";

  def fromExcelInputParallel(input: InputStream) : JsObject = {

   signal_type match {
     case 1 => fromExcelInputParallelEDA(input)
     case _ => fromExcelInputParallelEDA(input)
   }
  }
  def fromExcelInputParallelEDA(input: InputStream) : JsObject = {

    val workbook: XSSFWorkbook = new XSSFWorkbook(input)
    val sheet: XSSFSheet = workbook.getSheetAt(0);



    var row: Row = null
    var cellIterator: util.Iterator[Cell] = null
    var cell: Cell = null
    // we noly allow four empty spaces



    var header =  Json.arr();

      row = sheet.getRow(8);
      cellIterator = row.cellIterator
      var i = 0;
      var num = 0.0;
      while (cellIterator.hasNext) {
        cell = cellIterator.next
          cell.getCellType match {
            /*case Cell.CELL_TYPE_BOOLEAN =>
              num = cell.getNumericCellValue;
            //break //todo: break is not supported
            case Cell.CELL_TYPE_NUMERIC =>
              num = cell.getNumericCellValue;
            //break //todo: break is not supported
            case Cell.CELL_TYPE_STRING =>
            //break //todo: break is not supported
            case Cell.CELL_TYPE_FORMULA =>
              num = cell.getNumericCellValue;*/
            // print("What: " + i + " " + num);
            case Cell.CELL_TYPE_STRING  => print("cc")
              if(i ==0)
                i+=1;
              else {
              header = header.:+ ( Json.obj(
                "id" -> "",
                "label" -> cell.getStringCellValue,
                "patter" -> 31,
                "type" -> "number"
              ))
              }
            case _ => println("Empty Header")

          }

      }


    Logger.info("fromExcelInputParallel has been called");
    val length = sheet.getLastRowNum;

    Logger.info("The number of rows using getLastRowNum= " + length)
    Logger.info("The number of rows using getPhysicalNumberOfRows= " + sheet.getPhysicalNumberOfRows)

    if(length > 100000)
      frameRate = frameRate *3;
    val num1 : Int = length/8 + 9;
    var num2: Int = 0;
    if(length%4 != 0)
      num2 = length - 3*num1;
    else
      num2= num1
    val myActor = Akka.system.actorOf(Props(new SplitExcelActor(sheet, 9, num1, frameRate)));
    val myActor2 = Akka.system.actorOf(Props(new SplitExcelActor( sheet, num1+1, 2*num1, frameRate)));
    val myActor3 = Akka.system.actorOf(Props(new SplitExcelActor(sheet, 2*num1+1 , 3*num1, frameRate)));
    val myActor4 = Akka.system.actorOf(Props(new SplitExcelActor( sheet, 3*num1+1, 4*num1,frameRate)));
    val myActor5 = Akka.system.actorOf(Props(new SplitExcelActor( sheet, 4*num1+1, 5*num1, frameRate)));
    val myActor6 = Akka.system.actorOf(Props(new SplitExcelActor(sheet, 5*num1+1, 6*num1, frameRate)));
    val myActor7 = Akka.system.actorOf(Props(new SplitExcelActor(sheet, 6*num1+1 , 7*num1, frameRate)));
    val myActor8 = Akka.system.actorOf(Props(new SplitExcelActor(sheet, 7*num1+1, length, frameRate)));
    //myActor ! "ss";
    implicit val timeout = Timeout (200 seconds)
    val future  = myActor  ? "START"
    //val future: Future[JsArray] = ask(myActor, "START").mapTo[JsArray]
    val future2 = myActor2 ? "START"
    //val future2: Future[JsArray] = ask(myActor2, "START").mapTo[JsArray]
    val future3 = myActor3 ? "START"
    //val future3: Future[JsArray] = ask(myActor3, "START").mapTo[JsArray]
    val future4 = myActor4 ? "START"
    //val future4: Future[JsArray] = ask(myActor4, "START").mapTo[JsArray]
    val future5 = myActor5 ? "START"
    //val future5: Future[JsArray] = ask(myActor5, "START").mapTo[JsArray]
    val future6 = myActor6 ? "START"
    //val future6: Future[JsArray] = ask(myActor6, "START").mapTo[JsArray]
    val future7 = myActor7 ? "START"
    //val future7: Future[JsArray] = ask(myActor7, "START").mapTo[JsArray]
    val future8 = myActor8 ? "START"
    //val future8: Future[JsArray] = ask(myActor8, "START").mapTo[JsArray]

    val result = Await.result(future, timeout.duration).asInstanceOf[JsArray]
    val result2 = Await.result(future2, timeout.duration).asInstanceOf[JsArray]
    val result3 = Await.result(future3, timeout.duration).asInstanceOf[JsArray]
    val result4 = Await.result(future4, timeout.duration).asInstanceOf[JsArray]
    val result5 = Await.result(future5, timeout.duration).asInstanceOf[JsArray]
    val result6 = Await.result(future6, timeout.duration).asInstanceOf[JsArray]
    val result7 = Await.result(future7, timeout.duration).asInstanceOf[JsArray]
    val result8 = Await.result(future8, timeout.duration).asInstanceOf[JsArray]


    myActor  ! "STOP"
    myActor2  ! "STOP"
    myActor3  ! "STOP"
    myActor4  ! "STOP"
    myActor5  ! "STOP"
    myActor6  ! "STOP"
    myActor7  ! "STOP"
    myActor8  ! "STOP"



    var js: JsArray = result++(result2)++(result3)++(result4)++(result5)++(result6)++(result7)++(result8) ;



    //var js: JsArray = (future.)++(future2)++(future3)++(future4)++(future5)++(future6)++(future7)++(future8) ;

    Logger.info("fromExcelInputParallel now will Exit");
    var obj = Json.obj("cols" -> header, "rows" -> js);
    obj;
  }

  /**
   * It is not used
   * @param input
   * @return
   */
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
