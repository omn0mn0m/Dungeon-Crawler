package list;

import java.util.HashMap;
import java.util.Map;

import entity.Hostile;
import game.Game;

/**
 * This is a list of all the hostiles in the game.
 * @author Nam Tran
 *
 */
public class HostileList {
    
    private String[] keys;	// List of keys for each hostile
    private Hostile[] values;	// List of hostiles
    private Map<String, Hostile> hostileMap;	// Map of keys and which hostile they correspond to
    
    private String fileName = "resources/Hostile.nam";	// Name of the file for the list of hostiles
    
    private int totalHostiles = 0;	// Total attacks in the game
    
    /**
	 * Constructor that loads up the list
	 */
	public HostileList() {
		this.loadTotalHostiles();
		this.loadKeys();
		this.loadValues();
		this.mapHostiles();
	}
	
	/**
	 * Returns a hostile that corresponds to a specified key.
	 * @param key
	 * @return
	 */
	public Hostile getHostile(String key) {
		return hostileMap.get(key);
	}
	
	/**
	 * Loads the number of total hostiles from the file.
	 */
	public void loadTotalHostiles() {
		Game.namReader.loadFile(fileName);
		Game.namReader.findData("Total");
		totalHostiles = Game.namReader.getIntData();
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every key in the hostile file and assigns it to an array.
	 */
	public void loadKeys() {
		keys = new String[totalHostiles];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalHostiles; i++) {
			Game.namReader.findData(i + "-Key");
			keys[i] = Game.namReader.getStringData();
		}
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every hostile in the hostile list and assigns it to an array.
	 */
	public void loadValues() {
		values = new Hostile[totalHostiles];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalHostiles; i++) {
			Game.namReader.findData(String.valueOf(i + "-Name"));
	    	String name = Game.namReader.getStringData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-Health"));
	    	int health = Game.namReader.getIntData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-Attack"));
	    	int attack = Game.namReader.getIntData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-Defense"));
	    	int defense = Game.namReader.getIntData();
	    	
			values[i] = new Hostile(name, health, defense, attack);
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
	
	/**
	 * Gets the key based on where it appears in the hostiles file.
	 * @param number
	 * @return
	 */
	public String getKey(int number) {
		return keys[number];
	}
}
