package es.eternalshadow.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jline.reader.LineReader;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.enums.ParsingKeys;
import es.eternalshadow.interfaces.Accion;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.motor.Escena;
import es.eternalshadow.motor.Opcion;
import es.eternalshadow.pojos.Enemigo;
import es.eternalshadow.pojos.Pocion;
import es.eternalshadow.service.interfaces.CapitulosLoader;
import es.eternalshadow.story.Capitulo;
import es.eternalshadow.util.ExceptionsHandler;

public class CapitulosLoaderImpl implements CapitulosLoader {
	private final GameContext context;

	public CapitulosLoaderImpl(GameContext context) {
		this.context = context;
	}

	/**
	 * Se encarga de cargar los Capitulos 1:1 y añadirlos a un ArrayList de
	 * Capitulos.
	 */
	@Override
	public void cargarCapitulos() {
		try {
			int total = context.getUtil().getStoryLoader().getCapitulosTotales();
			for (int i = 1; i <= total; i++) {
				context.getHistoria().getCapitulos()
						.add(new Capitulo(i, "", null));
				context.getUtil().getFileManager()
						.toLeerArchivo("docs/mq/capitulo" + i + ".txt");
			}
		} catch (IOException e) {
			ExceptionsHandler.printException(e);
		}
	}

	/**
	 * Se parsea el archivo y los jugadores junto con una criatura y se devuelve
	 * el capitulo finalmente
	 */
	@Override
	public Capitulo cargarCapitulo(String ruta, List<Criatura> jugadores,
			Criatura criatura) throws IOException {
		List<String> lineas = context.getUtil().getFileManager().toLeerArchivo(ruta);
		Map<String, Escena> escenas = new HashMap<>();

		Escena escenaActual = null;
		List<Opcion> opcionesActuales = null;

		LineReader reader = context.getReader();
		String nombre = "";
		int numero = 0;

		for (int i = 0; i < lineas.size(); i++) {
			String linea = lineas.get(i).trim();

			if (linea.startsWith("#FIN")) {
				break;
			}

			ParsingKeys key = context.getUtil().getStoryLoader().getParsingKey(linea);
			if (key == null) {
				System.out.print(linea);
				reader.readLine();
				continue;
			}
			switch (key) {
			case JUGADOR:
				String textoJugador = linea
						.replace("#JUGADOR", context.getJugador().getNombre())
						.trim();
				System.out.print(textoJugador);
				reader.readLine();
				break;

			case NOMBRE:
				nombre = linea.replace("#NOMBRE", "").trim();
				break;

			case CAPITULO:
				numero = Integer
						.parseInt(linea.replace("#CAPITULO", "").trim());
				break;

			case ESCENA:
				String id = linea.replace("#ESCENA", "").trim();
				escenaActual = new Escena(id, new ArrayList<>());
				escenas.put(id, escenaActual);
				opcionesActuales = escenaActual.getOpciones();
				break;

			case OPCION:
				String texto = linea.replace("#OPCION", "").trim();
				Opcion opcion = new Opcion(texto);

				while (++i < lineas.size()) {
					String sub = lineas.get(i).trim();
					if (sub.startsWith("#") || sub.isEmpty()) {
						i--;
						break;
					}
					if (sub.startsWith("ACCION:")) {
						opcion.setAccion(() -> ejecutarAccion(
								sub.substring(7).trim(), jugadores, criatura));
					} else if (sub.startsWith("DESTINO:")) {
						opcion.setSiguienteEscenaId(sub.substring(8).trim());
					}
				}
				opcionesActuales.add(opcion);
				break;

			case COMBATE:
				
				break;

			default:
				System.out.println(linea);
				reader.readLine();
				break;
			}

			for (Escena escena : escenas.values()) {
				for (Opcion opcion : escena.getOpciones()) {
					String destinoId = opcion.getSiguienteEscenaId();
					if (destinoId != null) {
						Escena destino = escenas.get(destinoId);
						if (destino == null) {
							System.err.println(
									"ERROR: Escena destino no encontrada: "
											+ destinoId);
						} else {
							opcion.setEscenaDestino(destino);
						}
					}
				}
			}
		}
		return new Capitulo(numero, nombre, escenaActual);
	}

	/**
	 * Ejecuta las acciones que se parsean por #OPCION
	 */
	@Override
	public void ejecutarAccion(String nombreAccion, List<Criatura> jugadores,
			Criatura criatura) {
		Accion accion = context.getAcciones().get(nombreAccion);
		if (accion == null) {
			System.err.println("ERROR: Acción desconocida: " + nombreAccion);
			return;
		}
		accion.ejecutar(jugadores, criatura);
	}

	/**
	 * Inicializa las acciones de la historia
	 */
	@Override
	public void startAcciones() {
		Map<String, Accion> acciones = context.getAcciones();

		acciones.put("addPocion",
				(jugadores, criatura) -> context.getJugador().getInventario()
						.put("Pocion de Sanación",
								new Pocion("Pocion de Curacion", 1)));
		acciones.put("aumentarMoral",
				(jugadores, criatura) -> context.getJugador().modMoral(1));
		acciones.put("luchar", (jugadores, criatura) -> 
	    context.getServices().getCombateService().luchar(context.getJugador(), (Enemigo) criatura)
		);
		acciones.put("huir", (jugadores, criatura) -> 
		    context.getServices().getCombateService().huir(context.getJugador())
		);

	}
}
