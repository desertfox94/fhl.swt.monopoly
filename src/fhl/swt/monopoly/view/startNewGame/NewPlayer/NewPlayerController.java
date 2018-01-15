package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	private FigureCombobox icons;

	@FXML
	private TextField name;

	private NewPlayerModel newPlayerModel = new NewPlayerModel();

	private ObservableList<String> availableFigures;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.textProperty().bindBidirectional(newPlayerModel.getName());
		newPlayerModel.getName().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				newPlayerModel.getValidProperty().set(
						newPlayerModel.getName().get() != null
								&& newPlayerModel.getIcon().get() != null
								&& !newPlayerModel.getName().get().trim()
										.isEmpty()
								&& !newPlayerModel.getIcon().get().trim()
										.isEmpty());
			}

		});
		icons.selectionModelProperty().addListener(
				new ChangeListener<Object>() {
					@Override
					public void changed(
							ObservableValue<? extends Object> observable,
							Object oldValue, Object newValue) {
						newPlayerModel.getIcon().set(newValue.toString());
					}

				});

		newPlayerModel.getIcon().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				newPlayerModel.getValidProperty().set(
						!newPlayerModel.getName().get().trim().isEmpty()
								&& !newPlayerModel.getIcon().get().trim()
										.isEmpty());
			}

		});
	}

	public void setNumber(int n) {
		number.textProperty().set(String.valueOf(n));
	}

	public boolean isModelValid() {
		return newPlayerModel.getValidProperty().get();
	}

	public void setItems(ObservableList<String> availableFigures) {
//		icons.setAvailableFigures(availableFigures);
//		icons.setItems(availableFigures);
	}

	public void addValidListener(ChangeListener<Boolean> changeListener) {
		newPlayerModel.getValidProperty().addListener(changeListener);
	}
}
