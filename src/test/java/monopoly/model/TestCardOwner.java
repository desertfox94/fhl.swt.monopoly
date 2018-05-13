package monopoly.model;


import org.junit.Test;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static org.junit.Assert.assertTrue;

/**
 * @author Florian Nickel
 */
public class TestCardOwner {
    @Test
    public void testAddCard(){
        Payer player = new Payer();
        Player.addCardToInventory(new CardFactory().getInstance().createEventCards()draw());
        assertTrue(player.getCards().Count == 1);
    }

    @Test
    public void testRemoveCard(){
        Payer player = new Payer();
        Card card = new CardFactory().getInstance().createEventCards()draw();
        Player.addCardToInventory(card);
        player.removeCardFromInventory(card);
        assertTrue(player.getCards().Count == 0);
    }
}
