package com.project.service;

import java.util.List;

import com.project.domain.Film;

public interface IFilmM {
	
	public boolean add_film(Film film);	
	public boolean add_all_film(List<Film> film);
		
	public List<Film> get_all_film();
	
	public boolean update_film(String sfilm, String nfilm);

	public void clear_film();
	public boolean clear_film(Film film);
	
	public void set_kategoria_for_film(Film film, String kategoria_nazwa);
	public List<Film> get_all_film_for_kategori(String nazwa);
}
