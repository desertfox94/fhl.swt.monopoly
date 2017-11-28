package fhl.swt.monopoly.model;

import java.util.Random;

public class Die {

	private static final Random RANDOM = new Random();
	private int number;

	public int current() {
		return number;
	}

	public int roll() {
		number = RANDOM.nextInt(5) + 1;
		return number;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Die) {
			return number == ((Die) o).number;
		}
		return false;
	}

}
