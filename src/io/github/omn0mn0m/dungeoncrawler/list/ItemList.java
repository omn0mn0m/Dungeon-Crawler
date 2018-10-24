package io.github.omn0mn0m.dungeoncrawler.list;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.util.NamReader;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a list of all the items in the game.
 * @author Nam Tran
 *
 */
public class ItemList extends NamList {

    private Item[] values;	// List of items
    private Map<String, Item> itemsMap;	// Map of keys and which item they correspond to
    private String fileName = "Items.nam";	// Name of the file for the list of items
    private int totalItems;		// Total items in the game
    
    /**
	 * Constructor that loads up the list
	 */
	public ItemList() {
		totalItems = namReader.getTotal(fileName);
		this.loadKeys();
		this.loadValues();
		this.mapItems();
	}
	
	/**
	 * Returns an item that corresponds to a specified key.
	 * @param key - The element to be searched for
	 * @return item
	 */
	public Item getItem(String key) {
		return itemsMap.get(key);
	}
	
	/**
	 * Loads every key in the items file and assigns it to an array.
	 */
	public void loadKeys() {
		keys = new String[totalItems];
		namReader.loadFile(fileName);
		for (int i = 0; i < totalItems; i++) {
			namReader.findData(i + "-Key");
			keys[i] = namReader.getStringData().toLowerCase();
		}
		namReader.unloadFile();
	}
	
	/**
	 * Loads every item in the items list and assigns it to an array.
	 */
	public void loadValues() {
		values = new Item[totalItems];
		namReader.loadFile(fileName);
		for (int i = 0; i < totalItems; i++) {
			namReader.findData(String.valueOf(i + "-Name"));
	    	String name = namReader.getStringData();
	    	
	    	namReader.findData(String.valueOf(i + "-Type"));
	    	String type = namReader.getStringData().toLowerCase();
	    	
	    	namReader.findData(String.valueOf(i + "-AttackBuff"));
	    	int attackBuff = namReader.getIntData();
	    	
	    	namReader.findData(String.valueOf(i + "-DefenseBuff"));
	    	int defenseBuff = namReader.getIntData();
	    	
	    	namReader.findData(String.valueOf(i + "-HealthBuff"));
	    	int healthBuff = namReader.getIntData();
	    	
			values[i] = new Item(name, type, attackBuff, defenseBuff, healthBuff);
		}
		namReader.unloadFile();
	}
	
	/**
	 * Matches the keys array and the items array to a map.
	 */
	public void mapItems() {
		itemsMap  = new HashMap<>(totalItems);
		for (int i = 0; i < totalItems; i++) {
			itemsMap.put(keys[i], values[i]);
		}
	}
	
	/**
	 * Gets the key based on where it appears in the items file.
	 * @param number - The index of the key
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
