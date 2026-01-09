package es.eternalshadow.interfaces;

import java.util.List;

import es.eternalshadow.entities.Criatura;

@FunctionalInterface
public interface Accion {
	void ejecutar(List<Criatura> jugadores, Criatura criatura);
}
