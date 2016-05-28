<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.html" %>

</head>
<body>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <form class="form-horizontal" role="form" action="profile" method="post">
        <fieldset>
        	<legend>Edit Profile</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="name">Name</label>			
						<div class="col-sm-10">
							<input id ="name" type="text" size ="30" name="name" value=${sessionScope.profile.name}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="currentPosition">Current Position</label>			
						<div class="col-sm-10">
							<input id ="currentPosition" type="text" size ="30" name="currentPosition" value=${sessionScope.profile.currentPosition}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="education">Education</label>			
						<div class="col-sm-10">
							<input id ="education" type="text" size ="30" name="education" value=${sessionScope.profile.education}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="pastExperience">Past Experience</label>		
						<div class="col-sm-10">
							<input required id="pastExperience" size="30" name="pastExperience" value=${sessionScope.profile.pastExperience} >
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="profressionalSkills">Professional Skills</label>			
						<div class="col-sm-10">
							<input id ="professionalSkills"  size ="30" name="professionalSkills" value=${sessionScope.profile.professionalSkills}>
						</div>
					</div>
					<input  type="hidden" id="id" name ="id" value=${sessionScope.profile.id}>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			               		<input type="hidden" value="update" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Update Details</button>
			             	</div>
			           </div>
			        </div>
			</fieldset>
		</form>
	</div>
</body>
</html>