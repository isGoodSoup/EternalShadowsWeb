package es.eternalshadow.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import es.eternalshadow.service.interfaces.EULAService;

/**
 * Clase service para leer, guardar y checar si existe el 
 * archivo de EULA para las condiciones de uso del juego
 */
public class EULAServiceImpl implements EULAService {
    private final Path rutaEula;

    public EULAServiceImpl(String ruta) {
        this.rutaEula = Path.of(ruta);
    }
    
    @Override
    public boolean isExiste() {
        return Files.exists(rutaEula);
    }
    
    @Override
    public boolean isAceptado() throws IOException {
        List<String> lineas = Files.readAllLines(rutaEula);
        if (lineas.isEmpty()) return false;
        return Boolean.parseBoolean(lineas.get(0));
    }
    
    @Override
    public List<String> leerTexto() throws IOException {
        List<String> lineas = Files.readAllLines(rutaEula);

        if (!lineas.isEmpty() &&
            (lineas.get(0).equalsIgnoreCase("true") ||
             lineas.get(0).equalsIgnoreCase("false"))) {
            lineas.remove(0);
        }
        return lineas;
    }
    
    @Override
    public void guardar(boolean aceptado) throws IOException {
        List<String> texto = leerTexto();
        texto.add(0, String.valueOf(aceptado));
        Files.write(rutaEula, texto);
    }
}

