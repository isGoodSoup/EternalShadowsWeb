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
    private GameContext context;
    private static Random random = new Random();
    
    /**
     * Constructor que recibe el contexto del juego.
     */
    public CharacterFactory(GameContext context) {
        this.context = context;
    }
    
    /**
     * Permite al usuario crear un personaje personalizado eligiendo clase y
     * atributos.
     * @param reader Lector de líneas.
     * @return Objeto {@link Jugador} creado.
     * @throws LimiteCombateException 
     */
    public Jugador crearPersonaje(LineReader reader) throws LimiteCombateException {
        ConsolePrinter.toGetString("CREACIÓN DE PERSONAJE");
        System.out.println("Tienes 80 puntos para distribuir entre Fuerza, Resistencia, Velocidad y Magia.");
        
        int puntos = 0;
        int ataque = 0, defensa = 0;
        int moral = 50;
        int f, r, v, m;
        
        do {
            System.out.println("\nDistribuye tus 80 puntos:");
            f = InputHandler.toScanInteger(reader, "Fuerza (1-50)");
            r = InputHandler.toScanInteger(reader, "Resistencia (1-50)");
            v = InputHandler.toScanInteger(reader, "Velocidad (1-50)");
            m = InputHandler.toScanInteger(reader, "Magia (1-50)");
            
            puntos = f + r + v + m;
            
            if (puntos != 80) {
                System.out.println("¡Error! La suma debe ser exactamente 80 puntos. Suma actual: " + puntos);
                System.out.println("Puntos restantes: " + (80 - puntos));
            }
        } while (puntos != 80);
        
        boolean isAtLimite = false;
        do {
            System.out.println("\nAhora distribuye 10 puntos entre Ataque y Defensa:");
            ataque = InputHandler.toScanInteger(reader, "Ataque (0-10)");
            defensa = InputHandler.toScanInteger(reader, "Defensa (0-10)");
            
            isAtLimite = ataque + defensa != 10;
                    
            if (isAtLimite) { 
                ExceptionsHandler.printException(new LimiteCombateException("El ataque y la defensa deben sumar 10"));
            }
        } while (isAtLimite);
        
        String tipo = InputHandler.toScan(reader, "Elige tu raza (Humano, Elfo, Enano, Orco)");
        String nombre = InputHandler.toScan(reader, "Introduce tu nombre");
        
        Jugador jugador = new Jugador(tipo, nombre, f, r, v, m, 100, moral, ataque, defensa, null);
        System.out.println("\n¡Personaje creado exitosamente!");
        System.out.println(jugador);
        
        return jugador;
    }

    /**
     * Crea una criatura default con valores default con el objetivo de testeo
     * más eficiente.
     * 
     * @return Lista de {@link Criatura} creadas.
     */
    public List<Criatura> crearPersonajesTest() {
        List<Criatura> criaturas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int f = Dados.tirarDados(2) / 2; // Promedio más balanceado
            int r = Dados.tirarDados(2) / 2;
            int v = Dados.tirarDados(2) / 2;
            int m = Dados.tirarDados(2) / 2;
            
            String tipo = "CriaturaGenerica";
            String nombre = "JugadorTest" + i;
            Jugador jugador = new Jugador(tipo, nombre, f, r, v, m, 100, 50,
                    Dados.lanzar() % 6 + 5, // Ataque 5-10
                    Dados.lanzar() % 6 + 5, // Defensa 5-10
                    null);
            criaturas.add(jugador);
        }
        return criaturas;
    }

    /**
     * Crea una criatura aleatoria con atributos que suman 80 (como jugador) y clase
     * aleatoria.
     * 
     * @return Criatura aleatoria creada.
     */
    public Criatura crearCriaturaAleatoria() {
        // Distribuir 80 puntos entre 4 atributos
        int[] atributos = new int[4];
        int puntosTotal = 80;
        
        for (int i = 0; i < 3; i++) {
            // Asegurar que queden puntos para los últimos atributos
            int max = Math.min(50, puntosTotal - (3 - i));
            atributos[i] = random.nextInt(Math.max(1, max)) + 1;
            puntosTotal -= atributos[i];
        }
        atributos[3] = Math.max(1, puntosTotal); // El último se lleva lo que queda

        int fuerza = atributos[0];
        int resistencia = atributos[1];
        int velocidad = atributos[2];
        int magia = atributos[3];

        String[] razas = { "Mago", "Guerrero", "Demonio", "Elfo Oscuro",
                "Enano", "Elfo", "Humano", "Orco" };
        String tipo = razas[random.nextInt(razas.length)];

        // Generar nombre aleatorio
        String[] nombres = { "Grom", "Thrall", "Jaina", "Arthas", "Sylvanas", 
                           "Uther", "Illidan", "Tyrande", "Malfurion", "Anduin" };
        String nombre = nombres[random.nextInt(nombres.length)] + " el " + tipo;
        
        int puntosVida = 80 + random.nextInt(41); // 80-120 PV
        
        Criatura c = new Criatura(tipo, nombre, fuerza, resistencia, velocidad,
                magia, puntosVida);
        
        System.out.println("Criatura enemiga creada: " + c.getNombre()
                + " con atributos: " + "Fuerza: " + fuerza
                + ", Resistencia: " + resistencia + ", Velocidad: "
                + velocidad + ", Magia: " + magia + ", PV: " + puntosVida);
        
        return c;
    }

    /**
     * Formatea un mensaje para solicitar un atributo.
     * 
     * @param s Nombre del atributo.
     * @return Mensaje formateado.
     */
    private String q(String s) {
        return "Introduce el valor de " + s + " (0-50)";
    }
}