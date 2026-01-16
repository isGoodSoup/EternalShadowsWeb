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
	private String password;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String username, String password, RolUsuario rol) {
		this.username = username;
		this.password = password;
		this.rol = rol;

	}

	public UsuarioDTO(int id, String username, String email, RolUsuario rol, boolean activo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.rol = rol;
		this.activo = activo;
	}

	public UsuarioDTO(String username, String password) {
		this.username = username;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return username + " (" + email + ") - " + rol + " - " + (activo ? "Activo" : "Inactivo");
	}
}