
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
object ShowSignals extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template12[String,Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],Int,String,Map[String, List[scala.Tuple3[String, String, String]]],Int,List[scala.Tuple3[String, Long, String]],List[scala.Tuple3[Int, Int, Int]],String,Map[String, List[scala.Tuple3[String, String, String]]],List[String],Int,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(SubjectId: String, sub_sess:Map[String, (String, List[(String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int)])], studyNo:Int, userName: String, videos:  Map[String, List[(String, String, String)]], study_type: Int, sub_sess_menu: List[(String, Long, String)], generalList: List[(Int, Int, Int)], studyName: String, audio:  Map[String, List[(String, String, String)]], inProgressSubjects: List[String], isThere : Int):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;
import java.math.BigInteger; var j =1; var t =1; var r=1;

Seq[Any](format.raw/*1.464*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
    <style type="text/css">



    /* The Modal (background) */
.modal """),format.raw/*10.8*/("""{"""),format.raw/*10.9*/("""
    """),format.raw/*11.5*/("""display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
"""),format.raw/*22.1*/("""}"""),format.raw/*22.2*/("""

"""),format.raw/*24.1*/("""/* Modal Content */
.modal-content """),format.raw/*25.16*/("""{"""),format.raw/*25.17*/("""
    """),format.raw/*26.5*/("""position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 80%;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
    -webkit-animation-name: animatetop;
    -webkit-animation-duration: 0.4s;
    animation-name: animatetop;
    animation-duration: 0.4s
"""),format.raw/*37.1*/("""}"""),format.raw/*37.2*/("""



"""),format.raw/*41.1*/("""/* The Close Button */
.close """),format.raw/*42.8*/("""{"""),format.raw/*42.9*/("""
    """),format.raw/*43.5*/("""color: white;
    float: right;
    font-size: 40px;
    font-weight: bold;
"""),format.raw/*47.1*/("""}"""),format.raw/*47.2*/("""

"""),format.raw/*49.1*/(""".close:hover,
.close:focus """),format.raw/*50.14*/("""{"""),format.raw/*50.15*/("""
    """),format.raw/*51.5*/("""color: #000;
    text-decoration: none;
    cursor: pointer;
"""),format.raw/*54.1*/("""}"""),format.raw/*54.2*/("""

"""),format.raw/*56.1*/(""".modal-header """),format.raw/*56.15*/("""{"""),format.raw/*56.16*/("""
    """),format.raw/*57.5*/("""padding: 2px 10px;
    background-color: DodgerBlue ;
    color: white;
"""),format.raw/*60.1*/("""}"""),format.raw/*60.2*/("""

"""),format.raw/*62.1*/(""".modal-body """),format.raw/*62.13*/("""{"""),format.raw/*62.14*/("""padding: 2px 16px;"""),format.raw/*62.32*/("""}"""),format.raw/*62.33*/("""

"""),format.raw/*64.1*/(""".modal-footer """),format.raw/*64.15*/("""{"""),format.raw/*64.16*/("""
    """),format.raw/*65.5*/("""padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
"""),format.raw/*68.1*/("""}"""),format.raw/*68.2*/("""

        """),format.raw/*70.9*/(""".markerStyle
        """),format.raw/*71.9*/("""{"""),format.raw/*71.10*/("""
        """),format.raw/*72.9*/("""position: absolute;
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
        """),format.raw/*83.9*/("""}"""),format.raw/*83.10*/("""




       """),format.raw/*88.8*/(""".blink """),format.raw/*88.15*/("""{"""),format.raw/*88.16*/("""
    """),format.raw/*89.5*/("""animation-duration: 1s;
    animation-name: blink;
    animation-iteration-count: infinite;
    animation-timing-function: steps(2, start);
"""),format.raw/*93.1*/("""}"""),format.raw/*93.2*/("""
"""),format.raw/*94.1*/("""@keyframes blink """),format.raw/*94.19*/("""{"""),format.raw/*94.20*/("""
    """),format.raw/*95.5*/("""80% """),format.raw/*95.9*/("""{"""),format.raw/*95.10*/("""
        """),format.raw/*96.9*/("""visibility: hidden;
    """),format.raw/*97.5*/("""}"""),format.raw/*97.6*/("""
"""),format.raw/*98.1*/("""}"""),format.raw/*98.2*/("""


    """),format.raw/*101.5*/("""</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SubjectBook</title>
    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">

    <!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
    <!--<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->

    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <!-- <script type='text/javascript' src='"""),_display_(/*119.46*/routes/*119.52*/.Assets.at("javascripts/changevideo.js")),format.raw/*119.92*/("""'></script> -->
    <script type='text/javascript' src='"""),_display_(/*120.42*/routes/*120.48*/.Assets.at("javascripts/signalwithtabNew.js")),format.raw/*120.93*/("""'></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/jquery-ui-1.9.2.css">


    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/chart.min.js"></script>
    <script src="/assets/javascripts/chart-data.js"></script>
    <script src="/assets/javascripts/easypiechart.js"></script>
    <script src="/assets/javascripts/easypiechart-data.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>
    <script>

		!function ($) """),format.raw/*132.17*/("""{"""),format.raw/*132.18*/("""
		    """),format.raw/*133.7*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*133.76*/("""{"""),format.raw/*133.77*/("""
		        """),format.raw/*134.11*/("""$(this).find('em:first').toggleClass("glyphicon-minus");
		    """),format.raw/*135.7*/("""}"""),format.raw/*135.8*/(""");


		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*139.3*/("""}"""),format.raw/*139.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*141.38*/("""{"""),format.raw/*141.39*/("""
		  """),format.raw/*142.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*143.3*/("""}"""),format.raw/*143.4*/(""")
		$(window).on('resize', function () """),format.raw/*144.38*/("""{"""),format.raw/*144.39*/("""
		  """),format.raw/*145.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*146.3*/("""}"""),format.raw/*146.4*/(""")
        // this is to unfold the subject list
        $(document).ready(function()"""),format.raw/*148.37*/("""{"""),format.raw/*148.38*/("""
        """),format.raw/*149.9*/("""$('ul.nav li.parent > a > span.icon').trigger("click");
        """),format.raw/*150.9*/("""}"""),format.raw/*150.10*/(""");

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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*194.128*/userName),format.raw/*194.136*/(""" """),format.raw/*194.137*/("""<span class="caret"></span></a>
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
                <span class="glyphicon glyphicon-list"></span> In Progress Subjects <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>
            <ul class="children collapse1">
                """),_display_(/*224.18*/for(sub <- inProgressSubjects) yield /*224.48*/{_display_(Seq[Any](format.raw/*224.49*/("""
                """),format.raw/*225.17*/("""<li>
                    <a class="" href="/displaySubject?studyNo="""),_display_(/*226.64*/studyNo),format.raw/*226.71*/("""&SubjectId="""),_display_(/*226.83*/sub),format.raw/*226.86*/("""">
                       <div  class="blink"> <span class="glyphicon glyphicon-user" style="color:red"></span> <b style="color:red" class="blink"> """),_display_(/*227.147*/sub),format.raw/*227.150*/("""  """),format.raw/*227.152*/("""</b> </div>
                    </a>
                </li>
                """)))}),format.raw/*230.18*/("""
            """),format.raw/*231.13*/("""</ul>
            <a href="#">
                <span class="glyphicon glyphicon-list"></span> Subjects <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>

            <ul class="children collapse" id="sub-item-1">
                """),_display_(/*237.18*/for((subject, count, grp) <- sub_sess_menu) yield /*237.61*/{_display_(Seq[Any](format.raw/*237.62*/("""
                """),format.raw/*238.17*/("""<li>

                    <a class="" href="/displaySubject?studyNo="""),_display_(/*240.64*/studyNo),format.raw/*240.71*/("""&SubjectId="""),_display_(/*240.83*/subject),format.raw/*240.90*/("""">
                        """),_display_(/*241.26*/if(count > 0)/*241.39*/ {_display_(Seq[Any](format.raw/*241.41*/("""
                                    """),_display_(/*242.38*/if(grp == "SIT")/*242.54*/ {_display_(Seq[Any](format.raw/*242.56*/("""
                                        """),format.raw/*243.41*/("""<span class="glyphicon glyphicon-user" style="color:red"></span> <b style="color:red"> """),_display_(/*243.129*/subject),format.raw/*243.136*/("""  """),format.raw/*243.138*/("""["""),_display_(/*243.140*/grp),format.raw/*243.143*/("""] </b>
                                    """)))}/*244.39*/else/*244.44*/{_display_(Seq[Any](format.raw/*244.45*/(""" """),_display_(/*244.47*/if(grp == "Control")/*244.67*/ {_display_(Seq[Any](format.raw/*244.69*/("""
                                         """),format.raw/*245.42*/("""<span class="glyphicon glyphicon-user" style="color:green" ></span> <b style="color:green"> """),_display_(/*245.135*/subject),format.raw/*245.142*/(""" """),format.raw/*245.143*/("""["""),_display_(/*245.145*/grp),format.raw/*245.148*/("""]  </b>
                                          """)))}/*246.46*/else/*246.51*/{_display_(Seq[Any](format.raw/*246.52*/("""
                                                """),format.raw/*247.49*/("""<span class="glyphicon glyphicon-user" ></span> <b> """),_display_(/*247.102*/subject),format.raw/*247.109*/("""   """),format.raw/*247.112*/("""</b>
                                                """)))}),format.raw/*248.50*/("""

                                    """)))}),format.raw/*250.38*/("""
                        """)))}/*251.27*/else/*251.32*/{_display_(Seq[Any](format.raw/*251.33*/("""
                        """),format.raw/*252.25*/("""<span class="glyphicon glyphicon-user" style="color:grey"></span> <b style="color:grey"> """),_display_(/*252.115*/subject),format.raw/*252.122*/(""" """),format.raw/*252.123*/("""</b>
                        """)))}),format.raw/*253.26*/("""

                    """),format.raw/*255.21*/("""</a>
                </li>


                """)))}),format.raw/*259.18*/("""

            """),format.raw/*261.13*/("""</ul>
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
            <li class="active"><a href="/showStudy/"""),_display_(/*277.53*/studyNo),format.raw/*277.60*/("""">"""),_display_(/*277.63*/studyName),format.raw/*277.72*/(""" """),format.raw/*277.73*/("""</a> </li>
            <li class="active"><a href="/showStudySkipTop/"""),_display_(/*278.60*/studyNo),format.raw/*278.67*/("""">User Portrait </a> </li>
            <li>"""),_display_(/*279.18*/SubjectId),format.raw/*279.27*/("""</li>



            <!-- <li><button id ='hideSideBar'><a href="/file">toggle</a></button></li> -->
        </ol>
    </div><!--/.row-->
    <div class="row">
        </br>
    </div>

    <div class="row" id ="allInfo">


        <!-- <a href="/showRadar?SubjectId="""),_display_(/*293.45*/SubjectId),format.raw/*293.54*/("""&studyNo="""),_display_(/*293.64*/studyNo),format.raw/*293.71*/(""""> Show Session view </a> -->

        """),_display_(/*295.10*/for(temp <- generalList) yield /*295.34*/{_display_(Seq[Any](format.raw/*295.35*/("""

        """),format.raw/*297.9*/("""<div class="col-md-4" id="generalData"""),_display_(/*297.47*/temp/*297.51*/._1),_display_(/*297.55*/temp/*297.59*/._2),format.raw/*297.62*/(""""  subject=""""),_display_(/*297.75*/SubjectId),format.raw/*297.84*/("""" session="sss" signalSequence=""""),_display_(/*297.117*/temp/*297.121*/._1),format.raw/*297.124*/("""" signalCode=""""),_display_(/*297.139*/temp/*297.143*/._2),format.raw/*297.146*/("""" dataType=""""),_display_(/*297.159*/temp/*297.163*/._3),format.raw/*297.166*/("""" studyId="""),_display_(/*297.177*/studyNo),format.raw/*297.184*/(""" """),format.raw/*297.185*/("""style="display: none;">

        </div>

        """)))}),format.raw/*301.10*/("""



    """),format.raw/*305.5*/("""</div><!--/.row-->


    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">×</span>
                <h2>Help</h2>
            </div>
            <div class="modal-body">
                <!--<p>Some text in the Modal Body</p>
                <p>Some other text...</p> -->
                <img src='"""),_display_(/*319.28*/routes/*319.34*/.Assets.at("images/help.gif")),format.raw/*319.63*/("""'  alt="Mountain View" style="width:100%;height:100%;">
            </div>
            <!--<div class="modal-footer">
              <h3>Modal Footer</h3>
            </div> -->
        </div>

    </div>
    <script>
// Get the modal
var modal = document.getElementById('myModal');

var ctr = 0;

// Get the button that opens the modal
//var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
/*btn.onclick = function() """),format.raw/*340.28*/("""{"""),format.raw/*340.29*/("""
    """),format.raw/*341.5*/("""modal.style.display = "block";
"""),format.raw/*342.1*/("""}"""),format.raw/*342.2*/("""*/


function myFunction() """),format.raw/*345.23*/("""{"""),format.raw/*345.24*/("""
"""),format.raw/*346.1*/("""if ("""),_display_(/*346.6*/isThere),format.raw/*346.13*/(""" """),format.raw/*346.14*/("""== 0 && ctr ==0)
    modal.style.display = "block";
    ctr =1;
"""),format.raw/*349.1*/("""}"""),format.raw/*349.2*/("""

"""),format.raw/*351.1*/("""// When the user clicks on <span> (x), close the modal
span.onclick = function() """),format.raw/*352.27*/("""{"""),format.raw/*352.28*/("""
    """),format.raw/*353.5*/("""modal.style.display = "none";
"""),format.raw/*354.1*/("""}"""),format.raw/*354.2*/("""

"""),format.raw/*356.1*/("""// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) """),format.raw/*357.34*/("""{"""),format.raw/*357.35*/("""
    """),format.raw/*358.5*/("""if (event.target == modal) """),format.raw/*358.32*/("""{"""),format.raw/*358.33*/("""
        """),format.raw/*359.9*/("""modal.style.display = "none";
    """),format.raw/*360.5*/("""}"""),format.raw/*360.6*/("""
"""),format.raw/*361.1*/("""}"""),format.raw/*361.2*/("""
"""),format.raw/*362.1*/("""</script>

    <div class="">
        <div class="panel panel-default">
            <div class="panel-body tabs">
                """),format.raw/*368.1*/("""                """),format.raw/*368.17*/("""<ul class="nav nav-tabs">
                    """),_display_(/*369.22*/for((name,session) <- sub_sess) yield /*369.53*/{_display_(Seq[Any](format.raw/*369.54*/("""
                    """),_display_(/*370.22*/if(i == 1)/*370.32*/ {_display_(Seq[Any](format.raw/*370.34*/("""
                    """),format.raw/*371.21*/("""<li class="active"><a  href="#"""),_display_(/*371.52*/session/*371.59*/._1),format.raw/*371.62*/("""" id="link"""),_display_(/*371.73*/session/*371.80*/._1),format.raw/*371.83*/("""" class="onesignal" subject=""""),_display_(/*371.113*/SubjectId),format.raw/*371.122*/("""" session=""""),_display_(/*371.134*/session/*371.141*/._1),format.raw/*371.144*/("""" newSessionName=""""),_display_(/*371.163*/name),format.raw/*371.167*/("""" studyId="""),_display_(/*371.178*/studyNo),format.raw/*371.185*/("""  """),format.raw/*371.187*/("""occupied ="no" data-toggle="tab">"""),_display_(/*371.221*/name/*371.225*/.replaceFirst("(\\d*\\s*)", "")),format.raw/*371.256*/("""</a></li>
                    """)))}/*372.23*/else/*372.28*/{_display_(Seq[Any](format.raw/*372.29*/("""
                    """),format.raw/*373.21*/("""<li><a  href="#"""),_display_(/*373.37*/session/*373.44*/._1),format.raw/*373.47*/("""" id="link"""),_display_(/*373.58*/session/*373.65*/._1),format.raw/*373.68*/("""" class="onesignal" subject=""""),_display_(/*373.98*/SubjectId),format.raw/*373.107*/("""" session=""""),_display_(/*373.119*/session/*373.126*/._1),format.raw/*373.129*/("""" newSessionName=""""),_display_(/*373.148*/name),format.raw/*373.152*/("""" studyId="""),_display_(/*373.163*/studyNo),format.raw/*373.170*/("""  """),format.raw/*373.172*/("""occupied ="no" data-toggle="tab">"""),_display_(/*373.206*/name/*373.210*/.replaceFirst("(\\d*\\s*)", "")),format.raw/*373.241*/("""</a></li>
                    """)))}),format.raw/*374.22*/("""
                    """),_display_(/*375.22*/{i = i + 1;}),format.raw/*375.34*/("""
                    """)))}),format.raw/*376.22*/("""
                """),format.raw/*377.17*/("""</ul>

                <div class="tab-content"  onmouseover = "myFunction()">

                    """),format.raw/*382.1*/("""                    """),_display_(/*382.22*/for((name,sessions) <- sub_sess) yield /*382.54*/{_display_(Seq[Any](format.raw/*382.55*/("""
                            """),_display_(/*383.30*/if(j == 1)/*383.40*/ {_display_(Seq[Any](format.raw/*383.42*/("""
                             """),format.raw/*384.30*/("""<div id=""""),_display_(/*384.40*/sessions/*384.48*/._1),format.raw/*384.51*/("""" class="tab-pane fade in active">
                            """)))}/*385.31*/else/*385.36*/{_display_(Seq[Any](format.raw/*385.37*/("""
                            """),format.raw/*386.29*/("""<div id=""""),_display_(/*386.39*/sessions/*386.47*/._1),format.raw/*386.50*/("""" class="tab-pane fade">
                            """)))}),format.raw/*387.30*/("""
                            """),_display_(/*388.30*/{j = j + 1;}),format.raw/*388.42*/("""
                                """),_display_(/*389.34*/{r = 1}),format.raw/*389.41*/("""
                                """),_display_(/*390.34*/{t = 1}),format.raw/*390.41*/("""
                                """),_display_(/*391.34*/for(signal <- sessions._2) yield /*391.60*/{_display_(Seq[Any](format.raw/*391.61*/("""
                                    """),format.raw/*392.37*/("""<!-- to avoid the non signal files -->
                                        """),_display_(/*393.42*/if(signal._3 == 1 || signal._3 == 3 || signal._3 == 4|| signal._3 == 5)/*393.113*/ {_display_(Seq[Any](format.raw/*393.115*/("""
                                        """),format.raw/*394.41*/("""<div  id="dashboard_div"""),_display_(/*394.65*/sessions/*394.73*/._1),_display_(/*394.77*/signal/*394.83*/._2),format.raw/*394.86*/("""">
                                            <div id="chart"""),_display_(/*395.60*/sessions/*395.68*/._1),_display_(/*395.72*/signal/*395.78*/._2),format.raw/*395.81*/("""" class="chart" subject=""""),_display_(/*395.107*/SubjectId),format.raw/*395.116*/("""" session=""""),_display_(/*395.128*/sessions/*395.136*/._1),format.raw/*395.139*/("""" newSessionName=""""),_display_(/*395.158*/name),format.raw/*395.162*/("""" signalSequence=""""),_display_(/*395.181*/signal/*395.187*/._2),format.raw/*395.190*/("""" signalType=""""),_display_(/*395.205*/signal/*395.211*/._3),format.raw/*395.214*/("""" yTitle = """"),_display_(/*395.227*/signal/*395.233*/._4),format.raw/*395.236*/("""" ymin =""""),_display_(/*395.246*/signal/*395.252*/._5),format.raw/*395.255*/("""" ymax =""""),_display_(/*395.265*/signal/*395.271*/._6),format.raw/*395.274*/(""""  log = """"),_display_(/*395.285*/signal/*395.291*/._7),format.raw/*395.294*/("""" style="height: 250px;">
                                            </div>
                                        </div>
                                        <!--<br> -->

                                        <!--<br> -->
                                            """),_display_(/*401.46*/if(signal._3 == 1)/*401.64*/{_display_(Seq[Any](format.raw/*401.65*/("""
                                                """),format.raw/*402.49*/("""&nbsp;<div id="videoboard"""),_display_(/*402.75*/sessions/*402.83*/._1),_display_(/*402.87*/signal/*402.93*/._2),format.raw/*402.96*/("""" class= "video-board" name="videoboard"""),_display_(/*402.136*/sessions/*402.144*/._1),_display_(/*402.148*/signal/*402.154*/._2),format.raw/*402.157*/("""" style="position:relative; left: 40px; width: 80%; height: 400px; overflow-x:scroll;  white-space: nowrap;" hidden>
                                                <div class="button-station"> <a id = "showvideo"""),_display_(/*403.97*/sessions/*403.105*/._1),_display_(/*403.109*/signal/*403.115*/._2),format.raw/*403.118*/("""" class = "btn btn-default show-video" >Hide Videos</a>  </div>
                                                <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
                                                    """),_display_(/*405.54*/for((sess,videoList) <- videos) yield /*405.85*/{_display_(Seq[Any](format.raw/*405.86*/("""

                                                        """),_display_(/*407.58*/if(sess == signal._1)/*407.79*/ {_display_(Seq[Any](format.raw/*407.81*/("""
                                                            """),_display_(/*408.62*/for(video <- videoList) yield /*408.85*/{_display_(Seq[Any](format.raw/*408.86*/("""
                                                                """),_display_(/*409.66*/if(study_type == 1)/*409.85*/ {_display_(Seq[Any](format.raw/*409.87*/("""
                                                                    """),_display_(/*410.70*/if(r <= 1)/*410.80*/ {_display_(Seq[Any](format.raw/*410.82*/("""

                                                                        """),format.raw/*412.73*/("""<meta  http-equiv="X-UA-Compatible" content="IE=Edge"/>
                                                                        <video controls="controls"  id="videofile"""),_display_(/*413.115*/video/*413.120*/._2),_display_(/*413.124*/signal/*413.130*/._2),format.raw/*413.133*/("""" width="400" height="300" class = "sal" vName =""""),_display_(/*413.183*/video/*413.188*/._3),format.raw/*413.191*/("""" sess=""""),_display_(/*413.200*/sessions/*413.208*/._1),format.raw/*413.211*/("""" sub= """"),_display_(/*413.220*/SubjectId),format.raw/*413.229*/("""" >
                                                                            <source src="" type='video/mp4'/>
                                                                            <!--<source src="/videoC?studyNo="""),_display_(/*415.111*/studyNo),format.raw/*415.118*/("""&subjectId="""),_display_(/*415.130*/SubjectId),format.raw/*415.139*/("""&sessionName="""),_display_(/*415.153*/sessions/*415.161*/._1),format.raw/*415.164*/("""&fileName="""),_display_(/*415.175*/video/*415.180*/._3),format.raw/*415.183*/("""" type='video/mp4'/>-->
                                                                             <!-- <source src="https://drive.google.com/uc?export=download&id="""),_display_(/*416.144*/video/*416.149*/._2),format.raw/*416.152*/("""" type='video/mp4'/>
                                                                             <!-- <source src="https://googledrive.com/host/"""),_display_(/*417.126*/video/*417.131*/._2),format.raw/*417.134*/("""" type='video/mp4'/> -->
                                                                             <!-- <source src="/videoB" type='video/mp4'/>-->
                                                                        </video>




                                                                                """)))}),format.raw/*424.82*/("""
                                                                        """),format.raw/*425.73*/("""<!--<object  id=""""),_display_(/*425.91*/video),format.raw/*425.96*/("""" class = "vdTag">
                                                                            <param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param>
                                                                            <param name='allowFullScreen' value='true'></param>
                                                                            <param name='allowScriptAccess' value='always'></param>
                                                                            <embed id='video"""),_display_(/*429.94*/video/*429.99*/._2),_display_(/*429.103*/signal/*429.109*/._2),format.raw/*429.112*/("""' src='https://video.google.com/get_player?docid="""),_display_(/*429.162*/video/*429.167*/._2),format.raw/*429.170*/("""&amp;enablejsapi=1&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1&amp;playerapiid=video"""),_display_(/*429.261*/video/*429.266*/._2),_display_(/*429.270*/signal/*429.276*/._2),format.raw/*429.279*/("""' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width=50% height='300'></embed>
                                                                        </object> -->


                                                                """)))}/*433.67*/else/*433.72*/{_display_(Seq[Any](format.raw/*433.73*/("""

                                                                """),format.raw/*435.65*/("""<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
                                                                <video id=""""),_display_(/*436.77*/video),format.raw/*436.82*/("""" width="640" height="264" controls>
                                                                    <source src="/videoB" controls type='video/mp4' >
                                                                    <source src="file:///"""),_display_(/*438.91*/video/*438.96*/._2),format.raw/*438.99*/(""""
                                                                            type='video/ogg; codecs="theora, vorbis"'>
                                                                    Your user agent does not support the HTML5 Video element.
                                                                </video>

                                                                """)))}),format.raw/*443.66*/(""" """),format.raw/*443.67*/("""<!-- else -->

                                                            """)))}),format.raw/*445.62*/(""" """),format.raw/*445.63*/("""<!-- video List -->
                                                            """),_display_(/*446.62*/{r = r + 1}),format.raw/*446.73*/("""
                                                        """)))}),format.raw/*447.58*/(""" """),format.raw/*447.59*/("""<!-- if sess== session._1 -->
                                                    """)))}),format.raw/*448.54*/("""<!-- main for for videos -->


                                                    """),_display_(/*451.54*/for((sess,videoList) <- audio) yield /*451.84*/{_display_(Seq[Any](format.raw/*451.85*/("""
                                                        """),_display_(/*452.58*/if(sess == signal._1)/*452.79*/ {_display_(Seq[Any](format.raw/*452.81*/("""
                                                                """),_display_(/*453.66*/for(video <- videoList) yield /*453.89*/{_display_(Seq[Any](format.raw/*453.90*/("""
                                                                        """),_display_(/*454.74*/if(study_type == 1)/*454.93*/ {_display_(Seq[Any](format.raw/*454.95*/("""
                                                                                """),_display_(/*455.82*/if(t == 1)/*455.92*/ {_display_(Seq[Any](format.raw/*455.94*/("""
                                                                                    """),format.raw/*456.85*/("""<!-- <audio controls="controls" id=""""),_display_(/*456.122*/video/*456.127*/._2),format.raw/*456.130*/(""""  src="https://googledrive.com/host/"""),_display_(/*456.168*/video/*456.173*/._2),format.raw/*456.176*/("""" type=audio/x-wav" width="400" height="300" class = "sal" vName =""""),_display_(/*456.244*/video/*456.249*/._3),format.raw/*456.252*/("""" sess=""""),_display_(/*456.261*/sessions/*456.269*/._1),format.raw/*456.272*/("""" sub= """"),_display_(/*456.281*/SubjectId),format.raw/*456.290*/(""""> </audio> -->
                                                                                    <audio controls id=""""),_display_(/*457.106*/video/*457.111*/._2),format.raw/*457.114*/("""" width="400" height="300" class = "sal"  vName =""""),_display_(/*457.165*/video/*457.170*/._3),format.raw/*457.173*/("""" sess=""""),_display_(/*457.182*/sessions/*457.190*/._1),format.raw/*457.193*/("""" sub= """"),_display_(/*457.202*/SubjectId),format.raw/*457.211*/("""">
                                                                                        <source src="" type="audio/mpeg">
                                                                                        <p id = """"),_display_(/*459.99*/t),format.raw/*459.100*/("""" >Your browser does not support the <code>audio</code> element </p>
                                                                                    </audio>
                                                                                """)))}),format.raw/*461.82*/("""
                                                                            """)))}),format.raw/*462.78*/("""
                                                                         """),_display_(/*463.75*/{t = t + 1}),format.raw/*463.86*/("""
                                                                """)))}),format.raw/*464.66*/(""" """),format.raw/*464.67*/("""<!-- video List -->

                                                        """)))}),format.raw/*466.58*/(""" """),format.raw/*466.59*/("""<!-- if sess== session._1 -->
                                                    """)))}),format.raw/*467.54*/("""<!-- main for for videos -->

                                                <br>
                                                <a id = "stopvideo"""),_display_(/*470.68*/sessions/*470.76*/._1),_display_(/*470.80*/signal/*470.86*/._2),format.raw/*470.89*/(""""  class="btn btn-default">Stop Videos</a>   &nbsp; &nbsp;
                                                </div>

                                            """)))}),format.raw/*473.46*/(""" """),format.raw/*473.47*/("""<!-- check the sequence number  -->
                                        """)))}),format.raw/*474.42*/(""" """),format.raw/*474.43*/("""<!-- check the type of signal -->


                            """)))}),format.raw/*477.30*/("""
                            """),format.raw/*478.29*/("""</div>
                        """)))}),format.raw/*479.26*/("""

                    """),format.raw/*481.21*/("""</div>
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

  def render(SubjectId:String,sub_sess:Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],studyNo:Int,userName:String,videos:Map[String, List[scala.Tuple3[String, String, String]]],study_type:Int,sub_sess_menu:List[scala.Tuple3[String, Long, String]],generalList:List[scala.Tuple3[Int, Int, Int]],studyName:String,audio:Map[String, List[scala.Tuple3[String, String, String]]],inProgressSubjects:List[String],isThere:Int): play.twirl.api.HtmlFormat.Appendable = apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName,audio,inProgressSubjects,isThere)

  def f:((String,Map[String, scala.Tuple2[String, List[scala.Tuple7[String, Int, Int, Option[String], Option[java.math.BigDecimal], Option[java.math.BigDecimal], Int]]]],Int,String,Map[String, List[scala.Tuple3[String, String, String]]],Int,List[scala.Tuple3[String, Long, String]],List[scala.Tuple3[Int, Int, Int]],String,Map[String, List[scala.Tuple3[String, String, String]]],List[String],Int) => play.twirl.api.HtmlFormat.Appendable) = (SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName,audio,inProgressSubjects,isThere) => apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName,audio,inProgressSubjects,isThere)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Dec 02 17:02:45 CST 2016
                  SOURCE: C:/first_play/app/views/ShowSignals.scala.html
                  HASH: b5fb625bbe5512014c4e236b6bebd97de1800585
                  MATRIX: 891->1|1537->463|1565->465|1701->574|1729->575|1762->581|2225->1017|2253->1018|2284->1022|2348->1058|2377->1059|2410->1065|2791->1419|2819->1420|2854->1428|2912->1459|2940->1460|2973->1466|3080->1546|3108->1547|3139->1551|3195->1579|3224->1580|3257->1586|3348->1650|3376->1651|3407->1655|3449->1669|3478->1670|3511->1676|3613->1751|3641->1752|3672->1756|3712->1768|3741->1769|3787->1787|3816->1788|3847->1792|3889->1806|3918->1807|3951->1813|4049->1884|4077->1885|4116->1897|4165->1919|4194->1920|4231->1930|4589->2261|4618->2262|4662->2279|4697->2286|4726->2287|4759->2293|4930->2437|4958->2438|4987->2440|5032->2458|5061->2459|5094->2465|5125->2469|5154->2470|5191->2480|5243->2505|5271->2506|5300->2508|5328->2509|5366->2519|6349->3474|6365->3480|6427->3520|6513->3578|6529->3584|6596->3629|7165->4169|7195->4170|7231->4178|7329->4247|7359->4248|7400->4260|7492->4324|7521->4325|7635->4411|7664->4412|7750->4469|7780->4470|7814->4476|7914->4548|7943->4549|8012->4589|8042->4590|8076->4596|8177->4669|8206->4670|8321->4756|8351->4757|8389->4767|8482->4832|8512->4833|10629->6921|10660->6929|10691->6930|12322->8533|12369->8563|12409->8564|12456->8582|12553->8651|12582->8658|12622->8670|12647->8673|12826->8823|12852->8826|12884->8828|12995->8907|13038->8921|13403->9258|13463->9301|13503->9302|13550->9320|13649->9391|13678->9398|13718->9410|13747->9417|13804->9446|13827->9459|13868->9461|13935->9500|13961->9516|14002->9518|14073->9560|14190->9648|14220->9655|14252->9657|14283->9659|14309->9662|14374->9708|14388->9713|14428->9714|14458->9716|14488->9736|14529->9738|14601->9781|14723->9874|14753->9881|14784->9882|14815->9884|14841->9887|14913->9941|14927->9946|14967->9947|15046->9997|15128->10050|15158->10057|15191->10060|15278->10115|15351->10156|15398->10184|15412->10189|15452->10190|15507->10216|15626->10306|15656->10313|15687->10314|15750->10345|15803->10369|15885->10419|15930->10435|16586->11063|16615->11070|16646->11073|16677->11082|16707->11083|16806->11154|16835->11161|16908->11206|16939->11215|17249->11497|17280->11506|17318->11516|17347->11523|17417->11565|17458->11589|17498->11590|17538->11602|17604->11640|17618->11644|17643->11648|17657->11652|17682->11655|17723->11668|17754->11677|17816->11710|17831->11714|17857->11717|17901->11732|17916->11736|17942->11739|17984->11752|17999->11756|18025->11759|18065->11770|18095->11777|18126->11778|18212->11832|18252->11844|18718->12282|18734->12288|18785->12317|19401->12904|19431->12905|19465->12911|19525->12943|19554->12944|19613->12974|19643->12975|19673->12977|19705->12982|19734->12989|19764->12990|19859->13057|19888->13058|19920->13062|20031->13144|20061->13145|20095->13151|20154->13182|20183->13183|20215->13187|20342->13285|20372->13286|20406->13292|20462->13319|20492->13320|20530->13330|20593->13365|20622->13366|20652->13368|20681->13369|20711->13371|20874->13546|20919->13562|20995->13610|21043->13641|21083->13642|21134->13665|21154->13675|21195->13677|21246->13699|21305->13730|21322->13737|21347->13740|21386->13751|21403->13758|21428->13761|21487->13791|21519->13800|21560->13812|21578->13819|21604->13822|21652->13841|21679->13845|21719->13856|21749->13863|21781->13865|21844->13899|21859->13903|21913->13934|21965->13967|21979->13972|22019->13973|22070->13995|22114->14011|22131->14018|22156->14021|22195->14032|22212->14039|22237->14042|22295->14072|22327->14081|22368->14093|22386->14100|22412->14103|22460->14122|22487->14126|22527->14137|22557->14144|22589->14146|22652->14180|22667->14184|22721->14215|22785->14247|22836->14270|22870->14282|22925->14305|22972->14323|23104->14487|23153->14508|23202->14540|23242->14541|23301->14572|23321->14582|23362->14584|23422->14615|23460->14625|23478->14633|23503->14636|23588->14702|23602->14707|23642->14708|23701->14738|23739->14748|23757->14756|23782->14759|23869->14814|23928->14845|23962->14857|24025->14892|24054->14899|24117->14934|24146->14941|24209->14976|24252->15002|24292->15003|24359->15041|24468->15122|24550->15193|24592->15195|24663->15237|24715->15261|24733->15269|24758->15273|24774->15279|24799->15282|24890->15345|24908->15353|24933->15357|24949->15363|24974->15366|25029->15392|25061->15401|25102->15413|25121->15421|25147->15424|25195->15443|25222->15447|25270->15466|25287->15472|25313->15475|25357->15490|25374->15496|25400->15499|25442->15512|25459->15518|25485->15521|25524->15531|25541->15537|25567->15540|25606->15550|25623->15556|25649->15559|25689->15570|25706->15576|25732->15579|26042->15861|26070->15879|26110->15880|26189->15930|26243->15956|26261->15964|26286->15968|26302->15974|26327->15977|26396->16017|26415->16025|26441->16029|26458->16035|26484->16038|26726->16252|26745->16260|26771->16264|26788->16270|26814->16273|27064->16495|27112->16526|27152->16527|27241->16588|27272->16609|27313->16611|27404->16674|27444->16697|27484->16698|27579->16765|27608->16784|27649->16786|27748->16857|27768->16867|27809->16869|27914->16945|28114->17116|28130->17121|28156->17125|28173->17131|28199->17134|28278->17184|28294->17189|28320->17192|28358->17201|28377->17209|28403->17212|28441->17221|28473->17230|28728->17456|28758->17463|28799->17475|28831->17484|28874->17498|28893->17506|28919->17509|28959->17520|28975->17525|29001->17528|29198->17696|29214->17701|29240->17704|29416->17851|29432->17856|29458->17859|29814->18183|29917->18257|29963->18275|29990->18280|30628->18890|30643->18895|30669->18899|30686->18905|30712->18908|30791->18958|30807->18963|30833->18966|30953->19057|30969->19062|30995->19066|31012->19072|31038->19075|31336->19354|31350->19359|31390->19360|31487->19428|31647->19560|31674->19565|31949->19812|31964->19817|31989->19820|32412->20211|32442->20212|32552->20290|32582->20291|32692->20373|32725->20384|32816->20443|32846->20444|32962->20528|33077->20615|33124->20645|33164->20646|33251->20705|33282->20726|33323->20728|33418->20795|33458->20818|33498->20819|33601->20894|33630->20913|33671->20915|33782->20998|33802->21008|33843->21010|33958->21096|34024->21133|34040->21138|34066->21141|34133->21179|34149->21184|34175->21187|34272->21255|34288->21260|34314->21263|34352->21272|34371->21280|34397->21283|34435->21292|34467->21301|34618->21423|34634->21428|34660->21431|34740->21482|34756->21487|34782->21490|34820->21499|34839->21507|34865->21510|34903->21519|34935->21528|35188->21753|35212->21754|35489->21999|35600->22078|35704->22154|35737->22165|35836->22232|35866->22233|35978->22313|36008->22314|36124->22398|36305->22551|36323->22559|36348->22563|36364->22569|36389->22572|36584->22735|36614->22736|36724->22814|36754->22815|36854->22883|36913->22913|36978->22946|37031->22970
                  LINES: 19->1|23->1|24->2|32->10|32->10|33->11|44->22|44->22|46->24|47->25|47->25|48->26|59->37|59->37|63->41|64->42|64->42|65->43|69->47|69->47|71->49|72->50|72->50|73->51|76->54|76->54|78->56|78->56|78->56|79->57|82->60|82->60|84->62|84->62|84->62|84->62|84->62|86->64|86->64|86->64|87->65|90->68|90->68|92->70|93->71|93->71|94->72|105->83|105->83|110->88|110->88|110->88|111->89|115->93|115->93|116->94|116->94|116->94|117->95|117->95|117->95|118->96|119->97|119->97|120->98|120->98|123->101|141->119|141->119|141->119|142->120|142->120|142->120|154->132|154->132|155->133|155->133|155->133|156->134|157->135|157->135|161->139|161->139|163->141|163->141|164->142|165->143|165->143|166->144|166->144|167->145|168->146|168->146|170->148|170->148|171->149|172->150|172->150|216->194|216->194|216->194|246->224|246->224|246->224|247->225|248->226|248->226|248->226|248->226|249->227|249->227|249->227|252->230|253->231|259->237|259->237|259->237|260->238|262->240|262->240|262->240|262->240|263->241|263->241|263->241|264->242|264->242|264->242|265->243|265->243|265->243|265->243|265->243|265->243|266->244|266->244|266->244|266->244|266->244|266->244|267->245|267->245|267->245|267->245|267->245|267->245|268->246|268->246|268->246|269->247|269->247|269->247|269->247|270->248|272->250|273->251|273->251|273->251|274->252|274->252|274->252|274->252|275->253|277->255|281->259|283->261|299->277|299->277|299->277|299->277|299->277|300->278|300->278|301->279|301->279|315->293|315->293|315->293|315->293|317->295|317->295|317->295|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|319->297|323->301|327->305|341->319|341->319|341->319|362->340|362->340|363->341|364->342|364->342|367->345|367->345|368->346|368->346|368->346|368->346|371->349|371->349|373->351|374->352|374->352|375->353|376->354|376->354|378->356|379->357|379->357|380->358|380->358|380->358|381->359|382->360|382->360|383->361|383->361|384->362|389->368|389->368|390->369|390->369|390->369|391->370|391->370|391->370|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|392->371|393->372|393->372|393->372|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|394->373|395->374|396->375|396->375|397->376|398->377|402->382|402->382|402->382|402->382|403->383|403->383|403->383|404->384|404->384|404->384|404->384|405->385|405->385|405->385|406->386|406->386|406->386|406->386|407->387|408->388|408->388|409->389|409->389|410->390|410->390|411->391|411->391|411->391|412->392|413->393|413->393|413->393|414->394|414->394|414->394|414->394|414->394|414->394|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|415->395|421->401|421->401|421->401|422->402|422->402|422->402|422->402|422->402|422->402|422->402|422->402|422->402|422->402|422->402|423->403|423->403|423->403|423->403|423->403|425->405|425->405|425->405|427->407|427->407|427->407|428->408|428->408|428->408|429->409|429->409|429->409|430->410|430->410|430->410|432->412|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|433->413|435->415|435->415|435->415|435->415|435->415|435->415|435->415|435->415|435->415|435->415|436->416|436->416|436->416|437->417|437->417|437->417|444->424|445->425|445->425|445->425|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|449->429|453->433|453->433|453->433|455->435|456->436|456->436|458->438|458->438|458->438|463->443|463->443|465->445|465->445|466->446|466->446|467->447|467->447|468->448|471->451|471->451|471->451|472->452|472->452|472->452|473->453|473->453|473->453|474->454|474->454|474->454|475->455|475->455|475->455|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|476->456|477->457|477->457|477->457|477->457|477->457|477->457|477->457|477->457|477->457|477->457|477->457|479->459|479->459|481->461|482->462|483->463|483->463|484->464|484->464|486->466|486->466|487->467|490->470|490->470|490->470|490->470|490->470|493->473|493->473|494->474|494->474|497->477|498->478|499->479|501->481
                  -- GENERATED --
              */
          