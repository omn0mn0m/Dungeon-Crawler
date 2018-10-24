package io.github.omn0mn0m.dungeoncrawler.list;

import io.github.omn0mn0m.dungeoncrawler.entity.Hostile;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a list of all the hostiles in the game.
 * @author Nam Tran
 *
 */
public class HostileList extends NamList {

    private Hostile[] values;	// List of hostiles
    private Map<String, Hostile> hostileMap;	// Map of keys and which hostile they correspond to
    private String fileName = "Hostile.nam";	// Name of the file for the list of hostiles
    private int totalHostiles;	// Total hostiles in the game
    
    /**
	 * Constructor that loads up the list
	 */
	public HostileList() {
		totalHostiles = namReader.getTotal(fileName);
		this.loadKeys();
		this.loadValues();
		this.mapHostiles();
	}
	
	/**
	 * Returns a hostile that corresponds to a specified key.
	 * @param key - The hostile to be searched
	 * @return a Hostile
	 */
	public Hostile getHostile(String key) {
		return hostileMap.get(key);
	}
	
	/**
	 * Loads every key in the hostile file and assigns it to an array.
	 */
	public void loadKeys() {
		keys = new String[totalHostiles];
		namReader.loadFile(fileName);
		for (int i = 0; i < totalHostiles; i++) {
			namReader.findData(i + "-Key");
			keys[i] = namReader.getStringData();
		}
		namReader.unloadFile();
	}
	
	/**
	 * Loads every hostile in the hostile list and assigns it to an array.
	 */
	public void loadValues() {
		values = new Hostile[totalHostiles];
		namReader.loadFile(fileName);
		for (int i = 0; i < totalHostiles; i++) {
			namReader.findData(String.valueOf(i + "-Name"));
	    	String name = namReader.getStringData();
	    	
	    	namReader.findData(String.valueOf(i + "-Health"));
	    	int health = namReader.getIntData();
	    	
	    	namReader.findData(String.valueOf(i + "-Attack"));
	    	int attack = namReader.getIntData();
	    	
	    	namReader.findData(String.valueOf(i + "-Defense"));
	    	int defense = namReader.getIntData();
	    	
	    	namReader.findData(String.valueOf(i + "-XP"));
	    	int xp = namReader.getIntData();
	    	
			values[i] = new Hostile(name, health, defense, attack, xp);
		}
	}
	
	/**
	 * Matches the keys array and the hostiles array to a map.
	 */
	public void mapHostiles() {
		hostileMap  = new HashMap<String, Hostile>(totalHostiles);
		for (int i = 0; i < totalHostiles; i++) {
			hostileMap.put(keys[i], values[i]);
		}
	}
	
	/**
	 * Returns the total hostiles in the game.
	 * @return total hostiles
	 */
	public int getTotalHostiles() {
		return totalHostiles;
	}

}
