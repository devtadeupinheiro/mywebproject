package dev.tadeupinheiro.controller;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class FiltroAutenticacao
 */
public class FiltroAutenticacao extends HttpFilter implements Filter {
       
    public FiltroAutenticacao() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Casting do request para acessar os métodos da classe mãe e conseguir o getSession()
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String url = httpServletRequest.getRequestURI();
		
		// Está logado?
		HttpSession sessao = httpServletRequest.getSession();

		if (sessao.getAttribute("usuLogado") != null || url.lastIndexOf("login.html") > -1 || url.lastIndexOf("autcontroller.do") > -1 ) {
			
			chain.doFilter(request, response); //Permite o fluxo da requisição

		} else {
			
			//Redireciona para o login
			((HttpServletResponse) response).sendRedirect("login.html");
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
