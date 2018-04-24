package monopoly.view;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import monopoly.core.MessageUtil;

public class AppViewController {

	@FXML
	public BorderPane mainPane;

	@FXML
	public GridPane mainMenu;

	private static AppViewController instance;

	public AppViewController() {
		instance = this;
	}

	@FXML
	/**
	 * Opens the NewGameView, no save action will be executed.
	 */
	public void startNewGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppViewController.class.getResource("startNewGame/NewGameView.fxml"));
			showInCenterPane(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Shows a Node in the center pane of the AppView
	 * 
	 * @param pane
	 */
	public static void showInCenterPane(Node pane) {
		instance.mainPane.centerProperty().set(pane);
	}

	@FXML
	public void openMainMenu() {
		showInCenterPane(mainMenu);
	}

	@FXML
	/**
	 * Switches the to "LoadGameView".
	 */
	public void loadGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppViewController.class.getResource("loadGame/LoadGameView.fxml"));
			showInCenterPane(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void save() {
		// TODO: save current state of game.
		MessageUtil.inform("Spiel gespeichert", "Das Spiel wurde erfolgreich gespeichert!");
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

}