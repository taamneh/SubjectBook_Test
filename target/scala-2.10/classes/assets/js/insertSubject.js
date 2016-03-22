$(document).ready(function(){
      $("#btn1052").click(function(){
        $("#sharing").slideDown("slow");
        alert('1');
        $('#share').click(function(evt) {
        //$('#errors').hide();
        $.ajax({
            type : 'POST',
            url : '@routes.Application.shareMyStudy',
             data : {
                    email: $("input[name=email]").val(),
                    role: $("select[name=role]").val() ,
                    message: $("textarea[name=message]").val(),
                    studyId: $("input[name=studyId]").val(),
                },
            success : function(data) {
                $("#sharingResult").html(data);
            },
            error : function(data) {
                 $("#sharingResult").html(data);
                setError('Make call failed');
            }
        });
         $("#sharing").slideUp("slow");
        return false;
            });

      e.preventDefault();
      });



    });