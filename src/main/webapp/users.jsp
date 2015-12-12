<%@page import="com.bmdb.persist.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	User user = (User) request.getSession().getAttribute("user");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User management</title>
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
<link rel="stylesheet" type="text/css" href="style/tables.css" />
</head>
<body>
    <jsp:include page="navigation.jsp"></jsp:include>

</body>
</html>