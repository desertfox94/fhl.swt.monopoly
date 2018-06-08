package monopoly.model;

import java.util.LinkedList;
import java.util.List;

import monopoly.core.CircleList;
import monopoly.core.fields.Field;

public class Edition {

	private String name;
	private CircleList<Field> fields;
	private int maxAmountOfPlayers;
	private String currency;
	private int currencyFactor;
	private List<Figure> figures = new LinkedList<>();
	private String background;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CircleList<Field> getFields() {
		return fields;
	}

	public void setFields(CircleList<Field> fields) {
		this.fields = fields;
	}

	public int getMaxAmountOfPlayers() {
		return maxAmountOfPlayers;
	}

	public void setMaxAmountOfPlayers(int maxAmountOfPlayers) {
		this.maxAmountOfPlayers = maxAmountOfPlayers;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getCurrencyFactor() {
		return currencyFactor;
	}

	public void setCurrencyFactor(int currencyFactor) {
		this.currencyFactor = currencyFactor;
	}

	public List<Figure> getFigures() {
		return figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

	public void addFigure(Figure figure) {
		figures.add(figure);
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	@Override
	public String toString() {
		return name;
	}

}
