package monopoly.core.fields;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import monopoly.core.MessageUtil;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetOwner;

/**
 * A regular Street field. several of these will form a majority of the playing
 * field.
 * 
 */
public class StreetField extends Field {
	public boolean buyStreet = false; // für JunitTesting als globale
	public boolean mortage = true;
	private Street street;

	public StreetField(Street street, int index) {
		super(street.getName(), index);
		this.street = street;
	}

	public boolean ask() {
		return MessageUtil.ask("Strasse Kaufen", "Wollen Sie die Strasse Kaufen?", "ja", "nein, Auktion starten");
	}

	public boolean askForMortage() {
		return MessageUtil.ask("Hypothek aufnehmen", "Wollen Sie eine Hypothek aufnehmen?", "ja",
				"nein, Haus verkaufen");
	}

	public boolean askRedeemMortage() {
		return MessageUtil.ask("Hypothek abbezahlen?", "Wollen Sie die Hypothek abbezahlen?", "ja", "nein");
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public Street getStreet() {
		return street;
	}

	@Override
	public void landing(Player player) {
		Player owner = street.getOwner();
		buyStreet = ask();

		if (owner == null) {
			if (buyStreet) {
				player.pay(street.getPrice());
				player.addToInventory(street);
				street.setOwner(player);
			} else {
				// zur auktion freigeben
				street.startAuction();
			}
		} else if (owner != player) {

			int rent = street.isMortage() ? 0 : street.getRent().intValue();

			if (player.getBalance().intValue() < rent && (player.hasHouses() == true)
					|| (player.hasNoMortagedStreets() == true)) {
				mortage = askForMortage();

				if (mortage == true) {
					player.notMortagedStreets().get(0).assumeMortage();
				} // Abfrage welches Straße zur Hypothek TODO

				else if (mortage == false) {
					((Player) player.streetsWithHouse()).sellHouse(0);
				} // Abfrage welches Haus zum verkauf TODO
			}

			else if ((player.getBalance().intValue() >= rent) && (!((Player) owner).isInJail())) {
				player.pay(rent);
				if (owner instanceof Player) {
					((Player) owner).addMoney(rent);
				}
			}
		}
	}
}
