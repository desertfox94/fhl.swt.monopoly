package monopoly.view.startNewGame.NewPlayer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import monopoly.model.Figure;
import monopoly.model.Player;
import monopoly.view.startNewGame.NewGameController;

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

	private NewPlayerModel model = new NewPlayerModel(this);

	private GroupCombobox figureCombobox;

	private NewGameController gameController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.textProperty().bindBidirectional(model.getNameProperty());
		name.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (newValue != null) {
					newValue = newValue.trim();
					if (newValue.length() > MAX_PLAYER_NAME_LENGTH) {
						newValue = newValue.substring(0, MAX_PLAYER_NAME_LENGTH);
					}
					name.textProperty().set(newValue);
				}
			}

		});
	}

	public void setNameValid(boolean valid) {
		if (valid) {
			name.getStyleClass().removeAll(NAME_ERROR);
		} else {
			name.getStyleClass().add(NAME_ERROR);
		}
	}

	public void setFigureValid(boolean valid) {
		if (valid) {
			combobox.getStyleClass().removeAll(FIGURE_ERROR);
			combobox.getStyleClass().add("figureValid");

		} else {
			combobox.getStyleClass().removeAll("figureValid");
			combobox.getStyleClass().add(FIGURE_ERROR);
		}
	}

	public boolean isUsed(String name) {
		return gameController.isUsed(name);
	}

	public void setGameController(NewGameController gameController) {
		this.gameController = gameController;
	}

	public void setNumber(int n) {
		number.textProperty().set(String.valueOf(n));
	}

	public boolean isModelValid() {
		return model.isValid();
	}

	public boolean isModelIncomplete() {
		return model.isIncomplete();
	}

	public void setItems(ComboGroup group, List<Figure> availableFigures) {
		figureCombobox = new GroupCombobox(group, availableFigures, combobox);
		model.getFigure().bind(figureCombobox.getSelection());
	}

	public void addModelValidationListener(ChangeListener<Boolean> changeListener) {
		model.getValidProperty().addListener(changeListener);
		model.getIncomplete().addListener(changeListener);
	}

	public TextField getName() {
		return name;
	}

	public Player getPlayer() {
		Player player = new Player();
		player.setName(model.getNameProperty().get());
		player.setFigure(model.getFigure().get());
		return player;
	}

	public NewPlayerModel getModel() {
		return model;
	}
}
