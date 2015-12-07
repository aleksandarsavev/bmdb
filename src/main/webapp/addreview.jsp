<%@page import="com.bmdb.persist.User"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add review</title>
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<%
	    int movieId = Integer.parseInt(request.getParameter("movieId"));
	    Movie movie = DBContext.get().getMoviesProvider().getMovie(movieId);
	    User user = (User) session.getAttribute("username");
	    if (user == null) {
	%>
	<span>You have to be logged in to add a review for a movie.
		Click here to <a href="login.jsp">log in</a>
	</span>
	<%
	    } else {
	%>
	<span>Add review for <span><%=movie.getName()%>(<%=movie.getYear()%>)</span></span>
	<form action="addreview" method="post">
		<input name="movieId" type="hidden" value="<%=movieId%>"></input>
		Comment:<br> <input type="text" name="comment" required>
		<br> Rating:<br> <select name="rating">
			<%
			    for (int i = 1; i < 11; i++) {
			%>
			<option value="<%=Integer.toString(i)%>"><%=i%></option>
			<%
			    }
			%>
		</select> <br> <input type="submit" value="Submit review"> <br>
	</form>
	<%
	    }
	%>
</body>
</html>