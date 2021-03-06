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

<title>Update Patient Record</title>
</head>
<body class="patient-info-body">

<div class="ui container">
	<div id="bottom-container" class="ui raised segment">
	<h1 class="ui center aligned dividing header">
	<i class="write icon"></i>
	Update Patient Record
	</h1>
	<div class="ui pointing menu">
		  <a class="item" href="<c:url value="/Home"/>">
		  <i class="home icon"></i>
		    Home
		  </a>
		  <a class="item" href="<c:url value="/InitializeAdd"/>" >
		  	<i class="add user icon"></i>
		    New Patient Record
		  </a>
		  <div class="right menu">
		  </div>
	</div>
	<div class="ui segment">
		<c:choose>
		<c:when test="${empty requestScope.addMsg}">
		
		</c:when>
		<c:when test="${requestScope.addMsg}">
		<div class="ui success message success-msg">
		  <i class="close icon"></i>
			  <div class="header">
			    Patient Information successfully updated.
			  </div>
		  <p>Updated list can now be viewed on the home page. To view updated patient information, click 
		  <a href="<c:url value="/PatientInfo?pid=${patient.caseNo}" />">here</a>.
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
		<form class="ui form" action="<c:url value="/UpdateRecord" />" method="post">
		<div class="field">

		
		  <div class="ui grid">
		  <div class="two column row">
		  <div class="left floated twelve wide column">
			<h4 class="ui dividing header">Patient Information</h4>
			
			<div class="two fields">
				<div class="five wide field">
					<label>Case Number:</label> <input id="caseNo" type="text" name="caseNo" value="${patient.caseNo}" readonly="readonly">
				</div>
				<div class="five wide field">
					<label>Attending Physician:</label> <input id="doc" type="text" name="attPhys" value="Dr. ${sessionScope.doc_name}" readonly="readonly">
				</div>
			</div>
	
			<div class="field">
				<div class="two fields">
					<div class="eight wide field">
					<label>Last name*</label>
					<input class="clearable" type="text" placeholder="Lastname" name="lastname" value="${patient.lastName}">
					</div>
					<div class="eight wide field">
					<label>First name*</label>	
					<input class="clearable" type="text" placeholder="Firstname" name="firstname" value="${patient.firstName}">
					</div>
				</div>
			</div>
			<div class="field">
				<div class="three fields">
					<div class="four wide field">
						<label>Sex*</label>
						<select class="clearable" name="sex" >
							<option ${patient.sex == 'm' ? 'selected' : ''}>Male</option>
							<option ${patient.sex == 'f' ? 'selected' : ''}>Female</option>
						</select>
					</div>
					<div class="five wide field">
						<label>Date of birth*</label>
						<input class="clearable" type="date" name="dob" value="${patient.dob}">
					</div>
					<div class="seven wide field">
						<label>Home town/city*</label>
						<input class="clearable" type="text" placeholder="Enter town/city" name="address" value="${patient.address}">
					</div>
				</div>
			</div>
		  </div>
		  <div class="right four wide column">
		  	<div class="ui segment">
	  			<img class="ui centered medium image" src="http://sugartin.info/wp-content/uploads/2013/11/logo.png">
			</div>
		  </div>
		  </div>
		  </div>
		</div>
			<div class="field">
				<div class="sixteen wide field">
					<label>Diagnosis</label>
					<input class="clearable" type="text" placeholder="Enter Diagnosis" name="diagnosis" value="${patient.diagnosis}">
				</div>
			</div>
			<h4 class="ui dividing header">Patient Workup Information</h4>
			
			<table class="ui striped table">
				<thead>
			    <tr>
			      <th>Workup</th>
			      <th>Frequency</th>
			    </tr>
	 			</thead>
	 			<tbody>
	 			<c:set var="ptwMap" value="${requestScope.ptwMap}" />
	 				<c:forEach var="workup" items="${applicationScope.workups}">
	 				<c:set var="wid" value="${workup.id}" />
	 					<tr id="workup-row-${wid}">
	 					  <td>
	 					  	<div class="ui checkbox">
	 						  <input id="cbox-${wid}" class="workupitem clearable" type="checkbox" tabindex="0" ${not empty ptwMap[wid] ? "checked" : ""}>
	 						  <label><c:out value="${workup.name}" /></label>
	 						</div>
	 					  </td>
	 					  <td>
					  	    <div class="ui checkbox">
	 				  	      <input class="ui input clearable" id="freq-${wid}" placeholder="0" type="number" size="5" name="freq${wid}" value="${ptwMap[wid]}" ${empty ptwMap[wid] ? "disabled" : ""}>
						    </div>
					      </td>
	 					</tr>
	 				</c:forEach>
	 				
	 			</tbody>
	 			
			</table>
			<div class="ui grid">
				<div class="one column row">
					<div class="right floated column">
						<input type="submit" class="ui primary right floated button" value="Update" name="addButton">
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