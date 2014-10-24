package Models;

/**
 * Created by staamneh on 10/6/2014.
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.oauth2.model.Userinfo;
import controllers.StudyTopology;
import play.api.libs.json.JsArray;
import play.Logger;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class GoogleDrive {

    private GoogleCredential googleCredential;
    private Drive service;
    private Credential credential;
    private String authorizationCode;
    Userinfo userInfo;
    private final static int GOOGLE_DRIVE = 1;
    private final static int LOCALSERVER = 2;
    public static String authorizationUrl;
    private static class NoUserIdException extends Exception {
    }

    public GoogleDrive() throws IOException
    {
        authorizationUrl = MyClass.getAuthorizationUrl("taamneh83@hotmail.com", "Not good");
    }
    public GoogleDrive(String authorization_Code)  throws IOException
    {
        System.out.println("Let's start");
        authorizationUrl = MyClass.getAuthorizationUrl("taamneh83@hotmail.com", "Not good");
        System.out.println(authorizationUrl);
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        try {
            /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String code1 = br.readLine();  //TODO this should be retrieved from query string
            System.out.println(code1);*/
            authorizationCode = authorization_Code;
            credential = MyClass.getCredentials(authorizationCode, "Any Thing");
            googleCredential = new GoogleCredential();
            googleCredential.setAccessToken(credential.getAccessToken());
            //googleCredential.setRefreshToken(credential.getRefreshToken());

            //service = MyClass.buildService(googleCredential);
            userInfo = MyClass.getUserInfo(credential);

            if (credential.getRefreshToken() != null) {
                controllers.Application.storeCredentials(userInfo.getEmail(), credential.getAccessToken(), credential.getRefreshToken());
            }

        }
        catch (MyClass.CodeExchangeException e)
        {
            e.printStackTrace();
        }
            catch (MyClass.NoRefreshTokenException e)
        {
        e.printStackTrace();
        } catch (MyClass.NoUserIdException e) {
            e.printStackTrace();
        }
    }
    public String getAuthorizationUrl()
    {
        return authorizationUrl;
    }
    public String getUserEmail()
    {
      //System.out.println(userInfo.getEmail());
       return  userInfo.getEmail();
    }
    public String FindStudy (String folder_id, String studyName, String username, StudyTopology studyTopology)  throws IOException
    {
        String report = "Creating Study's Report: \n";

        String access  = controllers.Application.getStoredCredentials(username);
         googleCredential = new GoogleCredential();
        googleCredential.setAccessToken(access);
        service = MyClass.buildService(googleCredential);

            //String query = "title = '"+ studyName + "'";
            //System.out.println(query);
            //List<File> result = MyClass.retrieveAllFiles(service,query);
            //if(result.size()> 0) {
                //List<String> subjects = MyClass.returnFilesInFolder(service, result.get(0).getId(), "mimeType = 'application/vnd.google-apps.folder'");
                //System.out.println("IMPORTANT: " +  result.get(0).getId());
                List<String> subjects = MyClass.returnFilesInFolder(service,folder_id, "mimeType = 'application/vnd.google-apps.folder'");
                //test number of subjects
                if(subjects.size() > studyTopology.noOfSubjects())
                    report = report + "Your selected " + studyTopology.noOfSubjects() + " as the number of subjects in your study but you have: " + subjects.size() + "\n";
                else if (subjects.size() < studyTopology.noOfSubjects() )
                    report = report + "You have fewer subjects than the number you have selected\n";
                int study_no = controllers.Application.GenerateStudyNoGD(studyName, username, GOOGLE_DRIVE);
                for (String subject : subjects) {
                    File file0 = service.files().get(subject).execute();
                    controllers.Application.InsertSubjectGD(file0.getTitle(), study_no);
                    List<String> sessions = MyClass.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");
                    int session_no = 1;
                    if(sessions.size() > studyTopology.noOfSession())
                        report = report + "Subject <" + file0.getTitle() + "> Has more sessions than that specified\n";
                    else if (sessions.size() < studyTopology.noOfSession() )
                        report = report + "Subject<" + file0.getTitle() + "> Has fewer sessions than that specified\n";
                    for(String sessionId : sessions)
                    {
                        int eda=0,motion=0,perspiration= 0, thermal_breath =0 , belt_breath=0, heart_rate =0, gsr=0;
                        File file = service.files().get(sessionId).execute();
                        System.out.println("File Id: " + file.getTitle());
                        List<String> Signals = MyClass.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                        int signal_type =1 ;
                        for(String signalId : Signals)
                        {
                            System.out.print(studyTopology.physiology().EDA() == 0);

                            File file2 = service.files().get(signalId).execute();

                            // to skip conflict files that start with ~
                            if(file2.getTitle().contains("~"))
                                continue;
                            String extension = file2.getFileExtension();
                            System.out.println("Extension: " + extension);
                            if(extension.toLowerCase().equals("q_eda") ){
                                if(studyTopology.physiology().EDA() !=1)
                                    continue;
                                signal_type =1;
                                eda= 1;
                            }
                            else if(extension.toLowerCase().equals("q_motion") ){
                                if(studyTopology.physiology().Motion() !=1)
                                    continue;
                                signal_type =2;
                                motion=1;
                            }
                            else if(extension.toLowerCase().equals("perspiration")){
                                if(studyTopology.physiology().Perspiration() !=1)
                                    continue;
                                signal_type =3;
                                perspiration=1;
                            }
                            else if(extension.toLowerCase().equals( "breathing")){
                                if(studyTopology.physiology().Breathing_Thermal() !=1)
                                    continue;
                                signal_type =4;
                                thermal_breath =1;
                            }
                            else if(extension.toLowerCase().equals( "z_ecg")){
                                if(studyTopology.physiology().Heart_Rate() !=1)
                                    continue;
                                signal_type =5;
                                heart_rate=1;
                            }
                            else if(extension.toLowerCase().equals( "z_breathing")) {
                                if(studyTopology.physiology().Breathing_Belt() !=1)
                                    continue;
                                signal_type =6;
                                belt_breath =1;
                            }
                            else if(extension.toLowerCase().equals( "q_temperature")) signal_type =7;
                            else if(extension.toLowerCase().equals( "avi")) signal_type =8;
                            else if(extension.toLowerCase().equals( "info")) signal_type =9;
                            else if(extension.toLowerCase().equals( "activity")) signal_type =10;
                            else signal_type =11;

                            controllers.Application.InsertSessionGD(file0.getTitle(), study_no, session_no, file.getTitle(), signal_type, file2.getDownloadUrl());
                            System.out.println("File Id: " + file2.getTitle());



                            //signal_type++;

                        }
                        session_no++;
                        if(studyTopology.physiology().EDA() ==1 && eda != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: EDA signal was not found\n";
                        }
                        if(studyTopology.physiology().Motion() ==1 && motion != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: Motion signal was not found\n";
                        }
                        if(studyTopology.physiology().GSR_Finger() ==1 && gsr != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: GSR_Finger signal was not found\n";
                        }
                        if(studyTopology.physiology().Heart_Rate() ==1 && heart_rate != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: heart_rate signal was not found\n";
                        }
                        if(studyTopology.physiology().Perspiration() ==1 && perspiration != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: perspiration signal was not found\n";
                        }
                        if(studyTopology.physiology().Breathing_Belt() ==1 && belt_breath != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: Breathing_Belt signal was not found\n";
                        }
                        if(studyTopology.physiology().Breathing_Thermal() ==1 && thermal_breath != 1 ){
                            report = report + "     Session <"+ file.getTitle() + ">: Breathing_Thermal signal was not found\n";
                        }
                    }

                    //Check the missing Signal

                    // .Info and .pm
                    List<String> infos= MyClass.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");
                    for(String info : infos) {
                        File fileInfo = service.files().get(info).execute();

                        // to skip conflict files that start with ~
                        if(fileInfo.getTitle().contains("~"))
                            continue;
                        String extension = fileInfo.getFileExtension();
                        System.out.println("Extension: " + extension);

                        if(extension.toLowerCase().equals( "info"))
                        {
                            Scanner inputStream = null;
                            inputStream = new Scanner(MyClass.downloadFileByUrl(service, fileInfo.getDownloadUrl()));
                            String s = inputStream.nextLine();
                            System.out.println("TTTTTTTTTTTTTTTTTTTTTT::" + s);

                            ////// handle the subject information line by line >>>>
                            // age:20
                            //speed:100


                        }
                        if(extension.toLowerCase().equals( "pm"))
                        {
                            // handle the psychometric
                        }

                    }

                }
          // }
        //else
             //System.out.println("There is no Study stored in your Google Drive with name: " + studyName);
        report = "Study has been created with the following warning:/n" + report;
       return report;
    }

    public static void FindStudyLocalServer (String studyName,String url, String username, int noOfSession)  throws IOException
    {
        // Get the study folder

        //final java.io.File study = new java.io.File("\\\\172.21.132.12\\TempEswarDataAugust2012\\" + studyName);
        //url = url.replace("\\", "\\\\");

        final java.io.File study = new java.io.File(url);
        if(study.exists()) {
            Logger.info("We Found it");
            // Insert new study in the DB
            int study_no = controllers.Application.GenerateStudyNoGD(studyName, username, LOCALSERVER);
            // Get all the subjects in that study
            for (final java.io.File subject : study.listFiles()) {
                String subjectName = subject.getName();
                if (subject.isDirectory()) {
                    System.out.println(subject.getAbsolutePath());
                    //Inset subjects into the new study
                     controllers.Application.InsertSubjectGD(subject.getName(), study_no);
                    int session_no = 1;
                    if(noOfSession !=0) {
                        // get all the session inside a subject
                        for (final java.io.File session : subject.listFiles()) {
                            String sessionName = session.getName();
                            sessionName = sessionName.replaceAll("\\s+", "");
                            System.out.println(sessionName);
                            if (session.isDirectory()) {
                                int signal_type = 1;
                                //get all the signals inside a session
                                for (final java.io.File signal : session.listFiles()) {
                                    int pos = signal.getAbsolutePath().toString().lastIndexOf('.');
                                    String fileExtension = signal.getAbsolutePath().substring(pos + 1);
                                    System.out.println("Extention: " + fileExtension);
                                    switch (fileExtension.toLowerCase()) {
                                        case "q_eda":
                                            signal_type =1;
                                            break;
                                        case "q_motion":
                                            signal_type =2;
                                            break;
                                        case "perspiration":
                                            signal_type =3;
                                            break;
                                        case "breathing":
                                            signal_type =4;
                                            break;
                                        case "z_ecg":
                                            signal_type =5;
                                            break;
                                        case "z_breathing":
                                            signal_type =6;
                                            break;
                                        case "q_temperature":
                                            signal_type =7;
                                            break;
                                        case "avi":
                                            signal_type =8;
                                            break;
                                        case "info":
                                            signal_type =9;
                                            break;
                                        case "activity":
                                            signal_type =10;
                                            break;
                                        default:
                                            Logger.error("Invlaid File Extension: " + fileExtension );
                                    }
                                    //insert signal for the new study
                                   controllers.Application.InsertSessionGD(subjectName,study_no,session_no, sessionName, signal_type,signal.getAbsolutePath());
                                }
                            } else {
                                //Logger.error("The Study folder does not follow the study Grammer");
                                // if the file has .info read the information
                            }

                        }
                        session_no++;
                    }
                    else {
                        int signal_type = 1;
                        for (final java.io.File signal : subject.listFiles()) {
                            int pos = signal.getAbsolutePath().toString().lastIndexOf('.');
                            String fileExtension = signal.getAbsolutePath().substring(pos + 1);
                            System.out.println("Extention: " + fileExtension);
                            switch (fileExtension.toLowerCase()) {
                                case "q_eda":
                                    signal_type =1;
                                    break;
                                case "q_motion":
                                    signal_type =2;
                                    break;
                                case "perspiration":
                                    signal_type =3;
                                    break;
                                case "breathing":
                                    signal_type =4;
                                    break;
                                case "z_ecg":
                                    signal_type =5;
                                    break;
                                case "z_breathing":
                                    signal_type =6;
                                    break;
                                case "q_temperature":
                                    signal_type =7;
                                    break;
                                case "avi":
                                    signal_type =8;
                                    break;
                                case "info":
                                    signal_type =9;
                                    break;
                                case "activity":
                                    signal_type =10;
                                    break;
                                default:
                                    Logger.error("Invlaid File Extension: " + fileExtension );
                            }
                            //insert signal for the new study
                            controllers.Application.InsertSessionGD(subjectName,study_no,session_no, "None", signal_type,signal.getAbsolutePath());
                        }
                    }
                }
                else
                {
                    Logger.error("Some files inside study are not folders (i.e., subjects)");
                }
            }
        }
        else
        {
            Logger.error("There is no folder with this name");
        }

    }

    //SourceType 1: from server, 2: from Google drive
    public JsArray DownloadSignal(String username, String url, int sourceType)  throws IOException
    {
        if (sourceType == LOCALSERVER)
        {
            ReadExcelScala newjs = new ReadExcelScala("", "", " ");
            InputStream input = new BufferedInputStream(
                    new FileInputStream(url));
            return newjs.fromExcelInputParallel(input);
        }
        else {
            String access = controllers.Application.getStoredCredentials(username);
            googleCredential = new GoogleCredential();
            googleCredential.setAccessToken(access);
            service = MyClass.buildService(googleCredential);
            ReadExcelScala newjs = new ReadExcelScala("", "", " ");
            //return newjs.fromExcelInput(MyClass.downloadFileByUrl(service,url));
            return newjs.fromExcelInputParallel(MyClass.downloadFileByUrl(service, url));
        }
    }

    public static void main(String[] args) throws IOException {
        //GoogleDrive test = new GoogleDrive();
        //test.FindStudy("stressbook");

     /*   //controllers.Application.print();

        /////////////////////////////////////////
        String st = MyClass.getAuthorizationUrl("taamneh83@hotmail.com", "Not good");
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println(st);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String code1 = br.readLine();  //TODO this should be retrieved from query string
            System.out.println(code1);
            //Credential cred = MyClass.exchangeCode(code1);
            Credential cred = MyClass.getCredentials(code1, "Any Thing");
            GoogleCredential goog_cred = new GoogleCredential();
            goog_cred.setAccessToken(cred.getAccessToken());
            Drive service2 = MyClass.buildService(goog_cred);

            File body = new File();
            body.setTitle("My document");
            body.setDescription("A test document");
            body.setMimeType("text/plain");

            java.io.File fileContent = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\app\\Welcome.txt");
            FileContent mediaContent = new FileContent("text/plain", fileContent);

            File file = service2.files().insert(body, mediaContent).execute();
            System.out.println("File ID: " + file.getId());
            MyClass.printFile(service2,"0B2TDTGk9sqZLSWlaNklENUk1bjQ");

            /////////   This code will return the Meta Data for all the folder or files that meet the query setQ    \\\\\\\
            List<File> result = new ArrayList<File>();
            Drive.Files.List request = service2.files().list();
            request.setQ("title = 'stressbook'");

            do {
                try {
                    FileList files = request.execute();

                    result.addAll(files.getItems());
                    //System.out.println(result.size());
                    //System.out.println(files.getNextLink());
                    request.setPageToken(files.getNextPageToken());
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e);
                    request.setPageToken(null);
                }
            } while (request.getPageToken() != null &&
                    request.getPageToken().length() > 0);

            System.out.println("Title: " + result.get(0).getTitle());
            System.out.println(MyClass.downloadFile(service2,result.get(0)));
            MyClass.printFilesInFolder(service2,result.get(0).getId(), "");

            // This committed code will download an exile file and read it \\\\
            //ReadExcelScala newjs= new ReadExcelScala("" , "", " " );
            //newjs.fromExcelInput(MyClass.downloadFile(service2,result.get(0)));

            System.out.println(result.get(0).getParents());
            System.out.println(result.get(0).getDownloadUrl());

            //String downloadUrl = result.get(0).getExportLinks().get("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //System.out.println(downloadUrl);
            //////////////////////////////////////////////////////////////////////////////////////////

        } catch (MyClass.CodeExchangeException e) {
            e.printStackTrace();
        } catch (MyClass.NoRefreshTokenException e) {
            e.printStackTrace();
        }


        ////////////////////////////////////////

        */

        /* HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
                .setAccessType("online")
                .setApprovalPrompt("auto").build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

        //Create a new authorized API client
        Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();



        //Insert a file
       File body = new File();
        body.setTitle("My document");
        body.setDescription("A test document");
        body.setMimeType("text/plain");

        java.io.File fileContent = new java.io.File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\app\\Welcome.txt");
        FileContent mediaContent = new FileContent("text/plain", fileContent);

        File file = service.files().insert(body, mediaContent).execute();
        System.out.println("File ID: " + file.getId());*/
    }

}
