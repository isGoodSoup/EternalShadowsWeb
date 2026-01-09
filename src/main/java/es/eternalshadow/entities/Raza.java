package es.eternalshadow.entities;

import es.eternalshadow.interfaces.Accionable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_RAZA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Raza implements Accionable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "TIPO", nullable = false, length = 50)
	private String tipo;
	@Column(name = "FUERZA", nullable = false)
	private int fuerza;
	@Column(name = "RESISTENCIA", nullable = false)
	private int resistencia;
	@Column(name = "VELOCIDAD", nullable = false)
	private int velocidad;
	@Column(name = "MAGIA", nullable = false)
	private int magia;

	public Raza() {
	}

	public Raza(int id, String tipo, int fuerza, int resistencia, int velocidad,
			int magia) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		this.velocidad = velocidad;
		this.magia = magia;
	}
	
	public Raza(String tipo, int fuerza, int resistencia, int velocidad,
			int magia) {
		super();
		this.tipo = tipo;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
		this.velocidad = velocidad;
		this.magia = magia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public String toString() {
		return "Raza [id=" + id + ", tipo=" + tipo + ", fuerza=" + fuerza
				+ ", resistencia=" + resistencia + ", velocidad=" + velocidad
				+ ", magia=" + magia + "]";
	}
}
