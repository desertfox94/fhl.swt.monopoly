package monopoly.core.fields;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.security.MessageDigest;

import monopoly.core.MessageUtil;
import org.junit.Before;
import org.junit.Test;

import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetDetails;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageUtil.class})
public class TestStreetField {

    @Mock
    private Street street;

    @InjectMocks
    StreetField streetField;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testLandingAndAuction(){
        PowerMockito.mockStatic(MessageUtil.class);
        when(MessageUtil.ask("Strasse Kaufen", "Wollen Sie die Strasse Kaufen?", "ja", "nein, Auktion starten")).thenReturn(false);
        Mockito.doThrow(new Exception("auction started")).when(street).startAuction();
        Player player = new Player();

        String message = "";
        try {
            streetField.landing(player);
        }
        catch (Exception e){
        	message = e.getMessage();
        }
        if(message.equals("auction started")){
        	assertTrue(true);
        }
        else{
        	assertTrue(false);
        }

	}

	@Test
    public void testLanding()
    {
		BigDecimal dec = new BigDecimal(500);
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
	    street.setPrice(dec);

	    player.addMoney(10000);

	    streetField.buyStreet = true;
	    streetField.landing(player);
	    assertEquals(player.getBalance().doubleValue(), 9500.0);
	    assertEquals(street.getOwner(), player);
	    assertTrue(!player.getStreets().isEmpty());
	    assertEquals(player.getStreets().get(0).getName(), "ParkAllee");
	    streetField.buyStreet = false;


	   playerTwo.addMoney(10000);

	   assertEquals(player.getBalance().get(), 9500.0);
	   streetField.landing(playerTwo);
	   assertEquals(playerTwo.getBalance().get(), 9800.0);
	   assertEquals(player.getBalance().get(), 9700.0);

	   player.sendToJail();
	   streetField.landing(playerTwo);
	   assertEquals(playerTwo.getBalance().get(), 9800.0);
	   assertEquals(player.getBalance().get(), 9700.0);


	   playerThree.addMoney(100);
	   assertEquals(playerThree.getBalance().doubleValue(), 100.0);
	   playerThree.freeFromJail();

	   assertEquals(player.getBalance().get(), 9700.0);
	   streetField.landing(playerThree);
	   assertEquals(playerThree.getBalance().get(), 100.0); // 100 statt -100 , betrag kann nicht negativ werden
	   assertEquals(player.getBalance().get(), 9900.0);
    }
}
