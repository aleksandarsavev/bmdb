package com.bmdb;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.Movie;
import com.bmdb.persist.MoviesProvider;


/**
 * Servlet implementation class MoviesServlet
 */
@WebServlet("/movies")
public class MoviesServlet
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesServlet()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String func = request.getParameter("func");
        if (func != null && func.equals("delete"))
        {
            MoviesProvider movies = DBContext.get().getMoviesProvider();
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
