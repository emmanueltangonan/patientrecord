<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="semantic.min.js"></script>
<script type="text/javascript" src="app.js"></script>
<link rel="stylesheet" href="semantic.min.css">
<link rel="stylesheet" href="app.css">

<title>Patients Records Log in</title>
</head>

<body class="login-body">

<div class="ui huge header">
	<h2 class="ui center aligned icon header">
		<i class="circular doctor icon"></i>
		<div class="content">
		    Patient Records System
		    <div class="sub header">Organize your patient records <br/><b>(Simple CRUD App using Java Servlet and JSP, running on Tomcat Server and MySQL Database)</b></div>
		 </div>
	</h2>
</div><br/>

<div class="ui one column center aligned grid">
  <div class="column four wide form-holder">
    <h2 class="center aligned header form-head">Sign in</h2>
    <p>System-provided username and password. <br/> Use default account (user = pepe, password = 1234)</p>
    <div class="ui form">
    
    <c:if test="${requestScope.error == true}">
    <div class="ui negative message failed-msg">
		<i class="close icon"></i>
		<div class="header">
			Login failed.
		</div>
		<p>Incorrect username or password. </p>
	</div>
    </c:if>
    
    <form action="Login" method="post">
      <div class="field login-field">
        <div id="user-login" class="ui left icon input">
          <input type="text" placeholder="Username" name="username" required="required">
          <i class="user icon"></i>
        </div>
      </div>
      <div class="field login-field">
        <div id="pass-login" class="ui left icon input">
          <input type="password" placeholder="Password" name="password" required="required">
          <i class="lock icon"></i>
        </div>
      </div>
      <div id="btn-login-div" class="field login-field">
        <input id="btn-login" type="submit" value="Log in" class="ui button large fluid green">
      </div>
      <div class="inline field">
     </form>
      </div>
    </div>
  </div>
</div>



</body>
</html>