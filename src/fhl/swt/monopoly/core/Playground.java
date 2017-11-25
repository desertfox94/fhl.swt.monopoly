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
		fields.select(player.getField());
		Field field = fields.next();
		for (int i = 0; i < diceCast; i++) {
			field.passing(player);
		}
		field.landing(player);
		player.setField(field);
	}
	
}
