package entity;

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
            System.exit(0);
        }
    }

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
    
    public void add(int slot, Item item) {
    	inventory.add(slot, item);
    }
    
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
    }
}
