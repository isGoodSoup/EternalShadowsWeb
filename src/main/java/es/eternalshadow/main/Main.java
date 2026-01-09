package es.eternalshadow.main;

import java.io.IOException;

import es.eternalshadow.exception.GameException;
import es.eternalshadow.interfaces.Iniciable;
import es.eternalshadow.util.ExceptionsHandler;

public class Main implements Iniciable {
	private Panel panel = new Panel();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}
	
	@Override
	public void init() {
		try { panel.comenzar(); } catch (IOException | InterruptedException | GameException e) {
			ExceptionsHandler.printException(e);
		}
	}
}
