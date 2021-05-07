<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.ArrayList"%>



<!DOCTYPE html>

<html>

<head>
<title>Update Project Details</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>
body {
	background: #FF0099; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #493240, #FF0099);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #493240, #FF0099);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.topnav {
	overflow: hidden;
	background-color: black;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 20px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a.logout {
	float: right;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

.btn-outline-success {
	color: #28a745;
	border-color: #28a745;
	margin-left: 35px;
}

.gradient-multiline {
  position: relative;
  width: 400px;
  margin: 1px auto;
  background-color: #fff;
  text-align: center;
  line-height: 1.5em;
  overflow:hidden;
}
  
.gradient-multiline span {
  color: #fff;
  background-color: #000;

  padding: 0.225rem 0.5rem;

  /* Needs prefixing */
  -webkit-box-decoration-break: clone;
  box-decoration-break: clone;
}

@supports(mix-blend-mode: lighten) {
  
  .gradient-multiline::after {
    position: absolute;
    content: '';
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    pointer-events: none;
    background: linear-gradient(60deg, #cf521d, #00ceff);

    mix-blend-mode: lighten;
  }
  
}

.gradient-multiline-1 {
  position: relative;
  width: 400px;
  margin: 1px auto;
  background-color: #fff;
  text-align: center;
  line-height: 1.5em;
  overflow:hidden;
}
  
.gradient-multiline-1 span {
  color: #fff;
  background-color: #000;

  padding: 0.225rem 0.5rem;

  /* Needs prefixing */
  -webkit-box-decoration-break: clone;
  box-decoration-break: clone;
}

@supports(mix-blend-mode: lighten) {
  
  .gradient-multiline-1::after {
    position: absolute;
    content: '';
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    pointer-events: none;
    background: linear-gradient(60deg, #a82a56, #00ceff);

    mix-blend-mode: lighten;
  }
  
}

.gradient-multiline-2 {
  position: relative;
  width: 400px;
  margin: 1px auto;
  background-color: #fff;
  text-align: center;
  line-height: 1.5em;
  overflow:hidden;
}
  
.gradient-multiline-2 span {
  color: #fff;
  background-color: #000;

  padding: 0.225rem 0.5rem;

  /* Needs prefixing */
  -webkit-box-decoration-break: clone;
  box-decoration-break: clone;
}

@supports(mix-blend-mode: lighten) {
  
  .gradient-multiline-2::after {
    position: absolute;
    content: '';
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    pointer-events: none;
    background: linear-gradient(60deg, #1dcf20, #00ceff);

    mix-blend-mode: lighten;
  }
  
}

</style>

</head>

<body>

	<header>
		<div class="topnav">
			<a class="navbar-brand"
				href="https://www.nablainfotech.com/index.html"> <img
				src="https://www.nablainfotech.com/images/nabala_logo.png"
				alt="logo" style="width: 50px;">
			</a> <a class="active" href="#home">Home</a> <a
				href="https://www.nablainfotech.com/index.html">About</a> <a
				class="logout" href="/home">GO BACK</a>
		</div>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="/updateProject" method="post" id="myForm">



					<br>
					<h1 class="gradient-multiline-1">
						<span>Project Details</span>
					</h1>
					<br> <br>

					<div>
						<fieldset class="form-group">
							<label>Project Id</label> <input type="text"
								value="${projectDetails.projectId }" class="form-control"
								name="projectId" readonly="readonly">
						</fieldset>

						<fieldset class="form-group">
							<label>Project Name</label> <input type="text"
								value="${projectDetails.projectName }" class="form-control"
								name="projectName" required="required">
						</fieldset>
						<fieldset class="form-group">
							<label>Project Description</label>
							<textarea id="projectDescription" name="projectDescription"
								rows="4" cols="75">${projectDetails.projectDescription }</textarea>

						</fieldset>

						<fieldset class="form-group">
							<label>Project Owner</label> <input type="text"
								value="${projectDetails.projectOwner }" class="form-control"
								name="projectOwner" required="required">
						</fieldset>


						<fieldset class="form-group">
							<label for="ProjectStatus">Project Status </label> <select
								class="form-control" id="status" name="status">
								<option value="${projectDetails.status }" selected>${projectDetails.status }</option>
								<c:forEach var="item" items="${statusEnum}">
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
						</fieldset>

						<fieldset class="form-group">
							<label>Start Date</label> <input type="date"
								value="${projectDetails.startDate }" class="form-control"
								name="startDate" id="startDate" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label>End Date</label> <input type="date"
								value="${projectDetails.endDate }" class="form-control"
								name="endDate" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label>Modified Date</label> <input type="text"
								value="${projectDetails.modifiedDate }" class="form-control"
								name="modifiedDate" required="required" readonly="readonly">
						</fieldset>



						<br>
						<h2 class="gradient-multiline">
							<span>Technology Details</span>
						</h2>
						<br>

						<fieldset class="form-group">
							<label>Programming Language</label> <input type="text"
								value="${projectDetails.technology.language }"
								class="form-control" name="language" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label>Framework</label> <input type="text"
								value="${projectDetails.technology.framework }"
								class="form-control" name="framework" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label for="ProjectType">Project Type </label> <select
								class="form-control" id="projectType" name="projectType">
								<option value="${projectDetails.technology.projectType }"
									selected>${projectDetails.technology.projectType }</option>
								<c:forEach var="item" items="${projectType}">
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
						</fieldset>

						<br>
						<h3 class="gradient-multiline-2">
							<span>Database</span>
						</h3>
						<br>

						<c:forEach items="${projectDetails.technology.database }"
							var="databases">


							<fieldset class="form-group">
								<label>Database Name </label> <input type="text"
									value="${databases.databaseName }" class="form-control"
									name="databaseName" required="required">
							</fieldset>
						</c:forEach>

						<br>
						<h2 class="gradient-multiline">
							<span>Team Details</span>
						</h2>
						<br>

						<fieldset class="form-group">
							<label>Project Leader</label> <input type="text"
								value="${projectDetails.teamDetails.projectLeader }"
								class="form-control" name="projectLeader" required="required">
						</fieldset>
						<fieldset class="form-group">
							<label>Project Assigned</label> <input type="text"
								value="${projectDetails.teamDetails.projectAssigned }"
								class="form-control" name="projectAssigned" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label>Team Size</label> <input type="text"
								value="${projectDetails.teamSize }" class="form-control"
								name="teamSize" required="required">
						</fieldset>

						<c:forEach
							items="${projectDetails.teamDetails.teamMembersDetails }"
							var="teamMembersDetails">

							<br>
							<h3 class="gradient-multiline-2">
								<span>Team Members Detail</span>
							</h3>
							<br>
							
							

							<fieldset class="form-group">
								<label>Employee Full Name</label> <input type="text"
									value="${teamMembersDetails.employeeFullName }"
									class="form-control" name="employeeFullName"
									required="required">
							</fieldset>

							<fieldset class="form-group">
								<label>First Name</label> <input type="text"
									value="${teamMembersDetails.firstName }" class="form-control"
									name="firstName" required="required">
							</fieldset>
							<fieldset class="form-group">
								<label>Middle Name</label> <input type="text"
									value="${teamMembersDetails.middleName }" class="form-control"
									name="middleName" required="required">
							</fieldset>
							<fieldset class="form-group">
								<label>Last Name</label> <input type="text"
									value="${teamMembersDetails.lastName }" class="form-control"
									name="lastName" required="required">
							</fieldset>

							<fieldset class="form-group">
								<label>Mobile Number</label> <input type="text"
									value="${teamMembersDetails.mobileNumber }"
									class="form-control" name="mobileNumber" required="required">
							</fieldset>

							<fieldset class="form-group">
								<label for="position">Position</label> <select
									class="form-control" id="position" name="position">
									<option value="${teamMembersDetails.position }" selected>${teamMembersDetails.position }</option>
									<c:forEach var="item" items="${position}">
										<option value="${item}">${item}</option>
									</c:forEach>
								</select>
							</fieldset>
							<fieldset class="form-group">
								<label>Years Of Experience</label> <select class="form-control"
									id="yearsOfExperience" name="yearsOfExperience">
									<option value="${teamMembersDetails.yearsOfExperience }"
										selected>${teamMembersDetails.yearsOfExperience }</option>
									<c:forEach var="item" items="${rangeYearsOfExperience}">
										<option value="${item}">${item}</option>
									</c:forEach>
								</select>
							</fieldset>

						</c:forEach>






						<br>
						<div>
							<button type="submit" class="btn btn-outline-success"
								data-mdb-toggle="button">Save</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-outline-warning" id="reset" name="reset"
								onclick="this.form.querySelectorAll('input[type=text]').forEach(function(input,i){input.value='';})">Clear
								All fields</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-outline-dark" type="reset" value="Reset">Reset</button>
							&nbsp;&nbsp;&nbsp;&nbsp; <a href="/home"
								class="btn btn-outline-danger">Back to Project Details</a>
						</div>
				</form>
			</div>
		</div>
	</div>

</body>

</html>