package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.item.Inventory;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.util.TextPrinter;

public class Trader extends Entity {
	
	private String name;
	
	private Inventory inventory;

	public Trader(String name, int invSize) {
		inventory = new Inventory(invSize);
		this.name = name;
		fillInventory();
		 
		TextPrinter.print("Hey there! I'm " + this.name + ". You can trade with me.");
	}
	
	public void checkInventory() {
		inventory.checkInventory(true);
	}
	
	public void exchangeItems(String playerItem, String traderItem, Hero hero) {
		if (hero.hasInventoryItem(playerItem) && inventory.hasItem(traderItem)) {
			hero.removeItem(inventory, playerItem);
			hero.addItem(inventory, traderItem);
		} else {
			TextPrinter.print("Something was missing in the transaction!");
		}
	}
	
	public void fillInventory() {
		for (int i = 0; i < inventory.getSize(); i++) {
    		inventory.addItem(new Item(Game.itemList.getItem(Game.itemList.getKey(random.nextInt(Game.itemList.getTotalItems())))));
		}
	}
}
