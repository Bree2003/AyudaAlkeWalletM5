<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<h1>Registrar</h1>
<form action="registrarse" method="post">
<label for="nombre">Nombre:</label>
<input type="text" id="nombre" name="nombre" placeholder="tu nombre" required>
<label for="apellido">Apellido:</label>
<input type="text" id="apellido" name="apellido" placeholder="tu apellido" required> <br>
<label for="correo">Correo:</label>
<input type="email" id="correo" name="correo" placeholder="ejemplo@gmail.com" required>
<label for="contrasena">Contraseña:</label>
<input type="password" id="contrasena" name="contrasena" placeholder="*****" minlength="4" required>
<input type="submit" value="Enviar">
</form>
<a href="login">Iniciar sesión</a>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
var status = document.getElementById("status").value;
if(status == "success") {
	swal.fire("felicitaciones","Cuenta creada", "success");
}else if(status == "failed"){
	swal.fire("Error","no se pudo crear la cuenta", "error");
}
</script>
</body>
</html>