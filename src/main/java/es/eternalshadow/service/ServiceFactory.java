package es.eternalshadow.service;

import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.*;

public class ServiceFactory {
    private GameContext context;
    private AuthServiceImpl authService;
    private JugadorServiceImpl jugadorService;
    private MenuServiceImpl menuService;
    private GameManagerServiceImpl gameManagerService;
    private CombateServiceImpl combateService;
    private GameUIServiceImpl gameUIService;
    
    public ServiceFactory(GameContext context) {
        this.context = context;
        initializeServices();
    }
    
    private void initializeServices() {
        this.authService = new AuthServiceImpl(context);
        this.jugadorService = new JugadorServiceImpl(context);
        this.menuService = new MenuServiceImpl(context);
        this.gameManagerService = new GameManagerServiceImpl(context);
        this.combateService = new CombateServiceImpl(context);
        this.gameUIService = new GameUIServiceImpl(context);
    }
    
    // Getters para cada servicio
    public AuthService getAuthService() { return authService; }
    public JugadorService getJugadorService() { return jugadorService; }
    public MenuService getMenuService() { return menuService; }
    public GameManagerService getGameManagerService() { return gameManagerService; }
    public CombateService getCombateService() { return combateService; }
    public GameUIService getGameUIService() { return gameUIService; }
    
    public GameContext getContext() { return context; }
}