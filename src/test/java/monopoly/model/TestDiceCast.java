package monopoly.model;


import org.junit.Test;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static org.junit.Assert.assertTrue;

/**
 * @author Jake Stradling
 */
public class TestDiceCast {

    @Test
    public void testCurrentAndNext(){
        DiceCast diceCast = new DiceCast();
        int roll = diceCast.next();
        assertTrue(roll == diceCast.current());
        if(roll > 12 || roll < 2)
            assertTrue(false);

    }

    @Test
    public void TestDouble(){
        DiceCast diceCast = new DiceCast();
        while (!diceCast.isDouble())
            diceCast.next();
        int roll = diceCast.next();
        if(roll % 2 != 0)
            assertTrue(false);
    }


    @Test void TestAddListeners() {
        DiceCast diceCast = new DiceCast();
        //Todo testing adding the changeListeners
    }
}
