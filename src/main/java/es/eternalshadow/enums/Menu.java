package es.eternalshadow.enums;

public enum Menu {
	COMENZAR(1, "Comenzar aventura"), 
	OPCIONES(2, "Opciones"), 
	SALIR(3, "Salir"), 
	DEBUG(700, "Debug");

	private final int codigo;
	private final String texto;

	Menu(int codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public static Menu fromCodigo(int codigo) {
		for (Menu m : values()) {
			if (m.codigo == codigo) {
				return m;
			}
		}
		return null;
	}
}
