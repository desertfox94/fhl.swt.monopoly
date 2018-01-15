package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewPlayerModel {

	private BooleanProperty valid = new SimpleBooleanProperty();

	private StringProperty name = new SimpleStringProperty();

	private StringProperty icon = new SimpleStringProperty();

	public NewPlayerModel() {
	}

	public BooleanProperty getValidProperty() {
		return valid;
	}

	public StringProperty getName() {
		return name;
	}

	public StringProperty getIcon() {
		return icon;
	}

}
