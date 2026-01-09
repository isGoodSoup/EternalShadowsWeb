package es.eternalshadow.servlets;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.util.UtilHub;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UtilHub util;
	private static final Logger log = LoggerFactory.getLogger(Login.class);

	public Login() {
		super();
		this.util = new UtilHub();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<h1>Loggeado con exito</h1>");
		log.debug("Usuario: {}", request.getParameter("username"));
		log.debug("Contraseña: {}", util.getPasswordUtil()
				.hashPassword(request.getParameter("password")));
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
