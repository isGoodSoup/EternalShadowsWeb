package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.dto.UsuarioDTO;
import es.eternalshadow.exception.UsuarioException;

public interface UsuarioService {
	void registrarUsuario(UsuarioDTO dto) throws UsuarioException;
    UsuarioDTO login(String usernameOrEmail, String password) throws UsuarioException;
    List<UsuarioDTO> listarUsuarios();
    void activarUsuario(Long id) throws UsuarioException;
    void desactivarUsuario(Long id) throws UsuarioException;
}
