
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
object ShowCreateStudy extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[scala.Tuple3[Option[String], Int, Int]],RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, explVar : List[(Option[String], Int, Int)])(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.97*/("""
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
    <script type='text/javascript' src='"""),_display_(/*45.42*/routes/*45.48*/.Assets.at("javascripts/studyDescriptor.js")),format.raw/*45.92*/("""'></script>
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*66.128*/userName),format.raw/*66.136*/(""" """),format.raw/*66.137*/("""<span class="caret"></span></a>
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



    """),_display_(/*121.6*/helper/*121.12*/.form(action = routes.Application.InsertNewStudy, 'enctype -> "multipart/form-data", 'id-> "target"  )/*121.114*/ {_display_(Seq[Any](format.raw/*121.116*/("""
        """),format.raw/*122.9*/("""<ul class="taa" id="progressbar1">
        <li class="active">Experimental Design</li>
        <li >Data Source</li>
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

        <fieldset  style="width: 700px;">
            <h2 class="fs-title">Experimental Design</h2>
            <h3 class="fs-subtitle"></h3>
            <table id="zob" cellspacing="0" class="data_table" id="add_sessions_table">


                <input type="hidden" name="code" id= "code" value=" ">
                <input type="hidden" name="studyId"  value="1">

                    <label>How many unique sessions does your study have? </label>
                    <input type="number" class="form-control"  name="pName", id = "noVar">




                    <label>How many treatment arms does your study have? </label>
                    <input type="number" class="form-control" name="min" value ="1" id = "noPoints">




                    <table data-toggle="table"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
                        <thead>
                        <tr id ="head">

                        </tr>
                        </thead>
                        <tbody  id="tableBody">

                        </tbody>
                    </table>




            </table>
            <input id ="zeroNext"  type="button" name="next" class="next0 action-button" value="Next" />



        </fieldset>
        <fieldset>
            <h2 class="fs-title">Data Source</h2>
        <h3 class="fs-subtitle"></h3>
        <table id="zob" cellspacing="0" class="data_table" id="add_sessions_table">

            <tr>
                <td class="cell_1">* Source type:</td>
                <td>
                    <select class="select_field"  id="sourcetype" name="study_type" >
                        <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value=1  selected ="selected">Google Drive</option>
                        """),_display_(/*267.26*/if( userName =="cplsubjectbook@gmail.com")/*267.68*/ {_display_(Seq[Any](format.raw/*267.70*/("""
                        """),format.raw/*268.25*/("""<option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_2" value=2>CPL Server</option>
                        """)))}),format.raw/*269.26*/("""
                    """),format.raw/*270.21*/("""</select>

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
                <td id = "kareem3"><input type="checkbox" id="quickCreation" name="quickCreation" value=1 style="width: 20px">Quick &nbsp;&nbsp;&nbsp;<br> </td>
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
            <input type="button" name="previous" class="previous0 action-button" value="Previous" />
            <input id ="firstNext"  type="button" name="next" class="next1 action-button" value="Next" />



        </fieldset>
        <fieldset>
            <h2 class="fs-title">Explanatory Variables</h2>
            <table cellspacing="0" class="data_table" id="add_sessions_table">
                <tr>
                    <td class="cell_1">Key Explanatory Variable:</td>
                    <td>
                        <select class="select_field"  id="explanatoryPrim" name="study_type"  style="width: 300px" required>
                            """),_display_(/*331.30*/for(temp <- explVar) yield /*331.50*/{_display_(Seq[Any](format.raw/*331.51*/("""
                            """),_display_(/*332.30*/if(temp._3 == 1 )/*332.47*/ {_display_(Seq[Any](format.raw/*332.49*/("""
                             """),format.raw/*333.30*/("""<option id="op1" value="""),_display_(/*333.54*/temp/*333.58*/._2),format.raw/*333.61*/("""  """),format.raw/*333.63*/("""selected ="selected"> """),_display_(/*333.86*/temp/*333.90*/._1),format.raw/*333.93*/(""" """),format.raw/*333.94*/("""</option>
                            """)))}),format.raw/*334.30*/("""
                            """)))}),format.raw/*335.30*/("""
                        """),format.raw/*336.25*/("""</select>
                    </td>
                </tr>

                <tr>
                    <td class="cell_1">Backup Key Explanatory Variables:</td>
                    <td>
                        <select class="select_field"  id="explanatoryPrimBackUp" name="study_type"  style="width: 300px" required>
                            """),_display_(/*344.30*/for(temp <- explVar) yield /*344.50*/{_display_(Seq[Any](format.raw/*344.51*/("""
                            """),_display_(/*345.30*/if(temp._3 == 1 )/*345.47*/ {_display_(Seq[Any](format.raw/*345.49*/("""
                            """),format.raw/*346.29*/("""<option id="op3" value="""),_display_(/*346.53*/temp/*346.57*/._2),format.raw/*346.60*/("""  """),format.raw/*346.62*/("""selected ="selected"> """),_display_(/*346.85*/temp/*346.89*/._1),format.raw/*346.92*/(""" """),format.raw/*346.93*/("""</option>
                            """)))}),format.raw/*347.30*/("""
                            """)))}),format.raw/*348.30*/("""
                        """),format.raw/*349.25*/("""</select>
                    </td>
                </tr>
                <tr>
                    <td class="cell_1">Secondary Explanatory Variables</td>
                    <td>

                        <div class="col-lg-12">
                            <div class="button-group">
                                <button type="button" style="width: 300px" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> Select Variables <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    """),_display_(/*360.38*/for(temp <- explVar) yield /*360.58*/{_display_(Seq[Any](format.raw/*360.59*/("""
                                    """),_display_(/*361.38*/if(temp._3 == 1 )/*361.55*/ {_display_(Seq[Any](format.raw/*361.57*/("""
                                    """),format.raw/*362.37*/("""<li><a href="#" class="small1" data-value=""""),_display_(/*362.81*/temp/*362.85*/._2),format.raw/*362.88*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*362.135*/temp/*362.139*/._1),format.raw/*362.142*/("""</a></li>
                                     """)))}),format.raw/*363.39*/("""
                                    """)))}),format.raw/*364.38*/("""
                                """),format.raw/*365.33*/("""</ul>
                            </div>
                        </div>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data Before Sessions</td>
                    <td>
                        <select class="select_field"  id="staticB" name="study_type" >

                            """),_display_(/*377.30*/for(temp <- explVar) yield /*377.50*/{_display_(Seq[Any](format.raw/*377.51*/("""
                            """),_display_(/*378.30*/if(temp._3 == 4)/*378.46*/ {_display_(Seq[Any](format.raw/*378.48*/("""
                            """),format.raw/*379.29*/("""<option id="op1" value=""""),_display_(/*379.54*/temp/*379.58*/._2),format.raw/*379.61*/("""" > """),_display_(/*379.66*/temp/*379.70*/._1),format.raw/*379.73*/("""</option>
                            """)))}),format.raw/*380.30*/("""
                            """)))}),format.raw/*381.30*/("""
                            """),format.raw/*382.29*/("""<option id="op1" value=-1 > none </option>
                        </select>

                    </td>

                </tr>
                <tr>
                    <td class="cell_1">Static Data After Sessions</td>
                    <td>
                        <select class="select_field"  id="staticA" name="study_type" >
                            """),_display_(/*392.30*/for(temp <- explVar) yield /*392.50*/{_display_(Seq[Any](format.raw/*392.51*/("""
                            """),_display_(/*393.30*/if(temp._3 == 4)/*393.46*/ {_display_(Seq[Any](format.raw/*393.48*/("""
                            """),format.raw/*394.29*/("""<option id="op1" value=""""),_display_(/*394.54*/temp/*394.58*/._2),format.raw/*394.61*/(""""  > """),_display_(/*394.67*/temp/*394.71*/._1),format.raw/*394.74*/("""</option>
                            """)))}),format.raw/*395.30*/("""
                            """)))}),format.raw/*396.30*/("""
                            """),format.raw/*397.29*/("""<option id="op1" value=-1 > none </option>
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
                            """),_display_(/*416.30*/for(temp <- explVar) yield /*416.50*/{_display_(Seq[Any](format.raw/*416.51*/("""
                                """),_display_(/*417.34*/if(temp._3 == 1)/*417.50*/ {_display_(Seq[Any](format.raw/*417.52*/("""
                                """),format.raw/*418.33*/("""<option id="op1" value="""),_display_(/*418.57*/temp/*418.61*/._2),format.raw/*418.64*/("""  """),format.raw/*418.66*/("""selected ="selected"> """),_display_(/*418.89*/temp/*418.93*/._1),format.raw/*418.96*/(""" """),format.raw/*418.97*/("""</option>
                                """)))}),format.raw/*419.34*/("""
                            """)))}),format.raw/*420.30*/("""

                        """),format.raw/*422.25*/("""</select>
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
                                    """),_display_(/*436.38*/for(temp <- explVar) yield /*436.58*/{_display_(Seq[Any](format.raw/*436.59*/("""
                                    """),_display_(/*437.38*/if(temp._3 == 1 )/*437.55*/ {_display_(Seq[Any](format.raw/*437.57*/("""
                                    """),format.raw/*438.37*/("""<li><a href="#"   class="small2" data-value=""""),_display_(/*438.83*/temp/*438.87*/._2),format.raw/*438.90*/("""" tabIndex="-1"><input type="checkbox"/>&nbsp;"""),_display_(/*438.137*/temp/*438.141*/._1),format.raw/*438.144*/("""</a></li>
                                    """)))}),format.raw/*439.38*/("""
                                    """)))}),format.raw/*440.38*/("""
                                """),format.raw/*441.33*/("""</ul>
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
                            """),_display_(/*465.30*/for(temp <- explVar) yield /*465.50*/{_display_(Seq[Any](format.raw/*465.51*/("""
                            """),_display_(/*466.30*/if(temp._3 == 3 )/*466.47*/ {_display_(Seq[Any](format.raw/*466.49*/("""
                            """),format.raw/*467.29*/("""<option id="op1" value="""),_display_(/*467.53*/temp/*467.57*/._2),format.raw/*467.60*/("""  """),format.raw/*467.62*/("""selected ="selected"> """),_display_(/*467.85*/temp/*467.89*/._1),format.raw/*467.92*/("""</option>
                            """)))}),format.raw/*468.30*/("""
                            """)))}),format.raw/*469.30*/("""
                            """),format.raw/*470.29*/("""<option id="op1" value=-1 > none </option>
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
                    $(document).ready(function()"""),format.raw/*489.49*/("""{"""),format.raw/*489.50*/("""
                    """),format.raw/*490.21*/("""$('#publicCh').change(function() """),format.raw/*490.54*/("""{"""),format.raw/*490.55*/("""
                    """),format.raw/*491.21*/("""if($(this).is(":checked")) """),format.raw/*491.48*/("""{"""),format.raw/*491.49*/("""
                           """),format.raw/*492.28*/("""$("#public").val(1);
                           """),format.raw/*493.28*/("""}"""),format.raw/*493.29*/("""
                           """),format.raw/*494.28*/("""else
                           """),format.raw/*495.28*/("""{"""),format.raw/*495.29*/("""
                            """),format.raw/*496.29*/("""$("#public").val(0);
                           """),format.raw/*497.28*/("""}"""),format.raw/*497.29*/("""
                         """),format.raw/*498.26*/("""}"""),format.raw/*498.27*/(""");
                       $('#portraitCh').change(function() """),format.raw/*499.59*/("""{"""),format.raw/*499.60*/("""
                    """),format.raw/*500.21*/("""if($(this).is(":checked")) """),format.raw/*500.48*/("""{"""),format.raw/*500.49*/("""
                           """),format.raw/*501.28*/("""$("#portrait").val(1);
                           """),format.raw/*502.28*/("""}"""),format.raw/*502.29*/("""
                           """),format.raw/*503.28*/("""else
                           """),format.raw/*504.28*/("""{"""),format.raw/*504.29*/("""
                            """),format.raw/*505.29*/("""$("#portrait").val(0);
                           """),format.raw/*506.28*/("""}"""),format.raw/*506.29*/("""
                         """),format.raw/*507.26*/("""}"""),format.raw/*507.27*/(""");
                    $('#Age').change(function() """),format.raw/*508.49*/("""{"""),format.raw/*508.50*/("""
                        """),format.raw/*509.25*/("""if($(this).is(":checked")) """),format.raw/*509.52*/("""{"""),format.raw/*509.53*/("""
                           """),format.raw/*510.28*/("""var a =  parseInt($("#ir").val()) + 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*512.28*/("""}"""),format.raw/*512.29*/("""
                           """),format.raw/*513.28*/("""else
                           """),format.raw/*514.28*/("""{"""),format.raw/*514.29*/("""
                            """),format.raw/*515.29*/("""var a =  parseInt($("#ir").val()) - 1
                           $("#ir").val(a.toString() );
                           """),format.raw/*517.28*/("""}"""),format.raw/*517.29*/("""
                         """),format.raw/*518.26*/("""}"""),format.raw/*518.27*/(""");

                         $('#Height').change(function() """),format.raw/*520.57*/("""{"""),format.raw/*520.58*/("""
                         """),format.raw/*521.26*/("""if($(this).is(":checked")) """),format.raw/*521.53*/("""{"""),format.raw/*521.54*/("""
                             """),format.raw/*522.30*/("""var a =  parseInt($("#ir").val()) + 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*524.28*/("""}"""),format.raw/*524.29*/("""
                           """),format.raw/*525.28*/("""else
                           """),format.raw/*526.28*/("""{"""),format.raw/*526.29*/("""
                             """),format.raw/*527.30*/("""var a =  parseInt($("#ir").val()) - 10
                           $("#ir").val(a.toString() );
                           """),format.raw/*529.28*/("""}"""),format.raw/*529.29*/("""
                         """),format.raw/*530.26*/("""}"""),format.raw/*530.27*/(""");

                          $('#Weight').change(function() """),format.raw/*532.58*/("""{"""),format.raw/*532.59*/("""
                         """),format.raw/*533.26*/("""if($(this).is(":checked")) """),format.raw/*533.53*/("""{"""),format.raw/*533.54*/("""

                           """),format.raw/*535.28*/("""var a =  parseInt($("#ir").val()) + 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*537.28*/("""}"""),format.raw/*537.29*/("""
                           """),format.raw/*538.28*/("""else
                           """),format.raw/*539.28*/("""{"""),format.raw/*539.29*/("""
                               """),format.raw/*540.32*/("""var a =  parseInt($("#ir").val()) - 100
                           $("#ir").val(a.toString() );
                           """),format.raw/*542.28*/("""}"""),format.raw/*542.29*/("""
                         """),format.raw/*543.26*/("""}"""),format.raw/*543.27*/(""");

                          $('#Gender').change(function() """),format.raw/*545.58*/("""{"""),format.raw/*545.59*/("""
                         """),format.raw/*546.26*/("""if($(this).is(":checked")) """),format.raw/*546.53*/("""{"""),format.raw/*546.54*/("""

                           """),format.raw/*548.28*/("""var a =  parseInt($("#ir").val()) + 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*550.28*/("""}"""),format.raw/*550.29*/("""
                           """),format.raw/*551.28*/("""else
                           """),format.raw/*552.28*/("""{"""),format.raw/*552.29*/("""
                               """),format.raw/*553.32*/("""var a =  parseInt($("#ir").val()) - 1000
                           $("#ir").val(a.toString() );
                           """),format.raw/*555.28*/("""}"""),format.raw/*555.29*/("""
                         """),format.raw/*556.26*/("""}"""),format.raw/*556.27*/(""");

                          $('#Ethnicity').change(function() """),format.raw/*558.61*/("""{"""),format.raw/*558.62*/("""
                         """),format.raw/*559.26*/("""if($(this).is(":checked")) """),format.raw/*559.53*/("""{"""),format.raw/*559.54*/("""

                           """),format.raw/*561.28*/("""var a =  parseInt($("#ir").val()) + 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*563.28*/("""}"""),format.raw/*563.29*/("""
                           """),format.raw/*564.28*/("""else
                           """),format.raw/*565.28*/("""{"""),format.raw/*565.29*/("""
                               """),format.raw/*566.32*/("""var a =  parseInt($("#ir").val()) - 10000
                           $("#ir").val(a.toString() );
                           """),format.raw/*568.28*/("""}"""),format.raw/*568.29*/("""
                         """),format.raw/*569.26*/("""}"""),format.raw/*569.27*/(""");

                          $('#Bio_other').change(function() """),format.raw/*571.61*/("""{"""),format.raw/*571.62*/("""
                         """),format.raw/*572.26*/("""if($(this).is(":checked")) """),format.raw/*572.53*/("""{"""),format.raw/*572.54*/("""

                           """),format.raw/*574.28*/("""var a =  parseInt($("#ir").val()) + 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*576.28*/("""}"""),format.raw/*576.29*/("""
                           """),format.raw/*577.28*/("""else
                           """),format.raw/*578.28*/("""{"""),format.raw/*578.29*/("""
                               """),format.raw/*579.32*/("""var a =  parseInt($("#ir").val()) - 100000
                           $("#ir").val(a.toString() );
                           """),format.raw/*581.28*/("""}"""),format.raw/*581.29*/("""
                         """),format.raw/*582.26*/("""}"""),format.raw/*582.27*/(""");

                     """),format.raw/*584.22*/("""}"""),format.raw/*584.23*/(""");
                                        </script>
            <input type="hidden" name="bio" id='ir' value=0>

            <input type="button" name="previous" class="previous action-button" value="Previous" />
           <!-- <input type="button" name="next" class="next action-button" value="Next" /> -->
            <input type="submit" id="sb" name="submit" class="submit action-button" value="Submit" id="createStudy"/>
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
                                                $(document).ready(function()"""),format.raw/*613.77*/("""{"""),format.raw/*613.78*/("""
                                                    """),format.raw/*614.53*/("""$('#SAI').change(function() """),format.raw/*614.81*/("""{"""),format.raw/*614.82*/("""
                                                    """),format.raw/*615.53*/("""if($(this).is(":checked")) """),format.raw/*615.80*/("""{"""),format.raw/*615.81*/("""
                                                       """),format.raw/*616.56*/("""var a =  parseInt($("#Psychometric").val()) + 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*618.56*/("""}"""),format.raw/*618.57*/("""
                                                       """),format.raw/*619.56*/("""else
                                                       """),format.raw/*620.56*/("""{"""),format.raw/*620.57*/("""
                                                        """),format.raw/*621.57*/("""var a =  parseInt($("#Psychometric").val()) - 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*623.56*/("""}"""),format.raw/*623.57*/("""
                                                     """),format.raw/*624.54*/("""}"""),format.raw/*624.55*/(""");

                                                     $('#TAI').change(function() """),format.raw/*626.82*/("""{"""),format.raw/*626.83*/("""
                                                     """),format.raw/*627.54*/("""if($(this).is(":checked")) """),format.raw/*627.81*/("""{"""),format.raw/*627.82*/("""
                                                       """),format.raw/*628.56*/("""var a =  parseInt($("#Psychometric").val()) + 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*630.56*/("""}"""),format.raw/*630.57*/("""
                                                       """),format.raw/*631.56*/("""else
                                                       """),format.raw/*632.56*/("""{"""),format.raw/*632.57*/("""
                                                         """),format.raw/*633.58*/("""var a =  parseInt($("#Psychometric").val()) - 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*635.56*/("""}"""),format.raw/*635.57*/("""
                                                     """),format.raw/*636.54*/("""}"""),format.raw/*636.55*/(""");

                                                      $('#PA').change(function() """),format.raw/*638.82*/("""{"""),format.raw/*638.83*/("""
                                                     """),format.raw/*639.54*/("""if($(this).is(":checked")) """),format.raw/*639.81*/("""{"""),format.raw/*639.82*/("""

                                                       """),format.raw/*641.56*/("""var a =  parseInt($("#Psychometric").val()) + 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*643.56*/("""}"""),format.raw/*643.57*/("""
                                                       """),format.raw/*644.56*/("""else
                                                       """),format.raw/*645.56*/("""{"""),format.raw/*645.57*/("""
                                                           """),format.raw/*646.60*/("""var a =  parseInt($("#Psychometric").val()) - 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*648.56*/("""}"""),format.raw/*648.57*/("""
                                                     """),format.raw/*649.54*/("""}"""),format.raw/*649.55*/(""");

                                                      $('#NA').change(function() """),format.raw/*651.82*/("""{"""),format.raw/*651.83*/("""
                                                     """),format.raw/*652.54*/("""if($(this).is(":checked")) """),format.raw/*652.81*/("""{"""),format.raw/*652.82*/("""

                                                       """),format.raw/*654.56*/("""var a =  parseInt($("#Psychometric").val()) + 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*656.56*/("""}"""),format.raw/*656.57*/("""
                                                       """),format.raw/*657.56*/("""else
                                                       """),format.raw/*658.56*/("""{"""),format.raw/*658.57*/("""
                                                           """),format.raw/*659.60*/("""var a =  parseInt($("#Psychometric").val()) - 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*661.56*/("""}"""),format.raw/*661.57*/("""
                                                     """),format.raw/*662.54*/("""}"""),format.raw/*662.55*/(""");

                                                      $('#Trait_Other').change(function() """),format.raw/*664.91*/("""{"""),format.raw/*664.92*/("""
                                                     """),format.raw/*665.54*/("""if($(this).is(":checked")) """),format.raw/*665.81*/("""{"""),format.raw/*665.82*/("""

                                                       """),format.raw/*667.56*/("""var a =  parseInt($("#Psychometric").val()) + 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*669.56*/("""}"""),format.raw/*669.57*/("""
                                                       """),format.raw/*670.56*/("""else
                                                       """),format.raw/*671.56*/("""{"""),format.raw/*671.57*/("""
                                                           """),format.raw/*672.60*/("""var a =  parseInt($("#Psychometric").val()) - 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*674.56*/("""}"""),format.raw/*674.57*/("""
                                                     """),format.raw/*675.54*/("""}"""),format.raw/*675.55*/(""");

                                                 """),format.raw/*677.50*/("""}"""),format.raw/*677.51*/(""");
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
                                                $(document).ready(function()"""),format.raw/*708.77*/("""{"""),format.raw/*708.78*/("""
                                                """),format.raw/*709.49*/("""$('#EDA').change(function() """),format.raw/*709.77*/("""{"""),format.raw/*709.78*/("""
                                                    """),format.raw/*710.53*/("""if($(this).is(":checked")) """),format.raw/*710.80*/("""{"""),format.raw/*710.81*/("""
                                                       """),format.raw/*711.56*/("""var a =  parseInt($("#physiology").val()) + 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*713.56*/("""}"""),format.raw/*713.57*/("""
                                                       """),format.raw/*714.56*/("""else
                                                       """),format.raw/*715.56*/("""{"""),format.raw/*715.57*/("""
                                                        """),format.raw/*716.57*/("""var a =  parseInt($("#physiology").val()) - 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*718.56*/("""}"""),format.raw/*718.57*/("""
                                                     """),format.raw/*719.54*/("""}"""),format.raw/*719.55*/(""");

                                                     $('#Motion').change(function() """),format.raw/*721.85*/("""{"""),format.raw/*721.86*/("""
                                                     """),format.raw/*722.54*/("""if($(this).is(":checked")) """),format.raw/*722.81*/("""{"""),format.raw/*722.82*/("""
                                                       """),format.raw/*723.56*/("""var a =  parseInt($("#physiology").val()) + 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*725.56*/("""}"""),format.raw/*725.57*/("""
                                                       """),format.raw/*726.56*/("""else
                                                       """),format.raw/*727.56*/("""{"""),format.raw/*727.57*/("""
                                                         """),format.raw/*728.58*/("""var a =  parseInt($("#physiology").val()) - 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*730.56*/("""}"""),format.raw/*730.57*/("""
                                                     """),format.raw/*731.54*/("""}"""),format.raw/*731.55*/(""");

                                                      $('#GSR_Finger').change(function() """),format.raw/*733.90*/("""{"""),format.raw/*733.91*/("""
                                                     """),format.raw/*734.54*/("""if($(this).is(":checked")) """),format.raw/*734.81*/("""{"""),format.raw/*734.82*/("""

                                                       """),format.raw/*736.56*/("""var a =  parseInt($("#physiology").val()) + 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*738.56*/("""}"""),format.raw/*738.57*/("""
                                                       """),format.raw/*739.56*/("""else
                                                       """),format.raw/*740.56*/("""{"""),format.raw/*740.57*/("""
                                                           """),format.raw/*741.60*/("""var a =  parseInt($("#physiology").val()) - 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*743.56*/("""}"""),format.raw/*743.57*/("""
                                                     """),format.raw/*744.54*/("""}"""),format.raw/*744.55*/(""");

                                                      $('#Breathing_Belt').change(function() """),format.raw/*746.94*/("""{"""),format.raw/*746.95*/("""
                                                     """),format.raw/*747.54*/("""if($(this).is(":checked")) """),format.raw/*747.81*/("""{"""),format.raw/*747.82*/("""

                                                       """),format.raw/*749.56*/("""var a =  parseInt($("#physiology").val()) + 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*751.56*/("""}"""),format.raw/*751.57*/("""
                                                       """),format.raw/*752.56*/("""else
                                                       """),format.raw/*753.56*/("""{"""),format.raw/*753.57*/("""
                                                           """),format.raw/*754.60*/("""var a =  parseInt($("#physiology").val()) - 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*756.56*/("""}"""),format.raw/*756.57*/("""
                                                     """),format.raw/*757.54*/("""}"""),format.raw/*757.55*/(""");

                                                      $('#Breathing_Thermal').change(function() """),format.raw/*759.97*/("""{"""),format.raw/*759.98*/("""
                                                     """),format.raw/*760.54*/("""if($(this).is(":checked")) """),format.raw/*760.81*/("""{"""),format.raw/*760.82*/("""

                                                       """),format.raw/*762.56*/("""var a =  parseInt($("#physiology").val()) + 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*764.56*/("""}"""),format.raw/*764.57*/("""
                                                       """),format.raw/*765.56*/("""else
                                                       """),format.raw/*766.56*/("""{"""),format.raw/*766.57*/("""
                                                           """),format.raw/*767.60*/("""var a =  parseInt($("#physiology").val()) - 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*769.56*/("""}"""),format.raw/*769.57*/("""
                                                     """),format.raw/*770.54*/("""}"""),format.raw/*770.55*/(""");

                                                      $('#Heart_Rate').change(function() """),format.raw/*772.90*/("""{"""),format.raw/*772.91*/("""
                                                     """),format.raw/*773.54*/("""if($(this).is(":checked")) """),format.raw/*773.81*/("""{"""),format.raw/*773.82*/("""

                                                       """),format.raw/*775.56*/("""var a =  parseInt($("#physiology").val()) + 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*777.56*/("""}"""),format.raw/*777.57*/("""
                                                       """),format.raw/*778.56*/("""else
                                                       """),format.raw/*779.56*/("""{"""),format.raw/*779.57*/("""
                                                           """),format.raw/*780.60*/("""var a =  parseInt($("#physiology").val()) - 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*782.56*/("""}"""),format.raw/*782.57*/("""
                                                     """),format.raw/*783.54*/("""}"""),format.raw/*783.55*/(""");

                                                      $('#Perspiration').change(function() """),format.raw/*785.92*/("""{"""),format.raw/*785.93*/("""
                                                     """),format.raw/*786.54*/("""if($(this).is(":checked")) """),format.raw/*786.81*/("""{"""),format.raw/*786.82*/("""

                                                       """),format.raw/*788.56*/("""var a =  parseInt($("#physiology").val()) + 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*790.56*/("""}"""),format.raw/*790.57*/("""
                                                       """),format.raw/*791.56*/("""else
                                                       """),format.raw/*792.56*/("""{"""),format.raw/*792.57*/("""
                                                           """),format.raw/*793.60*/("""var a =  parseInt($("#physiology").val()) - 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*795.56*/("""}"""),format.raw/*795.57*/("""
                                                     """),format.raw/*796.54*/("""}"""),format.raw/*796.55*/(""");

                                                 """),format.raw/*798.50*/("""}"""),format.raw/*798.51*/(""");
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
                                                $(document).ready(function()"""),format.raw/*832.77*/("""{"""),format.raw/*832.78*/("""
                                                """),format.raw/*833.49*/("""$('#Accelerometer').change(function() """),format.raw/*833.87*/("""{"""),format.raw/*833.88*/("""
                                                    """),format.raw/*834.53*/("""if($(this).is(":checked")) """),format.raw/*834.80*/("""{"""),format.raw/*834.81*/("""
                                                       """),format.raw/*835.56*/("""var a =  parseInt($("#observation").val()) + 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*837.56*/("""}"""),format.raw/*837.57*/("""
                                                       """),format.raw/*838.56*/("""else
                                                       """),format.raw/*839.56*/("""{"""),format.raw/*839.57*/("""
                                                        """),format.raw/*840.57*/("""var a =  parseInt($("#observation").val()) - 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*842.56*/("""}"""),format.raw/*842.57*/("""
                                                     """),format.raw/*843.54*/("""}"""),format.raw/*843.55*/(""");

                                                     $('#Obser_other').change(function() """),format.raw/*845.90*/("""{"""),format.raw/*845.91*/("""
                                                     """),format.raw/*846.54*/("""if($(this).is(":checked")) """),format.raw/*846.81*/("""{"""),format.raw/*846.82*/("""
                                                       """),format.raw/*847.56*/("""var a =  parseInt($("#observation").val()) + 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*849.56*/("""}"""),format.raw/*849.57*/("""
                                                       """),format.raw/*850.56*/("""else
                                                       """),format.raw/*851.56*/("""{"""),format.raw/*851.57*/("""
                                                         """),format.raw/*852.58*/("""var a =  parseInt($("#observation").val()) - 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*854.56*/("""}"""),format.raw/*854.57*/("""
                                                     """),format.raw/*855.54*/("""}"""),format.raw/*855.55*/(""");

                                                      $('#Video_Face').change(function() """),format.raw/*857.90*/("""{"""),format.raw/*857.91*/("""
                                                     """),format.raw/*858.54*/("""if($(this).is(":checked")) """),format.raw/*858.81*/("""{"""),format.raw/*858.82*/("""

                                                       """),format.raw/*860.56*/("""var a =  parseInt($("#observation").val()) + 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*862.56*/("""}"""),format.raw/*862.57*/("""
                                                       """),format.raw/*863.56*/("""else
                                                       """),format.raw/*864.56*/("""{"""),format.raw/*864.57*/("""
                                                           """),format.raw/*865.60*/("""var a =  parseInt($("#observation").val()) - 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*867.56*/("""}"""),format.raw/*867.57*/("""
                                                     """),format.raw/*868.54*/("""}"""),format.raw/*868.55*/(""");

                                                      $('#Video_Room').change(function() """),format.raw/*870.90*/("""{"""),format.raw/*870.91*/("""
                                                     """),format.raw/*871.54*/("""if($(this).is(":checked")) """),format.raw/*871.81*/("""{"""),format.raw/*871.82*/("""

                                                       """),format.raw/*873.56*/("""var a =  parseInt($("#observation").val()) + 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*875.56*/("""}"""),format.raw/*875.57*/("""
                                                       """),format.raw/*876.56*/("""else
                                                       """),format.raw/*877.56*/("""{"""),format.raw/*877.57*/("""
                                                           """),format.raw/*878.60*/("""var a =  parseInt($("#observation").val()) - 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*880.56*/("""}"""),format.raw/*880.57*/("""
                                                     """),format.raw/*881.54*/("""}"""),format.raw/*881.55*/(""");

                                                      $('#Video_Theater').change(function() """),format.raw/*883.93*/("""{"""),format.raw/*883.94*/("""
                                                     """),format.raw/*884.54*/("""if($(this).is(":checked")) """),format.raw/*884.81*/("""{"""),format.raw/*884.82*/("""

                                                       """),format.raw/*886.56*/("""var a =  parseInt($("#observation").val()) + 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*888.56*/("""}"""),format.raw/*888.57*/("""
                                                       """),format.raw/*889.56*/("""else
                                                       """),format.raw/*890.56*/("""{"""),format.raw/*890.57*/("""
                                                           """),format.raw/*891.60*/("""var a =  parseInt($("#observation").val()) - 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*893.56*/("""}"""),format.raw/*893.57*/("""
                                                     """),format.raw/*894.54*/("""}"""),format.raw/*894.55*/(""");

                                                      $('#FACS').change(function() """),format.raw/*896.84*/("""{"""),format.raw/*896.85*/("""
                                                     """),format.raw/*897.54*/("""if($(this).is(":checked")) """),format.raw/*897.81*/("""{"""),format.raw/*897.82*/("""

                                                       """),format.raw/*899.56*/("""var a =  parseInt($("#observation").val()) + 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*901.56*/("""}"""),format.raw/*901.57*/("""
                                                       """),format.raw/*902.56*/("""else
                                                       """),format.raw/*903.56*/("""{"""),format.raw/*903.57*/("""
                                                           """),format.raw/*904.60*/("""var a =  parseInt($("#observation").val()) - 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*906.56*/("""}"""),format.raw/*906.57*/("""
                                                     """),format.raw/*907.54*/("""}"""),format.raw/*907.55*/(""");

                                                      $('#Obser2d_other').change(function() """),format.raw/*909.93*/("""{"""),format.raw/*909.94*/("""
                                                     """),format.raw/*910.54*/("""if($(this).is(":checked")) """),format.raw/*910.81*/("""{"""),format.raw/*910.82*/("""

                                                       """),format.raw/*912.56*/("""var a =  parseInt($("#observation").val()) + 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*914.56*/("""}"""),format.raw/*914.57*/("""
                                                       """),format.raw/*915.56*/("""else
                                                       """),format.raw/*916.56*/("""{"""),format.raw/*916.57*/("""
                                                           """),format.raw/*917.60*/("""var a =  parseInt($("#observation").val()) - 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*919.56*/("""}"""),format.raw/*919.57*/("""
                                                     """),format.raw/*920.54*/("""}"""),format.raw/*920.55*/(""");

                                                 """),format.raw/*922.50*/("""}"""),format.raw/*922.51*/(""");
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
    """)))}),format.raw/*941.6*/("""
    """),format.raw/*942.5*/("""</form>

    </div>



</div><!--/.main-->

 <div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
     <p> Please wait, study is being created </p>
 </div>

<script>
    var wsUri = """"),_display_(/*955.19*/routes/*955.25*/.Application.socket.webSocketURL()),format.raw/*955.59*/("""";
    var userName = """"),_display_(/*956.22*/userName),format.raw/*956.30*/(""""
</script>



</body>

</html>
"""))}
  }

  def render(userName:String,explVar:List[scala.Tuple3[Option[String], Int, Int]],request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(userName,explVar)(request)

  def f:((String,List[scala.Tuple3[Option[String], Int, Int]]) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (userName,explVar) => (request) => apply(userName,explVar)(request)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Nov 22 14:18:43 CST 2016
                  SOURCE: C:/first_play/app/views/ShowCreateStudy.scala.html
                  HASH: b36d6d17740191fa97a96c0898a8787507f07e38
                  MATRIX: 574->1|772->96|800->116|909->198|937->199|979->213|1268->475|1297->476|1336->488|2532->1657|2547->1663|2612->1707|2693->1761|2708->1767|2773->1811|4023->3033|4053->3041|4083->3042|6085->5017|6101->5023|6214->5125|6256->5127|6294->5137|12369->11184|12421->11226|12462->11228|12517->11254|12704->11409|12755->11431|15957->14605|15994->14625|16034->14626|16093->14657|16120->14674|16161->14676|16221->14707|16273->14731|16287->14735|16312->14738|16343->14740|16394->14763|16408->14767|16433->14770|16463->14771|16535->14811|16598->14842|16653->14868|17032->15219|17069->15239|17109->15240|17168->15271|17195->15288|17236->15290|17295->15320|17347->15344|17361->15348|17386->15351|17417->15353|17468->15376|17482->15380|17507->15383|17537->15384|17609->15424|17672->15455|17727->15481|18346->16072|18383->16092|18423->16093|18490->16132|18517->16149|18558->16151|18625->16189|18697->16233|18711->16237|18736->16240|18812->16287|18827->16291|18853->16294|18934->16343|19005->16382|19068->16416|19465->16785|19502->16805|19542->16806|19601->16837|19627->16853|19668->16855|19727->16885|19780->16910|19794->16914|19819->16917|19852->16922|19866->16926|19891->16929|19963->16969|20026->17000|20085->17030|20483->17400|20520->17420|20560->17421|20619->17452|20645->17468|20686->17470|20745->17500|20798->17525|20812->17529|20837->17532|20871->17538|20885->17542|20910->17545|20982->17585|21045->17616|21104->17646|22038->18552|22075->18572|22115->18573|22178->18608|22204->18624|22245->18626|22308->18660|22360->18684|22374->18688|22399->18691|22430->18693|22481->18716|22495->18720|22520->18723|22550->18724|22626->18768|22689->18799|22746->18827|23593->19646|23630->19666|23670->19667|23737->19706|23764->19723|23805->19725|23872->19763|23946->19809|23960->19813|23985->19816|24061->19863|24076->19867|24102->19870|24182->19918|24253->19957|24316->19991|25265->20912|25302->20932|25342->20933|25401->20964|25428->20981|25469->20983|25528->21013|25580->21037|25594->21041|25619->21044|25650->21046|25701->21069|25715->21073|25740->21076|25812->21116|25875->21147|25934->21177|27108->22322|27138->22323|27189->22345|27251->22378|27281->22379|27332->22401|27388->22428|27418->22429|27476->22458|27554->22507|27584->22508|27642->22537|27704->22570|27734->22571|27793->22601|27871->22650|27901->22651|27957->22678|27987->22679|28078->22741|28108->22742|28159->22764|28215->22791|28245->22792|28303->22821|28383->22872|28413->22873|28471->22902|28533->22935|28563->22936|28622->22966|28702->23017|28732->23018|28788->23045|28818->23046|28899->23098|28929->23099|28984->23125|29040->23152|29070->23153|29128->23182|29280->23305|29310->23306|29368->23335|29430->23368|29460->23369|29519->23399|29671->23522|29701->23523|29757->23550|29787->23551|29878->23613|29908->23614|29964->23641|30020->23668|30050->23669|30110->23700|30263->23824|30293->23825|30351->23854|30413->23887|30443->23888|30503->23919|30656->24043|30686->24044|30742->24071|30772->24072|30864->24135|30894->24136|30950->24163|31006->24190|31036->24191|31096->24222|31250->24347|31280->24348|31338->24377|31400->24410|31430->24411|31492->24444|31646->24569|31676->24570|31732->24597|31762->24598|31854->24661|31884->24662|31940->24689|31996->24716|32026->24717|32086->24748|32241->24874|32271->24875|32329->24904|32391->24937|32421->24938|32483->24971|32638->25097|32668->25098|32724->25125|32754->25126|32849->25192|32879->25193|32935->25220|32991->25247|33021->25248|33081->25279|33237->25406|33267->25407|33325->25436|33387->25469|33417->25470|33479->25503|33635->25630|33665->25631|33721->25658|33751->25659|33846->25725|33876->25726|33932->25753|33988->25780|34018->25781|34078->25812|34235->25940|34265->25941|34323->25970|34385->26003|34415->26004|34477->26037|34634->26165|34664->26166|34720->26193|34750->26194|34806->26221|34836->26222|36609->27966|36639->27967|36722->28021|36779->28049|36809->28050|36892->28104|36948->28131|36978->28132|37064->28189|37292->28388|37322->28389|37408->28446|37498->28507|37528->28508|37615->28566|37843->28765|37873->28766|37957->28821|37987->28822|38103->28909|38133->28910|38217->28965|38273->28992|38303->28993|38389->29050|38618->29250|38648->29251|38734->29308|38824->29369|38854->29370|38942->29429|39171->29629|39201->29630|39285->29685|39315->29686|39431->29773|39461->29774|39545->29829|39601->29856|39631->29857|39719->29916|39949->30117|39979->30118|40065->30175|40155->30236|40185->30237|40275->30298|40505->30499|40535->30500|40619->30555|40649->30556|40765->30643|40795->30644|40879->30699|40935->30726|40965->30727|41053->30786|41284->30988|41314->30989|41400->31046|41490->31107|41520->31108|41610->31169|41841->31371|41871->31372|41955->31427|41985->31428|42110->31524|42140->31525|42224->31580|42280->31607|42310->31608|42398->31667|42630->31870|42660->31871|42746->31928|42836->31989|42866->31990|42956->32051|43188->32254|43218->32255|43302->32310|43332->32311|43416->32366|43446->32367|45708->34600|45738->34601|45817->34651|45874->34679|45904->34680|45987->34734|46043->34761|46073->34762|46159->34819|46383->35014|46413->35015|46499->35072|46589->35133|46619->35134|46706->35192|46930->35387|46960->35388|47044->35443|47074->35444|47193->35534|47223->35535|47307->35590|47363->35617|47393->35618|47479->35675|47704->35871|47734->35872|47820->35929|47910->35990|47940->35991|48028->36050|48253->36246|48283->36247|48367->36302|48397->36303|48521->36398|48551->36399|48635->36454|48691->36481|48721->36482|48809->36541|49035->36738|49065->36739|49151->36796|49241->36857|49271->36858|49361->36919|49587->37116|49617->37117|49701->37172|49731->37173|49859->37272|49889->37273|49973->37328|50029->37355|50059->37356|50147->37415|50374->37613|50404->37614|50490->37671|50580->37732|50610->37733|50700->37794|50927->37992|50957->37993|51041->38048|51071->38049|51202->38151|51232->38152|51316->38207|51372->38234|51402->38235|51490->38294|51718->38493|51748->38494|51834->38551|51924->38612|51954->38613|52044->38674|52272->38873|52302->38874|52386->38929|52416->38930|52540->39025|52570->39026|52654->39081|52710->39108|52740->39109|52828->39168|53057->39368|53087->39369|53173->39426|53263->39487|53293->39488|53383->39549|53612->39749|53642->39750|53726->39805|53756->39806|53882->39903|53912->39904|53996->39959|54052->39986|54082->39987|54170->40046|54400->40247|54430->40248|54516->40305|54606->40366|54636->40367|54726->40428|54956->40629|54986->40630|55070->40685|55100->40686|55184->40741|55214->40742|57316->42815|57346->42816|57425->42866|57492->42904|57522->42905|57605->42959|57661->42986|57691->42987|57777->43044|58003->43241|58033->43242|58119->43299|58209->43360|58239->43361|58326->43419|58552->43616|58582->43617|58666->43672|58696->43673|58820->43768|58850->43769|58934->43824|58990->43851|59020->43852|59106->43909|59333->44107|59363->44108|59449->44165|59539->44226|59569->44227|59657->44286|59884->44484|59914->44485|59998->44540|60028->44541|60152->44636|60182->44637|60266->44692|60322->44719|60352->44720|60440->44779|60668->44978|60698->44979|60784->45036|60874->45097|60904->45098|60994->45159|61222->45358|61252->45359|61336->45414|61366->45415|61490->45510|61520->45511|61604->45566|61660->45593|61690->45594|61778->45653|62007->45853|62037->45854|62123->45911|62213->45972|62243->45973|62333->46034|62562->46234|62592->46235|62676->46290|62706->46291|62833->46389|62863->46390|62947->46445|63003->46472|63033->46473|63121->46532|63351->46733|63381->46734|63467->46791|63557->46852|63587->46853|63677->46914|63907->47115|63937->47116|64021->47171|64051->47172|64169->47261|64199->47262|64283->47317|64339->47344|64369->47345|64457->47404|64688->47606|64718->47607|64804->47664|64894->47725|64924->47726|65014->47787|65245->47989|65275->47990|65359->48045|65389->48046|65516->48144|65546->48145|65630->48200|65686->48227|65716->48228|65804->48287|66036->48490|66066->48491|66152->48548|66242->48609|66272->48610|66362->48671|66594->48874|66624->48875|66708->48930|66738->48931|66822->48986|66852->48987|67966->50070|68000->50076|68352->50400|68368->50406|68424->50440|68477->50465|68507->50473
                  LINES: 19->1|22->1|23->3|29->9|29->9|30->10|38->18|38->18|42->22|64->44|64->44|64->44|65->45|65->45|65->45|86->66|86->66|86->66|141->121|141->121|141->121|141->121|142->122|287->267|287->267|287->267|288->268|289->269|290->270|351->331|351->331|351->331|352->332|352->332|352->332|353->333|353->333|353->333|353->333|353->333|353->333|353->333|353->333|353->333|354->334|355->335|356->336|364->344|364->344|364->344|365->345|365->345|365->345|366->346|366->346|366->346|366->346|366->346|366->346|366->346|366->346|366->346|367->347|368->348|369->349|380->360|380->360|380->360|381->361|381->361|381->361|382->362|382->362|382->362|382->362|382->362|382->362|382->362|383->363|384->364|385->365|397->377|397->377|397->377|398->378|398->378|398->378|399->379|399->379|399->379|399->379|399->379|399->379|399->379|400->380|401->381|402->382|412->392|412->392|412->392|413->393|413->393|413->393|414->394|414->394|414->394|414->394|414->394|414->394|414->394|415->395|416->396|417->397|436->416|436->416|436->416|437->417|437->417|437->417|438->418|438->418|438->418|438->418|438->418|438->418|438->418|438->418|438->418|439->419|440->420|442->422|456->436|456->436|456->436|457->437|457->437|457->437|458->438|458->438|458->438|458->438|458->438|458->438|458->438|459->439|460->440|461->441|485->465|485->465|485->465|486->466|486->466|486->466|487->467|487->467|487->467|487->467|487->467|487->467|487->467|487->467|488->468|489->469|490->470|509->489|509->489|510->490|510->490|510->490|511->491|511->491|511->491|512->492|513->493|513->493|514->494|515->495|515->495|516->496|517->497|517->497|518->498|518->498|519->499|519->499|520->500|520->500|520->500|521->501|522->502|522->502|523->503|524->504|524->504|525->505|526->506|526->506|527->507|527->507|528->508|528->508|529->509|529->509|529->509|530->510|532->512|532->512|533->513|534->514|534->514|535->515|537->517|537->517|538->518|538->518|540->520|540->520|541->521|541->521|541->521|542->522|544->524|544->524|545->525|546->526|546->526|547->527|549->529|549->529|550->530|550->530|552->532|552->532|553->533|553->533|553->533|555->535|557->537|557->537|558->538|559->539|559->539|560->540|562->542|562->542|563->543|563->543|565->545|565->545|566->546|566->546|566->546|568->548|570->550|570->550|571->551|572->552|572->552|573->553|575->555|575->555|576->556|576->556|578->558|578->558|579->559|579->559|579->559|581->561|583->563|583->563|584->564|585->565|585->565|586->566|588->568|588->568|589->569|589->569|591->571|591->571|592->572|592->572|592->572|594->574|596->576|596->576|597->577|598->578|598->578|599->579|601->581|601->581|602->582|602->582|604->584|604->584|633->613|633->613|634->614|634->614|634->614|635->615|635->615|635->615|636->616|638->618|638->618|639->619|640->620|640->620|641->621|643->623|643->623|644->624|644->624|646->626|646->626|647->627|647->627|647->627|648->628|650->630|650->630|651->631|652->632|652->632|653->633|655->635|655->635|656->636|656->636|658->638|658->638|659->639|659->639|659->639|661->641|663->643|663->643|664->644|665->645|665->645|666->646|668->648|668->648|669->649|669->649|671->651|671->651|672->652|672->652|672->652|674->654|676->656|676->656|677->657|678->658|678->658|679->659|681->661|681->661|682->662|682->662|684->664|684->664|685->665|685->665|685->665|687->667|689->669|689->669|690->670|691->671|691->671|692->672|694->674|694->674|695->675|695->675|697->677|697->677|728->708|728->708|729->709|729->709|729->709|730->710|730->710|730->710|731->711|733->713|733->713|734->714|735->715|735->715|736->716|738->718|738->718|739->719|739->719|741->721|741->721|742->722|742->722|742->722|743->723|745->725|745->725|746->726|747->727|747->727|748->728|750->730|750->730|751->731|751->731|753->733|753->733|754->734|754->734|754->734|756->736|758->738|758->738|759->739|760->740|760->740|761->741|763->743|763->743|764->744|764->744|766->746|766->746|767->747|767->747|767->747|769->749|771->751|771->751|772->752|773->753|773->753|774->754|776->756|776->756|777->757|777->757|779->759|779->759|780->760|780->760|780->760|782->762|784->764|784->764|785->765|786->766|786->766|787->767|789->769|789->769|790->770|790->770|792->772|792->772|793->773|793->773|793->773|795->775|797->777|797->777|798->778|799->779|799->779|800->780|802->782|802->782|803->783|803->783|805->785|805->785|806->786|806->786|806->786|808->788|810->790|810->790|811->791|812->792|812->792|813->793|815->795|815->795|816->796|816->796|818->798|818->798|852->832|852->832|853->833|853->833|853->833|854->834|854->834|854->834|855->835|857->837|857->837|858->838|859->839|859->839|860->840|862->842|862->842|863->843|863->843|865->845|865->845|866->846|866->846|866->846|867->847|869->849|869->849|870->850|871->851|871->851|872->852|874->854|874->854|875->855|875->855|877->857|877->857|878->858|878->858|878->858|880->860|882->862|882->862|883->863|884->864|884->864|885->865|887->867|887->867|888->868|888->868|890->870|890->870|891->871|891->871|891->871|893->873|895->875|895->875|896->876|897->877|897->877|898->878|900->880|900->880|901->881|901->881|903->883|903->883|904->884|904->884|904->884|906->886|908->888|908->888|909->889|910->890|910->890|911->891|913->893|913->893|914->894|914->894|916->896|916->896|917->897|917->897|917->897|919->899|921->901|921->901|922->902|923->903|923->903|924->904|926->906|926->906|927->907|927->907|929->909|929->909|930->910|930->910|930->910|932->912|934->914|934->914|935->915|936->916|936->916|937->917|939->919|939->919|940->920|940->920|942->922|942->922|961->941|962->942|975->955|975->955|975->955|976->956|976->956
                  -- GENERATED --
              */
          