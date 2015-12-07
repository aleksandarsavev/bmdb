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
	private MoviesProvider moviesProvider;
	private GenresProvider genresProvider;

	private DBContext() {
		factory = Persistence.createEntityManagerFactory("bmdb");
		entityManager = factory.createEntityManager();
		userProvider = new UsersProvider(entityManager);
		reviewsProvider = new ReviewProvider(entityManager);
		moviesProvider = new MoviesProvider(entityManager);
		genresProvider = new GenresProvider(entityManager);
	}

	public static DBContext get() {
		return instance;
	}

	public UsersProvider getUsersProvider() {
		return userProvider;
	}

	public MoviesProvider getMoviesProvider() {
		return moviesProvider;
	}

	public ReviewProvider getReviewsProvider() {
		return reviewsProvider;
	}

	public GenresProvider getGenresProvider() {
		return genresProvider;
	}
}
