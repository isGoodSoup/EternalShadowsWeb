package es.eternalshadow.servlets;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(Login.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsuarioDTO usuario = new UsuarioDTO(username, password);

		try {
			LoginService loginService = new LoginService(usuario);

			if(loginService.isAdmin(usuario)) {
				log.info("Administrador {} ha iniciado sesión correctamente.", username);
			} else {
				log.info("No puede iniciar sesión el usuario {}: no es administrador.", username);
			}

		} catch (IllegalArgumentException e) {

			response.setContentType("text/html");
			response.getWriter().append("<h1>Error de login: ").append(e.getMessage()).append("</h1>");
			log.warn("Intento de login fallido para usuario {}: {}", username, e.getMessage());
		}
	}

}
