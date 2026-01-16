package es.eternalshadow.service.interfaces;

/**
 * Servicio principal para mostrar menús.
 */
public interface MenuService {
    
    /**
     * Muestra el menú principal y gestiona la navegación.
     */
    void mostrarMenuPrincipal();
    
    /**
     * Muestra el menú de pausa durante el juego.
     */
    void mostrarMenuPausa();
    
    /**
     * Procesa la opción seleccionada en un menú.
     * @param opcion Opción seleccionada (1, 2, 3...).
     * @return true si debe volver al menú anterior, false si continuar.
     */
    boolean procesarOpcion(int opcion);
    
    /**
     * Limpia la pantalla y muestra un título.
     */
    void mostrarPantalla(String titulo);
}