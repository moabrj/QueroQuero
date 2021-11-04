package com.queroquero.rest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.queroquero.rest.model.Produto;
import com.queroquero.rest.model.Venda;
import com.queroquero.rest.util.Repository;

public class VendaRepository {
	
	private Connection con;
	
	public VendaRepository() {
		
	}
	
	public boolean add(Venda venda) {
		try {
			String sql = "INSERT INTO score.venda (idVendedor) values(?)";
			PreparedStatement stmt = Repository.getInstance().getConnection().prepareStatement(sql);
			stmt.setInt(1, venda.getVendedor().getMatricula());
			stmt.execute();
			//if(ok) {
				int idVenda = this.getLastInsertion();
				System.out.println("Ultima venda inserida: "+idVenda);
				sql = "INSERT INTO score.itemsVenda (idProduto, idVenda) values(?, ?)"; 				
				List<Produto> produtos = venda.getProdutos();
				//iterate the list inserting each product in the database
				for(Produto produto : produtos) {
					stmt = Repository.getInstance().getConnection().prepareStatement(sql);
					stmt.setInt(1, produto.getId());
					stmt.setInt(2, idVenda);
					stmt.execute();
				}
			//}
			Repository.getInstance().getConnection().close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	private int getLastInsertion() {
		try {
			String sql = "select * from venda order by id desc limit 1";
			Statement stmt = Repository.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
			}
			System.out.println(id);
			return id;
		} catch(Exception e) {
			System.out.println(e);
		}
		return 0;
		
	}
	

}
