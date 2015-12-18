
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
object ShowStudies extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[scala.Tuple2[scala.Tuple2[String, String], Int]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String, studies: List[((String,String),Int)], report: String = ""):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import java.math.BigInteger; var j =1;

Seq[Any](format.raw/*1.79*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SubjectBook</title>



    <link href="/assets/stylesheets/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/stylesheets/datepicker3.css" rel="stylesheet">
    <link href="/assets/stylesheets/bootstrap-table.css" rel="stylesheet">
    <link href="/assets/stylesheets/styles.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="/assets/javascripts/html5shiv.js"></script>
    <script src="/assets/javascripts/respond.min.js"></script>
    <![endif]-->

    <script src="/assets/javascripts/jquery-1.11.1.min.js"></script>
    <script src="/assets/javascripts/bootstrap.min.js"></script>
    <script src="/assets/javascripts/chart.min.js"></script>
    <script src="/assets/javascripts/bootstrap-datepicker.js"></script>
    <script src="/assets/javascripts/bootstrap-table.js"></script>

    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/funnel.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> """),_display_(/*47.128*/userName),format.raw/*47.136*/(""" """),format.raw/*47.137*/("""<span class="caret"></span></a>
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
        <!-- <li><a href="index.html"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></li>
        <li><a href="widgets.html"><span class="glyphicon glyphicon-th"></span> Widgets</a></li>
        <li class="active"><a href="charts.html"><span class="glyphicon glyphicon-stats"></span> Charts</a></li> -->
        <li><a href="/allStudies"><span class="glyphicon glyphicon-th"></span> My Studies </a></li>
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> New Study</a></li>
        <!-- <li><a href="/showAddDataType"><span class="glyphicon glyphicon-pencil"></span> Add New Data Type</a></li> -->

        <li><a href="/showAllDataTypes"><span class="glyphicon glyphicon-cog"></span> Manage your Data</a></li>
        <li><a href="/showAllPsychometric"><span class="glyphicon glyphicon-cog"></span> Manage your Psychometric data</a></li>
        <!-- <li><a href="#"><span class="glyphicon glyphicon glyphicon-share"></span> Share study</a></li> -->
        <!-- <li><a href="panels.html"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li> -->
        <li role="presentation" class="divider"></li>
        <!-- <li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Login Page</a></li> -->
    </ul>
    <!-- <div class="attribution">Template by <a href="http://www.medialoot.com/item/lumino-admin-bootstrap-template/">Medialoot</a></div> -->
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Studies</h1>
        </div>
    </div><!--/.row-->


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">Your Studies</div>



                <div class="panel-body">
                    <table data-toggle="table"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="names" data-sort-order="desc">
                        <thead>
                        <tr>
                            <th data-field="state" data-checkbox="true" >Item ID</th>
                            <th data-field="name" data-sortable="false">Study Name</th>
                            <!-- <th data-field="map"  data-sortable="false">Study Map</th> -->
                            <th data-field="price" data-sortable="true">Owner</th>
                            <th data-field="create" data-sortable="true">Create Summary</th>
                            <th data-field="export" data-sortable="true">Export</th>
                            <th data-field="delete" data-sortable="true">Delete</th>

                        </tr>
                        </thead>
                        <tbody>

                        """),format.raw/*122.1*/("""                        """),_display_(/*122.26*/for(study <- studies) yield /*122.47*/ {_display_(Seq[Any](format.raw/*122.49*/("""
                        """),format.raw/*123.25*/("""<tr class="table_row_odd table_row_first">
                            <td> </td>
                            <td><a href="/showPyramid?studyNo="""),_display_(/*125.64*/study/*125.69*/._2),format.raw/*125.72*/(""""><span>"""),_display_(/*125.81*/study/*125.86*/._1._1),format.raw/*125.92*/("""</span></a> </td>
                            <!-- <td>  <div class="pyramid"  id="container"""),_display_(/*126.76*/j),format.raw/*126.77*/("""" studyNum="""),_display_(/*126.89*/study/*126.94*/._2),format.raw/*126.97*/(""" """),format.raw/*126.98*/("""style="min-width: 180px; max-width: 200px; height: 140px; margin: 0 auto"></div> </td> -->
                            """),_display_(/*127.30*/{j = j + 1;}),format.raw/*127.42*/("""
                            """),format.raw/*128.29*/("""<td> """),_display_(/*128.35*/study/*128.40*/._1._2),format.raw/*128.46*/(""" """),format.raw/*128.47*/("""</td>
                            <td>
                                <a href="/showAddTopSummary?studyId="""),_display_(/*130.70*/study/*130.75*/._2),format.raw/*130.78*/(""""><span>Create Summary</span></a>
                            </td>


                            <td><a href="/downloadStudy?studyId="""),_display_(/*134.66*/study/*134.71*/._2),format.raw/*134.74*/("""" class="export">Export</a></td>


                            <td>
                            <form action="/deleteStudy" method="POST" enctype="multipart/form-data" onclick="return confirm('Are you sure you want to delete this item?');">
                            <input type="hidden" name="study_id" id="study_id" value= """),_display_(/*139.88*/study/*139.93*/._2),format.raw/*139.96*/(""">
                            """),_display_(/*140.30*/if(study._1._2 == userName)/*140.57*/ {_display_(Seq[Any](format.raw/*140.59*/("""

                             """),format.raw/*142.30*/("""<input type="submit" name="submit" value="delete">

                            """)))}),format.raw/*144.30*/("""
                            """),format.raw/*145.29*/("""</form>
                            </td>



                        </tr>
                        """)))}),format.raw/*151.26*/("""

                        """),format.raw/*153.25*/("""</tbody>
                    </table>
                </div>
            </div>
        </div>
    </div><!--/.row-->


</div><!--/.main-->

<div id="loading" style="display:none; position: fixed;height:100%; width:100%; background: rgba( 255, 255, 255, .8 ) url('/assets/images/ajax-loader.gif') 50% 50%    no-repeat;">
    <p> Please wait, study is being created </p>
</div>
<script>


$(document).ready(function()"""),format.raw/*169.29*/("""{"""),format.raw/*169.30*/("""


   """),format.raw/*172.4*/("""Highcharts.theme = """),format.raw/*172.23*/("""{"""),format.raw/*172.24*/("""
   """),format.raw/*173.4*/("""colors: ["#55BF3B", "#888888", "#DF5353"]
"""),format.raw/*174.1*/("""}"""),format.raw/*174.2*/(""";
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

$(".pyramid" ).each(function( index ) """),format.raw/*177.39*/("""{"""),format.raw/*177.40*/("""

"""),format.raw/*179.1*/("""var stdNo= $(this).attr('studyNum');


var name = "#container" + stdNo

 $(this).highcharts("""),format.raw/*184.21*/("""{"""),format.raw/*184.22*/("""
                """),format.raw/*185.17*/("""chart: """),format.raw/*185.24*/("""{"""),format.raw/*185.25*/("""
                    """),format.raw/*186.21*/("""type: 'pyramid',
                    marginRight: 10
                """),format.raw/*188.17*/("""}"""),format.raw/*188.18*/(""",
                title: """),format.raw/*189.24*/("""{"""),format.raw/*189.25*/("""
                    """),format.raw/*190.21*/("""text: '',
                    x: -50
                """),format.raw/*192.17*/("""}"""),format.raw/*192.18*/(""",
                tooltip: """),format.raw/*193.26*/("""{"""),format.raw/*193.27*/("""
            """),format.raw/*194.13*/("""enabled: false
        """),format.raw/*195.9*/("""}"""),format.raw/*195.10*/(""",
                plotOptions: """),format.raw/*196.30*/("""{"""),format.raw/*196.31*/("""
                        """),format.raw/*197.25*/("""series: """),format.raw/*197.33*/("""{"""),format.raw/*197.34*/("""
                           """),format.raw/*198.28*/("""dataLabels: """),format.raw/*198.40*/("""{"""),format.raw/*198.41*/("""
                                """),format.raw/*199.33*/("""enabled: false
                                """),format.raw/*200.33*/("""}"""),format.raw/*200.34*/(""",
                            cursor: 'pointer',
                            point: """),format.raw/*202.36*/("""{"""),format.raw/*202.37*/("""
                                """),format.raw/*203.33*/("""events: """),format.raw/*203.41*/("""{"""),format.raw/*203.42*/("""
                                    """),format.raw/*204.37*/("""click: function (e) """),format.raw/*204.57*/("""{"""),format.raw/*204.58*/("""
                                       """),format.raw/*205.40*/("""// var temp = this.series.data


                                        if(this.x == 2)"""),format.raw/*208.56*/("""{"""),format.raw/*208.57*/("""
                                           """),format.raw/*209.44*/("""window.location = '/showStudy/' + stdNo
                                           """),format.raw/*210.44*/("""}"""),format.raw/*210.45*/("""
                                         """),format.raw/*211.42*/("""else if(this.x ==1)
                                           window.location = '/showStudySkipTop/'+ stdNo
                                         else
                                          window.location = '/displaySubject?studyNo=' + stdNo + '&SubjectId='
                                    """),format.raw/*215.37*/("""}"""),format.raw/*215.38*/("""
                                """),format.raw/*216.33*/("""}"""),format.raw/*216.34*/("""
                            """),format.raw/*217.29*/("""}"""),format.raw/*217.30*/(""",
                            marker: """),format.raw/*218.37*/("""{"""),format.raw/*218.38*/("""
                                """),format.raw/*219.33*/("""lineWidth: 1
                            """),format.raw/*220.29*/("""}"""),format.raw/*220.30*/("""
                        """),format.raw/*221.25*/("""}"""),format.raw/*221.26*/("""
                """),format.raw/*222.17*/("""}"""),format.raw/*222.18*/(""",
                 credits: """),format.raw/*223.27*/("""{"""),format.raw/*223.28*/("""
                  """),format.raw/*224.19*/("""enabled: false
              """),format.raw/*225.15*/("""}"""),format.raw/*225.16*/(""",
                legend: """),format.raw/*226.25*/("""{"""),format.raw/*226.26*/("""
                    """),format.raw/*227.21*/("""enabled: false
                """),format.raw/*228.17*/("""}"""),format.raw/*228.18*/(""",
                exporting: """),format.raw/*229.28*/("""{"""),format.raw/*229.29*/(""" """),format.raw/*229.30*/("""enabled: false """),format.raw/*229.45*/("""}"""),format.raw/*229.46*/(""" """),format.raw/*229.47*/(""",
                series: ["""),format.raw/*230.26*/("""{"""),format.raw/*230.27*/("""
                    """),format.raw/*231.21*/("""name: 'Unique users',
                    data: [
                        ['Session view',   100],
                        ['User Portrait',       100],
                        ['Top Summary', 100],
                    ]
                """),format.raw/*237.17*/("""}"""),format.raw/*237.18*/("""]
            """),format.raw/*238.13*/("""}"""),format.raw/*238.14*/(""");

"""),format.raw/*240.1*/("""}"""),format.raw/*240.2*/(""");


   $('.export').click(function() """),format.raw/*243.34*/("""{"""),format.raw/*243.35*/("""
           """),format.raw/*244.12*/("""var link= $(this).attr('getLink');
           // alert(link);
          // Id of the element that was clicked
         $("#loading").show();

         $("#loading").hide();
    """),format.raw/*250.5*/("""}"""),format.raw/*250.6*/(""");
 """),format.raw/*251.2*/("""}"""),format.raw/*251.3*/(""");

</script>
<script>
		!function ($) """),format.raw/*255.17*/("""{"""),format.raw/*255.18*/("""
			"""),format.raw/*256.4*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*256.73*/("""{"""),format.raw/*256.74*/("""		  
				"""),format.raw/*257.5*/("""$(this).find('em:first').toggleClass("glyphicon-minus");	  
			"""),format.raw/*258.4*/("""}"""),format.raw/*258.5*/("""); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*260.3*/("""}"""),format.raw/*260.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*262.38*/("""{"""),format.raw/*262.39*/("""
		  """),format.raw/*263.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*264.3*/("""}"""),format.raw/*264.4*/(""")
		$(window).on('resize', function () """),format.raw/*265.38*/("""{"""),format.raw/*265.39*/("""
		  """),format.raw/*266.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*267.3*/("""}"""),format.raw/*267.4*/(""")
	</script>
</body>

</html>
"""))}
  }

  def render(userName:String,studies:List[scala.Tuple2[scala.Tuple2[String, String], Int]],report:String): play.twirl.api.HtmlFormat.Appendable = apply(userName,studies,report)

  def f:((String,List[scala.Tuple2[scala.Tuple2[String, String], Int]],String) => play.twirl.api.HtmlFormat.Appendable) = (userName,studies,report) => apply(userName,studies,report)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri Oct 30 12:26:00 CDT 2015
                  SOURCE: C:/first_play/app/views/ShowStudies.scala.html
                  HASH: 4574e9f0daf52180de4b93f1344213051e99d358
                  MATRIX: 572->1|775->78|803->80|3048->2297|3078->2305|3108->2306|7093->6304|7146->6329|7184->6350|7225->6352|7280->6378|7455->6525|7470->6530|7495->6533|7532->6542|7547->6547|7575->6553|7697->6647|7720->6648|7760->6660|7775->6665|7800->6668|7830->6669|7979->6790|8013->6802|8072->6832|8106->6838|8121->6843|8149->6849|8179->6850|8317->6960|8332->6965|8357->6968|8524->7107|8539->7112|8564->7115|8925->7448|8940->7453|8965->7456|9025->7488|9062->7515|9103->7517|9165->7550|9280->7633|9339->7663|9477->7769|9534->7797|9995->8229|10025->8230|10062->8239|10110->8258|10140->8259|10173->8264|10244->8307|10273->8308|10411->8417|10441->8418|10473->8422|10599->8519|10629->8520|10676->8538|10712->8545|10742->8546|10793->8568|10893->8639|10923->8640|10978->8666|11008->8667|11059->8689|11143->8744|11173->8745|11230->8773|11260->8774|11303->8788|11355->8812|11385->8813|11446->8845|11476->8846|11531->8872|11568->8880|11598->8881|11656->8910|11697->8922|11727->8923|11790->8957|11867->9005|11897->9006|12012->9092|12042->9093|12105->9127|12142->9135|12172->9136|12239->9174|12288->9194|12318->9195|12388->9236|12508->9327|12538->9328|12612->9373|12725->9457|12755->9458|12827->9501|13162->9807|13192->9808|13255->9842|13285->9843|13344->9873|13374->9874|13442->9913|13472->9914|13535->9948|13606->9990|13636->9991|13691->10017|13721->10018|13768->10036|13798->10037|13856->10066|13886->10067|13935->10087|13994->10117|14024->10118|14080->10145|14110->10146|14161->10168|14222->10200|14252->10201|14311->10231|14341->10232|14371->10233|14415->10248|14445->10249|14475->10250|14532->10278|14562->10279|14613->10301|14885->10544|14915->10545|14959->10560|14989->10561|15023->10567|15052->10568|15122->10609|15152->10610|15194->10623|15405->10806|15434->10807|15467->10812|15496->10813|15568->10856|15598->10857|15631->10862|15729->10931|15759->10932|15797->10942|15889->11006|15918->11007|16026->11087|16055->11088|16141->11145|16171->11146|16205->11152|16305->11224|16334->11225|16403->11265|16433->11266|16467->11272|16568->11345|16597->11346
                  LINES: 19->1|22->1|23->2|68->47|68->47|68->47|142->122|142->122|142->122|142->122|143->123|145->125|145->125|145->125|145->125|145->125|145->125|146->126|146->126|146->126|146->126|146->126|146->126|147->127|147->127|148->128|148->128|148->128|148->128|148->128|150->130|150->130|150->130|154->134|154->134|154->134|159->139|159->139|159->139|160->140|160->140|160->140|162->142|164->144|165->145|171->151|173->153|189->169|189->169|192->172|192->172|192->172|193->173|194->174|194->174|197->177|197->177|199->179|204->184|204->184|205->185|205->185|205->185|206->186|208->188|208->188|209->189|209->189|210->190|212->192|212->192|213->193|213->193|214->194|215->195|215->195|216->196|216->196|217->197|217->197|217->197|218->198|218->198|218->198|219->199|220->200|220->200|222->202|222->202|223->203|223->203|223->203|224->204|224->204|224->204|225->205|228->208|228->208|229->209|230->210|230->210|231->211|235->215|235->215|236->216|236->216|237->217|237->217|238->218|238->218|239->219|240->220|240->220|241->221|241->221|242->222|242->222|243->223|243->223|244->224|245->225|245->225|246->226|246->226|247->227|248->228|248->228|249->229|249->229|249->229|249->229|249->229|249->229|250->230|250->230|251->231|257->237|257->237|258->238|258->238|260->240|260->240|263->243|263->243|264->244|270->250|270->250|271->251|271->251|275->255|275->255|276->256|276->256|276->256|277->257|278->258|278->258|280->260|280->260|282->262|282->262|283->263|284->264|284->264|285->265|285->265|286->266|287->267|287->267
                  -- GENERATED --
              */
          