
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
object ShowSignals extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template9[String,Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],Int,String,Map[String, List[scala.Tuple2[String, String]]],Int,List[String],List[scala.Tuple3[Int, Int, Int]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(SubjectId: String, sub_sess:Map[String, (String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)])], studyNo:Int, userName: String, videos:  Map[String, List[(String, String)]], study_type: Int, sub_sess_menu: List[String], generalList: List[(Int, Int, Int)], studyName: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;
import java.math.BigInteger; var j =1;

Seq[Any](format.raw/*1.338*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
    <style type="text/css">

        .markerStyle
        """),format.raw/*8.9*/("""{"""),format.raw/*8.10*/("""
        """),format.raw/*9.9*/("""position: absolute;
        left: 40px;
        top: 50px;
        transition: left .5s ease-in, top .5s ease-in;
        width:0.0em;
        height:14.5em;
        #border-left: thick solid #99ccff;
        border-left: thick solid pink;
        white-space:nowrap;
        padding:0.5em;
        z-index: 10;
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/("""


    """),format.raw/*23.5*/("""</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SubjectBook</title>
    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">

    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <!-- <script type='text/javascript' src='"""),_display_(/*41.46*/routes/*41.52*/.Assets.at("javascripts/changevideo.js")),format.raw/*41.92*/("""'></script> -->
    <script type='text/javascript' src='"""),_display_(/*42.42*/routes/*42.48*/.Assets.at("javascripts/signalwithtabNew.js")),format.raw/*42.93*/("""'></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/jquery-ui-1.9.2.css">


    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/chart.min.js"></script>
    <script src="/assets/javascripts/chart-data.js"></script>
    <script src="/assets/javascripts/easypiechart.js"></script>
    <script src="/assets/javascripts/easypiechart-data.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>
    <script>

		!function ($) """),format.raw/*54.17*/("""{"""),format.raw/*54.18*/("""
		    """),format.raw/*55.7*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*55.76*/("""{"""),format.raw/*55.77*/("""
		        """),format.raw/*56.11*/("""$(this).find('em:first').toggleClass("glyphicon-minus");
		    """),format.raw/*57.7*/("""}"""),format.raw/*57.8*/(""");


		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*61.3*/("""}"""),format.raw/*61.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*63.38*/("""{"""),format.raw/*63.39*/("""
		  """),format.raw/*64.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*65.3*/("""}"""),format.raw/*65.4*/(""")
		$(window).on('resize', function () """),format.raw/*66.38*/("""{"""),format.raw/*66.39*/("""
		  """),format.raw/*67.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*68.3*/("""}"""),format.raw/*68.4*/(""")
        // this is to unfold the subject list
        $(document).ready(function()"""),format.raw/*70.37*/("""{"""),format.raw/*70.38*/("""
        """),format.raw/*71.9*/("""$('ul.nav li.parent > a > span.icon').trigger("click");
        """),format.raw/*72.9*/("""}"""),format.raw/*72.10*/(""");

</script>









    <!-- Ignite UI Required Combined CSS Files -->
    <link href="/assets/stylesheets/infragistics.theme.css" rel="stylesheet">
    <!-- <link href="http://cdn-na.infragistics.com/igniteui/2015.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" /> -->
    <!--  <link href="http://cdn-na.infragistics.com/igniteui/2015.1/latest/css/structure/infragistics.css" rel="stylesheet" />-->

    <!-- Used to style the API Viewer and Explorer UI -->
    <!--  <link href="http://www.igniteui.com/css/apiviewer.css" rel="stylesheet" type="text/css" /> -->

    <!--<script src="http://modernizr.com/downloads/modernizr-latest.js"></script> -->
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>

    <!-- Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2015.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2015.1/latest/js/infragistics.dv.js"></script>


</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>Subject</span>Book</a> <a class="navbar-brand" href="/allStudies"><span></span>My Studies</a>
            <!-- <a class="navbar-brand" href="#"><span></span>Share</a> -->
            <a class="navbar-brand" href="/frontPage"><span></span>Help</a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*116.128*/userName),format.raw/*116.136*/(""" """),format.raw/*116.137*/("""<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                        <li><a href="/"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>

    </div><!-- /.container-fluid -->
</nav>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">

        <li><a href="/allStudies"><span class="glyphicon glyphicon-th"></span> My Studies </a></li>
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> New Study</a></li>
        <li><a href="/showAllDataTypes"><span class="glyphicon glyphicon-cog"></span> Manage your Data</a></li>
        <li><a href="/showAllPsychometric"><span class="glyphicon glyphicon-cog"></span> Manage your Psychometric data</a></li>
        <li class="parent ">
            <a href="#">
                <span class="glyphicon glyphicon-list"></span> Subjects <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>
            <ul class="children collapse" id="sub-item-1">
                """),_display_(/*146.18*/for(subject <- sub_sess_menu) yield /*146.47*/{_display_(Seq[Any](format.raw/*146.48*/("""

                """),format.raw/*148.17*/("""<li>

                    <a class="" href="/displaySubject?studyNo="""),_display_(/*150.64*/studyNo),format.raw/*150.71*/("""&SubjectId="""),_display_(/*150.83*/subject),format.raw/*150.90*/("""">
                        <span class="glyphicon glyphicon-user"></span> """),_display_(/*151.73*/subject),format.raw/*151.80*/("""
                    """),format.raw/*152.21*/("""</a>
                </li>


                """)))}),format.raw/*156.18*/("""

            """),format.raw/*158.13*/("""</ul>
        </li>
        <li role="presentation" class="divider"></li>

    </ul>

</div><!--/.sidebar-->

<div id = 'main' class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

    <!-- <img src="/assets/images/ajax-loader.gif" alt="Mountain View" style="width:304px;height:228px"> -->
    <!-- <img src="/assets/images/ajax-loader.gif" alt="Mountain View" style="width:304px;height:228px"> -->

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="/allStudies"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active"><a href="/showStudy/"""),_display_(/*174.53*/studyNo),format.raw/*174.60*/("""">"""),_display_(/*174.63*/studyName),format.raw/*174.72*/(""" """),format.raw/*174.73*/("""</a> </li>
            <li class="active"><a href="/showStudySkipTop/"""),_display_(/*175.60*/studyNo),format.raw/*175.67*/("""">User Portrait </a> </li>
            <li>"""),_display_(/*176.18*/SubjectId),format.raw/*176.27*/("""</li>



            <!-- <li><button id ='hideSideBar'><a href="/file">toggle</a></button></li> -->
        </ol>
    </div><!--/.row-->
    <div class="row">
        </br>
    </div>

    <div class="row" id ="allInfo">


        <!-- <a href="/showRadar?SubjectId="""),_display_(/*190.45*/SubjectId),format.raw/*190.54*/("""&studyNo="""),_display_(/*190.64*/studyNo),format.raw/*190.71*/(""""> Show Session view </a> -->

        """),_display_(/*192.10*/for(temp <- generalList) yield /*192.34*/{_display_(Seq[Any](format.raw/*192.35*/("""

        """),format.raw/*194.9*/("""<div class="col-md-4" id="generalData"""),_display_(/*194.47*/temp/*194.51*/._1),_display_(/*194.55*/temp/*194.59*/._2),format.raw/*194.62*/(""""  subject=""""),_display_(/*194.75*/SubjectId),format.raw/*194.84*/("""" session="sss" signalSequence=""""),_display_(/*194.117*/temp/*194.121*/._1),format.raw/*194.124*/("""" signalCode=""""),_display_(/*194.139*/temp/*194.143*/._2),format.raw/*194.146*/("""" dataType=""""),_display_(/*194.159*/temp/*194.163*/._3),format.raw/*194.166*/("""" style="display: none;">

        </div>

        """)))}),format.raw/*198.10*/("""



    """),format.raw/*202.5*/("""</div><!--/.row-->
    <div class="">
        <div class="panel panel-default">
            <div class="panel-body tabs">
                """),format.raw/*207.1*/("""                """),format.raw/*207.17*/("""<ul class="nav nav-tabs">
                    """),_display_(/*208.22*/for((name,session) <- sub_sess) yield /*208.53*/{_display_(Seq[Any](format.raw/*208.54*/("""
                    """),_display_(/*209.22*/if(i == 1)/*209.32*/ {_display_(Seq[Any](format.raw/*209.34*/("""
                    """),format.raw/*210.21*/("""<li class="active"><a  href="#"""),_display_(/*210.52*/session/*210.59*/._1),format.raw/*210.62*/("""" id="link"""),_display_(/*210.73*/session/*210.80*/._1),format.raw/*210.83*/("""" class="onesignal" subject=""""),_display_(/*210.113*/SubjectId),format.raw/*210.122*/("""" session=""""),_display_(/*210.134*/session/*210.141*/._1),format.raw/*210.144*/("""" newSessionName=""""),_display_(/*210.163*/name),format.raw/*210.167*/("""" studyId="""),_display_(/*210.178*/studyNo),format.raw/*210.185*/("""  """),format.raw/*210.187*/("""occupied ="no" data-toggle="tab">"""),_display_(/*210.221*/name/*210.225*/.replaceFirst("(\\d*\\s*)", "")),format.raw/*210.256*/("""</a></li>
                    """)))}/*211.23*/else/*211.28*/{_display_(Seq[Any](format.raw/*211.29*/("""
                    """),format.raw/*212.21*/("""<li><a  href="#"""),_display_(/*212.37*/session/*212.44*/._1),format.raw/*212.47*/("""" id="link"""),_display_(/*212.58*/session/*212.65*/._1),format.raw/*212.68*/("""" class="onesignal" subject=""""),_display_(/*212.98*/SubjectId),format.raw/*212.107*/("""" session=""""),_display_(/*212.119*/session/*212.126*/._1),format.raw/*212.129*/("""" studyId="""),_display_(/*212.140*/studyNo),format.raw/*212.147*/("""  """),format.raw/*212.149*/("""occupied ="no" data-toggle="tab">"""),_display_(/*212.183*/name/*212.187*/.replaceFirst("(\\d*\\s*)", "")),format.raw/*212.218*/("""</a></li>
                    """)))}),format.raw/*213.22*/("""
                    """),_display_(/*214.22*/{i = i + 1;}),format.raw/*214.34*/("""
                    """)))}),format.raw/*215.22*/("""
                """),format.raw/*216.17*/("""</ul>

                <div class="tab-content">

                    """),format.raw/*221.1*/("""                    """),_display_(/*221.22*/for((name,sessions) <- sub_sess) yield /*221.54*/{_display_(Seq[Any](format.raw/*221.55*/("""
                            """),_display_(/*222.30*/if(j == 1)/*222.40*/ {_display_(Seq[Any](format.raw/*222.42*/("""
                             """),format.raw/*223.30*/("""<div id=""""),_display_(/*223.40*/sessions/*223.48*/._1),format.raw/*223.51*/("""" class="tab-pane fade in active">
                            """)))}/*224.31*/else/*224.36*/{_display_(Seq[Any](format.raw/*224.37*/("""
                            """),format.raw/*225.29*/("""<div id=""""),_display_(/*225.39*/sessions/*225.47*/._1),format.raw/*225.50*/("""" class="tab-pane fade">
                            """)))}),format.raw/*226.30*/("""
                            """),_display_(/*227.30*/{j = j + 1;}),format.raw/*227.42*/("""
                                """),_display_(/*228.34*/for(signal <- sessions._2) yield /*228.60*/{_display_(Seq[Any](format.raw/*228.61*/("""
                                    """),format.raw/*229.37*/("""<!-- to avoid the non signal files -->
                                        """),_display_(/*230.42*/if(signal._3 == 1 || signal._3 == 3 || signal._3 == 4|| signal._3 == 5)/*230.113*/ {_display_(Seq[Any](format.raw/*230.115*/("""
                                        """),format.raw/*231.41*/("""<div  id="dashboard_div"""),_display_(/*231.65*/sessions/*231.73*/._1),_display_(/*231.77*/signal/*231.83*/._2),format.raw/*231.86*/("""">
                                            <div id="chart"""),_display_(/*232.60*/sessions/*232.68*/._1),_display_(/*232.72*/signal/*232.78*/._2),format.raw/*232.81*/("""" class="chart" subject=""""),_display_(/*232.107*/SubjectId),format.raw/*232.116*/("""" session=""""),_display_(/*232.128*/sessions/*232.136*/._1),format.raw/*232.139*/("""" signalSequence=""""),_display_(/*232.158*/signal/*232.164*/._2),format.raw/*232.167*/("""" signalType=""""),_display_(/*232.182*/signal/*232.188*/._3),format.raw/*232.191*/("""" yTitle = """"),_display_(/*232.204*/signal/*232.210*/._4),format.raw/*232.213*/("""" ymin =""""),_display_(/*232.223*/signal/*232.229*/._5),format.raw/*232.232*/("""" ymax =""""),_display_(/*232.242*/signal/*232.248*/._6),format.raw/*232.251*/(""""  log = """"),_display_(/*232.262*/signal/*232.268*/._7),format.raw/*232.271*/("""" style="height: 250px;">
                                            </div>
                                        </div>
                                        <!--<br> -->

                                        <!--<br> -->
                                            """),_display_(/*238.46*/if(signal._3 == 1)/*238.64*/{_display_(Seq[Any](format.raw/*238.65*/("""
                                                """),format.raw/*239.49*/("""&nbsp;<div id="videoboard"""),_display_(/*239.75*/sessions/*239.83*/._1),_display_(/*239.87*/signal/*239.93*/._2),format.raw/*239.96*/("""" class= "video-board" name="videoboard"""),_display_(/*239.136*/sessions/*239.144*/._1),_display_(/*239.148*/signal/*239.154*/._2),format.raw/*239.157*/("""" style="position:relative; left: 40px; width: 80%; height: 400px; overflow-x:scroll;  white-space: nowrap;" hidden>
                                                <div class="button-station"> <a id = "showvideo"""),_display_(/*240.97*/sessions/*240.105*/._1),_display_(/*240.109*/signal/*240.115*/._2),format.raw/*240.118*/("""" class = "btn btn-default show-video" >Hide Videos</a>  </div>
                                                """),_display_(/*241.50*/{import java.math.BigInteger; var i=1}),format.raw/*241.88*/("""
                                                    """),_display_(/*242.54*/for((sess,videoList) <- videos) yield /*242.85*/{_display_(Seq[Any](format.raw/*242.86*/("""
                                                        """),_display_(/*243.58*/if(sess == signal._1)/*243.79*/ {_display_(Seq[Any](format.raw/*243.81*/("""
                                                            """),_display_(/*244.62*/for(video <- videoList) yield /*244.85*/{_display_(Seq[Any](format.raw/*244.86*/("""
                                                                """),_display_(/*245.66*/if(study_type == 1)/*245.85*/ {_display_(Seq[Any](format.raw/*245.87*/("""
                                                                """),format.raw/*246.65*/("""<object id=""""),_display_(/*246.78*/video),format.raw/*246.83*/("""">
                                                                    <param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param>
                                                                    <param name='allowFullScreen' value='true'></param>
                                                                    <param name='allowScriptAccess' value='always'></param>
                                                                    <embed id='video"""),_display_(/*250.86*/video/*250.91*/._2),_display_(/*250.95*/signal/*250.101*/._2),format.raw/*250.104*/("""' src='https://video.google.com/get_player?docid="""),_display_(/*250.154*/video/*250.159*/._2),format.raw/*250.162*/("""&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1&amp;color=white&amp;autoplay=0&amp;enablejsapi=1&amp;playerapiid=video"""),_display_(/*250.284*/video/*250.289*/._2),_display_(/*250.293*/signal/*250.299*/._2),format.raw/*250.302*/("""' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width=50% height='300'></embed>
                                                                </object>
                                                                """)))}/*252.67*/else/*252.72*/{_display_(Seq[Any](format.raw/*252.73*/("""

                                                                """),format.raw/*254.65*/("""<video id=""""),_display_(/*254.77*/video),format.raw/*254.82*/("""" width="640" height="264" controls>
                                                                    <source src="/videoB" controls type='video/mp4' >
                                                                    <source src="file:///"""),_display_(/*256.91*/video/*256.96*/._2),format.raw/*256.99*/(""""
                                                                            type='video/ogg; codecs="theora, vorbis"'>
                                                                    Your user agent does not support the HTML5 Video element.
                                                                </video>

                                                                """)))}),format.raw/*261.66*/(""" """),format.raw/*261.67*/("""<!-- else -->
                                                            """),_display_(/*262.62*/{i = i + 1}),format.raw/*262.73*/("""
                                                            """)))}),format.raw/*263.62*/(""" """),format.raw/*263.63*/("""<!-- video List -->
                                                        """)))}),format.raw/*264.58*/(""" """),format.raw/*264.59*/("""<!-- if sess== session._1 -->
                                                    """)))}),format.raw/*265.54*/("""<!-- main for for videos -->
                                                <br>
                                                <a id = "stopvideo"""),_display_(/*267.68*/sessions/*267.76*/._1),_display_(/*267.80*/signal/*267.86*/._2),format.raw/*267.89*/(""""  class="btn btn-default">Stop Videos</a>   &nbsp; &nbsp;
                                                </div>

                                            """)))}),format.raw/*270.46*/(""" """),format.raw/*270.47*/("""<!-- check the sequence number  -->
                                        """)))}),format.raw/*271.42*/(""" """),format.raw/*271.43*/("""<!-- check the type of signal -->


                            """)))}),format.raw/*274.30*/("""
                            """),format.raw/*275.29*/("""</div>
                        """)))}),format.raw/*276.26*/("""

                    """),format.raw/*278.21*/("""</div>
                </div>
            </div><!--/.panel-->
        </div><!--/.col-->




    </div>	<!--/.main-->



</div>

<div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
</div>

</body>

</html>
"""))}
  }

  def render(SubjectId:String,sub_sess:Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],studyNo:Int,userName:String,videos:Map[String, List[scala.Tuple2[String, String]]],study_type:Int,sub_sess_menu:List[String],generalList:List[scala.Tuple3[Int, Int, Int]],studyName:String): play.twirl.api.HtmlFormat.Appendable = apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName)

  def f:((String,Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],Int,String,Map[String, List[scala.Tuple2[String, String]]],Int,List[String],List[scala.Tuple3[Int, Int, Int]],String) => play.twirl.api.HtmlFormat.Appendable) = (SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName) => apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 22 12:56:51 CDT 2016
                  SOURCE: C:/first_play/app/views/ShowSignals.scala.html
                  HASH: 6c9174efdd89968e34003230d286953a479bd22c
                  MATRIX: 781->1|1282->337|1310->339|1430->433|1458->434|1494->444|1852->775|1881->776|1918->786|2883->1724|2898->1730|2959->1770|3044->1828|3059->1834|3125->1879|3693->2419|3722->2420|3757->2428|3854->2497|3883->2498|3923->2510|4014->2574|4042->2575|4155->2661|4183->2662|4268->2719|4297->2720|4330->2726|4429->2798|4457->2799|4525->2839|4554->2840|4587->2846|4687->2919|4715->2920|4829->3006|4858->3007|4895->3017|4987->3082|5016->3083|7133->5171|7164->5179|7195->5180|8829->6786|8875->6815|8915->6816|8964->6836|9063->6907|9092->6914|9132->6926|9161->6933|9265->7009|9294->7016|9345->7038|9427->7088|9472->7104|10128->7732|10157->7739|10188->7742|10219->7751|10249->7752|10348->7823|10377->7830|10450->7875|10481->7884|10791->8166|10822->8175|10860->8185|10889->8192|10959->8234|11000->8258|11040->8259|11080->8271|11146->8309|11160->8313|11185->8317|11199->8321|11224->8324|11265->8337|11296->8346|11358->8379|11373->8383|11399->8386|11443->8401|11458->8405|11484->8408|11526->8421|11541->8425|11567->8428|11655->8484|11695->8496|11865->8678|11910->8694|11986->8742|12034->8773|12074->8774|12125->8797|12145->8807|12186->8809|12237->8831|12296->8862|12313->8869|12338->8872|12377->8883|12394->8890|12419->8893|12478->8923|12510->8932|12551->8944|12569->8951|12595->8954|12643->8973|12670->8977|12710->8988|12740->8995|12772->8997|12835->9031|12850->9035|12904->9066|12956->9099|12970->9104|13010->9105|13061->9127|13105->9143|13122->9150|13147->9153|13186->9164|13203->9171|13228->9174|13286->9204|13318->9213|13359->9225|13377->9232|13403->9235|13443->9246|13473->9253|13505->9255|13568->9289|13583->9293|13637->9324|13701->9356|13752->9379|13786->9391|13841->9414|13888->9432|13990->9547|14039->9568|14088->9600|14128->9601|14187->9632|14207->9642|14248->9644|14308->9675|14346->9685|14364->9693|14389->9696|14474->9762|14488->9767|14528->9768|14587->9798|14625->9808|14643->9816|14668->9819|14755->9874|14814->9905|14848->9917|14911->9952|14954->9978|14994->9979|15061->10017|15170->10098|15252->10169|15294->10171|15365->10213|15417->10237|15435->10245|15460->10249|15476->10255|15501->10258|15592->10321|15610->10329|15635->10333|15651->10339|15676->10342|15731->10368|15763->10377|15804->10389|15823->10397|15849->10400|15897->10419|15914->10425|15940->10428|15984->10443|16001->10449|16027->10452|16069->10465|16086->10471|16112->10474|16151->10484|16168->10490|16194->10493|16233->10503|16250->10509|16276->10512|16316->10523|16333->10529|16359->10532|16669->10814|16697->10832|16737->10833|16816->10883|16870->10909|16888->10917|16913->10921|16929->10927|16954->10930|17023->10970|17042->10978|17068->10982|17085->10988|17111->10991|17353->11205|17372->11213|17398->11217|17415->11223|17441->11226|17583->11340|17643->11378|17726->11433|17774->11464|17814->11465|17901->11524|17932->11545|17973->11547|18064->11610|18104->11633|18144->11634|18239->11701|18268->11720|18309->11722|18404->11788|18445->11801|18472->11806|19062->12368|19077->12373|19102->12377|19119->12383|19145->12386|19224->12436|19240->12441|19266->12444|19417->12566|19433->12571|19459->12575|19476->12581|19502->12584|19784->12847|19798->12852|19838->12853|19935->12921|19975->12933|20002->12938|20277->13185|20292->13190|20317->13193|20740->13584|20770->13585|20874->13661|20907->13672|21002->13735|21032->13736|21142->13814|21172->13815|21288->13899|21467->14050|21485->14058|21510->14062|21526->14068|21551->14071|21746->14234|21776->14235|21886->14313|21916->14314|22016->14382|22075->14412|22140->14445|22193->14469
                  LINES: 19->1|23->1|24->2|30->8|30->8|31->9|42->20|42->20|45->23|63->41|63->41|63->41|64->42|64->42|64->42|76->54|76->54|77->55|77->55|77->55|78->56|79->57|79->57|83->61|83->61|85->63|85->63|86->64|87->65|87->65|88->66|88->66|89->67|90->68|90->68|92->70|92->70|93->71|94->72|94->72|138->116|138->116|138->116|168->146|168->146|168->146|170->148|172->150|172->150|172->150|172->150|173->151|173->151|174->152|178->156|180->158|196->174|196->174|196->174|196->174|196->174|197->175|197->175|198->176|198->176|212->190|212->190|212->190|212->190|214->192|214->192|214->192|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|216->194|220->198|224->202|228->207|228->207|229->208|229->208|229->208|230->209|230->209|230->209|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|231->210|232->211|232->211|232->211|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|233->212|234->213|235->214|235->214|236->215|237->216|241->221|241->221|241->221|241->221|242->222|242->222|242->222|243->223|243->223|243->223|243->223|244->224|244->224|244->224|245->225|245->225|245->225|245->225|246->226|247->227|247->227|248->228|248->228|248->228|249->229|250->230|250->230|250->230|251->231|251->231|251->231|251->231|251->231|251->231|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|252->232|258->238|258->238|258->238|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|260->240|260->240|260->240|260->240|260->240|261->241|261->241|262->242|262->242|262->242|263->243|263->243|263->243|264->244|264->244|264->244|265->245|265->245|265->245|266->246|266->246|266->246|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|270->250|272->252|272->252|272->252|274->254|274->254|274->254|276->256|276->256|276->256|281->261|281->261|282->262|282->262|283->263|283->263|284->264|284->264|285->265|287->267|287->267|287->267|287->267|287->267|290->270|290->270|291->271|291->271|294->274|295->275|296->276|298->278
                  -- GENERATED --
              */
          