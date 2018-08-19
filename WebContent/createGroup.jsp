<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/add-group.css"/>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
<%@include file="includes/header.html" %>
<div class="wrapper">
	<div class="addGrp">
		<div class="title">
			<h2>Create New Group</h2>
		</div>
		<form action="socialController" method="post" enctype="multipart/form-data">
			<input type="hidden" value="newGroup" name="command"/>
			
 			<div class="group-form">
				<div class="nameLabel">
                        <label>Group Name:</label>
                    </div>
                    <div class="nameInput">
                        <input type="text" name="grp-name" />
                    </div>
                    <div class="imageLabel">
                        <label>Set Profile Image:</label>
                    </div>
                    <div class="imageInput">
                        <input type="file" name="grp-image" />
                    </div>
                    <div class="descriptionLabel">
                        <label>Group Description:</label>
                    </div>
                    <div class="descriptionInput">
                        <textarea name="grp-description"></textarea>
                    </div>
                    <div class="grpBtn">
                        <button class="btn btn-primary btn-lg">Create</button>
                    </div>
          
			</div>
		</form>
	</div>
</div>
</body>
</html>