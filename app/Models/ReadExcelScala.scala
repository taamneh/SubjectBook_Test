package Models

import java.io.{IOException, FileNotFoundException, FileInputStream}
import java.util

import org.apache.poi.ss.usermodel.{Cell, Row}
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import play.api.libs.json.{JsArray, Json}

/**
 * Created by staamneh on 9/8/2014.
 */
class ReadExcelScala {
  def fromExcel: JsArray = {
    var js = Json.arr();
    var i =0;
    var num, time, EDA, Ankle : Double =0;
    try {
      //val file: FileInputStream = new FileInputStream("C:\\Users\\staamneh\\Desktop\\sal\\src\\main\\scala\\test.xlsx")
      val file: FileInputStream = new FileInputStream("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\Children Study\\Stressbook\\S004\\1 Difficult Reading\\RI_S004-001.Q_motion")
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
