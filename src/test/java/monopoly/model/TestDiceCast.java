package monopoly.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Jake Stradling
 */
public class TestDiceCast {

	@Test
	public void testCurrentAndNext() {
		DiceCast diceCast = new DiceCast();
		int roll = diceCast.next();
		assertTrue(roll == diceCast.current());
		assertTrue(roll <= 12);
		assertTrue(roll >= 2);
	}

	@Test
	public void TestDouble() {
		DiceCast diceCast = new DiceCast();
		while (!diceCast.isDouble())
			diceCast.next();
		
		// 1)
		assertEquals("Ein Pasch muss durch 2 teilbar sein!", 0, diceCast.current() % 2);
		
		// 2)
		if (diceCast.current() % 2 != 0)
			fail("Ein Pasch muss durch 2 teilbar sein!");
	}

	@Test
	public void TestAddListeners() {
		DiceCast diceCast = new DiceCast();
		// Todo testing adding the changeListeners
	}
}
