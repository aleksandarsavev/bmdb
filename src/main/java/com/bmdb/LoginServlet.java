package com.bmdb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.User;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User result = DBContext.get().getUsersService().login(username, password);
		if (result == null) {
			response.sendRedirect("login.jsp?success=false");
		} else {
			request.getSession().setAttribute("username", result);
			String redir = request.getParameter("redirect");
			redir=redir==null||redir.equals("null")?"account.jsp":redir;
			response.sendRedirect(redir);
		}
	}

}
