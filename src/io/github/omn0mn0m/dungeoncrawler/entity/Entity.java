package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.util.NamReader;
import io.github.omn0mn0m.util.TextPrinter;

import java.util.Random;

/**
 * This is an abstract class for all the basics of an entity in the game.
 * @author Nam Tran
 *
 */
public abstract class Entity {
	
	protected String name, description;	// String info for an entity
    protected int attack, defense, health;	// Stats for an entity
    protected int xp, level; // Experience points and level for an entity
    protected boolean alive = true;		// Whether an entity is alive or not
    
    protected Random random = new Random();	// Used for random numbers
    protected NamReader namReader = new NamReader(); //NamReader for reading the list values out of a .nam file
    
    /**
     * Constructor
     */
    public Entity() {
    	
    }
    
    /**
     * Constructor which allows the setting of name, health, defense, and attack
     * @param name
     * @param health
     * @param defense
     * @param attack
     */
    public Entity(String name, int health, int defense, int attack, int xp) {
		this.name = name;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
		this.xp = xp;
		level = 1;
	}
    
    /**
     * Constructor which allows the setting of name, description, health, defense, and attack
     * @param name
     * @param description
     * @param health
     * @param defense
     * @param attack
     */
    public Entity(String name, String description, int health, int defense, int attack, int xp) {
		this(name, health, defense, attack, xp);
		this.description = description;
	}
    
    /**
     * Returns the value of a specified stat.
     * @param stat
     * @return stat
     */
    public int getStat(String stat) {
        int displayedStat = 0;
        switch (stat) {
            case "health":
                return health;
            case "defense":
                return defense;
            case "attack":
                return attack;
            case "xp":
            	return xp;
            case "level":
            	return level;
            default:
                break;
        }
        return displayedStat;
    }
    
    /**
     * Sets the specified stat to a new value.
     * @param stat
     * @param change
     */
    public void setStat(String stat, int change) {
        switch (stat) {
            case "health":
                this.health = change;
                break;
            case "attack":
                this.attack = change;
                break;
            case "defense":
                this.defense = change;
                break;
            case "xp":
            	this.xp = change;
            	break;
            case "level":
            	this.level = change;
            	break;
            default:
                break;
        }
    }
    
    /**
     * Prints the stats of an entity.
     */
    public void printStats() {
    	TextPrinter.print("Name: " + name);
    	TextPrinter.print("Health: " + health);
    	TextPrinter.print("Attack: " + attack);
    	TextPrinter.print("Defense: " + defense);
    	TextPrinter.print("XP: " + xp);
    	TextPrinter.print("Level: " + level);
    }
    
    /**
     * Determines if an attacker's attack does damage and then deals it if it does.
     * @param attacker
     * @param attackBuff
     */
    public void takeDamage(Entity attacker, int attackBuff) {
        int attackDamage = (attacker.getStat("attack") + attackBuff) - this.defense;
        if (attackDamage > 0) {
            this.health -= attackDamage;
            TextPrinter.print(attacker.getName() + " deals " + attackDamage + " to " + this.name + ".");
        } else {
        	TextPrinter.print(this.name + " gets attacked by " + attacker.getName() + ", but it does no damage.");
        }
    }
    
    /**
     * Returns the name of an entity.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of an entity.
     * @param name
     */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
     * Returns if the entity is alive.
     * @return alive
     */
    public boolean isAlive() {
    	return alive;
    }
    
    /**
     * Sets whether the entity is alive.
     * @param alive
     */
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
    
    /**
     * Checks if the entity has enough health to be alive and changes accordingly.
     */
    public void checkHealth() {
    	if (health <= 0) {
            alive = false;
        }
    }
    
    /**
     * Checks if the entity has enough xp for the next level, and acts accordingly.
     */
    public void checkXP() {
    	int neededXp = 25 * level;
    	while (xp >= neededXp) {
    		xp -= neededXp;
    		level += 1;
    		
    		attack += level;
    		defense += level;
    		health += level;
    		
    		TextPrinter.print(name + " has gained a level!");
    		neededXp = 25 * level;
    	}
    }
    
    /**
     * Gives the xp of the entity to another entity.
     * @param target
     */
    public void giveXP(Entity target) {
    	target.setStat("xp", (target.getStat("xp") + this.getStat("xp")));
    }
    
    /**
     * Sets the health, attack, and defense of an entity.
     * @param health
     * @param attack
     * @param defense
     */
    public void setStats(int health, int attack, int defense) {
    	this.health = health;
        this.attack = attack;
        this.defense = defense;
    }
    
    /**
     * Sets the name, health, attack, and defense of an entity.
     * @param name
     * @param health
     * @param attack
     * @param defense
     */
    public void setStats(String name, int health, int attack, int defense) {
    	this.name = name;
    	setStats(health, attack, defense);
    }
    
    /**
     * Sets the name, description, health, attack, and defense of an entity.
     * @param name
     * @param description
     * @param health
     * @param attack
     * @param defense
     */
    public void setStats(String name, String description, int health, int attack, int defense) {
    	this.description = description;
    	setStats(name, health, attack, defense);
    }
}
