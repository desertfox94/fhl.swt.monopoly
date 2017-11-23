package fhl.swt.monopoly.model;

import java.util.List;

import fhl.swt.monopoly.core.cards.Card;

public interface CardOwner {

	public List<Card> getCards();
	
	public void addCardToInventory(Card card);

	public void removeCardFromInventory(Card card);
	
}
