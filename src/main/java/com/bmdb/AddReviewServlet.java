package com.bmdb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.Review;
import com.bmdb.persist.User;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet(urlPatterns = { "/addreview" })
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Review review = new Review();

		review.setComment(request.getParameter("comment"));
		review.setRating(Integer.parseInt(request.getParameter("rating")));
		review.setMovie(
				DBContext.get().getMoviesProvider().getMovie(Integer.parseInt(request.getParameter("movieId"))));
		review.setUser((User) request.getSession().getAttribute("username"));
		DBContext.get().getReviewsProvider().add(review);
		response.sendRedirect("reviews.jsp?movieId=" + review.getMovie().getId());
	}

}
