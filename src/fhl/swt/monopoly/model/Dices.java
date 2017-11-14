package fhl.swt.monopoly.model;

import java.util.Stack;

public class Dices {

	private Stack<DiceCast> throwLog;
	
	public boolean isDouble() {
		return throwLog.peek().isDouble();
	}
	
	public int current() {
		return throwLog.peek().getNumberOfPips();
	}
	
}
