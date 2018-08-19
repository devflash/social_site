<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Site</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/search-group.css"/>
</head>
<body>
<%@include file="includes/header.html" %>

	<c:set var="error" value="${ERROR}"></c:set>
<div class="wrapper">
	<c:choose>
		<c:when test="${error==0 }">
		<div id="grouplist">
			<table class="table table-striped">
		<tr>
			<th scope="col">Group Name</th>
			<th scope="col">Description</th>
			<th scope="col">Action</th>
		</tr>
		<c:forEach var="groupInfo" items="${GROUP_LIST}">
			<c:url var="addGroup" value="socialController">
				<c:param name="grpId" value="${groupInfo.grpid}"/>
				<c:param name="stuId" value="${USER_DETAILS.uid}"/>
				<c:param name="command" value="addGroup"/>
			</c:url>
			<tr scope="row">
				<td>${groupInfo.grpName}</td>
				<td>${groupInfo.description}</td>
				<td><a href="${addGroup}" onclick="alert("Group has been added")">Add</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</c:when>
		<c:when test="${error==1 }">
			<div id="error">
				<p>Group is already added</p>
			</div>
		</c:when>
		<c:otherwise>
			<p>Something is wrong</p>
		</c:otherwise>
	</c:choose>
		
</div>
</body>
</html>