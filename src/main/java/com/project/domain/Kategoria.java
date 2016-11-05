package com.project.domain;

public class Kategoria {

	private int id;
	private String nazwa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public Kategoria(int id, String nazwa) {
		this.id = id;
		this.nazwa = nazwa;
	}
	public Kategoria(String nazwa) {
		super();
		this.nazwa = nazwa;
	}
	public Kategoria() {
		
	}
}
