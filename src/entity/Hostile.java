package entity;

<<<<<<< HEAD
import game.Game;

/**
 * This is the class for the basic hostile. It has a name, health, defense, and attack.
 * @author Nam Tran
 *
 */
public class Hostile extends Entity {
	
	/**
	 * Constructor with a name, health, defense, and attack.
	 * It sets the hostile to alive.
	 * @param name
	 * @param health
	 * @param defense
	 * @param attack
	 */
    public Hostile(String name, int health, int defense, int attack) {
    	super(name, health, defense, attack);
    	alive = true;
    }

    /**
     * Checks if the hostile is still alive, and changes the boolean accordingly.
     * It prints a message if the hostile is dead announcing it was killed.
     * @param hero
     */
    public void checkIfAlive(Hero hero) {
    	this.checkHealth();
        if (!alive) {
            Game.print(hero.getName() + " killed a " + name);
        }
    }
=======
public class Hostile extends Entity {

    public Hostile() {
    	this.name = "DEAD";
    }

    public void checkHealth(Hero hero) {
        if (this.health <= 0) {
            System.out.println(hero.getName() + " killed a " + name);
            this.name = "DEAD";
        } else {
        }
    }
    
    public void generateMob() {
    	int mobNumber = random.nextInt(4);
    	namReader.loadFile("resources/Hostile.nam");
    	
    	namReader.findData(String.valueOf(mobNumber + "-Name"));
    	name = namReader.getStringData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Health"));
    	health = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Attack"));
    	attack = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Defense"));
    	defense = namReader.getIntData();
    	
    	namReader.unloadFile();
    }
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
}
