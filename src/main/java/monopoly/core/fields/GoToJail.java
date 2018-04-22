package monopoly.core.fields;

import monopoly.model.Player;
/**
 * the "go to jail"-field in the top right corner of the playing field.
 */
public class GoToJail extends Field {

	public GoToJail() {
		super("Gehe ins Gefängnis", 30);
	}

	@Override
	public void landing(Player player) {
		player.sendToJail();
	}

}
