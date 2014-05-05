package io.github.omn0mn0m.dungeoncrawler.list;

import io.github.omn0mn0m.dungeoncrawler.Game;
import io.github.omn0mn0m.dungeoncrawler.entity.Attack;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a list of all the attacks in the game.
 * @author Nam Tran
 *
 */
public class AttackList {
	
	private String[] keys;	// List of keys for each attack
	private Attack[] values;	// List of attacks
	private Map<String, Attack> attacksMap;	// Map of keys and which attack they correspond to
	
	private String fileName = Game.rootPath + "Attacks.nam";	// Name of the file for the list of attacks
	
	private int totalAttacks = 0;	// Total attacks in the game
	
	/**
	 * Constructor that loads up the list
	 */
	public AttackList() {
		this.loadTotalAttacks();
		this.loadKeys();
		this.loadValues();
		this.mapAttacks();
	}

	/**
	 * Returns an attack that corresponds to a specified key.
	 * @param key
	 * @return attack
	 */
	public Attack getAttack(String key) {
		return attacksMap.get(key);
	}
	
	/**
	 * Loads the number of total attacks from the file.
	 */
	public void loadTotalAttacks() {
		Game.namReader.loadFile(fileName);
		Game.namReader.findData("Total");
		totalAttacks = Game.namReader.getIntData();
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every key in the attack file and assigns it to an array.
	 */
	public void loadKeys() {
		keys = new String[totalAttacks];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalAttacks; i++) {
			Game.namReader.findData(i + "-Key");
			keys[i] = Game.namReader.getStringData().toLowerCase();
		}
		Game.namReader.unloadFile();
	}
	
	/**
	 * Loads every attack in the attack list and assigns it to an array.
	 */
	public void loadValues() {
		values = new Attack[totalAttacks];
		Game.namReader.loadFile(fileName);
		for (int i = 0; i < totalAttacks; i++) {
			Game.namReader.findData(String.valueOf(i + "-Name"));
	    	String name = Game.namReader.getStringData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-Requires"));
	    	String requires = Game.namReader.getStringData();
	    	
	    	Game.namReader.findData(String.valueOf(i + "-AttackBuff"));
	    	int attackBuff = Game.namReader.getIntData();
	    	
			values[i] = new Attack(name, requires, attackBuff);
		}
		Game.namReader.unloadFile();
	}
	
	/**
	 * Matches the keys array and the attacks array to a map.
	 */
	public void mapAttacks() {
		attacksMap  = new HashMap<String, Attack>(totalAttacks);
		for (int i = 0; i < totalAttacks; i++) {
			attacksMap.put(keys[i], values[i]);
		}
	}
}
