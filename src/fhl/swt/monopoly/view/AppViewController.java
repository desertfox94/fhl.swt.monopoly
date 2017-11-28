package fhl.swt.monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.view.playground.PlaygroundController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class AppViewController implements Initializable {

	public BorderPane pane;

	private LoadGameController loadGameController = new LoadGameController();

	@FXML
	public void startNewGame() {

	}

	@FXML
	public void loadGame() {
		if (loadGameController.loadGame()) {
			Game game = loadGameController.getGame();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppViewController.class.getResource("playground/PlaygroundView.fxml"));
			try {
				SplitPane splitPane = loader.load();
				PlaygroundController controller = loader.getController();
				controller.preparePlayGround(game);
				pane.centerProperty().set(splitPane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void save() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Spiel gespeichert");
		alert.setHeaderText(null);
		alert.setContentText("Das Spiel wurde erfolgreich gespeichert!");

		alert.showAndWait();
	}

	@FXML
	public void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Spiel beenden");
		alert.setHeaderText("Soll der Spielstand gespeichert werden?");
		alert.setContentText("Achtung! Ungespeicherte Änderungen gehen verloren.");

		ButtonType saveAndClose = new ButtonType("Speichern und Beenden");
		ButtonType closeWithoutSave = new ButtonType("Ohne Speichern beenden");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveAndClose, closeWithoutSave, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeCancel) {
			return;
		} else if (result.get() == saveAndClose) {
			save();
		} else if (result.get() == closeWithoutSave) {
			// ... user chose "Two"
		} else {
			// ... user chose CANCEL or closed the dialog
		}
		System.exit(0);
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		System.out.println();
	}

	public void setStage(BorderPane pane) {
		this.pane = pane;
	}

}
