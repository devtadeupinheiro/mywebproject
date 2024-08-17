<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Início</title>
<style>
	body {
		background-color: black;
		font-family: Arial, Helvetica, sans-serif;
		color: white;
	}
</style>
</head>
<body>

	<c:import url="includes/menu.jsp"></c:import>
	
	<article>
		<h1>Sejam bem vindo ${sessionScope.usuLogado.nome}!</h1>
	</article>
	
	<img alt="logo" src="imagens/logo.png" width=300vh />
	
</body>
</html>