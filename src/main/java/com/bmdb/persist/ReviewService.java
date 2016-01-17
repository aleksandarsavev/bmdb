package com.bmdb.persist;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;


public class ReviewService extends EntityService<Review>
{

    ReviewService(EntityManager entityManager)
    {
        super(entityManager, Review.class);
    }


    public List<Review> getReviewsByUser(String userName)
    {
        return getReviewsByUser(DBContext.get().getUsersService().getUser(userName));

    }


    public List<Review> getReviewsByUser(User user)
    {
        return filterEntities(user, "user", User.class);
    }


    public List<Review> getReviewsByMovie(int movie)
    {
        return getReviewsByMovie(DBContext.get().getMoviesService().getMovie(movie));
    }


    /**
     * Gets a reviews list filtered by the given movie.
     * 
     * @param movie movie for filter
     * @return a list of filtered reviews
     */
    public List<Review> getReviewsByMovie(Movie movie)
    {
        return filterEntities(movie, "movie", Movie.class);
    }


    public Review getReviewById(int id) {
        return getById(id);
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
