package dev.tadeupinheiro.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
			
			Class.forName("org.postgresql.Driver"); //Na aula não tinha esse driver declarado, por conta do erro no uso do método post eu encontrei essa maneira de resolver
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancoteste", "postgres", "Eusei2202@");
			System.out.println("Conectado com sucesso!");
			
		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("Não pode conectar: " + e.getMessage());
			
		}
		
		return con;
	}

}
