
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
    <script type='text/javascript' src='"""),_display_(/*44.42*/routes/*44.48*/.Assets.at("javascripts/showCreateStudy.js")),format.raw/*44.92*/("""'></script>
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
            <!-- <a class="navbar-brand" href="#"><span></span>Share</a> -->
            <a class="navbar-brand" href="/frontPage"><span></span>Help</a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*65.128*/userName),format.raw/*65.136*/(""" """),format.raw/*65.137*/("""<span class="caret"></span></a>
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



    """),_display_(/*120.6*/helper/*120.12*/.form(action = routes.Application.InsertNewStudy, 'enctype -> "multipart/form-data", 'id-> "target")/*120.112*/ {_display_(Seq[Any](format.raw/*120.114*/("""
        """),format.raw/*121.9*/("""<ul class="taa" id="progressbar1">
        <li class="active">Experimental Design</li>
        <li> Explanatory Variables</li>
        <li> Response Variable </li>
        <li> covariates</li>
       <!-- <li>Performance </li>  -->
    </ul>
    <!-- fieldsets -->

        <button type="button" id="mdl1" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1" style="display: none;">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Select Baseline Session</h4>
                    </div>

                    <div class="modal-footer" id = "mdl1Sub">

                    </div>

                    <!--<div class="modal-footer" >

                        <button type="button" class="btn btn-primary" id = "mdl1Finish">Finish</button>
                    </div> -->
                </div>
            </div>
        </div>


        <button type="button" id="mdl2" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2" style="display: none;">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Select the Explanatory Variable</h4>
                    </div>

                    <div class="modal-footer" id = "mdl1Expl">

                    </div>
                </div>
            </div>
        </div>



        <button type="button" id="mdl3" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal3" style="display: none;">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Select the Response Variable</h4>
                    </div>

                    <div class="modal-footer" id = "mdl1Resp">

                    </div>
                </div>
            </div>
        </div>


        <button type="button" id="mdl4" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal4" style="display: none;">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Select the Response Covariate</h4>
                    </div>
                        <input  type="radio" name="baseline" value="female" checked="true" > Gender
                    <div class="modal-footer" id = "mdl1covr">

                    </div>
                </div>
            </div>
        </div>

        <fieldset>
            <h2 class="fs-title">Experimental Design</h2>
        <h3 class="fs-subtitle"></h3>
        <table id="zob" cellspacing="0" class="data_table" id="add_sessions_table">

            <tr>
                <td class="cell_1">* Source type:</td>
                <td>
                    <select class="select_field"  id="sourcetype" name="study_type" >
                        <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value=1  selected ="selected">Google Drive</option>
                        """),_display_(/*229.26*/if( userName =="cplsubjectbook@gmail.com")/*229.68*/ {_display_(Seq[Any](format.raw/*229.70*/("""
                        """),format.raw/*230.25*/("""<option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_2" value=2>CPL Server</option>
                        """)))}),format.raw/*231.26*/("""
                    """),format.raw/*232.21*/("""</select>

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
                            """),_display_(/*291.30*/for(temp <- explVar) yield /*291.50*/{_display_(Seq[Any](format.raw/*291.51*/("""
                            """),_display_(/*292.30*/if(temp._3 == 1 )/*292.47*/ {_display_(Seq[Any](format.raw/*292.49*/("""
                             """),format.raw/*293.30*/("""<option id="op1" value="""),_display_(/*293.54*/temp/*293.58*/._2),format.raw/*293.61*/("""  """),format.raw/*293.63*/("""selected ="selected"> """),_display_(/*293.86*/temp/*293.90*/._1),format.raw/*293.93*/(""" """),format.raw/*293.94*/("""</option>
                            """)))}),format.raw/*294.30*/("""
                            """)))}),format.raw/*295.30*/("""
                        """),format.raw/*296.25*/("""</select>
                    </td>
                </tr>
                <tr>
                    <td class="cell_1">Secondary Explanatory Variables</td>
                    <td>

                        <div class="col-lg-12">
                            <div class="button-group">
                                <button type="button" style="width: 300px" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> Select Variables <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    """),_display_(/*307.38*/for(temp <- explVar) yield /*307.58*/{_display_(Seq[Any](format.raw/*307.59*/("""
                                    """),_display_(/*308.38*/if(temp._3 == 1 )/*308.55*/ {_display_(Seq[Any](format.raw/*308.57*/("""
                                    """),format.raw/*309.37*/("""<li><a href="#" class="small1" data-value=""""),_display_(/*309.81*/temp/*309.85*/._2),format.raw/*309.88*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*309.135*/temp/*309.139*/._1),format.raw/*309.142*/("""</a></li>
                                     """)))}),format.raw/*310.39*/("""
                                    """)))}),format.raw/*311.38*/("""
                                """),format.raw/*312.33*/("""</ul>
                            </div>
                        </div>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data Before Sessions</td>
                    <td>
                        <select class="select_field"  id="staticB" name="study_type" >

                            """),_display_(/*324.30*/for(temp <- explVar) yield /*324.50*/{_display_(Seq[Any](format.raw/*324.51*/("""
                            """),_display_(/*325.30*/if(temp._3 == 4)/*325.46*/ {_display_(Seq[Any](format.raw/*325.48*/("""
                            """),format.raw/*326.29*/("""<option id="op1" value=""""),_display_(/*326.54*/temp/*326.58*/._2),format.raw/*326.61*/("""" > """),_display_(/*326.66*/temp/*326.70*/._1),format.raw/*326.73*/("""</option>
                            """)))}),format.raw/*327.30*/("""
                            """)))}),format.raw/*328.30*/("""
                            """),format.raw/*329.29*/("""<option id="op1" value=-1 > none </option>
                        </select>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data After Sessions</td>
                    <td>
                        <select class="select_field"  id="staticA" name="study_type" >
                            """),_display_(/*339.30*/for(temp <- explVar) yield /*339.50*/{_display_(Seq[Any](format.raw/*339.51*/("""
                            """),_display_(/*340.30*/if(temp._3 == 4)/*340.46*/ {_display_(Seq[Any](format.raw/*340.48*/("""
                            """),format.raw/*341.29*/("""<option id="op1" value=""""),_display_(/*341.54*/temp/*341.58*/._2),format.raw/*341.61*/(""""  > """),_display_(/*341.67*/temp/*341.71*/._1),format.raw/*341.74*/("""</option>
                            """)))}),format.raw/*342.30*/("""
                            """)))}),format.raw/*343.30*/("""
                            """),format.raw/*344.29*/("""<option id="op1" value=-1 > none </option>
                        </select>

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
                            """),_display_(/*363.30*/for(temp <- explVar) yield /*363.50*/{_display_(Seq[Any](format.raw/*363.51*/("""
                                """),_display_(/*364.34*/if(temp._3 == 1)/*364.50*/ {_display_(Seq[Any](format.raw/*364.52*/("""
                                """),format.raw/*365.33*/("""<option id="op1" value="""),_display_(/*365.57*/temp/*365.61*/._2),format.raw/*365.64*/("""  """),format.raw/*365.66*/("""selected ="selected"> """),_display_(/*365.89*/temp/*365.93*/._1),format.raw/*365.96*/(""" """),format.raw/*365.97*/("""</option>
                                """)))}),format.raw/*366.34*/("""
                            """)))}),format.raw/*367.30*/("""

                        """),format.raw/*369.25*/("""</select>
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
                                    """),_display_(/*383.38*/for(temp <- explVar) yield /*383.58*/{_display_(Seq[Any](format.raw/*383.59*/("""
                                    """),_display_(/*384.38*/if(temp._3 == 1 )/*384.55*/ {_display_(Seq[Any](format.raw/*384.57*/("""
                                    """),format.raw/*385.37*/("""<li><a href="#"   class="small2" data-value=""""),_display_(/*385.83*/temp/*385.87*/._2),format.raw/*385.90*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*385.137*/temp/*385.141*/._1),format.raw/*385.144*/("""</a></li>
                                    """)))}),format.raw/*386.38*/("""
                                    """)))}),format.raw/*387.38*/("""
                                """),format.raw/*388.33*/("""</ul>
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
                            """),_display_(/*412.30*/for(temp <- explVar) yield /*412.50*/{_display_(Seq[Any](format.raw/*412.51*/("""
                            """),_display_(/*413.30*/if(temp._3 == 3 )/*413.47*/ {_display_(Seq[Any](format.raw/*413.49*/("""
                            """),format.raw/*414.29*/("""<option id="op1" value="""),_display_(/*414.53*/temp/*414.57*/._2),format.raw/*414.60*/("""  """),format.raw/*414.62*/("""selected ="selected"> """),_display_(/*414.85*/temp/*414.89*/._1),format.raw/*414.92*/("""</option>
                            """)))}),format.raw/*415.30*/("""
                            """)))}),format.raw/*416.30*/("""
                            """),format.raw/*417.29*/("""<option id="op1" value=-1 > none </option>
                        </select>

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
                    $(document).ready(function()"""),format.raw/*436.49*/("""{"""),format.raw/*436.50*/("""
                    """),format.raw/*437.21*/("""$('#publicCh').change(function() """),format.raw/*437.54*/("""{"""),format.raw/*437.55*/("""
                    """),format.raw/*438.21*/("""if($(this).is(":checked")) """),format.raw/*438.48*/("""{"""),format.raw/*438.49*/("""
                           """),format.raw/*439.28*/("""$("#public").val(1);
                           """),format.raw/*440.28*/("""}"""),format.raw/*440.29*/("""
                           """),format.raw/*441.28*/("""else
                           """),format.raw/*442.28*/("""{"""),format.raw/*442.29*/("""
                            """),format.raw/*443.29*/("""$("#public").val(0);
                           """),format.raw/*444.28*/("""}"""),format.raw/*444.29*/("""
                         """),format.raw/*445.26*/("""}"""),format.raw/*445.27*/(""");
                       $('#portraitCh').change(function() """),format.raw/*446.59*/("""{"""),format.raw/*446.60*/("""
                    """),format.raw/*447.21*/("""if($(this).is(":checked")) """),format.raw/*447.48*/("""{"""),format.raw/*447.49*/("""
                           """),format.raw/*448.28*/("""$("#portrait").val(1);
                           """),format.raw/*449.28*/("""}"""),format.raw/*449.29*/("""
                           """),format.raw/*450.28*/("""else
                           """),format.raw/*451.28*/("""{"""),format.raw/*451.29*/("""
                            """),format.raw/*452.29*/("""$("#portrait").val(0);
                           """),format.raw/*453.28*/("""}"""),format.raw/*453.29*/("""
                         """),format.raw/*454.26*/("""}"""),format.raw/*454.27*/(""");
                    $('#Age').change(function() """),format.raw/*455.49*/("""{"""),format.raw/*455.50*/("""
                        """),format.raw/*456.25*/("""if($(this).is(":checked")) """),format.raw/*456.52*/("""{"""),format.raw/*456.53*/("""
                           """),format.raw/*457.28*/("""var a =  parseInt($("#ir").val()) + 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*459.28*/("""}"""),format.raw/*459.29*/("""
                           """),format.raw/*460.28*/("""else
                           """),format.raw/*461.28*/("""{"""),format.raw/*461.29*/("""
                            """),format.raw/*462.29*/("""var a =  parseInt($("#ir").val()) - 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*464.28*/("""}"""),format.raw/*464.29*/("""
                         """),format.raw/*465.26*/("""}"""),format.raw/*465.27*/(""");

                         $('#Height').change(function() """),format.raw/*467.57*/("""{"""),format.raw/*467.58*/("""
                         """),format.raw/*468.26*/("""if($(this).is(":checked")) """),format.raw/*468.53*/("""{"""),format.raw/*468.54*/("""
                             """),format.raw/*469.30*/("""var a =  parseInt($("#ir").val()) + 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*471.28*/("""}"""),format.raw/*471.29*/("""
                           """),format.raw/*472.28*/("""else
                           """),format.raw/*473.28*/("""{"""),format.raw/*473.29*/("""
                             """),format.raw/*474.30*/("""var a =  parseInt($("#ir").val()) - 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*476.28*/("""}"""),format.raw/*476.29*/("""
                         """),format.raw/*477.26*/("""}"""),format.raw/*477.27*/(""");

                          $('#Weight').change(function() """),format.raw/*479.58*/("""{"""),format.raw/*479.59*/("""
                         """),format.raw/*480.26*/("""if($(this).is(":checked")) """),format.raw/*480.53*/("""{"""),format.raw/*480.54*/("""

                           """),format.raw/*482.28*/("""var a =  parseInt($("#ir").val()) + 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*484.28*/("""}"""),format.raw/*484.29*/("""
                           """),format.raw/*485.28*/("""else
                           """),format.raw/*486.28*/("""{"""),format.raw/*486.29*/("""
                               """),format.raw/*487.32*/("""var a =  parseInt($("#ir").val()) - 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*489.28*/("""}"""),format.raw/*489.29*/("""
                         """),format.raw/*490.26*/("""}"""),format.raw/*490.27*/(""");

                          $('#Gender').change(function() """),format.raw/*492.58*/("""{"""),format.raw/*492.59*/("""
                         """),format.raw/*493.26*/("""if($(this).is(":checked")) """),format.raw/*493.53*/("""{"""),format.raw/*493.54*/("""

                           """),format.raw/*495.28*/("""var a =  parseInt($("#ir").val()) + 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*497.28*/("""}"""),format.raw/*497.29*/("""
                           """),format.raw/*498.28*/("""else
                           """),format.raw/*499.28*/("""{"""),format.raw/*499.29*/("""
                               """),format.raw/*500.32*/("""var a =  parseInt($("#ir").val()) - 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*502.28*/("""}"""),format.raw/*502.29*/("""
                         """),format.raw/*503.26*/("""}"""),format.raw/*503.27*/(""");

                          $('#Ethnicity').change(function() """),format.raw/*505.61*/("""{"""),format.raw/*505.62*/("""
                         """),format.raw/*506.26*/("""if($(this).is(":checked")) """),format.raw/*506.53*/("""{"""),format.raw/*506.54*/("""

                           """),format.raw/*508.28*/("""var a =  parseInt($("#ir").val()) + 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*510.28*/("""}"""),format.raw/*510.29*/("""
                           """),format.raw/*511.28*/("""else
                           """),format.raw/*512.28*/("""{"""),format.raw/*512.29*/("""
                               """),format.raw/*513.32*/("""var a =  parseInt($("#ir").val()) - 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*515.28*/("""}"""),format.raw/*515.29*/("""
                         """),format.raw/*516.26*/("""}"""),format.raw/*516.27*/(""");

                          $('#Bio_other').change(function() """),format.raw/*518.61*/("""{"""),format.raw/*518.62*/("""
                         """),format.raw/*519.26*/("""if($(this).is(":checked")) """),format.raw/*519.53*/("""{"""),format.raw/*519.54*/("""

                           """),format.raw/*521.28*/("""var a =  parseInt($("#ir").val()) + 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*523.28*/("""}"""),format.raw/*523.29*/("""
                           """),format.raw/*524.28*/("""else
                           """),format.raw/*525.28*/("""{"""),format.raw/*525.29*/("""
                               """),format.raw/*526.32*/("""var a =  parseInt($("#ir").val()) - 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*528.28*/("""}"""),format.raw/*528.29*/("""
                         """),format.raw/*529.26*/("""}"""),format.raw/*529.27*/(""");

                     """),format.raw/*531.22*/("""}"""),format.raw/*531.23*/(""");
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
                                                $(document).ready(function()"""),format.raw/*560.77*/("""{"""),format.raw/*560.78*/("""
                                                    """),format.raw/*561.53*/("""$('#SAI').change(function() """),format.raw/*561.81*/("""{"""),format.raw/*561.82*/("""
                                                    """),format.raw/*562.53*/("""if($(this).is(":checked")) """),format.raw/*562.80*/("""{"""),format.raw/*562.81*/("""
                                                       """),format.raw/*563.56*/("""var a =  parseInt($("#Psychometric").val()) + 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*565.56*/("""}"""),format.raw/*565.57*/("""
                                                       """),format.raw/*566.56*/("""else
                                                       """),format.raw/*567.56*/("""{"""),format.raw/*567.57*/("""
                                                        """),format.raw/*568.57*/("""var a =  parseInt($("#Psychometric").val()) - 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*570.56*/("""}"""),format.raw/*570.57*/("""
                                                     """),format.raw/*571.54*/("""}"""),format.raw/*571.55*/(""");

                                                     $('#TAI').change(function() """),format.raw/*573.82*/("""{"""),format.raw/*573.83*/("""
                                                     """),format.raw/*574.54*/("""if($(this).is(":checked")) """),format.raw/*574.81*/("""{"""),format.raw/*574.82*/("""
                                                       """),format.raw/*575.56*/("""var a =  parseInt($("#Psychometric").val()) + 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*577.56*/("""}"""),format.raw/*577.57*/("""
                                                       """),format.raw/*578.56*/("""else
                                                       """),format.raw/*579.56*/("""{"""),format.raw/*579.57*/("""
                                                         """),format.raw/*580.58*/("""var a =  parseInt($("#Psychometric").val()) - 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*582.56*/("""}"""),format.raw/*582.57*/("""
                                                     """),format.raw/*583.54*/("""}"""),format.raw/*583.55*/(""");

                                                      $('#PA').change(function() """),format.raw/*585.82*/("""{"""),format.raw/*585.83*/("""
                                                     """),format.raw/*586.54*/("""if($(this).is(":checked")) """),format.raw/*586.81*/("""{"""),format.raw/*586.82*/("""

                                                       """),format.raw/*588.56*/("""var a =  parseInt($("#Psychometric").val()) + 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*590.56*/("""}"""),format.raw/*590.57*/("""
                                                       """),format.raw/*591.56*/("""else
                                                       """),format.raw/*592.56*/("""{"""),format.raw/*592.57*/("""
                                                           """),format.raw/*593.60*/("""var a =  parseInt($("#Psychometric").val()) - 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*595.56*/("""}"""),format.raw/*595.57*/("""
                                                     """),format.raw/*596.54*/("""}"""),format.raw/*596.55*/(""");

                                                      $('#NA').change(function() """),format.raw/*598.82*/("""{"""),format.raw/*598.83*/("""
                                                     """),format.raw/*599.54*/("""if($(this).is(":checked")) """),format.raw/*599.81*/("""{"""),format.raw/*599.82*/("""

                                                       """),format.raw/*601.56*/("""var a =  parseInt($("#Psychometric").val()) + 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*603.56*/("""}"""),format.raw/*603.57*/("""
                                                       """),format.raw/*604.56*/("""else
                                                       """),format.raw/*605.56*/("""{"""),format.raw/*605.57*/("""
                                                           """),format.raw/*606.60*/("""var a =  parseInt($("#Psychometric").val()) - 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*608.56*/("""}"""),format.raw/*608.57*/("""
                                                     """),format.raw/*609.54*/("""}"""),format.raw/*609.55*/(""");

                                                      $('#Trait_Other').change(function() """),format.raw/*611.91*/("""{"""),format.raw/*611.92*/("""
                                                     """),format.raw/*612.54*/("""if($(this).is(":checked")) """),format.raw/*612.81*/("""{"""),format.raw/*612.82*/("""

                                                       """),format.raw/*614.56*/("""var a =  parseInt($("#Psychometric").val()) + 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*616.56*/("""}"""),format.raw/*616.57*/("""
                                                       """),format.raw/*617.56*/("""else
                                                       """),format.raw/*618.56*/("""{"""),format.raw/*618.57*/("""
                                                           """),format.raw/*619.60*/("""var a =  parseInt($("#Psychometric").val()) - 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*621.56*/("""}"""),format.raw/*621.57*/("""
                                                     """),format.raw/*622.54*/("""}"""),format.raw/*622.55*/(""");

                                                 """),format.raw/*624.50*/("""}"""),format.raw/*624.51*/(""");
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
                                                $(document).ready(function()"""),format.raw/*655.77*/("""{"""),format.raw/*655.78*/("""
                                                """),format.raw/*656.49*/("""$('#EDA').change(function() """),format.raw/*656.77*/("""{"""),format.raw/*656.78*/("""
                                                    """),format.raw/*657.53*/("""if($(this).is(":checked")) """),format.raw/*657.80*/("""{"""),format.raw/*657.81*/("""
                                                       """),format.raw/*658.56*/("""var a =  parseInt($("#physiology").val()) + 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*660.56*/("""}"""),format.raw/*660.57*/("""
                                                       """),format.raw/*661.56*/("""else
                                                       """),format.raw/*662.56*/("""{"""),format.raw/*662.57*/("""
                                                        """),format.raw/*663.57*/("""var a =  parseInt($("#physiology").val()) - 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*665.56*/("""}"""),format.raw/*665.57*/("""
                                                     """),format.raw/*666.54*/("""}"""),format.raw/*666.55*/(""");

                                                     $('#Motion').change(function() """),format.raw/*668.85*/("""{"""),format.raw/*668.86*/("""
                                                     """),format.raw/*669.54*/("""if($(this).is(":checked")) """),format.raw/*669.81*/("""{"""),format.raw/*669.82*/("""
                                                       """),format.raw/*670.56*/("""var a =  parseInt($("#physiology").val()) + 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*672.56*/("""}"""),format.raw/*672.57*/("""
                                                       """),format.raw/*673.56*/("""else
                                                       """),format.raw/*674.56*/("""{"""),format.raw/*674.57*/("""
                                                         """),format.raw/*675.58*/("""var a =  parseInt($("#physiology").val()) - 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*677.56*/("""}"""),format.raw/*677.57*/("""
                                                     """),format.raw/*678.54*/("""}"""),format.raw/*678.55*/(""");

                                                      $('#GSR_Finger').change(function() """),format.raw/*680.90*/("""{"""),format.raw/*680.91*/("""
                                                     """),format.raw/*681.54*/("""if($(this).is(":checked")) """),format.raw/*681.81*/("""{"""),format.raw/*681.82*/("""

                                                       """),format.raw/*683.56*/("""var a =  parseInt($("#physiology").val()) + 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*685.56*/("""}"""),format.raw/*685.57*/("""
                                                       """),format.raw/*686.56*/("""else
                                                       """),format.raw/*687.56*/("""{"""),format.raw/*687.57*/("""
                                                           """),format.raw/*688.60*/("""var a =  parseInt($("#physiology").val()) - 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*690.56*/("""}"""),format.raw/*690.57*/("""
                                                     """),format.raw/*691.54*/("""}"""),format.raw/*691.55*/(""");

                                                      $('#Breathing_Belt').change(function() """),format.raw/*693.94*/("""{"""),format.raw/*693.95*/("""
                                                     """),format.raw/*694.54*/("""if($(this).is(":checked")) """),format.raw/*694.81*/("""{"""),format.raw/*694.82*/("""

                                                       """),format.raw/*696.56*/("""var a =  parseInt($("#physiology").val()) + 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*698.56*/("""}"""),format.raw/*698.57*/("""
                                                       """),format.raw/*699.56*/("""else
                                                       """),format.raw/*700.56*/("""{"""),format.raw/*700.57*/("""
                                                           """),format.raw/*701.60*/("""var a =  parseInt($("#physiology").val()) - 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*703.56*/("""}"""),format.raw/*703.57*/("""
                                                     """),format.raw/*704.54*/("""}"""),format.raw/*704.55*/(""");

                                                      $('#Breathing_Thermal').change(function() """),format.raw/*706.97*/("""{"""),format.raw/*706.98*/("""
                                                     """),format.raw/*707.54*/("""if($(this).is(":checked")) """),format.raw/*707.81*/("""{"""),format.raw/*707.82*/("""

                                                       """),format.raw/*709.56*/("""var a =  parseInt($("#physiology").val()) + 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*711.56*/("""}"""),format.raw/*711.57*/("""
                                                       """),format.raw/*712.56*/("""else
                                                       """),format.raw/*713.56*/("""{"""),format.raw/*713.57*/("""
                                                           """),format.raw/*714.60*/("""var a =  parseInt($("#physiology").val()) - 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*716.56*/("""}"""),format.raw/*716.57*/("""
                                                     """),format.raw/*717.54*/("""}"""),format.raw/*717.55*/(""");

                                                      $('#Heart_Rate').change(function() """),format.raw/*719.90*/("""{"""),format.raw/*719.91*/("""
                                                     """),format.raw/*720.54*/("""if($(this).is(":checked")) """),format.raw/*720.81*/("""{"""),format.raw/*720.82*/("""

                                                       """),format.raw/*722.56*/("""var a =  parseInt($("#physiology").val()) + 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*724.56*/("""}"""),format.raw/*724.57*/("""
                                                       """),format.raw/*725.56*/("""else
                                                       """),format.raw/*726.56*/("""{"""),format.raw/*726.57*/("""
                                                           """),format.raw/*727.60*/("""var a =  parseInt($("#physiology").val()) - 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*729.56*/("""}"""),format.raw/*729.57*/("""
                                                     """),format.raw/*730.54*/("""}"""),format.raw/*730.55*/(""");

                                                      $('#Perspiration').change(function() """),format.raw/*732.92*/("""{"""),format.raw/*732.93*/("""
                                                     """),format.raw/*733.54*/("""if($(this).is(":checked")) """),format.raw/*733.81*/("""{"""),format.raw/*733.82*/("""

                                                       """),format.raw/*735.56*/("""var a =  parseInt($("#physiology").val()) + 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*737.56*/("""}"""),format.raw/*737.57*/("""
                                                       """),format.raw/*738.56*/("""else
                                                       """),format.raw/*739.56*/("""{"""),format.raw/*739.57*/("""
                                                           """),format.raw/*740.60*/("""var a =  parseInt($("#physiology").val()) - 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*742.56*/("""}"""),format.raw/*742.57*/("""
                                                     """),format.raw/*743.54*/("""}"""),format.raw/*743.55*/(""");

                                                 """),format.raw/*745.50*/("""}"""),format.raw/*745.51*/(""");
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
                                                $(document).ready(function()"""),format.raw/*779.77*/("""{"""),format.raw/*779.78*/("""
                                                """),format.raw/*780.49*/("""$('#Accelerometer').change(function() """),format.raw/*780.87*/("""{"""),format.raw/*780.88*/("""
                                                    """),format.raw/*781.53*/("""if($(this).is(":checked")) """),format.raw/*781.80*/("""{"""),format.raw/*781.81*/("""
                                                       """),format.raw/*782.56*/("""var a =  parseInt($("#observation").val()) + 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*784.56*/("""}"""),format.raw/*784.57*/("""
                                                       """),format.raw/*785.56*/("""else
                                                       """),format.raw/*786.56*/("""{"""),format.raw/*786.57*/("""
                                                        """),format.raw/*787.57*/("""var a =  parseInt($("#observation").val()) - 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*789.56*/("""}"""),format.raw/*789.57*/("""
                                                     """),format.raw/*790.54*/("""}"""),format.raw/*790.55*/(""");

                                                     $('#Obser_other').change(function() """),format.raw/*792.90*/("""{"""),format.raw/*792.91*/("""
                                                     """),format.raw/*793.54*/("""if($(this).is(":checked")) """),format.raw/*793.81*/("""{"""),format.raw/*793.82*/("""
                                                       """),format.raw/*794.56*/("""var a =  parseInt($("#observation").val()) + 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*796.56*/("""}"""),format.raw/*796.57*/("""
                                                       """),format.raw/*797.56*/("""else
                                                       """),format.raw/*798.56*/("""{"""),format.raw/*798.57*/("""
                                                         """),format.raw/*799.58*/("""var a =  parseInt($("#observation").val()) - 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*801.56*/("""}"""),format.raw/*801.57*/("""
                                                     """),format.raw/*802.54*/("""}"""),format.raw/*802.55*/(""");

                                                      $('#Video_Face').change(function() """),format.raw/*804.90*/("""{"""),format.raw/*804.91*/("""
                                                     """),format.raw/*805.54*/("""if($(this).is(":checked")) """),format.raw/*805.81*/("""{"""),format.raw/*805.82*/("""

                                                       """),format.raw/*807.56*/("""var a =  parseInt($("#observation").val()) + 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*809.56*/("""}"""),format.raw/*809.57*/("""
                                                       """),format.raw/*810.56*/("""else
                                                       """),format.raw/*811.56*/("""{"""),format.raw/*811.57*/("""
                                                           """),format.raw/*812.60*/("""var a =  parseInt($("#observation").val()) - 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*814.56*/("""}"""),format.raw/*814.57*/("""
                                                     """),format.raw/*815.54*/("""}"""),format.raw/*815.55*/(""");

                                                      $('#Video_Room').change(function() """),format.raw/*817.90*/("""{"""),format.raw/*817.91*/("""
                                                     """),format.raw/*818.54*/("""if($(this).is(":checked")) """),format.raw/*818.81*/("""{"""),format.raw/*818.82*/("""

                                                       """),format.raw/*820.56*/("""var a =  parseInt($("#observation").val()) + 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*822.56*/("""}"""),format.raw/*822.57*/("""
                                                       """),format.raw/*823.56*/("""else
                                                       """),format.raw/*824.56*/("""{"""),format.raw/*824.57*/("""
                                                           """),format.raw/*825.60*/("""var a =  parseInt($("#observation").val()) - 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*827.56*/("""}"""),format.raw/*827.57*/("""
                                                     """),format.raw/*828.54*/("""}"""),format.raw/*828.55*/(""");

                                                      $('#Video_Theater').change(function() """),format.raw/*830.93*/("""{"""),format.raw/*830.94*/("""
                                                     """),format.raw/*831.54*/("""if($(this).is(":checked")) """),format.raw/*831.81*/("""{"""),format.raw/*831.82*/("""

                                                       """),format.raw/*833.56*/("""var a =  parseInt($("#observation").val()) + 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*835.56*/("""}"""),format.raw/*835.57*/("""
                                                       """),format.raw/*836.56*/("""else
                                                       """),format.raw/*837.56*/("""{"""),format.raw/*837.57*/("""
                                                           """),format.raw/*838.60*/("""var a =  parseInt($("#observation").val()) - 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*840.56*/("""}"""),format.raw/*840.57*/("""
                                                     """),format.raw/*841.54*/("""}"""),format.raw/*841.55*/(""");

                                                      $('#FACS').change(function() """),format.raw/*843.84*/("""{"""),format.raw/*843.85*/("""
                                                     """),format.raw/*844.54*/("""if($(this).is(":checked")) """),format.raw/*844.81*/("""{"""),format.raw/*844.82*/("""

                                                       """),format.raw/*846.56*/("""var a =  parseInt($("#observation").val()) + 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*848.56*/("""}"""),format.raw/*848.57*/("""
                                                       """),format.raw/*849.56*/("""else
                                                       """),format.raw/*850.56*/("""{"""),format.raw/*850.57*/("""
                                                           """),format.raw/*851.60*/("""var a =  parseInt($("#observation").val()) - 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*853.56*/("""}"""),format.raw/*853.57*/("""
                                                     """),format.raw/*854.54*/("""}"""),format.raw/*854.55*/(""");

                                                      $('#Obser2d_other').change(function() """),format.raw/*856.93*/("""{"""),format.raw/*856.94*/("""
                                                     """),format.raw/*857.54*/("""if($(this).is(":checked")) """),format.raw/*857.81*/("""{"""),format.raw/*857.82*/("""

                                                       """),format.raw/*859.56*/("""var a =  parseInt($("#observation").val()) + 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*861.56*/("""}"""),format.raw/*861.57*/("""
                                                       """),format.raw/*862.56*/("""else
                                                       """),format.raw/*863.56*/("""{"""),format.raw/*863.57*/("""
                                                           """),format.raw/*864.60*/("""var a =  parseInt($("#observation").val()) - 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*866.56*/("""}"""),format.raw/*866.57*/("""
                                                     """),format.raw/*867.54*/("""}"""),format.raw/*867.55*/(""");

                                                 """),format.raw/*869.50*/("""}"""),format.raw/*869.51*/(""");
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
    """)))}),format.raw/*888.6*/("""
    """),format.raw/*889.5*/("""</form>

    </div>



</div><!--/.main-->

 <div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
     <p> Please wait, study is being created </p>
 </div>

<script>
    var wsUri = """"),_display_(/*902.19*/routes/*902.25*/.Application.socket.webSocketURL()),format.raw/*902.59*/("""";
    var userName = """"),_display_(/*903.22*/userName),format.raw/*903.30*/(""""



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
                  DATE: Wed Mar 09 15:07:45 CST 2016
                  SOURCE: C:/first_play/app/views/ShowCreateStudy.scala.html
                  HASH: 986570e44da304cc1ab4bc74250aa18bfd21dc1b
                  MATRIX: 566->1|756->88|784->108|893->190|921->191|963->205|1252->467|1281->468|1320->480|2516->1649|2531->1655|2596->1699|3846->2921|3876->2929|3906->2930|5908->4905|5924->4911|6035->5011|6077->5013|6115->5023|10944->9824|10996->9866|11037->9868|11092->9894|11279->10049|11330->10071|14265->12978|14302->12998|14342->12999|14401->13030|14428->13047|14469->13049|14529->13080|14581->13104|14595->13108|14620->13111|14651->13113|14702->13136|14716->13140|14741->13143|14771->13144|14843->13184|14906->13215|14961->13241|15580->13832|15617->13852|15657->13853|15724->13892|15751->13909|15792->13911|15859->13949|15931->13993|15945->13997|15970->14000|16046->14047|16061->14051|16087->14054|16168->14103|16239->14142|16302->14176|16699->14545|16736->14565|16776->14566|16835->14597|16861->14613|16902->14615|16961->14645|17014->14670|17028->14674|17053->14677|17086->14682|17100->14686|17125->14689|17197->14729|17260->14760|17319->14790|17717->15160|17754->15180|17794->15181|17853->15212|17879->15228|17920->15230|17979->15260|18032->15285|18046->15289|18071->15292|18105->15298|18119->15302|18144->15305|18216->15345|18279->15376|18338->15406|19272->16312|19309->16332|19349->16333|19412->16368|19438->16384|19479->16386|19542->16420|19594->16444|19608->16448|19633->16451|19664->16453|19715->16476|19729->16480|19754->16483|19784->16484|19860->16528|19923->16559|19980->16587|20827->17406|20864->17426|20904->17427|20971->17466|20998->17483|21039->17485|21106->17523|21180->17569|21194->17573|21219->17576|21295->17623|21310->17627|21336->17630|21416->17678|21487->17717|21550->17751|22499->18672|22536->18692|22576->18693|22635->18724|22662->18741|22703->18743|22762->18773|22814->18797|22828->18801|22853->18804|22884->18806|22935->18829|22949->18833|22974->18836|23046->18876|23109->18907|23168->18937|24342->20082|24372->20083|24423->20105|24485->20138|24515->20139|24566->20161|24622->20188|24652->20189|24710->20218|24788->20267|24818->20268|24876->20297|24938->20330|24968->20331|25027->20361|25105->20410|25135->20411|25191->20438|25221->20439|25312->20501|25342->20502|25393->20524|25449->20551|25479->20552|25537->20581|25617->20632|25647->20633|25705->20662|25767->20695|25797->20696|25856->20726|25936->20777|25966->20778|26022->20805|26052->20806|26133->20858|26163->20859|26218->20885|26274->20912|26304->20913|26362->20942|26514->21065|26544->21066|26602->21095|26664->21128|26694->21129|26753->21159|26905->21282|26935->21283|26991->21310|27021->21311|27112->21373|27142->21374|27198->21401|27254->21428|27284->21429|27344->21460|27497->21584|27527->21585|27585->21614|27647->21647|27677->21648|27737->21679|27890->21803|27920->21804|27976->21831|28006->21832|28098->21895|28128->21896|28184->21923|28240->21950|28270->21951|28330->21982|28484->22107|28514->22108|28572->22137|28634->22170|28664->22171|28726->22204|28880->22329|28910->22330|28966->22357|28996->22358|29088->22421|29118->22422|29174->22449|29230->22476|29260->22477|29320->22508|29475->22634|29505->22635|29563->22664|29625->22697|29655->22698|29717->22731|29872->22857|29902->22858|29958->22885|29988->22886|30083->22952|30113->22953|30169->22980|30225->23007|30255->23008|30315->23039|30471->23166|30501->23167|30559->23196|30621->23229|30651->23230|30713->23263|30869->23390|30899->23391|30955->23418|30985->23419|31080->23485|31110->23486|31166->23513|31222->23540|31252->23541|31312->23572|31469->23700|31499->23701|31557->23730|31619->23763|31649->23764|31711->23797|31868->23925|31898->23926|31954->23953|31984->23954|32040->23981|32070->23982|33835->25718|33865->25719|33948->25773|34005->25801|34035->25802|34118->25856|34174->25883|34204->25884|34290->25941|34518->26140|34548->26141|34634->26198|34724->26259|34754->26260|34841->26318|35069->26517|35099->26518|35183->26573|35213->26574|35329->26661|35359->26662|35443->26717|35499->26744|35529->26745|35615->26802|35844->27002|35874->27003|35960->27060|36050->27121|36080->27122|36168->27181|36397->27381|36427->27382|36511->27437|36541->27438|36657->27525|36687->27526|36771->27581|36827->27608|36857->27609|36945->27668|37175->27869|37205->27870|37291->27927|37381->27988|37411->27989|37501->28050|37731->28251|37761->28252|37845->28307|37875->28308|37991->28395|38021->28396|38105->28451|38161->28478|38191->28479|38279->28538|38510->28740|38540->28741|38626->28798|38716->28859|38746->28860|38836->28921|39067->29123|39097->29124|39181->29179|39211->29180|39336->29276|39366->29277|39450->29332|39506->29359|39536->29360|39624->29419|39856->29622|39886->29623|39972->29680|40062->29741|40092->29742|40182->29803|40414->30006|40444->30007|40528->30062|40558->30063|40642->30118|40672->30119|42934->32352|42964->32353|43043->32403|43100->32431|43130->32432|43213->32486|43269->32513|43299->32514|43385->32571|43609->32766|43639->32767|43725->32824|43815->32885|43845->32886|43932->32944|44156->33139|44186->33140|44270->33195|44300->33196|44419->33286|44449->33287|44533->33342|44589->33369|44619->33370|44705->33427|44930->33623|44960->33624|45046->33681|45136->33742|45166->33743|45254->33802|45479->33998|45509->33999|45593->34054|45623->34055|45747->34150|45777->34151|45861->34206|45917->34233|45947->34234|46035->34293|46261->34490|46291->34491|46377->34548|46467->34609|46497->34610|46587->34671|46813->34868|46843->34869|46927->34924|46957->34925|47085->35024|47115->35025|47199->35080|47255->35107|47285->35108|47373->35167|47600->35365|47630->35366|47716->35423|47806->35484|47836->35485|47926->35546|48153->35744|48183->35745|48267->35800|48297->35801|48428->35903|48458->35904|48542->35959|48598->35986|48628->35987|48716->36046|48944->36245|48974->36246|49060->36303|49150->36364|49180->36365|49270->36426|49498->36625|49528->36626|49612->36681|49642->36682|49766->36777|49796->36778|49880->36833|49936->36860|49966->36861|50054->36920|50283->37120|50313->37121|50399->37178|50489->37239|50519->37240|50609->37301|50838->37501|50868->37502|50952->37557|50982->37558|51108->37655|51138->37656|51222->37711|51278->37738|51308->37739|51396->37798|51626->37999|51656->38000|51742->38057|51832->38118|51862->38119|51952->38180|52182->38381|52212->38382|52296->38437|52326->38438|52410->38493|52440->38494|54542->40567|54572->40568|54651->40618|54718->40656|54748->40657|54831->40711|54887->40738|54917->40739|55003->40796|55229->40993|55259->40994|55345->41051|55435->41112|55465->41113|55552->41171|55778->41368|55808->41369|55892->41424|55922->41425|56046->41520|56076->41521|56160->41576|56216->41603|56246->41604|56332->41661|56559->41859|56589->41860|56675->41917|56765->41978|56795->41979|56883->42038|57110->42236|57140->42237|57224->42292|57254->42293|57378->42388|57408->42389|57492->42444|57548->42471|57578->42472|57666->42531|57894->42730|57924->42731|58010->42788|58100->42849|58130->42850|58220->42911|58448->43110|58478->43111|58562->43166|58592->43167|58716->43262|58746->43263|58830->43318|58886->43345|58916->43346|59004->43405|59233->43605|59263->43606|59349->43663|59439->43724|59469->43725|59559->43786|59788->43986|59818->43987|59902->44042|59932->44043|60059->44141|60089->44142|60173->44197|60229->44224|60259->44225|60347->44284|60577->44485|60607->44486|60693->44543|60783->44604|60813->44605|60903->44666|61133->44867|61163->44868|61247->44923|61277->44924|61395->45013|61425->45014|61509->45069|61565->45096|61595->45097|61683->45156|61914->45358|61944->45359|62030->45416|62120->45477|62150->45478|62240->45539|62471->45741|62501->45742|62585->45797|62615->45798|62742->45896|62772->45897|62856->45952|62912->45979|62942->45980|63030->46039|63262->46242|63292->46243|63378->46300|63468->46361|63498->46362|63588->46423|63820->46626|63850->46627|63934->46682|63964->46683|64048->46738|64078->46739|65192->47822|65226->47828|65578->48152|65594->48158|65650->48192|65703->48217|65733->48225
                  LINES: 19->1|22->1|23->3|29->9|29->9|30->10|38->18|38->18|42->22|64->44|64->44|64->44|85->65|85->65|85->65|140->120|140->120|140->120|140->120|141->121|249->229|249->229|249->229|250->230|251->231|252->232|311->291|311->291|311->291|312->292|312->292|312->292|313->293|313->293|313->293|313->293|313->293|313->293|313->293|313->293|313->293|314->294|315->295|316->296|327->307|327->307|327->307|328->308|328->308|328->308|329->309|329->309|329->309|329->309|329->309|329->309|329->309|330->310|331->311|332->312|344->324|344->324|344->324|345->325|345->325|345->325|346->326|346->326|346->326|346->326|346->326|346->326|346->326|347->327|348->328|349->329|359->339|359->339|359->339|360->340|360->340|360->340|361->341|361->341|361->341|361->341|361->341|361->341|361->341|362->342|363->343|364->344|383->363|383->363|383->363|384->364|384->364|384->364|385->365|385->365|385->365|385->365|385->365|385->365|385->365|385->365|385->365|386->366|387->367|389->369|403->383|403->383|403->383|404->384|404->384|404->384|405->385|405->385|405->385|405->385|405->385|405->385|405->385|406->386|407->387|408->388|432->412|432->412|432->412|433->413|433->413|433->413|434->414|434->414|434->414|434->414|434->414|434->414|434->414|434->414|435->415|436->416|437->417|456->436|456->436|457->437|457->437|457->437|458->438|458->438|458->438|459->439|460->440|460->440|461->441|462->442|462->442|463->443|464->444|464->444|465->445|465->445|466->446|466->446|467->447|467->447|467->447|468->448|469->449|469->449|470->450|471->451|471->451|472->452|473->453|473->453|474->454|474->454|475->455|475->455|476->456|476->456|476->456|477->457|479->459|479->459|480->460|481->461|481->461|482->462|484->464|484->464|485->465|485->465|487->467|487->467|488->468|488->468|488->468|489->469|491->471|491->471|492->472|493->473|493->473|494->474|496->476|496->476|497->477|497->477|499->479|499->479|500->480|500->480|500->480|502->482|504->484|504->484|505->485|506->486|506->486|507->487|509->489|509->489|510->490|510->490|512->492|512->492|513->493|513->493|513->493|515->495|517->497|517->497|518->498|519->499|519->499|520->500|522->502|522->502|523->503|523->503|525->505|525->505|526->506|526->506|526->506|528->508|530->510|530->510|531->511|532->512|532->512|533->513|535->515|535->515|536->516|536->516|538->518|538->518|539->519|539->519|539->519|541->521|543->523|543->523|544->524|545->525|545->525|546->526|548->528|548->528|549->529|549->529|551->531|551->531|580->560|580->560|581->561|581->561|581->561|582->562|582->562|582->562|583->563|585->565|585->565|586->566|587->567|587->567|588->568|590->570|590->570|591->571|591->571|593->573|593->573|594->574|594->574|594->574|595->575|597->577|597->577|598->578|599->579|599->579|600->580|602->582|602->582|603->583|603->583|605->585|605->585|606->586|606->586|606->586|608->588|610->590|610->590|611->591|612->592|612->592|613->593|615->595|615->595|616->596|616->596|618->598|618->598|619->599|619->599|619->599|621->601|623->603|623->603|624->604|625->605|625->605|626->606|628->608|628->608|629->609|629->609|631->611|631->611|632->612|632->612|632->612|634->614|636->616|636->616|637->617|638->618|638->618|639->619|641->621|641->621|642->622|642->622|644->624|644->624|675->655|675->655|676->656|676->656|676->656|677->657|677->657|677->657|678->658|680->660|680->660|681->661|682->662|682->662|683->663|685->665|685->665|686->666|686->666|688->668|688->668|689->669|689->669|689->669|690->670|692->672|692->672|693->673|694->674|694->674|695->675|697->677|697->677|698->678|698->678|700->680|700->680|701->681|701->681|701->681|703->683|705->685|705->685|706->686|707->687|707->687|708->688|710->690|710->690|711->691|711->691|713->693|713->693|714->694|714->694|714->694|716->696|718->698|718->698|719->699|720->700|720->700|721->701|723->703|723->703|724->704|724->704|726->706|726->706|727->707|727->707|727->707|729->709|731->711|731->711|732->712|733->713|733->713|734->714|736->716|736->716|737->717|737->717|739->719|739->719|740->720|740->720|740->720|742->722|744->724|744->724|745->725|746->726|746->726|747->727|749->729|749->729|750->730|750->730|752->732|752->732|753->733|753->733|753->733|755->735|757->737|757->737|758->738|759->739|759->739|760->740|762->742|762->742|763->743|763->743|765->745|765->745|799->779|799->779|800->780|800->780|800->780|801->781|801->781|801->781|802->782|804->784|804->784|805->785|806->786|806->786|807->787|809->789|809->789|810->790|810->790|812->792|812->792|813->793|813->793|813->793|814->794|816->796|816->796|817->797|818->798|818->798|819->799|821->801|821->801|822->802|822->802|824->804|824->804|825->805|825->805|825->805|827->807|829->809|829->809|830->810|831->811|831->811|832->812|834->814|834->814|835->815|835->815|837->817|837->817|838->818|838->818|838->818|840->820|842->822|842->822|843->823|844->824|844->824|845->825|847->827|847->827|848->828|848->828|850->830|850->830|851->831|851->831|851->831|853->833|855->835|855->835|856->836|857->837|857->837|858->838|860->840|860->840|861->841|861->841|863->843|863->843|864->844|864->844|864->844|866->846|868->848|868->848|869->849|870->850|870->850|871->851|873->853|873->853|874->854|874->854|876->856|876->856|877->857|877->857|877->857|879->859|881->861|881->861|882->862|883->863|883->863|884->864|886->866|886->866|887->867|887->867|889->869|889->869|908->888|909->889|922->902|922->902|922->902|923->903|923->903
                  -- GENERATED --
              */
          