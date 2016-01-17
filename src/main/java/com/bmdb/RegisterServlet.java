package com.bmdb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.User;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User bean = new User();
        bean.setUserName(request.getParameter("username"));
        bean.setEmail(request.getParameter("email"));
        bean.setPassword(request.getParameter("password"));
        bean.setName(request.getParameter("name"));

        boolean result = DBContext.get().getUsersService().register(bean);
        response.getWriter().println(result);
        response.setStatus(200);
        if (result) {
            response.sendRedirect("account.jsp");
            request.getSession().setAttribute("username", bean);
        } else {
            response.sendRedirect("register.jsp?success=false");
        }
    }
}
