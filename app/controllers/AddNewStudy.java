package controllers;

import Models.*;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.json.simple.parser.JSONParser;
import play.Logger;
import play.libs.Akka;
import scala.Int;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.*;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by staamneh on 9/17/2015.
 */


public class AddNewStudy  {


    /*public static void parseJson(String data)
    {

        try {
            JSONObject json = (JSONObject) new JSONParser().parse(data);
            JSONObject arr = (JSONObject)json.get("secondadryExp");
            System.out.println(json.get("studyLocation"));
            System.out.println(json.get("covarite"));


            System.out.println(json.size());
            System.out.println(json.get("secondadryExp"));
            for(int i=1; i<= arr.size(); i++)
            {
                String indx = Integer.toString(i);
                System.out.println(arr.get(indx));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/

    public static String Malcolm (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code, int createPortrait, int shareStudy )  throws IOException, Exception
    {
        Logger.info("We start creating a new study called: " + studyName);
        String report = "";
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        AccessRefreshString acc  = DataBaseOperations.getStoredCredentials(username);
        Drive service =  GoogleDrive.buildService(googleCredential);

        /////share the folder with every body. Later it has to be some parameter to specify it the user want to make public or not


        List<String> subjects =  GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'");
        //test number of subjects

        PrintWriter writer = new PrintWriter("C:\\Users\\staamneh\\Documents\\Malcolm.txt", "UTF-8");




        for (String subject : subjects) {

            //File file0 = service.files().get(subject).execute();
            File file0 = GoogleDrive.waitUntilGetDGFile(service, subject);


            List<String> sessions =  GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");


            for(String sessionId : sessions)
            {
                int eda=0,motion=0,perspiration= 0, thermal_breath =0 , belt_breath=0, heart_rate =0, gsr=0;
                //File file = service.files().get(sessionId).execute();
                File file = GoogleDrive.waitUntilGetDGFile(service, sessionId);

                String sessionName = file.getTitle();
                sessionName = sessionName.replaceAll("\\s+", "");
                List<String> Signals =  GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                int signal_type =1 ;
                for(String signalId : Signals)
                {
                    boolean passed = false;
                    File file2 = null;


                            //file2 = service.files().get(signalId).execute();
                             file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);


                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    String extension = file2.getFileExtension();

                    if(SignalType.isSimulation(extension)) {

                        signal_type = SignalType.getSimulationCode();

                        InputStream input =  GoogleDrive.downloadFileByFileId(service, file2.getId());

                        MeanAndSizeOfSignal temp = ReadExcelJava.findMaxFromExcel(GoogleDrive.generateFileNameFromInputStream(input));

                        writer.write(file0.getTitle() + ": "+ sessionName +"\t" + temp.mean);

                        //belt_breath =1;
                    }
                    else continue; // skip if the singal is not supported
                }


            }

            //Check the missing Signal

            // searching for .Info and .pm files that reside in the root folde rof the subject



        }
        // }


        writer.close();
        return report;
    }

    public static String FindStudy (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code, int createPortrait, int shareStudy )  throws IOException, Exception
    {
        Logger.info("We start creating a new study called: " + studyName);
        String report = "";
        GoogleCredential googleCredential =  GoogleDrive.getStoredCredentials(username);
        AccessRefreshString acc  = DataBaseOperations.getStoredCredentials(username);
        Drive service =  GoogleDrive.buildService(googleCredential);

        /* List<Object> userDefinedExtension = DataBaseOperations.listOfFileExtension(username);*/


        /////share the folder with every body. Later it has to be some parameter to specify it the user want to make public or not
        if(shareStudy ==1) {
            //File fl = service.files().get(folder_id).execute();
            File fl = GoogleDrive.waitUntilGetDGFile(service, folder_id);
            Permission newPermission = new Permission();
            newPermission.setValue("");
            newPermission.setType("anyone");
            newPermission.setRole("reader");

            service.permissions().insert(fl.getId(), newPermission).execute();
        }

        List<String> subjects =  GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'");
        //test number of subjects
        if(subjects.size() > studyTopology.noOfSubjects())
            report = report + "Your selected " + studyTopology.noOfSubjects() + " as the number of subjects in your study but you have: " + subjects.size() + "\n";
        else if (subjects.size() < studyTopology.noOfSubjects() )
            report = report + "You have fewer subjects than the number you have selected\n";
        int study_no = 0;
        if(subjects.size() > 0 ) {
            study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, SharedData.GOOGLE_DRIVE,shareStudy, null, folder_id, null);
        }

        for (String subject : subjects) {

            //File file0 = service.files().get(subject).execute();
            File file0 = GoogleDrive.waitUntilGetDGFile(service, subject);
            DataBaseOperations.InsertSubjectGD(file0.getTitle(), study_no, subject, Psycho_code, physio_code);

            List<String> sessions =  GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            int session_no = 1;
            if(sessions.size() > studyTopology.noOfSession())
                report = report + "Subject <" + file0.getTitle() + "> Has more sessions than that specified\n";
            else if (sessions.size() < studyTopology.noOfSession() )
                report = report + "Subject<" + file0.getTitle() + "> Has fewer sessions than that specified\n";
            for(String sessionId : sessions)
            {
                int eda=0,motion=0,perspiration= 0, thermal_breath =0 , belt_breath=0, heart_rate =0, gsr=0;
                File file = service.files().get(sessionId).execute();

                String sessionName = file.getTitle();
                sessionName = sessionName.replaceAll("\\s+", "");
                List<String> Signals =  GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                int signal_type =1 ;
                for(String signalId : Signals)
                {
                    boolean passed = false;
                    File file2 = null;
                    file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);
                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    String extension = file2.getFileExtension();
                    if(SignalType.isEda(extension)){
                        if(studyTopology.physiology().EDA() !=1)
                            continue;
                        signal_type =SignalType.getEDACode();
                        eda= 1;
                    }
                    else if(SignalType.isMotion(extension)){
                        if(studyTopology.physiology().Motion() !=1)
                            continue;
                        signal_type = SignalType.getMotionCode();
                        motion=1;
                    }
                    else if(SignalType.isPerspiration(extension)){
                        if(studyTopology.physiology().Perspiration() !=1)
                            continue;
                        signal_type = SignalType.getPerspirationCode();
                        perspiration=1;
                    }
                    else if(SignalType.isBreathing(extension)){
                        if(studyTopology.physiology().Breathing_Thermal() !=1)
                            continue;
                        signal_type =SignalType.getBreathingCode();
                        thermal_breath =1;
                    }
                    else if(SignalType.isHeartRate(extension)){
                        if(studyTopology.physiology().Heart_Rate() !=1)
                            continue;
                        signal_type =SignalType.getHeartRateCode();
                        heart_rate=1;
                    }
                    else if(SignalType.isBeltBreathing(extension)) {
                        if(studyTopology.physiology().Breathing_Belt() !=1)
                            continue;
                        signal_type = SignalType.getBeltBreathingCode();
                        belt_breath =1;
                    }
                    else if(SignalType.isSimulation(extension)) {

                        signal_type = SignalType.getSimulationCode();
                        //belt_breath =1;
                    }
                    else if(SignalType.isNPerspiration(extension)) signal_type = SignalType.getNPerspirationCode();
                    else if(SignalType.isTemperature(extension)) signal_type = SignalType.getTemperatureCode();
                    else if(SignalType.isVideo(extension)) signal_type =SignalType.getVideoCode();
                        //else if(extension.toLowerCase().equals( "info")) signal_type =9;
                    else if(SignalType.isActivity(extension)) signal_type =SignalType.getActivityCode();
                    else if(SignalType.isHRV(extension)) signal_type =SignalType.getHRV();
                    else if(SignalType.isExpression(extension)) signal_type =SignalType.getExpression();
                    else if(SignalType.isEyeTracking(extension)) signal_type = SignalType.getEyeTrackingCode();


                    else continue; // skip if the singal is not supported

                    DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, sessionName, signal_type, file2.getId(), 0, false, 0);
                    //signal_type++;
                }
                session_no++;

                // To check if the study folder follow the rules that have ben enterd by the user
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

            // searching for .Info and .pm files that reside in the root folde rof the subject
            List<String> infos= GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");
            for(String info : infos) {
                //File fileInfo = service.files().get(info).execute();
                File fileInfo = GoogleDrive.waitUntilGetDGFile(service, info);
                // to skip conflict files that start with ~
                if(fileInfo.getTitle().contains("~"))
                    continue;
                String extension = fileInfo.getFileExtension();
                if(SignalType.isInfo(extension))
                {
                    DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "INFO", SignalType.getInfoCode() , fileInfo.getId(), 1, false , 0);
                }
                else if(SignalType.isPsychometric(extension))
                {
                    DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "PM", SignalType.getPsychometricCode() , fileInfo.getId(), 1, false, 0);
                }
                else if(SignalType.isPerfromance(extension))
                {
                    DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "PRF", SignalType.getPerformanceCode() , fileInfo.getId(), 1, false, 0);
                }
                else if(SignalType.isBar(extension)) {
                    DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "BAR", SignalType.getBarChatCode() , fileInfo.getId(), 1, false, 0);
                }

            }

        }
        // }


        if(subjects.size()>0) {
            report = "Study has been created successfully with the following warning:\n" + report;
            Logger.info("We finished creating a new study called: " + studyName);
            if (createPortrait ==1) {
                Logger.info("Calling studytoportart function");
                long startTime = System.nanoTime();
                //String query = studyToPortrat(folder_id, studyName, username, studyTopology, bio_code, Psycho_code, physio_code, study_no);
                String query ="";
                if(studyName.toLowerCase().contains("toyota"))
                    query = studyToPortratNewAlgo(folder_id, studyName, username, studyTopology, bio_code, Psycho_code, physio_code, study_no);
                else
                    query = studyToPortrat(folder_id, studyName, username, studyTopology, bio_code, Psycho_code, physio_code, study_no, null);

                Logger.debug("It takes: " +  (System.nanoTime() - startTime ) +  " To generate the portrait");
            }
        }
        else
            report = null;   // returning null to say that the study was not created correctely...


        return report;
    }


    public static String studyToPortrat (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int studyNo, Map <String, Int> filesExtensions )  throws IOException, Exception
    {


        //TODO search for singal that defined by user
        System.out.println("Welcome to the best application where people from all around word");
        String queryString = "", gender = "",traits = "0", grades= "", sBars= "", subjNames="", examsNames="";
        int sessionNO=1, flg1=0;;
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        Drive service = GoogleDrive.buildService(googleCredential);
        // get the names of subjects folders
        List<String> subjects = GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'");
        queryString = queryString + "subjects=" + subjects.size() + "&cols=3&hideButton=yes&title=" + studyName;


        for(int it=0 ;it<subjects.size()-1; it++)
            traits = traits + ",0";


        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++) {
            boolean passed = false;

                   // titlteWithId.put(service.files().get(subjects.get(i)).execute().getTitle(), subjects.get(i));
                    titlteWithId.put(GoogleDrive.waitUntilGetDGFile(service,subjects.get(i)).getTitle(), subjects.get(i));
                    passed=true;



        }

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        //ArrayList<BarPercentage> barForSessions = new ArrayList<>();
        TreeMap<String, BarPercentage> barForSessions = new TreeMap<>();
        ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();



        ArrayList<SharedData.SessionsBar> list1;
        ArrayList<SharedData.SessionsBar> list2 ;
        ArrayList<SharedData.SessionsBar> list3;
        ArrayList<SharedData.SessionsBar> list4;

        // to handle the situations where subjects do not have the same sessions
        ArrayList<String> uniqueSessionName = new ArrayList<>();

        ArrayList<ArrayList<SharedData.SessionsBar>> allList;
        TreeMap<String, String> titleWithIdSession;
        File file0= null, file =null;
        int session_no;
        boolean passed;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study

        while (it.hasNext()) {
            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();


            sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            // get all the session folder inside the current subject
            titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++) {
                passed =false;
                        titleWithIdSession.put( GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle(), sessions.get(i));
            }


            session_no = 1;
            if(sessions.size() > session_no)
                sessionNO = sessions.size();

            if(flg1!=0) {

                subjNames = subjNames + ",";
                gender = gender + ",";
            }
            flg1=1;
            int flg2=0;
             // subjNames = subjNames + file0.getTitle();
            subjNames = subjNames + pair.getKey().toString();


            //TODO include all the available signal not only the first 4
            list1 = new ArrayList<SharedData.SessionsBar>();
            list2 = new ArrayList<SharedData.SessionsBar>();
            list3 = new ArrayList<SharedData.SessionsBar>();
            list4 = new ArrayList<SharedData.SessionsBar>();


            TreeMap  <String,ArrayList<SharedData.SessionsBar>> allSignals = new TreeMap<>() ;

            googleCredential = GoogleDrive.getStoredCredentials(username);
            service = GoogleDrive.buildService(googleCredential);
            Iterator itSession = titleWithIdSession.entrySet().iterator();
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();



                flg2 = 1;



                // get all the signal inside the current session
                Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    passed = false;
                    File file2 = null;
                    file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);


                    String SessionName = pairSession.getKey().toString().replaceFirst("(\\d*\\s*)", "");

                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    extension = file2.getFileExtension();

                    if(filesExtensions.containsKey(extension)){

                        if(allSignals.containsKey(extension)){
                            allSignals.get(extension).add(new SharedData.SessionsBar( SessionName,file2.getId()));
                        }
                        else
                        {
                            ArrayList<SharedData.SessionsBar> tempList = new ArrayList<SharedData.SessionsBar>();
                            tempList.add(new SharedData.SessionsBar( SessionName,file2.getId()));
                            allSignals.put(extension,tempList);
                        }

                        //allSignals.put(extension, )
                    }


                   /* if(SignalType.isEda(extension) ){
                        if(studyTopology.physiology().EDA() !=1)
                            continue;
                        list3.add(new SharedData.SessionsBar( SessionName,file2.getId()));
                    }
                    else if(SignalType.isPerspiration(extension)){
                        if(studyTopology.physiology().Perspiration() !=1)
                            continue;
                        list4.add(new SharedData.SessionsBar( SessionName,file2.getId()));

                    }
                    else if(SignalType.isBreathing(extension)){
                        if(studyTopology.physiology().Breathing_Thermal() !=1)
                            continue;
                        list1.add(new SharedData.SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isHRV(extension)){
                        list2.add(new SharedData.SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isMotion(extension) ){
                        if(studyTopology.physiology().Motion() !=1)
                            continue;
                        //list2.add(file2.getId());
                    }*/
                }
                session_no++;
            }




            allList = new ArrayList<ArrayList<SharedData.SessionsBar>>();

            for(Map.Entry<String,ArrayList<SharedData.SessionsBar>> entry : allSignals.entrySet()) {
                //String key = entry.getKey();
                //Integer value = entry.getValue();
                allList.add(entry.getValue());
            }


              /*  if(list1.size() >0){

            }
            if(list2.size() >0){
                allList.add(list2);
            }
            if(list3.size() >0){
                allList.add(list3);
            }
            if(list4.size() >0){
                allList.add(list4);
            }*/


            //Check the missing Signal


            Logger.debug("We are going to call bar function");
            barForSessions = bar(username,allList, SharedData.GOOGLE_DRIVE, 4);
            allBarsForAllSubjs.add(barForSessions);






            // searching for .Info and .pm files that reside in the root folder rof the subject
            List<String> infos= GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");

            for(String info : infos) {
                //File fileInfo = service.files().get(info).execute();
                File fileInfo = GoogleDrive.waitUntilGetDGFile(service, info);

                // to skip conflict files that start with ~
                if (fileInfo.getTitle().contains("~"))
                    continue;
                extension = fileInfo.getFileExtension();

                if (SignalType.isInfo(extension)) {
                    Logger.info("Found info file    ");

                    InputStream input = GoogleDrive.downloadFileByFileId(service, fileInfo.getId());
                    if(input !=null) {
                        Scanner scanner = new Scanner(input);
                        int ctr = 1;
                        boolean firstOne = true;
                        Biography bio = AuxiliaryMethods.BiographyCode(bio_code);
                        while (scanner.hasNextLine()) {
                            if (ctr == 1 /*&& bio.Gender() == 1*/) {
                                String subjectGender = scanner.nextLine().toString();

                                if (subjectGender.toLowerCase().equals("male"))
                                    subjectGender = "M";
                                else if (subjectGender.toLowerCase().equals("female"))
                                    subjectGender = "F";
                                gender = gender + subjectGender;
                                break;
                            }
                            break;
                        }
                        scanner.close();
                    }
                }
            }
        }


        TreeMap<String, String> standardSessionList = new TreeMap<>();
        //   ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();
        int size=0;
        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            for(Map.Entry<String,BarPercentage> entry : tmp.entrySet()) {
                if(!standardSessionList.containsKey(entry.getKey())) {
                    // if(!standardSessionList.containsValue(entry.getKey().replaceFirst("(\\d*\\s*)", "")))
                    standardSessionList.put(entry.getKey().replaceFirst("(\\d*\\s*)", ""), " ");
                    System.out.println("@@@@@@" + entry.getKey());
                }
            }
        }


        sessionNO = standardSessionList.size();

        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            boolean isFirst = true;
            if(tmp.size()>0) {
                for(Map.Entry<String,String> entry : standardSessionList.entrySet()) {
                    // if(tmp.containsValue(entry.getKey().replaceFirst("(\\d*\\s*)", "")))
                    if (tmp.containsKey(entry.getKey()))
                    {
                        if (isFirst) {  // TODO some time we get NaN number therefore we should use  var == Double.NaN to check that
                            sBars = sBars + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).relaxed) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).normal) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).stressed);
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "NA";
                            isFirst = false;
                        } else {
                            sBars = sBars + ":" + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).relaxed) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).normal) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).stressed);
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + ",NA";
                        }
                    }else{

                        if(isFirst) {
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "NA";
                            isFirst = false;
                        }
                        else  {
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            sBars = sBars + ":";
                            grades = grades + ",NA";
                        }
                    }
                }
            }
            else{
                // sBars = sBars + ";";
            }
            sBars = sBars + ";";
            examsNames = examsNames + ";";
            grades = grades + ";";
        }





        if(subjects.size()>0) {
            //report = "Study has been created successfully with the following warning:\n" + report;
        }
        //else
        //report = "Not Found Folder";
        queryString = queryString + "&genders=" + gender +"&exams=" + sessionNO + "&traits=" + traits + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNames + "&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames;
        Logger.info(queryString);

        DataBaseOperations.InsertStudyPortraitString(studyNo, queryString);
        return queryString;
    }

    public static String studyToPortrat (Abstraction abstr,  int studyNo, Map <String, Int> filesExtensions )  throws IOException, Exception
    {


        //TODO search for singal that defined by user
        System.out.println("Welcome to the best application where people from all around word");
        String queryString = "", gender = "",traits = "0", grades= "", sBars= "", subjNames="", examsNames="";
        int sessionNO=1, flg1=0;;
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(abstr.userName());
        Drive service = GoogleDrive.buildService(googleCredential);
        // get the names of subjects folders
        List<String> subjects = GoogleDrive.returnFilesInFolder(service, abstr.studyLocation(), "mimeType = 'application/vnd.google-apps.folder'");
        queryString = queryString + "subjects=" + subjects.size() + "&cols=3&hideButton=yes&title=" + abstr.studyName();


        for(int it=0 ;it<subjects.size()-1; it++)
            traits = traits + ",0";


        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++) {
            boolean passed = false;

            // titlteWithId.put(service.files().get(subjects.get(i)).execute().getTitle(), subjects.get(i));
            titlteWithId.put(GoogleDrive.waitUntilGetDGFile(service,subjects.get(i)).getTitle(), subjects.get(i));
            passed=true;



        }

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        //ArrayList<BarPercentage> barForSessions = new ArrayList<>();
        TreeMap<String, BarPercentage> barForSessions = new TreeMap<>();
        ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();



        ArrayList<SharedData.SessionsBar> list1;
        ArrayList<SharedData.SessionsBar> list2 ;
        ArrayList<SharedData.SessionsBar> list3;
        ArrayList<SharedData.SessionsBar> list4;

        // to handle the situations where subjects do not have the same sessions
        ArrayList<String> uniqueSessionName = new ArrayList<>();

        ArrayList<ArrayList<SharedData.SessionsBar>> allList;
        TreeMap<String, String> titleWithIdSession;
        File file0= null, file =null;
        int session_no;
        boolean passed;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study

        while (it.hasNext()) {
            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();


            sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            // get all the session folder inside the current subject
            titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++) {
                passed =false;
                titleWithIdSession.put( GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle(), sessions.get(i));
            }


            session_no = 1;
            if(sessions.size() > session_no)
                sessionNO = sessions.size();

            if(flg1!=0) {

                subjNames = subjNames + ",";
                gender = gender + ",";
            }
            flg1=1;
            int flg2=0;
            // subjNames = subjNames + file0.getTitle();
            subjNames = subjNames + pair.getKey().toString();


            //TODO include all the available signal not only the first 4
            list1 = new ArrayList<SharedData.SessionsBar>();
            list2 = new ArrayList<SharedData.SessionsBar>();
            list3 = new ArrayList<SharedData.SessionsBar>();
            list4 = new ArrayList<SharedData.SessionsBar>();


            TreeMap  <String,ArrayList<SharedData.SessionsBar>> allSignals = new TreeMap<>() ;

            googleCredential = GoogleDrive.getStoredCredentials(abstr.userName());
            service = GoogleDrive.buildService(googleCredential);
            Iterator itSession = titleWithIdSession.entrySet().iterator();
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();



                flg2 = 1;



                // get all the signal inside the current session
                Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    passed = false;
                    File file2 = null;
                    file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);


                    String SessionName = pairSession.getKey().toString().replaceFirst("(\\d*\\s*)", "");

                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    extension = file2.getFileExtension();

                    if(filesExtensions.containsKey(extension)){

                        if(allSignals.containsKey(extension)){
                            allSignals.get(extension).add(new SharedData.SessionsBar( SessionName,file2.getId()));
                        }
                        else
                        {
                            ArrayList<SharedData.SessionsBar> tempList = new ArrayList<SharedData.SessionsBar>();
                            tempList.add(new SharedData.SessionsBar( SessionName,file2.getId()));
                            allSignals.put(extension,tempList);
                        }

                        //allSignals.put(extension, )
                    }


                   /* if(SignalType.isEda(extension) ){
                        if(studyTopology.physiology().EDA() !=1)
                            continue;
                        list3.add(new SharedData.SessionsBar( SessionName,file2.getId()));
                    }
                    else if(SignalType.isPerspiration(extension)){
                        if(studyTopology.physiology().Perspiration() !=1)
                            continue;
                        list4.add(new SharedData.SessionsBar( SessionName,file2.getId()));

                    }
                    else if(SignalType.isBreathing(extension)){
                        if(studyTopology.physiology().Breathing_Thermal() !=1)
                            continue;
                        list1.add(new SharedData.SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isHRV(extension)){
                        list2.add(new SharedData.SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isMotion(extension) ){
                        if(studyTopology.physiology().Motion() !=1)
                            continue;
                        //list2.add(file2.getId());
                    }*/
                }
                session_no++;
            }




            allList = new ArrayList<ArrayList<SharedData.SessionsBar>>();

            for(Map.Entry<String,ArrayList<SharedData.SessionsBar>> entry : allSignals.entrySet()) {
                //String key = entry.getKey();
                //Integer value = entry.getValue();
                allList.add(entry.getValue());
            }


              /*  if(list1.size() >0){

            }
            if(list2.size() >0){
                allList.add(list2);
            }
            if(list3.size() >0){
                allList.add(list3);
            }
            if(list4.size() >0){
                allList.add(list4);
            }*/


            //Check the missing Signal


            Logger.debug("We are going to call bar function");
            barForSessions = bar(abstr.userName(),allList, SharedData.GOOGLE_DRIVE, 4);
            allBarsForAllSubjs.add(barForSessions);






            // searching for .Info and .pm files that reside in the root folder rof the subject
            List<String> infos= GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");

            for(String info : infos) {
                //File fileInfo = service.files().get(info).execute();
                File fileInfo = GoogleDrive.waitUntilGetDGFile(service, info);

                // to skip conflict files that start with ~
                if (fileInfo.getTitle().contains("~"))
                    continue;
                extension = fileInfo.getFileExtension();

                if (SignalType.isInfo(extension)) {
                    Logger.info("Found info file    ");

                    InputStream input = GoogleDrive.downloadFileByFileId(service, fileInfo.getId());
                    if(input !=null) {
                        Scanner scanner = new Scanner(input);
                        int ctr = 1;
                        boolean firstOne = true;
                      //  Biography bio = AuxiliaryMethods.BiographyCode(bio_code);
                        while (scanner.hasNextLine()) {
                            if (ctr == 1 /*&& bio.Gender() == 1*/) {
                                String subjectGender = scanner.nextLine().toString();

                                if (subjectGender.toLowerCase().equals("male"))
                                    subjectGender = "M";
                                else if (subjectGender.toLowerCase().equals("female"))
                                    subjectGender = "F";
                                gender = gender + subjectGender;
                                break;
                            }
                            break;
                        }
                        scanner.close();
                    }
                }
            }
        }


        TreeMap<String, String> standardSessionList = new TreeMap<>();
        //   ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();
        int size=0;
        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            for(Map.Entry<String,BarPercentage> entry : tmp.entrySet()) {
                if(!standardSessionList.containsKey(entry.getKey())) {
                    // if(!standardSessionList.containsValue(entry.getKey().replaceFirst("(\\d*\\s*)", "")))
                    standardSessionList.put(entry.getKey().replaceFirst("(\\d*\\s*)", ""), " ");
                    System.out.println("@@@@@@" + entry.getKey());
                }
            }
        }


        sessionNO = standardSessionList.size();

        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            boolean isFirst = true;
            if(tmp.size()>0) {
                for(Map.Entry<String,String> entry : standardSessionList.entrySet()) {
                    // if(tmp.containsValue(entry.getKey().replaceFirst("(\\d*\\s*)", "")))
                    if (tmp.containsKey(entry.getKey()))
                    {
                        if (isFirst) {  // TODO some time we get NaN number therefore we should use  var == Double.NaN to check that
                            sBars = sBars + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).relaxed) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).normal) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).stressed);
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "NA";
                            isFirst = false;
                        } else {
                            sBars = sBars + ":" + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).relaxed) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).normal) + "," + new DecimalFormat("#.##").format(tmp.get(entry.getKey()).stressed);
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + ",NA";
                        }
                    }else{

                        if(isFirst) {
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "NA";
                            isFirst = false;
                        }
                        else  {
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            sBars = sBars + ":";
                            grades = grades + ",NA";
                        }
                    }
                }
            }
            else{
                // sBars = sBars + ";";
            }
            sBars = sBars + ";";
            examsNames = examsNames + ";";
            grades = grades + ";";
        }





        if(subjects.size()>0) {
            //report = "Study has been created successfully with the following warning:\n" + report;
        }
        //else
        //report = "Not Found Folder";
        queryString = queryString + "&genders=" + gender +"&exams=" + sessionNO + "&traits=" + traits + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNames + "&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames;
        Logger.info(queryString);

        DataBaseOperations.InsertStudyPortraitString(studyNo, queryString);
        return queryString;
    }


    public static String studyToPortratNewAlgo (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int studyNo )  throws IOException, Exception
    {
        String queryString = "", gender = "",traits = "0", grades= "", sBars= "", subjNames="", examsNames="";
        int sessionNO=1, flg1=0;
        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        Drive service = GoogleDrive.buildService(googleCredential);

        Double failureThreshold = findLoadedDrivingPoint(folder_id, username);

        // get the names of subjects folders
        List<String> subjects = GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'");

        for(int it=0 ;it<subjects.size()-1; it++)
            traits = traits + ",0";

        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++)
            titlteWithId.put(GoogleDrive.waitUntilGetDGFile(service, subjects.get(i)).getTitle(), subjects.get(i));

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();
        ArrayList<TreeMap<String, Double>> allPerformanceForAllSubs = new ArrayList<>();
        ArrayList<String> SubjectNames = new ArrayList<>();
        ArrayList<TreeMap<String, Double>> allPsychometricForAllSubs = new ArrayList<>();

        ArrayList<Double> maxes = new ArrayList<Double>();
        ArrayList<String> names = new ArrayList<String>();


        TreeMap<String, String> titleWithIdSession;
        File file0= null, file =null;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study
        while (it.hasNext()) {
            ArrayList<String> baseLineSignalsStress= new ArrayList<>(), baseLineSignalsPerformance = new ArrayList<>();

            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();

            file0 = GoogleDrive.waitUntilGetDGFile(service, subject);
            sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            // get all the session folder inside the current subject
            titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++)
                titleWithIdSession.put(GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle(), sessions.get(i));

            if(flg1!=0) {
                subjNames = subjNames + ",";
                gender = gender + ",";
            }
            flg1=1;
            subjNames = subjNames + file0.getTitle();
            ArrayList<SharedData.SessionsBar> signalsForIndicator = new ArrayList<>();
            ArrayList<SharedData.SessionsBar> signalsForPerformance= new ArrayList<>();

            googleCredential = GoogleDrive.getStoredCredentials(username);
            service = GoogleDrive.buildService(googleCredential);
            Iterator itSession = titleWithIdSession.entrySet().iterator();
            String ld1FileName=null, ld1SessionName=null, fdFilenmae = null, fdSessionName = null;
            ArrayList<Activity> fdActList = null;

            // Iterate over the session folder for the current subject
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();

                file = GoogleDrive.waitUntilGetDGFile(service, sessionId);

                // get all the signal inside the current session
                Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    File file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);

                    // String SessionName = file.getTitle().replaceFirst("(\\d*\\s*)", "");
                    String SessionName = file.getTitle();

                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    extension = file2.getFileExtension();
                    // if(SignalType.isSimulation(extension)) {
                    // I agree with malcolm that we read the sim for portrait from sim2 while for vis form sim
                    if(extension.equalsIgnoreCase("sim2")) {
                        if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd")|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd"))
                        {
                            InputStream input = GoogleDrive.downloadFileByFileId(service, file2.getId());
                            baseLineSignalsPerformance.add(GoogleDrive.generateFileNameFromInputStream(input));

                            if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1")) {
                                signalsForPerformance.add(new SharedData.SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId()));

                            }
                        }
                        else if(! org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl"))
                            signalsForPerformance.add(new SharedData.SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId()));
                    }

                    if(SignalType.isPerspiration(extension) || SignalType.isNPerspiration(extension)){
                        if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd")|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd")) {
                            InputStream input = GoogleDrive.downloadFileByFileId(service, file2.getId());
                            baseLineSignalsStress.add(GoogleDrive.generateFileNameFromInputStream(input));
                        }
                        else if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")){
                            InputStream input = GoogleDrive.downloadFileByFileId(service, file2.getId());

                            fdFilenmae = GoogleDrive.generateFileNameFromInputStream(input);
                            fdSessionName = SessionName;
                        }
                        else {
                            if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1")){
                                InputStream input = GoogleDrive.downloadFileByFileId(service, file2.getId());
                                ld1FileName = GoogleDrive.generateFileNameFromInputStream(input);
                                ld1SessionName = SessionName;
                            }
                            else
                                signalsForIndicator.add(new SharedData.SessionsBar(SessionName, file2.getId()));

                        }
                    }
                    if(SignalType.isActivity(extension) && org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")){
                        fdActList = ReadExcelJava.readActivity(GoogleDrive.downloadFileByFileId(service, file2.getId()));
                    }

                }
            }

            // Get the stress indicator for all sessions of the current subject

            //double threshold =  tws.threshold;


            MeanAndSizeOfSignal temp;
            double threshold;
            if(ld1FileName != null){
                temp = ReadExcelJava.findMeanFromExcel(ld1FileName);
                threshold = temp.mean;
            }
            else {
                threshold = 0;
            }

            StressBarWithThreshold tws = getStressThreshold(baseLineSignalsStress, threshold);
            TreeMap<String, BarPercentage> tt = getPortraitStateIndiactors(username,threshold, signalsForIndicator, SharedData.GOOGLE_DRIVE, 4);
            //tt.put("0TL",  new BarPercentage(0.0, 100, 0.0));
            if(ld1SessionName != null)
                tt.put(ld1SessionName, new BarPercentage(0.0, 100, 0.0));
            System.out.println("Threshold: " + failureThreshold + "*********************");


            BarPercentage bp = ReadExcelJava.findBarFromExcelWithActivity(failureThreshold, fdFilenmae, fdActList);


            System.out.println("Normal: " + bp.normal + ">>>>>  " + bp.stressed);
            tt.put(fdSessionName, bp);



            allBarsForAllSubjs.add(tt);
            SubjectNames.add(file0.getTitle());




            /////////////////////////////Calculate for Dr. P///////////////////////////////////////////
            /*double max =0;
            for( SessionsBar tem : signalsForIndicator) {
                InputStream input = downloadFileByFileId(service, tem.location);
                MeanAndSizeOfSignal t = ReadExcelJava.findMeanFromExcel(generateFileNameFromInputStream(input));
                double mean = t.mean;
                if(mean-threshold > max) max= mean-threshold;
            }

            maxes.add(max);
            names.add(file0.getTitle());*/
            ///////////////////////////////////////////////////////////////////////////////////////////

            // Get the perfromance for all sessions of the current subject
            TreeMap<String, Double> tt2 = getPortraitPerformance(username, getPerformanceThreshold(baseLineSignalsPerformance), signalsForPerformance, SharedData.GOOGLE_DRIVE, 4);
            tt2.put("TL", 0.0);
            allPerformanceForAllSubs.add(tt2);

            allPsychometricForAllSubs.add(generatePsychometricForPortrait(service, GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'")));

            // searching for .Info and .pm files that reside in the root folder rof the subject
            gender = gender + generateGenderForPortrait (service, GoogleDrive.returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), bio_code);
        } // next subject


        // queryString = queryString + "&genders=" + gender +"&exams=" + sessionNO + "&traits=" + traits + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNames + "&titleGrades=Perf.&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames;
        queryString = generateQueryString(allBarsForAllSubjs, allPerformanceForAllSubs, allPsychometricForAllSubs,  subjects.size(),studyName, gender, sessionNO, traits, grades, sBars, subjNames, studyNo, examsNames);
        Logger.info(queryString);

        DataBaseOperations.InsertStudyPortraitString(studyNo, queryString);

        ///////////////////////////to write to external file the data that dr.p wants to see ////////////////////////

      /*  PrintWriter writer = new PrintWriter("C:\\Users\\staamneh\\Documents\\P.txt", "UTF-8");
        int index =0;
        for( Double tem : maxes) {
            writer.write(names.get(index)+": " +tem.toString());
            index++;
            writer.write("\n");
        }

        writer.close();


         writer = new PrintWriter("C:\\Users\\staamneh\\Documents\\performance.txt", "UTF-8");
        int i =0;
        for(TreeMap<String, Double> temp : allPerformanceForAllSubs){
            writer.write(SubjectNames.get(i)+ "\t");
            for(Map.Entry<String,Double> entry : temp.entrySet()){
                writer.write(entry.getKey() + ": "+ entry.getValue()+"\t");
            }
            writer.write("\n");
            i++;
        }
        writer.close();

        i =0;
        writer = new PrintWriter("C:\\Users\\staamneh\\Documents\\NASA.txt", "UTF-8");
        for(TreeMap<String, Double> temp : allPsychometricForAllSubs){
            writer.write(SubjectNames.get(i)+ "\t");
            for(Map.Entry<String,Double> entry : temp.entrySet()){
                writer.write(entry.getValue()+"\t");
            }
            writer.write("\n");
            i++;
        }
        writer.close();

       */
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        return queryString;
    }


    public static Double findLoadedDrivingPoint(String folder_id,  String username )  throws  Exception
    {

        GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
        Drive service = GoogleDrive.buildService(googleCredential);

        // get the names of subjects folders
        List<String> subjects = GoogleDrive.returnFilesInFolder(service, folder_id, "mimeType = 'application/vnd.google-apps.folder'");
        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++)
            titlteWithId.put(GoogleDrive.waitUntilGetDGFile(service, subjects.get(i)).getTitle(), subjects.get(i));

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        Double startofLoadedDriveSum = 0.0;
        Double startofLoadedDriveCtr =0.0;

        TreeMap<String, String> titleWithIdSession;
        File  file =null;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study
        while (it.hasNext()) {

            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();

            sessions = GoogleDrive.returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            // get all the session folder inside the current subject
            titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++)
                titleWithIdSession.put(GoogleDrive.waitUntilGetDGFile(service, sessions.get(i)).getTitle(), sessions.get(i));

            Iterator itSession = titleWithIdSession.entrySet().iterator();
            // Iterate over the session folder for the current subject
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();

                file = GoogleDrive.waitUntilGetDGFile(service, sessionId);

                // get all the signal inside the current session
                Signals = GoogleDrive.returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    File file2 = GoogleDrive.waitUntilGetDGFile(service, signalId);

                    // String SessionName = file.getTitle().replaceFirst("(\\d*\\s*)", "");
                    String SessionName = file.getTitle();

                    if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "fd")) {


                        // to skip conflict files that start with ~
                        if (file2.getTitle().contains("~"))
                            continue;
                        extension = file2.getFileExtension();
                        if (SignalType.isActivity(extension)) {
                            InputStream input = GoogleDrive.downloadFileByFileId(service, file2.getId());
                            //baseLineSignalsStress.add(generateFileNameFromInputStream(input));
                            ArrayList<Activity> temp = ReadExcelJava.readActivity(input);


                            for(Activity ac : temp){
                                if(ac.actionType == 5){
                                    startofLoadedDriveSum +=ac.startTime;
                                    startofLoadedDriveCtr++;
                                }
                            }

                        }

                        //  break;
                    }

                }
            }

        } // next subject


        return (startofLoadedDriveSum/startofLoadedDriveCtr);
    }


    public static TreeMap<String, Double> generatePsychometricForPortrait(Drive service, List<String> infos)throws IOException, Exception {

        TreeMap<String,Double> sessionCutoff = new TreeMap<String, Double>();
        String gender = "";
        for(String info : infos) {
            //File fileInfo = service.files().get(info).execute();
            File fileInfo = GoogleDrive.waitUntilGetDGFile(service, info);

            // to skip conflict files that start with ~
            if (fileInfo.getTitle().contains("~"))
                continue;
            String extension = fileInfo.getFileExtension();

            if (SignalType.isBar(extension)) {
                Logger.info("Found info file    ");
                InputStream input = GoogleDrive.downloadFileByFileId(service, fileInfo.getId());
                if (input != null) {
                    ArrayList<Double> tmp = ReadExcelJava.findTotalNASA(11, GoogleDrive.generateFileNameFromInputStream(input));
                    ////////////////////// Hard codede //////////////////////////////////////////////////////////////
                    if(tmp.size()> 0)
                        sessionCutoff.put("ND", tmp.get(0));
                    if(tmp.size()> 1)
                        sessionCutoff.put("LD1", tmp.get(1));
                    if(tmp.size()> 2)
                        sessionCutoff.put("LD2", tmp.get(2));
                    if(tmp.size()> 3)
                        sessionCutoff.put("LD3", tmp.get(3));
                    if(tmp.size()> 4)
                        sessionCutoff.put("LD4", tmp.get(4));
                    if(tmp.size()> 5)
                        sessionCutoff.put("FD", tmp.get(5));
                    /////////////////////////////////////////////////////////////////////////////////////////////////
                }
                break;
            }

        }
        return sessionCutoff;
    }

    /**
     * This function recieve a list of location for baseline signal and return a number that split between stressed and normal states
     * @param list
     * @return
     * @throws Exception
     */
    public static double getPerformanceThreshold(ArrayList<String> list) throws Exception
    {
        double max =0, sum =0, counter = 0;
        for(String fileName: list){
            MeanAndSizeOfSignal temp = ReadExcelJava.findAbsoluteMeanFromExcel(fileName);


            //if (temp > max) max = temp;
            sum = sum + (temp.mean * temp.size);
            counter += temp.size;
        }

        return  sum/ counter;
    }


    public static double getPerformanceThresholdAbstraction(ArrayList<String> list, int respColNo) throws Exception
    {
        double max =0, sum =0, counter = 0;
        for(String fileName: list){
            MeanAndSizeOfSignal temp = ReadExcelJava.findAbsoluteMeanFromExcelAbstraction(fileName, respColNo);


            //if (temp > max) max = temp;
            sum = sum + (temp.mean * temp.size);
            counter += temp.size;
        }

        return  sum/ counter;
    }

    private static class StressBarWithThreshold{
        private double threshold;
        private BarPercentage stressBar;

        StressBarWithThreshold(double th, BarPercentage stress)
        {
            threshold = th;
            stressBar = stress;
        }
    }

    public static StressBarWithThreshold getStressThreshold(ArrayList<String> list, double threshold) throws Exception
    {
        //double threshold =0;
        double sum =0, counter = 0;
        ArrayList<Double> allNumber = new ArrayList<Double>();
        for(String fileName: list){
            MeanAndSizeOfSignal temp = ReadExcelJava.findMeanFromExcel(fileName);
            sum = sum + (temp.mean * temp.size);
            counter += temp.size;
            allNumber.addAll(temp.allNum);
        }
        // threshold = sum/ counter;


        double normal = 0, stress = 0;
        for(double num: allNumber){
            if (num < threshold) {
                normal++;
            } else
                stress++;
        }

        int total = allNumber.size();

        normal = (normal / total) * 100;
        stress = (stress / total) * 100;


        BarPercentage b = new BarPercentage(0.0, normal, stress);
        return  new StressBarWithThreshold(threshold, b);
    }


    public static String generateQueryString(ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs, ArrayList<TreeMap<String, Double>> allPerformanceForAllSubs,ArrayList<TreeMap<String, Double>> allPsychometricForAllSubs, int noOfSub, String studyName, String gender, int sessionNO, String traits, String grades, String sBars, String subjNames, int studyNo, String examsNames){

        String queryString = "subjects=" + noOfSub + "&cols=3&hideButton=yes&title=" + studyName;

        String tai = "";
        TreeMap<String, String> standardSessionListOrdered = new TreeMap<>();
        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            for(Map.Entry<String,BarPercentage> entry : tmp.entrySet()) {
                if(!standardSessionListOrdered.containsValue(entry.getKey().replaceFirst("(\\d*\\s*)", ""))) {
                    standardSessionListOrdered.put(entry.getKey(), entry.getKey().replaceFirst("(\\d*\\s*)", ""));
                    System.out.println("@@@@@@" + entry.getKey());
                }
            }
        }

        sessionNO = standardSessionListOrdered.size()-1;
        int itr =0 ;

        // iterate over all the subject that we calaucuated their state indicator and saved it in a map of session name and bar
        for( TreeMap<String, BarPercentage> tmp: allBarsForAllSubjs){
            TreeMap<String, Double> prf = allPerformanceForAllSubs.get(itr);  // get the set of performance for the current subject
            TreeMap<String, Double> sai_list = allPsychometricForAllSubs.get(itr);  // get the set of performance for the current subject
            itr ++;
            boolean isFirst = true;
            if(tmp.size()>0) {
                for(Map.Entry<String,String> entry : standardSessionListOrdered.entrySet()) {
                    boolean isthere = false;
                    long perf_temp =0;
                    String performance ="NA", sai_temp = "NA";
                    BarPercentage theOne = null;
                    // iterate over all the session of a particular subject to check is he has value for session: entry.getValue()
                    for(Map.Entry<String,BarPercentage> tem : tmp.entrySet()) {
                        if(tem.getKey().replaceFirst("(\\d*\\s*)","").equals(entry.getValue())) {
                            isthere =true;
                            theOne = tem.getValue();
                            if(prf.containsKey(entry.getValue()) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(entry.getKey(),"tl")) {
                                performance= Long.toString(Math.round( prf.get(entry.getValue())));
                            }
                            String neededKey = entry.getValue();
                            if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(entry.getKey(),"fd")) neededKey = "FD";
                            if(sai_list.containsKey(neededKey) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(neededKey,"tl")) {
                                sai_temp= Long.toString(Math.round( sai_list.get(neededKey)));
                            }
                        }
                    }
                    if (isthere)
                    {
                        if (isFirst) {  // TODO some time we get NaN number therefore we should use  var == Double.NaN to check that
                            sBars = sBars + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed);
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + performance;
                            tai = tai + sai_temp;
                            isFirst = false;
                        } else {
                            sBars = sBars + ":" + new DecimalFormat("#.##").format(theOne.relaxed) + "," + new DecimalFormat("#.##").format(theOne.normal) + "," + new DecimalFormat("#.##").format(theOne.stressed);
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "," + performance;
                            tai = tai + ":" + sai_temp;
                        }
                    }else {

                        if (!org.apache.commons.lang3.StringUtils.containsIgnoreCase(entry.getValue(), "fd")) // this is used to show only one of the filure drive
                        {
                            if (isFirst) {
                                examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                                grades = grades + "NA";
                                tai = tai + "NA";
                                isFirst = false;
                            } else {
                                examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                                sBars = sBars + ":";
                                grades = grades + ",NA";
                                tai = tai + ":NA";
                            }
                        }
                    }
                }
            }
            else{
                // sBars = sBars + ";";
            }
            sBars = sBars + ";";
            examsNames = examsNames + ";";
            grades = grades + ";";
            tai = tai + ";";
        }

        queryString = queryString + "&genders=" + gender +"&exams=" + sessionNO + "&traits=" + traits + "&SAIs=" + tai + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNames + "&titleGrades=ST&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames;
        return queryString;
    }

    public static String generateGenderForPortrait (Drive service, List<String> infos, int bio_code)throws IOException, Exception {


        String gender = "";
        for(String info : infos) {
            //File fileInfo = service.files().get(info).execute();
            File fileInfo = GoogleDrive.waitUntilGetDGFile(service, info);
            // to skip conflict files that start with ~
            if (fileInfo.getTitle().contains("~"))
                continue;
            String extension = fileInfo.getFileExtension();

            if (SignalType.isInfo(extension)) {
                Logger.info("Found info file    ");

                InputStream input = GoogleDrive.downloadFileByFileId(service, fileInfo.getId());
                if(input !=null) {
                    Scanner scanner = new Scanner(input);
                    Biography bio = AuxiliaryMethods.BiographyCode(bio_code);
                    while (scanner.hasNextLine()) {
                        if ( bio.Gender() == 1) {
                            String subjectGender = scanner.nextLine().toString();
                            if (subjectGender.toLowerCase().equals("male"))
                                subjectGender = "M";
                            else if (subjectGender.toLowerCase().equals("female"))
                                subjectGender = "F";
                            gender = subjectGender;
                            break;
                        }
                        break;
                    }
                    scanner.close();
                }
            }
        }
        return gender;
    }

    /**
     * This fucntion is used to get the peformance in eash session for a particulare subject5
     * @param username
     * @param max
     * @param url This is a list of session along with the location of the sim file in each session for a particuler subject
     * @param sourceType
     * @param signalType
     * @return The performance in each session for a subject
     * @throws IOException
     * @throws Exception
     */
    public static  TreeMap<String, Double>  getPortraitPerformance(String username, double max, ArrayList<SharedData.SessionsBar> url, int sourceType, int signalType)  throws IOException,  Exception
    {
        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        //ArrayList<BarPercentage> sessionCutoff = new ArrayList<BarPercentage>();
        TreeMap<String,Double> sessionCutoff = new TreeMap<String, Double>();
        if (sourceType == SharedData.LOCALSERVER) //TODO
        {
            return sessionCutoff;
        }
        else {   // read from google drive
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
                Drive service = GoogleDrive.buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {

                    InputStream input = GoogleDrive.downloadFileByFileId(service, url.get(i).location);
                    sessionCutoff.put(url.get(i).name, ReadExcelJava.findPerformanceFromExcel(max, GoogleDrive.generateFileNameFromInputStream(input)));
                }
            }catch (IndexOutOfBoundsException e) {
                Logger.error("In Bar method-Outer loop: index out of bound");
            }

            return sessionCutoff;
        }
    }
    /**
     * This fucntion take the location of the perspiration file for each sessions and return the state cut-off for each one of them
     * @param username
     * @param max
     * @param url
     * @param sourceType
     * @param signalType
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static  TreeMap<String, BarPercentage>  getPortraitStateIndiactors(String username, double max, ArrayList<SharedData.SessionsBar> url, int sourceType, int signalType)  throws IOException,  Exception
    {

        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        //ArrayList<BarPercentage> sessionCutoff = new ArrayList<BarPercentage>();
        TreeMap<String,BarPercentage> sessionCutoff = new TreeMap<String, BarPercentage>();
        if (sourceType == SharedData.LOCALSERVER) //TODO
        {
            return sessionCutoff;
        }
        else {   // read from google drive
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
                Drive service = GoogleDrive.buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {

                    InputStream input = GoogleDrive.downloadFileByFileId(service, url.get(i).location);
                    sessionCutoff.put(url.get(i).name, ReadExcelJava.findBarFromExcel(max, GoogleDrive.generateFileNameFromInputStream(input)));
                }
            }catch (IndexOutOfBoundsException e) {
                Logger.error("In Bar method-Outer loop: index out of bound");
            }

            return sessionCutoff;
        }
    }

    public static  TreeMap<String, BarPercentage>  bar(String username,ArrayList<ArrayList<SharedData.SessionsBar>> url, int sourceType, int signalType)  throws IOException,  Exception
    {
        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        TreeMap<String,BarPercentage> sessionCutoff = new TreeMap<String, BarPercentage>();
        if (sourceType == SharedData.LOCALSERVER)
        {
            for(int i =0 ; i< url.size(); i++) {
                for(int j=0 ; j < url.get(i).size(); j++) {
                    Logger.debug("Portrait: download the signal from the server, Signal Type is: \" + signalType);");
                    InputStream input = new BufferedInputStream(
                            new FileInputStream(url.get(i).get(j).location));
                    //all.add(input);
                    signal.put(url.get(i).get(j).name, "  ");

                    ActorRef myActor = Akka.system().actorOf(Props.create(ReadExcelScala.class, signalType, input));
                    Timeout timeout = new Timeout(Duration.create(100, "seconds"));
                    Future<Object> future = Patterns.ask(myActor, signalType, timeout);

                }
                //resl.add(ReadExcelJava.fromExceForPortrait(all, signalType));
                resl.add(ReadExcelJava.fromExceForPortraitTemp(signal, signalType));
            }

            return sessionCutoff;
        }
        else {   // read from google drive
            System.out.println("I am in bar function - GDrive");
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = GoogleDrive.getStoredCredentials(username);
                Drive service = GoogleDrive.buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {
                    signal = new TreeMap<>();
                    try {
                        for (int j = 0; j < url.get(i).size(); j++) {
                            InputStream input = GoogleDrive.downloadFileByFileId(service, url.get(i).get(j).location);
                            if(input != null) {
                                //all.add(input); // TODO save the strems in a folder and then pass that folder for readExcelJava
                                signal.put(url.get(i).get(j).name, GoogleDrive.generateFileNameFromInputStream(input));
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Logger.error("In Bar method-Inner loop: index out of bound");
                    }
                    Logger.debug("Portrait: Send all input streams for all session to -fromExcelForPortrait-  Function");
                    //resl.add(ReadExcelJava.fromExceForPortrait(signal, signalType));
                    resl.add(ReadExcelJava.fromExceForPortraitTemp(signal, signalType));
                }
            }catch (IndexOutOfBoundsException e) {
                Logger.error("In Bar method-Outer loop: index out of bound");
            }
            Iterator it = resl.iterator();
            TreeMap<String, BarPercentage> temp;
            TreeMap<String, Integer> counter = new TreeMap<>();
            BarPercentage tempBar;
            while(it.hasNext()){
                temp = (TreeMap<String, BarPercentage>) it.next();

                for(Map.Entry<String, BarPercentage> entry : temp.entrySet()) {
                    if(sessionCutoff.containsKey(entry.getKey())){
                        tempBar = new BarPercentage(entry.getValue().relaxed + sessionCutoff.get(entry.getKey()).relaxed ,entry.getValue().normal +sessionCutoff.get(entry.getKey()).normal , entry.getValue().stressed + sessionCutoff.get(entry.getKey()).stressed);
                        sessionCutoff.put(entry.getKey(),tempBar);
                        //sessionCutoff.replace(entry.getKey(),tempBar);
                        // to keep track of the number singals for a specific session. This will be used later for calculation


                        //counter.replace(entry.getKey(), counter.get(entry.getKey()) + 1);
                        counter.put(entry.getKey(), counter.get(entry.getKey()) + 1);
                    }
                    else{
                        sessionCutoff.put(entry.getKey(),entry.getValue());
                        counter.put(entry.getKey(), 1);
                    }
                }
            }

            for(Map.Entry<String, BarPercentage> entry : sessionCutoff.entrySet()) {
                int ctr = counter.get(entry.getKey());
                tempBar = new BarPercentage(entry.getValue().relaxed /ctr,entry.getValue().normal /ctr , entry.getValue().stressed/ ctr);
                //sessionCutoff.replace(entry.getKey(),tempBar);
                sessionCutoff.put(entry.getKey(), tempBar);
            }


            return sessionCutoff;
        }
    }


    public static void FindStudyLocalServer (String studyName,String url, String username, int noOfSession,  StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code )  throws IOException
    {
        final java.io.File study = new java.io.File(url);
        if(study.exists()) {
            Logger.info("We Found it");
            // Insert new study in the DB
            int study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, SharedData.LOCALSERVER, 0, null, url, null);
            // Get all the subjects in that study
            for (final java.io.File subject : study.listFiles()) {
                String subjectName = subject.getName();
                if (subject.isDirectory()) {
                    System.out.println("Subject folder path: " + subject.getAbsolutePath());
                    //Inset subjects into the new study
                    DataBaseOperations.InsertSubjectGD(subject.getName(), study_no,subject.getAbsolutePath(),Psycho_code,physio_code);
                    int session_no = 1;

                    // if the noOfSession is 0 we assume ther is no session and signal will be in the subject folder
                    if(noOfSession !=0) {
                        // get all the session inside a subject
                        for (final java.io.File session : subject.listFiles()) {
                            String sessionName = session.getName();
                            sessionName = sessionName.replaceAll("\\s+", "");
                            System.out.println("Session Folder Name: " + sessionName);
                            if (session.isDirectory()) {
                                int signal_type = 1;
                                //get all the signals inside a session
                                for (final java.io.File signal : session.listFiles()) {
                                    int pos = signal.getAbsolutePath().toString().lastIndexOf('.');
                                    String fileExtension = signal.getAbsolutePath().substring(pos + 1);
                                    System.out.println("Extension: " + fileExtension);
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
                                    DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, signal_type, signal.getAbsolutePath(),0, false,0);
                                }
                            }
                            else {

                                int pos = session.getAbsolutePath().toString().lastIndexOf('.');
                                String fileExtension = session.getAbsolutePath().substring(pos + 1);
                                System.out.println("Extension: " + fileExtension);
                                switch (fileExtension.toLowerCase()) {
                                    case "info":
                                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no,"INFO", 9 , session.getAbsolutePath(),1, false, 0);
                                        break;
                                    case "pm":
                                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no,"PM", 11 , session.getAbsolutePath(),1, false, 0);
                                        break;
                                    default:
                                        Logger.error("Invlaid File Extension: " + fileExtension );
                                }

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
                                case "pm":
                                    signal_type =11;
                                    break;
                                default:
                                    Logger.error("Invlaid File Extension: " + fileExtension );
                            }
                            //insert signal for the new study
                            if(signal_type == 9)
                            {
                                DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "INFO", signal_type, signal.getAbsolutePath(),1, false,0);
                            }
                            else if (signal_type ==11)
                            {
                                DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "PM", signal_type, signal.getAbsolutePath(),1, false, 0);
                            }
                            else
                                DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "None", signal_type, signal.getAbsolutePath(),1, false, 0);
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


}
