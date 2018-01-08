package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;

public class StartField extends Field {

	public StartField() {
		super("Los", 0);
	}

	@Override
	public void landing(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void passing(Player player) {
		// player.addMoney(new BigDecimal(8000));
	}

}
