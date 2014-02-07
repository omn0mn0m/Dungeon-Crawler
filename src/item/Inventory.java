package item;


public class Inventory {
	
	private Item[] inventory;
	
	public Inventory(int size) {
		inventory = new Item[size];
	}
	
	public void checkInventory() {
    	for (int i = 0; i < inventory.length; i++) {
    		if (!this.slotEmpty(i)) {
    			System.out.println(i + ". " + inventory[i].getName());
    		} else {
    			System.out.println(i + ". Empty Slot");
    		}
    	}
    }
	
	public String checkSlot(int slot) {
		return inventory[slot].getName();
	}
    
    public void add(int slot, Item item) {
    	inventory[slot] = item;
    }
    
    public void addItem(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
    		if (this.slotEmpty(i)) {
    			add(i, item);
    			break;
    		}
    	}
    }
    
    public void removeSlot(int slot) {
    	inventory[slot] = null;
    }
    
    public void removeItem(Item item) {
    	for (int i = 0; i < inventory.length; i++) {
    		if (!this.slotEmpty(i)) {
    			if (inventory[i] == item) {
    				removeSlot(i);
    				break;
    			}
    		}
    	}
    }
    
    public void clear() {
    	for (int i = 0; i < inventory.length; i++) {
    		this.removeSlot(i);
    	}
    }
    
    public boolean slotEmpty(int slot) {
    	return (inventory[slot] == null);
    }
}
