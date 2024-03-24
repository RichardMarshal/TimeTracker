<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<div class="container">
		<div class="screen">
			<div class="screen__content">
				<form class="login" action="authenticate" method="post">
					<div class="login__field">
						<i class="login__icon fas fa-user"></i> 
						<input type="text" class="login__input" placeholder="User Email" name="username">
					</div>
					<div class="login__field">
						<i class="login__icon fas fa-lock"></i> 
						<input type="password" class="login__input" placeholder="Password" name="password">
					</div>
					<button class="button login__submit">
						<span class="button__text">Log In</span> 
						<i class="button__icon fas fa-chevron-right"></i>
					</button>
				</form>
				<%
				if (request.getAttribute("error") != null) {
				%>
					<script>
               			alert('<%=request.getAttribute("error")%>');
					</script>
				<%
				}
				%>
				<div class="company-ref">
					<a class="shape4" onclick="redirectToUM()"> 
						<script>
							function redirectToUM(){
								window.open('http://www.ultramain.com', '_blank');
							}
						</script>
					</a>
				</div>
			</div>
			<div class="screen__background">
				<span class="screen__background__shape screen__background__shape4"></span>
				<span class="screen__background__shape screen__background__shape3"></span>
				<span class="screen__background__shape screen__background__shape2"></span>
				<span class="screen__background__shape screen__background__shape1"></span>
			</div>
		</div>
	</div>
	<script>
		function submitForm() {
			document.getElementById("loginForm").submit();
		}
	</script>
</body>
</html>