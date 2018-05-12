package monopoly.model;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jake Stradling
 */
public class TestDie {

    @Test
    public void testEquals(){
        Die die1 = new Die();
        Die die2 = new Die();
        die1.roll();
        die2.roll();
        while(die2.get() != die1.get()){
            die2.roll();
        }
        assertTrue(die1.equals(die2));
        assertFalse(die1.equals(new Integer(0)));

    }

    @Test
    public void testRoll(){
        Die die = new Die();
        for(int i = 0; i < 30; i++){
            int roll = die.roll();
            if(roll > 6 || roll < 1)
                assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void testGetNumber(){
        Die die = new Die();
        int roll = die.roll();
        assertTrue(die.getNumber().get() == roll);
    }

}
