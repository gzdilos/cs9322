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
      <form class="form-horizontal" role="form" action="manager" method="get">
        <fieldset>
        	<legend>Add Job Posting</legend>    
  			
					<div class="form-group">
						<label class="col-sm-2 control-label" for="title">Title</label>			
						<div class="col-sm-10">
							<input id ="title" type="text" size ="30" name="title" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="decription">Description</label>			
						<div class="col-sm-10">
							<input id ="description" type="text" size ="30" name="description" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="positiontype">Position Type</label>			
						<div class="col-sm-10">
							<input id ="positiontype" type="text" size ="30" name="positiontype" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="desiredskills">Desired Skills</label>		
						<div class="col-sm-10">
							<input required id="desiredskills" size="30" name="desiredskills" required >
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="salarylevel">Salary Level</label>			
						<div class="col-sm-10">
							<input id ="salarylevel"  size ="30" name="salarylevel" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="location">Location</label>			
						<div class="col-sm-10">
							<input id ="location"  size ="30" name="location" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
			             	<div class="pull-left">
			              		<input type="hidden" value="add" name="action">
			              		<button type="submit" class="btn btn-primary btn-lg btn-block">Post Job</button>
			              		
			             	</div>
			           </div>
			        </div>
</body>
</html>