package es.eternalshadow.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eternalshadow.entities.Jugador;
import es.eternalshadow.enums.Eula;
import es.eternalshadow.exception.GameException;
import es.eternalshadow.service.ServiceFactory;
import es.eternalshadow.story.Historia;
import es.eternalshadow.story.HistoriaPrincipal;
import es.eternalshadow.util.UtilHub;

public class Panel {
	private GameContext context;
	private Terminal terminal;
	private LineReader reader;
	private String titulo = "Eternal Shadows";
	private final UtilHub util = new UtilHub();
	private Eula eula;
	private int opcion;
	private static final Logger log = LoggerFactory.getLogger(Panel.class);

	public Panel() {
		try {
			terminal = TerminalBuilder.builder().system(true).build();
			reader = LineReaderBuilder.builder().terminal(terminal).build();
			ServiceFactory services = new ServiceFactory();
			GameContext contextTemp = new GameContext(null, new Jugador(),
			        reader, util, new HashMap<>(), services);
			services.init(contextTemp);

			Historia historia = new HistoriaPrincipal(titulo, this, contextTemp);
			contextTemp.setHistoria(historia);

			this.context = contextTemp;
			context.getServices().getCapitulosLoader().startAcciones();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Eula getEula() {
		return eula;
	}

	public void setEula(Eula eula) {
		this.eula = eula;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public GameContext getContext() {
		return context;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public LineReader getReader() {
		return reader;
	}

	public UtilHub getUtil() {
		return util;
	}

	/**
	 * Maneja la aceptación del EULA.
	 */
	private boolean aceptarEULA()
			throws IOException, InterruptedException, GameException {
		if (!context.getServices().getEulaService().isExiste()) {
			log.error("No se encuentra el archivo del EULA");
			System.exit(0);
		}
		if (context.getServices().getEulaService().isAceptado()) {
			return true;
		}

		context.getServices().getMenuService().pintarLogo("docs/logo.txt");
		for (String linea : context.getServices().getEulaService()
				.leerTexto()) {
			System.out.println(linea);
			Thread.sleep(50);
		}

		String[] menu = { "SI", "NO" };
		int eulaConf = util.getInputHandler().crearMenu(reader, menu,
				"¿Aceptas estas condiciones?");
		switch (eulaConf) {
		case 1 -> {
			eula = Eula.CONFIRMACION;
			context.getServices().getEulaService().guardar(true);
			return true;
		}

		case 2 -> {
			eula = Eula.NEGACION;
			context.getServices().getEulaService().guardar(false);
			return false;
		}

		default -> throw new GameException("Opción de EULA inválida");
		}
	}

	/**
	 * Inicia la aplicación y delega en los servicios.
	 */
	public void comenzar()
			throws InterruptedException, IOException, GameException {
		boolean isEula = aceptarEULA();
		if (!isEula) {
			log.error("Negación del EULA, saliendo...");
			System.exit(0);
		}
		log.debug("EULA confirmado, continuando...");

		List<String> credenciales = context.getServices().getUserService()
				.panelDeInicio();
		context.getServices().getCapitulosLoader().cargarCapitulos();
		context.getServices().getMenuService().menuPrincipal(credenciales);
	}
}
