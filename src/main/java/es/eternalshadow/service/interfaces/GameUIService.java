package es.eternalshadow.service.interfaces;

/**
 * Servicio para interfaces de usuario específicas del juego.
 */
public interface GameUIService {
    
    /**
     * Muestra el menú de personaje (estadísticas, equipamiento).
     */
    void mostrarMenuPersonaje();
    
    /**
     * Muestra el menú de inventario.
     */
    void mostrarMenuInventario();
    
    /**
     * Muestra el menú de habilidades.
     */
    void mostrarMenuHabilidades();
    
    /**
     * Muestra el menú de opciones/configuración.
     */
    void mostrarMenuOpciones();
    
    /**
     * Muestra estadísticas del jugador.
     */
    void mostrarStats();
    
    /**
     * Muestra el mapa del juego.
     */
    void mostrarMapa();
    
    /**
     * Muestra el diario de misiones.
     */
    void mostrarDiarioMisiones();
    
    /**
     * Muestra información de ayuda.
     */
    void mostrarAyuda();
}