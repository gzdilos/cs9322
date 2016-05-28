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
      <form class="form-horizontal" role="form" action="login" method="post">
        <fieldset>
        	<legend>Register</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="email">Email Address</label>			
						<div class="col-sm-10">
							<input id ="name" type="email" size ="30" name="email" placeholder="email@example.com" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">Password</label>			
						<div class="col-sm-10">
							<input id ="password" type="password" size ="30" name="password" placeholder="Password" required >
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			               		<input type="hidden" value="register" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
			             	</div>
			           </div>
			        </div>
			</fieldset>
		</form>
	</div>
</body>
</html>