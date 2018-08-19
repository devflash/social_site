$(document).ready(function(){
    $("a").click(function()
    {
    	$(".login").toggle();
        $(".signup").toggle();
        
    });
    	   
			   
    $(".hamburger-menu").click(function()
    {    	     
    	$(".list").slideToggle("slow");
    });
    
    var saveMeeting=function()
    {
    	console.log("button Clicked");
    }
    
    	  
    
});