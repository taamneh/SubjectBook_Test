
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
object ShowSubject extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[String,List[String],Int,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, sub_sess: List[( String)], studyNo:Int, study_name:String,  poratratParm: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.102*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SubjectBook</title>

    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/datepicker3.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->


    <script src="/assets/javascripts/jquery-1.11.1.min.js"></script>
    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/chart.min.js"></script>
    <script src="/assets/javascripts/chart-data.js"></script>
    <script src="/assets/javascripts/easypiechart.js"></script>
    <script src="/assets/javascripts/easypiechart-data.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>

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
            <a class="navbar-brand" href="#"><span></span>Share</a>
            <a class="navbar-brand" href="/frontPage"><span></span>Help</a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*44.128*/userName),format.raw/*44.136*/(""" """),format.raw/*44.137*/("""<span class="caret"></span></a>
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
        <!-- <li><a href="index.html"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></li>
        <li><a href="widgets.html"><span class="glyphicon glyphicon-th"></span> Widgets</a></li>
        <li class="active"><a href="charts.html"><span class="glyphicon glyphicon-stats"></span> Charts</a></li> -->
        <li><a href="/allStudies"><span class="glyphicon glyphicon-th"></span> My Studies </a></li>
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> New Study</a></li>
        <li><a href="/showAllDataTypes"><span class="glyphicon glyphicon-cog"></span> Manage your Data</a></li>
        <li><a href="/showAllPsychometric"><span class="glyphicon glyphicon-cog"></span> Manage your Psychometric data</a></li>
        <!-- <li><a href="panels.html"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li> -->
        <li role="presentation" class="divider"></li>
        <!-- <li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Login Page</a></li> -->
    </ul>
    <!-- <div class="attribution">Template by <a href="http://www.medialoot.com/item/lumino-admin-bootstrap-template/">Medialoot</a></div> -->
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="placePortrait">
    <div class="row">
        <ol class="breadcrumb">
                <li><a href="/allStudies"><span class="glyphicon glyphicon-home"></span></a></li>
                <li class="active"><a href="/showStudy/"""),_display_(/*82.57*/studyNo),format.raw/*82.64*/("""">"""),_display_(/*82.67*/study_name),format.raw/*82.77*/(""" """),format.raw/*82.78*/("""</a> </li>
        </ol>
    </div><!--/.row-->




    <!-- <a id ="portart">Portart </a> -->

    <!-- Use a container to wrap the slider, the purpose is to enable slider to always fit width of the wrapper while window resize -->
    <div class="row">
        <!-- Jssor Slider Begin -->
        <!-- To move inline styles to css file/block, please specify a class name for each element. -->
        <!-- ================================================== -->
        <div id="slider1_container" style="display: none; position: relative; margin: 0 auto; width: 1140px; height: 442px; overflow: hidden;">

            <!-- Loading Screen -->
            <div u="loading" style="position: absolute; top: 0px; left: 0px;">
                <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;

                background-color: #000; top: 0px; left: 0px;width: 100%; height:100%;">
                </div>
                <div style="position: absolute; display: block; background: url(/assets/images/loading.gif) no-repeat center center;

                top: 0px; left: 0px;width: 100%;height:100%;">
                </div>
            </div>

            <!-- Slides Container -->
            <div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 1140px; height: 442px;
            overflow: hidden;">
                <div>
                    <img u="image" src2="/assets/images/general.jpg" />
                </div>
                <div>
                    <img u="image" src2="/assets/images/sessions.jpg" />
                </div>
                <div>
                    <img u="image" src2="/assets/images/sessions2.jpg" />
                </div>
                <div>
                    <img u="image" src2="/assets/images/NASA.jpg" />
                </div>
                <div>
                    <img u="image" src2="/assets/images/performance.jpg" />
                </div>
            </div>

            <!--#region Bullet Navigator Skin Begin -->
            <!-- Help: http://www.jssor.com/development/slider-with-bullet-navigator-jquery.html -->
            <style>
                /* jssor slider bullet navigator skin 05 css */
                /*
                .jssorb05 div           (normal)
                .jssorb05 div:hover     (normal mouseover)
                .jssorb05 .av           (active)
                .jssorb05 .av:hover     (active mouseover)
                .jssorb05 .dn           (mousedown)
                */
                .jssorb05 """),format.raw/*141.27*/("""{"""),format.raw/*141.28*/("""
                """),format.raw/*142.17*/("""position: absolute;
                """),format.raw/*143.17*/("""}"""),format.raw/*143.18*/("""
                """),format.raw/*144.17*/(""".jssorb05 div, .jssorb05 div:hover, .jssorb05 .av """),format.raw/*144.67*/("""{"""),format.raw/*144.68*/("""
                """),format.raw/*145.17*/("""position: absolute;
                /* size of bullet elment */
                width: 16px;
                height: 16px;
                background: url(/assets/images/b05.png) no-repeat;
                overflow: hidden;
                cursor: pointer;
                """),format.raw/*152.17*/("""}"""),format.raw/*152.18*/("""
                """),format.raw/*153.17*/(""".jssorb05 div """),format.raw/*153.31*/("""{"""),format.raw/*153.32*/(""" """),format.raw/*153.33*/("""background-position: -7px -7px; """),format.raw/*153.65*/("""}"""),format.raw/*153.66*/("""
                """),format.raw/*154.17*/(""".jssorb05 div:hover, .jssorb05 .av:hover """),format.raw/*154.58*/("""{"""),format.raw/*154.59*/(""" """),format.raw/*154.60*/("""background-position: -37px -7px; """),format.raw/*154.93*/("""}"""),format.raw/*154.94*/("""
                """),format.raw/*155.17*/(""".jssorb05 .av """),format.raw/*155.31*/("""{"""),format.raw/*155.32*/(""" """),format.raw/*155.33*/("""background-position: -67px -7px; """),format.raw/*155.66*/("""}"""),format.raw/*155.67*/("""
                """),format.raw/*156.17*/(""".jssorb05 .dn, .jssorb05 .dn:hover """),format.raw/*156.52*/("""{"""),format.raw/*156.53*/(""" """),format.raw/*156.54*/("""background-position: -97px -7px; """),format.raw/*156.87*/("""}"""),format.raw/*156.88*/("""
            """),format.raw/*157.13*/("""</style>
            <!-- bullet navigator container -->
            <div u="navigator" class="jssorb05" style="bottom: 16px; right: 6px;">
                <!-- bullet navigator item prototype -->
                <div u="prototype"></div>
            </div>
            <!--#endregion Bullet Navigator Skin End -->

            <!--#region Arrow Navigator Skin Begin -->
            <!-- Help: http://www.jssor.com/development/slider-with-arrow-navigator-jquery.html -->
            <style>
                /* jssor slider arrow navigator skin 11 css */
                /*
                .jssora11l                  (normal)
                .jssora11r                  (normal)
                .jssora11l:hover            (normal mouseover)
                .jssora11r:hover            (normal mouseover)
                .jssora11l.jssora11ldn      (mousedown)
                .jssora11r.jssora11rdn      (mousedown)
                */
                .jssora11l, .jssora11r """),format.raw/*177.40*/("""{"""),format.raw/*177.41*/("""
                """),format.raw/*178.17*/("""display: block;
                position: absolute;
                /* size of arrow element */
                width: 37px;
                height: 37px;
                cursor: pointer;
                background: url(/assets/images/a11.png) no-repeat;
                overflow: hidden;
                """),format.raw/*186.17*/("""}"""),format.raw/*186.18*/("""
                """),format.raw/*187.17*/(""".jssora11l """),format.raw/*187.28*/("""{"""),format.raw/*187.29*/(""" """),format.raw/*187.30*/("""background-position: -11px -41px; """),format.raw/*187.64*/("""}"""),format.raw/*187.65*/("""
                """),format.raw/*188.17*/(""".jssora11r """),format.raw/*188.28*/("""{"""),format.raw/*188.29*/(""" """),format.raw/*188.30*/("""background-position: -71px -41px; """),format.raw/*188.64*/("""}"""),format.raw/*188.65*/("""
                """),format.raw/*189.17*/(""".jssora11l:hover """),format.raw/*189.34*/("""{"""),format.raw/*189.35*/(""" """),format.raw/*189.36*/("""background-position: -131px -41px; """),format.raw/*189.71*/("""}"""),format.raw/*189.72*/("""
                """),format.raw/*190.17*/(""".jssora11r:hover """),format.raw/*190.34*/("""{"""),format.raw/*190.35*/(""" """),format.raw/*190.36*/("""background-position: -191px -41px; """),format.raw/*190.71*/("""}"""),format.raw/*190.72*/("""
                """),format.raw/*191.17*/(""".jssora11l.jssora11ldn """),format.raw/*191.40*/("""{"""),format.raw/*191.41*/(""" """),format.raw/*191.42*/("""background-position: -251px -41px; """),format.raw/*191.77*/("""}"""),format.raw/*191.78*/("""
                """),format.raw/*192.17*/(""".jssora11r.jssora11rdn """),format.raw/*192.40*/("""{"""),format.raw/*192.41*/(""" """),format.raw/*192.42*/("""background-position: -311px -41px; """),format.raw/*192.77*/("""}"""),format.raw/*192.78*/("""
            """),format.raw/*193.13*/("""</style>
            <!-- Arrow Left -->
            <span u="arrowleft" class="jssora11l" style="top: 123px; left: 8px;">
            </span>
            <!-- Arrow Right -->
            <span u="arrowright" class="jssora11r" style="top: 123px; right: 8px;">
            </span>
            <!--#endregion Arrow Navigator Skin End -->
            <a style="display: none" href="http://www.jssor.com">Bootstrap Slider</a>
        </div>
        <!-- Jssor Slider End -->
    </div>


    <script type="text/javascript"  src="/assets/javascripts/jssor.slider.mini.js"></script>
    <script>
        jQuery(document).ready(function ($) """),format.raw/*209.45*/("""{"""),format.raw/*209.46*/("""
            """),format.raw/*210.13*/("""var options = """),format.raw/*210.27*/("""{"""),format.raw/*210.28*/("""
                """),format.raw/*211.17*/("""$AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
                $AutoPlaySteps: 1,                                  //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
                $AutoPlayInterval: 8000,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
                $PauseOnHover: 1,                                   //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, 4 freeze for desktop, 8 freeze for touch device, 12 freeze for desktop and touch device, default value is 1

                $ArrowKeyNavigation: true,   			            //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
                $SlideEasing: $JssorEasing$.$EaseOutQuint,          //[Optional] Specifies easing for right to left animation, default value is $JssorEasing$.$EaseOutQuad
                $SlideDuration: 800,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
                $MinDragOffsetToSlide: 20,                          //[Optional] Minimum drag offset to trigger slide , default value is 20
                //$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
                //$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
                $SlideSpacing: 0, 					                //[Optional] Space between each slide in pixels, default value is 0
                $DisplayPieces: 1,                                  //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
                $ParkingPosition: 0,                                //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
                $UISearchMode: 1,                                   //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
                $PlayOrientation: 1,                                //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
                $DragOrientation: 1,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)

                $ArrowNavigatorOptions: """),format.raw/*229.41*/("""{"""),format.raw/*229.42*/("""                           """),format.raw/*229.69*/("""//[Optional] Options to specify and enable arrow navigator or not
                    $Class: $JssorArrowNavigator$,                  //[Requried] Class to create arrow navigator instance
                    $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                    $AutoCenter: 2,                                 //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                    $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                    $Scale: false                                   //Scales bullets navigator or not while slider scale
                """),format.raw/*235.17*/("""}"""),format.raw/*235.18*/(""",

                $BulletNavigatorOptions: """),format.raw/*237.42*/("""{"""),format.raw/*237.43*/("""                                """),format.raw/*237.75*/("""//[Optional] Options to specify and enable navigator or not
                    $Class: $JssorBulletNavigator$,                       //[Required] Class to create navigator instance
                    $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                    $AutoCenter: 1,                                 //[Optional] Auto center navigator in parent container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                    $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                    $Lanes: 1,                                      //[Optional] Specify lanes to arrange items, default value is 1
                    $SpacingX: 12,                                   //[Optional] Horizontal space between each item in pixel, default value is 0
                    $SpacingY: 4,                                   //[Optional] Vertical space between each item in pixel, default value is 0
                    $Orientation: 1,                                //[Optional] The orientation of the navigator, 1 horizontal, 2 vertical, default value is 1
                    $Scale: false                                   //Scales bullets navigator or not while slider scale
                """),format.raw/*247.17*/("""}"""),format.raw/*247.18*/("""
            """),format.raw/*248.13*/("""}"""),format.raw/*248.14*/(""";

            //Make the element 'slider1_container' visible before initialize jssor slider.
            $("#slider1_container").css("display", "block");
            var jssor_slider1 = new $JssorSlider$("slider1_container", options);

            //responsive code begin
            //you can remove responsive code if you don't want the slider scales while window resizes
            function ScaleSlider() """),format.raw/*256.36*/("""{"""),format.raw/*256.37*/("""
                """),format.raw/*257.17*/("""var parentWidth = jssor_slider1.$Elmt.parentNode.clientWidth;
                if (parentWidth) """),format.raw/*258.34*/("""{"""),format.raw/*258.35*/("""
                    """),format.raw/*259.21*/("""jssor_slider1.$ScaleWidth(parentWidth - 30);
                """),format.raw/*260.17*/("""}"""),format.raw/*260.18*/("""
                """),format.raw/*261.17*/("""else
                    window.setTimeout(ScaleSlider, 30);
            """),format.raw/*263.13*/("""}"""),format.raw/*263.14*/("""
            """),format.raw/*264.13*/("""ScaleSlider();


            $(window).bind("load", ScaleSlider);
            $(window).bind("resize", ScaleSlider);
            $(window).bind("orientationchange", ScaleSlider);
            //responsive code end
        """),format.raw/*271.9*/("""}"""),format.raw/*271.10*/(""");
    </script>


    <script>

    function showPage(pageNo)
    """),format.raw/*278.5*/("""{"""),format.raw/*278.6*/("""
     """),format.raw/*279.6*/("""var el = document.getElementById('iframe');

     var pages = '"""),_display_(/*281.20*/poratratParm),format.raw/*281.32*/("""'.split("~");
      var str = pages[pageNo-1];
      var temp = str.match("subjects=\\d+");
      var numberOfSubjects = temp[0].match("\\d+");

      var height = Math.ceil(numberOfSubjects/3) * 157
      var res = str.replace(/&amp;/g, '&');
       el.height = height + "px";

       var studyName = '"""),_display_(/*290.26*/study_name),format.raw/*290.36*/("""'

       if(studyName.toLowerCase().indexOf("toyota")> -1)
           el.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota.php?" + res ;
        else
           el.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait.php?" + res ;


    """),format.raw/*298.5*/("""}"""),format.raw/*298.6*/("""
    """),format.raw/*299.5*/("""$( document ).ready(function() """),format.raw/*299.36*/("""{"""),format.raw/*299.37*/("""
            """),format.raw/*300.13*/("""if('"""),_display_(/*300.18*/poratratParm),format.raw/*300.30*/("""' != "")
            """),format.raw/*301.13*/("""{"""),format.raw/*301.14*/("""

                """),format.raw/*303.17*/("""var place = "placePortrait"
                var pages = """"),_display_(/*304.31*/poratratParm),format.raw/*304.43*/("""".split("~");
                var container = document.createElement('div');
                container.setAttribute("class", "text-center");


                var lst = document.createElement('ul');
                lst.className = "pagination pagination-lg";
                lst.setAttribute("id", "pag2");
                if(pages.length > 1) """),format.raw/*312.38*/("""{"""),format.raw/*312.39*/("""
                 """),format.raw/*313.18*/("""for (var i = 0; i < pages.length; i++) """),format.raw/*313.57*/("""{"""),format.raw/*313.58*/("""
                       """),format.raw/*314.24*/("""var number = i +1
                       var listItem = document.createElement('li');
                       listItem.innerHTML = "<a href=\"#\" onclick=showPage("+ number +")>" + number + "</a>";
                       lst.appendChild(listItem);
                 """),format.raw/*318.18*/("""}"""),format.raw/*318.19*/("""
               """),format.raw/*319.16*/("""}"""),format.raw/*319.17*/("""

                """),format.raw/*321.17*/("""var iframe = document.createElement('iframe');
                var name = "iframe"
                iframe.setAttribute("id", name);
                iframe.width = "100%";
                iframe.height = "2600px";
                iframe.style.borderWidth =0
                var str = pages[0];
                var res = str.replace(/&amp;/g, '&');


              var studyName = '"""),_display_(/*331.33*/study_name),format.raw/*331.43*/("""'

                if(studyName.toLowerCase().indexOf("toyota")> -1)"""),format.raw/*333.66*/("""{"""),format.raw/*333.67*/("""
                    """),format.raw/*334.21*/("""iframe.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota.php?" + res ;

                    """),format.raw/*336.21*/("""}"""),format.raw/*336.22*/("""
               """),format.raw/*337.16*/("""else"""),format.raw/*337.20*/("""{"""),format.raw/*337.21*/("""
                    """),format.raw/*338.21*/("""iframe.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait.php?" + res ;
                    $("#slider1_container").hide();
                    """),format.raw/*340.21*/("""}"""),format.raw/*340.22*/("""
                """),format.raw/*341.17*/("""document.getElementById(place).appendChild(iframe);
                container.appendChild(lst);
                document.getElementById(place).appendChild(container);


            """),format.raw/*346.13*/("""}"""),format.raw/*346.14*/("""
            """),format.raw/*347.13*/("""else
            """),format.raw/*348.13*/("""{"""),format.raw/*348.14*/("""  """),format.raw/*348.16*/("""// to hide the slider when no portrait

                 $("#slider1_container").hide();
            """),format.raw/*351.13*/("""}"""),format.raw/*351.14*/("""
     """),format.raw/*352.6*/("""}"""),format.raw/*352.7*/(""");

   /* paramList =  $.ajax("""),format.raw/*354.27*/("""{"""),format.raw/*354.28*/("""
               """),format.raw/*355.16*/("""type: 'GET',
               url: "/portrait",
               dataType:"text",
               data: "studyId=" + """),_display_(/*358.36*/studyNo),format.raw/*358.43*/(""",
                async: false
     """),format.raw/*360.6*/("""}"""),format.raw/*360.7*/(""").responseText;*/


    </script>




    """),_display_(/*368.6*/if(poratratParm == "")/*368.28*/ {_display_(Seq[Any](format.raw/*368.30*/("""

    """),format.raw/*370.5*/("""<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Subjects</h1>
        </div>
    </div><!--/.row-->

    <div class="row">
    """),_display_(/*377.6*/for(subject <- sub_sess) yield /*377.30*/{_display_(Seq[Any](format.raw/*377.31*/("""
        """),format.raw/*378.9*/("""<div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-blue panel-widget ">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-user glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">
                            <a href="/displaySubject?studyNo="""),_display_(/*386.63*/studyNo),format.raw/*386.70*/("""&SubjectId="""),_display_(/*386.82*/subject),format.raw/*386.89*/("""">
                                """),_display_(/*387.34*/subject),format.raw/*387.41*/("""
                            """),format.raw/*388.29*/("""</a>
                        </div>
                        <div class="text-muted">
                            <!-- <a class="" href="#">
                                <span class="glyphicon glyphicon-signal"></span> BL
                            </a> -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    """)))}),format.raw/*400.6*/("""


"""),format.raw/*403.1*/("""</div><!--/.row-->
    """)))}),format.raw/*404.6*/("""

"""),format.raw/*406.1*/("""</div>	<!--/.main-->




<script>
		$('#calendar').datepicker("""),format.raw/*412.29*/("""{"""),format.raw/*412.30*/("""
		"""),format.raw/*413.3*/("""}"""),format.raw/*413.4*/(""");

		!function ($) """),format.raw/*415.17*/("""{"""),format.raw/*415.18*/("""
		    """),format.raw/*416.7*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*416.76*/("""{"""),format.raw/*416.77*/("""          
		        """),format.raw/*417.11*/("""$(this).find('em:first').toggleClass("glyphicon-minus");      
		    """),format.raw/*418.7*/("""}"""),format.raw/*418.8*/("""); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*420.3*/("""}"""),format.raw/*420.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*422.38*/("""{"""),format.raw/*422.39*/("""
		  """),format.raw/*423.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*424.3*/("""}"""),format.raw/*424.4*/(""")
		$(window).on('resize', function () """),format.raw/*425.38*/("""{"""),format.raw/*425.39*/("""
		  """),format.raw/*426.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*427.3*/("""}"""),format.raw/*427.4*/(""")
	</script>
</body>

</html>
"""))}
  }

  def render(userName:String,sub_sess:List[String],studyNo:Int,study_name:String,poratratParm:String): play.twirl.api.HtmlFormat.Appendable = apply(userName,sub_sess,studyNo,study_name,poratratParm)

  def f:((String,List[String],Int,String,String) => play.twirl.api.HtmlFormat.Appendable) = (userName,sub_sess,studyNo,study_name,poratratParm) => apply(userName,sub_sess,studyNo,study_name,poratratParm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:26:00 CDT 2015
                  SOURCE: C:/first_play/app/views/ShowSubject.scala.html
                  HASH: efbfcde6285424789a6bb8413a4ea36e2ae72130
                  MATRIX: 542->1|731->101|759->103|2835->2151|2865->2159|2895->2160|5266->4504|5294->4511|5324->4514|5355->4524|5384->4525|8022->7134|8052->7135|8099->7153|8165->7190|8195->7191|8242->7209|8321->7259|8351->7260|8398->7278|8707->7558|8737->7559|8784->7577|8827->7591|8857->7592|8887->7593|8948->7625|8978->7626|9025->7644|9095->7685|9125->7686|9155->7687|9217->7720|9247->7721|9294->7739|9337->7753|9367->7754|9397->7755|9459->7788|9489->7789|9536->7807|9600->7842|9630->7843|9660->7844|9722->7877|9752->7878|9795->7892|10819->8887|10849->8888|10896->8906|11238->9219|11268->9220|11315->9238|11355->9249|11385->9250|11415->9251|11478->9285|11508->9286|11555->9304|11595->9315|11625->9316|11655->9317|11718->9351|11748->9352|11795->9370|11841->9387|11871->9388|11901->9389|11965->9424|11995->9425|12042->9443|12088->9460|12118->9461|12148->9462|12212->9497|12242->9498|12289->9516|12341->9539|12371->9540|12401->9541|12465->9576|12495->9577|12542->9595|12594->9618|12624->9619|12654->9620|12718->9655|12748->9656|12791->9670|13470->10320|13500->10321|13543->10335|13586->10349|13616->10350|13663->10368|16935->13611|16965->13612|17021->13639|17815->14404|17845->14405|17920->14451|17950->14452|18011->14484|19389->15833|19419->15834|19462->15848|19492->15849|19939->16267|19969->16268|20016->16286|20141->16382|20171->16383|20222->16405|20313->16467|20343->16468|20390->16486|20494->16561|20524->16562|20567->16576|20823->16804|20853->16805|20955->16879|20984->16880|21019->16887|21113->16953|21147->16965|21488->17278|21520->17288|21838->17578|21867->17579|21901->17585|21961->17616|21991->17617|22034->17631|22067->17636|22101->17648|22152->17670|22182->17671|22231->17691|22318->17750|22352->17762|22733->18114|22763->18115|22811->18134|22879->18173|22909->18174|22963->18199|23260->18467|23290->18468|23336->18485|23366->18486|23415->18506|23834->18897|23866->18907|23965->18977|23995->18978|24046->19000|24196->19121|24226->19122|24272->19139|24305->19143|24335->19144|24386->19166|24580->19331|24610->19332|24657->19350|24872->19536|24902->19537|24945->19551|24992->19569|25022->19570|25053->19572|25186->19676|25216->19677|25251->19684|25280->19685|25341->19717|25371->19718|25417->19735|25561->19851|25590->19858|25656->19896|25685->19897|25763->19948|25795->19970|25836->19972|25872->19980|26072->20153|26113->20177|26153->20178|26191->20188|26712->20681|26741->20688|26781->20700|26810->20707|26875->20744|26904->20751|26963->20781|27388->21175|27422->21181|27478->21206|27510->21210|27607->21278|27637->21279|27669->21283|27698->21284|27749->21306|27779->21307|27815->21315|27913->21384|27943->21385|27994->21407|28092->21477|28121->21478|28232->21561|28261->21562|28347->21619|28377->21620|28411->21626|28511->21698|28540->21699|28609->21739|28639->21740|28673->21746|28774->21819|28803->21820
                  LINES: 19->1|22->1|23->2|65->44|65->44|65->44|103->82|103->82|103->82|103->82|103->82|162->141|162->141|163->142|164->143|164->143|165->144|165->144|165->144|166->145|173->152|173->152|174->153|174->153|174->153|174->153|174->153|174->153|175->154|175->154|175->154|175->154|175->154|175->154|176->155|176->155|176->155|176->155|176->155|176->155|177->156|177->156|177->156|177->156|177->156|177->156|178->157|198->177|198->177|199->178|207->186|207->186|208->187|208->187|208->187|208->187|208->187|208->187|209->188|209->188|209->188|209->188|209->188|209->188|210->189|210->189|210->189|210->189|210->189|210->189|211->190|211->190|211->190|211->190|211->190|211->190|212->191|212->191|212->191|212->191|212->191|212->191|213->192|213->192|213->192|213->192|213->192|213->192|214->193|230->209|230->209|231->210|231->210|231->210|232->211|250->229|250->229|250->229|256->235|256->235|258->237|258->237|258->237|268->247|268->247|269->248|269->248|277->256|277->256|278->257|279->258|279->258|280->259|281->260|281->260|282->261|284->263|284->263|285->264|292->271|292->271|299->278|299->278|300->279|302->281|302->281|311->290|311->290|319->298|319->298|320->299|320->299|320->299|321->300|321->300|321->300|322->301|322->301|324->303|325->304|325->304|333->312|333->312|334->313|334->313|334->313|335->314|339->318|339->318|340->319|340->319|342->321|352->331|352->331|354->333|354->333|355->334|357->336|357->336|358->337|358->337|358->337|359->338|361->340|361->340|362->341|367->346|367->346|368->347|369->348|369->348|369->348|372->351|372->351|373->352|373->352|375->354|375->354|376->355|379->358|379->358|381->360|381->360|389->368|389->368|389->368|391->370|398->377|398->377|398->377|399->378|407->386|407->386|407->386|407->386|408->387|408->387|409->388|421->400|424->403|425->404|427->406|433->412|433->412|434->413|434->413|436->415|436->415|437->416|437->416|437->416|438->417|439->418|439->418|441->420|441->420|443->422|443->422|444->423|445->424|445->424|446->425|446->425|447->426|448->427|448->427
                  -- GENERATED --
              */
          