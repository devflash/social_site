<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/index.css">
<title>Welcome to social site</title>
</head>

<body>
	
<div class="wrapper">
                <div class="site-title">
                	<div style="min-width:292px;">
                    <h1>Social Site</h1>
                    </div>
                </div>
                <c:set var="error" value="${ERROR_STATUS}"></c:set>
                <c:if test="${error==1}">
                <div class="error">
        			<c:set var="status" value="${ACC_STATUS}"></c:set>
                        <c:if test="${STATUS==false}">
                            <p>Invallid user</p>
                        </c:if>
                         <c:if test="${status==2}">
                        <p>Account is created</p>
                    </c:if>
                    <c:if test="${status==0}">
                        <p>Password mismatch</p>
                    </c:if>
                    <c:if test="${status==1}">
                        <p>Account with this email address is already present</p>
                    </c:if>
                    
                        <c:if test="${STATUS=='' }"></c:if>
                    </div>
        		</c:if>
                <div class="login">
                   <div class="header">
                        <h2>Welcome back!</h2>
                        <p>No account yet?<a href="#" class="signupLink">Sign up for free</a></p>
                    </div>
        
                    <form action="socialController" method="post">
                        <div class="login-form">
                            <input type="hidden" value="validate" name="command" />
        
                            <div class="emailLabel">
                                <label>Email Address:</label>
                            </div>
                            <div class="emailInput">
                                <input type="email" name="email" required />
                            </div>
                            <div class="passLabel">
                                <label>Password:</label>
                            </div>
                            <div class="passInput">
                                <input type="password" name="password" required />
                            </div>
                            <div class="loginBtn">
                                <button value="login" class="loginButton">Login</button>
                               
                            </div>
        
        
                        </div>
                    </form>
        
                </div>
                <div class="signup">
                
                   <div class="header">
                        <h2>Create Account</h2>
                        <p>Already have an account?<a href="#" class="loginLink">Login</a></p>
                    </div>
                    <form action="socialController" method="post">
                        <div class="sign-form">
                            <input type="hidden" value="addUser" name="command" />
                            <div class="fLabel">
                                <label>First Name:</label>
                            </div>
                            <div class="fInput">
                                <input type="text" name="first-name" required />
                            </div>
                            <div class="lLabel">
                                <label>Last Name:</label>
                            </div>
                            <div class="lInput">
                                <input type="text" name="last-name" required />
                            </div>
                            <div class="eLabel">
                                <label>Email Address:</label>
                            </div>
                            <div class="eInput">
                                <input type="email" name="email" required />
                            </div>
                            <div class="pLabel">
                                <label>Password:</label>
                            </div>
                            <div class="pInput">
                                <input type="password" name="setPassword" required/>
                            </div>
                            <div class="cLabel">
                                <label>Confirm password:</label>
                            </div>
                            <div class="cInput">
                                <input type="password" name="confirmPassword" required />
                            </div>
                            <div class="createBtn">
                                    <button>Create</button>
                                    
                            </div>
                            
                        </div>
                    </form>
                </div>
            </div>
        
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
          <script src="js/script.js"></script>
</body>
</html>