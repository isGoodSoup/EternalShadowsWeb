package es.eternalshadow.service;

import es.eternalshadow.main.GameContext;

public class ServiceFactory {
	private GameContext context;
	private final UsuarioServiceImpl userService;
	private final JugadorServiceImpl jugadorService;
    private final MenuServiceImpl menuService;
    private final EULAServiceImpl eulaService;
    private final GameServiceImpl gameService;
    private final CombateServiceImpl combateService;
    private CapitulosLoaderImpl capitulosLoader;
	
    public ServiceFactory() {
		super();
		this.userService = new UsuarioServiceImpl(null);
        this.menuService = new MenuServiceImpl(null);
        this.eulaService = new EULAServiceImpl("docs/eula.txt");
        this.gameService = new GameServiceImpl(null);
        this.combateService = new CombateServiceImpl(null);
		this.capitulosLoader = new CapitulosLoaderImpl(null);
		this.jugadorService = new JugadorServiceImpl(null);
	}
    
    public void init(GameContext context) {
        this.context = context;	
        this.userService.setContext(context);
        this.jugadorService.setContext(context);
        this.menuService.setContext(context);
        this.gameService.setContext(context);
        this.combateService.setContext(context);
        this.capitulosLoader = new CapitulosLoaderImpl(context);
    }
    
    public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}
    
    public UsuarioServiceImpl getUserService() {
        return userService;
    }
    
    public JugadorServiceImpl getJugadorService() {
		return jugadorService;
	}

	public MenuServiceImpl getMenuService() {
        return menuService;
    }

    public EULAServiceImpl getEulaService() {
        return eulaService;
    }

    public GameServiceImpl getGameService() {
        return gameService;
    }
    
	public CombateServiceImpl getCombateService() {
		return combateService;
	}
	
	public CapitulosLoaderImpl getCapitulosLoader() {
		return capitulosLoader;
	}

	public void setCapitulosLoader(CapitulosLoaderImpl capitulosLoader) {
		this.capitulosLoader = capitulosLoader;
	}
}
