package io.github.omn0mn0m.dungeoncrawler.location;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.entity.Entity;
import io.github.omn0mn0m.dungeoncrawler.entity.Hostile;
import io.github.omn0mn0m.dungeoncrawler.item.Inventory;

public class LocationMap {
	
	private int roomsCleared = 0;
	private int xLength;
	private int yLength;
	private int currentRoomX = 0;
	private int currentRoomY = 0;
	
	private Location[][] locationMap;
	
	public LocationMap(int x, int y) {
		xLength = x;
		yLength = y;
		locationMap = new Location[xLength][yLength];
	}

	public void createRoom(int x, int y, int hostiles, int items) {
		locationMap[x][y] = new Location(hostiles, items);
	}
	
	public void createRoomAtPlayer(int hostiles, int items) {
		createRoom(currentRoomX, currentRoomY, hostiles, items);
	}
	
	public void enterMapLocation(int x, int y, Entity entity) {
		locationMap[x][y].enterLocation(entity);
	}
	
	public void enterCurrentMapLocation(Entity entity) {
		enterMapLocation(currentRoomX, currentRoomY, entity);
	}
	
	public void moveTo(int x, int y, Entity entity) {
		boolean moved = false;
		if (!getCurrentLocation().hasHostiles()) {
			if (currentRoomX + x >= 0 && currentRoomX + x <= xLength && x != 0) {
				currentRoomX += x;
				moved = true;
			}
		
			if (currentRoomY + y >= 0 && currentRoomY + y <= yLength && y != 0) {
				currentRoomY += y;
				moved = true;
			}
		
			if (moved) {
				if (getCurrentLocation() == null) {
					roomsCleared += 1;
					createRoomAtPlayer(Game.random.nextInt(3), Game.random.nextInt(Game.itemList.getTotalItems()));
				}
				getCurrentLocation().enterLocation(entity);
			} else {
				Game.print("You cannot move further in that direction...");
			}
		} else {
			Game.print("You try to run, but are stopped!");
		}
	}
	
	public void resetMap() {
		for (int i = 0; i < xLength; i++) {
			for (int c = 0; c < yLength; i++) {
				if (locationMap[i][c] != null) {
					locationMap[i][c] = null;
				}
			}
        }
	}
	
	public void resetPlayerLocation(Entity entity) {
		currentRoomX = 0;
		currentRoomY = 0;
		createRoomAtPlayer(0, Game.random.nextInt(Game.itemList.getTotalItems()));
		enterCurrentMapLocation(entity);
	}
	
	public void checkIfHostileDead(int index) {
		getCurrentLocation().checkIfHostileDead(index);
	}
	
	public void printCurrentItems() {
		locationMap[currentRoomX][currentRoomY].printItems();
	}
	
	public void printCurrentHostiles() {
		locationMap[currentRoomX][currentRoomY].printHostiles();
	}
	
	public void printAllCurrentLocationInformation() {
		printCurrentItems();
		printCurrentHostiles();
	}
	
	public void printTargetHostileStats(String target) {
		for (int i = 0; i < getHostilesInCurrentLocation(); i++) {
			if (getCurrentLocation().hostiles[i] != null && getCurrentLocation().hostiles[i].isTarget(target)) {
				getCurrentLocation().hostiles[i].printStats();
			}
		}
	}
	
	public Location getLocation(int x, int y) {
		return locationMap[x][y];
	}
	
	public Location getCurrentLocation() {
		return getLocation(currentRoomX, currentRoomY);
	}
	
	public int getHostilesInLocation(int x, int y) {
		return locationMap[x][y].hostiles.length;
	}
	
	public int getHostilesInCurrentLocation() {
		return getHostilesInLocation(currentRoomX, currentRoomY);
	}
	
	public Hostile getHostileAt(int x, int y, int index) {
		return getLocation(x, y).hostiles[index];
	}
	
	public Hostile getHostileAt(int x, int y, String name) {
		return getLocation(x, y).getLocationHostile(name);
	}
	
	public Hostile getHostileAtCurrentLocation(int index) {
		return getHostileAt(currentRoomX, currentRoomY, index);
	}
	
	public Hostile getHostileAtCurrentLocation(String name) {
		return getHostileAt(currentRoomX, currentRoomY, name);
	}
	
	public Inventory getLocationItems(int x, int y) {
		return getLocation(x, y).locationItems;
	}
	
	public Inventory getCurrentLocationItems() {
		return getLocationItems(currentRoomX, currentRoomY);
	}
	
	public int getRoomsCleared() {
		return roomsCleared;
	}
	
	public int getCurrentRoomX() {
		return currentRoomX;
	}
	
	public int getCurrentRoomY() {
		return currentRoomY;
	}
}
