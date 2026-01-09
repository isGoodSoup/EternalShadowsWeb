package es.eternalshadow.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
	
	/**
	 * Lee un archivo y devuelve su contenido como una lista de líneas.
	 * @param archivo Ruta del archivo a leer.
	 * @return Lista de líneas del archivo.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	public List<String> toLeerArchivo(String ruta) throws IOException {
	    InputStream is = getClass()
	            .getClassLoader()
	            .getResourceAsStream(ruta);

	    if (is != null) {
	        try (BufferedReader br = new BufferedReader(
	                new InputStreamReader(is, StandardCharsets.UTF_8))) {
	            return br.lines().toList();
	        }
	    }
	    
	    Path path = Paths.get(ruta);
	    if (Files.exists(path)) {
	        return Files.readAllLines(path, StandardCharsets.UTF_8);
	    }
	    throw new FileNotFoundException("No se encontró el archivo: " + ruta);
	}
}
