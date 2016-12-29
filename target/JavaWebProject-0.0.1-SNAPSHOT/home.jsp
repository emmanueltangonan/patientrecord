<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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


<title>Patient Record System</title>
</head>

<body class="patient-info-body">

<div class="ui container">
		<div id="patients-container" class="ui raised segment">
		<h1 class="ui center aligned dividing header">
		<i class="users icon"></i>
		All Patient Records
		</h1>
		<div class="ui pointing menu">
		  <a class="${not empty requestScope.patientList ? '' : 'active'} item" 
		  href="<c:url value="/Home" />">
		  <i class="home icon"></i>
		    Home
		  </a>
		  <a class="item" href="<c:url value="/Initialize"/>" >
		  <i class="add user icon"></i>
		    New Patient Record
		  </a>
		  <div class="right menu">
		    <div class="item">
		      <div class="ui transparent icon input">
		      <form action="<c:url value="/SearchPatient"/>" method="post">
		        <input class="ui input focus" type="text" placeholder="Search patient name..." name="searchInput" value="${requestScope.searchInput}">
		        <button class="ui primary button" type="submit" >Search</button>
		       </form>

		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="ui segment">
			
			<c:if test="${requestScope.isDeleted}">
			<div class="ui success message success-msg">
			  <i class="close icon"></i>
				  <div class="header">
				    Patient Information successfully deleted.
				  </div>
			  <p>List of patients has been updated. </p>
			</div>
			</c:if>
			
			<div class="ui divided link items">
			<c:choose>
			<c:when test="${not empty requestScope.patientList}">
				<c:set var="list" value="${requestScope.patientList}" />
			</c:when>
			<c:when test="${not empty sessionScope.allPatients and empty requestScope.patientList}">
				<c:set var="list" value="${sessionScope.allPatients}" />
			</c:when>
			<c:otherwise>
				<div class="ui message">
				  <div class="header">
				    You've got no records to show.
				  </div>
				  <p>To create a patient record, click on the "New Patient Record" tab above or you may click
				  <a href="<c:url value="/InitializeAdd" />">here</a>.
				  </p>
				</div>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${empty list[0]}">
				<div class="ui message">
				  <div class="header">
				    No records found.
				  </div>
				  <p>Click
				  <a href="<c:url value="/Home" />">here</a> to view all records.
				  </p>
				</div>
			</c:when>
			<c:otherwise>
			<c:forEach var="patient" items="${list}">
				<c:set var="sex" value="${patient.sex == 'm' ? 'Male' : 'Female'}"/>
			 	
				  <div class="item">
				  	
				    <div class="ui tiny image">
				      <img src="http://www.iconshock.com/img_vista/WINDOWS8/medical/jpg/patient_icon.jpg">
				    </div>
				    <div class="content">
					    <a href="<c:url value="/PatientInfo?pid=${patient.caseNo}" />">
					  	</a>
				      <div class="header">${patient.lastName}, ${patient.firstName}</div>
				      <div class="description">
				      	<p>${patient.age} years old /${sex} / Case No.: ${patient.caseNo}</p>
				        <p>Diagnosis: ${patient.diagnosis}</p>
				      </div>
				    </div>
				  
				   <div class="right floated center aligned row">
				   	 <a href="<c:url value="/RenderUpdateForm?pid=${patient.caseNo}" />">
					   <div class="ui green button">
					   <i class="edit icon"></i>
					   Update Record
					   </div>
					 </a>
				   </div>
				   <div class="right floated center aligned row">
				     <a onclick="return confirm('Are you sure you want to delete this record?');"
				     href="<c:url value="/DeleteServlet?pid=${patient.caseNo}" />">
					   <div class="ui grey button remove-user-btn">
					   <i class="remove user icon"></i>
					   Delete Record
					   </div>
					 </a>  
				   </div>
				  
				  </div>
				  
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</div>
			
		</div>
	</div>
</div>	




</body>

</html>
