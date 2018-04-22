package monopoly.core.cards;

import monopoly.core.CircleList;

public abstract class CardSet {

	private CircleList<Card> cards;
	private String name;

	public CardSet(CircleList<Card> cards, String name) {
		this.cards = cards;
		this.name = name;
	}

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

	public String getName() {
		return name;
	}

}
