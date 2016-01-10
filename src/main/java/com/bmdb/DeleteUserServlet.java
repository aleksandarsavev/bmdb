package com.bmdb;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;


public class DeleteUserServlet
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String userId = request.getParameter("id");
        DBContext.get().getUsersProvider().remove(Integer.parseInt(userId));
        try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        response.sendRedirect("users.jsp");
    }
}
