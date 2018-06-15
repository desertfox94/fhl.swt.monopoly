package monopoly.model;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import monopoly.core.CircleList;
import monopoly.core.cards.CardSet;
import monopoly.core.fields.Field;

public class Game {

	private CircleList<Player> players = new CircleList<>();

	private Edition edition;

	private int rounde;

	private IntegerProperty moneyInTheMiddle = new SimpleIntegerProperty();

	private List<DiceCast> currentPlayerDiceCastHistory = new LinkedList<DiceCast>();

	private CardSet communityCards;

	private CardSet eventCards;

	public void addPlayer(Player player) {
		players.add(player);
		Field field = player.getFieldProperty().get();
		if (field == null) {
			player.moveTo(edition.getFields().get(0));
		}
	}

	public CardSet getCommunityCards() {
		return communityCards;
	}

	public CardSet getEventCards() {
		return eventCards;
	}

	public CircleList<Player> getPlayers() {
		return players;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public int getRounde() {
		return rounde;
	}

	public void setRound(int rounde) {
		this.rounde = rounde;
	}

	public Player getCurrentPlayer() {
		return players.getCurrent();
	}

	public List<DiceCast> getCurrentPlayerDiceCastHistory() {
		return currentPlayerDiceCastHistory;
	}

	public IntegerProperty getMoneyInTheMiddleProperty() {
		return moneyInTheMiddle;
	}
	
	public int getMoneyInTheMiddle() {
		return moneyInTheMiddle.get();
	}


	public Player nextPlayer() {
		currentPlayerDiceCastHistory = new LinkedList<DiceCast>();
		return players.next();
	}

	public void playerRolledTheDice(DiceCast diceCast) {
		currentPlayerDiceCastHistory.add(diceCast);
	}

	public int getPlayersThrowCount() {
		return currentPlayerDiceCastHistory.size();
	}

	public void payOutMoneyInTheMiddle(Player player) {
		player.addMoney(moneyInTheMiddle.intValue());
		moneyInTheMiddle.set(0);
	}

	public void putMoneyInTheMiddle(int amount) {
		moneyInTheMiddle.set(moneyInTheMiddle.get() + amount);
	}

	public void movePlayerToField(Player player, Field target) {
		CircleList<Field> fields = getEdition().getFields();
		Field field = player.getFieldProperty().get();
		fields.select(field);
		while (target != (field = fields.next())) {
			player.moveTo(field);
			field.passing(player);
		}
		player.moveTo(field);
		field.landing(player);
	}
	
	/**
	 * This Method is called by rolling the dice and moves the player forward on the playing field.
	 * It also contains logic for passing and landing on a field.
	 * @param player The player who last rolled, and who is supposed to be moved.
	 * @param diceCast The value of the player's roll, so the method can evaluate how far to move said player.
	 */
	public void movePlayer(Player player, int diceCast) {
		CircleList<Field> fields = getEdition().getFields();
		fields.select(player.getFieldProperty().get());
		Field field = null;
		for (int i = 0; i < diceCast; i++) {
			field = fields.next();
			player.moveTo(field);
			field.passing(player);
		}
		player.moveTo(field);
		field.landing(player);
	}
	
	public void endGame() {
		//TODO
	}

}
