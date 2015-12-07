package com.bmdb.persist;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "MOVIE", catalog = "bmdb", schema = "PUBLIC")
@Entity
public class Movie {
	@ManyToMany
	@JoinTable(name = "M_G", joinColumns = {
			@JoinColumn(name = "movieid", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "genreid", referencedColumnName = "id") })
	private List<Genre> genres;
	private String country;
	private int year;
	private String info, name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getGenresStr() {
		return getGenres().stream().map(x -> x.getName()).collect(Collectors.joining(", "));
	}
}
