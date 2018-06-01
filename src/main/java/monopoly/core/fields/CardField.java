package monopoly.core.fields;

import monopoly.core.cards.Card;
import monopoly.core.cards.CardSet;
import monopoly.core.cards.OwnableCard;
import monopoly.model.Player;
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
		if (card instanceof OwnableCard) {
			((OwnableCard) card).assignTo(player);
		} else {
			card.execute(player);
			cards.returnCard(card);
		}
	}

}
