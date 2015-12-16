package com.bmdb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmdb.persist.DBContext;
import com.bmdb.persist.Review;
import com.bmdb.persist.User;


public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
