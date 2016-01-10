<%@page import="com.bmdb.persist.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
<div>
	<ul class="nav">
		<li class="nav"><a href="index.jsp">Home</a></li>
		<li class="nav"><a href="movies.jsp">Movies</a></li>
		<%
		    User name = (User) session.getAttribute("username");
		    if (name == null) {
		%>
		<li class="nav"><a href="login.jsp">Log in</a></li>
		<li class="nav"><a href="register.jsp">Sign up</a></li>
		<%
		    } else {
		%>
		<li class="nav"><a href="account.jsp" title="You are logged in as <%= name.getUserName() %>">My account(<%= name.getUserName() %>)</a></li>
		<li class="nav"><a href="" onclick="h()">Logout</a></li>
		<%
		    }
		%>
		<%
		    if (name != null && name.getUserName().equals("admin")) {
		%>
		<li class="nav"><a href="users.jsp">Users</a></li>
		<%
		    }
		%>
	</ul>
</div>
