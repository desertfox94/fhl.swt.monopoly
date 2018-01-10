package fhl.swt.monopoly.model;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Die {

	private static final Random RANDOM = new Random();
	private IntegerProperty number = new SimpleIntegerProperty();

	public IntegerProperty getNumber() {
		return number;
	}

	public int get() {
		return number.intValue();
	}

	public int roll() {
		number.set(RANDOM.nextInt(5) + 1);
		return number.get();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Die) {
			return number.get() == ((Die) o).number.get();
		}
		return false;
	}

}
