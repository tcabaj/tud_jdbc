package com.project.service;

import java.util.List;

import com.project.domain.Film;

public interface IFilmM {
	
	public boolean add_kategorie(Film film);
	
	public boolean add_all_pharmacies(List<Film> film);
		
	public List<Film> get_all_film();
	
	public boolean update_film(String sfilm, String nfilm);

	public void clear_kategoria();
	public boolean clear_kategoria(Film film);
	
}
