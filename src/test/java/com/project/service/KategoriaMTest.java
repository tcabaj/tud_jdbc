package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.domain.Kategoria;
import com.project.service.KategoriaM;

public class KategoriaMTest {
	
	KategoriaM KategoriaManager = new KategoriaM();
	
	private final static String NAME_1 = "Komedia‎";
	private final static String NAME_2 = "Historyczny";
	
	@Before
	public void clear_database(){
		KategoriaManager.clear_kategoria();
	}
	@Test
	public void test_connection(){
		assertNotNull(((KategoriaM)KategoriaManager).getConnection());
	}
	@Test
	public void test_add_one(){
		Kategoria kategoria = new Kategoria(NAME_1);
		assertEquals(true,KategoriaManager.add_kategoria(kategoria));
		List<Kategoria> kategorie = KategoriaManager.get_all_kategorie();
		Kategoria KategoriaPom= kategorie.get(0);
		assertEquals(NAME_1, KategoriaPom.getNazwa());
	}
	@Test
	public void test_delete_one(){
		KategoriaManager.clear_kategoria();
		Kategoria kategoria = new Kategoria(NAME_2);
		KategoriaManager.add_kategoria(kategoria);
		
		assertEquals(1,((KategoriaM)KategoriaManager).delete_kategoria(kategoria));		
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
		
		KategoriaManager.clear_kategoria();
		
		KategoriaManager.add_all_kategoria(good);
		
		List<Kategoria> all = KategoriaManager.get_all_kategorie();
		assertEquals(3, all.size());
	}
	@Test
	public void test_add_bad(){
		List<Kategoria> bad = new ArrayList<Kategoria>();
		Kategoria k1 = new Kategoria("Komedia");
		Kategoria k2 = new Kategoria("Historyczny");
		Kategoria k3 = new Kategoria ("Komedia");
		bad.add(k1);
		bad.add(k2);
		bad.add(k3);
		
		KategoriaManager.clear_kategoria();
		
		KategoriaManager.add_all_kategoria(bad);
		
		List<Kategoria> all = KategoriaManager.get_all_kategorie();
		assertEquals(0, all.size());
	}
	
	
	
	
	
	
}
