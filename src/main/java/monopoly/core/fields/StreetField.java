package monopoly.core.fields;

import monopoly.core.MessageUtil;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetOwner;

/**
 * A regular Street field. several of these will form a majority of the playing field.
 * 
 * MessageUtils auskommentiert, da  streetField.landing(player) im junittest einen ExceptioninitializerError wirft
 */
public class StreetField extends Field {
	public boolean buyStreet = false; // für JunitTesting als globale 
    private Street street;

    public StreetField(Street street, int index) {
        super(street.getName(), index);
        this.street = street;
    }

    public boolean ask(){
        return MessageUtil.ask("Strasse Kaufen", "Wollen Sie die Strasse Kaufen?", "ja", "nein, Auktion starten");
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Street getStreet() {
        return street;
    }

    @Override
    public void landing(Player player) {
        StreetOwner owner = street.getOwner();
        buyStreet = ask();
        if (owner == null) {
            if (buyStreet) {
                player.pay(street.getPrice().doubleValue());
                player.addToInventory(street);
                street.setOwner(player);
            } else {
                //zur auktion freigeben
                street.startAuction();
            }
        } else if (owner != player) {
            double rent = street.getRent().doubleValue();
        	boolean streetWithoutHouse = false;
            if ( player.getBalance().doubleValue() < rent ) {            	   		
            	for (Street s : player.getStreets()) {
            		if (s.getNumberOfHouses() == 0) {  // nehme vorerst erste Straße ohne Haus im loop
            			streetWithoutHouse = true;
            			if (!s.isMortage()) {s.assumeMortage();} // hypothek aufnehmen 
            		}
            	}            		
            	if (streetWithoutHouse == false) {
                	if (owner instanceof Player) {
                		((Player) owner).addMoney(rent); // gehe ins negative um rent zu zahlen
                	} 										
            	}			        	
            } 
            if ( (player.getBalance().doubleValue() >= rent) && (!((Player) owner).isInJail()) ) {
            	player.pay(rent);
            	if (owner instanceof Player) {
            		((Player) owner).addMoney(rent);
            	}
            }
        }
    }
}

