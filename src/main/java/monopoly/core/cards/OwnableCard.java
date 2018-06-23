package monopoly.core.cards;

import monopoly.core.Logger;
import monopoly.model.Player;

public abstract class OwnableCard extends Card {

	protected Player player;
	
	public OwnableCard(String title, String description) {
		super(title, description);
	}

	public void assignTo(Player player) {
		this.player = player;
		Logger.ActionLogger.log(player, "ASSIGNED TO", toString());
		player.addCardToInventory(this);
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public final boolean execute(Player player) {
		Logger.ActionLogger.log(player, "OWNABLE EXECUTED", toString());
		boolean result = execute();
		player.removeCardFromInventory(this);
		cardSet.returnCard(this);
		player = null;
		return result;
	}
	
	protected abstract boolean execute();
	
}
