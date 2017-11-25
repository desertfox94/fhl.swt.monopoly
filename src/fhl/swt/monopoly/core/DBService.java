package fhl.swt.monopoly.core;

import fhl.swt.monopoly.model.Game;

public class DBService {

	public Game loadGame() {
		return null;
	}
	
	public boolean saveGame(Game game) {
		return false;
	}
	
	public boolean connect(String connectionURL, String user, String pw) {
		return false;
	}
	
	public static DBService getDefault() {
		DBService dbService = new DBService();
		dbService.connect("", "", "");
		return dbService;
	}
	
}
