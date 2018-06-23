package monopoly.core.fields;

import static monopoly.core.Logger.*;
import monopoly.model.Player;
/**
 * the "go to jail"-field in the top right corner of the playing field.
 */
public class GoToJail extends Field {

	public GoToJail() {
		super("Gehe ins Gef�ngnis", 30);
	}

	@Override
	public void landing(Player player) {
		ActionLogger.log(player, LANDING, "GO TO JAIL", "");
		player.sendToJail();
	}

}
