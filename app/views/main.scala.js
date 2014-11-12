$(document).ready(function(){
	// back to top
    $(window).scroll(function(){
        if ($(window).scrollTop() > document.documentElement.clientHeight){
            $("#toTop").fadeIn();
        }else{
            $("#toTop").fadeOut();
        }
    });

    $("#toTop").click(function(){  
        $('body,html').animate({scrollTop:0},500);  
        return false;  
    }); 
    
    // test
    $('#startAllBtn').click(function(){
    	//alert('send');
        var requestData = {"masterIP":$('#masterIp').val(),"retry":$('#retry').val()};
        $.get("@routes.Application.startTest()",
            requestData,
            function(data){
                console.log(data.masterIP+ "-" +data.retry);
        });
    });
    
    
});

