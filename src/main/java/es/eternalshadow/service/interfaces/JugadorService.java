package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.entities.Jugador;

/**
 * Servicio para gestión de jugadores en el juego.
 */
public interface JugadorService {
    
    /**
     * Crea un nuevo jugador en el juego actual.
     */
    Jugador crearJugador(String nombre, String tipo);
    
    /**
     * Obtiene el jugador principal actual.
     */
    Jugador getJugadorPrincipal();
    
    /**
     * Obtiene todos los jugadores creados.
     */
    List<Jugador> getTodosJugadores();
    
    /**
     * Guarda el estado del jugador en base de datos.
     */
    void guardarJugador(Jugador jugador);
    
    /**
     * Carga un jugador desde base de datos.
     */
    Jugador cargarJugador(int id);
    
    /**
     * Elimina un jugador.
     */
    void eliminarJugador(int id);
}