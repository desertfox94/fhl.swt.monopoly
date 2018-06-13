package monopoly.core.fields;

import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;



public class TestStreetField {


    @Mock
    StreetField streetField;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * @author Jake Stradling
     */
    @Test
    public void testLandingAndAuction() {
        Player player = new Player();
        Street street = new Street();
        streetField = spy(new StreetField(street, 1));
        //Mockito.when(streetField.ask()).thenReturn(false);
   
        doReturn(false).when(streetField).ask();

        streetField.setStreet(street);
        streetField.landing(player);


        assertTrue(street.isAuctionObjective());


    }

    @Test
    public void testLanding() {
        int dec = 500;
        Street street = new Street();
        Player player = new Player();
        Player playerTwo = new Player();
        Player playerThree = new Player();
        int index = 5;
        StreetField streetField = spy(new StreetField(street, index));
        doReturn(true).when(streetField).ask();
        street.setName("ParkAllee");
        StreetDetails rentDetails = new StreetDetails();
        int rentHouse = 200;
        rentDetails.setFirstHouseRent(rentHouse);

        street.setRentDetails(rentDetails);
        street.buildHouses(1);
        street.setPrice(dec);

        player.addMoney(10000);

        streetField.buyStreet = true;
        streetField.landing(player);
        assertEquals(player.getBalance().intValue(), 9500);
        assertEquals(street.getOwner(), player);
        assertTrue(!player.getStreets().isEmpty());
        assertEquals(player.getStreets().get(0).getName(), "ParkAllee");
        streetField.buyStreet = false;


        playerTwo.addMoney(10000);

        assertEquals(player.getBalance().get(), 9500);
        streetField.landing(playerTwo);
        assertEquals(playerTwo.getBalance().get(), 9800);
        assertEquals(player.getBalance().get(), 9700);

        player.sendToJail();
        streetField.landing(playerTwo);
        assertEquals(playerTwo.getBalance().get(), 9800);
        assertEquals(player.getBalance().get(), 9700);


        playerThree.addMoney(100);
        assertEquals(playerThree.getBalance().intValue(), 100);
        playerThree.freeFromJail();
    }
    
    @Test
    public void testLanding_Mortage() {
    	 Player player = new Player();
    	 Player owner = new Player();
         Street playerStreet = new Street();
         Street landingStreet = new Street();
         StreetDetails rentDetails = new StreetDetails();
         streetField = spy(new StreetField(landingStreet, 10));
         
         doReturn(true).when(streetField).ask();
         doReturn(true).when(streetField).askForMortage();
         
         landingStreet.setPrice(50);
         rentDetails.setBaseRent(150);       
         landingStreet.setRentDetails(rentDetails);    
         player.addToInventory(playerStreet); // Assume Player has one not Mortaged Street 
     
         assertFalse(playerStreet.isMortage());  
         assertTrue(player.hasNoMortagedStreets());
         assertEquals(player.notMortagedStreets().get(0), playerStreet);        
       
         streetField.setStreet(landingStreet);         
         streetField.landing(owner);
         assertEquals(owner.getStreets().size(), 1);
         
         assertEquals(player.notMortagedStreets().get(0), playerStreet);
         streetField.landing(player);
         assertFalse(player.hasNoMortagedStreets());
         assertTrue(player.getStreets().get(0).isMortage());
         assertTrue(player.notMortagedStreets().isEmpty());        
     }   
}
