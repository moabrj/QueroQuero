package com.queroquero.rest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.queroquero.rest.model.Produto;
import com.queroquero.rest.util.Repository;

public class ProdutoRepository {
	
	private Connection con;
	
	public ProdutoRepository() {
		
	}
		
	/**
	 * Return true if it was possible to add the product and false otherwise 
	 * @param p
	 */
	public boolean add(Produto p) {
		try {
			String sql = "INSERT INTO score.produto (nome, preco) VALUES(?, ?)";
			con = Repository.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getNome());
			stmt.setDouble(2, p.getPreco());
			boolean ok = stmt.execute();
			con.close();
			return ok;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
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
			boolean ok = stmt.execute();
			con.close();
			return ok;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Return true if it was possible to change the product and false otherwise 
	 * @param produto
	 * @return boolean
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
			return ok;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
