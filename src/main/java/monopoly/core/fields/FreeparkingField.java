package monopoly.core.fields;

import static monopoly.core.Logger.ActionLogger;
import static monopoly.core.Logger.LANDING;

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
		ActionLogger.log(player, LANDING, "FREE PARKING", "");
		player.getGame().payOutMoneyInTheMiddle(player);
	}

}
