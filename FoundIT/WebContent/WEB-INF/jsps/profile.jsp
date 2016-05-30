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
      <c:choose>
   	  <c:when test="${sessionScope.loggedIn}">
      <form class="form-horizontal" role="form" action="profile" method="post">
      </c:when>
      <c:otherwise>
      <form class="form-horizontal" role="form" action="login" method="post">
      </c:otherwise>
      </c:choose>
      <input type="hidden" value="${requestScope.userType}" name="userType">
      <input type="hidden" value="${requestScope.email}" name="email">
      <input type="hidden" value="${requestScope.password}" name="password">
        <fieldset>
        	<legend>Edit Profile</legend>
        			<c:choose>
        			<c:when test="${userType == 'candidate'}">
        			
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
						<label class="col-sm-2 control-label" for="professionalSkills">Professional Skills</label>			
						<div class="col-sm-10">
							<input id ="professionalSkills"  size ="30" name="professionalSkills" value=${sessionScope.profile.professionalSkills}>
						</div>
					</div>
					<input  type="hidden" id="id" name ="id" value=${sessionScope.profile.id}>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			               		
			              		<c:choose>
			               		<c:when test="${empty sessionScope.loggedIn}">
			               		<input type="hidden" value="register" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Complete Registration</button>
			              		</c:when>
			              		<c:otherwise>
			              		<input type="hidden" value="update" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Update Details</button>
			              		</c:otherwise>
			              		</c:choose>
			             	</div>
			           </div>
			        </div>
			        </c:when>
			        <c:otherwise>
			        <div class="form-group">
						<label class="col-sm-2 control-label" for="name">Name</label>			
						<div class="col-sm-10">
							<input id ="name" type="text" size ="30" name="name" value=${sessionScope.companyprofile.name}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="description">Description</label>			
						<div class="col-sm-10">
							<input id ="description" type="text" size ="30" name="description" value=${sessionScope.companyprofile.description}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="website">Website</label>			
						<div class="col-sm-10">
							<input id ="website" type="text" size ="30" name="website" value=${sessionScope.companyprofile.website}>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="industrytype">IndustryType</label>		
						<div class="col-sm-10">
							<input required id="industrytype" size="30" name="industrytype" value=${sessionScope.companyprofile.industryType} >
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="address">Address</label>			
						<div class="col-sm-10">
							<input id ="address"  size ="30" name="address" value=${sessionScope.companyprofile.address}>
						</div>
					</div>
					<input  type="hidden" id="id" name ="id" value=${sessionScope.companyprofile.id}>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			               		
			               		<c:choose>
			               		<c:when test="${empty sessionScope.loggedIn}">
			               		<input type="hidden" value="register" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Complete Registration</button>
			              		</c:when>
			              		<c:otherwise>
			              		<input type="hidden" value="update" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Update Details</button>
			              		</c:otherwise>
			              		</c:choose>
			             	</div>
			           </div>
			        </div>
			        </c:otherwise>
			        </c:choose>
			        
			</fieldset>
		</form>
	</div>
</body>
</html>