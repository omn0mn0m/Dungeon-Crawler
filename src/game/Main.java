package game;

/**
 * This is the main class for the game. Everything for the game runs from here.
 * @author Nam Tran
 *
 */
public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
        Game engine = new Game();	// What runs for the game to work
        
        engine.heroClassSelect();	// Selection for the character class
        
        // Runs for the duration of the game
        while (true) {
        	engine.runInputCommand();	// Gets the player input and interprets it
        	engine.runGame();	// Runs non-player controlled elements such as hostile attacks
        }
=======
        Game engine = new Game();
        engine.run();
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
    }
}
