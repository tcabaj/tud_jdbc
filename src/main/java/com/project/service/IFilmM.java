package com.project.service;

import java.util.List;

import com.project.domain.Film;

public interface IFilmM {
	
	public boolean addFilm(Film film);	
	public boolean addAllFilm(List<Film> film);
		
	public List<Film> getAllFilm();
	
	public boolean updateFilm(Film sfilm, Film nfilm);

	public void clearFilm();
	public boolean clearFilm(Film film);
	
	public void setKategoriaForFilm(Film film, String kategoria_nazwa);
	public List<Film> getAllFilmForKategoria(String nazwa);
}
