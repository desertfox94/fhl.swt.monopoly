package fhl.swt.monopoly.core;

import java.util.ArrayList;
import java.util.List;

import fhl.swt.monopoly.core.mock.MockService;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Game;

public abstract class DBService {

	public abstract List<Edition> loadAvailableEditions();

	public abstract Edition loadEdition(String name);

	public Game loadGame(String name) {
		return null;
	}

	public boolean saveGame(Game game) {
		return false;
	}

	public boolean connect(String connectionURL, String user, String pw) {
		return false;
	}

	public static DBService getDefault() {
		return new MockService();
	}

	public Game loadLatestGame() {
		return null;
	}

	public List<String> loadSavedGameTitles() {
		return new ArrayList<>();
	}

}
