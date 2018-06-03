package monopoly.core.fields;

import monopoly.core.CircleList;
import monopoly.model.Edition;
import monopoly.model.Game;
import monopoly.model.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFreeparkingField
{
    @Test
    public void testFreeparkingField()
    {
        Game game = new Game();
        CircleList circlist = new CircleList();
        FreeparkingField freeparkingField = new FreeparkingField();
        Edition edi = new Edition();
        Player player = new Player();
        game.getPlayers().add(player);
        edi.setFields(circlist);
        game.setMoneyInTheMiddle(500);
        Boolean notMoved = true;
        double moneyBefore = player.getBalance().doubleValue();
        while (notMoved) {
            assertTrue(player.getField().getValue() == null);
            player.moveTo(freeparkingField);
            notMoved = false;
        }
        assertTrue(player.getField().getValue().equals(freeparkingField));
        double moneyAfter =  player.getBalance().doubleValue();
        assertEquals(moneyBefore+500,moneyAfter);
    }
}
