package com.bmdb.persist;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

public class MoviesService extends EntityService<Movie>{

    MoviesService(EntityManager entityManager) {
        super(entityManager,  Movie.class);
    }

    public List<Movie> getMovies() {
        return getEntities();
    }


    /**
     * Order a list of movies.
     * 
     * @param orderBy property for sorting: "name" or "year" are supported values
     * @param ascending direction of sorting
     * @param source source to be sorted
     * @return a sorted list
     */
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
        return source.stream().filter(x -> x.getName().toLowerCase().contains(search.toLowerCase()) || x.getInfo().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Movie getMovie(int id) {
        return getById(id);
    }

    public void addMovie(Movie movie) {
        addEntity(movie);
    }


    public void delete(Movie movie) {
        DBContext.get().getReviewsService().removeByMovie(movie);
        removeEntity(movie);
    }


    /**
     * Deletes a movie from the database by its id. It will delete all related data to the movie.
     * 
     * @param id id of the movie
     */
	public void delete(int id) {
		delete(getMovie(id));
	}
}
