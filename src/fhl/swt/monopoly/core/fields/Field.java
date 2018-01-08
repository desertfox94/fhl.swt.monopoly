package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;

public abstract class Field {

	private String name;
	private int index;

	public Field(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public abstract void landing(Player player);

	public void passing(Player player) {

	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

}
