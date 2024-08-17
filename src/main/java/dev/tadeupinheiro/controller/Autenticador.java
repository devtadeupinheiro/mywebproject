package dev.tadeupinheiro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dev.tadeupinheiro.entidades.Usuario;
import dev.tadeupinheiro.jdbc.UsuarioDAO;

/**
 * Servlet implementation class Autenticador
 */

//autcontroller.do é o caminho, coloquei ao configurar a classe mas não acho onde alterar. Era pra
//ter uma anotação, mas não tem.
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Autenticador() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(false);
		
		if (sessao != null) {
			
			sessao.invalidate();
			
		}
		response.sendRedirect("login.html"); //Redireciona para o login
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Captura dados da tela
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuDao = new UsuarioDAO();
		
		Usuario usuRetorno = usuDao.autenticar(usuario);
		if (usuRetorno != null) {
			
			//Criando sessao
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000); //Define o tempo de inatividade permitido pela sessão, se ultrapassar, desloga a sessão
			sessao.setAttribute("usuLogado", usuRetorno);
			
			//Encaminhando ao Index
			request.getRequestDispatcher("index.jsp").forward(request, response);
						
		} else {
			
			response.sendRedirect("login.html"); //Redireciona para a página de login
			
		}
		
	}

}
