package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;

public class GenresService extends EntityService<Genre> {

	GenresService(EntityManager entityManager) {
		super(entityManager, Genre.class);
	}

	public List<Genre> getGenres() {
		return getEntities();
	}

	public Genre getGenre(int id) {
		return getById(id);
	}
}
