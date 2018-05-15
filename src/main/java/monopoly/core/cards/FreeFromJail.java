package monopoly.core.cards;

public class FreeFromJail extends OwnableCard {

	public FreeFromJail() {
		super("Gefänginsfreikarte", "Sie kommen aus dem Gefängnis frei!");
	}

	@Override
	protected boolean execute() {
		player.freeFromJail();
		return false;
	}

}
