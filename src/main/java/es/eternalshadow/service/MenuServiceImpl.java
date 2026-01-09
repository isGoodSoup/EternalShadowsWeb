package es.eternalshadow.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.enums.Dificultad;
import es.eternalshadow.enums.Menu;
import es.eternalshadow.enums.MenuOpciones;
import es.eternalshadow.exception.GameException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.MenuService;

public class MenuServiceImpl implements MenuService {
    private GameContext context;
    private static final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    public MenuServiceImpl(GameContext context) {
        super();
        this.context = context;
    }
    
    public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}

	/**
     * Procesa el menu principal y lo desglosa en 4 partes
     */
	@Override
    public void menuPrincipal(List<String> credenciales)
            throws IOException, InterruptedException, GameException {
        boolean salir = false;

        String[] opciones = Arrays.stream(Menu.values())
                .map(Menu::getTexto)
                .toArray(String[]::new);

        while (!salir) {
            pintarLogo("./docs/logo.txt");
            int opcion = context.getUtil().getInputHandler().crearMenu(context.getReader(), opciones, "Introduce tu opción");
            Menu menuSeleccionado = Menu.fromCodigo(opcion);

            salir = opcionesMenu(menuSeleccionado);
        }
    }
	
	@Override
	public boolean opcionesMenu(Menu menu) throws GameException, InterruptedException {
        if (menu == null) return false;

        switch (menu) {
            case COMENZAR -> context.getServices().getGameService().iniciarPartida();
            case OPCIONES -> menuOpciones();
            case SALIR -> {
                log.debug("Salida");
                return true;
            }
            case DEBUG -> modoDebug();
        }
        return false;
    }
	
	@Override
	public void menuOpciones() throws GameException, InterruptedException {
        boolean volver = false;

        String[] opciones = Arrays.stream(MenuOpciones.values())
                .map(MenuOpciones::getTexto)
                .toArray(String[]::new);

        while (!volver) {
            int opcion = context.getUtil().getInputHandler().crearMenu(context.getReader(), opciones, "Opciones");
            MenuOpciones seleccion = MenuOpciones.fromCodigo(opcion);

            volver = menuOpciones(seleccion);
        }
    }
	
	@Override
	public boolean menuOpciones(MenuOpciones menu) throws GameException {
        if (menu == null) return false;

        switch (menu) {
            case DIFICULTAD -> {
                Dificultad nueva = Dificultad.NORMAL;
                log.debug("Dificultad seleccionada: " + nueva);
                return true;
            }
            case STATS -> verStats();
            case VOLVER -> {
                return true;
            }
        }
        return false;
    }
	
	@Override
	public void modoDebug() {
        log.debug("Dev mode");
        context.getHistoria().iniciar(context.getUtil().getCharacterFactory().crearPersonaje(), context.getReader(), context.getUtil());
    }
	
	@Override
	public void verStats() {
        // TODO Stats
    }

    /**
	 * Imprime un logo desde archivo, línea por línea.
	 */
	@Override
	public void pintarLogo(String ruta)
			throws IOException, InterruptedException {
		System.out.println();
		try {
			for (String linea : context.getUtil().getFileManager().toLeerArchivo(ruta)) {
				System.out.println(linea);
				Thread.sleep(50);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
