package monopoly.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * @author Christoph Thomas
 */
public class TestStreet {
	
	@Test
	public void testSetGetName() {
		Street street = new Street();
		assertTrue(street.getName() == null);
		street.setName("Stresemanstraße");
		assertTrue(street.getName().equals("Stresemanstraße"));
	}
	
	@Test
	public void testsetgetPrice() {
		Street street = new Street();
		int price = 8900;
		assertFalse(street.getPrice() == price);
		street.setPrice(price);
		assertTrue(street.getPrice() == price);
	}
	
	@Test
	public void testbuildgetdemolishHouses() {
		Street street = new Street();
		StreetDetails rentDetails = new StreetDetails();
		int price = 8900;
		rentDetails.setFirstHouseRent(price);
		
		assertTrue(street.getNumberOfHouses() == 0);	
		
		street.setRentDetails(rentDetails);
		street.buildHouses(1);
	
		assertTrue(street.getNumberOfHouses() == 1); 
		
		street.demolishHouses(1);				// demolish reduces number of houses to certrain amount
		assertTrue(street.getNumberOfHouses() == 1); 	
		
		street.demolishHouses(0);
		assertTrue(street.getNumberOfHouses() == 0); 
	}
	
	@Test
	public void testbuildgetdemolishHotel() {
		Street street = new Street();
		assertTrue(!street.getHotel());
		street.buildHotel();
		
		assertTrue(street.getHotel());
		
		street.demolishHotel();
		assertTrue(!street.getHotel());
		
		street.demolishHotel();				// shouldnt work
		assertTrue(!street.getHotel());
	}	
	
	@Test
	public void testassumeisMontage() {
		Street street = new Street();
		int price = 300;
		street.setPrice(price);
		Player owner = new Player();
		int balanceBefore = owner.getBalance();
		street.setOwner(owner);
		assertFalse(street.isMortgage());
		street.assumeMortgage();
		assertTrue(street.isMortgage());
		assertEquals(balanceBefore + price /2, owner.getBalance());
	}
	
	@Test
	public void testsetgetOwner() {
		Street street = new Street();
		Player player = new Player();
		assertTrue(street.getOwner() == null);		
		street.setOwner(player);
		assertTrue(street.getOwner().equals(player));	
		player.setName("frank");
		assertTrue(street.getOwner().getName().equals("frank"));		
	}
	
	@Test
	public void testsetgetRentDetails() {
		Street street = new Street();
		StreetDetails rentDetails = new StreetDetails();
		int price = 8900;
		
		assertTrue(street.getRentDetails() == null);	
		street.setRentDetails(rentDetails);
		assertTrue(street.getRentDetails().equals(rentDetails));		
		rentDetails.setFirstHouseRent(price);
		assertTrue(street.getRentDetails().getFirstHouseRent() == price);			
	}
	
	@Test
	public void testsetgetgroups() {
		StreetGroup group = new StreetGroup();
		int price = 8900;
		Street street = new Street();
		
		assertTrue(street.getGroup() == null);	
		street.setGroup(group);
		assertTrue(street.getGroup().equals(group));
		group.setPricePerHouse(price);
		assertTrue(street.getGroup().getPricePerHouse() == price);
	}
	
	@Test
	public void testgetrent() {
		Street street = new Street();
		StreetDetails rentDetails = new StreetDetails();
		int rent = 50;
		
		IntegerProperty rent2 = new SimpleIntegerProperty();
		rent2.set(50); // vergleichsobject
		
		assertTrue(	street.getRent().getValue() == 0);	
		
		rentDetails.setBaseRent(rent);
		street.setRentDetails(rentDetails);
		
		
		assertTrue(	street.getRent().getValue().compareTo(rent2.getValue()) == 0);					
	}
	
	@Test
	public void testgetCurrentRent() {
		Street street = new Street();
	
		
		StreetDetails rentDetails = new StreetDetails();
		int pricefirst = 8900;
		int pricesecond = 0;
		int pricethird = -3;
		int pricefourth = 40;

		assertTrue(street.getRentDetails() == null);	
		street.setRentDetails(rentDetails);
		
		street.buildHotel();
		street.getRentDetails().setHotelRent(pricefirst);
		assertTrue(street.getRentDetails().getHotelRent() == pricefirst);
		
		
		rentDetails.setFirstHouseRent(pricefirst);
		rentDetails.setSecondHouseRent(pricesecond);
		rentDetails.setThirdHouseRent(pricethird);
		rentDetails.setFourthHouseRent(pricefourth);
		
		street.buildHouses(4);
		
		assertTrue(street.getRentDetails().getFirstHouseRent() == pricefirst);
		assertTrue(street.getRentDetails().getSecondHouseRent() == pricesecond);
		assertTrue(street.getRentDetails().getThirdHouseRent() == pricethird);
		assertTrue(street.getRentDetails().getFourthHouseRent() == pricefourth);		
	}	

	// Florian Nickel
	@Test
	public void testReleaseToAuction() {
		Street street = new Street();
		assertFalse(street.isAuctionObjective());
		street.startAuction();
		assertTrue(street.isAuctionObjective());
	}
	
	// Florian Nickel
	@Test
	public void testTerminateAuction() {
		Street street = new Street();
		assertFalse(street.isAuctionObjective());
		street.startAuction();
		assertTrue(street.isAuctionObjective());
		street.endAuction();
		assertFalse(street.isAuctionObjective());
	}
}

