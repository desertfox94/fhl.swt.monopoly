package monopoly.model;


import org.junit.Test;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static org.junit.Assert.assertTrue;

/**
 * @author Florian Nickel
 */
public class TestStreetOwner {
    @Test
    public void testAddStreet(){
        Payer player = new Payer();
        Player.addToInventory(new Street());
        assertTrue(player.getStreets().Count == 1);
    }

    @Test
    public void testRemoveStreet(){
        Payer player = new Payer();
        Street street = new Street();
        Player.addToInventory(street);
        player.removeFromInventory(street);
        assertTrue(player.getStreets().Count == 0);
    }
}
