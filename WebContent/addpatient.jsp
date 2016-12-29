<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.eman.patientrecord.model.Workup" %> 
<%@ include file="header.jsp" %>


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
<body class="patient-info-body">

<div class="ui container">
	<div id="bottom-container" class="ui raised segment">
	
	<div class="ui pointing menu">
		  <a class="item" href="<c:url value="/Home"/>">
		  <i class="home icon"></i>
		    Home
		  </a>
		  <a class="item active">
		  	<i class="add user icon"></i>
		    New Patient Record
		  </a>
		  <div class="right menu">
		  </div>
	</div>
	
	<h1 class="ui center aligned dividing header">
	<i class="add user icon"></i>
	Create Patient Record
	</h1>
	
	<div class="ui segment">
		<c:choose>
		<c:when test="${empty requestScope.addMsg}">
		
		</c:when>
		<c:when test="${requestScope.addMsg}">
		<div class="ui success message success-msg">
		  <i class="close icon"></i>
			  <div class="header">
			    Patient Information successfully added.
			  </div>
		  <p>To view newly created patient information, click
		  <a href="<c:url value="/PatientInfo?pid=${requestScope.caseNo}" />">here</a>.
		  To create new patient info, simply close this message.
		  </p>
		</div>
		</c:when>
		
		<c:otherwise>
		  <div class="ui negative message failed-msg">
		  <i class="close icon"></i>
			  <div class="header">
			    Creation failed.
			  </div>
		  <p>Database Error. </p>
		  </div>	
		</c:otherwise>
		</c:choose>
		<form class="ui form" action="<c:url value="/AddPatient" />" method="post">
		<div class="field">

		
		  <div class="ui grid">
		  <div class="two column row">
		  <div class="left floated twelve wide column">
			<h4 class="ui dividing header">
			<i class="write square icon"></i>
			Patient Information
			</h4>
			
			<div class="two fields">
				<div class="five wide field">
					<label>Case Number:</label> <input id="caseNo" type="text" name="caseNo" value="${applicationScope.nextCaseNo}" readonly="readonly">
				</div>
				<div class="five wide field">
					<label>Attending Physician:</label> <input id="doc" type="text" name="attPhys" value="Dr. ${sessionScope.doc_name}" readonly="readonly">
				</div>
			</div>
	
			<div class="field">
				<div class="two fields">
					<div class="eight wide field">
					<label>Last name*</label>
					<input class="clearable" type="text" placeholder="Lastname" name="lastname" required="required">
					</div>
					<div class="eight wide field">
					<label>First name*</label>	
					<input class="clearable" type="text" placeholder="Firstname" name="firstname" required="required">
					</div>
				</div>
			</div>
			<div class="field">
				<div class="three fields">
					<div class="four wide field">
						<label>Sex*</label>
						<select class="clearable" name="sex" >
							<option>Male</option>
							<option>Female</option>
						</select>
					</div>
					<div class="five wide field">
						<label>Date of birth*</label>
						<input class="clearable" type="date" name="dob" required="required">
					</div>
					<div class="seven wide field">
						<label>Home town/city*</label>
						<input class="clearable" type="text" placeholder="Enter town/city" name="address" required="required">
					</div>
				</div>
			</div>
		  </div>
		  <div class="right four wide column">
		  	<div class="ui segment">
	  			<img class="ui centered small image" src="http://www.iconshock.com/img_vista/WINDOWS8/medical/jpg/patient_icon.jpg">
			</div>
			
		  </div>
		  </div>
		  </div>
		</div>
			<div class="field">
				<div class="sixteen wide field">
					<label>Diagnosis</label>
					<input class="clearable" type="text" placeholder="Enter Diagnosis" name="diagnosis">
				</div>
			</div>
			<h4 class="ui dividing header">
			<i class="heartbeat icon"></i>
			Patient Workup Information
			</h4>
			
			<table class="ui striped table">
				<thead>
			    <tr>
			      <th>Workup</th>
			      <th>Frequency</th>
			    </tr>
	 			</thead>
	 			<tbody>
	 				<c:forEach var="workup" items="${applicationScope.workups}">
	 					<tr id="workup-row-${workup.id}">
	 					  <td>
	 					  	<div class="ui checkbox">
	 						  <input id="cbox-${workup.id}" class="workupitem clearable" type="checkbox" tabindex="0">
	 						  <label><c:out value="${workup.name}" /></label>
	 						</div>
	 					  </td>
	 					  <td>
					  	    <div class="ui checkbox">
	 				  	      <input class="ui disabled input clearable" id="freq-${workup.id}" placeholder="0" type="number" size="5" name="freq${workup.id}" disabled>
						    </div>
					      </td>
	 					</tr>
	 				</c:forEach>
	 				
	 			
			</table>
			<div class="ui grid">
				<div class="one column row">
					<div class="right floated column">
						<input type="submit" class="ui primary right floated button" value="Save" name="addButton">
						
						<a href="<c:url value="/Home" />" onclick="return confirm('Are you sure you want to discard changes?');">
						<input type="button" class="ui right floated button" value="Discard">
						</a>
						
						<input type="button" class="ui left floated button add-clear" value="Clear Text Fields">
					</div>
				</div>
			</div>
			
		</form>
		
		</div>
	</div>
</div>	
</body>
</html>