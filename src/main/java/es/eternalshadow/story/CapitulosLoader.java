package es.eternalshadow.story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.motor.Escena;
import es.eternalshadow.motor.Opcion;

public class CapitulosLoader {

    public Capitulo cargarCapitulo(String ruta,
                                   List<Criatura> criaturas,
                                   Jugador jugador) throws IOException {

        InputStream is = getClass().getClassLoader().getResourceAsStream(ruta);

        if (is == null) {
            throw new IOException("No se encuentra el capítulo: " + ruta);
        }

        StringBuilder descripcion = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty() || linea.startsWith("#")) continue;

                descripcion.append(linea).append("\n");
            }
        }

        // 👇 USAMOS EL CONSTRUCTOR QUE SÍ EXISTE
        Escena escenaInicial = new Escena(
                descripcion.toString(),
                new ArrayList<Opcion>()   // IMPORTANTÍSIMO
        );

        Capitulo capitulo = new Capitulo();
        capitulo.setEscena(escenaInicial);

        return capitulo;
    }
}
