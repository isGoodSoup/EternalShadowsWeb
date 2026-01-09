package es.eternalshadow.service;

import es.eternalshadow.exception.GameException;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.GameService;
import es.eternalshadow.util.ExceptionsHandler;
import es.eternalshadow.util.InputHandler;

public class GameServiceImpl implements GameService {
	private GameContext context;

	public GameServiceImpl(GameContext context) {
		this.context = context;
	}
	
	public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}
	
	@Override
	public void iniciarPartida() {
		context.getCriaturas().clear();
		int tope;
		boolean isTope;

		do {
			tope = InputHandler.toScanInteger(context.getReader(),
					"Inserta el número de jugadores (máx 5)");
			isTope = tope < 2 || tope > 5;
			if (isTope) {
				try {
					throw new GameException(
							"Número de jugadores inválido: " + tope);
				} catch (GameException e) {
					ExceptionsHandler.printException(e);
				}
			}
		} while (isTope);

		for (int i = 1; i <= tope; i++) {
			try {
				context.setJugador(
						context.getUtil().getCharacterFactory().crearPersonaje(context.getReader()));
			} catch (LimiteCombateException e) {
				ExceptionsHandler.printException(e);
			}
			context.getCriaturas().add(context.getJugador());
		}
		context.getHistoria().iniciar(context.getCriaturas(),
				context.getReader(), context.getUtil());
	}
}
