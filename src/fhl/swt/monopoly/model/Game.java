package fhl.swt.monopoly.model;

import java.util.LinkedList;
import java.util.List;

import fhl.swt.monopoly.core.CircleList;
import fhl.swt.monopoly.core.cards.CardSet;
import fhl.swt.monopoly.core.fields.Field;

public class Game {

	private CircleList<Player> players = new CircleList<>();
	private Edition edition;
	private int rounde;

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

}
