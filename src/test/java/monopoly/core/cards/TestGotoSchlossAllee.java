package monopoly.core.cards;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import monopoly.core.CircleList;
import monopoly.core.fields.Field;
import monopoly.core.fields.StreetField;
import monopoly.model.Edition;
import monopoly.model.Game;
import monopoly.model.Player;
import monopoly.model.Street;

/**
 * @author Jake Stradling
 */

public class TestGotoSchlossAllee {

	@Test
	public void testExecute() {

		Player player = new Player();
		CircleList<Field> fields = new CircleList<>();
		fields.putLast(new StreetField(new Street(), 1));
		StreetField schlossAllee = new StreetField(new Street(), 2);
		fields.putLast(schlossAllee);
		Game game = new Game();
		game.setEdition(new Edition());
		game.getEdition().setFields(fields);
		player.setGame(game);
		GotoSchlossallee card = new GotoSchlossallee();
		card.execute(player);

		assertTrue(player.getFieldProperty().get().equals(schlossAllee));
	}

}
