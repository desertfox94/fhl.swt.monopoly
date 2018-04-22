package monopoly.model;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class StreetGroup {

	private List<Street> streets = new LinkedList<>();
	private Color color;
	private BigDecimal pricePerHouse;

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

	public BigDecimal getPricePerHouse() {
		return pricePerHouse;
	}

	public void setPricePerHouse(BigDecimal pricePerHouse) {
		this.pricePerHouse = pricePerHouse;
	}
}
