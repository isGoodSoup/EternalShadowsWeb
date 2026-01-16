package es.eternalshadow.main;

import java.util.ArrayList;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.service.ServiceFactory;
import es.eternalshadow.util.HibernateUtil;
import es.eternalshadow.util.UtilHub;

public class GameContext {
    private Terminal terminal;
    private LineReader reader;
    private UtilHub util;
    private Jugador jugadorActual;
    private List<Criatura> criaturas;
    private ServiceFactory services;
    
    public GameContext() {
        try {
            // Inicializar terminal
            this.terminal = TerminalBuilder.terminal();
            this.reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
            
            // Inicializar utilidades
            this.util = new UtilHub();
            this.util.initializeCharacterFactory(this);
            
            // Inicializar colecciones
            this.criaturas = new ArrayList<>();
            this.jugadorActual = null;
            
            // Inicializar servicios
            this.services = new ServiceFactory(this);
            
            // Configurar Hibernate
            HibernateUtil.setContext(this);
            
            System.out.println("✓ GameContext inicializado");
            
        } catch (Exception e) {
            System.err.println("✗ Error al inicializar GameContext: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void iniciar() {
        try {
            // Verificar y crear admin si no existe
            HibernateUtil.comprobarRolAdmin();
            
            // Mostrar bienvenida
            System.out.println("\n" + "=".repeat(50));
            System.out.println("  ETERNAL SHADOW RPG - v1.0");
            System.out.println("=".repeat(50));
            
            // Mostrar menú principal
            services.getMenuService().mostrarMenuPrincipal();
            
        } catch (Exception e) {
            System.err.println("✗ Error al iniciar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Getters
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
    
    public List<Criatura> getCriaturas() { 
        return criaturas; 
    }
    
    public ServiceFactory getServices() { 
        return services; 
    }
    
    public void shutdown() {
        try {
            // Guardar partida si está en curso
            if (services.getGameManagerService().isPartidaEnCurso()) {
                services.getGameManagerService().guardarPartida();
            }
            
            // Cerrar sesión si está autenticado
            if (services.getAuthService().isAutenticado()) {
                services.getAuthService().logout();
            }
            
            // Cerrar Hibernate
            HibernateUtil.shutdown();
            
            System.out.println("\n✓ Juego finalizado correctamente.");
        } catch (Exception e) {
            System.err.println("✗ Error al cerrar el juego: " + e.getMessage());
        }
    }
}