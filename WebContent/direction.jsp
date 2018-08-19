<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/header.css"> 
<style>
	#map {
        height: 100%;
      }
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
            .pac-card {
        margin: 10px 10px 0 0;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
        background-color: #fff;
        font-family: Roboto;
      }

      
      .pac-controls {
        display: inline-block;
        padding: 5px 11px;
      }

      .pac-controls label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
	  #title {
        color: #fff;
        background-color: #4d90fe;
        font-size: 25px;
        font-weight: 500;
        padding: 6px 12px;
      }
</style>
</head>
<body>
<%@include file="includes/header.html" %>
<div class="pac-card" id="pac-card">
<div id="title">
   Select transport mode
</div>
<div id="typeSelector" class="pac-controls">
	<input type="radio" name="type" id="changetype-driving" checked="checked" value="DRIVING">
          <label for="changetype-all">DRIVING</label>
	
    <input type="radio" name="type" id="changetype-bicycle" value="BICYCLING">
          <label for="changetype-all">BICYCLING</label>
          
    <input type="radio" name="type" id="changetype-transit" value="TRANSIT">
          <label for="changetype-all">TRANSIT</label>
          
    <input type="radio" name="type" id="changetype-walking" value="WALKING">
          <label for="changetype-all">WALKING</label>	

</div>
</div>
<div id="map"></div>
</body>
<script>
function initMap() {
	var card = document.getElementById('pac-card');
	var types = document.getElementById('type-selector');
  var directionsService = new google.maps.DirectionsService();
  var directionsDisplay = new google.maps.DirectionsRenderer();
  var mumbai = new google.maps.LatLng(19.0760, 72.8777);
  
  var mapOptions = {
    zoom:13,
    center: mumbai
  }
  
  var map = new google.maps.Map(document.getElementById('map'), mapOptions);
  map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);
  directionsDisplay.setMap(map);
  var radios = document.getElementsByName("type");
  for(var i = 0, max = radios.length; i < max; i++) {
      radios[i].addEventListener("change",function() {
    	  calcRoute(directionsService,directionsDisplay,this.value);
      });
  }
  
  calcRoute(directionsService,directionsDisplay,"DRIVING");
 
}

function calcRoute(directionsService,directionsDisplay,mode)
{
	console.log(mode);
	if (navigator.geolocation) {
	      navigator.geolocation.getCurrentPosition(function(position) {
	        var pos = {
	          lat: position.coords.latitude,
	          lng: position.coords.longitude
	        }
	        var start=pos;
	        var end='${DESTDETAILS}';
	        console.log(start);
	        console.log(end);
	        var request={
	        		origin:start,
	        		destination:end,
	        		travelMode:mode
	        }
	        directionsService.route(request, function(result, status) {
	        	console.log(status);
	            if (status == 'OK') {
	            	
	              directionsDisplay.setDirections(result);
	            }
	          });
	      });
	}
	
	
}


</script>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBxoTzcJBWsnT79MyxkTNNCdlLAPQPg-Fg&callback=initMap">
    </script>
</html>