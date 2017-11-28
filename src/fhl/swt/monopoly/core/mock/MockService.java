package fhl.swt.monopoly.core.mock;

import java.util.ArrayList;
import java.util.List;

import fhl.swt.monopoly.core.DBService;
import fhl.swt.monopoly.model.Game;

public class MockService extends DBService {

	public Game loadGame(String name) {
		return GameMock.mockGame();
	}

	public boolean saveGame(Game game) {
		return true;
	}

	public boolean connect(String connectionURL, String user, String pw) {
		return true;
	}

	public List<String> loadSavedGameTitles() {
		return new ArrayList<>();
	}

}
