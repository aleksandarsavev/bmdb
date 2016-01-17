<%@page import="java.util.List"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.Review"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviews</title>
<link rel="stylesheet" type="text/css" href="style/tables.css" />
</head>
<%
	String parameterUser = request.getParameter("userId");
	String parameterMovie = request.getParameter("movieId");
	List<Review> source;
	if (parameterMovie != null) {
		source = DBContext.get().getReviewsService().getReviewsByMovie(Integer.parseInt(parameterMovie));
	} else {
		source = DBContext.get().getReviewsService().getReviewsByUser(parameterUser);
	}
%>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<%
		if (parameterMovie != null) {
	%><h3>
		All reviews for the movie '<%=DBContext.get().getMoviesService().getMovie(Integer.parseInt(parameterMovie)).getName()%>'
	</h3>
	<%
	} else if (parameterUser != null) {
    %><h3>
        All reviews from user <%=DBContext.get().getUsersService().getUser(parameterUser).getUserName()%>
    </h3>
    <%
    } %>
	<div>
    <%if(source.isEmpty()){%>
    <h4>The result doesn't contain any reviews.</h4>
    <%}else{ %>
		<table>
			<tr>
				<th>Movie title</th>
				<th>Rating</th>
				<th>Comment</th>
				<th>Review by</th>
			</tr>
			<%
				for (Review review : source) {
			%>
			<tr>
				<td>
                <%if(parameterMovie==null) {%><a href="reviews.jsp?movieId=<%=review.getMovie().getId()%>" title="Go to reviews for '<%=review.getMovie().getName()%>'"><%} %>
                <%=review.getMovie().getName()%>
                <%if(parameterMovie==null) {%></a><%} %>
                </td>
				<td><%=review.getRating()%></td>
				<td><%=review.getComment()%></td>
				<td>
                <%if(parameterUser==null) {%><a href="reviews.jsp?userId=<%=review.getUser().getUserName()%>" title="Go to reviews from user <%=review.getUser().getName()%>"><%} %>
                <%=review.getUser().getUserName()%>
                <%if(parameterUser==null) {%></a><%} %>
                </td>
			</tr>
			<%
				}
			%>
		</table>
        <%} %>
	</div>
</body>
</html>