<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/new-meeting.css"/>
<link rel="stylesheet" href="css/header.css"/>
</head>
<body>
<%@include file="includes/header.html" %>	
<div class="pac-card" id="pac-card">
      <div>
        <div id="title">
          Autocomplete search
        </div>
        <div id="type-selector" class="pac-controls">
          <input type="radio" name="type" id="changetype-all" checked="checked">
          <label for="changetype-all">All</label>

          <input type="radio" name="type" id="changetype-establishment">
          <label for="changetype-establishment">Establishments</label>

          <input type="radio" name="type" id="changetype-address">
          <label for="changetype-address">Addresses</label>

          <input type="radio" name="type" id="changetype-geocode">
          <label for="changetype-geocode">Geocodes</label>
        </div>
        <div id="strict-bounds-selector" class="pac-controls">
          <input type="checkbox" id="use-strict-bounds" value="">
          <label for="use-strict-bounds">Strict Bounds</label>
        </div>
      </div>
      <div id="pac-container">
        <input id="pac-input" type="text"
            placeholder="Enter a location">
      </div>
    </div>
    <div id="map"></div>
    <div id="infowindow-content">
       <!-- <img src="" width="16" height="16" id="place-icon">
      <span id="place-name"  class="title"></span><br>
      <span id="place-address"></span>
      -->
      
      	<input type="text" placeholder="Meeting Purpose" id="purpose"/>
      	<select placeholder="Select group" id="group">
      		<c:forEach var="groups" items="${USER_GROUPS}">
      			<option>${groups}</option>
      		</c:forEach>
      	</select>
      	<input type="date" id="meet-date"/>
      	<input type="time" id="meet-time"/ >
      	<input type="text" placeholder="Address" id="place-address"/>
      	<button onclick="saveMeeting()">Setup</button >
      
    </div>

    <script>
      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
		var infowindow;
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.8688, lng: 151.2195},
          zoom: 13
        });
        var card = document.getElementById('pac-card');
        var input = document.getElementById('pac-input');
        var types = document.getElementById('type-selector');
        var strictBounds = document.getElementById('strict-bounds-selector');
        var today = new Date();
  	  var dd = today.getDate();
  	  var mm = today.getMonth()+1; //January is 0!
  	  var yyyy = today.getFullYear();

  	  if(dd<10) {
  	      dd = '0'+dd
  	  } 

  	  if(mm<10) {
  	      mm = '0'+mm
  	  } 

  	  today =yyyy + '-' + mm + '-' + dd;
  	  maxdate=yyyy+ '-' + mm + '-01';
  	var dateInput=document.getElementById("meet-date");
  	dateInput.setAttribute("min",today);
  	dateInput.setAttribute("max",maxdate);

        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

        var autocomplete = new google.maps.places.Autocomplete(input);

        // Bind the map's bounds (viewport) property to the autocomplete object,
        // so that the autocomplete requests use the current map bounds for the
        // bounds option in the request.
        autocomplete.bindTo('bounds', map);

        infowindow = new google.maps.InfoWindow();
        var infowindowContent = document.getElementById('infowindow-content');
        infowindow.setContent(infowindowContent);
        var marker = new google.maps.Marker({
          map: map,
          anchorPoint: new google.maps.Point(0, -29)
        });

        autocomplete.addListener('place_changed', function() {
          infowindow.close();
          marker.setVisible(false);
          var place = autocomplete.getPlace();
          console.log(place);
          if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
          }

          // If the place has a geometry, then present it on a map.
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
          }
          marker.setPosition(place.geometry.location);
          marker.setVisible(true);

          var address = '';
          if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
          }

          //infowindowContent.children['place-icon'].src = place.icon;
         // infowindowContent.children['place-name'].textContent = place.name;
          infowindowContent.children['place-address'].value = address;
          infowindow.open(map, marker);
        });

        // Sets a listener on a radio button to change the filter type on Places
        // Autocomplete.
        function setupClickListener(id, types) {
          var radioButton = document.getElementById(id);
          radioButton.addEventListener('click', function() {
            autocomplete.setTypes(types);
          });
        }

        setupClickListener('changetype-all', []);
        setupClickListener('changetype-address', ['address']);
        setupClickListener('changetype-establishment', ['establishment']);
        setupClickListener('changetype-geocode', ['geocode']);

        document.getElementById('use-strict-bounds')
            .addEventListener('click', function() {
              console.log('Checkbox clicked! New state=' + this.checked);
              autocomplete.setOptions({strictBounds: this.checked});
            });
       
      }
      function saveMeeting()
      {
    	var purpose=document.getElementById("purpose").value;
      	var group=document.getElementById("group").value;
      	var time=document.getElementById("meet-time").value;
        var place=document.getElementById("place-address").value;
      	var date=document.getElementById("meet-date").value;
      	console.log(date);
      	var url="socialController?command=saveMeeting&purpose="+purpose+"&group="+group+"&time="+time+"&place="+place+"&date="+date;
      	downloadUrl(url, function(data, responseCode) {
      		 
            if (responseCode == 200 && data.length <= 1) {
              infowindow.close();
             
            }
          });
      }
      function downloadUrl(url, callback) {
    	  
          var request = window.ActiveXObject ?
              new ActiveXObject('Microsoft.XMLHTTP') :
              new XMLHttpRequest;

          request.onreadystatechange = function() {
            if (request.readyState == 4) {
              request.onreadystatechange = doNothing;
              callback(request.responseText, request.status);
            }
          };
          
          request.open('GET', url, true);
          request.send(null);
          window.alert("New Meeting has been sheduled");
        }
      function doNothing () {
      }

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBxoTzcJBWsnT79MyxkTNNCdlLAPQPg-Fg&libraries=places&callback=initMap"
        async defer></script>
      
</body>
</html>