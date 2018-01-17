package fhl.swt.monopoly.view.startNewGame;

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
import fhl.swt.monopoly.core.DBService;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Figure;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.view.GameInitController;
import fhl.swt.monopoly.view.startNewGame.NewPlayer.NewPlayerController;

public class NewGameController extends GameInitController implements Initializable {

	private Edition selectedEdition;

	private ObservableList<Edition> editions = FXCollections.observableArrayList(DBService.getDefault().loadAvailableEditions());

	private ObservableList<Figure> figures = FXCollections.observableArrayList();

	@FXML
	private Pane root;

	@FXML
	private Button startGameButton;

	@FXML
	private GridPane playersGrid;

	@FXML
	private ChoiceBox<Edition> editionsBox;

	private List<NewPlayerController> players = new LinkedList<>();

	@FXML
	public void selectEdition() {
	}

	@FXML
	public void addNewPlayer() {

	}

	@FXML
	public void selectFigur() {

	}

	@FXML
	public void startGame() {
		Game game = new Game();
		game.setEdition(selectedEdition);
		for (Player player : getPlayers()) {
			game.addPlayer(player);
			player.setGame(game);
		}
		startGame(game);
	}

	private List<Player> getPlayers() {
		List<Player> players = new LinkedList<>();
		for (NewPlayerController playerController : this.players) {
			if (playerController.isModelValid()) {
				players.add(playerController.getPlayer());
			}
		}
		return players;
	}

	private void initPlayers() {
		figures = FXCollections.observableArrayList(selectedEdition.getFigures());
		players.clear();
		try {
			playersGrid.getChildren().clear();
			Pane newPlayerView;
			NewPlayerController controller;
			for (int i = 1; i <= selectedEdition.getMaxAmountOfPlayers(); i++) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(NewPlayerController.class.getResource("NewPlayer.fxml"));
				loader.load();
				controller = loader.getController();
				controller.setNumber(i);
				// figures.addAll(selectedEdition.getFigures());
				controller.setItems(figures);
				controller.addModelValidationListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
						startGameButton.setDisable(!(hasEnoughPlayers()) || !isEveryPlayerComplete());
					}
				});
				newPlayerView = (Pane) loader.getRoot();
				playersGrid.addRow(i, newPlayerView);
				players.add(controller);
				controller.setGameController(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean hasEnoughPlayers() {
		return players.stream().filter(p -> p.isModelValid()).count() > 1;
	}

	private boolean isEveryPlayerComplete() {
		return players.stream().filter(p -> p.isModelIncomplete()).count() == 0;
	}

	public boolean isUsed(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return players.stream().filter(p -> name.equals(p.getModel().getName())).count() > 1;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		editionsBox.setItems(editions);
		editionsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Edition>() {
			@Override
			public void changed(ObservableValue<? extends Edition> arg0, Edition arg1, Edition arg2) {
				selectedEdition = arg2;
				initPlayers();
				startGameButton.setDisable(true);
			}
		});
	}

}
