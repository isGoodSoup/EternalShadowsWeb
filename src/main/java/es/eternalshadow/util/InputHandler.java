package es.eternalshadow.util;

import org.jline.reader.LineReader;

public class InputHandler {
    
    /**
     * Muestra un menú y obtiene la opción seleccionada por el usuario.
     * @param reader Lector de líneas para recibir la entrada del usuario.
     * @param menu   Opciones del menú como arreglo de strings.
     * @param s      Mensaje para solicitar la opción.
     * @return La opción seleccionada como entero.
     */
    public int crearMenu(LineReader reader, String[] menu, String s) {
        System.out.println("\n" + s + ":");
        System.out.println("====================");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ") " + menu[i]);
        }
        System.out.println("====================");
        
        int num = toScanInteger(reader, "Selecciona una opción (1-" + menu.length + ")");
        
        // Validar rango
        while (num < 1 || num > menu.length) {
            System.out.println("Opción inválida. Por favor, selecciona entre 1 y " + menu.length);
            num = toScanInteger(reader, "Selecciona una opción (1-" + menu.length + ")");
        }
        
        return num;
    }
    
    /**
     * Solicita una entrada de texto al usuario hasta que sea no vacía.
     * @param reader Lector de líneas.
     * @param s      Mensaje de solicitud.
     * @return Cadena ingresada por el usuario.
     */
    public static String toScan(LineReader reader, String s) {
        String line = "";
        do {
            System.out.print(s + ": ");
            line = reader.readLine().trim();
            if (line.isEmpty()) {
                System.out.println("¡Este campo no puede estar vacío!");
            }
        } while (line.isEmpty());
        return line;
    }

    /**
     * Solicita al usuario un número entero válido.
     * @param reader Lector de líneas.
     * @param s      Mensaje de solicitud.
     * @return Número entero ingresado por el usuario.
     */
    public static int toScanInteger(LineReader reader, String s) {
        while (true) {
            try {
                System.out.print(s + ": ");
                String line = reader.readLine().trim();
                
                if (line.isEmpty()) {
                    System.out.println("¡Debes ingresar un número!");
                    continue;
                }
                
                int num = Integer.parseInt(line);
                return num;
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Debes ingresar un número válido.");
            }
        }
    }
    
    /**
     * Solicita confirmación (sí/no) al usuario.
     * @param reader Lector de líneas.
     * @param s      Mensaje de solicitud.
     * @return true si el usuario responde "s" o "si", false en caso contrario.
     */
    public static boolean toScanBoolean(LineReader reader, String s) {
        while (true) {
            System.out.print(s + " (s/n): ");
            String line = reader.readLine().trim().toLowerCase();
            
            if (line.equals("s") || line.equals("si") || line.equals("sí") || line.equals("y") || line.equals("yes")) {
                return true;
            } else if (line.equals("n") || line.equals("no")) {
                return false;
            } else {
                System.out.println("Por favor, responde con 's' para sí o 'n' para no.");
            }
        }
    }
}