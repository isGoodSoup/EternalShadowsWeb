package es.eternalshadow.util;

import java.util.Random;

import es.eternalshadow.enums.Armamento;
import es.eternalshadow.enums.Escuderia;

public class RandomUtils {
    private static Random random = new Random();
    
    /**
     * Genera un número decimal aleatorio entre min y max dividido por 100.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Número decimal aleatorio.
     */
    public static double toGetDouble(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min debe ser menor que max");
        }
        double d = random.nextInt(max - min + 1) + min;
        return d / 100.0;
    }

    /**
     * Genera un valor booleano aleatorio.
     * @return true o false de forma aleatoria.
     */
    public static boolean toGetBoolean() {
        return random.nextBoolean();
    }

    /**
     * Devuelve un elemento aleatorio de un arreglo de cadenas.
     * @param s Arreglo de cadenas.
     * @return Cadena aleatoria del arreglo.
     */
    public static String toGetString(String[] s) {
        if (s == null || s.length == 0) {
            return "";
        }
        return s[random.nextInt(s.length)];
    }

    /**
     * Devuelve un número entero aleatorio.
     * @return Número aleatorio.
     */
    public static int toGetInteger() {
        return random.nextInt();
    }

    /**
     * Devuelve un número aleatorio de un arreglo de enteros.
     * @param i Arreglo de enteros.
     * @return Entero aleatorio del arreglo.
     */
    public static int toGetInteger(int[] i) {
        if (i == null || i.length == 0) {
            return 0;
        }
        return i[random.nextInt(i.length)];
    }

    /**
     * Devuelve un número entero aleatorio dentro de un rango.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Número aleatorio entre min y max.
     */
    public static int toGetInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min debe ser menor que max");
        }
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Devuelve un número largo aleatorio dentro de un rango.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @return Número largo aleatorio entre min y max.
     */
    public static long toGetLong(long min, long max) {
        if (min >= max) {
            throw new IllegalArgumentException("min debe ser menor que max");
        }
        return min + (long)(random.nextDouble() * (max - min));
    }

    /**
     * Escoge un arma aleatoria del enum Armamento y la devuelve
     * @return Armamento
     */
    public static Armamento toGenArma() {
        Armamento[] armas = Armamento.values();
        return armas[random.nextInt(armas.length)];
    }
    
    /**
     * Escoge un escudo aleatorio del enum Escuderia y lo devuelve
     * @return Escuderia
     */
    public static Escuderia toGenEscudo() {
        Escuderia[] escudos = Escuderia.values();
        return escudos[random.nextInt(escudos.length)];
    }
    
    /**
     * Genera un porcentaje aleatorio entre 0 y 100.
     * @return Porcentaje aleatorio.
     */
    public static int toGetPorcentaje() {
        return random.nextInt(101); // 0-100
    }
}