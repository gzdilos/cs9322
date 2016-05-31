<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/WEB-INF/jsps/header.html" %>
<style type="text/css">
	.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
	.TFtable td{ 
		padding:7px; border:#4e95f4 1px solid;
	}
	
	.TFtable tr{
		background: #b8d1f3;
	}

	.TFtable tr:nth-child(odd){ 
		background: #dae5f4;
	}
	
	.TFtable tr:nth-child(even){
		background: #FFFFFF;
	}
	.TFtable tr:nth-child(1){ 
		background: #cccccc;
	}
</style>
</head>
<body>
<div class="col-md-9" >	
<c:choose>
<c:when test = "${empty requestScope.jobsList}">
	 <div class="col-sm-offset-3 col-sm-6">
		<h1>You have no job postings at the moment.</h1>
	</div>
</c:when>
<c:otherwise>
 <table class ="TFtable">
   <tr>
   		<th>
   			Job ID
   		</th>
   		<th>
   			Job Title
   		</th>
   		<th>
   			Job Position
   		</th>
   		<th>
   			Job Location
   		</th>
   		<th>
   			Status
   		</th>
   		<th>
   			Action
   		</th>
   	</tr>
   	<c:forEach var="job" items="${requestScope.jobsList}">
   	<tr>
   		<td>
   			${job.id}
   		</td>
   		<td>
   			${job.title}
   		</td>
   		<td>
   			${job.positionType}
   		</td>
   		<td>
   			${job.location}
   		</td>
   		<td>
   			${job.status}
   		</td>
   		<td>
   			<form method="get" action="manager" >
			<input type="hidden" id="applicationID" name="applicationID" value=${application.applicationId}>	
			<button name="action" value ="viewApplicants" type="submit" class="btn btn-primary btn-lg btn-block">Withdraw</a>
			</button>
   		</td>  
   	</tr>			
   	</c:forEach>   
   </table>	
   </c:otherwise>
   </c:choose>
 

 <div class="col-sm-offset-3 col-sm-6">
 <form method="get" action="manager">
 <p></p>
 	<button type="submit" name="add" class="btn btn-primary btn-lg btn-block">Add new Job Post</button>
 </form>
 </div>  
</body>

</html>