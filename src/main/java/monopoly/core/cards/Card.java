package monopoly.core.cards;

import monopoly.model.Player;

public abstract class Card {

	protected CardSet cardSet;

	private String title;

	private String description;

	public Card(String title, String description) {
		this.title = title.trim();
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}

	public abstract boolean execute(Player player);

	@Override
	public String toString() {
		return title;
	}
}
