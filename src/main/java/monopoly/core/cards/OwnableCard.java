package monopoly.core.cards;

import monopoly.model.Player;

public abstract class OwnableCard extends Card {

	protected Player player;
	
	public OwnableCard(String title, String description) {
		super(title, description);
	}

	public void assignTo(Player player) {
		this.player = player;
		player.addCardToInventory(this);
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public final boolean execute(Player player) {
		boolean result = execute();
		player.removeCardFromInventory(this);
		cardSet.returnCard(this);
		player = null;
		return result;
	}
	
	protected abstract boolean execute();
	
}
