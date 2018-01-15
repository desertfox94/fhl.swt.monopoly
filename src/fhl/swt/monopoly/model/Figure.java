package fhl.swt.monopoly.model;

import java.awt.image.BufferedImage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Figure {

	private ObjectProperty<BufferedImage> image;

	private StringProperty name;
	
	private BooleanProperty selected;

	public Figure(BufferedImage image, String name) {
		this.image = new SimpleObjectProperty<>(image);
		this.name = new SimpleStringProperty(name);
	}

	public BufferedImage getImage() {
		return image.get();
	}

	public String getName() {
		return name.get();
	}

	public StringProperty getNameProperty() {
		return name;
	}
	
	public BooleanProperty getSelected() {
		return selected;
	}
	
	public boolean isSelected() {
		return selected.get();
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
