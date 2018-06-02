package monopoly.core.mock;

import monopoly.core.MessageUtil;
import monopoly.core.cards.Card;
import monopoly.model.Player;

public class DummyCard extends Card {

	private static int count = 0;

	public DummyCard() {
		super("Karte " + count, "Dies ist Karte Nummer " + count++);
	}

	@Override
	public boolean execute(Player player) {
		MessageUtil.inform(getTitle(), getDescription());
		return false;
	}

}
