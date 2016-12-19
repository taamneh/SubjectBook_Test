
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
object CreateStudy extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.20*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>StressBook</title>
    <meta content="width=1170" name="viewport"><meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta content="IRQcweD5CIs6Qc-uVi9xT3BD-1jPvDxyk3-9LUah9T8" name="google-site-verification">
    <link href="/assets/images/cpllogo.png" rel="icon" type="image/png">
    <script type="text/javascript" src="/assets/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/assets/js/ow-all.js"></script>
    <script type="text/javascript" src="/assets/js/webapp.js"></script>
    <script type="text/javascript" src="/assets/js/browser-features.js"></script>
    <script type="text/javascript" src="/assets/js/common.js"></script>
    <script type="text/javascript" src="/assets/js/notifications.js"></script>
    <script type="text/javascript" src="/assets/js/models.js"></script>
    <script type="text/javascript" src="/assets/js/my_models.js"></script>
    <script type="text/javascript" src="/assets/js/showPicker.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb-salah.css">
   <!-- <link rel="stylesheet" type="text/css" href="/assets/stylesheets/dbwm.css"> -->
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/multi.css">

</head>
<body>
<div id="wrapper">
<div id="header_wrapper">
    <div id="header">
        <a class="logo" href="http://www.cpl.uh.edu/"><img alt="CPL Logo" width="70" height="40"  src="/assets/images/cpllogo.png"></a>
        <ul class="user_menu">
            <li class="user dropdown_holder"><span class="dropdown"><span>"""),_display_(/*32.76*/userName),format.raw/*32.84*/("""</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="" id="btn31"><span>My account</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.312*/("""{"""),format.raw/*32.313*/("""epoint.ow.Utils.bindEvent("click","btn31","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*32.401*/("""}"""),format.raw/*32.402*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.507*/("""{"""),format.raw/*32.508*/("""epoint.ow.Utils.bindEvent("click","btn31","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*32.599*/("""}"""),format.raw/*32.600*/(""",null,null);</script></li></ul></div></div></li><li class="notifications"><div id="flow_notifications_layer_div"><div class="menu_item dropdown_holder"><a class="dropdown notifications" href="" id="btn32"><span></span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.908*/("""{"""),format.raw/*32.909*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*32.997*/("""}"""),format.raw/*32.998*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.1103*/("""{"""),format.raw/*32.1104*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*32.1191*/("""}"""),format.raw/*32.1192*/(""",null,null);</script></div> <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.1311*/("""{"""),format.raw/*32.1312*/("""pl.epoint.dbwm.war_user.notifications.startNotificationsLayerRefresh("?action=flow.transit&flow.flow=notifications_layer&flow.transition=refresh&flow.reloadFlow=notifications_layer&flow.disableMessagesAddons=true");"""),format.raw/*32.1527*/("""}"""),format.raw/*32.1528*/(""",null,null);</script> --> </div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.1646*/("""{"""),format.raw/*32.1647*/("""epoint.ow.flow.Utils.setCurrentState("notifications_layer","collapsed");"""),format.raw/*32.1719*/("""}"""),format.raw/*32.1720*/(""",null,null);</script></li><li class="logout"><a href="/logout" id="btn33"><span>Log out</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.1904*/("""{"""),format.raw/*32.1905*/("""epoint.ow.Utils.bindEvent("click","btn33","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*32.1993*/("""}"""),format.raw/*32.1994*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*32.2099*/("""{"""),format.raw/*32.2100*/("""epoint.ow.Utils.bindEvent("click","btn33","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*32.2191*/("""}"""),format.raw/*32.2192*/(""",null,null);</script></li>
        </ul>
        <ul class="links">
            <li class="dropdown_holder expandable expand-priority-1"><span class="dropdown"><span>Modules</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/diagram">Diagram</a></li><li><a href="/allStudies" id="btn34"><span>My models</span></a></li><li><a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn35"><span>Sharing</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*35.492*/("""{"""),format.raw/*35.493*/("""epoint.ow.Utils.bindEvent("click","btn35","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*35.581*/("""}"""),format.raw/*35.582*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*35.687*/("""{"""),format.raw/*35.688*/("""epoint.ow.Utils.bindEvent("click","btn35","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*35.779*/("""}"""),format.raw/*35.780*/(""",null,null);</script></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn36"><span>Recommend us</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*35.1005*/("""{"""),format.raw/*35.1006*/("""epoint.ow.Utils.bindEvent("click","btn36","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*35.1094*/("""}"""),format.raw/*35.1095*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*35.1200*/("""{"""),format.raw/*35.1201*/("""epoint.ow.Utils.bindEvent("click","btn36","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*35.1292*/("""}"""),format.raw/*35.1293*/(""",null,null);</script></li></ul></div></div></li><li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span>
            <div class="dropdown_menu_holder">
                <div class="dropdown_menu">
                    <ul class="m">
                        <li>
                            <a href="javascript: IntroGuide.start();" id="take-application-tour-menu-item" style="display: none;">Take application tour</a>
                        </li>
                        <li id="shortcut-helper-menu-item" style="display: none;">
                            <a href="javascript: app.showShortcutsHelper();">Keyboard shortcuts</a>
                        </li>
                        <li>
                            <a target="_blank" href="http://vertabelo.com">Vertabelo website</a>
                        </li>
                        <li>
                            <a target="_blank" href="http://vertabelo.com/documentation">Documentation</a>
                        </li>
                        <li>
                            <a target="_blank" href="http://vertabelo.com/faq">FAQ</a>
                        </li>
                        <li>
                            <a href="/support/ask-a-question" id="btn37" target="_blank"><span>Ask a question</span></a>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
									"""),format.raw/*57.10*/("""{"""),format.raw/*57.11*/("""epoint.ow.Utils.bindEvent("click","btn37","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*57.99*/("""}"""),format.raw/*57.100*/(""",90,null);
								</script>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
									"""),format.raw/*60.10*/("""{"""),format.raw/*60.11*/("""epoint.ow.Utils.bindEvent("click","btn37","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*60.102*/("""}"""),format.raw/*60.103*/(""",null,null);
								</script>
                        </li>
                        <li>
                            <a href="/support/request-a-feature" id="btn38" target="_blank"><span>Request a feature</span></a>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								   """),format.raw/*66.12*/("""{"""),format.raw/*66.13*/("""epoint.ow.Utils.bindEvent("click","btn38","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*66.101*/("""}"""),format.raw/*66.102*/(""",90,null);
								</script>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								   """),format.raw/*69.12*/("""{"""),format.raw/*69.13*/("""epoint.ow.Utils.bindEvent("click","btn38","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*69.104*/("""}"""),format.raw/*69.105*/(""",null,null);
								</script>
                        </li>
                        <li>
                            <a href="/support/report-a-problem" id="btn39" target="_blank"><span>Report a problem</span></a>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								"""),format.raw/*75.9*/("""{"""),format.raw/*75.10*/("""epoint.ow.Utils.bindEvent("click","btn39","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*75.98*/("""}"""),format.raw/*75.99*/(""",90,null);
								</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								"""),format.raw/*77.9*/("""{"""),format.raw/*77.10*/("""epoint.ow.Utils.bindEvent("click","btn39","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*77.101*/("""}"""),format.raw/*77.102*/(""",null,null);
								</script>
                        </li>
                    </ul>
                    <div class="clearFix">
                    </div>
                </div>
            </div>
            </li>
        </ul>
    </div>


    <div class="clearFix">
    </div>
</div>
<div id="content_wrapper" style="height: 1000;">
    <div id="content" style="height: 800;">
        <div id="flow_models_div">
            <div class="box_pre_wrapper">
                <div class="box_wrapper" style="height: 800;">
                    <div class="box" style="height: 800;">
                        <div class="box_inner" style="height: 800;">
                            <div class="header"><h1>Create New Study</h1></div>
                            <!-- <div id= "newStudy"> -->
                            <div class ="showontop" >
                            <form calss="salah" id="msform" method="post">
                                <!-- progressbar -->
                                """),_display_(/*105.34*/helper/*105.40*/.form(action = routes.Application.InsertNewStudy, 'enctype -> "multipart/form-data")/*105.124*/ {_display_(Seq[Any](format.raw/*105.126*/("""
                                """),format.raw/*106.33*/("""<ul class="taa" id="progressbar1">
                                    <li class="active">Data Source</li>
                                    <li>Biography Information</li>
                                    <li>Psychometrics</li>
                                    <li>Physiological </li>
                                    <li>Observational </li>
                                    <li>Performance </li>
                                </ul>
                                <!-- fieldsets -->

                                <fieldset>
                                    <h2 class="fs-title">Specify your data source</h2>
                                    <h3 class="fs-subtitle"></h3>
                                    <table cellspacing="0" class="data_table" id="add_sessions_table">
                                        <tr>
                                            <td class="cell_1">* Source type:</td>
                                            <td>
                                                <select class="select_field"  id="sourcetype" name="study_type">
                                                    <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value=1>Google Drive</option>
                                                    <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_2" value=2>CPL Server</option>
                                                </select>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="cell_1">* Study name:</td>
                                            <td>
                                                <input class="text_field" size="200"  type="text" id="study_name" maxlength="50" name="study_name" prompttext="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="cell_1">* URL:</td>
                                            <td>
                                                <input class="text_field" size="200"  type="text" id="folder_name" maxlength="200" name="url" prompttext="">
                                                <input class="text_field" size="200"  type="hidden" id="folder_id" maxlength="50" name="folder_id" value="x">
                                            </td>
                                            <td>
                                                <input type="button" name="Add" id="browseGD"class="" value="...." />
                                            </td>
                                            <script type="text/javascript" src="https://apis.google.com/js/api.js?onload=onApiLoad"></script>
                                        </tr>
                                        <tr>
                                            <td class="cell_1">* Number of subjects:</td>
                                            <td>
                                                <input type="number" class="text_field" name="numSubj" min="1" max="100" value = 1>
                                            </td>
                                            </tr>
                                        <tr>
                                            <td class="cell_1">* Sessions per subject:</td>
                                            <td>
                                                <input type="number" class="text_field" name="numSess" min="0" max="100" value = 1>
                                            </td>
                                            </tr>
                                        <!-- <tr>
                                            <td class="cell_1"> Runs per session:</td>
                                            <td> -->
                                                <input type="hidden" class="text_field" name="numRun" min="1" max="100" value = 1>
                                            <!-- </td> -->

                                        <!-- </tr>  -->
                                        </table>
                                    <input id ="firstNext"  type="button" name="next" class="next action-button" value="Next" />




                                </fieldset>
                                <fieldset>
                                    <h2 class="fs-title">Biography</h2>
                                    <h3 class="fs-subtitle"></h3>

                                    <table cellspacing="0" class="data_table" id="add_sessions_table">
                                        <tr>
                                            <td><input type="checkbox" id="Age" name="Age" value=1 style="width: 20px">Age&nbsp;&nbsp;&nbsp;&nbsp;<br> </td>
                                            <td><input type="checkbox" id="Height" name="Height" value=10 style="width: 20px">Height&nbsp;&nbsp;&nbsp;<br> </td>
                                            <td><input type="checkbox" id="Weight" name="Weight" value=100 style="width: 20px">Weight&nbsp;&nbsp;&nbsp;<br> </td>
                                            <td><input type="checkbox" id="Gender" name="Gender" value=1000 style="width: 20px">Gender&nbsp;&nbsp;&nbsp;<br> </td>
                                            <td><input type="checkbox" id="Ethnicity" name="Ethnicity" value=10000 style="width: 20px">Ethnicity<br> </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" id="Bio_other"  name="Bio_other" value=100000 style="width: 20px">Other&nbsp;<br> </td>

                                        </tr>
                                        </table>
                                    <script>
                                                $(document).ready(function()"""),format.raw/*191.77*/("""{"""),format.raw/*191.78*/("""
                                                """),format.raw/*192.49*/("""$('#Age').change(function() """),format.raw/*192.77*/("""{"""),format.raw/*192.78*/("""
                                                    """),format.raw/*193.53*/("""if($(this).is(":checked")) """),format.raw/*193.80*/("""{"""),format.raw/*193.81*/("""
                                                       """),format.raw/*194.56*/("""var a =  parseInt($("#ir").val()) + 1
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*196.56*/("""}"""),format.raw/*196.57*/("""
                                                       """),format.raw/*197.56*/("""else
                                                       """),format.raw/*198.56*/("""{"""),format.raw/*198.57*/("""
                                                        """),format.raw/*199.57*/("""var a =  parseInt($("#ir").val()) - 1
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*201.56*/("""}"""),format.raw/*201.57*/("""
                                                     """),format.raw/*202.54*/("""}"""),format.raw/*202.55*/(""");

                                                     $('#Height').change(function() """),format.raw/*204.85*/("""{"""),format.raw/*204.86*/("""
                                                     """),format.raw/*205.54*/("""if($(this).is(":checked")) """),format.raw/*205.81*/("""{"""),format.raw/*205.82*/("""
                                                         """),format.raw/*206.58*/("""var a =  parseInt($("#ir").val()) + 10
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*208.56*/("""}"""),format.raw/*208.57*/("""
                                                       """),format.raw/*209.56*/("""else
                                                       """),format.raw/*210.56*/("""{"""),format.raw/*210.57*/("""
                                                         """),format.raw/*211.58*/("""var a =  parseInt($("#ir").val()) - 10
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*213.56*/("""}"""),format.raw/*213.57*/("""
                                                     """),format.raw/*214.54*/("""}"""),format.raw/*214.55*/(""");

                                                      $('#Weight').change(function() """),format.raw/*216.86*/("""{"""),format.raw/*216.87*/("""
                                                     """),format.raw/*217.54*/("""if($(this).is(":checked")) """),format.raw/*217.81*/("""{"""),format.raw/*217.82*/("""

                                                       """),format.raw/*219.56*/("""var a =  parseInt($("#ir").val()) + 100
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*221.56*/("""}"""),format.raw/*221.57*/("""
                                                       """),format.raw/*222.56*/("""else
                                                       """),format.raw/*223.56*/("""{"""),format.raw/*223.57*/("""
                                                           """),format.raw/*224.60*/("""var a =  parseInt($("#ir").val()) - 100
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*226.56*/("""}"""),format.raw/*226.57*/("""
                                                     """),format.raw/*227.54*/("""}"""),format.raw/*227.55*/(""");

                                                      $('#Gender').change(function() """),format.raw/*229.86*/("""{"""),format.raw/*229.87*/("""
                                                     """),format.raw/*230.54*/("""if($(this).is(":checked")) """),format.raw/*230.81*/("""{"""),format.raw/*230.82*/("""

                                                       """),format.raw/*232.56*/("""var a =  parseInt($("#ir").val()) + 1000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*234.56*/("""}"""),format.raw/*234.57*/("""
                                                       """),format.raw/*235.56*/("""else
                                                       """),format.raw/*236.56*/("""{"""),format.raw/*236.57*/("""
                                                           """),format.raw/*237.60*/("""var a =  parseInt($("#ir").val()) - 1000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*239.56*/("""}"""),format.raw/*239.57*/("""
                                                     """),format.raw/*240.54*/("""}"""),format.raw/*240.55*/(""");

                                                      $('#Ethnicity').change(function() """),format.raw/*242.89*/("""{"""),format.raw/*242.90*/("""
                                                     """),format.raw/*243.54*/("""if($(this).is(":checked")) """),format.raw/*243.81*/("""{"""),format.raw/*243.82*/("""

                                                       """),format.raw/*245.56*/("""var a =  parseInt($("#ir").val()) + 10000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*247.56*/("""}"""),format.raw/*247.57*/("""
                                                       """),format.raw/*248.56*/("""else
                                                       """),format.raw/*249.56*/("""{"""),format.raw/*249.57*/("""
                                                           """),format.raw/*250.60*/("""var a =  parseInt($("#ir").val()) - 10000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*252.56*/("""}"""),format.raw/*252.57*/("""
                                                     """),format.raw/*253.54*/("""}"""),format.raw/*253.55*/(""");

                                                      $('#Bio_other').change(function() """),format.raw/*255.89*/("""{"""),format.raw/*255.90*/("""
                                                     """),format.raw/*256.54*/("""if($(this).is(":checked")) """),format.raw/*256.81*/("""{"""),format.raw/*256.82*/("""

                                                       """),format.raw/*258.56*/("""var a =  parseInt($("#ir").val()) + 100000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*260.56*/("""}"""),format.raw/*260.57*/("""
                                                       """),format.raw/*261.56*/("""else
                                                       """),format.raw/*262.56*/("""{"""),format.raw/*262.57*/("""
                                                           """),format.raw/*263.60*/("""var a =  parseInt($("#ir").val()) - 100000
                                                       $("#ir").val(a.toString() );
                                                       """),format.raw/*265.56*/("""}"""),format.raw/*265.57*/("""
                                                     """),format.raw/*266.54*/("""}"""),format.raw/*266.55*/(""");

                                                 """),format.raw/*268.50*/("""}"""),format.raw/*268.51*/(""");
                                    </script>
                                    <input type="hidden" name="bio" id='ir' value=0>

                                    <input type="button" name="previous" class="previous action-button" value="Previous" />
                                    <input type="button" name="next" class="next action-button" value="Next" />
                                </fieldset>
                                <fieldset>
                                    <h2 class="fs-title">Psychometrics</h2>
                                    <h3 class="fs-subtitle"></h3>
                                    <table cellspacing="0" class="data_table" id="add_sessions_table">
                                        <tr>
                                            <td class="cell_1"> State-Trait Anxiety Inventory: </td>
                                            <td class="cell_2"><input type="checkbox" id="SAI" name="SAI" value=1 style="width: 20px">SAI&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                            <!--<td class="cell_2"><input type="checkbox" id="PA" name="PA" value=10 style="width: 20px">PA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td> -->
                                            <!-- <td class="cell_2"><input type="checkbox" id="NA" name="NA" value=100 style="width: 20px">NA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
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
                                                $(document).ready(function()"""),format.raw/*296.77*/("""{"""),format.raw/*296.78*/("""
                                                    """),format.raw/*297.53*/("""$('#SAI').change(function() """),format.raw/*297.81*/("""{"""),format.raw/*297.82*/("""
                                                    """),format.raw/*298.53*/("""if($(this).is(":checked")) """),format.raw/*298.80*/("""{"""),format.raw/*298.81*/("""
                                                       """),format.raw/*299.56*/("""var a =  parseInt($("#Psychometric").val()) + 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*301.56*/("""}"""),format.raw/*301.57*/("""
                                                       """),format.raw/*302.56*/("""else
                                                       """),format.raw/*303.56*/("""{"""),format.raw/*303.57*/("""
                                                        """),format.raw/*304.57*/("""var a =  parseInt($("#Psychometric").val()) - 1
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*306.56*/("""}"""),format.raw/*306.57*/("""
                                                     """),format.raw/*307.54*/("""}"""),format.raw/*307.55*/(""");

                                                     $('#TAI').change(function() """),format.raw/*309.82*/("""{"""),format.raw/*309.83*/("""
                                                     """),format.raw/*310.54*/("""if($(this).is(":checked")) """),format.raw/*310.81*/("""{"""),format.raw/*310.82*/("""
                                                       """),format.raw/*311.56*/("""var a =  parseInt($("#Psychometric").val()) + 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*313.56*/("""}"""),format.raw/*313.57*/("""
                                                       """),format.raw/*314.56*/("""else
                                                       """),format.raw/*315.56*/("""{"""),format.raw/*315.57*/("""
                                                         """),format.raw/*316.58*/("""var a =  parseInt($("#Psychometric").val()) - 10
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*318.56*/("""}"""),format.raw/*318.57*/("""
                                                     """),format.raw/*319.54*/("""}"""),format.raw/*319.55*/(""");

                                                      $('#PA').change(function() """),format.raw/*321.82*/("""{"""),format.raw/*321.83*/("""
                                                     """),format.raw/*322.54*/("""if($(this).is(":checked")) """),format.raw/*322.81*/("""{"""),format.raw/*322.82*/("""

                                                       """),format.raw/*324.56*/("""var a =  parseInt($("#Psychometric").val()) + 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*326.56*/("""}"""),format.raw/*326.57*/("""
                                                       """),format.raw/*327.56*/("""else
                                                       """),format.raw/*328.56*/("""{"""),format.raw/*328.57*/("""
                                                           """),format.raw/*329.60*/("""var a =  parseInt($("#Psychometric").val()) - 100
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*331.56*/("""}"""),format.raw/*331.57*/("""
                                                     """),format.raw/*332.54*/("""}"""),format.raw/*332.55*/(""");

                                                      $('#NA').change(function() """),format.raw/*334.82*/("""{"""),format.raw/*334.83*/("""
                                                     """),format.raw/*335.54*/("""if($(this).is(":checked")) """),format.raw/*335.81*/("""{"""),format.raw/*335.82*/("""

                                                       """),format.raw/*337.56*/("""var a =  parseInt($("#Psychometric").val()) + 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*339.56*/("""}"""),format.raw/*339.57*/("""
                                                       """),format.raw/*340.56*/("""else
                                                       """),format.raw/*341.56*/("""{"""),format.raw/*341.57*/("""
                                                           """),format.raw/*342.60*/("""var a =  parseInt($("#Psychometric").val()) - 1000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*344.56*/("""}"""),format.raw/*344.57*/("""
                                                     """),format.raw/*345.54*/("""}"""),format.raw/*345.55*/(""");

                                                      $('#Trait_Other').change(function() """),format.raw/*347.91*/("""{"""),format.raw/*347.92*/("""
                                                     """),format.raw/*348.54*/("""if($(this).is(":checked")) """),format.raw/*348.81*/("""{"""),format.raw/*348.82*/("""

                                                       """),format.raw/*350.56*/("""var a =  parseInt($("#Psychometric").val()) + 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*352.56*/("""}"""),format.raw/*352.57*/("""
                                                       """),format.raw/*353.56*/("""else
                                                       """),format.raw/*354.56*/("""{"""),format.raw/*354.57*/("""
                                                           """),format.raw/*355.60*/("""var a =  parseInt($("#Psychometric").val()) - 10000
                                                       $("#Psychometric").val(a.toString() );
                                                       """),format.raw/*357.56*/("""}"""),format.raw/*357.57*/("""
                                                     """),format.raw/*358.54*/("""}"""),format.raw/*358.55*/(""");

                                                 """),format.raw/*360.50*/("""}"""),format.raw/*360.51*/(""");
                                    </script>
                                    <input type="hidden" name="psychometric" id='Psychometric' value=0>


                                    <input type="button" name="previous" class="previous action-button" value="Previous" />
                                    <input type="button" name="next" class="next action-button" value="Next" />
                                </fieldset>
                                <fieldset>
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
                                                $(document).ready(function()"""),format.raw/*391.77*/("""{"""),format.raw/*391.78*/("""
                                                """),format.raw/*392.49*/("""$('#EDA').change(function() """),format.raw/*392.77*/("""{"""),format.raw/*392.78*/("""
                                                    """),format.raw/*393.53*/("""if($(this).is(":checked")) """),format.raw/*393.80*/("""{"""),format.raw/*393.81*/("""
                                                       """),format.raw/*394.56*/("""var a =  parseInt($("#physiology").val()) + 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*396.56*/("""}"""),format.raw/*396.57*/("""
                                                       """),format.raw/*397.56*/("""else
                                                       """),format.raw/*398.56*/("""{"""),format.raw/*398.57*/("""
                                                        """),format.raw/*399.57*/("""var a =  parseInt($("#physiology").val()) - 1
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*401.56*/("""}"""),format.raw/*401.57*/("""
                                                     """),format.raw/*402.54*/("""}"""),format.raw/*402.55*/(""");

                                                     $('#Motion').change(function() """),format.raw/*404.85*/("""{"""),format.raw/*404.86*/("""
                                                     """),format.raw/*405.54*/("""if($(this).is(":checked")) """),format.raw/*405.81*/("""{"""),format.raw/*405.82*/("""
                                                       """),format.raw/*406.56*/("""var a =  parseInt($("#physiology").val()) + 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*408.56*/("""}"""),format.raw/*408.57*/("""
                                                       """),format.raw/*409.56*/("""else
                                                       """),format.raw/*410.56*/("""{"""),format.raw/*410.57*/("""
                                                         """),format.raw/*411.58*/("""var a =  parseInt($("#physiology").val()) - 10
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*413.56*/("""}"""),format.raw/*413.57*/("""
                                                     """),format.raw/*414.54*/("""}"""),format.raw/*414.55*/(""");

                                                      $('#GSR_Finger').change(function() """),format.raw/*416.90*/("""{"""),format.raw/*416.91*/("""
                                                     """),format.raw/*417.54*/("""if($(this).is(":checked")) """),format.raw/*417.81*/("""{"""),format.raw/*417.82*/("""

                                                       """),format.raw/*419.56*/("""var a =  parseInt($("#physiology").val()) + 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*421.56*/("""}"""),format.raw/*421.57*/("""
                                                       """),format.raw/*422.56*/("""else
                                                       """),format.raw/*423.56*/("""{"""),format.raw/*423.57*/("""
                                                           """),format.raw/*424.60*/("""var a =  parseInt($("#physiology").val()) - 100
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*426.56*/("""}"""),format.raw/*426.57*/("""
                                                     """),format.raw/*427.54*/("""}"""),format.raw/*427.55*/(""");

                                                      $('#Breathing_Belt').change(function() """),format.raw/*429.94*/("""{"""),format.raw/*429.95*/("""
                                                     """),format.raw/*430.54*/("""if($(this).is(":checked")) """),format.raw/*430.81*/("""{"""),format.raw/*430.82*/("""

                                                       """),format.raw/*432.56*/("""var a =  parseInt($("#physiology").val()) + 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*434.56*/("""}"""),format.raw/*434.57*/("""
                                                       """),format.raw/*435.56*/("""else
                                                       """),format.raw/*436.56*/("""{"""),format.raw/*436.57*/("""
                                                           """),format.raw/*437.60*/("""var a =  parseInt($("#physiology").val()) - 1000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*439.56*/("""}"""),format.raw/*439.57*/("""
                                                     """),format.raw/*440.54*/("""}"""),format.raw/*440.55*/(""");

                                                      $('#Breathing_Thermal').change(function() """),format.raw/*442.97*/("""{"""),format.raw/*442.98*/("""
                                                     """),format.raw/*443.54*/("""if($(this).is(":checked")) """),format.raw/*443.81*/("""{"""),format.raw/*443.82*/("""

                                                       """),format.raw/*445.56*/("""var a =  parseInt($("#physiology").val()) + 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*447.56*/("""}"""),format.raw/*447.57*/("""
                                                       """),format.raw/*448.56*/("""else
                                                       """),format.raw/*449.56*/("""{"""),format.raw/*449.57*/("""
                                                           """),format.raw/*450.60*/("""var a =  parseInt($("#physiology").val()) - 10000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*452.56*/("""}"""),format.raw/*452.57*/("""
                                                     """),format.raw/*453.54*/("""}"""),format.raw/*453.55*/(""");

                                                      $('#Heart_Rate').change(function() """),format.raw/*455.90*/("""{"""),format.raw/*455.91*/("""
                                                     """),format.raw/*456.54*/("""if($(this).is(":checked")) """),format.raw/*456.81*/("""{"""),format.raw/*456.82*/("""

                                                       """),format.raw/*458.56*/("""var a =  parseInt($("#physiology").val()) + 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*460.56*/("""}"""),format.raw/*460.57*/("""
                                                       """),format.raw/*461.56*/("""else
                                                       """),format.raw/*462.56*/("""{"""),format.raw/*462.57*/("""
                                                           """),format.raw/*463.60*/("""var a =  parseInt($("#physiology").val()) - 100000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*465.56*/("""}"""),format.raw/*465.57*/("""
                                                     """),format.raw/*466.54*/("""}"""),format.raw/*466.55*/(""");

                                                      $('#Perspiration').change(function() """),format.raw/*468.92*/("""{"""),format.raw/*468.93*/("""
                                                     """),format.raw/*469.54*/("""if($(this).is(":checked")) """),format.raw/*469.81*/("""{"""),format.raw/*469.82*/("""

                                                       """),format.raw/*471.56*/("""var a =  parseInt($("#physiology").val()) + 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*473.56*/("""}"""),format.raw/*473.57*/("""
                                                       """),format.raw/*474.56*/("""else
                                                       """),format.raw/*475.56*/("""{"""),format.raw/*475.57*/("""
                                                           """),format.raw/*476.60*/("""var a =  parseInt($("#physiology").val()) - 1000000
                                                       $("#physiology").val(a.toString() );
                                                       """),format.raw/*478.56*/("""}"""),format.raw/*478.57*/("""
                                                     """),format.raw/*479.54*/("""}"""),format.raw/*479.55*/(""");

                                                 """),format.raw/*481.50*/("""}"""),format.raw/*481.51*/(""");
                                    </script>


                                    <input type="hidden" name="physiology" id='physiology' value=0>
                                    <input type="button" name="previous" class="previous action-button" value="Previous" />
                                    <input type="button" name="next" class="next action-button" value="Next" />
                                </fieldset>
                                <fieldset>
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
                                                $(document).ready(function()"""),format.raw/*515.77*/("""{"""),format.raw/*515.78*/("""
                                                """),format.raw/*516.49*/("""$('#Accelerometer').change(function() """),format.raw/*516.87*/("""{"""),format.raw/*516.88*/("""
                                                    """),format.raw/*517.53*/("""if($(this).is(":checked")) """),format.raw/*517.80*/("""{"""),format.raw/*517.81*/("""
                                                       """),format.raw/*518.56*/("""var a =  parseInt($("#observation").val()) + 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*520.56*/("""}"""),format.raw/*520.57*/("""
                                                       """),format.raw/*521.56*/("""else
                                                       """),format.raw/*522.56*/("""{"""),format.raw/*522.57*/("""
                                                        """),format.raw/*523.57*/("""var a =  parseInt($("#observation").val()) - 1
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*525.56*/("""}"""),format.raw/*525.57*/("""
                                                     """),format.raw/*526.54*/("""}"""),format.raw/*526.55*/(""");

                                                     $('#Obser_other').change(function() """),format.raw/*528.90*/("""{"""),format.raw/*528.91*/("""
                                                     """),format.raw/*529.54*/("""if($(this).is(":checked")) """),format.raw/*529.81*/("""{"""),format.raw/*529.82*/("""
                                                       """),format.raw/*530.56*/("""var a =  parseInt($("#observation").val()) + 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*532.56*/("""}"""),format.raw/*532.57*/("""
                                                       """),format.raw/*533.56*/("""else
                                                       """),format.raw/*534.56*/("""{"""),format.raw/*534.57*/("""
                                                         """),format.raw/*535.58*/("""var a =  parseInt($("#observation").val()) - 10
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*537.56*/("""}"""),format.raw/*537.57*/("""
                                                     """),format.raw/*538.54*/("""}"""),format.raw/*538.55*/(""");

                                                      $('#Video_Face').change(function() """),format.raw/*540.90*/("""{"""),format.raw/*540.91*/("""
                                                     """),format.raw/*541.54*/("""if($(this).is(":checked")) """),format.raw/*541.81*/("""{"""),format.raw/*541.82*/("""

                                                       """),format.raw/*543.56*/("""var a =  parseInt($("#observation").val()) + 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*545.56*/("""}"""),format.raw/*545.57*/("""
                                                       """),format.raw/*546.56*/("""else
                                                       """),format.raw/*547.56*/("""{"""),format.raw/*547.57*/("""
                                                           """),format.raw/*548.60*/("""var a =  parseInt($("#observation").val()) - 100
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*550.56*/("""}"""),format.raw/*550.57*/("""
                                                     """),format.raw/*551.54*/("""}"""),format.raw/*551.55*/(""");

                                                      $('#Video_Room').change(function() """),format.raw/*553.90*/("""{"""),format.raw/*553.91*/("""
                                                     """),format.raw/*554.54*/("""if($(this).is(":checked")) """),format.raw/*554.81*/("""{"""),format.raw/*554.82*/("""

                                                       """),format.raw/*556.56*/("""var a =  parseInt($("#observation").val()) + 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*558.56*/("""}"""),format.raw/*558.57*/("""
                                                       """),format.raw/*559.56*/("""else
                                                       """),format.raw/*560.56*/("""{"""),format.raw/*560.57*/("""
                                                           """),format.raw/*561.60*/("""var a =  parseInt($("#observation").val()) - 1000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*563.56*/("""}"""),format.raw/*563.57*/("""
                                                     """),format.raw/*564.54*/("""}"""),format.raw/*564.55*/(""");

                                                      $('#Video_Theater').change(function() """),format.raw/*566.93*/("""{"""),format.raw/*566.94*/("""
                                                     """),format.raw/*567.54*/("""if($(this).is(":checked")) """),format.raw/*567.81*/("""{"""),format.raw/*567.82*/("""

                                                       """),format.raw/*569.56*/("""var a =  parseInt($("#observation").val()) + 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*571.56*/("""}"""),format.raw/*571.57*/("""
                                                       """),format.raw/*572.56*/("""else
                                                       """),format.raw/*573.56*/("""{"""),format.raw/*573.57*/("""
                                                           """),format.raw/*574.60*/("""var a =  parseInt($("#observation").val()) - 10000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*576.56*/("""}"""),format.raw/*576.57*/("""
                                                     """),format.raw/*577.54*/("""}"""),format.raw/*577.55*/(""");

                                                      $('#FACS').change(function() """),format.raw/*579.84*/("""{"""),format.raw/*579.85*/("""
                                                     """),format.raw/*580.54*/("""if($(this).is(":checked")) """),format.raw/*580.81*/("""{"""),format.raw/*580.82*/("""

                                                       """),format.raw/*582.56*/("""var a =  parseInt($("#observation").val()) + 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*584.56*/("""}"""),format.raw/*584.57*/("""
                                                       """),format.raw/*585.56*/("""else
                                                       """),format.raw/*586.56*/("""{"""),format.raw/*586.57*/("""
                                                           """),format.raw/*587.60*/("""var a =  parseInt($("#observation").val()) - 100000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*589.56*/("""}"""),format.raw/*589.57*/("""
                                                     """),format.raw/*590.54*/("""}"""),format.raw/*590.55*/(""");

                                                      $('#Obser2d_other').change(function() """),format.raw/*592.93*/("""{"""),format.raw/*592.94*/("""
                                                     """),format.raw/*593.54*/("""if($(this).is(":checked")) """),format.raw/*593.81*/("""{"""),format.raw/*593.82*/("""

                                                       """),format.raw/*595.56*/("""var a =  parseInt($("#observation").val()) + 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*597.56*/("""}"""),format.raw/*597.57*/("""
                                                       """),format.raw/*598.56*/("""else
                                                       """),format.raw/*599.56*/("""{"""),format.raw/*599.57*/("""
                                                           """),format.raw/*600.60*/("""var a =  parseInt($("#observation").val()) - 1000000
                                                       $("#observation").val(a.toString() );
                                                       """),format.raw/*602.56*/("""}"""),format.raw/*602.57*/("""
                                                     """),format.raw/*603.54*/("""}"""),format.raw/*603.55*/(""");

                                                 """),format.raw/*605.50*/("""}"""),format.raw/*605.51*/(""");
                                    </script>

                                    <input type="hidden" name="observation" id='observation' value=0>
                                    <input type="button" name="previous" class="previous action-button" value="Previous" />
                                    <input type="button" name="next" class="next action-button" value="Next" />
                                </fieldset>
                                <fieldset>
                                    <h2 class="fs-title">Performance</h2>
                                    <h3 class="fs-subtitle"></h3>
                                    <input type="text" name="Perfro_name" placeholder="Performance Name" />
                                    <input type="text" name="Per_min" placeholder="Min" />
                                    <input type="text" name="Per_mix" placeholder="Max" />
                                    <input type="button" name="Add" class="next" value="Add" />
                                    <input type="button" name="previous" class="previous action-button" value="Previous" />
                                    <input type="submit" name="submit" class="submit action-button" value="Submit" />

                                </fieldset>
                                </br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
                                """)))}),format.raw/*624.34*/("""
                                """),format.raw/*625.33*/("""</form>

                            </div>
                     </div>
    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
				"""),format.raw/*630.5*/("""{"""),format.raw/*630.6*/("""epoint.ow.flow.Utils.setCurrentState("models","list");"""),format.raw/*630.60*/("""}"""),format.raw/*630.61*/(""",null,null);
			</script>
</div>
</div>
                </div>
            </div>
        </div>



<div id="footer_wrapper">
    <div id="footer">
        <ul class="links">
            <li>
                <a href="/support/request-a-feature" id="btn40" target="_blank"><span>Request a feature</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*646.8*/("""{"""),format.raw/*646.9*/("""epoint.ow.Utils.bindEvent("click","btn40","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*646.97*/("""}"""),format.raw/*646.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*649.8*/("""{"""),format.raw/*649.9*/("""epoint.ow.Utils.bindEvent("click","btn40","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*649.100*/("""}"""),format.raw/*649.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/report-a-problem" id="btn41" target="_blank"><span>Report a problem</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*655.8*/("""{"""),format.raw/*655.9*/("""epoint.ow.Utils.bindEvent("click","btn41","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*655.97*/("""}"""),format.raw/*655.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*658.8*/("""{"""),format.raw/*658.9*/("""epoint.ow.Utils.bindEvent("click","btn41","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*658.100*/("""}"""),format.raw/*658.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn42"><span>Recommend us</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*664.8*/("""{"""),format.raw/*664.9*/("""epoint.ow.Utils.bindEvent("click","btn42","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*664.97*/("""}"""),format.raw/*664.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*667.8*/("""{"""),format.raw/*667.9*/("""epoint.ow.Utils.bindEvent("click","btn42","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*667.100*/("""}"""),format.raw/*667.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/ask-a-question" id="btn43" target="_blank"><span>Ask a question</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*673.8*/("""{"""),format.raw/*673.9*/("""epoint.ow.Utils.bindEvent("click","btn43","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*673.97*/("""}"""),format.raw/*673.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*676.8*/("""{"""),format.raw/*676.9*/("""epoint.ow.Utils.bindEvent("click","btn43","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*676.100*/("""}"""),format.raw/*676.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a target="_blank" href="http://vertabelo.com/about-us">About us</a>
            </li>
            <li>
                <a href="http://vertabelo.com/terms-of-service">Terms of service</a>
            </li>
            <li>
                <a href="http://vertabelo.com/privacy-policy">Privacy policy</a>
            </li>
            <li>
                <a target="_blank" href="http://vertabelo.com/documentation">Documentation</a>
            </li>
        </ul>
        <div class="social">Follow us on: <a class="twitter" title="Twitter" href="http://twitter.com/Vertabelo"><span>Twitter</span></a> <a class="facebook" title="Facebook" href="https://www.facebook.com/Vertabelo"><span>Facebook</span></a> <a class="youtube" title="Youtube" href="http://www.youtube.com/user/vertabelo"><span>Youtube</span></a>
        </div>
        <div class="clearFix"></div>
        <div class="hr"><hr></div>
        <!-- <div class="powered_by">Powered by <a href="http://e-point.com">e-point SA</a> internet software house</div> -->
        <div class="copyright">StressBook v. 1.0.0 All rights reserved</div>
        <div class="clearFix"></div>
    </div>
</div>
</div>
<script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*702.87*/("""{"""),format.raw/*702.88*/("""oneweb.CsrfTokenManager.setToken("dE9ktmra82LBfstlCVdVyEosWMEiGhvB");"""),format.raw/*702.157*/("""}"""),format.raw/*702.158*/(""",10,null);
</script>
<script>
	//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches

$(".next").click(function()"""),format.raw/*710.28*/("""{"""),format.raw/*710.29*/("""

     """),format.raw/*712.6*/("""if(document.getElementById("study_name").value == "")
        alert("Please enter the name of study")
     else if(document.getElementById("folder_name").value == "")
         """),format.raw/*715.10*/("""{"""),format.raw/*715.11*/("""
            """),format.raw/*716.13*/("""alert("Please enter the URL")
         """),format.raw/*717.10*/("""}"""),format.raw/*717.11*/("""
     """),format.raw/*718.6*/("""else
     """),format.raw/*719.6*/("""{"""),format.raw/*719.7*/("""
	"""),format.raw/*720.2*/("""if(animating) return false;
	animating = true;

	current_fs = $(this).parent();
	next_fs = $(this).parent().next();

	//activate next step on progressbar using the index of next_fs
	$("#progressbar1 li").eq($("fieldset").index(next_fs)).addClass("active");

	//show the next fieldset
	next_fs.show();
	//hide the current fieldset with style
	current_fs.animate("""),format.raw/*732.21*/("""{"""),format.raw/*732.22*/("""opacity: 0"""),format.raw/*732.32*/("""}"""),format.raw/*732.33*/(""", """),format.raw/*732.35*/("""{"""),format.raw/*732.36*/("""
		"""),format.raw/*733.3*/("""step: function(now, mx) """),format.raw/*733.27*/("""{"""),format.raw/*733.28*/("""
			"""),format.raw/*734.4*/("""//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css("""),format.raw/*741.19*/("""{"""),format.raw/*741.20*/("""'transform': 'scale('+scale+')'"""),format.raw/*741.51*/("""}"""),format.raw/*741.52*/(""");
			next_fs.css("""),format.raw/*742.16*/("""{"""),format.raw/*742.17*/("""'left': left, 'opacity': opacity"""),format.raw/*742.49*/("""}"""),format.raw/*742.50*/(""");
		"""),format.raw/*743.3*/("""}"""),format.raw/*743.4*/(""",
		duration: 800,
		complete: function()"""),format.raw/*745.23*/("""{"""),format.raw/*745.24*/("""
			"""),format.raw/*746.4*/("""current_fs.hide();
			animating = false;
		"""),format.raw/*748.3*/("""}"""),format.raw/*748.4*/(""",
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	"""),format.raw/*751.2*/("""}"""),format.raw/*751.3*/(""");
	"""),format.raw/*752.2*/("""}"""),format.raw/*752.3*/("""
"""),format.raw/*753.1*/("""}"""),format.raw/*753.2*/(""");

$(".previous").click(function()"""),format.raw/*755.32*/("""{"""),format.raw/*755.33*/("""
	"""),format.raw/*756.2*/("""if(animating) return false;
	animating = true;

	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();

	//de-activate current step on progressbar
	$("#progressbar1 li").eq($("fieldset").index(current_fs)).removeClass("active");

	//show the previous fieldset
	previous_fs.show();
	//hide the current fieldset with style
	current_fs.animate("""),format.raw/*768.21*/("""{"""),format.raw/*768.22*/("""opacity: 0"""),format.raw/*768.32*/("""}"""),format.raw/*768.33*/(""", """),format.raw/*768.35*/("""{"""),format.raw/*768.36*/("""
		"""),format.raw/*769.3*/("""step: function(now, mx) """),format.raw/*769.27*/("""{"""),format.raw/*769.28*/("""
			"""),format.raw/*770.4*/("""//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css("""),format.raw/*777.19*/("""{"""),format.raw/*777.20*/("""'left': left"""),format.raw/*777.32*/("""}"""),format.raw/*777.33*/(""");
			previous_fs.css("""),format.raw/*778.20*/("""{"""),format.raw/*778.21*/("""'transform': 'scale('+scale+')', 'opacity': opacity"""),format.raw/*778.72*/("""}"""),format.raw/*778.73*/(""");
		"""),format.raw/*779.3*/("""}"""),format.raw/*779.4*/(""",
		duration: 800,
		complete: function()"""),format.raw/*781.23*/("""{"""),format.raw/*781.24*/("""
			"""),format.raw/*782.4*/("""current_fs.hide();
			animating = false;
		"""),format.raw/*784.3*/("""}"""),format.raw/*784.4*/(""",
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	"""),format.raw/*787.2*/("""}"""),format.raw/*787.3*/(""");
"""),format.raw/*788.1*/("""}"""),format.raw/*788.2*/(""");
</script>
</div>
</body>
</html>"""))}
  }

  def render(userName:String): play.twirl.api.HtmlFormat.Appendable = apply(userName)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (userName) => apply(userName)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Mar 30 23:41:16 CDT 2016
                  SOURCE: C:/first_play/app/views/CreateStudy.scala.html
                  HASH: 9ee621bd71aa94d6ffc5ef9caef8ab184f2088a8
                  MATRIX: 511->1|617->19|647->23|2565->1914|2594->1922|2851->2150|2881->2151|2998->2239|3028->2240|3162->2345|3192->2346|3312->2437|3342->2438|3679->2746|3709->2747|3826->2835|3856->2836|3991->2941|4022->2942|4139->3029|4170->3030|4319->3149|4350->3150|4595->3365|4626->3366|4774->3484|4805->3485|4907->3557|4938->3558|5152->3742|5183->3743|5301->3831|5332->3832|5467->3937|5498->3938|5619->4029|5650->4030|6241->4592|6271->4593|6388->4681|6418->4682|6552->4787|6582->4788|6702->4879|6732->4880|6987->5105|7018->5106|7136->5194|7167->5195|7302->5300|7333->5301|7454->5392|7485->5393|8969->6849|8998->6850|9114->6938|9144->6939|9327->7094|9356->7095|9476->7186|9506->7187|9882->7535|9911->7536|10028->7624|10058->7625|10243->7782|10272->7783|10392->7874|10422->7875|10792->8218|10821->8219|10937->8307|10966->8308|11117->8432|11146->8433|11266->8524|11296->8525|12355->9556|12371->9562|12466->9646|12508->9648|12571->9682|18759->15841|18789->15842|18868->15892|18925->15920|18955->15921|19038->15975|19094->16002|19124->16003|19210->16060|19418->16239|19448->16240|19534->16297|19624->16358|19654->16359|19741->16417|19949->16596|19979->16597|20063->16652|20093->16653|20212->16743|20242->16744|20326->16799|20382->16826|20412->16827|20500->16886|20709->17066|20739->17067|20825->17124|20915->17185|20945->17186|21033->17245|21242->17425|21272->17426|21356->17481|21386->17482|21506->17573|21536->17574|21620->17629|21676->17656|21706->17657|21794->17716|22004->17897|22034->17898|22120->17955|22210->18016|22240->18017|22330->18078|22540->18259|22570->18260|22654->18315|22684->18316|22804->18407|22834->18408|22918->18463|22974->18490|23004->18491|23092->18550|23303->18732|23333->18733|23419->18790|23509->18851|23539->18852|23629->18913|23840->19095|23870->19096|23954->19151|23984->19152|24107->19246|24137->19247|24221->19302|24277->19329|24307->19330|24395->19389|24607->19572|24637->19573|24723->19630|24813->19691|24843->19692|24933->19753|25145->19936|25175->19937|25259->19992|25289->19993|25412->20087|25442->20088|25526->20143|25582->20170|25612->20171|25700->20230|25913->20414|25943->20415|26029->20472|26119->20533|26149->20534|26239->20595|26452->20779|26482->20780|26566->20835|26596->20836|26680->20891|26710->20892|29321->23474|29351->23475|29434->23529|29491->23557|29521->23558|29604->23612|29660->23639|29690->23640|29776->23697|30004->23896|30034->23897|30120->23954|30210->24015|30240->24016|30327->24074|30555->24273|30585->24274|30669->24329|30699->24330|30815->24417|30845->24418|30929->24473|30985->24500|31015->24501|31101->24558|31330->24758|31360->24759|31446->24816|31536->24877|31566->24878|31654->24937|31883->25137|31913->25138|31997->25193|32027->25194|32143->25281|32173->25282|32257->25337|32313->25364|32343->25365|32431->25424|32661->25625|32691->25626|32777->25683|32867->25744|32897->25745|32987->25806|33217->26007|33247->26008|33331->26063|33361->26064|33477->26151|33507->26152|33591->26207|33647->26234|33677->26235|33765->26294|33996->26496|34026->26497|34112->26554|34202->26615|34232->26616|34322->26677|34553->26879|34583->26880|34667->26935|34697->26936|34822->27032|34852->27033|34936->27088|34992->27115|35022->27116|35110->27175|35342->27378|35372->27379|35458->27436|35548->27497|35578->27498|35668->27559|35900->27762|35930->27763|36014->27818|36044->27819|36128->27874|36158->27875|39111->30799|39141->30800|39220->30850|39277->30878|39307->30879|39390->30933|39446->30960|39476->30961|39562->31018|39786->31213|39816->31214|39902->31271|39992->31332|40022->31333|40109->31391|40333->31586|40363->31587|40447->31642|40477->31643|40596->31733|40626->31734|40710->31789|40766->31816|40796->31817|40882->31874|41107->32070|41137->32071|41223->32128|41313->32189|41343->32190|41431->32249|41656->32445|41686->32446|41770->32501|41800->32502|41924->32597|41954->32598|42038->32653|42094->32680|42124->32681|42212->32740|42438->32937|42468->32938|42554->32995|42644->33056|42674->33057|42764->33118|42990->33315|43020->33316|43104->33371|43134->33372|43262->33471|43292->33472|43376->33527|43432->33554|43462->33555|43550->33614|43777->33812|43807->33813|43893->33870|43983->33931|44013->33932|44103->33993|44330->34191|44360->34192|44444->34247|44474->34248|44605->34350|44635->34351|44719->34406|44775->34433|44805->34434|44893->34493|45121->34692|45151->34693|45237->34750|45327->34811|45357->34812|45447->34873|45675->35072|45705->35073|45789->35128|45819->35129|45943->35224|45973->35225|46057->35280|46113->35307|46143->35308|46231->35367|46460->35567|46490->35568|46576->35625|46666->35686|46696->35687|46786->35748|47015->35948|47045->35949|47129->36004|47159->36005|47285->36102|47315->36103|47399->36158|47455->36185|47485->36186|47573->36245|47803->36446|47833->36447|47919->36504|48009->36565|48039->36566|48129->36627|48359->36828|48389->36829|48473->36884|48503->36885|48587->36940|48617->36941|51523->39818|51553->39819|51632->39869|51699->39907|51729->39908|51812->39962|51868->39989|51898->39990|51984->40047|52210->40244|52240->40245|52326->40302|52416->40363|52446->40364|52533->40422|52759->40619|52789->40620|52873->40675|52903->40676|53027->40771|53057->40772|53141->40827|53197->40854|53227->40855|53313->40912|53540->41110|53570->41111|53656->41168|53746->41229|53776->41230|53864->41289|54091->41487|54121->41488|54205->41543|54235->41544|54359->41639|54389->41640|54473->41695|54529->41722|54559->41723|54647->41782|54875->41981|54905->41982|54991->42039|55081->42100|55111->42101|55201->42162|55429->42361|55459->42362|55543->42417|55573->42418|55697->42513|55727->42514|55811->42569|55867->42596|55897->42597|55985->42656|56214->42856|56244->42857|56330->42914|56420->42975|56450->42976|56540->43037|56769->43237|56799->43238|56883->43293|56913->43294|57040->43392|57070->43393|57154->43448|57210->43475|57240->43476|57328->43535|57558->43736|57588->43737|57674->43794|57764->43855|57794->43856|57884->43917|58114->44118|58144->44119|58228->44174|58258->44175|58376->44264|58406->44265|58490->44320|58546->44347|58576->44348|58664->44407|58895->44609|58925->44610|59011->44667|59101->44728|59131->44729|59221->44790|59452->44992|59482->44993|59566->45048|59596->45049|59723->45147|59753->45148|59837->45203|59893->45230|59923->45231|60011->45290|60243->45493|60273->45494|60359->45551|60449->45612|60479->45613|60569->45674|60801->45877|60831->45878|60915->45933|60945->45934|61029->45989|61059->45990|62594->47493|62657->47527|62856->47698|62885->47699|62968->47753|62998->47754|63458->48186|63487->48187|63604->48275|63634->48276|63801->48415|63830->48416|63951->48507|63982->48508|64302->48800|64331->48801|64448->48889|64478->48890|64645->49029|64674->49030|64795->49121|64826->49122|65159->49427|65188->49428|65305->49516|65335->49517|65502->49656|65531->49657|65652->49748|65683->49749|65999->50037|66028->50038|66145->50126|66175->50127|66342->50266|66371->50267|66492->50358|66523->50359|67911->51718|67941->51719|68040->51788|68071->51789|68361->52050|68391->52051|68428->52060|68636->52239|68666->52240|68709->52254|68778->52294|68808->52295|68843->52302|68882->52313|68911->52314|68942->52317|69344->52690|69374->52691|69413->52701|69443->52702|69474->52704|69504->52705|69536->52709|69589->52733|69619->52734|69652->52739|69983->53041|70013->53042|70073->53073|70103->53074|70151->53093|70181->53094|70242->53126|70272->53127|70306->53133|70335->53134|70407->53177|70437->53178|70470->53183|70543->53228|70572->53229|70677->53306|70706->53307|70739->53312|70768->53313|70798->53315|70827->53316|70893->53353|70923->53354|70954->53357|71353->53727|71383->53728|71422->53738|71452->53739|71483->53741|71513->53742|71545->53746|71598->53770|71628->53771|71661->53776|72018->54104|72048->54105|72089->54117|72119->54118|72171->54141|72201->54142|72281->54193|72311->54194|72345->54200|72374->54201|72446->54244|72476->54245|72509->54250|72582->54295|72611->54296|72716->54373|72745->54374|72777->54378|72806->54379
                  LINES: 19->1|22->1|24->3|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|53->32|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|56->35|78->57|78->57|78->57|78->57|81->60|81->60|81->60|81->60|87->66|87->66|87->66|87->66|90->69|90->69|90->69|90->69|96->75|96->75|96->75|96->75|98->77|98->77|98->77|98->77|126->105|126->105|126->105|126->105|127->106|212->191|212->191|213->192|213->192|213->192|214->193|214->193|214->193|215->194|217->196|217->196|218->197|219->198|219->198|220->199|222->201|222->201|223->202|223->202|225->204|225->204|226->205|226->205|226->205|227->206|229->208|229->208|230->209|231->210|231->210|232->211|234->213|234->213|235->214|235->214|237->216|237->216|238->217|238->217|238->217|240->219|242->221|242->221|243->222|244->223|244->223|245->224|247->226|247->226|248->227|248->227|250->229|250->229|251->230|251->230|251->230|253->232|255->234|255->234|256->235|257->236|257->236|258->237|260->239|260->239|261->240|261->240|263->242|263->242|264->243|264->243|264->243|266->245|268->247|268->247|269->248|270->249|270->249|271->250|273->252|273->252|274->253|274->253|276->255|276->255|277->256|277->256|277->256|279->258|281->260|281->260|282->261|283->262|283->262|284->263|286->265|286->265|287->266|287->266|289->268|289->268|317->296|317->296|318->297|318->297|318->297|319->298|319->298|319->298|320->299|322->301|322->301|323->302|324->303|324->303|325->304|327->306|327->306|328->307|328->307|330->309|330->309|331->310|331->310|331->310|332->311|334->313|334->313|335->314|336->315|336->315|337->316|339->318|339->318|340->319|340->319|342->321|342->321|343->322|343->322|343->322|345->324|347->326|347->326|348->327|349->328|349->328|350->329|352->331|352->331|353->332|353->332|355->334|355->334|356->335|356->335|356->335|358->337|360->339|360->339|361->340|362->341|362->341|363->342|365->344|365->344|366->345|366->345|368->347|368->347|369->348|369->348|369->348|371->350|373->352|373->352|374->353|375->354|375->354|376->355|378->357|378->357|379->358|379->358|381->360|381->360|412->391|412->391|413->392|413->392|413->392|414->393|414->393|414->393|415->394|417->396|417->396|418->397|419->398|419->398|420->399|422->401|422->401|423->402|423->402|425->404|425->404|426->405|426->405|426->405|427->406|429->408|429->408|430->409|431->410|431->410|432->411|434->413|434->413|435->414|435->414|437->416|437->416|438->417|438->417|438->417|440->419|442->421|442->421|443->422|444->423|444->423|445->424|447->426|447->426|448->427|448->427|450->429|450->429|451->430|451->430|451->430|453->432|455->434|455->434|456->435|457->436|457->436|458->437|460->439|460->439|461->440|461->440|463->442|463->442|464->443|464->443|464->443|466->445|468->447|468->447|469->448|470->449|470->449|471->450|473->452|473->452|474->453|474->453|476->455|476->455|477->456|477->456|477->456|479->458|481->460|481->460|482->461|483->462|483->462|484->463|486->465|486->465|487->466|487->466|489->468|489->468|490->469|490->469|490->469|492->471|494->473|494->473|495->474|496->475|496->475|497->476|499->478|499->478|500->479|500->479|502->481|502->481|536->515|536->515|537->516|537->516|537->516|538->517|538->517|538->517|539->518|541->520|541->520|542->521|543->522|543->522|544->523|546->525|546->525|547->526|547->526|549->528|549->528|550->529|550->529|550->529|551->530|553->532|553->532|554->533|555->534|555->534|556->535|558->537|558->537|559->538|559->538|561->540|561->540|562->541|562->541|562->541|564->543|566->545|566->545|567->546|568->547|568->547|569->548|571->550|571->550|572->551|572->551|574->553|574->553|575->554|575->554|575->554|577->556|579->558|579->558|580->559|581->560|581->560|582->561|584->563|584->563|585->564|585->564|587->566|587->566|588->567|588->567|588->567|590->569|592->571|592->571|593->572|594->573|594->573|595->574|597->576|597->576|598->577|598->577|600->579|600->579|601->580|601->580|601->580|603->582|605->584|605->584|606->585|607->586|607->586|608->587|610->589|610->589|611->590|611->590|613->592|613->592|614->593|614->593|614->593|616->595|618->597|618->597|619->598|620->599|620->599|621->600|623->602|623->602|624->603|624->603|626->605|626->605|645->624|646->625|651->630|651->630|651->630|651->630|667->646|667->646|667->646|667->646|670->649|670->649|670->649|670->649|676->655|676->655|676->655|676->655|679->658|679->658|679->658|679->658|685->664|685->664|685->664|685->664|688->667|688->667|688->667|688->667|694->673|694->673|694->673|694->673|697->676|697->676|697->676|697->676|723->702|723->702|723->702|723->702|731->710|731->710|733->712|736->715|736->715|737->716|738->717|738->717|739->718|740->719|740->719|741->720|753->732|753->732|753->732|753->732|753->732|753->732|754->733|754->733|754->733|755->734|762->741|762->741|762->741|762->741|763->742|763->742|763->742|763->742|764->743|764->743|766->745|766->745|767->746|769->748|769->748|772->751|772->751|773->752|773->752|774->753|774->753|776->755|776->755|777->756|789->768|789->768|789->768|789->768|789->768|789->768|790->769|790->769|790->769|791->770|798->777|798->777|798->777|798->777|799->778|799->778|799->778|799->778|800->779|800->779|802->781|802->781|803->782|805->784|805->784|808->787|808->787|809->788|809->788
                  -- GENERATED --
              */
          