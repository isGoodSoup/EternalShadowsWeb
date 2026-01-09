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
		double d = random.nextInt(min, max) / 100.0;
		return d;
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
		return i[random.nextInt(i.length)];
	}

	/**
	 * Devuelve un número entero aleatorio dentro de un rango.
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Número aleatorio entre min y max.
	 */
	public static int toGetInteger(int min, int max) {
		return random.nextInt(min, max);
	}

	/**
	 * Devuelve un número largo aleatorio dentro de un rango.
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Número largo aleatorio entre min y max.
	 */
	public static long toGetLong(long min, long max) {
		return random.nextLong(min, max);
	}

	/**
	 * Escoge un arma aleatoria del enum Armamento y la devuelve
	 * @return Armamento
	 */
	public Armamento toGenArma() {
		Armamento[] armas = Armamento.values();
		return armas[random.nextInt(armas.length)];
	}
	
	/**
	 * Escoge un escudo aleatorio del enum Escuderia y la devuelve
	 * @return Escuderia
	 */
	public Escuderia toGenEscudo() {
		Escuderia[] escudos = Escuderia.values();
		return escudos[random.nextInt(0, 1)];
	}
}
