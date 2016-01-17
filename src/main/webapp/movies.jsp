<%@page import="com.bmdb.persist.User"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.StringJoiner"%>
<%@page import="java.util.List"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="style/tables.css" />
<title>Movies</title>
<style type="text/css">
</style>
<%
	String search = request.getParameter("search");
	String orderBy = request.getParameter("orderBy");
	String order = request.getParameter("order");
	order = order == null ? "asc" : order;
	search = search == null ? "" : search;
	User user = (User) request.getSession().getAttribute("username");
%>
<script type="text/javascript">
	function sortby(column) {
		var orderedBy = document.getElementById("orderBy")
				.getAttribute("value");
		var order = document.getElementById("order").getAttribute("value");
		if (orderedBy == column) {
			order = order == "asc" ? "desc" : "asc";
		}
		var orderBy = "orderBy=" + column;
		var orderr = "order=" + order;
		var search = "search="
				+ document.getElementById("search").getAttribute("value");
		window.location = "movies.jsp?" + [ orderBy, orderr, search ].join("&");
	}

	function search(value) {
		var orderedBy = document.getElementById("orderBy")
				.getAttribute("value");
		var order = document.getElementById("order").getAttribute("value");
		var orderBy = "orderBy=" + orderedBy;
		var orderr = "order=" + order;
		var search = "search=" + value;
		window.location = "movies.jsp?" + [ orderBy, orderr, search ].join("&");
	}
	
	function deleteMovie(idm){
		post("deletemovie?id=" + idm + "&redirect=movies.jsp", null);
	}
</script>
</head>
<body>
	<oo id="orderBy" value="<%=orderBy%>" ></oo>
	<oo id="order" value="<%=order == null ? new String() : order%>" ></oo>
	<oo id="search" value="<%=search == null ? new String() : search%>" ></oo>
	<jsp:include page="navigation.jsp"></jsp:include>
    <h3>Movies</h3>
    <%if(DBContext.get().getMoviesService().getMovies().isEmpty()){
        %>
        <span>The movie data base is empty. You can add movies by clicking 
        <a href="addmovie.jsp">here</a> and filling the form.</span><%
    }else{ %>
	<div id="container">
		<input id="searchTxt" type="text" value="<%=search%>"/><input type="button"
			value="Search"
			onclick="search(document.getElementById('searchTxt').value)" />
        
         <% List<Movie> source = DBContext.get().getMoviesService().getMovies();
                if (search != null && search.length() > 0) {
                    source = DBContext.get().getMoviesService().searchInMovies(search, source);
                }
                if (orderBy != null) {
                    source = DBContext.get().getMoviesService().orderBy(orderBy, order.equals("asc"), source);
                }
                
        if(source.isEmpty()){%>
        <br>
        <span>BMDB couldn't find any movies with the search word '<%=search  %>'</span>
        <%}else{%>
		<table id="movies_table">
			<tr>
				<th id="name_th" class="sort" onclick="sortby('name')" title="Sort by Title">Title</th>
				<th id="info_th">Info</th>
				<th id="year_th" class="sort" title="Sort by Year" onclick="sortby('year')">Year</th>
				<th id="genre_th">Genre</th>
				<th colspan="3">Options</th>
			</tr>
			<%
				for (Movie movie : source) {
			%>
			<tr>
				<td><%=movie.getName()%></td>
				<td><%=movie.getInfo()%></td>
				<td><%=movie.getYear()%></td>
				<td><%=movie.getGenresStr()%></td>
				<%
					if (user != null) {
				%><td><a
					href="addreview.jsp?movieId=<%=movie.getId()%>"
					title="Add review and rating for the movie">Add review</a></td>
				<%
					}
				%>
				<td><a href="reviews.jsp?movieId=<%=movie.getId()%>"
					title="Show all reviews for the movie">Reviews</a></td>
				<%
					if (user != null && user.getName().equals("admin")) {
				%>
				<td><a href="" onclick="deleteMovie(<%=movie.getId()%>)"
					title="Delete movie">Delete</a></td>
				<%
					}
				%>

			</tr>
			<%
				}
			%>
		</table>
		<span>You can't find the movie you love or hate? Click <a
			href="addmovie.jsp">here</a> and add movies to your favorite data
			base.
		</span><%} %>
        <%} %>
	</div>
</body>
</html>