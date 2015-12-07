package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class ReviewProvider {
	private final EntityManager entityManager;

	ReviewProvider(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Review> getReviewsByUser(String userName) {
		return getReviewsByUser(DBContext.get().getUsersProvider().getUser(userName));
	
	}
	public List<Review> getReviewsByUser(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Review> q = cb.createQuery(Review.class);
		Root<Review> c = q.from(Review.class);
		ParameterExpression<User> p = cb.parameter(User.class);
		q.select(c).where(cb.equal(c.get("user"), p));

		TypedQuery<Review> query = entityManager.createQuery(q);
		query.setParameter(p, user);
		List<Review> results = query.getResultList();
		return results;
	}

	public List<Review> getReviewsByMovie(int movie) {
		return getReviewsByMovie(DBContext.get().getMoviesProvider().getMovie(movie));
	}

	public List<Review> getReviewsByMovie(Movie movie) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Review> q = cb.createQuery(Review.class);
		Root<Review> c = q.from(Review.class);
		ParameterExpression<Movie> p = cb.parameter(Movie.class);
		q.select(c).where(cb.equal(c.get("movie"), p));

		TypedQuery<Review> query = entityManager.createQuery(q);
		query.setParameter(p, movie);
		List<Review> results = query.getResultList();
		return results;
	}

	public void add(Review review) {
		entityManager.getTransaction().begin();
		entityManager.persist(review);
		entityManager.getTransaction().commit();
	}

}
