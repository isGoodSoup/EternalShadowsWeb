package es.eternalshadow;

import es.eternalshadow.main.GameContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Eternal Shadow RPG...");
        
        GameContext context = null;
        try {
            context = new GameContext();
            context.iniciar();
        } catch (Exception e) {
            System.err.println("✗ Error fatal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (context != null) {
                context.shutdown();
            }
        }
    }
}