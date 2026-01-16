package es.eternalshadow.util;

public class ExceptionsHandler {
    
    /**
     * Imprime información detallada de una excepción.
     * @param e Excepción a imprimir.
     */
    public static void printException(Exception e) {
        System.err.println("\n=== ERROR ===");
        System.err.println("Tipo: " + e.getClass().getSimpleName());
        System.err.println("Mensaje: " + e.getMessage());
        
        // Mostrar traza más relevante
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            // Buscar la primera línea de nuestro código
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().startsWith("es.eternalshadow")) {
                    System.err.println("Ubicación: " + element.getClassName() + 
                                     "." + element.getMethodName() + 
                                     "() línea " + element.getLineNumber());
                    break;
                }
            }
        }
        System.err.println("=============\n");
    }
    
    /**
     * Imprime un mensaje de error personalizado.
     * @param mensaje Mensaje de error.
     */
    public static void printError(String mensaje) {
        System.err.println("\n=== ERROR ===");
        System.err.println("Mensaje: " + mensaje);
        System.err.println("=============\n");
    }
    
    /**
     * Imprime un mensaje de advertencia.
     * @param mensaje Mensaje de advertencia.
     */
    public static void printWarning(String mensaje) {
        System.out.println("\n=== ADVERTENCIA ===");
        System.out.println("Mensaje: " + mensaje);
        System.out.println("===================\n");
    }
    
    /**
     * Maneja una excepción y pregunta al usuario qué hacer.
     * @param e Excepción a manejar.
     * @param reader Lector para entrada del usuario.
     * @return true si el usuario quiere continuar, false si quiere salir.
     */
    public static boolean handleExceptionWithRetry(Exception e, org.jline.reader.LineReader reader) {
        printException(e);
        
        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1) Reintentar");
        System.out.println("2) Continuar (ignorar error)");
        System.out.println("3) Salir del programa");
        
        while (true) {
            System.out.print("Selección: ");
            String input = reader.readLine().trim();
            
            switch (input) {
                case "1":
                    return true; // Reintentar
                case "2":
                    System.out.println("Continuando...");
                    return false; // Continuar
                case "3":
                    System.out.println("Saliendo del programa...");
                    System.exit(1);
                    return false;
                default:
                    System.out.println("Opción inválida. Elige 1, 2 o 3.");
            }
        }
    }
}