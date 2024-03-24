<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Time Tracker</title>
	</head>
	<body>
		<h2>My Time</h2>
		<form id="filterForm" action="employeeTimeTracker" method="post" >
			<span>Employee : <input type="text" name="employeeName" id="employeeName" value="${employeeName}"></input></span>
			<br>
			Select Date: <input type="date" name="dateSelector" id="dateSelector" value="${selectedDate}" max="<%=java.time.LocalDate.now()%>">
			<input type="submit" value="Filter">
		</form>
		<br>
		<% if (request.getAttribute("totalTime") != null) { %>
		<table border="1">
			<thead>
				<tr>
					<th>Total In Time</th>
					<th>Total Out Time</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${totalTime.deviceLog_totalInTime}</td>
					<td>${totalTime.deviceLog_totalOutTime}</td>
				</tr>
			</tbody>
		</table>
		<% } %>
	</body>
</html>