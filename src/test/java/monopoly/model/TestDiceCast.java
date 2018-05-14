package monopoly.model;

import static org.junit.Assert.assertTrue;

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
		if (roll > 12 || roll < 2)
			assertTrue(false);

	}

	@Test
	public void TestDouble() {
		DiceCast diceCast = new DiceCast();
		while (!diceCast.isDouble())
			diceCast.next();
		int roll = diceCast.next();
		if (roll % 2 != 0)
			assertTrue(false);
	}

	@Test
	public void TestAddListeners() {
		DiceCast diceCast = new DiceCast();
		// Todo testing adding the changeListeners
	}
}
