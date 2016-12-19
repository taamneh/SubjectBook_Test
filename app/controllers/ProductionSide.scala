package controllers

import java.util

/**
  * Created by staamneh on 9/30/2016.
  */
object ProductionSide {

  val videosLocattion = "C:\\videos\\"
  val tempDownloading = "C:\\temp\\"
  val CLIENTSECRETS_LOCATION = "C:\\first_play\\app\\client_secrets.json";

  val REDIRECT_URI = "http://testsubjectbook.ddns.net/display";
  val  SCOPES = util.Arrays.asList(
    //"https://www.googleapis.com/auth/drive.file",
    "https://spreadsheets.google.com/feeds",
    "https://www.googleapis.com/auth/drive",
    "https://www.googleapis.com/auth/userinfo.email",
    "https://www.googleapis.com/auth/userinfo.profile");
  val CLIENT_ID: String = "214102067690-01cnaes4gde0ufm03k1a4lpr8t7405eb.apps.googleusercontent.com"
  val CLIENT_SECRET: String = "HU34i6LHCV736uDzXUVW8gXs"

}
