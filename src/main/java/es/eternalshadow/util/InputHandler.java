package es.eternalshadow.util;

import org.jline.reader.LineReader;

public class InputHandler {
	
	/**
	 * Muestra un menú y obtiene la opción seleccionada por el usuario.
	 * @param reader Lector de líneas para recibir la entrada del usuario.
	 * @param menu   Opciones del menú como arreglo de strings.
	 * @param s      Mensaje para solicitar la opción.
	 * @return La opción seleccionada como entero.
	 * @throws InterruptedException
	 */
	public int crearMenu(LineReader reader, String[] menu, String s)
			throws InterruptedException {
		for (int i = 0; i < menu.length; i++) {
			Thread.sleep(100);
			System.out.println(i + 1 + ") " + menu[i]);
		}
		int num = toScanInteger(reader, s);
		return num;
	}
	
	/**
	 * Solicita una entrada de texto al usuario hasta que sea no vacía.
	 * @param reader Lector de líneas.
	 * @param s      Mensaje de solicitud.
	 * @return Cadena ingresada por el usuario.
	 */
	public static String toScan(LineReader reader, String s) {
		String line = "";
		do {
			System.out.print(s + ": ");
			line = reader.readLine().trim();
		} while (line.isEmpty());
		return line;
	}

	/**
	 * Solicita al usuario un número entero válido.
	 * @param reader Lector de líneas.
	 * @param s      Mensaje de solicitud.
	 * @return Número entero ingresado por el usuario.
	 */
	public static int toScanInteger(LineReader reader, String s) {
		String line = "";
		while (true) {
			try {
				do {
					System.out.print(s + ": ");
					line = reader.readLine().trim();
				} while (line.isEmpty());
				int num = Integer.parseInt(line);
				return num;
			} catch (Exception e) {
				ExceptionsHandler.printException(e);
			}
		}
	}
}
