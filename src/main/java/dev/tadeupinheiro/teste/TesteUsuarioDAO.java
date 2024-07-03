package dev.tadeupinheiro.teste;

import dev.tadeupinheiro.entidades.Usuario;
import dev.tadeupinheiro.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {
	
	public static void main (String[] args) {
		
		Usuario usu = new Usuario();
		usu.setNome("Tadeu");
		usu.setLogin("tadeujr");
		usu.setSenha("123");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrarUsuario(usu);
		
	}

}
