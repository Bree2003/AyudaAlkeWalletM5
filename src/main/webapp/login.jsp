<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Iniciar sesi�n</h1>
<form action="login" method="post">
<label for="correo">Correo:</label>
<input type="email" id="correo" name="correo" placeholder="tu correo" required>
<label for="contrasena">Contrase�a:</label>
<input type="password" id="contrasena" name="contrasena" placeholder="*****" required>
<input type="submit" value="Enviar">
</form>
</body>
</html>