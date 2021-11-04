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
	//private String url = "jdbc:mysql://localhost:3306/score";
	//private String user = "moabrj";
	//private String password = "baom291091";
	//Repository repo;
	
	public VendedorRepository() {
		//repo = Repository.getInstance();
	}
	
	/**
	 * Return a simple list with all the sellers
	 * @return
	 */
	public List<Vendedor> getAll(){
		//get data in database, put in list format and return
		List<Vendedor> lista = new ArrayList<Vendedor>();
		try {
			
			con = Repository.getInstance().getConnection();
			String sql = "select matricula, nome from vendedor";
			Statement stmt = con.createStatement();
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
	
	/**
	 * Return a list in order of the bests sellers (relative to quantity of sales)
	 * @return List<Vendedor>
	 */
	public List<Vendedor> getTopQuantity(){
		//get data in database, put in list format and return
		List<Vendedor> lista = new ArrayList<Vendedor>();
		try {
			
			con = Repository.getInstance().getConnection();
			String sql = "select matricula, nome, count(idVendedor) as numeroVendas "
					+ "from venda, vendedor where vendedor.matricula = venda.idVendedor "
					+ "group by idVendedor order by numeroVendas desc";
		
			Statement stmt = con.createStatement();
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
	
	/**
	 * Return a list in order of the bests sellers (relative to quantity of sales)
	 * @return List<Vendedor>
	 */
	public List<Vendedor> getTopValue(){
		//get data in database, put in list format and return
		List<Vendedor> lista = new ArrayList<Vendedor>();
		try {
			
			con = Repository.getInstance().getConnection();
			String sql = "select matricula, nome from vendedor, (select vendaProduto.idVendedor, "
					+ "sum(preco) as totalVendido from (select idVendedor, idProduto, idVenda, "
					+ "preco from produto, itemsVenda, venda where produto.id = itemsVenda.idProduto "
					+ "and venda.id = itemsVenda.idVenda) as vendaProduto "
					+ "group by vendaProduto.idVendedor order by totalVendido desc) as temp "
					+ "where matricula = temp.idVendedor";
		
			Statement stmt = con.createStatement();
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
	
	/**
	 * Return true if it was possible to add the salesman and false otherwise 
	 * @param v
	 */
	public void add(Vendedor v) {
		try {
			String sql = "INSERT INTO score.vendedor (nome) VALUES(?)";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getNome());
			stmt.execute();
			con.close();
			if(con.isClosed()) {
				System.out.println("A conexão foi fechada");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//lista.add(v);
	}

	/**
	 * Return true if it was possible to delete the salesman and false otherwise 
	 * @param matricula
	 */
	public boolean delete(int matricula) {
		try {
			String sql = "DELETE FROM score.vendedor WHERE matricula= ?";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, matricula);
			boolean ok = stmt.execute();
			con.close();
			return ok;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Return a Salesman with name empty and matricula equal 0 if there is no one 
	 * in the database with the passed matricula
	 * @param matricula
	 * @return
	 */
	public Vendedor get(int matricula) {
		Vendedor v = new Vendedor();
		try {
			String sql = "select * from vendedor where matricula = "+String.valueOf(matricula);
			con = Repository.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				v.setMatricula(rs.getInt(1));
				v.setNome(rs.getString(2));
			}
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return v;
	}

	/**
	 * Return true if it was possible to change the salesman and false otherwise 
	 * @param v
	 * @return
	 */
	public boolean update(Vendedor v) {
		try {
			String sql = "UPDATE score.vendedor SET nome = ? WHERE matricula = ?";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getNome());
			stmt.setInt(2, v.getMatricula());
			boolean ok = stmt.execute();
			con.close();
			return ok;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
