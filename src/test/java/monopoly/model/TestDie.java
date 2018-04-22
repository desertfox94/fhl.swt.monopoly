package monopoly.model;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

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

    }
}
