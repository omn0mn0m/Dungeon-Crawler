package game;

import java.util.Random;

import list.HostileList;
import list.ItemList;
import util.Input;
import util.NamReader;
import entity.Hero;

public class Game {
	
	private int numberOfRoomsPassed = 0;
	private int currentRoom = 0;
    private final int ROOMS_TO_WIN = 10;
	
    public static final NamReader namReader = new NamReader();
	public static final HostileList hostileList = new HostileList();
    public static final ItemList itemList = new ItemList();
	private Input input = new Input();
    private Hero hero = new Hero();
    private Random random = new Random();
    private Location[] locations = new Location[ROOMS_TO_WIN];
    
    public Game() {
    	locations[currentRoom] = new Location(0, random.nextInt(3));
		locations[currentRoom].enterLocation(hero);
    }
    
    public void runInputCommand() {
        switch (input.splitAndGetInput(0)) {
            case "go":
            	if (input.getInputWord(1).equals("to")) {
            		if (input.getInputWord(2).equals("next")) {
            			if (input.getInputWord(3).equals("room")) {
            				this.checkForWin();
            				this.goToNextRoom();
            			}
            		} else if (input.getInputWord(2).equals("previous")) {
            			if (input.getInputWord(3).equals("room")) {
            				this.goToPreviousRoom();
            			}
            		}
            	}
                break;
            case "look":
            	if (input.getInputWord(1).equals("around")) {
            		if (locations[currentRoom] != null) {
            			locations[currentRoom].printItems();
            			locations[currentRoom].printHostiles();
            		} else {
            			Game.print("There is nothing to see...");
            		}
            	}
            	break;
            case "attack":
            	if (input.getSplitLength() >= 2) {
            		hero.attack(locations[currentRoom].getLocationHostile(input.getInputWord(1)));
            	} else {
            		Game.print("You did not choose anything to attack...");
            	}
                break;
            case "quit":
                this.quit();
                break;
            case "restart":
                this.restart();
                break;
            case "reroll":
                hero.rerollCharacter();
                break;
            case "check":
            	switch (input.getInputWord(1)) {
            		case "stats":
            			hero.printStats();
            			break;
            		case "inventory":
            			hero.checkInventory();
            			break;
            		case "health":
            			print("Health: " + hero.getStat("health"));
            			break;
            		default:
            			print("That is not something valid to check...");
            			break;
            	}
            	break;
            case "remove":
            	hero.removeItem(locations[currentRoom].locationItems, input.getInputWord(1));
            	break;
            case "add":
            	hero.addItem(locations[currentRoom].locationItems, input.getInputWord(1));
            	break;
            default:
                print("That is not a valid command");
                break;
        }
    }
    
    public void runGame() {
		if (locations[currentRoom].hostiles[0] != null) {
			hero.takeDamage(locations[currentRoom].hostiles[0], 0);
			locations[currentRoom].hostiles[0].checkIfAlive(hero);
			locations[currentRoom].checkIfHostileDead();
		}
    	hero.checkIfAlive();
    }
    
    public void heroClassSelect() {
    	hero.selectClass();
    }
    
    public void checkForWin() {
    	if (numberOfRoomsPassed == ROOMS_TO_WIN) {
    		print("You walk through into the next room, but there is no more dungeon. You have reached the end. Congradulations!");
            System.exit(0);
    	}
    }
    
    public void quit() {
    	print("Are you sure you want to quit? ");
        if(input.getSimpleInput().equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
        	print("Resuming game then...");
        }
    }
    
    public void restart() {
    	print("Are you sure you want to restart?");
        if(input.getSimpleInput().equalsIgnoreCase("Yes")) {
            numberOfRoomsPassed = 0;
            for (int i = 0; i < ROOMS_TO_WIN; i++) {
            	if (locations[i] != null) {
            		locations[i].hostiles[0] = null;
            	}
            }
            this.heroClassSelect();
        }
    }
    
    public void goToNextRoom() {
    	if (locations[currentRoom].hostiles[0] == null) {
    		currentRoom += 1;
    		if (locations[currentRoom] == null) {
    			locations[currentRoom] = new Location(1, random.nextInt(3));
    			numberOfRoomsPassed += 1;
    		}
    		locations[currentRoom].enterLocation(hero);
        } else {
        	print("You try to run, but are stopped!");
        }
    }
    
    public void goToPreviousRoom() {
		if (currentRoom != 0) {
			currentRoom -= 1;
			locations[currentRoom].enterLocation(hero);
		} else {
			print("You can't go further back!");
		}
    }
    
    public static void print(String string) {
    	System.out.println(string);
    }
}
