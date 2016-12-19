
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
    <embed id = "toto" src='https://video.google.com/get_player?docid=0B00ugPsj4f4RakdCZEZmRGpiT0U&ps=docs&partnerid=30&cc_load_policy=1&controls=2&enablejsapi=1&playerapiid=player' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='640' height='385'></embed>
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

<div>
   <!-- <video src="/videoB" controls>
        Your browser does not support the <code>video</code> element.
    </video> -->

    <video controls="controls"  autoplay="autoplay" poster="eh5v.files/html5video/T002-008.jpg" style="width:100%" title="T002-008">
        <source src="/videoB" />
    </video>




</div>




<script>


</script>
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
                  DATE: Tue Sep 27 11:33:10 CDT 2016
                  SOURCE: C:/first_play/app/views/testVideoFromDrive.scala.html
                  HASH: c8d802670a2b0e785439040831134943ffc52151
                  MATRIX: 593->0|794->174|822->175|871->197|899->198|1001->273|1029->274|1065->284|1376->567|1405->568|1438->574|1537->645|1566->646|1595->647|1642->666|1671->667|1702->671|1730->672|1901->816|1929->817|1962->823
                  LINES: 22->1|26->5|26->5|26->5|26->5|28->7|28->7|29->8|37->16|37->16|38->17|40->19|40->19|40->19|40->19|40->19|41->20|41->20|46->25|46->25|47->26
                  -- GENERATED --
              */
          