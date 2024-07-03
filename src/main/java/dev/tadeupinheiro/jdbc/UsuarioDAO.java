package dev.tadeupinheiro.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.tadeupinheiro.entidades.Usuario;

public class UsuarioDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrarUsuario (Usuario usuario) {
		
		String sql = "INSERT INTO USUARIO (nome, login, senha) VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
