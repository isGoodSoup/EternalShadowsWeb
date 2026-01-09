package es.eternalshadow.util;

import java.util.Random;

/**
 * Simula el lanzamiento de dos dados de 20 caras.
 * 
 * @return Arreglo con los resultados de los dos dados.
 */
public class Dados {
	private static Random random = new Random();

	public static int lanzar() {
		return random.nextInt(21) + 1;
	}

	public static int tirarDados() {
		for (int i = 0; i < 5; i++) {
			lanzar();
		}
		return 0;
	}
}
