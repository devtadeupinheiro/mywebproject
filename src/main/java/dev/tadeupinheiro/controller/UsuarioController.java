package dev.tadeupinheiro.controller;

import jakarta.servlet.RequestDispatcher;
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
		
		//Obtendo a lista
		UsuarioDAO usuarioDao = new UsuarioDAO();
		List<Usuario> usuarioList = usuarioDao.buscarTodosUsuario();
		
		//Colocando ponteiro dentro do request
		request.setAttribute("lista", usuarioList);
		
		//Encaminhando ao JSP
		RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
		saida.forward(request, response);		
		
		/*for (Usuario usuario : usuarioList) {
			
			PrintWriter saida = response.getWriter();
			saida.println(usuario);
			
		}*/
		
		
		//MANEIRA RÚSTICA DE FAZER SERVLET COM HTML DE SAIDA
		/*
		StringBuilder htmlSaida = new StringBuilder("<html> <body> <table border='1'>");
		htmlSaida.append("<tr> <td> Id </td> <td> Nome </td> <td> Login </td> <td> Senha </td> </tr> ");
		String inicioLinhaTabela = "<tr> <td> ";
		String fimInicioDadoTabela = " </td> <td> ";
		String encerraTabela = "</td> </tr> </table> </body> </html>";
		
		for (Usuario usuario : usuarioList) {
			
			htmlSaida.append(inicioLinhaTabela);
			htmlSaida.append(usuario.getId());
			htmlSaida.append(fimInicioDadoTabela);
			htmlSaida.append(usuario.getNome());
			htmlSaida.append(fimInicioDadoTabela);
			htmlSaida.append(usuario.getLogin());
			htmlSaida.append(fimInicioDadoTabela);
			htmlSaida.append(usuario.getSenha());	
			
		}
		
		htmlSaida.append(encerraTabela);
		
		PrintWriter saida = response.getWriter();
		saida.print(htmlSaida.toString());
		*/
		
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
