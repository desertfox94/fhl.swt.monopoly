package monopoly.core.fields;

import monopoly.core.MessageUtil;
import monopoly.model.Player;
import monopoly.model.Street;

/**
 * A regular Street field. several of these will form a majority of the playing
 * field.
 * 
 */
public class StreetField extends Field {
	public boolean buyStreet = false; // für JunitTesting als globale
	public boolean mortgage = true;
	private Street street;

	public StreetField(Street street, int index) {
		super(street.getName(), index);
		this.street = street;
	}

	public boolean ask() {
		return MessageUtil.ask("Strasse Kaufen", "Wollen Sie die Strasse Kaufen?", "ja", "nein, Auktion starten");
	}

	public boolean askForMortgage() {
		return MessageUtil.ask("Hypothek aufnehmen", "Wollen Sie eine Hypothek aufnehmen?", "ja",
				"nein, Haus verkaufen");
	}

	public boolean askRedeemMortgage() {
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

			int rent = street.isMortgage() ? 0 : street.getRent().intValue();

			if (player.getBalance().intValue() < rent && (player.hasHouses() == true)
					|| (player.hasNoMortgagedStreets() == true)) {
				mortgage = askForMortgage();

				if (mortgage == true) {
					player.notMortgagedStreets().get(0).assumeMortgage();
				} // Abfrage welches Straße zur Hypothek TODO

				else if (mortgage == false) {
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
