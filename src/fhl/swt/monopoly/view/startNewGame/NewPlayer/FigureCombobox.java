package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import fhl.swt.monopoly.model.Figure;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class FigureCombobox {

	private static final Figure EMPTY = new Figure(null, "");

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
				if (oldValue != null && oldValue != EMPTY) {
					availableFigures.add(oldValue);
				}
				if (newValue != null && newValue != EMPTY) {
					availableFigures.remove(newValue);
				}
			}
		});
	}

	public Figure getSelection() {
		Figure selectedItem = comboBox.getSelectionModel().getSelectedItem();
		if (selectedItem != EMPTY) {
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
		if (selected != EMPTY) {
			items.add(EMPTY);
		}
		return items;
	}

}
