package io.github.omn0mn0m.dungeoncrawler.item;

import io.github.omn0mn0m.dungeoncrawler.Game;

/**
 * This is the basic inventory for items. It has all the methods needed for basic
 * inventory functionality, so it can be used for almost all item management cases.
 * @author Nam Tran
 *
 */
public class Inventory {
	
	private Item[] inventory; // Array that the inventory item objects are stored in
	
	/**
	 * Constructor for an inventory of a specified size.
	 * @param size
	 */
	public Inventory(int size) {
		inventory = new Item[size];
	}
	
	/**
	 * Prints out the inventory, with the option to show or not show empty slots.
	 * @param showEmpty
	 */
	public void checkInventory(boolean showEmpty) {
		if (showEmpty) {
	    	for (int i = 0; i < inventory.length; i++) {
	    		if (!this.slotEmpty(i)) {
	    			Game.print(i + ". " + inventory[i].getName());
	    		} else {
	    			Game.print(i + ". Empty Slot");
	    		}
	    	}
		} else {
			for (int i = 0; i < inventory.length; i++) {
	    		if (!this.slotEmpty(i)) {
	    			Game.print("- " + inventory[i].getName());
	    		}
	    	}
		}
    }
	
	/**
	 * Checks a slot and returns its value.
	 * @param slot
	 * @return item
	 */
	public Item checkSlot(int slot) {
		return inventory[slot];
	}
    
	/**
	 * Adds an item to a specified slot, regardless to what is already present.
	 * @param slot
	 * @param item
	 */
    public void add(int slot, Item item) {
    	inventory[slot] = item;
    }
    
    /**
     * Adds an item to the first available inventory slot.
     * @param item
     */
    public void addItem(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
    		if (this.slotEmpty(i)) {
    			add(i, new Item(item));
    			break;
    		}
    	}
    }
    
    /**
     * Clears an inventory slot of whatever is present.
     * @param slot
     */
    public void removeSlot(int slot) {
    	inventory[slot] = null;
    }
    
    /**
     * Searches inventory for an item and removes it if found.
     * @param item
     */
    public void removeItem(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
			if (!this.slotEmpty(i) && checkSlot(i).isTarget(item.getName())) {
				removeSlot(i);
				break;
			}
    	}
    }
    
    /**
     * Clears entire inventory.
     */
    public void clear() {
    	for (int i = 0; i < inventory.length; i++) {
    		this.removeSlot(i);
    	}
    }
    
    /**
     * Returns if a specified slot is empty.
     * @param slot
     * @return If slot is empty
     */
    public boolean slotEmpty(int slot) {
    	return (checkSlot(slot) == null);
    }
    
    /**
     * Returns if the inventory has a specified item.
     * @param item
     * @return If has item
     */
    public boolean hasItem(Item item) {
    	boolean hasItem = false;
    	for (int i = 0; i < inventory.length; i++) {
    		if (!slotEmpty(i) && checkSlot(i).isTarget(item.getName())) {
    			hasItem = true;
    			break;
    		}
    	}
    	return hasItem;
    }
    
    /**
     * Returns the size of the inventory.
     * @return size
     */
    public int getSize() {
    	return inventory.length;
    }
}
