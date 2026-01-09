package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.dto.JugadorDTO;
import es.eternalshadow.entities.Jugador;

public interface JugadorService {
	void crearJugador(JugadorDTO dto);
	void eliminarJugador(Long id);
	void actualizarJugador(JugadorDTO dto);
	JugadorDTO obtenerJugador(Long id);
	List<Jugador> obtenerTodosLosJugadores();
}
