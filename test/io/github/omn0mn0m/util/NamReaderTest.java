package io.github.omn0mn0m.util;

import io.github.omn0mn0m.dungeoncrawler.entity.Attack;
import io.github.omn0mn0m.dungeoncrawler.entity.Hostile;
import io.github.omn0mn0m.dungeoncrawler.item.Item;
import io.github.omn0mn0m.dungeoncrawler.list.AttackList;
import io.github.omn0mn0m.dungeoncrawler.list.HostileList;
import io.github.omn0mn0m.dungeoncrawler.list.ItemList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * @author Petteri Salonurmi
 *
 * Unit tests for testing the different List classes (Hostile, Item, Attack...) behave as expected.
 */
public class NamReaderTest {

    @Test
    public void testHostileListConstructor_retrieveValidElementFromList() {
        //Arrange
        HostileList testList = new HostileList();
        //Act
        String key = "Fairy";
        Hostile retrievedHostile = testList.getHostile(key.toLowerCase());
        //Assert
        assertEquals("The retrieved Hostile should be " + key, key, retrievedHostile.getName());
    }

    @Test
    public void testItemListConstructor_retrieveValidElementFromList() {
        //Arrange
        ItemList testList = new ItemList();
        //Act
        String key = "Sword";
        Item retrievedItem = testList.getItem(key.toLowerCase());
        //Assert
        assertEquals("The retrieved Item should be " + key, key, retrievedItem.getName());
    }

    @Test
    public void testAttackListConstructor_retrieveValidElementFromList() {
        //Arrange
        AttackList testList = new AttackList();
        //Act
        String key = "Punch";
        Attack retrievedAttack = testList.getAttack(key.toLowerCase());
        //Arrange
        assertEquals("The retrieved Attack should be " + key, key, retrievedAttack.getAttackName());
    }

    @Test
    public void testPlayerClasses_validElementFromListWarrior() {
        //Arrange
        String fileName = "Player Classes.nam";
        NamReader namReader = new NamReader();
        String className = "Warrior";
        //Act
        namReader.loadFile(fileName);
        namReader.findData(String.valueOf(className + "-Health"));
        int health = namReader.getIntData();
        namReader.unloadFile();
        //Assert
        assertEquals("The health of a " + className + "should be 175.", 175, health);
    }

}
