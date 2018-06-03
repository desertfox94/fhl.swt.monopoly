package monopoly.core.fields;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import monopoly.core.CircleList;
import monopoly.model.Edition;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetDetails;
import monopoly.model.StreetOwner;

public class TestStreetField {
	   	
	@Test
    public void testLanding()
    {
		BigDecimal dec = new BigDecimal(500);
		Game game = new Game();
	    Street street = new Street();
		   
	    Player player = new Player();
	    Player playerTwo = new Player();    
	    Player playerThree = new Player(); 
	    int index = 5;	
	    StreetField streetField = new StreetField(street, index);
	    street.setName("ParkAllee");
	    StreetDetails rentDetails = new StreetDetails();
	    BigDecimal rentHouse = new BigDecimal(200);	
	    rentDetails.setFirstHouseRent(rentHouse);
	    
	    street.setRentDetails(rentDetails);
	    street.buildHouses(1);

		
	    player.addMoney(10000); 		
	    street.setPrice(dec);
	  
	    streetField.buyStreet = true;
	    
	    if( streetField.buyStreet ) {
	    	streetField.landing(player);
	    	assertEquals(player.getBalance().doubleValue(), 9500.0);  
	    	assertTrue(street.getOwner().equals(player));
	    	assertTrue(!player.getStreets().isEmpty());
	    	assertTrue(player.getStreets().get(0).getName().equals("ParkAllee"));
	    	streetField.buyStreet = false;
	    }
	    	    
	   playerTwo.addMoney(10000); 
	    
	   if (streetField.buyStreet == false) {
			assertTrue(player.getBalance().get() == 9500); 
	    	streetField.landing(playerTwo);	    
	    	assertTrue(playerTwo.getBalance().get() == 9800); 
	    	assertTrue(player.getBalance().get() == 9700);    
	   }
	   
	   playerThree.addMoney(100); 
	   playerThree.sendToJail();

	   streetField.landing(playerThree);
	   assertTrue(playerThree.getBalance().doubleValue() == 100);  
	   playerThree.freeFromJail();
	
	   assertTrue(player.getBalance().doubleValue() == 9700);  
	   streetField.landing(playerThree);
	   assertTrue(playerThree.getBalance().doubleValue() == 0);   
	   assertTrue(player.getBalance().doubleValue() == 9800);  
	   playerThree.addMoney(100);    
    }
}
