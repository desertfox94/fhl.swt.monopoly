package fhl.swt.monopoly.core.cards;

import fhl.swt.monopoly.model.Player;

public class FreeFromJail extends Card implements OwnableCard {

	private Player player;

	public FreeFromJail() {
		super("Gef�nginsfreikarte", "Sie kommen aus dem Gef�ngnis frei!");
	}

	@Override
	public boolean execute(Player player) {
		return false;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

}
