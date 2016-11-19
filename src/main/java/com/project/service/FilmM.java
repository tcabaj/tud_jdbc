package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.domain.Film;

public class FilmM implements IFilmM {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableMedicine = "CREATE TABLE Film(id bigint GENERATED BY DEFAULT AS IDENTITY, tytul varchar(20) unique, dlugosc int, il_miejsc int, kategoria_id int, FOREIGN KEY(kategoria_id) REFERENCES Kategoria(id) ON DELETE CASCADE ON UPDATE CASCADE)";

	private PreparedStatement Sadd_one;
	private PreparedStatement Sdelete_all;
	private PreparedStatement Sdelete_one;
	private PreparedStatement Sget_all;
	private PreparedStatement Supdate;
	private PreparedStatement Supdate_kategoria;
	private PreparedStatement Sset_kategoria;
	private PreparedStatement Sget_for_kategoria;

	private Statement statement;
	
	public FilmM() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Film".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableMedicine);

			Sadd_one = connection.prepareStatement("INSERT INTO Film (tytul, dlugosc, il_miejsc, kategoria_id) VALUES (?,?,?,?)");
			Sdelete_one = connection.prepareStatement("DELETE FROM Film WHERE tytul=?");
			Sdelete_all = connection.prepareStatement("DELETE FROM Film");
			Sget_all = connection.prepareStatement("SELECT id, tytul, dlugosc, il_miejsc, kategoria_id FROM Film");
			Supdate = connection.prepareStatement("UPDATE Film SET tytul=?, dlugosc=?, il_miejsc=?, kategoria_id=? WHERE tytul=?");
			Supdate_kategoria = connection.prepareStatement("UPDATE Film SET kategoria_id=? WHERE tytul=?");
			Sset_kategoria = connection.prepareStatement("UPDATE Film SET kategoria_id=(SELECT id FROM Kategoria WHERE nazwa=?) WHERE tytul=?;");
			Sget_for_kategoria = connection.prepareStatement("SELECT id, tytul, dlugosc, il_miejsc, kategoria_id FROM Film WHERE kategoria_id = (SELECT id FROM Kategoria WHERE nazwa=?);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	@Override
	public boolean add_film(Film film) {
		int count = 0;
		try {
			Sadd_one.setString(1, film.getTytul());
			Sadd_one.setInt(2, film.getDlugosc());
			Sadd_one.setInt(3, film.getIl_miejsc());
			Sadd_one.setInt(4, film.getKat_id());
			

			count = Sadd_one.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean add_all_film(List<Film> film) {
		try {
			connection.setAutoCommit(false);
			
			for(Film filmy : film){
				Sadd_one.setString(1, filmy.getTytul());
				Sadd_one.setInt(2, filmy.getDlugosc());
				Sadd_one.setInt(3, filmy.getIl_miejsc());
				Sadd_one.setInt(4, filmy.getKat_id());
				Sadd_one.executeUpdate();
			}
			
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {

				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public List<Film> get_all_film() {
		List<Film> film = new ArrayList<Film>();

		try {
			ResultSet rs = Sget_all.executeQuery();

			while (rs.next()) {
				Film f = new Film();
				f.setId(rs.getInt("id"));
				f.setTytul(rs.getString("tytul"));
				f.setDlugosc(rs.getInt("dlugosc"));
				f.setIl_miejsc(rs.getInt("il_miejsc"));
				f.setKat_id(rs.getInt("kategoria_id"));
				film.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public boolean update_film(Film sfilm, Film nfilm) {
		int count = 0;
		try {
			//NEW
			Supdate.setString(1, nfilm.getTytul());
			Supdate.setInt(2, nfilm.getDlugosc());
			Supdate.setInt(3, nfilm.getIl_miejsc());
			Supdate.setInt(4, nfilm.getKat_id());
			//OLD
			Supdate.setString(5, sfilm.getTytul());

			count = Supdate.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1) return true;
		else return false;
	}

	@Override
	public void clear_film() {
		try {
			Sdelete_all.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean clear_film(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set_kategoria_for_film(Film film, String kategoria_nazwa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Film> get_all_film_for_kategoria(String nazwa) {
		
		List<Film> film = new ArrayList<Film>();
		try {
			Sget_for_kategoria.setString(1, nazwa);
			ResultSet rs = Sget_for_kategoria.executeQuery();
			while (rs.next()) {
				Film f = new Film();
				f.setTytul(rs.getString("tytul"));
				f.setDlugosc(rs.getInt("dlugosc"));
				f.setIl_miejsc(rs.getInt("il_miejsc"));
				f.setKat_id(rs.getInt("Kategoria_id"));
				film.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	
	
}
