package es.eternalshadow.enums;

import es.eternalshadow.enums.MenuSesiones;

public enum MenuSesiones {
	INICIAR_SESION(1, "Inicio de sesión"), 
	REGISTRAR(2, "Registrar"), 
	SALIR(3, "Salir");

	private final int codigo;
	private final String texto;

	MenuSesiones(int codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public static MenuSesiones fromCodigo(int codigo) {
		for (MenuSesiones m : values()) {
			if (m.codigo == codigo)
				return m;
		}
		return null;
	}
}
