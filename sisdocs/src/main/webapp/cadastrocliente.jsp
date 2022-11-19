<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/logincadastro.css">
<title>sisdocs | Criar Conta</title>
</head>
<body>
	<form action="ServletClienteAdicionar" method="POST">
		<label for="nome">Nome</label>
		<input id="nome" type="text" name="nome" required>
		
		<label for="nome">Login</label>
		<input id="login" type="text" name="login" required>
		
		<label for="pswd">Senha</label>
		<input id="pswd" type="password" name="pswd" required>
			
		<input type="submit" value="Criar conta">
		<a href="index.jsp">Já tenho uma conta</a>
	</form>
</body>
</html>