package fhl.swt.monopoly.model;

import java.util.List;

import fhl.swt.monopoly.core.cards.CardSet;

public class Game {

	private List<Player> players;
	private Edition edition;
	private int rounde;
	private Player currentPlayer;

	private CardSet communityCards;
	
	private CardSet eventCards;
	
	public void addPlayer() {
		 
	}
	
	public List<Player> getPlayers() {
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
		return players.get(players.indexOf(currentPlayer) + 1);
	}

	public void nextPlayer() {
	}

}
