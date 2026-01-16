package es.eternalshadow.dto;

import es.eternalshadow.enums.RolUsuario;

/**
 * DTO para transferencia de datos de usuarios.
 */
public class UsuarioDTO {
    private int id;
    private String username;
    private String email;
    private RolUsuario rol;
    private boolean activo;
    
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(String username, String email, RolUsuario rol) {
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.activo = true;
    }
    
    public UsuarioDTO(int id, String username, String email, RolUsuario rol, boolean activo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return username + " (" + email + ") - " + rol + " - " + (activo ? "Activo" : "Inactivo");
    }
}