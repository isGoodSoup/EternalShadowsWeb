package es.eternalshadow.service;

import es.eternalshadow.main.GameContext;
import es.eternalshadow.service.interfaces.MenuService;
import es.eternalshadow.util.InputHandler;

public class MenuServiceImpl implements MenuService {
    private GameContext context;
    private boolean enMenuPrincipal;
    
    public MenuServiceImpl(GameContext context) {
        this.context = context;
        this.enMenuPrincipal = true;
    }
    
    @Override
    public void mostrarMenuPrincipal() {
        enMenuPrincipal = true;
        
        while (enMenuPrincipal) {
            mostrarPantalla("MENÚ PRINCIPAL");
            
            System.out.println("1) Nueva partida");
            System.out.println("2) Cargar partida");
            System.out.println("3) Opciones");
            System.out.println("4) Ayuda");
            System.out.println("5) Salir");
            System.out.println("====================");
            
            if (context.getServices().getAuthService().isAutenticado()) {
                System.out.println("Usuario: " + context.getServices().getAuthService().getUsuarioActual().getUsername());
            } else {
                System.out.println("No autenticado - [L]ogin | [R]egistro");
            }
            
            String opcion = context.getReader().readLine("Opción: ").trim();
            
            procesarOpcionPrincipal(opcion);
        }
    }
    
    @Override
    public void mostrarMenuPausa() {
        mostrarPantalla("JUEGO EN PAUSA");
        
        System.out.println("1) Reanudar juego");
        System.out.println("2) Guardar partida");
        System.out.println("3) Cargar partida");
        System.out.println("4) Opciones");
        System.out.println("5) Volver al menú principal");
        System.out.println("====================");
        
        String opcion = context.getReader().readLine("Opción: ").trim();
        procesarOpcionPausa(opcion);
    }
    
    @Override
    public boolean procesarOpcion(int opcion) {
        // Método genérico para procesar opciones numéricas
        try {
            int numOpcion = Integer.parseInt(String.valueOf(opcion));
            return procesarOpcionNumerica(numOpcion);
        } catch (NumberFormatException e) {
            return procesarOpcionTexto(String.valueOf(opcion));
        }
    }
    
    private boolean procesarOpcionNumerica(int opcion) {
        switch (opcion) {
            case 1: // Nueva partida
                context.getServices().getGameManagerService().nuevaPartida();
                return false; // Sale del menú
            case 5: // Salir
                enMenuPrincipal = false;
                return true; // Vuelve
            default:
                System.out.println("Opción no implementada aún");
                return false;
        }
    }
    
    private boolean procesarOpcionTexto(String opcion) {
        opcion = opcion.toLowerCase();
        
        switch (opcion) {
            case "l":
            case "login":
                gestionarLogin();
                return false;
            case "r":
            case "registro":
                gestionarRegistro();
                return false;
            case "q":
            case "quit":
            case "exit":
                enMenuPrincipal = false;
                return true;
            default:
                System.out.println("Opción no válida");
                return false;
        }
    }
    
    @Override
    public void mostrarPantalla(String titulo) {
        System.out.println("\n".repeat(3));
        System.out.println("=".repeat(50));
        System.out.println(" ".repeat((50 - titulo.length()) / 2) + titulo);
        System.out.println("=".repeat(50));
        System.out.println();
    }
    
    private void procesarOpcionPrincipal(String opcion) {
        if (opcion.equalsIgnoreCase("l") || opcion.equalsIgnoreCase("login")) {
            gestionarLogin();
        } else if (opcion.equalsIgnoreCase("r") || opcion.equalsIgnoreCase("registro")) {
            gestionarRegistro();
        } else {
            try {
                int numOpcion = Integer.parseInt(opcion);
                switch (numOpcion) {
                    case 1:
                        context.getServices().getGameManagerService().nuevaPartida();
                        enMenuPrincipal = false;
                        break;
                    case 2:
                        context.getServices().getGameManagerService().cargarPartida(1); // ID dummy
                        break;
                    case 3:
                        context.getServices().getGameUIService().mostrarMenuOpciones();
                        break;
                    case 4:
                        context.getServices().getGameUIService().mostrarAyuda();
                        break;
                    case 5:
                        System.out.println("¡Hasta pronto!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida");
            }
        }
    }
    
    private void procesarOpcionPausa(String opcion) {
        try {
            int numOpcion = Integer.parseInt(opcion);
            switch (numOpcion) {
                case 1:
                    System.out.println("Reanudando juego...");
                    break;
                case 2:
                    context.getServices().getGameManagerService().guardarPartida();
                    break;
                case 3:
                    context.getServices().getGameManagerService().cargarPartida(1);
                    break;
                case 4:
                    context.getServices().getGameUIService().mostrarMenuOpciones();
                    break;
                case 5:
                    enMenuPrincipal = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida");
        }
    }
    
    private void gestionarLogin() {
        if (context.getServices().getAuthService().isAutenticado()) {
            System.out.println("Ya hay una sesión activa");
            return;
        }
        
        try {
            String username = InputHandler.toScan(context.getReader(), "Usuario o Email");
            String password = InputHandler.toScan(context.getReader(), "Contraseña");
            
            context.getServices().getAuthService().login(username, password);
        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
        }
    }
    
    private void gestionarRegistro() {
        try {
            String username = InputHandler.toScan(context.getReader(), "Nombre de usuario");
            String email = InputHandler.toScan(context.getReader(), "Email");
            String password = InputHandler.toScan(context.getReader(), "Contraseña");
            
            context.getServices().getAuthService().registrar(username, email, password);
        } catch (Exception e) {
            System.out.println("Error en registro: " + e.getMessage());
        }
    }
}