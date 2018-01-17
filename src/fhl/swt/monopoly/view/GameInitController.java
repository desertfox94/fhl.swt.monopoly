package fhl.swt.monopoly.view;

import java.io.IOException;

import fhl.swt.monopoly.core.MonopolyEngine;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.view.loadGame.LoadGameController;
import fhl.swt.monopoly.view.playground.PlaygroundController;
import fhl.swt.monopoly.view.startNewGame.NewGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;

/**
 * Der {@link GameInitController} ist eine Basis Klasse zum Initialisieren eines
 * Spiels. Er lädt die Game-View und sorgt dafür, dass ihr ein Spiel Objekt
 * übergeben wird und in der AppView angezeigt wird. Dieser Kontroller bildet
 * die Basis für bspw. {@link NewGameController} und {@link LoadGameController}.
 *
 */
public class GameInitController {

	public void startGame(Game game) {
		MonopolyEngine engine = new MonopolyEngine(game);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AppViewController.class.getResource("playground/PlaygroundView.fxml"));
		try {
			Control playground = loader.load();
			PlaygroundController controller = loader.getController();
			controller.preparePlayground(game);
			AppViewController.getInstance().showInCenterPane(playground);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
