package es.eternalshadow.exception;

/**
 * Excepción relacionada con operaciones de usuario.
 */
public class UsuarioException extends Exception {
    private static final long serialVersionUID = 1L;

    public UsuarioException() {
        super();
    }

    public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioException(Throwable cause) {
        super(cause);
    }
}