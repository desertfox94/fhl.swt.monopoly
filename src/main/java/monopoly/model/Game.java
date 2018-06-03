package monopoly.model;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import monopoly.core.CircleList;
import monopoly.core.cards.CardSet;
import monopoly.core.fields.Field;

public class Game {

	private CircleList<Player> players = new CircleList<>();
	private Edition edition;
	private int rounde;
	private DoubleProperty moneyInTheMiddle = new SimpleDoubleProperty();
	private List<DiceCast> currentPlayerDiceCastHistory = new LinkedList<DiceCast>();

	private CardSet communityCards;

	private CardSet eventCards;

	public void addPlayer(Player player) {
		players.add(player);
		Field field = player.getField().get();
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

	public DoubleProperty getMoneyInTheMiddle() {
		return moneyInTheMiddle;
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

	public void setMoneyInTheMiddle(double moneyInTheMiddle) {
		this.moneyInTheMiddle.set(moneyInTheMiddle);
	}
	
	public void endGame() {
		//TODO
	}

}
