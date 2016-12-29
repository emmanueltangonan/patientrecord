<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<title>Patient Information</title>
</head>
<body class="patient-info-body">

<div class="ui container">
	<div id="patient-profile-container" class="ui raised segment">
		<h1 class="ui center aligned dividing header">
		<i class="treatment icon"></i>
		Patient Information
		</h1>
		<div class="ui pointing menu">
			<a class="item" href="<c:url value="/Home"/>">
			<i class="home icon"></i>
		    	Home
		  	</a>
			<a class="item" href="<c:url value="/Initialize"/>">
			<i class="add user icon"></i>
		  		New Patient Record
			</a>
			<div class="right menu">
			</div>
		</div>
		<c:set var="patient" scope="page" value="${requestScope.patient}"/>
		
		<div class="ui column grid">
		  <div class="column">
		    <div class="ui raised segment">
		    	<div class="ui two column grid">
				  <div class="eight wide column">
				    <div class="ui raised segment">
				    
				      <a href="<c:url value="/RenderUpdateForm?pid=${patient.caseNo}" />" class="ui blue ribbon label">Update</a> 
				      
				      <h4 class="ui horizontal divider header">
						  <i class="user icon"></i>
						  Personal Information
						</h4>
					<img class="ui centered tiny image" src="http://www.iconshock.com/img_vista/WINDOWS8/medical/jpg/patient_icon.jpg">
						<table class="ui definition table">
						  <tbody>
						    <tr>
						      <td class="five wide column">Name</td>
						      <td>${patient.lastName}, ${patient.firstName }</td>
						    </tr>
						    <tr>
						      <td>Date of Birth</td>
						      <td>${patient.dob }</td>
						    </tr>
						    <tr>
						      <td>Age</td>
						      <td>${patient.age }</td>
						    </tr>
						    <tr>
						      <td>Sex</td>
						      <td>${patient.sex == "m" ? "Male" : "Female"}</td>
						    </tr>
						    <tr>
						      <td>Address</td>
						      <td>${patient.address }</td>
						    </tr>
						    <tr>
						      <td>Diagnosis</td>
						      <td>${patient.diagnosis == "" ? "None yet" : patient.diagnosis}</td>
						    </tr>
						  </tbody>
						</table>
				    </div>
				  </div>
				  
				  <div class="eight wide column">
				    <div class="ui raised segment bill-info">
				    <a class="ui blue ribbon label" href="<c:url value="/RenderUpdateForm?pid=${patient.caseNo}" />">Update</a> 
				    <h4 class="ui horizontal divider header">
						<i class="money icon"></i>
						  Partial Billing Information
					</h4>
		
					<table class="ui striped table">
					  <thead>
					    <tr>
					      <th>Item</th>
					      <th>Price (Php)</th>
					      <th class="center aligned">Frequency</th>
					      <th class="right aligned">Amount</th>
					    </tr>
					  </thead>
					  <tbody>
					  
					  <c:set var="workupslist" scope="page" value="${applicationScope.workups}"/>
					  <c:set var="total" value="${0}" />
					  <c:choose>
					  <c:when test="${not empty requestScope.ptwList}">
					  	<c:forEach var="ptw" items="${requestScope.ptwList}" >
					  	  <tr class="left aligned">
					  	  <c:set var="wid" value="${ptw.wid}"/>
					  	  <c:set var="amount" value="${workupslist[wid-1].price * ptw.frequency}" />
					  	  <c:set var="total" value="${total + amount}"/>
					      <td>${workupslist[wid-1].name}</td>
					      <td><fmt:formatNumber pattern="#,###.00" value="${workupslist[wid-1].price}"/></td>
					      <td class="center aligned">${ptw.frequency}</td>
					      <td class="right aligned"><fmt:formatNumber pattern="#,###.00" value="${amount}"/></td>
					      </tr>
					  	</c:forEach>
					  </c:when>
					  <c:otherwise>
					  	<td>No Workups Yet</td>
					  </c:otherwise>
					  </c:choose>
					    
					   </tbody>
					    <tfoot>
						    <tr>
						    <th></th><th></th>
						    <th class="left aligned"><b>Total Amount:</b></th><th class="right aligned"><b>Php <fmt:formatNumber pattern="#,##0.00" value="${total}"/></b></th>
						    </tr>
					    </tfoot>
					  
					</table>
						
					</div>		      	
			      </div>
				
				</div>
				
				
		    </div>
		  </div>
		  
		</div>
	
	</div>	
</div>

</body>
</html>