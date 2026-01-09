<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EternalShadows - Registro</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<form action="http://localhost:8084/EternalShadowsWeb/registro" method="post">
			<input type="text" id="email" name="email" placeholder="Email" required>
			<br>
            <input type="text" id="username" name="username" placeholder="Usuario" required>
			<br>
			<input type="password" id="password" name="password" placeholder="Contraseña" required>
			<br>
			<button type="submit"> Registrar </button>
		</form>
		<footer>Ya tienes cuenta? <a href="login.jsp">Iniciar sesión</a></footer>
	</body>
</html>