package fhl.swt.monopoly.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fhl.swt.monopoly.core.CircleList;
import fhl.swt.monopoly.core.cards.CardSet;

public class Game {

	private CircleList<Player> players = new CircleList<>();
	private Edition edition;
	private int rounde;
	private Player currentPlayer;
	
	private List<DiceCast> currentPlayerDiceCastHistory = new LinkedList<DiceCast>();

	public List<DiceCast> getCurrentPlayerDiceCastHistory() {
		return currentPlayerDiceCastHistory;
	}

	private CardSet communityCards;
	
	private CardSet eventCards;
	
	public void addPlayer(Player player) {
		 players.add(player);
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
		return currentPlayer;
	}

	public Player nextPlayer() {
		currentPlayer = players.next();
		currentPlayerDiceCastHistory = new LinkedList<DiceCast>();
		return currentPlayer;
	}

	public void playerRolledTheDice(DiceCast diceCast) {
		currentPlayerDiceCastHistory.add(diceCast);
	}
	
	public int getPlayersThrowCount() {
		return currentPlayerDiceCastHistory.size();
	}
	
}
