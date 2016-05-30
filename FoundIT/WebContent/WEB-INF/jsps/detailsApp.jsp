<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
</head>
<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <fieldset>
        	<legend>Applicant</legend>
      				<form class="form-horizontal" role="form" action="listapplicants" method="get">
      					<h3>User Profile</h3>
   					<div class="form-group">
						<label class="col-sm-2 control-label" for="name">Name</label>
						<div class="col-sm-10">
							${sessionScope.applicant.profile.name}	
						</div>		
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="currentPosition">Current Position</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.profile.currentPosition}	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="education">Education</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.profile.education}	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="pastExperience">Past Experience</label>		
						<div class="col-sm-10">
							${sessionScope.applicant.profile.pastExperience}	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="profressionalSkills">Professional Skills</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.profile.professionalSkills}	
						</div>
					</div>
					
					<h3>Job Application</h3>
					
					<div class="form-group">
						<label class="col-sm-2 control-label" for="coverletter">Cover Letter}</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.getJobApplication().coverLetter}	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="resume">Resume</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.getJobApplication().resume}	
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label" for="status">Status</label>			
						<div class="col-sm-10">
							${sessionScope.applicant.getJobApplication().status}	
						</div>
					</div>
					
					<input  type="hidden" id="${sessionScope.applicant.profile.id}" name ="userid" value=${sessionScope.applicant.profile.id}>
					<input  type="hidden" id="${sessionScope.applicant.getJobApplication().id}" name ="jobid" value=${sessionScope.applicant.getJobApplication().id}>
					<c:choose>
      					<c:when test="${sessionScope.applicant.getJobApplication().status == 'submitted'}">
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
			           <div class="form-group">
							<label class="col-sm-2 control-label" for="status">Comments</label>			
							<div class="col-sm-10">
								<input id ="comments"  size ="30" name="comments" value="Enter Comments Here">
							</div>
						</div>
			        </div>
			        </c:when>
			        </c:choose>
			        </form>	
					
			</fieldset>
	</div>
</body>
</html>