
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
object ShowSignals extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template9[String,Map[String, List[scala.Tuple4[String, Int, Int, String]]],Int,String,Map[String, List[scala.Tuple2[String, String]]],Int,List[String],List[scala.Tuple3[Int, Int, Int]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(SubjectId: String, sub_sess:Map[String, List[(String, Int, Int, String)]], studyNo:Int, userName: String, videos:  Map[String, List[(String, String)]], study_type: Int, sub_sess_menu: List[String], generalList: List[(Int, Int, Int)], studyName: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var i=1;
import java.math.BigInteger; var j =1;

Seq[Any](format.raw/*1.255*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
    <style type="text/css">

        .markerStyle
        """),format.raw/*8.9*/("""{"""),format.raw/*8.10*/("""
        """),format.raw/*9.9*/("""position: absolute;
        left: 40px;
        top: 50px;
        transition: left .5s ease-in, top .5s ease-in;
        width:0.0em;
        height:14.5em;
        #border-left: thick solid #99ccff;
        border-left: thick solid pink;
        white-space:nowrap;
        padding:0.5em;
        z-index: 10;
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/("""


    """),format.raw/*23.5*/("""</style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SubjectBook</title>
    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">

    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type='text/javascript' src='"""),_display_(/*41.42*/routes/*41.48*/.Assets.at("javascripts/signalwithtabNew.js")),format.raw/*41.93*/("""'></script>
    <link rel="stylesheet" type="text/css" href="/assets/stylesheets/jquery-ui-1.9.2.css">


    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/chart.min.js"></script>
    <script src="/assets/javascripts/chart-data.js"></script>
    <script src="/assets/javascripts/easypiechart.js"></script>
    <script src="/assets/javascripts/easypiechart-data.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>
    <script>

		!function ($) """),format.raw/*53.17*/("""{"""),format.raw/*53.18*/("""
		    """),format.raw/*54.7*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*54.76*/("""{"""),format.raw/*54.77*/("""
		        """),format.raw/*55.11*/("""$(this).find('em:first').toggleClass("glyphicon-minus");
		    """),format.raw/*56.7*/("""}"""),format.raw/*56.8*/(""");


		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*60.3*/("""}"""),format.raw/*60.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*62.38*/("""{"""),format.raw/*62.39*/("""
		  """),format.raw/*63.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*64.3*/("""}"""),format.raw/*64.4*/(""")
		$(window).on('resize', function () """),format.raw/*65.38*/("""{"""),format.raw/*65.39*/("""
		  """),format.raw/*66.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*67.3*/("""}"""),format.raw/*67.4*/(""")
        // this is to unfold the subject list
        $(document).ready(function()"""),format.raw/*69.37*/("""{"""),format.raw/*69.38*/("""
        """),format.raw/*70.9*/("""$('ul.nav li.parent > a > span.icon').trigger("click");
        """),format.raw/*71.9*/("""}"""),format.raw/*71.10*/(""");

</script>









    <!-- Ignite UI Required Combined CSS Files -->
    <link href="/assets/stylesheets/infragistics.theme.css" rel="stylesheet">
    <!-- <link href="http://cdn-na.infragistics.com/igniteui/2015.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" /> -->
    <!--  <link href="http://cdn-na.infragistics.com/igniteui/2015.1/latest/css/structure/infragistics.css" rel="stylesheet" />-->

    <!-- Used to style the API Viewer and Explorer UI -->
    <!--  <link href="http://www.igniteui.com/css/apiviewer.css" rel="stylesheet" type="text/css" /> -->

    <!--<script src="http://modernizr.com/downloads/modernizr-latest.js"></script> -->
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>

    <!-- Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2015.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2015.1/latest/js/infragistics.dv.js"></script>


</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>Subject</span>Book</a> <a class="navbar-brand" href="/allStudies"><span></span>My Studies</a>
            <a class="navbar-brand" href="#"><span></span>Share</a>
            <a class="navbar-brand" href="/frontPage"><span></span>Help</a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*115.128*/userName),format.raw/*115.136*/(""" """),format.raw/*115.137*/("""<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                        <li><a href="/"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>

    </div><!-- /.container-fluid -->
</nav>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">

        <li><a href="/allStudies"><span class="glyphicon glyphicon-th"></span> My Studies </a></li>
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> New Study</a></li>
        <li><a href="/showAllDataTypes"><span class="glyphicon glyphicon-cog"></span> Manage your Data</a></li>
        <li><a href="/showAllPsychometric"><span class="glyphicon glyphicon-cog"></span> Manage your Psychometric data</a></li>
        <li class="parent ">
            <a href="#">
                <span class="glyphicon glyphicon-list"></span> Subjects <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>
            <ul class="children collapse" id="sub-item-1">
                """),_display_(/*145.18*/for(subject <- sub_sess_menu) yield /*145.47*/{_display_(Seq[Any](format.raw/*145.48*/("""

                """),format.raw/*147.17*/("""<li>

                    <a class="" href="/displaySubject?studyNo="""),_display_(/*149.64*/studyNo),format.raw/*149.71*/("""&SubjectId="""),_display_(/*149.83*/subject),format.raw/*149.90*/("""">
                        <span class="glyphicon glyphicon-user"></span> """),_display_(/*150.73*/subject),format.raw/*150.80*/("""
                    """),format.raw/*151.21*/("""</a>
                </li>


                """)))}),format.raw/*155.18*/("""

            """),format.raw/*157.13*/("""</ul>
        </li>
        <li role="presentation" class="divider"></li>

    </ul>

</div><!--/.sidebar-->

<div id = 'main' class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

    <!-- <img src="/assets/images/ajax-loader.gif" alt="Mountain View" style="width:304px;height:228px"> -->
    <!-- <img src="/assets/images/ajax-loader.gif" alt="Mountain View" style="width:304px;height:228px"> -->

    <div class="row">
        <ol class="breadcrumb">
            <li><a href="/allStudies"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active"><a href="/showStudy/"""),_display_(/*173.53*/studyNo),format.raw/*173.60*/("""">"""),_display_(/*173.63*/studyName),format.raw/*173.72*/(""" """),format.raw/*173.73*/("""</a> </li>
            <li class="active"><a href="/showStudySkipTop/"""),_display_(/*174.60*/studyNo),format.raw/*174.67*/("""">User Portrait </a> </li>
            <li>"""),_display_(/*175.18*/SubjectId),format.raw/*175.27*/("""</li>



            <!-- <li><button id ='hideSideBar'><a href="/file">toggle</a></button></li> -->
        </ol>
    </div><!--/.row-->
    <div class="row">
        </br>
    </div>

    <div class="row" id ="allInfo">


        <a href="/showRadar?SubjectId="""),_display_(/*189.40*/SubjectId),format.raw/*189.49*/("""&studyNo="""),_display_(/*189.59*/studyNo),format.raw/*189.66*/(""""> Show Session view </a>

        """),_display_(/*191.10*/for(temp <- generalList) yield /*191.34*/{_display_(Seq[Any](format.raw/*191.35*/("""

        """),format.raw/*193.9*/("""<div class="col-md-4" id="generalData"""),_display_(/*193.47*/temp/*193.51*/._1),_display_(/*193.55*/temp/*193.59*/._2),format.raw/*193.62*/(""""  subject=""""),_display_(/*193.75*/SubjectId),format.raw/*193.84*/("""" session="sss" signalSequence=""""),_display_(/*193.117*/temp/*193.121*/._1),format.raw/*193.124*/("""" signalCode=""""),_display_(/*193.139*/temp/*193.143*/._2),format.raw/*193.146*/("""" dataType=""""),_display_(/*193.159*/temp/*193.163*/._3),format.raw/*193.166*/("""" style="display: none;">

        </div>

        """)))}),format.raw/*197.10*/("""



    """),format.raw/*201.5*/("""</div><!--/.row-->
    <div class="">
        <div class="panel panel-default">
            <div class="panel-body tabs">
                """),format.raw/*206.1*/("""                """),format.raw/*206.17*/("""<ul class="nav nav-tabs">
                    """),_display_(/*207.22*/for((name,session) <- sub_sess) yield /*207.53*/{_display_(Seq[Any](format.raw/*207.54*/("""
                    """),_display_(/*208.22*/if(i == 1)/*208.32*/ {_display_(Seq[Any](format.raw/*208.34*/("""
                    """),format.raw/*209.21*/("""<li class="active"><a  href="#"""),_display_(/*209.52*/name),format.raw/*209.56*/("""" id="link"""),_display_(/*209.67*/name),format.raw/*209.71*/("""" class="onesignal" subject=""""),_display_(/*209.101*/SubjectId),format.raw/*209.110*/("""" session=""""),_display_(/*209.122*/name),format.raw/*209.126*/("""" studyId="""),_display_(/*209.137*/studyNo),format.raw/*209.144*/("""  """),format.raw/*209.146*/("""occupied ="no" data-toggle="tab">"""),_display_(/*209.180*/name),format.raw/*209.184*/("""</a></li>
                    """)))}/*210.23*/else/*210.28*/{_display_(Seq[Any](format.raw/*210.29*/("""
                    """),format.raw/*211.21*/("""<li><a  href="#"""),_display_(/*211.37*/name),format.raw/*211.41*/("""" id="link"""),_display_(/*211.52*/name),format.raw/*211.56*/("""" class="onesignal" subject=""""),_display_(/*211.86*/SubjectId),format.raw/*211.95*/("""" session=""""),_display_(/*211.107*/name),format.raw/*211.111*/("""" studyId="""),_display_(/*211.122*/studyNo),format.raw/*211.129*/("""  """),format.raw/*211.131*/("""occupied ="no" data-toggle="tab">"""),_display_(/*211.165*/name),format.raw/*211.169*/("""</a></li>
                    """)))}),format.raw/*212.22*/("""
                    """),_display_(/*213.22*/{i = i + 1;}),format.raw/*213.34*/("""
                    """)))}),format.raw/*214.22*/("""
                """),format.raw/*215.17*/("""</ul>

                <div class="tab-content">

                    """),format.raw/*220.1*/("""                    """),_display_(/*220.22*/for((name,sessions) <- sub_sess) yield /*220.54*/{_display_(Seq[Any](format.raw/*220.55*/("""
                            """),_display_(/*221.30*/if(j == 1)/*221.40*/ {_display_(Seq[Any](format.raw/*221.42*/("""
                             """),format.raw/*222.30*/("""<div id=""""),_display_(/*222.40*/name),format.raw/*222.44*/("""" class="tab-pane fade in active">
                            """)))}/*223.31*/else/*223.36*/{_display_(Seq[Any](format.raw/*223.37*/("""
                            """),format.raw/*224.29*/("""<div id=""""),_display_(/*224.39*/name),format.raw/*224.43*/("""" class="tab-pane fade">
                            """)))}),format.raw/*225.30*/("""
                            """),_display_(/*226.30*/{j = j + 1;}),format.raw/*226.42*/("""
                                """),_display_(/*227.34*/for(signal <- sessions) yield /*227.57*/{_display_(Seq[Any](format.raw/*227.58*/("""
                                    """),format.raw/*228.37*/("""<!-- to avoid the non signal files -->
                                        """),_display_(/*229.42*/if(signal._3 == 1 || signal._3 == 3 || signal._3 == 4|| signal._3 == 5)/*229.113*/ {_display_(Seq[Any](format.raw/*229.115*/("""
                                        """),format.raw/*230.41*/("""<div  id="dashboard_div"""),_display_(/*230.65*/name),_display_(/*230.70*/signal/*230.76*/._2),format.raw/*230.79*/("""">
                                            <div id="chart"""),_display_(/*231.60*/name),_display_(/*231.65*/signal/*231.71*/._2),format.raw/*231.74*/("""" class="chart" subject=""""),_display_(/*231.100*/SubjectId),format.raw/*231.109*/("""" session=""""),_display_(/*231.121*/name),format.raw/*231.125*/("""" signalSequence=""""),_display_(/*231.144*/signal/*231.150*/._2),format.raw/*231.153*/("""" signalType=""""),_display_(/*231.168*/signal/*231.174*/._3),format.raw/*231.177*/("""" yTitle = """"),_display_(/*231.190*/signal/*231.196*/._4),format.raw/*231.199*/(""""  style="height: 250px;">
                                            </div>
                                        </div>
                                        <!--<br> -->

                                        <!--<br> -->
                                            """),_display_(/*237.46*/if(signal._3 == 1)/*237.64*/{_display_(Seq[Any](format.raw/*237.65*/("""
                                                """),format.raw/*238.49*/("""&nbsp;<div id="videoboard"""),_display_(/*238.75*/name),_display_(/*238.80*/signal/*238.86*/._2),format.raw/*238.89*/("""" class= "video-board" name="videoboard"""),_display_(/*238.129*/name),_display_(/*238.134*/signal/*238.140*/._2),format.raw/*238.143*/("""" style="position:relative; left: 40px; width: 80%; height: 400px; overflow-x:scroll;  white-space: nowrap;" hidden>
                                                <div class="button-station"> <a id = "showvideo"""),_display_(/*239.97*/name),_display_(/*239.102*/signal/*239.108*/._2),format.raw/*239.111*/("""" class = "btn btn-default show-video" >Hide Videos</a>  </div>
                                                """),_display_(/*240.50*/{import java.math.BigInteger; var i=1}),format.raw/*240.88*/("""
                                                    """),_display_(/*241.54*/for((sess,videoList) <- videos) yield /*241.85*/{_display_(Seq[Any](format.raw/*241.86*/("""
                                                        """),_display_(/*242.58*/if(sess == name)/*242.74*/ {_display_(Seq[Any](format.raw/*242.76*/("""
                                                            """),_display_(/*243.62*/for(video <- videoList) yield /*243.85*/{_display_(Seq[Any](format.raw/*243.86*/("""
                                                                """),_display_(/*244.66*/if(study_type == 1)/*244.85*/ {_display_(Seq[Any](format.raw/*244.87*/("""
                                                                """),format.raw/*245.65*/("""<object id=""""),_display_(/*245.78*/video),format.raw/*245.83*/("""">
                                                                    <param name='movie' value='https://video.google.com/get_player?docid=0B2TDTGk9sqZLQjlxcVRxYUNqOFE&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1'></param>
                                                                    <param name='allowFullScreen' value='true'></param>
                                                                    <param name='allowScriptAccess' value='always'></param>
                                                                    <embed id='video"""),_display_(/*249.86*/video/*249.91*/._2),_display_(/*249.95*/signal/*249.101*/._2),format.raw/*249.104*/("""' src='https://video.google.com/get_player?docid="""),_display_(/*249.154*/video/*249.159*/._2),format.raw/*249.162*/("""&amp;ps=docs&amp;partnerid=30&amp;cc_load_policy=1&amp;color=white&amp;autoplay=0&amp;enablejsapi=1&amp;playerapiid=video"""),_display_(/*249.284*/video/*249.289*/._2),_display_(/*249.293*/signal/*249.299*/._2),format.raw/*249.302*/("""' type='application/x-shockwave-flash' allowfullscreen='true' allowScriptAccess='always' width=50% height='300'></embed>
                                                                </object>
                                                                """)))}/*251.67*/else/*251.72*/{_display_(Seq[Any](format.raw/*251.73*/("""
                                                                """),format.raw/*252.65*/("""<object width='400' height='250' id=""""),_display_(/*252.103*/video),format.raw/*252.108*/("""">
                                                                    <embed src='"""),_display_(/*253.82*/video/*253.87*/._2),format.raw/*253.90*/("""' type='video/x-ms-wm' allowfullscreen='true' play ='false' autostart="false"  allowScriptAccess='always' width='350' height='250'></embed></object>
                                                                """)))}),format.raw/*254.66*/(""" """),format.raw/*254.67*/("""<!-- else -->
                                                            """),_display_(/*255.62*/{i = i + 1}),format.raw/*255.73*/("""
                                                            """)))}),format.raw/*256.62*/(""" """),format.raw/*256.63*/("""<!-- video List -->
                                                        """)))}),format.raw/*257.58*/(""" """),format.raw/*257.59*/("""<!-- if sess== session._1 -->
                                                    """)))}),format.raw/*258.54*/("""<!-- main for for videos -->
                                                <br>
                                                <a id = "stopvideo"""),_display_(/*260.68*/name),_display_(/*260.73*/signal/*260.79*/._2),format.raw/*260.82*/(""""  class="btn btn-default">Stop Videos</a>   &nbsp; &nbsp;
                                                </div>

                                            """)))}),format.raw/*263.46*/(""" """),format.raw/*263.47*/("""<!-- check the sequence number  -->
                                        """)))}),format.raw/*264.42*/(""" """),format.raw/*264.43*/("""<!-- check the type of signal -->


                            """)))}),format.raw/*267.30*/("""
                            """),format.raw/*268.29*/("""</div>
                        """)))}),format.raw/*269.26*/("""

                    """),format.raw/*271.21*/("""</div>
                </div>
            </div><!--/.panel-->
        </div><!--/.col-->




    </div>	<!--/.main-->



</div>

<div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
</div>

</body>

</html>
"""))}
  }

  def render(SubjectId:String,sub_sess:Map[String, List[scala.Tuple4[String, Int, Int, String]]],studyNo:Int,userName:String,videos:Map[String, List[scala.Tuple2[String, String]]],study_type:Int,sub_sess_menu:List[String],generalList:List[scala.Tuple3[Int, Int, Int]],studyName:String): play.twirl.api.HtmlFormat.Appendable = apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName)

  def f:((String,Map[String, List[scala.Tuple4[String, Int, Int, String]]],Int,String,Map[String, List[scala.Tuple2[String, String]]],Int,List[String],List[scala.Tuple3[Int, Int, Int]],String) => play.twirl.api.HtmlFormat.Appendable) = (SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName) => apply(SubjectId,sub_sess,studyNo,userName,videos,study_type,sub_sess_menu,generalList,studyName)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Dec 01 16:24:59 CST 2015
                  SOURCE: C:/first_play/app/views/ShowSignals.scala.html
                  HASH: 67a1ac5e15893dc368eea4ca5b13abd0cb4f6106
                  MATRIX: 686->1|1104->254|1132->256|1252->350|1280->351|1316->361|1674->692|1703->693|1740->703|2608->1544|2623->1550|2684->1590|2765->1644|2780->1650|2846->1695|3414->2235|3443->2236|3478->2244|3575->2313|3604->2314|3644->2326|3735->2390|3763->2391|3876->2477|3904->2478|3989->2535|4018->2536|4051->2542|4150->2614|4178->2615|4246->2655|4275->2656|4308->2662|4408->2735|4436->2736|4550->2822|4579->2823|4616->2833|4708->2898|4737->2899|6845->4978|6876->4986|6907->4987|8541->6593|8587->6622|8627->6623|8676->6643|8775->6714|8804->6721|8844->6733|8873->6740|8977->6816|9006->6823|9057->6845|9139->6895|9184->6911|9840->7539|9869->7546|9900->7549|9931->7558|9961->7559|10060->7630|10089->7637|10162->7682|10193->7691|10498->7968|10529->7977|10567->7987|10596->7994|10662->8032|10703->8056|10743->8057|10783->8069|10849->8107|10863->8111|10888->8115|10902->8119|10927->8122|10968->8135|10999->8144|11061->8177|11076->8181|11102->8184|11146->8199|11161->8203|11187->8206|11229->8219|11244->8223|11270->8226|11358->8282|11398->8294|11568->8476|11613->8492|11689->8540|11737->8571|11777->8572|11828->8595|11848->8605|11889->8607|11940->8629|11999->8660|12025->8664|12064->8675|12090->8679|12149->8709|12181->8718|12222->8730|12249->8734|12289->8745|12319->8752|12351->8754|12414->8788|12441->8792|12493->8825|12507->8830|12547->8831|12598->8853|12642->8869|12668->8873|12707->8884|12733->8888|12791->8918|12822->8927|12863->8939|12890->8943|12930->8954|12960->8961|12992->8963|13055->8997|13082->9001|13146->9033|13197->9056|13231->9068|13286->9091|13333->9109|13435->9224|13484->9245|13533->9277|13573->9278|13632->9309|13652->9319|13693->9321|13753->9352|13791->9362|13817->9366|13902->9432|13916->9437|13956->9438|14015->9468|14053->9478|14079->9482|14166->9537|14225->9568|14259->9580|14322->9615|14362->9638|14402->9639|14469->9677|14578->9758|14660->9829|14702->9831|14773->9873|14825->9897|14851->9902|14867->9908|14892->9911|14983->9974|15009->9979|15025->9985|15050->9988|15105->10014|15137->10023|15178->10035|15205->10039|15253->10058|15270->10064|15296->10067|15340->10082|15357->10088|15383->10091|15425->10104|15442->10110|15468->10113|15779->10396|15807->10414|15847->10415|15926->10465|15980->10491|16006->10496|16022->10502|16047->10505|16116->10545|16143->10550|16160->10556|16186->10559|16428->10773|16455->10778|16472->10784|16498->10787|16640->10901|16700->10939|16783->10994|16831->11025|16871->11026|16958->11085|16984->11101|17025->11103|17116->11166|17156->11189|17196->11190|17291->11257|17320->11276|17361->11278|17456->11344|17497->11357|17524->11362|18114->11924|18129->11929|18154->11933|18171->11939|18197->11942|18276->11992|18292->11997|18318->12000|18469->12122|18485->12127|18511->12131|18528->12137|18554->12140|18836->12403|18850->12408|18890->12409|18985->12475|19052->12513|19080->12518|19193->12603|19208->12608|19233->12611|19480->12826|19510->12827|19614->12903|19647->12914|19742->12977|19772->12978|19882->13056|19912->13057|20028->13141|20207->13292|20233->13297|20249->13303|20274->13306|20469->13469|20499->13470|20609->13548|20639->13549|20739->13617|20798->13647|20863->13680|20916->13704
                  LINES: 19->1|23->1|24->2|30->8|30->8|31->9|42->20|42->20|45->23|62->40|62->40|62->40|63->41|63->41|63->41|75->53|75->53|76->54|76->54|76->54|77->55|78->56|78->56|82->60|82->60|84->62|84->62|85->63|86->64|86->64|87->65|87->65|88->66|89->67|89->67|91->69|91->69|92->70|93->71|93->71|137->115|137->115|137->115|167->145|167->145|167->145|169->147|171->149|171->149|171->149|171->149|172->150|172->150|173->151|177->155|179->157|195->173|195->173|195->173|195->173|195->173|196->174|196->174|197->175|197->175|211->189|211->189|211->189|211->189|213->191|213->191|213->191|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|215->193|219->197|223->201|227->206|227->206|228->207|228->207|228->207|229->208|229->208|229->208|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|230->209|231->210|231->210|231->210|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|232->211|233->212|234->213|234->213|235->214|236->215|240->220|240->220|240->220|240->220|241->221|241->221|241->221|242->222|242->222|242->222|243->223|243->223|243->223|244->224|244->224|244->224|245->225|246->226|246->226|247->227|247->227|247->227|248->228|249->229|249->229|249->229|250->230|250->230|250->230|250->230|250->230|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|251->231|257->237|257->237|257->237|258->238|258->238|258->238|258->238|258->238|258->238|258->238|258->238|258->238|259->239|259->239|259->239|259->239|260->240|260->240|261->241|261->241|261->241|262->242|262->242|262->242|263->243|263->243|263->243|264->244|264->244|264->244|265->245|265->245|265->245|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|269->249|271->251|271->251|271->251|272->252|272->252|272->252|273->253|273->253|273->253|274->254|274->254|275->255|275->255|276->256|276->256|277->257|277->257|278->258|280->260|280->260|280->260|280->260|283->263|283->263|284->264|284->264|287->267|288->268|289->269|291->271
                  -- GENERATED --
              */
          