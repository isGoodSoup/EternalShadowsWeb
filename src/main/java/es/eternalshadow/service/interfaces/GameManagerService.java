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
     * Pausa la partida actual.
     */
    void pausarPartida();
    
    /**
     * Retorna una partida pausada.
     */
    void retomarPartida();
    
    /**
     * Obtiene el estado actual del juego.
     * @return "MENU", "EN_COMBATE", "EN_HISTORIA", "PAUSADO", etc.
     */
    String getEstadoJuego();
    
    /**
     * Verifica si hay una partida en curso.
     */
    boolean isPartidaEnCurso();
    
    /**
     * Obtiene el progreso actual de la partida (0-100%).
     */
    int getProgresoPartida();
    
    /**
     * Obtiene el tiempo transcurrido en la partida (en minutos).
     */
    int getTiempoJugado();
}