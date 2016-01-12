package com.bmdb;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.MoviesService;


public class MoviesServlet
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String func = request.getParameter("func");
        if (func != null && func.equals("delete"))
        {
            MoviesService movies = DBContext.get().getMoviesProvider();
            String idParameter = request.getParameter("id");
            if (idParameter != null) {
                movies.delete(movies.getMovie(Integer.parseInt(idParameter)));
            }
            else {
                System.err.println("Movie id cannot be null");
            }
        }
    }
}
