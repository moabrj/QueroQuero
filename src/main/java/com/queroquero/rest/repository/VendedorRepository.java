package com.queroquero.rest.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.queroquero.rest.model.Vendedor;
import com.queroquero.rest.util.Repository;

public class VendedorRepository {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/score";
	private String user = "moabrj";
	private String password = "baom291091";
	//Repository repo;
	
	public VendedorRepository() {
		//repo = Repository.getInstance();
	}
	
	
	public List<Vendedor> getAll(){
		//get data in database, put in list format and return
		List<Vendedor> lista = new ArrayList<Vendedor>();
		try {
			
			//con = DriverManager.getConnection(url, user, password);
			con = Repository.getInstance().getConnection();
			String sql = "select matricula, nome, count(id) "
					+ "from venda, vendedor where matricula = idVendedor "
					+ "group by(idVendedor) order by idVendedor desc";
		
			//Statement stmt = repo.getConnection().createStatement();
			Statement stmt = con.createStatement();
			//PreparedStatement stmt = repo.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Vendedor v = new Vendedor();
				v.setMatricula(rs.getInt(1));
				v.setNome(rs.getString(2));
				lista.add(v);
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
	}
	
	public void addVendedor(Vendedor v) {
		//lista.add(v);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public Vendedor get(int matricula) {
		/*
		for(Vendedor i : lista) {
			if(i.getMatricula() == matricula)
				return i;
		}
		*/
		return null;
	}

	public boolean update(Vendedor v) {
		/*
		for(Vendedor i : lista) {
			if(i.getMatricula() == v.getMatricula()) {
				i.setNome(v.getNome());
				return true;
			}
		}
		*/
		return false;
	}

}
