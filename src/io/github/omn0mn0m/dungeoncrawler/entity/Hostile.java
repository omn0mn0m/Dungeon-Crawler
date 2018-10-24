package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.util.TextPrinter;

/**
 * This is the class for the basic hostile. It has a name, health, defense, and attack.
 * @author Nam Tran
 *
 */
public class Hostile extends Entity {
	
	/**
	 * Constructor with a name, health, defense, and attack.
	 * It sets the hostile to alive.
	 * @param name
	 * @param health
	 * @param defense
	 * @param attack
	 */
    public Hostile(String name, int health, int defense, int attack, int xp) {
    	super(name, health, defense, attack, xp);
    	alive = true;
    }
    
    /**
     * Copy-constructor
     * @param hostile
     */
    public Hostile(Hostile hostile) {
    	this(hostile.getName(), hostile.getStat("health"), hostile.getStat("defense"), hostile.getStat("attack"), hostile.getStat("xp"));
    }

    /**
     * Checks if the hostile is still alive, and changes the boolean accordingly.
     * It prints a message if the hostile is dead announcing it was killed.
     * @param hero
     */
    public void checkIfAlive(Hero hero) {
    	this.checkHealth();
        if (!alive) {
            TextPrinter.print(hero.getName() + " killed a " + name);
        }
    }
    
    public boolean isTarget(String target) {
    	return this.getName().equalsIgnoreCase(target);
    }
}
