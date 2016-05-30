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
<c:when test = "${empty requestScope.myApplications}">
<h1>You have no applications at the moment.</h1>
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
   			Company
   		</th>
   		<th>
   			Status
   		</th>
   		<th>
   			Action
   		</th>
   	</tr>
   	<c:forEach var="application" items="${requestScope.myApplications}">
   	<tr>
   		<td>
   			Job ID
   		</td>
   		<td>
   			Job Title
   		</td>
   		<td>
   			Company
   		</td>
   		<td>
   			Status
   		</td>
   		<td>
   			Action
   		</td>  
   	</tr>			
   	</c:forEach>   
   </table>	
   </c:otherwise>
   </c:choose>
   
</body>
</html>