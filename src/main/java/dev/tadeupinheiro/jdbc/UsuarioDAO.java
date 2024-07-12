package dev.tadeupinheiro.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void alterarUsuario (Usuario usuario) {
			
		String sql = "UPDATE USUARIO SET nome=?, login=?, senha=? WHERE id=?";
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void excluirUsuario (Usuario usuario) {
		
		String sql = "DELETE FROM USUARIO WHERE id=?";
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, usuario.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Excluído com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public List<Usuario> buscarTodosUsuario () {
		
		String sql = "SELECT * FROM USUARIO ORDER BY id";
		
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);			
			
			ResultSet rs = preparador.executeQuery();
			
			while (rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				usuarioList.add(usuario);
				
			}
						
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return usuarioList;
		
	}
	
	public Usuario buscarUsuarioId (Integer id) {
		
		String sql = "SELECT * FROM USUARIO WHERE id = ?";
		
		Usuario usuario = null;
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet rs = preparador.executeQuery();
			
			if (rs.next()) { //necessário para ir para o primeiro/próximo registro
				
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
						
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return usuario;
		
	}
	
	public List<Usuario> buscarUsuarioNome (String nome) {
		
		String sql = "SELECT * FROM USUARIO WHERE nome like ?";
		
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");
			
			ResultSet rs = preparador.executeQuery();
			
			while (rs.next()) { //necessário para ir para o primeiro/próximo registro
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(nome);
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				usuarioList.add(usuario);
			}
						
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return usuarioList;
		
	}
	
	public Usuario autenticar (Usuario usuario) {
		
		String sql = "SELECT * FROM USUARIO WHERE login = ? AND senha = ?";
		
		Usuario usuarioRetorno = null;
		
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet rs = preparador.executeQuery();
			
			if (rs.next()) { //necessário para ir para o primeiro/próximo registro
				
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rs.getInt("id"));
				usuarioRetorno.setNome(rs.getString("nome"));
				usuarioRetorno.setLogin(rs.getString("login"));
				usuarioRetorno.setSenha(rs.getString("senha"));
			}
						
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return usuarioRetorno;
		
	}

}
