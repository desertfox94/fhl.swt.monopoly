package monopoly.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author johannes Gille
 *
 */
public class TestGame {

	@Test
	public void testAddPlayer() {
		Game game = new Game();
		assertTrue(game.getPlayers().toList().isEmpty());
		Player p = new Player();
		game.getPlayers().add(p);
		assertTrue(game.getPlayers().toList().contains(p));
	}

	@Test
	public void testCurrentPlayer() {
		Game game = new Game();
		Player p = new Player();
		game.getPlayers().add(p);
		assertTrue(game.getCurrentPlayer().equals(p));
	}

	@Test
	public void testNextPlayer() {
		Game game = new Game();
		Player p0 = new Player();
		Player p1 = new Player();
		game.getPlayers().add(p0);
		game.getPlayers().add(p1);
		assertTrue(game.getCurrentPlayer().equals(p0));
		game.nextPlayer();
		assertTrue(game.getCurrentPlayer().equals(p1));
	}

	@Test
	public void testDiceCastHistory() {
		Game game = new Game();
		Player p0 = new Player();
		Player p1 = new Player();
		game.getPlayers().add(p0);
		game.getPlayers().add(p1);
	}


}
