package fhl.swt.monopoly.core.cards;

import fhl.swt.monopoly.core.CircleList;

public abstract class CardSet {

	private CircleList<Card> cards;
	
	protected void addCard(Card card) {
		cards.add(card);
	}
	
	abstract void initCards();

	public Card draw() {
		Card card = cards.next();
		cards.remove(card);
		return card;
	}
	
	public void add(Card card) {
		cards.add(card);
	}
	
	
}
