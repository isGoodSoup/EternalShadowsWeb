package es.eternalshadow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.eternalshadow.entities.Criatura;
import es.eternalshadow.entities.Enemigo;
import es.eternalshadow.entities.Jugador;
import es.eternalshadow.exception.LimiteCombateException;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.CombateService;

public class CombateServiceImpl implements CombateService {
    private GameContext context;
    private Random random;
    private List<Criatura> jugadoresCombate;
    private List<Criatura> enemigosCombate;
    private boolean combateTerminado;
    private String ganador;
    private boolean esObligatorio;
    private int experienciaGanada;
    private List<String> botin;
    
    public CombateServiceImpl(GameContext context) {
        this.context = context;
        this.random = new Random();
        this.jugadoresCombate = new ArrayList<>();
        this.enemigosCombate = new ArrayList<>();
        this.combateTerminado = false;
        this.ganador = null;
        this.experienciaGanada = 0;
        this.botin = new ArrayList<>();
    }
    
    @Override
    public void iniciarCombate(List<Criatura> jugadores, List<Criatura> enemigos, boolean esObligatorio) 
            throws LimiteCombateException {
        
        if (jugadores == null || jugadores.isEmpty()) {
            throw new LimiteCombateException("No hay jugadores para el combate");
        }
        if (enemigos == null || enemigos.isEmpty()) {
            throw new LimiteCombateException("No hay enemigos para el combate");
        }
        
        this.jugadoresCombate = new ArrayList<>(jugadores);
        this.enemigosCombate = new ArrayList<>(enemigos);
        this.combateTerminado = false;
        this.ganador = null;
        this.esObligatorio = esObligatorio;
        this.experienciaGanada = 0;
        this.botin.clear();
        
        System.out.println("\n" + "⚔".repeat(25));
        System.out.println("¡COMBATE INICIADO!");
        System.out.println("Jugadores: " + jugadores.size() + " vs Enemigos: " + enemigos.size());
        System.out.println("⚔".repeat(25));
        
        // Calcular experiencia potencial
        for (Criatura enemigo : enemigos) {
            if (enemigo instanceof Enemigo) {
                experienciaGanada += ((Enemigo) enemigo).getExperienciaOtorgada();
            }
        }
        
        // Iniciar loop de combate
        ejecutarCombate();
    }
    
    @Override
    public void atacar(Jugador atacante, Enemigo objetivo) {
        if (combateTerminado) {
            System.out.println("El combate ya terminó");
            return;
        }
        
        int danio = calcularDanio(atacante, objetivo);
        objetivo.recibirDanio(danio);
        
        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + 
                          " causando " + danio + " de daño");
        
        verificarEstadoEnemigo(objetivo);
        verificarFinCombate();
    }
    
    @Override
    public boolean intentarHuir(Jugador jugador) {
        if (esObligatorio) {
            System.out.println("No puedes huir de este combate");
            return false;
        }
        
        double probabilidad = 0.5; // 50% base
        probabilidad += jugador.getVelocidad() * 0.01; // +1% por punto de velocidad
        
        boolean exito = random.nextDouble() < probabilidad;
        
        if (exito) {
            System.out.println(jugador.getNombre() + " huye del combate exitosamente");
            combateTerminado = true;
            ganador = "ENEMIGOS"; // Al huir, técnicamente pierdes
        } else {
            System.out.println(jugador.getNombre() + " falla al intentar huir");
        }
        
        return exito;
    }
    
    @Override
    public void usarHabilidad(Jugador jugador, String habilidad, Enemigo objetivo) {
        System.out.println(jugador.getNombre() + " usa la habilidad: " + habilidad);
        // TODO: Implementar sistema de habilidades
        System.out.println("(Sistema de habilidades no implementado aún)");
    }
    
    @Override
    public void usarItem(Jugador jugador, String item) {
        System.out.println(jugador.getNombre() + " usa: " + item);
        
        switch (item.toLowerCase()) {
            case "poción de salud":
            case "poción":
                int curacion = 30;
                jugador.setPuntosVida(jugador.getPuntosVida() + curacion);
                System.out.println(jugador.getNombre() + " recupera " + curacion + " PV");
                break;
            default:
                System.out.println("Item no reconocido");
        }
    }
    
    @Override
    public int calcularDañoCritico(int dañoBase) {
        double probabilidadCritico = 0.1; // 10% base
        boolean esCritico = random.nextDouble() < probabilidadCritico;
        
        if (esCritico) {
            System.out.println("¡GOLPE CRÍTICO!");
            return dañoBase * 2;
        }
        return dañoBase;
    }
    
    @Override
    public boolean isCombateTerminado() {
        return combateTerminado;
    }
    
    @Override
    public String getGanador() {
        return ganador;
    }
    
    @Override
    public int getExperienciaGanada() {
        return experienciaGanada;
    }
    
    @Override
    public List<String> getBotinObtenido() {
        return botin;
    }
    
    // ========== MÉTODOS PRIVADOS ==========
    
    private void ejecutarCombate() {
        System.out.println("\n--- COMBATE EN CURSO ---");
        
        while (!combateTerminado) {
            // Turno de jugadores
            for (Criatura criatura : jugadoresCombate) {
                if (criatura.isVivo() && criatura instanceof Jugador) {
                    mostrarOpcionesCombate((Jugador) criatura);
                }
            }
            
            // Verificar si terminó después del turno de jugadores
            verificarFinCombate();
            if (combateTerminado) break;
            
            // Turno de enemigos
            System.out.println("\n--- TURNO DE ENEMIGOS ---");
            for (Criatura enemigo : enemigosCombate) {
                if (enemigo.isVivo()) {
                    atacarEnemigo(enemigo);
                }
            }
            
            verificarFinCombate();
        }
        
        finalizarCombate();
    }
    
    private void mostrarOpcionesCombate(Jugador jugador) {
        System.out.println("\n--- TURNO DE " + jugador.getNombre().toUpperCase() + " ---");
        System.out.println("PV: " + jugador.getPuntosVida() + "/100");
        
        System.out.println("1) Atacar");
        System.out.println("2) Usar habilidad");
        System.out.println("3) Usar item");
        System.out.println("4) Intentar huir");
        
        String opcion = context.getReader().readLine("Elige una acción: ").trim();
        
        switch (opcion) {
            case "1":
                Enemigo objetivo = seleccionarObjetivo();
                if (objetivo != null) {
                    atacar(jugador, objetivo);
                }
                break;
            case "2":
                String habilidad = context.getReader().readLine("Nombre de la habilidad: ").trim();
                Enemigo objetivoHabilidad = seleccionarObjetivo();
                if (objetivoHabilidad != null) {
                    usarHabilidad(jugador, habilidad, objetivoHabilidad);
                }
                break;
            case "3":
                String item = context.getReader().readLine("Nombre del item: ").trim();
                usarItem(jugador, item);
                break;
            case "4":
                intentarHuir(jugador);
                break;
            default:
                System.out.println("Opción no válida, pierdes el turno");
        }
    }
    
    private Enemigo seleccionarObjetivo() {
        if (enemigosCombate.isEmpty()) {
            return null;
        }
        
        System.out.println("\nSelecciona un objetivo:");
        for (int i = 0; i < enemigosCombate.size(); i++) {
            Criatura enemigo = enemigosCombate.get(i);
            System.out.println((i + 1) + ") " + enemigo.getNombre() + 
                             " (PV: " + enemigo.getPuntosVida() + ")");
        }
        
        try {
            int seleccion = Integer.parseInt(context.getReader().readLine("Número: ").trim());
            if (seleccion > 0 && seleccion <= enemigosCombate.size()) {
                return (Enemigo) enemigosCombate.get(seleccion - 1);
            }
        } catch (NumberFormatException e) {
            // Selección aleatoria si no se especifica
        }
        
        // Selección aleatoria por defecto
        int index = random.nextInt(enemigosCombate.size());
        return (Enemigo) enemigosCombate.get(index);
    }
    
    private void atacarEnemigo(Criatura enemigo) {
        if (jugadoresCombate.isEmpty()) return;
        
        // Seleccionar jugador objetivo aleatorio
        List<Criatura> jugadoresVivos = jugadoresCombate.stream()
            .filter(Criatura::isVivo)
            .toList();
        
        if (jugadoresVivos.isEmpty()) return;
        
        Criatura objetivo = jugadoresVivos.get(random.nextInt(jugadoresVivos.size()));
        int danio = calcularDanio(enemigo, objetivo);
        objetivo.recibirDanio(danio);
        
        System.out.println(enemigo.getNombre() + " ataca a " + objetivo.getNombre() + 
                          " causando " + danio + " de daño");
        
        if (!objetivo.isVivo()) {
            System.out.println("¡" + objetivo.getNombre() + " ha sido derrotado!");
            jugadoresCombate.remove(objetivo);
        }
    }
    
    private int calcularDanio(Criatura atacante, Criatura objetivo) {
        int danioBase = atacante.getAtaque() - (objetivo.getDefensa() / 2);
        danioBase = Math.max(1, danioBase); // Mínimo 1 de daño
        return calcularDañoCritico(danioBase);
    }
    
    private void verificarEstadoEnemigo(Enemigo enemigo) {
        if (!enemigo.isVivo()) {
            System.out.println("¡" + enemigo.getNombre() + " ha sido derrotado!");
            enemigosCombate.remove(enemigo);
            
            // Añadir botín
            botin.add("Oro: " + (enemigo.getExperienciaOtorgada() / 2));
        }
    }
    
    private void verificarFinCombate() {
        boolean jugadoresVivos = jugadoresCombate.stream().anyMatch(Criatura::isVivo);
        boolean enemigosVivos = enemigosCombate.stream().anyMatch(Criatura::isVivo);
        
        if (!jugadoresVivos) {
            combateTerminado = true;
            ganador = "ENEMIGOS";
        } else if (!enemigosVivos) {
            combateTerminado = true;
            ganador = "JUGADORES";
        }
    }
    
    private void finalizarCombate() {
        System.out.println("\n" + "⚔".repeat(25));
        System.out.println("¡COMBATE TERMINADO!");
        System.out.println("Ganador: " + ganador);
        
        if (ganador.equals("JUGADORES")) {
            System.out.println("Experiencia ganada: " + experienciaGanada);
            System.out.println("Botín obtenido: " + botin);
            
            // Aplicar experiencia a jugadores vivos
            for (Criatura jugador : jugadoresCombate) {
                if (jugador.isVivo() && jugador instanceof Jugador) {
                    context.getServices().getJugadorService().aplicarExperiencia(
                        (Jugador) jugador, experienciaGanada / jugadoresCombate.size()
                    );
                }
            }
        }
        
        System.out.println("⚔".repeat(25));
    }
}