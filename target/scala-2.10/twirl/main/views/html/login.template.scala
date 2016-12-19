
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*4.1*/("""<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>StressBook</title><meta content="width=1170" name="viewport">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta content="IRQcweD5CIs6Qc-uVi9xT3BD-1jPvDxyk3-9LUah9T8" name="google-site-verification">
    <link href="/assets/images/cpllogo.png" rel="icon" type="image/png">
    <script type="text/javascript" src="/assets/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/assets/js/ow-all.js"></script>
    <script type="text/javascript" src="/assets/js/webapp.js"></script>
    <script type="text/javascript" src="/assets/js/browser-features.js"></script>
    <script type="text/javascript" src="/assets/js/common.js"></script>
    <script type="text/javascript" src="/assets/js/notifications.js"></script>
    <script type="text/javascript" src="/assets/js/login.js"></script>
    <script type="text/javascript" src="/assets/js/my_models.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/dbwm.css">
</head>
<body>
<div id="wrapper">
    <div id="header_wrapper">
        <div id="header"><a class="logo" href="http://www.cpl.uh.edu/" target="_blank">
            <img alt="CPL Logo" src="/assets/images/cpllogo.png"  width="70" height="40"></a>
            <ul class="user_menu">
                <li class="login"><a href="/login" id="btn16"><span>Log in</span></a></li><li><a href="/signup" id="btn17"><span>Sign up</span></a></li></ul>
            <ul class="links">
                <li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span>
                    <div class="dropdown_menu_holder">
                        <div class="dropdown_menu"><ul class="m">
                            <li><a href="javascript: IntroGuide.start();" id="take-application-tour-menu-item" style="display: none;">Take application tour</a>
                            </li>
                            <li id="shortcut-helper-menu-item" style="display: none;"><a href="javascript: app.showShortcutsHelper();">Keyboard shortcuts</a></li>
                            <li><a target="_blank" href="http://vertabelo.com">StressBook website</a></li>
                            <li><a target="_blank" href="http://vertabelo.com/documentation">Documentation</a></li>
                            <li><a target="_blank" href="http://vertabelo.com/faq">FAQ</a></li>
                            <li><a href="/support/ask-a-question" id="btn18" target="_blank"><span>Ask a question</span></a>
                            </li>
                            <li><a href="/support/request-a-feature" id="btn19" target="_blank"><span>Request a feature</span></a>
                            </li>
                            <li><a href="/support/report-a-problem" id="btn20" target="_blank"><span>Report a problem</span></a>
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
                <div id="not-supported-browser-info-header" style="display: none">Editing database model is not supported in your web browser. Use Google Chrome. <a href="http://www.vertabelo.com/faq#chrome" target="_blank">Learn more</a></div>
                <div id="oneweb-messages-placeholder"></div>
            </div> -->
            <div id="flow_login_div">
                <div class="box_pre_wrapper">
                    <div class="box_wrapper box_wrapper__decorated">
                        <div class="box box__login"><div class="box_inner">
                            <div class="form_errors initialHide" id="login_errors"><p class="form_errors_header">Form validation errors</p><div class="form_errors_list"></div></div>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*67.115*/("""{"""),format.raw/*67.116*/("""epoint.ow.forms.ErrorManager.setErrors("login",[]);epoint.ow.forms.ErrorDisplayManager.showErrors("login");"""),format.raw/*67.223*/("""}"""),format.raw/*67.224*/(""",200,null);</script>
                            <div class="header"><h1>Log in</h1></div>
                            <div class="content">
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*70.119*/("""{"""),format.raw/*70.120*/("""epoint.ow.forms.ConfigManager.setFormConfig("login","""),format.raw/*70.172*/("""{"""),format.raw/*70.173*/(""""fieldValidatorConfigs":"""),format.raw/*70.197*/("""{"""),format.raw/*70.198*/(""""password":["""),format.raw/*70.210*/("""{"""),format.raw/*70.211*/(""""hasJSValidator":true,"runIfNotAllFieldsFilled":true,"validatorParameters":[],"notValidFieldNames":["password"],"isAjaxValidator":true,"mainFieldName":["password"],"JSFunctionName":"pl.epoint.ow.forms.validators.ValueRequiredValidator","isRunOnChangeEvent":true,"allFieldNames":["password"],"errorMessagesMap":"""),format.raw/*70.521*/("""{"""),format.raw/*70.522*/(""""default":"Please enter your password""""),format.raw/*70.560*/("""}"""),format.raw/*70.561*/(""","validatorName":"ValueRequiredValidator""""),format.raw/*70.602*/("""}"""),format.raw/*70.603*/("""],"login":["""),format.raw/*70.614*/("""{"""),format.raw/*70.615*/(""""hasJSValidator":true,"runIfNotAllFieldsFilled":true,"validatorParameters":[],"notValidFieldNames":["login"],"isAjaxValidator":true,"mainFieldName":["login"],"JSFunctionName":"pl.epoint.ow.forms.validators.ValueRequiredValidator","isRunOnChangeEvent":true,"allFieldNames":["login"],"errorMessagesMap":"""),format.raw/*70.916*/("""{"""),format.raw/*70.917*/(""""default":"Please enter your email""""),format.raw/*70.952*/("""}"""),format.raw/*70.953*/(""","validatorName":"ValueRequiredValidator""""),format.raw/*70.994*/("""}"""),format.raw/*70.995*/("""]"""),format.raw/*70.996*/("""}"""),format.raw/*70.997*/(""","fieldNames":["login","password","rememberMe"],"multiFieldValidatorConfigs":["""),format.raw/*70.1075*/("""{"""),format.raw/*70.1076*/(""""hasJSValidator":false,"runIfNotAllFieldsFilled":false,"notValidFieldNames":["login","password"],"isAjaxValidator":false,"mainFieldName":null,"isRunOnChangeEvent":false,"allFieldNames":["login","password"],"errorMessagesMap":"""),format.raw/*70.1301*/("""{"""),format.raw/*70.1302*/(""""default":"You have entered an incorrect Email or Password. Please try again (make sure your Caps Lock key is off).""""),format.raw/*70.1418*/("""}"""),format.raw/*70.1419*/(""","validatorName":"AuthenticationFailedFormValidator""""),format.raw/*70.1471*/("""}"""),format.raw/*70.1472*/("""]"""),format.raw/*70.1473*/("""}"""),format.raw/*70.1474*/(""");epoint.ow.forms.ConfigManager.setJsValidationEnabled("login",true);epoint.ow.forms.ConfigManager.setAjaxValidationEnabled("login",false);epoint.ow.forms.ConfigManager.setOnChangeValidationEnabled("login",false);"""),format.raw/*70.1687*/("""}"""),format.raw/*70.1688*/(""",null,null);
			        </script>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*72.119*/("""{"""),format.raw/*72.120*/("""epoint.ow.forms.Utils.registerDefaultSubmitEventHandler("login");"""),format.raw/*72.185*/("""}"""),format.raw/*72.186*/(""",null,null);</script>
                                <!-- <form accept-charset="UTF-8" action="https://my.vertabelo.com/login?action=auth.login" enctype="application/x-www-form-urlencoded" id="form_login" method="POST" name="login"> -->
                                """),_display_(/*74.34*/helper/*74.40*/.form(action = routes.Application.authentication)/*74.89*/ {_display_(Seq[Any](format.raw/*74.91*/("""
                                    """),format.raw/*75.37*/("""<input class="default_submit" name="default_submit" style="position: absolute; top: -2000px; left: -2000px" type="submit" id="login_default_submit">
                                    <div class="box_form_section" id="form_login_section_other">
                                        <div class="box_form_content">
                                            <table cellspacing="0" class="form_login_table" i18n="pl.epoint.ow.i18n.ChainI18NBundle@2306e4">
                                                <tr class="row_1">
                                                    <td colspan="2"><span class="field_label" id="login_login_label">Email:<span class="mandatory">*</span></span>
                                                        <input class="text_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" type="text" id="login_login" name="username" prompttext="Email"><span class="field_error_container initialHide" id="login_login_error_container"></span>
                                                    </td>
                                                </tr>
                                                <tr class="row_2">
                                                    <td colspan="2"><span class="field_label" id="login_password_label">Password:<span class="mandatory">*</span></span><input class="password_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" type="password" id="login_password" name="password" prompttext="Password"><span class="field_error_container initialHide" id="login_password_error_container"></span>
                                                    </td>
                                                </tr>
                                                <tr class="row_1">
                                                    <td colspan="2"><input class="checkbox_field" onChange="epoint.ow.forms.Utils.fieldValueChanged(this)" type="checkbox" id="login_rememberMe" name="rememberMe" value="true"><label class="field_label" for="login_rememberMe" id="login_rememberMe_label">Remember me on this computer</label><span class="field_error_container initialHide" id="login_rememberMe_error_container"></span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                <div class="buttons">
                                    <input type="submit" class="button_primary" id="btn13" name="register" value="login" />
                                </div>
                                <!-- </form> -->
                                """)))}),format.raw/*99.34*/("""
                                """),format.raw/*100.33*/("""<!-- <div class="buttons">
                                    <a class="button_primary" id="btn13"><span>Log in</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*101.183*/("""{"""),format.raw/*101.184*/("""epoint.ow.Utils.bindEvent("click","btn13","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*101.272*/("""}"""),format.raw/*101.273*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*101.378*/("""{"""),format.raw/*101.379*/("""epoint.ow.Utils.bindEvent("click","btn13","epoint.ow.forms.Utils.submitFormButtonHandler","login",null,null);"""),format.raw/*101.488*/("""}"""),format.raw/*101.489*/(""",null,null);</script><a href="/password-reminder?action=flow.reset&amp;flow.flow=password_reminder" id="btn14"><span>Forgot your password?</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*101.724*/("""{"""),format.raw/*101.725*/("""epoint.ow.Utils.bindEvent("click","btn14","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*101.813*/("""}"""),format.raw/*101.814*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*101.919*/("""{"""),format.raw/*101.920*/("""epoint.ow.Utils.bindEvent("click","btn14","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*101.1011*/("""}"""),format.raw/*101.1012*/(""",null,null);</script>
                                </div> -->
                            </div></div></div></div></div><div class="box_pre_wrapper">
                        <div class="box_wrapper"><div class="box box__login"><div class="box_inner"><div class="content"><p class="special_text"><span>Don&#x27;t have an account yet? </span><a class="button_secondary" href="/signup" id="btn15"><span>Sign up</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.355*/("""{"""),format.raw/*104.356*/("""epoint.ow.Utils.bindEvent("click","btn15","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.444*/("""}"""),format.raw/*104.445*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.550*/("""{"""),format.raw/*104.551*/("""epoint.ow.Utils.bindEvent("click","btn15","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*104.642*/("""}"""),format.raw/*104.643*/(""",null,null);</script> or <a class="button_secondary" href="/try"><span>Try now</span></a></p></div></div></div></div></div></div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.858*/("""{"""),format.raw/*104.859*/("""epoint.ow.flow.Utils.setCurrentState("login","view");"""),format.raw/*104.912*/("""}"""),format.raw/*104.913*/(""",null,null);</script></div></div><div id="footer_wrapper"><div id="footer"><ul class="links"><li><a href="/support/request-a-feature" id="btn21" target="_blank"><span>Request a feature</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.1194*/("""{"""),format.raw/*104.1195*/("""epoint.ow.Utils.bindEvent("click","btn21","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.1283*/("""}"""),format.raw/*104.1284*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.1389*/("""{"""),format.raw/*104.1390*/("""epoint.ow.Utils.bindEvent("click","btn21","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*104.1481*/("""}"""),format.raw/*104.1482*/(""",null,null);</script></li><li><a href="/support/report-a-problem" id="btn22" target="_blank"><span>Report a problem</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.1694*/("""{"""),format.raw/*104.1695*/("""epoint.ow.Utils.bindEvent("click","btn22","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.1783*/("""}"""),format.raw/*104.1784*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.1889*/("""{"""),format.raw/*104.1890*/("""epoint.ow.Utils.bindEvent("click","btn22","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*104.1981*/("""}"""),format.raw/*104.1982*/(""",null,null);</script></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn23"><span>Recommend us</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.2207*/("""{"""),format.raw/*104.2208*/("""epoint.ow.Utils.bindEvent("click","btn23","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.2296*/("""}"""),format.raw/*104.2297*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.2402*/("""{"""),format.raw/*104.2403*/("""epoint.ow.Utils.bindEvent("click","btn23","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*104.2494*/("""}"""),format.raw/*104.2495*/(""",null,null);</script></li><li><a href="/support/ask-a-question" id="btn24" target="_blank"><span>Ask a question</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.2703*/("""{"""),format.raw/*104.2704*/("""epoint.ow.Utils.bindEvent("click","btn24","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*104.2792*/("""}"""),format.raw/*104.2793*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.2898*/("""{"""),format.raw/*104.2899*/("""epoint.ow.Utils.bindEvent("click","btn24","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*104.2990*/("""}"""),format.raw/*104.2991*/(""",null,null);</script></li><li><a target="_blank" href="http://vertabelo.com/about-us">About us</a></li><li><a href="http://vertabelo.com/terms-of-service">Terms of service</a></li><li><a href="http://vertabelo.com/privacy-policy">Privacy policy</a></li><li><a target="_blank" href="http://vertabelo.com/documentation">Documentation</a></li></ul><div class="social">Follow us on: <a class="twitter" title="Twitter" href="http://twitter.com/Vertabelo"><span>Twitter</span></a> <a class="facebook" title="Facebook" href="https://www.facebook.com/Vertabelo"><span>Facebook</span></a> <a class="youtube" title="Youtube" href="http://www.youtube.com/user/vertabelo"><span>Youtube</span></a></div><div class="clearFix"></div><div class="hr"><hr></div><div class="powered_by"></div><div class="copyright">StressBook v. 1.0.0 All rights reserved</div><div class="clearFix"></div></div></div></div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*104.3965*/("""{"""),format.raw/*104.3966*/("""oneweb.CsrfTokenManager.setToken("ZJc8F23LO0kjVJMPXyfxdfJMUxPQ28Vi");"""),format.raw/*104.4035*/("""}"""),format.raw/*104.4036*/(""",10,null);</script></body></html>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Mar 30 23:41:17 CDT 2016
                  SOURCE: C:/first_play/app/views/login.scala.html
                  HASH: cefbdcb814f323219900d9fd8fac2852bfe3445b
                  MATRIX: 580->6|5131->4528|5161->4529|5297->4636|5327->4637|5618->4899|5648->4900|5729->4952|5759->4953|5812->4977|5842->4978|5883->4990|5913->4991|6252->5301|6282->5302|6349->5340|6379->5341|6449->5382|6479->5383|6519->5394|6549->5395|6879->5696|6909->5697|6973->5732|7003->5733|7073->5774|7103->5775|7133->5776|7163->5777|7271->5855|7302->5856|7557->6081|7588->6082|7734->6198|7765->6199|7847->6251|7878->6252|7909->6253|7940->6254|8183->6467|8214->6468|8397->6622|8427->6623|8521->6688|8551->6689|8851->6962|8866->6968|8924->7017|8964->7019|9030->7057|11830->9827|11893->9861|12133->10071|12164->10072|12282->10160|12313->10161|12448->10266|12479->10267|12618->10376|12649->10377|12914->10612|12945->10613|13063->10701|13094->10702|13229->10807|13260->10808|13382->10899|13414->10900|13954->11410|13985->11411|14103->11499|14134->11500|14269->11605|14300->11606|14421->11697|14452->11698|14697->11913|14728->11914|14811->11967|14842->11968|15154->12249|15186->12250|15305->12338|15337->12339|15473->12444|15505->12445|15627->12536|15659->12537|15902->12749|15934->12750|16053->12838|16085->12839|16221->12944|16253->12945|16375->13036|16407->13037|16663->13262|16695->13263|16814->13351|16846->13352|16982->13457|17014->13458|17136->13549|17168->13550|17407->13758|17439->13759|17558->13847|17590->13848|17726->13953|17758->13954|17880->14045|17912->14046|18917->15020|18949->15021|19049->15090|19081->15091
                  LINES: 22->4|85->67|85->67|85->67|85->67|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|88->70|90->72|90->72|90->72|90->72|92->74|92->74|92->74|92->74|93->75|117->99|118->100|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|119->101|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104|122->104
                  -- GENERATED --
              */
          