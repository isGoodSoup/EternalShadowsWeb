package es.eternalshadow.mappers;

import es.eternalshadow.dto.JugadorDTO;
import es.eternalshadow.entities.Jugador;

public class JugadorMapper {

    public static JugadorDTO toDTO(Jugador jugador) {
        if (jugador == null) return null;
        return new JugadorDTO(
                jugador.getNombre(),
                jugador.getTipo(),
                jugador.getNivel(),
                jugador.getPuntosVida(),
                jugador.getMoral(),
                jugador.getArmas(),
                jugador.getEscudos()
        );
    }
}