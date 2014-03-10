package game;

/**
 * This is the main class for the game. Everything for the game runs from here.
 * @author Nam Tran
 *
 */
public class Main {

    public static void main(String[] args) {
        Game engine = new Game();	// What runs for the game to work
        
        engine.heroClassSelect();	// Selection for the character class
        
        // Runs for the duration of the game
        while (true) {
        	engine.runInputCommand();	// Gets the player input and interprets it
        	engine.runGame();	// Runs non-player controlled elements such as hostile attacks
        }
    }
}
