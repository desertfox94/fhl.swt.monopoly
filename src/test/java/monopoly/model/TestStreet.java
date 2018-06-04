package monopoly.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableNumberValue;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
		
		BigDecimal price = new BigDecimal("8900");
		BigDecimal negativeprice = new BigDecimal("-8900");
	
		
		assertTrue(street.getPrice() == null);
		street.setPrice(price);
		assertTrue(street.getPrice().compareTo(price) == 0);
		street.setPrice(price);	
		assertTrue(!(street.getPrice().compareTo(negativeprice) == 0)); // ignores (-) 8900 instead -8900			
	}
	
	@Test
	public void testbuildgetdemolishHouses() {
		Street street = new Street();
		StreetDetails rentDetails = new StreetDetails();
		BigDecimal price = new BigDecimal("8900");	
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
		assertTrue(!street.isMortage());
		street.assumeMortage();
		assertTrue(street.isMortage());		
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
		BigDecimal price = new BigDecimal("8900");	
		
		assertTrue(street.getRentDetails() == null);	
		street.setRentDetails(rentDetails);
		assertTrue(street.getRentDetails().equals(rentDetails));		
		rentDetails.setFirstHouseRent(price);
		assertTrue(street.getRentDetails().getFirstHouseRent() == price);			
	}
	
	@Test
	public void testsetgetgroups() {
		StreetGroup group = new StreetGroup();
		BigDecimal price = new BigDecimal("8900");			
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
		double rent = 50;
		
		DoubleProperty rent2 = new SimpleDoubleProperty();
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
		BigDecimal pricefirst = new BigDecimal("8900");	
		BigDecimal pricesecond = new BigDecimal("0");
		BigDecimal pricethird = new BigDecimal("-3");
		BigDecimal pricefourth = new BigDecimal("40");

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
		assertFalse(street.IsAuctionObjective());
		street.zurAuktionFreigeben();
		assertTrue(street.IsAuctionObjective());
	}
}

