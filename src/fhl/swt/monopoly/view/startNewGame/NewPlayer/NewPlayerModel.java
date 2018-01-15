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

	public NewPlayerModel() {
		name.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				refreshIsValid();
			}
		});
		figure.addListener(new ChangeListener<Figure>() {
			@Override
			public void changed(ObservableValue<? extends Figure> observable, Figure oldValue, Figure newValue) {
				refreshIsValid();
			}

		});
	}

	private void refreshIsValid() {
		String nameValue = name.getValue();
		Figure figureValue = figure.getValue();
		if (nameValue == null || figureValue == null) {
			valid.set(false);
		} else {
			valid.set(!nameValue.trim().isEmpty() && !figureValue.getName().isEmpty());
		}
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

	public boolean isValid() {
		return valid.get();
	}

}
