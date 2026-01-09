package es.eternalshadow.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import es.eternalshadow.enums.ParsingKeys;

public class StoryLoader {
	
	/**
	 * Devuelve el número total de capítulos en la historia.
	 * 
	 * @return Número total de capítulos.
	 */
	public int getCapitulosTotales() {
		try {
			return Files.list(Paths.get("./docs/mq"))
					.filter(f -> f.getFileName().toString().startsWith("capitulo"))
					.toArray().length;
		} catch (IOException e) {
			ExceptionsHandler.printException(e);
		}
		return 0;
	}

	/**
	 * Genera un enum dependiendo del tipo de parsing de la línea
	 * @param linea
	 * @return
	 */
	public ParsingKeys getParsingKey(String linea) {
		if (linea.startsWith("#NOMBRE"))
			return ParsingKeys.NOMBRE;
		if (linea.startsWith("#CAPITULO"))
			return ParsingKeys.CAPITULO;
		if (linea.startsWith("#ESCENA"))
			return ParsingKeys.ESCENA;
		if (linea.startsWith("#OPCION"))
			return ParsingKeys.OPCION;
		return null;
	}
}
