package es.eternalshadow.service;

import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.AuthService;
import es.eternalshadow.service.interfaces.CapitulosLoaderService;
import es.eternalshadow.service.interfaces.CombateService;
import es.eternalshadow.service.interfaces.GameManagerService;
import es.eternalshadow.service.interfaces.GameUIService;
import es.eternalshadow.service.interfaces.JugadorService;
import es.eternalshadow.service.interfaces.MenuService;

/**
 * Factory que centraliza la creación y acceso a todos los servicios del juego.
 * Patrón Singleton para asegurar una única instancia de cada servicio.
 */
public class ServiceFactory {
    private final GameContext context;
    
    // Instancias únicas de cada servicio (lazy initialization)
    private AuthService authService;
    private JugadorService jugadorService;
    private MenuService menuService;
    private GameManagerService gameManagerService;
    private CombateService combateService;
    private GameUIService gameUIService;
    private CapitulosLoaderService capitulosLoaderService;
    
    /**
     * Constructor principal.
     * @param context Contexto del juego.
     */
    public ServiceFactory(GameContext context) {
        this.context = context;
        // Los servicios se inicializan bajo demanda (lazy)
    }
    
    /**
     * Obtiene el servicio de autenticación.
     * @return Instancia de AuthService.
     */
    public AuthService getAuthService() {
        if (authService == null) {
            authService = new AuthServiceImpl(context);
        }
        return authService;
    }
    
    /**
     * Obtiene el servicio de gestión de jugadores.
     * @return Instancia de JugadorService.
     */
    public JugadorService getJugadorService() {
        if (jugadorService == null) {
            jugadorService = new JugadorServiceImpl(context);
        }
        return jugadorService;
    }
    
    /**
     * Obtiene el servicio de menús principales.
     * @return Instancia de MenuService.
     */
    public MenuService getMenuService() {
        if (menuService == null) {
            menuService = new MenuServiceImpl(context);
        }
        return menuService;
    }
    
    /**
     * Obtiene el servicio de gestión del juego.
     * @return Instancia de GameManagerService.
     */
    public GameManagerService getGameManagerService() {
        if (gameManagerService == null) {
            gameManagerService = new GameManagerServiceImpl(context);
        }
        return gameManagerService;
    }
    
    /**
     * Obtiene el servicio de combate.
     * @return Instancia de CombateService.
     */
    public CombateService getCombateService() {
        if (combateService == null) {
            combateService = new CombateServiceImpl(context);
        }
        return combateService;
    }
    
    /**
     * Obtiene el servicio de interfaces de usuario del juego.
     * @return Instancia de GameUIService.
     */
    public GameUIService getGameUIService() {
        if (gameUIService == null) {
            gameUIService = new GameUIServiceImpl(context);
        }
        return gameUIService;
    }
    
    /**
     * Obtiene el contexto del juego.
     * @return Contexto actual.
     */
    public GameContext getContext() {
        return context;
    }
    
    
    //obtiene el capitulo loader service
    public CapitulosLoaderService getCapitulosLoaderService() {
        if (capitulosLoaderService == null) {
            capitulosLoaderService = new CapitulosLoaderServiceImpl(context);
        }
        return capitulosLoaderService;
    }

    /**
     * Inicializa todos los servicios de una vez (para pre-carga).
     */
    public void initializeAllServices() {
        // Forzar la inicialización de todos los servicios
        getAuthService();
        getJugadorService();
        getMenuService();
        getGameManagerService();
        getCombateService();
        getGameUIService();
        System.out.println("✓ Todos los servicios inicializados");
    }
    
    /**
     * Reinicia todos los servicios (útil para nueva partida).
     */
    public void resetServices() {
        authService = null;
        jugadorService = null;
        menuService = null;
        gameManagerService = null;
        combateService = null;
        gameUIService = null;
        System.out.println("✓ Servicios reiniciados");
    }
    
    /**
     * Obtiene información del estado de los servicios.
     * @return String con el estado de cada servicio.
     */
    public String getServicesStatus() {
        StringBuilder status = new StringBuilder();
        status.append("=== ESTADO DE SERVICIOS ===\n");
        status.append("AuthService: ").append(authService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("JugadorService: ").append(jugadorService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("MenuService: ").append(menuService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("GameManagerService: ").append(gameManagerService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("CombateService: ").append(combateService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("GameUIService: ").append(gameUIService != null ? "✓ Activo" : "✗ Inactivo").append("\n");
        status.append("===========================");
        return status.toString();
    }
    
    /**
     * Cierra y libera recursos de todos los servicios.
     */
    public void shutdown() {
        System.out.println("Cerrando servicios...");
        
        // Cerrar sesión de autenticación
        if (authService != null && authService.isAutenticado()) {
            authService.logout();
        }
        
        // Guardar partida si está en curso
        if (gameManagerService != null && gameManagerService.isPartidaEnCurso()) {
            gameManagerService.guardarPartida();
        }
        
        // Limpiar referencias
        authService = null;
        jugadorService = null;
        menuService = null;
        gameManagerService = null;
        combateService = null;
        gameUIService = null;
        
        System.out.println("✓ Servicios cerrados correctamente");
    }

	
}