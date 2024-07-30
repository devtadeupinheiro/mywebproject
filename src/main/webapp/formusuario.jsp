<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulário Usuário</title>

</head>
<body>


	<form action="usucontroller.do" method="post">
	
		<label>ID: </label>
		<input type="text" name="txtid" value="${usuario.id}" size="10">
	
		<label>Nome: </label>
		<input type="text" name="txtnome" value="${usuario.nome}" size="20"/>
		
		<label>Login: </label>
		<input type="text" name="txtlogin" value="${usuario.login}" size="20"/>
		
		<label>Senha: </label>
		<input type="password" name="txtsenha" value="${usuario.senha}" maxlength="6"/>
		
		<input type="submit" value="Salvar">
	</form>
	
	
</body>
</html>