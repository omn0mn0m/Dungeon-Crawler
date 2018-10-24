package io.github.omn0mn0m.dungeoncrawler;

import io.github.omn0mn0m.dungeoncrawler.entity.Hero;
import io.github.omn0mn0m.dungeoncrawler.list.HostileList;
import io.github.omn0mn0m.dungeoncrawler.list.ItemList;
import io.github.omn0mn0m.dungeoncrawler.location.LocationMap;
import io.github.omn0mn0m.util.Input;
import io.github.omn0mn0m.util.TextPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final int ROOMS_TO_WIN = 10;

	public static String rootPath = (System.getProperty("os.name").toLowerCase().contains("win")) ? "resources/"
									: "storage/emulated/0/AppProjects/Dungeon-Crawler/resources/";
	public static String helpFilename;
	public static File HELP_FILE;
	private Scanner fileScanner;
	
	public static HostileList hostileList;
    public static ItemList itemList;
    
	private Input input = new Input();
	private boolean paused = false;
	
    private Hero hero;
    private LocationMap locationMap = new LocationMap(ROOMS_TO_WIN + 1, ROOMS_TO_WIN + 1);
    public static Random random = new Random();
    
    
    public Game() {

    	HELP_FILE = new File(rootPath + "help_file.txt");
    	try {
			fileScanner = new Scanner(HELP_FILE);
		} catch (FileNotFoundException e) {
			TextPrinter.print("A file is missing...");
		}
    	
    	hostileList = new HostileList();
    	TextPrinter.print("Hostiles list successfully loaded!");
    	itemList = new ItemList();
    	TextPrinter.print("Items list successfully loaded!");
    	hero = new Hero();
    	TextPrinter.print("Attacks list successfully loaded!");
    	TextPrinter.print("Player successfully loaded!");
    	TextPrinter.print("Swag out. \n");
    	
    	locationMap.createRoomAtPlayer(0, random.nextInt(itemList.getTotalItems()));
		locationMap.enterCurrentMapLocation(hero);
    }
	
    public void runInputCommand() {
    	try {
	    	if (!paused) {
	    		switch (input.splitAndGetInput(0)) {
		            case "go":
		            	switch (input.getInputWord(1)) {
		            		case "north":
		            			checkForWin();
								locationMap.moveTo(-1, 0, hero);
								break;
							case "east":
								checkForWin();
								locationMap.moveTo(0, 1, hero);
								break;
							case "south":
								checkForWin();
								locationMap.moveTo(1, 0, hero);
								break;
							case "west":
								checkForWin();
								locationMap.moveTo(0, -1, hero);
								break;
							default:
								TextPrinter.print("You can't go that way...");
								break;
		            	}
		                break;
		            case "look":
		            	if (input.isSplitWordTarget(1, "around")) {
		            		if (locationMap.getCurrentLocation() != null) {
		            			locationMap.printAllCurrentLocationInformation();
		            		} else {
		            			TextPrinter.print("There is nothing to see...");
		            		}
		            	} else if (input.isSplitWordTarget(1, "at")) {
							locationMap.printTargetHostileStats(input.getInputWord(2));
						}
		            	break;
		            case "attack":
		            	if (input.getSplitLength() >= 2) {
		            		hero.attack(locationMap.getHostileAtCurrentLocation(input.getInputWord(1)));
		            	} else {
		            		TextPrinter.print("You did not choose anything to attack...");
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
		            		case "equipped":
		            			hero.checkEquipped();
		            			break;
		            		default:
		            			TextPrinter.print("That is not something valid to check...");
		            			break;
		            	}
		            	break;
		            case "drop":
		            	hero.removeItem(locationMap.getCurrentLocationItems(), input.getInputWord(1));
		            	break;
		            case "take":
		            	hero.addItem(locationMap.getCurrentLocationItems(), input.getInputWord(1));
		            	break;
		            case "equip":
		            	hero.equipItem(input.getInputWord(1));
		            	break;
		            case "unequip":
		            	hero.unequipItem(input.getInputWord(1));
		            	break;
		            case "consume":
		            	hero.consumeItem(input.getInputWord(1));
		            	break;
		            case "pause":
		            	pause();
		            	break;
		            case "unpause":
		            	unpause();
		            	break;
		            case "help":
		            	printHelp();
		            	break;
		            default:
		                TextPrinter.print("That is not a valid command");
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
		            case "help":
		            	printHelp();
		            	break;
		            default:
		                TextPrinter.print("That is not a valid command");
		                break;
		        }
	    	}
    	} catch (NoSuchElementException e) {}
    }
    
    public void runGame() {
		for (int i = 0; i < locationMap.getHostilesInCurrentLocation(); i++) {
			if (locationMap.getHostileAtCurrentLocation(i) != null) {
				locationMap.getHostileAtCurrentLocation(i).checkIfAlive(hero);
				locationMap.checkIfHostileDead(i, hero);
				if (locationMap.getHostileAtCurrentLocation(i) != null) {
					hero.takeDamage(locationMap.getHostileAtCurrentLocation(i), 0);
				}
			}
		}
    	hero.checkIfAlive();
    }
    
    public void heroClassSelect() {
    	hero.selectClass();
    }
    
    public void checkForWin() {
    	if (locationMap.getRoomsCleared() == ROOMS_TO_WIN) {
    		TextPrinter.print("You walk through into the next room, but there is no more dungeon. You have reached the end. Congradulations!");
            System.exit(0);
    	}
    }
    
    public void quit() {
    	TextPrinter.print("Are you sure you want to quit? ");
        if(input.getSimpleInput().equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
        	TextPrinter.print("Resuming game then...");
        }
    }
    
    public void restart() {
    	TextPrinter.print("Are you sure you want to restart?");
        if(input.getSimpleInput().equalsIgnoreCase("Yes")) {
            locationMap.resetMap();
            locationMap.resetPlayerLocation(hero);
            this.heroClassSelect();
        }
    }
    
    public void pause() {
    	paused = true;
    	TextPrinter.print("The game is now paused.");
    }
    
    public void unpause() {
    	paused = false;
    	TextPrinter.print("The game is resuming.");
    }
    
    public boolean isPaused() {
    	return paused;
    }
    
    public void printHelp() {
    	if(input.getSplitLength() == 1) {
			while(fileScanner.hasNextLine()) {
				String fileStr = fileScanner.useDelimiter("[\\r\\n]+").next();
				TextPrinter.print(fileStr);
			}
    	} else {
    		switch(input.getInputWord(1)) {
    			case "go":
    				TextPrinter.print("Syntax: go <direction>");
    				TextPrinter.print("You may go north, south, east, or west.");
    				TextPrinter.print("You are not imaginative enough to even think of going other directions.");
    				break;
    			case "look":
    				TextPrinter.print("Syntax: look <arguments> <object>");
    				TextPrinter.print("You can look around anywhere, but you can only look at objects.");
    				break;
    			case "attack":
    				TextPrinter.print("Syntax: attack <enemy>");
    				TextPrinter.print("Just be sure you're attacking what is actually there!");
    				break;
    			case "quit":
    				TextPrinter.print("Syntax: quit");
    				TextPrinter.print("Quits the game and shouts ''I'm a quitter'' to the cosmos.");
    				break;
    			case "restart":
    				TextPrinter.print("Syntax: restart");
    				TextPrinter.print("Restarts the game. In case it wasn't already clear, this wipes your progress.");
    				break;
    			case "reroll":
    				TextPrinter.print("Syntax: reroll");
    				TextPrinter.print("Resets your character's stats, so you can change them.");
    				break;
    			case "check":
    				TextPrinter.print("Syntax: check <vitals>");
    				TextPrinter.print("You can check your stats, inventory, and equipped.");
    				TextPrinter.print("You tried checking some other stuff a while ago, but you found it too difficult and gave up.");
    				break;
    			case "drop":
    				TextPrinter.print("Syntax: drop <item>");
    				TextPrinter.print("Drops the item that you specify. Be careful what you do with basses.");
    				break;
    			case "take":
    				TextPrinter.print("Syntax: take <item>");
    				TextPrinter.print("Takes an item from the surroundings and places it in your inventory.");
    				break;
    			case "equip":
    				TextPrinter.print("Syntax: equip <item>");
    				TextPrinter.print("Equips the item you specify. Just be sure you actually have the item...");
    				break;
    			case "unequip":
    				TextPrinter.print("Syntax: unequip <item>");
    				TextPrinter.print("Removes the item from your equipment and places it in your inventory.");
    				break;
    			case "consume":
    				TextPrinter.print("Syntax: consume <item>");
    				TextPrinter.print("Consumes an item from your inventory and removes it from your inventory.");
    				break;
    			case "pause":
    				TextPrinter.print("Syntax: pause");
    				TextPrinter.print("Pauses the game, like stopping the world, only possible");
    				break;
    			case "unpause":
    				TextPrinter.print("Syntax: unpause");
    				TextPrinter.print("Unpauses the game, like unstopping the world but...");
    				TextPrinter.print("You know, now that I think about it, this is a really bad analogy");
    				break;
    			case "help":
    				TextPrinter.print("Syntax: help <command>");
    				TextPrinter.print("You ask for help, recieving a list of commands if you do not specify one.");
    				TextPrinter.print("Or you ask for help about a specific command, getting the syntax and purpose of it");
    				break;
    		}
    	}
    }
}
