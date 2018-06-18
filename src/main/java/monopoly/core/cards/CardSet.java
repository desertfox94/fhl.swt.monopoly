package monopoly.core.cards;

import monopoly.core.CircleList;

public class CardSet {

	private CircleList<Card> cards;

	private String name;

	public CardSet(CircleList<Card> cards, String name) {
		this.cards = cards;
		this.name = name;
		cards.toList().forEach(c -> c.setCardSet(this));
	}

	public Card draw() {
		Card card = cards.next();
		cards.remove(card);
		return card;
	}

	public void returnCard(Card card) {
		cards.putLast(card);
	}

	public String getName() {
		return name;
	}

}
