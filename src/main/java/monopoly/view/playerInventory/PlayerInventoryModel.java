package monopoly.view.playerInventory;

import java.util.List;

import monopoly.model.Player;
import monopoly.model.Street;

public class PlayerInventoryModel {

	private Player player;

	public String getAccountBalance() {
		return String.valueOf(player.getBalance());
	}

	public List<Street> getStreets() {
		return player.getStreets();
	}

}
