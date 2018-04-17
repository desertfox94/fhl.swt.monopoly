package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.model.Player;
/**
 * This abstract class serves as a super class for all fields a player can land on.
 * (such as streets, go, free parking, ... )
 */
public abstract class Field {

	private String name;
	private int index;

	public Field(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	/**
	 * triggers an event for when the player lands on a specific field (such as buy street dialog, rent and event cards)
	 * @param player the landing player
	 */
	public abstract void landing(Player player);

	/**
	 * triggers an event for when the player passes specific field (used right now exclusively for the start field)
	 * @param player the passing player
	 */
	public void passing(Player player) {

	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

}
