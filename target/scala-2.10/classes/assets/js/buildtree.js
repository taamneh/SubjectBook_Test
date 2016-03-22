function fun(x) {
alert(x);
}

$(document).ready(function () {
var ctr = 1;
$("#cv2").click(function(){

var session1 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Baseline Dexterity</label> <ul id="Run-BL-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
var session2 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Direct View</label> <ul id="Run-DV-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
var session3 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Monitor View</label> <ul id="Run-MV-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
var session4 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Baseline Reading</label> <ul id="Run-BR-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';
var session5 = '<li><input type="checkbox" id="item-0-0-23" /><label for="item-0-0-0">Difficult Reading</label> <ul id="Run-DR-S0' + ctr + '"> <li><a >Run 1</a></li> </ul></li>';

var allSession = session1 + session2 + session3 + session4 + session5;

var sub = $('<li><input type="checkbox" id="item-0-2" /><label for="item-0-1">S00' + ctr + '</label> <ul>'+ allSession +' </ul> </li>');
 ctr = ctr + 1;
 //var sub = $('<li>Taamneh</li>');
//$("#subjects").append(sub);
sub.appendTo($("#subjects"));
   //fun(10);
  });
});