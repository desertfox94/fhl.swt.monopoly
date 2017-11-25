package fhl.swt.monopoly.controller;

import fhl.swt.monopoly.core.MonopolyEngine;
import fhl.swt.monopoly.model.Game;

public class GameInitController {

	protected Game game;
	
	public void startGame() {
		MonopolyEngine engine = new MonopolyEngine(game);
	}
	
}
