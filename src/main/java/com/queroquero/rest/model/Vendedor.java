package com.queroquero.rest.model;

public class Vendedor {
	
	private int matricula;
	private String nome;
	
	public Vendedor() {
		super();
		this.matricula = 0;
		this.nome = "";
	}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return new String(String.valueOf(matricula)+" "+nome);
		
	}

}
