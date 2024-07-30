<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listando com JSTL</title>
</head>
<body>

	<table border="1">
		<tr bgcolor="#CCCCCC">
			<th> ID </th>
			<th> Nome </th>
			<th> Login </th>
			<th> Senha </th>
			<th> Ação </th>
		</tr>

<!-- Se chama EL, Expression Language, Linguagem de expressão -->
<c:forEach items="${requestScope.lista}" var="usu">

	<tr>
		<td>${usu.id}</td>
		<td>${usu.nome}</td>
		<td>${usu.login}</td> <!-- Sinal de "=" é um atalho para out.print, nesse caso não usar ponto e vírgula no final -->
		<td>${usu.senha}</td>
		<td>
			<a href="usucontroller.do?acao=exc&id=${usu.id}">Excluir</a> <!-- Passa para o método get o parâmetro ação e o parâmetro ID -->
			<a href="usucontroller.do?acao=alt&id=${usu.id}">Alterar</a>
		</td>
	</tr>

</c:forEach>
		
	</table>

</body>
</html>