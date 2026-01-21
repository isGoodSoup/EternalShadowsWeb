package es.eternalshadow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.CapitulosLoaderService;
import es.eternalshadow.story.Capitulo;

public class CapitulosLoaderServiceImpl implements CapitulosLoaderService {

    private final GameContext context;
    private final Map<Integer, Capitulo> capitulos = new HashMap<>();

    public CapitulosLoaderServiceImpl(GameContext context) {
        this.context = context;
        cargarCapitulos(null, null, null);
    }

    @Override
    public Capitulo cargarCapitulos(String rutaBase, List<Criatura> criaturas, Jugador jugador) {
		return null;
        // EJEMPLO BÁSICO (hardcodeado por ahora)
      
    }

    

    @Override
    public Capitulo getCapituloInicial() {
        return capitulos.get(1);
    }

	@Override
	public Capitulo getCapitulo(int numero) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

