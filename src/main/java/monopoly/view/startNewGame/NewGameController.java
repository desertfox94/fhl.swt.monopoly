package monopoly.view.startNewGame;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import monopoly.core.DBService;
import monopoly.model.Edition;
import monopoly.model.Figure;
import monopoly.model.Player;
import monopoly.view.GameInitController;
import monopoly.view.startNewGame.NewPlayer.NewPlayerController;

public class NewGameController extends GameInitController implements Initializable {

	private Edition selectedEdition;

	private ObservableList<Edition> editions = FXCollections.observableArrayList(DBService.getDefault().loadAvailableEditions());

	private ObservableList<Figure> figures = FXCollections.observableArrayList();

	@FXML
	private Pane root;

	@FXML
	private Button startGameButton;

	@FXML
	/**
	 * Auf diesem Grid werden die Felder f�r Spieler hinzugef�gt
	 */
	private GridPane playersGrid;

	@FXML
	private ChoiceBox<Edition> editionsBox;

	private List<NewPlayerController> newPlayerControllers = new LinkedList<>();

	@FXML
	public void startGame() {
		startGame(selectedEdition, getPlayers());
	}

	private List<Player> getPlayers() {
		List<Player> players = new LinkedList<>();
		for (NewPlayerController playerController : this.newPlayerControllers) {
			if (playerController.isModelValid()) {
				players.add(playerController.getPlayer());
			}
		}
		return players;
	}

	private void prepareForNewPlayers() {
		newPlayerControllers.clear();
		playersGrid.getChildren().clear();
		for (int i = 1; i <= selectedEdition.getMaxAmountOfPlayers(); i++) {
			playersGrid.addRow(i, initNewPlayerView(i));
		}
	}

	private Pane initNewPlayerView(int number) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(NewPlayerController.class.getResource("NewPlayer.fxml"));
		try {
			loader.load();
		} catch (IOException e) {
			System.err.println("Failed to load NewPlayerView");
			throw new RuntimeException(e);
		}
		NewPlayerController controller = loader.getController();
		controller.setNumber(number);
		controller.setItems(figures);
		controller.addModelValidationListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				startGameButton.setDisable(!(hasEnoughPlayers()) || !areAllPlayerComplete());
			}
		});
		controller.setGameController(this);
		newPlayerControllers.add(controller);
		return (Pane) loader.getRoot();
	}

	private void loadFiguresForEdition() {
		figures = FXCollections.observableArrayList(selectedEdition.getFigures());
	}

	/**
	 * Hat das Spiel mehr als einen Spieler
	 * 
	 * @return
	 */
	private boolean hasEnoughPlayers() {
		return newPlayerControllers.stream().filter(p -> p.isModelValid()).count() > 1;
	}

	/**
	 * @return Spieler m�ssen immer einen Namen und ein Bild angegeben haben
	 */
	private boolean areAllPlayerComplete() {
		return newPlayerControllers.stream().filter(p -> p.isModelIncomplete()).count() == 0;
	}

	/**
	 * Hat ein anderer Spieler bereits diesen Namen?
	 * 
	 * @param name
	 * @return
	 */
	public boolean isUsed(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return newPlayerControllers.stream().filter(p -> name.equals(p.getModel().getName())).count() > 1;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		editionsBox.setItems(editions);
		editionsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Edition>() {
			@Override
			public void changed(ObservableValue<? extends Edition> arg0, Edition arg1, Edition newEdition) {
				handleEditionChanged(newEdition);
			}

		});
	}

	private void handleEditionChanged(Edition newEdition) {
		selectedEdition = newEdition;
		loadFiguresForEdition();
		prepareForNewPlayers();
		startGameButton.setDisable(true);
	}
}
