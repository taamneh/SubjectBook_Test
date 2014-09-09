
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
object salah extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="utf-8" />
    <title>CSS3 Treeview. No JavaScript by Martin Ivanov</title>
    <style>

        .css-treeview ul,
        .css-treeview li
        """),format.raw/*10.9*/("""{"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""padding: 0;
        margin: 0;
        list-style: none;
        """),format.raw/*14.9*/("""}"""),format.raw/*14.10*/("""

        """),format.raw/*16.9*/(""".css-treeview input
        """),format.raw/*17.9*/("""{"""),format.raw/*17.10*/("""
        """),format.raw/*18.9*/("""position: absolute;
        opacity: 0;
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/("""

        """),format.raw/*22.9*/(""".css-treeview
        """),format.raw/*23.9*/("""{"""),format.raw/*23.10*/("""
        """),format.raw/*24.9*/("""font: normal 15px "Segoe UI", Arial, Sans-serif;
        -moz-user-select: none;
        -webkit-user-select: none;
        user-select: none;
        """),format.raw/*28.9*/("""}"""),format.raw/*28.10*/("""

        """),format.raw/*30.9*/(""".css-treeview a
        """),format.raw/*31.9*/("""{"""),format.raw/*31.10*/("""
        """),format.raw/*32.9*/("""color: #00f;
        text-decoration: none;
        """),format.raw/*34.9*/("""}"""),format.raw/*34.10*/("""

        """),format.raw/*36.9*/(""".css-treeview a:hover
        """),format.raw/*37.9*/("""{"""),format.raw/*37.10*/("""
        """),format.raw/*38.9*/("""text-decoration: underline;
        """),format.raw/*39.9*/("""}"""),format.raw/*39.10*/("""

        """),format.raw/*41.9*/(""".css-treeview input + label + ul
        """),format.raw/*42.9*/("""{"""),format.raw/*42.10*/("""
        """),format.raw/*43.9*/("""margin: 0 0 0 22px;
        """),format.raw/*44.9*/("""}"""),format.raw/*44.10*/("""

        """),format.raw/*46.9*/(""".css-treeview input ~ ul
        """),format.raw/*47.9*/("""{"""),format.raw/*47.10*/("""
        """),format.raw/*48.9*/("""display: none;
        """),format.raw/*49.9*/("""}"""),format.raw/*49.10*/("""

        """),format.raw/*51.9*/(""".css-treeview label,
        .css-treeview label::before
        """),format.raw/*53.9*/("""{"""),format.raw/*53.10*/("""
        """),format.raw/*54.9*/("""cursor: pointer;
        """),format.raw/*55.9*/("""}"""),format.raw/*55.10*/("""

        """),format.raw/*57.9*/(""".css-treeview input:disabled + label
        """),format.raw/*58.9*/("""{"""),format.raw/*58.10*/("""
        """),format.raw/*59.9*/("""cursor: default;
        opacity: .6;
        """),format.raw/*61.9*/("""}"""),format.raw/*61.10*/("""

        """),format.raw/*63.9*/(""".css-treeview input:checked:not(:disabled) ~ ul
        """),format.raw/*64.9*/("""{"""),format.raw/*64.10*/("""
        """),format.raw/*65.9*/("""display: block;
        """),format.raw/*66.9*/("""}"""),format.raw/*66.10*/("""

        """),format.raw/*68.9*/(""".css-treeview label,
        .css-treeview label::before
        """),format.raw/*70.9*/("""{"""),format.raw/*70.10*/("""
        """),format.raw/*71.9*/("""background: url("assets/images/icons.png") no-repeat;
        """),format.raw/*72.9*/("""}"""),format.raw/*72.10*/("""

        """),format.raw/*74.9*/(""".css-treeview label,
        .css-treeview a,
        .css-treeview label::before
        """),format.raw/*77.9*/("""{"""),format.raw/*77.10*/("""
        """),format.raw/*78.9*/("""display: inline-block;
        height: 16px;
        line-height: 16px;,
        vertical-align: middle;
        """),format.raw/*82.9*/("""}"""),format.raw/*82.10*/("""

        """),format.raw/*84.9*/(""".css-treeview label
        """),format.raw/*85.9*/("""{"""),format.raw/*85.10*/("""
        """),format.raw/*86.9*/("""background-position: 18px 0;
        """),format.raw/*87.9*/("""}"""),format.raw/*87.10*/("""

        """),format.raw/*89.9*/(""".css-treeview label::before
        """),format.raw/*90.9*/("""{"""),format.raw/*90.10*/("""
        """),format.raw/*91.9*/("""content: "";
        width: 16px;
        margin: 0 22px 0 0;
        vertical-align: middle;
        background-position: 0 -32px;
        """),format.raw/*96.9*/("""}"""),format.raw/*96.10*/("""

        """),format.raw/*98.9*/(""".css-treeview input:checked + label::before
        """),format.raw/*99.9*/("""{"""),format.raw/*99.10*/("""
        """),format.raw/*100.9*/("""background-position: 0 -16px;
        """),format.raw/*101.9*/("""}"""),format.raw/*101.10*/("""

        """),format.raw/*103.9*/("""/* webkit adjacent element selector bugfix */

    </style>
</head>
<body>
<h1>CSS3 Treeview. No JavaScript by Martin Ivanov</h1>
<p>If you like this solution, you can also check my
    <a href="http://wemakesites.net/" target="_blank">personal wesbite</a>,
    <a href="http://acidjs.wemakesites.net/" target="_blank">Acid.JS Web.UI</a>,
    <a href="https://acidmartin.wordpress.com/" target="_blank">my blog</a>
    or follow me on <a href="https://twitter.com/#!/wemakesitesnet" target="_blank">Twitter</a>.</p>
<p>Love that? Please, <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=QFUHPWJB2JDBS" target="_blank">donate</a>! </p>
<p>This solution works with all modern browsers and Internet Explorer 9+.
    If you are interested in <a href="http://experiments.wemakesites.net/miscellaneous/acidjs-css3-treeview/" target="_blank">purchasing</a> a JavaScript enabler for IE8 for the
    CSS3 Treeview, please, <a href="http://experiments.wemakesites.net/miscellaneous/acidjs-css3-treeview/" target="_blank">click here</a>.</p>
<div class="css-treeview">
    <ul>
        <li><input type="checkbox" id="item-0" /><label for="item-0">This Folder is Closed By Default</label>
            <ul>
                <li><input type="checkbox" id="item-0-0" /><label for="item-0-0">Ooops! A Nested Folder</label>
                    <ul>
                        <li><input type="checkbox" id="item-0-0-0" /><label for="item-0-0-0">Look Ma - No Hands!</label>
                            <ul>
                                <li><a href="./">First Nested Item</a></li>
                                <li><a href="./">Second Nested Item</a></li>
                                <li><a href="./">Third Nested Item</a></li>
                                <li><a href="./">Fourth Nested Item</a></li>
                            </ul>
                        </li>
                        <li><a href="./">Item 1</a></li>
                        <li><a href="./">Item 2</a></li>
                        <li><a href="./">Item 3</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-0-1" /><label for="item-0-1">Yet Another One</label>
                    <ul>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-0-2" disabled="disabled" /><label for="item-0-2">Disabled Nested Items</label>
                    <ul>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                    </ul>
                </li>
                <li><a href="./">item</a></li>
                <li><a href="./">item</a></li>
                <li><a href="./">item</a></li>
                <li><a href="./">item</a></li>
            </ul>
        </li>
        <li><input type="checkbox" id="item-1" checked="checked" /><label for="item-1">This One is Open by Default...</label>
            <ul>
                <li><input type="checkbox" id="item-1-0" /><label for="item-1-0">And Contains More Nested Items...</label>
                    <ul>
                        <li><a href="./">Look Ma - No Hands</a></li>
                        <li><a href="./">Another Item</a></li>
                        <li><a href="./">And Yet Another</a></li>
                    </ul>
                </li>
                <li><a href="./">Lorem</a></li>
                <li><a href="./">Ipsum</a></li>
                <li><a href="./">Dolor</a></li>
                <li><a href="./">Sit Amet</a></li>
            </ul>
        </li>
        <li><input type="checkbox" id="item-2" /><label for="item-2">Can You Believe...</label>
            <ul>
                <li><input type="checkbox" id="item-2-0" /><label for="item-2-0">That This Treeview...</label>
                    <ul>
                        <li><input type="checkbox" id="item-2-2-0" /><label for="item-2-2-0">Does Not Use Any JavaScript...</label>
                            <ul>
                                <li><a href="./">But Relies Only</a></li>
                                <li><a href="./">On the Power</a></li>
                                <li><a href="./">Of CSS3</a></li>
                            </ul>
                        </li>
                        <li><a href="./">Item 1</a></li>
                        <li><a href="./">Item 2</a></li>
                        <li><a href="./">Item 3</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-2-1" /><label for="item-2-1">This is a Folder With...</label>
                    <ul>
                        <li><a href="./">Some Nested Items...</a></li>
                        <li><a href="./">Some Nested Items...</a></li>
                        <li><a href="./">Some Nested Items...</a></li>
                        <li><a href="./">Some Nested Items...</a></li>
                        <li><a href="./">Some Nested Items...</a></li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-2-2" disabled="disabled" /><label for="item-2-2">Disabled Nested Items</label>
                    <ul>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                        <li><a href="./">item</a></li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Sep 08 15:33:52 CDT 2014
                  SOURCE: C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/app/views/salah.scala.html
                  HASH: 26128a0092b94ff774be16a3fb94ffd31bc5415a
                  MATRIX: 580->0|825->218|854->219|891->229|986->297|1015->298|1054->310|1110->339|1139->340|1176->350|1253->400|1282->401|1321->413|1371->436|1400->437|1437->447|1619->602|1648->603|1687->615|1739->640|1768->641|1805->651|1886->705|1915->706|1954->718|2012->749|2041->750|2078->760|2142->797|2171->798|2210->810|2279->852|2308->853|2345->863|2401->892|2430->893|2469->905|2530->939|2559->940|2596->950|2647->974|2676->975|2715->987|2809->1054|2838->1055|2875->1065|2928->1091|2957->1092|2996->1104|3069->1150|3098->1151|3135->1161|3210->1209|3239->1210|3278->1222|3362->1279|3391->1280|3428->1290|3480->1315|3509->1316|3548->1328|3642->1395|3671->1396|3708->1406|3798->1469|3827->1470|3866->1482|3986->1575|4015->1576|4052->1586|4196->1703|4225->1704|4264->1716|4320->1745|4349->1746|4386->1756|4451->1794|4480->1795|4519->1807|4583->1844|4612->1845|4649->1855|4821->2000|4850->2001|4889->2013|4969->2066|4998->2067|5036->2077|5103->2116|5133->2117|5173->2129
                  LINES: 22->1|31->10|31->10|32->11|35->14|35->14|37->16|38->17|38->17|39->18|41->20|41->20|43->22|44->23|44->23|45->24|49->28|49->28|51->30|52->31|52->31|53->32|55->34|55->34|57->36|58->37|58->37|59->38|60->39|60->39|62->41|63->42|63->42|64->43|65->44|65->44|67->46|68->47|68->47|69->48|70->49|70->49|72->51|74->53|74->53|75->54|76->55|76->55|78->57|79->58|79->58|80->59|82->61|82->61|84->63|85->64|85->64|86->65|87->66|87->66|89->68|91->70|91->70|92->71|93->72|93->72|95->74|98->77|98->77|99->78|103->82|103->82|105->84|106->85|106->85|107->86|108->87|108->87|110->89|111->90|111->90|112->91|117->96|117->96|119->98|120->99|120->99|121->100|122->101|122->101|124->103
                  -- GENERATED --
              */
          