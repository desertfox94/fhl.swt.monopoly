package monopoly.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRedeemMortage {

	@Test
	public void reedemMortage() {
		Player player = new Player();
		Street street = new Street();
		street.setPrice(1000);
		street.setOwner(player);
		int playerMoney = player.getBalance().intValue();
		player.addToInventory(street);
		
		// Hypothek aufnhemen
		street.assumeMortage();
		
		// Hypothek abbezahlen
		street.redeemMortage();
		int newMoney = player.getBalance().intValue();
		assertEquals(playerMoney, newMoney);
	}
	
	@Test
	public void DontReedemMoratgeWithoutMortage() {
		Player player = new Player();
		Street street = new Street();
		street.setPrice(1000);
		street.setOwner(player);
		int playerMoney = player.getBalance().intValue();
		player.addToInventory(street);
		
		// Hypothek abbezahlen
		street.redeemMortage();
		int newMoney = player.getBalance().intValue();
		assertEquals(playerMoney, newMoney);
	}
}
