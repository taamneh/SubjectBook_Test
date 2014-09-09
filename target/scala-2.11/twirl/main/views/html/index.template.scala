
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("My First Play Project ")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""

    """),_display_(/*5.6*/message),format.raw/*5.13*/("""

""")))}),format.raw/*7.2*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Aug 30 01:27:22 CDT 2014
                  SOURCE: C:/Users/Salah/first_play/app/views/index.scala.html
                  HASH: c3507cc472fbab26fcd8ec3b3cca7623c928f4f3
                  MATRIX: 505->1|610->18|638->21|676->51|715->53|747->60|774->67|806->70
                  LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|28->7
                  -- GENERATED --
              */
          