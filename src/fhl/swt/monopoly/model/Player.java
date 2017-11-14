package fhl.swt.monopoly.model;

import java.math.BigDecimal;
import java.util.List;

public class Player {

	private String id;
	private String name;
	private Figur figur;
	private BigDecimal balance;
	private List<Street> streets;
	private List<Card> cards;
	private boolean inJail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Figur getFigur() {
		return figur;
	}

	public void setFigur(Figur figur) {
		this.figur = figur;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

}
