<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page isELIgnored="false"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Libre+Baskerville|Marcellus'>

<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('.eBtn').on('click', function(event) {
							$(".databaseNameDiv").remove();
							event.preventDefault();
							var href = $(this).attr('href');
							$.get(href, function(jsonProjectDetails, status) {
								var obj = $.parseJSON(jsonProjectDetails);
								$('#technologyLanguage').val(obj.technology.language);
								$('#technologyFramework').val(obj.technology.framework);
								$('#technologyProjectType').val(obj.technology.projectType);
								populateFormFields(jsonProjectDetails);
							});
							$('#exampleModal2').modal('show');

						});

						function populateFormFields(jsonProjectDetails) {

							var obj = $.parseJSON(jsonProjectDetails);
							(obj.technology.database).forEach(myFunction);
							function myFunction(value) {
								console.log(value);
								var newDatabaseName = $(
										document.createElement("div")).attr(
										"class", "form-group databaseNameDiv");

								newDatabaseName
										.after()
										.html(
												"<label>Database"
														+ " : </label>"
														+ '<input type="text" name="databaseName' +
            	          '" id="databaseNameValue' +
            	          '" class="form-control' +
            	          '" value="'+value.databaseName+'" >');

								newDatabaseName
										.appendTo("#technologyDetailsDiv");

							}

						}
					});

	$('#exampleModal2').on('hidden', function() {
		window.location.reload(true);
	})
</script>



<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('.teamDetailsBtn')
								.on(
										'click',
										function(event) {
											$(".teamMember").remove();
											$(".form-group label").remove();
											$("hr").remove();
											event.preventDefault();
											var href = $(this).attr('href');
											$
													.get(
															href,
															function(
																	jsonProjectDetails,
																	status) {
																var obj = $
																		.parseJSON(jsonProjectDetails);
																(obj.teamDetails.teamMembersDetails)
																		.forEach(myFunction);

																function myFunction(
																		value) {
																	var employeeFullName = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	employeeFullName
																			.after()
																			.html(
																					"<label>Employee Full Name:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="employeeFullName' +
            	          '" value="'+value.employeeFullName+'" >');

																	employeeFullName
																			.appendTo("#teamMemberDetailsDiv");

																	var firstName = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	firstName
																			.after()
																			.html(
																					"<label>First Name:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="firstName' +
            	          '" value="'+value.firstName+'" >');

																	firstName
																			.appendTo("#teamMemberDetailsDiv");

																	var middleName = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	middleName
																			.after()
																			.html(
																					"<label>Middle Name:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="middleName' +
            	          '" value="'+value.middleName+'" >');

																	middleName
																			.appendTo("#teamMemberDetailsDiv");

																	var lastName = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	lastName
																			.after()
																			.html(
																					"<label>Last Name:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="lastName' +
            	          '" value="'+value.lastName+'" >');

																	lastName
																			.appendTo("#teamMemberDetailsDiv");

																	var mobileNumber = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	mobileNumber
																			.after()
																			.html(
																					"<label>Mobile Number:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="mobileNumber' +
            	          '" value="'+value.mobileNumber+'" >');

																	mobileNumber
																			.appendTo("#teamMemberDetailsDiv");

																	var position = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	position
																			.after()
																			.html(
																					"<label>Posititon:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="position' +
            	          '" value="'+value.position+'" >');

																	position
																			.appendTo("#teamMemberDetailsDiv");

																	var yearsOfExperience = $(
																			document
																					.createElement("div"))
																			.attr(
																					"class",
																					"form-group");

																	yearsOfExperience
																			.after()
																			.html(
																					"<label>Years Of Experience:"
																							+ " : </label>"
																							+ '<input type="text" class="form-control teamMember' +
            	          '" id="yearsOfExperience' +
            	          '" value="'+value.yearsOfExperience+'" >'
																							+ '<hr>');

																	yearsOfExperience
																			.appendTo("#teamMemberDetailsDiv");

																}
															});
											$('#teamDetailsModal')
													.modal('show');

										});
					});
</script>




<style type="text/css">
#TextBoxesGroup {
	padding: 8px;
}

* {
	box-sizing: border-box;
}
</style>


<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet" />
<link rel="stylesheet" href="<c:url value=" /resources/css/main.css" />" />
<link rel="stylesheet"
	href="<c:url value=" /resources/css/styleProjectDetails.css" />" />


<title>Project Management</title>



<link rel="stylesheet"
	href="<c:url value="
					/resources/assets/plugins/bootstrap/css/bootstrap.min.css" />" />


<link rel="stylesheet"
	href="<c:url value=" /resources/assets/css/main.css" />" />
<link rel="stylesheet"
	href="<c:url value=" /resources/assets/css/theme1.css" />" />
<link rel="stylesheet"
	href="<c:url value=" /resources/css/font-awesome.css" />" />

</head>

<body class="font-montserrat">
	<div class="page-loader-wrapper">
		<div class="loader"></div>
	</div>

	<div id="main_content">
		

		<div id="myForm2">
			<div aria-hidden="true" aria-labelledby="exampleModalLabel"
				class="modal fade" id="exampleModal2" role="dialog" tabindex="-1">
				<div class="modal-dialog modal-sm">
					<div class="modal-content" style="text-align: center;">
						<div class="container" style="background-color: darkslategray;">
							<div class="card text-white bg-dark" style="max-width: 18rem;">
								<div class="card-header" style="padding-left: 60px;">
									<b>Technology Details</b>
								</div>
								<div class="card-body">
									<form>
										<div id="technologyDetailsDiv">
											<div class="form-group">
												<label for="language">Programming language:</label> <input
													type="text" class="form-control" id=technologyLanguage
													name="programmingLanguage">
											</div>
											<div class="form-group">
												<label for="framework">Framework:</label> <input type="text"
													class="form-control" id="technologyFramework" name="framework">
											</div>
											<div class="form-group">
												<label for="project-type">Project Type:</label> <input
													type="text" class="form-control" id="technologyProjectType"
													name="projectType">
											</div>

										</div>
									</form>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>



		<div class="modal fade" id="teamDetailsModal" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="teamDetailsTitle"
							style="padding-left: 165px;">Team Details</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">

						<div class="container" style="background-color: darkslategray;">
							<div class="card text-white bg-info" style="max-width: 50rem;">
								<div class="card-header" style="padding-left: 140px;">
									<b>Team Member Details</b>
								</div>
								<div class="card-body">

									<div class="teamMemberDetailsDiv" id="teamMemberDetailsDiv">

									</div>

								</div>
							</div>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>

		<div class="page">
			<div id="page_top" class="section-body top_dark">
				<div class="container-fluid">
					<div class="page-header row">
						<div class="col-7">
							<h1 class="page-title">Project Management</h1>
						</div>
						<div class="col-5">
							<div class="s003">
								<form>
									<div class="inner-form">
										<div class="input-field first-wrap">
											<div class="input-select">
												<select data-trigger="" name="choices-single-defaul">
													<option placeholder="">Choose One</option>
													<option>Project Id</option>
													<option>Project Name</option>
													<option>Project Status</option>
												</select>
											</div>
										</div>
										<div class="input-field second-wrap">
											<input id="search" type="text" placeholder="Enter Keywords" />
										</div>
										<div class="input-field third-wrap">
											<button class="btn-search" type="button">
												<svg class="svg-inline--fa fa-search fa-w-16"
													aria-hidden="true" data-prefix="fas" data-icon="search"
													role="img" xmlns="http://www.w3.org/2000/svg"
													viewBox="0 0 512 512">
																<path fill="currentColor"
														d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z">
																</path>
															</svg>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="section-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="d-flex justify-content-between align-items-center">
								<ul class="nav nav-tabs page-header-tab">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#ProjectDetails" role="tab">Project
											Details</a></li>
									<li class="nav-item "><a class="nav-link"
										data-toggle="tab" href="#CreateProjectTeam" role="tab">Create
											Project Team</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#Project-add" role="tab">Add Project Details</a></li>
									<li class="nav-item "><a class="nav-link"
										data-toggle="tab" href="#AllProjectDetails" role="tab">All
											Project Details</a></li>
								</ul>


								<ul class="nav navbar-nav navbar-right">
									<li class="nav-item dropdown justify-content-end"><a
										class="nav-link dropdown-toggle" data-toggle="dropdown"
										href="#">Filter Project Details</a>
										<div class="dropdown-menu">
											<a class="nav-link" data-toggle="tab" href="#Project-Working"
												role="tab">In Progress</a> <a class="nav-link"
												data-toggle="tab" href="#Project-Completed" role="tab">Completed</a>
											<a class="nav-link" data-toggle="tab" href="#Project-Pending"
												role="tab">Pending</a> <a class="nav-link" data-toggle="tab"
												href="#ProjectDetails" role="tab">All Project Details</a>
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="section-body mt-3">
				<div class="container-fluid">
					<div class="tab-content">
						<div class="tab-pane fade" id="CreateProjectTeam" role="tabpanel">
							<form action="/createTeam" method="POST" id="msformTeamMember"
								class="steps">
								<!-- progressbar -->
								<ul id="progressbarTeamMember">
									<li class="active">Team Details</li>
									<li>No. of Members</li>
									<li>Team Members Details</li>
								</ul>
								<!-- fieldsets -->
								<fieldset>
									<h2 class="fs-title">Team Details</h2>
									<h3 class="fs-subtitle">Details About The Team</h3>
									<input type="text" name="projectLeader"
										placeholder="Project Leader" /> <input type="text"
										name="projectAssigned" placeholder="Project Assigned" /> <input
										type="button" name="next" class="next action-button"
										value="Next" />
								</fieldset>
								<fieldset>
									<h2 class="fs-title">Team Size</h2>
									<h3 class="fs-subtitle">Number of members in the team</h3>
									<input type="text" name="teamSize" id="teamSize"
										placeholder="Team Size" /> <input type="button"
										name="previous" class="previous action-button-previous"
										value="Previous" /> <input type="button" name="next"
										class="next action-button" value="Next" />
								</fieldset>
								<fieldset>
									<h2 class="fs-title">Team Member Details</h2>

									<div class="clone">
										<h4>Member Detail</h4>
										<input type="text" name="employeeFullName"
											placeholder="Employee Full Name" /> <input type="text"
											name="firstName" placeholder="First Name" /> <input
											type="text" name="middleName" placeholder="Middle Name" /> <input
											type="text" name="lastName" placeholder="Last Name" /> <input
											type="text" name="mobileNumber" placeholder="Mobile Number" />
										<select id="position" name="position" class="form-control">
											<c:forEach var="item" items="${position}">
												<option value="${item}" class="positionOptions">${item}
												</option>
											</c:forEach>
										</select> <input type="text" name="yearsOfExperience"
											placeholder="Years Of Experience" />

									</div>
									<div id="newFields"></div>
									<input type="button" name="previous"
										class="previous action-button-previous" value="Previous" /> <input
										type="submit" name="submit" class="submit action-button"
										value="Submit" />
								</fieldset>
							</form>
						</div>

						<div class="tab-pane fade show active" id="ProjectDetails"
							role="tabpanel">
							<div class="row">
								<c:forEach items="${projectDetailsList }" var="projectDetails">

									<div class="col-lg-6 col-md-12">
										<div class="card">
											<div class="card-header">
												<h3 class="card-title">${projectDetails.projectName }</h3>
												<div class="card-options">
													<label class="custom-switch m-0"> <input
														type="checkbox" value="1" class="custom-switch-input"
														checked /> <span class="custom-switch-indicator"></span>
													</label> <a href="#" class="card-options-collapse"
														data-toggle="card-collapse"><i class="bi bi-trash"></i></a>
												</div>
											</div>

											<div class="card-body">
												<span class="tag mb-3 tag-project-type"
													style="color: white;"><b>${projectDetails.technology.projectType
																	}</b></span>
												<p>${projectDetails.projectDescription }</p>
												<div class="row">
													<div class="col-5 py-1">
														<strong>Project Owner:</strong>
													</div>
													<div class="col-7 py-1">${projectDetails.projectOwner }
													</div>
													<div class="col-5 py-1">
														<strong>Start Date:</strong>
													</div>
													<div class="col-7 py-1">${projectDetails.startDate }
													</div>
													<div class="col-5 py-1">
														<strong>End Date:</strong>
													</div>
													<div class="col-7 py-1">${projectDetails.endDate }</div>
													<div class="col-5 py-1">
														<strong>Technology Details:</strong>
													</div>
													<div class="col-7 py-1 table1">
														<strong><a class="btn btn-primary eBtn"
															style="background-color: #3b5998" role="button"
															href="/findById/${projectDetails.projectId }"
															type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																class="badge bg-danger ms-2">Get Details</span></a></strong>
													</div>
													<div class="col-5 py-1">
														<strong>Team Size:</strong>
													</div>
													<div class="col-7 py-1">
														<strong>${projectDetails.teamSize }</strong>
													</div>
													<div class="col-5 py-1">
														<strong>Team Details:</strong>
													</div>
													<div class="col-7 py-1">
														<strong><a class="btn btn-primary teamDetailsBtn"
															style="background-color: #485563" role="button"
															data-toggle="modal"
															href="/findById/${projectDetails.projectId }"
															type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																class="badge bg-danger ms-2">Get Team Details</span></a></strong>
													</div>
													<div class="col-5 py-1">
														<strong>Team:</strong>
													</div>
													<div class="col-7 py-1">
														<div class="avatar-list avatar-list-stacked">
															<c:forEach
																items="${projectDetails.teamDetails.teamMembersDetails }"
																var="teamMembersDetails">
																<img class="avatar avatar-sm"
																	src="https://ui-avatars.com/api/?name=${teamMembersDetails.firstName }+${teamMembersDetails.lastName }&background=random&bold=true"
																	data-toggle="tooltip"
																	title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }"
																	data-original-title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }" />
															</c:forEach>
														</div>
													</div>
												</div>
											</div>

											<div class="row card-footer">
												<div class="col-4">
													<button class="continue-application"
														onclick="location.href='/update/${projectDetails.projectId }'">
														<div>
															<div class="pencil"></div>
															<div class="folder">
																<div class="top">
																	<svg viewBox="0 0 24 27">
																					<path
																			d="M1,0 L23,0 C23.5522847,-1.01453063e-16 24,0.44771525 24,1 L24,8.17157288 C24,8.70200585 23.7892863,9.21071368 23.4142136,9.58578644 L20.5857864,12.4142136 C20.2107137,12.7892863 20,13.2979941 20,13.8284271 L20,26 C20,26.5522847 19.5522847,27 19,27 L1,27 C0.44771525,27 6.76353751e-17,26.5522847 0,26 L0,1 C-6.76353751e-17,0.44771525 0.44771525,1.01453063e-16 1,0 Z">
																					</path>
																				</svg>
																</div>
																<div class="paper"></div>
															</div>
														</div>
														Update Details
													</button>
												</div>
												<div class="col-4">
													<button class="button-delete"
														onclick="location.href='/delete/${projectDetails.projectId }'">
														<div class="icon">
															<svg class="top">
																			<use xlink:href="#top" />
																		</svg>
															<svg class="bottom">
																			<use xlink:href="#bottom" />
																		</svg>
														</div>
														<span>Delete</span>
													</button>

													<svg xmlns="http://www.w3.org/2000/svg"
														style="display: none;">
																	<symbol xmlns="http://www.w3.org/2000/svg"
															viewBox="0 0 24 24" id="top">
																		<path
															d="M15,4 C15.5522847,4 16,4.44771525 16,5 L16,6 L18.25,6 C18.6642136,6 19,6.33578644 19,6.75 C19,7.16421356 18.6642136,7.5 18.25,7.5 L5.75,7.5 C5.33578644,7.5 5,7.16421356 5,6.75 C5,6.33578644 5.33578644,6 5.75,6 L8,6 L8,5 C8,4.44771525 8.44771525,4 9,4 L15,4 Z M14,5.25 L10,5.25 C9.72385763,5.25 9.5,5.47385763 9.5,5.75 C9.5,5.99545989 9.67687516,6.19960837 9.91012437,6.24194433 L10,6.25 L14,6.25 C14.2761424,6.25 14.5,6.02614237 14.5,5.75 C14.5,5.50454011 14.3231248,5.30039163 14.0898756,5.25805567 L14,5.25 Z">
																		</path>
																	</symbol>
																	<symbol xmlns="http://www.w3.org/2000/svg"
															viewBox="0 0 24 24" id="bottom">
																		<path
															d="M16.9535129,8 C17.4663488,8 17.8890201,8.38604019 17.9467852,8.88337887 L17.953255,9.02270969 L17.953255,9.02270969 L17.5309272,18.3196017 C17.5119599,18.7374363 17.2349366,19.0993109 16.8365446,19.2267053 C15.2243631,19.7422351 13.6121815,20 12,20 C10.3878264,20 8.77565288,19.7422377 7.16347932,19.226713 C6.76508717,19.0993333 6.48806648,18.7374627 6.46907425,18.3196335 L6.04751853,9.04540766 C6.02423185,8.53310079 6.39068134,8.09333626 6.88488406,8.01304774 L7.02377738,8.0002579 L16.9535129,8 Z M9.75,10.5 C9.37030423,10.5 9.05650904,10.719453 9.00684662,11.0041785 L9,11.0833333 L9,16.9166667 C9,17.2388328 9.33578644,17.5 9.75,17.5 C10.1296958,17.5 10.443491,17.280547 10.4931534,16.9958215 L10.5,16.9166667 L10.5,11.0833333 C10.5,10.7611672 10.1642136,10.5 9.75,10.5 Z M14.25,10.5 C13.8703042,10.5 13.556509,10.719453 13.5068466,11.0041785 L13.5,11.0833333 L13.5,16.9166667 C13.5,17.2388328 13.8357864,17.5 14.25,17.5 C14.6296958,17.5 14.943491,17.280547 14.9931534,16.9958215 L15,16.9166667 L15,11.0833333 C15,10.7611672 14.6642136,10.5 14.25,10.5 Z">
																		</path>
																	</symbol>
																</svg>
												</div>
												<div class="col-4 modified-date">
													<a target="_blank" class="contact-button"> <i
														class="bi bi-wrench"></i>&nbsp;&nbsp;
														${projectDetails.modifiedDate }
													</a>

												</div>
											</div>

										</div>
									</div>






								</c:forEach>
							</div>
						</div>





						<div class="tab-pane fade" id="AllProjectDetails" role="tabpanel">
							<div class="row">

								<div class="col-md-12">
									<input class="form-control" id="projectSearch" type="text"
										placeholder="Search Project Details...."> <br>
									<div class="card">
										<div class="card-body">
											<div class="table-responsive">
												<table
													class="table table-hover table-striped table-vcenter mb-0 text-nowrap">
													<thead>
														<tr>
															<th style="padding-left: 50px">Owner</th>
															<th style="text-align: center;">Project Name</th>
															<th class="w100" style="text-align: center;">Type</th>
															<th class="w100" style="text-align: center;">
																Duration</th>
															<th style="text-align: center;">Status</th>
														</tr>
													</thead>
													<tbody id="allProjectDetailsTableBody">
														<c:forEach items="${projectDetailsList }"
															var="allProjectDetails">
															<tr>
																<td><img
																	src="https://ui-avatars.com/api/?name=${allProjectDetails.projectOwner }&background=random"
																	alt="Avatar" class="w30 rounded-circle mr-2"> <span>${allProjectDetails.projectOwner
																					}</span>
																</td>
																<td style="text-align: center;">
																	${allProjectDetails.projectName }</td>
																<td style="text-align: center;"><span>${allProjectDetails.technology.projectType
																					}</span>
																</td>
																<td style="text-align: center;"><script
																		type="text/javascript">
																	function hoursDiff(
																			dt1,
																			dt2) {

																		// calculate the time difference of two dates JavaScript
																		var diffTime = (dt2
																				.getTime() - dt1
																				.getTime());

																		// calculate the number of days between hours dates javascript
																		var hoursDiff = diffTime
																				/ (1000 * 3600);

																		return hoursDiff;

																	}

																	dt1 = new Date(
																			'${allProjectDetails.startDate}');
																	dt2 = new Date(
																			'${allProjectDetails.endDate}');

																	var hours = hoursDiff(
																			dt1,
																			dt2)
																	var days = hoursDiff(
																			dt1,
																			dt2)

																	document
																			.write(hours
																					+ " Hours / "
																					+ days
																					/ 24
																					+ " Days");
																</script></td>
																<td style="text-align: center;"><span
																	class="text-warning">${allProjectDetails.status
																					}</span></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade" id="Project-add" role="tabpanel">
							<div class="info">
								<h1>
									<b>Project Details</b>
								</h1>
								<span> Add New <i class="bi bi-bag-plus-fill"></i>
									Project Details

								</span>
							</div>

							<div class="container">
								<div id="myInfo" class="modal fade bd-example-modal-lg"
									tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<h2 class="fs-title">Project Details</h2>
											<h3 class="fs-subtitle">Information related to this
												Project details Page</h3>
											<p>This is a Page where you have to provide all the
												details related to project.Which will be later saved in the
												database. This is divided into 5 sections.First section is
												Project information where you have to provide basic but
												important information related to project like project
												name,project description etc.Second section is PROJECT DATES
												& STATUS where you have to provide start date,end date and
												status of the project. Third section is TECHNOLOGY DETAILS
												where you have to provide the programming language you will
												be using to build the project,framework used ,project type
												and database used. Fourth section has only one field where
												you have give the name of the project leader and in last
												section you have to provide team member details.</p>
											<input type="button" name="next"
												class="next action-button modal-close" value="Got it!"
												data-dismiss="modal">
										</div>
									</div>
								</div>
							</div>

							<form class="steps" action="addProjectDetailsForm" method="POST">
								<ul id="progressbar">
									<li class="active">Project Information</li>
									<li>Project Dates & Status</li>
									<li>Technology Details</li>
									<li>Project Team Details</li>
								</ul>
								<!-- USER INFORMATION FIELD SET -->
								<fieldset>
									<h2 class="fs-title">Basic Project Information</h2>
									<h3 class="fs-subtitle">Basic Information about the
										project.</h3>
									<!-- Begin Project name Field -->
									<div class="ProjectName">

										<label for="ProjectName">Project Name *</label> <input
											id="projectname_1" name="projectName" required="required"
											type="text" value="" placeholder="" data-rule-required="true"
											data-msg-required="&nbsp; Please include the project name">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project name Field -->

									<!-- Begin Project Description Field -->
									<div class="ProjectDescription2">

										<label for="ProjectDescription">Project Description *</label>
										<input id="projectdescription_1" name="projectDescription"
											required="required" type="text" value="" placeholder=""
											data-rule-required="true"
											data-msg-required="&nbsp; Please give Project description.">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project Description Field -->

									<!-- Begin Project Owner Field -->
									<div class="ProjectOwner">

										<label for="ProjectOwner">Project Owner *</label> <input
											id="projectowner_1" name="projectOwner" required="required"
											type="text" value="" placeholder="" data-rule-required="true"
											data-msg-required="&nbsp; Please provide Project owner name.">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project Owner Field -->

									<input type="button" data-page="1" name="next"
										class="next action-button" value="Next" />
									<div class="explanation btn btn-small modal-trigger"
										data-toggle="modal" href="#myInfo">
										<i class="bi bi-question-circle-fill amber-text"></i> What Is
										This?
									</div>
								</fieldset>

								<!-- Project Dates & Status FIELD SET -->
								<fieldset>
									<h2 class="fs-title">Project Dates & Status</h2>
									<h3 class="fs-subtitle">Important dates and status related
										to the project</h3>
									<!-- Begin Project Status Field -->
									<div class="ProjectStatus">

										<label for="ProjectStatus">Project Status *</label> <select
											id="status" name="status" class="form-control">
											<c:forEach var="item" items="${statusEnum}">
												<option value="${item}">${item}</option>
											</c:forEach>
										</select> <span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project status Field -->

									<!-- Begin Project startDate Field -->
									<div class="ProjectStartDate">

										<label for="ProjectStartDate">Project Start Date *</label> <input
											id="projectstartdate_1" name="startDate" required="required"
											type="date" value="" placeholder="" data-rule-required="true"
											data-msg-required="&nbsp; Please Provide Project Start Date.">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project startDate Field -->

									<!-- Begin Project EndDate Field -->
									<div class="ProjectEndDate">

										<label for="ProjectEndDate">Project End Date *</label> <input
											id="projectenddate_1" name="endDate" required="required"
											type="date" value="" placeholder="" data-rule-required="true"
											data-msg-required="&nbsp; Please provide Project End Date.">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>

									<!-- Begin Project EndDate Field -->
									<div class="ProjectModifiedDate">

										<label for="ProjectModifiedDate">Project Modified Date
											*</label> <input id="projectenddate_1" name="modifiedDate"
											required="required" type="text" value="" placeholder=""
											data-rule-required="true"
											data-msg-required="&nbsp; Please provide Project End Date."
											readonly="readonly"> <span class="error1"
											style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project EndDate Field -->
									<input type="button" data-page="2" name="previous"
										class="previous action-button" value="Previous" /> <input
										type="button" data-page="2" name="next"
										class="next action-button" value="Next" />
									<div class="explanation btn btn-small modal-trigger"
										data-modal-id="modal-3">
										<i class="bi bi-question-circle-fill amber-text"></i>What Is
										This?
									</div>
								</fieldset>

								<!-- Project Dates & Status FIELD SET -->
								<fieldset>
									<h2 class="fs-title">Technology Details</h2>
									<h3 class="fs-subtitle">Technology Details which will be
										used in the project</h3>
									<!-- Begin Project ProgrammingLanguage Field -->
									<div class="ProgrammingLanguage">

										<label for="ProgrammingLanguage">Programming Language
											*</label> <input id="programminglanguage_1" name="language"
											required="required" type="text" value="" placeholder=""
											data-rule-required="true"
											data-msg-required="&nbsp; Please Provide the Programming Language used in Project">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project ProgrammingLanguage Field -->

									<!-- Begin Project Framework Field -->
									<div class="Framework">

										<label for="Framework">Framework *</label> <input
											id="projectframework_1" name="framework" required="required"
											type="text" value="" placeholder="" data-rule-required="true"
											data-msg-required="&nbsp; Please Provide Framework used in the project.">
										<span class="error1" style="display: none;"> <i
											class="bi bi-x-octagon-fill red-text"></i>
										</span>
									</div>
									<!-- End Project Framework Field -->

									<!-- Begin  ProjectType Field -->
									<div class="ProjectType">

										<label for="ProjectType">Project Type *</label> <select
											id="projectType" name="projectType" class="form-control">
											<c:forEach var="item" items="${projectType}">
												<option value="${item}">${item}</option>
											</c:forEach>
										</select>
									</div>
									<!-- End ProjectType Field -->

									<div id='TextBoxesGroup'>
										<div id="TextBoxDiv1">
											<label>Database Name #1 : </label><input type='textbox'
												id='textbox_1' name="databaseName">
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<input type='button' value='Add Button' id='addButton'>
										</div>
										<div class="col-6">
											<input type='button' value='Remove Button' id='removeButton'>
										</div>
									</div>
									<input type="button" data-page="2" name="previous"
										class="previous action-button" value="Previous" /> <input
										type="button" data-page="2" name="next"
										class="next action-button" value="Next" />
									<div class="explanation btn btn-small modal-trigger"
										data-modal-id="modal-3">
										<i class="bi bi-question-circle-fill amber-text"></i>What Is
										This?
									</div>
								</fieldset>

								<!-- Project Leader FIELD SET -->
								<fieldset>
									<h2 class="fs-title">Team Details</h2>
									<h3 class="fs-subtitle">Team Which will be working on this
										project.</h3>




									<div class="TeamSize">

										<label for="TeamSize">Team Size *</label> <select
											id="teamSize" name="teamSize" class="form-control">
											<c:forEach var="item" items="${teamDetailsList}">
												<option value="${item.teamMembersDetails.size()}">${item.teamMembersDetails.size()}
												</option>
											</c:forEach>
										</select>
									</div>
									<div class="TeamDetails">

										<label for="cars">Choose project Team:</label> <select
											id="ProjectTeamId" name="ProjectTeamId" class="form-control">
											<c:forEach var="item" items="${teamDetailsList}">
												<option value="${item.teamId}">${item.projectAssigned}
													/ ${item.projectLeader}</option>
											</c:forEach>
										</select>
									</div>
									<input type="button" data-page="3" name="previous"
										class="previous action-button" value="Previous" /> <input
										id="submit" class="hs-button primary large action-button next"
										type="submit" value="Submit"
										onclick="location.href='<%=request.getContextPath()%>/home';">
									<div class="explanation btn btn-small modal-trigger"
										data-modal-id="modal-3">
										<i class="bi bi-question-circle-fill amber-text"></i>What Is
										This?
									</div>

								</fieldset>


							</form>

						</div>

						<div class="tab-pane fade" id="Project-Working" role="tabpanel">
							<div class="row">
								<c:forEach items="${projectDetailsList }" var="projectDetails">
									<c:if test="${projectDetails.status == 'WORKING'}">

										<div class="col-lg-6 col-md-12">
											<div class="card">
												<div class="card-header">
													<h3 class="card-title">${projectDetails.projectName }</h3>
													<div class="card-options">
														<label class="custom-switch m-0"> <input
															type="checkbox" value="1" class="custom-switch-input"
															checked /> <span class="custom-switch-indicator"></span>
														</label> <a href="#" class="card-options-collapse"
															data-toggle="card-collapse"><i class="bi bi-trash"></i></a>
													</div>
												</div>

												<div class="card-body">
													<span class="tag mb-3 tag-project-type"
														style="color: white;"><b>${projectDetails.technology.projectType
																	}</b></span>
													<p>${projectDetails.projectDescription }</p>
													<div class="row">
														<div class="col-5 py-1">
															<strong>Project Owner:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.projectOwner }
														</div>
														<div class="col-5 py-1">
															<strong>Start Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.startDate }
														</div>
														<div class="col-5 py-1">
															<strong>End Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.endDate }</div>
														<div class="col-5 py-1">
															<strong>Technology Details:</strong>
														</div>
														<div class="col-7 py-1 table1">
															<strong><a class="btn btn-primary eBtn"
																style="background-color: #3b5998" role="button"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Size:</strong>
														</div>
														<div class="col-7 py-1">
															<strong>${projectDetails.teamSize }</strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Details:</strong>
														</div>
														<div class="col-7 py-1">
															<strong><a
																class="btn btn-primary teamDetailsBtn"
																style="background-color: #485563" role="button"
																data-toggle="modal"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Team Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team:</strong>
														</div>
														<div class="col-7 py-1">
															<div class="avatar-list avatar-list-stacked">
																<c:forEach
																	items="${projectDetails.teamDetails.teamMembersDetails }"
																	var="teamMembersDetails">
																	<img class="avatar avatar-sm"
																		src="https://ui-avatars.com/api/?name=${teamMembersDetails.firstName }+${teamMembersDetails.lastName }&background=random&bold=true"
																		data-toggle="tooltip"
																		title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }"
																		data-original-title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }" />
																</c:forEach>
															</div>
														</div>
													</div>
												</div>

												<div class="row card-footer">
													<div class="col-4">
														<button class="continue-application"
															onclick="location.href='/update/${projectDetails.projectId }'">
															<div>
																<div class="pencil"></div>
																<div class="folder">
																	<div class="top">
																		<svg viewBox="0 0 24 27">
																					<path
																				d="M1,0 L23,0 C23.5522847,-1.01453063e-16 24,0.44771525 24,1 L24,8.17157288 C24,8.70200585 23.7892863,9.21071368 23.4142136,9.58578644 L20.5857864,12.4142136 C20.2107137,12.7892863 20,13.2979941 20,13.8284271 L20,26 C20,26.5522847 19.5522847,27 19,27 L1,27 C0.44771525,27 6.76353751e-17,26.5522847 0,26 L0,1 C-6.76353751e-17,0.44771525 0.44771525,1.01453063e-16 1,0 Z">
																					</path>
																				</svg>
																	</div>
																	<div class="paper"></div>
																</div>
															</div>
															Update Details
														</button>
													</div>
													<div class="col-4">
														<button class="button-delete"
															onclick="location.href='/delete/${projectDetails.projectId }'">
															<div class="icon">
																<svg class="top">
																			<use xlink:href="#top" />
																		</svg>
																<svg class="bottom">
																			<use xlink:href="#bottom" />
																		</svg>
															</div>
															<span>Delete</span>
														</button>

														<svg xmlns="http://www.w3.org/2000/svg"
															style="display: none;">
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="top">
																		<path
																d="M15,4 C15.5522847,4 16,4.44771525 16,5 L16,6 L18.25,6 C18.6642136,6 19,6.33578644 19,6.75 C19,7.16421356 18.6642136,7.5 18.25,7.5 L5.75,7.5 C5.33578644,7.5 5,7.16421356 5,6.75 C5,6.33578644 5.33578644,6 5.75,6 L8,6 L8,5 C8,4.44771525 8.44771525,4 9,4 L15,4 Z M14,5.25 L10,5.25 C9.72385763,5.25 9.5,5.47385763 9.5,5.75 C9.5,5.99545989 9.67687516,6.19960837 9.91012437,6.24194433 L10,6.25 L14,6.25 C14.2761424,6.25 14.5,6.02614237 14.5,5.75 C14.5,5.50454011 14.3231248,5.30039163 14.0898756,5.25805567 L14,5.25 Z">
																		</path>
																	</symbol>
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="bottom">
																		<path
																d="M16.9535129,8 C17.4663488,8 17.8890201,8.38604019 17.9467852,8.88337887 L17.953255,9.02270969 L17.953255,9.02270969 L17.5309272,18.3196017 C17.5119599,18.7374363 17.2349366,19.0993109 16.8365446,19.2267053 C15.2243631,19.7422351 13.6121815,20 12,20 C10.3878264,20 8.77565288,19.7422377 7.16347932,19.226713 C6.76508717,19.0993333 6.48806648,18.7374627 6.46907425,18.3196335 L6.04751853,9.04540766 C6.02423185,8.53310079 6.39068134,8.09333626 6.88488406,8.01304774 L7.02377738,8.0002579 L16.9535129,8 Z M9.75,10.5 C9.37030423,10.5 9.05650904,10.719453 9.00684662,11.0041785 L9,11.0833333 L9,16.9166667 C9,17.2388328 9.33578644,17.5 9.75,17.5 C10.1296958,17.5 10.443491,17.280547 10.4931534,16.9958215 L10.5,16.9166667 L10.5,11.0833333 C10.5,10.7611672 10.1642136,10.5 9.75,10.5 Z M14.25,10.5 C13.8703042,10.5 13.556509,10.719453 13.5068466,11.0041785 L13.5,11.0833333 L13.5,16.9166667 C13.5,17.2388328 13.8357864,17.5 14.25,17.5 C14.6296958,17.5 14.943491,17.280547 14.9931534,16.9958215 L15,16.9166667 L15,11.0833333 C15,10.7611672 14.6642136,10.5 14.25,10.5 Z">
																		</path>
																	</symbol>
																</svg>
													</div>
													<div class="col-4 modified-date">
														<a target="_blank" class="contact-button"> <i
															class="bi bi-wrench"></i>&nbsp;&nbsp;
															${projectDetails.modifiedDate }
														</a>

													</div>
												</div>

											</div>
										</div>


									</c:if>
								</c:forEach>
							</div>
						</div>



						<div class="tab-pane fade" id="Project-Completed" role="tabpanel">
							<div class="row">
								<c:forEach items="${projectDetailsList }" var="projectDetails">
									<c:if test="${projectDetails.status == 'COMPLETED'}">

										<div class="col-lg-6 col-md-12">
											<div class="card">
												<div class="card-header">
													<h3 class="card-title">${projectDetails.projectName }</h3>
													<div class="card-options">
														<label class="custom-switch m-0"> <input
															type="checkbox" value="1" class="custom-switch-input"
															checked /> <span class="custom-switch-indicator"></span>
														</label> <a href="#" class="card-options-collapse"
															data-toggle="card-collapse"><i class="bi bi-trash"></i></a>
													</div>
												</div>

												<div class="card-body">
													<span class="tag mb-3 tag-project-type"
														style="color: white;"><b>${projectDetails.technology.projectType
																	}</b></span>
													<p>${projectDetails.projectDescription }</p>
													<div class="row">
														<div class="col-5 py-1">
															<strong>Project Owner:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.projectOwner }
														</div>
														<div class="col-5 py-1">
															<strong>Start Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.startDate }
														</div>
														<div class="col-5 py-1">
															<strong>End Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.endDate }</div>
														<div class="col-5 py-1">
															<strong>Technology Details:</strong>
														</div>
														<div class="col-7 py-1 table1">
															<strong><a class="btn btn-primary eBtn"
																style="background-color: #3b5998" role="button"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Size:</strong>
														</div>
														<div class="col-7 py-1">
															<strong>${projectDetails.teamSize }</strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Details:</strong>
														</div>
														<div class="col-7 py-1">
															<strong><a
																class="btn btn-primary teamDetailsBtn"
																style="background-color: #485563" role="button"
																data-toggle="modal"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Team Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team:</strong>
														</div>
														<div class="col-7 py-1">
															<div class="avatar-list avatar-list-stacked">
																<c:forEach
																	items="${projectDetails.teamDetails.teamMembersDetails }"
																	var="teamMembersDetails">
																	<img class="avatar avatar-sm"
																		src="https://ui-avatars.com/api/?name=${teamMembersDetails.firstName }+${teamMembersDetails.lastName }&background=random&bold=true"
																		data-toggle="tooltip"
																		title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }"
																		data-original-title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }" />
																</c:forEach>
															</div>
														</div>
													</div>
												</div>

												<div class="row card-footer">
													<div class="col-4">
														<button class="continue-application"
															onclick="location.href='/update/${projectDetails.projectId }'">
															<div>
																<div class="pencil"></div>
																<div class="folder">
																	<div class="top">
																		<svg viewBox="0 0 24 27">
																					<path
																				d="M1,0 L23,0 C23.5522847,-1.01453063e-16 24,0.44771525 24,1 L24,8.17157288 C24,8.70200585 23.7892863,9.21071368 23.4142136,9.58578644 L20.5857864,12.4142136 C20.2107137,12.7892863 20,13.2979941 20,13.8284271 L20,26 C20,26.5522847 19.5522847,27 19,27 L1,27 C0.44771525,27 6.76353751e-17,26.5522847 0,26 L0,1 C-6.76353751e-17,0.44771525 0.44771525,1.01453063e-16 1,0 Z">
																					</path>
																				</svg>
																	</div>
																	<div class="paper"></div>
																</div>
															</div>
															Update Details
														</button>
													</div>
													<div class="col-4">
														<button class="button-delete"
															onclick="location.href='/delete/${projectDetails.projectId }'">
															<div class="icon">
																<svg class="top">
																			<use xlink:href="#top" />
																		</svg>
																<svg class="bottom">
																			<use xlink:href="#bottom" />
																		</svg>
															</div>
															<span>Delete</span>
														</button>

														<svg xmlns="http://www.w3.org/2000/svg"
															style="display: none;">
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="top">
																		<path
																d="M15,4 C15.5522847,4 16,4.44771525 16,5 L16,6 L18.25,6 C18.6642136,6 19,6.33578644 19,6.75 C19,7.16421356 18.6642136,7.5 18.25,7.5 L5.75,7.5 C5.33578644,7.5 5,7.16421356 5,6.75 C5,6.33578644 5.33578644,6 5.75,6 L8,6 L8,5 C8,4.44771525 8.44771525,4 9,4 L15,4 Z M14,5.25 L10,5.25 C9.72385763,5.25 9.5,5.47385763 9.5,5.75 C9.5,5.99545989 9.67687516,6.19960837 9.91012437,6.24194433 L10,6.25 L14,6.25 C14.2761424,6.25 14.5,6.02614237 14.5,5.75 C14.5,5.50454011 14.3231248,5.30039163 14.0898756,5.25805567 L14,5.25 Z">
																		</path>
																	</symbol>
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="bottom">
																		<path
																d="M16.9535129,8 C17.4663488,8 17.8890201,8.38604019 17.9467852,8.88337887 L17.953255,9.02270969 L17.953255,9.02270969 L17.5309272,18.3196017 C17.5119599,18.7374363 17.2349366,19.0993109 16.8365446,19.2267053 C15.2243631,19.7422351 13.6121815,20 12,20 C10.3878264,20 8.77565288,19.7422377 7.16347932,19.226713 C6.76508717,19.0993333 6.48806648,18.7374627 6.46907425,18.3196335 L6.04751853,9.04540766 C6.02423185,8.53310079 6.39068134,8.09333626 6.88488406,8.01304774 L7.02377738,8.0002579 L16.9535129,8 Z M9.75,10.5 C9.37030423,10.5 9.05650904,10.719453 9.00684662,11.0041785 L9,11.0833333 L9,16.9166667 C9,17.2388328 9.33578644,17.5 9.75,17.5 C10.1296958,17.5 10.443491,17.280547 10.4931534,16.9958215 L10.5,16.9166667 L10.5,11.0833333 C10.5,10.7611672 10.1642136,10.5 9.75,10.5 Z M14.25,10.5 C13.8703042,10.5 13.556509,10.719453 13.5068466,11.0041785 L13.5,11.0833333 L13.5,16.9166667 C13.5,17.2388328 13.8357864,17.5 14.25,17.5 C14.6296958,17.5 14.943491,17.280547 14.9931534,16.9958215 L15,16.9166667 L15,11.0833333 C15,10.7611672 14.6642136,10.5 14.25,10.5 Z">
																		</path>
																	</symbol>
																</svg>
													</div>
													<div class="col-4 modified-date">
														<a target="_blank" class="contact-button"> <i
															class="bi bi-wrench"></i>&nbsp;&nbsp;
															${projectDetails.modifiedDate }
														</a>

													</div>
												</div>

											</div>
										</div>


									</c:if>
								</c:forEach>
							</div>
						</div>


						<div class="tab-pane fade" id="Project-Pending" role="tabpanel">
							<div class="row">
								<c:forEach items="${projectDetailsList }" var="projectDetails">
									<c:if test="${projectDetails.status == 'STOPPED'}">

										<div class="col-lg-6 col-md-12">
											<div class="card">
												<div class="card-header">
													<h3 class="card-title">${projectDetails.projectName }</h3>
													<div class="card-options">
														<label class="custom-switch m-0"> <input
															type="checkbox" value="1" class="custom-switch-input"
															checked /> <span class="custom-switch-indicator"></span>
														</label> <a href="#" class="card-options-collapse"
															data-toggle="card-collapse"><i class="bi bi-trash"></i></a>
													</div>
												</div>

												<div class="card-body">
													<span class="tag mb-3 tag-project-type"
														style="color: white;"><b>${projectDetails.technology.projectType
																	}</b></span>
													<p>${projectDetails.projectDescription }</p>
													<div class="row">
														<div class="col-5 py-1">
															<strong>Project Owner:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.projectOwner }
														</div>
														<div class="col-5 py-1">
															<strong>Start Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.startDate }
														</div>
														<div class="col-5 py-1">
															<strong>End Date:</strong>
														</div>
														<div class="col-7 py-1">${projectDetails.endDate }</div>
														<div class="col-5 py-1">
															<strong>Technology Details:</strong>
														</div>
														<div class="col-7 py-1 table1">
															<strong><a class="btn btn-primary eBtn"
																style="background-color: #3b5998" role="button"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Size:</strong>
														</div>
														<div class="col-7 py-1">
															<strong>${projectDetails.teamSize }</strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team Details:</strong>
														</div>
														<div class="col-7 py-1">
															<strong><a
																class="btn btn-primary teamDetailsBtn"
																style="background-color: #485563" role="button"
																data-toggle="modal"
																href="/findById/${projectDetails.projectId }"
																type="button"><i class="bi bi-info-circle"></i>&nbsp;<span
																	class="badge bg-danger ms-2">Get Team Details</span></a></strong>
														</div>
														<div class="col-5 py-1">
															<strong>Team:</strong>
														</div>
														<div class="col-7 py-1">
															<div class="avatar-list avatar-list-stacked">
																<c:forEach
																	items="${projectDetails.teamDetails.teamMembersDetails }"
																	var="teamMembersDetails">
																	<img class="avatar avatar-sm"
																		src="https://ui-avatars.com/api/?name=${teamMembersDetails.firstName }+${teamMembersDetails.lastName }&background=random&bold=true"
																		data-toggle="tooltip"
																		title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }"
																		data-original-title="${teamMembersDetails.firstName } ${teamMembersDetails.lastName }" />
																</c:forEach>
															</div>
														</div>
													</div>
												</div>

												<div class="row card-footer">
													<div class="col-4">
														<button class="continue-application"
															onclick="location.href='/update/${projectDetails.projectId }'">
															<div>
																<div class="pencil"></div>
																<div class="folder">
																	<div class="top">
																		<svg viewBox="0 0 24 27">
																					<path
																				d="M1,0 L23,0 C23.5522847,-1.01453063e-16 24,0.44771525 24,1 L24,8.17157288 C24,8.70200585 23.7892863,9.21071368 23.4142136,9.58578644 L20.5857864,12.4142136 C20.2107137,12.7892863 20,13.2979941 20,13.8284271 L20,26 C20,26.5522847 19.5522847,27 19,27 L1,27 C0.44771525,27 6.76353751e-17,26.5522847 0,26 L0,1 C-6.76353751e-17,0.44771525 0.44771525,1.01453063e-16 1,0 Z">
																					</path>
																				</svg>
																	</div>
																	<div class="paper"></div>
																</div>
															</div>
															Update Details
														</button>
													</div>
													<div class="col-4">
														<button class="button-delete"
															onclick="location.href='/delete/${projectDetails.projectId }'">
															<div class="icon">
																<svg class="top">
																			<use xlink:href="#top" />
																		</svg>
																<svg class="bottom">
																			<use xlink:href="#bottom" />
																		</svg>
															</div>
															<span>Delete</span>
														</button>

														<svg xmlns="http://www.w3.org/2000/svg"
															style="display: none;">
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="top">
																		<path
																d="M15,4 C15.5522847,4 16,4.44771525 16,5 L16,6 L18.25,6 C18.6642136,6 19,6.33578644 19,6.75 C19,7.16421356 18.6642136,7.5 18.25,7.5 L5.75,7.5 C5.33578644,7.5 5,7.16421356 5,6.75 C5,6.33578644 5.33578644,6 5.75,6 L8,6 L8,5 C8,4.44771525 8.44771525,4 9,4 L15,4 Z M14,5.25 L10,5.25 C9.72385763,5.25 9.5,5.47385763 9.5,5.75 C9.5,5.99545989 9.67687516,6.19960837 9.91012437,6.24194433 L10,6.25 L14,6.25 C14.2761424,6.25 14.5,6.02614237 14.5,5.75 C14.5,5.50454011 14.3231248,5.30039163 14.0898756,5.25805567 L14,5.25 Z">
																		</path>
																	</symbol>
																	<symbol xmlns="http://www.w3.org/2000/svg"
																viewBox="0 0 24 24" id="bottom">
																		<path
																d="M16.9535129,8 C17.4663488,8 17.8890201,8.38604019 17.9467852,8.88337887 L17.953255,9.02270969 L17.953255,9.02270969 L17.5309272,18.3196017 C17.5119599,18.7374363 17.2349366,19.0993109 16.8365446,19.2267053 C15.2243631,19.7422351 13.6121815,20 12,20 C10.3878264,20 8.77565288,19.7422377 7.16347932,19.226713 C6.76508717,19.0993333 6.48806648,18.7374627 6.46907425,18.3196335 L6.04751853,9.04540766 C6.02423185,8.53310079 6.39068134,8.09333626 6.88488406,8.01304774 L7.02377738,8.0002579 L16.9535129,8 Z M9.75,10.5 C9.37030423,10.5 9.05650904,10.719453 9.00684662,11.0041785 L9,11.0833333 L9,16.9166667 C9,17.2388328 9.33578644,17.5 9.75,17.5 C10.1296958,17.5 10.443491,17.280547 10.4931534,16.9958215 L10.5,16.9166667 L10.5,11.0833333 C10.5,10.7611672 10.1642136,10.5 9.75,10.5 Z M14.25,10.5 C13.8703042,10.5 13.556509,10.719453 13.5068466,11.0041785 L13.5,11.0833333 L13.5,16.9166667 C13.5,17.2388328 13.8357864,17.5 14.25,17.5 C14.6296958,17.5 14.943491,17.280547 14.9931534,16.9958215 L15,16.9166667 L15,11.0833333 C15,10.7611672 14.6642136,10.5 14.25,10.5 Z">
																		</path>
																	</symbol>
																</svg>
													</div>
													<div class="col-4 modified-date">
														<a target="_blank" class="contact-button"> <i
															class="bi bi-wrench"></i>&nbsp;&nbsp;
															${projectDetails.modifiedDate }
														</a>

													</div>
												</div>

											</div>
										</div>


									</c:if>
								</c:forEach>
							</div>
						</div>







					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="<c:url value="/resources/assets/bundles/lib.vendor.bundle.js"/>"></script>
	<script src="<c:url value="/resources/js/extention/choices.js"/>"></script>

	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
	<script
		src='https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js'></script>
	<script src="<c:url value="/resources/js/scriptProjectDetails.js"/>"></script>
	<script>
		const choices = new Choices('[data-trigger]', {
			searchEnabled : false,
			itemSelectText : '',
		});
	</script>

	<script>
		$(document).ready(function() {
			$('.tag-project-type').each(function() {

				var x = Math.floor(Math.random() * 256);
				var y = Math.floor(Math.random() * 256);
				var z = Math.floor(Math.random() * 256);

				var hue = "rgb(" + x + "," + y + "," + z + ")";
				$(this).css("background-color", hue);
			});
		});
	</script>

	<script>
		$(document)
				.ready(
						function() {
							$("#projectSearch")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$(
														"#allProjectDetailsTableBody tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>


	<script src="<c:url value="/resources/assets/js/core.js"/>"></script>

</body>

</html>