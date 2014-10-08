$(document).ready(function(){

 $('.tabs .tab-links a').click(function() {

   function intermediate ()
                     {
                        drawStuff(session, subject , name, studyId);
                        showVideo(name);

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
                         url: '/test',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject + "&studyId=" + studyId,
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
    var chartPlace = "#chart" + chartDestination;
    var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);


     var videoName = "#video" + chartDestination;
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