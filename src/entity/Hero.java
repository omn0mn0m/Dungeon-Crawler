package entity;

<<<<<<< HEAD
import list.AttackList;
import game.Game;
import item.Inventory;
import item.Item;
import util.Input;

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
        
        Game.namReader.loadFile("resources/Player Classes.nam");
    	
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
=======
import item.Inventory;
import item.Item;
import item.ItemList;

import java.util.Scanner;

public class Hero extends Entity {
	
	private Inventory inventory = new Inventory(9);
	private Inventory equipped = new Inventory(2);
    private Scanner input = new Scanner(System.in);
    
    private String playerClass;

    public Hero() {
    	this.name = "You";
    }

    public String getInput() {
    	return input.nextLine().toLowerCase();
    }

    public void selectClass() {
        System.out.println("You wake up inside a dungeon. Fight your way out.");
        System.out.println("Actually, first you should select a class... (Type the name)");
        System.out.println("| Warrior | Rogue | Mage | Healer |");
        playerClass = this.getInput();
        System.out.println("Okay, so you are a " + playerClass);
        
        namReader.loadFile("resources/Player Classes.nam");
    	
    	namReader.findData(String.valueOf(playerClass + "-Health"));
    	health = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(playerClass + "-Attack"));
    	attack = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(playerClass + "-Defense"));
    	defense = namReader.getIntData();
    	
    	namReader.unloadFile();
    	
    	if (!namReader.isFoundElement()) {
    	  System.out.println("That's awkward... your class doesn't actually do anything...");
          System.out.println("Do you still wish to proceed?");
          if (this.getInput().equals("yes")) {
          	this.setClassStats(random.nextInt(100), random.nextInt(15), random.nextInt(15));
          } else {
          	this.selectClass();
          }
    	}
    }

    public void checkHealth() {
        if (this.health <= 0) {
            System.out.println("You have died.");
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
            System.exit(0);
        }
    }

<<<<<<< HEAD
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
=======
    public void attack(Hostile mob) {
        if (!mob.getName().equals("DEAD")) {
            int attackBuff = 0;
            System.out.println("Which attack should you use?");
            String attackType = this.getInput();
            
            namReader.loadFile("resources/Player Classes.nam");
        	
        	namReader.findData(String.valueOf(attackType));
        	attackBuff = namReader.getIntData();
        	
        	namReader.unloadFile();
        	
            System.out.println("You " + attackType + " the " + mob.getName() + ".");
            mob.takeDamage(this, attackBuff);
            this.takeDamage(mob, 0);
            mob.checkHealth(this);
            this.checkHealth();
        } else {
            System.out.println("You can't attack what's not there...");
        }
    }
    
    public void rerollCharacter() {
        System.out.println("Are you sure you wish to reroll your character?");
        System.out.println("This will knock you unconscious...");
        if (input.nextLine().equalsIgnoreCase("yes")) {
            this.selectClass();
        } else {
            System.out.println("You have cancelled your rerolling.");
        }
    }
    
    public void checkInventory() {
    	inventory.checkInventory();
    }
    
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
    public void add(int slot, Item item) {
    	inventory.add(slot, item);
    }
    
<<<<<<< HEAD
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
    	Game.print(targetItem.getName());
    	if (donorInventory.hasItem(Game.itemList.getItem(item))) {
    		inventory.addItem(Game.itemList.getItem(item));
    		donorInventory.removeItem(Game.itemList.getItem(item));
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
=======
    public void removeSlot() {
    	System.out.println("Which slot would you like to remove an item from?");
    	int slot = Integer.parseInt(this.getInput());
    	inventory.removeSlot(slot);
    	System.out.println("Slot " + slot + " has been emptied.");
    }
    
    public void removeItem(ItemList itemList) {
    	System.out.println("What item would you like to remove?");
    	String item = this.getInput();
    	inventory.removeItem(itemList.getItem(item));
    	System.out.println("The " + item + " has been removed.");
    }
    
    public void addItem(ItemList itemList) {
    	System.out.println("What item would you like to add?");
    	String item = this.getInput();
    	inventory.addItem(itemList.getItem(item));
    	System.out.println("The " + item + " has been added.");
    }
    
    public void checkEquipped() {
    	equipped.checkInventory();
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
    }
}
