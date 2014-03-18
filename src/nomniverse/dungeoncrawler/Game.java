package nomniverse.dungeoncrawler;

import java.util.Random;

import nomniverse.dungeoncrawler.entity.Hero;
import nomniverse.dungeoncrawler.list.HostileList;
import nomniverse.dungeoncrawler.list.ItemList;
import nomniverse.util.Input;
import nomniverse.util.NamReader;

public class Game {
	
	private int currentRoom = 0;
    private final int ROOMS_TO_WIN = 10;
	
    public static final NamReader namReader = new NamReader();
//	public static final String ROOT_PATH = "storage/emulated/0/AppProjects/Dungeon-Crawler/resources/";
	public static final String ROOT_PATH = "resources/";
	
	public static final HostileList hostileList = new HostileList();
    public static final ItemList itemList = new ItemList();
    
	private Input input = new Input();
	private boolean paused;
	
    private Hero hero = new Hero();
    private Random random = new Random();
    private Location[] locations = new Location[ROOMS_TO_WIN];
    
    public Game() {
    	paused = false;
    	locations[currentRoom] = new Location(0, random.nextInt(itemList.getTotalItems()));
		locations[currentRoom].enterLocation(hero);
    }
	
    public void runInputCommand() {
    	if (!paused) {
    		switch (input.splitAndGetInput(0)) {
	            case "go":
	            	if (input.isSplitWordTarget(1, "to")) {
	            		if (input.isSplitWordTarget(2, "next")) {
	            			if (input.isSplitWordTarget(3, "room")) {
	            				this.checkForWin();
	            				this.goToNextRoom();
	            			}
	            		} else if (input.isSplitWordTarget(2, "previous")) {
	            			if (input.isSplitWordTarget(3, "room")) {
	            				this.goToPreviousRoom();
	            			}
	            		}
	            	}
	                break;
	            case "look":
	            	if (input.isSplitWordTarget(1, "around")) {
	            		if (locations[currentRoom] != null) {
	            			locations[currentRoom].printItems();
	            			locations[currentRoom].printHostiles();
	            		} else {
	            			Game.print("There is nothing to see...");
	            		}
	            	} else if (input.isSplitWordTarget(1, "at")) {
						for (int i = 0; i < locations[currentRoom].hostiles.length; i++) {
							if (locations[currentRoom].hostiles[i].isTarget(input.getInputWord(2))) {
								locations[currentRoom].hostiles[i].printStats();
							}
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
	            case "pause":
	            	pause();
	            	break;
	            case "unpause":
	            	unpause();
	            	break;
	            default:
	                print("That is not a valid command");
	                break;
	        }
    	} else {
    		switch (input.splitAndGetInput(0)) {
	            case "quit":
	                this.quit();
	                break;
	            case "restart":
	                this.restart();
	                break;
	            case "reroll":
	                hero.rerollCharacter();
	                break;
	            case "pause":
	            	pause();
	            	break;
	            case "unpause":
	            	unpause();
	            	break;
	            default:
	                print("That is not a valid command");
	                break;
	        }
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
    	if (currentRoom == ROOMS_TO_WIN) {
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
            for (int i = 0; i < ROOMS_TO_WIN; i++) {
				if (locations[i] != null) {
					locations[i] = null;
				}
            }
			currentRoom = 0;
			locations[currentRoom] = new Location(0, random.nextInt(itemList.getTotalItems()));
			locations[currentRoom].enterLocation(hero);
            this.heroClassSelect();
        }
    }
    
    public void pause() {
    	paused = true;
    	print("The game is now paused.");
    }
    
    public void unpause() {
    	paused = false;
    	print("The game is resuming.");
    }
    
    public boolean isPaused() {
    	return paused;
    }
    
    public void goToNextRoom() {
    	if (!locations[currentRoom].hasHostiles()) {
    		currentRoom += 1;
    		if (locations[currentRoom] == null) {
    			locations[currentRoom] = new Location(1, random.nextInt(itemList.getTotalItems()));
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
