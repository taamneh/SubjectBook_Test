
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <meta charset="utf-8" />
        <style>
            .css-treeview ul,
            .css-treeview li
            """),format.raw/*12.13*/("""{"""),format.raw/*12.14*/("""
            """),format.raw/*13.13*/("""padding: 0;
            margin: 0;
            list-style: none;
            """),format.raw/*16.13*/("""}"""),format.raw/*16.14*/("""

            """),format.raw/*18.13*/(""".css-treeview input
            """),format.raw/*19.13*/("""{"""),format.raw/*19.14*/("""
            """),format.raw/*20.13*/("""position: absolute;
            opacity: 0;
            """),format.raw/*22.13*/("""}"""),format.raw/*22.14*/("""

            """),format.raw/*24.13*/(""".css-treeview
            """),format.raw/*25.13*/("""{"""),format.raw/*25.14*/("""
            """),format.raw/*26.13*/("""font: normal 11px "Segoe UI", Arial, Sans-serif;
            -moz-user-select: none;
            -webkit-user-select: none;
            user-select: none;
            """),format.raw/*30.13*/("""}"""),format.raw/*30.14*/("""

            """),format.raw/*32.13*/(""".css-treeview a
            """),format.raw/*33.13*/("""{"""),format.raw/*33.14*/("""
            """),format.raw/*34.13*/("""color: #00f;
            text-decoration: none;
            """),format.raw/*36.13*/("""}"""),format.raw/*36.14*/("""

            """),format.raw/*38.13*/(""".css-treeview a:hover
            """),format.raw/*39.13*/("""{"""),format.raw/*39.14*/("""
            """),format.raw/*40.13*/("""text-decoration: underline;
            """),format.raw/*41.13*/("""}"""),format.raw/*41.14*/("""

            """),format.raw/*43.13*/(""".css-treeview input + label + ul
            """),format.raw/*44.13*/("""{"""),format.raw/*44.14*/("""
            """),format.raw/*45.13*/("""margin: 0 0 0 22px;
            """),format.raw/*46.13*/("""}"""),format.raw/*46.14*/("""

            """),format.raw/*48.13*/(""".css-treeview input ~ ul
            """),format.raw/*49.13*/("""{"""),format.raw/*49.14*/("""
            """),format.raw/*50.13*/("""display: none;
            """),format.raw/*51.13*/("""}"""),format.raw/*51.14*/("""

            """),format.raw/*53.13*/(""".css-treeview label,
            .css-treeview label::before
            """),format.raw/*55.13*/("""{"""),format.raw/*55.14*/("""
            """),format.raw/*56.13*/("""cursor: pointer;
            """),format.raw/*57.13*/("""}"""),format.raw/*57.14*/("""

            """),format.raw/*59.13*/(""".css-treeview input:disabled + label
            """),format.raw/*60.13*/("""{"""),format.raw/*60.14*/("""
            """),format.raw/*61.13*/("""cursor: default;
            opacity: .6;
            """),format.raw/*63.13*/("""}"""),format.raw/*63.14*/("""

            """),format.raw/*65.13*/(""".css-treeview input:checked:not(:disabled) ~ ul
            """),format.raw/*66.13*/("""{"""),format.raw/*66.14*/("""
            """),format.raw/*67.13*/("""display: block;
            """),format.raw/*68.13*/("""}"""),format.raw/*68.14*/("""

            """),format.raw/*70.13*/(""".css-treeview label,
            .css-treeview label::before
            """),format.raw/*72.13*/("""{"""),format.raw/*72.14*/("""
            """),format.raw/*73.13*/("""background: url("assets/images/icons.png") no-repeat;
            """),format.raw/*74.13*/("""}"""),format.raw/*74.14*/("""

            """),format.raw/*76.13*/(""".css-treeview label,
            .css-treeview a,
            .css-treeview label::before
            """),format.raw/*79.13*/("""{"""),format.raw/*79.14*/("""
            """),format.raw/*80.13*/("""display: inline-block;
            height: 16px;
            line-height: 16px;,
            vertical-align: middle;
            """),format.raw/*84.13*/("""}"""),format.raw/*84.14*/("""

            """),format.raw/*86.13*/(""".css-treeview label
            """),format.raw/*87.13*/("""{"""),format.raw/*87.14*/("""
            """),format.raw/*88.13*/("""background-position: 18px 0;
            """),format.raw/*89.13*/("""}"""),format.raw/*89.14*/("""

            """),format.raw/*91.13*/(""".css-treeview label::before
            """),format.raw/*92.13*/("""{"""),format.raw/*92.14*/("""
            """),format.raw/*93.13*/("""content: "";
            width: 16px;
            margin: 0 22px 0 0;
            vertical-align: middle;
            background-position: 0 -32px;
            """),format.raw/*98.13*/("""}"""),format.raw/*98.14*/("""

            """),format.raw/*100.13*/(""".css-treeview input:checked + label::before
            """),format.raw/*101.13*/("""{"""),format.raw/*101.14*/("""
            """),format.raw/*102.13*/("""background-position: 0 -16px;
            """),format.raw/*103.13*/("""}"""),format.raw/*103.14*/("""

            """),format.raw/*105.13*/("""/* webkit adjacent element selector bugfix */
        </style>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*107.54*/routes/*107.60*/.Assets.at("stylesheets/main.css")),format.raw/*107.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*108.59*/routes/*108.65*/.Assets.at("images/favicon.png")),format.raw/*108.97*/("""">
        <script src=""""),_display_(/*109.23*/routes/*109.29*/.Assets.at("javascripts/hello.js")),format.raw/*109.63*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(/*112.10*/content),format.raw/*112.17*/("""
    """),format.raw/*113.5*/("""</body>
</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Sep 08 15:33:52 CDT 2014
                  SOURCE: C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/app/views/main.scala.html
                  HASH: 868ccc9c806b01033fce5236e90de6063aa68ddf
                  MATRIX: 509->1|627->31|655->33|732->84|757->89|914->218|943->219|984->232|1089->309|1118->310|1160->324|1220->356|1249->357|1290->370|1374->426|1403->427|1445->441|1499->467|1528->468|1569->481|1764->648|1793->649|1835->663|1891->691|1920->692|1961->705|2049->765|2078->766|2120->780|2182->814|2211->815|2252->828|2320->868|2349->869|2391->883|2464->928|2493->929|2534->942|2594->974|2623->975|2665->989|2730->1026|2759->1027|2800->1040|2855->1067|2884->1068|2926->1082|3027->1155|3056->1156|3097->1169|3154->1198|3183->1199|3225->1213|3302->1262|3331->1263|3372->1276|3454->1330|3483->1331|3525->1345|3613->1405|3642->1406|3683->1419|3739->1447|3768->1448|3810->1462|3911->1535|3940->1536|3981->1549|4075->1615|4104->1616|4146->1630|4276->1732|4305->1733|4346->1746|4503->1875|4532->1876|4574->1890|4634->1922|4663->1923|4704->1936|4773->1977|4802->1978|4844->1992|4912->2032|4941->2033|4982->2046|5170->2206|5199->2207|5242->2221|5327->2277|5357->2278|5399->2291|5470->2333|5500->2334|5543->2348|5687->2464|5703->2470|5759->2504|5848->2565|5864->2571|5918->2603|5971->2628|5987->2634|6043->2668|6138->2735|6167->2742|6200->2747
                  LINES: 19->1|22->1|24->3|28->7|28->7|33->12|33->12|34->13|37->16|37->16|39->18|40->19|40->19|41->20|43->22|43->22|45->24|46->25|46->25|47->26|51->30|51->30|53->32|54->33|54->33|55->34|57->36|57->36|59->38|60->39|60->39|61->40|62->41|62->41|64->43|65->44|65->44|66->45|67->46|67->46|69->48|70->49|70->49|71->50|72->51|72->51|74->53|76->55|76->55|77->56|78->57|78->57|80->59|81->60|81->60|82->61|84->63|84->63|86->65|87->66|87->66|88->67|89->68|89->68|91->70|93->72|93->72|94->73|95->74|95->74|97->76|100->79|100->79|101->80|105->84|105->84|107->86|108->87|108->87|109->88|110->89|110->89|112->91|113->92|113->92|114->93|119->98|119->98|121->100|122->101|122->101|123->102|124->103|124->103|126->105|128->107|128->107|128->107|129->108|129->108|129->108|130->109|130->109|130->109|133->112|133->112|134->113
                  -- GENERATED --
              */
          