package game;

import java.util.Random;

import item.Inventory;
import entity.Hero;
import entity.Hostile;

public class Location {

	public Hostile[] hostiles;		// Array of hostiles in the location
    public Inventory locationItems;	//Array of items in the location
    private Random random = new Random();	// Random class
    private boolean hasHostiles = true;		// Whether the area should have hostiles
    private boolean generated = false;		// Whether the area already has generated things for it
    
    /**
     * Constructor which specified the number of hostiles and items to generate
     * @param hostileNumber
     * @param itemNumber
     */
    public Location(int hostileNumber, int itemNumber) {
    	if (hostileNumber == 0) {
    		hasHostiles = false;
    		hostileNumber = 1;
    	}
    	hostiles = new Hostile[hostileNumber];
    	locationItems = new Inventory(itemNumber);
    }
    
    /**
     * Runs when the player enters a location. It generates things if it is being
     * entered for the first time in the game or has not been generated.
     * @param hero
     */
    public void enterLocation(Hero hero) {
    	if (!generated) {
    		this.generateHostile();
    		this.generateItem();
    		generated = true;
    	} else {}
		Game.print(hero.getName() + " walks into the room.");
    }
    
    /**
     * Randomly generates hostiles to fill the location.
     */
    public void generateHostile() {
		for (int i = 0; i < hostiles.length; i++) {
			if (hasHostiles) {
				hostiles[i] = Game.hostileList.getHostile(Game.hostileList.getKey(random.nextInt(Game.hostileList.getTotalHostiles())));
			} else {
				hostiles[i] = null;
			}
		}
    }
    
    /**
     * Randomly generates items to fill the location.
     */
    public void generateItem() {
    	for (int i = 0; i < locationItems.getSize(); i++) {
    		locationItems.addItem(Game.itemList.getItem(Game.itemList.getKey(random.nextInt(Game.itemList.getTotalItems()))));
    	}
    }
    
    /**
     * Checks if a hostile in the location is dead, then deletes if it is.
     */
    public void checkIfHostileDead() {
    	for (int i = 0; i < hostiles.length; i++) {
    		if (hostiles[i] != null && hostiles[i].isAlive() == false) {
    			hostiles[i] = null;
    		}
    	}
    }
    
    public Hostile getLocationHostile(String targetHostile) {
    	Hostile hostile = null;
    	for (int i = 0; i < hostiles.length; i++) {
    		if (hostiles[i] == Game.hostileList.getHostile(targetHostile)) {
    			hostile = hostiles[i];
    			break;
    		}
    	}
    	return hostile;
    }
    
    /**
     * Prints a list of items in the location.
     */
    public void printItems() {
    	Game.print("Items:");
    	locationItems.checkInventory(false);
    }
    
    /**
     * Prints a list of hostiles in the location.
     */
    public void printHostiles() {
    	Game.print("Hostiles:");
    	for (int i = 0; i < hostiles.length; i++) {
    		if (hostiles[i] != null) {
    			Game.print("- " + hostiles[i].getName());
    		}
    	}
    }
}
