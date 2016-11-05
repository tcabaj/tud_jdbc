package com.project.domain;

public class Film {

	private int id;
	
	private String tytul;
	private int dlugosc;
	private int il_miejsc;
	private int kat_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public int getDlugosc() {
		return dlugosc;
	}
	public void setDlugosc(int dlugosc) {
		this.dlugosc = dlugosc;
	}
	public int getIl_miejsc() {
		return il_miejsc;
	}
	public void setIl_miejsc(int il_miejsc) {
		this.il_miejsc = il_miejsc;
	}
	public int getKat_id() {
		return kat_id;
	}
	public void setKat_id(int kat_id) {
		this.kat_id = kat_id;
	}
	public Film(int id, String tytul, int dlugosc, int il_miejsc, int kat_id) {
		super();
		this.id = id;
		this.tytul = tytul;
		this.dlugosc = dlugosc;
		this.il_miejsc = il_miejsc;
		this.kat_id = kat_id;
	}
	public Film(String tytul, int dlugosc, int il_miejsc, int kat_id) {
		super();
		this.tytul = tytul;
		this.dlugosc = dlugosc;
		this.il_miejsc = il_miejsc;
		this.kat_id = kat_id;
	}
	public Film(String tytul, int dlugosc, int il_miejsc) {
		super();
		this.tytul = tytul;
		this.dlugosc = dlugosc;
		this.il_miejsc = il_miejsc;
	}
	public Film() {
	}
	
}
