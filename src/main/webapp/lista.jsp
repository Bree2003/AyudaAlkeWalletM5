<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<thead>
<tr>
<c:out value = "${'Esto es un c:out en el index'}" />
<th>ID</th>
<th>Nombre</th>
<th>Apellido</th>
<th>Correo</th>
<th>Contraseña</th>
<th>Saldo</th>
</tr>
</thead>
<tbody>
<c:forEach var="usuario" items="${lista}">
<tr>
<td><c:out value="${usuario.id}"></c:out></td>
<td><c:out value="${usuario.nombre}"></c:out></td>
<td><c:out value="${usuario.apellido}"></c:out></td>
<td><c:out value="${usuario.correo}"></c:out></td>
<td><c:out value="${usuario.contrasena}"></c:out></td>
<td><c:out value="${usuario.saldo}"></c:out></td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>