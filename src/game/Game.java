package game;

import item.ItemList;
import entity.Hero;
import entity.Hostile;

public class Game {

    private Hero hero = new Hero();
    private Hostile hostile = new Hostile();
    private ItemList itemList = new ItemList();

    private int numberOfRoomsPassed;

    public Game() {
    	
    }

    public void run() {
        hero.selectClass();
        while (true) {
            String command = hero.getInput();
            switch (command) {
                case "go to next room":
                    this.checkForWin();
                    this.goToNextRoom();
                    break;
                case "look around":
                	System.out.println("There is nothing of interest...");
                	break;
                case "attack":
                    hero.attack(hostile);
                    break;
                case "check health":
                    System.out.println(hero.getStat("health"));
                    break;
                case "quit":
                    this.quit();
                    break;
                case "restart":
                    this.restart();
                    break;
                case "reroll character":
                    hero.rerollCharacter();
                    break;
                case "check inventory":
                	hero.checkInventory();
                	break;
                case "remove item":
                	hero.removeItem(itemList);
                	break;
                case "add item":
                	hero.addItem(itemList);
                	break;
                default:
                    System.out.println("That is not a valid command");
                    break;
            }
        }
    }
    
    public void checkForWin() {
    	if (numberOfRoomsPassed == 10) {
            System.out.println("You walk through into the next room, but there is no more dungeon.");
            System.out.println("You have reached the end. Congradulations!");
            System.exit(0);
    	}
    }
    
    public void quit() {
    	System.out.println("Are you sure you want to quit? ");
        if(hero.getInput().equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
        	System.out.println("Resuming game then...");
        }
    }
    
    public void restart() {
    	System.out.println("Are you sure you want to restart?");
        if(hero.getInput().equalsIgnoreCase("Yes")) {
            numberOfRoomsPassed = 0;
            hostile.setName("DEAD");
            this.run();
        }
    }
    
    public void goToNextRoom() {
    	if (hostile.getName().equals("DEAD")) {
            hostile.generateMob();
            System.out.println(hero.getName() + " walks into the next room, and finds a hostile " + hostile.getName());
            numberOfRoomsPassed = numberOfRoomsPassed + 1;
        } else {
            System.out.println("You can't walk out of a fight!");
        }
    }
}
