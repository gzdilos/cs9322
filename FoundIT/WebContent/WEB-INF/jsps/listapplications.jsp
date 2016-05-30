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
       					<h3>Applicant ${index}</h3>
    					
						<input  type="hidden" id="${sessionScope.listapp.getProfile(index).id}" name="userid" value=${sessionScope.listapp.getProfile(index).id}>
						<input  type="hidden" id="${sessionScope.listapp.getJobApp(index).id}" name="jobid" value=${sessionScope.listapp.getJobApp(index).id}>
						<input  type="hidden" id="index" name="index" value="${index}">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
				             	<div class="pull-left">
				               		<input type="hidden" value="details" name="details">
				              		<button type="submit" class="btn btn-primary btn-lg btn-block">More Details</button>
				             	</div>
				     
				           </div>
				        </div>
				        </form>
					</c:forEach>	
					
			</fieldset>
	</div>
</body>
</html>