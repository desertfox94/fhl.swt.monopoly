package monopoly.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRedeemMortgage {

	@Test
	public void reedemMortgage() {
		Player player = new Player();
		Street street = new Street();
		street.setPrice(1000);
		street.setOwner(player);
		int playerMoney = player.getBalance().intValue();
		player.addToInventory(street);
		
		// Hypothek aufnhemen
		street.assumeMortgage();
		
		// Hypothek abbezahlen
		street.redeemMortgage();
		int newMoney = player.getBalance().intValue();
		assertEquals(playerMoney, newMoney);
	}
	
	@Test
	public void dontReedemMortgageWithoutMortgage() {
		Player player = new Player();
		Street street = new Street();
		street.setPrice(1000);
		street.setOwner(player);
		int playerMoney = player.getBalance().intValue();
		player.addToInventory(street);
		
		// Hypothek abbezahlen
		street.redeemMortgage();
		int newMoney = player.getBalance().intValue();
		assertEquals(playerMoney, newMoney);
	}
}
