package es.eternalshadow.service;

import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.GameManagerService;

public class GameServiceImpl implements GameManagerService {
    private GameContext context;
    private boolean partidaEnCurso;
    private boolean partidaPausada;
    private String estadoJuego;
    private int tiempoJugado; // en segundos
    private long inicioPartida;
    
    public GameServiceImpl(GameContext context) {
        this.context = context;
        this.partidaEnCurso = false;
        this.partidaPausada = false;
        this.estadoJuego = "MENU";
        this.tiempoJugado = 0;
    }
    
    @Override
    public void nuevaPartida() {
        if (partidaEnCurso) {
            System.out.println("¡Ya hay una partida en curso!");
            return;
        }
        
        if (!context.getServices().getAuthService().isAutenticado()) {
            System.out.println("Debes iniciar sesión para jugar");
            return;
        }
        
        partidaEnCurso = true;
        partidaPausada = false;
        estadoJuego = "EN_JUEGO";
        inicioPartida = System.currentTimeMillis();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("¡NUEVA PARTIDA INICIADA!");
        System.out.println("=".repeat(50));
        
        // Crear personaje inicial
        System.out.println("\nCrea tu personaje:");
        String nombre = context.getUtil().getInputHandler().toScan(context.getReader(), "Nombre del héroe");
        String tipo = context.getUtil().getInputHandler().toScan(context.getReader(), "Clase (Guerrero/Mago/Arquero)");
        
        context.getServices().getJugadorService().crearJugador(nombre, tipo);
        
        System.out.println("\n¡Bienvenido a Eternal Shadow, " + nombre + "!");
        System.out.println("Tu aventura comienza ahora...");
        
        // Entrar en loop de juego básico
        iniciarLoopJuego();
    }
    
    @Override
    public void cargarPartida(int idPartida) {
        if (partidaEnCurso) {
            System.out.println("Termina la partida actual primero");
            return;
        }
        
        System.out.println("Cargando partida #" + idPartida + "...");
        // TODO: Implementar carga real desde base de datos
        System.out.println("(Función de carga no implementada aún)");
    }
    
    @Override
    public void guardarPartida() {
        if (!partidaEnCurso) {
            System.out.println("No hay partida para guardar");
            return;
        }
        
        System.out.println("Guardando partida...");
        // Guardar jugador principal
        if (context.getServices().getJugadorService().getJugadorPrincipal() != null) {
            context.getServices().getJugadorService().guardarJugador(
                context.getServices().getJugadorService().getJugadorPrincipal()
            );
        }
        
        System.out.println("✓ Partida guardada");
    }
    
    @Override
    public void continuarPartida() {
        if (!partidaEnCurso) {
            System.out.println("No hay partida en curso");
            return;
        }
        
        if (partidaPausada) {
            partidaPausada = false;
            estadoJuego = "EN_JUEGO";
            System.out.println("Partida reanudada");
            iniciarLoopJuego();
        } else {
            System.out.println("La partida ya está en curso");
        }
    }
    
    @Override
    public void terminarPartida() {
        if (!partidaEnCurso) {
            System.out.println("No hay partida en curso");
            return;
        }
        
        guardarPartida();
        
        partidaEnCurso = false;
        partidaPausada = false;
        estadoJuego = "MENU";
        
        long finPartida = System.currentTimeMillis();
        tiempoJugado = (int) ((finPartida - inicioPartida) / 1000);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PARTIDA TERMINADA");
        System.out.println("Tiempo jugado: " + tiempoJugado + " segundos");
        System.out.println("=".repeat(50));
    }
    
    @Override
    public void pausarPartida() {
        if (!partidaEnCurso || partidaPausada) {
            return;
        }
        
        partidaPausada = true;
        estadoJuego = "PAUSADO";
        System.out.println("Partida pausada");
    }
    
    @Override
    public void retomarPartida() {
        if (!partidaEnCurso || !partidaPausada) {
            return;
        }
        
        partidaPausada = false;
        estadoJuego = "EN_JUEGO";
        System.out.println("Partida reanudada");
    }
    
    @Override
    public String getEstadoJuego() {
        return estadoJuego;
    }
    
    @Override
    public boolean isPartidaEnCurso() {
        return partidaEnCurso;
    }
    
    @Override
    public int getProgresoPartida() {
        // Sistema simple: 1% por minuto jugado (máx 100%)
        int minutos = tiempoJugado / 60;
        return Math.min(minutos, 100);
    }
    
    @Override
    public int getTiempoJugado() {
        return tiempoJugado / 60; // Devuelve minutos
    }
    
    private void iniciarLoopJuego() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ZONA DE PRUEBA - Loop de juego básico");
        System.out.println("=".repeat(50));
        
        boolean enJuego = true;
        
        while (enJuego && partidaEnCurso && !partidaPausada) {
            System.out.println("\n--- Acciones disponibles ---");
            System.out.println("1) Explorar");
            System.out.println("2) Ver stats");
            System.out.println("3) Ver inventario");
            System.out.println("4) Guardar partida");
            System.out.println("5) Pausar");
            System.out.println("6) Terminar partida");
            System.out.println("----------------------------");
            
            String opcion = context.getReader().readLine("¿Qué quieres hacer? ").trim();
            
            switch (opcion) {
                case "1":
                    explorar();
                    break;
                case "2":
                    context.getServices().getGameUIService().mostrarStats();
                    break;
                case "3":
                    context.getServices().getGameUIService().mostrarMenuInventario();
                    break;
                case "4":
                    guardarPartida();
                    break;
                case "5":
                    pausarPartida();
                    context.getServices().getMenuService().mostrarMenuPausa();
                    break;
                case "6":
                    terminarPartida();
                    enJuego = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            
            // Actualizar tiempo jugado
            tiempoJugado = (int) ((System.currentTimeMillis() - inicioPartida) / 1000);
        }
    }
    
    private void explorar() {
        System.out.println("\nExplorando el área...");
        
        // Eventos aleatorios básicos
        double random = Math.random();
        
        if (random < 0.3) {
            System.out.println("Encontraste una poción de salud");
            context.getServices().getJugadorService().añadirItem(
                context.getServices().getJugadorService().getJugadorPrincipal(),
                "Poción de Salud",
                1
            );
        } else if (random < 0.6) {
            System.out.println("No encontraste nada interesante...");
        } else {
            System.out.println("¡Te topaste con un enemigo!");
            // TODO: Iniciar combate
            System.out.println("(Sistema de combate no implementado aún)");
        }
    }
}