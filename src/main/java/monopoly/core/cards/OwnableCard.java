package monopoly.core.cards;

import monopoly.model.Player;


public interface OwnableCard {

	Player getPlayer();
	
	void setPlayer(Player player);
	
	default boolean isAvailabe() {
		return getPlayer() == null;
	}

}