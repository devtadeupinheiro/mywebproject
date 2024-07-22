<%@page import="dev.tadeupinheiro.entidades.Usuario"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<table border="1">
		<tr bgcolor="#CCCCCC">
			<th> ID </th>
			<th> Nome </th>
			<th> Login </th>
			<th> Senha </th>
		</tr>
	
<%
//Scriplet
List<Usuario> usuarioList = (List<Usuario>) request.getAttribute("lista");

for (Usuario usuario : usuarioList) {	
%>

	<tr>
		<td><%=usuario.getId()%></td>
		<td><%out.print(usuario.getNome());%></td>
		<td><%=usuario.getLogin()%></td> <!-- Sinal de "=" é um atalho para out.print, nesse caso não usar ponto e vírgula no final -->
		<td><%=usuario.getSenha()%>
	</tr>

<% } %>
		
	</table>

</body>
</html>