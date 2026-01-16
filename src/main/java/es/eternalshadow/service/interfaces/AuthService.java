package es.eternalshadow.service.interfaces;

import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.exception.UsuarioException;

/**
 * Servicio para autenticación y gestión básica de usuarios.
 */
public interface AuthService {
    
    /**
     * Registra un nuevo usuario.
     */
    void registrar(String username, String email, String password) throws UsuarioException;
    
    /**
     * Inicia sesión con usuario/email y contraseña.
     * @return UsuarioDTO si el login es exitoso.
     */
    UsuarioDTO login(String usernameOrEmail, String password) throws UsuarioException;
    
    /**
     * Cierra la sesión actual.
     */
    void logout();
    
    /**
     * Verifica si hay un usuario autenticado.
     */
    boolean isAutenticado();
    
    /**
     * Obtiene el usuario actualmente autenticado.
     */
    UsuarioDTO getUsuarioActual();
    
    /**
     * Cambia la contraseña del usuario actual.
     */
    void cambiarPassword(String passwordActual, String nuevaPassword) throws UsuarioException;
    
    /**
     * Verifica si un username o email está disponible.
     */
    boolean isDisponible(String usernameOrEmail);
}