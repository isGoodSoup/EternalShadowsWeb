package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Jugador;

public interface JugadorDAO extends DAO {
	List<Jugador> obtenerTodosLosJugadores();
}
