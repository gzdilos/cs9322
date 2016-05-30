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
      <form class="form-horizontal" role="form" action="login" method="get">
        <fieldset>
        	<c:if test="${requestScope.action == 'addMember'}">
        	<legend>Create new Team Member</legend>    
        	</c:if>
  			
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
</body>
</html>