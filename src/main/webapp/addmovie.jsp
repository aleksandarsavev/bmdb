<%@page import="java.util.List"%>
<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.bmdb.persist.Genre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new movie</title>
<script type="text/javascript">
	var selected = [];
	function pp(cb) {
		var index = selected.indexOf(cb.value);
		if (cb.checked) {
			if (index == -1)
				selected.push(cb.value);
		} else {
			if (index > -1)
				selected.splice(index, 1);
		}
		document.getElementById("genres").value = selected.join(",")
	}
</script>
</head>

<%
	if (request.getSession().getAttribute("username") == null) {
                response.sendRedirect("login.jsp?redirect=addmovie.jsp");
            }
%>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<div>
		<h1>Add movie</h1>


		<form name="add_movie_form" action="addmovie" method="post"
			autocomplete="on">
			Movie title:<br> <input type="text" name="title" required>
			<br> Year:<br> <input type="number" name="year" min="1896"
				max="<%=Calendar.getInstance().get(Calendar.YEAR)%>" step="1" required>
			<br> <span>Genres:</span>
			<div style="max-width: 500px">
				<%
					List<Genre> genres = DBContext.get().getGenresService().getGenres();
					for (int i =0; i<genres.size();i++) {
						Genre genre = genres.get(i);
				%>
				<span ><div style="width:150px;display: inline-block;">
				<input type="checkbox" value="<%=genre.getId()%>"
					onclick="pp(this);"><%=genre.getName()%></input></div></span>
				<%
					}
				%>
				<textarea rows="10" cols="60" name="info" required></textarea>
				<input required="required" id="genres" name="genres" type="text"
					hidden="true" />
			</div>
			<input type="submit" value="Add movie">
		</form>
	</div>
</body>
</html>