package es.eternalshadow.pojos;

import es.eternalshadow.dto.ItemDTO;
import es.eternalshadow.enums.Reliquias;

public class Artefacto extends ItemDTO {
	private Reliquias artefacto;

	public Artefacto() {
		super();
	}
	
	public Artefacto(String nombre, int cantidad, String tipo, 
			String descripcion, int valor, Reliquias artefacto) {
		super(nombre, cantidad, tipo, descripcion, valor);
		this.artefacto = artefacto;
	}

	public Reliquias getArtefacto() {
		return artefacto;
	}

	public void setArtefacto(Reliquias artefacto) {
		this.artefacto = artefacto;
	}
}
