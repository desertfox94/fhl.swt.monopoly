package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;
/**
 * A tax field. two of these exist total, and a landing player has to pay a fixed amount of money to the bank.
 */
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
