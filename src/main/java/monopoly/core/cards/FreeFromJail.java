package monopoly.core.cards;

public class FreeFromJail extends OwnableCard {

	public FreeFromJail() {
		super("Gef�nginsfreikarte", "Sie kommen aus dem Gef�ngnis frei!");
	}

	@Override
	protected boolean execute() {
		player.freeFromJail();
		player.removeCardFromInventory(this);
		return false;
	}

}
