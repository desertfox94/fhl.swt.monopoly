package fhl.swt.monopoly.controller;

import fhl.swt.monopoly.core.DBService;

public class LoadGameController extends GameInitController {
	
	private DBService dbService = DBService.getDefault();
	
	public void selectSaveGame() {
		game = dbService.loadGame();
	}
	
}
