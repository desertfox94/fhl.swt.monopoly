package monopoly.model;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import monopoly.core.CircleList;
import monopoly.core.cards.Card;

import monopoly.core.fields.JailField;
import monopoly.core.fields.StartField;
import monopoly.core.fields.StreetField;

/**
 * @author Christoph Thomas
 */
public class TestPlayer {

	@Test
	public void testGetfield() {
		Player player = new Player();
		assertTrue(player.getField() != null);
	}

	@Test
	public void testmoveTofield() {
		Game game = new Game();
		CircleList circlist = new CircleList();
		StartField startfield = new StartField();
		Edition edi = new Edition();
		Player player = new Player();
		edi.setFields(circlist);

		Boolean notMoved = true;
		while (notMoved) {
			assertTrue(player.getField().getValue() == null);
			player.moveTo(startfield);
			notMoved = false;
		}
		assertTrue(player.getField().getValue().equals(startfield));
	}

	@Test
	public void testgetsetId() {
		Player player = new Player();
		assertTrue(player.getId() == null);
		player.setId("123");
		assertTrue(player.getId().equals("123"));
		player.setId("456");
		assertTrue(player.getId().equals("456"));
	}

	@Test
	public void testgetsetName() {
		Player player = new Player();
		assertTrue(player.getName() == null);
		player.setName("günther");
		assertTrue(player.getName().equals("günther"));
		player.setName("Hans");
		assertTrue(player.getName().equals("Hans"));
	}

	@Test
	public void testgetsetBalance() {
		Player player = new Player();
		assertTrue((player.getBalance() != null) && (player.getBalance().intValue() == 0));
		int balance = 4;
		player.setBalance(balance);
		assertTrue(player.getBalance().intValue() == 4);
	}

	@Test
	public void testpay() {
		Player player = new Player();
		player.setBalance(100);
		int beforeBalance = player.getBalance().intValue();
		int payment = 3;
		int payment2 = 1000;
		int negativePayment = -200;

		player.pay(payment);
		assertTrue(player.getBalance().intValue() == beforeBalance - payment); // -3.5

		player.setBalance(beforeBalance);

		player.pay(payment2);
		assertTrue(player.getBalance().intValue() == beforeBalance - payment2); // -900

		player.setBalance(beforeBalance);

		player.pay(negativePayment);
		assertTrue(player.getBalance().intValue() == beforeBalance - negativePayment); // 300
																							// ->
																							// ignores
																							// -
	}

	@Test
	public void testaddMoney() {
		Player player = new Player();
		player.setBalance(100);
		int beforeBalance = player.getBalance().intValue();
		int money = 3;
		int negativeMoney = -1000;

		player.addMoney(money);
		assertTrue(player.getBalance().intValue() == beforeBalance + money);

		player.setBalance(beforeBalance);

		player.pay(negativeMoney);
		assertTrue(player.getBalance().intValue() == beforeBalance - negativeMoney); // 1100
																						// ->
																						// ignores
																						// -
	}

	@Test
	public void testgetaddToInventoryStreets() {
		Player player = new Player();
		Street street = new Street();
		Street street2 = new Street();
		
		assertTrue(player.getStreets().isEmpty());

		player.addToInventory(street);

		assertTrue(player.getStreets().get(0) == street && !player.getStreets().isEmpty());
		
		player.addToInventory(street2);
		assertTrue(player.getStreets().size() == 2);
	}
	
	@Test
	public void testgetsetCards() {
		Player player = new Player();
		ObservableList<Card> cards = FXCollections.observableArrayList();
		
		assertTrue(player.getCards().isEmpty());
		
		Card card = new Card("eventcard", "triggers an event") {
			@Override
			public boolean execute(Player player) {
				
				return false;
			}};		
		cards.add(card);
		
		player.setCards(cards);
		
		assertTrue(player.getCards().get(0).getTitle() == "eventcard");
		
	
	}
	@Test
	public void testisInJail() {
		Player player = new Player();

		assertTrue(!player.isInJail());
		
		player.sendToJail();
		assertTrue(player.isInJail()); 

	
		
	}
	
	@Test
	public void testfreeFromJail() {
		Player player = new Player();
		
		assertTrue(!player.isInJail());
		
		player.sendToJail();
		assertTrue(player.isInJail());
		
		player.freeFromJail();
		//assertTrue(!player.isInJail()); -> boolean should be false -> not working yet			
	}
	@Test
	public void testsendToJail() {
		Player player = new Player();
		
		assertTrue(!player.isInJail());
		
		player.sendToJail();
		assertTrue(player.isInJail());		
	}
	
	@Test
	public void testgetsetFigure() {
		Player player = new Player();
		Figure fig = new Figure(null, "Blau");

		assertTrue(player.getFigure() == null);	
		
		player.setFigure(fig);
		assertTrue(player.getFigure().getName() == "Blau");	
				
	}
	@Test
	public void testincgetDoublecount() {
		Player player = new Player();
		assertTrue(player.getDoubleCount() == 0);	
		
		player.incDoubleCount();
		assertTrue(player.getDoubleCount() == 1);
				
	}
	@Test
	public void testaddToInventory() {
		Player player = new Player();
		Street street = new Street();
		
		assertTrue(player.getDoubleCount() == 0);	
		
		player.incDoubleCount();
		assertTrue(player.getDoubleCount() == 1);
				
	}
	@Test
	public void testgetPosition() {
		Player player = new Player();
		Street street = new Street();
		StartField startfield = new StartField();
		StreetField streetfield = new StreetField(street, 15);
		JailField jailfield = new JailField();
		
		assertTrue(player.getPosition().get() == 0);
		
		player.moveTo(startfield);
		assertTrue(player.getPosition().get() == 0);
	
		player.moveTo(streetfield);
		assertTrue(player.getPosition().get() == 15);
		
		player.moveTo(jailfield);
		assertTrue(player.getPosition().get() == 10); //jailindex static = 10				
	}
	
	@Test
	public void testsetGame() {
		Player player = new Player();
		Game game = new Game();
		player.setGame(game); 		
	}
	
	/**
	 * @author Christoph Thomas, 9. Juni
	 */
	@Test
	public void testhasHouses() {
		Player player = new Player();
		Street street = new Street();
		StreetDetails rentDetails = new StreetDetails();		
		
		street.setRentDetails(rentDetails);
		rentDetails.setFirstHouseRent(50);
		player.addToInventory(street);
	
		assertFalse(player.hasHouses());
		street.buildHouses(1);
		assertTrue(player.hasHouses());	
	}


	@Test
	public void teststreetsWithHouse() {
		Player player = new Player();
		Street street = new Street();
		Street street2 = new Street();
		StreetDetails rentDetails = new StreetDetails();		
		StreetDetails rentDetails2 = new StreetDetails();	
		
		street.setRentDetails(rentDetails);
		street2.setRentDetails(rentDetails);
		rentDetails.setFirstHouseRent(50);	
		rentDetails2.setFirstHouseRent(50);

		player.addToInventory(street);
		player.addToInventory(street2);
		
		assertEquals(player.streetsWithHouse().size(), 0);
		
		street.buildHouses(1);
		street2.buildHouses(1);
		
		assertEquals(player.streetsWithHouse().size(), 2);
		assertEquals(player.streetsWithHouse().get(0), street);
	}

	@Test
	public void testhasNoMortagedStreets() {
		Player player = new Player();
		Street street = new Street();
		Street street2 = new Street();
		StreetDetails rentDetails = new StreetDetails();	
		StreetDetails rentDetails2 = new StreetDetails();	
		
		street.setRentDetails(rentDetails);
		street2.setRentDetails(rentDetails);
		rentDetails.setFirstHouseRent(50);
		rentDetails2.setFirstHouseRent(50);
		player.addToInventory(street);
		player.addToInventory(street2);
	
		assertTrue(player.hasNoMortagedStreets());
		street.assumeMortage();
		assertTrue(player.hasNoMortagedStreets());
		street2.assumeMortage();
		assertFalse(player.hasNoMortagedStreets());
	}
	
	@Test
	public void testnotMortagedHouses() {
		Player player = new Player();
		Street street = new Street();
		Street street2 = new Street();
		StreetDetails rentDetails = new StreetDetails();	
		StreetDetails rentDetails2 = new StreetDetails();	
		
		street.setRentDetails(rentDetails);
		street2.setRentDetails(rentDetails);
		rentDetails.setFirstHouseRent(50);
		rentDetails2.setFirstHouseRent(50);
		player.addToInventory(street);
		player.addToInventory(street2);
	
		assertEquals(player.notMortagedStreets().size(), 2);
		assertEquals(player.notMortagedStreets().get(0), street);
		street.assumeMortage();
		assertEquals(player.notMortagedStreets().size(), 1);
		street2.assumeMortage();
		assertEquals(player.notMortagedStreets().size(), 0);
	}		
}

