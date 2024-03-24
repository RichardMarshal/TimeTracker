<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/employeeTimeTracker.css">
		<title>Time Tracker</title>
	</head>
	<body>
	<label class="text-label">Hello ${employeeName}!</label>
			<br>
	<div class="container">
		<form id="filterForm" action="employeeTimeTracker" method="post" >
			Select Date: 
			<input type="date" class="date-input" name="dateSelector" id="dateSelector" value="${selectedDate}" max="<%=java.time.LocalDate.now()%>">
			<button class="button filter__submit">
						<span class="button__text">Filter</span> 
						<i class="button__icon fas fa-chevron-right"></i>
					</button>
		</form>
		<br>
		<div class="company-ref">
					<a class="shape4" onclick="redirectToUM()"> 
						<script>
							function redirectToUM(){
								window.open('http://www.ultramain.com', '_blank');
							}
						</script>
					</a>
				</div>
		<div class="table-container">
		<% if (request.getAttribute("totalTime") != null) { %>
		<div class="inTime-container">
			<label class="inTime-label">Total In Time
				<span class="inTime-span">${totalTime.deviceLog_totalInTime}</span>
			</label>
		</div>
		<br>
		<div class="outTime-container">
			<label class="outTime-label">Total Out Time
				<span class="outTime-span">${totalTime.deviceLog_totalOutTime}</span>
			</label>
		</div>
<!-- 		<table border="1"> -->
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th>Total In Time</th> -->
<!-- 					<th>Total Out Time</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<%-- 					<td>${totalTime.deviceLog_totalInTime}</td> --%>
<%-- 					<td>${totalTime.deviceLog_totalOutTime}</td> --%>
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
		<% } %>
		</div>
		</div>
	</body>
</html>