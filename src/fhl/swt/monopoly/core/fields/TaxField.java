package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;

public class TaxField extends Field {

	private static final int TAX = 100;

	public TaxField(int index) {
		super("Einkommenssteuer", index);
	}

	@Override
	public void landing(Player player) {
		player.pay(TAX);
	}

}
