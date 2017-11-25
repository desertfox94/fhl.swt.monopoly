package fhl.swt.monopoly.controller;

import fhl.swt.monopoly.core.MonopolyEngine;

public class PlaygroundController {

	private MonopolyEngine engine;
	
	public PlaygroundController(MonopolyEngine engine) {
		this.engine = engine;
	}

	public void refreshPlayground() {
		
	}
	
	public void rollDiceButtonClicked() {
		engine.playerRollsTheDice();
	}
	
	public boolean canRollTheDice() {
		return engine.canPlayerRollTheDice();
	}
	
}
