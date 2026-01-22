package es.eternalshadow.service;

import java.util.Random;

import es.eternalshadow.entities.Jugador;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.GameManagerService;
import es.eternalshadow.util.InputHandler;

public class GameManagerServiceImpl implements GameManagerService {
    private GameContext context;
    private boolean partidaEnCurso;
    private boolean partidaPausada;
    private String estadoJuego;
    private int tiempoJugado; // en segundos
    private long inicioPartida;
    private Random random;
    
    public GameManagerServiceImpl(GameContext context) {
        this.context = context;
        this.partidaEnCurso = false;
        this.partidaPausada = false;
        this.estadoJuego = "MENU";
        this.tiempoJugado = 0;
        this.random = new Random();
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
        tiempoJugado = 0;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("¡NUEVA PARTIDA INICIADA!");
        System.out.println("=".repeat(50));
        
        // Crear personaje inicial
        crearPersonajeInicial();
        
        System.out.println("\n¡Bienvenido a Eternal Shadow, " + 
                          context.getServices().getJugadorService().getJugadorPrincipal().getNombre() + "!");
        System.out.println("Tu aventura comienza ahora...");
        
        //To do: Introducir historia inicial aquí
        
        
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
        
        // Actualizar tiempo jugado
        tiempoJugado = (int) ((System.currentTimeMillis() - inicioPartida) / 1000);
        
        // Guardar jugador principal
        if (context.getServices().getJugadorService().getJugadorPrincipal() != null) {
            context.getServices().getJugadorService().guardarJugador(
                context.getServices().getJugadorService().getJugadorPrincipal()
            );
        }
        
        System.out.println("✓ Partida guardada");
        System.out.println("Tiempo jugado: " + getTiempoJugado() + " minutos");
        System.out.println("Progreso: " + getProgresoPartida() + "%");
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
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PARTIDA TERMINADA");
        System.out.println("=".repeat(50));
        
        // Resetear jugador para nueva partida
        context.getServices().getJugadorService().setJugadorPrincipal(null);
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
        // Sistema simple: 1% por 30 segundos jugados (máx 100%)
        int progreso = tiempoJugado / 30;
        return Math.min(progreso, 100);
    }
    
    @Override
    public int getTiempoJugado() {
        return tiempoJugado / 60; // Devuelve minutos
    }
    
    // ========== MÉTODOS PRIVADOS ==========
    
    private void crearPersonajeInicial() {
        System.out.println("\nCrea tu personaje:");
        String nombre = InputHandler.toScan(context.getReader(), "Nombre del héroe");
        
        System.out.println("\nElige tu clase:");
        System.out.println("1) Guerrero (Fuerza +5, Defensa +3)");
        System.out.println("2) Mago (Magia +5, Ataque +3)");
        System.out.println("3) Arquero (Velocidad +5, Ataque +3)");
        
        String tipo = "";
        int opcion = InputHandler.toScanInteger(context.getReader(), "Opción (1-3)");
        
        switch (opcion) {
            case 1:
                tipo = "Guerrero";
                break;
            case 2:
                tipo = "Mago";
                break;
            case 3:
                tipo = "Arquero";
                break;
            default:
                tipo = "Aventurero";
        }
        
        context.getServices().getJugadorService().crearJugador(nombre, tipo);
    }
    
    private void iniciarLoopJuego() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ZONA DE PRUEBA - Loop de juego básico");
        System.out.println("Usa 'menu' para volver al menú principal");
        System.out.println("=".repeat(50));
        
        boolean enJuego = true;
        
        while (enJuego && partidaEnCurso && !partidaPausada) {
            System.out.println("\n--- Acciones disponibles ---");
            System.out.println("1) Explorar el área");
            System.out.println("2) Ver mis estadísticas");
            System.out.println("3) Ver mi inventario");
            System.out.println("4) Guardar partida");
            System.out.println("5) Pausar partida");
            System.out.println("6) Terminar partida y volver al menú");
            System.out.println("----------------------------");
            
            String opcion = context.getReader().readLine("¿Qué quieres hacer? ").trim().toLowerCase();
            
            switch (opcion) {
                case "1":
                case "explorar":
                    explorar();
                    break;
                case "2":
                case "stats":
                case "estadisticas":
                    context.getServices().getGameUIService().mostrarStats();
                    break;
                case "3":
                case "inv":
                case "inventario":
                    context.getServices().getGameUIService().mostrarMenuInventario();
                    break;
                case "4":
                case "guardar":
                    guardarPartida();
                    break;
                case "5":
                case "pausa":
                case "pausar":
                    pausarPartida();
                    context.getServices().getMenuService().mostrarMenuPausa();
                    break;
                case "6":
                case "salir":
                case "terminar":
                case "menu":
                    terminarPartida();
                    enJuego = false;
                    break;
                default:
                    System.out.println("Opción no válida. Usa 1-6 o comandos como 'explorar', 'stats', etc.");
            }
            
            // Actualizar tiempo jugado
            if (partidaEnCurso) {
                tiempoJugado = (int) ((System.currentTimeMillis() - inicioPartida) / 1000);
            }
        }
    }
    
    private void explorar() {
        System.out.println("\nExplorando el área...");
        
        // Eventos aleatorios
        int evento = random.nextInt(100);
        
        if (evento < 30) {
            System.out.println("Encontraste una poción de salud en el camino");
            context.getServices().getJugadorService().añadirItem(
                context.getServices().getJugadorService().getJugadorPrincipal(),
                "Poción de Salud",
                1
            );
            System.out.println("✓ +1 Poción de Salud");
            
        } else if (evento < 60) {
            System.out.println("No encontraste nada interesante...");
            
        } else if (evento < 90) {
            System.out.println("¡Te topaste con un enemigo!");
            iniciarCombateAleatorio();
            
        } else {
            System.out.println("¡Encontraste un cofre del tesoro!");
            System.out.println("Contenía 50 monedas de oro");
            context.getServices().getJugadorService().añadirItem(
                context.getServices().getJugadorService().getJugadorPrincipal(),
                "Monedas de Oro",
                50
            );
        }
    }
    
    private void iniciarCombateAleatorio() {
        System.out.println("Preparándose para el combate...");
        
        // Crear enemigo aleatorio
        String[] enemigos = {"Goblin", "Orco", "Esqueleto", "Araña Gigante", "Lobo Salvaje"};
        String nombreEnemigo = enemigos[random.nextInt(enemigos.length)];
        
        // Stats del enemigo basados en el nivel del jugador
        Jugador jugador = context.getServices().getJugadorService().getJugadorPrincipal();
        int nivelJugador = jugador != null ? jugador.getNivel() : 1;
        
        System.out.println("Te enfrentas a: " + nombreEnemigo);
        
        // Preguntar al jugador qué hacer
        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1) Atacar");
        System.out.println("2) Intentar huir");
        System.out.println("3) Usar item");
        
        String opcion = context.getReader().readLine("Opción: ").trim();
        
        switch (opcion) {
            case "1":
                System.out.println("Atacas al " + nombreEnemigo);
                int danio = random.nextInt(15) + 5;
                System.out.println("Infliges " + danio + " puntos de daño");
                
                // Posible contraataque
                if (random.nextBoolean()) {
                    int danioRecibido = random.nextInt(10) + 3;
                    System.out.println("El " + nombreEnemigo + " te contraataca y recibes " + 
                                     danioRecibido + " puntos de daño");
                    
                    if (jugador != null) {
                        int nuevaVida = jugador.getPuntosVida() - danioRecibido;
                        jugador.setPuntosVida(Math.max(0, nuevaVida));
                        
                        if (jugador.getPuntosVida() <= 0) {
                            System.out.println("¡Has sido derrotado!");
                            terminarPartida();
                        }
                    }
                } else {
                    System.out.println("¡Has derrotado al " + nombreEnemigo + "!");
                    System.out.println("Ganas 25 puntos de experiencia");
                    
                    if (jugador != null) {
                        context.getServices().getJugadorService().aplicarExperiencia(jugador, 25);
                    }
                }
                break;
                
            case "2":
                System.out.println("Intentas huir...");
                if (random.nextDouble() < 0.7) { // 70% de éxito
                    System.out.println("¡Logras huir exitosamente!");
                } else {
                    System.out.println("¡Fallaste al huir! El enemigo te ataca");
                    int danioHuir = random.nextInt(12) + 5;
                    System.out.println("Recibes " + danioHuir + " puntos de daño");
                    
                    if (jugador != null) {
                        int nuevaVida = jugador.getPuntosVida() - danioHuir;
                        jugador.setPuntosVida(Math.max(0, nuevaVida));
                    }
                }
                break;
                
            case "3":
                System.out.println("(Sistema de items en combate no implementado aún)");
                System.out.println("Pierdes tu turno...");
                break;
                
            default:
                System.out.println("Opción no válida, pierdes tu turno");
        }
        
        System.out.println("\nContinúas tu camino...");
    }
}