package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;

public class StreetField extends Field {

	private Street street;

	public StreetField(Street street, int index) {
		super(street.getName(), index);
		this.street = street;
	}

	@Override
	public void landing(Player player) {
		// wanna buy a street?
	}

}
