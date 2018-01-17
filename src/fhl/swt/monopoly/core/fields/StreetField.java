package fhl.swt.monopoly.core.fields;

import fhl.swt.monopoly.core.MessageUtil;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;
import fhl.swt.monopoly.model.StreetOwner;

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
			boolean buyStreet = MessageUtil.ask("Wollen Sie " + street.getName() + " kaufen?", "Straﬂe kaufen", "Ja!", "Nicht Kaufen");
			if (buyStreet) {
				player.pay(street.getPrice().doubleValue());
				player.addToInventory(street);
				street.setOwner(player);
			}
		} else if (owner != player) {
			double rent = street.getRent().doubleValue();
			String text = "Sie sind auf " + street.getName() + " (" + owner.getName() + ") gelandet und zahlen " + rent + " Miete.";
			String title = "Miete zahlen";
			MessageUtil.show(text, title);
			player.pay(rent);
			if (owner instanceof Player) {
				((Player) owner).addMoney(rent);
			}
		}
	}

}
