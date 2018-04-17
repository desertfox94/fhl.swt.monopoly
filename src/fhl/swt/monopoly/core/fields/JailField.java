package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;
/**
 * the Jail field in the bottom left corner of the playing field.
 */
public class JailField extends Field {

	public static final int INDEX = 10;

	public JailField() {
		super("Gef�ngnis", INDEX);
	}

	@Override
	public void landing(Player player) {
	}

}
