package monopoly.core.cards;

import org.junit.Test;

import monopoly.model.Player;

public class StreetReconstructionTest {

	@Test
	public void test() {

		StreetReconstruction card = new StreetReconstruction();
		Player player = new Player();
		int balance = 5000;
		player.setBalance(balance);
		card.execute(player);
//		assertTrue(player.getBalanceProperty().get() == balance);
	
	}

}
