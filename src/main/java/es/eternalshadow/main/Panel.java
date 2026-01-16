package es.eternalshadow.main;

import org.jline.terminal.Terminal;

/**
 * Panel que gestiona la terminal/consola.
 */
public class Panel {
    private Terminal terminal;
    
    public Panel(Terminal terminal) {
        this.terminal = terminal;
    }
    
    public Terminal getTerminal() {
        return terminal;
    }
    
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
    
    /**
     * Escribe una línea en la terminal.
     */
    public void escribirLinea(String texto) {
        terminal.writer().println(texto);
        terminal.writer().flush();
    }
    
    /**
     * Limpia la pantalla.
     */
    public void limpiar() {
        terminal.writer().println("\033[H\033[2J");
        terminal.writer().flush();
    }
}