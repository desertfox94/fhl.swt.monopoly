package monopoly.core.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.model.Player;

public class TestOwnableCards {

	@Test
	public void test_freeFromJail() {
		Player player = new Player();
		FreeFromJail card = new FreeFromJail();
		card.assignTo(player);
		assertEquals(player.getCards().get(0), card);
		assertEquals(player, card.getPlayer());
		player.sendToJail();
		assertTrue(player.isInJail());
		card.execute();
		assertFalse(player.isInJail());
		assertTrue(player.getCards().isEmpty());
	}

}
