package monopoly.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.core.cards.*;

public class TestCards {
	@Test
	public void testDividendCard() {
		Player player = new Player();		
		assertTrue(Double.compare(player.getBalance().doubleValue(), 0.0) == 0);
		Card dividendCard = new DividendCard();
		assertTrue(dividendCard.execute(player));
		assertTrue(Double.compare(player.getBalance().doubleValue(), 1000.0) == 0);
	}
}
