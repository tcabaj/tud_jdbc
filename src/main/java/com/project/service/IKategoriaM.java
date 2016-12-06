package com.project.service;

import java.util.List;

import com.project.domain.Kategoria;

public interface IKategoriaM {

	public boolean addKategoria(Kategoria kategoria);
	
	public List<Kategoria> getAllKategorie();
	
	public boolean updateKategoria(String nazwa, String nnazwa);

	public boolean addAllKategoria(List<Kategoria> kategoria);
	
	public void clearKategoria();
	
	public int selectIdFromKategoria(String nazwa);
}