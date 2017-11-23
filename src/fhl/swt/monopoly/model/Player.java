package fhl.swt.monopoly.model;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import fhl.swt.monopoly.core.cards.Card;

public class Player implements StreetOwner, CardOwner {

	private String id;
	private String name;
	private BigDecimal balance;
	private List<Street> streets;
	private List<Card> cards;
	private int jailCount;
	private int position;
	private Color color;
	private int doubleCount;

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

	public BigDecimal getBalance() {
		return balance;
	}

	public void pay(BigDecimal amount) {
		balance.subtract(amount);
	}
	
	public void addMoney(BigDecimal amount) {
		balance.add(amount);
	}

	@Override
	public List<Street> getStreets() {
		return streets;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public boolean isInJail() {
		return jailCount > 0;
	}
	
	public void freeFromJail() {
		jailCount = 0;
	}

	public void sendToJail() {
		jailCount++;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void incDoubleCount() {
		doubleCount++;
	}

	public int getDoubleCount() {
		return doubleCount;
	}

	@Override
	public void addToInventory(Street street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromInventory(Street street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCardToInventory(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCardFromInventory(Card card) {
		// TODO Auto-generated method stub
		
	}

}
