<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/add-post.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/header.css">

<title>Social Site</title>
</head>
<body>
<%@include file="includes/header.html" %>
<div class="wrapper">

        <div class="add">
            <div class="title">
                Create Post
            </div>
            <form action="socialController" method="post" enctype="multipart/form-data">
                <input type="hidden" value="addPost" name="command" />
                <div class="post-form">
                    <div class="titleLabel">
                        <label>Post Title:</label>
                    </div>
                    <div class="titleInput">
                        <input type="text" name="post-title" />
                    </div>
                    <div class="groupeLabel">
                    	<label>Select Group:</label>
                    </div>
                    <div class="groupInput">
                    	<select name="grpName">
                    		<c:forEach var="grpName" items="${USER_GROUPS}">
                    			<option value="${grpName}">${grpName}</option>
                    		</c:forEach>
                    	</select>
                    </div>
                    <div class="imageLabel">
                        <label>Image:</label>
                    </div>
                    <div class="imageInput">
                        <input type="file" name="image" />
                    </div>
                    <div class="contentLabel">
                        <label>Post Content:</label>
                    </div>
                    <div class="contentInput">
                        <textarea name="post-content"></textarea>
                    </div>
                    <div class="postBtn">
                        <button class="btn btn-primary btn-lg">Post</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>