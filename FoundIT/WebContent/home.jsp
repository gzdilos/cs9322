<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/WEB-INF/jsps/header.html" %>
</head>
<body>
		<form role="form" action="search" method="get"> 
  			<div class="form-group col-md-4 col-md-offset-4">
	            <div class="form-group has-feedback">
	                <label class="control-label" for="search_keyword">Job Keyword(s)</label>
	                <input type="text" class="form-control" id="search_keyword" placeholder="Enter Keyword(s)"/>
	                <span class="glyphicon glyphicon-search form-control-feedback"></span>
	            </div>
        	</div>
		</form>
		
</body>
</html>