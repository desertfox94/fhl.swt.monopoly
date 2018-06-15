
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
		int playerMoney = player.getBalance();
		player.addToInventory(street);
		
		// Hypothek aufnhemen
		street.assumeMortgage();
		
		// Hypothek abbezahlen
		street.redeemMortgage();
		int newMoney = player.getBalance();
		assertEquals(playerMoney, newMoney);
	}
	
	@Test
	public void DontReedemMoratgeWithoutMortage() {
		Player player = new Player();
		Street street = new Street();
		street.setPrice(1000);
		street.setOwner(player);
		int playerMoney = player.getBalance();
		player.addToInventory(street);
		
		// Hypothek abbezahlen
		street.redeemMortgage();
		int newMoney = player.getBalance();
		assertEquals(playerMoney, newMoney);
	}
}
