<%@page import="com.bmdb.persist.User"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My account</title>
<link rel="stylesheet" type="text/css" href="style/tables.css" />
</head>
<%
    User user = (User) request.getSession().getAttribute("username");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
    String parameterUser = user.getUserName();
    List<Review> source;
    source = DBContext.get().getReviewsProvider().getReviewsByUser(parameterUser);
%>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div id="container">
		<table id="movies_table">
			<tr>
				<th>Movie title</th>
				<th>Rating</th>
				<th>Comment</th>
				<th>Review by</th>
			</tr>
			<%
			    for (Review movie : source) {
			%>
			<tr>
				<td><%=movie.getMovie().getName()%></td>
				<td><%=movie.getRating()%></td>
				<td><%=movie.getComment()%></td>
				<td><%=movie.getUser().getName()%></td>
				<%
				    if (request.getAttribute("username") != null) {
				%>
				<td><a href="addreview.jsp?movieId=<%=movie.getId()%>"
					title="Add review and reting for the movie">Add review</a></td>
				<%
				    }
				%>
			</tr>
			<%
			    }
			%>
		</table>
	</div>
</html>