package Models;

import java.io.*;
import java.lang.Double;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import play.Logger;
import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import play.libs.Scala;

/**
 * Created by staamneh on 10/29/2014.
 */
public class ReadExcelJava {

    private static int getFrameRate(int signalType) {
        if(signalType == 3 || signalType == 6 || signalType==2 ) {
            return 8;
        }
        else if(signalType ==1  )
            return 4;
        else if(signalType ==5 )
            return 16;
        else
            return 1;
    }

    /**
     * This fucntion is used to read the activity from excel file in order to use it to annotated the other signals
     * @param input
     * @return
     * @throws IOException
     */

    public static ArrayList<Activity> readActivity(InputStream input)  throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        int i = 0, rowNum=0, j=0;
        boolean exit =false, startWroking = false;
        Map<Double, String> code = new TreeMap<>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        try {

           // XSSFWorkbook workbook = new XSSFWorkbook(input);
            Workbook workbook = WorkbookFactory.create(input);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = null;
            Iterator cellIterator = null;
            Cell cell = null;

            Logger.info("We are trying to read the activity file");
            Iterator rowIterator = sheet.iterator();
            while (rowIterator.hasNext() && !exit) {

                //Logger.info("Just ot cehck if there is an infine loop or not - Rows");
                row = (Row) rowIterator.next();
                // to skip the first 5 rows
                //if (rowNum >= ROW_STAR_AT) {
                if(startWroking){
                    cellIterator = row.cellIterator();
                    i = 0;
                    double start = 0, end = 0;
                    int actionType = 1;
                    String annotation = "", annotationText = "";
                    while (cellIterator.hasNext() && !exit) {
                        cell = (Cell) cellIterator.next();
                        try {
                            switch (i) {
                                case 0:
                                    //start = Double.parseDouble(cell.getStringCellValue());
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                                        start = cell.getNumericCellValue();
                                        if ( start == 0.0)
                                            exit = true;
                                    }
                                    else /*if(cell.getCellType()==Cell.CELL_TYPE_BLANK)*/ {
                                        exit = true;
                                    }
                                    break;
                                case 1:
                                    end =  cell.getNumericCellValue();
                                    break;
                                case 5:
                                    actionType = (int) cell.getNumericCellValue();
                                    break;
                                case 6:
                                    //annotation = cell.getStringCellValue();
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        annotation = Double.toString(cell.getNumericCellValue());
                                    }
                                    else if(cell.getCellType() == Cell.CELL_TYPE_STRING)
                                    {
                                        annotation = cell.getStringCellValue();
                                    }
                                    annotationText = annotation;
                                    activities.add(new Activity(start, end, actionType, annotation, annotationText, code.get( (double) actionType)));
                                    break;

                            }
                        } catch (NumberFormatException ex) { // handle your exception
                            ex.printStackTrace();;
                        }

                        i++;
                    }

                }
                else
                {
                    // this code is to attached the code for each action type
                    if(rowNum >0) {
                        cellIterator = row.cellIterator();
                        j = 0;
                        double key = 0;
                        while (cellIterator.hasNext()) {
                            cell = (Cell) cellIterator.next();
                            if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                                continue;
                            }
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && j==0) {
                                key= cell.getNumericCellValue();
                            }
                            else if(cell.getCellType() == Cell.CELL_TYPE_STRING && j==1) {
                                code.put(key, cell.getStringCellValue());
                                break;
                            }

                            else {
                                startWroking = true;
                            }
                            j++;
                        }
                    }
                    rowNum++;
                }
            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            input.close();
        }
        return activities;
    }

    /**
     * This fuctnion is used to find the maximum value in the first column after the time in an excel file
     * @param fileName
     * @return
     * @throws Exception
     */
    public static MeanAndSizeOfSignal findMaxFromExcel (String fileName)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName,1,  7);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double  max = 0;
        for (Double t: allNumber) {

            if(t > max) max = t;
            counter++;
        }

        return new MeanAndSizeOfSignal(max, counter, allNumber);

    }

    /**
     * This fucntion is used to find the percentage of the signal that is higher than max, and lower thatn max
     * @param max
     * @param fileName
     * @return
     * @throws Exception
     */
    public static BarPercentage findBarFromExcel (double max, String fileName)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);
            OldExcelFormat oldF = new OldExcelFormat(barRaw);
            try {

                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                oldF.readSheet();
            }
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double normal = 0, stress = 0, sum =0;
        for(double num: allNumber){
            sum = sum +num;
            if (num < max) {
                normal++;
            } else
                stress++;
        }

        int total = allNumber.size();
        normal = (normal / total) * 100;
        stress = (stress / total) * 100;
        return new BarPercentage(0.0, normal, stress);

    }

    /**
     * This fuctnion is used to find the mean value in the first column after the time in an excel file
     * @param fileName
     * @return
     * @throws Exception
     */
    public static MeanAndSizeOfSignal findMeanFromExcel (String fileName)  throws Exception {
        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double sum=0;
        for (Double t: allNumber) {
            counter++;
            sum += t;
        }
        double mean = sum / counter;
        return new MeanAndSizeOfSignal(mean, counter, allNumber);

    }

    public static BarPercentage findBarFromExcelWithActivity (double threshold, String fileName, ArrayList<Activity> activities)  throws Exception {

        double localThreshold = threshold;

        if(activities != null) {
            for (Activity ac : activities) {
                if (ac.actionType == 5)
                    localThreshold = ac.startTime;
            }
        }

        ArrayList<Double> allNumber = new ArrayList<Double>();
        ArrayList<Double> allTime = new ArrayList<Double>();

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);
            OldExcelFormat oldF = new OldExcelFormat(barRaw);
            try {

                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                oldF.readSheet();
            }

            allNumber = barRaw.getArrayOfDouble();
            allTime = barRaw.getTimeArray();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        /// find the mean
        double intialMean =0, intialcounter =0;
        int index =0;
        for(double num: allNumber){
            if(allTime.get(index) < threshold) {
                intialMean += num;
                intialcounter ++;
            }
            else
                break;
            index++;
        }

        intialMean = intialMean/intialcounter;


        double  normal = 0, stress = 0;
        int total =0;
        index =0;
        for(double num: allNumber){
            if(index < allTime.size()) {
                if (allTime.get(index) > threshold) {
                    if (num < intialMean) {
                        normal++;
                    } else
                        stress++;
                    total++;
                }
                index++;
            }
        }

        normal = (normal / total) * 100;
        stress = (stress / total) * 100;


        return new BarPercentage(0.0, normal, stress);
    }
    public static Double findPerformanceFromExcelAbstraction (double max, String fileName, int responseColNum)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1, responseColNum);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);
            OldExcelFormat oldF = new OldExcelFormat(barRaw);
            try {

                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double relax = 0, normal = 0, stress = 0;
        for(double num: allNumber){
            if (Math.abs(num) < max) {
                normal++;
            } else
                stress++;
        }

        int total = allNumber.size();
        //relax = (relax / actual.get(i).size())*100;
        relax = 0;
        normal = (normal / total) * 100;
        stress = (stress / total) * 100;

        // System.out.println("The totoal is "+ total  +"    The perfromance value is: " + stress   + "    The other one is:  " + normal);

        //returnList.add(new BarPercentage(relax, normal, stress));


        return stress;



    }
    public static Double findPerformanceFromExcel (double max, String fileName)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1, 4);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);
            OldExcelFormat oldF = new OldExcelFormat(barRaw);
            try {

                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double relax = 0, normal = 0, stress = 0;
        for(double num: allNumber){
            if (Math.abs(num) < max) {
                normal++;
            } else
                stress++;
        }

        int total = allNumber.size();
        //relax = (relax / actual.get(i).size())*100;
        relax = 0;
        normal = (normal / total) * 100;
        stress = (stress / total) * 100;

        // System.out.println("The totoal is "+ total  +"    The perfromance value is: " + stress   + "    The other one is:  " + normal);

        //returnList.add(new BarPercentage(relax, normal, stress));


        return stress;



    }
    public static MeanAndSizeOfSignal findAbsoluteMeanFromExcel (String fileName)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1,4);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double sum=0;
        for (Double t: allNumber) {
            counter++;
            sum += Math.abs(t);
            //sumSqure += Math.pow(temp, 2);
        }



        double sd = 0;

        double mean = sum / counter;
        return new MeanAndSizeOfSignal(mean, counter, allNumber);
    }
    public static MeanAndSizeOfSignal findAbsoluteMeanFromExcelAbstraction (String fileName, int respColNo)  throws Exception {

        ArrayList<Double> allNumber = new ArrayList<Double>();
        int counter = 0;

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1,respColNo);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            //return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }

        double sum=0;
        for (Double t: allNumber) {
            counter++;
            sum += Math.abs(t);
            //sumSqure += Math.pow(temp, 2);
        }



        double sd = 0;

        double mean = sum / counter;
        return new MeanAndSizeOfSignal(mean, counter, allNumber);
    }
    public static TreeMap<Double, Double> getAllSignalFromExcelAbstraction (String fileName, int respColNo)  throws Exception {




        if(fileName == null)
        {
            System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        }

        File file = new File(fileName);
        ForBarFromExcel barRaw = new ForBarFromExcel(fileName, 1,respColNo);
        try {

            NewExcelFormat newF = new NewExcelFormat(barRaw);

            try {
                newF.processAllSheets();
            } catch (InvalidOperationException ioe) {
                OldExcelFormat oldF = new OldExcelFormat(barRaw);
                oldF.readSheet();
            }

            // actual.put(entry.getKey(), barRaw.getArrayOfDouble());
            //allNumber = barRaw.getArrayOfDouble();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            System.out.println("Wrong Format..........!!!!!!!!!!!!!!!!!!");
        } catch (IOException e) {
            System.out.println("ERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRIIIIIIIIIIIIIII");
            e.printStackTrace();
        } finally {

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
        }


        return  barRaw.timeAndData;
    }
    public  static ArrayList<Double> findTotalNASA(int signalType, String fileName)  throws  Exception {
        long startTime =System.nanoTime();
        int i = 0, frameRate = getFrameRate(signalType);
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        BarChartFromExcel jsonForChart = new BarChartFromExcel(fileName, signalType);

        try {

            NewExcelFormatForBarData newF = new NewExcelFormatForBarData(jsonForChart);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            jsonForChart.finalize();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return jsonForChart.getArrayOfDouble();
    }

    public  static TreeMap<String, Double> findTotalNASAWithHeader(int signalType, String fileName,  TreeMap<String, String> mp )  throws  Exception {
        long startTime =System.nanoTime();
        int i = 0, frameRate = getFrameRate(signalType);
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        BarChartFromExcel jsonForChart = new BarChartFromExcel(fileName, signalType, mp);

        try {

            NewExcelFormatForBarData newF = new NewExcelFormatForBarData(jsonForChart);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            jsonForChart.finalize();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return jsonForChart.getCloNamesWithVal();
    }

    public static JSONObject fromExcelInputTemp(int signalType,TreeMap<Double, Double> basline,  ArrayList<Activity> activities, String fileName, int first_row, int first_col)  throws Exception {
        long startTime =System.nanoTime();
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        //InputStream input = new FileInputStream(file);
        NPOIFSFileSystem npoifs = null;
        OPCPackage pkg = null;

        try {
            //PlainChartFromExcel jsonForChart = new PlainChartFromExcel(fileName);
            //AnnotatedChartFromExcel ann = new AnnotatedChartFromExcel(fileName, activities, signalType);

            if(basline !=null) {

                AnnotatedChartFromExcelWithBaseLine ann = new AnnotatedChartFromExcelWithBaseLine(fileName, basline, activities, signalType);

                NewExcelFormat newF = new NewExcelFormat(ann, first_row, first_col);
                //NewExcelFormat newF = new NewExcelFormat(ann);
                OldExcelFormat oldF = new OldExcelFormat(ann);
                try {

                    newF.processAllSheets();

                } catch (InvalidOperationException ioe) {
                    oldF.readSheet();
                }

                ann.finalSteps();

                return ann.getJosonForChart();
            }
            else
            {
                AnnotatedChartFromExcel ann = new AnnotatedChartFromExcel(fileName, activities, signalType);

                NewExcelFormat newF = new NewExcelFormat(ann, first_row, first_col);
                //NewExcelFormat newF = new NewExcelFormat(ann);
                OldExcelFormat oldF = new OldExcelFormat(ann);
                try {

                    newF.processAllSheets();

                } catch (InvalidOperationException ioe) {
                    oldF.readSheet();
                }

                ann.finalSteps();

                return ann.getJosonForChart();
            }


        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            if (npoifs != null) { npoifs.close(); }
            if (pkg != null) { pkg.close(); }

            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type:  "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return all;
    }

    public  JSONObject fromExcelInputTemp(int signalType, String fileName, int first_row, int first_col)  throws  Exception {
        long startTime =System.nanoTime();
        int i = 0, frameRate = getFrameRate(signalType);
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        PlainChartFromExcel jsonForChart = new PlainChartFromExcel(fileName, signalType);

        try {

            NewExcelFormat newF = new NewExcelFormat(jsonForChart, first_row,first_col);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            //jsonForChart.close();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return jsonForChart.getJosonForChart();


    }


    // it just used while creating a new study  TODO maybe find a way to avoid having this new code by using other code that is almost identical except for the returning data
    public  ArrayList<String> fromExcelInputTempForCreateStudy(int signalType, String fileName, int first_row, int first_col)  throws  Exception {
        long startTime =System.nanoTime();
        int i = 0, frameRate = getFrameRate(signalType);
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        PlainChartFromExcel jsonForChart = new PlainChartFromExcel(fileName, signalType);

        try {

            NewExcelFormat newF = new NewExcelFormat(jsonForChart, first_row,first_col);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            //jsonForChart.close();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }

        ArrayList<String> labels = new ArrayList<>();
        Iterator each = jsonForChart.header.iterator(), it;
        while(each.hasNext()){
            JSONObject line =  (JSONObject) each.next();
            //System.out.println(line.get("label"));
            labels.add(line.get("label").toString());
        }
        return labels;


    }

    /**
     * This fucntion is used to read bar chart from exel
     * @param signalType
     * @param fileName
     * @return
     * @throws Exception
     */
    public  JSONObject fromExcelInputToCharTemp(int signalType, String fileName, TreeMap<String, String> mp) throws  Exception {
        long startTime =System.nanoTime();
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        BarChartFromExcel jsonForChart = new BarChartFromExcel(fileName, signalType, mp);

        try {

            NewExcelFormatForBarData newF = new NewExcelFormatForBarData(jsonForChart);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            jsonForChart.finalize();
            //jsonForChart.getArrayOfDouble();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return jsonForChart.getJosonForChart();


    }


    public   ArrayList<SessionDescription>   getStudyDescription(int signalType, String fileName) throws  Exception {
        long startTime =System.nanoTime();
        org.json.simple.JSONObject all = null;
        File file = new File(fileName);
        StudyDescriptionFromExcel jsonForChart = new StudyDescriptionFromExcel(fileName, signalType);

        try {

            NewExcelFormatForBarData newF = new NewExcelFormatForBarData(jsonForChart);
            OldExcelFormat oldF = new OldExcelFormat(jsonForChart);
            try {

                newF.processAllSheets();
            } catch(InvalidOperationException ioe)
            {
                oldF.readSheet();
            }
            jsonForChart.finalize();
            //jsonForChart.getArrayOfDouble();
        }
        catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {


            // delete the file
            if (! file.delete()) {
                Logger.info("File has NOT been deleted");
            }
            Logger.info("Singal Type: "+signalType +"  Time spent in method fromExcelInput is :" + ( System.nanoTime()- startTime));
        }
        return jsonForChart.getDescriptor();


    }

    // when the signal has activity data

    public static TreeMap<String, BarPercentage> fromExceForPortraitTemp(TreeMap<String,String> input, int signalType) throws Exception {
        {
            int i = 0;
            double num;

            TreeMap<String, ArrayList<Double>> actual = new TreeMap<>();

            double all = 0;
            ArrayList<Double> allNumber = new ArrayList<Double>();
            int counter = 0;
            for (Map.Entry<String, String> entry : input.entrySet()) {

                File file = new File(entry.getValue());
                ForBarFromExcel barRaw = new ForBarFromExcel(entry.getValue(), signalType);
                try {
                    ArrayList<Double> templst = new ArrayList<Double>();


                    NewExcelFormat newF = new NewExcelFormat(barRaw);
                    OldExcelFormat oldF = new OldExcelFormat(barRaw);
                    try {

                        newF.processAllSheets();
                    } catch (InvalidOperationException ioe) {
                        oldF.readSheet();
                    }

                    actual.put(entry.getKey(), barRaw.getArrayOfDouble());
                } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {


                    // delete the file
                    if (! file.delete()) {
                        Logger.info("File has NOT been deleted");
                    }
                    //       Logger.info("Singal Type: " + signalType + "  Time spent in method fromExcelInput is :" + (System.nanoTime() - startTime));
                }

            }

            //double mean = all / counter;

            double sum=0, sumSqure=0;
            for (Map.Entry<String, ArrayList<Double>> entry2 : actual.entrySet()) {
                for (int j = 0; j < entry2.getValue().size(); j++) {
                    counter++;
                    double temp = entry2.getValue().get(j);
                    sum += temp;
                    //sumSqure += Math.pow(temp, 2);
                }
            }
            //   double sd = Math.sqrt((sumSqure/counter)  - Math.pow(mean/counter, 2));

            double sd = 0;

            double mean = sum / counter;

            for (Map.Entry<String, ArrayList<Double>> entry2 : actual.entrySet()) {
                for (int j = 0; j < entry2.getValue().size(); j++) {
                    //counter++;
                    double temp = entry2.getValue().get(j);
                    sd = sd + Math.pow(temp - mean, 2);
                }
            }

            sd = Math.sqrt((sd) / (counter - 1));


            //System.out.println("Mean= " + mean + "  sd=" + sd);
           /* for (i = 0; i < allNumber.size(); i++) {
                sd = sd + Math.pow(allNumber.get(i) - mean, 2);
            }
            sd = Math.sqrt((sd) / (allNumber.size() - 1));

            System.out.println("SD= " + sd + "  Signal Type=" + signalType);*/


            TreeMap<String, BarPercentage> returnList = new TreeMap<>();

            //for(i=0; i< actual.size(); i++){
            double val;
            for (Map.Entry<String, ArrayList<Double>> entry2 : actual.entrySet()) {
                double relax = 0, normal = 0, stress = 0;
                for (int j = 0; j < entry2.getValue().size(); j++) {
                    val = entry2.getValue().get(j);
                    if (val < mean - 1 * sd) {
                        relax++;
                    } else if ((val >= (mean - 1 * sd)) && (val <= (mean + 1 * sd))) {
                        normal++;
                    } else
                        stress++;
                }

                int total = entry2.getValue().size();
                //relax = (relax / actual.get(i).size())*100;
                relax = (relax / total) * 100;
                normal = (normal / total) * 100;
                stress = (stress / total) * 100;

                //returnList.add(new BarPercentage(relax, normal, stress));
                returnList.put(entry2.getKey(), new BarPercentage(relax, normal, stress));

                // System.out.println("relax: " + relax + "  normal: " + normal + "  stress: " + stress);
            }
            return returnList;
        }
    }



}