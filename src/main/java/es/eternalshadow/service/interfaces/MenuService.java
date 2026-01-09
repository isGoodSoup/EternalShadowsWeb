package es.eternalshadow.service.interfaces;

import java.io.IOException;
import java.util.List;

import es.eternalshadow.enums.Menu;
import es.eternalshadow.enums.MenuOpciones;
import es.eternalshadow.exception.GameException;

public interface MenuService {
	void menuPrincipal(List<String> credenciales) throws IOException, InterruptedException, GameException;
	boolean opcionesMenu(Menu menu) throws GameException, InterruptedException;
	void menuOpciones() throws GameException, InterruptedException;
	boolean menuOpciones(MenuOpciones menu) throws GameException;
	void modoDebug();
	void verStats();
	void pintarLogo(String ruta) throws IOException, InterruptedException;
}
