package monopoly.view.startNewGame.NewPlayer;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import monopoly.model.Figure;

public class GroupCombobox {

	private static final Figure PLACEHOLDER = new Figure(null, "");

	private List<Figure> availableFigures;

	private SimpleObjectProperty<Figure> selection = new SimpleObjectProperty<Figure>();

	private ComboBox<Figure> comboBox;

	private ComboGroup group;

	public GroupCombobox(ComboGroup group, List<Figure> availableFigures, ComboBox<Figure> comboBox) {
		this.group = group;
		this.comboBox = comboBox;
		this.availableFigures = availableFigures;
		init();
	}

	public void init() {
		group.add(this);
		refreshItems();
		comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Figure>() {

			@Override
			public void changed(ObservableValue<? extends Figure> observable, Figure oldValue, Figure newValue) {
				if (oldValue != null) {
					oldValue.selected = false;
				}
				if (newValue == PLACEHOLDER) {
					selection.set(null);
				} else if (newValue != null) {
					newValue.selected = true;
					selection.set(newValue);
				}

			}
		});

		comboBox.addEventHandler(ComboBox.ON_HIDDEN, event -> {
			group.notify(GroupCombobox.this);
		});

	}

	void refreshItems() {
		comboBox.setItems(getItems());
	}

	public SimpleObjectProperty<Figure> getSelection() {
		return selection;
	}

	private ObservableList<Figure> getItems() {
		List<Figure> selectableFigures = new LinkedList<Figure>();
		for (Figure figure : availableFigures) {
			if (!figure.selected) {
				selectableFigures.add(figure);
			}
		}
		Figure selected = comboBox.getSelectionModel().getSelectedItem();
		if (selected != null) {
			selectableFigures.add(selected);
		}
		if (selected != PLACEHOLDER) {
			selectableFigures.add(PLACEHOLDER);
		}
		return FXCollections.observableArrayList(selectableFigures);
	}

}
