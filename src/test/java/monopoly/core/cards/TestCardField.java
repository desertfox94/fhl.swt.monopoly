package monopoly.core.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.core.CircleList;
import monopoly.core.fields.CardField;
import monopoly.model.Player;

public class TestCardField {

	private boolean cardExecuted;

	@Test
	public void test_landOnCard() {
		CircleList<Card> cards = new CircleList<>();
		final Player expected = new Player();
		cardExecuted = false;
		Card card = new Card("", "") {
			@Override
			public boolean execute(Player player) {
				cardExecuted = true;
				assertEquals(expected, player);
				return true;
			}
		};

		cards.add(card);
		CardSet set = new CardSet(cards, "TestKarten");
		CardField field = new CardField(set, 0);
		field.landing(expected);
		assertTrue(cardExecuted);
		field.landing(expected);
	}

}
