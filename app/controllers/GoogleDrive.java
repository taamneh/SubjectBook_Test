package controllers;

/**
 * Created by staamneh on 10/6/2014.
 */

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import org.json.simple.JSONObject;
import Models.*;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.model.File;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import play.Logger;

import play.libs.Akka;

import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;


import java.io.*;
import java.lang.String;
import java.lang.System;
import java.util.*;



// the reason for this class is that google only support java API



public class GoogleDrive {

    //private static final String CLIENTSECRETS_LOCATION = "C:\\Users\\Salah\\Desktop\\Cpl\\SubjectBook\\first_play\\app\\client_secrets.json";
    //private static final String CLIENTSECRETS_LOCATION = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\app\\client_secrets.json";
    private static final String CLIENTSECRETS_LOCATION = "C:\\first_play\\app\\client_secrets.json";


    //private static final String REDIRECT_URI = "http://subjectbook.times.uh.edu/display";
    private static final String REDIRECT_URI = "http://testsubjectbook.ddns.net/display";
    private static final List<String> SCOPES = Arrays.asList(
            //"https://www.googleapis.com/auth/drive.file",
            "https://www.googleapis.com/auth/drive",
            "https://www.googleapis.com/auth/userinfo.email",
            "https://www.googleapis.com/auth/userinfo.profile");

    private static GoogleAuthorizationCodeFlow flow = null;

    private final static int GOOGLE_DRIVE = 1;
    private final static int LOCALSERVER = 2;
    private final static String CLIENT_ID = "591248185477-ko9n5elh2863ahrj4h3cfpqpn6ct53a0.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "tfuh8wcEzcBeJKA2Qu35PKAt";


    public static String authorizationUrl;

    private static class SessionsBar{
        public String name;
        public String location;

        public SessionsBar(String a, String b)
        {
            name = a;
            location = b;
        }
    }

    public static class GetCredentialsException extends Exception {

        protected String authorizationUrl;

        /**
         * Construct a GetCredentialsException.
         *
         * @param authorizationUrl The authorization URL to redirect the user to.
         */
        public GetCredentialsException(String authorizationUrl) {
            this.authorizationUrl = authorizationUrl;
        }

        /**
         * Set the authorization URL.
         */
        public void setAuthorizationUrl(String authorizationUrl) {
            this.authorizationUrl = authorizationUrl;
        }

        /**
         * @return the authorizationUrl
         */
        public String getAuthorizationUrl() {
            return authorizationUrl;
        }
    }

    /**
     * Exception thrown when a code exchange has failed.
     */
    public static class CodeExchangeException extends GetCredentialsException {

        /**
         * Construct a CodeExchangeException.
         *
         * @param authorizationUrl The authorization URL to redirect the user to.
         */
        public CodeExchangeException(String authorizationUrl) {
            super(authorizationUrl);
        }

    }

    /**
     * Exception thrown when no refresh token has been found.
     */
    public static class NoRefreshTokenException extends GetCredentialsException {

        /**
         * Construct a NoRefreshTokenException.
         *
         * @param authorizationUrl The authorization URL to redirect the user to.
         */
        public NoRefreshTokenException(String authorizationUrl) {
            super(authorizationUrl);
        }

    }

    /**
     * Exception thrown when no user ID could be retrieved.
     */
    public static class NoUserIdException extends Exception {
    }




    /**
     * Retrieved stored credentials for the provided user ID.
     *
     * @param userId User's ID.
     * @return Stored Credential if found, {@code null} otherwise.
     */
    static GoogleCredential getStoredCredentials(String userId) {
        System.gc();
        //Logger.info("get the credentials for: " +userId );
        AccessRefreshString temp = DataBaseOperations.getStoredCredentials(userId);

        System.out.println("Refresh:  " + temp.refresh() );
        System.out.println("Access:   " + temp.Access() );

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();

        GoogleCredential credentials = new GoogleCredential.Builder()
                .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
                .setJsonFactory(jsonFactory).setTransport(httpTransport).build()
                .setRefreshToken(temp.refresh()).setAccessToken(temp.Access());

        return credentials;
        // Credential instance with stored accessToken and refreshToken.
    }

    /**
     * Store OAuth 2.0 credentials in the application's database.
     *
     * @param userId      User's ID.
     * @param credentials The OAuth 2.0 credentials to store.
     */
    static void storeCredentials(String userId, Credential credentials) {
        Logger.info("Store the credentials for: " +userId );
        DataBaseOperations.storeCredentials(userId, credentials.getAccessToken(),credentials.getRefreshToken());
        // Store the credentials.getAccessToken() and credentials.getRefreshToken()
        // string values in your database.
       // throw new UnsupportedOperationException();
    }

    /**
     * Build an authorization flow and store it as a static class attribute.
     *
     * @return GoogleAuthorizationCodeFlow instance.
     * @throws java.io.IOException Unable to load client_secrets.json.
     */
    static GoogleAuthorizationCodeFlow getFlow() throws IOException {
        Reader rd = new FileReader(CLIENTSECRETS_LOCATION);
        if (flow == null) {
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();
            //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, MyClass.class.getResourceAsStream(CLIENTSECRETS_LOCATION));
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, rd);
            flow =
                    new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, SCOPES)
                            .setAccessType("offline").setApprovalPrompt("force").build();
        }
        return flow;
    }

    /**
     * Exchange an authorization code for OAuth 2.0 credentials.
     *
     * @param authorizationCode Authorization code to exchange for OAuth 2.0
     *                          credentials.
     * @return OAuth 2.0 credentials.
     * @throws CodeExchangeException An error occurred.
     */
    static Credential exchangeCode(String authorizationCode)
            throws CodeExchangeException {
        try {
            GoogleAuthorizationCodeFlow flow = getFlow();
            GoogleTokenResponse response =
                    flow.newTokenRequest(authorizationCode).setRedirectUri(REDIRECT_URI).execute();

            return flow.createAndStoreCredential(response, null);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e);
            throw new CodeExchangeException(null);
        }
    }

    /**
     * Send a request to the UserInfo API to retrieve the user's information.
     *
     * @param credentials OAuth 2.0 credentials to authorize the request.
     * @return User's information.
     * @throws NoUserIdException An error occurred.
     */
    static Userinfo getUserInfo(Credential credentials)
            throws NoUserIdException {
        Oauth2 userInfoService =
                new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credentials).build();
        Userinfo userInfo = null;
        try {
            userInfo = userInfoService.userinfo().get().execute();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e);
        }
        if (userInfo != null && userInfo.getId() != null) {
            return userInfo;
        } else {
            throw new NoUserIdException();
        }
    }

    /**
     * Retrieve the authorization URL.
     *
     * @param emailAddress User's e-mail address.
     * @param state State for the authorization URL.
     * @return Authorization URL to redirect the user to.
     * @throws java.io.IOException Unable to load client_secrets.json.
     */
    public static String getAuthorizationUrl(String emailAddress, String state) throws IOException {
        GoogleAuthorizationCodeRequestUrl urlBuilder =
                getFlow().newAuthorizationUrl().setRedirectUri(REDIRECT_URI).setState(state);
        urlBuilder.set("user_id", emailAddress);
        return urlBuilder.build();
    }

    /**
     * Retrieve credentials using the provided authorization code.
     *
     * This function exchanges the authorization code for an access token and
     * queries the UserInfo API to retrieve the user's e-mail address. If a
     * refresh token has been retrieved along with an access token, it is stored
     * in the application database using the user's e-mail address as key. If no
     * refresh token has been retrieved, the function checks in the application
     * database for one and returns it if found or throws a NoRefreshTokenException
     * with the authorization URL to redirect the user to.
     *
     * @param authorizationCode Authorization code to use to retrieve an access
     *        token.
     * @param state State to set to the authorization URL in case of error.
     * @return OAuth 2.0 credentials instance containing an access and refresh
     *         token.
     * @throws NoRefreshTokenException No refresh token could be retrieved from
     *         the available sources.
     * @throws java.io.IOException Unable to load client_secrets.json.
     */
    public static Credential getCredentials(String authorizationCode, String state)
            throws CodeExchangeException, NoRefreshTokenException, IOException {
        System.gc();
        String emailAddress = "";
        try {
            Credential credentials = exchangeCode(authorizationCode);
            Userinfo userInfo = getUserInfo(credentials);
            String userId = userInfo.getId();
            emailAddress = userInfo.getEmail();
            if (credentials.getRefreshToken() != null) {
                Logger.info("Refresh token is not empty");
                storeCredentials(emailAddress, credentials);
                return credentials;
            } else {
                Logger.info("Refresh token is empty");
                credentials = getStoredCredentials(emailAddress);
                if (credentials != null && credentials.getRefreshToken() != null) {
                    return credentials;
                }
            }
        } catch (CodeExchangeException e) {
            e.printStackTrace();
            // Drive apps should try to retrieve the user and credentials for the current
            // session.
            // If none is available, redirect the user to the authorization URL.
            e.setAuthorizationUrl(getAuthorizationUrl(emailAddress, state));
            throw e;
        } catch (NoUserIdException e) {
            e.printStackTrace();
        }
        // No refresh token has been retrieved.
        String authorizationUrl = getAuthorizationUrl(emailAddress, state);
        throw new NoRefreshTokenException(authorizationUrl);
    }

    /**
     * Build a Drive service object.
     *
     * @param credentials OAuth 2.0 credentials.
     * @return Drive service object.
     */
    static Drive buildService(GoogleCredential credentials) {
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();

        return new Drive.Builder(httpTransport, jsonFactory, credentials)
                .build();
    }


    /**
     * Print a file's metadata.
     *
     * @param service Drive API service instance.
     * @param fileId ID of the file to print metadata for.
     */


    /**
     * Insert new file.
     *
     * @param service Drive API service instance.
     * @param title Title of the file to insert, including the extension.
     * @param description Description of the file to insert.
     * @param parentId Optional parent folder's ID.
     * @param mimeType MIME type of the file to insert.
     * @param filename Filename of the file to insert.
     * @return Inserted file metadata if successful, {@code null} otherwise.
     */
    private static File insertFile(Drive service, String title, String description,
                                   String parentId, String mimeType, String filename) {
        // File's metadata.
        File body = new File();
        body.setTitle(title);
        body.setDescription(description);
        body.setMimeType(mimeType);

        // Set the parent folder.
        if (parentId != null && parentId.length() > 0) {
            body.setParents(
                    Arrays.asList(new ParentReference().setId(parentId)));
        }

        // File's content.
        java.io.File fileContent = new java.io.File(filename);
        FileContent mediaContent = new FileContent(mimeType, fileContent);
        try {
            File file = service.files().insert(body, mediaContent).execute();

            // Uncomment the following line to print the File ID.
            // System.out.println("File ID: " + file.getId());

            return file;
        } catch (IOException e) {
            System.out.println("An error occured: " + e);
            return null;
        }
    }

    static InputStream downloadFile(Drive service, File file) {
        if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
            try {
                HttpResponse resp =
                        service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                                .execute();
                return resp.getContent();
            } catch (IOException e) {
                // An error occurred.
                e.printStackTrace();
                return null;
            }
        } else {
            // The file doesn't have any content stored on Drive.
            return null;
        }
    }

    static InputStream downloadFileToClient(String username, String fileId) {

        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);
        try {

            File file = service.files().get(fileId).execute();
            HttpResponse resp =
                    service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                            .execute();
            return resp.getContent();
        } catch (IOException e) {
            // An error occurred.
            //e.printStackTrace();
            Logger.error("Could not downlad a file: it might have been deleted");
            return null;
        }
    }



    static ArrayList<ZipFileEntry> downloadFileToClientWholeStudy(String username,final List<String> locations, final List<String> subjects, final List<String> sessions) {
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);
        ArrayList<ZipFileEntry> streams= new ArrayList<ZipFileEntry>();


        int iterator =-1;
        for(String stream: locations) {

            try {
                iterator ++;
                File file = service.files().get(stream).execute();
                HttpResponse resp =
                        service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                                .execute();
                streams.add(new ZipFileEntry(resp.getContent(), file.getTitle().toString(),sessions.get(iterator),subjects.get(iterator)));
            } catch (IOException e) {
                // An error occurred.
                //e.printStackTrace();
                Logger.error("Could not downlad a file: it might have been deleted");
                //return null;
            }
        }
        return streams;
    }

    static InputStream downloadFileByFileId(Drive service, String fileId) {
        //if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
        try {

            File file = service.files().get(fileId).execute();
            HttpResponse resp =
                    service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                            .execute();
            return resp.getContent();
        } catch (FileNotFoundException e) {
            // An error occurred.
            Logger.debug("File wasnot found in user's drive");
            return null;
        }
        catch (IOException e) {
            // An error occurred.
            Logger.debug("The owner of this has deleted it from his Drive");
            return null;
        }

    }
    /**
     * Retrieve a list of File resources.
     *
     * @param service Drive API service instance.
     * @return List of File resources.
     */
    static List<File> retrieveAllFiles(Drive service, String query) throws IOException {
        List<File> result = new ArrayList<File>();
        Drive.Files.List request = service.files().list();
        request.setQ(query);
        do {
            try {
                FileList files = request.execute();

                result.addAll(files.getItems());
                request.setPageToken(files.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);

        return result;
    }


    /**
     * Print files belonging to a folder.
     *
     * @param service Drive API service instance.
     * @param folderId ID of the folder to print files from.
     */
    static void printFilesInFolder(Drive service, String folderId)
            throws IOException {
        Drive.Children.List request = service.children().list(folderId);
        do {
            try {
                ChildList children = request.execute();

                for (ChildReference child : children.getItems()) {
                    System.out.println("File Id: " + child.getId());
                    //test(service,child.getId());
                }
                request.setPageToken(children.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);
    }

    static List<String> returnFilesInFolder(Drive service, String folderId, String query)
            throws IOException {
        List<String> result = new ArrayList<String>();
        Drive.Children.List request = service.children().list(folderId);
        request.setQ(query);
        do {
            try {
                ChildList children = request.execute();

                for (ChildReference child : children.getItems()) {
                    //System.out.println("File Id: " + child.getId());
                    //test(service,child.getId());
                    result.add(child.getId());
                }
                request.setPageToken(children.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);
        return result;
    }




       public static String getAuthorizationUrl() throws IOException
    {
        return getAuthorizationUrl("taamneh83@hotmail.com", "Not good");

    }
    public static String getUserEmail(String authorization_Code) throws IOException
    {

        Userinfo userInfo = null;
        try {

            String authorizationCode = authorization_Code;
            Credential credential = getCredentials(authorizationCode, "Any Thing");
            GoogleCredential googleCredential = new GoogleCredential();
            googleCredential.setAccessToken(credential.getAccessToken());

            userInfo = getUserInfo(credential);
            Logger.info("LOGIN: " + userInfo);
        }
        catch (CodeExchangeException e)
        {
            e.printStackTrace();
        }
        catch (NoRefreshTokenException e)
        {
            e.printStackTrace();
        } catch (NoUserIdException e) {
            e.printStackTrace();
        }

       return  userInfo.getEmail();
    }
    public static String FindStudy (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code, int createPortrait, int shareStudy )  throws IOException, Exception
    {
        Logger.info("We start creating a new study called: " + studyName);
        String report = "";
        GoogleCredential googleCredential = getStoredCredentials(username);
        AccessRefreshString acc  = DataBaseOperations.getStoredCredentials(username);
        Drive service = buildService(googleCredential);

        /////share the folder with every body. Later it has to be some parameter to specify it the user want to make public or not
        if(shareStudy ==1) {
            File fl = service.files().get(folder_id).execute();
            Permission newPermission = new Permission();
            newPermission.setValue("");
            newPermission.setType("anyone");
            newPermission.setRole("reader");
            service.permissions().insert(fl.getId(), newPermission).execute();
        }

                List<String> subjects = returnFilesInFolder(service,folder_id, "mimeType = 'application/vnd.google-apps.folder'");
                //test number of subjects
                if(subjects.size() > studyTopology.noOfSubjects())
                    report = report + "Your selected " + studyTopology.noOfSubjects() + " as the number of subjects in your study but you have: " + subjects.size() + "\n";
                else if (subjects.size() < studyTopology.noOfSubjects() )
                    report = report + "You have fewer subjects than the number you have selected\n";
                int study_no = 0;
                if(subjects.size() > 0 ) {
                     study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, GOOGLE_DRIVE,shareStudy);
                }


                for (String subject : subjects) {

                    File file0 = service.files().get(subject).execute();
                    DataBaseOperations.InsertSubjectGD(file0.getTitle(), study_no, bio_code, Psycho_code, physio_code);

                    List<String> sessions = returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

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
                        List<String> Signals = returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                        int signal_type =1 ;
                        for(String signalId : Signals)
                        {
                            boolean passed = false;
                            File file2 = null;
                            while (!passed)
                            {
                                try {
                                    file2 = service.files().get(signalId).execute();
                                    passed = true;
                                }catch (SocketTimeoutException e)
                                {
                                    Logger.debug("We have socketTimeOutException Salah while search for signal");
                                }
                            }
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
                               /* if(studyTopology.physiology().Breathing_Belt() !=1)  // TODO un comment this and add this signal to the list
                                    continue;*/
                                signal_type = SignalType.getSimulationCode();
                                //belt_breath =1;
                            }
                            else if(SignalType.isTemperature(extension)) signal_type = SignalType.getTemperatureCode();
                            else if(SignalType.isVideo(extension)) signal_type =SignalType.getVideoCode();
                            //else if(extension.toLowerCase().equals( "info")) signal_type =9;
                            else if(SignalType.isActivity(extension)) signal_type =SignalType.getActivityCode();
                            else if(SignalType.isHRV(extension)) signal_type =SignalType.getHRV();
                            else if(SignalType.isExpression(extension)) signal_type =SignalType.getExpression();


                            else continue; // skip if the singal is not supported

                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, sessionName, signal_type, file2.getId());

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
                    List<String> infos= returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");
                    for(String info : infos) {
                        File fileInfo = service.files().get(info).execute();
                        // to skip conflict files that start with ~
                        if(fileInfo.getTitle().contains("~"))
                            continue;
                        String extension = fileInfo.getFileExtension();
                        if(SignalType.isInfo(extension))
                        {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "INFO", SignalType.getInfoCode() , fileInfo.getId());
                        }
                        else if(SignalType.isPsychometric(extension))
                        {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "PM", SignalType.getPsychometricCode() , fileInfo.getId());
                        }
                        else if(SignalType.isPerfromance(extension))
                        {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "PRF", SignalType.getPerformanceCode() , fileInfo.getId());
                        }
                        else if(SignalType.isBar(extension)) {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "BAR", SignalType.getBarChatCode() , fileInfo.getId());
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
                String query = studyToPortratNewAlgo(folder_id, studyName, username, studyTopology, bio_code, Psycho_code, physio_code, study_no);
                Logger.debug("It takes: " +  (System.nanoTime() - startTime ) +  " To generate the portrait");
            }
        }
        else
           report = null;   // returning null to say that the study was not created correctely...
       return report;
    }


    public static String studyToPortrat (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int studyNo )  throws IOException, Exception
    {
        String queryString = "", gender = "",traits = "0", grades= "", sBars= "", subjNames="", examsNames="";
        int sessionNO=1, flg1=0;;
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);
        // get the names of subjects folders
        List<String> subjects = returnFilesInFolder(service,folder_id, "mimeType = 'application/vnd.google-apps.folder'");
        queryString = queryString + "subjects=" + subjects.size() + "&cols=3&hideButton=yes&title=" + studyName;


        for(int it=0 ;it<subjects.size()-1; it++)
            traits = traits + ",0";


        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++) {
            boolean passed = false;
            while(!passed) {
                try {
                    titlteWithId.put(service.files().get(subjects.get(i)).execute().getTitle(), subjects.get(i));
                    passed=true;
                }catch (SocketTimeoutException e)
                {
                    Logger.debug("We have socketTimeOutException while trying to sort subjects for sake of portrait");
                }

            }
        }

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        //ArrayList<BarPercentage> barForSessions = new ArrayList<>();
        TreeMap<String, BarPercentage> barForSessions = new TreeMap<>();
        ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();



        ArrayList<SessionsBar> list1;
        ArrayList<SessionsBar> list2 ;
        ArrayList<SessionsBar> list3;
        ArrayList<SessionsBar> list4;

        // to handle the situations where subjects do not have the same sessions
        ArrayList<String> uniqueSessionName = new ArrayList<>();
        boolean alreadyfilled = false;

        ArrayList<ArrayList<SessionsBar>> allList;
        TreeMap<String, String> titleWithIdSession;
        File file0= null, file =null;
        int session_no;
        boolean passed;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study

        while (it.hasNext()) {
            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();


            passed =false;
            while(!passed) {
                try {
                    file0 = service.files().get(subject).execute();
                    passed = true;
                } catch (GoogleJsonResponseException e)  // TODO cehck if we can stay here until we get the file
                {  // this exception is to handle file not found
                    continue;
                } catch (SocketTimeoutException e) {
                    Logger.debug("We have socketTimeOutException whuile trying to sort sessions for sake of portrait");
                }
            }

            sessions = returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

              // get all the session folder inside the current subject
             titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++) {
                passed =false;
                while(!passed){
                    try {
                        titleWithIdSession.put(service.files().get(sessions.get(i)).execute().getTitle(), sessions.get(i));
                        passed=true;
                    } catch (SocketTimeoutException e)
                    {
                        Logger.debug("We have socketTimeOutException whuile trying to sort sessions for sake of portrait");
                    }
                }

            }


            session_no = 1;
            if(sessions.size() > session_no)
                sessionNO = sessions.size();

            if(flg1!=0) {
               // grades = grades + ";";
                //sBars = sBars + ";";
               // examsNames = examsNames + ";";
                subjNames = subjNames + ",";
                gender = gender + ",";
            }
            flg1=1;
            int flg2=0;
            subjNames = subjNames + file0.getTitle();
            //TODO include all the available signal not only the first 4
            list1 = new ArrayList<SessionsBar>();
            list2 = new ArrayList<SessionsBar>();
            list3 = new ArrayList<SessionsBar>();
            list4 = new ArrayList<SessionsBar>();


            googleCredential = getStoredCredentials(username);
            service = buildService(googleCredential);
            Iterator itSession = titleWithIdSession.entrySet().iterator();
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();



                passed = false;
                while (!passed) {
                    try {
                        file = service.files().get(sessionId).execute();
                        passed = true;
                    }catch (SocketTimeoutException e)
                    {
                        Logger.debug("We have socketTimeOutException Salah while search for signal");
                    }
                }


                // we here assume that every subject has the same session as the first one. WE collect the session name for the first one and used for the rest
                //if(!alreadyfilled) {
                    //uniqueSessionName.add(file.getTitle().replaceFirst("(\\d*\\s*)", ""));
                    if (flg2 == 0) {
                        //grades = grades + "NA";
                        //String tempName = file.getTitle().replaceFirst("(\\d*\\s*)", "");
                        //examsNames = examsNames + tempName;

                    } else {
                       // grades = grades + ",NA";
                        //String tempName = file.getTitle().replaceFirst("(\\d*\\s*)", "");
                        //examsNames = examsNames + "," + tempName;
                    }
                    flg2 = 1;



                // get all the signal inside the current session
                Signals = returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    passed = false;
                    File file2 = null;
                    while (!passed) {
                        try {
                            file2 = service.files().get(signalId).execute();
                            passed = true;
                        }catch (SocketTimeoutException e)
                        {
                            Logger.debug("We have socketTimeOutException Salah while search for signal");
                        }
                    }



                    String SessionName = file.getTitle().replaceFirst("(\\d*\\s*)", "");

                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    extension = file2.getFileExtension();
                    if(SignalType.isEda(extension) ){
                        if(studyTopology.physiology().EDA() !=1)
                            continue;
                        list3.add(new SessionsBar( SessionName,file2.getId()));
                    }
                    else if(SignalType.isPerspiration(extension)){
                        if(studyTopology.physiology().Perspiration() !=1)
                            continue;
                        list4.add(new SessionsBar( SessionName,file2.getId()));
                        //System.out.println("This is Perspiration file id" + file2.getId());
                    }
                    else if(SignalType.isBreathing(extension)){
                        if(studyTopology.physiology().Breathing_Thermal() !=1)
                            continue;
                        list1.add(new SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isHRV(extension)){
                        //if(studyTopology.physiology().Heart_Rate() !=1)
                            //continue;
                        list2.add(new SessionsBar(SessionName,file2.getId()));
                    }
                    else if(SignalType.isMotion(extension) ){
                        if(studyTopology.physiology().Motion() !=1)
                            continue;
                        //list2.add(file2.getId());
                    }
                }
                session_no++;
            }

            allList = new ArrayList<ArrayList<SessionsBar>>();
            if(list1.size() >0){
                //  allList.add(list1);
                // System.out.println("Breathing -----------");
            }
            if(list2.size() >0){
                allList.add(list2);
            }
            if(list3.size() >0){
                allList.add(list3);
            }
            if(list4.size() >0){
                allList.add(list4);
            }
            //Check the missing Signal


            Logger.debug("We are going to call bar function");
            barForSessions = bar(username,allList, GOOGLE_DRIVE, 4);
            allBarsForAllSubjs.add(barForSessions);






            // searching for .Info and .pm files that reside in the root folder rof the subject
            List<String> infos= returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'");

              for(String info : infos) {
                  File fileInfo = service.files().get(info).execute();

                  // to skip conflict files that start with ~
                  if (fileInfo.getTitle().contains("~"))
                      continue;
                  extension = fileInfo.getFileExtension();

                  if (SignalType.isInfo(extension)) {
                      Logger.info("Found info file    ");

                      InputStream input = downloadFileByFileId(service, fileInfo.getId());
                      if(input !=null) {
                          Scanner scanner = new Scanner(input);
                          int ctr = 1;
                          boolean firstOne = true;
                          Biography bio = AuxiliaryMethods.BiographyCode(bio_code);
                          while (scanner.hasNextLine()) {
                              if (ctr == 1 && bio.Gender() == 1) {
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
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);

        // get the names of subjects folders
        List<String> subjects = returnFilesInFolder(service,folder_id, "mimeType = 'application/vnd.google-apps.folder'");

        for(int it=0 ;it<subjects.size()-1; it++)
              traits = traits + ",0";

        TreeMap<String, String> titlteWithId = new TreeMap<String, String>();

        // put subject folder name and subject id in a tree map to sort them.
        for (int i= 0; i< subjects.size(); i++)
            titlteWithId.put(waitUntilGetDGFile(service, subjects.get(i)).getTitle(), subjects.get(i));

        Map.Entry pair, pairSession;
        String subject, sessionId,  extension;
        List<String> sessions, Signals;

        ArrayList<TreeMap<String, BarPercentage>> allBarsForAllSubjs = new ArrayList<>();
        ArrayList<TreeMap<String, Double>> allPerformanceForAllSubs = new ArrayList<>();
        ArrayList<TreeMap<String, Double>> allPsychometricForAllSubs = new ArrayList<>();


        TreeMap<String, String> titleWithIdSession;
        File file0= null, file =null;

        Iterator it = titlteWithId.entrySet().iterator();
        // Iterate over the subjects in the study
        while (it.hasNext()) {
            ArrayList<String> baseLineSignalsStress= new ArrayList<>(), baseLineSignalsPerformance = new ArrayList<>();

            pair = (Map.Entry)it.next();
            subject = pair.getValue().toString();

            file0 = waitUntilGetDGFile(service, subject);
            sessions = returnFilesInFolder(service, subject, "mimeType = 'application/vnd.google-apps.folder'");

            // get all the session folder inside the current subject
            titleWithIdSession = new TreeMap<String, String>();

            for (int i= 0; i< sessions.size(); i++)
                titleWithIdSession.put(waitUntilGetDGFile(service, sessions.get(i)).getTitle(), sessions.get(i));

            if(flg1!=0) {
                subjNames = subjNames + ",";
                gender = gender + ",";
            }
            flg1=1;
            subjNames = subjNames + file0.getTitle();
            ArrayList<SessionsBar> signalsForIndicator = new ArrayList<>();
            ArrayList<SessionsBar> signalsForPerformance= new ArrayList<>();

            googleCredential = getStoredCredentials(username);
            service = buildService(googleCredential);
            Iterator itSession = titleWithIdSession.entrySet().iterator();
            // Iterate over the session folder for the current subject
            while (itSession.hasNext())
            {
                pairSession = (Map.Entry)itSession.next();
                sessionId = pairSession.getValue().toString();

                file = waitUntilGetDGFile(service, sessionId);

                // get all the signal inside the current session
                Signals = returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
                for(String signalId : Signals) {
                    File file2 = waitUntilGetDGFile(service, signalId);

                   // String SessionName = file.getTitle().replaceFirst("(\\d*\\s*)", "");
                    String SessionName = file.getTitle();

                    // to skip conflict files that start with ~
                    if(file2.getTitle().contains("~"))
                        continue;
                    extension = file2.getFileExtension();
                    if(SignalType.isSimulation(extension)) {
                        if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd")|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd"))
                        {
                            InputStream input = downloadFileByFileId(service, file2.getId());
                            baseLineSignalsPerformance.add(generateFileNameFromInputStream(input));

                            if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "ld1"))
                                signalsForPerformance.add(new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId()));
                        }
                        else if(! org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl"))
                            signalsForPerformance.add(new SessionsBar(SessionName.replaceFirst("(\\d*\\s*)", ""), file2.getId()));
                    }
                  if(SignalType.isPerspiration(extension)){
                      if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "bl") || org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "pd")|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(SessionName, "nd")) {
                          InputStream input = downloadFileByFileId(service, file2.getId());
                          baseLineSignalsStress.add(generateFileNameFromInputStream(input));
                      }
                      else
                          signalsForIndicator.add(new SessionsBar(SessionName, file2.getId()));
                    }

                }
            }

            // Get the stress indicator for all sessions of the current subject
            StressBarWithThreshold tws = getStressThreshold(baseLineSignalsStress);
            double threshold =  tws.threshold;
            TreeMap<String, BarPercentage> tt = getPortraitStateIndiactors(username,threshold, signalsForIndicator, GOOGLE_DRIVE, 4);
            tt.put("0TL", tws.stressBar);
            allBarsForAllSubjs.add(tt);

            // Get the perfromance for all sessions of the current subject
            TreeMap<String, Double> tt2 = getPortraitPerformance(username, getPerformanceThreshold(baseLineSignalsPerformance), signalsForPerformance, GOOGLE_DRIVE, 4);
            tt2.put("TL", 0.0);
            allPerformanceForAllSubs.add(tt2);

            allPsychometricForAllSubs.add(generatePsychometricForPortrait(service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'")));

            // searching for .Info and .pm files that reside in the root folder rof the subject
            gender = gender + generateGenderForPortrait (service, returnFilesInFolder(service, subject, "mimeType != 'application/vnd.google-apps.folder'"), bio_code);
        } // next subject


       // queryString = queryString + "&genders=" + gender +"&exams=" + sessionNO + "&traits=" + traits + "&grades=" + grades + "&sBars=" + sBars + "&namesSubjects=" + subjNames + "&titleGrades=Perf.&studyNo=" + studyNo + "&exLinks=http://subjectbook.times.uh.edu/displaySubject" + "&namesExams=" + examsNames;
        queryString = generateQueryString(allBarsForAllSubjs, allPerformanceForAllSubs, allPsychometricForAllSubs,  subjects.size(),studyName, gender, sessionNO, traits, grades, sBars, subjNames, studyNo, examsNames);
        Logger.info(queryString);

        DataBaseOperations.InsertStudyPortraitString(studyNo, queryString);
        return queryString;
    }


    public static TreeMap<String, Double> generatePsychometricForPortrait(Drive service, List<String> infos)throws IOException, Exception {

        TreeMap<String,Double> sessionCutoff = new TreeMap<String, Double>();
        String gender = "";
        for(String info : infos) {
            File fileInfo = service.files().get(info).execute();

            // to skip conflict files that start with ~
            if (fileInfo.getTitle().contains("~"))
                continue;
            String extension = fileInfo.getFileExtension();

            if (SignalType.isBar(extension)) {
                Logger.info("Found info file    ");
                InputStream input = downloadFileByFileId(service, fileInfo.getId());
                if (input != null) {
                    ArrayList<Double> tmp = ReadExcelJava.findTotalNASA(11, generateFileNameFromInputStream(input));
                    ////////////////////// Hard codede //////////////////////////////////////////////////////////////
                    sessionCutoff.put("ND", tmp.get(0));
                    sessionCutoff.put("LD1", tmp.get(1));
                    sessionCutoff.put("LD2", tmp.get(2));
                    sessionCutoff.put("LD3", tmp.get(3));
                    sessionCutoff.put("LD4", tmp.get(4));
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

    private static class StressBarWithThreshold{
        private double threshold;
        private BarPercentage stressBar;

        StressBarWithThreshold(double th, BarPercentage stress)
        {
            threshold = th;
            stressBar = stress;
        }
    }

    public static StressBarWithThreshold getStressThreshold(ArrayList<String> list) throws Exception
    {
        double threshold =0, sum =0, counter = 0;
        ArrayList<Double> allNumber = new ArrayList<Double>();
        for(String fileName: list){
            MeanAndSizeOfSignal temp = ReadExcelJava.findMeanFromExcel(fileName);
            sum = sum + (temp.mean * temp.size);
            counter += temp.size;
            allNumber.addAll(temp.allNum);
        }
        threshold = sum/ counter;

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
    public static File waitUntilGetDGFile (Drive service, String fileName) throws IOException, Exception {

        boolean passed = false;
        File file = null;
        while(!passed) {
            try {
                file = service.files().get(fileName).execute();
                passed = true;
            } catch (GoogleJsonResponseException e)  // TODO cehck if we can stay here until we get the file
            {  // this exception is to handle file not found
                continue;
            } catch (SocketTimeoutException e) {
                Logger.debug("We have socketTimeOutException whuile trying to sort sessions for sake of portrait");
            }
        }
        return file;
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

        sessionNO = standardSessionListOrdered.size();
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
                            if(sai_list.containsKey(entry.getValue()) && !org.apache.commons.lang3.StringUtils.containsIgnoreCase(entry.getKey(),"tl")) {
                                sai_temp= Long.toString(Math.round( sai_list.get(entry.getValue())));
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
                            tai = tai + "," + sai_temp;
                        }
                    }else{

                        if(isFirst) {
                            examsNames = examsNames + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            grades = grades + "NA";
                            tai = tai + "NA";
                            isFirst = false;
                        }
                        else  {
                            examsNames = examsNames + "," + entry.getKey().replaceFirst("(\\d*\\s*)", "");
                            sBars = sBars + ":";
                            grades = grades + ",NA";
                            tai = tai + ",NA";
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
            File fileInfo = service.files().get(info).execute();

            // to skip conflict files that start with ~
            if (fileInfo.getTitle().contains("~"))
                continue;
            String extension = fileInfo.getFileExtension();

            if (SignalType.isInfo(extension)) {
                Logger.info("Found info file    ");

                InputStream input = downloadFileByFileId(service, fileInfo.getId());
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
    public static  TreeMap<String, Double>  getPortraitPerformance(String username, double max, ArrayList<SessionsBar> url, int sourceType, int signalType)  throws IOException,  Exception
    {
        System.out.println("I am in bar function");
        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        //ArrayList<BarPercentage> sessionCutoff = new ArrayList<BarPercentage>();
        TreeMap<String,Double> sessionCutoff = new TreeMap<String, Double>();
        if (sourceType == LOCALSERVER) //TODO
        {
            return sessionCutoff;
        }
        else {   // read from google drive
            System.out.println("I am in bar function - GDrive");
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = getStoredCredentials(username);
                Drive service = buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {

                    InputStream input = downloadFileByFileId(service, url.get(i).location);
                    sessionCutoff.put(url.get(i).name, ReadExcelJava.findPerformanceFromExcel(max, generateFileNameFromInputStream(input)));
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
    public static  TreeMap<String, BarPercentage>  getPortraitStateIndiactors(String username, double max, ArrayList<SessionsBar> url, int sourceType, int signalType)  throws IOException,  Exception
    {
        System.out.println("I am in bar function");
        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        //ArrayList<BarPercentage> sessionCutoff = new ArrayList<BarPercentage>();
        TreeMap<String,BarPercentage> sessionCutoff = new TreeMap<String, BarPercentage>();
        if (sourceType == LOCALSERVER) //TODO
        {
            return sessionCutoff;
        }
        else {   // read from google drive
            System.out.println("I am in bar function - GDrive");
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = getStoredCredentials(username);
                Drive service = buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {

                    InputStream input = downloadFileByFileId(service, url.get(i).location);
                    sessionCutoff.put(url.get(i).name, ReadExcelJava.findBarFromExcel(max, generateFileNameFromInputStream(input)));
                }
            }catch (IndexOutOfBoundsException e) {
                Logger.error("In Bar method-Outer loop: index out of bound");
            }

            return sessionCutoff;
        }
    }

    public static  TreeMap<String, BarPercentage>  bar(String username,ArrayList<ArrayList<SessionsBar>> url, int sourceType, int signalType)  throws IOException,  Exception
    {
        System.out.println("I am in bar function");
        ArrayList<InputStream> all = new ArrayList<InputStream>();
        TreeMap<String, String> signal = new TreeMap<String, String>();
        //ArrayList<ArrayList<BarPercentage>> resl = new ArrayList<ArrayList<BarPercentage>>();
        ArrayList<TreeMap<String, BarPercentage>> resl = new ArrayList<TreeMap<String, BarPercentage>>();
        //ArrayList<BarPercentage> sessionCutoff = new ArrayList<BarPercentage>();
        TreeMap<String,BarPercentage> sessionCutoff = new TreeMap<String, BarPercentage>();
        if (sourceType == LOCALSERVER)
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
                resl.add(ReadExcelJava.fromExceForPortrait(signal, signalType));
            }

            return sessionCutoff;
        }
        else {   // read from google drive
            System.out.println("I am in bar function - GDrive");
            try {
                // iterate over the signal lists; e.g, EDA List for all sessions
                GoogleCredential googleCredential = getStoredCredentials(username);
                Drive service = buildService(googleCredential);
                for (int i = 0; i < url.size(); i++) {
                    signal = new TreeMap<>();
                    try {
                        for (int j = 0; j < url.get(i).size(); j++) {
                            InputStream input = downloadFileByFileId(service, url.get(i).get(j).location);
                            if(input != null) {
                                //all.add(input); // TODO save the strems in a folder and then pass that folder for readExcelJava
                                signal.put(url.get(i).get(j).name, generateFileNameFromInputStream(input));
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


    public static String generateFileNameFromInputStream (InputStream input){
        Path destination = null;
        UUID id = UUID.randomUUID();
        String fileName = "C:\\temp\\" + id +  ".xlsx";


        try {
            destination = Paths.get(fileName);
            Files.copy(input, destination);
            input.close();
            return fileName;
        }
        catch (java.lang.IllegalArgumentException e)
        {
            System.out.println("Sorry");
        }
        catch (Exception e)
        {
            System.out.println("Sorry");
        }
        return null;
    }

    public static void FindStudyLocalServer (String studyName,String url, String username, int noOfSession,  StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code )  throws IOException
    {
        final java.io.File study = new java.io.File(url);
        if(study.exists()) {
            Logger.info("We Found it");
            // Insert new study in the DB
            int study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, LOCALSERVER, 0);
            // Get all the subjects in that study
            for (final java.io.File subject : study.listFiles()) {
                String subjectName = subject.getName();
                if (subject.isDirectory()) {
                    System.out.println("Subject folder path: " + subject.getAbsolutePath());
                    //Inset subjects into the new study
                     DataBaseOperations.InsertSubjectGD(subject.getName(), study_no,bio_code,Psycho_code,physio_code);
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
                                   DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, sessionName, signal_type, signal.getAbsolutePath());
                                }
                            }
                            else {

                                int pos = session.getAbsolutePath().toString().lastIndexOf('.');
                                String fileExtension = session.getAbsolutePath().substring(pos + 1);
                                System.out.println("Extension: " + fileExtension);
                                switch (fileExtension.toLowerCase()) {
                                    case "info":
                                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no,"INFO", 9 , session.getAbsolutePath());
                                        break;
                                    case "pm":
                                        DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no,"PM", 11 , session.getAbsolutePath());
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
                                DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "INFO", signal_type, signal.getAbsolutePath());
                            }
                            else if (signal_type ==11)
                            {
                                DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "PM", signal_type, signal.getAbsolutePath());
                            }
                            else
                              DataBaseOperations.InsertSessionGD(subjectName, study_no, session_no, "None", signal_type, signal.getAbsolutePath());
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
    public static org.json.simple.JSONObject DownloadSignal(String username, String url, int sourceType, int signalType, String activityFile)  throws  Exception
    {

        if (sourceType == LOCALSERVER)
        {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            InputStream input = new BufferedInputStream(
                    new FileInputStream(url));


            /*ReadExcelScala GetSignalAsJOSON = new ReadExcelScala("", "", " ",signalType );
            return GetSignalAsJOSON.fromExcelInputParallel(input);*/

            ActorRef myActor = Akka.system().actorOf(Props.create(ReadExcelScala.class, signalType, input));
            Timeout timeout = new Timeout(Duration.create(100, "seconds"));
            Future<Object> future = Patterns.ask(myActor,signalType, timeout);

            org.json.simple.JSONObject x = (org.json.simple.JSONObject) Await.result(future, timeout.duration());


            input.close();
            return  x;
        }
        else {

            Logger.debug("Download the signal from Google Drive, Signal Type is: " + signalType);
            GoogleCredential googleCredential = getStoredCredentials(username);
            Drive service = buildService(googleCredential);
            InputStream input =  downloadFileByFileId(service, url);


            String fileName = generateFileNameFromInputStream(input);

            Logger.debug("After getting the file as Inputstream from Google Drive");

            org.json.simple.JSONObject x = null;



            ReadExcelJava tt = new ReadExcelJava();

            if(input != null) {
                // if the request file is required to be visulaized in column chart
                if (signalType == 11) {
                    //x = ReadExcelJava.fromExcelInputToChar(signalType, fileName);
                    x = tt.fromExcelInputToCharTemp(signalType, fileName);

                } else {
                    if (activityFile != null) {
                        Logger.info("Try to read from excel file with activity");
                       // x = ReadExcelJava.fromExcelInput(signalType, ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)), fileName);
                        x = ReadExcelJava.fromExcelInputTemp(signalType, ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)), fileName);
                    } else {
                        Logger.info("Try to read from excel file without activity");
                       //x = ReadExcelJava.fromExcelInput(signalType, fileName);
                        x = tt.fromExcelInputTemp(signalType, fileName);
                    }

                    input.close();
                }
            }

            return  x;

        }
    }

    public static String GetSubjectPRF(String username, String url, int sourceType)  throws IOException
    {
        String json = "[";
        String line = "";
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = getStoredCredentials(username);
            Drive service = buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = downloadFileByFileId(service, url);
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
    public static String GetSubjectInfo(String username, String url, int sourceType, int signalType,Biography bio)  throws IOException
    {
        String json = "{";
        String line = "";
        Scanner scanner = null;
        InputStream input = null;
        if (sourceType == LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = getStoredCredentials(username);
            Drive service = buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = downloadFileByFileId(service, url);
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
                System.out.println("STUPID" + ctr);

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
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);

        File file = service.files().get(url).execute();
        Logger.info("Open The info File with URL: " + url);
        Logger.info("The video size is" + file.getFileSize());
        return downloadFileByFileId(service, url);


}


    public static String GetVideoSize(String username, String url, int sourceType, int signalType)  throws IOException
    {
        InputStream input = null;
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);

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
        if (sourceType == LOCALSERVER) {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            input = new BufferedInputStream(
                    new FileInputStream(url));
            Logger.info("Open The info File with URL: " + url);
        }
        else {

            GoogleCredential googleCredential = getStoredCredentials(username);
            Drive service = buildService(googleCredential);
            Logger.info("Open The info File with URL: " + url);
            input = downloadFileByFileId(service, url);
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

            // first line is the gender
            /*if (ctr == 1 && psycho.SAI()==1)
            {

                if (firstOne) {
                    json = json + "\"SAI\": \"" + scanner.nextLine().toString() + "\"";
                    firstOne = false;
                }
                else
                    json = json + ", \"SAI\": \"" + scanner.nextLine().toString() + "\"";
            }
            else if(ctr ==2 && psycho.TAI()==1){
                String str = scanner.nextLine().toString();
                if (firstOne) {
                    json = json + "\"TAI\": \"" + str + "\"";
                    firstOne = false;
                }
                else
                    json = json + ", \"TAI\": \"" + str + "\"";
            }
            else if(ctr==3 && psycho.NA() == 1) {
                String str = scanner.nextLine().toString();
                if(firstOne) {
                    json = json + "\"NA\": \"" + str + "\"";
                    firstOne = false;
                }
                else
                    json = json + ", \"NA\": \"" + str + "\"";
            }
            else if(ctr==4 && psycho.PA() == 1) {
                String str = scanner.nextLine().toString();
                if(firstOne) {
                    json = json + "\"PA\": \"" + str + "\"";
                    firstOne = false;
                }
                else
                    json = json + ", \"PA\": \"" + str + "\"";
            }
            else if(ctr==5 &&psycho.Other() == 1){
                String str = scanner.nextLine().toString();
                if(firstOne) {
                    json = json + "\"Other\": \"" + str + "\"";
                    firstOne = false;
                }
                else
                    json = json + ", \"Other\": \"" + str + "\"";
            }
            else {
                scanner.nextLine();
            }
            ctr++;
                */
        }
        scanner.close();
        input.close();
        json = json + "}";
        System.out.println(json);
        return  json;

    }


}
