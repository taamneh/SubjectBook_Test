package controllers;

import Models.Biography;
import Models.Psychometric;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.apache.poi.ss.formula.functions.MinaMaxa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import play.Logger;
import scala.collection.immutable.Map;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by staamneh on 9/17/2015.
 */
public class QueryStudy {

    public static String GetSubjectPRF(String username, String url, int sourceType)  throws IOException
    {
        String json = "[";
        String line = "";
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == SharedData.LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
            Drive service = GoogleDrive.buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = GoogleDrive.downloadFileByFileId(service, url);
        }
        if(input!=null) {
            scanner = new Scanner(input);
            int ctr = 1;
            boolean firstOne = true;
            while (scanner.hasNextLine() && scanner.hasNext()) {
                System.out.println("TATAWTWERAWERAWERA");


                if (firstOne) {
                    json = json + "{\"name\":\"" + scanner.next().toString() + "\", \"value\": \"" + scanner.next().toString() + "\", \"min\": \"" + scanner.next().toString() + "\", \"max\": \"" + scanner.next().toString() + "\"}";
                    firstOne = false;
                } else {
                    json = json + ", {\"name\":\"" + scanner.next().toString() + "\", \"value\": \"" + scanner.next().toString() + "\", \"min\": \"" + scanner.next().toString() + "\", \"max\": \"" + scanner.next().toString() + "\"}";
                }

            }
            scanner.close();
            input.close();
            json = json + "]";
            System.out.println(json);
            return json;
        }
        else {
            return null;  // if the file was not found in user's drive
        }
    }
    public static String GetSubjectInfo(String username, String url, int sourceType, int signalType, Biography bio)  throws IOException
    {
        String json = "{";
        String line = "";
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == SharedData.LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
            Drive service = GoogleDrive.buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = GoogleDrive.downloadFileByFileId(service, url);
        }
        if(input!=null) {
            scanner = new Scanner(input);
            int ctr = 1;
            boolean firstOne = true;
            while (scanner.hasNextLine()) {

                // first line is the gender
                if (ctr == 1 && bio.Gender() == 1) {

                    if (firstOne) {
                        json = json + "\"Gender\": \"" + scanner.nextLine().toString() + "\"";
                        firstOne = false;
                    } else
                        json = json + ", \"Gender\": \"" + scanner.nextLine().toString() + "\"";
                } else if (ctr == 2 && bio.Age() == 1) {
                    String str = scanner.nextLine().toString();
                    if (firstOne) {
                        json = json + "\"Age\": \"" + str + "\"";
                        firstOne = false;
                    } else
                        json = json + ", \"Age\": \"" + str + "\"";
                } else if (ctr == 3 && bio.Ethnicity() == 1) {
                    String str = scanner.nextLine().toString();
                    if (firstOne) {
                        json = json + "\"Ethnicity\": \"" + str + "\"";
                        firstOne = false;
                    } else
                        json = json + ", \"Ethnicity\": \"" + str + "\"";
                } else if (ctr == 4 && bio.Height() == 1) {
                    String str = scanner.nextLine().toString();
                    if (firstOne) {
                        json = json + "\"Height\": \"" + str + "\"";
                        firstOne = false;
                    } else
                        json = json + ", \"Height\": \"" + str + "\"";
                } else if (ctr == 5 && bio.Weight() == 1) {
                    String str = scanner.nextLine().toString();
                    if (firstOne) {
                        json = json + "\"Weight\": \"" + str + "\"";
                        firstOne = false;
                    } else
                        json = json + ", \"Weight\": \"" + str + "\"";
                } else {
                    scanner.nextLine();
                }
                //json = "{\"age\": 45, \"Gender\": \"Male\"";

                ctr++;

            }
            scanner.close();
            input.close();
            json = json + "}";
            //System.out.println(json);
            return  json;
        }
        else { // if the file was not found in user's drive
            return null;
        }

    }


    public static String GetSubjectInfo2(String username, String url, int sourceType, int signalType, Biography bio)  throws IOException
    {
        String json = "{";
        String line = "";
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == SharedData.LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
            Drive service = GoogleDrive.buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = GoogleDrive.downloadFileByFileId(service, url);
        }
        if(input!=null) {
            scanner = new Scanner(input);
            int ctr = 1;
            boolean firstOne = true;
            while (scanner.hasNextLine()) {
                if (firstOne) {
                    json = json + "\"" + ctr + "\": \"" + scanner.nextLine().toString() + "\"";
                    firstOne = false;
                } else {
                    json = json + ", \"" + ctr + "\": \"" + scanner.nextLine().toString() + "\"";
                }
                ctr++;
            }

            scanner.close();
            input.close();
            json = json + "}";


            //System.out.println(json);
            return  json;
        }
        else { // if the file was not found in user's drive
            return null;
        }

    }

    public static InputStream GetVideoInputStream(String username, String url, int sourceType, int signalType)  throws IOException
    {
        InputStream input = null;
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        Drive service = GoogleDrive.buildService(googleCredential);

        File file = service.files().get(url).execute();
        Logger.info("Open The info File with URL: " + url);
        Logger.info("The video size is" + file.getFileSize());
        return GoogleDrive.downloadFileByFileId(service, url);


    }


    public static String GetVideoSize(String username, String url, int sourceType, int signalType)  throws IOException
    {
        InputStream input = null;
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        Drive service = GoogleDrive.buildService(googleCredential);

        File file = service.files().get(url).execute();
        Logger.info("Open The info File with URL: " + url);
        Logger.info("Open The info File with embedlink: " + file.getEmbedLink());
        Logger.info("Open The info File with exportLink: " + file.getExportLinks());
        Logger.info("MIME type: " + file.getMimeType());

        Logger.info("The video size is" + file.getFileSize());
        return file.getFileSize().toString();
        //return file.getDownloadUrl();

    }


    public static String GetSubjectPm(String username, String url, int sourceType, int signalType, Psychometric psycho)  throws IOException
    {
        String json = "{";
        String line = "", metric, score;
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == SharedData.LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
            Drive service = GoogleDrive.buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = GoogleDrive.downloadFileByFileId(service, url);
        }
        scanner = new Scanner(input);
        int ctr= 1;
        boolean firstOne = true;
        while(scanner.hasNextLine()) {

            line = scanner.nextLine();
            int lastIndex = line.indexOf(':');
            if(lastIndex == -1 || line.length()==0){
                continue;
            }
            metric = line.substring(0,lastIndex);
            score = line.substring(lastIndex+1,line.length());
            metric  = metric.trim();
            score = score.trim();

            System.out.println("--------------" + metric);

            switch(metric.toLowerCase())
            {
                case "sai":
                    if(psycho.SAI()==1) {
                        if (firstOne) {
                            json = json + "\"SAI\": \"" + score + "\"";
                            firstOne = false;
                        } else
                            json = json + ", \"SAI\": \"" + score + "\"";
                    }
                    break;
                case "tai":
                    if(psycho.TAI()==1){
                        if (firstOne) {
                            json = json + "\"TAI\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"TAI\": \"" + score + "\"";
                    }
                    break;
                case "na" :
                    if(psycho.NA() == 1){
                        if(firstOne) {
                            json = json + "\"NA\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"NA\": \"" + score + "\"";
                    }
                    break;
                case "pa":
                    if(psycho.PA() == 1) {
                        if(firstOne) {
                            json = json + "\"PA\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"PA\": \"" + score + "\"";
                    }
                    break;
                default:
                    if(psycho.Other() == 1) {
                        if (firstOne) {
                            json = json + "\"" + metric.toUpperCase() + "\": \"" + score + "\"";
                            firstOne = false;
                        } else
                            json = json + ", \"" + metric.toUpperCase() + "\": \"" + score + "\"";
                    }
                    break;
            }


        }
        scanner.close();
        input.close();
        json = json + "}";
        System.out.println(json);
        return  json;

    }


    public static org.json.simple.JSONObject GetSubjectPm2(String username, String url, int sourceType, int signalType, Psychometric psycho, TreeMap<String, SharedData.MinMax> lstOfPy)  throws IOException
    {
        String json = "{";
        String line = "", metric, score;
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == SharedData.LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
            Drive service = GoogleDrive.buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = GoogleDrive.downloadFileByFileId(service, url);
        }
        scanner = new Scanner(input);
        int ctr= 1;
        boolean firstOne = true;

        JSONObject all = new JSONObject();
      /*  JSONObject obj = new JSONObject();

        obj.put("name", "PA");
        obj.put("score", 70);
        obj.put("min", 10);
        obj.put("max", 100);

        JSONObject obj2 = new JSONObject();

        obj2.put("name", "NA");
        obj2.put("score", 70);
        obj2.put("min", 30);
        obj2.put("max", 400);

        //list.add(obj2);

        JSONObject all = new JSONObject();
        all.put("1", obj);
        all.put("2", obj2);

        */

        while(scanner.hasNextLine()) {


            line = scanner.nextLine();
            int lastIndex = line.indexOf(':');
            if(lastIndex == -1 || line.length()==0){
                continue;
            }
            metric = line.substring(0,lastIndex);
            score = line.substring(lastIndex + 1, line.length());
            metric  = metric.trim().toUpperCase();
            score = score.trim();

            //System.out.println("--------------" + metric);

            //System.out.println("--------------" + lstOfPy);

            if(lstOfPy.containsKey(metric))
            {
               // System.out.println("*****************" + lstOfPy.get(metric).min);
                JSONObject newObj = new JSONObject();
                newObj.put("name", metric);
                newObj.put("score", score);
                newObj.put("min", lstOfPy.get(metric).min);
                newObj.put("max", lstOfPy.get(metric).max);

                all.put("\"" + ctr + "\"", newObj);
                ctr++;
            }



           /* switch(metric.toLowerCase())
            {
                case "sai":
                    if(psycho.SAI()==1) {
                        if (firstOne) {
                            json = json + "\"SAI\": \"" + score + "\"";
                            firstOne = false;
                        } else
                            json = json + ", \"SAI\": \"" + score + "\"";
                    }
                    break;
                case "tai":
                    if(psycho.TAI()==1){
                        if (firstOne) {
                            json = json + "\"TAI\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"TAI\": \"" + score + "\"";
                    }
                    break;
                case "na" :
                    if(psycho.NA() == 1){
                        if(firstOne) {
                            json = json + "\"NA\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"NA\": \"" + score + "\"";
                    }
                    break;
                case "pa":
                    if(psycho.PA() == 1) {
                        if(firstOne) {
                            json = json + "\"PA\": \"" + score + "\"";
                            firstOne = false;
                        }
                        else
                            json = json + ", \"PA\": \"" + score + "\"";
                    }
                    break;
                default:
                    if(psycho.Other() == 1) {
                        if (firstOne) {
                            json = json + "\"" + metric.toUpperCase() + "\": \"" + score + "\"";
                            firstOne = false;
                        } else
                            json = json + ", \"" + metric.toUpperCase() + "\": \"" + score + "\"";
                    }
                    break;
            } */


        }
        scanner.close();
        input.close();
        json = json + "}";
       // System.out.println(json);
        //return  json;
        return all;

    }

}
