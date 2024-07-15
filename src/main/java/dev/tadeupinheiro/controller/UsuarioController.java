package dev.tadeupinheiro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import dev.tadeupinheiro.entidades.Usuario;
import dev.tadeupinheiro.jdbc.UsuarioDAO;

public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public UsuarioController() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("Chamando Método GET");
		
		String nome = request.getParameter("nome");
		//System.out.println("Nome:" + nome);
		
		String empresa = request.getParameter("empresa");
		//System.out.println("Empresa:" + empresa);
		
		PrintWriter saida = response.getWriter();
		saida.println("Nome:" + nome + "\n" + "Empresa:" + empresa);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método POST");
		
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		
	
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrarUsuario(usuario);
		
		PrintWriter saida = response.getWriter();
		saida.print("Cadastrado");
		
	}
	

}
