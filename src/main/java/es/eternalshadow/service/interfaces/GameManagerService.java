package es.eternalshadow.service.interfaces;

/**
 * Servicio para gestión general del juego.
 */
public interface GameManagerService {
    
    /**
     * Inicia una nueva partida.
     */
    void nuevaPartida();
    
    /**
     * Carga una partida guardada.
     */
    void cargarPartida(int idPartida);
    
    /**
     * Guarda la partida actual.
     */
    void guardarPartida();
    
    /**
     * Continúa una partida en curso.
     */
    void continuarPartida();
    
    /**
     * Termina la partida actual.
     */
    void terminarPartida();
    
    /**
     * Obtiene el estado actual del juego.
     * @return "MENU", "EN_COMBATE", "EN_HISTORIA", etc.
     */
    String getEstadoJuego();
    
    /**
     * Verifica si hay una partida en curso.
     */
    boolean isPartidaEnCurso();
}