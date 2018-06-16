package monopoly.core;

import java.util.List;

import monopoly.core.fields.Field;
import monopoly.model.DiceCast;
import monopoly.model.Game;
import monopoly.model.Player;
/**
 * An engine implementing a substantial part of the game logic and rules.
 */
public class MonopolyEngine {

	private static final int MAX_THROWS_IN_JAIL = 3;
	private static final int MAX_DOUBLE_COUNT = 3;
	private final Game game;

	private final DiceCast diceCast = new DiceCast();

	public MonopolyEngine(Game game) {
		this.game = game;
	}

	/**
	 * This Method is called by a player at the start of his move, if he is in jail.
	 * @return returns the dicecast of the roll, independent from its result. Frees the player if a double was rolled.
	 */
	private DiceCast rollDiceInJail() {
		if (game.getPlayersThrowCount() > MAX_THROWS_IN_JAIL) {
			throw new RuntimeException("Spieler hat zu oft gew�rfelt!");
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			Player player = game.getCurrentPlayer();
			player.freeFromJail();
			game.movePlayer(player, diceCast.current());
		}

		return diceCast;
	}

	
	public void endTurn() {
		game.nextPlayer();
	}

	
	/**
	 * This Method is called by a player at the start of his move if he is not in jail.
	 * it also manages the count of doubles rolled by the player in his current move, as well as initializing player movement.
	 * @return returns the dicecast of the roll, independent from its result. 
	 */
	public DiceCast playerRollsTheDice() {
		Player player = game.getCurrentPlayer();
		if (player.isInJail()) {
			return rollDiceInJail();
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			player.incDoubleCount();
		}
		if (player.getDoubleCount() == MAX_DOUBLE_COUNT) {
			player.sendToJail();
		}
		game.movePlayer(player, diceCast.current());
		return diceCast;
	}


	private void rollTheDice() {
		diceCast.next();
		game.playerRolledTheDice(diceCast);
	}


	/**
	 * evaluates whether or not the player shoud be allowed to roll, based on the number of times he rolled and if he rolled doubles.
	 * @return returns true, if current player can roll, false otherwise.
	 */
	public boolean canPlayerRollTheDice() {
		List<DiceCast> diceCastHistory = game.getCurrentPlayerDiceCastHistory();
		return (diceCastHistory.isEmpty() || diceCastHistory.stream().allMatch(diceCast -> diceCast.isDouble())) && diceCastHistory.size() < 3;
	}

	public Game getGame() {
		return game;
	}

	public DiceCast getDiceCast() {
		return diceCast;
	}

}
