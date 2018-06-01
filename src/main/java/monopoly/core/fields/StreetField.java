package monopoly.core.fields;

import monopoly.core.MessageUtil;
import monopoly.model.Player;
import monopoly.model.Street;
import monopoly.model.StreetOwner;

/**
 * A regular Street field. several of these will form a majority of the playing field.
 */
public class StreetField extends Field {

    private Street street;

    public StreetField(Street street, int index) {
        super(street.getName(), index);
        this.street = street;
    }

    @Override
    public void landing(Player player) {
        StreetOwner owner = street.getOwner();
        if (owner == null) {
            boolean buyStreet = MessageUtil.ask("Wollen Sie " + street.getName() + " kaufen?", "Stra�e kaufen", "Ja!", "Nein, Auktion starten");
            if (buyStreet) {
                player.pay(street.getPrice().doubleValue());
                player.addToInventory(street);
                street.setOwner(player);
            } else {
                //zur auktion freigeben
                //Todo: Auktion Starten
            }
        } else if (owner != player) {
            double rent = street.getRent().doubleValue();
            //Todo: hat der Speler genug geld Siehe: Miete bezahlen M19
            String text = "Sie sind auf " + street.getName() + " (" + owner.getName() + ") gelandet und zahlen " + rent + " Miete.";
            String title = "Miete zahlen";
            MessageUtil.inform(text, title);
            player.pay(rent);
            if (owner instanceof Player) {
                ((Player) owner).addMoney(rent);
            }
        }
    }
}

