<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Applicants</title>
</head>
<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <fieldset>
        	<legend>List Applicants</legend>
       				<c:forEach begin="0" end="${sessionScope.listapp.size - 1}" var="index">
       					<form class="form-horizontal" role="form" action="listapplicants" method="get">
       					<h1>User Profile</h1>
    					<div class="form-group">
							<label class="col-sm-2 control-label" for="name">Name</label>
							<div class="col-sm-10">
								${sessionScope.listapp.getProfile(index).name}	
							</div>		
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="currentPosition">Current Position</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getProfile(index).currentPosition}	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="education">Education</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getProfile(index).education}	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="pastExperience">Past Experience</label>		
							<div class="col-sm-10">
								${sessionScope.listapp.getProfile(index).pastExperience}	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="profressionalSkills">Professional Skills</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getProfile(index).professionalSkills}	
							</div>
						</div>
						
						<h1>Job Application</h1>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="coverletter">Cover Letter}</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getJobApp(index).coverLetter}	
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="resume">Resume</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getJobApp(index).resume}	
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="status">Status</label>			
							<div class="col-sm-10">
								${sessionScope.listapp.getJobApp(index).status}	
							</div>
						</div>
						
						<input  type="hidden" id="${sessionScope.listapp.getProfile(index).id}" name ="userid" value=${sessionScope.listapp.getProfile(index).id}>
						<input  type="hidden" id="${sessionScope.listapp.getJobApp(index).id}" name ="jobid" value=${sessionScope.listapp.getJobApp(index).id}>
						<c:choose>
       					<c:when test="${sessionScope.listapp.getJobApp(index).status == 'submitted'}">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
				             	<div class="pull-left">
				               		<input type="hidden" value="success" name="shortlist">
				              		<button type="submit" class="btn btn-primary btn-lg btn-block">Shortlist Applicant</button>
				             	</div>
				             	<div class="pull-right">
				               		<input type="hidden" value="fail" name="shortlist">
				              		<button type="submit" class="btn btn-primary btn-lg btn-block">Reject Applicant</button>
				             	</div>
				           </div>
				        </div>
				        </c:when>
				        </c:choose>
				        </form>
					</c:forEach>	
					
			</fieldset>
	</div>
</body>
</html>