package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;

public class GenresService extends EntityService {

	GenresService(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Genre> getGenres() {
		return getEntities(Genre.class);
	}

	public Genre getGenre(int id) {
		return getById(id, Genre.class);
	}
}
