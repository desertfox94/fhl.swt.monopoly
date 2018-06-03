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
      
    @Override
    public void landing(Player player) {
        StreetOwner owner = street.getOwner();
        if (owner == null) {
           // boolean buyStreet = MessageUtil.ask("Wollen Sie " + street.getName() + " kaufen?", "Stra�e kaufen", "Ja!", "Nein, Auktion starten");          
            if (buyStreet) {
                player.pay(street.getPrice().doubleValue());
                player.addToInventory(street);
                street.setOwner(player);
            } else {
                //zur auktion freigeben
                street.zurAuktionFreigeben();
            }
        } else if (owner != player) {
            double rent = street.getRent().doubleValue();
        	boolean hasZeroHouses = true;
            if ( (player.getBalance().doubleValue() < rent) && (!player.isInJail()) ) {            	
            	if( !street.isMortage() ) {        		
            		for (Street s : player.getStreets()) {
            			if (s.getNumberOfHouses() != 0) { 
            				hasZeroHouses = false;
            			}
            		}            		
            		if (hasZeroHouses == true) {
            			street.isMortage(); // nachfragen welche bedigungen eintreten bei hypothek   			
            		}	
            		else { }       	//verkaufe häuser, zahle rent. rent immer noch weniger? Hypothek aufnehmen / nachfragen 
            	} 
            	 	
            double moneyLeft = player.getBalance().doubleValue();
            player.setBalance(player.getBalance().doubleValue() - moneyLeft);
            	if (owner instanceof Player) {
            		((Player) owner).addMoney(moneyLeft); // gebe gläubigen den rest des Geldes
                } 
            		//player.getGame().endGame(); // ende game für diesen Spieler        	
            } 
          //  String text = "Sie sind auf " + street.getName() + " (" + owner.getName() + ") gelandet und zahlen " + rent + " Miete.";
          //  String title = "Miete zahlen";
          //   MessageUtil.inform(text, title);
            if ( (player.getBalance().doubleValue() >= rent) && (!player.isInJail()) ) {
            	player.pay(rent);
            	if (owner instanceof Player) {
            		((Player) owner).addMoney(rent);
            	}
            }
        }
    }
}

