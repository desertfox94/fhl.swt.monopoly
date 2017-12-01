package fhl.swt.monopoly.view;

import java.io.IOException;

import fhl.swt.monopoly.core.MonopolyEngine;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.view.playground.PlaygroundController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;

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
