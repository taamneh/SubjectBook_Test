$(document).ready(function(){
document.getElementById('browseGD').addEventListener("click", function(){

      // The Browser API key obtained from the Google Developers Console.
         var developerKey = 'AIzaSyAwxXjelceSaJQIETuw8miWnkdCBXSDSA4';

         // The Client ID obtained from the Google Developers Console.
         var clientId = '1019721884317-30f4nbmi3s1pvq7tglarglbf50f0lf7j.apps.googleusercontent.com';

         // Scope to use to access user's photos.
         var scope = ['https://www.googleapis.com/auth/drive'];

         var pickerApiLoaded = false;
         var oauthToken;

         onApiLoad();
         // Use the API Loader script to load google.picker and gapi.auth.
         function onApiLoad() {
           gapi.load('auth', {'callback': onAuthApiLoad});
           gapi.load('picker', {'callback': onPickerApiLoad});
         }

         function onAuthApiLoad() {
           window.gapi.auth.authorize(
               {
                 'client_id': clientId,
                 'scope': scope,
                 'immediate': false
               },
               handleAuthResult);
         }

         function onPickerApiLoad() {
           pickerApiLoaded = true;
           createPicker();
         }

         function handleAuthResult(authResult) {
           if (authResult && !authResult.error) {
             oauthToken = authResult.access_token;
             createPicker();
           }
         }

         // Create and render a Picker object for picking user Photos.
         function createPicker() {
           if (pickerApiLoaded && oauthToken) {
            var picker = new google.picker.PickerBuilder()
               .addView(new google.picker.DocsView()
               .setIncludeFolders(true)
                .setMimeTypes('application/vnd.google-apps.folder')
                .setQuery(title = 'root')
                .setSelectFolderEnabled(true))
               .setOAuthToken(oauthToken)
               .setDeveloperKey(developerKey)
               .setCallback(pickerCallback).build();
             picker.setVisible(true);
           }
         }

         // A simple callback implementation.
         function pickerCallback(data) {
           var url = '';
           var id = '';
           if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
             var doc = data[google.picker.Response.DOCUMENTS][0];
             url = doc[google.picker.Document.URL];
             id =  doc[google.picker.Document.ID];
           }
           var message = 'You picked: ' + url;
           $('input[name=url]').val(url);
            $('input[name=folder_id]').val(id);
         }

});

});