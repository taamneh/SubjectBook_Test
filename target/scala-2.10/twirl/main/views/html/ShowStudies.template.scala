
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
            <!-- <a class="navbar-brand" href="#"><span></span>Share</a> -->
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
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> Visualize an Existing Study</a></li>
        <li><a href="/InitiateStudy"><span class="glyphicon glyphicon-pencil"></span> Initiate a study</a></li>
        <!-- <li><a href="/showAddDataType"><span class="glyphicon glyphicon-pencil"></span> Add New Data Type</a></li> -->

        <li><a href="/showAllDataTypes"><span class="glyphicon glyphicon-cog"></span> Manage your Data</a></li>
        <li><a href="/showAllPsychometric"><span class="glyphicon glyphicon-cog"></span> Manage your Psychometric data</a></li>
        <li><a href="/realTime"><span class="glyphicon glyphicon-cog"></span>Real-time Streaming</a></li>
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
                           <!-- <th data-field="create" data-sortable="true">Create Summary</th> -->
                            <th data-field="edit" data-sortable="true">Edit</th>
                            <th data-field="export" data-sortable="true">Export</th>
                            <th data-field="delete" data-sortable="true">Delete</th>

                        </tr>
                        </thead>
                        <tbody>

                        """),format.raw/*125.1*/("""                        """),_display_(/*125.26*/for(study <- studies) yield /*125.47*/ {_display_(Seq[Any](format.raw/*125.49*/("""
                        """),format.raw/*126.25*/("""<tr class="table_row_odd table_row_first">
                            <td> </td>
                            <td><a href="/showPyramid?studyNo="""),_display_(/*128.64*/study/*128.69*/._2),format.raw/*128.72*/(""""><span>"""),_display_(/*128.81*/study/*128.86*/._1._1),format.raw/*128.92*/("""</span></a> </td>
                            <!-- <td>  <div class="pyramid"  id="container"""),_display_(/*129.76*/j),format.raw/*129.77*/("""" studyNum="""),_display_(/*129.89*/study/*129.94*/._2),format.raw/*129.97*/(""" """),format.raw/*129.98*/("""style="min-width: 180px; max-width: 200px; height: 140px; margin: 0 auto"></div> </td> -->
                            """),_display_(/*130.30*/{j = j + 1;}),format.raw/*130.42*/("""
                            """),format.raw/*131.29*/("""<td> """),_display_(/*131.35*/study/*131.40*/._1._2),format.raw/*131.46*/(""" """),format.raw/*131.47*/("""</td>
                            <!-- <td> <a href="/showAddTopSummary?studyId="""),_display_(/*132.76*/study/*132.81*/._2),format.raw/*132.84*/(""""><span>Create Summary</span></a> </td> -->


                            <td>
                                <form action="/showAllSubjectsHide" method="GET" enctype="multipart/form-data">
                                    <input type="hidden" name="studyId" id="studyId" value= """),_display_(/*137.94*/study/*137.99*/._2),format.raw/*137.102*/(""">
                                    """),_display_(/*138.38*/if(study._1._2 == userName)/*138.65*/ {_display_(Seq[Any](format.raw/*138.67*/("""

                                    """),format.raw/*140.37*/("""<input type="submit" name="Edit" value="Edit">

                                    """)))}),format.raw/*142.38*/("""
                                """),format.raw/*143.33*/("""</form>
                            </td>

                            <!-- <td><a href="/downloadStudy?studyId="""),_display_(/*146.71*/study/*146.76*/._2),format.raw/*146.79*/("""" class="export">Export</a></td> -->
                            <td><a href="/drp?studyNo="""),_display_(/*147.56*/study/*147.61*/._2),format.raw/*147.64*/("""" class="export">Export</a></td>



                            <td>
                            <form action="/deleteStudy" method="POST" enctype="multipart/form-data" onclick="return confirm('Are you sure you want to delete this item?');">
                            <input type="hidden" name="study_id" id="study_id" value= """),_display_(/*153.88*/study/*153.93*/._2),format.raw/*153.96*/(""">
                            """),_display_(/*154.30*/if(study._1._2 == userName && userName != "sim2dc@gmail.com")/*154.91*/ {_display_(Seq[Any](format.raw/*154.93*/("""

                             """),format.raw/*156.30*/("""<input type="submit" name="submit" value="delete">

                            """)))}),format.raw/*158.30*/("""
                            """),format.raw/*159.29*/("""</form>
                            </td>

                           <!-- <td>
                                <form action="/addNewSubject" method="GET" enctype="multipart/form-data">
                                    <input type="hidden" name="studyNo" id="studyNo" value= """),_display_(/*164.94*/study/*164.99*/._2),format.raw/*164.102*/(""">
                                    """),_display_(/*165.38*/if(study._1._2 == userName)/*165.65*/ {_display_(Seq[Any](format.raw/*165.67*/("""

                                    """),format.raw/*167.37*/("""<input type="submit" name="Edit" value="Edit2">

                                    """)))}),format.raw/*169.38*/("""
                                """),format.raw/*170.33*/("""</form>
                            </td> -->





                        </tr>
                        """)))}),format.raw/*178.26*/("""

                        """),format.raw/*180.25*/("""</tbody>
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


$(document).ready(function()"""),format.raw/*196.29*/("""{"""),format.raw/*196.30*/("""


   """),format.raw/*199.4*/("""Highcharts.theme = """),format.raw/*199.23*/("""{"""),format.raw/*199.24*/("""
   """),format.raw/*200.4*/("""colors: ["#55BF3B", "#888888", "#DF5353"]
"""),format.raw/*201.1*/("""}"""),format.raw/*201.2*/(""";
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

$(".pyramid" ).each(function( index ) """),format.raw/*204.39*/("""{"""),format.raw/*204.40*/("""

"""),format.raw/*206.1*/("""var stdNo= $(this).attr('studyNum');


var name = "#container" + stdNo

 $(this).highcharts("""),format.raw/*211.21*/("""{"""),format.raw/*211.22*/("""
                """),format.raw/*212.17*/("""chart: """),format.raw/*212.24*/("""{"""),format.raw/*212.25*/("""
                    """),format.raw/*213.21*/("""type: 'pyramid',
                    marginRight: 10
                """),format.raw/*215.17*/("""}"""),format.raw/*215.18*/(""",
                title: """),format.raw/*216.24*/("""{"""),format.raw/*216.25*/("""
                    """),format.raw/*217.21*/("""text: '',
                    x: -50
                """),format.raw/*219.17*/("""}"""),format.raw/*219.18*/(""",
                tooltip: """),format.raw/*220.26*/("""{"""),format.raw/*220.27*/("""
            """),format.raw/*221.13*/("""enabled: false
        """),format.raw/*222.9*/("""}"""),format.raw/*222.10*/(""",
                plotOptions: """),format.raw/*223.30*/("""{"""),format.raw/*223.31*/("""
                        """),format.raw/*224.25*/("""series: """),format.raw/*224.33*/("""{"""),format.raw/*224.34*/("""
                           """),format.raw/*225.28*/("""dataLabels: """),format.raw/*225.40*/("""{"""),format.raw/*225.41*/("""
                                """),format.raw/*226.33*/("""enabled: false
                                """),format.raw/*227.33*/("""}"""),format.raw/*227.34*/(""",
                            cursor: 'pointer',
                            point: """),format.raw/*229.36*/("""{"""),format.raw/*229.37*/("""
                                """),format.raw/*230.33*/("""events: """),format.raw/*230.41*/("""{"""),format.raw/*230.42*/("""
                                    """),format.raw/*231.37*/("""click: function (e) """),format.raw/*231.57*/("""{"""),format.raw/*231.58*/("""
                                       """),format.raw/*232.40*/("""// var temp = this.series.data


                                        if(this.x == 2)"""),format.raw/*235.56*/("""{"""),format.raw/*235.57*/("""
                                           """),format.raw/*236.44*/("""window.location = '/showStudy/' + stdNo
                                           """),format.raw/*237.44*/("""}"""),format.raw/*237.45*/("""
                                         """),format.raw/*238.42*/("""else if(this.x ==1)
                                           window.location = '/showStudySkipTop/'+ stdNo
                                         else
                                          window.location = '/displaySubject?studyNo=' + stdNo + '&SubjectId='
                                    """),format.raw/*242.37*/("""}"""),format.raw/*242.38*/("""
                                """),format.raw/*243.33*/("""}"""),format.raw/*243.34*/("""
                            """),format.raw/*244.29*/("""}"""),format.raw/*244.30*/(""",
                            marker: """),format.raw/*245.37*/("""{"""),format.raw/*245.38*/("""
                                """),format.raw/*246.33*/("""lineWidth: 1
                            """),format.raw/*247.29*/("""}"""),format.raw/*247.30*/("""
                        """),format.raw/*248.25*/("""}"""),format.raw/*248.26*/("""
                """),format.raw/*249.17*/("""}"""),format.raw/*249.18*/(""",
                 credits: """),format.raw/*250.27*/("""{"""),format.raw/*250.28*/("""
                  """),format.raw/*251.19*/("""enabled: false
              """),format.raw/*252.15*/("""}"""),format.raw/*252.16*/(""",
                legend: """),format.raw/*253.25*/("""{"""),format.raw/*253.26*/("""
                    """),format.raw/*254.21*/("""enabled: false
                """),format.raw/*255.17*/("""}"""),format.raw/*255.18*/(""",
                exporting: """),format.raw/*256.28*/("""{"""),format.raw/*256.29*/(""" """),format.raw/*256.30*/("""enabled: false """),format.raw/*256.45*/("""}"""),format.raw/*256.46*/(""" """),format.raw/*256.47*/(""",
                series: ["""),format.raw/*257.26*/("""{"""),format.raw/*257.27*/("""
                    """),format.raw/*258.21*/("""name: 'Unique users',
                    data: [
                        ['Session view',   100],
                        ['User Portrait',       100],
                        ['Top Summary', 100],
                    ]
                """),format.raw/*264.17*/("""}"""),format.raw/*264.18*/("""]
            """),format.raw/*265.13*/("""}"""),format.raw/*265.14*/(""");

"""),format.raw/*267.1*/("""}"""),format.raw/*267.2*/(""");


   $('.export').click(function() """),format.raw/*270.34*/("""{"""),format.raw/*270.35*/("""
           """),format.raw/*271.12*/("""var link= $(this).attr('getLink');
           // alert(link);
          // Id of the element that was clicked
         $("#loading").show();

         $("#loading").hide();
    """),format.raw/*277.5*/("""}"""),format.raw/*277.6*/(""");
 """),format.raw/*278.2*/("""}"""),format.raw/*278.3*/(""");

</script>
<script>
		!function ($) """),format.raw/*282.17*/("""{"""),format.raw/*282.18*/("""
			"""),format.raw/*283.4*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*283.73*/("""{"""),format.raw/*283.74*/("""		  
				"""),format.raw/*284.5*/("""$(this).find('em:first').toggleClass("glyphicon-minus");	  
			"""),format.raw/*285.4*/("""}"""),format.raw/*285.5*/("""); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*287.3*/("""}"""),format.raw/*287.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*289.38*/("""{"""),format.raw/*289.39*/("""
		  """),format.raw/*290.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*291.3*/("""}"""),format.raw/*291.4*/(""")
		$(window).on('resize', function () """),format.raw/*292.38*/("""{"""),format.raw/*292.39*/("""
		  """),format.raw/*293.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*294.3*/("""}"""),format.raw/*294.4*/(""")
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
                  DATE: Thu Sep 01 18:29:22 CDT 2016
                  SOURCE: C:/first_play/app/views/ShowStudies.scala.html
                  HASH: c123122b662f4adc0294ae1f490ce82820a5f526
                  MATRIX: 572->1|775->78|803->80|3057->2306|3087->2314|3117->2315|7430->6641|7483->6666|7521->6687|7562->6689|7617->6715|7792->6862|7807->6867|7832->6870|7869->6879|7884->6884|7912->6890|8034->6984|8057->6985|8097->6997|8112->7002|8137->7005|8167->7006|8316->7127|8350->7139|8409->7169|8443->7175|8458->7180|8486->7186|8516->7187|8626->7269|8641->7274|8666->7277|8983->7566|8998->7571|9024->7574|9092->7614|9129->7641|9170->7643|9239->7683|9358->7770|9421->7804|9565->7920|9580->7925|9605->7928|9726->8021|9741->8026|9766->8029|10129->8364|10144->8369|10169->8372|10229->8404|10300->8465|10341->8467|10403->8500|10518->8583|10577->8613|10889->8897|10904->8902|10930->8905|10998->8945|11035->8972|11076->8974|11145->9014|11265->9102|11328->9136|11474->9250|11531->9278|11992->9710|12022->9711|12059->9720|12107->9739|12137->9740|12170->9745|12241->9788|12270->9789|12408->9898|12438->9899|12470->9903|12596->10000|12626->10001|12673->10019|12709->10026|12739->10027|12790->10049|12890->10120|12920->10121|12975->10147|13005->10148|13056->10170|13140->10225|13170->10226|13227->10254|13257->10255|13300->10269|13352->10293|13382->10294|13443->10326|13473->10327|13528->10353|13565->10361|13595->10362|13653->10391|13694->10403|13724->10404|13787->10438|13864->10486|13894->10487|14009->10573|14039->10574|14102->10608|14139->10616|14169->10617|14236->10655|14285->10675|14315->10676|14385->10717|14505->10808|14535->10809|14609->10854|14722->10938|14752->10939|14824->10982|15159->11288|15189->11289|15252->11323|15282->11324|15341->11354|15371->11355|15439->11394|15469->11395|15532->11429|15603->11471|15633->11472|15688->11498|15718->11499|15765->11517|15795->11518|15853->11547|15883->11548|15932->11568|15991->11598|16021->11599|16077->11626|16107->11627|16158->11649|16219->11681|16249->11682|16308->11712|16338->11713|16368->11714|16412->11729|16442->11730|16472->11731|16529->11759|16559->11760|16610->11782|16882->12025|16912->12026|16956->12041|16986->12042|17020->12048|17049->12049|17119->12090|17149->12091|17191->12104|17402->12287|17431->12288|17464->12293|17493->12294|17565->12337|17595->12338|17628->12343|17726->12412|17756->12413|17794->12423|17886->12487|17915->12488|18023->12568|18052->12569|18138->12626|18168->12627|18202->12633|18302->12705|18331->12706|18400->12746|18430->12747|18464->12753|18565->12826|18594->12827
                  LINES: 19->1|22->1|23->2|68->47|68->47|68->47|145->125|145->125|145->125|145->125|146->126|148->128|148->128|148->128|148->128|148->128|148->128|149->129|149->129|149->129|149->129|149->129|149->129|150->130|150->130|151->131|151->131|151->131|151->131|151->131|152->132|152->132|152->132|157->137|157->137|157->137|158->138|158->138|158->138|160->140|162->142|163->143|166->146|166->146|166->146|167->147|167->147|167->147|173->153|173->153|173->153|174->154|174->154|174->154|176->156|178->158|179->159|184->164|184->164|184->164|185->165|185->165|185->165|187->167|189->169|190->170|198->178|200->180|216->196|216->196|219->199|219->199|219->199|220->200|221->201|221->201|224->204|224->204|226->206|231->211|231->211|232->212|232->212|232->212|233->213|235->215|235->215|236->216|236->216|237->217|239->219|239->219|240->220|240->220|241->221|242->222|242->222|243->223|243->223|244->224|244->224|244->224|245->225|245->225|245->225|246->226|247->227|247->227|249->229|249->229|250->230|250->230|250->230|251->231|251->231|251->231|252->232|255->235|255->235|256->236|257->237|257->237|258->238|262->242|262->242|263->243|263->243|264->244|264->244|265->245|265->245|266->246|267->247|267->247|268->248|268->248|269->249|269->249|270->250|270->250|271->251|272->252|272->252|273->253|273->253|274->254|275->255|275->255|276->256|276->256|276->256|276->256|276->256|276->256|277->257|277->257|278->258|284->264|284->264|285->265|285->265|287->267|287->267|290->270|290->270|291->271|297->277|297->277|298->278|298->278|302->282|302->282|303->283|303->283|303->283|304->284|305->285|305->285|307->287|307->287|309->289|309->289|310->290|311->291|311->291|312->292|312->292|313->293|314->294|314->294
                  -- GENERATED --
              */
          