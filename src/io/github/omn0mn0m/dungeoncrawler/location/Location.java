package io.github.omn0mn0m.dungeoncrawler.location;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.entity.Entity;
import io.github.omn0mn0m.dungeoncrawler.entity.Hostile;
import io.github.omn0mn0m.dungeoncrawler.item.Inventory;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.util.TextPrinter;

import java.util.Random;

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
    	if (hostileNumber <= 0) {
    		hasHostiles = false;
    		hostileNumber = 1;
    	}
    	hostiles = new Hostile[hostileNumber];
    	locationItems = new Inventory(itemNumber);
    }
    
    /**
     * Runs when the player enters a location. It generates things if it is being
     * entered for the first time in the game or has not been generated.
     * @param entity
     */
    public void enterLocation(Entity entity) {
    	if (!generated) {
    		this.generateHostile();
    		this.generateItem();
    		generated = true;
    	} else {}
		TextPrinter.print(entity.getName() + " walks into the room.");
    }
    
    /**
     * Randomly generates hostiles to fill the location.
     */
    public void generateHostile() {
		for (int i = 0; i < hostiles.length; i++) {
			if (hasHostiles) {
				hostiles[i] = new Hostile(Game.hostileList.getHostile(Game.hostileList.getKey(random.nextInt(Game.hostileList.getTotalHostiles()))));
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
    		locationItems.addItem(new Item(Game.itemList.getItem(Game.itemList.getKey(random.nextInt(Game.itemList.getTotalItems())))));
    	}
    }
    
    /**
     * Checks if a hostile in the location is dead, then deletes if it is.
     */
    public void checkIfHostileDead(int index, Entity cause) {
		if (hostiles[index] != null && !hostiles[index].isAlive()) {
			hostiles[index].giveXP(cause);
			cause.checkXP();
			hostiles[index] = null;
		}
    }
    
    public Hostile getLocationHostile(String targetHostile) {
    	Hostile hostile = null;
    	for (int i = 0; i < hostiles.length; i++) {
    		if (hostiles[i] != null && hostiles[i].isTarget(targetHostile)) {
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
    	TextPrinter.print("Items:");
    	locationItems.checkInventory(false);
    }
    
    /**
     * Prints a list of hostiles in the location.
     */
    public void printHostiles() {
    	TextPrinter.print("Hostiles:");
    	for (int i = 0; i < hostiles.length; i++) {
    		if (hostiles[i] != null) {
    			TextPrinter.print("- " + hostiles[i].getName());
    		}
    	}
    }
	
	public boolean hasHostiles() {
		boolean hasHostiles = false;
		
		for (int i = 0; i < hostiles.length; i++) {
			if (hostiles[i] != null) {
				hasHostiles = true;
				break;
			}
		}
		
		return hasHostiles;
	}
	
	public void killAllHostiles() {
		for (int i = 0; i < hostiles.length; i++) {
			hostiles[i] = null;
		}
	}
	
	public void killAllItems() {
		for (int i = 0; i < locationItems.getSize(); i++) {
			locationItems.removeSlot(i);
		}
	}
}
