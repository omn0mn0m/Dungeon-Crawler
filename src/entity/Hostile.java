package entity;

public class Hostile extends Entity {

    public Hostile() {
    	this.name = "DEAD";
    }

    public void checkHealth(Hero hero) {
        if (this.health <= 0) {
            System.out.println(hero.getName() + " killed a " + name);
            this.name = "DEAD";
        } else {
        }
    }
    
    public void generateMob() {
    	int mobNumber = random.nextInt(4);
    	namReader.loadFile("resources/Hostile.nam");
    	
    	namReader.findData(String.valueOf(mobNumber + "-Name"));
    	name = namReader.getStringData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Health"));
    	health = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Attack"));
    	attack = namReader.getIntData();
    	
    	namReader.findData(String.valueOf(mobNumber + "-Defense"));
    	defense = namReader.getIntData();
    	
    	namReader.unloadFile();
    }
}
