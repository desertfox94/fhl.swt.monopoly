package fhl.swt.monopoly.model;

import java.util.Random;
import java.util.Stack;

public class Die {

	private static final Random RANDOM = new Random();
	private int number;
	
	public int current() {
		return 0;//throwLog.peek().getNumberOfPips();
	}
	
	public int roll() {
		return RANDOM.nextInt(5) + 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Die) {
			return number ==((Die) o).number;
		}
		return false;
	}
	
}
