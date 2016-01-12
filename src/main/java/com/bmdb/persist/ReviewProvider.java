package com.bmdb.persist;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


public class ReviewProvider extends EntityService
{

    ReviewProvider(EntityManager entityManager)
    {
        super(entityManager);
    }


    public List<Review> getReviewsByUser(String userName)
    {
        return getReviewsByUser(DBContext.get().getUsersProvider().getUser(userName));

    }


    public List<Review> getReviewsByUser(User user)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Review> q = cb.createQuery(Review.class);
        Root<Review> c = q.from(Review.class);
        ParameterExpression<User> p = cb.parameter(User.class);
        q.select(c).where(cb.equal(c.get("user"), p));

        TypedQuery<Review> query = getEntityManager().createQuery(q);
        query.setParameter(p, user);
        List<Review> results = query.getResultList();
        return results;
    }


    public List<Review> getReviewsByMovie(int movie)
    {
        return getReviewsByMovie(DBContext.get().getMoviesProvider().getMovie(movie));
    }


    /**
     * Gets a reviews list filtered by the given movie.
     * 
     * @param movie movie for filter
     * @return a list of filtered reviews
     */
    public List<Review> getReviewsByMovie(Movie movie)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Review> q = cb.createQuery(Review.class);
        Root<Review> c = q.from(Review.class);
        ParameterExpression<Movie> p = cb.parameter(Movie.class);
        q.select(c).where(cb.equal(c.get("movie"), p));

        TypedQuery<Review> query = getEntityManager().createQuery(q);
        query.setParameter(p, movie);
        List<Review> results = query.getResultList();
        return results;
    }


    public Review getReviewById(int id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Review> q = cb.createQuery(Review.class);
        Root<Review> c = q.from(Review.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("id"), p));

        TypedQuery<Review> query = getEntityManager().createQuery(q);
        query.setParameter(p, id);
        List<Review> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    
    public Review getReview(User user, Movie movie) {
        Optional<Review> review = getReviewsByUser(user).stream().filter(x -> x.getMovie() == movie).findFirst();
        return review.isPresent() ? review.get() : null;
    }

    public void add(Review review)
    {
        // remove a review for the movie if the user has already added review
        Review oldReview = getReview(review.getUser(), review.getMovie());
        if (oldReview != null) {
            remove(oldReview);
        }

        addEntity(review);
    }


    public void remove(Review review)
    {
        removeEntity(review);
    }

    /**
     * Removes a review by its ID.
     * 
     * @param id id of the review
     */
    public void remove(int id) {
        Review review = getReviewById(id);
        if (review != null) {
            remove(review);
        }
    }

    /**
     * Removes all reviews for the given movie.
     * 
     * @param movie movie which will be used for filtering of the reviews
     */
    public void removeByMovie(Movie movie)
    {
        getReviewsByMovie(movie).stream().forEach(x -> remove(x));
    }


    public void removeByUser(User user)
    {
        getReviewsByUser(user).stream().forEach(x -> remove(x));
    }
}
