package io.github.omn0mn0m.dungeoncrawler;

import io.github.omn0mn0m.dungeoncrawler.entity.Hero;
import io.github.omn0mn0m.dungeoncrawler.list.HostileList;
import io.github.omn0mn0m.dungeoncrawler.list.ItemList;
import io.github.omn0mn0m.dungeoncrawler.location.Location;
import io.github.omn0mn0m.util.Input;
import io.github.omn0mn0m.util.NamReader;

import java.util.Random;

public class Game {
	
	private int roomsCleared = 0;
	private int currentRoomX = 0;
	private int currentRoomY = 0;
    private final int ROOMS_TO_WIN = 10;
	
    public static final NamReader namReader = new NamReader();
	public static String rootPath;
	
	public static HostileList hostileList;
    public static ItemList itemList;
    
	private Input input = new Input();
	private boolean paused;
	
    private Hero hero;
    private Random random = new Random();
    private Location[][] locations = new Location[ROOMS_TO_WIN][ROOMS_TO_WIN];
    
    public Game() {
    	if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
    		rootPath = "resources/";
    	} else {
    		rootPath = "storage/emulated/0/AppProjects/Dungeon-Crawler/resources/";
    	}
    	
    	hostileList = new HostileList();
    	itemList = new ItemList();
    	hero = new Hero();
    	
    	paused = false;
    	locations[currentRoomX][currentRoomY] = new Location(0, random.nextInt(itemList.getTotalItems()));
		locations[currentRoomX][currentRoomY].enterLocation(hero);
    }
	
    public void runInputCommand() {
    	if (!paused) {
    		switch (input.splitAndGetInput(0)) {
	            case "go":
	            	switch (input.getInputWord(1)) {
	            		case "north":
							moveTo(-1, 0);
							break;
						case "east":
							moveTo(0, 1);
							break;
						case "south":
							moveTo(1, 0);
							break;
						case "west":
							moveTo(0, -1);
							break;
						default:
							print("You can't go that way...");
							break;
	            	}
	                break;
	            case "look":
	            	if (input.isSplitWordTarget(1, "around")) {
	            		if (locations[currentRoomX][currentRoomY] != null) {
	            			locations[currentRoomX][currentRoomY].printItems();
	            			locations[currentRoomX][currentRoomY].printHostiles();
	            		} else {
	            			Game.print("There is nothing to see...");
	            		}
	            	} else if (input.isSplitWordTarget(1, "at")) {
						for (int i = 0; i < locations[currentRoomX][currentRoomY].hostiles.length; i++) {
							if (locations[currentRoomX][currentRoomY].hostiles[i].isTarget(input.getInputWord(2))) {
								locations[currentRoomX][currentRoomY].hostiles[i].printStats();
							}
						}
					}
	            	break;
	            case "attack":
	            	if (input.getSplitLength() >= 2) {
	            		hero.attack(locations[currentRoomX][currentRoomY].getLocationHostile(input.getInputWord(1)));
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
	            case "drop":
	            	hero.removeItem(locations[currentRoomX][currentRoomY].locationItems, input.getInputWord(1));
	            	break;
	            case "take":
	            	hero.addItem(locations[currentRoomX][currentRoomY].locationItems, input.getInputWord(1));
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
		for (int i = 0; i < locations[currentRoomX][currentRoomY].hostiles.length; i++) {
			if (locations[currentRoomX][currentRoomY].hostiles[i] != null) {
				hero.takeDamage(locations[currentRoomX][currentRoomY].hostiles[i], 0);
				locations[currentRoomX][currentRoomY].hostiles[i].checkIfAlive(hero);
				locations[currentRoomX][currentRoomY].checkIfHostileDead();
			}
		}
    	hero.checkIfAlive();
    }
    
    public void heroClassSelect() {
    	hero.selectClass();
    }
    
    public void checkForWin() {
    	if (roomsCleared == ROOMS_TO_WIN) {
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
				for (int c = 0; c < ROOMS_TO_WIN; i++) {
					if (locations[i][c] != null) {
						locations[i][c] = null;
					}
				}
            }
			currentRoomX = 0;
			currentRoomY = 0;
			locations[currentRoomX][currentRoomY] = new Location(0, random.nextInt(itemList.getTotalItems()));
			locations[currentRoomX][currentRoomY].enterLocation(hero);
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
	
	public void moveTo(int x, int y) {
		boolean moved = false;
		checkForWin();
		
		if (!locations[currentRoomX][currentRoomY].hasHostiles()) {
			if (currentRoomX + x > 0 && currentRoomX + x <= ROOMS_TO_WIN) {
				currentRoomX += x;
				moved = true;
			}
		
			if (currentRoomY + y > 0 && currentRoomY + y <= ROOMS_TO_WIN) {
				currentRoomY += y;
				moved = true;
			}
		
			if (moved) {
				if (locations[currentRoomX][currentRoomY] == null) {
					roomsCleared += 1;
					locations[currentRoomX][currentRoomY] = new Location(random.nextInt(3), random.nextInt(itemList.getTotalItems()));
				}
				locations[currentRoomX][currentRoomY].enterLocation(hero);
			} else {
				print("You cannot move further in that direction...");
			}
		} else {
			print("You try to run, but are stopped!");
		}
	}
    
    public static void print(String string) {
    	System.out.println(string);
    }
}
