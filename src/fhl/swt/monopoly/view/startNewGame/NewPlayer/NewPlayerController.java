package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import java.net.URL;
import java.util.ResourceBundle;

import fhl.swt.monopoly.model.Figure;
import fhl.swt.monopoly.model.Player;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewPlayerController implements Initializable {

	@FXML
	private Label number;

	@FXML
	private ComboBox<Figure> combobox;

	@FXML
	private TextField name;

	private NewPlayerModel newPlayerModel = new NewPlayerModel();

	private FigureCombobox figureCombobox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.textProperty().bindBidirectional(newPlayerModel.getName());
		newPlayerModel.getFigure().bind(combobox.getSelectionModel().selectedItemProperty());
		figureCombobox = new FigureCombobox(combobox);
	}

	public void setNumber(int n) {
		number.textProperty().set(String.valueOf(n));
	}

	public boolean isModelValid() {
		return newPlayerModel.isValid();
	}

	public void setItems(ObservableList<Figure> availableFigures) {
		figureCombobox.setAvailableFigures(availableFigures);
	}

	public void addValidListener(ChangeListener<Boolean> changeListener) {
		newPlayerModel.getValidProperty().addListener(changeListener);
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
