package fhl.swt.monopoly.view.loadGame;

import fhl.swt.monopoly.core.DBService;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.view.GameInitController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class LoadGameController extends GameInitController {

	private DBService dbService = DBService.getDefault();

	@FXML
	private TableView<Game> savedGamesTable;

	@FXML
	private TableColumn<Game, String> nameColumn;

	@FXML
	private TableColumn<Game, String> editionColumn;

	@FXML
	private TableColumn<Game, String> saveDateColumn;

	@FXML
	private TableColumn<Game, String> playerCountColumn;

	@FXML
	private Button loadGameButton;

	@FXML
	private Button loadLatestGameButton;

	@FXML
	public void loadLatestGame() {
		startGame(dbService.loadLatestGame());
	}

	@FXML
	public void loadGameFromSelection() {
		TableViewSelectionModel<Game> selectionModel = savedGamesTable.getSelectionModel();
		if (selectionModel == null) {
			return;
		}
		startGame(selectionModel.getSelectedItem());
	}

}
