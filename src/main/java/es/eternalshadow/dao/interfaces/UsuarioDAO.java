package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Usuario;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    Usuario obtenerPorId(int id);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario obtenerPorString(String usernameOrEmail);
    boolean isExisteEmail(String email);
    void actualizar(Usuario usuario);
}