package fhl.swt.monopoly.view;

import fhl.swt.monopoly.core.MonopolyEngine;
import fhl.swt.monopoly.model.Game;

public class GameInitController {

	protected Game game;

	public void startGame() {
		MonopolyEngine engine = new MonopolyEngine(game);
	}

	public Game getGame() {
		return game;
	}

}
