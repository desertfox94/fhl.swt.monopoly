package monopoly.view;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import monopoly.model.Edition;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.view.loadGame.LoadGameController;
import monopoly.view.playground.PlaygroundController;
import monopoly.view.startNewGame.NewGameController;

/**
 * Der {@link GameInitController} ist eine Basis Klasse zum Initialisieren eines
 * Spiels. Er l�dt die Game-View und sorgt daf�r, dass ihr ein Spiel Objekt
 * �bergeben wird und in der AppView angezeigt wird. Dieser Kontroller bildet
 * die Basis f�r bspw. {@link NewGameController} und {@link LoadGameController}.
 *
 */
public class GameInitController {

	public void startGame(Edition edition, List<Player> players) {
		Game game = new Game();
		game.setEdition(edition);
		for (Player player : players) {
			game.addPlayer(player);
			player.setGame(game);
			player.setBalance(edition.getStartMoney());
		}
		startGame(game);
	}
	
	public void startGame(Game game) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AppViewController.class.getResource("playground/PlaygroundView.fxml"));
		try {
			Control playground = loader.load();
			PlaygroundController controller = loader.getController();
			controller.preparePlayground(game);
			AppViewController.showInCenterPane(playground);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
