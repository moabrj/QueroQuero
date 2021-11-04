package com.queroquero.rest.util;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Repository {
	
	private static Repository instance;
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/score";
	private String user = "moabrj";
	private String password = "baom291091";
	

	public static Repository getInstance() {
		if(instance == null)
			instance = new Repository();
		return instance;
	}
	
	public Connection getConnection() {
		try {
			if(con==null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
			} else if (con.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}

}
