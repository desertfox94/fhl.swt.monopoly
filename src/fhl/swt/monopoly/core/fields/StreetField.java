package fhl.swt.monopoly.core.fields;

import java.util.Optional;

import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;
import fhl.swt.monopoly.model.StreetOwner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

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

			ButtonType buy = new ButtonType("Ja!", ButtonBar.ButtonData.OK_DONE);
			ButtonType nothing = new ButtonType("Nicht Kaufen", ButtonBar.ButtonData.CANCEL_CLOSE);
			Alert alert = new Alert(AlertType.INFORMATION, "", buy, nothing);
			alert.setHeaderText("Wollen Sie " + street.getName() + " kaufen?");
			alert.setTitle("Straﬂe kaufen");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == buy) {
				player.pay(street.getPrice().doubleValue());
				player.addToInventory(street);
				street.setOwner(player);
			}
		} else if (owner != player) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Miete zahlen");
			double rent = street.getRent().doubleValue();
			alert.setHeaderText("Sie sind auf " + street.getName() + " (" + owner.getName() + ") gelandet und zahlen " + rent + " Miete.");
			alert.show();
			player.pay(rent);
			if (owner instanceof Player) {
				((Player) owner).addMoney(rent);
			}
		}
		// wanna buy a street?
	}

}
