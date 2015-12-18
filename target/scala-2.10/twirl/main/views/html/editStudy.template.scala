
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
object editStudy extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,String,Int,List[scala.Tuple2[String, java.util.Date]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(answer: String = "", username: String , studyId: Int = 1, subjects: List[(String,java.util.Date)]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;

Seq[Any](format.raw/*1.101*/("""

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
                    <li><a href="/diagram">Diagram</a></li><li><a href="/allStudies" id="btn1068"><span>My models</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.211*/("""{"""),format.raw/*73.212*/("""epoint.ow.Utils.bindEvent("click","btn1068","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*73.302*/("""}"""),format.raw/*73.303*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.408*/("""{"""),format.raw/*73.409*/("""epoint.ow.Utils.bindEvent("click","btn1068","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*73.502*/("""}"""),format.raw/*73.503*/(""",null,null);</script></li><li><a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn1069"><span>Sharing</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.721*/("""{"""),format.raw/*73.722*/("""epoint.ow.Utils.bindEvent("click","btn1069","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*73.812*/("""}"""),format.raw/*73.813*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.918*/("""{"""),format.raw/*73.919*/("""epoint.ow.Utils.bindEvent("click","btn1069","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*73.1012*/("""}"""),format.raw/*73.1013*/(""",null,null);</script></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn1070"><span>Recommend us</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.1240*/("""{"""),format.raw/*73.1241*/("""epoint.ow.Utils.bindEvent("click","btn1070","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*73.1331*/("""}"""),format.raw/*73.1332*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*73.1437*/("""{"""),format.raw/*73.1438*/("""epoint.ow.Utils.bindEvent("click","btn1070","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*73.1531*/("""}"""),format.raw/*73.1532*/(""",null,null);</script></li>
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
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*87.111*/("""{"""),format.raw/*87.112*/("""epoint.ow.Utils.bindEvent("click","btn1071","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*87.202*/("""}"""),format.raw/*87.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*88.111*/("""{"""),format.raw/*88.112*/("""epoint.ow.Utils.bindEvent("click","btn1071","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*88.205*/("""}"""),format.raw/*88.206*/(""",null,null);</script>
                    </li>
                    <li><a href="/support/request-a-feature" id="btn1072" target="_blank"><span>Request a feature</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*91.111*/("""{"""),format.raw/*91.112*/("""epoint.ow.Utils.bindEvent("click","btn1072","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*91.202*/("""}"""),format.raw/*91.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*92.111*/("""{"""),format.raw/*92.112*/("""epoint.ow.Utils.bindEvent("click","btn1072","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*92.205*/("""}"""),format.raw/*92.206*/(""",null,null);</script>
                    </li>
                    <li><a href="/support/report-a-problem" id="btn1073" target="_blank"><span>Report a problem</span></a>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*95.111*/("""{"""),format.raw/*95.112*/("""epoint.ow.Utils.bindEvent("click","btn1073","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*95.202*/("""}"""),format.raw/*95.203*/(""",90,null);</script>
                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*96.111*/("""{"""),format.raw/*96.112*/("""epoint.ow.Utils.bindEvent("click","btn1073","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*96.205*/("""}"""),format.raw/*96.206*/(""",null,null);</script>
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
            <div class="problems_list_row" data-goto=""""),format.raw/*126.55*/("""{"""),format.raw/*126.56*/("""{"""),format.raw/*126.57*/(""" """),format.raw/*126.58*/("""id """),format.raw/*126.61*/("""}"""),format.raw/*126.62*/("""}"""),format.raw/*126.63*/("""" data-property=""""),format.raw/*126.80*/("""{"""),format.raw/*126.81*/("""{"""),format.raw/*126.82*/(""" """),format.raw/*126.83*/("""property """),format.raw/*126.92*/("""}"""),format.raw/*126.93*/("""}"""),format.raw/*126.94*/("""" title=""""),format.raw/*126.103*/("""{"""),format.raw/*126.104*/("""{"""),format.raw/*126.105*/(""" """),format.raw/*126.106*/("""description """),format.raw/*126.118*/("""}"""),format.raw/*126.119*/("""}"""),format.raw/*126.120*/(""""><code>"""),format.raw/*126.128*/("""{"""),format.raw/*126.129*/("""{"""),format.raw/*126.130*/(""" """),format.raw/*126.131*/("""name """),format.raw/*126.136*/("""}"""),format.raw/*126.137*/("""}"""),format.raw/*126.138*/(""" """),format.raw/*126.139*/("""</code><span class="problems_list_row_message">"""),format.raw/*126.186*/("""{"""),format.raw/*126.187*/("""{"""),format.raw/*126.188*/(""" """),format.raw/*126.189*/("""message """),format.raw/*126.197*/("""}"""),format.raw/*126.198*/("""}"""),format.raw/*126.199*/(""" """),format.raw/*126.200*/("""</span>
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
        <script type="text/template" id="problems-list-element-errors-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*142.134*/("""{"""),format.raw/*142.135*/("""{"""),format.raw/*142.136*/(""" """),format.raw/*142.137*/("""name """),format.raw/*142.142*/("""}"""),format.raw/*142.143*/("""}"""),format.raw/*142.144*/("""</strong></div><div class="list"></div></script>
        <script type="text/template" id="problems-list-element-warnings-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*143.136*/("""{"""),format.raw/*143.137*/("""{"""),format.raw/*143.138*/(""" """),format.raw/*143.139*/("""name """),format.raw/*143.144*/("""}"""),format.raw/*143.145*/("""}"""),format.raw/*143.146*/("""</strong></div><div class="list"></div></script>
        <script type="text/template" id="problems-list-element-hints-template"><div class="title"><span class="icon"></span><strong>"""),format.raw/*144.133*/("""{"""),format.raw/*144.134*/("""{"""),format.raw/*144.135*/(""" """),format.raw/*144.136*/("""name """),format.raw/*144.141*/("""}"""),format.raw/*144.142*/("""}"""),format.raw/*144.143*/("""</strong></div><div class="list"></div></script>
        <div id="diagram_as_image" style="display:none">
        </div>
        <div class="box_pre_wrapper">
            <div class="box_wrapper">
                <div class="box">
                    <div class="box_inner">
                        <div class="header">
                            <div class="backbutton">
                                <a class="button" href="?action=flow.transit&amp;flow.flow=models&amp;flow.transition=back&amp;flow.reloadFlow=models" id="btn1064"><span>Back</span></a>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*154.119*/("""{"""),format.raw/*154.120*/("""epoint.ow.Utils.bindEvent("click","btn1064","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*154.210*/("""}"""),format.raw/*154.211*/(""",90,null);</script>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*155.119*/("""{"""),format.raw/*155.120*/("""epoint.ow.Utils.bindEvent("click","btn1064","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*155.209*/("""}"""),format.raw/*155.210*/(""",null,null);</script>
                            </div><h1>Study details</h1>
                        </div>
                        <div class="content">
                            <div class="section with__right_content" >
                                <div class="right_content" style="display:none"><div class="actions_stack">
                                    <a class="action action_model" href="/model/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ">Diagram</a>
                                    <a class="action action_sql" id="generate_sql_button"><span>Generate SQL</span></a>
                                    <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*163.128*/("""{"""),format.raw/*163.129*/("""epoint.ow.Utils.bindEvent("click","generate_sql_button","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*163.231*/("""}"""),format.raw/*163.232*/(""",90,null);</script> -->
                                    <div class="block_dialog_container">
                                        <div class="popup initialHide compact" id="sql_generator_form_container"><div class="popup_arrow_up"></div><a class="close-popup-button" id="btn1044"><span>X</span></a>
                                            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*166.136*/("""{"""),format.raw/*166.137*/("""epoint.ow.Utils.bindEvent("click","btn1044","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*166.227*/("""}"""),format.raw/*166.228*/(""",90,null);</script>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*167.131*/("""{"""),format.raw/*167.132*/("""epoint.ow.Utils.bindEvent("click","btn1044","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*167.265*/("""}"""),format.raw/*167.266*/(""",null,null);</script> -->
                                            <div class="clearFix"></div>
                                            <div class="popup_inner">
                                                <div class="header"><h1>Generate SQL Script</h1>
                                                </div>
                                                <form id="sql_generator_form" onSubmit="return false;">
                                                    <div class="box_form_section">
                                                        <div class="box_form_content">
                                                            <table cellspacing="0" class="form_table">
                                                                <tr class="row_1">
                                                                    <td class="cell_1"><span class="field_label">I want to generate:<span class="mandatory">*</span></span></td>
                                                                    <td class="cell_2">
                                                                        <div class="h_options"><span class="field_option">
                                                                             <input checked="checked" class="radio_field" id="sql_generator_type_create" name="type" type="radio" value="create"><label for="sql_generator_type_create">Create</label></span><span class="field_option">
                                                                             <input class="radio_field" id="sql_generator_type_drop" name="type" type="radio" value="drop"><label for="sql_generator_type_drop">Drop</label></span>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr class="row_2">
                                                                    <td class="cell_1"><span class="field_label">For following elements:<span class="mandatory">*</span></span>
                                                                    </td>
                                                                    <td class="cell_2"><div class="v_options"><span class="field_option">
                                                                         <input checked="checked" class="radio_field" id="sql_generator_scope_tables" name="scope" type="checkbox" value="tables"><label for="sql_generator_scope_tables">Tables</label></span><span class="field_option">
                                                                         <input checked="checked" class="radio_field" id="sql_generator_scope_references" name="scope" type="checkbox" value="references"><label for="sql_generator_scope_references">References</label></span><span class="field_option">
                                                                         <input checked="checked" class="radio_field" id="sql_generator_scope_sequences" name="scope" type="checkbox" value="sequences"><label for="sql_generator_scope_sequences">Sequences</label></span><span class="field_option">
                                                                         <input checked="checked" class="radio_field" id="sql_generator_scope_views" name="scope" type="checkbox" value="views"><label for="sql_generator_scope_views">Views</label></span><span class="field_option">
                                                                         <input checked="checked" class="radio_field" id="sql_generator_scope_procedures" name="scope" type="checkbox" value="procedures"><label for="sql_generator_scope_procedures">Procedures</label></span>
                                                                    </div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                            <p class="sql_generator_errors" id="sql_generator_empty_script">You will get empty sql script. Choose at least one element.</p>
                                                        </div>
                                                    </div>
                                                </form>
                                                <div id="sql_generator_problems" style="display: none">
                                                    <div class="section section__small section__separated_below">
                                                        <div class="header"><h2>Problems </h2></div><div class="content">
                                                        <p class="sql_generator_errors">The model you want to generate a SQL script from contains errors&#x2F;warnings.
                                                            The generated script may not be accepted by your database.
                                                            We highly recommend that you correct all errors and warnings before generating the SQL script.</p>
                                                        <div class="accordion" id="sql_generator_problems_list" style="display: none">
                                                        </div>
                                                    </div>
                                                        <div class="clearFix">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="sql_generator_progress_checking" style="display: none"><p><img alt="" class="loading-small" src="/assets/images/loading-small.gif">Checking model. Please wait...</p></div>
                                                <div id="sql_generator_progress_generating" style="display: none"><p><img alt="" class="loading-small" src="/assets/images/loading-small.gif">Generating SQL script. Please wait...</p></div>
                                                <div id="sql_generator_download" style="display: none">
                                                    <div class="box_table">
                                                        <table class="table_table">
                                                            <thead class="table_header"><tr><th colspan="2">Generated file</th></tr></thead>
                                                            <tbody class="table_body">
                                                            <tr class="table_row_odd table_row_first table_row_last">
                                                                <td class="width_max"><a href="#" id="sql_generator_show_model_link" target="_blank">none.sql</a></td>
                                                                <td><a class="button" href="#" id="sql_generator_download_model_button">Download</a></td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="buttons">
                                                    <a class="button_primary" id="sql_generator_form_button"><span>Generate</span></a>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*232.139*/("""{"""),format.raw/*232.140*/("""epoint.ow.Utils.bindEvent("click","sql_generator_form_button","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*232.248*/("""}"""),format.raw/*232.249*/(""",90,null);</script>
                                                    <a class="button_primary" id="sql_generator_form_despite_warnings_button"><span>Ignore warnings and generate</span></a>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*234.139*/("""{"""),format.raw/*234.140*/("""epoint.ow.Utils.bindEvent("click","sql_generator_form_despite_warnings_button","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*234.265*/("""}"""),format.raw/*234.266*/(""",90,null);</script>
                                                    <a class="link" id="btn1043"><span>Cancel</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*235.191*/("""{"""),format.raw/*235.192*/("""epoint.ow.Utils.bindEvent("click","btn1043","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*235.282*/("""}"""),format.raw/*235.283*/(""",90,null);</script>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*236.139*/("""{"""),format.raw/*236.140*/("""epoint.ow.Utils.bindEvent("click","btn1043","epoint.ow.popup.PopupManager.closePopupClickHandler","sql_generator_form_container",[]);"""),format.raw/*236.273*/("""}"""),format.raw/*236.274*/(""",null,null);</script>
                                                </div><p class="mandatory_info">Fields marked with [<span class="mandatory">*</span>] must be filled.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*241.123*/("""{"""),format.raw/*241.124*/("""epoint.ow.Utils.bindEvent("click","generate_sql_button","SqlGenerator.Form.show");"""),format.raw/*241.206*/("""}"""),format.raw/*241.207*/(""",null,null);</script>
                                    <a class="action action_png" id="export_as_image"><span>Export to image</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*243.123*/("""{"""),format.raw/*243.124*/("""epoint.ow.Utils.bindEvent("click","export_as_image","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*243.222*/("""}"""),format.raw/*243.223*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*244.123*/("""{"""),format.raw/*244.124*/("""epoint.ow.Utils.bindEvent("click","export_as_image","Diagram.Exporter.exportToImage");"""),format.raw/*244.210*/("""}"""),format.raw/*244.211*/(""",null,null);</script>
                                    <a class="action action_export_xml" target="_blank" href="/model_version_as_xml?gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4">Export to XML</a>
                                    <a class="action action_import_xml" href="?action=flow.reset&amp;flow.flow=import_model_from_xml&amp;flow.reloadFlow=import_model_from_xml" id="btn1045"><span>Import from XML</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*247.123*/("""{"""),format.raw/*247.124*/("""epoint.ow.Utils.bindEvent("click","btn1045","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*247.214*/("""}"""),format.raw/*247.215*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*248.123*/("""{"""),format.raw/*248.124*/("""epoint.ow.Utils.bindEvent("click","btn1045","epoint.ow.popup.PopupManager.openPopupAfterAjaxFlowReloadClickHandler","popup_flow_import_model_from_xml_div","flow_import_model_from_xml_div",[]);epoint.ow.Utils.bindEvent("click","btn1045","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*248.405*/("""}"""),format.raw/*248.406*/(""",null,null);</script>
                                    <div class="block_dialog_container"><div class="popup initialHide" id="popup_flow_import_model_from_xml_div">
                                        <div class="popup_arrow_up"></div>
                                        <a class="close-popup-button" id="btn1046"><span>X</span></a>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*252.127*/("""{"""),format.raw/*252.128*/("""epoint.ow.Utils.bindEvent("click","btn1046","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*252.218*/("""}"""),format.raw/*252.219*/(""",90,null);</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*253.127*/("""{"""),format.raw/*253.128*/("""epoint.ow.Utils.bindEvent("click","btn1046","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_import_model_from_xml_div",[]);"""),format.raw/*253.269*/("""}"""),format.raw/*253.270*/(""",null,null);</script>
                                        <div class="clearFix"></div>
                                        <div class="popup_inner">
                                            <div id="flow_import_model_from_xml_div"><img src="/assets/images/empty_flow_content.gif"></div>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*257.131*/("""{"""),format.raw/*257.132*/("""epoint.ow.flow.Utils.setCurrentState("import_model_from_xml","import_file");"""),format.raw/*257.208*/("""}"""),format.raw/*257.209*/(""",null,null);</script>
                                        </div>
                                    </div>
                                    </div>
                                    <a class="action action_import_ddl" href="?action=flow.reset&amp;flow.flow=import_ddl&amp;flow.reloadFlow=import_ddl" id="btn1047"><span>Import from SQL</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*262.123*/("""{"""),format.raw/*262.124*/("""epoint.ow.Utils.bindEvent("click","btn1047","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*262.214*/("""}"""),format.raw/*262.215*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*263.123*/("""{"""),format.raw/*263.124*/("""epoint.ow.Utils.bindEvent("click","btn1047","epoint.ow.popup.PopupManager.openPopupAfterAjaxFlowReloadClickHandler","popup_flow_import_ddl_div","flow_import_ddl_div",[]);epoint.ow.Utils.bindEvent("click","btn1047","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*263.383*/("""}"""),format.raw/*263.384*/(""",null,null);</script>
                                    <div class="block_dialog_container"><div class="popup initialHide" id="popup_flow_import_ddl_div">
                                        <div class="popup_arrow_up"></div>
                                        <a class="close-popup-button" id="btn1048"><span>X</span></a>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*267.127*/("""{"""),format.raw/*267.128*/("""epoint.ow.Utils.bindEvent("click","btn1048","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*267.218*/("""}"""),format.raw/*267.219*/(""",90,null);</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*268.127*/("""{"""),format.raw/*268.128*/("""epoint.ow.Utils.bindEvent("click","btn1048","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_import_ddl_div",[]);"""),format.raw/*268.258*/("""}"""),format.raw/*268.259*/(""",null,null);</script>
                                        <div class="clearFix">
                                            </div>
                                        <div class="popup_inner">
                                            <div id="flow_import_ddl_div"><img src="/assets/images/empty_flow_content.gif"></div>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*273.131*/("""{"""),format.raw/*273.132*/("""epoint.ow.flow.Utils.setCurrentState("import_ddl","import_file");"""),format.raw/*273.197*/("""}"""),format.raw/*273.198*/(""",null,null);</script>
                                        </div>
                                    </div>
                                    </div>
                                    <a class="action action_clone" href="?action=flow.transit&amp;flow.flow=models&amp;flow.transition=clone_model&amp;flow.reloadFlow=models" id="btn1049"><span>Clone model</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*278.123*/("""{"""),format.raw/*278.124*/("""epoint.ow.Utils.bindEvent("click","btn1049","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*278.214*/("""}"""),format.raw/*278.215*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*279.123*/("""{"""),format.raw/*279.124*/("""epoint.ow.Utils.bindEvent("click","btn1049","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*279.213*/("""}"""),format.raw/*279.214*/(""",null,null);</script>
                                    <a class="action action_delete" href="?action=flow.transit&amp;flow.flow=models&amp;flow.transition=delete&amp;flow.reloadFlow=models" id="btn1050"><span>Delete model</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*281.123*/("""{"""),format.raw/*281.124*/("""epoint.ow.Utils.bindEvent("click","btn1050","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*281.214*/("""}"""),format.raw/*281.215*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*282.123*/("""{"""),format.raw/*282.124*/("""epoint.ow.Utils.bindEvent("click","btn1050","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*282.213*/("""}"""),format.raw/*282.214*/(""",null,null);</script>
                                    <a class="action action_additional" href="?action=flow.transit&amp;flow.flow=models&amp;flow.transition=additional_properties&amp;flow.reloadFlow=models" id="btn1051"><span>Additional properties</span></a>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*284.123*/("""{"""),format.raw/*284.124*/("""epoint.ow.Utils.bindEvent("click","btn1051","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*284.214*/("""}"""),format.raw/*284.215*/(""",90,null);</script>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*285.123*/("""{"""),format.raw/*285.124*/("""epoint.ow.Utils.bindEvent("click","btn1051","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*285.213*/("""}"""),format.raw/*285.214*/(""",null,null);</script>
                                </div>
                                </div>
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
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*300.155*/("""{"""),format.raw/*300.156*/("""epoint.ow.Utils.bindEvent("click","btn1040","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*300.246*/("""}"""),format.raw/*300.247*/(""",90,null);</script>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*301.155*/("""{"""),format.raw/*301.156*/("""epoint.ow.Utils.bindEvent("click","btn1040","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*301.245*/("""}"""),format.raw/*301.246*/(""",null,null);</script></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*307.135*/("""{"""),format.raw/*307.136*/("""epoint.ow.flow.Utils.setCurrentState("name_edit","view");"""),format.raw/*307.193*/("""}"""),format.raw/*307.194*/(""",null,null);</script>
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
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*327.155*/("""{"""),format.raw/*327.156*/("""epoint.ow.Utils.bindEvent("click","btn1041","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*327.246*/("""}"""),format.raw/*327.247*/(""",90,null);</script>
                                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*328.155*/("""{"""),format.raw/*328.156*/("""epoint.ow.Utils.bindEvent("click","btn1041","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*328.245*/("""}"""),format.raw/*328.246*/(""",null,null);</script></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*334.135*/("""{"""),format.raw/*334.136*/("""epoint.ow.flow.Utils.setCurrentState("description_edit","view");"""),format.raw/*334.200*/("""}"""),format.raw/*334.201*/(""",null,null);</script>
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
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*353.139*/("""{"""),format.raw/*353.140*/("""epoint.ow.Utils.bindEvent("click","btn1042","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*353.230*/("""}"""),format.raw/*353.231*/(""",90,null);</script>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*354.139*/("""{"""),format.raw/*354.140*/("""epoint.ow.Utils.bindEvent("click","btn1042","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*354.229*/("""}"""),format.raw/*354.230*/(""",null,null);</script></span></div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*354.350*/("""{"""),format.raw/*354.351*/("""epoint.ow.flow.Utils.setCurrentState("public_link","no_public_link");"""),format.raw/*354.420*/("""}"""),format.raw/*354.421*/(""",null,null);</script>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="clearFix">
                                    </div>
                            </div>
                            <div class="section">
                                <div class="header"><h2>You share this model with</h2>
                                </div>
                                <div class="content">
                                    <div id="flow_share_div"><p class="table_norows_info">Nobody. Click the button below to start sharing.</p>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*367.127*/("""{"""),format.raw/*367.128*/("""epoint.ow.table.Table.registerHandlers("table_share");"""),format.raw/*367.182*/("""}"""),format.raw/*367.183*/(""",null,null);</script>
                                    </div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*368.129*/("""{"""),format.raw/*368.130*/("""epoint.ow.flow.Utils.setCurrentState("share","view");"""),format.raw/*368.183*/("""}"""),format.raw/*368.184*/(""",null,null);</script>
                                    <div class="buttons">
                                    <p style="color:green;" id ="sharingResult"> </p>
                                    <div id="sharing" style="display: none;">
                                        <!-- <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4" method="POST" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4"> -->
                                        <!-- """),_display_(/*373.47*/helper/*373.53*/.form(action = routes.Application.shareMyStudy)/*373.100*/ {_display_(Seq[Any](format.raw/*373.102*/(""" """),format.raw/*373.103*/("""-->
                                            <table cellspacing="0" class="data_table" id="model_details_table">
                                                <tr class="row_1">
                                                    <td class="cell_1">* Email:</td>
                                                    <td>
                                                        <input class="text_field" size="35" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" type="text" id="email" maxlength="100" name="email" prompttext="">
                                                    </td>
                                                <tr class="row_2">
                                                    <td class="cell_1">* Name:</td>
                                                    <td>
                                                        <select class="select_field" id="select_privilage" name="role">
                                                            <option id="owner" value=1>Owner</option>
                                                            <option id="editor" value=2>Editor</option>
                                                            <option id="viewer" value=3>Viewer</option>
                                                        </select>

                                                    </td>
                                                    </tr>
                                                <tr class="row_3">
                                                    <td class="cell_1">Message:</td>
                                                    <td>
                                                        <textarea rows="4" cols="50" name="message" id="message"> I want to share this study with you</textarea>
                                                    </td>
                                                </tr>
                                                <tr class="row_4">
                                                    <td class="cell_1"></td>
                                                    <td>
                                                        <input type="submit" class="button_primary" id="share" name="register" value="Share" />

                                                    </td>
                                                </tr>
                                                <input id= "studyId" type="hidden" name="studyId" value="""),_display_(/*404.106*/studyId),format.raw/*404.113*/(""">
                                            </table>
                                        <!-- </form> -->
                                       <!-- """)))}),format.raw/*407.46*/(""" """),format.raw/*407.47*/("""-->
                                    </div>

                                        <a class="button_primary" id="btn1052"><span>Share model</span></a>
                                       <script>
                                        $(document).ready(function()"""),format.raw/*412.69*/("""{"""),format.raw/*412.70*/("""
                                          """),format.raw/*413.43*/("""$("#btn1052").click(function()"""),format.raw/*413.73*/("""{"""),format.raw/*413.74*/("""
                                            """),format.raw/*414.45*/("""$("#sharing").slideDown("slow");

                                            $('#share').click(function(evt) """),format.raw/*416.77*/("""{"""),format.raw/*416.78*/("""
                                            """),format.raw/*417.45*/("""//$('#errors').hide();
                                            $.ajax("""),format.raw/*418.52*/("""{"""),format.raw/*418.53*/("""
                                                """),format.raw/*419.49*/("""type : 'POST',
                                                url : '"""),_display_(/*420.57*/routes/*420.63*/.Application.shareMyStudy),format.raw/*420.88*/("""',
                                                 data : """),format.raw/*421.57*/("""{"""),format.raw/*421.58*/("""
                                                        """),format.raw/*422.57*/("""email: $("input[name=email]").val(),
                                                        role: $("select[name=role]").val() ,
                                                        message: $("textarea[name=message]").val(),
                                                        studyId: $("input[name=studyId]").val(),
                                                    """),format.raw/*426.53*/("""}"""),format.raw/*426.54*/(""",
                                                success : function(data) """),format.raw/*427.74*/("""{"""),format.raw/*427.75*/("""
                                                    """),format.raw/*428.53*/("""$("#sharingResult").html(data);
                                                """),format.raw/*429.49*/("""}"""),format.raw/*429.50*/(""",
                                                error : function(data) """),format.raw/*430.72*/("""{"""),format.raw/*430.73*/("""
                                                     """),format.raw/*431.54*/("""$("#sharingResult").html(data);
                                                    setError('Make call failed');
                                                """),format.raw/*433.49*/("""}"""),format.raw/*433.50*/("""
                                            """),format.raw/*434.45*/("""}"""),format.raw/*434.46*/(""");
                                             $("#sharing").slideUp("slow");
                                            return false;
                                                """),format.raw/*437.49*/("""}"""),format.raw/*437.50*/(""");

                                          e.preventDefault();
                                          """),format.raw/*440.43*/("""}"""),format.raw/*440.44*/(""");
                                        """),format.raw/*441.41*/("""}"""),format.raw/*441.42*/(""");
                                        </script>
                                       <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*443.131*/("""{"""),format.raw/*443.132*/("""epoint.ow.Utils.bindEvent("click","btn1052","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*443.222*/("""}"""),format.raw/*443.223*/(""",90,null);</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*444.127*/("""{"""),format.raw/*444.128*/("""epoint.ow.Utils.bindEvent("click","btn1052","epoint.ow.popup.PopupManager.openPopupAfterAjaxFlowReloadClickHandler","popup_flow_share_model_div","flow_share_model_div",[]);epoint.ow.Utils.bindEvent("click","btn1052","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*444.389*/("""}"""),format.raw/*444.390*/(""",null,null);</script> -->
                                    </div>
                                    <div class="block_dialog_container">
                                        <div class="popup initialHide" id="popup_flow_share_model_div">
                                            <div class="popup_arrow_up"></div>
                                            <a class="close-popup-button" id="btn1053"><span>X</span></a>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*450.131*/("""{"""),format.raw/*450.132*/("""epoint.ow.Utils.bindEvent("click","btn1053","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*450.222*/("""}"""),format.raw/*450.223*/(""",90,null);</script>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*451.131*/("""{"""),format.raw/*451.132*/("""epoint.ow.Utils.bindEvent("click","btn1053","epoint.ow.popup.PopupManager.closePopupClickHandler","popup_flow_share_model_div",[]);"""),format.raw/*451.263*/("""}"""),format.raw/*451.264*/(""",null,null);</script>
                                            <div class="clearFix">
                                            </div><div class="popup_inner"><div id="flow_share_model_div"><img src="/assets/images/empty_flow_content.gif"></div>
                                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*454.131*/("""{"""),format.raw/*454.132*/("""epoint.ow.flow.Utils.setCurrentState("share_model","share");"""),format.raw/*454.192*/("""}"""),format.raw/*454.193*/(""",null,null);</script>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearFix"></div>
                            </div>
                            <div class="section">
                                <div class="header"><h2>Study Topology</h2></div>
                                <div class="content">
                                    <div id="flow_versions_div">
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*465.127*/("""{"""),format.raw/*465.128*/("""epoint.ow.forms.ConfigManager.setFormConfig("show_autosaved_versions","""),format.raw/*465.198*/("""{"""),format.raw/*465.199*/(""""fieldValidatorConfigs":"""),format.raw/*465.223*/("""{"""),format.raw/*465.224*/("""}"""),format.raw/*465.225*/(""","fieldNames":["showAutosavedVersions"],"multiFieldValidatorConfigs":[]"""),format.raw/*465.296*/("""}"""),format.raw/*465.297*/(""");epoint.ow.forms.ConfigManager.setJsValidationEnabled("show_autosaved_versions",true);epoint.ow.forms.ConfigManager.setAjaxValidationEnabled("show_autosaved_versions",true);epoint.ow.forms.ConfigManager.setOnChangeValidationEnabled("show_autosaved_versions",true);"""),format.raw/*465.562*/("""}"""),format.raw/*465.563*/(""",null,null);</script>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*466.127*/("""{"""),format.raw/*466.128*/("""epoint.ow.forms.Utils.registerDefaultSubmitEventHandler("show_autosaved_versions");"""),format.raw/*466.211*/("""}"""),format.raw/*466.212*/(""",null,null);</script>
                                        <div class="form_errors initialHide" id="show_autosaved_versions_errors"><p class="form_errors_header">Form validation errors</p><div class="form_errors_list"></div>
                                        </div>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*469.127*/("""{"""),format.raw/*469.128*/("""epoint.ow.forms.ErrorManager.setErrors("show_autosaved_versions",[]);epoint.ow.forms.ErrorDisplayManager.showErrors("show_autosaved_versions");"""),format.raw/*469.271*/("""}"""),format.raw/*469.272*/(""",200,null);</script>
                                        <form accept-charset="UTF-8" action="?action=flow.transit&amp;flow.flow=versions&amp;flow.transition=toggle_show_autosaved_versions&amp;flow.reloadFlow=versions" enctype="application/x-www-form-urlencoded" id="form_show_autosaved_versions" method="POST" name="show_autosaved_versions">
                                            <input class="default_submit" name="default_submit" style="position: absolute; top: -2000px; left: -2000px" type="submit" id="show_autosaved_versions_default_submit">
                                            <div class="box_form_section" id="form_show_autosaved_versions_section_other">
                                                <div class="box_form_content">
                                                    <table cellspacing="0" class="form_table" i18n="pl.epoint.ow.i18n.ChainI18NBundle@11be3e5">
                                                        <tr class="row_1"><td class="cell_1"></td>
                                                            <td class="cell_2">
                                                                <input class="checkbox_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" type="checkbox" id="show_autosaved_versions_showAutosavedVersions" name="showAutosavedVersions" value="true"><label class="field_label" for="show_autosaved_versions_showAutosavedVersions" id="show_autosaved_versions_showAutosavedVersions_label">Show autosaved versions</label><span class="field_error_container initialHide" id="show_autosaved_versions_showAutosavedVersions_error_container"></span>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </form>
                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*484.127*/("""{"""),format.raw/*484.128*/("""registerShowAutosavedVersionsClickHandler("show_autosaved_versions_showAutosavedVersions","?action=flow.transit&flow.flow=versions&flow.transition=toggle_show_autosaved_versions&flow.reloadFlow=versions");"""),format.raw/*484.333*/("""}"""),format.raw/*484.334*/(""",null,null);</script>
                                        <div class="box_table" id="table_versions">
                                        <table cellpadding="0" cellspacing="0" class="table_table">
                                            <thead class="table_thead">
                                            <tr class="table_header">
                                                <th>Date</th>
                                                <th>Subject</th>
                                                 <th>Gender</th>
                                                <!--<th>Name (tag)</th> -->
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody class="table_body">
                                            """),format.raw/*499.1*/("""                                            """),_display_(/*499.46*/for(subject <- subjects) yield /*499.70*/ {_display_(Seq[Any](format.raw/*499.72*/("""
                                            """),_display_(/*500.46*/if(i%2 == 0)/*500.58*/ {_display_(Seq[Any](format.raw/*500.60*/("""
                                            """),format.raw/*501.45*/("""<tr class="table_row_odd table_row_first">
                                            """)))}/*502.47*/else/*502.52*/{_display_(Seq[Any](format.raw/*502.53*/("""
                                            """),format.raw/*503.45*/("""<tr class="table_row_even">
                                            """)))}),format.raw/*504.46*/("""
                                                """),_display_(/*505.50*/{i = i + 1;}),format.raw/*505.62*/("""
                                                """),format.raw/*506.49*/("""<td class="c01">
                                                    <a target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4">2014-09-23 13:57</a>
                                                </td>
                                                <td class="c02">"""),_display_(/*509.66*/subject/*509.73*/._1),format.raw/*509.76*/("""</td>
                                                <td class="c03">M</td>
                                                <!-- <td class="c04">
                                                    <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4" method="POST" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4">
                                                        <select class="select_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select">
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_" value="">Other...</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value="1">EDA Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_3" value="3">Thermal Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_4" value="4">Video</option>
                                                        </select>
                                                    </form>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*520.139*/("""{"""),format.raw/*520.140*/("""registerModelVersionActionSelectClickHandler("form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select",""""),format.raw/*520.274*/("""{"""),format.raw/*520.275*/("""\"0\":\"\",\"1\":\"model_version_as_xml?gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"2\":\"\",\"3\":\"?action=flow.transit&flow.flow=models&flow.transition=create_new_model_from_model_version&flow.reloadFlow=models&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"4\":\"?action=flow.transit&flow.flow=versions&flow.transition=delete_version&flow.reloadFlow=versions&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"5\":\"?action=flow.transit&flow.flow=versions&flow.transition=restore_version&flow.reloadFlow=versions&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\""""),format.raw/*520.939*/("""}"""),format.raw/*520.940*/("""");"""),format.raw/*520.943*/("""}"""),format.raw/*520.944*/(""",null,null);</script>
                                                </td> -->
                                                <td class="c05"><a class="button" href="/editSubject?SubjectId="""),_display_(/*522.113*/subject/*522.120*/._1),format.raw/*522.123*/("""&studyNo="""),_display_(/*522.133*/studyId),format.raw/*522.140*/(""""><span>Edit</span></a></td>
                                                <td class="c06"><a class="button"  href="/editSubject?SubjectId="""),_display_(/*523.114*/subject/*523.121*/._1),format.raw/*523.124*/("""&studyNo="""),_display_(/*523.134*/studyId),format.raw/*523.141*/(""""><span>Delete</span></a></td>

                                                <!-- """),_display_(/*525.55*/helper/*525.61*/.form(action = routes.Application.uploadFile, 'enctype -> "multipart/form-data")/*525.141*/ {_display_(Seq[Any](format.raw/*525.143*/("""
                                                """),format.raw/*526.49*/("""<td>
                                                    <input type="file" name="fileUpload">
                                                </td>
                                                <td>
                                                    <input type="submit" value="Upload">
                                                    """)))}),format.raw/*531.54*/("""
                                                """),format.raw/*532.49*/("""</td> -->
                                            </tr>
                                            """)))}),format.raw/*534.46*/("""
                                            """),format.raw/*535.45*/("""<!-- <tr class="table_row_even">
                                                <td class="c01">
                                                    <a target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4">2014-09-23 13:57</a>
                                                </td>
                                                <td class="c02">S002</td>
                                                <td class="c03">F</td>
                                                <td class="c03">
                                                    <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4" method="POST" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4">
                                                        <select class="select_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select" name="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select">
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_" value="">Other...</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_1" value="1">EDA Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_3" value="3">Thermal Signal</option>
                                                            <option id="form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select_4" value="4">Video</option>
                                                        </select>
                                                    </form>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*550.139*/("""{"""),format.raw/*550.140*/("""registerModelVersionActionSelectClickHandler("form_versions_avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4_select",""""),format.raw/*550.274*/("""{"""),format.raw/*550.275*/("""\"0\":\"\",\"1\":\"model_version_as_xml?gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"2\":\"\",\"3\":\"?action=flow.transit&flow.flow=models&flow.transition=create_new_model_from_model_version&flow.reloadFlow=models&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"4\":\"?action=flow.transit&flow.flow=versions&flow.transition=delete_version&flow.reloadFlow=versions&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\",\"5\":\"?action=flow.transit&flow.flow=versions&flow.transition=restore_version&flow.reloadFlow=versions&gid=avmMoOfAa88BZFkbhDIE2swy6EGJ6QVmwtW7KMk5SbtgxnVrVvxQINqa6aN2CIr4\""""),format.raw/*550.939*/("""}"""),format.raw/*550.940*/("""");"""),format.raw/*550.943*/("""}"""),format.raw/*550.944*/(""",null,null);</script>
                                                </td>
                                                <td class="c04"><a class="button" target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/oGdetLq8pjHcoavNncz8crYI3bQ34b6mYxsDnT4FO881JwOySctO8FVCDGDQrIkE"><span>Edit</span></a></td>
                                            </tr> -->
                                           <!-- <tr class="table_row_odd">
                                                <td class="c01">
                                                    <a target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW">2014-09-17 23:08</a>
                                                </td>
                                                <td class="c02">salah taamne</td>
                                                <td class="c03">Window close</td>
                                                <td class="c04">
                                                    <div id="flow_version_name_edit_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_div">
                                                        <a class="button" href="?action=flow.transit&amp;flow.flow=version_name_edit_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW&amp;flow.transition=edit&amp;flow.reloadFlow=version_name_edit_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW" id="btn1060"><span>Assign</span></a>
                                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*563.143*/("""{"""),format.raw/*563.144*/("""epoint.ow.Utils.bindEvent("click","btn1060","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*563.234*/("""}"""),format.raw/*563.235*/(""",90,null);</script>
                                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*564.143*/("""{"""),format.raw/*564.144*/("""epoint.ow.Utils.bindEvent("click","btn1060","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*564.233*/("""}"""),format.raw/*564.234*/(""",null,null);</script>
                                                    </div>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*566.139*/("""{"""),format.raw/*566.140*/("""epoint.ow.flow.Utils.setCurrentState("version_name_edit_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW","view");"""),format.raw/*566.270*/("""}"""),format.raw/*566.271*/(""",null,null);</script>
                                                </td>
                                                <td class="c05">
                                                    <a class="button" target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW"><span>Preview</span></a>
                                                </td>
                                                <td class="c06">
                                                    <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW" method="POST" name="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW">
                                                        <select class="select_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select" name="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select">
                                                            <option id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select_" value="">More...</option>
                                                            <option id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select_1" value="1">Export to XML</option>
                                                            <option id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select_3" value="3">Create new model from this version</option>
                                                            <option id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select_4" value="4">Delete version</option>
                                                            <option id="form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select_5" value="5">Restore version</option>
                                                        </select>
                                                    </form>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*581.139*/("""{"""),format.raw/*581.140*/("""registerModelVersionActionSelectClickHandler("form_versions_lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW_select",""""),format.raw/*581.274*/("""{"""),format.raw/*581.275*/("""\"0\":\"\",\"1\":\"model_version_as_xml?gid=lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW\",\"2\":\"\",\"3\":\"?action=flow.transit&flow.flow=models&flow.transition=create_new_model_from_model_version&flow.reloadFlow=models&gid=lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW\",\"4\":\"?action=flow.transit&flow.flow=versions&flow.transition=delete_version&flow.reloadFlow=versions&gid=lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW\",\"5\":\"?action=flow.transit&flow.flow=versions&flow.transition=restore_version&flow.reloadFlow=versions&gid=lBRZX2qGLg5kK5eO6wX7Sd8s4SXpDJQPuVKwROxNE16HSYOaEaZMpdRYjyc7U0gW\""""),format.raw/*581.939*/("""}"""),format.raw/*581.940*/("""");"""),format.raw/*581.943*/("""}"""),format.raw/*581.944*/(""",null,null);</script>
                                                </td>
                                            </tr>
                                            <tr class="table_row_even table_row_last">
                                                <td class="c01">
                                                    <a target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l">2014-09-17 15:56</a>
                                                </td>
                                                <td class="c02">salah taamne</td>
                                                <td class="c03">Window close</td>
                                                <td class="c04">
                                                    <div id="flow_version_name_edit_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_div">
                                                    <a class="button" href="?action=flow.transit&amp;flow.flow=version_name_edit_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l&amp;flow.transition=edit&amp;flow.reloadFlow=version_name_edit_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l" id="btn1061"><span>Assign</span></a>
                                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*593.143*/("""{"""),format.raw/*593.144*/("""epoint.ow.Utils.bindEvent("click","btn1061","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*593.234*/("""}"""),format.raw/*593.235*/(""",90,null);</script>
                                                        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*594.143*/("""{"""),format.raw/*594.144*/("""epoint.ow.Utils.bindEvent("click","btn1061","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*594.233*/("""}"""),format.raw/*594.234*/(""",null,null);</script>
                                                    </div>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*596.139*/("""{"""),format.raw/*596.140*/("""epoint.ow.flow.Utils.setCurrentState("version_name_edit_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l","view");"""),format.raw/*596.270*/("""}"""),format.raw/*596.271*/(""",null,null);</script>
                                                </td>
                                                <td class="c05">
                                                    <a class="button" target="_blank" href="/model-version-view/6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ/BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l"><span>Preview</span></a>
                                                </td>
                                                <td class="c06">
                                                    <form accept-charset="UTF-8" enctype="application/x-www-form-urlencoded" id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l" method="POST" name="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l">
                                                        <select class="select_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select" name="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select">
                                                            <option id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select_" value="">More...</option>
                                                            <option id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select_1" value="1">Export to XML</option>
                                                            <option id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select_3" value="3">Create new model from this version</option><
                                                            option id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select_4" value="4">Delete version</option>
                                                            <option id="form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select_5" value="5">Restore version</option>
                                                        </select>
                                                    </form>
                                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*611.139*/("""{"""),format.raw/*611.140*/("""registerModelVersionActionSelectClickHandler("form_versions_BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l_select",""""),format.raw/*611.274*/("""{"""),format.raw/*611.275*/("""\"0\":\"\",\"1\":\"model_version_as_xml?gid=BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l\",\"2\":\"\",\"3\":\"?action=flow.transit&flow.flow=models&flow.transition=create_new_model_from_model_version&flow.reloadFlow=models&gid=BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l\",\"4\":\"?action=flow.transit&flow.flow=versions&flow.transition=delete_version&flow.reloadFlow=versions&gid=BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l\",\"5\":\"?action=flow.transit&flow.flow=versions&flow.transition=restore_version&flow.reloadFlow=versions&gid=BnBEdon09rv80qulrPKzrBhRF58IMBDoxAFrcAQEZHoxIaOOvcPToPdMox3x4V2l\""""),format.raw/*611.939*/("""}"""),format.raw/*611.940*/("""");"""),format.raw/*611.943*/("""}"""),format.raw/*611.944*/(""",null,null);</script>
                                                </td>
                                            </tr> -->
                                            </tbody>
                                        </table>
                                        <a class="button_primary" id="addSubject"><span>Add Subject</span></a>
                                        <script>
                                        $(document).ready(function()"""),format.raw/*618.69*/("""{"""),format.raw/*618.70*/("""
                                          """),format.raw/*619.43*/("""$("#addSubject").click(function()"""),format.raw/*619.76*/("""{"""),format.raw/*619.77*/("""
                                            """),format.raw/*620.45*/("""$("#newSubject").slideDown("slow");

                                            """),format.raw/*622.45*/("""}"""),format.raw/*622.46*/(""");
                                            """),format.raw/*623.45*/("""}"""),format.raw/*623.46*/(""");
                                            </script>

                                        </div>
                                        """),_display_(/*627.42*/helper/*627.48*/.form(action = routes.Application.insertSubject, 'enctype -> "multipart/form-data")/*627.131*/ {_display_(Seq[Any](format.raw/*627.133*/("""
                                        """),format.raw/*628.41*/("""<div id="newSubject" style="display: none;">

                                            <table cellspacing="0" class="data_table" id="add_subject_table">
                                                <tr class="row_1">
                                                    <td class="cell_1">* Subject ID:</td>
                                                    <td>
                                                        <input class="text_field" size="10"  type="text" id="subject_id" maxlength="10" name="subject_id" prompttext="">
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
                                                        <input id= "study_id" type="hidden" name="study_id" value="""),_display_(/*659.116*/studyId),format.raw/*659.123*/(""">
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

                                            </table>
                                            """)))}),format.raw/*673.46*/("""

                                          """),format.raw/*675.43*/("""<!--   <script>
                                            $(document).ready(function()"""),format.raw/*676.73*/("""{"""),format.raw/*676.74*/("""
                                            """),format.raw/*677.45*/("""$("#addSub").click(function()"""),format.raw/*677.74*/("""{"""),format.raw/*677.75*/("""

                                            """),format.raw/*679.45*/("""$.ajax("""),format.raw/*679.52*/("""{"""),format.raw/*679.53*/("""
                                            """),format.raw/*680.45*/("""type : 'POST',
                                            url : '"""),_display_(/*681.53*/routes/*681.59*/.Application.insertSubject),format.raw/*681.85*/("""',
                                            data : """),format.raw/*682.52*/("""{"""),format.raw/*682.53*/("""
                                            """),format.raw/*683.45*/("""subject_id: $("input[name=subject_id]").val(),
                                            study_id: $("input[name=studyId]").val(),
                                            subject_DOB: $("input[name=subject_DOB]").val(),
                                            STAI: $("input[name=STAI]").val(),
                                            PAS: $("input[name=PAS]").val(),
                                            NAS: $("input[name=NAS]").val()
                                            """),format.raw/*689.45*/("""}"""),format.raw/*689.46*/(""",
                                            success : function(data) """),format.raw/*690.70*/("""{"""),format.raw/*690.71*/("""
                                            """),format.raw/*691.45*/("""alert('Sucess');
                                            $("#sharingResult").html(data);
                                            """),format.raw/*693.45*/("""}"""),format.raw/*693.46*/(""",
                                            error : function(data) """),format.raw/*694.68*/("""{"""),format.raw/*694.69*/("""
                                            """),format.raw/*695.45*/("""alert('Not Sucess');
                                            $("#sharingResult").html(data);
                                            setError('Make call failed');
                                            """),format.raw/*698.45*/("""}"""),format.raw/*698.46*/("""
                                            """),format.raw/*699.45*/("""}"""),format.raw/*699.46*/(""");
                                            return false;
                                            """),format.raw/*701.45*/("""}"""),format.raw/*701.46*/(""");

                                            //e.preventDefault();
                                            """),format.raw/*704.45*/("""}"""),format.raw/*704.46*/(""");
                                            </script> -->



                                        </div>

                                        <div class="buttons">
                                        </div>
                                    </div>
                                    <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*714.123*/("""{"""),format.raw/*714.124*/("""epoint.ow.flow.Utils.setCurrentState("versions","view");"""),format.raw/*714.180*/("""}"""),format.raw/*714.181*/(""",null,null);</script>
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
            <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*730.104*/("""{"""),format.raw/*730.105*/("""App.embedded();app.load("6hAY25fT5vSlN8rulB1TOQKdrnvhRVnvUcZsCJOXOEgCgku4UIl5sxSrAP6OMAqQ");"""),format.raw/*730.197*/("""}"""),format.raw/*730.198*/(""",null,null);</script> -->

        </div>
    <!--  <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*733.97*/("""{"""),format.raw/*733.98*/("""epoint.ow.flow.Utils.setCurrentState("models","details");"""),format.raw/*733.155*/("""}"""),format.raw/*733.156*/(""",null,null);</script> -->
    </div>
</div>
<div id="footer_wrapper">
    <div id="footer">
        <ul class="links">
            <li><a href="/support/request-a-feature" id="btn1074" target="_blank"><span>Request a feature</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*740.103*/("""{"""),format.raw/*740.104*/("""epoint.ow.Utils.bindEvent("click","btn1074","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*740.194*/("""}"""),format.raw/*740.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*741.103*/("""{"""),format.raw/*741.104*/("""epoint.ow.Utils.bindEvent("click","btn1074","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*741.197*/("""}"""),format.raw/*741.198*/(""",null,null);</script>
            </li>
            <li><a href="/support/report-a-problem" id="btn1075" target="_blank"><span>Report a problem</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*744.103*/("""{"""),format.raw/*744.104*/("""epoint.ow.Utils.bindEvent("click","btn1075","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*744.194*/("""}"""),format.raw/*744.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*745.103*/("""{"""),format.raw/*745.104*/("""epoint.ow.Utils.bindEvent("click","btn1075","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*745.197*/("""}"""),format.raw/*745.198*/(""",null,null);</script>
            </li>
            <li>
                <a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn1076"><span>Recommend us</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*749.103*/("""{"""),format.raw/*749.104*/("""epoint.ow.Utils.bindEvent("click","btn1076","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*749.194*/("""}"""),format.raw/*749.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*750.103*/("""{"""),format.raw/*750.104*/("""epoint.ow.Utils.bindEvent("click","btn1076","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*750.197*/("""}"""),format.raw/*750.198*/(""",null,null);</script>
            </li>
            <li>
                <a href="/support/ask-a-question" id="btn1077" target="_blank"><span>Ask a question</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*754.103*/("""{"""),format.raw/*754.104*/("""epoint.ow.Utils.bindEvent("click","btn1077","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*754.194*/("""}"""),format.raw/*754.195*/(""",90,null);</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*755.103*/("""{"""),format.raw/*755.104*/("""epoint.ow.Utils.bindEvent("click","btn1077","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*755.197*/("""}"""),format.raw/*755.198*/(""",null,null);</script>
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
<!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*779.92*/("""{"""),format.raw/*779.93*/("""oneweb.CsrfTokenManager.setToken("Xlgx5t9sddK8TDNxtjUMYBh74aW73CIl");"""),format.raw/*779.162*/("""}"""),format.raw/*779.163*/(""",10,null);
			  </script> -->
</body>
</html>"""))}
  }

  def render(answer:String,username:String,studyId:Int,subjects:List[scala.Tuple2[String, java.util.Date]]): play.twirl.api.HtmlFormat.Appendable = apply(answer,username,studyId,subjects)

  def f:((String,String,Int,List[scala.Tuple2[String, java.util.Date]]) => play.twirl.api.HtmlFormat.Appendable) = (answer,username,studyId,subjects) => apply(answer,username,studyId,subjects)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:25:56 CDT 2015
                  SOURCE: C:/first_play/app/views/editStudy.scala.html
                  HASH: c9329fc8672b022ff94a99305a0f81f55b648e88
                  MATRIX: 563->1|788->100|818->104|2318->1576|2347->1577|2386->1588|2415->1589|2736->1881|2766->1882|2810->1897|2840->1898|2935->1964|2965->1965|3053->2024|3083->2025|3113->2026|3253->2138|3282->2139|3381->2209|3411->2210|3469->2239|3499->2240|3565->2277|3595->2278|3944->2599|3973->2600|4012->2611|4041->2612|4070->2613|4286->2801|4315->2802|4848->3308|4877->3309|5192->3595|5222->3596|5259->3604|5308->3624|5338->3625|5371->3629|5579->3808|5609->3809|5644->3815|5710->3852|5740->3853|5770->3854|5804->3859|5834->3860|5869->3866|5932->3900|5962->3901|5995->3905|6036->3917|6066->3918|6101->3924|6432->4226|6462->4227|6795->4533|6824->4541|7251->4939|7281->4940|7400->5030|7430->5031|7594->5166|7624->5167|7746->5260|7776->5261|8449->5905|8479->5906|8598->5996|8628->5997|8784->6124|8814->6125|8932->6214|8962->6215|9149->6373|9179->6374|9423->6589|9453->6590|9632->6740|9662->6741|9763->6813|9793->6814|10065->7057|10095->7058|10214->7148|10244->7149|10391->7268|10421->7269|10543->7362|10573->7363|11101->7862|11131->7863|11250->7953|11280->7954|11414->8059|11444->8060|11566->8153|11596->8154|11843->8372|11873->8373|11992->8463|12022->8464|12156->8569|12186->8570|12309->8663|12340->8664|12597->8891|12628->8892|12748->8982|12779->8983|12914->9088|12945->9089|13068->9182|13099->9183|14270->10325|14300->10326|14419->10416|14449->10417|14609->10548|14639->10549|14761->10642|14791->10643|15106->10929|15136->10930|15255->11020|15285->11021|15445->11152|15475->11153|15597->11246|15627->11247|15940->11531|15970->11532|16089->11622|16119->11623|16279->11754|16309->11755|16431->11848|16461->11849|18916->14275|18946->14276|18976->14277|19006->14278|19038->14281|19068->14282|19098->14283|19144->14300|19174->14301|19204->14302|19234->14303|19272->14312|19302->14313|19332->14314|19371->14323|19402->14324|19433->14325|19464->14326|19506->14338|19537->14339|19568->14340|19606->14348|19637->14349|19668->14350|19699->14351|19734->14356|19765->14357|19796->14358|19827->14359|19904->14406|19935->14407|19966->14408|19997->14409|20035->14417|20066->14418|20097->14419|20128->14420|21372->15634|21403->15635|21434->15636|21465->15637|21500->15642|21531->15643|21562->15644|21777->15829|21808->15830|21839->15831|21870->15832|21905->15837|21936->15838|21967->15839|22179->16021|22210->16022|22241->16023|22272->16024|22307->16029|22338->16030|22369->16031|23086->16718|23117->16719|23237->16809|23268->16810|23437->16949|23468->16950|23587->17039|23618->17040|24396->17788|24427->17789|24559->17891|24590->17892|25064->18336|25095->18337|25215->18427|25246->18428|25427->18579|25458->18580|25621->18713|25652->18714|33555->26587|33586->26588|33724->26696|33755->26697|34117->27029|34148->27030|34303->27155|34334->27156|34575->27367|34606->27368|34726->27458|34757->27459|34946->27618|34977->27619|35140->27752|35171->27753|35645->28197|35676->28198|35788->28280|35819->28281|36114->28546|36145->28547|36273->28645|36304->28646|36477->28789|36508->28790|36624->28876|36655->28877|37258->29450|37289->29451|37409->29541|37440->29542|37613->29685|37644->29686|37955->29967|37986->29968|38491->30443|38522->30444|38642->30534|38673->30535|38850->30682|38881->30683|39052->30824|39083->30825|39545->31257|39576->31258|39682->31334|39713->31335|40225->31817|40256->31818|40376->31908|40407->31909|40580->32052|40611->32053|40900->32312|40931->32313|41425->32777|41456->32778|41576->32868|41607->32869|41784->33016|41815->33017|41975->33147|42006->33148|42503->33615|42534->33616|42629->33681|42660->33682|43189->34181|43220->34182|43340->34272|43371->34273|43544->34416|43575->34417|43694->34506|43725->34507|44115->34867|44146->34868|44266->34958|44297->34959|44470->35102|44501->35103|44620->35192|44651->35193|45069->35581|45100->35582|45220->35672|45251->35673|45424->35816|45455->35817|45574->35906|45605->35907|46981->37253|47012->37254|47132->37344|47163->37345|47368->37520|47399->37521|47518->37610|47549->37611|48063->38095|48094->38096|48181->38153|48212->38154|50022->39934|50053->39935|50173->40025|50204->40026|50409->40201|50440->40202|50559->40291|50590->40292|51104->40776|51135->40777|51229->40841|51260->40842|52950->42502|52981->42503|53101->42593|53132->42594|53321->42753|53352->42754|53471->42843|53502->42844|53652->42964|53683->42965|53782->43034|53813->43035|54690->43882|54721->43883|54805->43937|54836->43938|55017->44089|55048->44090|55131->44143|55162->44144|55792->44746|55808->44752|55866->44799|55908->44801|55939->44802|58464->47298|58494->47305|58686->47465|58716->47466|59022->47743|59052->47744|59125->47788|59184->47818|59214->47819|59289->47865|59430->47977|59460->47978|59535->48024|59639->48099|59669->48100|59748->48150|59848->48222|59864->48228|59911->48253|60000->48313|60030->48314|60117->48372|60529->48755|60559->48756|60664->48832|60694->48833|60777->48887|60887->48968|60917->48969|61020->49043|61050->49044|61134->49099|61327->49263|61357->49264|61432->49310|61462->49311|61679->49499|61709->49500|61849->49611|61879->49612|61952->49656|61982->49657|62197->49842|62228->49843|62348->49933|62379->49934|62556->50081|62587->50082|62878->50343|62909->50344|63506->50911|63537->50912|63657->51002|63688->51003|63869->51154|63900->51155|64061->51286|64092->51287|64506->51671|64537->51672|64627->51732|64658->51733|65370->52415|65401->52416|65501->52486|65532->52487|65586->52511|65617->52512|65648->52513|65749->52584|65780->52585|66075->52850|66106->52851|66285->53000|66316->53001|66429->53084|66460->53085|66894->53489|66925->53490|67098->53633|67129->53634|69276->55752|69307->55753|69542->55958|69573->55959|70565->56963|70638->57008|70679->57032|70720->57034|70795->57081|70817->57093|70858->57095|70933->57141|71042->57231|71056->57236|71096->57237|71171->57283|71277->57357|71356->57408|71390->57420|71469->57470|71885->57858|71902->57865|71927->57868|73775->59686|73806->59687|73970->59821|74001->59822|74695->60486|74726->60487|74759->60490|74790->60491|75013->60685|75031->60692|75057->60695|75096->60705|75126->60712|75298->60855|75316->60862|75342->60865|75381->60875|75411->60882|75527->60970|75543->60976|75634->61056|75676->61058|75755->61108|76136->61457|76215->61507|76354->61614|76429->61660|78745->63946|78776->63947|78940->64081|78971->64082|79665->64746|79696->64747|79729->64750|79760->64751|81548->66509|81579->66510|81699->66600|81730->66601|81923->66764|81954->66765|82073->66854|82104->66855|82355->67076|82386->67077|82546->67207|82577->67208|85037->69638|85068->69639|85232->69773|85263->69774|85957->70438|85988->70439|86021->70442|86052->70443|87561->71922|87592->71923|87712->72013|87743->72014|87936->72177|87967->72178|88086->72267|88117->72268|88368->72489|88399->72490|88559->72620|88590->72621|91050->75051|91081->75052|91245->75186|91276->75187|91970->75851|92001->75852|92034->75855|92065->75856|92561->76323|92591->76324|92664->76368|92726->76401|92756->76402|92831->76448|92943->76531|92973->76532|93050->76580|93080->76581|93258->76731|93274->76737|93368->76820|93410->76822|93481->76864|96252->79606|96282->79613|97141->80440|97216->80486|97334->80575|97364->80576|97439->80622|97497->80651|97527->80652|97604->80700|97640->80707|97670->80708|97745->80754|97841->80822|97857->80828|97905->80854|97989->80909|98019->80910|98094->80956|98631->81464|98661->81465|98762->81537|98792->81538|98867->81584|99035->81723|99065->81724|99164->81794|99194->81795|99269->81841|99516->82059|99546->82060|99621->82106|99651->82107|99787->82214|99817->82215|99963->82332|99993->82333|100419->82729|100450->82730|100536->82786|100567->82787|101252->83442|101283->83443|101405->83535|101436->83536|101606->83677|101636->83678|101723->83735|101754->83736|102129->84081|102160->84082|102280->84172|102311->84173|102464->84296|102495->84297|102618->84390|102649->84391|102939->84651|102970->84652|103090->84742|103121->84743|103274->84866|103305->84867|103428->84960|103459->84961|103780->85252|103811->85253|103931->85343|103962->85344|104115->85467|104146->85468|104269->85561|104300->85562|104604->85836|104635->85837|104755->85927|104786->85928|104939->86051|104970->86052|105093->86145|105124->86146|106447->87440|106477->87441|106576->87510|106607->87511
                  LINES: 19->1|22->1|24->3|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|43->22|43->22|43->22|43->22|43->22|43->22|43->22|43->22|44->23|44->23|44->23|44->23|44->23|49->28|49->28|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|57->36|62->41|62->41|67->46|67->46|67->46|67->46|68->47|68->47|68->47|68->47|79->58|79->58|79->58|79->58|80->59|80->59|80->59|80->59|82->61|82->61|82->61|82->61|84->63|84->63|84->63|84->63|88->67|88->67|88->67|88->67|89->68|89->68|89->68|89->68|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|94->73|108->87|108->87|108->87|108->87|109->88|109->88|109->88|109->88|112->91|112->91|112->91|112->91|113->92|113->92|113->92|113->92|116->95|116->95|116->95|116->95|117->96|117->96|117->96|117->96|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|147->126|163->142|163->142|163->142|163->142|163->142|163->142|163->142|164->143|164->143|164->143|164->143|164->143|164->143|164->143|165->144|165->144|165->144|165->144|165->144|165->144|165->144|175->154|175->154|175->154|175->154|176->155|176->155|176->155|176->155|184->163|184->163|184->163|184->163|187->166|187->166|187->166|187->166|188->167|188->167|188->167|188->167|253->232|253->232|253->232|253->232|255->234|255->234|255->234|255->234|256->235|256->235|256->235|256->235|257->236|257->236|257->236|257->236|262->241|262->241|262->241|262->241|264->243|264->243|264->243|264->243|265->244|265->244|265->244|265->244|268->247|268->247|268->247|268->247|269->248|269->248|269->248|269->248|273->252|273->252|273->252|273->252|274->253|274->253|274->253|274->253|278->257|278->257|278->257|278->257|283->262|283->262|283->262|283->262|284->263|284->263|284->263|284->263|288->267|288->267|288->267|288->267|289->268|289->268|289->268|289->268|294->273|294->273|294->273|294->273|299->278|299->278|299->278|299->278|300->279|300->279|300->279|300->279|302->281|302->281|302->281|302->281|303->282|303->282|303->282|303->282|305->284|305->284|305->284|305->284|306->285|306->285|306->285|306->285|321->300|321->300|321->300|321->300|322->301|322->301|322->301|322->301|328->307|328->307|328->307|328->307|348->327|348->327|348->327|348->327|349->328|349->328|349->328|349->328|355->334|355->334|355->334|355->334|374->353|374->353|374->353|374->353|375->354|375->354|375->354|375->354|375->354|375->354|375->354|375->354|388->367|388->367|388->367|388->367|389->368|389->368|389->368|389->368|394->373|394->373|394->373|394->373|394->373|425->404|425->404|428->407|428->407|433->412|433->412|434->413|434->413|434->413|435->414|437->416|437->416|438->417|439->418|439->418|440->419|441->420|441->420|441->420|442->421|442->421|443->422|447->426|447->426|448->427|448->427|449->428|450->429|450->429|451->430|451->430|452->431|454->433|454->433|455->434|455->434|458->437|458->437|461->440|461->440|462->441|462->441|464->443|464->443|464->443|464->443|465->444|465->444|465->444|465->444|471->450|471->450|471->450|471->450|472->451|472->451|472->451|472->451|475->454|475->454|475->454|475->454|486->465|486->465|486->465|486->465|486->465|486->465|486->465|486->465|486->465|486->465|486->465|487->466|487->466|487->466|487->466|490->469|490->469|490->469|490->469|505->484|505->484|505->484|505->484|519->499|519->499|519->499|519->499|520->500|520->500|520->500|521->501|522->502|522->502|522->502|523->503|524->504|525->505|525->505|526->506|529->509|529->509|529->509|540->520|540->520|540->520|540->520|540->520|540->520|540->520|540->520|542->522|542->522|542->522|542->522|542->522|543->523|543->523|543->523|543->523|543->523|545->525|545->525|545->525|545->525|546->526|551->531|552->532|554->534|555->535|570->550|570->550|570->550|570->550|570->550|570->550|570->550|570->550|583->563|583->563|583->563|583->563|584->564|584->564|584->564|584->564|586->566|586->566|586->566|586->566|601->581|601->581|601->581|601->581|601->581|601->581|601->581|601->581|613->593|613->593|613->593|613->593|614->594|614->594|614->594|614->594|616->596|616->596|616->596|616->596|631->611|631->611|631->611|631->611|631->611|631->611|631->611|631->611|638->618|638->618|639->619|639->619|639->619|640->620|642->622|642->622|643->623|643->623|647->627|647->627|647->627|647->627|648->628|679->659|679->659|693->673|695->675|696->676|696->676|697->677|697->677|697->677|699->679|699->679|699->679|700->680|701->681|701->681|701->681|702->682|702->682|703->683|709->689|709->689|710->690|710->690|711->691|713->693|713->693|714->694|714->694|715->695|718->698|718->698|719->699|719->699|721->701|721->701|724->704|724->704|734->714|734->714|734->714|734->714|750->730|750->730|750->730|750->730|753->733|753->733|753->733|753->733|760->740|760->740|760->740|760->740|761->741|761->741|761->741|761->741|764->744|764->744|764->744|764->744|765->745|765->745|765->745|765->745|769->749|769->749|769->749|769->749|770->750|770->750|770->750|770->750|774->754|774->754|774->754|774->754|775->755|775->755|775->755|775->755|799->779|799->779|799->779|799->779
                  -- GENERATED --
              */
          