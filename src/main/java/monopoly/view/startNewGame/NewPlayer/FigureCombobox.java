package monopoly.view.startNewGame.NewPlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import monopoly.model.Figure;

public class FigureCombobox {

	private static final Figure PLACEHOLDER = new Figure(null, "");

	private ObservableList<Figure> availableFigures;

	private ComboBox<Figure> comboBox;

	public FigureCombobox(ComboBox<Figure> comboBox) {
		this.comboBox = comboBox;
	}

	@SuppressWarnings("unchecked")
	public void setAvailableFigures(ObservableList<Figure> availableFigures) {
		this.availableFigures = availableFigures;
		availableFigures.addListener(new ListChangeListener() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") Change c) {
				comboBox.setItems(getItems());
			}
		});
		comboBox.setItems(getItems());
		comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Figure>() {

			@Override
			public void changed(ObservableValue<? extends Figure> observable, Figure oldValue, Figure newValue) {
				if (oldValue != null && oldValue != PLACEHOLDER) {
					availableFigures.add(oldValue);
				}
				if (newValue != null && newValue != PLACEHOLDER) {
					availableFigures.remove(newValue);
				}
			}
		});
	}

	public Figure getSelection() {
		Figure selectedItem = comboBox.getSelectionModel().getSelectedItem();
		if (selectedItem != PLACEHOLDER) {
			return selectedItem;
		}
		return null;
	}

	private ObservableList<Figure> getItems() {
		ObservableList<Figure> items = FXCollections.observableArrayList(FigureCombobox.this.availableFigures);
		Figure selected = comboBox.getSelectionModel().getSelectedItem();
		if (selected != null) {
			items.add(selected);
		}
		if (selected != PLACEHOLDER) {
			items.add(PLACEHOLDER);
		}
		return items;
	}

}
