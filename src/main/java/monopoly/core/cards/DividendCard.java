package monopoly.core.cards;

import monopoly.model.Player;

public class DividendCard extends Card {

	public DividendCard() {
		super("Die Bank zahlt dir eine Dividende", "Die Bank zahlt dir eine Dividende von\r\nDM 1000");
	}

	@Override
	public boolean execute(Player player) {
		player.addMoney(1000);
		return true;
	}

}
