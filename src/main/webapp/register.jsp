<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<script type="text/javascript" src="script/script.js"></script>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div>
		<h1>Registration</h1>
		<%
		    if (request.getParameter("success") != null && request.getParameter("success").equals("false")) {
		%>
		<span style="color: red"> Registration was not successful. User
			name is already used. </span>
		<%
		    }
		%>
		<form class="register_form" name="register_form" action="register"
			method="post" autocomplete="on">
			User name:<br> <input class="field" type="text" name="username"
				required> <br>Name:<br> <input class="field"
				type="text" style="float: right" name="name"><br>E-mail:<br>
			<input class="field" type="email" name="email" autocomplete="off"
				required><br>Password:<br> <input class="field"
				type="password" name="password" required><br>Confirm
			password: <br> <input class="field" type="password"
				name="conf_password" required><br> <input type="submit"
				value="Register"> <br> <span>If you already have
				an account <a href="login.jsp">click here!</a>
			</span>
		</form>
	</div>
</body>
</html>