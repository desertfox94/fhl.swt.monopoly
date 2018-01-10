package fhl.swt.monopoly.core.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fhl.swt.monopoly.core.DBService;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Figure;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;

public class GameMock extends Game {

	private static Random r = new Random();

	public static List<Game> games() {
		List<Game> games = new ArrayList<>();
		games.add(mockGame());
		return games;
	}

	public static Game mockGame() {
		Game game = new Game();
		Edition edition = DBService.getDefault().loadEdition(null);
		game.setEdition(edition);
		game.addPlayer(mockPlayer("John", edition.getFigures().get(0)));
		game.addPlayer(mockPlayer("Bernd", edition.getFigures().get(1)));
		game.addPlayer(mockPlayer("Bud", edition.getFigures().get(2)));
		return game;
	}

	private static Player mockPlayer(String name, Figure figure) {
		Player player = new Player();
		player.setBalance(8000.0);
		int streetCount = r.nextInt(8);
		player.setFigure(figure);
		// for (int i = 0; i < streetCount; i++) {
		// player.addToInventory(streets.get(r.nextInt(streets.size())));
		// }
		player.setName(name);
		return player;
	}

}
