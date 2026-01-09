package es.eternalshadow.service;

import java.util.List;

import es.eternalshadow.dao.JugadorDAOImpl;
import es.eternalshadow.dao.interfaces.JugadorDAO;
import es.eternalshadow.dto.JugadorDTO;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.JugadorService;

public class JugadorServiceImpl implements JugadorService {
	private GameContext context;
    private JugadorDAO jugadorDAO;

    public JugadorServiceImpl(GameContext context) {
    	this.context = context;
        this.jugadorDAO = new JugadorDAOImpl();
    }
    
    public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}

	public JugadorDAO getJugadorDAO() {
		return jugadorDAO;
	}

	public void setJugadorDAO(JugadorDAO jugadorDAO) {
		this.jugadorDAO = jugadorDAO;
	}
	
	@Override
	public void crearJugador(JugadorDTO dto) {
        Jugador jugador = new Jugador(
            dto.getNombre(),
            dto.getTipo(),
            dto.getNivel(),
            dto.getPuntosVida(),
            dto.getMoral(),
            dto.getArmas(),
            dto.getEscudos(),
            dto.getInventario()
        );
        jugadorDAO.guardar(jugador);
    }
    
	@Override
    public void eliminarJugador(Long id) {
        jugadorDAO.eliminar(id);
    }

	@Override
    public void actualizarJugador(JugadorDTO dto) {
        Jugador jugador = (Jugador) jugadorDAO.obtenerPorId(dto.getId());
        if (jugador != null) {
            jugador.setNombre(dto.getNombre());
            jugador.setTipo(dto.getTipo());
            jugador.setNivel(dto.getNivel());
            jugador.setPuntosVida(dto.getPuntosVida());
            jugador.setMoral(dto.getMoral());
            jugador.setArmas(dto.getArmas());
            jugador.setEscudos(dto.getEscudos());
            jugador.setInventario(dto.getInventario());

            jugadorDAO.actualizar(jugador);
        }
    }
	
	@Override
    public JugadorDTO obtenerJugador(Long id) {
        Jugador jugador = (Jugador) jugadorDAO.obtenerPorId(id);
        if (jugador != null) {
            return new JugadorDTO(
                jugador.getNombre(),
                jugador.getTipo(),
                jugador.getNivel(),
                jugador.getPuntosVida(),
                jugador.getMoral(),
                jugador.getArmas(),
                jugador.getEscudos(),
                jugador.getInventario()
            );
        }
        return null;
    }
	
	@Override
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorDAO.obtenerTodosLosJugadores();
    }
}