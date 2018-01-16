package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import fhl.swt.monopoly.model.Figure;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.view.startNewGame.NewGameController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class NewPlayerController implements Initializable {

	private static final String NAME_ERROR = "nameError";

	private static final String FIGURE_ERROR = "figureError";

	private static final int MAX_PLAYER_NAME_LENGTH = 40;

	@FXML
	private Label number;

	@FXML
	private ComboBox<Figure> combobox;

	@FXML
	private TextField name;

	private NewPlayerModel newPlayerModel = new NewPlayerModel(this);

	private FigureCombobox figureCombobox;

	private NewGameController gameController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.textProperty().bindBidirectional(newPlayerModel.getName());
		name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (newValue != null) {
					newValue = newValue.trim();
					if (newValue.length() > MAX_PLAYER_NAME_LENGTH) {
						newValue = newValue.substring(0, MAX_PLAYER_NAME_LENGTH);
					}
					setNameValid(!gameController.isUsed(newValue));
					name.textProperty().set(newValue);
				}
			}
			
		});
		newPlayerModel.getFigure().bind(combobox.getSelectionModel().selectedItemProperty());
		figureCombobox = new FigureCombobox(combobox);
	}
	
	void setNameValid(boolean valid) {
		if (valid) {
			name.getStyleClass().remove(NAME_ERROR);
		} else {
			name.getStyleClass().add(NAME_ERROR);
		}
	}
	
	void setFigureValid(boolean valid) {
		if (valid) {
			combobox.getStyleClass().remove(FIGURE_ERROR);
			combobox.getStyleClass().add("figureValid");

		} else {
			combobox.getStyleClass().remove("figureValid");
			combobox.getStyleClass().add(FIGURE_ERROR);
		}
	}
	
	public void setGameController(NewGameController gameController)
	{
		this.gameController = gameController;
	}
	
	public void setNumber(int n) {
		number.textProperty().set(String.valueOf(n));
	}

	public boolean isModelValid() {
		return newPlayerModel.isValid();
	}
	
	public boolean isModelIncomplete() {
		return newPlayerModel.isIncomplete();
	}

	public void setItems(ObservableList<Figure> availableFigures) {
		figureCombobox.setAvailableFigures(availableFigures);
	}

	public void addModelValidationListener(ChangeListener<Boolean> changeListener) {
		newPlayerModel.getValidProperty().addListener(changeListener);
		newPlayerModel.getIncomplete().addListener(changeListener);
	}

	public TextField getName() {
		return name;
	}

	public Player getPlayer() {
		Player player = new Player();
		player.setName(newPlayerModel.getName().get());
		player.setFigure(newPlayerModel.getFigure().get());
		return player;
	}

	public NewPlayerModel getNewPlayerModel() {
		return newPlayerModel;
	}
}
