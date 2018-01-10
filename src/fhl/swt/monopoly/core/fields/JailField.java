package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;

public class JailField extends Field {

	public static final int INDEX = 10;

	public JailField() {
		super("Gefängnis", INDEX);
	}

	@Override
	public void landing(Player player) {
	}

}
