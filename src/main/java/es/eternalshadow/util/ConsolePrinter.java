package es.eternalshadow.util;

import org.jline.utils.InfoCmp;

import es.eternalshadow.main.Panel;

public class ConsolePrinter {
	private Panel panel;
	
	/**
	 * Imprime un título decorativo en consola.
	 * @param s Texto del título.
	 */
	public static void toGetString(String s) {
		for (int i = 0; i < s.length(); i++)
			System.out.print("=");
		System.out.print(" " + s + " ");
		for (int i = 0; i < s.length(); i++)
			System.out.print("=");
		System.out.println();
	}
	
	public void limpiarPantalla() {
		panel.getTerminal().puts(InfoCmp.Capability.clear_screen);
		panel.getTerminal().flush();
	}
}
