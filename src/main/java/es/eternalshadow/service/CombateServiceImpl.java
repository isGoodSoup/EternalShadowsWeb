package es.eternalshadow.service;

import java.util.List;
import java.util.Random;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.pojos.Enemigo;
import es.eternalshadow.service.interfaces.CombateService;

public class CombateServiceImpl implements CombateService {
	private GameContext context;
	private final Random random = new Random();

	public CombateServiceImpl(GameContext context) {
		this.context = context;
	}

	public GameContext getContext() {
		return context;
	}
	
	public void setContext(GameContext context) {
		this.context = context;
	}
	
	@Override
	public void iniciarCombate(List<Criatura> jugadores, 
			List<Criatura> enemigos) throws LimiteCombateException {

		if (jugadores == null || jugadores.isEmpty()) {
			throw new LimiteCombateException("No hay jugadores para el combate");
		}
		if (enemigos == null || enemigos.isEmpty()) {
			throw new LimiteCombateException("No hay enemigos para el combate");
		}

		while (!jugadores.isEmpty() && !enemigos.isEmpty()) {
			for (Criatura jugador : jugadores) {
				if (!jugador.isVivo(jugador))
					continue;
				Criatura enemigo = enemigos.get(random.nextInt(enemigos.size()));
				int dano = jugador.atacar(enemigo);
				enemigo.recibirDanio(dano);

				if (!enemigo.isVivo(enemigo)) {
					enemigos.remove(enemigo);
				}
				if (enemigos.isEmpty())
					break;
			}

			for (Criatura enemigo : enemigos) {
				if (!enemigo.isVivo(enemigo))
					continue;
				Criatura jugador = jugadores.get(random.nextInt(jugadores.size()));
				int dano = enemigo.atacar(jugador);
				jugador.recibirDanio(dano);

				if (!jugador.isVivo(jugador)) {
					jugadores.remove(jugador);
				}
				if (jugadores.isEmpty())
					break;
			}
		}

		if (jugadores.isEmpty()) {
			System.out.println("¡Derrota!");
		} else {
			System.out.println("¡Victoria!");
		}
	}
	
	@Override
	public void luchar(Jugador jugador, Enemigo enemigo) {
		int dano = jugador.atacar(enemigo);
		enemigo.recibirDanio(dano);
	}
	
	@Override
	public boolean huir(Jugador jugador) {
		boolean exito = Math.random() < 0.5;
		return exito;
	}
	
	@Override
	public int calcularCritico(int base) {
		return random.nextDouble() < 0.1 ? base * 2 : base;
	}
}
