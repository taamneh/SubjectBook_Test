$(document).ready(function(){
     $("#btn1").click(function(){
        $.get("/persons",function(data){
           var x = 5;
           $.each(data, function(idx, obj) {
               alert(obj.name + x);
               x = x + 1;
           });
    });
  });
});