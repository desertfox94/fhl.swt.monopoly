package fhl.swt.monopoly.model;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import fhl.swt.monopoly.core.CircleList;
import fhl.swt.monopoly.core.fields.Field;

public class Edition {

	private String name;
	private CircleList<Field> fields;
	private int maxAmountOfPlayers;
	private String currency;
	private double currencyFactor;
	private List<Color> colors;
	private Image background;

	public String getName() {
		return name;
	}

	public CircleList<Field> getFields() {
		return fields;
	}

	public void setFields(CircleList<Field> fields) {
		this.fields = fields;
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
