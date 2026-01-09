package es.eternalshadow.enums;

import es.eternalshadow.enums.MenuOpciones;

public enum MenuOpciones {
	DIFICULTAD(1, "Dificultad"), 
	STATS(2, "Stats"), 
	VOLVER(3, "Volver");

	private final int codigo;
	private final String texto;

	MenuOpciones(int codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public static MenuOpciones fromCodigo(int codigo) {
		for (MenuOpciones m : values()) {
			if (m.codigo == codigo)
				return m;
		}
		return null;
	}
}
