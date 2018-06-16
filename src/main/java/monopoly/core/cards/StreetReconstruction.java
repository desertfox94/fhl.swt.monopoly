package monopoly.core.cards;

import javafx.collections.ObservableList;
import monopoly.model.Player;
import monopoly.model.Street;

public class StreetReconstruction extends Card {

	public static final int COSTS_PER_HOUSE = 800;
	public static final int COSTS_PER_HOTEL = 2300;

	public StreetReconstruction() {
		super("Straßenausbesserungsarbeiten", "Du wirst zu Straßenausbesserungsarbeiten herangezogen. Zahle für deine Häuser und Hotels:\nDM 800 je Haus\nDM 2300 je Hotel\nAn die Bank");
	}

	@Override
	public boolean execute(Player player) {
		ObservableList<Street> streets = player.getStreets();
		int houseCount = 0;
		int hotelCount = 0;
		for (Street street : streets) {
			if (street.getHotel()) {
				hotelCount++;
			} else {
				houseCount += street.getNumberOfHouses();
			}
		}
		player.pay(hotelCount * COSTS_PER_HOTEL + houseCount * COSTS_PER_HOUSE);
		return true;
	}

}
