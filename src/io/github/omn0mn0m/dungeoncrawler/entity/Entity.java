package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.dungeoncrawler.Game;

import java.util.Random;

/**
 * This is an abstract class for all the basics of an entity in the game.
 * @author Nam Tran
 *
 */
public abstract class Entity {
	
	protected String name, description;	// String info for an entity
    protected int attack, defense, health;	// Stats for an entity
    protected int xp; // Experience points for an entity
    protected boolean alive = true;		// Whether an entity is alive or not
    
    protected Random random = new Random();	// Used for random numbers
    
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
    public Entity(String name, int health, int defense, int attack) {
		this.name = name;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}
    
    /**
     * Constructor which allows the setting of name, description, health, defense, and attack
     * @param name
     * @param description
     * @param health
     * @param defense
     * @param attack
     */
    public Entity(String name, String description, int health, int defense, int attack) {
		this(name, health, defense, attack);
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
            default:
                break;
        }
    }
    
    /**
     * Prints the stats of an entity.
     */
    public void printStats() {
    	Game.print("Name: " + name);
    	Game.print("Health: " + health);
    	Game.print("Attack: " + attack);
    	Game.print("Defense: " + defense);
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
            Game.print(attacker.getName() + " deals " + attackDamage + " to " + this.name + ".");
        } else {
        	Game.print(this.name + " gets attacked by " + attacker.getName() + ", but it does no damage.");
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
