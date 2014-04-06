package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.item.Inventory;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.dungeoncrawler.list.AttackList;
import io.github.omn0mn0m.util.Input;

/**
 * This is the class for the player.
 * @author Nam Tran
 *
 */
public class Hero extends Entity {
	
	// Inventory sizes
	private final int INVENTORY_SIZE = 9;
	private final int EQUIPPED_SIZE = 2;
	
	// Random Class Stats
	private final int RANDOM_HEALTH = 100;
	private final int RANDOM_ATTACK = 15;
	private final int RANDOM_DEFENSE = 15;
	
	private Inventory inventory = new Inventory(INVENTORY_SIZE);	// Player's inventory
	private Inventory equipped = new Inventory(EQUIPPED_SIZE);		// Player's equip inventory
    private Input input = new Input();	// Player's input
    private AttackList attackList = new AttackList();	// List of attacks the player can do
    
    private String playerClass;		// Class of the player
	private String classFilePath = Game.rootPath + "Player Classes.nam";

    /**
     * Constructor
     */
    public Hero() {
    	this.name = "You";
    }
    
    /**
     * Runs a script to select the player class and change the stats accordingly.
     */
    public void selectClass() {
        Game.print("You wake up inside a dungeon. Fight your way out.");
        Game.print("Actually, first you should select a class... (Type the name)");
        Game.print("| Warrior | Rogue | Mage | Healer |");
        playerClass = input.splitAndGetInput(0);
        Game.print("Okay, so you are a " + playerClass);
        
        Game.namReader.loadFile(classFilePath);
    	
        Game.namReader.findData(String.valueOf(playerClass + "-Health"));
    	health = Game.namReader.getIntData();
    	
    	Game.namReader.findData(String.valueOf(playerClass + "-Attack"));
    	attack = Game.namReader.getIntData();
    	
    	Game.namReader.findData(String.valueOf(playerClass + "-Defense"));
    	defense = Game.namReader.getIntData();
    	
    	Game.namReader.unloadFile();
    	
    	if (!Game.namReader.isFoundElement()) {
    		Game.print("That's awkward... your class doesn't actually do anything... Do you still wish to proceed?");
            if (input.splitAndGetInput(0).equals("yes")) {
            	this.setStats(random.nextInt(RANDOM_HEALTH), random.nextInt(RANDOM_ATTACK), random.nextInt(RANDOM_DEFENSE));
            } else {
            	this.selectClass();
            }
    	}
    }

    /**
     * Checks for if the player is dead and ends the game if so.
     */
    public void checkIfAlive() {
    	this.checkHealth();
        if (!alive) {
        	Game.print("You have died.");
            System.exit(0);
        }
    }

    /**
     * Attacks a specified hostile and determines how much damage should be done.
     * @param hostile
     */
    public void attack(Hostile hostile) {
        if (hostile == null) {
        	Game.print("You can't attack what's not there...");
        } else {
        	int attackBuff = 0;
            Game.print("Which attack should you use?");
            String attackType = input.getSimpleInput();
            if (attackList.getAttack(attackType) != null) {
            	attackBuff = attackList.getAttack(attackType).getAttackBuff();
            }
        	Game.print("You " + attackType + " the " + hostile.getName() + ".");
            hostile.takeDamage(this, attackBuff);
        }
    }
    
    /**
     * Runs the script to reroll the character's class and stats.
     */
    public void rerollCharacter() {
    	Game.print("Are you sure you wish to reroll your character? This will knock you unconscious...");
        if (input.getSimpleInput().equalsIgnoreCase("yes")) {
            this.selectClass();
        } else {
        	Game.print("You have cancelled your rerolling.");
        }
    }
    
    /**
     * Prints the player's inventory, including all empty slots.
     */
    public void checkInventory() {
    	inventory.checkInventory(true);
    }
    
    /**
     * Adds an item to a specific slot in the player's inventory.
     * @param slot
     * @param item
     */
    public void add(int slot, Item item) {
    	inventory.add(slot, item);
    }
    
    /**
     * Removes an item from a specific slot.
     */
    public void removeSlot(int slot) {
    	inventory.removeSlot(slot);
    	Game.print("Slot " + slot + " has been emptied.");
    }
    
    /**
     * Removes an item from the player inventory and adds it to a "donation" inventory.
     * @param donationInventory
     * @param item
     */
    public void removeItem(Inventory donationInventory, String item) {
    	Item targetItem = Game.itemList.getItem(item);
    	if (inventory.hasItem(targetItem)) {
    		inventory.removeItem(targetItem);
    		donationInventory.addItem(targetItem);
    		Game.print("The " + item + " has been removed.");
    	} else {
    		Game.print("That item can't be removed!");
    	}
    }
    
    /**
     * Adds an item to the player inventory and removes it from a "donor" inventory.
     * @param donorInventory
     * @param item
     */
    public void addItem(Inventory donorInventory, String item) {
    	Item targetItem = Game.itemList.getItem(item);
    	if (donorInventory.hasItem(targetItem)) {
    		inventory.addItem(targetItem);
    		donorInventory.removeItem(targetItem);
    		Game.print("The " + item + " has been added.");
    	} else {
    		Game.print("That item can't be added!");
    	}
    }
    
    /**
     * Prints the player's equip inventory.
     */
    public void checkEquipped() {
    	equipped.checkInventory(true);
    }
}
