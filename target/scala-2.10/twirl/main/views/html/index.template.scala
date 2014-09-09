
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
   <script type='text/javascript' src='"""),_display_(/*14.41*/routes/*14.47*/.Assets.at("js/test.js")),format.raw/*14.71*/("""'></script>


  <div style="background:#A0A0A0 ">
      <img src='"""),_display_(/*18.18*/routes/*18.24*/.Assets.at("images/cpllogo.png")),format.raw/*18.56*/("""' alt="Mountain View" style="width:214px;height:123px">
  </div>

<br>
<br>
<br>
<div id="parent" style="border:1px solid;width:100%; float: left">
<div class="css-treeview" style="float:left;width:20%;height:800px;">
    <ul>
        <li><input type="checkbox" id="item-0" /><label for="item-0">Children Study</label>
            <ul>
                <li><input type="checkbox" id="item-0-0" /><label for="item-0-0">S001</label>
                    <ul>
                        <li><input type="checkbox" id="item-0-0-0" /><label for="item-0-0-0">Baseline</label>
                            <ul id="S001BL">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>
                        <li><input type="checkbox" id="item-0-0-1" /><label for="item-0-0-0">BaseLine Reading</label>
                            <ul id="S001BR">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>
                        <li><input type="checkbox" id="item-0-0-2" /><label for="item-0-0-0">Difficult Reading</label>
                            <ul id="S001DR">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>
                        <li><input type="checkbox" id="item-0-0-3" /><label for="item-0-0-0">Direct View</label>
                            <ul id="S001DV">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>
                        <li><input type="checkbox" id="item-0-0-4" /><label for="item-0-0-0">Monitor View</label>
                            <ul id="S001MV">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><input type="checkbox" id="item-0-1" /><label for="item-0-1">S002</label>
                    <ul>
                        <li><input type="checkbox" id="item-0-0-22" /><label for="item-0-0-0">Difficult Reading</label>
                            <ul id="signal-D">
                                <li><a >Run 1</a></li>
                            </ul>
                        </li>

                    </ul>
                </li>

            </ul>
        </li>
    </ul>
</div>

<div  style="float:left;border:1px solid; border-radius:25px;background:#F0FFFF;width:60%;">
   <!-- <p> <button id = "bt"> Generate Line Chart</button> </p> -->
    <!-- <p><button id = "bt2"> Add a new line to the same chart</button> </p> -->

   <!-- <ul id ="persons" style="width:600"></ul> -->
     <!--<form action=""""),_display_(/*79.25*/routes/*79.31*/.Application.addPerson()),format.raw/*79.55*/("""" method ="post"> -->
        <!--<input name="name" type="text"> -->
        <!--<button> Add person</button> -->
     <!--</form> -->
    <!--<p id = test1> My name is Salah Taamne </p> -->
    <!--<button id = "btn1"> Print persons as alert</button> -->
    <div id="chart" style="width:100%"> </div>
    <div>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     <video id= "kid0" width="400px" controls>
      <source src= "assets/images/v.mp4" type="video/mp4">
      Your browser does not support HTML5 video.
     </video>
    <!-- <video id = "kid" width="400px" controls> -->
      <!-- Your browser does not support HTML5 video. -->
    <!-- </video> -->
    <!-- <button id = "cv"> Change the video please</button> -->
  <!-- <button id = "cv2"> Jum </button> -->
    </div>
   </div>
    </div>
  """)))}),format.raw/*100.4*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Sep 09 13:30:10 CDT 2014
                  SOURCE: C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/app/views/index.scala.html
                  HASH: 775cc7cd9d4ca7894c359be9ead5107d3b66356b
                  MATRIX: 505->1|610->18|638->21|676->51|715->53|748->60|1000->286|1014->292|1063->321|1142->373|1157->379|1209->410|1295->469|1310->475|1356->500|1439->556|1454->562|1499->586|1593->653|1608->659|1661->691|4414->3417|4429->3423|4474->3447|5339->4281
                  LINES: 19->1|22->1|24->3|24->3|24->3|28->7|30->9|30->9|30->9|31->10|31->10|31->10|34->13|34->13|34->13|35->14|35->14|35->14|39->18|39->18|39->18|100->79|100->79|100->79|121->100
                  -- GENERATED --
              */
          