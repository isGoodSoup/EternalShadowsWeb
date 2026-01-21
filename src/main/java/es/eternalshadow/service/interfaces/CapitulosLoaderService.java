package es.eternalshadow.service.interfaces;

import java.util.List;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.story.Capitulo;

public interface CapitulosLoaderService {

    /**
     * Carga todos los capítulos disponibles del juego.
     */
    Capitulo cargarCapitulos(String rutaBase, List<Criatura> criaturas, Jugador jugador);

    /**
     * Obtiene un capítulo por número.
     * @param numero número del capítulo
     * @return Capítulo correspondiente o null si no existe
     */
    Capitulo getCapitulo(int numero);

    /**
     * Obtiene el capítulo inicial del juego.
     */
    Capitulo getCapituloInicial();


}

