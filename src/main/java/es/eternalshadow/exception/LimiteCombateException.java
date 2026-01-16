package es.eternalshadow.exception;

/**
 * Excepción lanzada cuando se excede un límite en combate.
 */
public class LimiteCombateException extends Exception {
    private static final long serialVersionUID = 1L;

    public LimiteCombateException() {
        super();
    }

    public LimiteCombateException(String message) {
        super(message);
    }

    public LimiteCombateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimiteCombateException(Throwable cause) {
        super(cause);
    }
}