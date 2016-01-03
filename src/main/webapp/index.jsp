<%@page import="com.bmdb.persist.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
</head>
<body>
    <jsp:include page="navigation.jsp"></jsp:include>
    <h3>BMDB - Bulgarian movie data base</h3>
    <h4>The place where you can find and review your favorite
        movies.</h4>
    <%
        if (request.getSession().getAttribute("username") == null) {
    %>
    <h5>
        If you are a new user of the site we recommend you to sign up <a
            href="register.jsp">here</a>.
    </h5>
    <h5>
        If you are already a registered user click <a href="login.jsp">here</a>
        to log into your profile.
    </h5>
    <h5>
        You can simply click <a href="movies.jsp">here</a> to see a full
        list of movies in the data base.
    </h5>
    <%
        }
    %>
</body>
</html>
