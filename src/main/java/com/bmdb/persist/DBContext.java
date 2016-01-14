package com.bmdb.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBContext {
	private static final DBContext instance = new DBContext();
	private UsersService userProvider;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private ReviewService reviewsProvider;
	private MoviesService moviesProvider;
	private GenresService genresProvider;

	private DBContext() {
		factory = Persistence.createEntityManagerFactory("bmdb");
		entityManager = factory.createEntityManager();
		userProvider = new UsersService(entityManager);
		reviewsProvider = new ReviewService(entityManager);
		moviesProvider = new MoviesService(entityManager);
		genresProvider = new GenresService(entityManager);
	}

	public static DBContext get() {
		return instance;
	}

	public UsersService getUsersProvider() {
		return userProvider;
	}

	public MoviesService getMoviesProvider() {
		return moviesProvider;
	}

	public ReviewService getReviewsProvider() {
		return reviewsProvider;
	}

	public GenresService getGenresProvider() {
		return genresProvider;
	}
}
