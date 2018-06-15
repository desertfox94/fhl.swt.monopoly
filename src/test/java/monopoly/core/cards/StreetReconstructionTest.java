package monopoly.core.cards;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetDetails;

public class StreetReconstructionTest {

	@Test
	public void testWithOneStreet() {
		StreetReconstruction card = new StreetReconstruction();
		Player player = new Player();
		int balance = 15000;
		player.setBalance(balance);
		card.execute(player);
		assertTrue(player.getBalance() == balance);
		
		Street s1 = createStreet();
		player.addToInventory(s1);

		card.execute(player);
		assertTrue(player.getBalance() == balance);
		
		int numberOfHouses = 2;
		s1.buildHouses(numberOfHouses);
		card.execute(player);
		
		balance = balance - numberOfHouses * StreetReconstruction.COSTS_PER_HOUSE;
		assertTrue(player.getBalance() == balance);
	}
	
	@Test
	public void testWithMultipleStreets() {
		Player player = new Player();
		int balance = 15000;
		player.setBalance(balance);
		
		Street s1 = createStreet();
		int numberOfHouses = 2;
		s1.buildHouses(numberOfHouses);
		player.addToInventory(s1);
		
		// add some more dummy streets
		player.addToInventory(createStreet());
		player.addToInventory(createStreet());
		player.addToInventory(createStreet());
		
		new StreetReconstruction().execute(player);
		balance = balance - numberOfHouses * StreetReconstruction.COSTS_PER_HOUSE;
		assertTrue(player.getBalance() == balance);
	}
	
	@Test
	public void testWithMultipleStreetsAndHouses() {
		Player player = new Player();
		int balance = 15000;
		player.setBalance(balance);
		
		Street s1 = createStreet();
		Street s2 = createStreet();
		Street s3 = createStreet();
		Street s4 = createStreet();
		
		int numberOfHouses = 0;
		s1.buildHouses(2);
		numberOfHouses += 2;
		s2.buildHouses(3);
		numberOfHouses += 3;
		s3.buildHouses(4);
		numberOfHouses += 4;
		
		player.addToInventory(s1);
		player.addToInventory(s2);
		player.addToInventory(s3);
		player.addToInventory(s4);
		
		new StreetReconstruction().execute(player);
		balance = balance - numberOfHouses * StreetReconstruction.COSTS_PER_HOUSE;
		assertTrue(player.getBalance() == balance);
	}
	
	@Test
	public void testWithHousesAndHotels() {
		Player player = new Player();
		int balance = 15000;
		player.setBalance(balance);
		
		Street s1 = createStreet();
		Street s2 = createStreet();
		Street s3 = createStreet();
		
		int numberOfHouses = 0;
		int numberOfHotels = 0;
		s1.buildHouses(2);
		numberOfHouses += 2;
		s2.buildHotel();
		numberOfHotels++;
		s3.buildHotel();
		numberOfHotels++;
		
		player.addToInventory(s1);
		player.addToInventory(s2);
		player.addToInventory(s3);
		
		new StreetReconstruction().execute(player);
		balance = balance - numberOfHouses * StreetReconstruction.COSTS_PER_HOUSE - numberOfHotels * StreetReconstruction.COSTS_PER_HOTEL;
		assertTrue(player.getBalance() == balance);
	}

	private Street createStreet() {
		Street street = new Street();
		street.setRentDetails(new StreetDetails());
		return street;
	}
	

}
