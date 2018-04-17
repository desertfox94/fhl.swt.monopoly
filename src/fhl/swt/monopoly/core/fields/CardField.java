package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.core.cards.Card;
import fhl.swt.monopoly.core.cards.CardSet;
import fhl.swt.monopoly.model.Player;
/**
 * This class represents community chests or chance card fields, on which the player has to draw a card from either stack respectively.
 */
public class CardField extends Field {

	private CardSet cards;

	public CardField(CardSet cards, int number) {
		super(cards.getName(), number);
		this.cards = cards;
	}
	
	@Override
	public void landing(Player player) {
		Card card = cards.draw();
		if (card != null) {
			card.execute(player);
		}
	}

}
