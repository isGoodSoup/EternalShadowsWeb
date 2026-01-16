package es.eternalshadow.main;

import java.util.ArrayList;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import es.eternalshadow.entities.Jugador;
import es.eternalshadow.service.interfaces.GameService;
import es.eternalshadow.service.interfaces.MenuService;
import es.eternalshadow.util.HibernateUtil;
import es.eternalshadow.util.UtilHub;

/**
 * Contexto principal del juego que gestiona todos los componentes.
 */
public class GameContext {
    private Terminal terminal;
    private LineReader reader;
    private UtilHub util;
    private Jugador jugadorActual;
    private List<Jugador> jugadores;
    private GameService gameService;
    private MenuService menuService;
    private boolean enPartida;
    private int capituloActual;
    
    public GameContext() {
        try {
            // Inicializar terminal
            this.terminal = TerminalBuilder.terminal();
            this.reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
            
            // Inicializar utilidades
            this.util = new UtilHub();
            
            // Inicializar colecciones
            this.jugadores = new ArrayList<>();
            this.enPartida = false;
            this.capituloActual = 1;
            
            // Configurar Hibernate
            HibernateUtil.setContext(this);
            
            System.out.println("GameContext inicializado correctamente.");
            
        } catch (Exception e) {
            System.err.println("Error al inicializar GameContext: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void iniciar() {
        try {
            // Verificar y crear admin si no existe
            HibernateUtil.comprobarRolAdmin();
            
            // Mostrar bienvenida
            util.getConsolePrinter().toGetString("ETERNAL SHADOW RPG");
            System.out.println("Bienvenido al mundo de Eternal Shadow!");
            System.out.println("=======================================");
            
            // TODO: Iniciar servicios
            // this.menuService.menuPrincipal(...);
            
        } catch (Exception e) {
            System.err.println("Error al iniciar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Getters y Setters
    public Terminal getTerminal() {
        return terminal;
    }
    
    public LineReader getReader() {
        return reader;
    }
    
    public UtilHub getUtil() {
        return util;
    }
    
    public Jugador getJugador() {
        return jugadorActual;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugadorActual = jugador;
    }
    
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
    public void addJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }
    
    public boolean isEnPartida() {
        return enPartida;
    }
    
    public void setEnPartida(boolean enPartida) {
        this.enPartida = enPartida;
    }
    
    public int getCapituloActual() {
        return capituloActual;
    }
    
    public void setCapituloActual(int capituloActual) {
        this.capituloActual = capituloActual;
    }
    
    public void siguienteCapitulo() {
        this.capituloActual++;
    }
    
    public GameService getGameService() {
        return gameService;
    }
    
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
    
    public MenuService getMenuService() {
        return menuService;
    }
    
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    
    /**
     * Cierra todos los recursos del juego.
     */
    public void shutdown() {
        try {
            if (reader != null) {
                // reader.getTerminal().close();
            }
            HibernateUtil.shutdown();
            System.out.println("Juego finalizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al cerrar el juego: " + e.getMessage());
        }
    }
}