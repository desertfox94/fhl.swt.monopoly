package fhl.swt.monopoly.core;

import fhl.swt.monopoly.core.fields.Field;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Player;

public class Playground {

	private final CircleList<Field> fields;

	public Playground(Edition edition) {
		fields = edition.getFields();
	}

	public void movePlayer(Player player, int diceCast) {
		fields.select(player.getField().get());
		Field field = null;
		for (int i = 0; i < diceCast; i++) {
			field = fields.next();
			player.moveTo(field);
			field.passing(player);
		}
		field.landing(player);
		player.moveTo(field);
	}

}
