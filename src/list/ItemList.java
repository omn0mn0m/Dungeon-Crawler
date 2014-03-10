package list;

import game.Game;
import item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a list of all the items in the game.
 * @author Nam Tran
 *
 */
public class ItemList {
	
    private String[] keys;	// List of keys for each item
    private Item[] values;	// List of items
    private Map<String, Item> itemsMap;	// Map of keys and which item they correspond to
    
    private String fileName = "resources/Items.nam";	// Name of the file for the list of items
    
    private int totalItems = 0;		// Total items in the game
    
    /**
	 * Constructor that loads up the list
	 */
	public ItemList() {
		this.loadTotalItems();
		this.loadKeys();
		this.loadValues();
		this.mapItems();
	}
	
	/**
	 * Returns an item that corresponds to a specified key.
	 * @param key
	 * @return item
	 */
	public Item getItem(String key) {
		return itemsMap.get(key);
	}
	
	/**
	 * Loads the number of total items from the file.
	 */
	public void loadTotalItems() {
		Game.namReader.loadFile(fileName);
		Game.namReader.findData("Total");
		totalItems = Game.namReader.getIntData();
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every key in the items file and assigns it to an array.
	 */
	public void loadKeys() {
		keys = new String[totalItems];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalItems; i++) {
			Game.namReader.findData(i + "-Key");
			keys[i] = Game.namReader.getStringData().toLowerCase();
		}
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every item in the items list and assigns it to an array.
	 */
	public void loadValues() {
		values = new Item[totalItems];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalItems; i++) {
			Game.namReader.findData(String.valueOf(i + "-Name"));
	    	String name = Game.namReader.getStringData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-Type"));
	    	String type = Game.namReader.getStringData().toLowerCase();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-AttackBuff"));
	    	int attackBuff = Game.namReader.getIntData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-DefenseBuff"));
	    	int defenseBuff = Game.namReader.getIntData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-HealthBuff"));
	    	int healthBuff = Game.namReader.getIntData();
	    	
			values[i] = new Item(name, type, attackBuff, defenseBuff, healthBuff);
		}
		Game.namReader.unloadFile();
	}
	
	/**
	 * Matches the keys array and the items array to a map.
	 */
	public void mapItems() {
		itemsMap  = new HashMap<String, Item>(totalItems);
		for (int i = 0; i < totalItems; i++) {
			itemsMap.put(keys[i], values[i]);
		}
	}
	
	/**
	 * Gets the key based on where it appears in the items file.
	 * @param number
	 * @return key
	 */
	public String getKey(int number) {
		return keys[number];
	}
	
	/**
	 * Returns the number of total items in the game.
	 * @return total items
	 */
	public int getTotalItems() {
		return totalItems;
	}
}
