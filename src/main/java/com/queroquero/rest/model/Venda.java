package com.queroquero.rest.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Venda {
	
	private int id;
	private Vendedor vendedor;
	private List<Produto> produtos;
	
	public Venda() {
		super();
		this.vendedor = new Vendedor();
		this.produtos = new ArrayList<Produto>();
	}

	public Venda(Vendedor vendedor, List<Produto> produtos) {
		this.vendedor = vendedor;
		this.produtos = produtos;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
