<%@page import="java.util.List"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.Review"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviews</title>
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
<link rel="stylesheet" type="text/css" href="style/tables.css" />
</head>
<%
	String parameterUser = request.getParameter("userId");
	String parameterMovie = request.getParameter("movieId");
	List<Review> source;
	if (parameterMovie != null) {
		source = DBContext.get().getReviewsProvider().getReviewsByMovie(Integer.parseInt(parameterMovie));
	} else {
		source = DBContext.get().getReviewsProvider().getReviewsByUser(parameterUser);
	}
%>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<%
		if (parameterMovie != null) {
	%><h3>
		All reviews for the movie '<%=DBContext.get().getMoviesProvider().getMovie(Integer.parseInt(parameterMovie)).getName()%>'
	</h3>
	<%
	} %>
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
				<td><%=(movie.getUser() == null ? "null" : movie.getUser().getName())%></td>
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
</body>
</html>