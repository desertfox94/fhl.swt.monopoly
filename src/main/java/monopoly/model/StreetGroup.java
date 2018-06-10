package monopoly.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class StreetGroup {

	private List<Street> streets = new LinkedList<>();
	private Color color;
	private int pricePerHouse;

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPricePerHouse() {
		return pricePerHouse;
	}

	public void setPricePerHouse(int pricePerHouse) {
		this.pricePerHouse = pricePerHouse;
	}
}
