<%@page import="modelo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");%>
<h1>Hola, <c:out value="${usuario.getNombre()}"></c:out></h1>
<h3>Tienes un saldo de <c:out value="${usuario.getSaldo()}"></c:out></h3>
<%-- el action es la ruta a la que va a redirigir al momento de hacer submit --%>
<form action="operacion" method="post">
<h4>Escoge la operación a hacer:</h4>
<label for="operacion">Operación:</label>
<label for="monto">Ingrese monto:</label>
<input type="number" id="monto" name="monto">
<select id="operacion" name="operacion">
<option value="depositar">Depositar</option>
<option value="retirar">Retirar</option>
</select>
<input type="submit" value="enviar">
</form>
<button id="logout"><a href="logout">Cerrar Sesión</a></button>
</body>
</html>