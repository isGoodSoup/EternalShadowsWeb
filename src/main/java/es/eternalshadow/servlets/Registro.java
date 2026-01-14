package es.eternalshadow.servlets;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.exception.UsuarioException;
import es.eternalshadow.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ServiceFactory service;
	private static final Logger log = LoggerFactory.getLogger(Registro.class);

	public Registro() {
		super();
		this.service = new ServiceFactory();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<h1>Registro con exito</h1>");
		log.info("Procesando registro...");
		
		UsuarioDTO dto = new UsuarioDTO(
	            request.getParameter("username"),
	            request.getParameter("email"),
	            request.getParameter("password"),
	            null,
	            null,
	            true
	        );

	        try {
	            service.getUserService().registrarUsuario(dto);
	            response.getWriter().append("<h1>Registro con éxito</h1>");
	            log.info("Usuario registrado: {}", dto.getUsername());
	        } catch (UsuarioException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	        }
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
