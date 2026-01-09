package es.eternalshadow.dto;

import java.sql.Date;

import es.eternalshadow.enums.RolUsuario;

public class UsuarioDTO {
	private int id;
	private String username;
	private String email;
	private String password;
	private RolUsuario rol;
	private Date fechaAlta;
	private boolean activo;

	public UsuarioDTO() {}

	public UsuarioDTO(int id, String username, String email, String password, RolUsuario rol, Date fechaAlta,
			boolean activo) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}

	public UsuarioDTO(String username, String email, String password, RolUsuario rol, Date fechaAlta, boolean activo) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}

	public UsuarioDTO(String username, String email) {
		super();
		this.username = username;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RolUsuario getRol() {
		return rol;
	}

	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", rol="
				+ rol + ", fechaAlta=" + fechaAlta + ", activo=" + activo + "]";
	}
}
