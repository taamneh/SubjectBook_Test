package Models;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import scala.util.parsing.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by staamneh on 3/4/2015.
 */
public class TestMyJAvaCode {

    private static class Dat
    {
        public Dat(int var1, int var2)
        {
            x = var1;
            y = var2;
        }
        public int x;
        public int y;

    }
    public static void round(Integer x) {

        x.equals(7);
    }
    public static void main(String[] args) throws   Exception
    {

        String salah = "Salah";
        Integer x = new Integer(3);
        round(x);
        System.out.println(x);

        /*ForBarFromExcel barRaw = new ForBarFromExcel("C:\\Users\\staamneh\\Downloads\\T001-005.sim", 1, 4);

        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println();
        //System.out.println(ReadExcelJava.findAbsoluteMeanFromExcel("C:\\Users\\staamneh\\Downloads\\T001-005.sim"));
        double x = ReadExcelJava.findAbsoluteMeanFromExcel("C:\\Users\\staamneh\\Downloads\\T001-005.sim");
        ReadExcelJava.findPerformanceFromExcel(x, "C:\\Users\\staamneh\\Downloads\\T001-005.sim");*/
    }
}
