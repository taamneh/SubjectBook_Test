function drawStuff(task, subject, chartDestination) {

    //alert('hello');
    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/test',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject,
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
     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();


      });
}


function linkable(link, type, subjectNum) {
  $(link).not('.emptyMessage').click(function() {
              google.load('visualization', '1.0', { 'packages': ['corechart'], callback: intermediate});
               var str = "#S00" + subjectNum;
               $(str).show();
              function intermediate ()
              {
                  drawStuff(type, "S00" +subjectNum , subjectNum);
                  showVideo(subjectNum);
              }
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
$(document).ready(function(){

  // Building the tree after the user select his study
for ( var i = 1; i <= 20; i++ ) {


  var ctr = i;
  var session1 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Baseline Dexterity</label> <ul id="Run-BL-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
  var session2 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Direct View</label> <ul id="Run-DV-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
  var session3 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Monitor View</label> <ul id="Run-MV-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
  var session4 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Baseline Reading</label> <ul id="Run-BR-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
  var session5 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Difficult Reading</label> <ul id="Run-DR-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';

  var allSession = session1 + session2 + session3 + session4 + session5;

  var sub = $('<li><input type="checkbox" id="item-0-2" /><label for="item-0-1">S00' + ctr + '</label> <ul>'+ allSession +' </ul> </li>');
  sub.appendTo($(".subjects"));

  //var session1 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Baseline Dexterity</label> <ul id="Run-BL-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
  //var sub = $('<li><input type="checkbox" id="item-0-1" /><label for="item-0-1">S002</label> <ul> <li><input type="checkbox" id="item-0-0-22" /><label for="item-0-0-0">Difficult Reading</label><ul id="signal"><li><a >Run 1</a></li></ul></li></ul></li>');

  //$('.subjects').append(sub);


  // for each subject create a placeholder for the charts and video i.e. Div
    var button_var = '<button id = "showvideo'+ ctr + '">Show Videos</button>';
    var video_var = '<div id="videoboard'+ ctr + '" hidden> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <video id= "video'+ ctr +'" width="400px" controls preload="auto" muted> Your browser does not support HTML5 video.</video> </div>';
    var placeholder = '<div id ="S00'+ ctr + '" style="float:left;border:1px solid; border-radius:25px;background:#F0FFFF;width:60%;" hidden> <div id="chart'+ ctr + '" style="width:100%"> </div> ' + button_var + '<br> '+ video_var+ '&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div>';

      //var x = '<p> Taamneh </p>';
    $('.SubjectsDock').append(placeholder);

      var par1 = "#Run-BL-S0" + ctr + " li";
      var par2 = "#Run-DV-S0" + ctr + " li";
      var par3 = "#Run-MV-S0"+ ctr + " li";
      var par4 = "#Run-BR-S0"+ ctr + " li";
      var par5 = "#Run-DR-S0"+ ctr + " li";

      linkable(par1,"BL", ctr);
      linkable(par2,"DV", ctr);
      linkable(par3,"MV", ctr);
      linkable(par4,"BR", ctr);
      linkable(par5,"DR", ctr);


    ctr = ctr + 1;
      //x.appendTo($('#parent'));
    //placeholder.appendTo($("#parent"));
  }



});