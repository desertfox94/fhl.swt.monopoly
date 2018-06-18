package monopoly.model;

import java.awt.image.BufferedImage;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Figure {

	private ObjectProperty<BufferedImage> image;

	private StringProperty name;

	public boolean selected;

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

	@Override
	public String toString() {
		return getName();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
