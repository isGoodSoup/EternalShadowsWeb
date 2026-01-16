package es.eternalshadow;

import es.eternalshadow.main.GameContext;

/**
 * Clase principal que inicia el juego.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Iniciando Eternal Shadow RPG...");
        
        GameContext context = null;
        try {
            // Crear contexto del juego
            context = new GameContext();
            
            // Iniciar el juego
            context.iniciar();
            
            // Esperar entrada del usuario (simulación)
            Thread.sleep(2000);
            
            System.out.println("\nDemo completada. El juego está listo para implementar.");
            System.out.println("Faltan por implementar los servicios completos.");
            
        } catch (Exception e) {
            System.err.println("Error fatal en la aplicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (context != null) {
                context.shutdown();
            }
        }
    }
}