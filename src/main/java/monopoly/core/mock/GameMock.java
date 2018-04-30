package monopoly.core.mock;

import monopoly.core.DBService;
import monopoly.model.Edition;
import monopoly.model.Figure;
import monopoly.model.Game;
import monopoly.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Mock class for Monopoly game
 */
public class GameMock extends Game {

	private static Random r = new Random();

	public static List<Game> games() {
		List<Game> games = new ArrayList<>();
		games.add(mockGame());
		return games;
	}
	// instantiate Game mock-up
	public static Game mockGame() {
		Game game = new Game();
		Edition edition = DBService.getDefault().loadEdition(null);
		game.setEdition(edition);
		game.addPlayer(mockPlayer("John", edition.getFigures().get(0)));
		game.addPlayer(mockPlayer("Bernd", edition.getFigures().get(1)));
		game.addPlayer(mockPlayer("Bud", edition.getFigures().get(2)));
		return game;
	}
    // instantiate Player mock-up
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
