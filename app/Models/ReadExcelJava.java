package Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import play.api.libs.json.JsArray;
import play.api.libs.json.Json;

/**
 * Created by staamneh on 10/29/2014.
 */
public class ReadExcelJava {
    public static String file_location;

    void ReadExclJava(String loc)
    {
        file_location = loc;
    }
    public static JsArray fromExcelInput(InputStream input)  throws IOException {
        JsArray js = null;
        int i = 0;
        double num, time, EDA;
        double Ankle = 0;
        try {
            String fileName = file_location;

            //val file: FileInputStream = new FileInputStream(input)
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheetAt(0);
            // sheet.groupRow(0, sheet.getLastRowNum())
            Iterator rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = (Row) rowIterator.next();
                Iterator cellIterator = row.cellIterator();
                i = 0;
                num = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    if (i > 0) {   // to avoid the first column

                        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                            num = cell.getNumericCellValue();
                        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            num = cell.getNumericCellValue();
                        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            //print(cell.getStringCellValue + "\t\t")
                            //break //todo: break is not supported
                        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            //print(cell.getNumericCellValue + "\t\t")
                            num = cell.getNumericCellValue();
                        } else
                            System.out.println("Empty");

                    }
                    if (i == 1)
                        time = num;
                    if (i == 2)
                        EDA = num;
                    if (i == 3)
                        Ankle = num;
                    i = i + 1;
                }
                // println("")
            }

            //file.close

            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return js;
    }
}
