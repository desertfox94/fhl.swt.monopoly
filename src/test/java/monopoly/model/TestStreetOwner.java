package monopoly.model;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Florian Nickel
 */
public class TestStreetOwner {
    @Test
    public void testAddStreet(){
        Player player = new Player();
        player.addToInventory(new Street());
        assertFalse(player.getStreets().isEmpty());
    }

    @Test
    public void testRemoveStreet(){
    	Player player = new Player();
        Street street = new Street();
        player.addToInventory(street);
        player.removeFromInventory(street);
        assertTrue(player.getStreets().isEmpty());
    }
}
