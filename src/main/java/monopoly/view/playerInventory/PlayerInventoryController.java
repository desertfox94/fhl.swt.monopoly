package monopoly.view.playerInventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import monopoly.core.fields.Field;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.core.cards.Card;

public class PlayerInventoryController {

	private Game game;

	@FXML
	private Label accountBalanceLabel;

	@FXML
	private Label currentFieldLabel;

	@FXML
	private TableView<Street> streetTable;
	
	@FXML
	private ListView<Card> cardTable;

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
	
	@FXML
	private MenuItem executeCardMenuItem;

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
		streetTable.setItems(player.getStreets());
		cardTable.setItems(player.getCards());
		streetGroupColum.setCellValueFactory(s -> new SimpleStringProperty(""));
		streetNameColum.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getName()));
		
		executeCardMenuItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	cardTable.getSelectionModel().getSelectedItem().execute(player);	        	
		    }
		});
		
		// currentRentColumn.setCellValueFactory(s -> new
		// SimpleStringProperty("" +
		// s.getValue().getRent().get()));
		player.getBalanceProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				accountBalanceLabel.textProperty().set(newValue.toString());
			}
		});
		player.getFieldProperty().addListener(new ChangeListener<Field>() {
			public void changed(javafx.beans.value.ObservableValue<? extends Field> observable, Field oldValue, Field newValue) {
				currentFieldLabel.textProperty().set(newValue.getName());
			};
		});
		currentFieldLabel.textProperty().set(player.getFieldProperty().get().getName());
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
