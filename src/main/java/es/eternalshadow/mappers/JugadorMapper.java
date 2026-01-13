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
            jugador.getArmasJugador(),
            jugador.getEscudosJugador(),
            jugador.getInventario()
        );
    }

    public static Jugador toEntity(JugadorDTO dto) {
        if (dto == null) return null;

        Jugador jugador = new Jugador();
        jugador.setNombre(dto.getNombre());
        jugador.setTipo(dto.getTipo());
        jugador.setNivel(dto.getNivel());
        jugador.setPuntosVida(dto.getPuntosVida());
        jugador.setMoral(dto.getMoral());

        jugador.setArmasJugador(dto.getArmas());
        jugador.setEscudosJugador(dto.getEscudos());
        jugador.setInventario(dto.getInventario());

        return jugador;
    }

}
