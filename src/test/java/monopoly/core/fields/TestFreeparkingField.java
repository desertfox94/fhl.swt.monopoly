package monopoly.core.fields;

import static junit.framework.TestCase.assertEquals;
import monopoly.model.Game;
import monopoly.model.Player;

import org.junit.Test;

public class TestFreeparkingField
{

	@Test
	public void testFreeparkingField()
	{
		Game game = new Game();
		int moneyInTheMiddle = 500;
		FreeparkingField freeparkingField = new FreeparkingField();
		Player player = new Player();
		int moneyBefore = player.getBalance().intValue();
		player.setGame(game);

		game.putMoneyInTheMiddle(moneyInTheMiddle);
		assertEquals(moneyInTheMiddle, game.getMoneyInTheMiddle().intValue());

		freeparkingField.landing(player);

		int moneyAfter = player.getBalance().intValue();
		assertEquals(moneyBefore + moneyInTheMiddle, moneyAfter);
		assertEquals(0, game.getMoneyInTheMiddle().intValue());
	}
}
