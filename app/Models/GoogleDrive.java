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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleDrive {
    private static String CLIENT_ID = "1019721884317-30f4nbmi3s1pvq7tglarglbf50f0lf7j.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "M8W3dj1MHyfAIMX686T9mXQ8";

    private static String REDIRECT_URI = "http://www.cpl.uh.edu/";

    public static void main(String[] args) throws IOException {

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


            List<File> result = new ArrayList<File>();
            Drive.Files.List request = service2.files().list();
            request.setQ("title = 'RI_S001-001.Q_EDA'");

            do {
                try {
                    FileList files = request.execute();
                    result.addAll(files.getItems());
                    System.out.println(result.size());
                    request.setPageToken(files.getNextPageToken());
                } catch (IOException e) {
                    System.out.println("An error occurred: " + e);
                    request.setPageToken(null);
                }
            } while (request.getPageToken() != null &&
                    request.getPageToken().length() > 0);

            System.out.println(result.get(0).getTitle());
            System.out.println(MyClass.downloadFile(service2,result.get(0)));

            ReadExcelScala newjs= new ReadExcelScala("" , "", " " );
            newjs.fromExcelInput(MyClass.downloadFile(service2,result.get(0)));

            System.out.println(result.get(0).getParents());
            System.out.println(result.get(0).getDownloadUrl());

            //String downloadUrl = result.get(0).getExportLinks().get("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //System.out.println(downloadUrl);


        } catch (MyClass.CodeExchangeException e) {
            e.printStackTrace();
        } catch (MyClass.NoRefreshTokenException e) {
            e.printStackTrace();
        }


        ////////////////////////////////////////

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
