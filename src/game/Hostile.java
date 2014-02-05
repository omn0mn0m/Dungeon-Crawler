package game;

public class Hostile extends Entity {

    public Hostile() {
    	this.name = "DEAD";
    }

    public void checkHealth(Hero hero) {
        if (this.health <= 0) {
            System.out.println("You killed a " + name);
            this.name = "DEAD";
        } else {
        }
    }
    
    public void generateMob() {
        switch (random.nextInt(4)) {
            case 0:
                this.setClassStats("Fairy", 10, 1, 0);
                break;
            case 1:
                this.setClassStats("Goblin", 25, 10, 5);
                break;
            case 2:
                this.setClassStats("Vampire", 100, 20, 10);
                break;
            case 3:
                this.setClassStats("Bandit", 75, 15, 10);
                break;
            default:
                break;
        }
        System.out.println("You walk into the next room and see a hostile " + name + ".");
    }
}
