package fhl.swt.monopoly.model;

public class DiceCast {

	private Die firstDie;
	private Die secondDie;

	public int current() {
		return firstDie.current() + secondDie.current();
	}
	
	public boolean isDouble() {
		return firstDie.equals(secondDie);
	}

	public int next() {
		firstDie.roll();
		secondDie.roll();
		return current();
	}
	
}
