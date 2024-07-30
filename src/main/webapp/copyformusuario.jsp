<%@page import="dev.tadeupinheiro.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulário Usuário</title>

</head>
<body>

<%

Usuario usuario = (Usuario) request.getAttribute("usuario");

%>

	<form action="usucontroller.do" method="post">
	
		<label>ID: </label>
		<input type="text" name="txtid" value="<%=usuario.getId()%>" size="10">
	
		<label>Nome: </label>
		<input type="text" name="txtnome" value="<%=usuario.getNome()%>" size="20"/>
		
		<label>Login: </label>
		<input type="text" name="txtlogin" value="<%=usuario.getLogin()%>" size="20"/>
		
		<label>Senha: </label>
		<input type="password" name="txtsenha" value="<%=usuario.getSenha()%>" maxlength="6"/>
		
		<input type="submit" value="Salvar">
	</form>
	
	
</body>
</html>