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
	public void clear_database(){
		KategoriaManager.clear_kategoria();
		FilmManager.clear_film();
	}
	@Test
	public void checkConnection(){
		assertNotNull(KategoriaManager.getConnection());
		assertNotNull(FilmManager.getConnection());
	}
	@Test
	public void test_add_one(){
		KategoriaManager.add_kategoria(new Kategoria("Komedia"));
		Film film = new Film(NAME_1, 200, 100, KategoriaManager.select_id_from_kategoria("Komedia"));
		assertEquals(true,FilmManager.add_film(film));
		
		List<Film> filmy = FilmManager.get_all_film();
		Film medicinePom = filmy.get(0);
		assertEquals(NAME_1, medicinePom.getTytul());
	}
	@Test
	public void test_add_all(){
		KategoriaManager.add_kategoria(new Kategoria("Przyrodniczy"));
		List<Film> good = new ArrayList<Film>();
		int k_id = KategoriaManager.select_id_from_kategoria("Przyrodniczy");
		Film f1 = new Film("Kot", 200, 10, k_id);
		Film f2 = new Film("Zwierzęta", 200, 10, k_id);
		Film f3 = new Film("Pies", 200, 10, k_id);
		good.add(f1);
		good.add(f2);
		good.add(f3);
		
		FilmManager.add_all_film(good);
		
		List<Film> all = FilmManager.get_all_film();
		assertEquals(3, all.size());
	}
	@Test
	public void test_update(){
		KategoriaManager.add_kategoria(new Kategoria("Test1"));
		KategoriaManager.add_kategoria(new Kategoria("Test2"));

		int k_id1 = KategoriaManager.select_id_from_kategoria("Test1");
		int k_id2 = KategoriaManager.select_id_from_kategoria("Test2");
		Film film = new Film(NAME_2, 10, 30, k_id1);
		FilmManager.add_film(film);
		
		Film filmNew = new Film(NAME_2, 99, 4, k_id2);
		assertEquals(true,FilmManager.update_film(film, filmNew));
	}
	@Test	
	public void test_get_films(){
		KategoriaManager.clear_kategoria();
		FilmManager.clear_film();
		KategoriaManager.add_kategoria(new Kategoria("Test1"));
		KategoriaManager.add_kategoria(new Kategoria("Test2"));
		
		int id1 = KategoriaManager.select_id_from_kategoria("Test1");
		int id2 = KategoriaManager.select_id_from_kategoria("Test2");

		Film f1 = new Film("... ", 144, 230, id1);
		Film f2 = new Film("..1 ", 144, 230, id2);
		Film f3 = new Film("..2 ", 144, 230, id1);
		FilmManager.add_film(f1);
		FilmManager.add_film(f2);
		FilmManager.add_film(f3);
		
		List<Film> filmy =  FilmManager.get_all_film();
		assertEquals(3, filmy.size());
	}
	@Test 
	public void test_delete_one(){
		KategoriaManager.add_kategoria(new Kategoria("Komedia"));
		int k_id1 = KategoriaManager.select_id_from_kategoria("Komedia");
		Film film1 = new Film(NAME_1, 211, 1000, k_id1);
		FilmManager.add_film(film1);
		Film film2 = new Film("Kac Vegas 2", 200, 200, k_id1);
		FilmManager.add_film(film2);
		
		FilmManager.clear_film(film1);
		List<Film> filmy = FilmManager.get_all_film();
		assertEquals(1, filmy.size());
		Film check = filmy.get(0);
		assertEquals("Kac Vegas 2", check.getTytul());
	}
	@Test
	public void test_delete(){
		KategoriaManager.add_kategoria(new Kategoria("Dramat"));
		int k_id1 = KategoriaManager.select_id_from_kategoria("Dramat");
		Film film1 = new Film(NAME_2, 211, 1000, k_id1);
		FilmManager.add_film(film1);
		FilmManager.clear_film();
		List<Film> filmy = FilmManager.get_all_film();
		assertEquals(0, filmy.size());
	}	
}
