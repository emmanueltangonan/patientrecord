<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.eman.patientrecord.model.Workup" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="semantic.min.js"></script>
<script type="text/javascript" src="app.js"></script>
<link rel="stylesheet" href="semantic.min.css">
<link rel="stylesheet" href="app.css">

<title>Add Patient Record</title>
</head>
<body>
<div class="ui container">
	<div class="ui raised segment">
		
		<div class="ui grid">
		<div class="three column row">
		    <div class="left floated eight wide column">
				<h1>
					<i class="heartbeat icon"></i>
					Department of ${sessionScope.doc_department}
					<div class="sub header">
					<h4>University of the Philippines Hospital</h4>
					</div>
				</h1>
				
		    </div>
		   
		   
		     <div class="right floated five wide column">
			     <div class="right floated four wide column top-options">
			     	<a style="cursor:pointer" href="<c:url value="/LogoutServlet"/>">
			    	Sign out 
			    	</a> |
			    	<a href="#">
			    	Help
			    	</a> |
			    	<a href="#">
			    	About
			    	</a>
			     </div>
		     <div class="right floated four wide column">
		    	
				<h4 class="ui right floated header signed_h2">
				Signed in as:
				  <!--img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQPJM4YvjzSCCDoQdLrpE1OP4CjT6kr8P-HMy8FfTjfhMgLpjPE03WXtw" class="ui circular image"-->
				  <i class="doctor icon"></i>
				  ${sessionScope.doc_name}
				</h4>
		    </div>
		    </div>
		</div>
		
		</div>
	</div>
</div>

</body>
</html>