package es.eternalshadow.service;

import es.eternalshadow.dao.UsuarioDAOImpl;

import es.eternalshadow.dto.UsuarioDTO;


public class LoginService {

    private UsuarioDTO usuarioDTO;

    public LoginService(UsuarioDTO usuarioDTO) {
        if (!validarNombreUsuario(usuarioDTO.getUsername())) {
            throw new IllegalArgumentException("Nombre de usuario inválido: debe tener entre 3 y 15 caracteres");
        }

        if (!validarContrasena(usuarioDTO.getPassword())) {
            throw new IllegalArgumentException("Contraseña inválida: debe tener al menos 8 caracteres");
        }

        this.usuarioDTO = usuarioDTO; // Guardamos el usuario solo si es válido
    }

    public static boolean validarNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null) return false;
        nombreUsuario = nombreUsuario.trim();
        return !nombreUsuario.isEmpty() && nombreUsuario.length() >= 3 && nombreUsuario.length() <= 15;
    }

    public static boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.trim().length() >= 8;
    }

    public boolean isAdmin(UsuarioDTO usuarioDTO) {
    	UsuarioDAOImpl usuarioDAOImpl=new UsuarioDAOImpl();
    
 
    	return usuarioDAOImpl.esAdmin(usuarioDTO.getUsername(), usuarioDTO.getPassword());
   
    	
    }
}

