
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
            <!-- <a class="navbar-brand" href="#"><span></span>Share</a> -->
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
           el.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota-new.php?" + res ;
        else
           el.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota-new.php?" + res ;


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
                    """),format.raw/*334.21*/("""iframe.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota-new.php?" + res ;

                    """),format.raw/*336.21*/("""}"""),format.raw/*336.22*/("""
               """),format.raw/*337.16*/("""else"""),format.raw/*337.20*/("""{"""),format.raw/*337.21*/("""
                    """),format.raw/*338.21*/("""//iframe.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait.php?" + res ;
                    iframe.src = "http://kyeongan.cpl.uh.edu/projects/css/api/generator-portrait-toyota-new.php?" + res ;
                    $("#slider1_container").hide();
                    """),format.raw/*341.21*/("""}"""),format.raw/*341.22*/("""
                """),format.raw/*342.17*/("""document.getElementById(place).appendChild(iframe);
                container.appendChild(lst);
                document.getElementById(place).appendChild(container);


            """),format.raw/*347.13*/("""}"""),format.raw/*347.14*/("""
            """),format.raw/*348.13*/("""else
            """),format.raw/*349.13*/("""{"""),format.raw/*349.14*/("""  """),format.raw/*349.16*/("""// to hide the slider when no portrait

                 $("#slider1_container").hide();
            """),format.raw/*352.13*/("""}"""),format.raw/*352.14*/("""
     """),format.raw/*353.6*/("""}"""),format.raw/*353.7*/(""");

   /* paramList =  $.ajax("""),format.raw/*355.27*/("""{"""),format.raw/*355.28*/("""
               """),format.raw/*356.16*/("""type: 'GET',
               url: "/portrait",
               dataType:"text",
               data: "studyId=" + """),_display_(/*359.36*/studyNo),format.raw/*359.43*/(""",
                async: false
     """),format.raw/*361.6*/("""}"""),format.raw/*361.7*/(""").responseText;*/


    </script>




    """),_display_(/*369.6*/if(poratratParm == "")/*369.28*/ {_display_(Seq[Any](format.raw/*369.30*/("""

    """),format.raw/*371.5*/("""<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Subjects</h1>
        </div>
    </div><!--/.row-->

    <div class="row">
    """),_display_(/*378.6*/for(subject <- sub_sess) yield /*378.30*/{_display_(Seq[Any](format.raw/*378.31*/("""
        """),format.raw/*379.9*/("""<div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-blue panel-widget ">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                       <em class="glyphicon glyphicon-user glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">
                            <a href="/displaySubject?studyNo="""),_display_(/*387.63*/studyNo),format.raw/*387.70*/("""&SubjectId="""),_display_(/*387.82*/subject),format.raw/*387.89*/("""">
                                """),_display_(/*388.34*/subject),format.raw/*388.41*/("""
                            """),format.raw/*389.29*/("""</a>
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
    """)))}),format.raw/*401.6*/("""


"""),format.raw/*404.1*/("""</div><!--/.row-->
    """)))}),format.raw/*405.6*/("""

"""),format.raw/*407.1*/("""</div>	<!--/.main-->




<script>
		$('#calendar').datepicker("""),format.raw/*413.29*/("""{"""),format.raw/*413.30*/("""
		"""),format.raw/*414.3*/("""}"""),format.raw/*414.4*/(""");

		!function ($) """),format.raw/*416.17*/("""{"""),format.raw/*416.18*/("""
		    """),format.raw/*417.7*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*417.76*/("""{"""),format.raw/*417.77*/("""          
		        """),format.raw/*418.11*/("""$(this).find('em:first').toggleClass("glyphicon-minus");      
		    """),format.raw/*419.7*/("""}"""),format.raw/*419.8*/("""); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*421.3*/("""}"""),format.raw/*421.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*423.38*/("""{"""),format.raw/*423.39*/("""
		  """),format.raw/*424.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*425.3*/("""}"""),format.raw/*425.4*/(""")
		$(window).on('resize', function () """),format.raw/*426.38*/("""{"""),format.raw/*426.39*/("""
		  """),format.raw/*427.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*428.3*/("""}"""),format.raw/*428.4*/(""")
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
                  DATE: Thu Aug 04 14:39:25 CDT 2016
                  SOURCE: C:/first_play/app/views/ShowSubject.scala.html
                  HASH: c96f09706c347a5ad6805bd5246f7bdc7a376493
                  MATRIX: 542->1|731->101|759->103|2844->2160|2874->2168|2904->2169|5275->4513|5303->4520|5333->4523|5364->4533|5393->4534|8031->7143|8061->7144|8108->7162|8174->7199|8204->7200|8251->7218|8330->7268|8360->7269|8407->7287|8716->7567|8746->7568|8793->7586|8836->7600|8866->7601|8896->7602|8957->7634|8987->7635|9034->7653|9104->7694|9134->7695|9164->7696|9226->7729|9256->7730|9303->7748|9346->7762|9376->7763|9406->7764|9468->7797|9498->7798|9545->7816|9609->7851|9639->7852|9669->7853|9731->7886|9761->7887|9804->7901|10828->8896|10858->8897|10905->8915|11247->9228|11277->9229|11324->9247|11364->9258|11394->9259|11424->9260|11487->9294|11517->9295|11564->9313|11604->9324|11634->9325|11664->9326|11727->9360|11757->9361|11804->9379|11850->9396|11880->9397|11910->9398|11974->9433|12004->9434|12051->9452|12097->9469|12127->9470|12157->9471|12221->9506|12251->9507|12298->9525|12350->9548|12380->9549|12410->9550|12474->9585|12504->9586|12551->9604|12603->9627|12633->9628|12663->9629|12727->9664|12757->9665|12800->9679|13479->10329|13509->10330|13552->10344|13595->10358|13625->10359|13672->10377|16944->13620|16974->13621|17030->13648|17824->14413|17854->14414|17929->14460|17959->14461|18020->14493|19398->15842|19428->15843|19471->15857|19501->15858|19948->16276|19978->16277|20025->16295|20150->16391|20180->16392|20231->16414|20322->16476|20352->16477|20399->16495|20503->16570|20533->16571|20576->16585|20832->16813|20862->16814|20964->16888|20993->16889|21028->16896|21122->16962|21156->16974|21497->17287|21529->17297|21862->17602|21891->17603|21925->17609|21985->17640|22015->17641|22058->17655|22091->17660|22125->17672|22176->17694|22206->17695|22255->17715|22342->17774|22376->17786|22757->18138|22787->18139|22835->18158|22903->18197|22933->18198|22987->18223|23284->18491|23314->18492|23360->18509|23390->18510|23439->18530|23858->18921|23890->18931|23989->19001|24019->19002|24070->19024|24224->19149|24254->19150|24300->19167|24333->19171|24363->19172|24414->19194|24733->19484|24763->19485|24810->19503|25025->19689|25055->19690|25098->19704|25145->19722|25175->19723|25206->19725|25339->19829|25369->19830|25404->19837|25433->19838|25494->19870|25524->19871|25570->19888|25714->20004|25743->20011|25809->20049|25838->20050|25916->20101|25948->20123|25989->20125|26025->20133|26225->20306|26266->20330|26306->20331|26344->20341|26864->20833|26893->20840|26933->20852|26962->20859|27027->20896|27056->20903|27115->20933|27540->21327|27574->21333|27630->21358|27662->21362|27759->21430|27789->21431|27821->21435|27850->21436|27901->21458|27931->21459|27967->21467|28065->21536|28095->21537|28146->21559|28244->21629|28273->21630|28384->21713|28413->21714|28499->21771|28529->21772|28563->21778|28663->21850|28692->21851|28761->21891|28791->21892|28825->21898|28926->21971|28955->21972
                  LINES: 19->1|22->1|23->2|65->44|65->44|65->44|103->82|103->82|103->82|103->82|103->82|162->141|162->141|163->142|164->143|164->143|165->144|165->144|165->144|166->145|173->152|173->152|174->153|174->153|174->153|174->153|174->153|174->153|175->154|175->154|175->154|175->154|175->154|175->154|176->155|176->155|176->155|176->155|176->155|176->155|177->156|177->156|177->156|177->156|177->156|177->156|178->157|198->177|198->177|199->178|207->186|207->186|208->187|208->187|208->187|208->187|208->187|208->187|209->188|209->188|209->188|209->188|209->188|209->188|210->189|210->189|210->189|210->189|210->189|210->189|211->190|211->190|211->190|211->190|211->190|211->190|212->191|212->191|212->191|212->191|212->191|212->191|213->192|213->192|213->192|213->192|213->192|213->192|214->193|230->209|230->209|231->210|231->210|231->210|232->211|250->229|250->229|250->229|256->235|256->235|258->237|258->237|258->237|268->247|268->247|269->248|269->248|277->256|277->256|278->257|279->258|279->258|280->259|281->260|281->260|282->261|284->263|284->263|285->264|292->271|292->271|299->278|299->278|300->279|302->281|302->281|311->290|311->290|319->298|319->298|320->299|320->299|320->299|321->300|321->300|321->300|322->301|322->301|324->303|325->304|325->304|333->312|333->312|334->313|334->313|334->313|335->314|339->318|339->318|340->319|340->319|342->321|352->331|352->331|354->333|354->333|355->334|357->336|357->336|358->337|358->337|358->337|359->338|362->341|362->341|363->342|368->347|368->347|369->348|370->349|370->349|370->349|373->352|373->352|374->353|374->353|376->355|376->355|377->356|380->359|380->359|382->361|382->361|390->369|390->369|390->369|392->371|399->378|399->378|399->378|400->379|408->387|408->387|408->387|408->387|409->388|409->388|410->389|422->401|425->404|426->405|428->407|434->413|434->413|435->414|435->414|437->416|437->416|438->417|438->417|438->417|439->418|440->419|440->419|442->421|442->421|444->423|444->423|445->424|446->425|446->425|447->426|447->426|448->427|449->428|449->428
                  -- GENERATED --
              */
          