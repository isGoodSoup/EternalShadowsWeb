package es.eternalshadow.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jline.reader.LineReader;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.main.GameContext;

public class CharacterFactory {
    private static Random random = new Random();
    
    /**
     * Permite al usuario crear un personaje personalizado.
     */
    public Jugador crearPersonaje(LineReader reader) throws LimiteCombateException {
        System.out.println("\n=== CREACIÓN DE PERSONAJE ===");
        
        // Nombre
        String nombre = InputHandler.toScan(reader, "Nombre del personaje");
        
        // Raza/Tipo
        String tipo = InputHandler.toScan(reader, "Raza (Humano, Elfo, Enano, Orco)");
        
        // Atributos básicos (simplificado)
        System.out.println("\nDistribuye 20 puntos entre estos atributos (mínimo 1 en cada):");
        int fuerza = getValidAttribute(reader, "Fuerza", 20);
        int resistencia = getValidAttribute(reader, "Resistencia", 20 - fuerza);
        int velocidad = getValidAttribute(reader, "Velocidad", 20 - fuerza - resistencia);
        int magia = 20 - fuerza - resistencia - velocidad;
        System.out.println("Magia asignada automáticamente: " + magia);
        
        // Ataque/Defensa (simplificado)
        System.out.println("\nDistribuye 10 puntos entre Ataque y Defensa:");
        int ataque = getValidCombatStat(reader, "Ataque");
        int defensa = 10 - ataque;
        System.out.println("Defensa asignada automáticamente: " + defensa);
        
        // Crear jugador
        Jugador jugador = new Jugador(tipo, nombre, fuerza, resistencia, velocidad, magia, 100, 50, ataque, defensa);
        
        System.out.println("\n¡Personaje creado exitosamente!");
        System.out.println(jugador);
        
        return jugador;
    }
    
    private int getValidAttribute(LineReader reader, String atributo, int puntosRestantes) {
        while (true) {
            System.out.print("\nPuntos restantes: " + puntosRestantes);
            int valor = InputHandler.toScanInteger(reader, atributo + " (mínimo 1, máximo " + Math.max(1, puntosRestantes) + ")");
            
            if (valor < 1) {
                System.out.println("Debe ser al menos 1.");
            } else if (valor > puntosRestantes) {
                System.out.println("No tienes suficientes puntos.");
            } else {
                return valor;
            }
        }
    }
    
    private int getValidCombatStat(LineReader reader, String stat) {
        while (true) {
            int valor = InputHandler.toScanInteger(reader, stat + " (0-10)");
            if (valor < 0 || valor > 10) {
                System.out.println("Debe estar entre 0 y 10.");
            } else {
                return valor;
            }
        }
    }
    
    /**
     * Crea personajes de prueba.
     */
    public List<Criatura> crearPersonajesTest() {
        List<Criatura> criaturas = new ArrayList<>();
        String[] nombres = {"Aragorn", "Legolas", "Gimli", "Gandalf", "Frodo", "Sam"};
        String[] razas = {"Humano", "Elfo", "Enano", "Mago", "Hobbit", "Hobbit"};
        
        for (int i = 0; i < 6; i++) {
            Jugador jugador = new Jugador(
                razas[i],
                nombres[i],
                random.nextInt(10) + 5,  // Fuerza 5-14
                random.nextInt(10) + 5,  // Resistencia 5-14
                random.nextInt(10) + 5,  // Velocidad 5-14
                random.nextInt(10) + 5,  // Magia 5-14
                100,
                50,
                random.nextInt(6) + 5,   // Ataque 5-10
                random.nextInt(6) + 5    // Defensa 5-10
            );
            criaturas.add(jugador);
        }
        
        System.out.println("Creados 6 personajes de prueba.");
        return criaturas;
    }
}