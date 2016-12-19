package Models;

import controllers.DataBaseOperations;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import scala.util.parsing.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void shimmer() {


        String dic = "\\\\times-cpl-files.times.uh.edu\\ToyotaData\\ToyotaP2\\Test Track";
        File dir = new File(dic);
        String[] names = dir.list();

        Long x = 3561029465L;
        java.util.Date time2=new java.util.Date((long)x*1000);
        System.out.println(time2);

        ArrayList<String> excl =  new ArrayList<String>(Arrays.asList(/*"Participant07", "Participant08", "Participant09","Participant10", "Participant16", "Participant17", "Participant19", "Participant01", "Participant01", "Participant15", */ "Participant18"));
        for(String name : names) // subjects
        {
            if(excl.contains(name)) {
                if (new File(dic + "\\" + name).isDirectory()) {
                    //System.out.println(name);
                    String name1 = dic + "\\" + name + "\\" + "Thermal";
                    File thermal = new File(name1);

                    File[] infos = thermal.listFiles();
                    if (infos != null) {
                        for (File info : infos) {
                            if (info.getName().toLowerCase().contains(".inf")) {  // for each info file

                                String end = "";
                                String begin = "";
                                try {
                                    Scanner scanner = new Scanner(new File(name1 + "//" + info.getName()));
                                    scanner.useDelimiter(" ");


                                    int ctr = 0;
                                    while (scanner.hasNextLine() && ctr < 4) {
                                        scanner.nextLine();
                                        ctr++;
                                    }
                                    if (scanner.hasNextLine()) {
                                        scanner.next();
                                        scanner.next();
                                        scanner.next();
                                        begin = scanner.next();
                                    }


                                    while (scanner.hasNextLine()) {
                                        end = scanner.nextLine();
                                    }
                                    if (end != "") {
                                        String[] result = end.split("\\s");
                                        end = result[result.length - 2];
                                    }

                                    if (begin == "")
                                        continue;


                                    scanner.close();
                                }
                                catch (java.io.FileNotFoundException e){
                                   System.out.println("error");
                                }
                                DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                                java.util.Date beginTime, endTime;
                                try {
                                    beginTime = format.parse(begin);
                                    if (beginTime.getHours() >= 1 && beginTime.getHours() < 8)
                                        beginTime.setHours(beginTime.getHours() + 12);
                                    endTime = format.parse(end);
                                    if (endTime.getHours() >= 1 && endTime.getHours() < 8)
                                        endTime.setHours(endTime.getHours() + 12);
                                }
                                catch(java.text.ParseException e) {

                                    System.out.println("error Parsing");
                                    continue;
                                }

                                int beginTotal = ((beginTime.getHours()) * 3600) + (beginTime.getMinutes() * 60) + beginTime.getSeconds();
                                int endTotal = ((endTime.getHours()) * 3600) + (endTime.getMinutes() * 60) + endTime.getSeconds();


                                // after finding the begin and end time we open the shimer file
                                String name2 = dic + "\\" + name + "\\Shimmer\\";
                                File shimeer = new File(name2);
                                File[] edas = shimeer.listFiles();
                                if (edas != null) {
                                    for (File eda : edas) {
                                        // PrintWriter writer = new PrintWriter("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".peda", "UTF-8");
                                        HSSFWorkbook workbook = new HSSFWorkbook();
                                        HSSFSheet sheet = workbook.createSheet(info.getName().substring(0, info.getName().length() - 5));

                                        int rownum = 9;
                                        Row rowtemp = sheet.createRow(8);

                                        Cell cell = rowtemp.createCell(0);
                                        cell.setCellValue("frame");

                                        cell = rowtemp.createCell(1);
                                        cell.setCellValue("Time");

                                        cell = rowtemp.createCell(2);
                                        cell.setCellValue("Palm EDA");

                                        // writer.println("frame" + "\t" + "Time" + "\t" + "signal");
                                        int frame = 1;
                                        double timeAx =0.0;

                                        try {
                                            Scanner scanner2 = new Scanner(new File(name2 + "\\" + eda.getName()));
                                            scanner2.useDelimiter("\t");
                                            scanner2.nextLine();
                                            scanner2.nextLine();
                                            scanner2.nextLine();
                                            scanner2.nextLine();

                                        /*String[] res = scanner2.nextLine().split("\\s");
                                        Long num1 = Long.parseLong(res[2].substring(0, res[2].indexOf('.')));
                                        java.util.Date t = new java.util.Date((long) num1 * 1000);


                                       System.out.println("Drive:" + info.getName().substring(0, info.getName().length() - 5) + "beging: " + beginTime + "    End:" + endTime + " FirstTime" + t);*/

                                            while (scanner2.hasNextLine()) {
                                                String[] result = scanner2.nextLine().split("\\s");
                                                Long num = Long.parseLong(result[2].substring(0, result[2].indexOf('.')));
                                                java.util.Date time = new java.util.Date((long) num * 1000);
                                                time.setHours(time.getHours() + 1);
                                                int currentTotal = ((time.getHours()) * 3600) + (time.getMinutes() * 60) + time.getSeconds();


                                                if (currentTotal >= beginTotal && currentTotal <= endTotal) {


                                                    Row row = sheet.createRow(rownum++);
                                                    Object[] objArr = new Object[]{frame, (double) (1.0 / 60.0) * frame, result[11]};

                                                    int cellnum = 0;
                                                    cell = row.createCell(cellnum++);
                                                    cell.setCellValue(frame);
                                                    cell = row.createCell(cellnum++);
                                                    cell.setCellValue((double) (1.0 / 60.0) * frame);
                                                    cell = row.createCell(cellnum++);
                                                    cell.setCellValue(Double.parseDouble(result[11]));


                                                    //writer.println(frame + "\t" + (double)(1.0/60.0)*frame + "\t" + result[11]);
                                                    frame++;
                                                }

                                            }
                                            scanner2.close();
                                        }
                                        catch (java.io.FileNotFoundException e){
                                            System.out.println("error");
                                        }


                                        try {
                                            FileOutputStream out =
                                                    new FileOutputStream(new File("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".peda"));
                                            workbook.write(out);
                                            out.close();
                                            System.out.println("Excel written successfully..");

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        //writer.close();
                                    }
                                }


                            }  // each info

                        } // all infos
                    }

                }
            }
        }


    }

    public static void hr() {


        String dic = "\\\\times-cpl-files.times.uh.edu\\ToyotaData\\ToyotaP2\\Test Track";
        File dir = new File(dic);
        String[] names = dir.list();

        Long x = 3561029465L;
        java.util.Date time2=new java.util.Date((long)x*1000);
        System.out.println(time2);

        //ArrayList<String> excl =  new ArrayList<String>(Arrays.asList(/*"Participant07", "Participant08", "Participant09","Participant10", "Participant16", "Participant17", "Participant19", "Participant01", "Participant01", "Participant15", */ "Participant18"));
        for(String name : names) // subjects
        {
           // if(excl.contains(name)) {
                if (new File(dic + "\\" + name).isDirectory()) {
                    //System.out.println(name);
                    String name1 = dic + "\\" + name + "\\" + "Thermal";
                    File thermal = new File(name1);

                    File[] infos = thermal.listFiles();
                    if (infos != null) {
                        for (File info : infos) {
                            if (info.getName().toLowerCase().contains(".inf")) {  // for each info file

                                String end = "";
                                String begin = "";
                                try {
                                    Scanner scanner = new Scanner(new File(name1 + "//" + info.getName()));
                                    scanner.useDelimiter(" ");


                                    int ctr = 0;
                                    while (scanner.hasNextLine() && ctr < 4) {
                                        scanner.nextLine();
                                        ctr++;
                                    }
                                    if (scanner.hasNextLine()) {
                                        scanner.next();
                                        scanner.next();
                                        scanner.next();
                                        begin = scanner.next();
                                    }


                                    while (scanner.hasNextLine()) {
                                        end = scanner.nextLine();
                                    }
                                    if (end != "") {
                                        String[] result = end.split("\\s");
                                        end = result[result.length - 2];
                                    }

                                    if (begin == "")
                                        continue;


                                    scanner.close();
                                }
                                catch (java.io.FileNotFoundException e){
                                    System.out.println("error");
                                }
                                DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                                java.util.Date beginTime, endTime;
                                try {
                                    beginTime = format.parse(begin);
                                    if (beginTime.getHours() >= 1 && beginTime.getHours() < 8)
                                        beginTime.setHours(beginTime.getHours() + 12);
                                    endTime = format.parse(end);
                                    if (endTime.getHours() >= 1 && endTime.getHours() < 8)
                                        endTime.setHours(endTime.getHours() + 12);
                                }
                                catch(java.text.ParseException e) {

                                    System.out.println("error Parsing");
                                    continue;
                                }

                                int beginTotal = ((beginTime.getHours()) * 3600) + (beginTime.getMinutes() * 60) + beginTime.getSeconds();
                                int endTotal = ((endTime.getHours()) * 3600) + (endTime.getMinutes() * 60) + endTime.getSeconds();


                                // after finding the begin and end time we open the shimer file
                                String name2 = dic + "\\" + name + "\\Bioharness\\";
                                File shimeer = new File(name2);
                                File[] edas = shimeer.listFiles();
                                if (edas != null) {
                                    for (File eda : edas) {

                                        if(eda.getName().toLowerCase().contains("summary.csv")) {
                                        // PrintWriter writer = new PrintWriter("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".peda", "UTF-8");
                                        HSSFWorkbook workbook = new HSSFWorkbook();
                                        HSSFSheet sheet = workbook.createSheet(info.getName().substring(0, info.getName().length() - 5));

                                        int rownum = 9;
                                        Row rowtemp = sheet.createRow(8);

                                        Cell cell = rowtemp.createCell(0);
                                        cell.setCellValue("frame");

                                        cell = rowtemp.createCell(1);
                                        cell.setCellValue("Time");

                                        cell = rowtemp.createCell(2);
                                        cell.setCellValue("HR");

                                        // writer.println("frame" + "\t" + "Time" + "\t" + "signal");
                                        int frame = 1;
                                        double timeAx = 0.0;

                                        try {
                                            Scanner scanner2 = new Scanner(new File(name2 + "\\" + eda.getName()));
                                            scanner2.useDelimiter("\t");
                                            scanner2.nextLine();
                                            /*String[] result1 = scanner2.nextLine().split("\\s");



                                             System.out.println("Drive:" + info.getName().substring(0, info.getName().length() - 5) + "beging: " + beginTime + "    End: " + endTime + " current " + result1[1].substring(0, result1[1].indexOf('.')));*/
                                            int currentTotal =0;
                                            while (scanner2.hasNextLine()) {
                                                String[] result = scanner2.nextLine().split("\\s");
                                                //Long num = Long.parseLong(result[2].substring(0, result[2].indexOf('.')));
                                                //java.util.Date time = new java.util.Date((long) num * 1000);

                                                try {

                                                    String tempTime = result[1].substring(0, result[1].indexOf('.'));
                                                    java.util.Date time = format.parse(tempTime);
                                                    //time.setHours(time.getHours() + 1);
                                                    currentTotal = ((time.getHours()) * 3600) + (time.getMinutes() * 60) + time.getSeconds();


                                                    String [] data = result[1].split(",");

                                                    if (currentTotal >= beginTotal && currentTotal <= endTotal) {


                                                        Row row = sheet.createRow(rownum++);
                                                        Object[] objArr = new Object[]{frame, frame, data[1]};

                                                        int cellnum = 0;
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue(frame);
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue( frame);
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue(Double.parseDouble(data[1]));


                                                        //writer.println(frame + "\t" + (double)(1.0/60.0)*frame + "\t" + result[11]);
                                                        frame++;
                                                    }
                                                }
                                                catch (java.text.ParseException e){
                                                    System.out.println("error Parsing 2222");
                                                    continue;
                                                }

                                            }

                                            while(currentTotal <= endTotal){
                                                Row row = sheet.createRow(rownum++);

                                                int cellnum = 0;
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue(frame);
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue( frame);
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue(0);
                                                frame++;
                                                currentTotal += 1;// add one second
                                            }
                                            scanner2.close();
                                        }
                                        catch (java.io.FileNotFoundException e) {
                                            System.out.println("error");
                                        }


                                        try {
                                            FileOutputStream out =
                                                    new FileOutputStream(new File("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".hrc"));
                                            workbook.write(out);
                                            out.close();
                                            System.out.println("Excel written successfully..");

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        //writer.close();
                                    }
                                    }
                                }


                            }  // each info

                        } // all infos
                    }

                }
           // }
        }


    }

    public static void br() {


        String dic = "\\\\times-cpl-files.times.uh.edu\\ToyotaData\\ToyotaP2\\Test Track";
        File dir = new File(dic);
        String[] names = dir.list();

        Long x = 3561029465L;
        java.util.Date time2=new java.util.Date((long)x*1000);
        System.out.println(time2);

        //ArrayList<String> excl =  new ArrayList<String>(Arrays.asList(/*"Participant07", "Participant08", "Participant09","Participant10", "Participant16", "Participant17", "Participant19", "Participant01", "Participant01", "Participant15", */ "Participant18"));
        for(String name : names) // subjects
        {
            // if(excl.contains(name)) {
            if (new File(dic + "\\" + name).isDirectory()) {
                //System.out.println(name);
                String name1 = dic + "\\" + name + "\\" + "Thermal";
                File thermal = new File(name1);

                File[] infos = thermal.listFiles();
                if (infos != null) {
                    for (File info : infos) {
                        if (info.getName().toLowerCase().contains(".inf")) {  // for each info file

                            String end = "";
                            String begin = "";
                            try {
                                Scanner scanner = new Scanner(new File(name1 + "//" + info.getName()));
                                scanner.useDelimiter(" ");


                                int ctr = 0;
                                while (scanner.hasNextLine() && ctr < 4) {
                                    scanner.nextLine();
                                    ctr++;
                                }
                                if (scanner.hasNextLine()) {
                                    scanner.next();
                                    scanner.next();
                                    scanner.next();
                                    begin = scanner.next();
                                }


                                while (scanner.hasNextLine()) {
                                    end = scanner.nextLine();
                                }
                                if (end != "") {
                                    String[] result = end.split("\\s");
                                    end = result[result.length - 2];
                                }

                                if (begin == "")
                                    continue;


                                scanner.close();
                            }
                            catch (java.io.FileNotFoundException e){
                                System.out.println("error");
                            }
                            DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                            java.util.Date beginTime, endTime;
                            try {
                                beginTime = format.parse(begin);
                                if (beginTime.getHours() >= 1 && beginTime.getHours() < 8)
                                    beginTime.setHours(beginTime.getHours() + 12);
                                endTime = format.parse(end);
                                if (endTime.getHours() >= 1 && endTime.getHours() < 8)
                                    endTime.setHours(endTime.getHours() + 12);
                            }
                            catch(java.text.ParseException e) {

                                System.out.println("error Parsing");
                                continue;
                            }

                            int beginTotal = ((beginTime.getHours()) * 3600) + (beginTime.getMinutes() * 60) + beginTime.getSeconds();
                            int endTotal = ((endTime.getHours()) * 3600) + (endTime.getMinutes() * 60) + endTime.getSeconds();


                            // after finding the begin and end time we open the shimer file
                            String name2 = dic + "\\" + name + "\\Bioharness\\";
                            File shimeer = new File(name2);
                            File[] edas = shimeer.listFiles();
                            if (edas != null) {
                                for (File eda : edas) {

                                    if(eda.getName().toLowerCase().contains("summary.csv")) {
                                        // PrintWriter writer = new PrintWriter("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".peda", "UTF-8");
                                        HSSFWorkbook workbook = new HSSFWorkbook();
                                        HSSFSheet sheet = workbook.createSheet(info.getName().substring(0, info.getName().length() - 5));

                                        int rownum = 9;
                                        Row rowtemp = sheet.createRow(8);

                                        Cell cell = rowtemp.createCell(0);
                                        cell.setCellValue("frame");

                                        cell = rowtemp.createCell(1);
                                        cell.setCellValue("Time");

                                        cell = rowtemp.createCell(2);
                                        cell.setCellValue("BR");

                                        // writer.println("frame" + "\t" + "Time" + "\t" + "signal");
                                        int frame = 1;
                                        double timeAx = 0.0;

                                        try {
                                            Scanner scanner2 = new Scanner(new File(name2 + "\\" + eda.getName()));
                                            scanner2.useDelimiter("\t");
                                            scanner2.nextLine();
                                            /*String[] result1 = scanner2.nextLine().split("\\s");



                                             System.out.println("Drive:" + info.getName().substring(0, info.getName().length() - 5) + "beging: " + beginTime + "    End: " + endTime + " current " + result1[1].substring(0, result1[1].indexOf('.')));*/
                                            int currentTotal =0;
                                            while (scanner2.hasNextLine()) {
                                                String[] result = scanner2.nextLine().split("\\s");
                                                //Long num = Long.parseLong(result[2].substring(0, result[2].indexOf('.')));
                                                //java.util.Date time = new java.util.Date((long) num * 1000);

                                                try {

                                                    String tempTime = result[1].substring(0, result[1].indexOf('.'));
                                                    java.util.Date time = format.parse(tempTime);
                                                    //time.setHours(time.getHours() + 1);
                                                    currentTotal = ((time.getHours()) * 3600) + (time.getMinutes() * 60) + time.getSeconds();


                                                    String [] data = result[1].split(",");

                                                    if (currentTotal >= beginTotal && currentTotal <= endTotal) {


                                                        Row row = sheet.createRow(rownum++);
                                                        Object[] objArr = new Object[]{frame, frame, data[2]};

                                                        int cellnum = 0;
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue(frame);
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue( frame);
                                                        cell = row.createCell(cellnum++);
                                                        cell.setCellValue(Double.parseDouble(data[2]));


                                                        //writer.println(frame + "\t" + (double)(1.0/60.0)*frame + "\t" + result[11]);
                                                        frame++;
                                                    }
                                                }
                                                catch (java.text.ParseException e){
                                                    System.out.println("error Parsing 2222");
                                                    continue;
                                                }

                                            }

                                            while(currentTotal <= endTotal){
                                                Row row = sheet.createRow(rownum++);

                                                int cellnum = 0;
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue(frame);
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue( frame);
                                                cell = row.createCell(cellnum++);
                                                cell.setCellValue(0);
                                                frame++;
                                                currentTotal += 1;// add one second
                                            }
                                            scanner2.close();
                                        }
                                        catch (java.io.FileNotFoundException e) {
                                            System.out.println("error");
                                        }


                                        try {
                                            FileOutputStream out =
                                                    new FileOutputStream(new File("C:\\Users\\staamneh\\Dropbox\\temp2\\" + info.getName().substring(0, info.getName().length() - 5) + ".brc"));
                                            workbook.write(out);
                                            out.close();
                                            System.out.println("Excel written successfully..");

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        //writer.close();
                                    }
                                }
                            }


                        }  // each info

                    } // all infos
                }

            }
            // }
        }


    }
    public static void main(String[] args) throws   Exception
    {




        br();


/*
        Long x = 3562509005L;
        java.util.Date time=new java.util.Date((long)x*1000);

        Scanner scanner = new Scanner(new File("\\\\times-cpl-files.times.uh.edu\\ToyotaData\\ToyotaP2\\Test Track\\Participant25\\Shimmer\\25 - 11-20-2016AM 11-50-05.dat"));
        scanner.useDelimiter("\t");


        while(scanner.hasNext()){
            System.out.print(scanner.next()+"|");
        }
        scanner.close();

        System.out.println(time.getHours());

        */

        /*

        String dic = "\\\\timesfile3.times.uh.edu\\cpl\\Toyota\\Raw Data\\73\\Thermal Data";
        String name = String.valueOf(dic.charAt(46));
        if(Character.isDigit(dic.charAt(47)))
            name = name + String.valueOf(dic.charAt(47));

        System.out.println(name);
        File dir = new File(dic);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {

                File file = new File(dic + "\\" + child.getName());

                if (child.getName().toLowerCase().contains("baseline.dat") || child.getName().toLowerCase().contains("b.dat") || child.getName().toLowerCase().contains("base.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-BL.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("baseline.inf") || child.getName().toLowerCase().contains("b.inf") || child.getName().toLowerCase().contains("base.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-BL.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");
                }

                if (child.getName().toLowerCase().contains("ld1.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-ND.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("ld1.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-ND.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");
                }
                if(child.getName().toLowerCase().contains("ld2.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-CD.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");
                }
                if(child.getName().toLowerCase().contains("ld2.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-CD.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");
                }
                if(child.getName().toLowerCase().contains("ld3.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-ED.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");
                }
                if(child.getName().toLowerCase().contains("ld3.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-ED.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("ld4.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-MD.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("ld4.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-MD.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }


                if(child.getName().toLowerCase().contains("p.dat") || child.getName().toLowerCase().contains("pd.dat") ||  child.getName().toLowerCase().contains("practice.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-PD.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }

                if(child.getName().toLowerCase().contains("p.inf") || child.getName().toLowerCase().contains("pd.inf") || child.getName().toLowerCase().contains("practice.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-PD.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("fdn.dat") &&  String.valueOf(child.getName().toLowerCase().charAt(0)) != "t")
                {
                    File file2 = new File(dic + "\\T0" + name + "-FDN.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                else{
                    if(child.getName().toLowerCase().contains("n.dat") || child.getName().toLowerCase().contains("nd.dat") || child.getName().toLowerCase().contains("normal.dat"))
                    {
                        File file2 = new File(dic + "\\T0" + name + "-RD.dat");
                        boolean success=  file.renameTo(file2);
                        if(!success)
                            System.out.println("Errorrrrrrr");

                    }
                }
                if(child.getName().toLowerCase().contains("fdn.inf") && String.valueOf(child.getName().toLowerCase().charAt(0)) != "t")
                {
                    File file2 = new File(dic + "\\T0" + name + "-FDN.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                else{
                    if(child.getName().toLowerCase().contains("n.inf") || child.getName().toLowerCase().contains("nd.inf") || child.getName().toLowerCase().contains("normal.inf"))
                    {
                        File file2 = new File(dic + "\\T0" + name + "-RD.inf");
                        boolean success=  file.renameTo(file2);
                        if(!success)
                            System.out.println("Errorrrrrrr");

                    }
                }
                if(child.getName().toLowerCase().contains("fdl.dat"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-FDL.dat");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                if(child.getName().toLowerCase().contains("fdl.inf"))
                {
                    File file2 = new File(dic + "\\T0" + name + "-FDL.inf");
                    boolean success=  file.renameTo(file2);
                    if(!success)
                        System.out.println("Errorrrrrrr");

                }
                //System.out.println(child.getName());
            }
        }
*/
        /*Runtime.getRuntime().exec("cmd /c dir");

        //creating File instance to reference text file in Java
        File text = new File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\videosNeedToSync.csv");

        //Creating Scanner instnace to read File in Java
        Scanner scnr = new Scanner(text);

        //Reading each line of file using Scanner class
        int lineNumber = 1;
        //Runtime.getRuntime().exec("cmd /c start C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\16\\New\\generic.bat  50 20 C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\16\\New\\1_2016-08-15_15-41-53.mp4 C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\16\\New\\");



        String l = scnr.nextLine(); // to skip the header
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();

            String[] tokens = line.split(",");


            String task = tokens[2].substring(1);
            switch (task.toLowerCase()) {
                case "practicedrive":
                    task = "pd";
                    break;
                case "normaldrive":
                    task = "nd";
                    break;
                case "cognitivedrive":
                    task = "cd";
                    break;
                case "motoricdrive":
                    task = "md";
                    break;
                case "finaldrive":
                    task = "nd";
                    break;
            }
            String subjectFolder = tokens[0].substring(tokens[0].length() - 2, tokens[0].length());
            subjectFolder = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\" + subjectFolder + "\\" + task + ".mp4";
            String dest = tokens[1] + "\\" + tokens[2] + "\\" + tokens[0] + "_" + tokens[2].substring(1) + ".avi2";


            if (tokens.length > 4) {
                if (!tokens[4].equals(" ")) {

                    System.out.println("Hoon ");
                    int start = Integer.parseInt(tokens[4]);
                    int length = Integer.parseInt(tokens[3]);

                    if (start < 0) {
                        length = length + (start);
                        start = 1;
                    }


                    Runtime.getRuntime().exec("cmd /c start C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\generic.bat " + Integer.toString(start) + " " + tokens[3] + " " + subjectFolder + " " + dest + " " + tokens[1] + "\\" + tokens[2] + "\\ " + Integer.toString(length) + " 1");
                } else {
                    System.out.println(" mesh Hoon ");
                    //int start = Integer.parseInt(tokens[4]);
                    int length = Integer.parseInt(tokens[3]);
                    System.out.println(subjectFolder);
                    Runtime.getRuntime().exec("cmd /c start C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\generic.bat " + Integer.toString(1) + " " + tokens[3] + " " + subjectFolder + " " + dest + " " + tokens[1] + "\\" + tokens[2] + "\\ " + Integer.toString(length) + " 0");

                }
            }
            else {
                System.out.println(" mesh Hoon ");
                //int start = Integer.parseInt(tokens[4]);
                int length = Integer.parseInt(tokens[3]);
                System.out.println(subjectFolder);
                Runtime.getRuntime().exec("cmd /c start C:\\Users\\staamneh\\Desktop\\CPL-Lab\\videosCliping\\generic.bat " + Integer.toString(1) + " " + tokens[3] + " " + subjectFolder + " " + dest + " " + tokens[1] + "\\" + tokens[2] + "\\ " + Integer.toString(length) + " 0");
            }
            lineNumber++;
            //break;
            Thread.sleep(40000);
        }

            */

    }
}
