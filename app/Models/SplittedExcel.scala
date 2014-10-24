package Models

import java.io.InputStream
import java.util

import akka.actor.Actor
import org.apache.poi.ss.usermodel.{Cell, Row}
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import play.api.libs.json.Json

/**
 * Created by staamneh on 10/14/2014.
 */
class SplittedExcel(sheet: XSSFSheet, fromRow: Int, toRow: Int) extends Actor {

    var state = 10;
    def receive = {
      case _ =>

        var js = Json.arr();
        var i = 0;
        var rowNo =fromRow ;
        var num, time, EDA, Ankle: Double = 0;
        try {
          //val workbook: XSSFWorkbook = new XSSFWorkbook(input)
          //val sheet: XSSFSheet = workbook.getSheetAt(0)
          // sheet.groupRow(0, sheet.getLastRowNum())
          val rowIterator: util.Iterator[Row] = sheet.iterator
          //while (rowIterator.hasNext) {

          while(rowNo < toRow) {
            //val row: Row = rowIterator.next
            val row: Row = sheet.getRow(rowNo);
            val cellIterator: util.Iterator[Cell] = row.cellIterator
            i = 0;
            num = 0;
            while (cellIterator.hasNext) {
              val cell: Cell = cellIterator.next
              if (i > 0) {
                // to avoid the first column
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
              if (i == 1)
                time = num;
              if (i == 2)
                EDA = num;
              if (i == 3)
                Ankle = num;
              i = i + 1;
            }
            js = js.:+(Json.obj(
              "c" -> Json.arr(
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
            //println("")
            rowNo+=1;
          }
          //file.close
          sender() ! js;
        }
    }

}
