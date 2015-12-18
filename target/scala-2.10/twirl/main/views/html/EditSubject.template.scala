
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
object EditSubject extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,String,Int,List[scala.Tuple3[Int, String, String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/( Subjet_ID: String, username: String , studyId: Int = 1, session_list : List[( Int, String, String)]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;

Seq[Any](format.raw/*1.104*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"><html><head><title>Vertabelo</title>
    <meta content="width=1170" name="viewport">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta content="IRQcweD5CIs6Qc-uVi9xT3BD-1jPvDxyk3-9LUah9T8" name="google-site-verification">
    <link href="/assets/images/favicon.png" rel="icon" type="image/png">
    <script type="text/javascript" src="/assets/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/assets/js/ow-all.js"></script>
    <script type="text/javascript" src="/assets/js/webapp.js"></script>
    <script type="text/javascript" src="/assets/js/browser-features.js"></script>
    <script type="text/javascript" src="/assets/js/common.js"></script>
    <script type="text/javascript" src="/assets/js/my_models.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/dbwm.css"><!-- start Mixpanel --><script content-type="text/javascript">

              (function(e,b)"""),format.raw/*21.29*/("""{"""),format.raw/*21.30*/("""if(!b.__SV)"""),format.raw/*21.41*/("""{"""),format.raw/*21.42*/("""var a,f,i,g;window.mixpanel=b;a=e.createElement('script');a.type='text/javascript';a.async=!0;a.src=('https:'===e.location.protocol?'https:':'http:')+'//cdn.mxpnl.com/libs/mixpanel-2.2.min.js';f=e.getElementsByTagName('script')[0];f.parentNode.insertBefore(a,f);b._i=[];b.init=function(a,e,d)"""),format.raw/*21.334*/("""{"""),format.raw/*21.335*/("""function f(b,h)"""),format.raw/*21.350*/("""{"""),format.raw/*21.351*/("""var a=h.split('.');2==a.length&&(b=b[a[0]],h=a[1]);b[h]=function()"""),format.raw/*21.417*/("""{"""),format.raw/*21.418*/("""b.push([h].concat(Array.prototype.slice.call(arguments,0)))"""),format.raw/*21.477*/("""}"""),format.raw/*21.478*/("""}"""),format.raw/*21.479*/("""var c=b;'undefined'!==
            typeof d?c=b[d]=[]:d='mixpanel';c.people=c.people||[];c.toString=function(b)"""),format.raw/*22.89*/("""{"""),format.raw/*22.90*/("""var a='mixpanel';'mixpanel'!==d&&(a+='.'+d);b||(a+=' (stub)');return a"""),format.raw/*22.160*/("""}"""),format.raw/*22.161*/(""";c.people.toString=function()"""),format.raw/*22.190*/("""{"""),format.raw/*22.191*/("""return c.toString(1)+'.people (stub)'"""),format.raw/*22.228*/("""}"""),format.raw/*22.229*/(""";i='disable track track_pageview track_links track_forms register register_once alias unregister identify name_tag set_config people.set people.set_once people.increment people.append people.track_charge people.clear_charges people.delete_user'.split(' ');for(g=0;g<i.length;g++)f(c,i[g]);
            b._i.push([a,e,d])"""),format.raw/*23.31*/("""}"""),format.raw/*23.32*/(""";b.__SV=1.2"""),format.raw/*23.43*/("""}"""),format.raw/*23.44*/("""}"""),format.raw/*23.45*/(""")(document,window.mixpanel||[]);


              mixpanel.init("7db79ecd16267922e52d126972ff304e");

              </script><script content-type="text/javascript">mixpanel.people.set("""),format.raw/*28.83*/("""{"""),format.raw/*28.84*/(""""$email": "taamneh_07@hotmail.com",
                                    "Account Plan": "Free",
                                    "Is Trial Plan": false,
                                    "Account Plan Valid To": "",
                                    "Last Login Date": "2014-09-23T17:42:49",
                                    "Active Models":2,
                                    "Largest Own Model":7,
                                    "Sharing Degree":0
                             """),format.raw/*36.30*/("""}"""),format.raw/*36.31*/(""");mixpanel.identify("pr-18363");mixpanel.track_pageview();</script><!-- end Mixpanel --><script type='text/javascript'>    var reader = document.createElement('a');    reader.href = document.location.href;    var ary = reader.pathname.split('/');    var p = null;    if(ary.length > 1) """),format.raw/*36.317*/("""{"""),format.raw/*36.318*/("""        """),format.raw/*36.326*/("""p= '/' + ary[1];    """),format.raw/*36.346*/("""}"""),format.raw/*36.347*/("""    """),format.raw/*36.351*/("""var _gaq = _gaq || [];    _gaq.push(['_setAccount', 'UA-42689964-1']);    _gaq.push(['_setDomainName', 'vertabelo.com']);    _gaq.push(['_setAllowLinker', true]);    if(p = null) """),format.raw/*36.530*/("""{"""),format.raw/*36.531*/("""      """),format.raw/*36.537*/("""_gaq.push(['_trackPageview', p]);    """),format.raw/*36.574*/("""}"""),format.raw/*36.575*/(""" """),format.raw/*36.576*/("""else """),format.raw/*36.581*/("""{"""),format.raw/*36.582*/("""      """),format.raw/*36.588*/("""_gaq.push(['_trackPageview']);    """),format.raw/*36.622*/("""}"""),format.raw/*36.623*/("""    """),format.raw/*36.627*/("""(function() """),format.raw/*36.639*/("""{"""),format.raw/*36.640*/("""      """),format.raw/*36.646*/("""var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;      ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);    """),format.raw/*36.948*/("""}"""),format.raw/*36.949*/(""")();</script>
</head>
<body>
<div id="wrapper"><div id="header_wrapper"><div id="header"><a class="logo" href="/diagram"><img alt="CPL Logo" src="/assets/images/cpllogo.png"  width="70" height="40"></a>
    <ul class="user_menu">
        <li class="user dropdown_holder"><span class="dropdown"><span>"""),_display_(/*41.72*/username),format.raw/*41.80*/("""</span></span>
            <div class="dropdown_menu_holder">
                <div class="dropdown_menu">
                    <ul class="m">
                        <li><a href="/my-account?action=flow.reset&amp;flow.flow=my_account" id="btn1065"><span>My account</span></a>
                           <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*46.119*/("""{"""),format.raw/*46.120*/("""epoint.ow.Utils.bindEvent("click","btn1065","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*46.210*/("""}"""),format.raw/*46.211*/(""",90,null);</script>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*47.115*/("""{"""),format.raw/*47.116*/("""epoint.ow.Utils.bindEvent("click","btn1065","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*47.209*/("""}"""),format.raw/*47.210*/(""",null,null);
                        </script> -->
                        </li>
                    </ul>
                </div>
            </div>
        </li>
        <li class="notifications">
            <div id="flow_notifications_layer_div">
                <div class="menu_item dropdown_holder">
                    <a class="dropdown notifications" href="?action=flow.transit&amp;flow.flow=notifications_layer&amp;flow.transition=expanded&amp;flow.reloadFlow=notifications_layer" id="btn1066"><span></span></a>
                    <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*58.112*/("""{"""),format.raw/*58.113*/("""epoint.ow.Utils.bindEvent("click","btn1066","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*58.203*/("""}"""),format.raw/*58.204*/(""",90,null);</script>
                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*59.107*/("""{"""),format.raw/*59.108*/("""epoint.ow.Utils.bindEvent("click","btn1066","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*59.197*/("""}"""),format.raw/*59.198*/(""",null,null);</script> -->
                </div>
                <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*61.108*/("""{"""),format.raw/*61.109*/("""pl.epoint.dbwm.war_user.notifications.startNotificationsLayerRefresh("?action=flow.transit&flow.flow=notifications_layer&flow.transition=refresh&flow.reloadFlow=notifications_layer&flow.disableMessagesAddons=true");"""),format.raw/*61.324*/("""}"""),format.raw/*61.325*/(""",null,null);</script> -->
            </div>
            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*63.104*/("""{"""),format.raw/*63.105*/("""epoint.ow.flow.Utils.setCurrentState("notifications_layer","collapsed");"""),format.raw/*63.177*/("""}"""),format.raw/*63.178*/(""",null,null);</script> -->
        </li>
        <li class="logout">
            <a href="/logout" id="btn1067"><span>Log out</span></a>
            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*67.104*/("""{"""),format.raw/*67.105*/("""epoint.ow.Utils.bindEvent("click","btn1067","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*67.195*/("""}"""),format.raw/*67.196*/(""",90,null);</script>
            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*68.99*/("""{"""),format.raw/*68.100*/("""epoint.ow.Utils.bindEvent("click","btn1067","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*68.193*/("""}"""),format.raw/*68.194*/(""",null,null);</script> -->
        </li></ul><ul class="links"><li class="dropdown_holder expandable expand-priority-1"><span class="dropdown"><span>Modules</span></span>
        <div class="dropdown_menu_holder">
            <div class="dropdown_menu">
                <ul class="m">
                    <li><a href="/diagram">Diagram</a></li>
                    <li><a href="/allStudies" id="btn1068"><span>My models</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*75.111*/("""{"""),format.raw/*75.112*/("""epoint.ow.Utils.bindEvent("click","btn1068","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*75.202*/("""}"""),format.raw/*75.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*76.111*/("""{"""),format.raw/*76.112*/("""epoint.ow.Utils.bindEvent("click","btn1068","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*76.205*/("""}"""),format.raw/*76.206*/(""",null,null);</script>
                    </li>
                    <li>
                        <a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn1069"><span>Sharing</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*80.111*/("""{"""),format.raw/*80.112*/("""epoint.ow.Utils.bindEvent("click","btn1069","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*80.202*/("""}"""),format.raw/*80.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*81.111*/("""{"""),format.raw/*81.112*/("""epoint.ow.Utils.bindEvent("click","btn1069","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*81.205*/("""}"""),format.raw/*81.206*/(""",null,null);</script>
                    </li>
                    <li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn1070"><span>Recommend us</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*84.111*/("""{"""),format.raw/*84.112*/("""epoint.ow.Utils.bindEvent("click","btn1070","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*84.202*/("""}"""),format.raw/*84.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*85.111*/("""{"""),format.raw/*85.112*/("""epoint.ow.Utils.bindEvent("click","btn1070","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*85.205*/("""}"""),format.raw/*85.206*/(""",null,null);</script>
                    </li>
                </ul>
            </div>
        </div>
    </li>
        <li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span><div class="dropdown_menu_holder">
            <div class="dropdown_menu">
                <ul class="m">
                    <li><a href="javascript: IntroGuide.start();" id="take-application-tour-menu-item" style="display: none;">Take application tour</a></li>
                    <li id="shortcut-helper-menu-item" style="display: none;"><a href="javascript: app.showShortcutsHelper();">Keyboard shortcuts</a></li><li><a target="_blank" href="http://vertabelo.com">Vertabelo website</a>
                </li>
                    <li><a target="_blank" href="http://vertabelo.com/documentation">Documentation</a></li>
                    <li><a target="_blank" href="http://vertabelo.com/faq">FAQ</a></li>
                    <li><a href="/support/ask-a-question" id="btn1071" target="_blank"><span>Ask a question</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*100.111*/("""{"""),format.raw/*100.112*/("""epoint.ow.Utils.bindEvent("click","btn1071","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*100.202*/("""}"""),format.raw/*100.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*101.111*/("""{"""),format.raw/*101.112*/("""epoint.ow.Utils.bindEvent("click","btn1071","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*101.205*/("""}"""),format.raw/*101.206*/(""",null,null);</script>
                    </li>
                    <li><a href="/support/request-a-feature" id="btn1072" target="_blank"><span>Request a feature</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.111*/("""{"""),format.raw/*104.112*/("""epoint.ow.Utils.bindEvent("click","btn1072","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.202*/("""}"""),format.raw/*104.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*105.111*/("""{"""),format.raw/*105.112*/("""epoint.ow.Utils.bindEvent("click","btn1072","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*105.205*/("""}"""),format.raw/*105.206*/(""",null,null);</script>
                    </li>
                    <li><a href="/support/report-a-problem" id="btn1073" target="_blank"><span>Report a problem</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*108.111*/("""{"""),format.raw/*108.112*/("""epoint.ow.Utils.bindEvent("click","btn1073","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*108.202*/("""}"""),format.raw/*108.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*109.111*/("""{"""),format.raw/*109.112*/("""epoint.ow.Utils.bindEvent("click","btn1073","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*109.205*/("""}"""),format.raw/*109.206*/(""",null,null);</script>
                    </li>
                </ul>
            </div>
        </div>
        </li>
    </ul>
    <div class="clearFix"></div>
    <div class="clearFix"></div>
</div>
</div>
<div id="content_wrapper">
    <div id="content">
    <!--<div id="messages-placeholder">
       <div id="not-supported-browser-info-header" style="display: none">Editing database model is not supported in your web browser. Use Google Chrome.
            <a href="http://www.vertabelo.com/faq#chrome" target="_blank">Learn more</a>
        </div>
        <div id="oneweb-messages-placeholder"></div>
    </div>-->
        <div id="flow_models_div">
            <script type="text/javascript" src="/assets/js/modeler/lib/jquery.caret.1.02.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/jquery.mousewheel.min.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/jquery.cookie.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/kinetic-v4.7.4.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/underscore.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/backbone.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/undomanager.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/dbwm.check.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/codemirror/codemirror.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/codemirror/sql.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/codemirror/fullscreen.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/codemirror/vertabelo-editor.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/sql-parser-0.4.1.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/intro-0.8.0.min.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/lib/highlight.min.js"></script><script type="text/javascript" src="/assets/js/modeler/lib/dbwm-extras.js"></script>
            <script type="text/javascript" src="/assets/js/modeler/modeler.js"></script>
            <script type="text/template" id="problems-list-row-problem-template">
            <div class="problems_list_row" data-goto=""""),format.raw/*139.55*/("""{"""),format.raw/*139.56*/("""{"""),format.raw/*139.57*/(""" """),format.raw/*139.58*/("""id """),format.raw/*139.61*/("""}"""),format.raw/*139.62*/("""}"""),format.raw/*139.63*/("""" data-property=""""),format.raw/*139.80*/("""{"""),format.raw/*139.81*/("""{"""),format.raw/*139.82*/(""" """),format.raw/*139.83*/("""property """),format.raw/*139.92*/("""}"""),format.raw/*139.93*/("""}"""),format.raw/*139.94*/("""" title=""""),format.raw/*139.103*/("""{"""),format.raw/*139.104*/("""{"""),format.raw/*139.105*/(""" """),format.raw/*139.106*/("""description """),format.raw/*139.118*/("""}"""),format.raw/*139.119*/("""}"""),format.raw/*139.120*/(""""><code>"""),format.raw/*139.128*/("""{"""),format.raw/*139.129*/("""{"""),format.raw/*139.130*/(""" """),format.raw/*139.131*/("""name """),format.raw/*139.136*/("""}"""),format.raw/*139.137*/("""}"""),format.raw/*139.138*/(""" """),format.raw/*139.139*/("""</code><span class="problems_list_row_message">"""),format.raw/*139.186*/("""{"""),format.raw/*139.187*/("""{"""),format.raw/*139.188*/(""" """),format.raw/*139.189*/("""message """),format.raw/*139.197*/("""}"""),format.raw/*139.198*/("""}"""),format.raw/*139.199*/(""" """),format.raw/*139.200*/("""</span>
            </div>
        </script>
        <script type="text/template" id="problems-list-group-errors-template">
            <div class="details problems_error" open="open"><summary><span><span class="title">Errors </span><span class="title_details"></span><span class="decor"></span></span></summary><div class="content"></div></div>
        </script>
        <script type="text/template" id="problems-list-group-warnings-template">
            <div class="details problems_warning" open="open"><summary><span><span class="title">Warnings </span><span class="title_details"></span><span class="decor"></span></span></summary>
                <div class="content"></div>
            </div>
        </script>
        <script type="text/template" id="problems-list-group-hints-template">
            <div class="details problems_hint" open="open"><summary><span><span class="title">Hints </span><span class="title_details"></span><span class="decor"></span></span></summary>
                <div class="content"></div>
            </div>
        </script>
        <script type="text/template" id="problems-list-element-errors-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*155.134*/("""{"""),format.raw/*155.135*/("""{"""),format.raw/*155.136*/(""" """),format.raw/*155.137*/("""name """),format.raw/*155.142*/("""}"""),format.raw/*155.143*/("""}"""),format.raw/*155.144*/("""</strong></div><div class="list"></div></script>
        <script type="text/template" id="problems-list-element-warnings-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*156.136*/("""{"""),format.raw/*156.137*/("""{"""),format.raw/*156.138*/(""" """),format.raw/*156.139*/("""name """),format.raw/*156.144*/("""}"""),format.raw/*156.145*/("""}"""),format.raw/*156.146*/("""</strong></div><div class="list"></div></script>
        <script type="text/template" id="problems-list-element-hints-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*157.133*/("""{"""),format.raw/*157.134*/("""{"""),format.raw/*157.135*/(""" """),format.raw/*157.136*/("""name """),format.raw/*157.141*/("""}"""),format.raw/*157.142*/("""}"""),format.raw/*157.143*/("""</strong></div><div class="list"></div></script>
        <div id="diagram_as_image" style="display:none">
        </div>
        <div class="box_pre_wrapper">
            <div class="box_wrapper">
                <div class="box">
                    <div class="box_inner">
                        <div class="header">
                            <div class="backbutton">
                                <a class="button" href="?action=flow.transit&amp;flow.flow=models&amp;flow.transition=back&amp;flow.reloadFlow=models" id="btn1064"><span>Back</span></a>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*167.119*/("""{"""),format.raw/*167.120*/("""epoint.ow.Utils.bindEvent("click","btn1064","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*167.210*/("""}"""),format.raw/*167.211*/(""",90,null);</script>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*168.119*/("""{"""),format.raw/*168.120*/("""epoint.ow.Utils.bindEvent("click","btn1064","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*168.209*/("""}"""),format.raw/*168.210*/(""",null,null);</script>
                            </div><h1>Study details</h1>
                        </div>
                        <div class="content">
                            <div class="section with__right_content">
                                <div class="content">
                                    <table cellspacing="0" class="data_table" id="model_details_table">
                                        <tr class="row_1">
                                            <td class="cell_1">Name:</td>
                                            <td class="cell_2">
                                                <div id="flow_name_edit_div">
                                                    <div class="editable">
                                                        <table>
                                                            <tr>
                                                                <td class="value">My first model</td>
                                                                <td class="buttons"><span class="editbutton">
                                                                    <a class="button" href="?action=flow.transit&amp;flow.flow=name_edit&amp;flow.transition=edit&amp;flow.reloadFlow=name_edit" id="btn1040"><span>Edit</span></a>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*185.155*/("""{"""),format.raw/*185.156*/("""epoint.ow.Utils.bindEvent("click","btn1040","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*185.246*/("""}"""),format.raw/*185.247*/(""",90,null);</script>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*186.155*/("""{"""),format.raw/*186.156*/("""epoint.ow.Utils.bindEvent("click","btn1040","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*186.245*/("""}"""),format.raw/*186.246*/(""",null,null);</script></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*192.135*/("""{"""),format.raw/*192.136*/("""epoint.ow.flow.Utils.setCurrentState("name_edit","view");"""),format.raw/*192.193*/("""}"""),format.raw/*192.194*/(""",null,null);</script>
                                            </td>
                                        </tr>
                                        <tr class="row_2">
                                            <td class="cell_1">Identifier:</td>
                                            <td class="cell_2">6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ</td>
                                        </tr><tr class="row_1">
                                        <td class="cell_1">Your role:</td>
                                        <td class="cell_2"><span class="role_owner">Owner</span></td>
                                    </tr>
                                        <tr class="row_2">
                                            <td class="cell_1">Description:</td>
                                            <td class="cell_2">
                                                <div id="flow_description_edit_div">
                                                    <div class="editable">
                                                        <table>
                                                            <tr>
                                                                <td class="value"></td>
                                                                <td class="buttons"><span class="editbutton">
                                                                    <a class="button" href="?action=flow.transit&amp;flow.flow=description_edit&amp;flow.transition=edit&amp;flow.reloadFlow=description_edit" id="btn1041"><span>Edit</span></a>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*212.155*/("""{"""),format.raw/*212.156*/("""epoint.ow.Utils.bindEvent("click","btn1041","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*212.246*/("""}"""),format.raw/*212.247*/(""",90,null);</script>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*213.155*/("""{"""),format.raw/*213.156*/("""epoint.ow.Utils.bindEvent("click","btn1041","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*213.245*/("""}"""),format.raw/*213.246*/(""",null,null);</script></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*219.135*/("""{"""),format.raw/*219.136*/("""epoint.ow.flow.Utils.setCurrentState("description_edit","view");"""),format.raw/*219.200*/("""}"""),format.raw/*219.201*/(""",null,null);</script>
                                            </td>
                                        </tr>
                                        <tr class="row_1">
                                            <td class="cell_1">Database:</td>
                                            <td class="cell_2">MySQL 5.5</td></tr>
                                        <tr class="row_2">
                                            <td class="cell_1">Created:</td>
                                            <td class="cell_2">2014-09-17 12:52 by salah taamne</td>
                                        </tr>
                                        <tr class="row_1">
                                            <td class="cell_1">Last edited:</td>
                                            <td class="cell_2">2014-09-23 13:57 by salah taamne</td>
                                        </tr>
                                        <tr class="row_2">
                                            <td class="cell_1">Public link:</td>
                                            <td class="cell_2">
                                                <div id="flow_public_link_div"><span>You may set up a public link, which allows anyone to view your model.
                                                    <a class="button at_text_button" href="?action=flow.transit&amp;flow.flow=public_link&amp;flow.transition=create&amp;flow.reloadFlow=public_link" id="btn1042"><span>Create</span> </a>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*238.139*/("""{"""),format.raw/*238.140*/("""epoint.ow.Utils.bindEvent("click","btn1042","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*238.230*/("""}"""),format.raw/*238.231*/(""",90,null);</script>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*239.139*/("""{"""),format.raw/*239.140*/("""epoint.ow.Utils.bindEvent("click","btn1042","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*239.229*/("""}"""),format.raw/*239.230*/(""",null,null);</script></span></div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*239.350*/("""{"""),format.raw/*239.351*/("""epoint.ow.flow.Utils.setCurrentState("public_link","no_public_link");"""),format.raw/*239.420*/("""}"""),format.raw/*239.421*/(""",null,null);</script>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="clearFix">
                                    </div>
                            </div>
                             <div class="section" style="display:none;">
                                <div class="header"><h2>You share this model with</h2></div>

                                <p class="table_norows_info">Nobody. Click the button below to start sharing.</p>

                            </div>
                            <div class="section">
                                <div class="header"><h2>"""),_display_(/*254.58*/Subjet_ID),format.raw/*254.67*/("""'s Data</h2></div>
                                <div class="content">
                                    <div id="flow_versions_div">
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*257.127*/("""{"""),format.raw/*257.128*/("""epoint.ow.forms.Utils.registerDefaultSubmitEventHandler("show_autosaved_versions");"""),format.raw/*257.211*/("""}"""),format.raw/*257.212*/(""",null,null);</script>
                                        <div class="form_errors initialHide" id="show_autosaved_versions_errors"><p class="form_errors_header">Form validation errors</p><div class="form_errors_list"></div>
                                        </div>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*260.127*/("""{"""),format.raw/*260.128*/("""epoint.ow.forms.ErrorManager.setErrors("show_autosaved_versions",[]);epoint.ow.forms.ErrorDisplayManager.showErrors("show_autosaved_versions");"""),format.raw/*260.271*/("""}"""),format.raw/*260.272*/(""",200,null);</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*261.127*/("""{"""),format.raw/*261.128*/("""registerShowAutosavedVersionsClickHandler("show_autosaved_versions_showAutosavedVersions","?action=flow.transit&flow.flow=versions&flow.transition=toggle_show_autosaved_versions&flow.reloadFlow=versions");"""),format.raw/*261.333*/("""}"""),format.raw/*261.334*/(""",null,null);</script>
                                        <div class="box_table" id="table_versions">
                                        <!-- <a class="button_primary" id="addSubject"><span>Add Subject</span></a>
                                        <script>
                                        $(document).ready(function()"""),format.raw/*265.69*/("""{"""),format.raw/*265.70*/("""
                                          """),format.raw/*266.43*/("""$("#addSubject").click(function()"""),format.raw/*266.76*/("""{"""),format.raw/*266.77*/("""
                                            """),format.raw/*267.45*/("""$("#newSubject").slideDown("slow");

                                            """),format.raw/*269.45*/("""}"""),format.raw/*269.46*/(""");
                                            """),format.raw/*270.45*/("""}"""),format.raw/*270.46*/(""");
                                            </script>
                                            -->
                                        </div>
                                        <div id="newSubject" >
                                            <!-- <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4" method="POST" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4"> -->
                                            <!-- """),_display_(/*276.51*/helper/*276.57*/.form(action = routes.Application.shareMyStudy)/*276.104*/ {_display_(Seq[Any](format.raw/*276.106*/(""" """),format.raw/*276.107*/("""-->
                                            <table cellspacing="0" class="data_table" id="add_subject_table">
                                                <tr class="row_1">
                                                    <td class="cell_1">* Subject ID:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="text" id="subject_id" maxlength="10" name="subject_id" prompttext="" value="""),_display_(/*281.176*/Subjet_ID),format.raw/*281.185*/(""">
                                                    </td>
                                                    <td class="cell_1">Subject DOB:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="date" id="subject_DOB" maxlength="10" name="subject_DOB" prompttext="">
                                                    </td>
                                                    <td class="cell_1">Subject gender:</td>
                                                    <td>
                                                        <input class="text_field" size="5" o type="text" id="subject_gender" maxlength="10" name="subject_gender" prompttext="">
                                                    </td>
                                                </tr>
                                                <tr class="row_2">
                                                    <td class="cell_1">STAI:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="number" id="STAI" maxlength="10" name="STAI" prompttext="">
                                                    </td>
                                                    <td class="cell_1">PAS:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="number" id="PAS" maxlength="10" name="PAS" prompttext="">
                                                    </td>
                                                    <td class="cell_1">NAS:</td>
                                                    <td>
                                                        <input class="text_field" size="5" o type="number" id="NAS" maxlength="10" name="NAS" prompttext="">
                                                    </td>
                                                    <td>
                                                        <input type="submit" class="button_primary" id="addSub" name="InsertSubject" value="Save" />
                                                    </td>
                                                </tr>
                                                <tr class="row_3">
                                                    <td class="cell_1"></td>

                                                    <td> </td>
                                                    <td> </td>
                                                    <td> </td>
                                                    <td> </td>
                                                </tr>
                                                <input id= "studyId" type="hidden" name="studyId" value="""),_display_(/*317.106*/studyId),format.raw/*317.113*/(""">
                                            </table>

                                            <script>
                                            $(document).ready(function()"""),format.raw/*321.73*/("""{"""),format.raw/*321.74*/("""
                                            """),format.raw/*322.45*/("""$("#addSub").click(function()"""),format.raw/*322.74*/("""{"""),format.raw/*322.75*/("""

                                            """),format.raw/*324.45*/("""$.ajax("""),format.raw/*324.52*/("""{"""),format.raw/*324.53*/("""
                                            """),format.raw/*325.45*/("""type : 'POST',
                                            url : '"""),_display_(/*326.53*/routes/*326.59*/.Application.insertSubject),format.raw/*326.85*/("""',
                                            data : """),format.raw/*327.52*/("""{"""),format.raw/*327.53*/("""
                                            """),format.raw/*328.45*/("""subject_id: $("input[name=subject_id]").val(),
                                            study_id: $("input[name=studyId]").val(),
                                            subject_DOB: $("input[name=subject_DOB]").val(),
                                            STAI: $("input[name=STAI]").val(),
                                            PAS: $("input[name=PAS]").val(),
                                            NAS: $("input[name=NAS]").val()
                                            """),format.raw/*334.45*/("""}"""),format.raw/*334.46*/(""",
                                            success : function(data) """),format.raw/*335.70*/("""{"""),format.raw/*335.71*/("""
                                            """),format.raw/*336.45*/("""alert('Sucess');
                                            $("#sharingResult").html(data);
                                            """),format.raw/*338.45*/("""}"""),format.raw/*338.46*/(""",
                                            error : function(data) """),format.raw/*339.68*/("""{"""),format.raw/*339.69*/("""
                                            """),format.raw/*340.45*/("""alert('Not Sucess');
                                            $("#sharingResult").html(data);
                                            setError('Make call failed');
                                            """),format.raw/*343.45*/("""}"""),format.raw/*343.46*/("""
                                            """),format.raw/*344.45*/("""}"""),format.raw/*344.46*/(""");
                                            return false;
                                            """),format.raw/*346.45*/("""}"""),format.raw/*346.46*/(""");

                                            //e.preventDefault();
                                            """),format.raw/*349.45*/("""}"""),format.raw/*349.46*/(""");
                                            </script>
                                            <table cellspacing="0" class="table_table" id="saved_sessions_table">
                                                <thead class="table_thead">
                                                    <tr class="table_header">
                                                        <th>Session Number:</th>
                                                        <th>Session Name</th>
                                                        <th>Signal Type</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody class="table_body">
                                                """),_display_(/*361.50*/for(see <- session_list) yield /*361.74*/ {_display_(Seq[Any](format.raw/*361.76*/("""
                                                """),_display_(/*362.50*/if(i%2 == 0)/*362.62*/ {_display_(Seq[Any](format.raw/*362.64*/("""
                                                """),format.raw/*363.49*/("""<tr class="table_row_odd table_row_first">
                                                    """)))}/*364.55*/else/*364.60*/{_display_(Seq[Any](format.raw/*364.61*/("""
                                                """),format.raw/*365.49*/("""<tr class="table_row_even">
                                                    """)))}),format.raw/*366.54*/("""
                                                    """),_display_(/*367.54*/{i = i + 1;}),format.raw/*367.66*/("""
                                                    """),format.raw/*368.53*/("""<td>"""),_display_(/*368.58*/see/*368.61*/._1),format.raw/*368.64*/(""" """),format.raw/*368.65*/("""</td>
                                                    <td>"""),_display_(/*369.58*/see/*369.61*/._2),format.raw/*369.64*/(""" """),format.raw/*369.65*/("""</td>
                                                    <td>"""),_display_(/*370.58*/see/*370.61*/._3),format.raw/*370.64*/(""" """),format.raw/*370.65*/("""</td>
                                                    <td class="c05"><a class="button" target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/oGdetLq8pjHcoavNncz8crYI3bQ34b6mYxsDnT4FO881JwOySctO8FVCDGDQrIkE"><span>Edit</span></a></td>
                                                    <td class="c06"><a class="button" target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/oGdetLq8pjHcoavNncz8crYI3bQ34b6mYxsDnT4FO881JwOySctO8FVCDGDQrIkE"><span>Delete</span></a></tr>
                                                </tr>
                                                """)))}),format.raw/*374.50*/("""
                                                """),format.raw/*375.49*/("""</tbody>
                                             </table>
                                            <script>
                                            $(document).ready(function()"""),format.raw/*378.73*/("""{"""),format.raw/*378.74*/("""
                                            """),format.raw/*379.45*/("""$("#addSession").click(function()"""),format.raw/*379.78*/("""{"""),format.raw/*379.79*/("""
                                            """),format.raw/*380.45*/("""$("#newSession").slideDown("slow");
                                            """),format.raw/*381.45*/("""}"""),format.raw/*381.46*/(""");
                                            """),format.raw/*382.45*/("""}"""),format.raw/*382.46*/(""");
                                            </script>

                                            <div id= "newSession">
                                                """),_display_(/*386.50*/helper/*386.56*/.form(action = routes.Application.uploadFile, 'enctype -> "multipart/form-data")/*386.136*/ {_display_(Seq[Any](format.raw/*386.138*/("""
                                                """),format.raw/*387.49*/("""<input id= "studyId2" type="hidden" name="study_id" value="""),_display_(/*387.108*/studyId),format.raw/*387.115*/(""">
                                                <input id= "run_no" type="hidden" name="run_no" value=1>
                                                <input id= "subid" type="hidden" name="subject_id" value="""),_display_(/*389.107*/Subjet_ID),format.raw/*389.116*/(""">

                                            <table cellspacing="0" class="data_table" id="add_sessions_table">
                                                <thead class="table_thead">

                                                </thead>
                                                <tr>
                                                    <td class="cell_1">* Session Name:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="text" id="session_name" maxlength="10" name="session_name" prompttext="">
                                                    </td>
                                                </tr>
                                            </table>
                                            <script>
                                                $(document).ready(function()"""),format.raw/*403.77*/("""{"""),format.raw/*403.78*/("""
                                                """),format.raw/*404.49*/("""$("#addSess").click(function()"""),format.raw/*404.79*/("""{"""),format.raw/*404.80*/("""

                                                  """),format.raw/*406.51*/("""var elem = document.getElementById("subid");
                                                 $(elem).attr('value',$("input[name=subject_id]").val());
                                                 """),format.raw/*408.50*/("""}"""),format.raw/*408.51*/(""");
                                                 """),format.raw/*409.50*/("""}"""),format.raw/*409.51*/(""");
                                            </script>
                                            <table cellpadding="10" cellspacing="0" class="data_table" id="add_Signal_table">
                                                <tr class= "row_1">
                                                    <td class="cell_1">* Source Type:</td>
                                                    <td>
                                                        <select class="select_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select" name="signal_type">
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_" value=0>Other...</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value=1>EDA Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_3" value=2>Thermal Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_4" value=3>Video</option>
                                                        </select>

                                                    </td>
                                                    <td class="cell_1">Item: </td>
                                                    <td>
                                                        <input type="file" name="signal_loc">
                                                    </td>
                                                    <td>
                                                        <input type="submit" id="addSess" name="InsertSession" value="Upload and Save">

                                                    </td>

                                                </tr>

                                                 </table>
                                                """)))}),format.raw/*435.50*/("""
                                                 """),format.raw/*436.50*/("""<!-- </form> -->
                                            <!-- """)))}),format.raw/*437.51*/(""" """),format.raw/*437.52*/("""-->
                                         </div>
                                        </div>

                                        <div class="buttons">
                                            <a class="button" href="?action=flow.transit&amp;flow.flow=versions&amp;flow.transition=more_versions&amp;flow.reloadFlow=versions" id="btn1062"><span>Save</span></a>

                                            <!-- <a class="button" href="?action=flow.transit&amp;flow.flow=versions&amp;flow.transition=show_all_versions&amp;flow.reloadFlow=versions" id="btn1063"><span>Show all versions</span></a> -->
                                        </div>
                                    </div>
                                    <div class="block_dialog_container">
                                        <div class="popup initialHide" id="popup_flow_share_model_div">
                                            <div class="popup_arrow_up"></div>
                                            <a class="close-popup-button" id="btn1053"><span>X</span></a>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*451.131*/("""{"""),format.raw/*451.132*/("""epoint.ow.Utils.bindEvent("click","btn1053","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*451.222*/("""}"""),format.raw/*451.223*/(""",90,null);</script>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*452.131*/("""{"""),format.raw/*452.132*/("""epoint.ow.Utils.bindEvent("click","btn1053","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_share_model_div",[]);"""),format.raw/*452.263*/("""}"""),format.raw/*452.264*/(""",null,null);</script>
                                            <div class="clearFix">
                                            </div><div class="popup_inner"><div id="flow_share_model_div"><img src="/assets/images/empty_flow_content.gif"></div>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*455.131*/("""{"""),format.raw/*455.132*/("""epoint.ow.flow.Utils.setCurrentState("share_model","share");"""),format.raw/*455.192*/("""}"""),format.raw/*455.193*/(""",null,null);</script>
                                        </div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*459.123*/("""{"""),format.raw/*459.124*/("""epoint.ow.flow.Utils.setCurrentState("versions","view");"""),format.raw/*459.180*/("""}"""),format.raw/*459.181*/(""",null,null);</script>
                                </div>
                                <div class="clearFix">

                                </div>
                            </div>
                        </div>
                        <div class="footer">
                            <div class="backbutton">
                                <a href="#"><span>Back</span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        </div>
    </div>
</div>
<div id="footer_wrapper">
    <div id="footer">
        <ul class="links">
            <li><a href="/support/request-a-feature" id="btn1074" target="_blank"><span>Request a feature</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*483.103*/("""{"""),format.raw/*483.104*/("""epoint.ow.Utils.bindEvent("click","btn1074","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*483.194*/("""}"""),format.raw/*483.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*484.103*/("""{"""),format.raw/*484.104*/("""epoint.ow.Utils.bindEvent("click","btn1074","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*484.197*/("""}"""),format.raw/*484.198*/(""",null,null);</script>
            </li>
            <li><a href="/support/report-a-problem" id="btn1075" target="_blank"><span>Report a problem</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*487.103*/("""{"""),format.raw/*487.104*/("""epoint.ow.Utils.bindEvent("click","btn1075","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*487.194*/("""}"""),format.raw/*487.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*488.103*/("""{"""),format.raw/*488.104*/("""epoint.ow.Utils.bindEvent("click","btn1075","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*488.197*/("""}"""),format.raw/*488.198*/(""",null,null);</script>
            </li>
            <li>
                <a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn1076"><span>Recommend us</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*492.103*/("""{"""),format.raw/*492.104*/("""epoint.ow.Utils.bindEvent("click","btn1076","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*492.194*/("""}"""),format.raw/*492.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*493.103*/("""{"""),format.raw/*493.104*/("""epoint.ow.Utils.bindEvent("click","btn1076","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*493.197*/("""}"""),format.raw/*493.198*/(""",null,null);</script>
            </li>
            <li>
                <a href="/support/ask-a-question" id="btn1077" target="_blank"><span>Ask a question</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*497.103*/("""{"""),format.raw/*497.104*/("""epoint.ow.Utils.bindEvent("click","btn1077","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*497.194*/("""}"""),format.raw/*497.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*498.103*/("""{"""),format.raw/*498.104*/("""epoint.ow.Utils.bindEvent("click","btn1077","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*498.197*/("""}"""),format.raw/*498.198*/(""",null,null);</script>
            </li>
            <li>
                <a target="_blank" href="http://vertabelo.com/about-us">About us</a></li><li><a href="http://vertabelo.com/terms-of-service">Terms of service</a>
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
        <div class="clearFix">

        </div><div class="hr"><hr></div>
    <!-- <div class="powered_by">Powered by <a href="http://e-point.com">e-point SA</a> internet software house</div> -->
    <div class="copyright">StressBook v. 1.0.0 All rights reserved</div>
    <div class="clearFix">
    </div>
</div>
</div>
</div>
<!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*522.92*/("""{"""),format.raw/*522.93*/("""oneweb.CsrfTokenManager.setToken("Xlgx5t9sddK8TDNxtjUMYBh74aW73CIl");"""),format.raw/*522.162*/("""}"""),format.raw/*522.163*/(""",10,null);
			  </script> -->
</body>
</html>"""))}
  }

  def render(Subjet_ID:String,username:String,studyId:Int,session_list:List[scala.Tuple3[Int, String, String]]): play.twirl.api.HtmlFormat.Appendable = apply(Subjet_ID,username,studyId,session_list)

  def f:((String,String,Int,List[scala.Tuple3[Int, String, String]]) => play.twirl.api.HtmlFormat.Appendable) = (Subjet_ID,username,studyId,session_list) => apply(Subjet_ID,username,studyId,session_list)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:25:58 CDT 2015
                  SOURCE: C:/first_play/app/views/EditSubject.scala.html
                  HASH: 7daac788c375c1bf6962ea4366b61d87811bda82
                  MATRIX: 562->1|790->103|818->145|2318->1617|2347->1618|2386->1629|2415->1630|2736->1922|2766->1923|2810->1938|2840->1939|2935->2005|2965->2006|3053->2065|3083->2066|3113->2067|3253->2179|3282->2180|3381->2250|3411->2251|3469->2280|3499->2281|3565->2318|3595->2319|3944->2640|3973->2641|4012->2652|4041->2653|4070->2654|4286->2842|4315->2843|4848->3349|4877->3350|5192->3636|5222->3637|5259->3645|5308->3665|5338->3666|5371->3670|5579->3849|5609->3850|5644->3856|5710->3893|5740->3894|5770->3895|5804->3900|5834->3901|5869->3907|5932->3941|5962->3942|5995->3946|6036->3958|6066->3959|6101->3965|6432->4267|6462->4268|6795->4574|6824->4582|7251->4980|7281->4981|7400->5071|7430->5072|7594->5207|7624->5208|7746->5301|7776->5302|8449->5946|8479->5947|8598->6037|8628->6038|8784->6165|8814->6166|8932->6255|8962->6256|9149->6414|9179->6415|9423->6630|9453->6631|9632->6781|9662->6782|9763->6854|9793->6855|10065->7098|10095->7099|10214->7189|10244->7190|10391->7309|10421->7310|10543->7403|10573->7404|11149->7951|11179->7952|11298->8042|11328->8043|11488->8174|11518->8175|11640->8268|11670->8269|12013->8583|12043->8584|12162->8674|12192->8675|12352->8806|12382->8807|12504->8900|12534->8901|12860->9198|12890->9199|13009->9289|13039->9290|13199->9421|13229->9422|13351->9515|13381->9516|14575->10680|14606->10681|14726->10771|14757->10772|14918->10903|14949->10904|15072->10997|15103->10998|15419->11284|15450->11285|15570->11375|15601->11376|15762->11507|15793->11508|15916->11601|15947->11602|16261->11886|16292->11887|16412->11977|16443->11978|16604->12109|16635->12110|16758->12203|16789->12204|19244->14630|19274->14631|19304->14632|19334->14633|19366->14636|19396->14637|19426->14638|19472->14655|19502->14656|19532->14657|19562->14658|19600->14667|19630->14668|19660->14669|19699->14678|19730->14679|19761->14680|19792->14681|19834->14693|19865->14694|19896->14695|19934->14703|19965->14704|19996->14705|20027->14706|20062->14711|20093->14712|20124->14713|20155->14714|20232->14761|20263->14762|20294->14763|20325->14764|20363->14772|20394->14773|20425->14774|20456->14775|21700->15989|21731->15990|21762->15991|21793->15992|21828->15997|21859->15998|21890->15999|22105->16184|22136->16185|22167->16186|22198->16187|22233->16192|22264->16193|22295->16194|22507->16376|22538->16377|22569->16378|22600->16379|22635->16384|22666->16385|22697->16386|23414->17073|23445->17074|23565->17164|23596->17165|23765->17304|23796->17305|23915->17394|23946->17395|25450->18869|25481->18870|25601->18960|25632->18961|25837->19136|25868->19137|25987->19226|26018->19227|26532->19711|26563->19712|26650->19769|26681->19770|28491->21550|28522->21551|28642->21641|28673->21642|28878->21817|28909->21818|29028->21907|29059->21908|29573->22392|29604->22393|29698->22457|29729->22458|31419->24118|31450->24119|31570->24209|31601->24210|31790->24369|31821->24370|31940->24459|31971->24460|32121->24580|32152->24581|32251->24650|32282->24651|33084->25425|33115->25434|33412->25701|33443->25702|33556->25785|33587->25786|34021->26190|34052->26191|34225->26334|34256->26335|34434->26483|34465->26484|34700->26689|34731->26690|35103->27033|35133->27034|35206->27078|35268->27111|35298->27112|35373->27158|35485->27241|35515->27242|35592->27290|35622->27291|36232->27873|36248->27879|36306->27926|36348->27928|36379->27929|36916->28437|36948->28446|39889->31358|39919->31365|40133->31550|40163->31551|40238->31597|40296->31626|40326->31627|40403->31675|40439->31682|40469->31683|40544->31729|40640->31797|40656->31803|40704->31829|40788->31884|40818->31885|40893->31931|41430->32439|41460->32440|41561->32512|41591->32513|41666->32559|41834->32698|41864->32699|41963->32769|41993->32770|42068->32816|42315->33034|42345->33035|42420->33081|42450->33082|42586->33189|42616->33190|42762->33307|42792->33308|43698->34186|43739->34210|43780->34212|43859->34263|43881->34275|43922->34277|44001->34327|44118->34425|44132->34430|44172->34431|44251->34481|44365->34563|44448->34618|44482->34630|44565->34684|44598->34689|44611->34692|44636->34695|44666->34696|44758->34760|44771->34763|44796->34766|44826->34767|44918->34831|44931->34834|44956->34837|44986->34838|45705->35525|45784->35575|46004->35766|46034->35767|46109->35813|46171->35846|46201->35847|46276->35893|46386->35974|46416->35975|46493->36023|46523->36024|46729->36202|46745->36208|46836->36288|46878->36290|46957->36340|47045->36399|47075->36406|47319->36621|47351->36630|48311->37561|48341->37562|48420->37612|48479->37642|48509->37643|48592->37697|48823->37899|48853->37900|48935->37953|48965->37954|51259->40216|51339->40267|51439->40335|51469->40336|52706->41543|52737->41544|52857->41634|52888->41635|53069->41786|53100->41787|53261->41918|53292->41919|53706->42303|53737->42304|53827->42364|53858->42365|54173->42650|54204->42651|54290->42707|54321->42708|55239->43596|55270->43597|55390->43687|55421->43688|55574->43811|55605->43812|55728->43905|55759->43906|56049->44166|56080->44167|56200->44257|56231->44258|56384->44381|56415->44382|56538->44475|56569->44476|56890->44767|56921->44768|57041->44858|57072->44859|57225->44982|57256->44983|57379->45076|57410->45077|57714->45351|57745->45352|57865->45442|57896->45443|58049->45566|58080->45567|58203->45660|58234->45661|59557->46955|59587->46956|59686->47025|59717->47026
                  LINES: 19->1|22->1|23->3|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|41->21|42->22|42->22|42->22|42->22|42->22|42->22|42->22|42->22|43->23|43->23|43->23|43->23|43->23|48->28|48->28|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|56->36|61->41|61->41|66->46|66->46|66->46|66->46|67->47|67->47|67->47|67->47|78->58|78->58|78->58|78->58|79->59|79->59|79->59|79->59|81->61|81->61|81->61|81->61|83->63|83->63|83->63|83->63|87->67|87->67|87->67|87->67|88->68|88->68|88->68|88->68|95->75|95->75|95->75|95->75|96->76|96->76|96->76|96->76|100->80|100->80|100->80|100->80|101->81|101->81|101->81|101->81|104->84|104->84|104->84|104->84|105->85|105->85|105->85|105->85|120->100|120->100|120->100|120->100|121->101|121->101|121->101|121->101|124->104|124->104|124->104|124->104|125->105|125->105|125->105|125->105|128->108|128->108|128->108|128->108|129->109|129->109|129->109|129->109|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|159->139|175->155|175->155|175->155|175->155|175->155|175->155|175->155|176->156|176->156|176->156|176->156|176->156|176->156|176->156|177->157|177->157|177->157|177->157|177->157|177->157|177->157|187->167|187->167|187->167|187->167|188->168|188->168|188->168|188->168|205->185|205->185|205->185|205->185|206->186|206->186|206->186|206->186|212->192|212->192|212->192|212->192|232->212|232->212|232->212|232->212|233->213|233->213|233->213|233->213|239->219|239->219|239->219|239->219|258->238|258->238|258->238|258->238|259->239|259->239|259->239|259->239|259->239|259->239|259->239|259->239|274->254|274->254|277->257|277->257|277->257|277->257|280->260|280->260|280->260|280->260|281->261|281->261|281->261|281->261|285->265|285->265|286->266|286->266|286->266|287->267|289->269|289->269|290->270|290->270|296->276|296->276|296->276|296->276|296->276|301->281|301->281|337->317|337->317|341->321|341->321|342->322|342->322|342->322|344->324|344->324|344->324|345->325|346->326|346->326|346->326|347->327|347->327|348->328|354->334|354->334|355->335|355->335|356->336|358->338|358->338|359->339|359->339|360->340|363->343|363->343|364->344|364->344|366->346|366->346|369->349|369->349|381->361|381->361|381->361|382->362|382->362|382->362|383->363|384->364|384->364|384->364|385->365|386->366|387->367|387->367|388->368|388->368|388->368|388->368|388->368|389->369|389->369|389->369|389->369|390->370|390->370|390->370|390->370|394->374|395->375|398->378|398->378|399->379|399->379|399->379|400->380|401->381|401->381|402->382|402->382|406->386|406->386|406->386|406->386|407->387|407->387|407->387|409->389|409->389|423->403|423->403|424->404|424->404|424->404|426->406|428->408|428->408|429->409|429->409|455->435|456->436|457->437|457->437|471->451|471->451|471->451|471->451|472->452|472->452|472->452|472->452|475->455|475->455|475->455|475->455|479->459|479->459|479->459|479->459|503->483|503->483|503->483|503->483|504->484|504->484|504->484|504->484|507->487|507->487|507->487|507->487|508->488|508->488|508->488|508->488|512->492|512->492|512->492|512->492|513->493|513->493|513->493|513->493|517->497|517->497|517->497|517->497|518->498|518->498|518->498|518->498|542->522|542->522|542->522|542->522
                  -- GENERATED --
              */
          