 var PERSPIRATION_CODE = 1;
 var SIMULATION_CODE = 2;
 var EDA_CODE = 3;
 var HRV_CODE =4;
 var EXPRESSION_CODE = 5;
 var MOTION_CODE = 6;
 var BREATHING_CODE = 7;
 var HEART_RATE_CODE = 8;
 var BELT_BREATHING_CODE = 9;
 var TEMPERATURE_CODE = 10;

 var N_PERSPIRATION_CODE =11;
 var BAR_CHART_CODE =12;
 var EYE_TRACKING_CODE =13;


 var VIDEO_CODE =100;
 var INFO_CODE = 101;
 var ACTIVITY_CODE =102;
 var PSYCHOMETRIC_CODE = 103;
 var PERFORMANCE_CODE =104;
 var OTHER_CODE =105;


var goToTime = 0; // when youtube file get read it use this variable to go to a particulare point
var currentVideoButton ="";  // needed

var refreshIntervalId = null;
// we assume that the maximum number of video is 4--- TODO
var player1 = null, player2 = null, player3 = null, player4 = null;
var videonum =0 ;

var subject;
var session;
var studyId;
var occupied ;

var gChart= null;
var gData = null;

$(document).ready(function(){
// when user click on the tab (i.e., session)


 $('.panel-body.tabs .nav.nav-tabs a').click(function() {

   function intermediate ()
     {




          var counter =0, iterator=0;
          // just to add horizontal axis to the last chart
          $( ".chart" ).each(function( index ) {
            if($(this).attr('session') == session)
                    {
                        counter++;
                    }
          });

          // to show only the first video
          var firstVideo = true;
          // call AJAX method for each signal in the current session and place the return JSON data in (.class) Div
          $( ".chart" ).each(function( index ) {
          if($(this).attr('session') == session)
          {

               var signalType = $(this).attr('signalType');
               var signalSequence = $(this).attr('signalSequence');
               var yTitle = $(this).attr('yTitle');
               var ymin = $(this).attr('ymin');
               var ymax = $(this).attr('ymax');
               var log = $(this).attr('log');
               var chartname = "chart"+session+signalSequence;
               var dynamicbtn = "dynamic"+session+signalSequence;
               if(iterator== counter -1){
                 drawStuff_temp1(session, subject , chartname, studyId, signalSequence,signalType, dynamicbtn, info, true, yTitle, ymin, ymax, log);
               }
               else
                 drawStuff_temp1(session, subject , chartname, studyId, signalSequence,signalType, dynamicbtn, info, false, yTitle, ymin, ymax, log);
               info = 0;
               iterator++;
               showVideo($(this).attr('session'),signalSequence);

          }

          });
     }
       var currentAttrValue = $(this).attr('href');
      $(currentAttrValue).show().siblings().hide();

        subject= $(this).attr('subject');
        session = $(this).attr('session');
        newSessionName = $(this).attr('newSessionName');
        studyId = $(this).attr('studyId');
        occupied = $(this).attr('occupied');

        // to avoid calling the AJAX multiple time

       if(occupied =="no")
       {

            // $("#loading").show();
           $(this).attr("occupied", "yes");
           google.load('visualization', '1', { 'packages': ['corechart', 'bar'], callback: intermediate});
           //e.preventDefault();
           var videoDiv ="#"+session;
            $(videoDiv).slideDown("slow");
       }


        if (gData !== null)
         {


           var match = /\d*/
           for( i =1; i< gData.getNumberOfColumns(); i++){

             //alert(session.replace(match, "").toUpperCase()  +'   '+  gData.getColumnLabel(i).toUpperCase() )
             if(gData.getColumnLabel(i).toUpperCase() == session.replace(match, "").toUpperCase())
             {
                gChart.setSelection([{row:null,column:i}]);
                //alert('fonnd it');
             }
           }

         }


    });


    var info = 1; // this var is used to avoid calling the information function every time
     // this is used to automatically request data from the first tab
    $('.panel-body.tabs .nav.nav-tabs a').first().trigger("click");
      //$('.btn.btn-default.show-video').first().trigger("click");
     showAllGeneral();  // this fucntion will show all the general infromation
});

function showAllGeneral(){
         $('div[id^="generalData"]').each(function() {
             var signalCode = $(this).attr('signalCode');
             var dataType = $(this).attr('dataType');
             var yTitle = $(this).attr('yTitle');
             var signalSequence = $(this).attr('signalSequence');
             var chartName = "generalInfo" + signalSequence + signalCode;

            if(dataType == 4) {   /// if this is bar
              $(this).attr( "class", "col-lg-12" );
                   $(this).append( "<div class=\"panel panel-info\"> \
                                          <div class=\"panel-heading\" > \
                                              NASA Task Load Index <a><span data-toggle=\"collapse\" href=\"#\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-chevron-down\"></em></span> </a> \
                                          </div> \
                                          <div id =\"general123\" class=\"panel-body\"> \
                                               <div  id=\"dashboard_div@temp._1@temp._1\"> \
                                                      <div id=\"" + chartName   + "\" class=\"chartBar\" style=\"height: 250px;\"> \
                                                      </div> \
                                               </div> \
                                          </div> \
                                    </div>");
                   $(this).show();
                    drawStuff_temp1("BAR", subject , chartName , studyId, signalSequence,dataType, "", 1, true, yTitle, -1, -1, 0);
                 }
               if(dataType == 3) {
                                   $(this).append( "<div class=\"panel panel-info\"> \
                                          <div class=\"panel-heading\" > \
                                               Biography <a><span data-toggle=\"collapse\" href=\"#\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-chevron-down\"></em></span> </a> \
                                          </div> \
                                         <div id=\"" + chartName + "\" class=\"panel-body\"> \
                                            <span class=\"glyphicons glyphicons-girl\"></span> \
                                         </div> \
                                    </div>");
                  $(this).show();
                  getBiography (" ", subject, chartName, studyId, signalSequence);
              }
               if(dataType == 5) {
               $(this).attr( "class", "col-lg-8" );
                                $(this).append( "<div class=\"panel panel-info\"> \
                                         <div class=\"panel-heading\" > \
                                              Psychometrics  <a><span data-toggle=\"collapse\" href=\"#\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-chevron-down\"></em></span> </a> \
                                         </div> \
                                        <div id=\"" + chartName + "\" class=\"panel-body\"> \
                                        </div> \
                                   </div>");
                  $(this).show();
                  getPsychometric (" ", subject, chartName, studyId, signalSequence);
               }

               // this code to allow minimize each panel
               var temp =  $(this).attr('id');
                var str = "div#" + temp  +" div.panel div.panel-heading > a > span.icon"
                                $(document).on("click",str, function(){
                                   $("div.panel div#" +chartName ).slideToggle();
                });
               /*if(dataType == 1) {
                   $(this).attr( "class", "col-lg-12" );
                              $(this).append( "<div class=\"panel panel-info\"> \
                                       <div class=\"panel-heading\" > \
                                            Signal <a><span data-toggle=\"collapse\" href=\"#\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-chevron-down\"></em></span> </a> \
                                       </div> \
                                      <div id=\"" + chartName + "\" class=\"panel-body\"> \
                                      </div> \
                                 </div>");
                            $(this).show();
                           // getPsychometric (" ", subject, chartName, studyId, signalSequence);
                            drawStuff_temp1(" ss", subject , chartName, studyId, signalSequence, signalCode, dynamicbtn, info, true);

               }*/
          });
}

var marker ="";
var stopBar = false;
var STAI = 0;
var TypeAB = 1;

function showPsychometricScale( id, type, score, min_value , max_value){

    var target =  "#" + id;
    if(type == "TYPE AB") {


            var jump = (max_value-min_value)/5;

            $(target).igLinearGauge({
                height: "60px",
                width: "100%",
                value: score,
                minimumValue: min_value,
                maximumValue: max_value,
                ranges: [
                    {
                     brush: '#33FFFF',
                        startValue: min_value, endValue: min_value + jump, name: "range5"
                    },
                    {
                     brush: '#33FFCC',
                        startValue: min_value + jump, endValue: min_value + (2*jump), name: "range4"
                    },
                    {
                     brush: '#33FF99',
                        startValue:  min_value + (2*jump), endValue:  min_value + (3*jump), name: "range3"
                    },
                    {
                     brush: '#33FF66',
                        startValue:  min_value + (3*jump), endValue:  min_value + (4*jump), name: "range2"
                    },
                    {
                     brush: '#33FF33',
                        startValue:  min_value + (4*jump), endValue:  min_value + (5*jump), name: "range1"
                    },
                ]
            });

            }

    else  {

    var jump = (max_value-min_value)/5;

     $(target).igLinearGauge({
                    height: "60px",
                    width: "100%",
                     value: score,
                    minimumValue: min_value,
                    maximumValue: max_value,
                    ranges: [
                        {
                            brush: '#FFFFCC',
                            startValue: min_value, endValue:min_value + jump, name: "range1"
                        },
                        {
                             brush: '#FFFF99',
                            startValue: min_value + jump, endValue: min_value + (2*jump), name: "range2"
                        },
                        {
                        brush: '#FFFF66',
                            startValue: min_value + (2*jump), endValue: min_value + (3*jump), name: "range3"
                        },
                        {
                       brush: '#FFFF33',
                            startValue: min_value + (3*jump), endValue: min_value + (4*jump), name: "range4"
                        },
                        {
                           brush: '#FFFF00',
                            startValue: min_value + (4*jump), endValue: min_value + (5*jump), name: "range5"
                        },
                    ]
                });

     }
}
var globalCtr = 1
function getBiography(task, subject, chartDestination, studyId, signalSequence) {

  var chart = "#" + chartDestination ;
   var jsonDataInfo;
           $.ajax({
                type: 'GET',
                url: "/getInfo",
                dataType:"json",
                data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_seq=" + signalSequence ,
                async: true,
               success:function(result) {
                   jsonDataInfo = result;
                   //jsonDataInfo= JSON.parse(jsonDataInfo);
                          var bio1;
                                $(chart).empty();
                              //  alert(jsonDataInfo);
                               if( !jQuery.isEmptyObject(jsonDataInfo))
                               {
                                   //$("#BioTop" ).show();
                                   jQuery.each(jsonDataInfo, function(key, val) {
                                  //$(chart).append( "<p style=\"font-weight: bold\">" + key + ": " + val +" </p>" );
                                  $(chart).append( "<p style=\"font-weight: bold\">" +  val +" </p>" );
                                   });
                               }
                               else{
                                 // $( "#BioTop" ).hide();
                               }
               }
               });
}
function getPsychometric(task, subject, chartDestination, studyId, signalSequence) {
  var chart = "#" + chartDestination ;
   var jsonDataPm = '';
          $.ajax({
                type: 'GET',
                url: "/getPsycho",
                dataType:"json",
                data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_seq=" + signalSequence ,
                 async: true,
                 success:function(result) {
                 jsonDataPm = result;
                   // jsonDataPm= JSON.parse(jsonDataPm);
                          var sycho;
                         $(chart).empty();

                          var pName, min, max, score;
                         if( !jQuery.isEmptyObject (jsonDataPm))
                         {
                            // $("#phychoTop" ).show();
                             jQuery.each(jsonDataPm, function(key, val) {

                             jQuery.each(val, function(key2, val2) {
                               if (key2 == "name")
                                  pName = val2;
                                if(key2=="score")
                                  score= val2;
                                 if(key2=="min")
                                   min = val2;
                                 if(key2=="max")
                                   max = val2;

                             });
                               var name = "lineargauge" + globalCtr;
                                globalCtr++;
                              //<div >Absolute Zero</div> <div style="height:60px"> <div id="lineargauge"></div> </div>

                              $(chart).append( "<div style=\"font-weight: bold\" >"+ pName +": " + score +"</div> <div style=\"height:60px\"> <div id=\"" + name + "\"></div> </div> &nbsp; &nbsp;");
                              showPsychometricScale(name, pName, score, min, max)

                              // $('#phycho').append( "<p>" + key + ": " + val +" </p>" );
                             });
                         }
                         else{
                         //  $( "#phychoTop" ).hide();
                         }

                 }

                })
}

function showPerformance(task, subject, chartDestination, studyId, signalSequence){
         var jsonDataPRF = '';
           $.ajax({
                         type: 'GET',
                         url: "/getPRF",
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=12" ,
                          async: true,
                          success:function(result) {
                          jsonDataPRF = result;
                         // jsonDataPRF= JSON.parse(jsonDataPRF);
                                var counter = 0;
                                while(counter < jsonDataPRF.length)
                                {
                                    var val =  jsonDataPRF[counter].value
                                    if(!isNaN(parseInt(val))){
                                         var perc = jsonDataPRF[counter].value / jsonDataPRF[counter].max*100;
                                     }
                                    else
                                       { var perc = 0;}


                                    $('#allInfo').append(" <div class=\"col-xs-6 col-md-3\"> \
                                                             <div class=\"panel panel-default\"> \
                                                                <div class=\"panel-body easypiechart-panel\"> <h4>" + jsonDataPRF[counter].name + ":</h4> \
                                                                    <div class=\"easypiechart\" id=\"easypiechart-orange\" data-percent=\""+ perc +"\"><span class=\"percent\">"+ jsonDataPRF[counter].value +"</span> \
                                                                    </div> <script> $('.easypiechart').easyPieChart({ scaleColor: false, barColor: '#ffb53e'}); </script> \
                                                                </div> \
                                                            </div> \
                                                      </div>");
                                    counter++;
                                }
                          }
                         })



}



function drawStuff_temp1(task, subject, chartDestination, studyId, signalSequence,signal_type, dynamicbtn, info, showhAxis, vTitle, ymin, ymax, log) {


    var signal_title ;
    var ytitle = "";
    var showYAxis = 'out' // this is used to hide the vaxis of simulation data
    var tooltipTrigger = 'select' // just to not show the tooltip for simulation data
    var header = "#header"+task+ signalSequence;
    var dash = "dashboard_div"+task+ signalSequence;
    var filter = "filter_div" + task + signalSequence;
    var editme = "#editchart"  + task + signalSequence;
    var max_yvalue = "auto"
    var min_xvalue = 0
    var logType = null;

       if(ymin != null ){
             min_xvalue = ymin;
          }
      if(ymax != null){
        max_yvalue = ymax;
      }

    if(log ==1)
       logType = 'log'


    var jsonData;
     $.ajax({
                         type: 'GET',
                         url: '/GetSignal',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=" + signal_type + "&signal_sequence=" + signalSequence,
                         async: true,
                          beforeSend: function() { $('#loading').show(); },
                          complete: function() { $('#loading').hide(); },
                          success:function(result) {
                             jsonData = result;
                              var data = new google.visualization.DataTable(jsonData);

                             if(signal_type ==4){
                             gData = data;

                                        var options = {
                                         title: 'NASA Task Load Index',
                                          hAxis: {
                                            minValue: 0
                                          },
                                          vAxis: {
                                          },
                                          legend: { position: 'top'},
                                          bars: 'vertical'
                                        };
                              gChart = new google.visualization.ColumnChart(document.getElementById(chartDestination));
                              gChart.draw(data,options );


                              //chart.setSelection([{row:null, column:0}])
                               //gChart.setSelection([{row:null,column:1}]);
                              }
                              else if(signal_type == 3){
                                  getBiography (" ", subject, chartDestination, studyId, signalSequence);

                              }
                              else if(signal_type == 5){
                                 chartDestination = "#"+ chartDestination;

                                 $(chartDestination).attr( "class", "col-lg-8" );
                                 var chartName = "sessionRelated" + signalSequence;
                                    $(chartDestination).append( "<div class=\"panel panel-info\"> \
                                                                           <div class=\"panel-heading\" > \
                                                                                Psychometric <a><span data-toggle=\"collapse\" href=\"#\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-chevron-down\"></em></span> </a> \
                                                                           </div> \
                                                                          <div id=\"" + chartName + "\" class=\"panel-body\"> \
                                                                          </div> \
                                                                     </div>");

                                     getPsychometric (" ", subject, chartName, studyId, signalSequence);

                              }
                              else{

                                   switch(parseInt(signal_type))
                                   {
                                     case EDA_CODE:
                                        signal_title = "EDA Signal";
                                        ytitle = "Palm EDA [µS]";
                                        break;
                                     case MOTION_CODE:
                                        signal_title = "Motion Signal";
                                        ytitle = "Energy";
                                         break;
                                     case PERSPIRATION_CODE:
                                         signal_title = "Perspiration Signal";
                                     	ytitle = "Perinasal EDA ["+ '\u00B0' +"C"+ '\xB2' +"]";
                                     	break;
                                     case N_PERSPIRATION_CODE:
                                     	 signal_title = "Perspiration Signal";
                                         ytitle = "Nasal EDA ["+ '\u00B0' +"C"+ '\xB2' +"]";
                                         break;
                                     case BREATHING_CODE:
                                         signal_title = "Breathing";
                                     	ytitle = "Rate";
                                     	break;
                                     case HEART_RATE_CODE:
                                         signal_title = "Heart Rate";
                                     	ytitle = "Rate";
                                     	break;
                                     case BELT_BREATHING_CODE:
                                         signal_title = "Belt Breathing Signal";
                                         ytitle = "Rate";
                                         break;
                                     case TEMPERATURE_CODE:
                                         signal_title = "Temperature Signal";
                                     	ytitle = "Temperature";
                                     	break;
                                     case SIMULATION_CODE:
                                         signal_title = "Driving Simulator Data";
                                         ytitle = "";
                                         showYAxis = 'none'
                                         tooltipTrigger = 'none'
                                        	break;
                                     case HRV_CODE:
                                         signal_title = "Heart Rate Variability";
                                         ytitle = "HRV [bpm]";
                                            break;
                                       case EXPRESSION_CODE:
                                          signal_title = "Facial Expressions";
                                          ytitle = "";
                                             break;
                                      case EYE_TRACKING_CODE:
                                            signal_title = "Malcolm is Idiot";
                                            ytitle = "Eye Tracker";
                                            break;

                                     default:
                                       signal_title = "Anonymous Signal";
                                       ytitle = "";
                                   }
                                    ytitle = vTitle;      /// Just use the parameter vtitle as the ytile for the chart

                                    // Tempraray
                                    if(ytitle.toLowerCase().indexOf("perinasal") > -1)
                                         ytitle = "Perinasal EDA ["+ '\u00B0' +"C"+ '\xB2' +"]";
                                     else if(ytitle.toLowerCase().indexOf("nasal") > -1)
                                        ytitle = "Nasal EDA ["+ '\u00B0' +"C"+ '\xB2' +"]";
                                      else if(ytitle.toLowerCase().indexOf("eda") > -1)
                                        ytitle = "Palm EDA [µS]";
                                      else if(ytitle.toLowerCase().indexOf("hrv") > -1)
                                        ytitle =  "HRV [bpm]";

                                    ////////////////////////////


                                  // to set the title for each signal
                                  $( header ).html( signal_title );

                                   if(info == 1){
                                    // showInfo(task, subject, chartDestination, studyId, signalSequence);
                               }
                                // this is to show the axis for the last chart
                                var showAx = "none";
                                var titleVar =null;
                                if(showhAxis)
                                {
                                    showAx ="out";
                                    titleVar = "Time [s]"
                                }


                                // here we specify the index of the first annotation cloums
                                var indexOfLast = data.getNumberOfColumns();
                                var startAnnotFrom;
                                for( t=0; t< indexOfLast; t++)
                                {
                                  if(data.getColumnId(t).substr(0, 5) == "bgCol")
                                   {
                                     startAnnotFrom = t-3;
                                     break;
                                   }
                                }

                                var colors = ["#dc3912","#3366cc","#ff9900","#109618","#A901DB","#0099c6","#dd4477","#66aa00","#b82e2e","#316395","#E88DBA","#22aa99","#aaaa11","#6633cc","#e67300",'#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'];
                                 var chart = new google.visualization.LineChart(document.getElementById(chartDestination));
                                 var options = {
                                           //title: signal_title,

                                           legend: { position: 'top', maxLines: 2 },
                                           curveType: 'function',
                                           colors: colors,
                                          // backgroundColor:  '#F5F5F5',
                                           chartArea:{left:100,top: 50, width:'85%',height:'65%'},
                                           series: {
                                              //2 : {type: "area"}

                                                          },
                                           hAxis: {
                                                  title: titleVar,
                                                   baseline: 0,
                                                   gridlines: {
                                                                      color: 'transparent'
                                                                  },
                                                   textStyle:{
                                                                        //color : 'green',
                                                                        bold: true,
                                                                        italic: false
                                                                       },
                                                   titleTextStyle:{
                                                                    //color : 'green',
                                                                     bold: true,
                                                                     italic: false
                                                   },
                                                  textPosition : showAx

                                                },
                                           crosshair: { trigger: 'selection', orientation: 'vertical', color: 'black'},
                                           annotations: {
                                                       //style: 'line',
                                                       color: 'red',
                                                           textStyle: {
                                                             fontName: 'Times-Roman',
                                                             fontSize:5,
                                                             bold: true,
                                                             //italic: true,
                                                             color: 'grey',     // The color of the text.
                                                             auraColor: 'red', // The color of the text outline.
                                                             opacity: 1          // The transparency of the text.
                                                           }
                                                   },
                                             tooltip: { trigger: tooltipTrigger, showColorCode: true },
                                           //tooltip: { trigger: 'none' },
                                           explorer: { actions: ['dragToZoom', 'rightClickToReset'], maxZoomIn: .01 },
                                           animation:{ startup:true},
                                           vAxis: {
                                           scaleType: logType,
                                           title: ytitle,
                                           baseline: min_xvalue,
                                            textPosition: showYAxis,
                                            format: '###0.000',
                                           gridlines: {
                                                  color: 'transparent'
                                              },
                                           textStyle:{
                                                   //color : 'green',
                                                   bold: true,
                                                   italic: false
                                          },
                                           titleTextStyle:{
                                                          // color : 'green',
                                                           bold: true,
                                                           italic: false
                                                           },
                                           viewWindow :{
                                                       min : min_xvalue,
                                                       max : max_yvalue
                                                      }}
                                           /*vAxis: { title: ytitle,
                                                    baseline: min_xvalue,
                                                     textPosition: showYAxis,
                                                     format: '###0.000',
                                                    gridlines: {
                                                           color: 'transparent'
                                                       },
                                                    textStyle:{
                                                            //color : 'green',
                                                            bold: true,
                                                            italic: false
                                                   },
                                                    titleTextStyle:{
                                                                   // color : 'green',
                                                                    bold: true,
                                                                    italic: false
                                                                    },
                                                   viewWindow :{
                                                    min : min_xvalue,
                                                    max : max_yvalue
                                                   }
                                            }*/
                                      };

                                 // to assigna colors for the annotaiton series....
                                 myObj = {};
                                 var first   = "#FFFF80";
                                 var second  = "#D4A1C7";
                                 var third  = "#CECEF6";
                                 var fourth = "#66FFFF"
                                 var fifth = "#D6D633"
                                 var last = "#D1D1FF"

                                 var isSecondTaken = false  // if the second has been assigned for failure

                                 var colorVar;
                                 var ctrColor =1;

                                var columns = [];
                                var series = {};
                                ctrColor =1;
                                for (var i = 0; i < data.getNumberOfColumns(); i++) {
                                    columns.push(i);
                                    if(i>=startAnnotFrom)
                                    {
                                   // alert(i + '  '+  data.getColumnLabel(i))
                                      switch(ctrColor)
                                       {
                                        case 1:
                                           colorVar = first;
                                           break;
                                        case 2:
                                           if(! isSecondTaken)
                                              colorVar = second;
                                            else
                                              colorVar = last;
                                            break;
                                         case 3:
                                           colorVar = third;
                                            break;
                                         case 4:
                                             colorVar = fourth;
                                             break;
                                          case 5:
                                             colorVar = fifth;
                                             break;
                                         }


                                          //////////////////////////////////Under test code  Toyota ///////////////////////////////
                                          if(data.getColumnLabel(i).toLowerCase().indexOf("failure") > -1)  // this is an exception of Toyota study to show all the failure drive with
                                             {
                                                   series[i-3].color = second;
                                                   colors[i-3] = second
                                                  //alert(colorVar + ' After ' + second )
                                                  colorVar = second;
                                                  isSecondTaken = true; // to make sure this color will not be assigned again to an
                                             }
                                         /////////////////////////////////////////////////////////////////////////////////////////

                                        ctrColor++;

                                        series[i] = {type: "area", areaOpacity:0.9, lineWidth:0, color: colorVar};
                                        colors[i] = colorVar;
                                    }
                                    else {
                                        series[i] = {color: colors[i]};
                                    }
                                }

                                 // update the option with new colors for annotations
                                 options.series = series;
                                 chart.draw(data,options );



                                  //  var currentdate = new Date("July 21, 1983 01:15:00:526");
                              google.visualization.events.addListener(chart, 'select', selectHandler);
                              function selectHandler(e) {
                                   var videoButton = "#showvideo"+ task + signalSequence;
                                   var videoBoard = "#videoboard" + task + signalSequence;
                                    var sel =  chart.getSelection();
                                      // if selection length is 0, we deselected an element
                                      if (sel.length > 0) {
                                        if (sel[0].row === null) {
                                                      var col = sel[0].column;
                                                      var shift;
                                                      if (typeof startAnnotFrom === "undefined")
                                                           shift = 0;
                                                      else
                                                           shift = 2;

                                                      if (columns[col] == col) {
                                                          // hide the data series
                                                          columns[col] = {
                                                              label: data.getColumnLabel(col),
                                                              type: data.getColumnType(col),
                                                              calc: function () {
                                                                  return null;
                                                              }
                                                          };

                                                          // grey out the legend entry
                                                          series[col - (shift+1)].color = '#CCCCCC';
                                                      }
                                                      else {
                                                          // show the data series
                                                          columns[col] = col;
                                                          series[col - (shift+1)].color = null;
                                                      }
                                                      var view = new google.visualization.DataView(data);
                                                      view.setColumns(columns);
                                                       //options2.series = myObj;
                                                      chart.draw(view, options);
                                                  }
                                                  else
                                                {
                                                    slidDownVideoPlay(videoButton, videoBoard);
                                                   //var videoButton = "#showvideo"+ task + signalSequence;
                                                    if($(stopv).html()== 'Play Videos')
                                                     {
                                                        $(stopv).html('Stop Videos');
                                                     }
                                                    if($(videoButton).html()!= 'Show Videos')
                                                    {
                                                           // to stop the previous indicator
                                                         if(refreshIntervalId != null){
                                                                       clearInterval(refreshIntervalId);
                                                          }
                                                         var selectedItem = chart.getSelection()[0];
                                                         var diff =data.getValue(selectedItem.row+1, 0)- data.getValue(selectedItem.row, 0)
                                                         // to move second by second
                                                          var addAmount =Math.ceil(1/diff);
                                                          var point = selectedItem.row;
                                                          var pointValue = data.getValue(point,0);
                                                          seek(Math.floor(pointValue), videoButton);
                                                          goToTime= Math.floor(pointValue);  // this is to let the video play when it is not ready yet
                                                          //seek(Math.floor(pointValue));  // this is to play the video when it is rady

                                                          refreshIntervalId =setInterval(function() {
                                                              // only start the indicator if there is a video playing
                                                              if(player1.getPlayerState() == 1)
                                                                {
                                                                    // this will not fire slection handler;
                                                                    chart.setSelection([{row:point}]);
                                                                    point = point + addAmount;
                                                                    //pointValue = data.getValue(point,0);
                                                                }
                                                               },1000);
                                                         currentVideoButton = videoButton;
                                                     }
                                                }
                                      }

                                   }


                                 $(editme).show();
                                 $(editme).click(function()
                                 {
                                      var chartEditor = new google.visualization.ChartEditor();
                                      google.visualization.events.addListener(chartEditor, 'ok', redrawChart);
                                      chartEditor.openDialog(wrapper, {});
                                      function redrawChart(){
                                            //chartEditor.getChartWrapper().draw(document.getElementById('chart1DirectView1'));
                                           var newwrapper = chartEditor.getChartWrapper();
                                            dashboard.bind(donutRangeSlider, newwrapper);
                                                               dashboard.draw(data);

                                                               google.visualization.events.addListener(newwrapper, 'select', function(e) {
                                                                           var selectedItemIn  = newwrapper.getChart().getSelection()[0].row;
                                                                           var point = data.getValue(selectedItemIn, 0);
                                                                           var v = donutRangeSlider.getState();
                                                                                   var absStart = v.range.start;
                                                                                   var end = v.range.end;
                                                                                   var w = wrapper.getChart().getChartLayoutInterface().getChartAreaBoundingBox().width;
                                                                                   move_str =0;
                                                                                   var relStart =  point + absStart;
                                                                                   active++;
                                                                                    stopBar = false;
                                                                                   doMove(absStart, relStart, end, w, active);
                                                                                  seek(point + absStart);
                                                                     });
                                          }
                                  });



                                  var stopv = "#stopvideo"  + task + signalSequence;
                                  $(document).on('keypress', function(e) {
                                      var tag = e.target.tagName.toLowerCase();
                                      if ( e.which === 115 && tag != 'input' && tag != 'textarea')
                                      {
                                         $(stopv).html('Play Videos');
                                         stopvideo();
                                         clearInterval(refreshIntervalId);
                                      }
                                       if ( e.which === 112 && tag != 'input' && tag != 'textarea')
                                       {
                                          $(stopv).html('Stop Videos');
                                          google.visualization.events.trigger(chart, 'select', selectHandler);
                                       }

                                  });

                                   $(stopv).click(function() {
                                     if($(stopv).html()== 'Stop Videos')
                                           {$(stopv).html('Play Videos');
                                             stopvideo();
                                             clearInterval(refreshIntervalId);
                                            }
                                        else
                                            {
                                            $(stopv).html('Stop Videos');
                                             google.visualization.events.trigger(chart, 'select', selectHandler);
                                            }

                                   });

                                  $('#hideSideBar').click(function(){
                                       $('#sidebar-collapse').toggle(
                                        function() {
                                                  $('#main').css('left', '0')
                                                 }, function() {
                                                     $('#main').css('left', '0px')
                                                 });
                                  });




                                    /*var export1 = "#export" + task + signalSequence;
                                    $(export1).show();
                                    var a = document.getElementById("export" + task + signalSequence); //or grab it by tagname etc
                                    var exportLink = "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=" + signal_type + "&signal_sequence=" + signalSequence;
                                    a.href = "/file?"+ exportLink;*/


                                      var dynamic = dynamicbtn;
                                     $(dynamic).show();
                                     $(dynamic).click(function() {
                                         var result = [];
                                          //var obj = JSON.parse(jsonData);
                                          obj= jsonData;

                                          var points =  obj.rows.length;

                                          var sum = 0;
                                          var counter = 0;
                                          var j = 0;
                                         for (i = 0; i < points; i++) {

                                           sum = sum + obj.rows[i].c[1].v;
                                           counter++;

                                               if(counter == 10)
                                               {
                                                     sum = sum/10;
                                                     result[j] = sum;
                                                     sum = 0;
                                                     counter = 0;
                                                     j++;
                                               }
                                           }
                                         var arr =[100,1000,3000,9000];
                                         window.location = 'http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=' + result;
                                         });



                             }
                             }
                         })
      if( jQuery.isEmptyObject(jsonData))
                 {
                    //alert('Data is not there');
      }



}



// to create a video board for each signal that is called when the user press show video
function showVideo(SessionName, signalSequence) {

   var videoButton = "#showvideo"+SessionName + signalSequence;
   var videoBoard = "#videoboard" + SessionName + signalSequence;
   //$(videoButton).show();

   if($(videoBoard).children().length <= 3 ) {
        $(videoButton).hide();
   }

$(videoButton).click(function(){

       currentVideoButton ="";
      $('div[id^="videoboard"]').each(function() {
              var v_id=  "#" + this.id;
             if(v_id == videoBoard){  // to avoid hidding the video for current  chart
              // player1 = null, player2 = null, player3 = null, player4 = null;
              $(v_id).slideUp();
              $(v_id).hide();
             }
       });
       /* $('a[id^="showvideo"]').each(function() {
            var v_id=  "#" + this.id;
            if(v_id != videoButton)
                 $(v_id).html('Show Videos');
             });

    videonum =0;*/

    //  $(videoButton).html('Hide Videos');

      // slidDownVideoPlay(videoButton, videoBoard);



      $(videoBoard).slideUp();
      $(videoBoard).hide();


  });
}

function slidDownVideoPlay(videoButton, videoBoard){


    $('div[id^="videoboard"]').each(function() {
             var v_id=  "#" + this.id;
             if(v_id != videoBoard){
             $(v_id).slideUp();
             $(v_id).hide();
             }
      });

      /* $('a[id^="showvideo"]').each(function() {
           var v_id=  "#" + this.id;
           if(v_id != videoButton)
                $(v_id).html('Show Videos');
            });*/

   videonum =0;
                //$(videoBoard).toggle();
     $(videoButton).html('Hide Videos');
     $(videoBoard).slideDown();


  }

// this fucntion is called whenever whe show the videoboard becuase that create new videos...
function onYouTubePlayerReady(playerId)
{
  switch(videonum)
     {
       case 0:

         player1 = document.getElementById(playerId);
         player1.seekTo(goToTime, true);
         player1.playVideo();
         //player1.pauseVideo();

         player1.addEventListener("onStateChange", "onytplayerStateChange");
          break;
       case 1:

          player2 = document.getElementById(playerId);
          player2.seekTo(goToTime, true);
          player2.playVideo();
          //player2.pauseVideo();

          player2.addEventListener("onStateChange", "onytplayerStateChange");
        break;
       case 2:

           player3 = document.getElementById(playerId);
           player3.seekTo(goToTime, true);
           player3.playVideo();
           player3.addEventListener("onStateChange", "onytplayerStateChange");
        break;
       case 3:
           player4 = document.getElementById(playerId);
           player4.seekTo(goToTime, true);
           player4.playVideo();
           //player4.pauseVideo();
           player4.addEventListener("onStateChange", "onytplayerStateChange");
        break;
       case 4:
           player5 = document.getElementById(playerId);
            player5.playVideo();
            //player5.pauseVideo();
            player5.seekTo(goToTime, true);
           player5.addEventListener("onStateChange", "onytplayerStateChange");
         break;
     }
  videonum++;
}

function onytplayerStateChange(newState) {
  // alert("Player's new state: " + newState);
}

function seek(to, videoButton) {


if(videoButton == currentVideoButton){
 if(player1 != null ){
      player1.seekTo(to, true);
      player1.playVideo();
     }
 if(player2 != null ){
      player2.seekTo(to, true);
      player2.playVideo();
     }
  if(player3 != null ){
      player3.seekTo(to, true);
      player3.playVideo();
  }
  if(player4 != null ){
    player4.seekTo(to, true);
    player4.playVideo();
       }
   }
}

// to play after pause
function playPausedVideos() {
 if(player1 != null) {
   player1.playVideo();
 }
 if(player2 != null){
  player2.playVideo();
  }
  if(player3 != null){
    player3.playVideo();
  }
  if(player4 != null){
    player4.playVideo();
   }
}

function stopvideo() {
 if(player1 != null) {
  player1.pauseVideo();
 }
  if(player2 != null){
  player2.pauseVideo();
  }
  if(player3 != null){
    player3.pauseVideo();
  }
  if(player4 != null){
    player4.pauseVideo();
   }
}
