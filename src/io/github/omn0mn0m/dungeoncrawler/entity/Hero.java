package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.item.Inventory;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.dungeoncrawler.list.AttackList;
import io.github.omn0mn0m.util.Input;
import io.github.omn0mn0m.util.TextPrinter;

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
	private final int WEAPON_SLOT = 0;
	private final int ARMOUR_SLOT = 1;
    
	private Input input = new Input();	// Player's input
    private AttackList attackList = new AttackList();	// List of attacks the player can do
    
    private String playerClass;		// Class of the player
	private String classFilePath = "Player Classes.nam"; //The name of the .nam file
	
    /**
     * Constructor
     */
    public Hero() {
    	this.name = "You";
    	level = 1;
    }
    
    /**
     * Runs a script to select the player class and change the stats accordingly.
     */
    public void selectClass() {
        TextPrinter.print("You wake up inside a dungeon. Fight your way out.");
        TextPrinter.print("Actually, first you should select a class... (Type the name)");
        TextPrinter.print("| Warrior | Rogue | Mage | Healer |");
        playerClass = input.splitAndGetInput(0);
        TextPrinter.print("Okay, so you are a " + playerClass);
        
        namReader.loadFile(classFilePath);
    	
        namReader.findData(String.valueOf(playerClass + "-Health"));
    	health = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(playerClass + "-Attack"));
    	attack = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(playerClass + "-Defense"));
    	defense = namReader.getIntData();
    	
    	namReader.unloadFile();
    	
    	if (!namReader.isFoundElement()) {
    		TextPrinter.print("That's awkward... your class doesn't actually do anything... Do you still wish to proceed?");
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
        	TextPrinter.print("You have died.");
            System.exit(0);
        }
    }

    /**
     * Attacks a specified hostile and determines how much damage should be done.
     * @param hostile
     */
    public void attack(Hostile hostile) {
        if (hostile == null) {
        	TextPrinter.print("You can't attack what's not there...");
        } else {
        	int attackBuff = 0;
            TextPrinter.print("Which attack should you use?");
            String attackType = input.getSimpleInput();
            
            if (attackList.getAttack(attackType) != null) {
            	if (equipped.checkSlot(WEAPON_SLOT) != null && attackList.getAttack(attackType).getAttackRequires().equalsIgnoreCase(equipped.checkSlot(WEAPON_SLOT).getName())) {
            		attackBuff = attackList.getAttack(attackType).getAttackBuff();
            	} else {
            		TextPrinter.print("You attack, but a lack of items makes it not as effective...");
            	}
            }
            
        	TextPrinter.print("You " + attackType + " the " + hostile.getName() + ".");
            hostile.takeDamage(this, attackBuff);
        }
    }
    
    /**
     * Runs the script to reroll the character's class and stats.
     */
    public void rerollCharacter() {
    	TextPrinter.print("Are you sure you wish to reroll your character? This will knock you unconscious...");
        if (input.getSimpleInput().equalsIgnoreCase("yes")) {
            this.selectClass();
        } else {
        	TextPrinter.print("You have cancelled your rerolling.");
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
    	TextPrinter.print("Slot " + slot + " has been emptied.");
    }
    
    /**
     * Removes an item from the player inventory and adds it to a "donation" inventory.
     * @param donationInventory
     * @param item
     */
    public void removeItem(Inventory donationInventory, String item) {
    	try {
	    	Item targetItem = Game.itemList.getItem(item);
	    	if (inventory.hasItem(targetItem.getName())) {
	    		inventory.removeItem(targetItem);
	    		donationInventory.addItem(targetItem);
	    		TextPrinter.print("The " + item + " has been removed.");
	    	} else {
	    		TextPrinter.print("That item can't be removed!");
	    	}
    	} catch (NullPointerException e) {
    		TextPrinter.print("That item doesn't exist!");
    	}
    }
    
    /**
     * Adds an item to the player inventory and removes it from a "donor" inventory.
     * @param donorInventory
     * @param item
     */
    public void addItem(Inventory donorInventory, String item) {
    	try {
	    	Item targetItem = Game.itemList.getItem(item);
	    	if (donorInventory.hasItem(targetItem.getName())) {
	    		inventory.addItem(targetItem);
	    		donorInventory.removeItem(targetItem);
	    		TextPrinter.print("The " + item + " has been added.");
	    	} else {
	    		TextPrinter.print("That item can't be added!");
	    	}
    	} catch (NullPointerException e) {
    		TextPrinter.print("That item doesn't exist!");
    	}
    }
    
    public boolean hasInventoryItem(String item) {
    	return inventory.hasItem(item);
    }
    
    /**
     * Prints the player's equip inventory.
     */
    public void checkEquipped() {
    	equipped.checkInventory(true);
    }
    
    /**
     * Equips an item if the item is equippable and the slot is empty
     * @param item
     */
    public void equipItem(String item) {
    	try {
    		Item targetItem = Game.itemList.getItem(item);
        	int targetSlot = -1;
        	
	    	if (targetItem.getType().equals("weapon")) {
	    		targetSlot = WEAPON_SLOT;
	    	} else if (targetItem.getType().equals("armour")) {
	    		targetSlot = ARMOUR_SLOT;
	    	}
	    	
	    	if (targetSlot != -1) {
		    	if (equipped.slotEmpty(targetSlot) && inventory.hasItem(targetItem.getName())) {
					equipped.add(targetSlot, targetItem);
					inventory.removeItem(targetItem);
					
					// Adjusts the stats
					this.attack += targetItem.getStatBuff("attack");
					this.defense += targetItem.getStatBuff("defense");
					this.health += targetItem.getStatBuff("health");
					
					TextPrinter.print(name + " has equipped a " + targetItem.getName() + ".");
				} else {
					TextPrinter.print("You can't equip that!");
				}
	    	}
    	} catch (NullPointerException e) {
    		TextPrinter.print("That item doesn't exist!");
    	}
    }
    
    public void unequipItem(String item) {
    	try {
    		Item targetItem = Game.itemList.getItem(item);
        	int targetSlot = -1;
        	
	    	if (targetItem.getType().equals("weapon")) {
	    		targetSlot = WEAPON_SLOT;
	    	} else if (targetItem.getType().equals("armour")) {
	    		targetSlot = ARMOUR_SLOT;
	    	} else {
	    		TextPrinter.print("You cannot unequip that!");
	    	}
	    	
	    	if (targetSlot != -1) {
		    	if (equipped.hasItem(targetItem.getName())) {
		    		// Adjusts the stats
					this.attack -= targetItem.getStatBuff("attack");
					this.defense -= targetItem.getStatBuff("defense");
					this.health -= targetItem.getStatBuff("health");
					
					equipped.removeItem(targetItem);
					inventory.addItem(targetItem);
					TextPrinter.print("The " + targetItem.getName() + " has been unequipped and moved to your inventory.");
				} else {
					TextPrinter.print("That slot is already empty!");
				}
	    	}
    	} catch (NullPointerException e) {
    		TextPrinter.print("That item doesn't exist!");
    	}
    }
    
    public void consumeItem(String item) {
    	try {
	    	Item targetItem = Game.itemList.getItem(item);
	    	
	    	if (inventory.hasItem(targetItem.getName()) && targetItem.getType().equals("consumable")) {
	    		// Adjusts the stats
				this.attack += targetItem.getStatBuff("attack");
				this.defense += targetItem.getStatBuff("defense");
				this.health += targetItem.getStatBuff("health");
	    		
	    		inventory.removeItem(targetItem);
	    		TextPrinter.print("The " + item + " has been consumed.");
	    	} else {
	    		TextPrinter.print("That item can't be consumed!");
	    	}
    	} catch (NullPointerException e) {
    		TextPrinter.print("That item doesn't exist!");
    	}
    }
}
