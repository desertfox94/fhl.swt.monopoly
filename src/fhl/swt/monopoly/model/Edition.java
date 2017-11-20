package fhl.swt.monopoly.model;

import java.awt.Color;
import java.util.List;

import com.sun.prism.Image;

public class Edition {

	private String name;
	private List<Field> fields;
	private int maxAmountOfPlayers;
	private String currency;
	private double currencyFactor;
	private List<Color> colors;
	private Image background;

	public String getName() {
		return name;
	}

	public Field getField(int i) {
		return fields.get(i);
	}

	public String getWaehrung() {
		return currency;
	}

	public void setWaehrung(String waehrung) {
		this.currency = waehrung;
	}

	public List<Color> getFiguren() {
		return colors;
	}

	public Image getBackground() {
		return background;
	}

	public double getCurrencyFactor() {
		return currencyFactor;
	}

	public int getMaxAmountOfPlayers() {
		return maxAmountOfPlayers;
	}

}
