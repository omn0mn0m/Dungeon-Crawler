package nomniverse.dungeoncrawler;

/**
 * This is the main class for the game. Everything for the game runs from here.
 * @author Nam Tran
 *
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game();	// What runs for the game to work
        
        game.heroClassSelect();	// Selection for the character class
        
        // Runs for the duration of the game
        while (true) {
        	if (game.isPaused()) {
        		game.runInputCommand();	// Gets the player input and interprets it
        	} else {
        		game.runInputCommand();
        		game.runGame();	// Runs non-player controlled elements such as hostile attacks
        	}
        }
    }
}
