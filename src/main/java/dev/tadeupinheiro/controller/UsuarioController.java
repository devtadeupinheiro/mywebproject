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
    
    protected void listarUsuarios (UsuarioDAO usuDao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtendo a lista
		List<Usuario> usuarioList = usuDao.buscarTodosUsuario();
		
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
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chamando Método GET");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		
		String acao = request.getParameter("acao"); //Pega o parametro "acao" que foi passado para o metodo
		boolean teste = acao != null;
		
		
		
		if (teste && acao.equals("exc")) {
			
			String id = request.getParameter("id");
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			usuDao.excluirUsuario(usuario);
			
			//listarUsuarios(usuDao, request, response);
			response.sendRedirect("usucontroller.do");
			//Ao invés de criar outro método, poderia simplesmente deixa o código no ELSE e redirecionar o servidor para
			//uma nova requisição que atenda ao teste lógico do else e vá direto pra ele.
					
			//OUTRA MANEIRA DE CHAMAR A LISTA DEPOIS DE EXECUTADA A AÇÃO
			//response.sendRedirect("usucontroller.do?=acao=lis")
			/*Desta maneira precisa estar todos as acções em IF e não else if, pois assim executaria o código seguinte
			 * mesmo que entrasse no if anterior. Com else if, ele excuta e depois encerra. Escolhi desta maneira pois evita
			 * de o servlet ter que continuar fazendo os teste lógicos a cada if mesmo atendendo ao teste escolhido.
			 * */
			
		} else if (teste && acao.equals("alt")) {
			
			String id = request.getParameter("id");
			Usuario usuario = usuDao.buscarUsuarioId(Integer.parseInt(id));
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("formusuario.jsp");
			saida.forward(request, response);
			
			//listarUsuarios(usuDao, request, response); Não funcionou
			//response.sendRedirect("usucontroller.do"); Não funcionou
			
		} else if (teste && acao.equals("cad")) {
			
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("formusuario.jsp");
			saida.forward(request, response);
			
			//listarUsuarios(usuDao, request, response); Não funcionou
			//response.sendRedirect("usucontroller.do"); Não funcionou
			
		} else {
			
			listarUsuarios(usuDao, request, response);
			
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
