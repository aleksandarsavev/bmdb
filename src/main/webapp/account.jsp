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
<script type="text/javascript">
function deleteReview(reviewId){
	post("deletereview?id="+reviewId, null);
	location.reload(true);
}
</script>
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
	<h3>
		Hi,
		<%=user.getUserName()%>!<%=(source.isEmpty() ? " You don't have any reviews yet."
                            : "Here is a list of your reviews.")%></h3>
	<div>
		<%
		    if (source.isEmpty())
		    {
		        %>
		        <span>Click <a href="movies.jsp">here</a> and see a full list with movies.</span><br/>
		        <span>Click <a href="addmovie.jsp">here</a> and add movies to your favorite data base.</span>
		        <%
		    }
		    else
		    {
		%>
		<table id="movies_table">
			<tr>
				<th>Movie title</th>
				<th>Rating</th>
				<th>Comment</th>
				<th>Review by</th>
                <th colspan="2">Options</th>
			</tr>
			<%
			    for (Review movie : source) {
			%>
			<tr>
				<td><a href="reviews.jsp?movieId=<%=movie.getMovie().getId()%>"><%=movie.getMovie().getName()%></a></td>
				<td><%=movie.getRating()%></td>
				<td><%=movie.getComment()%></td>
				<td>Myself</td>
                <td title="Change your review for '<%=movie.getMovie().getName()%>'"><a href="addreview.jsp?movieId=<%=movie.getMovie().getId()%>">Edit</a></td>
                <td title="Delete the review"><a href="#" onclick="deleteReview(<%=movie.getId()%>)">Delete</a></td>
			</tr>
			<%
			    }
			%>
		</table>
		<span>You can't find the movie you love or hate? Click <a
			href="addmovie.jsp">here</a> and add movies to your favorite data
			base.
		</span><%} %>
	</div>
</html>