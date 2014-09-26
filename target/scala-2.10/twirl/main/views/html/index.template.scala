
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

"""),_display_(/*3.2*/main(message)/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""



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
     <div class="SubjectsDock">
     </div>
</div>
  """)))}),format.raw/*40.4*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Sep 19 00:42:34 CDT 2014
                  SOURCE: C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/app/views/index.scala.html
                  HASH: f737fd840eb7a55d81bfb24379427783d57be0e5
                  MATRIX: 505->1|610->18|638->21|659->34|698->36|731->43|982->268|996->274|1045->303|1124->355|1139->361|1191->392|1277->451|1292->457|1338->482|1421->538|1436->544|1490->577|1569->629|1584->635|1634->664|1728->731|1743->737|1796->769|2420->1363
                  LINES: 19->1|22->1|24->3|24->3|24->3|28->7|30->9|30->9|30->9|31->10|31->10|31->10|34->13|34->13|34->13|35->14|35->14|35->14|36->15|36->15|36->15|40->19|40->19|40->19|61->40
                  -- GENERATED --
              */
          