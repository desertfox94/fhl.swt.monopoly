package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;

public abstract class Field {

	private String name;

	public Field(String name) {
		this.name = name;
	}

	public abstract void landing(Player player);

	public void passing(Player player) {

	}

	public String getName() {
		return name;
	}

}
