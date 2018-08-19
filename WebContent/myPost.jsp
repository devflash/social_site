<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social site</title>
<link rel="stylesheet" href="css/my-post.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
<%@include file="includes/header.html" %>
	
	<c:set var="noPost" value="${NO_POST_ERROR}"></c:set>
	<c:url var="newpostlink" value="socialController">
    	<c:param name="command" value="newPost"/>
    </c:url>
	
	<div class="wrapper">
	
	
	<c:choose>
		<c:when test="${noPost==1}">
			<div class="error">
				<p>You have no posts created</p>
				<a href="${newpostlink}">Create Post</a>
			</div>
		</c:when>
		<c:when test="${noPost==0}">
			<c:forEach var="post" items="${USER_POSTS }">
		<div class="post">
            <h2>${post.title}</h2>
            <p>${post.content }</p>
            <img src="data:image.jpg;base64,${post.post64image}"/>
            <div class="icons">
                    <i class="far fa-thumbs-up"></i>
                    <i class="far fa-thumbs-down"></i>
                    
            </div>
            
        </div>
	</c:forEach>			
		</c:when>
	</c:choose>
</div>	
</body>
</html>