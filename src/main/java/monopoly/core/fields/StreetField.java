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
		return MessageUtil.ask("Strasse Kaufen", "Wollen Sie " + street.getName() + " Kaufen?", street.getName() + " kostet " + street.getPrice(), "ja", "nein, Auktion starten");
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

			//auf der strasse ist keine hypothek und der besitzer ist nicht im gefängnis
			if(!street.isMortgage() && !owner.isInJail()){
				//Miete muss bezahlt werden
				int rent =  street.getRent().intValue();

				//Spieler hat genug geld zum bezahlen
				if(player.getBalance() >= rent){
					player.pay(rent);
					owner.addMoney(rent);
				}
				//Spieler hat nicht genug geld
				else{
					int gatherable = player.getStreets().stream()
							.map(s -> s.getHotel()? (s.getRentDetails().getPricePerHouse() * 5 + s.mortgageValue()) : (s.getRentDetails().getPricePerHouse() * s.getNumberOfHouses() + s.mortgageValue()))
							.reduce(0, (a,b)-> a + b) ;

					System.out.println("gatherable: " + gatherable + " rent: " + rent);
					//Spieler hat keine möglichkeit das geld aufzutreiben
					if(gatherable < rent){
						//Todo: Spieler verliert das Spiel
						return;
					}
					else if(player.hasHouses() && player.hasNoMortgagedStreets()){
						//Spieler kann hypothek aufnehmen oder häuser verkaufen
						boolean ask = MessageUtil.ask("Zu wenig Geld", "Sie haben nicht genug Geld um die Miete zu Zahlen", "Häuser verkaufen", "Hypothek aufnehmen");
						if(ask){
							//Todo:Frage welche häuser
						}
						else {
							//Todo:Frage welche strassen mit hypothek belasten
						}

					}
					else if(player.hasNoMortgagedStreets()){
						//Todo: Frage welche Strassen
					}
					else if(player.hasHouses()){
						//Todo: Frage welche häuser
					}

					//Todo: entfernen wenn vorherige bedingungen implementiert sind
					for (Street street1 : player.streetsWithHouse()) {

						if(street1.getHotel()){
							street1.demolishHotel();
							player.addMoney(street1.getRentDetails().getPricePerHouse() * 5);
							street1.demolishHotel();
							street1.demolishHouses( 4);
						}else{
							int count = street1.getNumberOfHouses();
							street1.demolishHouses(count);
							player.addMoney(street1.getRentDetails().getPricePerHouse() * count);
						}
					}
					if(player.getBalance() < rent){

						player.getStreets().forEach(s -> s.assumeMortgage());
					}
					//to be removed ends here


					landing(player);
				}
			}

		}
	}
}
