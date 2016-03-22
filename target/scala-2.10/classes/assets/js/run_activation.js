$(document).ready(function(){
  var x=document.getElementById("S001BL");
  //var y = x + " li";
    var  studyId = $("#studyNo").text();
    //alert(y);
    //var t = $("#studyNo").attr('subject');
    //alert(t);

    $(".inner > .label").not('.emptyMessage').click(function() {
       var subject= $(this).parent().attr('subject');
       var session = $(this).parent().attr('session');
       var name = subject+session;

       var button_var = '<button id = "showvideo'+ name + '">Show Videos</button>';
       var video_var = '<div id="videoboard'+ name + '" hidden> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <video id= "video'+ name +'" width="400px" controls preload="auto" muted> Your browser does not support HTML5 video.</video> </div>';
       var placeholder = '<div id ="'+ name + '" style="float:left;border:1px solid; border-radius:25px;background:#F0FFFF;width:60%;"> <div id="chart'+ name + '" style="width:100%"> </div> ' + button_var + '<br> '+ video_var+ '&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div>';
       $(".SubjectsDock").append(placeholder);
       google.load('visualization', '1.0', { 'packages': ['corechart'], callback: intermediate});
       var videoDiv ="#"+name;
          function intermediate ()
           {
              drawStuff(session, subject , name);
              showVideo(name);
           }
          $(videoDiv).toggle();
     });
});
function drawStuff(task, subject, chartDestination) {

    //alert('hello');
    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/displaySubject',
                         dataType:"json",
                         //data: "task=" + task + "&subject=" + subject ,
                         data: "studyNo=1" + "&SubjectId=" + subject ,
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'out'
                    },
              backgroundColor: { fill:'transparent' }

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
             $('#video1').html(' <source src= "assets/images/v.mp4" type="video/mp4">');
}