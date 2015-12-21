<%@page import="com.bmdb.persist.DBContext"%>
<%@page import="com.bmdb.persist.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    User user = (User)request.getSession().getAttribute("user");
    if (user == null || !user.getUserName().equals("admin"))
    {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User management</title>
<script type="text/javascript" src="script/navigator.js"></script>
<link rel="stylesheet" type="text/css" href="style/navigator.css" />
<link rel="stylesheet" type="text/css" href="style/tables.css" />
<script type="text/javascript">
    function remove(id) {
        post("deleteuser?id="+id, null);
        location.reload();
    }
</script>
</head>
<body>
    <jsp:include page="navigation.jsp"></jsp:include>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>User name</th>
                <th>Name</th>
                <th>E-mail</th>
                <th>Options</th>
            </tr>
            <%
                for (User u : DBContext.get().getUsersProvider().getUsers())
                {
            %>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getUserName()%></td>
                <td><%=u.getUserName()%></td>
                <td><%=u.getEmail()%></td>
                <td><a href="reviews.jsp?userId=<%=u.getId()%>"
                    title="Reviews by user <%=u.getUserName()%>">Reviews</a></td>
                <td><a href="#" onclick="remove(<%=u.getId()%>)">Delete</a></td>
            </tr>
            <%
                }
            %>
            <tr>
        </table>
    </div>
</body>
</html>