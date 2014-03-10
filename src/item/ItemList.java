package item;

import java.util.HashMap;
import java.util.Map;

import util.NamReader;

public class ItemList {
	
	private NamReader namReader = new NamReader();
    
    private String[] keys;
    private Item[] values;
    private Map<String, Item> itemsMap;
    
    private int totalItems = 0;
    
	public ItemList() {
		this.loadKeys();
		this.loadValues();
		this.mapItems();
	}
	
	public Item getItem(String key) {
		return itemsMap.get(key);
	}
	
	public void getTotalItems() {
		namReader.loadFile("resources/Items.nam");
		namReader.findData("Total");
		totalItems = namReader.getIntData();
		namReader.unloadFile();
	}
	
	public void loadKeys() {
		keys = new String[totalItems];
		namReader.loadFile("resources/Items.nam");
		for (int i = 0; i < keys.length; i++) {
			namReader.findData(i + "-Key");
			keys[i] = namReader.getStringData().toLowerCase();
		}
		namReader.unloadFile();
	}
	
	public void loadValues() {
		values = new Item[totalItems];
		namReader.loadFile("resources/Items.nam");
		for (int i = 0; i < values.length; i++) {
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
	}
	
	public void mapItems() {
		itemsMap  = new HashMap<String, Item>(totalItems);
		for (int i = 0; i < totalItems; i++) {
			itemsMap.put(keys[i], values[i]);
		}
	}
}
