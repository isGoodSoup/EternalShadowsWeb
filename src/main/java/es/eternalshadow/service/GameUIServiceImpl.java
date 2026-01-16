package es.eternalshadow.service;

import es.eternalshadow.entities.Jugador;
import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.GameUIService;

public class GameUIServiceImpl implements GameUIService {
    private GameContext context;
    
    public GameUIServiceImpl(GameContext context) {
        this.context = context;
    }
    
    @Override
    public void mostrarMenuPersonaje() {
        Jugador jugador = context.getServices().getJugadorService().getJugadorPrincipal();
        
        if (jugador == null) {
            System.out.println("No hay jugador activo");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ESTADÍSTICAS DE PERSONAJE");
        System.out.println("=".repeat(50));
        
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Clase: " + jugador.getTipo());
        System.out.println("Nivel: " + jugador.getNivel());
        System.out.println("\n--- ATRIBUTOS ---");
        System.out.println("Fuerza: " + jugador.getFuerza());
        System.out.println("Resistencia: " + jugador.getResistencia());
        System.out.println("Velocidad: " + jugador.getVelocidad());
        System.out.println("Magia: " + jugador.getMagia());
        System.out.println("\n--- COMBATE ---");
        System.out.println("PV: " + jugador.getPuntosVida() + "/100");
        System.out.println("Moral: " + jugador.getMoral());
        System.out.println("Ataque: " + jugador.getAtaque());
        System.out.println("Defensa: " + jugador.getDefensa());
        System.out.println("=".repeat(50));
        
        context.getReader().readLine("\nPresiona Enter para continuar...");
    }
    
    @Override
    public void mostrarMenuInventario() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("INVENTARIO");
        System.out.println("=".repeat(50));
        
        Jugador jugador = context.getServices().getJugadorService().getJugadorPrincipal();
        if (jugador != null && jugador.getInventario() != null && !jugador.getInventario().isEmpty()) {
            jugador.getInventario().forEach((nombre, item) -> {
                System.out.println("- " + item.getCantidad() + "x " + nombre);
            });
        } else {
            System.out.println("El inventario está vacío");
        }
        
        System.out.println("=".repeat(50));
        
        System.out.println("\n1) Usar item");
        System.out.println("2) Descartar item");
        System.out.println("3) Volver");
        
        String opcion = context.getReader().readLine("Opción: ").trim();
        
        switch (opcion) {
            case "1":
                System.out.println("(Función usar item no implementada)");
                break;
            case "2":
                System.out.println("(Función descartar item no implementada)");
                break;
            default:
                // Volver
        }
    }
    
    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("HABILIDADES");
        System.out.println("=".repeat(50));
        System.out.println("(Sistema de habilidades no implementado)");
        System.out.println("=".repeat(50));
        context.getReader().readLine("\nPresiona Enter para continuar...");
    }
    
    @Override
    public void mostrarMenuOpciones() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("OPCIONES");
        System.out.println("=".repeat(50));
        
        System.out.println("1) Cambiar contraseña");
        System.out.println("2) Configuración gráfica");
        System.out.println("3) Configuración de sonido");
        System.out.println("4) Controles");
        System.out.println("5) Volver");
        
        String opcion = context.getReader().readLine("Opción: ").trim();
        
        switch (opcion) {
            case "1":
                cambiarContraseña();
                break;
            case "2":
                System.out.println("(Configuración gráfica no implementada)");
                break;
            case "3":
                System.out.println("(Configuración de sonido no implementada)");
                break;
            case "4":
                System.out.println("(Configuración de controles no implementada)");
                break;
            default:
                // Volver
        }
    }
    
    @Override
    public void mostrarStats() {
        mostrarMenuPersonaje(); // Reutiliza el mismo método
    }
    
    @Override
    public void mostrarMapa() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MAPA DEL MUNDO");
        System.out.println("=".repeat(50));
        
        // Mapa ASCII simple
        String[] mapa = {
            "┌─────────────┐",
            "│  Bosque     │",
            "│   🏠        │",
            "│     🐺      │",
            "│  🧙  🏰     │",
            "└─────────────┘"
        };
        
        for (String linea : mapa) {
            System.out.println(linea);
        }
        
        System.out.println("\nLeyenda:");
        System.out.println("🏠 - Tu posición");
        System.out.println("🏰 - Castillo");
        System.out.println("🧙 - Pueblo");
        System.out.println("🐺 - Zona peligrosa");
        System.out.println("=".repeat(50));
        
        context.getReader().readLine("\nPresiona Enter para continuar...");
    }
    
    @Override
    public void mostrarDiarioMisiones() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DIARIO DE MISIONES");
        System.out.println("=".repeat(50));
        System.out.println("1) [ ] Derrotar al jefe del bosque");
        System.out.println("2) [ ] Encontrar el artefacto perdido");
        System.out.println("3) [X] Hablar con el anciano del pueblo");
        System.out.println("=".repeat(50));
        context.getReader().readLine("\nPresiona Enter para continuar...");
    }
    
    @Override
    public void mostrarAyuda() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("AYUDA - CONTROLES BÁSICOS");
        System.out.println("=".repeat(50));
        System.out.println("• Menú principal: Navegación con números");
        System.out.println("• En combate: 1-Atacar, 2-Habilidad, 3-Item, 4-Huir");
        System.out.println("• Exploración: Siguientes pasos por implementar");
        System.out.println("• Guardar partida: Opción en menú de pausa");
        System.out.println("\nCOMANDOS ESPECIALES:");
        System.out.println("• 'help' - Muestra esta ayuda");
        System.out.println("• 'exit' - Sale del juego");
        System.out.println("• 'stats' - Muestra estadísticas");
        System.out.println("• 'inv' - Muestra inventario");
        System.out.println("=".repeat(50));
        context.getReader().readLine("\nPresiona Enter para continuar...");
    }
    
    private void cambiarContraseña() {
        if (!context.getServices().getAuthService().isAutenticado()) {
            System.out.println("Debes estar autenticado para cambiar la contraseña");
            return;
        }
        
        try {
            String actual = context.getReader().readLine("Contraseña actual: ").trim();
            String nueva = context.getReader().readLine("Nueva contraseña: ").trim();
            String confirmar = context.getReader().readLine("Confirmar nueva contraseña: ").trim();
            
            if (!nueva.equals(confirmar)) {
                System.out.println("Las contraseñas no coinciden");
                return;
            }
            
            context.getServices().getAuthService().cambiarPassword(actual, nueva);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}