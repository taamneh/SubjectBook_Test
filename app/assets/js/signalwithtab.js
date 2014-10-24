$(document).ready(function(){

 $('.tabs .tab-links a').click(function() {

   function intermediate ()
                     {
                        //drawStuff(session, subject , name, studyId);
                        var chartname = "#chart"+name+1;
                        var dynamicbtn = "#dynamic"+name+1;
                        if($(chartname).length > 0)
                        {
                        drawStuff_temp1(session, subject , chartname, studyId, 1, dynamicbtn);
                        }
                        chartname = "#chart"+name+2;
                        dynamicbtn = "#dynamic"+name+2;
                        if($(chartname).length > 0)
                        {
                           drawStuff_temp1(session, subject , chartname, studyId, 2,dynamicbtn);
                        }
                         chartname = "#chart"+name+3;
                         dynamicbtn = "#dynamic"+name+3;
                          if($(chartname).length > 0)
                          {
                            drawStuff_temp1(session, subject , chartname, studyId, 3, dynamicbtn);
                           }
                        chartname = "#chart"+name+4;
                        dynamicbtn = "#dynamic"+name+4;
                        if($(chartname).length > 0)
                        {
                           drawStuff_temp1(session, subject , chartname, studyId, 4, dynamicbtn);
                        }
                        chartname = "#chart"+name+5;
                        dynamicbtn = "#dynamic"+name+5;
                        if($(chartname).length > 0)
                        {
                           drawStuff_temp1(session, subject , chartname, studyId, 5, dynamicbtn);
                        }
                        chartname = "#chart"+name+6;
                        dynamicbtn = "#dynamic"+name+6;
                        if($(chartname).length > 0)
                        {
                           drawStuff_temp1(session, subject , chartname, studyId, 6, dynamicbtn);
                        }
                        chartname = "#chart"+name+7;
                        dynamicbtn = "#dynamic"+name+7;
                         if($(chartname).length > 0)
                         {
                            drawStuff_temp1(session, subject , chartname, studyId, 7, dynamicbtn);
                         }
                        /*chartname = "#chart"+name+8;
                        if($(chartname).length > 0)
                        {
                           drawStuff_temp1(session, subject , chartname, studyId, 8);
                        }*/

                        //drawStuff_temp1(session, subject , name, studyId);


                        //drawStuff_temp2(session, subject , name, studyId);


                     }

      //alert('1111');
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
               google.load('visualization', '1.0', { 'packages': ['corechart'], callback: intermediate});
               e.preventDefault();

              var videoDiv ="#"+name;

                 $(videoDiv).slideDown("slow");
               }
            });




});
function drawStuff(task, subject, chartDestination, studyId) {


    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/GetSignal',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId + "&signal_type=1",
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'in'
                    },
              backgroundColor: {
               color: 'F0FFFF',
              fill:'transparent' },
              chartArea:{left:40,top:20,width:'50%',height:'75%'}

      };

    //instantiate and draw our chart, passing in the options
    var chartPlace = "#edachart" + chartDestination;

    //alert(chartPlace);
    var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);


     var videoName = "#video" + chartDestination;

     //alert(videoName);
     //alert(videoName);
     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();
      });
}

function drawStuff_temp1(task, subject, chartDestination, studyId, signal_type, dynamicbtn) {


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

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'in'
                    },
              backgroundColor: {
               color: 'F0FFFF',
              fill:'transparent' },
              chartArea:{left:40,top:20,width:'50%',height:'75%'}

      };

    //instantiate and draw our chart, passing in the options
   // var chartPlace = "#chart" + chartDestination;
   var chartPlace = chartDestination;

   //alert(chartDestination);

    //alert(chartPlace);
    var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);

        //var dynamic = '#dynamiceda' + chartDestination;
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

              if(counter == 320)
              {
                    sum = sum/320;
                    result[j] = sum;
                    sum = 0;
                    counter = 0;
                    j++;
              }
          }


         /*var obj2 = JSON.parse(jsonData.rows);
         alert(obj2.c[1].v);

          for(var i in obj)
               result.push([i, obj.rows[i].c[1]]);
          alert(result);*/

        var arr =[100,1000,3000,9000];
        //window.location = 'http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=1,2,3,4,5,6,76,7,8,8,8,9';
        window.location = 'http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=' + result;

       /* $.get("http://kyeongan.cpl.uh.edu/projects/css/api/generator.html?data=1,2,3,4,5,6,76,7,8,8,8,9",function(data,status){
            alert("Data: " + data + "\nStatus: " + status);
          });*/


        });



     var videoName = "#video" + chartDestination;

     //alert(videoName);
     //alert(videoName);
     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();
      });
}


function drawStuff_temp2(task, subject, chartDestination, studyId) {


    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/GetSignal',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId + "&signal_type=2",
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'in'
                    },
              backgroundColor: {
               color: 'F0FFFF',
              fill:'transparent' },
              chartArea:{left:40,top:20,width:'50%',height:'75%'}

      };

    //instantiate and draw our chart, passing in the options
    var chartPlace = "#motionchart" + chartDestination;

    //alert(chartPlace);
    var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);


     var videoName = "#video" + chartDestination;

     //alert(videoName);
     //alert(videoName);
     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();
      });
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

             //$(v).html(' <source src= $vpath type="video/mp4">');
}