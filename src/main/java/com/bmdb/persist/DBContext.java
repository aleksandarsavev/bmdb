package com.bmdb.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBContext {
	private static final DBContext instance = new DBContext();
	private UsersService userService;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private ReviewService reviewsService;
	private MoviesService moviesService;
	private GenresService genresService;

	private DBContext() {
		factory = Persistence.createEntityManagerFactory("bmdb");
		entityManager = factory.createEntityManager();
		userService = new UsersService(entityManager);
		reviewsService = new ReviewService(entityManager);
		moviesService = new MoviesService(entityManager);
		genresService = new GenresService(entityManager);
	}

	public static DBContext get() {
		return instance;
	}

	public UsersService getUsersService() {
		return userService;
	}

	public MoviesService getMoviesService() {
		return moviesService;
	}

	public ReviewService getReviewsService() {
		return reviewsService;
	}

	public GenresService getGenresService() {
		return genresService;
	}
}
