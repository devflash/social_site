<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/header.css"/>
<link rel="stylesheet" href="css/notification.css"/>
</head>
<body>
<%@include file="includes/header.html" %>
<div class="cards-wrapper">
	<c:set var="meetingError" value="${NO_MEETING_ERROR}"></c:set>
	<c:choose>
		<c:when test="${meetingError==1}">
			<div class="error">
				<p>No new meeting set</p>
			</div>
		</c:when>
		<c:when test="${meetingError==0}">
				<c:forEach var="meeting" items="${MEETINGDETAIL}">
		<c:url var="tempLink" value="socialController">
			<c:param name="command" value="direction" />
			<c:param name="meetingId" value="${meeting.meid}" />
			<c:param name="address" value="${meeting.place}" />
		</c:url>
		<c:url var="removeLink" value="socialController">
			<c:param name="command" value="remove" />
			<c:param name="meetingId" value="${meeting.meid}" />
		
		</c:url>
	<div class="eachCard">
	<div class="card">
	<div class="group-header">
		<h2>${meeting.groupName}</h2>
	</div>
	
		<div class="img">
			<img src="data:image.jpg;base64,${meeting.groupImg}"/>
		</div>
		<div class="info">
			<table cellspacing="7px">
				<tr>
					<td>
						<span>Purpose:</span>
					</td>
					<td>
						<p>${ meeting.purpose}</p>
					</td>
				</tr>
				<tr>
					<td>
						<span>Date:</span>
					</td>
					<td>
						<p>${meeting.meetDate }</p>
					</td>
				</tr>
				<tr>
					<td>
						<span>Time:</span>
					</td>
					<td>
						<p>${meeting.time}</p>
					</td>
				</tr>
				<tr>
					<td>
						<span>Place:</span>
					</td>
					<td>
						<p>${meeting.place}</p>
					</td>
				</tr>
			</table>
					
		</div>
		
	</div>
	
		<a href="${tempLink}">Directions</a>
		<a href="${removeLink}" onclick="if(!(confirm('Are you sure?'))) return false">Remove</a>
		</div>
	</c:forEach>		
		
		
		
		
		</c:when>
	</c:choose>

	
</div>

</body>
</html>