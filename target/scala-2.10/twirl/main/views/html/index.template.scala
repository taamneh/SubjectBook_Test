
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



   """),format.raw/*7.4*/("""<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
   <script type='text/javascript' src='"""),_display_(/*9.41*/routes/*9.47*/.Assets.at("js/drawchart.js")),format.raw/*9.76*/("""'></script>
   <script type='text/javascript' src='"""),_display_(/*10.41*/routes/*10.47*/.Assets.at("js/changevideo.js")),format.raw/*10.78*/("""'></script>


   <!-- <script type='text/javascript' src='"""),_display_(/*13.46*/routes/*13.52*/.Assets.at("js/index.js")),format.raw/*13.77*/("""'></script> -->
   <script type='text/javascript' src='"""),_display_(/*14.41*/routes/*14.47*/.Assets.at("js/Intialization.js")),format.raw/*14.80*/("""'></script>
   <script type='text/javascript' src='"""),_display_(/*15.41*/routes/*15.47*/.Assets.at("js/buildtree.js")),format.raw/*15.76*/("""'></script>


  <div style="background:#A0A0A0 ">
      <img src='"""),_display_(/*19.18*/routes/*19.24*/.Assets.at("images/cpllogo.png")),format.raw/*19.56*/("""' alt="Mountain View" style="width:214px;height:123px">
  </div>

<br>
<br>
<br>
<div id="parent" style="border:1px solid;width:100%; float: left" >
    <br>
<div class="css-treeview" style="float:left;width:20%;height:1000px;">
    <button id = "cv2"> Build the Tree </button>
    <ul>
        <li><input type="checkbox" id="item-0" /><label for="item-0">Children Study</label>
            <ul class ="subjects">
                <!--<ul id="signal"><li><a>Run 1</a></li></ul></li></ul> -->
            </ul>
        </li>
    </ul>
</div>
    </div>
  """)))}),format.raw/*38.4*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Sep 10 12:49:43 CDT 2014
                  SOURCE: C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/app/views/index.scala.html
                  HASH: 985ee7ccf29e7d985ac79e71ca0f483398386124
                  MATRIX: 505->1|610->18|638->21|676->51|715->53|748->60|999->285|1013->291|1062->320|1141->372|1156->378|1208->409|1294->468|1309->474|1355->499|1438->555|1453->561|1507->594|1586->646|1601->652|1651->681|1745->748|1760->754|1813->786|2397->1340
                  LINES: 19->1|22->1|24->3|24->3|24->3|28->7|30->9|30->9|30->9|31->10|31->10|31->10|34->13|34->13|34->13|35->14|35->14|35->14|36->15|36->15|36->15|40->19|40->19|40->19|59->38
                  -- GENERATED --
              */
          