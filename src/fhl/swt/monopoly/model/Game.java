package fhl.swt.monopoly.model;

import java.util.List;

public class Game {

	private List<Player> players;
	private Edition edition;
	private int rounde;
	private int currentPlayer;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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

	public void setRounde(int rounde) {
		this.rounde = rounde;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
