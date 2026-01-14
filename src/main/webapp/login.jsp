<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>EternalShadows - Login</title>
	    <link rel="stylesheet" href="style.css">
	    <link rel="icon" type="image/png" href="brand/icon.png">
	</head>
	<body>
		<img src="brand/logo_v3.png" alt="EternalShadows" class="logo">
		<br>
	    <form action="http://localhost:8084/EternalShadowsWeb/login" method="post">
	        <input type="text" id="username" name="username" placeholder="Usuario" required>
	        <br>
	        <input type="password" id="password" name="password" placeholder="Contraseña" required>
	        <br>
	        <button type="submit">Login</button>
	    </form>
	    <footer>No tienes cuenta? <a href="registro.jsp">Únete</a></footer>
	</body>
</html>
