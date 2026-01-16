package es.eternalshadow.service;

import es.eternalshadow.dao.UsuarioDAOImpl;
import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.entities.Usuario;
import es.eternalshadow.enums.RolUsuario;
import es.eternalshadow.exception.UsuarioException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.AuthService;
import es.eternalshadow.util.PasswordUtil;

public class AuthServiceImpl implements AuthService {
    private GameContext context;
    private UsuarioDAOImpl usuarioDAO;
    private UsuarioDTO usuarioActual;
    
    public AuthServiceImpl(GameContext context) {
        this.context = context;
        this.usuarioDAO = new UsuarioDAOImpl();
        this.usuarioActual = null;
    }
    
    @Override
    public void registrar(String username, String email, String password) throws UsuarioException {
        // Validaciones básicas
        if (username == null || username.trim().isEmpty()) {
            throw new UsuarioException("El nombre de usuario no puede estar vacío");
        }
        if (email == null || !email.contains("@")) {
            throw new UsuarioException("Email inválido");
        }
        if (password == null || password.length() < 6) {
            throw new UsuarioException("La contraseña debe tener al menos 6 caracteres");
        }
        
        // Verificar si ya existe
        if (usuarioDAO.isExisteEmail(email)) {
            throw new UsuarioException("El email ya está registrado");
        }
        
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(context.getUtil().getPasswordUtil().hashPassword(password));
        usuario.setRol(RolUsuario.JUGADOR);
        usuario.setActivo(true);
        usuario.setFechaAlta(new java.sql.Date(System.currentTimeMillis()));
        
        // Guardar
        usuarioDAO.guardar(usuario);
        System.out.println("Usuario registrado exitosamente: " + username);
    }
    
    @Override
    public UsuarioDTO login(String usernameOrEmail, String password) throws UsuarioException {
        Usuario usuario = usuarioDAO.obtenerPorString(usernameOrEmail);
        
        if (usuario == null) {
            throw new UsuarioException("Usuario no encontrado");
        }
        
        if (!usuario.isActivo()) {
            throw new UsuarioException("Usuario desactivado");
        }
        
        PasswordUtil passwordUtil = context.getUtil().getPasswordUtil();
        if (!passwordUtil.isPassword(password, usuario.getPassword())) {
            throw new UsuarioException("Contraseña incorrecta");
        }
        
        // Crear DTO
        usuarioActual = new UsuarioDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getEmail(),
            usuario.getRol(),
            usuario.isActivo()
        );
        
        System.out.println("Login exitoso: " + usuario.getUsername());
        return usuarioActual;
    }
    
    @Override
    public void logout() {
        usuarioActual = null;
        System.out.println("Sesión cerrada");
    }
    
    @Override
    public boolean isAutenticado() {
        return usuarioActual != null;
    }
    
    @Override
    public UsuarioDTO getUsuarioActual() {
        return usuarioActual;
    }
    
    @Override
    public void cambiarPassword(String passwordActual, String nuevaPassword) throws UsuarioException {
        if (!isAutenticado()) {
            throw new UsuarioException("No hay usuario autenticado");
        }
        
        Usuario usuario = usuarioDAO.obtenerPorId(usuarioActual.getId());
        PasswordUtil passwordUtil = context.getUtil().getPasswordUtil();
        
        if (!passwordUtil.isPassword(passwordActual, usuario.getPassword())) {
            throw new UsuarioException("Contraseña actual incorrecta");
        }
        
        usuario.setPassword(passwordUtil.hashPassword(nuevaPassword));
        usuarioDAO.actualizar(usuario);
        System.out.println("Contraseña cambiada exitosamente");
    }
    
    @Override
    public boolean isDisponible(String usernameOrEmail) {
        return usuarioDAO.obtenerPorString(usernameOrEmail) == null;
    }
}