
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
object ShowCreateStudy extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[scala.Tuple3[String, Int, Int]],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, explVar : List[(String, Int, Int)])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.89*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>

    <style type="text/css">

        .kareem """),format.raw/*9.17*/("""{"""),format.raw/*9.18*/("""
            """),format.raw/*10.13*/("""padding: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
            box-sizing: border-box;
            font-family: montserrat;
            color: #2C3E50;
            font-size: 13px;
        """),format.raw/*18.9*/("""}"""),format.raw/*18.10*/("""



    """),format.raw/*22.5*/("""</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="width=1170" name="viewport"><meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <script type="text/javascript" src="/assets/javascripts/jquery-1.9.0.min.js"></script>
    <title>SubjectBoo</title>

    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/datepicker3.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/multi.css">


    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="/assets/javascripts/showPicker.js"></script>
    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="/assets/javascripts/ow-all.js"></script>
    <script type="text/javascript" src="/assets/javascripts/showCreateStudy.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb-salah.css">
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*64.128*/userName),format.raw/*64.136*/(""" """),format.raw/*64.137*/("""<span class="caret"></span></a>
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
        <li role="presentation" class="divider"></li>

    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a  href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">Forms</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Create New Study</h1>
        </div>
    </div><!--/.row-->
    <!-- <div id= "newStudy"> -->
    <div class="row">
    <div class="col-sm-8">
        <h3> <a href="/setUpStudy">see how to set up your data in SubjectBook </a> </h3>
        <h3> <a href="/howToCreateNewStudy">See how to create a new study in SubjectBook </a> </h3>
    </div>
    </div>

    <div class ="showontop" >

    <form calss="salah" id="msform" method="post">
    <!-- progressbar -->



    """),_display_(/*119.6*/helper/*119.12*/.form(action = routes.Application.InsertNewStudy, 'enctype -> "multipart/form-data", 'id-> "target")/*119.112*/ {_display_(Seq[Any](format.raw/*119.114*/("""
        """),format.raw/*120.9*/("""<ul class="taa" id="progressbar1">
        <li class="active">Experimental Design</li>
        <li> Explanatory Variables</li>
        <li> Response Variable </li>
        <li> covariates</li>
       <!-- <li>Performance </li>  -->
    </ul>
    <!-- fieldsets -->

        <fieldset>
            <h2 class="fs-title">Experimental Design</h2>
        <h3 class="fs-subtitle"></h3>
        <table id="zob" cellspacing="0" class="data_table" id="add_sessions_table">

            <tr>
                <td class="cell_1">* Source type:</td>
                <td>
                    <select class="select_field"  id="sourcetype" name="study_type" >
                        <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value=1  selected ="selected">Google Drive</option>
                       <!-- <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_2" value=2>CPL Server</option> -->
                    </select>

                </td>
            </tr>
            <tr>
                <td class="cell_1">* Study name:</td>
                <td id = "kareem">
                    <input  size="200"  type="text" id="study_name" maxlength="50" name="study_name" prompttext="" required>
                </td>
            </tr>
            <tr>
                <td class="cell_1">* URL:</td>
                <td id = "kareem">
                    <input  size="200"  type="text" id="folder_name" maxlength="200" name="url" prompttext="" required>
                        <input size="200"  type="hidden" id="folder_id" maxlength="50" name="folder_id" value="x">
                </td>
                <td id = "kareem">
                    <input  type="button" name="Add" id="browseGD"class="" value="...." />
                </td>
                <script type="text/javascript" src="https://apis.google.com/js/api.js?onload=onApiLoad"></script>
            </tr>
             <tr>
                <td class="cell_1">* Number of subjects:</td>
                <td id = "kareem">
                    <input type="number" class="text_field" name="numSubj" id="numSubj" min="1" max="100" value = 1>
                </td>
            </tr>
            <tr>
                <td class="cell_1">* Sessions per subject:</td>
                <td id = "kareem">
                    <input  type="number" class="text_field" name="numSess" min="0" max="100" value = 1>
                </td>
            </tr>
            <tr>
                <td id = "kareem"><input  type="checkbox" id="publicCh" name="publicCh" value=1 style="width: 20px;">Public study&nbsp;&nbsp;&nbsp;&nbsp;<br> </td>
                <td id = "kareem"><input type="checkbox" id="portraitCh" name="portraitCh" value=1 style="width: 20px">Generate Portrait&nbsp;&nbsp;&nbsp;<br> </td>
                <input  type="hidden" name="public" id='public' value=0>
                <input  type="hidden" name="portrait" id='portrait' value=0>
            </tr>
            <!-- <tr>
                <td class="cell_1"> Runs per session:</td>
                <td> -->
            <input class="kareem" type="hidden" class="text_field" name="numRun" min="1" max="100" value = 1>
            <!-- </td> -->

            <!-- </tr>  -->
        </table>
        <input id ="firstNext"  type="button" name="next" class="next1 action-button" value="Next" />



        </fieldset>
        <fieldset>
            <h2 class="fs-title">Explanatory Variables</h2>
            <table cellspacing="0" class="data_table" id="add_sessions_table">
                <tr>
                    <td class="cell_1">Key Explanatory Variables:</td>
                    <td>
                        <select class="select_field"  id="explanatoryPrim" name="study_type"  style="width: 300px" required>
                            """),_display_(/*199.30*/for(temp <- explVar) yield /*199.50*/{_display_(Seq[Any](format.raw/*199.51*/("""
                            """),_display_(/*200.30*/if(temp._3 == 1 )/*200.47*/ {_display_(Seq[Any](format.raw/*200.49*/("""
                             """),format.raw/*201.30*/("""<option id="op1" value="""),_display_(/*201.54*/temp/*201.58*/._2),format.raw/*201.61*/("""  """),format.raw/*201.63*/("""selected ="selected"> """),_display_(/*201.86*/temp/*201.90*/._1),format.raw/*201.93*/(""" """),format.raw/*201.94*/("""</option>
                            """)))}),format.raw/*202.30*/("""
                            """)))}),format.raw/*203.30*/("""
                        """),format.raw/*204.25*/("""</select>
                    </td>
                </tr>
                <tr>
                    <td class="cell_1">Secondary Explanatory Variables</td>
                    <td>

                        <div class="col-lg-12">
                            <div class="button-group">
                                <button type="button" style="width: 300px" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> Select Variables <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    """),_display_(/*215.38*/for(temp <- explVar) yield /*215.58*/{_display_(Seq[Any](format.raw/*215.59*/("""
                                    """),_display_(/*216.38*/if(temp._3 == 1 )/*216.55*/ {_display_(Seq[Any](format.raw/*216.57*/("""
                                    """),format.raw/*217.37*/("""<li><a href="#" class="small1" data-value=""""),_display_(/*217.81*/temp/*217.85*/._2),format.raw/*217.88*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*217.135*/temp/*217.139*/._1),format.raw/*217.142*/("""</a></li>
                                     """)))}),format.raw/*218.39*/("""
                                    """)))}),format.raw/*219.38*/("""
                                """),format.raw/*220.33*/("""</ul>
                            </div>
                        </div>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data Before Sessions</td>
                    <td>
                        <select class="select_field"  id="staticB" name="study_type" >
                            <option id="op1" value=-1 > none </option>
                            """),_display_(/*232.30*/for(temp <- explVar) yield /*232.50*/{_display_(Seq[Any](format.raw/*232.51*/("""
                            """),_display_(/*233.30*/if(temp._3 == 4)/*233.46*/ {_display_(Seq[Any](format.raw/*233.48*/("""
                            """),format.raw/*234.29*/("""<option id="op1" value=""""),_display_(/*234.54*/temp/*234.58*/._2),format.raw/*234.61*/("""" > """),_display_(/*234.66*/temp/*234.70*/._1),format.raw/*234.73*/("""</option>
                            """)))}),format.raw/*235.30*/("""
                            """)))}),format.raw/*236.30*/("""
                        """),format.raw/*237.25*/("""</select>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data After Sessions</td>
                    <td>
                        <select class="select_field"  id="staticA" name="study_type" >
                            <option id="op1" value=-1 > none </option>
                            """),_display_(/*247.30*/for(temp <- explVar) yield /*247.50*/{_display_(Seq[Any](format.raw/*247.51*/("""
                            """),_display_(/*248.30*/if(temp._3 == 4)/*248.46*/ {_display_(Seq[Any](format.raw/*248.48*/("""
                            """),format.raw/*249.29*/("""<option id="op1" value=""""),_display_(/*249.54*/temp/*249.58*/._2),format.raw/*249.61*/(""""  > """),_display_(/*249.67*/temp/*249.71*/._1),format.raw/*249.74*/("""</option>
                            """)))}),format.raw/*250.30*/("""
                            """)))}),format.raw/*251.30*/("""
                        """),format.raw/*252.25*/("""</select>

                    </td>

                </tr>
            </table>
            <input type="button" name="previous" class="previous action-button" value="Previous" />
            <input type="button" name="next" class="next2 action-button" value="Next" />
            <!--<input type="submit" name="submit" class="submit action-button" value="Submit" id="createStudy"/> -->

        </fieldset>
        <fieldset>
            <h2 class="fs-title">Response Variables</h2>
            <table cellspacing="0" class="data_table" id="add_sessions_table">
                <tr>
                    <td class="cell_1">Key Response Variables:</td>
                    <td>
                        <select class="select_field"  id="responsePrim" name="study_type"  style="width: 300px">
                            """),_display_(/*270.30*/for(temp <- explVar) yield /*270.50*/{_display_(Seq[Any](format.raw/*270.51*/("""
                                """),_display_(/*271.34*/if(temp._3 == 1)/*271.50*/ {_display_(Seq[Any](format.raw/*271.52*/("""
                                """),format.raw/*272.33*/("""<option id="op1" value="""),_display_(/*272.57*/temp/*272.61*/._2),format.raw/*272.64*/("""  """),format.raw/*272.66*/("""selected ="selected"> """),_display_(/*272.89*/temp/*272.93*/._1),format.raw/*272.96*/(""" """),format.raw/*272.97*/("""</option>
                                """)))}),format.raw/*273.34*/("""
                            """)))}),format.raw/*274.30*/("""

                        """),format.raw/*276.25*/("""</select>
                    </td>
                </tr>
                <tr>
                    <td class="cell_1">Secondary Response Variables</td>
                    <td>
                        <!-- <select class="select_field"  id="responseSec" name="study_type" >
                            <option id="op1" value=1  selected ="selected"> EDA Palm</option>
                        </select> -->

                        <div class="col-lg-12">
                            <div class="button-group">
                                <button type="button" style="width: 300px" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> Select Variables <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    """),_display_(/*290.38*/for(temp <- explVar) yield /*290.58*/{_display_(Seq[Any](format.raw/*290.59*/("""
                                    """),_display_(/*291.38*/if(temp._3 == 1 )/*291.55*/ {_display_(Seq[Any](format.raw/*291.57*/("""
                                    """),format.raw/*292.37*/("""<li><a href="#"   class="small2" data-value=""""),_display_(/*292.83*/temp/*292.87*/._2),format.raw/*292.90*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*292.137*/temp/*292.141*/._1),format.raw/*292.144*/("""</a></li>
                                    """)))}),format.raw/*293.38*/("""
                                    """)))}),format.raw/*294.38*/("""
                                """),format.raw/*295.33*/("""</ul>
                            </div>
                        </div>


                    </td>

                </tr>

            </table>
            <input type="button" name="previous" class="previous action-button" value="Previous" />
            <input type="button" name="next" class="next3 action-button" value="Next" />
            <!--<input type="submit" name="submit" class="submit action-button" value="Submit" id="createStudy"/> -->

        </fieldset>
        <fieldset>
            <h2 class="fs-title">Covariates</h2>
            <h3 class="fs-subtitle"></h3>

            <table cellspacing="0" class="data_table" id="add_sessions_table">
                <tr>
                    <td class="cell_1">Covariates</td>
                    <td>
                        <select class="select_field"  id="cov" name="study_type"  style="width: 300px">
                            <option id="op1" value=-1 > none </option>
                            """),_display_(/*320.30*/for(temp <- explVar) yield /*320.50*/{_display_(Seq[Any](format.raw/*320.51*/("""
                            """),_display_(/*321.30*/if(temp._3 == 3 )/*321.47*/ {_display_(Seq[Any](format.raw/*321.49*/("""
                            """),format.raw/*322.29*/("""<option id="op1" value="""),_display_(/*322.53*/temp/*322.57*/._2),format.raw/*322.60*/("""  """),format.raw/*322.62*/("""selected ="selected"> """),_display_(/*322.85*/temp/*322.89*/._1),format.raw/*322.92*/("""</option>
                            """)))}),format.raw/*323.30*/("""
                            """)))}),format.raw/*324.30*/("""
                        """),format.raw/*325.25*/("""</select>

                    </td>

                </tr>
                <!-- <tr>
                    <td><input type="checkbox" id="Age" name="Age" value=1 style="width: 20px">Age&nbsp;&nbsp;&nbsp;&nbsp;<br> </td>
                    <td><input type="checkbox" id="Height" name="Height" value=10 style="width: 20px">Height&nbsp;&nbsp;&nbsp;<br> </td>
                    <td><input type="checkbox" id="Weight" name="Weight" value=100 style="width: 20px">Weight&nbsp;&nbsp;&nbsp;<br> </td>
                    <td><input type="checkbox" id="Gender" name="Gender" value=1000 style="width: 20px">Gender&nbsp;&nbsp;&nbsp;<br> </td>
                    <td><input type="checkbox" id="Ethnicity" name="Ethnicity" value=10000 style="width: 20px">Ethnicity<br> </td>
                </tr>
                <tr>
                    <td><input type="checkbox" id="Bio_other"  name="Bio_other" value=100000 style="width: 20px">Other&nbsp;<br> </td>

                </tr> -->
            </table>
            <script>
                    $(document).ready(function()"""),format.raw/*343.49*/("""{"""),format.raw/*343.50*/("""
                    """),format.raw/*344.21*/("""$('#publicCh').change(function() """),format.raw/*344.54*/("""{"""),format.raw/*344.55*/("""
                    """),format.raw/*345.21*/("""if($(this).is(":checked")) """),format.raw/*345.48*/("""{"""),format.raw/*345.49*/("""
                           """),format.raw/*346.28*/("""$("#public").val(1);
                           """),format.raw/*347.28*/("""}"""),format.raw/*347.29*/("""
                           """),format.raw/*348.28*/("""else
                           """),format.raw/*349.28*/("""{"""),format.raw/*349.29*/("""
                            """),format.raw/*350.29*/("""$("#public").val(0);
                           """),format.raw/*351.28*/("""}"""),format.raw/*351.29*/("""
                         """),format.raw/*352.26*/("""}"""),format.raw/*352.27*/(""");
                       $('#portraitCh').change(function() """),format.raw/*353.59*/("""{"""),format.raw/*353.60*/("""
                    """),format.raw/*354.21*/("""if($(this).is(":checked")) """),format.raw/*354.48*/("""{"""),format.raw/*354.49*/("""
                           """),format.raw/*355.28*/("""$("#portrait").val(1);
                           """),format.raw/*356.28*/("""}"""),format.raw/*356.29*/("""
                           """),format.raw/*357.28*/("""else
                           """),format.raw/*358.28*/("""{"""),format.raw/*358.29*/("""
                            """),format.raw/*359.29*/("""$("#portrait").val(0);
                           """),format.raw/*360.28*/("""}"""),format.raw/*360.29*/("""
                         """),format.raw/*361.26*/("""}"""),format.raw/*361.27*/(""");
                    $('#Age').change(function() """),format.raw/*362.49*/("""{"""),format.raw/*362.50*/("""
                        """),format.raw/*363.25*/("""if($(this).is(":checked")) """),format.raw/*363.52*/("""{"""),format.raw/*363.53*/("""
                           """),format.raw/*364.28*/("""var a =  parseInt($("#ir").val()) + 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*366.28*/("""}"""),format.raw/*366.29*/("""
                           """),format.raw/*367.28*/("""else
                           """),format.raw/*368.28*/("""{"""),format.raw/*368.29*/("""
                            """),format.raw/*369.29*/("""var a =  parseInt($("#ir").val()) - 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*371.28*/("""}"""),format.raw/*371.29*/("""
                         """),format.raw/*372.26*/("""}"""),format.raw/*372.27*/(""");

                         $('#Height').change(function() """),format.raw/*374.57*/("""{"""),format.raw/*374.58*/("""
                         """),format.raw/*375.26*/("""if($(this).is(":checked")) """),format.raw/*375.53*/("""{"""),format.raw/*375.54*/("""
                             """),format.raw/*376.30*/("""var a =  parseInt($("#ir").val()) + 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*378.28*/("""}"""),format.raw/*378.29*/("""
                           """),format.raw/*379.28*/("""else
                           """),format.raw/*380.28*/("""{"""),format.raw/*380.29*/("""
                             """),format.raw/*381.30*/("""var a =  parseInt($("#ir").val()) - 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*383.28*/("""}"""),format.raw/*383.29*/("""
                         """),format.raw/*384.26*/("""}"""),format.raw/*384.27*/(""");

                          $('#Weight').change(function() """),format.raw/*386.58*/("""{"""),format.raw/*386.59*/("""
                         """),format.raw/*387.26*/("""if($(this).is(":checked")) """),format.raw/*387.53*/("""{"""),format.raw/*387.54*/("""

                           """),format.raw/*389.28*/("""var a =  parseInt($("#ir").val()) + 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*391.28*/("""}"""),format.raw/*391.29*/("""
                           """),format.raw/*392.28*/("""else
                           """),format.raw/*393.28*/("""{"""),format.raw/*393.29*/("""
                               """),format.raw/*394.32*/("""var a =  parseInt($("#ir").val()) - 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*396.28*/("""}"""),format.raw/*396.29*/("""
                         """),format.raw/*397.26*/("""}"""),format.raw/*397.27*/(""");

                          $('#Gender').change(function() """),format.raw/*399.58*/("""{"""),format.raw/*399.59*/("""
                         """),format.raw/*400.26*/("""if($(this).is(":checked")) """),format.raw/*400.53*/("""{"""),format.raw/*400.54*/("""

                           """),format.raw/*402.28*/("""var a =  parseInt($("#ir").val()) + 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*404.28*/("""}"""),format.raw/*404.29*/("""
                           """),format.raw/*405.28*/("""else
                           """),format.raw/*406.28*/("""{"""),format.raw/*406.29*/("""
                               """),format.raw/*407.32*/("""var a =  parseInt($("#ir").val()) - 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*409.28*/("""}"""),format.raw/*409.29*/("""
                         """),format.raw/*410.26*/("""}"""),format.raw/*410.27*/(""");

                          $('#Ethnicity').change(function() """),format.raw/*412.61*/("""{"""),format.raw/*412.62*/("""
                         """),format.raw/*413.26*/("""if($(this).is(":checked")) """),format.raw/*413.53*/("""{"""),format.raw/*413.54*/("""

                           """),format.raw/*415.28*/("""var a =  parseInt($("#ir").val()) + 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*417.28*/("""}"""),format.raw/*417.29*/("""
                           """),format.raw/*418.28*/("""else
                           """),format.raw/*419.28*/("""{"""),format.raw/*419.29*/("""
                               """),format.raw/*420.32*/("""var a =  parseInt($("#ir").val()) - 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*422.28*/("""}"""),format.raw/*422.29*/("""
                         """),format.raw/*423.26*/("""}"""),format.raw/*423.27*/(""");

                          $('#Bio_other').change(function() """),format.raw/*425.61*/("""{"""),format.raw/*425.62*/("""
                         """),format.raw/*426.26*/("""if($(this).is(":checked")) """),format.raw/*426.53*/("""{"""),format.raw/*426.54*/("""

                           """),format.raw/*428.28*/("""var a =  parseInt($("#ir").val()) + 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*430.28*/("""}"""),format.raw/*430.29*/("""
                           """),format.raw/*431.28*/("""else
                           """),format.raw/*432.28*/("""{"""),format.raw/*432.29*/("""
                               """),format.raw/*433.32*/("""var a =  parseInt($("#ir").val()) - 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*435.28*/("""}"""),format.raw/*435.29*/("""
                         """),format.raw/*436.26*/("""}"""),format.raw/*436.27*/(""");

                     """),format.raw/*438.22*/("""}"""),format.raw/*438.23*/(""");
                                        </script>
            <input type="hidden" name="bio" id='ir' value=0>

            <input type="button" name="previous" class="previous action-button" value="Previous" />
           <!-- <input type="button" name="next" class="next action-button" value="Next" /> -->
            <input type="submit" name="submit" class="submit action-button" value="Submit" id="createStudy"/>
      </fieldset>
    <!--<fieldset>
        <h2 class="fs-title">Psychometrics</h2>
        <h3 class="fs-subtitle"></h3>
        <table cellspacing="0" class="data_table" id="add_sessions_table">
            <tr>
                <td class="cell_1"> State-Trait Anxiety Inventory: </td>
                <td class="cell_2"><input type="checkbox" id="SAI" name="SAI" value=1 style="width: 20px">SAI&nbsp;&nbsp;&nbsp;&nbsp; </td>


                <td class="cell_2"><input type="checkbox" id="TAI" name="STAI_other" value=1000 style="width: 20px">TAI</td>
            </tr>
            <tr>
                <td class="cell_1"> Positive and Negative Affect Scales: </td>
                <td class="cell_2"><input type="checkbox" id="PA" name="PA" value=10000 style="width: 20px">PA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                <td class="cell_2"><input type="checkbox" id="NA" name="NA" value=100000 style="width: 20px">NA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                <td class="cell_2"><input type="checkbox" id ="Trait_Other" name="Trait_Other" value=1000000 style="width: 20px">Other</td>
                <td class="cell_2"></td>
            </tr>
        </table>

        <script>
                                                $(document).ready(function()"""),format.raw/*467.77*/("""{"""),format.raw/*467.78*/("""
                                                    """),format.raw/*468.53*/("""$('#SAI').change(function() """),format.raw/*468.81*/("""{"""),format.raw/*468.82*/("""
                                                    """),format.raw/*469.53*/("""if($(this).is(":checked")) """),format.raw/*469.80*/("""{"""),format.raw/*469.81*/("""
                                                       """),format.raw/*470.56*/("""var a =  parseInt($("#Psychometric").val()) + 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*472.56*/("""}"""),format.raw/*472.57*/("""
                                                       """),format.raw/*473.56*/("""else
                                                       """),format.raw/*474.56*/("""{"""),format.raw/*474.57*/("""
                                                        """),format.raw/*475.57*/("""var a =  parseInt($("#Psychometric").val()) - 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*477.56*/("""}"""),format.raw/*477.57*/("""
                                                     """),format.raw/*478.54*/("""}"""),format.raw/*478.55*/(""");

                                                     $('#TAI').change(function() """),format.raw/*480.82*/("""{"""),format.raw/*480.83*/("""
                                                     """),format.raw/*481.54*/("""if($(this).is(":checked")) """),format.raw/*481.81*/("""{"""),format.raw/*481.82*/("""
                                                       """),format.raw/*482.56*/("""var a =  parseInt($("#Psychometric").val()) + 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*484.56*/("""}"""),format.raw/*484.57*/("""
                                                       """),format.raw/*485.56*/("""else
                                                       """),format.raw/*486.56*/("""{"""),format.raw/*486.57*/("""
                                                         """),format.raw/*487.58*/("""var a =  parseInt($("#Psychometric").val()) - 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*489.56*/("""}"""),format.raw/*489.57*/("""
                                                     """),format.raw/*490.54*/("""}"""),format.raw/*490.55*/(""");

                                                      $('#PA').change(function() """),format.raw/*492.82*/("""{"""),format.raw/*492.83*/("""
                                                     """),format.raw/*493.54*/("""if($(this).is(":checked")) """),format.raw/*493.81*/("""{"""),format.raw/*493.82*/("""

                                                       """),format.raw/*495.56*/("""var a =  parseInt($("#Psychometric").val()) + 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*497.56*/("""}"""),format.raw/*497.57*/("""
                                                       """),format.raw/*498.56*/("""else
                                                       """),format.raw/*499.56*/("""{"""),format.raw/*499.57*/("""
                                                           """),format.raw/*500.60*/("""var a =  parseInt($("#Psychometric").val()) - 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*502.56*/("""}"""),format.raw/*502.57*/("""
                                                     """),format.raw/*503.54*/("""}"""),format.raw/*503.55*/(""");

                                                      $('#NA').change(function() """),format.raw/*505.82*/("""{"""),format.raw/*505.83*/("""
                                                     """),format.raw/*506.54*/("""if($(this).is(":checked")) """),format.raw/*506.81*/("""{"""),format.raw/*506.82*/("""

                                                       """),format.raw/*508.56*/("""var a =  parseInt($("#Psychometric").val()) + 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*510.56*/("""}"""),format.raw/*510.57*/("""
                                                       """),format.raw/*511.56*/("""else
                                                       """),format.raw/*512.56*/("""{"""),format.raw/*512.57*/("""
                                                           """),format.raw/*513.60*/("""var a =  parseInt($("#Psychometric").val()) - 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*515.56*/("""}"""),format.raw/*515.57*/("""
                                                     """),format.raw/*516.54*/("""}"""),format.raw/*516.55*/(""");

                                                      $('#Trait_Other').change(function() """),format.raw/*518.91*/("""{"""),format.raw/*518.92*/("""
                                                     """),format.raw/*519.54*/("""if($(this).is(":checked")) """),format.raw/*519.81*/("""{"""),format.raw/*519.82*/("""

                                                       """),format.raw/*521.56*/("""var a =  parseInt($("#Psychometric").val()) + 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*523.56*/("""}"""),format.raw/*523.57*/("""
                                                       """),format.raw/*524.56*/("""else
                                                       """),format.raw/*525.56*/("""{"""),format.raw/*525.57*/("""
                                                           """),format.raw/*526.60*/("""var a =  parseInt($("#Psychometric").val()) - 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*528.56*/("""}"""),format.raw/*528.57*/("""
                                                     """),format.raw/*529.54*/("""}"""),format.raw/*529.55*/(""");

                                                 """),format.raw/*531.50*/("""}"""),format.raw/*531.51*/(""");
                                    </script>
        <input type="hidden" name="psychometric" id='Psychometric' value=0>


        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="button" name="next" class="next action-button" value="Next" />
    </fieldset> -->
    <!-- <fieldset>
        <h2 class="fs-title">Physiological</h2>
        <h3 class="fs-subtitle"></h3>
        <table cellspacing="0" class="data_table" id="add_sessions_table">
            <tr>
                <td  class="cell_2"><input type="checkbox" id="EDA" name="EDA" value=1 style="width: 20px">EDA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                <td  class="cell_2"><input type="checkbox" id="Motion" name="Motion" value=2 style="width: 20px">Motion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                <td  class="cell_2"><input type="checkbox" id= "GSR_Finger" name="GSR_Finger" value=3 style="width: 20px">GSR-Finger</td>
            </tr>
            <tr>
                <td  class="cell_2"><input type="checkbox" id="Breathing_Belt" name="Breathing_Belt" value=4 style="width: 20px">Breathing-Belt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td  class="cell_2"><input type="checkbox" id="Breathing_Thermal" name="Breathing_Thermal" value=4 style="width: 20px">Breathing-Thermal&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td  class="cell_2"><input type="checkbox" id= "Heart_Rate" name="Heart_Rate" value=4 style="width: 20px">Heart Rate&nbsp;&nbsp;</td>

            </tr>
            <tr>
                <td  class="cell_2"><input type="checkbox" id="Perspiration" name="Perspiration" value=4 style="width: 20px">Perspiration&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class="cell_2"></td>
                <td class="cell_2"></td>
            </tr>

        </table>
        <script>
                                                $(document).ready(function()"""),format.raw/*562.77*/("""{"""),format.raw/*562.78*/("""
                                                """),format.raw/*563.49*/("""$('#EDA').change(function() """),format.raw/*563.77*/("""{"""),format.raw/*563.78*/("""
                                                    """),format.raw/*564.53*/("""if($(this).is(":checked")) """),format.raw/*564.80*/("""{"""),format.raw/*564.81*/("""
                                                       """),format.raw/*565.56*/("""var a =  parseInt($("#physiology").val()) + 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*567.56*/("""}"""),format.raw/*567.57*/("""
                                                       """),format.raw/*568.56*/("""else
                                                       """),format.raw/*569.56*/("""{"""),format.raw/*569.57*/("""
                                                        """),format.raw/*570.57*/("""var a =  parseInt($("#physiology").val()) - 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*572.56*/("""}"""),format.raw/*572.57*/("""
                                                     """),format.raw/*573.54*/("""}"""),format.raw/*573.55*/(""");

                                                     $('#Motion').change(function() """),format.raw/*575.85*/("""{"""),format.raw/*575.86*/("""
                                                     """),format.raw/*576.54*/("""if($(this).is(":checked")) """),format.raw/*576.81*/("""{"""),format.raw/*576.82*/("""
                                                       """),format.raw/*577.56*/("""var a =  parseInt($("#physiology").val()) + 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*579.56*/("""}"""),format.raw/*579.57*/("""
                                                       """),format.raw/*580.56*/("""else
                                                       """),format.raw/*581.56*/("""{"""),format.raw/*581.57*/("""
                                                         """),format.raw/*582.58*/("""var a =  parseInt($("#physiology").val()) - 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*584.56*/("""}"""),format.raw/*584.57*/("""
                                                     """),format.raw/*585.54*/("""}"""),format.raw/*585.55*/(""");

                                                      $('#GSR_Finger').change(function() """),format.raw/*587.90*/("""{"""),format.raw/*587.91*/("""
                                                     """),format.raw/*588.54*/("""if($(this).is(":checked")) """),format.raw/*588.81*/("""{"""),format.raw/*588.82*/("""

                                                       """),format.raw/*590.56*/("""var a =  parseInt($("#physiology").val()) + 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*592.56*/("""}"""),format.raw/*592.57*/("""
                                                       """),format.raw/*593.56*/("""else
                                                       """),format.raw/*594.56*/("""{"""),format.raw/*594.57*/("""
                                                           """),format.raw/*595.60*/("""var a =  parseInt($("#physiology").val()) - 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*597.56*/("""}"""),format.raw/*597.57*/("""
                                                     """),format.raw/*598.54*/("""}"""),format.raw/*598.55*/(""");

                                                      $('#Breathing_Belt').change(function() """),format.raw/*600.94*/("""{"""),format.raw/*600.95*/("""
                                                     """),format.raw/*601.54*/("""if($(this).is(":checked")) """),format.raw/*601.81*/("""{"""),format.raw/*601.82*/("""

                                                       """),format.raw/*603.56*/("""var a =  parseInt($("#physiology").val()) + 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*605.56*/("""}"""),format.raw/*605.57*/("""
                                                       """),format.raw/*606.56*/("""else
                                                       """),format.raw/*607.56*/("""{"""),format.raw/*607.57*/("""
                                                           """),format.raw/*608.60*/("""var a =  parseInt($("#physiology").val()) - 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*610.56*/("""}"""),format.raw/*610.57*/("""
                                                     """),format.raw/*611.54*/("""}"""),format.raw/*611.55*/(""");

                                                      $('#Breathing_Thermal').change(function() """),format.raw/*613.97*/("""{"""),format.raw/*613.98*/("""
                                                     """),format.raw/*614.54*/("""if($(this).is(":checked")) """),format.raw/*614.81*/("""{"""),format.raw/*614.82*/("""

                                                       """),format.raw/*616.56*/("""var a =  parseInt($("#physiology").val()) + 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*618.56*/("""}"""),format.raw/*618.57*/("""
                                                       """),format.raw/*619.56*/("""else
                                                       """),format.raw/*620.56*/("""{"""),format.raw/*620.57*/("""
                                                           """),format.raw/*621.60*/("""var a =  parseInt($("#physiology").val()) - 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*623.56*/("""}"""),format.raw/*623.57*/("""
                                                     """),format.raw/*624.54*/("""}"""),format.raw/*624.55*/(""");

                                                      $('#Heart_Rate').change(function() """),format.raw/*626.90*/("""{"""),format.raw/*626.91*/("""
                                                     """),format.raw/*627.54*/("""if($(this).is(":checked")) """),format.raw/*627.81*/("""{"""),format.raw/*627.82*/("""

                                                       """),format.raw/*629.56*/("""var a =  parseInt($("#physiology").val()) + 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*631.56*/("""}"""),format.raw/*631.57*/("""
                                                       """),format.raw/*632.56*/("""else
                                                       """),format.raw/*633.56*/("""{"""),format.raw/*633.57*/("""
                                                           """),format.raw/*634.60*/("""var a =  parseInt($("#physiology").val()) - 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*636.56*/("""}"""),format.raw/*636.57*/("""
                                                     """),format.raw/*637.54*/("""}"""),format.raw/*637.55*/(""");

                                                      $('#Perspiration').change(function() """),format.raw/*639.92*/("""{"""),format.raw/*639.93*/("""
                                                     """),format.raw/*640.54*/("""if($(this).is(":checked")) """),format.raw/*640.81*/("""{"""),format.raw/*640.82*/("""

                                                       """),format.raw/*642.56*/("""var a =  parseInt($("#physiology").val()) + 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*644.56*/("""}"""),format.raw/*644.57*/("""
                                                       """),format.raw/*645.56*/("""else
                                                       """),format.raw/*646.56*/("""{"""),format.raw/*646.57*/("""
                                                           """),format.raw/*647.60*/("""var a =  parseInt($("#physiology").val()) - 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*649.56*/("""}"""),format.raw/*649.57*/("""
                                                     """),format.raw/*650.54*/("""}"""),format.raw/*650.55*/(""");

                                                 """),format.raw/*652.50*/("""}"""),format.raw/*652.51*/(""");
                                    </script>


        <input type="hidden" name="physiology" id='physiology' value=0>
        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="button" name="next" class="next action-button" value="Next" />
    </fieldset> -->
    <!--<fieldset>
        <h2 class="fs-title">Observational</h2>
        <h3 class="fs-subtitle"></h3>
        <table cellspacing="0" class="data_table" id="add_sessions_table">
            <tr>
                <td class="cell_1"> Observational 1D: </td>
                <td class="cell_2"><input type="checkbox" id="Accelerometer" name="Accelerometer" value=2 style="width: 20px">Accelerometer</td>
                <td class="cell_2"><input type="checkbox" id="Obser_other" name="Obser_other" value=3 style="width: 20px">Other</td>
            </tr>
            <tr>
                <td class="cell_1"> Observational 2D: </td>
                <td class="cell_2"><input type="checkbox" id="Video_Face" name="Video_Face" value=2 style="width: 20px">Video-Face&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class="cell_2"><input type="checkbox" id="Video_Room" name="Video_Room" value=4 style="width: 20px">Video-Room</td>
            </tr>
            <tr>
                <td class="cell_1"></td>
                <td class="cell_2"><input type="checkbox" id= "Video_Theater" name="Video_Theater" value=4 style="width: 20px">Video-Theater</td>
                <td class="cell_2"><input type="checkbox" id="FACS" name="FACS" value=4 style="width: 20px">FACS</td>
            </tr>
            <tr>
                <td class="cell_1"></td>
                <td class="cell_2"><input type="checkbox" id="Obser2d_other" name="Obser2d_other" value=4 style="width: 20px">Other&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </table>

        <script>
                                                $(document).ready(function()"""),format.raw/*686.77*/("""{"""),format.raw/*686.78*/("""
                                                """),format.raw/*687.49*/("""$('#Accelerometer').change(function() """),format.raw/*687.87*/("""{"""),format.raw/*687.88*/("""
                                                    """),format.raw/*688.53*/("""if($(this).is(":checked")) """),format.raw/*688.80*/("""{"""),format.raw/*688.81*/("""
                                                       """),format.raw/*689.56*/("""var a =  parseInt($("#observation").val()) + 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*691.56*/("""}"""),format.raw/*691.57*/("""
                                                       """),format.raw/*692.56*/("""else
                                                       """),format.raw/*693.56*/("""{"""),format.raw/*693.57*/("""
                                                        """),format.raw/*694.57*/("""var a =  parseInt($("#observation").val()) - 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*696.56*/("""}"""),format.raw/*696.57*/("""
                                                     """),format.raw/*697.54*/("""}"""),format.raw/*697.55*/(""");

                                                     $('#Obser_other').change(function() """),format.raw/*699.90*/("""{"""),format.raw/*699.91*/("""
                                                     """),format.raw/*700.54*/("""if($(this).is(":checked")) """),format.raw/*700.81*/("""{"""),format.raw/*700.82*/("""
                                                       """),format.raw/*701.56*/("""var a =  parseInt($("#observation").val()) + 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*703.56*/("""}"""),format.raw/*703.57*/("""
                                                       """),format.raw/*704.56*/("""else
                                                       """),format.raw/*705.56*/("""{"""),format.raw/*705.57*/("""
                                                         """),format.raw/*706.58*/("""var a =  parseInt($("#observation").val()) - 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*708.56*/("""}"""),format.raw/*708.57*/("""
                                                     """),format.raw/*709.54*/("""}"""),format.raw/*709.55*/(""");

                                                      $('#Video_Face').change(function() """),format.raw/*711.90*/("""{"""),format.raw/*711.91*/("""
                                                     """),format.raw/*712.54*/("""if($(this).is(":checked")) """),format.raw/*712.81*/("""{"""),format.raw/*712.82*/("""

                                                       """),format.raw/*714.56*/("""var a =  parseInt($("#observation").val()) + 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*716.56*/("""}"""),format.raw/*716.57*/("""
                                                       """),format.raw/*717.56*/("""else
                                                       """),format.raw/*718.56*/("""{"""),format.raw/*718.57*/("""
                                                           """),format.raw/*719.60*/("""var a =  parseInt($("#observation").val()) - 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*721.56*/("""}"""),format.raw/*721.57*/("""
                                                     """),format.raw/*722.54*/("""}"""),format.raw/*722.55*/(""");

                                                      $('#Video_Room').change(function() """),format.raw/*724.90*/("""{"""),format.raw/*724.91*/("""
                                                     """),format.raw/*725.54*/("""if($(this).is(":checked")) """),format.raw/*725.81*/("""{"""),format.raw/*725.82*/("""

                                                       """),format.raw/*727.56*/("""var a =  parseInt($("#observation").val()) + 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*729.56*/("""}"""),format.raw/*729.57*/("""
                                                       """),format.raw/*730.56*/("""else
                                                       """),format.raw/*731.56*/("""{"""),format.raw/*731.57*/("""
                                                           """),format.raw/*732.60*/("""var a =  parseInt($("#observation").val()) - 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*734.56*/("""}"""),format.raw/*734.57*/("""
                                                     """),format.raw/*735.54*/("""}"""),format.raw/*735.55*/(""");

                                                      $('#Video_Theater').change(function() """),format.raw/*737.93*/("""{"""),format.raw/*737.94*/("""
                                                     """),format.raw/*738.54*/("""if($(this).is(":checked")) """),format.raw/*738.81*/("""{"""),format.raw/*738.82*/("""

                                                       """),format.raw/*740.56*/("""var a =  parseInt($("#observation").val()) + 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*742.56*/("""}"""),format.raw/*742.57*/("""
                                                       """),format.raw/*743.56*/("""else
                                                       """),format.raw/*744.56*/("""{"""),format.raw/*744.57*/("""
                                                           """),format.raw/*745.60*/("""var a =  parseInt($("#observation").val()) - 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*747.56*/("""}"""),format.raw/*747.57*/("""
                                                     """),format.raw/*748.54*/("""}"""),format.raw/*748.55*/(""");

                                                      $('#FACS').change(function() """),format.raw/*750.84*/("""{"""),format.raw/*750.85*/("""
                                                     """),format.raw/*751.54*/("""if($(this).is(":checked")) """),format.raw/*751.81*/("""{"""),format.raw/*751.82*/("""

                                                       """),format.raw/*753.56*/("""var a =  parseInt($("#observation").val()) + 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*755.56*/("""}"""),format.raw/*755.57*/("""
                                                       """),format.raw/*756.56*/("""else
                                                       """),format.raw/*757.56*/("""{"""),format.raw/*757.57*/("""
                                                           """),format.raw/*758.60*/("""var a =  parseInt($("#observation").val()) - 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*760.56*/("""}"""),format.raw/*760.57*/("""
                                                     """),format.raw/*761.54*/("""}"""),format.raw/*761.55*/(""");

                                                      $('#Obser2d_other').change(function() """),format.raw/*763.93*/("""{"""),format.raw/*763.94*/("""
                                                     """),format.raw/*764.54*/("""if($(this).is(":checked")) """),format.raw/*764.81*/("""{"""),format.raw/*764.82*/("""

                                                       """),format.raw/*766.56*/("""var a =  parseInt($("#observation").val()) + 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*768.56*/("""}"""),format.raw/*768.57*/("""
                                                       """),format.raw/*769.56*/("""else
                                                       """),format.raw/*770.56*/("""{"""),format.raw/*770.57*/("""
                                                           """),format.raw/*771.60*/("""var a =  parseInt($("#observation").val()) - 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*773.56*/("""}"""),format.raw/*773.57*/("""
                                                     """),format.raw/*774.54*/("""}"""),format.raw/*774.55*/(""");

                                                 """),format.raw/*776.50*/("""}"""),format.raw/*776.51*/(""");
                                    </script>

        <input type="hidden" name="observation" id='observation' value=0>
        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="button" name="next" class="next action-button" value="Next" />
    </fieldset> -->
    <!--<fieldset>
        <h2 class="fs-title">Performance</h2>
        <h3 class="fs-subtitle"></h3>
        <input type="text" name="Perfro_name" placeholder="Performance Name" />
        <input type="text" name="Per_min" placeholder="Min" />
        <input type="text" name="Per_mix" placeholder="Max" />
        <input type="button" name="Add" class="next" value="Add" />
        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="submit" name="submit" class="submit action-button" value="Submit" id="createStudy"/>

    </fieldset> -->
    </br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
    """)))}),format.raw/*795.6*/("""
    """),format.raw/*796.5*/("""</form>

    </div>



</div><!--/.main-->

 <div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
     <p> Please wait, study is being created </p>
 </div>

<script>
    var wsUri = """"),_display_(/*809.19*/routes/*809.25*/.Application.socket.webSocketURL()),format.raw/*809.59*/("""";
    var userName = """"),_display_(/*810.22*/userName),format.raw/*810.30*/(""""
</script>



</body>

</html>
"""))}
  }

  def render(userName:String,explVar:List[scala.Tuple3[String, Int, Int]],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(userName,explVar)(request)

  def f:((String,List[scala.Tuple3[String, Int, Int]]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (userName,explVar) => (request) => apply(userName,explVar)(request)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Dec 03 16:26:34 CST 2015
                  SOURCE: C:/first_play/app/views/ShowCreateStudy.scala.html
                  HASH: 8cb1a02ad7a202c166626c34ad85fc90bed49dc3
                  MATRIX: 566->1|756->88|784->108|893->190|921->191|963->205|1252->467|1281->468|1320->480|3765->2897|3795->2905|3825->2906|5827->4881|5843->4887|5954->4987|5996->4989|6034->4999|9992->8929|10029->8949|10069->8950|10128->8981|10155->8998|10196->9000|10256->9031|10308->9055|10322->9059|10347->9062|10378->9064|10429->9087|10443->9091|10468->9094|10498->9095|10570->9135|10633->9166|10688->9192|11307->9783|11344->9803|11384->9804|11451->9843|11478->9860|11519->9862|11586->9900|11658->9944|11672->9948|11697->9951|11773->9998|11788->10002|11814->10005|11895->10054|11966->10093|12029->10127|12496->10566|12533->10586|12573->10587|12632->10618|12658->10634|12699->10636|12758->10666|12811->10691|12825->10695|12850->10698|12883->10703|12897->10707|12922->10710|12994->10750|13057->10781|13112->10807|13514->11181|13551->11201|13591->11202|13650->11233|13676->11249|13717->11251|13776->11281|13829->11306|13843->11310|13868->11313|13902->11319|13916->11323|13941->11326|14013->11366|14076->11397|14131->11423|14997->12261|15034->12281|15074->12282|15137->12317|15163->12333|15204->12335|15267->12369|15319->12393|15333->12397|15358->12400|15389->12402|15440->12425|15454->12429|15479->12432|15509->12433|15585->12477|15648->12508|15705->12536|16552->13355|16589->13375|16629->13376|16696->13415|16723->13432|16764->13434|16831->13472|16905->13518|16919->13522|16944->13525|17020->13572|17035->13576|17061->13579|17141->13627|17212->13666|17275->13700|18296->14693|18333->14713|18373->14714|18432->14745|18459->14762|18500->14764|18559->14794|18611->14818|18625->14822|18650->14825|18681->14827|18732->14850|18746->14854|18771->14857|18843->14897|18906->14928|18961->14954|20067->16031|20097->16032|20148->16054|20210->16087|20240->16088|20291->16110|20347->16137|20377->16138|20435->16167|20513->16216|20543->16217|20601->16246|20663->16279|20693->16280|20752->16310|20830->16359|20860->16360|20916->16387|20946->16388|21037->16450|21067->16451|21118->16473|21174->16500|21204->16501|21262->16530|21342->16581|21372->16582|21430->16611|21492->16644|21522->16645|21581->16675|21661->16726|21691->16727|21747->16754|21777->16755|21858->16807|21888->16808|21943->16834|21999->16861|22029->16862|22087->16891|22239->17014|22269->17015|22327->17044|22389->17077|22419->17078|22478->17108|22630->17231|22660->17232|22716->17259|22746->17260|22837->17322|22867->17323|22923->17350|22979->17377|23009->17378|23069->17409|23222->17533|23252->17534|23310->17563|23372->17596|23402->17597|23462->17628|23615->17752|23645->17753|23701->17780|23731->17781|23823->17844|23853->17845|23909->17872|23965->17899|23995->17900|24055->17931|24209->18056|24239->18057|24297->18086|24359->18119|24389->18120|24451->18153|24605->18278|24635->18279|24691->18306|24721->18307|24813->18370|24843->18371|24899->18398|24955->18425|24985->18426|25045->18457|25200->18583|25230->18584|25288->18613|25350->18646|25380->18647|25442->18680|25597->18806|25627->18807|25683->18834|25713->18835|25808->18901|25838->18902|25894->18929|25950->18956|25980->18957|26040->18988|26196->19115|26226->19116|26284->19145|26346->19178|26376->19179|26438->19212|26594->19339|26624->19340|26680->19367|26710->19368|26805->19434|26835->19435|26891->19462|26947->19489|26977->19490|27037->19521|27194->19649|27224->19650|27282->19679|27344->19712|27374->19713|27436->19746|27593->19874|27623->19875|27679->19902|27709->19903|27765->19930|27795->19931|29560->21667|29590->21668|29673->21722|29730->21750|29760->21751|29843->21805|29899->21832|29929->21833|30015->21890|30243->22089|30273->22090|30359->22147|30449->22208|30479->22209|30566->22267|30794->22466|30824->22467|30908->22522|30938->22523|31054->22610|31084->22611|31168->22666|31224->22693|31254->22694|31340->22751|31569->22951|31599->22952|31685->23009|31775->23070|31805->23071|31893->23130|32122->23330|32152->23331|32236->23386|32266->23387|32382->23474|32412->23475|32496->23530|32552->23557|32582->23558|32670->23617|32900->23818|32930->23819|33016->23876|33106->23937|33136->23938|33226->23999|33456->24200|33486->24201|33570->24256|33600->24257|33716->24344|33746->24345|33830->24400|33886->24427|33916->24428|34004->24487|34235->24689|34265->24690|34351->24747|34441->24808|34471->24809|34561->24870|34792->25072|34822->25073|34906->25128|34936->25129|35061->25225|35091->25226|35175->25281|35231->25308|35261->25309|35349->25368|35581->25571|35611->25572|35697->25629|35787->25690|35817->25691|35907->25752|36139->25955|36169->25956|36253->26011|36283->26012|36367->26067|36397->26068|38659->28301|38689->28302|38768->28352|38825->28380|38855->28381|38938->28435|38994->28462|39024->28463|39110->28520|39334->28715|39364->28716|39450->28773|39540->28834|39570->28835|39657->28893|39881->29088|39911->29089|39995->29144|40025->29145|40144->29235|40174->29236|40258->29291|40314->29318|40344->29319|40430->29376|40655->29572|40685->29573|40771->29630|40861->29691|40891->29692|40979->29751|41204->29947|41234->29948|41318->30003|41348->30004|41472->30099|41502->30100|41586->30155|41642->30182|41672->30183|41760->30242|41986->30439|42016->30440|42102->30497|42192->30558|42222->30559|42312->30620|42538->30817|42568->30818|42652->30873|42682->30874|42810->30973|42840->30974|42924->31029|42980->31056|43010->31057|43098->31116|43325->31314|43355->31315|43441->31372|43531->31433|43561->31434|43651->31495|43878->31693|43908->31694|43992->31749|44022->31750|44153->31852|44183->31853|44267->31908|44323->31935|44353->31936|44441->31995|44669->32194|44699->32195|44785->32252|44875->32313|44905->32314|44995->32375|45223->32574|45253->32575|45337->32630|45367->32631|45491->32726|45521->32727|45605->32782|45661->32809|45691->32810|45779->32869|46008->33069|46038->33070|46124->33127|46214->33188|46244->33189|46334->33250|46563->33450|46593->33451|46677->33506|46707->33507|46833->33604|46863->33605|46947->33660|47003->33687|47033->33688|47121->33747|47351->33948|47381->33949|47467->34006|47557->34067|47587->34068|47677->34129|47907->34330|47937->34331|48021->34386|48051->34387|48135->34442|48165->34443|50267->36516|50297->36517|50376->36567|50443->36605|50473->36606|50556->36660|50612->36687|50642->36688|50728->36745|50954->36942|50984->36943|51070->37000|51160->37061|51190->37062|51277->37120|51503->37317|51533->37318|51617->37373|51647->37374|51771->37469|51801->37470|51885->37525|51941->37552|51971->37553|52057->37610|52284->37808|52314->37809|52400->37866|52490->37927|52520->37928|52608->37987|52835->38185|52865->38186|52949->38241|52979->38242|53103->38337|53133->38338|53217->38393|53273->38420|53303->38421|53391->38480|53619->38679|53649->38680|53735->38737|53825->38798|53855->38799|53945->38860|54173->39059|54203->39060|54287->39115|54317->39116|54441->39211|54471->39212|54555->39267|54611->39294|54641->39295|54729->39354|54958->39554|54988->39555|55074->39612|55164->39673|55194->39674|55284->39735|55513->39935|55543->39936|55627->39991|55657->39992|55784->40090|55814->40091|55898->40146|55954->40173|55984->40174|56072->40233|56302->40434|56332->40435|56418->40492|56508->40553|56538->40554|56628->40615|56858->40816|56888->40817|56972->40872|57002->40873|57120->40962|57150->40963|57234->41018|57290->41045|57320->41046|57408->41105|57639->41307|57669->41308|57755->41365|57845->41426|57875->41427|57965->41488|58196->41690|58226->41691|58310->41746|58340->41747|58467->41845|58497->41846|58581->41901|58637->41928|58667->41929|58755->41988|58987->42191|59017->42192|59103->42249|59193->42310|59223->42311|59313->42372|59545->42575|59575->42576|59659->42631|59689->42632|59773->42687|59803->42688|60917->43771|60951->43777|61303->44101|61319->44107|61375->44141|61428->44166|61458->44174
                  LINES: 19->1|22->1|23->3|29->9|29->9|30->10|38->18|38->18|42->22|84->64|84->64|84->64|139->119|139->119|139->119|139->119|140->120|219->199|219->199|219->199|220->200|220->200|220->200|221->201|221->201|221->201|221->201|221->201|221->201|221->201|221->201|221->201|222->202|223->203|224->204|235->215|235->215|235->215|236->216|236->216|236->216|237->217|237->217|237->217|237->217|237->217|237->217|237->217|238->218|239->219|240->220|252->232|252->232|252->232|253->233|253->233|253->233|254->234|254->234|254->234|254->234|254->234|254->234|254->234|255->235|256->236|257->237|267->247|267->247|267->247|268->248|268->248|268->248|269->249|269->249|269->249|269->249|269->249|269->249|269->249|270->250|271->251|272->252|290->270|290->270|290->270|291->271|291->271|291->271|292->272|292->272|292->272|292->272|292->272|292->272|292->272|292->272|292->272|293->273|294->274|296->276|310->290|310->290|310->290|311->291|311->291|311->291|312->292|312->292|312->292|312->292|312->292|312->292|312->292|313->293|314->294|315->295|340->320|340->320|340->320|341->321|341->321|341->321|342->322|342->322|342->322|342->322|342->322|342->322|342->322|342->322|343->323|344->324|345->325|363->343|363->343|364->344|364->344|364->344|365->345|365->345|365->345|366->346|367->347|367->347|368->348|369->349|369->349|370->350|371->351|371->351|372->352|372->352|373->353|373->353|374->354|374->354|374->354|375->355|376->356|376->356|377->357|378->358|378->358|379->359|380->360|380->360|381->361|381->361|382->362|382->362|383->363|383->363|383->363|384->364|386->366|386->366|387->367|388->368|388->368|389->369|391->371|391->371|392->372|392->372|394->374|394->374|395->375|395->375|395->375|396->376|398->378|398->378|399->379|400->380|400->380|401->381|403->383|403->383|404->384|404->384|406->386|406->386|407->387|407->387|407->387|409->389|411->391|411->391|412->392|413->393|413->393|414->394|416->396|416->396|417->397|417->397|419->399|419->399|420->400|420->400|420->400|422->402|424->404|424->404|425->405|426->406|426->406|427->407|429->409|429->409|430->410|430->410|432->412|432->412|433->413|433->413|433->413|435->415|437->417|437->417|438->418|439->419|439->419|440->420|442->422|442->422|443->423|443->423|445->425|445->425|446->426|446->426|446->426|448->428|450->430|450->430|451->431|452->432|452->432|453->433|455->435|455->435|456->436|456->436|458->438|458->438|487->467|487->467|488->468|488->468|488->468|489->469|489->469|489->469|490->470|492->472|492->472|493->473|494->474|494->474|495->475|497->477|497->477|498->478|498->478|500->480|500->480|501->481|501->481|501->481|502->482|504->484|504->484|505->485|506->486|506->486|507->487|509->489|509->489|510->490|510->490|512->492|512->492|513->493|513->493|513->493|515->495|517->497|517->497|518->498|519->499|519->499|520->500|522->502|522->502|523->503|523->503|525->505|525->505|526->506|526->506|526->506|528->508|530->510|530->510|531->511|532->512|532->512|533->513|535->515|535->515|536->516|536->516|538->518|538->518|539->519|539->519|539->519|541->521|543->523|543->523|544->524|545->525|545->525|546->526|548->528|548->528|549->529|549->529|551->531|551->531|582->562|582->562|583->563|583->563|583->563|584->564|584->564|584->564|585->565|587->567|587->567|588->568|589->569|589->569|590->570|592->572|592->572|593->573|593->573|595->575|595->575|596->576|596->576|596->576|597->577|599->579|599->579|600->580|601->581|601->581|602->582|604->584|604->584|605->585|605->585|607->587|607->587|608->588|608->588|608->588|610->590|612->592|612->592|613->593|614->594|614->594|615->595|617->597|617->597|618->598|618->598|620->600|620->600|621->601|621->601|621->601|623->603|625->605|625->605|626->606|627->607|627->607|628->608|630->610|630->610|631->611|631->611|633->613|633->613|634->614|634->614|634->614|636->616|638->618|638->618|639->619|640->620|640->620|641->621|643->623|643->623|644->624|644->624|646->626|646->626|647->627|647->627|647->627|649->629|651->631|651->631|652->632|653->633|653->633|654->634|656->636|656->636|657->637|657->637|659->639|659->639|660->640|660->640|660->640|662->642|664->644|664->644|665->645|666->646|666->646|667->647|669->649|669->649|670->650|670->650|672->652|672->652|706->686|706->686|707->687|707->687|707->687|708->688|708->688|708->688|709->689|711->691|711->691|712->692|713->693|713->693|714->694|716->696|716->696|717->697|717->697|719->699|719->699|720->700|720->700|720->700|721->701|723->703|723->703|724->704|725->705|725->705|726->706|728->708|728->708|729->709|729->709|731->711|731->711|732->712|732->712|732->712|734->714|736->716|736->716|737->717|738->718|738->718|739->719|741->721|741->721|742->722|742->722|744->724|744->724|745->725|745->725|745->725|747->727|749->729|749->729|750->730|751->731|751->731|752->732|754->734|754->734|755->735|755->735|757->737|757->737|758->738|758->738|758->738|760->740|762->742|762->742|763->743|764->744|764->744|765->745|767->747|767->747|768->748|768->748|770->750|770->750|771->751|771->751|771->751|773->753|775->755|775->755|776->756|777->757|777->757|778->758|780->760|780->760|781->761|781->761|783->763|783->763|784->764|784->764|784->764|786->766|788->768|788->768|789->769|790->770|790->770|791->771|793->773|793->773|794->774|794->774|796->776|796->776|815->795|816->796|829->809|829->809|829->809|830->810|830->810
                  -- GENERATED --
              */
          