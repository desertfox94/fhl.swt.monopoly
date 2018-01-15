package fhl.swt.monopoly.view.startNewGame.NewPlayer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NewPlayerModel {

	private BooleanProperty valid;
	
	private StringProperty name;
	
	private StringProperty icon;
	
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
