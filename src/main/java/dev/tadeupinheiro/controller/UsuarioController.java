package dev.tadeupinheiro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public UsuarioController() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método GET");
		
		String nome = request.getParameter("nome");
		System.out.println("Nome:" + nome);
		
		String empresa = request.getParameter("empresa");
		System.out.println("Empresa:" + empresa);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método POST");
		
	}
	

}
