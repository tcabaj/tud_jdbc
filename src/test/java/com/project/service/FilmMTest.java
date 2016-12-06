package com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.domain.Film;
import com.project.domain.Kategoria;
import com.project.service.FilmM;
import com.project.service.KategoriaM;

public class FilmMTest {
	FilmM FilmManager = new FilmM();
	KategoriaM KategoriaManager = new KategoriaM();

	private final static String NAME_1 = "Kac Vegas";
	private final static String NAME_2 = "...";
	
	
	@Before
	public void clearDatabase(){
		KategoriaManager.clearKategoria();
		FilmManager.clearFilm();
	}
	@Test
	public void checkConnection(){
		assertNotNull(KategoriaManager.getConnection());
		assertNotNull(FilmManager.getConnection());
	}
	@Test
	public void testAddOne(){
		KategoriaManager.addKategoria(new Kategoria("Komedia"));
		Film film = new Film(NAME_1, 200, 100, KategoriaManager.selectIdFromKategoria("Komedia"));
		assertEquals(true,FilmManager.addFilm(film));
		
		List<Film> filmy = FilmManager.getAllFilm();
		Film medicinePom = filmy.get(0);
		assertEquals(NAME_1, medicinePom.getTytul());
	}
	@Test
	public void testAddAll(){
		KategoriaManager.addKategoria(new Kategoria("Przyrodniczy"));
		List<Film> good = new ArrayList<Film>();
		int k_id = KategoriaManager.selectIdFromKategoria("Przyrodniczy");
		Film f1 = new Film("Kot", 200, 10, k_id);
		Film f2 = new Film("Zwierzęta", 200, 10, k_id);
		Film f3 = new Film("Pies", 200, 10, k_id);
		good.add(f1);
		good.add(f2);
		good.add(f3);
		
		FilmManager.addAllFilm(good);
		
		List<Film> all = FilmManager.getAllFilm();
		assertEquals(3, all.size());
	}
	@Test
	public void testUpdate(){
		KategoriaManager.addKategoria(new Kategoria("Test1"));
		KategoriaManager.addKategoria(new Kategoria("Test2"));

		int k_id1 = KategoriaManager.selectIdFromKategoria("Test1");
		int k_id2 = KategoriaManager.selectIdFromKategoria("Test2");
		Film film = new Film(NAME_2, 10, 30, k_id1);
		FilmManager.addFilm(film);
		
		Film filmNew = new Film(NAME_2, 99, 4, k_id2);
		assertEquals(true,FilmManager.updateFilm(film, filmNew));
	}
	@Test	
	public void testGetFilms(){
		KategoriaManager.clearKategoria();
		FilmManager.clearFilm();
		KategoriaManager.addKategoria(new Kategoria("Test1"));
		KategoriaManager.addKategoria(new Kategoria("Test2"));
		
		int id1 = KategoriaManager.selectIdFromKategoria("Test1");
		int id2 = KategoriaManager.selectIdFromKategoria("Test2");

		Film f1 = new Film("... ", 144, 230, id1);
		Film f2 = new Film("..1 ", 144, 230, id2);
		Film f3 = new Film("..2 ", 144, 230, id1);
		FilmManager.addFilm(f1);
		FilmManager.addFilm(f2);
		FilmManager.addFilm(f3);
		
		List<Film> filmy =  FilmManager.getAllFilm();
		assertEquals(3, filmy.size());
	}
	@Test 
	public void testDeleteOne(){
		KategoriaManager.addKategoria(new Kategoria("Komedia"));
		int k_id1 = KategoriaManager.selectIdFromKategoria("Komedia");
		Film film1 = new Film(NAME_1, 211, 1000, k_id1);
		FilmManager.addFilm(film1);
		Film film2 = new Film("Kac Vegas 2", 200, 200, k_id1);
		FilmManager.addFilm(film2);
		
		FilmManager.clearFilm(film1);
		List<Film> filmy = FilmManager.getAllFilm();
		assertEquals(1, filmy.size());
		Film check = filmy.get(0);
		assertEquals("Kac Vegas 2", check.getTytul());
	}
	@Test
	public void testDelete(){
		KategoriaManager.addKategoria(new Kategoria("Dramat"));
		int k_id1 = KategoriaManager.selectIdFromKategoria("Dramat");
		Film film1 = new Film(NAME_2, 211, 1000, k_id1);
		FilmManager.addFilm(film1);
		FilmManager.clearFilm();
		List<Film> filmy = FilmManager.getAllFilm();
		assertEquals(0, filmy.size());
	}	
}
