package fhl.swt.monopoly.view.playerInventory;

import java.util.List;

import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;

public class PlayerInventoryModel {

	private Player player;

	public String getAccountBalance() {
		return player.getBalance().toString();
	}

	public List<Street> getStreets() {
		return player.getStreets();
	}

}
