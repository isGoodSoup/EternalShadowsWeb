package es.eternalshadow.service.interfaces;

import java.io.IOException;
import java.util.List;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.story.Capitulo;

public interface CapitulosLoader {
	void cargarCapitulos();
	Capitulo cargarCapitulo(String ruta, List<Criatura> jugadores, Criatura criatura) throws IOException;
	void ejecutarAccion(String nombreAccion, List<Criatura> jugadores, Criatura criatura);
	void startAcciones();
}
