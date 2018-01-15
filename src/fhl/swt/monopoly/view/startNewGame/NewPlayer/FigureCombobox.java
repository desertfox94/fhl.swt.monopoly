package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import fhl.swt.monopoly.model.Figure;

public class FigureCombobox extends ComboBox<Figure> {

	private static final Figure EMPTY = new Figure(null, "");
	
	private ObservableList<Figure> availableFigures;

	public FigureCombobox() {
		super();
		selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<Figure>>() {
			
			@Override
			public void changed(
					ObservableValue<? extends SingleSelectionModel<Figure>> observable,
					SingleSelectionModel<Figure> oldValue,
					SingleSelectionModel<Figure> newValue) {
				Figure old = oldValue.getSelectedItem();
				if (old != null && old != EMPTY) {
					availableFigures.add(old);
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
				ObservableList<Figure> items = FXCollections.observableList(FigureCombobox.this.availableFigures);
				Figure selected = getSelectionModel().getSelectedItem();
				if (selected != null) {
					items.add(selected);
				}
				items.add(EMPTY);
			}
		});
	}
	
	
	
	
}
