package monopoly.model;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import monopoly.core.cards.Card;
import monopoly.core.cards.CardFactory;

/**
 * @author Florian Nickel
 */
public class TestCardOwner {
    @Test
    public void testAddCard(){
    	Player player = new Player();
    	player.addCardToInventory(CardFactory.createEventCards().draw());
    	assertFalse(player.getCards().isEmpty());
    }

    @Test
    public void testRemoveCard(){
    	Player player = new Player();
        Card card = CardFactory.createEventCards().draw();
        player.addCardToInventory(card);
        player.removeCardFromInventory(card);
        assertTrue(player.getCards().isEmpty());
    }
}
