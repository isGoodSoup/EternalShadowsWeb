package es.eternalshadow.entities;

import es.eternalshadow.enums.Armamento;
import es.eternalshadow.pojos.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
@Entity
@Table(name = "TB_ARMA")
public class Arma extends Item {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ARMAMENTO", nullable = false, length = 50)
	private Armamento arma;

	public Arma(String nombre, int cantidad, Armamento arma) {
		super(nombre, cantidad);
		this.arma = arma;
	}
	
	public Arma() {
		
	}

	public Armamento getArma() {
		return arma;
	}

	public void setArma(Armamento arma) {
		this.arma = arma;
	}
}
