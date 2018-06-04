package monopoly.core.fields;

import monopoly.model.Player;

/**
 * the free parking field in the top left corner of the playing field.
 *
 */
public class FreeparkingField extends Field {

	public FreeparkingField() {
		super("Frei Parken", 20);
	}

	@Override
	public void landing(Player player) {
		player.getGame().payOutMoneyInTheMiddle(player);
	}

}
