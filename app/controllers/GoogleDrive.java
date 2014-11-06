package controllers;

/**
 * Created by staamneh on 10/6/2014.
 */

import Models.*;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.model.File;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import play.Logger;
import play.api.libs.json.JsArray;
import play.api.libs.json.JsObject;
import play.api.libs.json.JsValue;
import play.api.libs.json.Json;
import scala.Int;
import scala.concurrent.Future;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONArray$;
import scala.util.parsing.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//import java.io.*;
//import java.io.File;

// the reason for this class is that google only support java API
public class GoogleDrive {

    private static final String CLIENTSECRETS_LOCATION = "C:\\Users\\staamneh\\Desktop\\CPL-Lab\\System Desgin\\first_play\\app\\client_secrets.json";

    private static final String REDIRECT_URI = "http://stressbook.ddns.net/display";
    private static final List<String> SCOPES = Arrays.asList(
            //"https://www.googleapis.com/auth/drive.file",
            "https://www.googleapis.com/auth/drive",
            "https://www.googleapis.com/auth/userinfo.email",
            "https://www.googleapis.com/auth/userinfo.profile");

    private static GoogleAuthorizationCodeFlow flow = null;

    private final static int GOOGLE_DRIVE = 1;
    private final static int LOCALSERVER = 2;
    private final static String CLIENT_ID = "1019721884317-30f4nbmi3s1pvq7tglarglbf50f0lf7j.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "M8W3dj1MHyfAIMX686T9mXQ8";


    public static String authorizationUrl;



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
    static GoogleCredential getStoredCredentials(String userId)  throws CodeExchangeException{

        Logger.info("get the credentials for: " +userId );
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
    static void printFile(Drive service, String fileId) {
        try {

            File file = service.files().get(fileId).execute();

            System.out.println("Title: " + file.getTitle());
            System.out.println("Description: " + file.getDescription());
            System.out.println("MIME type: " + file.getMimeType());
        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 401) {
                // Credentials have been revoked.
                // TODO: Redirect the user to the authorization URL.
                throw new UnsupportedOperationException();
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }


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


    static InputStream downloadFileByFileId(Drive service, String fileId) {
        //if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
        try {

            File file = service.files().get(fileId).execute();
            HttpResponse resp =
                    service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                            .execute();
            return resp.getContent();
        } catch (IOException e) {
            // An error occurred.
            e.printStackTrace();
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
        System.out.println("Let's start");
        System.out.println(GoogleDrive.getAuthorizationUrl());
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        Userinfo userInfo = null;
        try {

            String authorizationCode = authorization_Code;
            Credential credential = getCredentials(authorizationCode, "Any Thing");
            GoogleCredential googleCredential = new GoogleCredential();
            googleCredential.setAccessToken(credential.getAccessToken());

            userInfo = getUserInfo(credential);
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
    public static String FindStudy (String folder_id, String studyName, String username, StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code )  throws IOException, CodeExchangeException
    {
        String report = "";


        //Credential credential  = getStoredCredentials(username);
        //GoogleCredential googleCredential = new GoogleCredential();
        GoogleCredential googleCredential = getStoredCredentials(username);
        //googleCredential.setAccessToken(credential.getAccessToken());
        AccessRefreshString acc  = DataBaseOperations.getStoredCredentials(username);
        Drive service = buildService(googleCredential);

                List<String> subjects = returnFilesInFolder(service,folder_id, "mimeType = 'application/vnd.google-apps.folder'");
                //test number of subjects
                if(subjects.size() > studyTopology.noOfSubjects())
                    report = report + "Your selected " + studyTopology.noOfSubjects() + " as the number of subjects in your study but you have: " + subjects.size() + "\n";
                else if (subjects.size() < studyTopology.noOfSubjects() )
                    report = report + "You have fewer subjects than the number you have selected\n";
                int study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, GOOGLE_DRIVE);
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

                        System.out.println("File Id: " + file.getTitle());
                        List<String> Signals = returnFilesInFolder(service, sessionId, "mimeType != 'application/vnd.google-apps.folder'");
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

                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, sessionName, signal_type, file2.getId());
                            System.out.println("File Id: " + file2.getTitle());



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
                        System.out.println("Extension: " + extension);

                        if(extension.toLowerCase().equals( "info"))
                        {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "INFO", 9 , fileInfo.getId());
                        }
                        if(extension.toLowerCase().equals( "pm"))
                        {
                            DataBaseOperations.InsertSessionGD(file0.getTitle(), study_no, session_no, "PM", 11 , fileInfo.getId());
                        }
                    }

                }
          // }
        //else
             //System.out.println("There is no Study stored in your Google Drive with name: " + studyName);
        report = "Study has been created successfully with the following warning:\n" + report;
       return report;
    }



    public static void FindStudyLocalServer (String studyName,String url, String username, int noOfSession,  StudyTopology studyTopology,  int bio_code,  int Psycho_code, int physio_code, int perf_code )  throws IOException, CodeExchangeException
    {
        final java.io.File study = new java.io.File(url);
        if(study.exists()) {
            Logger.info("We Found it");
            // Insert new study in the DB
            int study_no = DataBaseOperations.GenerateStudyNoGD(studyName, username, LOCALSERVER);
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
    public static JsObject DownloadSignal(String username, String url, int sourceType, int signalType)  throws IOException, CodeExchangeException
    {
        if (sourceType == LOCALSERVER)
        {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            InputStream input = new BufferedInputStream(
                    new FileInputStream(url));
            ReadExcelScala GetSignalAsJOSON = new ReadExcelScala("", "", " ",signalType );

            return GetSignalAsJOSON.fromExcelInputParallel(input);
        }
        else {

            Logger.debug("Download the signal from Google Drive, Signal Type is: " + signalType);
            GoogleCredential googleCredential = getStoredCredentials(username);
            Drive service = buildService(googleCredential);
            InputStream input =  downloadFileByFileId(service, url);


            ReadExcelScala GetSignalAsJOSON = new ReadExcelScala("", "", " ", signalType);
            JsObject x= GetSignalAsJOSON.fromExcelInputParallel(input);
            //JsArray x= ReadExcelJava.fromExcelInput(input);
            input.close();
            return  x;
        }
    }

    public static String GetSubjectInfo(String username, String url, int sourceType, int signalType,Biography bio)  throws IOException, CodeExchangeException
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
        scanner = new Scanner(input);
            int ctr= 1;
            boolean firstOne = true;
            while(scanner.hasNextLine()) {

                // first line is the gender
                if (ctr == 1 && bio.Gender()==1)
                {

                    if (firstOne) {
                        json = json + "\"gender\": \"" + scanner.nextLine().toString() + "\"";
                        firstOne = false;
                    }
                    else
                        json = json + ", \"gender\": \"" + scanner.nextLine().toString() + "\"";
                }
                else if(ctr ==2 && bio.Age()==1){
                    String str = scanner.nextLine().toString();
                    if (firstOne) {
                        json = json + "\"age\": \"" + str + "\"";
                        firstOne = false;
                    }
                    else
                        json = json + ", \"age\": \"" + str + "\"";
                }
                else if(ctr==3 && bio.Ethnicity() == 1) {
                    String str = scanner.nextLine().toString();
                    if(firstOne) {
                        json = json + "\"Ethnicity\": \"" + str + "\"";
                        firstOne = false;
                    }
                    else
                        json = json + ", \"Ethnicity\": \"" + str + "\"";
                }
                else if(ctr==4 && bio.Height() == 1) {
                    String str = scanner.nextLine().toString();
                    if(firstOne) {
                        json = json + "\"Height\": \"" + str + "\"";
                        firstOne = false;
                    }
                    else
                        json = json + ", \"Height\": \"" + str + "\"";
                }
                else if(ctr==5 && bio.Weight() == 1){
                    String str = scanner.nextLine().toString();
                    if(firstOne) {
                        json = json + "\"Weight\": \"" + str + "\"";
                        firstOne = false;
                    }
                    else
                        json = json + ", \"Weight\": \"" + str + "\"";
                }
                else
                {
                    scanner.nextLine();
                }
                //json = "{\"age\": 45, \"Gender\": \"Male\"";

                ctr++;
                System.out.println("STUPID" + ctr);

            }
           scanner.close();
            json = json + "}";
            System.out.println(json);
            return  json;

        }


    public static InputStream GetVideoInputStream(String username, String url, int sourceType, int signalType)  throws IOException, CodeExchangeException
    {
        InputStream input = null;
        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);

        File file = service.files().get(url).execute();
        Logger.info("Open The info File with URL: " + url);
        Logger.info("The video size is" + file.getFileSize());
        return downloadFileByFileId(service, url);


}
    public static String GetVideoUrl(String url)  throws IOException, CodeExchangeException {
        String header = "https://drive.google.com/uc?export=download&id=";
        return header + url;
    }

    public static String GetVideoSize(String username, String url, int sourceType, int signalType)  throws IOException, CodeExchangeException
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


    public static String GetSubjectPm(String username, String url, int sourceType, int signalType, Psychometric psycho)  throws IOException, CodeExchangeException
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
        scanner = new Scanner(input);
        int ctr= 1;
        boolean firstOne = true;
        while(scanner.hasNextLine()) {

            // first line is the gender
            if (ctr == 1 && psycho.SAI()==1)
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
            else
            {
                scanner.nextLine();
            }
            //json = "{\"age\": 45, \"Gender\": \"Male\"";

            ctr++;
            System.out.println("STUPID" + ctr);

        }
        scanner.close();
        json = json + "}";
        System.out.println(json);
        return  json;

    }


}
