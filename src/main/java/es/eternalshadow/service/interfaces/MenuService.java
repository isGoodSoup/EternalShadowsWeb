package es.eternalshadow.service.interfaces;

/**
 * Servicio para mostrar menús y gestionar navegación.
 */
public interface MenuService {
    
    /**
     * Muestra el menú principal y gestiona la navegación.
     */
    void mostrarMenuPrincipal();
    
    /**
     * Muestra el menú de opciones/configuración.
     */
    void mostrarMenuOpciones();
    
    /**
     * Muestra el menú de personaje.
     */
    void mostrarMenuPersonaje();
    
    /**
     * Muestra el menú de inventario.
     */
    void mostrarMenuInventario();
    
    /**
     * Muestra estadísticas del jugador.
     */
    void mostrarStats();
    
    /**
     * Muestra información de ayuda.
     */
    void mostrarAyuda();
}