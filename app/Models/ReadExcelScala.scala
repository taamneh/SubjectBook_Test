package Models

import java.io.{InputStream, IOException, FileNotFoundException, FileInputStream}
import java.util

import org.apache.poi.ss.usermodel.{Cell, Row}
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import play.api.libs.json.{JsArray, Json}

/**
 * Created by staamneh on 9/8/2014.
 */
class ReadExcelScala(taskP: String , subjectP : String, loc: String) {
  var task : String = taskP;
  var subject : String = subjectP;
  var file_location: String = loc;

  var excelFile = "RI_" + subject + "-001.Q_EDA";

  def fromExcelInput(input: InputStream) = {
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


      //val file: FileInputStream = new FileInputStream(fileName)
      val workbook: XSSFWorkbook = new XSSFWorkbook(input)
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
