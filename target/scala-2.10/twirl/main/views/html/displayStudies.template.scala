
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
object displayStudies extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[scala.Tuple2[String, Int]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, studies: List[(String,Int)], report: String = ""):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.70*/("""

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

                                <div>
                                    """),_display_(/*200.38*/report),format.raw/*200.44*/("""
                                """),format.raw/*201.33*/("""</div>

                            </div>
                    </div>
                </div>
             </div>
         </div>
    </div>
        <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
				"""),format.raw/*210.5*/("""{"""),format.raw/*210.6*/("""epoint.ow.flow.Utils.setCurrentState("models","list");"""),format.raw/*210.60*/("""}"""),format.raw/*210.61*/(""",null,null);
			</script>
</div>
</div>

<div id="footer_wrapper">
    <div id="footer">
        <ul class="links">
            <li>
                <a href="/support/request-a-feature" id="btn40" target="_blank"><span>Request a feature</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*221.8*/("""{"""),format.raw/*221.9*/("""epoint.ow.Utils.bindEvent("click","btn40","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*221.97*/("""}"""),format.raw/*221.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*224.8*/("""{"""),format.raw/*224.9*/("""epoint.ow.Utils.bindEvent("click","btn40","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*224.100*/("""}"""),format.raw/*224.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/report-a-problem" id="btn41" target="_blank"><span>Report a problem</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*230.8*/("""{"""),format.raw/*230.9*/("""epoint.ow.Utils.bindEvent("click","btn41","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*230.97*/("""}"""),format.raw/*230.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*233.8*/("""{"""),format.raw/*233.9*/("""epoint.ow.Utils.bindEvent("click","btn41","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*233.100*/("""}"""),format.raw/*233.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/recommend-us?action=flow.reset&amp;flow.flow=recommend_us" id="btn42"><span>Recommend us</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*239.8*/("""{"""),format.raw/*239.9*/("""epoint.ow.Utils.bindEvent("click","btn42","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*239.97*/("""}"""),format.raw/*239.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*242.8*/("""{"""),format.raw/*242.9*/("""epoint.ow.Utils.bindEvent("click","btn42","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*242.100*/("""}"""),format.raw/*242.101*/(""",null,null);
						</script>
            </li>
            <li>
                <a href="/support/ask-a-question" id="btn43" target="_blank"><span>Ask a question</span></a>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*248.8*/("""{"""),format.raw/*248.9*/("""epoint.ow.Utils.bindEvent("click","btn43","epoint.ow.Utils.buttonDefaultsHandler",null);"""),format.raw/*248.97*/("""}"""),format.raw/*248.98*/(""",90,null);
						</script>
                <script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function()
							"""),format.raw/*251.8*/("""{"""),format.raw/*251.9*/("""epoint.ow.Utils.bindEvent("click","btn43","oneweb.CsrfTokenManager.postHandler",null,null);"""),format.raw/*251.100*/("""}"""),format.raw/*251.101*/(""",null,null);
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
<script type="text/javascript">oneweb.DocumentReadyManager.addOnewebAction(function() """),format.raw/*277.87*/("""{"""),format.raw/*277.88*/("""oneweb.CsrfTokenManager.setToken("dE9ktmra82LBfstlCVdVyEosWMEiGhvB");"""),format.raw/*277.157*/("""}"""),format.raw/*277.158*/(""",10,null);
</script>
</body>
</html>"""))}
  }

  def render(userName:String,studies:List[scala.Tuple2[String, Int]],report:String): play.twirl.api.HtmlFormat.Appendable = apply(userName,studies,report)

  def f:((String,List[scala.Tuple2[String, Int]],String) => play.twirl.api.HtmlFormat.Appendable) = (userName,studies,report) => apply(userName,studies,report)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:25:56 CDT 2015
                  SOURCE: C:/first_play/app/views/displayStudies.scala.html
                  HASH: 3909f316d80466fc37b71701ad42633b2d443186
                  MATRIX: 553->1|709->69|737->71|2456->1763|2485->1771|2742->1999|2772->2000|2889->2088|2919->2089|3053->2194|3083->2195|3203->2286|3233->2287|3570->2595|3600->2596|3717->2684|3747->2685|3882->2790|3913->2791|4030->2878|4061->2879|4210->2998|4241->2999|4486->3214|4517->3215|4665->3333|4696->3334|4798->3406|4829->3407|5043->3591|5074->3592|5192->3680|5223->3681|5358->3786|5389->3787|5510->3878|5541->3879|6129->4438|6159->4439|6276->4527|6306->4528|6440->4633|6470->4634|6590->4725|6620->4726|6875->4951|6906->4952|7024->5040|7055->5041|7190->5146|7221->5147|7342->5238|7373->5239|8835->6673|8864->6674|8980->6762|9010->6763|9190->6915|9219->6916|9339->7007|9369->7008|9739->7350|9768->7351|9885->7439|9915->7440|10097->7594|10126->7595|10246->7686|10276->7687|10640->8024|10669->8025|10785->8113|10814->8114|10963->8236|10992->8237|11112->8328|11142->8329|12909->10067|12939->10068|13057->10156|13088->10157|13254->10294|13284->10295|13405->10386|13436->10387|13975->10897|14005->10898|14123->10986|14154->10987|14369->11173|14399->11174|14520->11265|14551->11266|15016->11702|15046->11703|15164->11791|15195->11792|15410->11978|15440->11979|15561->12070|15592->12071|16067->12517|16097->12518|16215->12606|16246->12607|16461->12793|16491->12794|16612->12885|16643->12886|17101->13315|17131->13316|17249->13404|17280->13405|17493->13589|17523->13590|17644->13681|17675->13682|18191->14170|18229->14191|18270->14193|18340->14234|18529->14395|18544->14400|18569->14403|18606->14412|18621->14417|18646->14420|19110->14856|19125->14861|19150->14864|19343->15029|19358->15034|19383->15037|19424->15049|19440->15054|19466->15057|19659->15220|19690->15221|19808->15309|19839->15310|20039->15480|20070->15481|20187->15568|20218->15569|20429->15748|20499->15789|20740->16000|20771->16001|20856->16056|20887->16057|21207->16348|21237->16349|21309->16392|21369->16423|21399->16424|21473->16469|21581->16548|21611->16549|21687->16596|21717->16597|21911->16763|21939->16769|22001->16802|22267->17040|22296->17041|22379->17095|22409->17096|22794->17453|22823->17454|22940->17542|22970->17543|23134->17679|23163->17680|23284->17771|23315->17772|23629->18058|23658->18059|23775->18147|23805->18148|23969->18284|23998->18285|24119->18376|24150->18377|24477->18676|24506->18677|24623->18765|24653->18766|24817->18902|24846->18903|24967->18994|24998->18995|25308->19277|25337->19278|25454->19366|25484->19367|25648->19503|25677->19504|25798->19595|25829->19596|27191->20929|27221->20930|27320->20999|27351->21000
                  LINES: 19->1|22->1|24->3|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|52->31|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|77->56|77->56|77->56|77->56|80->59|80->59|80->59|80->59|86->65|86->65|86->65|86->65|89->68|89->68|89->68|89->68|95->74|95->74|95->74|95->74|97->76|97->76|97->76|97->76|135->114|135->114|135->114|135->114|137->116|137->116|137->116|137->116|143->122|143->122|143->122|143->122|146->125|146->125|146->125|146->125|152->131|152->131|152->131|152->131|155->134|155->134|155->134|155->134|161->140|161->140|161->140|161->140|164->143|164->143|164->143|164->143|170->149|170->149|170->149|170->149|173->152|173->152|173->152|173->152|183->162|183->162|183->162|184->163|186->165|186->165|186->165|186->165|186->165|186->165|192->171|192->171|192->171|194->173|194->173|194->173|194->173|194->173|194->173|195->174|195->174|195->174|195->174|197->176|197->176|197->176|197->176|201->180|202->181|205->184|205->184|205->184|205->184|210->189|210->189|211->190|211->190|211->190|212->191|214->193|214->193|215->194|215->194|221->200|221->200|222->201|231->210|231->210|231->210|231->210|242->221|242->221|242->221|242->221|245->224|245->224|245->224|245->224|251->230|251->230|251->230|251->230|254->233|254->233|254->233|254->233|260->239|260->239|260->239|260->239|263->242|263->242|263->242|263->242|269->248|269->248|269->248|269->248|272->251|272->251|272->251|272->251|298->277|298->277|298->277|298->277
                  -- GENERATED --
              */
          