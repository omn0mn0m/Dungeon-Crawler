package game;

public class Engine {

    private static Hero player = new Hero();
    private static Hostile mob = new Hostile();
    
    private static int numberOfRoomsPassed;

    public Engine() {
    }

    public void run() {
        player.selectClass();
        while (true) {
            String command = player.getInput();
            switch (command) {
                case "go to next room":
                    this.checkForWin();
                    this.goToNextRoom();
                    break;
                case "look around":
                	System.out.println("There is nothing of interest...");
                	break;
                case "attack":
                    player.attack(mob);
                    break;
                case "check health":
                    System.out.println(player.getStat("health"));
                    break;
                case "quit":
                    this.quit();
                    break;
                case "restart":
                    this.restart();
                    break;
                case "reroll character":
                    player.rerollCharacter();
                    break;
                case "open inventory":
                	player.openInventory();
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
        if(player.getInput().equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
        	System.out.println("Resuming game then...");
        }
    }
    
    public void restart() {
    	System.out.println("Are you sure you want to restart?");
        if(player.getInput().equalsIgnoreCase("Yes")) {
            numberOfRoomsPassed = 0;
            mob.setName("DEAD");
            this.run();
        }
    }
    
    public void goToNextRoom() {
    	if (mob.getName().equals("DEAD")) {
            mob.generateMob();
            numberOfRoomsPassed = numberOfRoomsPassed + 1;
        } else {
            System.out.println("You can't walk out of a fight!");
        }
    }
}
