package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.core.cards.CardSet;
import fhl.swt.monopoly.model.Player;

public class CardField extends Field {

	private CardSet cards;

	public CardField(CardSet cards) {
		super(cards.getName());
		this.cards = cards;
	}

	@Override
	public void landing(Player player) {
		cards.draw().execute(player);
	}

}
