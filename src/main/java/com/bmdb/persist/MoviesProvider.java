package com.bmdb.persist;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class MoviesProvider {
    private EntityManager entityManager;

    MoviesProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Movie> getMovies() {
        TypedQuery<Movie> createQuery = entityManager
                .createQuery(entityManager.getCriteriaBuilder().createQuery(Movie.class));
        return createQuery.getResultList();
    }

    public List<Movie> orderBy(String orderBy, boolean ascending, List<Movie> source) {
        int dir = ascending ? 1 : -1;
        return source.stream().sorted((x, y) -> {
            if (orderBy.equals("name")) {
                return x.getName().compareTo(y.getName()) * dir;
            }
            return Integer.compare(x.getYear(), y.getYear()) * dir;
        }).collect(Collectors.toList());
    }

    public List<Movie> searchInMovies(String search, List<Movie> source) {
        return source.stream().filter(x -> x.getName().contains(search) || x.getInfo().contains(search))
                .collect(Collectors.toList());
    }

    public Movie getMovie(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> q = cb.createQuery(Movie.class);
        Root<Movie> c = q.from(Movie.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("id"), p));

        TypedQuery<Movie> query = entityManager.createQuery(q);
        query.setParameter(p, id);
        List<Movie> results = query.getResultList();
        if (results.isEmpty())
            return null;
        return results.get(0);
    }

    public void addMovie(Movie movie) {
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
    }
}
