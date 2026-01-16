package es.eternalshadow.util;

import java.util.Random;

/**
 * Simula el lanzamiento de dados.
 */
public class Dados {
    private static Random random = new Random();

    /**
     * Lanza un dado de 20 caras.
     * @return Valor entre 1 y 20.
     */
    public static int lanzar() {
        return random.nextInt(20) + 1; // 1-20
    }

    /**
     * Tira 2 dados de 20 caras y devuelve la suma.
     * @return Suma de dos dados (2-40).
     */
    public static int tirarDados() {
        return lanzar() + lanzar();
    }
    
    /**
     * Tira un número específico de dados.
     * @param cantidad Número de dados a tirar.
     * @return Suma de los dados.
     */
    public static int tirarDados(int cantidad) {
        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            total += lanzar();
        }
        return total;
    }
}