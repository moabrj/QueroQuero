package com.queroquero.rest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.queroquero.rest.model.Produto;
import com.queroquero.rest.util.Repository;

public class ProdutoRepository {
	
	private Connection con;
	
	public ProdutoRepository() {
		
	}
	
	/**
	 * Return a list with all products in the database
	 */
	public List<Produto> get(){
		List<Produto> produtos = new ArrayList<>();
		try {
			String sql = "select * from produto";
			Statement stmt = Repository.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				produtos.add(new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
			Repository.getInstance().getConnection().close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return produtos;
	}
		
	/**
	 * Return true if it was possible to add the product and false otherwise 
	 * @param produto
	 */
	public boolean add(Produto produto) {
		try {
			String sql = "INSERT INTO score.produto (nome, preco) VALUES(?, ?)";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.execute();
			con.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * Return true if it was possible to delete the product and false otherwise 
	 * @param id
	 */
	public boolean delete(int id) {
		try {
			String sql = "DELETE FROM score.produto WHERE id= ?";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			con.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * Return true if it was possible to change the product and false otherwise 
	 * @param produto
	 */
	public boolean update(Produto produto) {
		try {
			String sql = "UPDATE score.produto SET nome = ?, preco = ? WHERE id = ?";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getId());
			boolean ok = stmt.execute();
			con.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
