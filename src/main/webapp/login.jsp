<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Iniciar sesión</h1>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<form action="login" method="post">
<label for="correo">Correo:</label>
<input type="email" id="correo" name="correo" placeholder="tu correo" required>
<label for="contrasena">Contraseña:</label>
<input type="password" id="contrasena" name="contrasena" placeholder="*****" required>
<input type="submit" value="Enviar">
</form>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
var status = document.getElementById("status").value;
if(status == "failed") {
	swal.fire("Error","Correo o contraseña equivocadas", "error");
}
</script>
</body>
</html>