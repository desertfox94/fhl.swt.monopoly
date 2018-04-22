package monopoly.model;

import javafx.beans.value.ChangeListener;

public class DiceCast {

	private Die firstDie = new Die();
	private Die secondDie = new Die();

	public int current() {
		return firstDie.get() + secondDie.get();
	}

	public boolean isDouble() {
		return firstDie.equals(secondDie);
	}

	public void addListeners(ChangeListener<Number> dieOne, ChangeListener<Number> dieTwo) {
		firstDie.getNumber().addListener(dieOne);
		secondDie.getNumber().addListener(dieTwo);
	}

	public int next() {
		firstDie.roll();
		secondDie.roll();
		return current();
	}

}
