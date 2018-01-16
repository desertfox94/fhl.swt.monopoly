package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import fhl.swt.monopoly.model.Figure;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NewPlayerModel {

	private BooleanProperty valid = new SimpleBooleanProperty(false);

	private StringProperty name = new SimpleStringProperty();

	private ObjectProperty<Figure> figure = new SimpleObjectProperty<>();

	private BooleanProperty incomplete = new SimpleBooleanProperty();

	public NewPlayerModel() {
		name.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				refreshStatus();
			}
		});
		figure.addListener(new ChangeListener<Figure>() {
			@Override
			public void changed(ObservableValue<? extends Figure> observable, Figure oldValue, Figure newValue) {
				refreshStatus();
			}

		});
	}

	private void refreshStatus() {
		
		boolean nameIsEmpty = name.getValue() == null || name.getValue().isEmpty();
		Figure figureValue = figure.getValue();
		boolean figureNotSelected = figureValue == null || isDummy(figureValue);

		if (nameIsEmpty && figureNotSelected) {
			incomplete.set(false);
			valid.set(false);
		} else if (nameIsEmpty || figureNotSelected) {
			incomplete.set(true);
			valid.set(false);
		} else {
			incomplete.set(false);
			valid.set(true);
		}
	}
	
	private boolean isDummy(Figure figure) {
		return figure.getImage() == null;
	}

	public BooleanProperty getValidProperty() {
		return valid;
	}

	public StringProperty getName() {
		return name;
	}

	public ObjectProperty<Figure> getFigure() {
		return figure;
	}

	public BooleanProperty getIncomplete() {
		return incomplete;
	}

	public boolean isIncomplete() {
		return incomplete.get();
	}

	public boolean isValid() {
		return valid.get();
	}

}
