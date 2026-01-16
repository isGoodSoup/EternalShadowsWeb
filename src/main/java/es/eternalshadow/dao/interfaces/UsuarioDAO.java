package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Usuario;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    Usuario obtenerPorId(int id);
    Usuario obtenerPorString(String usernameOrEmail);
    boolean isExisteEmail(String email);
    List<Usuario> obtenerTodosLosUsuarios();
}