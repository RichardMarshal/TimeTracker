<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/employeeTimeTracker.css">
<title>Time Tracker</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<label class="text-label">Hello ${employeeName}!</label>
	<br>
	<div class="container" id="timeTracker">
		<form id="filterForm" action="employeeTimeTracker" method="post">
			Select Date: 
			<input type="date" class="date-input" name="dateSelector" id="dateSelector" value="${selectedDate}" max="<%=java.time.LocalDate.now()%>">
			<button class="button filter__submit">
				<span class="button__text">Filter</span> 
				<i class="button__icon fas fa-chevron-right"></i>
			</button>
		</form>
		<br>
		<div class="table-container">
			<%
			if (request.getAttribute("deviceLogDto") != null) {
			%>
			<div class="inTime-container">
				<label class="inTime-label">
					Total In Time
					<div class="inTime-div">
						<span class="inTime-span">${deviceLogDto.deviceLog_totalInHour}</span>
						<span class="sperator">:</span>
						<span class="inTime-span">${deviceLogDto.deviceLog_totalInMin}</span>
						<span class="sperator">:</span>
						<span class="inTime-span">${deviceLogDto.deviceLog_totalInSec}</span>
					</div>
					<div class="description-div">
						<span class="description hours">hours</span>
						<span class="description min">min</span>
						<span class="description sec">sec</span>
					</div>
				</label>
			</div>
			<br>
			<div class="outTime-container">
				<label class="outTime-label">
					Total Out Time
					<div class="outTime-div">
						<span class="outTime-span">${deviceLogDto.deviceLog_totalOutHour}</span>
						<span class="sperator">:</span>
						<span class="outTime-span">${deviceLogDto.deviceLog_totalOutMin}</span>
						<span class="sperator">:</span>
						<span class="outTime-span">${deviceLogDto.deviceLog_totalOutSec}</span>
					</div>
					<div class="description-div">
						<span class="description hours">hours</span>
						<span class="description min">min</span>
						<span class="description sec">sec</span>
					</div>
				</label>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>