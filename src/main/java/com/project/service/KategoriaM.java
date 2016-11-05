package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.domain.Kategoria;
import com.project.service.IKategoriaM;

public class KategoriaM implements IKategoriaM {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	private String createTableKategoria = "CREATE TABLE Kategoria(id bigint GENERATED BY DEFAULT AS IDENTITY, nazwa varchar(20), PRIMARY KEY(id))";
	
	private PreparedStatement Sadd_kategoria;
	private PreparedStatement Sdelete_all;
	private PreparedStatement Sdelete_one;
	private PreparedStatement Sget_all;
	private PreparedStatement Supdate;
	private PreparedStatement Sselect_id;
	private Statement statement;
	
	public KategoriaM() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Kategoria".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableKategoria);

			Sadd_kategoria = connection.prepareStatement("INSERT INTO Kategoria (nazwa) VALUES (?)");
			Sdelete_one = connection.prepareStatement("DELETE FROM Kategoria WHERE nazwa=?");
			Sdelete_all = connection.prepareStatement("DELETE FROM Kategoria");
			Sget_all = connection.prepareStatement("SELECT id, nazwa FROM Kategoria");
			Supdate = connection.prepareStatement("UPDATE Kategoria SET nazwa=? WHERE nazwa=?");
			Sselect_id = connection.prepareStatement("SELECT id FROM Kategoria WHERE nazwa=?;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}
	public int delete_kategoria(Kategoria kategoria){
		int count = 0;
		try{
			Sdelete_one.setString(1, kategoria.getNazwa());
			count = Sdelete_one.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public void clear_kategoria() {
		try {
			
			Sdelete_all.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean add_kategoria(Kategoria kategoria) {
		int count = 0;
		try {
			Sadd_kategoria.setString(1, kategoria.getNazwa());

			count = Sadd_kategoria.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1) return true;
		else return false;
	}
	@Override
	public List<Kategoria> get_all_kategorie() {
		List<Kategoria> kategoria = new ArrayList<Kategoria>();

		try {
			ResultSet rs = Sget_all.executeQuery();

			while (rs.next()) {
				Kategoria k = new Kategoria();
				k.setId(rs.getInt("id"));
				k.setNazwa(rs.getString("nazwa"));
				kategoria.add(k);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kategoria;
	}
	@Override
	public boolean update_kategoria(String nazwa, String nnazwa) {
		int count = 0;
		try {
			Supdate.setString(2, nazwa);
			Supdate.setString(1, nnazwa);

			count = Supdate.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1) return true;
		else return false;
	}
	@Override
	public boolean add_all_kategoria(List<Kategoria> kategoria) {
		try {
			connection.setAutoCommit(false);
			
			for(Kategoria kategoria1 : kategoria){
				Sadd_kategoria.setString(1, kategoria1.getNazwa());
				Sadd_kategoria.executeUpdate();
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
	public int select_id_from_kategoria(String nazwa) {
		int output = -1;
		try {
			Sselect_id.setString(1, nazwa);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		try {
			ResultSet rs = Sselect_id.executeQuery();
			while (rs.next()) {
				output = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return output;
	}
}
