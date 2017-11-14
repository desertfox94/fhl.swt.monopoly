package fhl.swt.monopoly.model;

public class DiceCast {

	private int numberOfPips;
	private int firstDice;
	private int secondDice;
	private Player player;

	public boolean isDouble() {
		return firstDice == secondDice;
	}

	public int getFirstDice() {
		return firstDice;
	}

	public void setFirstDice(int firstDice) {
		this.firstDice = firstDice;
	}

	public int getSecondDice() {
		return secondDice;
	}

	public void setSecondDice(int secondDice) {
		this.secondDice = secondDice;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getNumberOfPips() {
		return numberOfPips;
	}
	
}
