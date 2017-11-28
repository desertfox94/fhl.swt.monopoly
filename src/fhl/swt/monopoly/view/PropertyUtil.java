package fhl.swt.monopoly.view;

import java.util.function.Consumer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PropertyUtil {

	public static StringProperty stringProperty(String value, Consumer<String> setter) {
		StringProperty property = new SimpleStringProperty(value);
		addSetter(property, setter);
		return property;
	}

	public static BooleanProperty booleanPropertyNotNull(Boolean value, Consumer<Boolean> setter) {
		return booleanProperty(value != null ? value : false, setter);
	}

	public static BooleanProperty booleanProperty(boolean value, Consumer<Boolean> setter) {
		BooleanProperty property = new SimpleBooleanProperty(value);
		addSetter(property, setter);
		return property;
	}

	public static <T> SimpleObjectProperty<T> property(T value, Consumer<T> setter) {
		SimpleObjectProperty<T> property = new SimpleObjectProperty<>(value);
		addSetter(property, setter);
		return property;
	}

	private static <T> void addSetter(Property<T> property, Consumer<T> setter) {
		property.addListener(newChangeListener(setter));
	}

	private static <T> ChangeListener<T> newChangeListener(Consumer<T> setter) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				setter.accept(newValue);
			}
		};
	}

}
