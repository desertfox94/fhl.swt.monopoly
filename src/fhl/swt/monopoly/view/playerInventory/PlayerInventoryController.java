package fhl.swt.monopoly.view.playerInventory;

import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class PlayerInventoryController {

	private Game game;

	@FXML
	private Label accountBalanceLabel;

	@FXML
	private Label currentFieldLabel;

	@FXML
	private TableView<Street> streetTable;

	@FXML
	private TableColumn<Street, String> streetNameColum;

	@FXML
	private TableColumn<Street, String> currentRentColumn;

	@FXML
	private TableColumn<Street, String> streetGroupColum;

	@FXML
	private MenuItem takeMortgageMenuItem;

	@FXML
	private MenuItem removeMortgageMenuItem;

	@FXML
	private MenuItem detailsMenuItem;

	private Player player;

	private TitledPane inventory;

	@FXML
	public void takeMortgage() {

	}

	@FXML
	public void removeMortgage() {

	}

	@FXML
	public void showStreetDetails() {

	}

	public PlayerInventoryController() {
	}

	private void intializeControls() {
		streetTable.setItems(FXCollections.observableArrayList(player.getStreets()));
		streetGroupColum.setCellValueFactory(s -> new SimpleStringProperty(""));
		streetNameColum.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getName()));
		currentRentColumn.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getCurrentRent().toString()));
		accountBalanceLabel.textProperty().bind(new SimpleStringProperty(player.getBalance().toString()));
		currentFieldLabel.textProperty().bind(new SimpleStringProperty(player.getField() == null ? "" : player.getField().getName()));
		refreshTitle();
	}

	public void setPlayer(Game game, Player player) {
		this.game = game;
		this.player = player;
		intializeControls();
	}

	public void setPane(TitledPane inventory) {
		this.inventory = inventory;
	}

	public void refreshTitle() {
		String name = player.getName();
		inventory.setText(game.getCurrentPlayer() == player ? name + " - ist am Zug" : name);
	}

}
