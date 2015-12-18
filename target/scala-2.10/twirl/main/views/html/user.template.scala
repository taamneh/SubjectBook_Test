
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
object user extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*4.1*/("""<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>Computational Physiology Lab :: Login</title>
    <meta name="description" content="" />
    <meta name="generator" content="concrete5 - 5.6.2.1" />
    <script type="text/javascript">
    var CCM_DISPATCHER_FILENAME = '/index.php';
    var CCM_CID = 5;
    var CCM_EDIT_MODE = false;
    var CCM_ARRANGE_MODE = false;
    var CCM_IMAGE_PATH = "http://www.cpl.uh.edu/concrete/images";
    var CCM_TOOLS_PATH = "/index.php/tools/required";
    var CCM_BASE_URL = "http://www.cpl.uh.edu";
    var CCM_REL = "";
    </script>
    <link rel="shortcut icon" href="/files/7112/3949/7292/cpl.ico" type="image/x-icon" />
    <link rel="icon" href="/assets/images/cpl.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="http://www.cpl.uh.edu/concrete/css/ccm.base.css?v=cd6d3742f62dbcab6fa890a719708e1d" />
    <script type="text/javascript" src="http://www.cpl.uh.edu/concrete/js/jquery.js?v=cd6d3742f62dbcab6fa890a719708e1d"></script>
    <script type="text/javascript" src="http://www.cpl.uh.edu/concrete/js/ccm.base.js?v=cd6d3742f62dbcab6fa890a719708e1d"></script>
    <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-8996380-1']);
  _gaq.push(['_trackPageview']);

  (function() """),format.raw/*32.15*/("""{"""),format.raw/*32.16*/("""
    """),format.raw/*33.5*/("""var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  """),format.raw/*36.3*/("""}"""),format.raw/*36.4*/(""")();

</script>

     <!-- <style type="text/css">@import "http://www.cpl.uh.edu/concrete/css/ccm.default.theme.css";</style> -->
     <style type="text/css">@import "/assets/stylesheets/ccm.default.theme.css";</style>
    <!-- <style type="text/css">@import "http://www.cpl.uh.edu/concrete/css/ccm.install.css";</style> -->
     <style type="text/css">@import "/assets/stylesheets/ccm.install.css";</style>
    <!--<style type="text/css">@import "http://www.cpl.uh.edu/concrete/css/ccm.app.css";</style> -->
     <style type="text/css">@import "/assets/stylesheets/ccm.app.css";</style>
 </head>
 </head>
 <body>

 <div class="ccm-ui">

     <div id="ccm-logo"><img id="ccm-logo" src="/assets/images/cpllogo.png" width="80" height="49" alt="concrete5" title="concrete5" /></div>




     <div class="container">
         <div class="row">
             <div class="span10 offset1">
             </div>
         </div>

         <script type="text/javascript">
 $(function() """),format.raw/*64.15*/("""{"""),format.raw/*64.16*/("""
     """),format.raw/*65.6*/("""$("input[name=uName]").focus();
 """),format.raw/*66.2*/("""}"""),format.raw/*66.3*/(""");
 </script>


         <div class="row">
             <div class="span10 offset1">
                 <div class="page-header">
                     <h1>Sign in to StressBook</h1>
                 </div>
             </div>
         </div>




         <!-- <form method="post" action= 'routes.Application.authentication' class="form-horizontal"> -->
        """),_display_(/*82.10*/helper/*82.16*/.form(action = routes.Application.authentication)/*82.65*/ {_display_(Seq[Any](format.raw/*82.67*/("""
            """),format.raw/*83.13*/("""<div class="row">
                <div class="span10 offset1">
                    <div class="row">
                        <div class="span5">

                            <fieldset>

                                <legend>User Account</legend>

                                <div class="control-group">

                                    <label for="username" class="control-label">		Username	</label>
                                    <div class="controls">
                                        <input type="text" name="username" id="username"  class="ccm-input-text">
                                    </div>

                                </div>
                                <div class="control-group">

                                    <label for="password" class="control-label">Password</label>

                                    <div class="controls">
                                        <input type="password" name="password" id="password" class="ccm-input-text" />
                                    </div>

                                </div>
                            </fieldset>


                        </div>
                        <div class="span4 offset1">

                            <fieldset>

                                <legend>Options</legend>


                                <div class="control-group">
                                    <label class="checkbox"><input type="checkbox"  class="ccm-input-checkbox" name="uMaintainLogin" id="uMaintainLogin" value="1"  /> <span>Remain logged in to website.</span></label>
                                </div>
                                <input type="hidden" name="rcID" value="" />

                            </fieldset>
                        </div>
                        <div class="span10">
                            <div class="actions">
                                <input type="submit" class="primary btn ccm-input-submit" id="submit" name="submit" value="Sign In &gt;" />	</div>
                        </div>
                    </div>
                </div>
            </div>
        """)))}),format.raw/*134.10*/("""

        """),format.raw/*136.9*/("""<a name="forgot_password"></a>

        <form method="post" action="/index.php/login/forgot_password/" class="form-horizontal">
            <div class="row">
                <div class="span10 offset1">

                    <h3>Forgot Your Password?</h3>

                    <p>Enter your email address below. We will send you instructions to reset your password.</p>

                    <input type="hidden" name="rcID" value="" />

                    <div class="control-group">
                        <label for="uEmail" class="control-label">Email Address</label>
                        <div class="controls">
                            <input type="text" name="uEmail" value="" class="ccm-input-text" >
                        </div>
                    </div>

                    <div class="actions">
                        <input type="submit" class="btn ccm-input-submit" id="submit" name="submit" value="Reset and Email Password &gt;" />	</div>

                </div>
            </div>
        </form>




    </div>
</div>

<script type="text/javascript" src="http://www.cpl.uh.edu/concrete/js/bootstrap.js?v=cd6d3742f62dbcab6fa890a719708e1d"></script>

</body>
</html>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:26:01 CDT 2015
                  SOURCE: C:/first_play/app/views/user.scala.html
                  HASH: b487a8f8ac6b5324c347b9e653a4761cf114299f
                  MATRIX: 579->3|2104->1500|2133->1501|2165->1506|2491->1805|2519->1806|3521->2786|3550->2787|3583->2793|3643->2826|3671->2827|4058->3187|4073->3193|4131->3242|4171->3244|4212->3257|6371->5384|6409->5394
                  LINES: 22->4|50->32|50->32|51->33|54->36|54->36|82->64|82->64|83->65|84->66|84->66|100->82|100->82|100->82|100->82|101->83|152->134|154->136
                  -- GENERATED --
              */
          