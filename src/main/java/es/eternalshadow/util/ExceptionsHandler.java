package es.eternalshadow.util;

public class ExceptionsHandler {
	
	/**
	 * Imprime información detallada de una excepción.
	 * 
	 * @param e Excepción a imprimir.
	 */
	public static void printException(Exception e) {
		System.err.println(e.getClass().getSimpleName() + " at line "
				+ e.getStackTrace()[e.getStackTrace().length - 3].getLineNumber() + ": " + e.getMessage());
	}
}
