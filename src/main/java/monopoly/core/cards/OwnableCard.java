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
		if (player != null && !this.player.equals(player)) {
			throw new RuntimeException(String.format("Diese Karte kann nicht auf %s angewendet werden, da sie bereits %s zugewiesen ist!", player.toString(), this.player.toString()));
		}
		boolean result = execute();
		player.removeCardFromInventory(this);
		player = null;
		return result;
	}
	
	protected abstract boolean execute();
	
}
