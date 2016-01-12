package com.bmdb.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBContext {
	private static final DBContext instance = new DBContext();
	private UsersProvider userProvider;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private ReviewProvider reviewsProvider;
	private MoviesService moviesProvider;
	private GenresService genresProvider;

	private DBContext() {
		factory = Persistence.createEntityManagerFactory("bmdb");
		entityManager = factory.createEntityManager();
		userProvider = new UsersProvider(entityManager);
		reviewsProvider = new ReviewProvider(entityManager);
		moviesProvider = new MoviesService(entityManager);
		genresProvider = new GenresService(entityManager);
	}

	public static DBContext get() {
		return instance;
	}

	public UsersProvider getUsersProvider() {
		return userProvider;
	}

	public MoviesService getMoviesProvider() {
		return moviesProvider;
	}

	public ReviewProvider getReviewsProvider() {
		return reviewsProvider;
	}

	public GenresService getGenresProvider() {
		return genresProvider;
	}
}
