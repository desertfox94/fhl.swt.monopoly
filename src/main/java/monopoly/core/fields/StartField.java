package monopoly.core.fields;

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
		player.addMoney(MONEY * 2);
	}

	@Override
	public void passing(Player player) {
		player.addMoney(MONEY);
	}

}
