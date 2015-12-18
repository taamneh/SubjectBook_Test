
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
object reportResult extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,List[scala.Tuple2[String, Int]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, studies: List[(String,Int)]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.49*/("""

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
    <script type="text/javascript" src="/assets/js/my_models.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/datePicker.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/oneweb.css">
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/dbwm.css">

</head>
<body>

<div id="wrapper">
<div id="header_wrapper">
    <div id="header">
        <a class="logo" href="http://www.cpl.uh.edu/"><img alt="CPL Logo" width="70" height="40"  src="/assets/images/cpllogo.png"></a>
        <ul class="user_menu">
            <li class="user dropdown_holder"><span class="dropdown"><span>"""),_display_(/*31.76*/userName),format.raw/*31.84*/("""</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="" id="btn31"><span>My account</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.312*/("""{"""),format.raw/*31.313*/("""epoint.ow.Utils.bindEvent("click","btn31","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*31.401*/("""}"""),format.raw/*31.402*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.507*/("""{"""),format.raw/*31.508*/("""epoint.ow.Utils.bindEvent("click","btn31","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*31.599*/("""}"""),format.raw/*31.600*/(""",null,null);</script></li></ul></div></div></li><li class="notifications"><div id="flow_notifications_layer_div"><div class="menu_item dropdown_holder"><a class="dropdown notifications" href="" id="btn32"><span></span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.908*/("""{"""),format.raw/*31.909*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*31.997*/("""}"""),format.raw/*31.998*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.1103*/("""{"""),format.raw/*31.1104*/("""epoint.ow.Utils.bindEvent("click","btn32","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*31.1191*/("""}"""),format.raw/*31.1192*/(""",null,null);</script></div> <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.1311*/("""{"""),format.raw/*31.1312*/("""pl.epoint.dbwm.war_user.notifications.startNotificationsLayerRefresh("?action=flow.transit&flow.flow=notifications_layer&flow.transition=refresh&flow.reloadFlow=notifications_layer&flow.disableMessagesAddons=true");"""),format.raw/*31.1527*/("""}"""),format.raw/*31.1528*/(""",null,null);</script> --> </div><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.1646*/("""{"""),format.raw/*31.1647*/("""epoint.ow.flow.Utils.setCurrentState("notifications_layer","collapsed");"""),format.raw/*31.1719*/("""}"""),format.raw/*31.1720*/(""",null,null);</script></li><li class="logout"><a href="/logout" id="btn33"><span>Log out</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.1904*/("""{"""),format.raw/*31.1905*/("""epoint.ow.Utils.bindEvent("click","btn33","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*31.1993*/("""}"""),format.raw/*31.1994*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*31.2099*/("""{"""),format.raw/*31.2100*/("""epoint.ow.Utils.bindEvent("click","btn33","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*31.2191*/("""}"""),format.raw/*31.2192*/(""",null,null);</script></li>
        </ul>
        <ul class="links">
            <li class="dropdown_holder expandable expand-priority-1"><span class="dropdown"><span>Modules</span></span><div class="dropdown_menu_holder"><div class="dropdown_menu"><ul class="m"><li><a href="/diagram">Diagram</a></li><li><a href="/allStudies" id="btn34"><span>My models</span></a></li><li><a href="/sharing?action=flow.reset&amp;flow.flow=model_sharing" id="btn35"><span>Sharing</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*34.492*/("""{"""),format.raw/*34.493*/("""epoint.ow.Utils.bindEvent("click","btn35","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*34.581*/("""}"""),format.raw/*34.582*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*34.687*/("""{"""),format.raw/*34.688*/("""epoint.ow.Utils.bindEvent("click","btn35","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*34.779*/("""}"""),format.raw/*34.780*/(""",null,null);</script></li><li><a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn36"><span>Recommend us</span></a><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*34.1005*/("""{"""),format.raw/*34.1006*/("""epoint.ow.Utils.bindEvent("click","btn36","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*34.1094*/("""}"""),format.raw/*34.1095*/(""",90,null);</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*34.1200*/("""{"""),format.raw/*34.1201*/("""epoint.ow.Utils.bindEvent("click","btn36","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*34.1292*/("""}"""),format.raw/*34.1293*/(""",null,null);</script></li></ul></div></div></li><li class="dropdown_holder" id="help-menu"><span class="dropdown"><span>Help</span></span>
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
									"""),format.raw/*56.10*/("""{"""),format.raw/*56.11*/("""epoint.ow.Utils.bindEvent("click","btn37","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*56.99*/("""}"""),format.raw/*56.100*/(""",90,null);
								</script>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
									"""),format.raw/*59.10*/("""{"""),format.raw/*59.11*/("""epoint.ow.Utils.bindEvent("click","btn37","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*59.102*/("""}"""),format.raw/*59.103*/(""",null,null);
								</script>
                        </li>
                        <li>
                            <a href="/support/request-a-feature" id="btn38" target="_blank"><span>Request a feature</span></a>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								   """),format.raw/*65.12*/("""{"""),format.raw/*65.13*/("""epoint.ow.Utils.bindEvent("click","btn38","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*65.101*/("""}"""),format.raw/*65.102*/(""",90,null);
								</script>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								   """),format.raw/*68.12*/("""{"""),format.raw/*68.13*/("""epoint.ow.Utils.bindEvent("click","btn38","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*68.104*/("""}"""),format.raw/*68.105*/(""",null,null);
								</script>
                        </li>
                        <li>
                            <a href="/support/report-a-problem" id="btn39" target="_blank"><span>Report a problem</span></a>
                            <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								"""),format.raw/*74.9*/("""{"""),format.raw/*74.10*/("""epoint.ow.Utils.bindEvent("click","btn39","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*74.98*/("""}"""),format.raw/*74.99*/(""",90,null);
								</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
								"""),format.raw/*76.9*/("""{"""),format.raw/*76.10*/("""epoint.ow.Utils.bindEvent("click","btn39","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*76.101*/("""}"""),format.raw/*76.102*/(""",null,null);
								</script>
                        </li>
                    </ul>
                    <div class="clearFix">
                    </div>
                </div>
                </div>
          </div>
        </li>
        </ul>

        <div class="clearFix">
        </div>
</div>
<div id="content_wrapper">
    <div id="content">
        <!-- <div id="messages-placeholder"> -->
        <!--<div id="not-supported-browser-info-header" style="display: none;">Editing database model is not supported in your web browser. Use Google Chrome.-->
        <!--<a href="http://www.vertabelo.com/faq#chrome" target="_blank">Learn more</a> -->
        <!--</div> -->
        <!--<div id="oneweb-messages-placeholder"> -->
        <!--</div> -->
        <!-- </div> -->
        <div id="flow_models_div">
            <div class="box_pre_wrapper">
                <div class="box_wrapper">
                    <div class="box">
                        <div class="box_inner">
                            <div class="header"><h1>My Studies</h1></div>
                            <div class="content">
                                <div class="box_table" id="table_models">
                                    <table cellpadding="0" cellspacing="0" class="table_table">
                                        <thead class="table_thead">
                                        <tr class="table_header">
                                            <th>
                                                <a href="?action=table.flipOrder&amp;table=models" id="btn24">Model name</a>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*114.17*/("""{"""),format.raw/*114.18*/("""epoint.ow.Utils.bindEvent("click","btn24","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*114.106*/("""}"""),format.raw/*114.107*/(""",90,null);
															</script><script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*116.17*/("""{"""),format.raw/*116.18*/("""epoint.ow.Utils.bindEvent("click","btn24","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*116.109*/("""}"""),format.raw/*116.110*/(""",null,null);
															</script><img alt="Sorted in ascending order" src="/assets/images/table/sort_asc.gif">
                                            </th>
                                            <th>
                                                <a href="?action=table.setOrderColumn&amp;table=models&amp;column=dbName" id="btn25">Database</a>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*122.17*/("""{"""),format.raw/*122.18*/("""epoint.ow.Utils.bindEvent("click","btn25","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*122.106*/("""}"""),format.raw/*122.107*/(""",90,null);
															</script>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*125.17*/("""{"""),format.raw/*125.18*/("""epoint.ow.Utils.bindEvent("click","btn25","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*125.109*/("""}"""),format.raw/*125.110*/(""",null,null);
															</script>
                                            </th>
                                            <th>
                                                <a href="?action=table.setOrderColumn&amp;table=models&amp;column=userRole" id="btn26">Your role</a>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*131.17*/("""{"""),format.raw/*131.18*/("""epoint.ow.Utils.bindEvent("click","btn26","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*131.106*/("""}"""),format.raw/*131.107*/(""",90,null);
															</script>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*134.17*/("""{"""),format.raw/*134.18*/("""epoint.ow.Utils.bindEvent("click","btn26","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*134.109*/("""}"""),format.raw/*134.110*/(""",null,null);
															</script>
                                            </th>
                                            <th>
                                                <a href="?action=table.setOrderColumn&amp;table=models&amp;column=lastModificationDate" id="btn27">Updated</a>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*140.17*/("""{"""),format.raw/*140.18*/("""epoint.ow.Utils.bindEvent("click","btn27","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*140.106*/("""}"""),format.raw/*140.107*/(""",90,null);
															</script>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
																"""),format.raw/*143.17*/("""{"""),format.raw/*143.18*/("""epoint.ow.Utils.bindEvent("click","btn27","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*143.109*/("""}"""),format.raw/*143.110*/(""",null,null);
															</script>
                                            </th>
                                            <th>
                                                <a href="?action=table.setOrderColumn&amp;table=models&amp;column=createdBy" id="btn28">By</a>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
															"""),format.raw/*149.16*/("""{"""),format.raw/*149.17*/("""epoint.ow.Utils.bindEvent("click","btn28","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*149.105*/("""}"""),format.raw/*149.106*/(""",90,null);
														</script>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
															"""),format.raw/*152.16*/("""{"""),format.raw/*152.17*/("""epoint.ow.Utils.bindEvent("click","btn28","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*152.108*/("""}"""),format.raw/*152.109*/(""",null,null);
														</script>
                                            </th>
                                            <th>
                                            </th>
                                            <th>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody class="table_body">
                                        """),_display_(/*162.42*/for(study <- studies) yield /*162.63*/ {_display_(Seq[Any](format.raw/*162.65*/("""
                                        """),format.raw/*163.41*/("""<tr class="table_row_odd table_row_first">
                                            <td>
                                                <a href="/showStudy/"""),_display_(/*165.70*/study/*165.75*/._2),format.raw/*165.78*/(""""><span>"""),_display_(/*165.87*/study/*165.92*/._1),format.raw/*165.95*/("""</span></a>
                                            </td>
                                            <td>CPL Lab</td>
                                            <td><span class="role_owner">Owner</span></td>
                                            <td>2014-09-17 15:56</td>
                                            <td>Salah Taamneh</td>
                                            <td><a class="button"  href="/showStudy/"""),_display_(/*171.86*/study/*171.91*/._2),format.raw/*171.94*/(""""><span>Preview</span></a></td>
                                            <td>
                                                <a class="button" href="/editStudy/"""),_display_(/*173.85*/study/*173.90*/._2),format.raw/*173.93*/("""" id="btn29"""),_display_(/*173.105*/study/*173.110*/._2),format.raw/*173.113*/(""""><span>Edit</span></a>
                                                <!-- <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*174.140*/("""{"""),format.raw/*174.141*/("""epoint.ow.Utils.bindEvent("click","btn29","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*174.229*/("""}"""),format.raw/*174.230*/(""",90,null);
															</script>
                                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*176.135*/("""{"""),format.raw/*176.136*/("""epoint.ow.Utils.bindEvent("click","btn29","epoint.ow.flow.Utils.ajaxFlowHandler",null);"""),format.raw/*176.223*/("""}"""),format.raw/*176.224*/(""",null,null);
															</script> -->
                                            </td>
                                        </tr>
                                        """)))}),format.raw/*180.42*/("""
                                        """),format.raw/*181.41*/("""</tbody>
                                    </table>
                                </div>
                                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*184.119*/("""{"""),format.raw/*184.120*/("""epoint.ow.table.Table.registerHandlers("table_models");"""),format.raw/*184.175*/("""}"""),format.raw/*184.176*/(""",null,null);
									</script>
                                 <div class="buttons"><a  href="/createStudy" class="button_primary" id="addStudy"><span>Create Study</span></a>

                                    <script>
                                        $(document).ready(function()"""),format.raw/*189.69*/("""{"""),format.raw/*189.70*/("""
                                          """),format.raw/*190.43*/("""$("#addStudy").click(function()"""),format.raw/*190.74*/("""{"""),format.raw/*190.75*/("""
                                            """),format.raw/*191.45*/("""$("#newStudy").slideDown("slow");

                                            """),format.raw/*193.45*/("""}"""),format.raw/*193.46*/(""");
                                            """),format.raw/*194.45*/("""}"""),format.raw/*194.46*/(""");
                                     </script>

                                </div>

                            </div>
                    </div>
                </div>
             </div>
         </div>
    </div>
        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
				"""),format.raw/*206.5*/("""{"""),format.raw/*206.6*/("""epoint.ow.flow.Utils.setCurrentState("models","list");"""),format.raw/*206.60*/("""}"""),format.raw/*206.61*/(""",null,null);
			</script>
</div>
</div>

<div id="footer_wrapper">
    <div id="footer">
        <ul class="links">
            <li>
                <a href="/support/request-a-feature" id="btn40" target="_blank"><span>Request a feature</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*217.8*/("""{"""),format.raw/*217.9*/("""epoint.ow.Utils.bindEvent("click","btn40","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*217.97*/("""}"""),format.raw/*217.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*220.8*/("""{"""),format.raw/*220.9*/("""epoint.ow.Utils.bindEvent("click","btn40","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*220.100*/("""}"""),format.raw/*220.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/report-a-problem" id="btn41" target="_blank"><span>Report a problem</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*226.8*/("""{"""),format.raw/*226.9*/("""epoint.ow.Utils.bindEvent("click","btn41","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*226.97*/("""}"""),format.raw/*226.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*229.8*/("""{"""),format.raw/*229.9*/("""epoint.ow.Utils.bindEvent("click","btn41","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*229.100*/("""}"""),format.raw/*229.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn42"><span>Recommend us</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*235.8*/("""{"""),format.raw/*235.9*/("""epoint.ow.Utils.bindEvent("click","btn42","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*235.97*/("""}"""),format.raw/*235.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*238.8*/("""{"""),format.raw/*238.9*/("""epoint.ow.Utils.bindEvent("click","btn42","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*238.100*/("""}"""),format.raw/*238.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/ask-a-question" id="btn43" target="_blank"><span>Ask a question</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*244.8*/("""{"""),format.raw/*244.9*/("""epoint.ow.Utils.bindEvent("click","btn43","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*244.97*/("""}"""),format.raw/*244.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*247.8*/("""{"""),format.raw/*247.9*/("""epoint.ow.Utils.bindEvent("click","btn43","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*247.100*/("""}"""),format.raw/*247.101*/(""",null,null);
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
<script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*273.87*/("""{"""),format.raw/*273.88*/("""oneweb.CsrfTokenManager.setToken("dE9ktmra82LBfstlCVdVyEosWMEiGhvB");"""),format.raw/*273.157*/("""}"""),format.raw/*273.158*/(""",10,null);
</script>
</body>
</html>"""))}
  }

  def render(userName:String,studies:List[scala.Tuple2[String, Int]]): play.twirl.api.HtmlFormat.Appendable = apply(userName,studies)

  def f:((String,List[scala.Tuple2[String, Int]]) => play.twirl.api.HtmlFormat.Appendable) = (userName,studies) => apply(userName,studies)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:25:59 CDT 2015
                  SOURCE: C:/first_play/app/views/reportResult.scala.html
                  HASH: 5e0d0a63947839acb7e4f249e31c7a5fcc568eff
                  MATRIX: 544->1|679->48|707->50|2426->1742|2455->1750|2712->1978|2742->1979|2859->2067|2889->2068|3023->2173|3053->2174|3173->2265|3203->2266|3540->2574|3570->2575|3687->2663|3717->2664|3852->2769|3883->2770|4000->2857|4031->2858|4180->2977|4211->2978|4456->3193|4487->3194|4635->3312|4666->3313|4768->3385|4799->3386|5013->3570|5044->3571|5162->3659|5193->3660|5328->3765|5359->3766|5480->3857|5511->3858|6099->4417|6129->4418|6246->4506|6276->4507|6410->4612|6440->4613|6560->4704|6590->4705|6845->4930|6876->4931|6994->5019|7025->5020|7160->5125|7191->5126|7312->5217|7343->5218|8805->6652|8834->6653|8950->6741|8980->6742|9160->6894|9189->6895|9309->6986|9339->6987|9709->7329|9738->7330|9855->7418|9885->7419|10067->7573|10096->7574|10216->7665|10246->7666|10610->8003|10639->8004|10755->8092|10784->8093|10933->8215|10962->8216|11082->8307|11112->8308|12879->10046|12909->10047|13027->10135|13058->10136|13224->10273|13254->10274|13375->10365|13406->10366|13945->10876|13975->10877|14093->10965|14124->10966|14339->11152|14369->11153|14490->11244|14521->11245|14986->11681|15016->11682|15134->11770|15165->11771|15380->11957|15410->11958|15531->12049|15562->12050|16037->12496|16067->12497|16185->12585|16216->12586|16431->12772|16461->12773|16582->12864|16613->12865|17071->13294|17101->13295|17219->13383|17250->13384|17463->13568|17493->13569|17614->13660|17645->13661|18161->14149|18199->14170|18240->14172|18310->14213|18499->14374|18514->14379|18539->14382|18576->14391|18591->14396|18616->14399|19080->14835|19095->14840|19120->14843|19313->15008|19328->15013|19353->15016|19394->15028|19410->15033|19436->15036|19629->15199|19660->15200|19778->15288|19809->15289|20009->15459|20040->15460|20157->15547|20188->15548|20399->15727|20469->15768|20710->15979|20741->15980|20826->16035|20857->16036|21177->16327|21207->16328|21279->16371|21339->16402|21369->16403|21443->16448|21551->16527|21581->16528|21657->16575|21687->16576|22036->16897|22065->16898|22148->16952|22178->16953|22563->17310|22592->17311|22709->17399|22739->17400|22903->17536|22932->17537|23053->17628|23084->17629|23398->17915|23427->17916|23544->18004|23574->18005|23738->18141|23767->18142|23888->18233|23919->18234|24246->18533|24275->18534|24392->18622|24422->18623|24586->18759|24615->18760|24736->18851|24767->18852|25077->19134|25106->19135|25223->19223|25253->19224|25417->19360|25446->19361|25567->19452|25598->19453|26960->20786|26990->20787|27089->20856|27120->20857
                  LINES: 19->1|22->1|24->3|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|77->56|77->56|77->56|77->56|80->59|80->59|80->59|80->59|86->65|86->65|86->65|86->65|89->68|89->68|89->68|89->68|95->74|95->74|95->74|95->74|97->76|97->76|97->76|97->76|135->114|135->114|135->114|135->114|137->116|137->116|137->116|137->116|143->122|143->122|143->122|143->122|146->125|146->125|146->125|146->125|152->131|152->131|152->131|152->131|155->134|155->134|155->134|155->134|161->140|161->140|161->140|161->140|164->143|164->143|164->143|164->143|170->149|170->149|170->149|170->149|173->152|173->152|173->152|173->152|183->162|183->162|183->162|184->163|186->165|186->165|186->165|186->165|186->165|186->165|192->171|192->171|192->171|194->173|194->173|194->173|194->173|194->173|194->173|195->174|195->174|195->174|195->174|197->176|197->176|197->176|197->176|201->180|202->181|205->184|205->184|205->184|205->184|210->189|210->189|211->190|211->190|211->190|212->191|214->193|214->193|215->194|215->194|227->206|227->206|227->206|227->206|238->217|238->217|238->217|238->217|241->220|241->220|241->220|241->220|247->226|247->226|247->226|247->226|250->229|250->229|250->229|250->229|256->235|256->235|256->235|256->235|259->238|259->238|259->238|259->238|265->244|265->244|265->244|265->244|268->247|268->247|268->247|268->247|294->273|294->273|294->273|294->273
                  -- GENERATED --
              */
          