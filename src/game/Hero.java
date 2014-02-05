package game;

import java.util.Scanner;

public class Hero extends Entity {

    private Scanner input = new Scanner(System.in);
    
    private Item[] inventory = new Item[9];
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
        switch (playerClass) {
            case "warrior":
                this.setClassStats(175, 10, 15);
                break;
            case "rogue":
                this.setClassStats(125, 25, 10);
                break;
            case "mage":
                this.setClassStats(150, 20, 20);
                break;
            case "healer":
                this.setClassStats(200, 5, 25);
                break;
            default:
                System.out.println("That's awkward... your class doesn't actually do anything...");
                System.out.println("Do you still wish to proceed?");
                if (this.getInput().equals("yes")) {
                	this.setClassStats(random.nextInt(100), random.nextInt(15), random.nextInt(15));
                } else {
                	this.selectClass();
                }
                break;
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
            String attackType = input.nextLine().toLowerCase();
            switch (attackType) {
                case "punch":
                    attackBuff = 0;
                    break;
                case "kick":
                    attackBuff = 0;
                    break;
                case "slash":
                    attackBuff = 10;
                    break;
                default:
                    break;
            }
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
    
    public void openInventory() {
    	for (int i = 0; i < inventory.length; i++) {
    		if (inventory[i] != null) {
    			System.out.println(i + ". " + inventory[i].getInfo("name"));
    		} else {
    			System.out.println(i + ". Empty Slot");
    		}
    	}
    }
    
    public void addToInventory(int slot, Item item) {
    	inventory[slot] = item;
    }
    
    public void addToInventory(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
    		if (inventory[i] == null) {
    			addToInventory(i, item);
    		}
    	}
    }
    
    public void removeFromInventory(int slot) {
    	inventory[slot] = null;
    }
    
    public void removeFromInventory(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
    		if (inventory[i] == null) {
    			removeFromInventory(i);
    		}
    	}
    }
}
