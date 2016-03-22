
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
        <li><a href="/createStudy"><span class="glyphicon glyphicon-pencil"></span> New Study</a></li>
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

                        """),format.raw/*124.1*/("""                        """),_display_(/*124.26*/for(study <- studies) yield /*124.47*/ {_display_(Seq[Any](format.raw/*124.49*/("""
                        """),format.raw/*125.25*/("""<tr class="table_row_odd table_row_first">
                            <td> </td>
                            <td><a href="/showPyramid?studyNo="""),_display_(/*127.64*/study/*127.69*/._2),format.raw/*127.72*/(""""><span>"""),_display_(/*127.81*/study/*127.86*/._1._1),format.raw/*127.92*/("""</span></a> </td>
                            <!-- <td>  <div class="pyramid"  id="container"""),_display_(/*128.76*/j),format.raw/*128.77*/("""" studyNum="""),_display_(/*128.89*/study/*128.94*/._2),format.raw/*128.97*/(""" """),format.raw/*128.98*/("""style="min-width: 180px; max-width: 200px; height: 140px; margin: 0 auto"></div> </td> -->
                            """),_display_(/*129.30*/{j = j + 1;}),format.raw/*129.42*/("""
                            """),format.raw/*130.29*/("""<td> """),_display_(/*130.35*/study/*130.40*/._1._2),format.raw/*130.46*/(""" """),format.raw/*130.47*/("""</td>
                            <!-- <td> <a href="/showAddTopSummary?studyId="""),_display_(/*131.76*/study/*131.81*/._2),format.raw/*131.84*/(""""><span>Create Summary</span></a> </td> -->


                            <td>
                                <form action="/showAllSubjectsHide" method="GET" enctype="multipart/form-data">
                                    <input type="hidden" name="studyId" id="studyId" value= """),_display_(/*136.94*/study/*136.99*/._2),format.raw/*136.102*/(""">
                                    """),_display_(/*137.38*/if(study._1._2 == userName)/*137.65*/ {_display_(Seq[Any](format.raw/*137.67*/("""

                                    """),format.raw/*139.37*/("""<input type="submit" name="Edit" value="Edit">

                                    """)))}),format.raw/*141.38*/("""
                                """),format.raw/*142.33*/("""</form>
                            </td>

                            <td><a href="/downloadStudy?studyId="""),_display_(/*145.66*/study/*145.71*/._2),format.raw/*145.74*/("""" class="export">Export</a></td>


                            <td>
                            <form action="/deleteStudy" method="POST" enctype="multipart/form-data" onclick="return confirm('Are you sure you want to delete this item?');">
                            <input type="hidden" name="study_id" id="study_id" value= """),_display_(/*150.88*/study/*150.93*/._2),format.raw/*150.96*/(""">
                            """),_display_(/*151.30*/if(study._1._2 == userName)/*151.57*/ {_display_(Seq[Any](format.raw/*151.59*/("""

                             """),format.raw/*153.30*/("""<input type="submit" name="submit" value="delete">

                            """)))}),format.raw/*155.30*/("""
                            """),format.raw/*156.29*/("""</form>
                            </td>



                        </tr>
                        """)))}),format.raw/*162.26*/("""

                        """),format.raw/*164.25*/("""</tbody>
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


$(document).ready(function()"""),format.raw/*180.29*/("""{"""),format.raw/*180.30*/("""


   """),format.raw/*183.4*/("""Highcharts.theme = """),format.raw/*183.23*/("""{"""),format.raw/*183.24*/("""
   """),format.raw/*184.4*/("""colors: ["#55BF3B", "#888888", "#DF5353"]
"""),format.raw/*185.1*/("""}"""),format.raw/*185.2*/(""";
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

$(".pyramid" ).each(function( index ) """),format.raw/*188.39*/("""{"""),format.raw/*188.40*/("""

"""),format.raw/*190.1*/("""var stdNo= $(this).attr('studyNum');


var name = "#container" + stdNo

 $(this).highcharts("""),format.raw/*195.21*/("""{"""),format.raw/*195.22*/("""
                """),format.raw/*196.17*/("""chart: """),format.raw/*196.24*/("""{"""),format.raw/*196.25*/("""
                    """),format.raw/*197.21*/("""type: 'pyramid',
                    marginRight: 10
                """),format.raw/*199.17*/("""}"""),format.raw/*199.18*/(""",
                title: """),format.raw/*200.24*/("""{"""),format.raw/*200.25*/("""
                    """),format.raw/*201.21*/("""text: '',
                    x: -50
                """),format.raw/*203.17*/("""}"""),format.raw/*203.18*/(""",
                tooltip: """),format.raw/*204.26*/("""{"""),format.raw/*204.27*/("""
            """),format.raw/*205.13*/("""enabled: false
        """),format.raw/*206.9*/("""}"""),format.raw/*206.10*/(""",
                plotOptions: """),format.raw/*207.30*/("""{"""),format.raw/*207.31*/("""
                        """),format.raw/*208.25*/("""series: """),format.raw/*208.33*/("""{"""),format.raw/*208.34*/("""
                           """),format.raw/*209.28*/("""dataLabels: """),format.raw/*209.40*/("""{"""),format.raw/*209.41*/("""
                                """),format.raw/*210.33*/("""enabled: false
                                """),format.raw/*211.33*/("""}"""),format.raw/*211.34*/(""",
                            cursor: 'pointer',
                            point: """),format.raw/*213.36*/("""{"""),format.raw/*213.37*/("""
                                """),format.raw/*214.33*/("""events: """),format.raw/*214.41*/("""{"""),format.raw/*214.42*/("""
                                    """),format.raw/*215.37*/("""click: function (e) """),format.raw/*215.57*/("""{"""),format.raw/*215.58*/("""
                                       """),format.raw/*216.40*/("""// var temp = this.series.data


                                        if(this.x == 2)"""),format.raw/*219.56*/("""{"""),format.raw/*219.57*/("""
                                           """),format.raw/*220.44*/("""window.location = '/showStudy/' + stdNo
                                           """),format.raw/*221.44*/("""}"""),format.raw/*221.45*/("""
                                         """),format.raw/*222.42*/("""else if(this.x ==1)
                                           window.location = '/showStudySkipTop/'+ stdNo
                                         else
                                          window.location = '/displaySubject?studyNo=' + stdNo + '&SubjectId='
                                    """),format.raw/*226.37*/("""}"""),format.raw/*226.38*/("""
                                """),format.raw/*227.33*/("""}"""),format.raw/*227.34*/("""
                            """),format.raw/*228.29*/("""}"""),format.raw/*228.30*/(""",
                            marker: """),format.raw/*229.37*/("""{"""),format.raw/*229.38*/("""
                                """),format.raw/*230.33*/("""lineWidth: 1
                            """),format.raw/*231.29*/("""}"""),format.raw/*231.30*/("""
                        """),format.raw/*232.25*/("""}"""),format.raw/*232.26*/("""
                """),format.raw/*233.17*/("""}"""),format.raw/*233.18*/(""",
                 credits: """),format.raw/*234.27*/("""{"""),format.raw/*234.28*/("""
                  """),format.raw/*235.19*/("""enabled: false
              """),format.raw/*236.15*/("""}"""),format.raw/*236.16*/(""",
                legend: """),format.raw/*237.25*/("""{"""),format.raw/*237.26*/("""
                    """),format.raw/*238.21*/("""enabled: false
                """),format.raw/*239.17*/("""}"""),format.raw/*239.18*/(""",
                exporting: """),format.raw/*240.28*/("""{"""),format.raw/*240.29*/(""" """),format.raw/*240.30*/("""enabled: false """),format.raw/*240.45*/("""}"""),format.raw/*240.46*/(""" """),format.raw/*240.47*/(""",
                series: ["""),format.raw/*241.26*/("""{"""),format.raw/*241.27*/("""
                    """),format.raw/*242.21*/("""name: 'Unique users',
                    data: [
                        ['Session view',   100],
                        ['User Portrait',       100],
                        ['Top Summary', 100],
                    ]
                """),format.raw/*248.17*/("""}"""),format.raw/*248.18*/("""]
            """),format.raw/*249.13*/("""}"""),format.raw/*249.14*/(""");

"""),format.raw/*251.1*/("""}"""),format.raw/*251.2*/(""");


   $('.export').click(function() """),format.raw/*254.34*/("""{"""),format.raw/*254.35*/("""
           """),format.raw/*255.12*/("""var link= $(this).attr('getLink');
           // alert(link);
          // Id of the element that was clicked
         $("#loading").show();

         $("#loading").hide();
    """),format.raw/*261.5*/("""}"""),format.raw/*261.6*/(""");
 """),format.raw/*262.2*/("""}"""),format.raw/*262.3*/(""");

</script>
<script>
		!function ($) """),format.raw/*266.17*/("""{"""),format.raw/*266.18*/("""
			"""),format.raw/*267.4*/("""$(document).on("click","ul.nav li.parent > a > span.icon", function()"""),format.raw/*267.73*/("""{"""),format.raw/*267.74*/("""		  
				"""),format.raw/*268.5*/("""$(this).find('em:first').toggleClass("glyphicon-minus");	  
			"""),format.raw/*269.4*/("""}"""),format.raw/*269.5*/("""); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		"""),format.raw/*271.3*/("""}"""),format.raw/*271.4*/("""(window.jQuery);

		$(window).on('resize', function () """),format.raw/*273.38*/("""{"""),format.raw/*273.39*/("""
		  """),format.raw/*274.5*/("""if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		"""),format.raw/*275.3*/("""}"""),format.raw/*275.4*/(""")
		$(window).on('resize', function () """),format.raw/*276.38*/("""{"""),format.raw/*276.39*/("""
		  """),format.raw/*277.5*/("""if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		"""),format.raw/*278.3*/("""}"""),format.raw/*278.4*/(""")
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
                  DATE: Wed Mar 09 12:13:36 CST 2016
                  SOURCE: C:/first_play/app/views/ShowStudies.scala.html
                  HASH: 7f165e52dbb5dc3d26a3dd80a1b3e3971015df55
                  MATRIX: 572->1|775->78|803->80|3057->2306|3087->2314|3117->2315|7299->6510|7352->6535|7390->6556|7431->6558|7486->6584|7661->6731|7676->6736|7701->6739|7738->6748|7753->6753|7781->6759|7903->6853|7926->6854|7966->6866|7981->6871|8006->6874|8036->6875|8185->6996|8219->7008|8278->7038|8312->7044|8327->7049|8355->7055|8385->7056|8495->7138|8510->7143|8535->7146|8852->7435|8867->7440|8893->7443|8961->7483|8998->7510|9039->7512|9108->7552|9227->7639|9290->7673|9429->7784|9444->7789|9469->7792|9830->8125|9845->8130|9870->8133|9930->8165|9967->8192|10008->8194|10070->8227|10185->8310|10244->8340|10382->8446|10439->8474|10900->8906|10930->8907|10967->8916|11015->8935|11045->8936|11078->8941|11149->8984|11178->8985|11316->9094|11346->9095|11378->9099|11504->9196|11534->9197|11581->9215|11617->9222|11647->9223|11698->9245|11798->9316|11828->9317|11883->9343|11913->9344|11964->9366|12048->9421|12078->9422|12135->9450|12165->9451|12208->9465|12260->9489|12290->9490|12351->9522|12381->9523|12436->9549|12473->9557|12503->9558|12561->9587|12602->9599|12632->9600|12695->9634|12772->9682|12802->9683|12917->9769|12947->9770|13010->9804|13047->9812|13077->9813|13144->9851|13193->9871|13223->9872|13293->9913|13413->10004|13443->10005|13517->10050|13630->10134|13660->10135|13732->10178|14067->10484|14097->10485|14160->10519|14190->10520|14249->10550|14279->10551|14347->10590|14377->10591|14440->10625|14511->10667|14541->10668|14596->10694|14626->10695|14673->10713|14703->10714|14761->10743|14791->10744|14840->10764|14899->10794|14929->10795|14985->10822|15015->10823|15066->10845|15127->10877|15157->10878|15216->10908|15246->10909|15276->10910|15320->10925|15350->10926|15380->10927|15437->10955|15467->10956|15518->10978|15790->11221|15820->11222|15864->11237|15894->11238|15928->11244|15957->11245|16027->11286|16057->11287|16099->11300|16310->11483|16339->11484|16372->11489|16401->11490|16473->11533|16503->11534|16536->11539|16634->11608|16664->11609|16702->11619|16794->11683|16823->11684|16931->11764|16960->11765|17046->11822|17076->11823|17110->11829|17210->11901|17239->11902|17308->11942|17338->11943|17372->11949|17473->12022|17502->12023
                  LINES: 19->1|22->1|23->2|68->47|68->47|68->47|144->124|144->124|144->124|144->124|145->125|147->127|147->127|147->127|147->127|147->127|147->127|148->128|148->128|148->128|148->128|148->128|148->128|149->129|149->129|150->130|150->130|150->130|150->130|150->130|151->131|151->131|151->131|156->136|156->136|156->136|157->137|157->137|157->137|159->139|161->141|162->142|165->145|165->145|165->145|170->150|170->150|170->150|171->151|171->151|171->151|173->153|175->155|176->156|182->162|184->164|200->180|200->180|203->183|203->183|203->183|204->184|205->185|205->185|208->188|208->188|210->190|215->195|215->195|216->196|216->196|216->196|217->197|219->199|219->199|220->200|220->200|221->201|223->203|223->203|224->204|224->204|225->205|226->206|226->206|227->207|227->207|228->208|228->208|228->208|229->209|229->209|229->209|230->210|231->211|231->211|233->213|233->213|234->214|234->214|234->214|235->215|235->215|235->215|236->216|239->219|239->219|240->220|241->221|241->221|242->222|246->226|246->226|247->227|247->227|248->228|248->228|249->229|249->229|250->230|251->231|251->231|252->232|252->232|253->233|253->233|254->234|254->234|255->235|256->236|256->236|257->237|257->237|258->238|259->239|259->239|260->240|260->240|260->240|260->240|260->240|260->240|261->241|261->241|262->242|268->248|268->248|269->249|269->249|271->251|271->251|274->254|274->254|275->255|281->261|281->261|282->262|282->262|286->266|286->266|287->267|287->267|287->267|288->268|289->269|289->269|291->271|291->271|293->273|293->273|294->274|295->275|295->275|296->276|296->276|297->277|298->278|298->278
                  -- GENERATED --
              */
          