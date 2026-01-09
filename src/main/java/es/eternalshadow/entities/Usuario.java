package es.eternalshadow.entities;

import java.sql.Date;

import es.eternalshadow.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "USERNAME", nullable = false, unique = true, length = 50)
	private String username;
	@Column(name = "EMAIL", nullable = false, unique = true, length = 100)
	private String email;
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;
	@Column(name = "ROL", nullable = false, length = 20)
	private RolUsuario rol;
	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;
	@Column(name = "ACTIVO", nullable = false)
	private boolean activo;

	public Usuario() {
	}
	
	
	// Constructor con id 
	public Usuario(int id, String username, String email, String password,
			RolUsuario rol, Date fechaAlta, boolean activo) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}
	
	// Constructor sin id
	public Usuario(String username, String email, String password,
			RolUsuario rol, Date fechaAlta, boolean activo) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
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
		return "Usuario [id=" + id + ", username=" + username + ", email="
				+ email + ", password=" + password + ", rol=" + rol
				+ ", fechaAlta=" + fechaAlta + ", activo=" + activo + "]";
	}
}
