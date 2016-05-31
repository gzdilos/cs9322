<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.html" %>

</head>
<body>
<c:choose>
 <c:when test="${requestScope.action == 'addMember'}">
 <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <form class="form-horizontal" role="form" action="login" method="post">
        <fieldset>
        	
        	<legend>Create new Team Member</legend>    
  			
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username">Username</label>			
						<div class="col-sm-10">
							<input id ="username" type="text" size ="30" name="username" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">Password</label>			
						<div class="col-sm-10">
							<input id ="password" type="text" size ="30" name="password" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="professionalskills">Skills</label>			
						<div class="col-sm-10">
							<input id ="professionalskills" type="text" size ="30" name="professionalskills" required>
						</div>
					</div>					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			              		<input type="hidden" value="addReviewer" name="action">			
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Create Team Member</button>
			              		
			             	</div>
			           </div>
			        </div>
				</fieldset>
			</form>
		</div>
	</div>
</c:when>
 <c:otherwise>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <form class="form-horizontal" role="form" action="manager" method="post">
        <fieldset>
        	
        	<legend>Create new Hiring Team</legend>    
  			
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username1">First Reviewer Username</label>			
						<div class="col-sm-10">
							<input id ="username1" type="text" size ="30" name="username1" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password1">First Reviewer Password</label>			
						<div class="col-sm-10">
							<input id ="password1" type="text" size ="30" name="password1" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username2">Second Reviewer Username</label>			
						<div class="col-sm-10">
							<input id ="username2" type="text" size ="30" name="username2" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password2">Second Reviewer Password</label>			
						<div class="col-sm-10">
							<input id ="password2" type="text" size ="30" name="password2" required>
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username3">Third Reviewer Username</label>			
						<div class="col-sm-10">
							<input id ="username3" type="text" size ="30" name="username3" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password3">Third Reviewer Password</label>			
						<div class="col-sm-10">
							<input id ="password3" type="text" size ="30" name="password3" required>
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username4">Fourth Reviewer Username</label>			
						<div class="col-sm-10">
							<input id ="username4" type="text" size ="30" name="username4" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password4">Fourth Reviewer Password</label>			
						<div class="col-sm-10">
							<input id ="password4" type="text" size ="30" name="password4" required>
						</div>
					</div>		
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username5">Fifth Reviewer Username</label>			
						<div class="col-sm-10">
							<input id ="username5" type="text" size ="30" name="username5" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password5">Fifth Reviewer Password</label>			
						<div class="col-sm-10">
							<input id ="password5" type="text" size ="30" name="password5" required>
						</div>
					</div>									
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			              		<input type="hidden" value="addTeam" name="action">			
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Create Hiring Team</button>
			             	</div>
			           </div>
			        </div>
				</fieldset>
			</form>
		</div>
	</div>
 </c:otherwise>
 </c:choose>
</body>
</html>