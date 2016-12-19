
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
object studyModel extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,Map[String, List[String]],Int,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, sub_sess: Map[String, List[( String)]], studyNo:Int):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;

Seq[Any](format.raw/*1.73*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>StressBook</title>
    <meta content="width=1170" name="viewport">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"><meta content="IRQcweD5CIs6Qc-uVi9xT3BD-1jPvDxyk3-9LUah9T8" name="google-site-verification">
    <link href="/assets/images/favicon.png" rel="icon" type="image/png">
    <script type="text/javascript" src="/assets/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/assets/js/ow-all.js"></script>
    <script type="text/javascript" src="/assets/js/webapp.js"></script>
    <script type="text/javascript" src="/assets/js/browser-features.js"></script>
    <script type="text/javascript" src="/assets/js/common.js"></script>
    <!-- <script type="text/javascript" src="/assets/js/notifications.js"></script> -->
    <!-- <script type="text/javascript" src="/assets/js/modeler/lib/jquery.caret.1.02.js"></script> -->
    <!--<script type="text/javascript" src="/assets/js/modeler/lib/jquery.mousewheel.min.js"></script> -->
    <script type="text/javascript" src="/assets/js/modeler/lib/jquery.cookie.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/kinetic-v4.7.4.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/underscore.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/backbone.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/undomanager.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/dbwm.check.js"></script>
    <script type="text/javascript" src="/assets/js/modeler/lib/codemirror/codemirror.js"></script>
    <!--<script type="text/javascript" src="/assets/js/modeler/lib/codemirror/sql.js"></script> -->
    <script type="text/javascript" src="/assets/js/modeler/lib/codemirror/fullscreen.js"></script>
    <!--  <script type="text/javascript" src="/assets/js/modeler/lib/codemirror/vertabelo-editor.js"></script> -->
    <!--   <script type="text/javascript" src="/assets/js/modeler/lib/sql-parser-0.4.1.js"></script> -->
       <script type="text/javascript" src="/assets/js/modeler/lib/intro-0.8.0.min.js"></script>
       <script type="text/javascript" src="/assets/js/modeler/lib/highlight.min.js"></script>
       <script type="text/javascript" src="/assets/js/modeler/lib/dbwm-extras.js"></script>
       <script type="text/javascript" src="/assets/js/modeler/modeler.js"></script>
       <script type="text/javascript" src="/assets/js/my_models.js"></script>


       <script type="text/javascript" src="https://www.google.com/jsapi"></script>
       <script type='text/javascript' src='"""),_display_(/*38.45*/routes/*38.51*/.Assets.at("js/drawchart.js")),format.raw/*38.80*/("""'></script>
       <script type='text/javascript' src='"""),_display_(/*39.45*/routes/*39.51*/.Assets.at("js/changevideo.js")),format.raw/*39.82*/("""'></script>
       <script type='text/javascript' src='"""),_display_(/*40.45*/routes/*40.51*/.Assets.at("js/run_activation.js")),format.raw/*40.85*/("""'></script>




     <link rel="stylesheet" href="/assets/stylesheets/portrat.css" type="text/css" />


    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/dbwm.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/jquery-ui-1.9.2.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/modeler.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/codemirror/codemirror.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/codemirror/fullscreen.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/codemirror/vertabelo-editor.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/qunit-1.14.0.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/introjs-0.8.0.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/highlight-railscasts.min.css"><!-- start Mixpanel --><script content-type="text/javascript">

              (function(e,b)"""),format.raw/*60.29*/("""{"""),format.raw/*60.30*/("""if(!b.__SV)"""),format.raw/*60.41*/("""{"""),format.raw/*60.42*/("""var a,f,i,g;window.mixpanel=b;a=e.createElement('script');a.type='text/javascript';a.async=!0;a.src=('https:'===e.location.protocol?'https:':'http:')+'//cdn.mxpnl.com/libs/mixpanel-2.2.min.js';f=e.getElementsByTagName('script')[0];f.parentNode.insertBefore(a,f);b._i=[];b.init=function(a,e,d)"""),format.raw/*60.334*/("""{"""),format.raw/*60.335*/("""function f(b,h)"""),format.raw/*60.350*/("""{"""),format.raw/*60.351*/("""var a=h.split('.');2==a.length&&(b=b[a[0]],h=a[1]);b[h]=function()"""),format.raw/*60.417*/("""{"""),format.raw/*60.418*/("""b.push([h].concat(Array.prototype.slice.call(arguments,0)))"""),format.raw/*60.477*/("""}"""),format.raw/*60.478*/("""}"""),format.raw/*60.479*/("""var c=b;'undefined'!==
            typeof d?c=b[d]=[]:d='mixpanel';c.people=c.people||[];c.toString=function(b)"""),format.raw/*61.89*/("""{"""),format.raw/*61.90*/("""var a='mixpanel';'mixpanel'!==d&&(a+='.'+d);b||(a+=' (stub)');return a"""),format.raw/*61.160*/("""}"""),format.raw/*61.161*/(""";c.people.toString=function()"""),format.raw/*61.190*/("""{"""),format.raw/*61.191*/("""return c.toString(1)+'.people (stub)'"""),format.raw/*61.228*/("""}"""),format.raw/*61.229*/(""";i='disable track track_pageview track_links track_forms register register_once alias unregister identify name_tag set_config people.set people.set_once people.increment people.append people.track_charge people.clear_charges people.delete_user'.split(' ');for(g=0;g<i.length;g++)f(c,i[g]);
            b._i.push([a,e,d])"""),format.raw/*62.31*/("""}"""),format.raw/*62.32*/(""";b.__SV=1.2"""),format.raw/*62.43*/("""}"""),format.raw/*62.44*/("""}"""),format.raw/*62.45*/(""")(document,window.mixpanel||[]);


              mixpanel.init("7db79ecd16267922e52d126972ff304e");

              </script><script content-type="text/javascript">mixpanel.people.set("""),format.raw/*67.83*/("""{"""),format.raw/*67.84*/(""""$email": "taamneh_07@hotmail.com",
                                    "Account Plan": "Free",
                                    "Is Trial Plan": false,
                                    "Account Plan Valid To": "",
                                    "Last Login Date": "2014-09-17T21:06:57",
                                    "Active Models":2,
                                    "Largest Own Model":7,
                                    "Sharing Degree":0
                             """),format.raw/*75.30*/("""}"""),format.raw/*75.31*/(""");mixpanel.identify("pr-18363");mixpanel.track_pageview();</script><!-- end Mixpanel -->
    <script type='text/javascript'>    var reader = document.createElement('a');    reader.href = document.location.href;    var ary = reader.pathname.split('/');    var p = null;    if(ary.length > 1) """),format.raw/*76.203*/("""{"""),format.raw/*76.204*/("""        """),format.raw/*76.212*/("""p= '/' + ary[1];    """),format.raw/*76.232*/("""}"""),format.raw/*76.233*/("""    """),format.raw/*76.237*/("""var _gaq = _gaq || [];    _gaq.push(['_setAccount', 'UA-42689964-1']);    _gaq.push(['_setDomainName', 'vertabelo.com']);    _gaq.push(['_setAllowLinker', true]);    if(p = null) """),format.raw/*76.416*/("""{"""),format.raw/*76.417*/("""      """),format.raw/*76.423*/("""_gaq.push(['_trackPageview', p]);    """),format.raw/*76.460*/("""}"""),format.raw/*76.461*/(""" """),format.raw/*76.462*/("""else """),format.raw/*76.467*/("""{"""),format.raw/*76.468*/("""      """),format.raw/*76.474*/("""_gaq.push(['_trackPageview']);    """),format.raw/*76.508*/("""}"""),format.raw/*76.509*/("""    """),format.raw/*76.513*/("""(function() """),format.raw/*76.525*/("""{"""),format.raw/*76.526*/("""      """),format.raw/*76.532*/("""var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;      ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);    """),format.raw/*76.834*/("""}"""),format.raw/*76.835*/(""")();
							 </script>
</head>
<body>
<!-- <div id="fusebox_ajax_fuse" style="display: none;"></div> -->
<!-- <div class="tooltip"><div class="content"></div> -->
<!-- <div class="decor"></div></div> -->
<div id="wrapper">
<table class="border_layout" style="border:1px solid;width:100%;">
<tr>
    <td class="border_layout_north" colspan="3">
        <div class="border_layout_north">
            <div id="header_wrapper">


                <div id="header">
                    <a class="logo" href="http://www.cpl.uh.edu/"><img alt="Vertabelo Logo" width="70" height="40"  src="/assets/images/cpllogo.png"></a>
                    <ul class="user_menu">
                        <li class="user dropdown_holder"><span class="dropdown"><span>"""),_display_(/*94.88*/userName),format.raw/*94.96*/("""</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/my-account?action=flow.reset&amp;flow.flow=my_account" id="btn31"><span>My account</span></a>
                            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*95.120*/("""{"""),format.raw/*95.121*/("""epoint.ow.Utils.bindEvent("click","btn31","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*95.209*/("""}"""),format.raw/*95.210*/(""",90,null);</script> -->
                            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*96.120*/("""{"""),format.raw/*96.121*/("""epoint.ow.Utils.bindEvent("click","btn31","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*96.212*/("""}"""),format.raw/*96.213*/(""",null,null);</script> -->
                        </li>
                        </ul>
                        </div>
                        </div>
                        </li>
                        <li class="notifications"><div id="flow_notifications_layer_div"><div class="menu_item dropdown_holder"><a class="dropdown notifications" href="?action=flow.transit&amp;flow.flow=notifications_layer&amp;flow.transition=expanded&amp;flow.reloadFlow=notifications_layer" id="btn32"><span></span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*102.408*/("""{"""),format.raw/*102.409*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*102.497*/("""}"""),format.raw/*102.498*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*102.603*/("""{"""),format.raw/*102.604*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*102.691*/("""}"""),format.raw/*102.692*/(""",null,null);
                             </script></div>
                       <!--  <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.116*/("""{"""),format.raw/*104.117*/("""pl.epoint.dbwm.war_user.notifications.startNotificationsLayerRefresh("?action=flow.transit&flow.flow=notifications_layer&flow.transition=refresh&flow.reloadFlow=notifications_layer&flow.disableMessagesAddons=true");"""),format.raw/*104.332*/("""}"""),format.raw/*104.333*/(""",null,null);</script> -->
                    </div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*105.113*/("""{"""),format.raw/*105.114*/("""epoint.ow.flow.Utils.setCurrentState("notifications_layer","collapsed");"""),format.raw/*105.186*/("""}"""),format.raw/*105.187*/(""",null,null);</script></li><li class="logout"><a href="/logout" id="btn33"><span>Log out</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*105.371*/("""{"""),format.raw/*105.372*/("""epoint.ow.Utils.bindEvent("click","btn33","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*105.460*/("""}"""),format.raw/*105.461*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*105.566*/("""{"""),format.raw/*105.567*/("""epoint.ow.Utils.bindEvent("click","btn33","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*105.658*/("""}"""),format.raw/*105.659*/(""",null,null);</script></li>
                    </ul>
                    <ul class="links">
                        <li class="dropdown_holder expandable expand-priority-1"><span class="dropdown"><span>Modules</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/diagram">Diagram</a></li><li><a href="/allStudies" id="btn34"><span>My models</span></a></li><li><a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn35"><span>Sharing</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*108.504*/("""{"""),format.raw/*108.505*/("""epoint.ow.Utils.bindEvent("click","btn35","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*108.593*/("""}"""),format.raw/*108.594*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*108.699*/("""{"""),format.raw/*108.700*/("""epoint.ow.Utils.bindEvent("click","btn35","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*108.791*/("""}"""),format.raw/*108.792*/(""",null,null);</script></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn36"><span>Recommend us</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*108.1017*/("""{"""),format.raw/*108.1018*/("""epoint.ow.Utils.bindEvent("click","btn36","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*108.1106*/("""}"""),format.raw/*108.1107*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*108.1212*/("""{"""),format.raw/*108.1213*/("""epoint.ow.Utils.bindEvent("click","btn36","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*108.1304*/("""}"""),format.raw/*108.1305*/(""",null,null);</script></li></ul></div></div></li><li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span>
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
																	"""),format.raw/*130.18*/("""{"""),format.raw/*130.19*/("""epoint.ow.Utils.bindEvent("click","btn37","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*130.107*/("""}"""),format.raw/*130.108*/(""",90,null);
																</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																	"""),format.raw/*133.18*/("""{"""),format.raw/*133.19*/("""epoint.ow.Utils.bindEvent("click","btn37","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*133.110*/("""}"""),format.raw/*133.111*/(""",null,null);
																</script>
                                    </li>
                                    <li>
                                        <a href="/support/request-a-feature" id="btn38" target="_blank"><span>Request a feature</span></a>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																   """),format.raw/*139.20*/("""{"""),format.raw/*139.21*/("""epoint.ow.Utils.bindEvent("click","btn38","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*139.109*/("""}"""),format.raw/*139.110*/(""",90,null);
																</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																   """),format.raw/*142.20*/("""{"""),format.raw/*142.21*/("""epoint.ow.Utils.bindEvent("click","btn38","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*142.112*/("""}"""),format.raw/*142.113*/(""",null,null);
																</script>
                                    </li>
                                    <li>
                                        <a href="/support/report-a-problem" id="btn39" target="_blank"><span>Report a problem</span></a>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*148.17*/("""{"""),format.raw/*148.18*/("""epoint.ow.Utils.bindEvent("click","btn39","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*148.106*/("""}"""),format.raw/*148.107*/(""",90,null);
																</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*150.17*/("""{"""),format.raw/*150.18*/("""epoint.ow.Utils.bindEvent("click","btn39","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*150.109*/("""}"""),format.raw/*150.110*/(""",null,null);
																</script>
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

             <table id="toolbar_wrapper"><tr><td class="item wide">



                <div class="block_dialog_container"><div class="popup initialHide" id="popup_flow_share_model_div"><div class="popup_arrow_up"></div><a class="close-popup-button" id="btn2521"><span>X</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.297*/("""{"""),format.raw/*172.298*/("""epoint.ow.Utils.bindEvent("click","btn2521","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*172.388*/("""}"""),format.raw/*172.389*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.494*/("""{"""),format.raw/*172.495*/("""epoint.ow.Utils.bindEvent("click","btn2521","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_share_model_div",[]);"""),format.raw/*172.626*/("""}"""),format.raw/*172.627*/(""",null,null);</script><div class="clearFix"></div><div class="popup_inner"><div id="flow_share_model_div"><img src="/assets/images/empty_flow_content.gif"></div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.873*/("""{"""),format.raw/*172.874*/("""epoint.ow.flow.Utils.setCurrentState("share_model","share");"""),format.raw/*172.934*/("""}"""),format.raw/*172.935*/(""",null,null);</script></div></div></div><span id="share_model_button_wrapper"><a class="button" href="?action=flow.reset&amp;flow.flow=share_model&amp;flow.reloadFlow=share_model" id="btn2522"><span>Share model</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.1241*/("""{"""),format.raw/*172.1242*/("""epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*172.1332*/("""}"""),format.raw/*172.1333*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.1438*/("""{"""),format.raw/*172.1439*/("""epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.popup.PopupManager.openPopupAfterAjaxFlowReloadClickHandler","popup_flow_share_model_div","flow_share_model_div",[]);epoint.ow.Utils.bindEvent("click","btn2522","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*172.1700*/("""}"""),format.raw/*172.1701*/(""",null,null);</script></span><div class="block_dialog_container"><div class="popup initialHide compact" id="sql_generator_form_container"><div class="popup_arrow_up"></div><a class="close-popup-button" id="btn2524"><span>X</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.2019*/("""{"""),format.raw/*172.2020*/("""epoint.ow.Utils.bindEvent("click","btn2524","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*172.2110*/("""}"""),format.raw/*172.2111*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*172.2216*/("""{"""),format.raw/*172.2217*/("""epoint.ow.Utils.bindEvent("click","btn2524","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*172.2350*/("""}"""),format.raw/*172.2351*/(""",null,null);</script><div class="clearFix"></div><div class="popup_inner"><div class="header"><h1>Generate SQL Script</h1></div><form id="sql_generator_form" onSubmit="return false;"><div class="box_form_section"><div class="box_form_content">
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
                <a class="link" id="btn2523"><span>Cancel</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*192.155*/("""{"""),format.raw/*192.156*/("""epoint.ow.Utils.bindEvent("click","btn2523","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*192.246*/("""}"""),format.raw/*192.247*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*192.352*/("""{"""),format.raw/*192.353*/("""epoint.ow.Utils.bindEvent("click","btn2523","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*192.486*/("""}"""),format.raw/*192.487*/(""",null,null);</script></div><p class="mandatory_info">Fields marked with [<span class="mandatory">*</span>] must be filled.</p></div></div></div></td><td class="item"><div id="search-box"></div><script type="text/template" id="search-template"><form name="search"><input class="text_field" name="query" placeholder="Search (Ctrl+F)" type="text"></form></script>
            </td></tr>
            </table>
        </div>
    </td>
</tr>
<tr>
    <td class="border_layout_west" id="left-panel">
        <div class="border_layout_west">
            <div class="accordion">
                <div class="block">
                    <div class="header">
                        <h2>Study Structure</h2>
                    </div>
                    <div class="content" style="height:760px;">
                        <div id="handy-menu1"></div>
                        <script type="text/template" id="handy-menu-template">
                            <form onSubmit="return false;">
                                <button class="handy-menu-create-button" type="button">Add """),format.raw/*210.92*/("""{"""),format.raw/*210.93*/("""{"""),format.raw/*210.94*/("""what"""),format.raw/*210.98*/("""}"""),format.raw/*210.99*/("""}"""),format.raw/*210.100*/("""</button>
                                <button class="handy-menu-create-ghost-button" type="button">Add shortcut """),format.raw/*211.107*/("""{"""),format.raw/*211.108*/("""{"""),format.raw/*211.109*/("""what"""),format.raw/*211.113*/("""}"""),format.raw/*211.114*/("""}"""),format.raw/*211.115*/("""</button>
                                <button class="handy-menu-delete-button" type="button">Delete """),format.raw/*212.95*/("""{"""),format.raw/*212.96*/("""{"""),format.raw/*212.97*/("""what"""),format.raw/*212.101*/("""}"""),format.raw/*212.102*/("""}"""),format.raw/*212.103*/("""</button>
                                <button class="handy-menu-find-button" type="button">Find in diagram</button>
                                <button class="handy-menu-find-outside-area-button" type="button">Find in diagram</button>
                                <button class="handy-menu-create-table-button" type="button">Add table</button>
                                <button class="handy-menu-create-view-button" type="button">Add view</button>
                            </form>
                        </script>
                        <script type="text/template" id="handy-menu-find-in-area-template">
                            <button class="handy-menu-find-in-area-button" type="button">Find in """),format.raw/*220.98*/("""{"""),format.raw/*220.99*/("""{"""),format.raw/*220.100*/("""area"""),format.raw/*220.104*/("""}"""),format.raw/*220.105*/("""}"""),format.raw/*220.106*/("""</button>
                        </script>
                        <div class="tree">
                            <div class="tree_title">Model</div>
                            <ul>
                                <li>
                                    <div id="table-list-root-node"><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">Subjects</span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                    <ul id="table-list">
                                        <p hidden id = "studyNo" value = "123" subject ="S001">"""),_display_(/*228.97*/studyNo),format.raw/*228.104*/("""</p>
                                                   <!-- <li><input type="checkbox" id="item-0" /><label for="item-0">Children Study</label> -->
                                                    <!-- <ul class ="subjects"> -->
                                                        """),format.raw/*232.1*/("""                                                        """),_display_(/*232.58*/for((subject,sessions) <- sub_sess) yield /*232.93*/{_display_(Seq[Any](format.raw/*232.94*/("""
                                                        """),format.raw/*233.57*/("""<!-- <li><input type="checkbox" id="item-0-2" />"""),_display_(/*233.106*/subject),format.raw/*233.113*/("""<label for="item-0"></label> -->
                                                        <li> <div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label"><a href="/displaySubject?studyNo="""),_display_(/*234.211*/studyNo),format.raw/*234.218*/("""&SubjectId="""),_display_(/*234.230*/subject),format.raw/*234.237*/("""">"""),_display_(/*234.240*/subject),format.raw/*234.247*/(""" """),format.raw/*234.248*/("""</a></span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                                            <ul>
                                                                """),_display_(/*236.66*/for(session <- sessions) yield /*236.90*/{_display_(Seq[Any](format.raw/*236.91*/("""
                                                                    """),format.raw/*237.69*/("""<!-- <li><input type="checkbox" id="item-0-0-23" /><label for="item-0">"""),_display_(/*237.141*/session),format.raw/*237.148*/("""</label> <ul id=""""),_display_(/*237.166*/subject),_display_(/*237.174*/session),format.raw/*237.181*/(""""> <li class="inner" subject="""),_display_(/*237.211*/subject),format.raw/*237.218*/(""" """),format.raw/*237.219*/("""session="""),_display_(/*237.228*/session),format.raw/*237.235*/("""><a >Run 1</a></li> </ul></li> -->

                                                                <li><div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label"><a href="/displaySubject?studyNo="""),_display_(/*239.218*/studyNo),format.raw/*239.225*/("""&SubjectId="""),_display_(/*239.237*/subject),format.raw/*239.244*/("""#"""),_display_(/*239.246*/session),format.raw/*239.253*/("""">"""),_display_(/*239.256*/session),format.raw/*239.263*/(""" """),format.raw/*239.264*/("""</a></span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div>
                                                                   <!-- <ul id=""""),_display_(/*240.82*/subject),_display_(/*240.90*/session),format.raw/*240.97*/("""">
                                                                        <li>
                                                                            <div><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span>
                                                                                <div class="inner" subject="""),_display_(/*243.109*/subject),format.raw/*243.116*/(""" """),format.raw/*243.117*/("""session="""),_display_(/*243.126*/session),format.raw/*243.133*/(""" """),format.raw/*243.134*/("""> <span class="label">Run 1</span> </div>

                                                                                <span class="context-menu-trigger" data-label="table" data-what="tables"></span>
                                                                            </div>
                                                                        </li>
                                                                    </ul> -->
                                                                </li>

                                                                <!-- <li> <div ><span class="control"></span><span class="icon"><span class="icon_overlay"></span></span><span class="label">"""),_display_(/*251.191*/subject),format.raw/*251.198*/("""</span><span class="context-menu-trigger" data-label="table" data-what="tables"></span></div> -->
                                                                """)))}),format.raw/*252.66*/("""

                                                             """),format.raw/*254.62*/("""</ul>
                                                        </li>
                                                        """)))}),format.raw/*256.58*/("""
                                                   """),format.raw/*257.52*/("""<!-- </ul> -->
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
    <!--<td  height="100%"><div  height ="100%">
        <div  id="messages1" style="display:none"></div>
        <div id="parent1" style="border:1px solid;width:100%; float: left" >
            <br>
            <div style="float:left;width:20%;height:1000px;">

            </div>
            <div class="SubjectsDock">


            </div>
    </div>
    </div>
    </td> -->

    <td class="border_layout_center">
        <div class="border_layout_center" >
            <!-- <div class="messages" id="messages" style="display:none"></div> -->
            <div id="diagram_container" >
                <!-- <div id="progress_bar_layer">
                    <div class="progress_bar_wrapper">
                        <div id="progress_bar_content">
                            <span cl
                            ass="progress_bar"></span>
                            <div class="clearFix"></div>
                            <progress max="100" value="1">
                            </progress>
                        </div>
                    </div>
                </div> -->
                <div id="diagram" tabindex="1" >

                    """),_display_(/*318.22*/for((subject,sessions) <- sub_sess) yield /*318.57*/{_display_(Seq[Any](format.raw/*318.58*/("""
                    """),format.raw/*319.21*/("""<div class="vid">
                        <header class="vidTop"><a href="/manchester-city-7-0-sheffield-wednesday-2014-09/">"""),_display_(/*320.109*/subject),format.raw/*320.116*/("""</a></header>
                        <div class="vidthumb">
                            <a href="/displaySubject?studyNo="""),_display_(/*322.63*/studyNo),format.raw/*322.70*/("""&SubjectId="""),_display_(/*322.82*/subject),format.raw/*322.89*/("""">
                                <img src="/assets/images/female.jpg" />
                            </a>
                        </div>
                        <footer class="vidBot">

                            <!-- <div class="postInfo">
                                <div class="views-count"><span class="icon"></span>64050</div>			<div class="comments-count"><span class="icon"></span>55</div>		</div> -->

                            <!-- <span class="vid_category"><a href="/videos/england/carlingcup/">Capital One Cup</a></span> -->
		                    <span class="time_added">
			                    """),_display_(/*333.25*/subject),format.raw/*333.32*/("""		"""),format.raw/*333.34*/("""</span>
                        </footer>
                    </div>
                    """)))}),format.raw/*336.22*/("""

                """),format.raw/*338.17*/("""</div>
                <div id="preview">

                </div>
                <div id="diagram_topbar">
                    <!-- <div class="item">
                        <div id="toolbox-container" style="display: none;"></div>
                        <script type="text/template" id="toolbox-template">
                            <div><button type="button" class="select_mode_button" data-tooltip="(1) Select"><span class="icon"></span><span class="label">(1) Select</span></button>
                                <button type="button" class="select_rect_mode_button" data-tooltip="(2) Select area"><span class="icon"></span><span class="label">(2) Select area</span></button>
                                <button type="button" class="add_table_mode_button" data-tooltip="(3) Add new table"><span class="icon"></span><span class="label">(3) Add new table</span></button>
                                <button type="button" class="add_relationship_mode_button" data-tooltip="(4) Add new reference"><span class="icon"></span><span class="label">(4) Add new reference</span></button>
                                <button type="button" class="add_view_button" data-tooltip="(5) Add new view"><span class="icon"></span><span class="label">(5) Add new view</span></button>
                                <button type="button" class="add_note_button" data-tooltip="(6) Add new note"><span class="icon"></span><span class="label">(6) Add new note</span></button>
                                <button type="button" class="add_area_button" data-tooltip="(7) Add new area"><span class="icon"></span><span class="label">(7) Add new area</span></button>
                            </div>
                        </script>
                    </div> -->
                    <div class="item wide"><div id="notification-container" style="display:none"><div class="content"></div></div></div>
                </div>
            </div>
            <div id="diagram_as_image" style="display:none">
            </div>
        </div>
    </td>


</tr>

</table>

</div>
</body>
</html>"""))}
  }

  def render(userName:String,sub_sess:Map[String, List[String]],studyNo:Int): play.twirl.api.HtmlFormat.Appendable = apply(userName,sub_sess,studyNo)

  def f:((String,Map[String, List[String]],Int) => play.twirl.api.HtmlFormat.Appendable) = (userName,sub_sess,studyNo) => apply(userName,sub_sess,studyNo)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Mar 30 23:41:19 CDT 2016
                  SOURCE: C:/first_play/app/views/studyModel.scala.html
                  HASH: 6692ee1fd5c39180fc082bc5c3c69bbed7f95e6b
                  MATRIX: 540->1|736->72|764->74|3607->2890|3622->2896|3672->2925|3755->2981|3770->2987|3822->3018|3905->3074|3920->3080|3975->3114|5198->4309|5227->4310|5266->4321|5295->4322|5616->4614|5646->4615|5690->4630|5720->4631|5815->4697|5845->4698|5933->4757|5963->4758|5993->4759|6132->4870|6161->4871|6260->4941|6290->4942|6348->4971|6378->4972|6444->5009|6474->5010|6822->5330|6851->5331|6890->5342|6919->5343|6948->5344|7159->5527|7188->5528|7713->6026|7742->6027|8062->6318|8092->6319|8129->6327|8178->6347|8208->6348|8241->6352|8449->6531|8479->6532|8514->6538|8580->6575|8610->6576|8640->6577|8674->6582|8704->6583|8739->6589|8802->6623|8832->6624|8865->6628|8906->6640|8936->6641|8971->6647|9302->6949|9332->6950|10103->7694|10132->7702|10477->8018|10507->8019|10624->8107|10654->8108|10826->8251|10856->8252|10976->8343|11006->8344|11621->8929|11652->8930|11770->9018|11801->9019|11936->9124|11967->9125|12084->9212|12115->9213|12318->9386|12349->9387|12594->9602|12625->9603|12793->9741|12824->9742|12926->9814|12957->9815|13171->9999|13202->10000|13320->10088|13351->10089|13486->10194|13517->10195|13638->10286|13669->10287|14294->10882|14325->10883|14443->10971|14474->10972|14609->11077|14640->11078|14761->11169|14792->11170|15048->11395|15080->11396|15199->11484|15231->11485|15367->11590|15399->11591|15521->11682|15553->11683|17276->13377|17306->13378|17424->13466|17455->13467|17664->13647|17694->13648|17815->13739|17846->13740|18281->14146|18311->14147|18429->14235|18460->14236|18671->14418|18701->14419|18822->14510|18853->14511|19283->14912|19313->14913|19431->15001|19462->15002|19629->15140|19659->15141|19780->15232|19811->15233|20631->16023|20662->16024|20782->16114|20813->16115|20948->16220|20979->16221|21140->16352|21171->16353|21447->16599|21478->16600|21568->16660|21599->16661|21936->16967|21968->16968|22089->17058|22121->17059|22257->17164|22289->17165|22581->17426|22613->17427|22962->17745|22994->17746|23115->17836|23147->17837|23283->17942|23315->17943|23479->18076|23511->18077|28008->22544|28039->22545|28159->22635|28190->22636|28325->22741|28356->22742|28519->22875|28550->22876|29649->23946|29679->23947|29709->23948|29742->23952|29772->23953|29803->23954|29949->24070|29980->24071|30011->24072|30045->24076|30076->24077|30107->24078|30240->24182|30270->24183|30300->24184|30334->24188|30365->24189|30396->24190|31149->24914|31179->24915|31210->24916|31244->24920|31275->24921|31306->24922|31986->25574|32016->25581|32333->25909|32418->25966|32470->26001|32510->26002|32596->26059|32674->26108|32704->26115|32976->26358|33006->26365|33047->26377|33077->26384|33109->26387|33139->26394|33170->26395|33426->26623|33467->26647|33507->26648|33605->26717|33706->26789|33736->26796|33783->26814|33813->26822|33843->26829|33902->26859|33932->26866|33963->26867|34001->26876|34031->26883|34313->27136|34343->27143|34384->27155|34414->27162|34445->27164|34475->27171|34507->27174|34537->27181|34568->27182|34775->27361|34804->27369|34833->27376|35221->27735|35251->27742|35282->27743|35320->27752|35350->27759|35381->27760|36114->28464|36144->28471|36339->28634|36431->28697|36588->28822|36669->28874|40431->32608|40483->32643|40523->32644|40573->32665|40728->32791|40758->32798|40909->32921|40938->32928|40978->32940|41007->32947|41653->33565|41682->33572|41713->33574|41835->33664|41882->33682
                  LINES: 19->1|22->1|24->3|59->38|59->38|59->38|60->39|60->39|60->39|61->40|61->40|61->40|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|81->60|82->61|82->61|82->61|82->61|82->61|82->61|82->61|82->61|83->62|83->62|83->62|83->62|83->62|88->67|88->67|96->75|96->75|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|97->76|115->94|115->94|116->95|116->95|116->95|116->95|117->96|117->96|117->96|117->96|123->102|123->102|123->102|123->102|123->102|123->102|123->102|123->102|125->104|125->104|125->104|125->104|126->105|126->105|126->105|126->105|126->105|126->105|126->105|126->105|126->105|126->105|126->105|126->105|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|129->108|151->130|151->130|151->130|151->130|154->133|154->133|154->133|154->133|160->139|160->139|160->139|160->139|163->142|163->142|163->142|163->142|169->148|169->148|169->148|169->148|171->150|171->150|171->150|171->150|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|193->172|213->192|213->192|213->192|213->192|213->192|213->192|213->192|213->192|231->210|231->210|231->210|231->210|231->210|231->210|232->211|232->211|232->211|232->211|232->211|232->211|233->212|233->212|233->212|233->212|233->212|233->212|241->220|241->220|241->220|241->220|241->220|241->220|249->228|249->228|252->232|252->232|252->232|252->232|253->233|253->233|253->233|254->234|254->234|254->234|254->234|254->234|254->234|254->234|256->236|256->236|256->236|257->237|257->237|257->237|257->237|257->237|257->237|257->237|257->237|257->237|257->237|257->237|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|260->240|260->240|260->240|263->243|263->243|263->243|263->243|263->243|263->243|271->251|271->251|272->252|274->254|276->256|277->257|338->318|338->318|338->318|339->319|340->320|340->320|342->322|342->322|342->322|342->322|353->333|353->333|353->333|356->336|358->338
                  -- GENERATED --
              */
          