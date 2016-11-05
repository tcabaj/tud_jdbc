package com.project.service;

import java.util.List;

import com.project.domain.Kategoria;

public interface IKategoriaM {

	public boolean add_kategorie(Kategoria kategoria);
	
	public List<Kategoria> get_all_kategorie();
	
	public boolean update_kategoria(String nazwa, String nnazwa);

	public boolean add_all_pharmacies(List<Kategoria> kategoria);
	
	public void clear_kategoria();
	
	public int select_id_from_kategoria(String nazwa);
}