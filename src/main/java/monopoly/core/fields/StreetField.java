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
 * A regular Street field. several of these will form a majority of the playing field.
 * 
 * MessageUtils auskommentiert, da  streetField.landing(player) im junittest einen ExceptioninitializerError wirft
 */
public class StreetField extends Field {
	public boolean buyStreet = false; // für JunitTesting als globale 
	public boolean mortage = false;
    private Street street;
    
    public StreetField(Street street, int index) {
        super(street.getName(), index);
        this.street = street;
    }

    public boolean ask(){
        return MessageUtil.ask("Strasse Kaufen", "Wollen Sie die Strasse Kaufen?", "ja", "nein, Auktion starten");
    }
    
    public boolean askMortage(){
        return MessageUtil.ask("Hypothek aufnehmen", "Wollen Sie eine Hypothek aufnehmen?", "ja", "nein, Haus verkaufen");
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
                player.pay(street.getPrice().intValue());
                player.addToInventory(street);
                street.setOwner(player);
            } else {
                //zur auktion freigeben
                street.startAuction();
            }
        } else if (owner != player) {
            int rent = street.getRent().intValue();
   
            if ( player.getBalance().intValue() < rent
            		&& ( player.checkForHouses() == true ) || ( player.checkForMortage() == true ) ) {       	 	
            	
            	mortage = askMortage();
            	
            	if (mortage = true)										
            	{ player.nonMortagedHouses().get(0).assumeMortage(); }	// Abfrage welches Haus zur Hypothek TODO
            										
            	else if (mortage = false)
            	{ ((Player) player.sellableHouses()).sellHouse(0); }   // Abfrage welches Haus zum verkauf TODO
            }
                   
            else if ( (player.getBalance().intValue() >= rent) && (!((Player) owner).isInJail()) ) {
            	player.pay(rent);
            	if (owner instanceof Player) {
            		((Player) owner).addMoney(rent);
            	}
            }
        }
    }
}

