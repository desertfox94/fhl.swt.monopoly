package fhl.swt.monopoly.view.startNewGame;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class NewGameController extends GameInitController implements
		Initializable {

	private Edition selectedEdition;

	private ObservableList<String> editions = FXCollections
			.observableArrayList(DBService.getDefault().loadAvailableEditions());

	private ObservableList<Figure> figures = FXCollections
			.observableArrayList();

	private List<String> takenFigures = new LinkedList<>();

	@FXML
	private Pane root;

	@FXML
	private Button startGameButton;

	@FXML
	private GridPane playersGrid;

	@FXML
	private ChoiceBox<String> editionsBox;

	private List<NewPlayerController> players = new LinkedList<>();

	private boolean updateInProgress, editionSelected, enoughPlayers;

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
		String name;
		List<Player> players = new LinkedList<>();
		// for (int i = 0; i < textFields.size(); i++) {
		// name = textFields.get(i).getText();
		// final String figure = (String)
		// comboBoxes.get(i).getSelectionModel().getSelectedItem();
		// if (!name.trim().isEmpty()) {
		// if (figure.isEmpty()) {
		// // TODO: Feld rot hinterlegen o.ä. Spiel darf jedenfalls nicht
		// gestartet werden.
		// System.out.println();
		// } else {
		// Player player = new Player();
		// player.setName(name);
		// player.setFigure(selectedEdition.getFigures().stream().filter(f ->
		// f.getName().equals(figure)).findFirst().get());
		// players.add(player);
		// player.setBalance(8000);
		// // if (textFields.stream().filter(e ->
		// !e.getText().isEmpty()).count() >= 2) {
		// // startGameButton.setDisable(false);
		// // }
		//
		// }
		// }
		// }
		return players;
	}

	private void initPlayers() {
		figures = FXCollections.observableArrayList(selectedEdition.getFigures());
		try {
			playersGrid.getChildren().clear();
			Pane newPlayerView;
			NewPlayerController controller;
			for (int i = 1; i <= 6; i++) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(NewPlayerController.class
						.getResource("NewPlayer.fxml"));
				loader.load();
				controller = loader.getController();
				controller.setNumber(i);
				// figures.addAll(selectedEdition.getFigures());
				controller.setItems(figures);
				controller.addValidListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> arg0,
							Boolean arg1, Boolean arg2) {
						startGameButton.setDisable(!hasEnoughPlayers());
					}
				});
				newPlayerView = (Pane) loader.getRoot();
				playersGrid.addRow(i, newPlayerView);
				players.add(controller);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean hasEnoughPlayers() {
		return players.stream().filter(p -> p.isModelValid()).count() > 1;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		editionsBox.setItems(editions);
		editionsBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String arg1, String arg2) {
						selectedEdition = DBService.getDefault().loadEdition(
								arg2);
						initPlayers();
					}
				});
		// comboBoxes = findByType(root, ComboBox.class);
		// for (final ComboBox<String> box : comboBoxes) {
		// box.setItems(figures);
		// box.getSelectionModel().selectedIndexProperty().addListener(new
		// ChangeListener<Number>() {
		//
		// @Override
		// public void changed(ObservableValue<? extends Number> observable,
		// Number oldValue, Number newValue) {
		// if (updateInProgress) {
		// return;
		// }
		// if (newValue.intValue() != -1) {
		// takenFigures.add(box.getItems().get(newValue.intValue()));
		// if (oldValue.intValue() != -1) {
		// takenFigures.remove(box.getItems().get(oldValue.intValue()));
		// }
		// updateFigureSelections();
		// }
		// }
		// });
		//
		// }
		// textFields = findByType(root, TextField.class);
	}

	private void updateFigureSelections() {
		// updateInProgress = true;
		// for (ComboBox<String> comboBox : comboBoxes) {
		// List<String> taken = new ArrayList<>(takenFigures);
		// String selectedItem = comboBox.getSelectionModel().getSelectedItem();
		// taken.remove(selectedItem);
		// ArrayList<String> available = new ArrayList<>(figures);
		// available.removeAll(taken);
		// comboBox.setItems(FXCollections.observableArrayList(available));
		// if (selectedItem != null) {
		// comboBox.getSelectionModel().select(selectedItem);
		// }
		// }
		// updateInProgress = false;
	}

	private void enableChildren(Parent parent) {
		parent.setDisable(false);
		ObservableList<Node> children = parent.getChildrenUnmodifiable();
		for (Node child : children) {
			if (child instanceof Parent) {
				enableChildren((Parent) child);
			}
		}
	}

	private <T> List<T> findByType(Parent parent, Class<T> clazz) {
		List<T> result = new LinkedList<>();
		ObservableList<Node> children = parent.getChildrenUnmodifiable();
		for (Node child : children) {
			if (clazz.isInstance(child)) {
				result.add((T) child);
			} else if (child instanceof Parent) {
				result.addAll(findByType((Parent) child, clazz));
			}
		}
		return result;
	}

	private class FigureSelectionListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> arg0,
				Number oldVal, Number newVal) {
		}
	}

}
