package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class GenresProvider {
	private EntityManager entityManager;

	GenresProvider(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Genre> getGenres() {
		TypedQuery<Genre> createQuery = entityManager
				.createQuery(entityManager.getCriteriaBuilder().createQuery(Genre.class));
		return createQuery.getResultList();
	}

	public Genre getGenre(int id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Genre> q = cb.createQuery(Genre.class);
		Root<Genre> c = q.from(Genre.class);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		q.select(c).where(cb.equal(c.get("id"), p));

		TypedQuery<Genre> query = entityManager.createQuery(q);
		query.setParameter(p, id);
		List<Genre> results = query.getResultList();
		if (results.isEmpty())
			return null;
		return results.get(0);
	}
}
