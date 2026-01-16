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
     * Establece el jugador principal.
     */
    void setJugadorPrincipal(Jugador jugador);
    
    /**
     * Obtiene todos los jugadores creados.
     */
    List<Jugador> getTodosJugadores();
    
    /**
     * Guarda el estado del jugador en base de datos.
     */
    void guardarJugador(Jugador jugador);
    
    /**
     * Actualiza un jugador existente.
     */
    void actualizarJugador(Jugador jugador);
    
    /**
     * Carga un jugador desde base de datos.
     */
    Jugador cargarJugador(int id);
    
    /**
     * Elimina un jugador.
     */
    void eliminarJugador(int id);
    
    /**
     * Sube de nivel al jugador.
     */
    void subirNivel(Jugador jugador);
    
    /**
     * Aplica experiencia al jugador.
     */
    void aplicarExperiencia(Jugador jugador, int experiencia);
    
    /**
     * Cura al jugador (restaura puntos de vida).
     */
    void curarJugador(Jugador jugador, int puntosCuracion);
    
    /**
     * Añade un item al inventario del jugador.
     */
    void añadirItem(Jugador jugador, String item, int cantidad);
    
    /**
     * Quita un item del inventario del jugador.
     */
    void quitarItem(Jugador jugador, String item, int cantidad);
}