package dev.tadeupinheiro.teste;

import java.util.List;

import dev.tadeupinheiro.entidades.Usuario;
import dev.tadeupinheiro.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {
	
	public static void main (String[] args) {
		
		testeCadastrar();
		//testeAlterar();
		//testeExcluir();
		//testeBuscarTodos();
		//testeAutenticar();
		//testeBuscarId();
		
	}

	private static void testeCadastrar() {
		
		Usuario usu = new Usuario();
		usu.setNome("Tadeu123");
		usu.setLogin("tadeujr123");
		usu.setSenha("123");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrarUsuario(usu);
		
	}
	
	private static void testeAlterar() {
		
		Usuario usu = new Usuario();
		usu.setId(7);
		usu.setNome("Graziele");
		usu.setLogin("grazi");
		usu.setSenha("456");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.alterarUsuario(usu);		
		
	}
	
	private static void testeExcluir() {
			
		Usuario usu = new Usuario();
		usu.setId(11);
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.excluirUsuario(usu);		
		
	}
	
	private static void testeBuscarTodos() {
			
		UsuarioDAO usuDao = new UsuarioDAO();
		List<Usuario> usuarioList = usuDao.buscarTodosUsuario();
		
		for (Usuario e : usuarioList) {
			System.out.println(e.toString());
		}

	}
	
	public static void testeAutenticar () {
		
		var usuario = new Usuario();
		usuario.setLogin("grazi");
		usuario.setSenha("456");
		
		var usuarioDao = new UsuarioDAO();
				
		System.out.println(usuarioDao.autenticar(usuario)); //Não tá retornando referência de memória porque já fiz o toString
		
	}
	
	public static void testeBuscarId() {
		
		var usuarioDao = new UsuarioDAO();
		var usuario = usuarioDao.buscarUsuarioId(10);
		System.out.println(usuario);
		
	}

}
