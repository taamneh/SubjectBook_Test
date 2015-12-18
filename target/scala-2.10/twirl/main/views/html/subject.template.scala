
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
object subject extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template9[String,Map[String, List[String]],Int,String,List[Int],Map[String, List[String]],Int,Map[String, List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(SubjectId: String, sub_sess: Map[String, List[String]], studyNo:Int, userName: String, signals: List[Int], videos:  Map[String, List[ String]], study_type: Int, sub_sess_menu: Map[String, List[String]], studyName: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;

Seq[Any](format.raw/*1.224*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html itemscope="" itemtype="http://schema.org/WebPage" lang="en">
<head>

    <style type="text/css">


        #fooObject """),format.raw/*10.20*/("""{"""),format.raw/*10.21*/("""
        """),format.raw/*11.9*/("""/* simple box */
        position:absolute;
        left:0px;
        top:8em;
        width:0em;
        line-height:10em;
        background:#99ccff;
        border:1px 0px 0px 0px solid #003366;
        white-space:nowrap;
        padding:0.5em;
        border-radius: 25px;
        """),format.raw/*22.9*/("""}"""),format.raw/*22.10*/("""

    """),format.raw/*24.5*/("""</style>

    <meta charset="UTF-8">
    <!-- <script type="text/javascript" src="/assets/js/jquery-1.9.0.min.js"></script> -->
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/tab.css">
    <title>StressBook</title>
    <meta content="width=1170" name="viewport">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"><meta content="IRQcweD5CIs6Qc-uVi9xT3BD-1jPvDxyk3-9LUah9T8" name="google-site-verification">
    <link href="/assets/images/cpllog.png" rel="icon" type="image/png">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <script type="text/javascript" src="/assets/js/ow-all.js"></script>
    <script type="text/javascript" src="/assets/js/webapp.js"></script>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>

    <link rel="stylesheet" href="/assets/stylesheets/portrat.css" type="text/css" />


    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/jquery-ui-1.9.2.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/modeler.css">

</head>
<body style="border:1px solid;width:100%; height=100%;">
<div id="wrapper" >
<table class="border_layout" style="border:1px solid;width:100%; height=100%">
<tr>
    <td class="border_layout_north" colspan="3">
        <div class="border_layout_north">
            <div id="header_wrapper">


                <div id="header">
                    <a class="logo" href="http://www.cpl.uh.edu/"><img alt="CPL Logo" width="70" height="40"  src="/assets/images/cpllogo.png"></a>
                    <ul class="user_menu"> <li class="user dropdown_holder"><span class="dropdown"><span>"""),_display_(/*62.107*/userName),format.raw/*62.115*/("""</span></span> <div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/my-account?action=flow.reset&amp;flow.flow=my_account" id="btn31"><span>My account</span></a> </li> </ul></div></div></li><li class="notifications"><div id="flow_notifications_layer_div"><div class="menu_item dropdown_holder"><a class="dropdown notifications" href="?action=flow.transit&amp;flow.flow=notifications_layer&amp;flow.transition=expanded&amp;flow.reloadFlow=notifications_layer" id="btn32"><span></span></a></div></div></li><li class="logout"><a href="/logout" id="btn33"><span>Log out</span></a></li></ul>
                    <ul class="links">
                        <li class="dropdown_holder expandable expand-priority-1"><span class="dropdown"><span>Modules</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/diagram">Diagram</a></li><li><a href="/allStudies"  id="btn34"><span>My models</span></a></li><li><a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn35"><span>Sharing</span></a></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn36"><span>Recommend us</span></a></li></ul></div></div></li><li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span>
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
                                        <a target="_blank" href="http://www.cpl.uh.edu/">CPL website</a>
                                    </li>
                                    <li>
                                        <a target="_blank" href="http://vertabelo.com/documentation">Documentation</a>
                                    </li>
                                    <li>
                                        <a target="_blank" href="http://vertabelo.com/faq">FAQ</a>
                                    </li>
                                    <li>
                                        <a href="/support/ask-a-question" id="btn37" target="_blank"><span>Ask a question</span></a>
                                    <li>
                                        <a href="/support/request-a-feature" id="btn38" target="_blank"><span>Request a feature</span></a>
                                    </li>
                                    <li>
                                        <a href="/support/report-a-problem" id="btn39" target="_blank"><span>Report a problem</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    </ul>

                    <div class="clearFix">
                    </div>
                    <div class="clearFix">
                    </div>
                </div>


            </div>

           <!-- <table id="toolbar_wrapper"><tr><td class="item wide">



                <div class="block_dialog_container"><div class="popup initialHide" id="popup_flow_share_model_div">
                    <div class="popup_arrow_up"></div>
                    <a class="close-popup-button" id="btn2521"><span>X</span></a>
                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*113.107*/("""{"""),format.raw/*113.108*/("""epoint.ow.Utils.bindEvent("click","btn2521","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*113.198*/("""}"""),format.raw/*113.199*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*113.304*/("""{"""),format.raw/*113.305*/("""epoint.ow.Utils.bindEvent("click","btn2521","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_share_model_div",[]);"""),format.raw/*113.436*/("""}"""),format.raw/*113.437*/(""",null,null);</script>
                    <div class="clearFix"></div>
                    <div class="popup_inner">
                        <div id="flow_share_model_div"><img src="/assets/images/empty_flow_content.gif"></div>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*117.111*/("""{"""),format.raw/*117.112*/("""epoint.ow.flow.Utils.setCurrentState("share_model","share");"""),format.raw/*117.172*/("""}"""),format.raw/*117.173*/(""",null,null);</script>
                    </div>
                </div>
                </div><span id="share_model_button_wrapper"><a class="button" href="?action=flow.reset&amp;flow.flow=share_model&amp;flow.reloadFlow=share_model" id="btn2522"><span>Share model</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*120.290*/("""{"""),format.raw/*120.291*/("""epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*120.381*/("""}"""),format.raw/*120.382*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*120.487*/("""{"""),format.raw/*120.488*/("""epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.popup.PopupManager.openPopupAfterAjaxFlowReloadClickHandler","popup_flow_share_model_div","flow_share_model_div",[]);epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*120.749*/("""}"""),format.raw/*120.750*/(""",null,null);</script></span><div class="block_dialog_container"><div class="popup initialHide compact" id="sql_generator_form_container">
                <div class="popup_arrow_up"></div><a class="close-popup-button" id="btn2524"><span>X</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*121.198*/("""{"""),format.raw/*121.199*/("""epoint.ow.Utils.bindEvent("click","btn2524","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*121.289*/("""}"""),format.raw/*121.290*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*121.395*/("""{"""),format.raw/*121.396*/("""epoint.ow.Utils.bindEvent("click","btn2524","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*121.529*/("""}"""),format.raw/*121.530*/(""",null,null);</script>
                <div class="clearFix"></div>
                <div class="popup_inner">
                <div class="header"><h1>Generate SQL Script</h1></div>
                    <form id="sql_generator_form" onSubmit="return false;"><div class="box_form_section"><div class="box_form_content">
                <table cellspacing="0" class="form_table">
                    <tr class="row_1"><td class="cell_1"><span class="field_label">I want to generate:<span class="mandatory">*</span></span>
                    </td>
                        <td class="cell_2"><div class="h_options"><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_type_create" name="type" type="radio" value="create"><label for="sql_generator_type_create">Create</label></span><span class="field_option"><input class="radio_field" id="sql_generator_type_drop" name="type" type="radio" value="drop"><label for="sql_generator_type_drop">Drop</label></span></div>
                        </td>
                    </tr>
                    <tr class="row_2"><td class="cell_1"><span class="field_label">For following elements:<span class="mandatory">*</span></span></td>
                        <td class="cell_2"><div class="v_options"><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_scope_tables" name="scope" type="checkbox" value="tables"><label for="sql_generator_scope_tables">Tables</label></span><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_scope_references" name="scope" type="checkbox" value="references"><label for="sql_generator_scope_references">References</label></span><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_scope_sequences" name="scope" type="checkbox" value="sequences"><label for="sql_generator_scope_sequences">Sequences</label></span><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_scope_views" name="scope" type="checkbox" value="views"><label for="sql_generator_scope_views">Views</label></span><span class="field_option"><input checked="checked" class="radio_field" id="sql_generator_scope_procedures" name="scope" type="checkbox" value="procedures"><label for="sql_generator_scope_procedures">Procedures</label></span></div>
                        </td>
                    </tr>
                </table><p class="sql_generator_errors" id="sql_generator_empty_script">You will get empty sql script. Choose at least one element.</p></div></div></form><div id="sql_generator_problems" style="display: none"><div class="section section__small section__separated_below"><div class="header"><h2>Problems </h2></div><div class="content"><p class="sql_generator_errors">The model you want to generate a SQL script from contains errors&#x2F;warnings.
                The generated script may not be accepted by your database.
                We highly recommend that you correct all errors and warnings before generating the SQL script.</p><div class="accordion" id="sql_generator_problems_list" style="display: none"></div></div><div class="clearFix"></div></div></div><div id="sql_generator_progress_checking" style="display: none"><p><img alt="" class="loading-small" src="/assets/images/loading-small.gif">Checking model. Please wait...</p></div><div id="sql_generator_progress_generating" style="display: none"><p><img alt="" class="loading-small" src="/assets/images/loading-small.gif">Generating SQL script. Please wait...</p></div><div id="sql_generator_download" style="display: none"><div class="box_table">
                <table class="table_table"><thead class="table_header">
                <tr><th colspan="2">Generated file</th></tr></thead><tbody class="table_body">
                <tr class="table_row_odd table_row_first table_row_last"><td class="width_max"><a href="#" id="sql_generator_show_model_link" target="_blank">none.sql</a></td><td><a class="button" href="#" id="sql_generator_download_model_button">Download</a></td>
                </tr></tbody>
                </table></div></div><div class="buttons"><a class="button_primary" id="sql_generator_form_button"><span>Generate</span></a>
                <a class="button_primary" id="sql_generator_form_despite_warnings_button"><span>Ignore warnings and generate</span></a>
                <a class="link" id="btn2523"><span>Cancel</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*145.155*/("""{"""),format.raw/*145.156*/("""epoint.ow.Utils.bindEvent("click","btn2523","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*145.246*/("""}"""),format.raw/*145.247*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*145.352*/("""{"""),format.raw/*145.353*/("""epoint.ow.Utils.bindEvent("click","btn2523","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*145.486*/("""}"""),format.raw/*145.487*/(""",null,null);</script></div><p class="mandatory_info">Fields marked with [<span class="mandatory">*</span>] must be filled.</p>
                </div>
            </div>
            </div>
            </td><td class="item"><div id="search-box"></div><script type="text/template" id="search-template"><form name="search"><input class="text_field" name="query" placeholder="Search (Ctrl+F)" type="text"></form></script>
            </td></tr>
            </table> -->
        </div>
    </td>

</tr>
<tr>
    <td class="border_layout_west" id="left-panel" >
        <div class="border_layout_west">
            <div class="accordion" >
                <div class="block" >
                    <div class="header">
                        <h2>Study Structure</h2>
                    </div>
                    <div class="content" style="height:800px;">
                        <div id="handy-menu1"></div>
                        <script type="text/template" id="handy-menu-find-in-area-template">
                            <button class="handy-menu-find-in-area-button" type="button">Find in """),format.raw/*167.98*/("""{"""),format.raw/*167.99*/("""{"""),format.raw/*167.100*/("""area"""),format.raw/*167.104*/("""}"""),format.raw/*167.105*/("""}"""),format.raw/*167.106*/("""</button>
                        </script>
                        <div class="tree">
                            <div class="tree_title">"""),_display_(/*170.54*/studyName),format.raw/*170.63*/(""" """),format.raw/*170.64*/("""Study [<a href="/showStudy/"""),_display_(/*170.92*/studyNo),format.raw/*170.99*/(""""> Portrait View</a>]</div>
                            <ul>
                                <li>
                                    <div id="table-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Subjects</span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                    <ul id="table-list">
                                        <p hidden id = "studyNo" value = "123" subject ="S001">"""),_display_(/*175.97*/studyNo),format.raw/*175.104*/("""</p>
                                        """),_display_(/*176.42*/for((subject,sessions) <- sub_sess_menu) yield /*176.82*/{_display_(Seq[Any](format.raw/*176.83*/("""
                                        """),format.raw/*177.41*/("""<!-- <li><input type="checkbox" id="item-0-2" />"""),_display_(/*177.90*/subject),format.raw/*177.97*/("""<label for="item-0"></label> -->
                                        <li> <div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label"><a href="/displaySubject?studyNo="""),_display_(/*178.195*/studyNo),format.raw/*178.202*/("""&SubjectId="""),_display_(/*178.214*/subject),format.raw/*178.221*/("""">"""),_display_(/*178.224*/subject),format.raw/*178.231*/(""" """),format.raw/*178.232*/("""</a></span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                            <ul>
                                                """),_display_(/*180.50*/for(session <- sessions) yield /*180.74*/{_display_(Seq[Any](format.raw/*180.75*/("""
                                                """),format.raw/*181.49*/("""<!-- <li><input type="checkbox" id="item-0-0-23" /><label for="item-0">"""),_display_(/*181.121*/session),format.raw/*181.128*/("""</label> <ul id=""""),_display_(/*181.146*/subject),_display_(/*181.154*/session),format.raw/*181.161*/(""""> <li class="inner" subject="""),_display_(/*181.191*/subject),format.raw/*181.198*/(""" """),format.raw/*181.199*/("""session="""),_display_(/*181.208*/session),format.raw/*181.215*/("""><a >Run 1</a></li> </ul></li> -->

                                                <li><div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label"><a href="/displaySubject?studyNo="""),_display_(/*183.202*/studyNo),format.raw/*183.209*/("""&SubjectId="""),_display_(/*183.221*/subject),format.raw/*183.228*/("""#"""),_display_(/*183.230*/session),format.raw/*183.237*/("""">"""),_display_(/*183.240*/session),format.raw/*183.247*/(""" """),format.raw/*183.248*/("""</a></span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                                    <!-- <ul id=""""),_display_(/*184.67*/subject),_display_(/*184.75*/session),format.raw/*184.82*/("""">
                                                         <li>
                                                             <div><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span>
                                                                 <div class="inner" subject="""),_display_(/*187.94*/subject),format.raw/*187.101*/(""" """),format.raw/*187.102*/("""session="""),_display_(/*187.111*/session),format.raw/*187.118*/(""" """),format.raw/*187.119*/("""> <span class="label">Run 1</span> </div>

                                                                 <span class="context-menu-trigger" data-label="table" data-what="tables"></span>
                                                             </div>
                                                         </li>
                                                     </ul> -->
                                                </li>

                                                <!-- <li> <div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">"""),_display_(/*195.175*/subject),format.raw/*195.182*/("""</span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div> -->
                                                """)))}),format.raw/*196.50*/("""

                                            """),format.raw/*198.45*/("""</ul>
                                        </li>
                                        """)))}),format.raw/*200.42*/("""
                                        """),format.raw/*201.41*/("""<!-- </ul> -->
                                        <!-- </li> -->
                                    </ul>
                                </li>
                                <!--<li>
                                    <div id="reference-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">References</span><span class="context-menu-trigger" data-label="reference" data-what="references"></span></div><ul id="reference-list"></ul>
                                </li>
                                <li>
                                    <div id="sequence-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Sequences</span><span class="context-menu-trigger" data-label="sequence" data-what="sequences"></span></div><ul id="sequence-list"></ul>
                                </li>
                                <li><div id="note-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Text notes</span><span class="context-menu-trigger" data-label="note" data-what="notes"></span></div><ul id="note-list"></ul>
                                </li>
                                <li><div id="view-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Views</span><span class="context-menu-trigger" data-label="view" data-what="views"></span></div><ul id="view-list"></ul>
                                </li>
                                <li><div id="area-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Subject areas</span><span class="context-menu-trigger" data-label="area" data-what="areas"></span></div>
                                    <ul id="area-list">
                                        <li>
                                            <div id="area-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Subject areas</span><span class="context-menu-trigger" data-label="area" data-what="areas"></span></div>
                                        </li>
                                    </ul>

                                </li> -->
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </td>


    <td class="border_layout_center">
        <div class="border_layout_center" >
            <div id="diagram_container" >
                    <div class="tabs">


                        """),format.raw/*239.1*/("""                        """),_display_(/*239.26*/for((subject,sessions) <- sub_sess) yield /*239.61*/{_display_(Seq[Any](format.raw/*239.62*/("""
                        """),format.raw/*240.25*/("""<ul class="tab-links">
                            """),_display_(/*241.30*/for(session <- sessions) yield /*241.54*/{_display_(Seq[Any](format.raw/*241.55*/("""
                            """),_display_(/*242.30*/if(i == 1)/*242.40*/ {_display_(Seq[Any](format.raw/*242.42*/("""
                            """),format.raw/*243.29*/("""<li class="active"><a  href="#"""),_display_(/*243.60*/session),format.raw/*243.67*/("""" id="link"""),_display_(/*243.78*/session),format.raw/*243.85*/("""" class="onesignal" subject=""""),_display_(/*243.115*/subject),format.raw/*243.122*/("""" session=""""),_display_(/*243.134*/session),format.raw/*243.141*/("""" studyId="""),_display_(/*243.152*/studyNo),format.raw/*243.159*/("""  """),format.raw/*243.161*/("""occupied ="no">"""),_display_(/*243.177*/session),format.raw/*243.184*/("""</a></li>
                            """)))}/*244.31*/else/*244.36*/{_display_(Seq[Any](format.raw/*244.37*/("""
                            """),format.raw/*245.29*/("""<li><a  href="#"""),_display_(/*245.45*/session),format.raw/*245.52*/("""" id="link"""),_display_(/*245.63*/session),format.raw/*245.70*/("""" class="onesignal" subject=""""),_display_(/*245.100*/subject),format.raw/*245.107*/("""" session=""""),_display_(/*245.119*/session),format.raw/*245.126*/("""" studyId="""),_display_(/*245.137*/studyNo),format.raw/*245.144*/("""  """),format.raw/*245.146*/("""occupied ="no">"""),_display_(/*245.162*/session),format.raw/*245.169*/("""</a></li>
                            """)))}),format.raw/*246.30*/("""
                                """),_display_(/*247.34*/{i = i + 1;}),format.raw/*247.46*/("""
                            """)))}),format.raw/*248.30*/("""
                        """),format.raw/*249.25*/("""</ul>
                        """)))}),format.raw/*250.26*/("""

                        """),format.raw/*252.25*/("""<div class="tab-content" style="overflow:scroll; height:800px;">
                            """),_display_(/*253.30*/for((subject,sessions) <- sub_sess) yield /*253.65*/{_display_(Seq[Any](format.raw/*253.66*/("""
                                """),_display_(/*254.34*/for(session <- sessions) yield /*254.58*/{_display_(Seq[Any](format.raw/*254.59*/("""

                                """),format.raw/*256.33*/("""<div id=""""),_display_(/*256.43*/session),format.raw/*256.50*/("""" class="tab">
                                    <fieldset>
                                    <h2>Subject: """),_display_(/*258.51*/SubjectId),format.raw/*258.60*/(""" """),format.raw/*258.61*/("""</h2>
                                    <br>
                                  <div id="bio"""),_display_(/*260.48*/session),format.raw/*260.55*/("""">
                                    <table cellspacing="0" class="form_table">
                                        <tr> <h2>Biography </h2></tr>
                                        <tr class="row_1">
                                            <td class="cell_2"><h4 id = "first"""),_display_(/*264.80*/session),format.raw/*264.87*/(""""> </h4> </td>
                                            <td class="cell_2"><h4 id = "second"""),_display_(/*265.81*/session),format.raw/*265.88*/(""""> </h4> </td>
                                            <td class="cell_2"><h4 id = "third"""),_display_(/*266.80*/session),format.raw/*266.87*/(""""> </h4> </td>
                                        </tr>
                                        <tr class="row_1">
                                            <td class="cell_2"><h4 id = "fourth"""),_display_(/*269.81*/session),format.raw/*269.88*/(""""> </h4> </td>
                                            <td class="cell_2"><h4 id = "fifth"""),_display_(/*270.80*/session),format.raw/*270.87*/(""""> </h4> </td>
                                            <td class="cell_2"><h4 id = "sixth"""),_display_(/*271.80*/session),format.raw/*271.87*/(""""> </h4> </td>
                                        </tr>
                                    </table>
                                  </div>
                                    <div id="sycho"""),_display_(/*275.52*/session),format.raw/*275.59*/("""">
                                        <table cellspacing="0" class="form_table">
                                            <tr> <h2>PSYCHOMETRICS </h2></tr>
                                            <tr class="row_1">
                                                <td class="cell_2"><h4 id = "firstsycho"""),_display_(/*279.89*/session),format.raw/*279.96*/(""""> </h4> </td>
                                                <td class="cell_2"><h4 id = "secondsycho"""),_display_(/*280.90*/session),format.raw/*280.97*/(""""> </h4> </td>
                                                <td class="cell_2"><h4 id = "thirdsycho"""),_display_(/*281.89*/session),format.raw/*281.96*/(""""> </h4> </td>
                                            </tr>
                                            <tr class="row_1">
                                                <td class="cell_2"><h4 id = "fourthsycho"""),_display_(/*284.90*/session),format.raw/*284.97*/(""""> </h4> </td>
                                                <td class="cell_2"><h4 id = "fifthsycho"""),_display_(/*285.89*/session),format.raw/*285.96*/(""""> </h4> </td>
                                                <td class="cell_2"><h4 id = "sixthsycho"""),_display_(/*286.89*/session),format.raw/*286.96*/(""""> </h4> </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <br><br>

                                    <div>

                                        """),_display_(/*294.42*/for(signal <- signals) yield /*294.64*/{_display_(Seq[Any](format.raw/*294.65*/("""
                                        """),_display_(/*295.42*/if(signal < 8)/*295.56*/ {_display_(Seq[Any](format.raw/*295.58*/("""
                                        """),format.raw/*296.41*/("""<div id="Combo" style="border-style: solid;  border-radius: 15px; border-color: #636363;">

                                                <div  id="dashboard_div"""),_display_(/*298.73*/session),_display_(/*298.81*/signal),format.raw/*298.87*/("""">
                                                    <div id="chart"""),_display_(/*299.68*/session),_display_(/*299.76*/signal),format.raw/*299.82*/("""" class="chart" style="width: 915px; height: 200px;" subject=""""),_display_(/*299.145*/subject),format.raw/*299.152*/("""" session=""""),_display_(/*299.164*/session),format.raw/*299.171*/("""" signal=""""),_display_(/*299.182*/signal),format.raw/*299.188*/("""">
                                                    </div>
                                                    <div id="filter_div"""),_display_(/*301.73*/session),_display_(/*301.81*/signal),format.raw/*301.87*/("""" style="width: 915px; height: 50px;"></div>
                                                </div>
                                                <br>
                                                <!-- &nbsp; &nbsp; &nbsp; &nbsp;  <a id = "dynamic"""),_display_(/*304.100*/session),_display_(/*304.108*/signal),format.raw/*304.114*/("""">Dynamic</a><button id = "showvideo"""),_display_(/*304.151*/session),_display_(/*304.159*/signal),format.raw/*304.165*/("""">Show Videos</button><button id = "editchart"""),_display_(/*304.211*/session),_display_(/*304.219*/signal),format.raw/*304.225*/("""">Edit Chart</button>  <br> -->
                                                &nbsp; &nbsp; &nbsp; &nbsp;  <a id = "dynamic"""),_display_(/*305.95*/session),_display_(/*305.103*/signal),format.raw/*305.109*/("""">Dynamic</a><a id = "showvideo"""),_display_(/*305.141*/session),_display_(/*305.149*/signal),format.raw/*305.155*/("""">Show Videos</a><a id = "editchart"""),_display_(/*305.191*/session),_display_(/*305.199*/signal),format.raw/*305.205*/("""">Edit Chart</a>  <br>
                                            <br>
                                                <div id="videoboard"""),_display_(/*307.69*/session),_display_(/*307.77*/signal),format.raw/*307.83*/("""" name="videoboard"""),_display_(/*307.102*/session),_display_(/*307.110*/signal),format.raw/*307.116*/("""" style="position:relative; left: 40px; width: 915px; height: 300px; overflow-x:scroll; white-space: nowrap;" hidden>
                                                    <!-- <video id= "video"""),_display_(/*308.76*/session),format.raw/*308.83*/("""" width="350px" controls preload="auto" muted> Your browser does not support HTML5 video.
                                                    </video>
                                                    <video id= "video1"""),_display_(/*310.72*/session),format.raw/*310.79*/("""" width="350px" controls preload="auto" muted> Your browser does not support HTML5 video.
                                                    </video> -->
                                                    """),_display_(/*312.54*/{import java.math.BigInteger; var i=1}),format.raw/*312.92*/(""";
                                                    """),_display_(/*313.54*/for((sess,videoList) <- videos) yield /*313.85*/{_display_(Seq[Any](format.raw/*313.86*/("""
                                                    """),_display_(/*314.54*/if(sess == session)/*314.73*/ {_display_(Seq[Any](format.raw/*314.75*/("""
                                                    """),_display_(/*315.54*/for(video <- videoList) yield /*315.77*/{_display_(Seq[Any](format.raw/*315.78*/("""
                                                    """),_display_(/*316.54*/if(study_type == 1)/*316.73*/ {_display_(Seq[Any](format.raw/*316.75*/("""
                                                    """),format.raw/*317.53*/("""<object id=""""),_display_(/*317.66*/video),format.raw/*317.71*/("""">
                                                        <param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param>
                                                        <param name='allowFullScreen' value='true'></param>
                                                        <param name='allowScriptAccess' value='always'></param>
                                                        <embed id='video"""),_display_(/*321.74*/video),_display_(/*321.80*/signal),format.raw/*321.86*/("""' src='https://video.google.com/get_player?docid="""),_display_(/*321.136*/video),format.raw/*321.141*/("""&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1&amp;color=white&amp;autoplay=0&amp;enablejsapi=1&amp;playerapiid=video"""),_display_(/*321.263*/video),_display_(/*321.269*/signal),format.raw/*321.275*/("""' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width='350' height='250'></embed>
                                                    </object>
                                                    """)))}/*323.55*/else/*323.60*/{_display_(Seq[Any](format.raw/*323.61*/("""
                                                    """),format.raw/*324.53*/("""<object width='400' height='250' id=""""),_display_(/*324.91*/video),format.raw/*324.96*/("""">
                                                        <embed src='"""),_display_(/*325.70*/video),format.raw/*325.75*/("""' type='video/x-ms-wm' allowfullscreen='true' play ='false' autostart="false"  allowScriptAccess='always' width='350' height='250'></embed></object>
                                                    """)))}),format.raw/*326.54*/("""
                                                    """),_display_(/*327.54*/{i = i + 1;}),format.raw/*327.66*/("""
                                                    """)))}),format.raw/*328.54*/("""
                                                    """)))}),format.raw/*329.54*/("""
                                                    """)))}),format.raw/*330.54*/("""
                                                    """),format.raw/*331.53*/("""<br>
                                                    <a id = "stopvideo"""),_display_(/*332.72*/session),_display_(/*332.80*/signal),format.raw/*332.86*/("""">Stop Videos</a>
                                                </div>
                                           <!-- <div id="fooObject">
                                                <p> p</p>
                                            </div> -->
                                        </div>
                                        <br><br><br> <br><br><br>
                                               """)))}),format.raw/*339.49*/("""
                                            """)))}),format.raw/*340.46*/("""


                                        """),format.raw/*343.41*/("""&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                    </div>
                                    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                                        </fieldset>
                                </div>
                               """)))}),format.raw/*348.33*/("""
                            """)))}),format.raw/*349.30*/("""

                        """),format.raw/*351.25*/("""</div>
                    </div>


            </div>
        </div>
    </td>


</tr>

</table>

</div>
</body>
</html>"""))}
  }

  def render(SubjectId:String,sub_sess:Map[String, List[String]],studyNo:Int,userName:String,signals:List[Int],videos:Map[String, List[String]],study_type:Int,sub_sess_menu:Map[String, List[String]],studyName:String): play.twirl.api.HtmlFormat.Appendable = apply(SubjectId,sub_sess,studyNo,userName,signals,videos,study_type,sub_sess_menu,studyName)

  def f:((String,Map[String, List[String]],Int,String,List[Int],Map[String, List[String]],Int,Map[String, List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (SubjectId,sub_sess,studyNo,userName,signals,videos,study_type,sub_sess_menu,studyName) => apply(SubjectId,sub_sess,studyNo,userName,signals,videos,study_type,sub_sess_menu,studyName)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:26:01 CDT 2015
                  SOURCE: C:/first_play/app/views/subject.scala.html
                  HASH: dec2dfb4d6d6f9bb3d9bbc7754c119d34a0cb7fa
                  MATRIX: 617->1|965->223|995->227|1170->374|1199->375|1236->385|1560->682|1589->683|1624->691|2640->1680|2655->1686|2707->1717|2788->1771|2803->1777|2857->1810|3851->2776|3881->2784|7903->6776|7934->6777|8054->6867|8085->6868|8220->6973|8251->6974|8412->7105|8443->7106|8815->7448|8846->7449|8936->7509|8967->7510|9361->7874|9392->7875|9512->7965|9543->7966|9678->8071|9709->8072|10000->8333|10031->8334|10397->8670|10428->8671|10548->8761|10579->8762|10714->8867|10745->8868|10908->9001|10939->9002|15532->13565|15563->13566|15683->13656|15714->13657|15849->13762|15880->13763|16043->13896|16074->13897|17218->15012|17248->15013|17279->15014|17313->15018|17344->15019|17375->15020|17546->15163|17577->15172|17607->15173|17663->15201|17692->15208|18254->15742|18284->15749|18359->15796|18416->15836|18456->15837|18527->15879|18604->15928|18633->15935|18890->16163|18920->16170|18961->16182|18991->16189|19023->16192|19053->16199|19084->16200|19310->16398|19351->16422|19391->16423|19470->16473|19571->16545|19601->16552|19648->16570|19678->16578|19708->16585|19767->16615|19797->16622|19828->16623|19866->16632|19896->16639|20164->16878|20194->16885|20235->16897|20265->16904|20296->16906|20326->16913|20358->16916|20388->16923|20419->16924|20612->17089|20641->17097|20670->17104|21015->17421|21045->17428|21076->17429|21114->17438|21144->17445|21175->17446|21824->18066|21854->18073|22034->18221|22111->18269|22238->18364|22309->18406|25138->21247|25191->21272|25243->21307|25283->21308|25338->21334|25419->21387|25460->21411|25500->21412|25559->21443|25579->21453|25620->21455|25679->21485|25738->21516|25767->21523|25806->21534|25835->21541|25894->21571|25924->21578|25965->21590|25995->21597|26035->21608|26065->21615|26097->21617|26142->21633|26172->21640|26232->21681|26246->21686|26286->21687|26345->21717|26389->21733|26418->21740|26457->21751|26486->21758|26545->21788|26575->21795|26616->21807|26646->21814|26686->21825|26716->21832|26748->21834|26793->21850|26823->21857|26895->21897|26958->21932|26992->21944|27055->21975|27110->22001|27174->22033|27231->22061|27354->22156|27406->22191|27446->22192|27509->22227|27550->22251|27590->22252|27655->22288|27693->22298|27722->22305|27864->22419|27895->22428|27925->22429|28049->22525|28078->22532|28400->22826|28429->22833|28553->22929|28582->22936|28705->23031|28734->23038|28965->23241|28994->23248|29117->23343|29146->23350|29269->23445|29298->23452|29528->23654|29557->23661|29904->23980|29933->23987|30066->24092|30095->24099|30227->24203|30256->24210|30504->24430|30533->24437|30665->24541|30694->24548|30826->24652|30855->24659|31178->24954|31217->24976|31257->24977|31328->25020|31352->25034|31393->25036|31464->25078|31658->25244|31687->25252|31715->25258|31814->25329|31843->25337|31871->25343|31963->25406|31993->25413|32034->25425|32064->25432|32104->25443|32133->25449|32297->25585|32326->25593|32354->25599|32638->25854|32668->25862|32697->25868|32763->25905|32793->25913|32822->25919|32897->25965|32927->25973|32956->25979|33111->26106|33141->26114|33170->26120|33231->26152|33261->26160|33290->26166|33355->26202|33385->26210|33414->26216|33584->26358|33613->26366|33641->26372|33689->26391|33719->26399|33748->26405|33970->26599|33999->26606|34251->26830|34280->26837|34518->27047|34578->27085|34662->27141|34710->27172|34750->27173|34833->27228|34862->27247|34903->27249|34986->27304|35026->27327|35066->27328|35149->27383|35178->27402|35219->27404|35302->27458|35343->27471|35370->27476|35912->27990|35939->27996|35967->28002|36046->28052|36074->28057|36225->28179|36253->28185|36282->28191|36542->28432|36556->28437|36596->28438|36679->28492|36745->28530|36772->28535|36873->28608|36900->28613|37135->28816|37218->28871|37252->28883|37339->28938|37426->28993|37513->29048|37596->29102|37701->29179|37730->29187|37758->29193|38213->29616|38292->29663|38367->29709|38862->30172|38925->30203|38982->30231
                  LINES: 19->1|22->1|24->3|31->10|31->10|32->11|43->22|43->22|45->24|61->40|61->40|61->40|62->41|62->41|62->41|83->62|83->62|134->113|134->113|134->113|134->113|134->113|134->113|134->113|134->113|138->117|138->117|138->117|138->117|141->120|141->120|141->120|141->120|141->120|141->120|141->120|141->120|142->121|142->121|142->121|142->121|142->121|142->121|142->121|142->121|166->145|166->145|166->145|166->145|166->145|166->145|166->145|166->145|188->167|188->167|188->167|188->167|188->167|188->167|191->170|191->170|191->170|191->170|191->170|196->175|196->175|197->176|197->176|197->176|198->177|198->177|198->177|199->178|199->178|199->178|199->178|199->178|199->178|199->178|201->180|201->180|201->180|202->181|202->181|202->181|202->181|202->181|202->181|202->181|202->181|202->181|202->181|202->181|204->183|204->183|204->183|204->183|204->183|204->183|204->183|204->183|204->183|205->184|205->184|205->184|208->187|208->187|208->187|208->187|208->187|208->187|216->195|216->195|217->196|219->198|221->200|222->201|259->239|259->239|259->239|259->239|260->240|261->241|261->241|261->241|262->242|262->242|262->242|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|263->243|264->244|264->244|264->244|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|265->245|266->246|267->247|267->247|268->248|269->249|270->250|272->252|273->253|273->253|273->253|274->254|274->254|274->254|276->256|276->256|276->256|278->258|278->258|278->258|280->260|280->260|284->264|284->264|285->265|285->265|286->266|286->266|289->269|289->269|290->270|290->270|291->271|291->271|295->275|295->275|299->279|299->279|300->280|300->280|301->281|301->281|304->284|304->284|305->285|305->285|306->286|306->286|314->294|314->294|314->294|315->295|315->295|315->295|316->296|318->298|318->298|318->298|319->299|319->299|319->299|319->299|319->299|319->299|319->299|319->299|319->299|321->301|321->301|321->301|324->304|324->304|324->304|324->304|324->304|324->304|324->304|324->304|324->304|325->305|325->305|325->305|325->305|325->305|325->305|325->305|325->305|325->305|327->307|327->307|327->307|327->307|327->307|327->307|328->308|328->308|330->310|330->310|332->312|332->312|333->313|333->313|333->313|334->314|334->314|334->314|335->315|335->315|335->315|336->316|336->316|336->316|337->317|337->317|337->317|341->321|341->321|341->321|341->321|341->321|341->321|341->321|341->321|343->323|343->323|343->323|344->324|344->324|344->324|345->325|345->325|346->326|347->327|347->327|348->328|349->329|350->330|351->331|352->332|352->332|352->332|359->339|360->340|363->343|368->348|369->349|371->351
                  -- GENERATED --
              */
          