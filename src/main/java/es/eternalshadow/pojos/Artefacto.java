package es.eternalshadow.pojos;

import es.eternalshadow.enums.Reliquias;

public class Artefacto extends Item {
	private Reliquias artefacto;

	public Artefacto(String nombre, int cantidad, Reliquias artefacto) {
		super(nombre, cantidad);
		this.artefacto = artefacto;
	}

	public Reliquias getArtefacto() {
		return artefacto;
	}

	public void setArtefacto(Reliquias artefacto) {
		this.artefacto = artefacto;
	}
}
