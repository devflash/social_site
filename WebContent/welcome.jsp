<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/welcome.css">
    <link href="https://fonts.googleapis.com/css?family=Assistant:200|Dosis:600" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

</head>
<body>
 <c:url var="mypostlink" value="socialController">
    <c:param name="command" value="myPost"/>

</c:url>
<c:url var="newpostlink" value="socialController">
    <c:param name="command" value="newPost"/>

</c:url>
<c:url var="meetingLink" value="socialController">
	<c:param name="command" value="newMeeting"/>
</c:url>
<c:url var="notificationLink" value="socialController">
	<c:param name="command" value="notification"/>
</c:url>
<c:url var="addGroupLink" value="socialController">
	<c:param name="command" value="searchGroup"/>
</c:url>
<c:url var="addSignOutLink" value="socialController">
	<c:param name="command" value="signOut"/>
</c:url>
<c:set var="sessionStatus" value="${SESSION_STATUS}"></c:set>
<c:choose>
	<c:when test="${sessionStatus==1}">
		<div class="wrapper">
       <div class="header">
        <div class="site-title">
            <h3>Social Site</h3>
        </div>
        <div class="hamburger-menu">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>
        <div class="list">
            <nav>
                <ul>
                    <li><a href="${newpostlink}">New Post</a></li>
                    <li><a href="createGroup.jsp">Create Group</a></li>
                    <li><a href="${mypostlink}">My Posts</a></li>
                    <li><a href="#">Chat</a></li>
                    <li><a href="${meetingLink}">Setup Meeting</a></li>
                    <li><a href="#">My profile</a></li>
                    <li><a href="${notificationLink}">Notification</a></li>
                    <li><a href="${addGroupLink}">New Group</a>
                    <li><a href="${addSignOutLink}">Sign Out</a></li>
                </ul>
            </nav>
        </div>
        <div class="profile">
            <img src="data:image.jpg;base64,${USER_DETAILS.displayPicture}"/>
            <h3>${USER_DETAILS.first_name}</h3>
            <p>${USER_DETAILS.email}</p>
        </div>
        <c:choose>
        	<c:when test="${USER_POST==null}">
        		<p>Add group</p>
        	</c:when>
        	<c:when test="${USER_POST!=null}">
        		<div class="user_post">
        <c:forEach var="post" items="${USER_POST}">
        <div class="post">
            <h2>${post.title}</h2>
            <p>${post.content}</p>
            <img src="data:image.jpg;base64,${post.post64image}"/>
            <div class="icons">
                    <i class="far fa-thumbs-up"></i>
                    <i class="far fa-thumbs-down"></i>
                    
            </div>
            
        </div>
        
        </c:forEach>
        </div>
        	</c:when>
        </c:choose>
        
                <div class="footer">
            <p>Social site &copy; Qlab Project</p>
        </div>
   </div>
			
	</c:when>
	<c:when test="${sessionStatus==0}">
		<%response.sendRedirect("index.jsp"); %>
	</c:when>
</c:choose>
   
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
<script src="js/script.js"></script>
</html>