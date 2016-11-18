package com.project.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.project.domain.Film;
import com.project.domain.Kategoria;
import com.project.service.FilmM;
import com.project.service.KategoriaM;

public class FilmMTest {
	FilmM FilmManager = new FilmM();
	KategoriaM KategoriaManager = new KategoriaM();

	
	@Test
	public void checkConnection(){
		assertNotNull(KategoriaManager.getConnection());
		assertNotNull(FilmManager.getConnection());

	}
}
