$(document).ready(function(){

 $('.tabs .tab-links a').click(function() {

   function intermediate ()
                     {
                        //drawStuff(session, subject , name, studyId);
                         //drawStuff(session, subject , name, studyId);
                                               var chartname = "#chart"+name+1;
                                               var dynamicbtn = "#dynamic"+name+1;
                                               var info = 1;
                                               if($(chartname).length > 0)
                                               {
                                               drawStuff_temp1(session, subject , chartname, studyId, 1, dynamicbtn, info);
                                               info = 0;
                                               }
                                               chartname = "#chart"+name+2;
                                               dynamicbtn = "#dynamic"+name+2;
                                               if($(chartname).length > 0)
                                               {
                                                  drawStuff_temp1(session, subject , chartname, studyId, 2,dynamicbtn, info);
                                                  info = 0;
                                               }
                                                chartname = "#chart"+name+3;
                                                dynamicbtn = "#dynamic"+name+3;
                                                 if($(chartname).length > 0)
                                                 {
                                                   drawStuff_temp1(session, subject , chartname, studyId, 3, dynamicbtn, info);
                                                   info = 0;
                                                  }
                                               chartname = "#chart"+name+4;
                                               dynamicbtn = "#dynamic"+name+4;
                                               if($(chartname).length > 0)
                                               {
                                                  drawStuff_temp1(session, subject , chartname, studyId, 4, dynamicbtn, info);
                                                  info = 0;
                                               }
                                               chartname = "#chart"+name+5;
                                               dynamicbtn = "#dynamic"+name+5;
                                               if($(chartname).length > 0)
                                               {
                                                  drawStuff_temp1(session, subject , chartname, studyId, 5, dynamicbtn, info);
                                                  info = 0;
                                               }
                                               chartname = "#chart"+name+6;
                                               dynamicbtn = "#dynamic"+name+6;
                                               if($(chartname).length > 0)
                                               {
                                                  drawStuff_temp1(session, subject , chartname, studyId, 6, dynamicbtn, info);
                                                  info = 0;
                                               }
                                               chartname = "#chart"+name+7;
                                               dynamicbtn = "#dynamic"+name+7;
                                                if($(chartname).length > 0)
                                                {
                                                   drawStuff_temp1(session, subject , chartname, studyId, 7, dynamicbtn, info);
                                                   info = 0;
                                                }

                             showVideo(session);



                     }


      var currentAttrValue = $(this).attr('href');
      //alert(currentAttrValue);

              // Show/Hide Tabs

             //$('.tabs ' + currentAttrValue).show().siblings().hide();
             $('.tabs ' + currentAttrValue).show().siblings().hide();

              // Change/remove current tab to active
              $(this).parent('li').addClass('active').siblings().removeClass('active');



               var subject= $(this).attr('subject');
               var session = $(this).attr('session');
               var studyId = $(this).attr('studyId');
               var name = session;
               var occupied = $(this).attr('occupied');

               if(occupied =="no")
               {
               $(this).attr("occupied", "yes");
               //google.load('visualization', '1.0', {'packages':['controls']});
               google.load('visualization', '1.0', { 'packages': ['corechart', 'controls'], callback: intermediate});
               e.preventDefault();

              var videoDiv ="#"+name;

                 $(videoDiv).slideDown("slow");
               }
            });




});

function drawStuff_temp1(task, subject, chartDestination, studyId, signal_type, dynamicbtn, info) {


    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/GetSignal',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=" + signal_type,
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

      var signal_title = "Anonymous Signal";
          if(signal_type == 1)
          {
              signal_title = "EDA Signal";
              }
          if(signal_type == 2){
             signal_title = "Motion Signal";
             }
          if(signal_type == 3){
              signal_title = "Perspiration Signal";
          }
          if(signal_type == 4)
          {
              signal_title = "Breathing";
          }
          if(signal_type == 5)
          {
             signal_title = "Hear Rate";
             }
          if(signal_type == 6)
          {
                 signal_title = "Belt Breathing Signal";
                 }
          if(signal_type == 7)
          {
                 signal_title = "Temperature Signal";
          }

     var options = {
              title: signal_title,
               animation: {
                      duration: 1000,
                      easing: 'in'
                    },
              backgroundColor: {
               color: 'F0FFFF',
              fill:'transparent' },
              chartArea:{left:40,top:20,width:'50%',height:'75%'}

      };


      if(info == 1)
          {
     jsonDataInfo =  $.ajax({
            type: 'GET',
            url: "/getInfo",
            dataType:"json",
            data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=9" ,
            async: false
              }).responseText;
               jsonDataInfo= JSON.parse(jsonDataInfo);
                var bio1;
               if(jsonDataInfo.hasOwnProperty('age')){
                   bio1 = '#first'+ task;
                   $(bio1).text('Age: ' + jsonDataInfo.age);
               }
               if(jsonDataInfo.hasOwnProperty('gender')){
                  bio1 = '#second'+ task;
                  $(bio1).text('Gender: ' + jsonDataInfo.gender);
               }

    jsonDataPm =  $.ajax({
           type: 'GET',
           url: "/getPsycho",
           dataType:"json",
           data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId  + "&signal_type=11" ,
            async: false
           }).responseText;
                   jsonDataPm= JSON.parse(jsonDataPm);
                   var sycho;
                   if(jsonDataPm.hasOwnProperty('SAI')){
                     sycho = '#firstsycho'+ task;
                     $(sycho).text('SAI: ' + jsonDataPm.SAI);
                   }
                    if(jsonDataPm.hasOwnProperty('TAI')){
                      sycho = '#secondsycho'+ task;
                      $(sycho).text('TAI: ' + jsonDataPm.TAI);
                                                        }
              }

    //instantiate and draw our chart, passing in the options
   // var chartPlace = "#chart" + chartDestination;
   var chartPlace = chartDestination;

   //alert(chartDestination);

     var dash = "dashboard_div"+task+ signal_type;
     var filter = "filter_div" + task + signal_type;
     var charName= "chart" + task + signal_type;
     var dashboard = new google.visualization.Dashboard(
                document.getElementById(dash));

     var donutRangeSlider = new google.visualization.ControlWrapper({
               'controlType': 'NumberRangeFilter',
               'containerId': filter,
               'options': {
                 'filterColumnLabel': 'Time'
               }
             });

      var wrapper = new google.visualization.ChartWrapper({
           'chartType':'LineChart',
		dataTable: data,
		options : {
                      title: signal_title,
                       animation: {
                              duration: 1000,
                              easing: 'in'
                            },
                      backgroundColor: {
                       color: 'F0FFFF',
                      fill:'transparent' },
                      chartArea:{left:40,top:20,width:'50%',height:'75%'}

              },
		containerId: charName
           });


    dashboard.bind(donutRangeSlider, wrapper);
                   dashboard.draw(data);
    /*var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);*/




        var dynamic = dynamicbtn;
        //$('#dynamicedaBR').click(function() {
        $(dynamic).click(function() {
        var result = [];
        /*for(var i in jsonData)
            result.push([i, jsonData [i]]);*/
         var obj = JSON.parse(jsonData);
         //alert(obj.rows[1].c[1].v);

         //alert(obj.rows.length);

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
        //window.location = 'http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=1,2,3,4,5,6,76,7,8,8,8,9';
        window.location = 'http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=' + result;

       /* $.get("http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=1,2,3,4,5,6,76,7,8,8,8,9",function(data,status){
            alert("Data: " + data + "\nStatus: " + status);
          });*/


        });



  /*   var videoName = "#video" + chartDestination;


     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();
      });
*/


}


function showVideo(num) {

   var videoButton = "#showvideo"+num;
   var videoBoard = "#videoboard" + num;
   $(videoButton).click(function(){
                //$(videoBoard).toggle();
             if($(videoButton).html()== 'Show Videos')
                {$(videoButton).html('Hide Videos');
                 $(videoBoard).slideDown();}
             else
                 {$(videoButton).html('Show Videos');
                    $(videoBoard).slideUp();
                 }
                     });
               var v = "#video" + num;
               var v2 = "#video" + num;
               var vpath = "assets/images/" + num +".mp4";
             $(v).attr("src", vpath);
             $(v).attr("type", "video/mp4");
             $(v).attr("preload","auto");

             $(v2).attr("src", vpath);
             $(v2).attr("type", "video/mp4");
             $(v2).attr("preload","auto");

             $(v).html(' <source src= $vpath type="video/mp4">');
}