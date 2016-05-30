<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/WEB-INF/jsps/header.html" %>
<style>
.container { margin-top: 20px; }
.mb20 { margin-bottom: 20px; } 

hgroup { padding-left: 15px; border-bottom: 1px solid #ccc; }
hgroup h1 { font: 500 normal 1.625em "Roboto",Arial,Verdana,sans-serif; color: #2a3644; margin-top: 0; line-height: 1.15; }
hgroup h2.lead { font: normal normal 1.125em "Roboto",Arial,Verdana,sans-serif; color: #2a3644; margin: 0; padding-bottom: 10px; }

.search-result .thumbnail { border-radius: 0 !important; }
.search-result:first-child { margin-top: 0 !important; }
.search-result { margin-top: 20px; }
.search-result .col-md-2 { border-right: 1px dotted #ccc; min-height: 140px; }
.search-result ul { padding-left: 0 !important; list-style: none;  }
.search-result ul li { font: 400 normal .85em "Roboto",Arial,Verdana,sans-serif;  line-height: 30px; }
.search-result ul li i { padding-right: 5px; }
.search-result .col-md-7 { position: relative; }
.search-result h3 { font: 500 normal 1.375em "Roboto",Arial,Verdana,sans-serif; margin-top: 0 !important; margin-bottom: 10px !important; }
.search-result h3 > a, .search-result i { color: #248dc1 !important; }
.search-result p { font: normal normal 1.125em "Roboto",Arial,Verdana,sans-serif; } 
.search-result span.plus { position: absolute; right: 0; top: 126px; }
.search-result span.plus a { background-color: #248dc1; padding: 5px 5px 3px 5px; }
.search-result span.plus a:hover { background-color: #414141; }
.search-result span.plus a i { color: #fff !important; }
.search-result span.border { display: block; width: 97%; margin: 0 15px; border-bottom: 1px dotted #ccc; }
</style>
</head>
<body>
<div class="col-md-9" >	
<c:if test = "${empty resquestScope.results}">
<h1>You have no saved jobs</h1>
</c:if>
<c:forEach var="result" items="${requestScope.results}">
	<c:if test="${result.status == 'open'}">	
	<div class="result" id=${result.id}>
	<article class="search-result row">	
		<div class="col-md-6 excerpet">
			<h1>${result.title}</h2>
			<h3>${result.positionType}</h3>
			<h3>${result.description}</a></h3>
			<h4>${result.skills}</h4>			              
		</div>	
			<div class="col-md-2">
				<ul class="meta-search">
					<li>Salary <span>${result.salaryLevel}</span></li>
					<li>Location <span>${result.location}</span></li>
				</ul>
				<form method="get" action ="login" id=${result.id}>
				<input type="hidden" id="jobID" name="jobID" value=${result.id}>				
				<button name="action"  value ="removeJob" type="submit" class="btn btn-primary btn-lg btn-block">Remove</button>	
				</form>			
				<form method="post" name="apply" id=${result.id}>
				<input type="hidden" id="jobID" name="jobID" value=${result.id}>	
				<button name="apply" type="submit" class="btn btn-primary btn-lg btn-block">Apply</a>
				</form>	
			</div>
						
	</article>	
	</div>	
	</c:if>
</c:forEach>		
</div>
<input type="hidden" id="userID" name=userID value=${sessionScope.user.id}>
</body>
<script>
$(document).on("submit", "form", function(event)
		{		
			
			
			var userid = document.getElementById("userID").value;
		     
 
		    if(this.name =='apply'){
		    	 event.preventDefault();  
		    	var action = "apply";
		    	$.ajax({
	            url:'application',
	            data:{'action':action,'jobid':this.jobID.value,'userid':userid},
	            type:'post',
	            cache:false,
	            success:function(data){
	               alert('Application Successful');
	               $('#somediv').text(responseText); 
	            },
	            error:function(data){
	              alert('Error');
	            }
	         }); 
		    }
		});
</script>
</html>