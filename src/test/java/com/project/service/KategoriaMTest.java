package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.domain.Kategoria;
import com.project.service.KategoriaM;
import com.project.domain.Film;
import com.project.service.FilmM;


public class KategoriaMTest {
	
	KategoriaM KategoriaManager = new KategoriaM();
	
	private final static String NAME_1 = "Komedia‎";
	private final static String NAME_2 = "Historyczny";
	
	@Before
	public void clear_database(){
		KategoriaManager.clearKategoria();
	}
	@Test
	public void test_connection(){
		assertNotNull(((KategoriaM)KategoriaManager).getConnection());
	}
	@Test
	public void test_add_one(){
		Kategoria kategoria = new Kategoria(NAME_1);
		assertEquals(true,KategoriaManager.addKategoria(kategoria));
		List<Kategoria> kategorie = KategoriaManager.getAllKategorie();
		Kategoria KategoriaPom= kategorie.get(0);
		assertEquals(NAME_1, KategoriaPom.getNazwa());
	}
	@Test
	public void test_delete_one(){
		KategoriaManager.clearKategoria();
		Kategoria kategoria = new Kategoria(NAME_2);
		KategoriaManager.addKategoria(kategoria);
		
		assertEquals(1,((KategoriaM)KategoriaManager).deleteKategoria(kategoria));		
	}
	@Test
	public void test_add_all(){
		List<Kategoria> good = new ArrayList<Kategoria>();
		Kategoria k1 = new Kategoria("Komedia");
		Kategoria k2 = new Kategoria("Historyczny");
		Kategoria k3 = new Kategoria ("Familijne");
		good.add(k1);
		good.add(k2);
		good.add(k3);
	
		KategoriaManager.clearKategoria();
		KategoriaManager.addAllKategoria(good);
		List<Kategoria> all = KategoriaManager.getAllKategorie();
		assertEquals(3, all.size());
		
		// add all bad 
		List<Kategoria> bad = new ArrayList<Kategoria>();
		Kategoria b1 = new Kategoria("Komedia");
		Kategoria b2 = new Kategoria("Historyczny");
		Kategoria b3 = new Kategoria ("Komedia");
		bad.add(b1);
		bad.add(b2);
		bad.add(b3);
		
		KategoriaManager.clearKategoria();		
		KategoriaManager.addAllKategoria(bad);		
		List<Kategoria> all_bad = KategoriaManager.getAllKategorie();
		assertEquals(0, all_bad.size());
	}
	@Test
	public void test_delete_all(){
		//Adding : 
		List<Kategoria> good = new ArrayList<Kategoria>();
		Kategoria k1 = new Kategoria("Komedia");
		Kategoria k2 = new Kategoria("Historyczny");
		Kategoria k3 = new Kategoria ("Familijne");
		good.add(k1);
		good.add(k2);
		good.add(k3);		
		KategoriaManager.clearKategoria();		
		KategoriaManager.addAllKategoria(good);
		
		//Delete : 
		((KategoriaM)KategoriaManager).clearKategoria();
		List<Kategoria> kategoria = KategoriaManager.getAllKategorie();
		assertEquals(0, kategoria.size());
	}
	@Test
	public void test_update(){
		KategoriaManager.clearKategoria();
		Kategoria kategoria = new Kategoria(NAME_1);
		KategoriaManager.addKategoria(kategoria);	
		assertEquals(true, KategoriaManager.updateKategoria(kategoria.getNazwa(), "Kategoria"));
	}
	@Test
	public void test_film_for_kategoria(){
		FilmM fm = new FilmM();
		KategoriaManager.addKategoria(new Kategoria("Horror"));
		
		int kategoria_id = KategoriaManager.selectIdFromKategoria("Horror");
		
		fm.addFilm(new Film("Piła1", 211,100,kategoria_id));
		fm.addFilm(new Film("Piła2", 222,100,kategoria_id));
		
		List<Film> film = fm.getAllFilmForKategoria("Horror");
		assertEquals(2, film.size());
		fm.clearFilm();
	}
	
	
	
	
	
}
