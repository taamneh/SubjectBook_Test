    package controllers;

/**
 * Created by staamneh on 10/6/2014.
 */

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import org.apache.poi.ss.formula.functions.Now;
import org.json.simple.JSONArray;
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
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import play.Logger;

import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import com.google.gdata.util.*;


import play.libs.Akka;

import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import play.libs.Scala;
import scala.Int;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;


import java.io.*;
import java.lang.String;
import java.lang.System;
import java.util.*;

import java.net.*;




// the reason for this class is that google only support java API



public class GoogleDrive {


    private static final String CLIENTSECRETS_LOCATION = ProductionSide.CLIENTSECRETS_LOCATION();


    //private static final String REDIRECT_URI = "http://subjectbook.times.uh.edu/display";
    private static final String REDIRECT_URI =  ProductionSide.REDIRECT_URI();
    private static final List<String> SCOPES =  ProductionSide.SCOPES();

    private static GoogleAuthorizationCodeFlow flow = null;
    private final static int numberOfretry = 7;
    public static long lastRequestTime = System.nanoTime();


    private final static String CLIENT_ID = ProductionSide.CLIENT_ID();
    private final static String CLIENT_SECRET = ProductionSide.CLIENT_SECRET();


    public static String authorizationUrl;

    public static void test(){
        RConnection connection = null;

        try {
             /* Create a connection to Rserve instance running
              * on default port 6311
              */
            connection = new RConnection();

            String vector = "c(1,2,3,4)";
            connection.eval("meanVal=mean(" + vector + ")");
            connection.eval("x <- c(70,60)");
            double m = connection.eval("mean(x)").asDouble();
            System.out.println("The mean of given vector is=" + m);
            double mean = connection.eval("meanVal").asDouble();
            System.out.println("The mean of given vector is=" + mean);
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
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
        Logger.info("Store the credentials for: " + userId);
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





    static InputStream downloadFileByFileId(Drive service, String fileId)  {

        Random randomGenerator = new Random();
        boolean x = false;
        for (int n =1 ; n <= numberOfretry; n++) {
            if(n>1 && !x)
                Logger.error("Another fucking n: " + n);
            if(x) // to be delete
                Logger.info("After read time out we have a new message kareem taamneh");
            try {
                File file = null;
                // exponential backoff
                file = service.files().get(fileId).execute();
                System.out.println("Title::::: " + file.getFileExtension());
                HttpResponse resp =
                        service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                                .execute();
                return resp.getContent();
            } catch (FileNotFoundException e) {
                // An error occurred.
                Logger.debug("File was not found in user's drive");
                return null;
            } catch (GoogleJsonResponseException e) {
                if ((e.getDetails().getErrors().get(0).getReason().equals("rateLimitExceeded")
                        || e.getDetails().getErrors().get(0).getReason().equals("userRateLimitExceeded"))) {
                    Logger.error("in downloadFileById rate limit exceeded(salah) " + e);
                    // Apply exponential backoff.
                    try {
                        Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001)*n);
                        Logger.error("time to sleep: " + (1 << n) * 1000 + randomGenerator.nextInt(1001));
                    } catch (InterruptedException e2) {
                        Logger.error("The sleep did not work === " + e2);
                    }

                }
            }
            catch (MalformedURLException e){
                System.out.println("MalformedURLException in downloadFileByFileId");
            }
            catch (IllegalArgumentException e){
                System.out.println("IllegalArgumentException in downloadFileByFileId");
                return null;

            }
            catch (IOException e) {
                // An error occurred.
                Logger.debug("The owner of this has deleted it from his Drive Or something else" + e);
                x = true;
                //return null;
            }
        }
        return null;
    }

    static TreeMap<String, String> returnFilesInFolderJustForTest(Drive service, String folderId, String query) throws IOException {

        TreeMap<String, String> mp= new TreeMap<String, String>();
        List<File> result = new ArrayList<File>();
        com.google.api.services.drive.Drive.Files.List request = service.files().list();
        String q = "'" + folderId + "' in parents" + query;
        request.setQ(q);

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

        for(File file: result)
            mp.put(file.getTitle(), file.getId());
       // System.out.println("id: "+ file.getId() + " title: " + file.getTitle());

        return mp;

    }


    static TreeMap<String, File> returnFilesInFolderJustForTest2(Drive service, String folderId, String query) throws IOException {
        TreeMap<String, File> mp= new TreeMap<String, File>();
        List<File> result = new ArrayList<File>();
        com.google.api.services.drive.Drive.Files.List request = service.files().list();
        String q = "'" + folderId + "' in parents and " + query;
        request.setQ(q);
        boolean exit = false;
        Random randomGenerator = new Random();
        do {
            for (int n = 1; n <= numberOfretry && !exit ; n++) {
                try {
                    FileList files = request.execute();
                    result.addAll(files.getItems());
                    request.setPageToken(files.getNextPageToken());
                    exit = true;
                } catch (GoogleJsonResponseException e) {
                    Logger.error("returnFilesInFolder" + e.getMessage());
                    if ((e.getDetails().getErrors().get(0).getReason().equals("rateLimitExceeded")
                            || e.getDetails().getErrors().get(0).getReason().equals("userRateLimitExceeded"))) {
                        Logger.error("in returnFilesInFoldertest2 rate limit exceeded(salah) " + e);
                        // Apply exponential backoff.
                        try {
                            Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001) * n);
                        } catch (InterruptedException e2) {
                            Logger.error("got interrupted!" + e2);
                        }

                    }
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e);
                    request.setPageToken(null);
                }
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);

        for(File file: result) {
            if(!file.getLabels().getTrashed())
                mp.put(file.getId(), file);
        }
        // System.out.println("id: "+ file.getId() + " title: " + file.getTitle());

        return mp;

    }

    static List<String> returnFilesInFolder(Drive service, String folderId, String query) {


        List<String> result = new ArrayList<String>();
        Drive.Children.List request = null;
        try {
            request = service.children().list(folderId);
        }
        catch(IOException e){
            Logger.error("Here is anohter possible reson " + e);
        }
        Random randomGenerator = new Random();
        request.setQ(query);
        do {
            try {
                boolean exit= false;
                for (int n = 1; n <= numberOfretry && !exit ; n++) {
                    try {

                        ChildList children = request.execute();
                        for (ChildReference child : children.getItems()) {
                            result.add(child.getId());
                        }
                        request.setPageToken(children.getNextPageToken());
                        exit = true;
                    } catch (GoogleJsonResponseException e) {
                        Logger.error("returnFilesInFolder" + e.getMessage());
                        if ((e.getDetails().getErrors().get(0).getReason().equals("rateLimitExceeded")
                                || e.getDetails().getErrors().get(0).getReason().equals("userRateLimitExceeded"))) {
                            Logger.error("in returnFilesInFolder rate limit exceeded(salah) " + e);
                            // Apply exponential backoff.
                            try {
                                Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001)*n);
                            } catch (InterruptedException e2) {
                                Logger.error("got interrupted!" + e2);
                            }

                        }
                    }
                }
            } catch (IOException e) {
                Logger.error("An error occurred In function returnFilesInFolder: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);
        return result;
    }
    public static File waitUntilGetDGFile (Drive service, String fileName)  {
        Random randomGenerator = new Random();
        int n=1;
        boolean passed = false;
        File file = null;
        while(!passed) {
            try {
                file = service.files().get(fileName).execute();
                passed = true;
            } catch (GoogleJsonResponseException e)  // TODO cehck if we can stay here until we get the file
            {
                if ((e.getDetails().getErrors().get(0).getReason().equals("rateLimitExceeded")
                        || e.getDetails().getErrors().get(0).getReason().equals("userRateLimitExceeded"))) {
                    Logger.error("in waitUntilGetDGFile rate limit exceeded(salah) " + e);
                    // Apply exponential backoff.
                    try {
                        Thread.sleep((1 << n) * 1000 + randomGenerator.nextInt(1001)*n);
                        n++;
                    } catch (InterruptedException e2) {
                        Logger.error("got interrupted!" + e2);
                    }

                }

            } catch (SocketTimeoutException e) {
                Logger.debug("We have socketTimeOutException whuile trying to sort sessions for sake of portrait");
            } catch (IOException e){
                Logger.debug("Error in waitUntilGetDGFile in GoogleDrive Calss" + e.getMessage());
            }
        }
        return file;
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

    public static void plz()
    {
        ////////////////////////////////////////////////////////////////////////////
        // STEP 1: Configure how to perform OAuth 2.0
        ////////////////////////////////////////////////////////////////////////////

        // TODO: Update the following information with that obtained from
        // https://code.google.com/apis/console. After registering
        // your application, these will be provided for you.

        // Space separated list of scopes for which to request access.
         String SCOPE = "https://spreadsheets.google.com/feeds https://docs.google.com/feeds";

        // This is the Redirect URI for installed applications.
        // If you are building a web application, you have to set your
        // Redirect URI at https://code.google.com/apis/console.
        //string REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

        ////////////////////////////////////////////////////////////////////////////
        // STEP 2: Set up the OAuth 2.0 object
        ////////////////////////////////////////////////////////////////////////////

        // OAuth2Parameters holds all the parameters related to OAuth 2.0.
        OAuthParameters parameters = new OAuthParameters();

        // Set your OAuth 2.0 Client Id (which you can register at
        // https://code.google.com/apis/console).
        //parameters.ClientId = CLIENT_ID;



        // Set your OAuth 2.0 Client Secret, which can be obtained at
        // https://code.google.com/apis/console.
        //parameters.ClientSecret = CLIENT_SECRET;

        // Set your Redirect URI, which can be registered at
        // https://code.google.com/apis/console.
        //parameters.RedirectUri = REDIRECT_URI;

        ////////////////////////////////////////////////////////////////////////////
        // STEP 3: Get the Authorization URL
        ////////////////////////////////////////////////////////////////////////////

        // Set the scope for this particular service.
        //parameters.Scope = SCOPE;

        // Get the authorization url.  The user of your application must visit
        // this url in order to authorize with Google.  If you are building a
        // browser-based application, you can redirect the user to the authorization
        // url.
        //String authorizationUrl = OAuthUtil.CreateOAuth2AuthorizationUrl(parameters);
       /* Console.WriteLine(authorizationUrl);
        Console.WriteLine("Please visit the URL above to authorize your OAuth "
                + "request token.  Once that is complete, type in your access code to "
                + "continue...");*/
       // parameters.AccessCode = Console.ReadLine();

        ////////////////////////////////////////////////////////////////////////////
        // STEP 4: Get the Access Token
        ////////////////////////////////////////////////////////////////////////////

        // Once the user authorizes with Google, the request token can be exchanged
        // for a long-lived access token.  If you are building a browser-based
        // application, you should parse the incoming request token from the url and
        // set it in OAuthParameters before calling GetAccessToken().
        //OAuthUtil.GetAccessToken(parameters);
        //String accessToken = parameters.AccessToken;
        /*Console.WriteLine("OAuth Access Token: " + accessToken);*/

        ////////////////////////////////////////////////////////////////////////////
        // STEP 5: Make an OAuth authorized request to Google
        ////////////////////////////////////////////////////////////////////////////

        // Initialize the variables needed to make the request


        GoogleCredential googleCredential = getStoredCredentials("salah");
        //Drive serviceOrg = buildService(googleCredential);

        //GOAuth2RequestFactory requestFactory =                 new GOAuth2RequestFactory(null, "MySpreadsheetIntegration-v1", parameters);
       // SpreadsheetsService service = new SpreadsheetsService("MySpreadsheetIntegration-v1");
        SpreadsheetService service2 =  new SpreadsheetService("MySpreadsheetIntegration-v1");

    }

    public  static JSONObject nasa( String user, String id) {

            try {
                SpreadsheetService service =
                        new SpreadsheetService("MySpreadsheetIntegration-v1");

                // TODO: Authorize the service object for a specific user (see other sections)


                GoogleCredential googleCredential = getStoredCredentials(user);
                service.setOAuth2Credentials(googleCredential);

                // Define the URL to request.  This should never change.
                URL SPREADSHEET_FEED_URL = new URL(
                        "https://spreadsheets.google.com/feeds/spreadsheets/private/full");

                // Make a request to the API and get all spreadsheets.
                SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
                        SpreadsheetFeed.class);
                List<SpreadsheetEntry> spreadsheets = feed.getEntries();

                int loc = 0;
                SpreadsheetEntry mySheet = spreadsheets.get(0);
                // Iterate through all of the spreadsheets returned
                for (SpreadsheetEntry spreadsheet : spreadsheets) {
                    // Print the title of this spreadsheet to the screen
                    System.out.println(spreadsheet.getTitle().getPlainText());
                    System.out.println(spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/") + 1));

                    if (spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/") + 1).equals(id)) {
                        mySheet = spreadsheet;
                        System.out.println("yes we have and it is : " + spreadsheet.getId());
                        break;
                    }

                }
                // TODO: Choose a spreadsheet more intelligently based on your
                // app's needs.
                SpreadsheetEntry spreadsheet = mySheet;
                System.out.println(spreadsheet.getTitle().getPlainText());

                // Get the first worksheet of the first spreadsheet.
                // TODO: Choose a worksheet more intelligently based on your
                // app's needs.
                WorksheetFeed worksheetFeed = service.getFeed(
                        spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
                List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
                WorksheetEntry worksheet = worksheets.get(0);

                // Fetch the cell feed of the worksheet.
                URL cellFeedUrl = worksheet.getCellFeedUrl();
                CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);


                JSONObject all = new JSONObject();
                JSONArray header = new JSONArray();


                JSONArray content = new JSONArray();

                JSONArray arrTemp= new JSONArray();


                String lstRow = "";
                // Iterate through each cell, printing its value.
                for (CellEntry cell : cellFeed.getEntries()) {


                    System.out.println(lstRow);

                    if((cell.getId().substring(cell.getId().lastIndexOf('/') + 1).toLowerCase().contains("r9"))){  // all these are header
                        if(cell.getTitle().getPlainText().toLowerCase().contains("a9")){  // this one has string type

                            JSONObject obj = new JSONObject();
                            obj.put("id","");
                            obj.put("label",cell.getCell().getInputValue());
                            obj.put("type", "string");
                            header.add(obj);
                        }
                        else
                        {

                            JSONObject obj = new JSONObject();
                            obj.put("id","");
                            obj.put("label",cell.getCell().getInputValue());
                            obj.put("type", "number");
                            header.add(obj);

                        }

                    }
                    else
                    {
                        if(lstRow.equals("") ){ // if this is the first line

                            System.out.println("Empty");

                            JSONObject firstVal = new JSONObject();
                            firstVal.put("v",cell.getCell().getInputValue());
                            arrTemp.add(firstVal);
                        }
                        else {
                            System.out.println(cell.getTitle().getPlainText().substring(1));
                            if(cell.getTitle().getPlainText().substring(1).equals(lstRow)) // the same line
                            {
                                JSONObject firstVal = new JSONObject();
                                firstVal.put("v",cell.getCell().getInputValue());
                                arrTemp.add(firstVal);
                            }
                            else{ // different line
                                JSONObject obj = new JSONObject();
                                obj.put("c", arrTemp);
                                content.add(obj);
                                arrTemp = new JSONArray();
                                JSONObject firstVal = new JSONObject();
                                firstVal.put("v", cell.getCell().getInputValue()); // this will be the time
                                arrTemp.add(firstVal);
                            }
                        }

                        lstRow = cell.getTitle().getPlainText().substring(1);
                    }
              /*      // Print the cell's address in A1 notation
                    System.out.print(cell.getTitle().getPlainText() + "\t");
                    // Print the cell's address in R1C1 notation
                    System.out.print(cell.getId().substring(cell.getId().lastIndexOf('/') + 1) + "\t");
                    // Print the cell's formula or text value
                    System.out.print(cell.getCell().getInputValue() + "\t");
                    // Print the cell's calculated value if the cell's value is numeric
                    // Prints empty string if cell's value is not numeric
                    System.out.print(cell.getCell().getNumericValue() + "\t");
                    // Print the cell's displayed value (useful if the cell has a formula)
                    System.out.println(cell.getCell().getValue() + "\t");
            */
                   // return null;
                }

                JSONObject obj = new JSONObject();
                obj.put("c", arrTemp);
                content.add(obj);


                all.put("cols", header);
                all.put("rows", content);

                return all;
            }

            catch(ServiceException e){
                System.out.println(" ServiceException in UpdateRealTime");
                return null;

            }
            catch(IOException e){
                System.out.println(" IO Exception in UpdateRealTime");
                return null;

            }

        //return null;
    }


        public  static JSONObject UpdatingRealTime( String user, String id)  {


        try {


            SpreadsheetService service2 = new SpreadsheetService("MySpreadsheetIntegration-v1");
            //GoogleCredential credential = getCredentials("", "");
            GoogleCredential googleCredential = getStoredCredentials(user);
            service2.setOAuth2Credentials(googleCredential);


            // TODO: Authorize the service object for a specific user (see other sections)

            // Define the URL to request.  This should never change.
            URL SPREADSHEET_FEED_URL = new URL(
                    "https://spreadsheets.google.com/feeds/spreadsheets/private/full");

            // Make a request to the API and get all spreadsheets.
            SpreadsheetFeed feed = service2.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();

            int loc = 0;
            SpreadsheetEntry mySheet = spreadsheets.get(0);
            // Iterate through all of the spreadsheets returned
            for (SpreadsheetEntry spreadsheet : spreadsheets) {
                // Print the title of this spreadsheet to the screen
                System.out.println(spreadsheet.getTitle().getPlainText());
                System.out.println(spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/") + 1));

                if (spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/") + 1).equals(id)) {
                    mySheet = spreadsheet;
                    System.out.println("yes we have and it is : " + spreadsheet.getId());
                    break;
                }

            }


            JSONObject all = new JSONObject();
            JSONArray header = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("id", "");
            obj.put("label", "Time");
            obj.put("type", "number");
            header.add(obj);
            obj = new JSONObject();
            obj.put("id", "");
            obj.put("label", "Signal");
            obj.put("type", "number");
            header.add(obj);

            JSONArray content = new JSONArray();


            // System.out.println(mySheet.getTitle().getPlainText());
            WorksheetFeed worksheetFeed = service2.getFeed(
                    mySheet.getWorksheetFeedUrl(), WorksheetFeed.class);
            List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
            WorksheetEntry worksheet = worksheets.get(0);

            //URL listFeedUrl = worksheet.getListFeedUrl();
            URL listFeedUrl = new URI(worksheet.getListFeedUrl().toString() + "?orderby=column:time").toURL();


            System.out.println(listFeedUrl);


            ListFeed listFeed = service2.getFeed(listFeedUrl, ListFeed.class);

            for (ListEntry row : listFeed.getEntries()) {
                obj = new JSONObject();
                // Print the first column's cell value
                //System.out.print(row.getTitle().getPlainText() + "\t");
                // Iterate over the remaining columns, and print each cell value

                JSONArray arrTemp = new JSONArray();
                for (String tag : row.getCustomElements().getTags()) {
                    JSONObject firstVal = new JSONObject();
                    firstVal.put("v", row.getCustomElements().getValue(tag));
                    // System.out.print(row.getCustomElements().getValue(tag) + "\t");
                    arrTemp.add(firstVal);
                }
                obj.put("c", arrTemp);
                content.add(obj);
                // System.out.println();
            }

            all.put("cols", header);
            all.put("rows", content);

            //System.out.println(all);
            return all;
        }
        catch(URISyntaxException e){
            System.out.println(" URISyntaxException in UpdateRealTime");
            return null;

        }
        catch(ServiceException e){
            System.out.println(" ServiceException in UpdateRealTime");
            return null;

        }
        catch(IOException e){
         System.out.println(" IO Exception in UpdateRealTime");
            return null;

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
    public  static File insertFile(Drive service, String title, String description,
                                   String parentId, String mimeType, String filename)  throws AuthenticationException, MalformedURLException, IOException, ServiceException {

        SpreadsheetService service2 = new SpreadsheetService("MySpreadsheetIntegration-v1");

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


        File file = null;
        try {
            file = service.files().insert(body, mediaContent).set("convert", true).execute();
            System.out.println(file.getId());
        } catch (IOException e) {
            System.out.println("An error occured: " + e);
            // return null;
        }

        //GoogleCredential credential = getCredentials("", "");
        GoogleCredential googleCredential = getStoredCredentials("cplsubjectbook@gmail.com");
        service2.setOAuth2Credentials(googleCredential);


        // TODO: Authorize the service object for a specific user (see other sections)

        // Define the URL to request.  This should never change.
        URL SPREADSHEET_FEED_URL = new URL(
                "https://spreadsheets.google.com/feeds/spreadsheets/private/full");

        // Make a request to the API and get all spreadsheets.
        SpreadsheetFeed feed = service2.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        int loc = 0;
        // Iterate through all of the spreadsheets returned
        for (SpreadsheetEntry spreadsheet : spreadsheets) {
            // Print the title of this spreadsheet to the screen
            System.out.println(spreadsheet.getTitle().getPlainText());
            System.out.println(spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/")+1));

            if(spreadsheet.getId().substring(spreadsheet.getId().lastIndexOf("/")+1).equals(file.getId())){
                System.out.println( "yes we have and it is : "  + spreadsheet.getId()  );
            }

        }



        SpreadsheetEntry spreadsheet = spreadsheets.get(0);
        System.out.println(spreadsheet.getTitle().getPlainText());

       /* WorksheetEntry worksheet2 = new WorksheetEntry();
        worksheet2.setTitle(new PlainTextConstruct("New Worksheet"));
        worksheet2.setColCount(10);
        worksheet2.setRowCount(20);

        // Send the local representation of the worksheet to the API for
        // creation.  The URL to use here is the worksheet feed URL of our
        // spreadsheet.
        URL worksheetFeedUrl = spreadsheet.getWorksheetFeedUrl();
        service2.insert(worksheetFeedUrl, worksheet2);*/


        WorksheetFeed worksheetFeed = service2.getFeed(
                spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
        WorksheetEntry worksheet = worksheets.get(0);


        // Make a request to the API to fetch information about all
        // worksheets in the spreadsheet.
        /*List<WorksheetEntry> worksheets = spreadsheet.getWorksheets();

        // Iterate through each worksheet in the spreadsheet.
        for (WorksheetEntry worksheet : worksheets) {
            // Get the worksheet's title, row count, and column count.
            String title2 = worksheet.getTitle().getPlainText();
            int rowCount = worksheet.getRowCount();
            int colCount = worksheet.getColCount();

            // Print the fetched information to the screen for this worksheet.
            System.out.println("\t" + title2 + "- rows:" + rowCount + " cols: " + colCount);
        }*/

        URL listFeedUrl = worksheet.getListFeedUrl();


     //   for(int i =0; i< 1000; i++) {
            ListEntry row2 = new ListEntry();
            row2.getCustomElements().setValueLocal("salah", "Joe2");
            row2.getCustomElements().setValueLocal("taamneh", "Smith");
            row2.getCustomElements().setValueLocal("malcom", "26");


            // Send the new row to the API for insertion.
            row2 = service2.insert(listFeedUrl, row2);
       // }


        ListFeed listFeed = service2.getFeed(listFeedUrl, ListFeed.class);

        for (ListEntry row : listFeed.getEntries()) {
            // Print the first column's cell value
            System.out.print(row.getTitle().getPlainText() + "\t");
            // Iterate over the remaining columns, and print each cell value
            for (String tag : row.getCustomElements().getTags()) {
                System.out.print(row.getCustomElements().getValue(tag) + "\t");
            }
            System.out.println();
        }


        return null;


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


    /**
     * Retrieve a list of File resources.
     *
     * @param service Drive API service instance.
     * @return List of File resources.
     */
    /*static List<File> retrieveAllFiles(Drive service, String query)  {
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
    }*/


    /**
     * Print files belonging to a folder.
     *
     * @param service Drive API service instance.
     * @param folderId ID of the folder to print files from.
     */
    /*static void printFilesInFolder(Drive service, String folderId)  {
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
    }*/





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
        //TODO sometime google for some weired reason return back forbbien request make sure to handle this situation
        return  userInfo.getEmail();
    }


    public static String generateFileNameFromInputStream (InputStream input){
        Path destination = null;
        UUID id = UUID.randomUUID();
        String fileName = ProductionSide.tempDownloading() + id +  ".xlsx";


        try {
            destination = Paths.get(fileName);
            Files.copy(input, destination);
            input.close();
            return fileName;
        }
        catch (java.lang.IllegalArgumentException e)
        {
            Logger.error("In generateFileNameFromInputStream " + e);
        }
        catch (Exception e)
        {
            Logger.error("In generateFileNameFromInputStream " + e);
        }
        return null;
    }



    public static List<Change> retrieveAllChanges(Drive service,
                                                   Long startChangeId) throws IOException {
        List<Change> result = new ArrayList<Change>();
        Drive.Changes.List request = service.changes().list();
        //request.setStartChangeId();


        if (startChangeId != null) {
            request.setStartChangeId(startChangeId);
        }
        do {
            System.out.println("SSSSSSSSSSSSS");
            try {
                ChangeList changes = request.execute();

                result.addAll(changes.getItems());
                System.out.println(result);
                request.setPageToken(changes.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null &&
                request.getPageToken().length() > 0);

        System.out.println("I am outttttttttt");
        for (Change change : result) {

            //System.out.println("Change found for file: " + change.getId() +  "    " + change.getFileId() +  "-----"  + change.getKind()  +  "---" + change.getSelfLink());

            if(!change.getKind().contains("change"))
            // Process change
            //change.getId();
            System.out.println("Change found for file: " + change.getId() +  "    " + change.getFileId() +  "-----"  + change.getKind()  +  "---" + change.getSelfLink());
        }

        return result;
    }


    public static void downloadVideo( String username,String url, String whereToSave)
    {

        GoogleCredential googleCredential = getStoredCredentials(username);
        Drive service = buildService(googleCredential);
        InputStream input =  downloadFileByFileId(service, url);


        Path destination = null;

        try {
            destination = Paths.get(whereToSave);
            Files.copy(input, destination);
            input.close();
            //return fileName;
        }
        catch (java.lang.IllegalArgumentException e)
        {
            Logger.error("In generateFileNameFromInputStream " + e);
        }
        catch (Exception e)
        {
            Logger.error("In generateFileNameFromInputStream " + e);
        }
    }


    //SourceType 1: from server, 2: from Google drive
    public static org.json.simple.JSONObject DownloadSignal(String username, String url, int sourceType, int signalType, int frameRate,int first_row, int first_col, String activityFile, String baseLineFile, String baselineSessionName, TreeMap<String, String> desc, int islog, double miny)  throws  Exception
    {

        ReadExcelJava tt = new ReadExcelJava();

        if (sourceType == SharedData.LOCALSERVER)
        {
            Logger.debug("Download the signal from the server, Signal Type is: \" + signalType);");
            InputStream input = new BufferedInputStream(
                    new FileInputStream(url));
            String fileName = generateFileNameFromInputStream(input);


            /*ActorRef myActor = Akka.system().actorOf(Props.create(ReadExcelScala.class, signalType, input));
            Timeout timeout = new Timeout(Duration.create(100, "seconds"));
            Future<Object> future = Patterns.ask(myActor,signalType, timeout);
            */

            org.json.simple.JSONObject x = null;

            x = tt.fromExcelInputTemp(frameRate, fileName, first_row, first_col);

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

            if(input != null) {
                // if the request file is required to be visulaized in column chart
                if (signalType == 4) { //TODO: use constant name
                    //x = ReadExcelJava.fromExcelInputToChar(signalType, fileName);
                    x = tt.fromExcelInputToCharTemp(signalType, fileName, desc);

                } else {
                    if (activityFile != null) {
                        Logger.info("Try to read from excel file with activity");


                        // x = ReadExcelJava.fromExcelInput(signalType, ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)), fileName);
                        if(baseLineFile != null) {
                            InputStream input2 = downloadFileByFileId(service, baseLineFile);
                            String fileName2 = generateFileNameFromInputStream(input2);


                            TreeMap<Double, Double> signal = null;
                            if(fileName2 != null){  // of tje baseline signal is avaialbe. in the case of realtime system the signal might not be avialble
                                System.out.println("what the fuck is going on here guys ");
                                signal = ReadExcelJava.getAllSignalFromExcelAbstraction(fileName2, 1);
                            }

                            //TODO replace 1 with something else that is specific for each signal(i.e, paransal should have 2)

                            x = ReadExcelJava.fromExcelInputTemp(frameRate,signal , baselineSessionName, ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)), fileName, first_row, first_col, islog, miny);
                            if(fileName2 != null)
                                input2.close();
                        }
                        else{
                            System.out.println( "%%%%%%%%%%% "  + ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)).size());
                            x = ReadExcelJava.fromExcelInputTemp(frameRate,null, baselineSessionName, ReadExcelJava.readActivity(downloadFileByFileId(service, activityFile)), fileName, first_row, first_col, islog, miny);
                        }

                    } else {
                        Logger.info("Try to read from excel file without activity");
                        //x = ReadExcelJava.fromExcelInput(signalType, fileName);
                        x = tt.fromExcelInputTemp(frameRate, fileName, first_row, first_col);
                    }

                    input.close();
                }
            }
            else if(signalType == 4){
                x = nasa(username, url);
            }

            return  x;

        }
    }



}
