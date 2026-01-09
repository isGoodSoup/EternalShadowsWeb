package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Usuario;

public interface UsuarioDAO extends DAO {
	public List<Usuario> obtenerTodosLosUsuarios();
	Usuario obtenerPorString(String valor);
    boolean isExisteEmail(String email);
}
