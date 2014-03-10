package entity;

import java.util.Random;
import util.NamReader;

public abstract class Entity {
	
	protected String name, description;
    protected int attack, defense, health;
    
    protected NamReader namReader = new NamReader();
    protected Random random = new Random();
    
    public Entity() {
    	
    }
    
    public Entity(String name, int health, int defense, int attack) {
		this.name = name;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}
    
    public Entity(String name, String description, int health, int defense, int attack) {
		this.name = name;
		this.description = description;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}
    
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
    
    public void takeDamage(Entity attacker, int attackBuff) {
        int attackDamage = (attacker.getStat("attack") + attackBuff) - this.defense;
        if (attackDamage > 0) {
            this.health -= attackDamage;
            System.out.println(attacker.getName() + " deals " + attackDamage + " to " + this.name + ".");
        } else {
            System.out.println(this.name + " gets attacked, but it does no damage.");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setClassStats(int health, int attack, int defense) {
    	this.health = health;
        this.attack = attack;
        this.defense = defense;
    }
    
    public void setClassStats(String name, int health, int attack, int defense) {
    	this.name = name;
    	this.health = health;
        this.attack = attack;
        this.defense = defense;
    }
    
    public void setClassStats(String name, String description, int health, int attack, int defense) {
    	this.name = name;
    	this.description = description;
    	this.health = health;
        this.attack = attack;
        this.defense = defense;
    }
}
