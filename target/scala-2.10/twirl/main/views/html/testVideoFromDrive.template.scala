
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
object testVideoFromDrive extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<html>
<head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", """),format.raw/*5.41*/("""{"""),format.raw/*5.42*/("""packages:["corechart"]"""),format.raw/*5.64*/("""}"""),format.raw/*5.65*/(""");
      google.setOnLoadCallback(drawChart);
      function drawChart() """),format.raw/*7.28*/("""{"""),format.raw/*7.29*/("""
        """),format.raw/*8.9*/("""var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  1000,      400],
          ['2005',  1170,      460],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = """),format.raw/*16.23*/("""{"""),format.raw/*16.24*/("""
    """),format.raw/*17.5*/("""title: 'Company Performance',
    curveType: 'function',
    legend: """),format.raw/*19.13*/("""{"""),format.raw/*19.14*/(""" """),format.raw/*19.15*/("""position: 'bottom' """),format.raw/*19.34*/("""}"""),format.raw/*19.35*/("""
  """),format.raw/*20.3*/("""}"""),format.raw/*20.4*/(""";

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      """),format.raw/*25.7*/("""}"""),format.raw/*25.8*/("""
    """),format.raw/*26.5*/("""</script>
</head>
<body>

<div id="player">
<object width='640' height='385'  id="video1">
    <param name="id" value="myytplayer"> </param>
    <embed id = "toto" src='https://video.google.com/get_player?docid=0B2TDTGk9sqZLWTNSNklUM0xKc2s&ps=docs&partnerid=30&cc_load_policy=1&controls=2&enablejsapi=1&playerapiid=player' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed>
</object>

</div>
<!-- <video width="320" height="240" controls>
    <source src="https://video.google.com/get_player?docid=0B-onIMU0j11bVVJoX0ZMUkZPS0E&ps=docs&partnerid=30&cc_load_policy=1&_=.mp4" type="video/mp4">
    <source src="movie.ogg" type="video/ogg">
    Your browser does not support the video tag.
</video>

<object width='640' height='385'>
    <param name='movie' value='https://video.google.com/get_player?docid=0B5Y-fwYJF2hLMTY1NjA4NjktOGExOS00ZDQzLWEzZGYtZWQ1YTkzNDBlZmMx&ps=docs&partnerid=30'></param>
    <param name='allowFullScreen' value='true'></param>
    <param name='allowScriptAccess' value='always'></param>
    <param name="autoplay" value="true"> </param>
    <embed src='https://video.google.com/get_player?docid=0B5Y-fwYJF2hLMTY1NjA4NjktOGExOS00ZDQzLWEzZGYtZWQ1YTkzNDBlZmMx&ps=docs&partnerid=30' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed>
</object>
<object width='640' height='385'><param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param><param name='allowFullScreen' value='true'></param><param name='allowScriptAccess' value='always'></param><embed src='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed></object>
<object width='640' height='385'><param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLaWwza1VhSWJBRWs&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param><param name='allowFullScreen' value='true'></param><param name='allowScriptAccess' value='always'></param><embed src='https://video.google.com/get_player?docid=0B2TDTGk9sqZLaWwza1VhSWJBRWs&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed></object>
<object width='640' height='385' id='myytplayer'><embed src='https://video.google.com/get_player?docid=0B2TDTGk9sqZLWTNSNklUM0xKc2s&ps=docs&partnerid=30&cc_load_policy=1&amp;color=white&amp;autoplay=0&amp;enablejsapi=1&amp;start=20&amp;playerapiid=myytplayer' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed></object>

-->






<script>

    var player;
    function onYouTubeIframeAPIReady() """),format.raw/*64.40*/("""{"""),format.raw/*64.41*/("""
	
	"""),format.raw/*66.2*/("""if(document.getElementById('myytplayer')!=null)
	"""),format.raw/*67.2*/("""{"""),format.raw/*67.3*/("""
	   """),format.raw/*68.5*/("""alert('exist')
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/("""
	   """),format.raw/*70.5*/("""/* player = document.getElementById('player');
		player.playVideo();
		alert(ply);*/
    """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/("""
	"""),format.raw/*74.2*/("""function onYouTubePlayerReady(playerId)
	"""),format.raw/*75.2*/("""{"""),format.raw/*75.3*/("""

	    """),format.raw/*77.6*/("""//playerId.seekTo(100, true);
	   	player = document.getElementById("toto");
	   	//ytplayer.playVideo()
	   	player.addEventListener("onStateChange", "onytplayerStateChange");
	   	alert('what' + playerId);
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
    """),format.raw/*83.5*/("""function onPlayerReady(evt) """),format.raw/*83.33*/("""{"""),format.raw/*83.34*/("""
	    """),format.raw/*84.6*/("""alert('fuckl');
        evt.target.playVideo();
		
    """),format.raw/*87.5*/("""}"""),format.raw/*87.6*/("""
    """),format.raw/*88.5*/("""function onPlayerStateChange(evt) """),format.raw/*88.39*/("""{"""),format.raw/*88.40*/("""
    """),format.raw/*89.5*/("""alert('fuckl');
        if (evt.data == YT.PlayerState.PLAYING && !done) """),format.raw/*90.58*/("""{"""),format.raw/*90.59*/("""
            """),format.raw/*91.13*/("""setTimeout(stopVideo, 6000);
            done = true;
        """),format.raw/*93.9*/("""}"""),format.raw/*93.10*/("""
    """),format.raw/*94.5*/("""}"""),format.raw/*94.6*/("""
    """),format.raw/*95.5*/("""function stopVideo() """),format.raw/*95.26*/("""{"""),format.raw/*95.27*/("""
        """),format.raw/*96.9*/("""player.stopVideo();
    """),format.raw/*97.5*/("""}"""),format.raw/*97.6*/("""

    """),format.raw/*99.5*/("""function play() """),format.raw/*99.21*/("""{"""),format.raw/*99.22*/("""
    """),format.raw/*100.5*/("""alert('bef');
  if (player) """),format.raw/*101.15*/("""{"""),format.raw/*101.16*/("""
    """),format.raw/*102.5*/("""alert(player);

    player.seekTo(60, true)
    player.playVideo();
    alert(player.getDuration())
  """),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""
"""),format.raw/*108.1*/("""}"""),format.raw/*108.2*/("""
"""),format.raw/*109.1*/("""</script>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
<a href="javascript:void(0);" onclick="play();">Play</a>
</body>
</html>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:26:01 CDT 2015
                  SOURCE: C:/first_play/app/views/testVideoFromDrive.scala.html
                  HASH: 092e466571baaaf3c67ea7f0388d3847279f0211
                  MATRIX: 593->0|794->174|822->175|871->197|899->198|1001->273|1029->274|1065->284|1376->567|1405->568|1438->574|1537->645|1566->646|1595->647|1642->666|1671->667|1702->671|1730->672|1901->816|1929->817|1962->823|5019->3852|5048->3853|5081->3859|5158->3909|5186->3910|5219->3916|5263->3933|5291->3934|5324->3940|5443->4032|5471->4033|5501->4036|5570->4078|5598->4079|5634->4088|5875->4302|5903->4303|5936->4309|5992->4337|6021->4338|6055->4345|6140->4403|6168->4404|6201->4410|6263->4444|6292->4445|6325->4451|6427->4525|6456->4526|6498->4540|6589->4604|6618->4605|6651->4611|6679->4612|6712->4618|6761->4639|6790->4640|6827->4650|6879->4675|6907->4676|6942->4684|6986->4700|7015->4701|7049->4707|7107->4736|7137->4737|7171->4743|7306->4850|7335->4851|7365->4853|7394->4854|7424->4856
                  LINES: 22->1|26->5|26->5|26->5|26->5|28->7|28->7|29->8|37->16|37->16|38->17|40->19|40->19|40->19|40->19|40->19|41->20|41->20|46->25|46->25|47->26|85->64|85->64|87->66|88->67|88->67|89->68|90->69|90->69|91->70|94->73|94->73|95->74|96->75|96->75|98->77|103->82|103->82|104->83|104->83|104->83|105->84|108->87|108->87|109->88|109->88|109->88|110->89|111->90|111->90|112->91|114->93|114->93|115->94|115->94|116->95|116->95|116->95|117->96|118->97|118->97|120->99|120->99|120->99|121->100|122->101|122->101|123->102|128->107|128->107|129->108|129->108|130->109
                  -- GENERATED --
              */
          