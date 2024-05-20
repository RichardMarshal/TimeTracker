<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
<title>Header</title>
</head>
<body>
	<header class="navbar navbar-expand-lg navbar-light bg-dark">
		<div class="container">
			<a class="navbar-brand shape4" href="#" onclick="redirectToUM()">
				<img src="${pageContext.request.contextPath}/css/logo_white.png" alt="Logo" height="30">
				<script>
					function redirectToUM() {
						window.open('http://www.ultramain.com', '_blank');
					}
				</script>
			</a>

			<!-- Menu Group -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Menu
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/employeeTimeTracker">My Time</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/login">Logout</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</header>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</body>
</html>