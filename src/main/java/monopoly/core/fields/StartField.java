package monopoly.core.fields;

import static monopoly.core.Logger.*;
import monopoly.model.Player;

/**
 * the Start field in the bottom right corner of the playing field.
 */
public class StartField extends Field {

	private static final int MONEY = 200;
	

	public StartField() {
		super("Los", 0);
	}

	@Override
	public void landing(Player player) {
		ActionLogger.log(player, LANDING, "START FIELD", ""); 
		player.addMoney(MONEY * 2);
	}

	@Override
	public void passing(Player player) {
		if (!player.isInJail()) {
			ActionLogger.log(player, PASSING, "START FIELD", ""); 
			// a player who is send to jail can pass one last time the start field.
			player.addMoney(MONEY);
		}
	}

}
