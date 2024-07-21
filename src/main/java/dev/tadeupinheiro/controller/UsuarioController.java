package dev.tadeupinheiro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dev.tadeupinheiro.entidades.Usuario;
import dev.tadeupinheiro.jdbc.UsuarioDAO;

public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public UsuarioController() {}  
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método GET");
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		List<Usuario> usuarioList = usuarioDao.buscarTodosUsuario();
		
		for (Usuario usuario : usuarioList) {
			
			PrintWriter saida = response.getWriter();
			saida.println(usuario);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método POST");
		String mensagemFinal = "Salvo com sucesso!"; //Adicionei sem estar no curso por causa do try/catch do parse int
		
		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		
	
		Usuario usuario = new Usuario();
		
		if (id != null && id != "" && id != "0") {
			
			try {
				
				usuario.setId(Integer.parseInt(id)); //Adicionei esse try/catch por conta própria para caso o usuário digite um texto ao invés de números
				
			} catch (NumberFormatException e) {
				
				System.out.println("O ID precisa ser um número!" + e.getMessage());
				mensagemFinal = "O ID precisa ser um número!";
				
			}
		}
		
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.salvar(usuario); //Método dentro do DAO decide se vai salvar um novo ou alterar
		
		PrintWriter saida = response.getWriter();
		saida.print(mensagemFinal);
		
	}
	

}
