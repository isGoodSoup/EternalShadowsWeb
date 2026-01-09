package es.eternalshadow.util;

public class UtilHub {
	private final InputHandler inputHandler;
	private final RandomUtils randomUtils;
	private final CharacterFactory characterFactory;
	private final FileManager fileManager;
	private final ConsolePrinter consolePrinter;
	private final StoryLoader storyLoader;
	private final ExceptionsHandler exceptionsHandler;
	private final PasswordUtil passwordUtil;

	public UtilHub() {
		this.inputHandler = new InputHandler();
		this.randomUtils = new RandomUtils();
		this.characterFactory = new CharacterFactory();
		this.fileManager = new FileManager();
		this.consolePrinter = new ConsolePrinter();
		this.storyLoader = new StoryLoader();
		this.exceptionsHandler = new ExceptionsHandler();
		this.passwordUtil = new PasswordUtil();
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public RandomUtils getRandomUtils() {
		return randomUtils;
	}

	public CharacterFactory getCharacterFactory() {
		return characterFactory;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public ConsolePrinter getConsolePrinter() {
		return consolePrinter;
	}

	public StoryLoader getStoryLoader() {
		return storyLoader;
	}

	public ExceptionsHandler getExceptionsHandler() {
		return exceptionsHandler;
	}

	public PasswordUtil getPasswordUtil() {
		return passwordUtil;
	}
}
