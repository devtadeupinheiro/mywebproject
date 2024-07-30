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
			<th> A��o </th>
		</tr>

<!-- Se chama EL, Expression Language, Linguagem de express�o -->
<c:forEach items="${requestScope.lista}" var="usu">

	<tr>
		<td>${usu.id}</td>
		<td>${usu.nome}</td>
		<td>${usu.login}</td> <!-- Sinal de "=" � um atalho para out.print, nesse caso n�o usar ponto e v�rgula no final -->
		<td>${usu.senha}</td>
		<td>
			<a href="usucontroller.do?acao=exc&id=${usu.id}">Excluir</a> <!-- Passa para o m�todo get o par�metro a��o e o par�metro ID -->
			<a href="usucontroller.do?acao=alt&id=${usu.id}">Alterar</a>
		</td>
	</tr>

</c:forEach>
		
	</table>

</body>
</html>