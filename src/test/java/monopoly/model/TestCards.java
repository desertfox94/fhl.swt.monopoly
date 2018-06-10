package monopoly.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.core.cards.*;

public class TestCards {
	@Test
	public void testDividendCard() {
		Player player = new Player();		
		assertEquals(0.0, player.getBalance().intValue(), 0.00001);
		Card dividendCard = new DividendCard();
		assertTrue(dividendCard.execute(player));
		assertEquals(1000.0, player.getBalance().intValue(), 0.00001);
	}
}
