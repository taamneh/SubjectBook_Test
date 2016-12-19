
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object googlePicker extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Google Picker Example</title>

    <script type="text/javascript">

      // The Browser API key obtained from the Google Developers Console.
      var developerKey = 'AIzaSyAwxXjelceSaJQIETuw8miWnkdCBXSDSA4';

      // The Client ID obtained from the Google Developers Console.
      var clientId = '1019721884317-30f4nbmi3s1pvq7tglarglbf50f0lf7j.apps.googleusercontent.com';

      // Scope to use to access user's photos.
      var scope = ['https://www.googleapis.com/auth/drive'];

      var pickerApiLoaded = false;
      var oauthToken;

      // Use the API Loader script to load google.picker and gapi.auth.
      function onApiLoad() """),format.raw/*22.28*/("""{"""),format.raw/*22.29*/("""
        """),format.raw/*23.9*/("""gapi.load('auth', """),format.raw/*23.27*/("""{"""),format.raw/*23.28*/("""'callback': onAuthApiLoad"""),format.raw/*23.53*/("""}"""),format.raw/*23.54*/(""");
        gapi.load('picker', """),format.raw/*24.29*/("""{"""),format.raw/*24.30*/("""'callback': onPickerApiLoad"""),format.raw/*24.57*/("""}"""),format.raw/*24.58*/(""");
      """),format.raw/*25.7*/("""}"""),format.raw/*25.8*/("""

      """),format.raw/*27.7*/("""function onAuthApiLoad() """),format.raw/*27.32*/("""{"""),format.raw/*27.33*/("""
        """),format.raw/*28.9*/("""window.gapi.auth.authorize(
            """),format.raw/*29.13*/("""{"""),format.raw/*29.14*/("""
              """),format.raw/*30.15*/("""'client_id': clientId,
              'scope': scope,
              'immediate': false
            """),format.raw/*33.13*/("""}"""),format.raw/*33.14*/(""",
            handleAuthResult);
      """),format.raw/*35.7*/("""}"""),format.raw/*35.8*/("""

      """),format.raw/*37.7*/("""function onPickerApiLoad() """),format.raw/*37.34*/("""{"""),format.raw/*37.35*/("""
        """),format.raw/*38.9*/("""pickerApiLoaded = true;
        createPicker();
      """),format.raw/*40.7*/("""}"""),format.raw/*40.8*/("""

      """),format.raw/*42.7*/("""function handleAuthResult(authResult) """),format.raw/*42.45*/("""{"""),format.raw/*42.46*/("""
        """),format.raw/*43.9*/("""if (authResult && !authResult.error) """),format.raw/*43.46*/("""{"""),format.raw/*43.47*/("""
          """),format.raw/*44.11*/("""oauthToken = authResult.access_token;
          createPicker();
        """),format.raw/*46.9*/("""}"""),format.raw/*46.10*/("""
      """),format.raw/*47.7*/("""}"""),format.raw/*47.8*/("""

      """),format.raw/*49.7*/("""// Create and render a Picker object for picking user Photos.
      function createPicker() """),format.raw/*50.31*/("""{"""),format.raw/*50.32*/("""
        """),format.raw/*51.9*/("""if (pickerApiLoaded && oauthToken) """),format.raw/*51.44*/("""{"""),format.raw/*51.45*/("""
         """),format.raw/*52.10*/("""var picker = new google.picker.PickerBuilder()
            .addView(new google.picker.DocsView()
            .setIncludeFolders(true)
             .setMimeTypes('application/vnd.google-apps.folder')
             .setSelectFolderEnabled(true))
            .setOAuthToken(oauthToken)
            .setDeveloperKey(developerKey)
            .setCallback(pickerCallback).build();
          picker.setVisible(true);
        """),format.raw/*61.9*/("""}"""),format.raw/*61.10*/("""
      """),format.raw/*62.7*/("""}"""),format.raw/*62.8*/("""

      """),format.raw/*64.7*/("""// A simple callback implementation.
      function pickerCallback(data) """),format.raw/*65.37*/("""{"""),format.raw/*65.38*/("""
        """),format.raw/*66.9*/("""var url = 'nothing';
        if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) """),format.raw/*67.81*/("""{"""),format.raw/*67.82*/("""
          """),format.raw/*68.11*/("""var doc = data[google.picker.Response.DOCUMENTS][0];
          url = doc[google.picker.Document.URL];
        """),format.raw/*70.9*/("""}"""),format.raw/*70.10*/("""
        """),format.raw/*71.9*/("""var message = 'You picked: ' + url;
        document.getElementById('result').innerHTML = message;
      """),format.raw/*73.7*/("""}"""),format.raw/*73.8*/("""
    """),format.raw/*74.5*/("""</script>
</head>
<body>
<div id="result"></div>

<!-- The Google API Loader script. -->
<script type="text/javascript" src="https://apis.google.com/js/api.js?onload=onApiLoad"></script>
</body>
</html>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Mar 30 23:41:17 CDT 2016
                  SOURCE: C:/first_play/app/views/googlePicker.scala.html
                  HASH: c7f6028f5b9235e1ab4002aa84e31ada6733b08c
                  MATRIX: 587->0|1431->816|1460->817|1497->827|1543->845|1572->846|1625->871|1654->872|1714->904|1743->905|1798->932|1827->933|1864->943|1892->944|1929->954|1982->979|2011->980|2048->990|2117->1031|2146->1032|2190->1048|2319->1149|2348->1150|2416->1191|2444->1192|2481->1202|2536->1229|2565->1230|2602->1240|2685->1296|2713->1297|2750->1307|2816->1345|2845->1346|2882->1356|2947->1393|2976->1394|3016->1406|3117->1480|3146->1481|3181->1489|3209->1490|3246->1500|3367->1593|3396->1594|3433->1604|3496->1639|3525->1640|3564->1651|4018->2078|4047->2079|4082->2087|4110->2088|4147->2098|4249->2172|4278->2173|4315->2183|4445->2285|4474->2286|4514->2298|4653->2410|4682->2411|4719->2421|4853->2528|4881->2529|4914->2535
                  LINES: 22->1|43->22|43->22|44->23|44->23|44->23|44->23|44->23|45->24|45->24|45->24|45->24|46->25|46->25|48->27|48->27|48->27|49->28|50->29|50->29|51->30|54->33|54->33|56->35|56->35|58->37|58->37|58->37|59->38|61->40|61->40|63->42|63->42|63->42|64->43|64->43|64->43|65->44|67->46|67->46|68->47|68->47|70->49|71->50|71->50|72->51|72->51|72->51|73->52|82->61|82->61|83->62|83->62|85->64|86->65|86->65|87->66|88->67|88->67|89->68|91->70|91->70|92->71|94->73|94->73|95->74
                  -- GENERATED --
              */
          