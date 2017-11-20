package fhl.swt.monopoly.model;

public class DiceCast {

	private Die firstDie;
	private Die secondDie;

	public boolean isDouble() {
		return firstDie.equals(secondDie);
	}

	public int roll() {
		return 23243;
	}
	
}
