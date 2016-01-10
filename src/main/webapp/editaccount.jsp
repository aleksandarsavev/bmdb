<%@page import="com.bmdb.persist.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head >
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
<%User user = (User)request.getSession().getAttribute("username");
if (user == null) {
	%>
	<span>You have to be logged in to update your user settings.
		Click here to <a href="login.jsp">log in</a>
	</span>
	<%
	    } else {
	    	
	%>
	<h3>User settings</h3>
	<form action="updateuser" method="post">
		<span>Full name: </span>
		<br>
		<input name="name" type="text" maxlength="60" value="<%=user.getName()%>"/><br>
	<span>E-mail: </span>
		<br>
		<input name="email" type="email" maxlength="60" value="<%=user.getEmail()%>"/>
	 <br> <input type="submit" value="Submit changes"> <br>
	</form>
	<%} %>
</body>
</html>