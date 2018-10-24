package io.github.omn0mn0m.dungeoncrawler.list;

import io.github.omn0mn0m.util.NamReader;

/**
 * @author Petteri Salonurmi
 *
 * NamList
 * An abstract super class for all the different Lists (Hostile, Item, Attack...) that the game contains.
 * The details of a NamList are read from a given .nam file.
 *
 */
public abstract class NamList {

    protected String[] keys;	// List of keys for each element in the list
    protected NamReader namReader = new NamReader(); //NamReader for reading the list values out of a .nam file

    /**
     * Gets the key based on where it appears in the hostiles file.
     * @param number - The index of the Element to be fetched
     * @return The element found in the index given
     */
    public String getKey(int number) {
        return keys[number];
    }

}
