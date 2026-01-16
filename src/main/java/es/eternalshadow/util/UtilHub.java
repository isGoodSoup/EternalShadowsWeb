package es.eternalshadow.util;

public class UtilHub {
    private final InputHandler inputHandler;
    private final PasswordUtil passwordUtil;
    private final CharacterFactory characterFactory;
    private final ExceptionsHandler exceptionsHandler;
    private final FileManager fileManager;
    private final ConsolePrinter consolePrinter;
    
    public UtilHub() {
        this.inputHandler = new InputHandler();
        this.passwordUtil = new PasswordUtil();
        this.characterFactory = null; // Se inicializa después con setContext
        this.exceptionsHandler = new ExceptionsHandler();
        this.fileManager = new FileManager();
        this.consolePrinter = new ConsolePrinter();
    }
    
    /**
     * Inicializa el CharacterFactory con el contexto del juego.
     * @param context Contexto del juego.
     */
    public void initializeCharacterFactory(es.eternalshadow.main.GameContext context) {
        this.characterFactory = new CharacterFactory(context);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public PasswordUtil getPasswordUtil() {
        return passwordUtil;
    }

    public CharacterFactory getCharacterFactory() {
        if (characterFactory == null) {
            throw new IllegalStateException("CharacterFactory no inicializado. Llama a initializeCharacterFactory primero.");
        }
        return characterFactory;
    }

    public ExceptionsHandler getExceptionsHandler() {
        return exceptionsHandler;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConsolePrinter getConsolePrinter() {
        return consolePrinter;
    }
}