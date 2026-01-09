package es.eternalshadow.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.dao.UsuarioDAOImpl;
import es.eternalshadow.dao.interfaces.UsuarioDAO;
import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.entities.Usuario;
import es.eternalshadow.enums.MenuSesiones;
import es.eternalshadow.enums.RolUsuario;
import es.eternalshadow.enums.Validacion;
import es.eternalshadow.exception.GameException;
import es.eternalshadow.exception.UsuarioException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.UsuarioService;
import es.eternalshadow.util.InputHandler;

public class UsuarioServiceImpl implements UsuarioService {
	private GameContext context;
	private UsuarioDAO usuarioDAO;
	private RolUsuario rol;
	private List<String> credenciales;
	private static final Logger log = LoggerFactory
			.getLogger(UsuarioServiceImpl.class);

	public UsuarioServiceImpl(GameContext context) {
		this.context = context;
		this.rol = RolUsuario.JUGADOR;
		this.usuarioDAO = new UsuarioDAOImpl();
	}

	public List<String> getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(List<String> credenciales) {
		this.credenciales = credenciales;
	}

	public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}

	public RolUsuario getRol() {
		return rol;
	}

	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}

	/**
	 * Panel de inicio de sesión entre otras, registrar y salir del programa
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws GameException
	 */
	public List<String> panelDeInicio()
			throws IOException, InterruptedException, GameException {
		boolean salir = false;

		String[] opciones = Arrays.stream(MenuSesiones.values())
				.map(MenuSesiones::getTexto).toArray(String[]::new);

		while (!salir) {
			context.getServices().getMenuService().pintarLogo("docs/logo.txt");
			int opcion = context.getUtil().getInputHandler().crearMenu(
					context.getReader(), opciones, "Introduce tu opción");
			MenuSesiones menu = MenuSesiones.fromCodigo(opcion);

			salir = sesionesMenu(menu);
		}
		return credenciales;
	}

	private boolean sesionesMenu(MenuSesiones menu)
			throws GameException, InterruptedException {
		if (menu == null) {
			context.getUtil().getConsolePrinter().limpiarPantalla();
			return false;
		}

		switch (menu) {
			case INICIAR_SESION -> {
				gestionarLogin();
				return true;
			}
			case REGISTRAR -> {
				gestionarRegistro();
				return true;
			}
			case SALIR -> {
				log.debug("Salida");
				return true;
			}
		}
		return false;
	}
	
	@Override
	public UsuarioDTO login(String usernameOrEmail, String password)
	        throws UsuarioException {

		Usuario usuario = usuarioDAO.obtenerPorString(usernameOrEmail);
		if (usuario == null) {
		    throw new UsuarioException("Usuario no encontrado");
		}

	    if (!usuario.isActivo()) {
	        throw new UsuarioException("Usuario desactivado");
	    }

	    if (!context.getUtil().getPasswordUtil().isPassword(password, usuario.getPassword())) {
	        throw new UsuarioException("Contraseña incorrecta");
	    }

	    return new UsuarioDTO(
	            usuario.getId(),
	            usuario.getUsername(),
	            usuario.getEmail(),
	            null,
	            usuario.getRol(),
	            usuario.getFechaAlta(),
	            usuario.isActivo()
	    );
	}
	
	@Override
	public void registrarUsuario(UsuarioDTO dto) throws UsuarioException {

	    if (!validarString(dto.getEmail(), Validacion.EMAIL)) {
	        throw new UsuarioException("Email no válido");
	    }

	    if (!validarString(dto.getUsername(), Validacion.USER)) {
	        throw new UsuarioException("Usuario no válido");
	    }

	    if (!validarString(dto.getPassword(), Validacion.PASSWORD)) {
	        throw new UsuarioException("Contraseña no válida");
	    }

	    if (usuarioDAO.isExisteEmail(dto.getEmail())) {
	        throw new UsuarioException("El email ya está registrado");
	    }

	    Usuario usuario = new Usuario();
	    usuario.setEmail(dto.getEmail());
	    usuario.setUsername(dto.getUsername());
	    usuario.setPassword(context.getUtil().getPasswordUtil().hashPassword(dto.getPassword()));
	    usuario.setRol(RolUsuario.JUGADOR);
	    usuario.setActivo(true);

	    usuarioDAO.guardar(usuario);
	}
	
	@Override
	public List<UsuarioDTO> listarUsuarios() {
	    return usuarioDAO.obtenerTodosLosUsuarios()
	        .stream()
	        .map(u -> new UsuarioDTO(
	            u.getId(),
	            u.getUsername(),
	            u.getEmail(),
	            null,
	            u.getRol(),
	            u.getFechaAlta(),
	            u.isActivo()
	        ))
	        .toList();
	}

	@Override
	public void activarUsuario(Long id) throws UsuarioException {
		Usuario usuario = (Usuario) usuarioDAO.obtenerPorId(id);
	    if (usuario == null) {
	        throw new UsuarioException("Usuario no encontrado");
	    }
	    usuario.setActivo(true);
	    usuarioDAO.actualizar(usuario);
	}

	@Override
	public void desactivarUsuario(Long id) throws UsuarioException {
		Usuario usuario = (Usuario) usuarioDAO.obtenerPorId(id);
	    if (usuario == null) {
	        throw new UsuarioException("Usuario no encontrado");
	    }
	    usuario.setActivo(false);
	    usuarioDAO.actualizar(usuario);
	}
	
	private boolean validarString(String valor, Validacion tipo) {
	    if (valor == null || valor.isBlank()) {
	        return false;
	    }
	    switch (tipo) {
	        case EMAIL -> { return valor.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$"); }
	        case USER -> { return valor.length() >= 3 && !valor.contains(" "); }
	        case PASSWORD -> { return valor.length() >= 6; }
	        default -> { return false; }
	    }
	}
	
	private void gestionarLogin() throws UsuarioException {
	    String usernameOrEmail = InputHandler.toScan(context.getReader(), "Usuario o email");
	    String password = InputHandler.toScan(context.getReader(), "Contraseña");
	    UsuarioDTO usuarioDTO = login(usernameOrEmail, password);

	    this.rol = usuarioDTO.getRol();
	    this.credenciales = List.of(usernameOrEmail);
	    log.error("Login correcto: {}", usuarioDTO.getUsername());
	}
	
	private void gestionarRegistro() throws UsuarioException {
	    String email = InputHandler.toScan(context.getReader(), "Email");
	    String username = InputHandler.toScan(context.getReader(), "Usuario");
	    String password = InputHandler.toScan(context.getReader(), "Contraseña");

	    UsuarioDTO dto = new UsuarioDTO(
	        username,
	        email,
	        password,
	        RolUsuario.JUGADOR,
	        null,
	        true
	    );
	    registrarUsuario(dto);
	    log.info("Usuario registrado correctamente");
	}
}
