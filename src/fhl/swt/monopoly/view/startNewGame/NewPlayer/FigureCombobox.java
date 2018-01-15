package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import fhl.swt.monopoly.model.Figure;

public class FigureCombobox{

	private static final Figure EMPTY = new Figure(null, "");

	private ObservableList<Figure> availableFigures;

	private ComboBox<Figure> comboBox;
	
	public FigureCombobox(ComboBox<Figure> comboBox) {
		this.comboBox = comboBox;
		comboBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<Figure>>() {

			@Override
			public void changed(ObservableValue<? extends SingleSelectionModel<Figure>> observable, SingleSelectionModel<Figure> oldValue, SingleSelectionModel<Figure> newValue) {
				Figure old = oldValue.getSelectedItem();
				if (old != null && old != EMPTY) {
					availableFigures.add(old);
					old.setSelected(false);
				}
				Figure selectedItem = newValue.getSelectedItem();
				if (selectedItem != EMPTY) {
					availableFigures.remove(selectedItem);
					selectedItem.setSelected(true);
				}
			}
		});
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
		items.add(EMPTY);
		return items;
	}

}
