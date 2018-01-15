package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
	private ComboBox<String> icons;
	
	@FXML
	private TextField name;

	private NewPlayerModel newPlayerModel = new NewPlayerModel();

	private ObservableList<String> items;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setNumber(int n) {
		number.textProperty().set(String.valueOf(n));
	}

	public boolean isModelValid() {
//		icons.setItems(value);
		newPlayerModel.getName().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				newPlayerModel.getValidProperty().set(!newPlayerModel.getName().get().trim().isEmpty() && !newPlayerModel.getIcon().get().trim().isEmpty());
			}
			
		});
		newPlayerModel.getIcon().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				newPlayerModel.getValidProperty().set(!newPlayerModel.getName().get().trim().isEmpty() && !newPlayerModel.getIcon().get().trim().isEmpty());
			}
			
		});
		return newPlayerModel.getValidProperty().get();
	}
	
	public void setItems(ObservableList<String> availableFigures) {
		this.items = FXCollections.observableArrayList(availableFigures);
		icons.setItems(items);
		availableFigures.addListener(new ListChangeListener() {

			@Override
			public void onChanged(Change c) {
			}
			
		});
		
	}
	
}
