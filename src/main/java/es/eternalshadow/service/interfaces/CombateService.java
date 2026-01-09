package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.pojos.Enemigo;

public interface CombateService {
	void iniciarCombate(List<Criatura> jugadores, List<Criatura> enemigos) throws LimiteCombateException;
	void luchar(Jugador jugador, Enemigo enemigo);
	boolean huir(Jugador jugador);
	int calcularCritico(int base);
}
