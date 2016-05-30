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
<c:when test = "${empty requestScope.hiringTeam}">
	 <div class="col-sm-offset-3 col-sm-6">
		<h1>You currently have no Hiring Team</h1>
	</div>
</c:when>
<c:otherwise>
 <table class ="TFtable">
   <tr>
   		<th>
   			Team Member ID
   		</th>
   		<th>
   			Name
   		</th>
   		<th>
   			Skills
   		</th>
   		<th>
   			Action
   		</th>
   	</tr>
   	<c:forEach var="member" items="${requestScope.hiringTeam}">
   	<tr>
   		<td>
   			${member.id}
   		</td>
   		<td>
   			${member.username}
   		</td>
   		<td>
   			${member.professionalSkills}
   		</td>
   		<td>
   			Action
   		</td>  
   	</tr>			
   	</c:forEach>   
   </table>	
   </c:otherwise>
   </c:choose>
 <div class="col-sm-offset-3 col-sm-6">
 <form method="get" action="manager">
 <p></p>
 	<c:if test="{empty requestScope.hiringTeam}">
 	<button type="submit" name="addTeam" class="btn btn-primary btn-lg btn-block">Add Hiring Team</button>
 	</c:if>
 	<c:if test="not empty requestScope.hiringTeam}">
 	<button type="submit" name="changeTeam" class="btn btn-primary btn-lg btn-block">Change Hiring Team</button>
 	<button type="submit" name="removeTeam" class="btn btn-primary btn-lg btn-block">Drop Hiring Team</button>
 	</c:if>
 	<button type="submit" name="addMember" class="btn btn-primary btn-lg btn-block">Add Team Member</button>
 </form>
 </div>  
</body>
</html>