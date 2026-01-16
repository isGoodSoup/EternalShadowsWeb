package es.eternalshadow.dao.interfaces;

import java.util.List;

import es.eternalshadow.entities.Jugador;

public interface JugadorDAO {
    void guardar(Jugador jugador);
    void actualizar(Jugador jugador);
    void eliminar(Long id);
    Jugador obtenerPorId(Long id);
    List<Jugador> obtenerTodosLosJugadores();
}