<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div>
		<h1>Log in</h1>

		<%
			if (request.getParameter("success") != null && request.getParameter("success").equals("false")) {
		%>
		<span style="color: red"> Login was not successful. User name
			and/or password are wrong. </span>
		<%
			}
		%>
		<form class="login_form" name="login_form" action="login"
			method="post" autocomplete="on">
			User name:<br> <input class="field" type="text" name="username"
				required> <br> Password:<br> <input class="field"
				type="password" name="password" required> <br> <input
				type="submit" value="Log in"> <br> <span>If you
				don't have an account <a href="register.jsp">click here!</a>
                <input type="hidden" name="redirect" value="<%=request.getParameter("redirect") %>" />
			</span>
		</form>
	</div>
</body>
</html>