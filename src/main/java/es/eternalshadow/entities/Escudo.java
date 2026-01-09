package es.eternalshadow.entities;

import es.eternalshadow.enums.Escuderia;
import es.eternalshadow.pojos.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ESCUDO")

public class Escudo extends Item {
	@Enumerated(EnumType.STRING)
	@Column(name = "ESCUDERIA", nullable = false, length = 50)
	private Escuderia escudo;

	public Escudo(String nombre, int cantidad, Escuderia escudo) {
		super(nombre, cantidad);
		this.escudo = escudo;
	}
	public Escudo() {

	}

	public Escuderia getEscudo() {
		return escudo;
	}

	public void setEscudo(Escuderia escudo) {
		this.escudo = escudo;
	}
}
