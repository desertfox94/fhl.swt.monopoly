package fhl.swt.monopoly.core.cards;

import fhl.swt.monopoly.model.Player;


public interface OwnableCard {

	Player getPlayer();
	
	void setPlayer(Player player);
	
	default boolean isAvailabe() {
		return getPlayer() == null;
	}

}
