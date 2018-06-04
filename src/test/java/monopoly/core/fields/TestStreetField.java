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

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class TestStreetField {


    @Mock
    StreetField streetField;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLandingAndAuction() {
        Player player = new Player();
        Street street = new Street();
         streetField = spy(new StreetField(street, 1));
        //Mockito.when(streetField.ask()).thenReturn(false);

        doReturn(false).when(streetField).ask();
        Whitebox.setInternalState(streetField, "street", street);

        streetField.setStreet(street);
        streetField.landing(player);


        assertTrue(street.isAuctionObjective());


    }

    @Test
    public void testLanding() {
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
