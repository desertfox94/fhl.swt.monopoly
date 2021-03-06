package monopoly.core;

import java.util.List;

import static monopoly.core.Logger.*;
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

	private DiceCast diceCast = new DiceCast();


	
	public void setDiceCast(DiceCast diceCast) {
		this.diceCast = diceCast;
	}

	public MonopolyEngine(Game game) {
		this.game = game;
	}

	/**
	 * This Method is called by a player at the start of his move, if he is in jail.
	 *
	 * @return returns the dicecast of the roll, independent from its result. Frees the player if a double was rolled.
	 */
	private DiceCast rollDiceInJail() {
		ActionLogger.log(game.getCurrentPlayer(), GAME, "ROLL DICE IN JAIL", "");
		if (game.getPlayersThrowCount() > MAX_THROWS_IN_JAIL) {
			throw new RuntimeException("Spieler hat zu oft gewürfelt!");
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			ActionLogger.log(game.getCurrentPlayer(), GAME, "DOUBLE IN JAIL", "");
			Player player = game.getCurrentPlayer();
			player.freeFromJail();
			game.setJustGotOutOfJail(true);
			MessageUtil.inform("Du kommst aus dem Gefängnis frei!", "Du kommst Aus dem Gefängnis frei, weil du einen Pasch gewürfelt hast.");
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
	 *
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
			ActionLogger.log(player, GAME, "DOUBLE LIMIT REACHED", "");
			MessageUtil.inform("Ins Gefängnis", "Sie haben den dritten Pasch in Folge gewürfelt, gehe in Das Gefängnis.", "Begib dich direkt dorthin, gehe nicht über Los und ziehe keine 200DM ein.");
			player.sendToJail();
			game.moveToJail(player);
		} else {
			game.movePlayer(player, diceCast.current());
		}
		return diceCast;
	}

	private void rollTheDice() {
		diceCast.next();
		ActionLogger.log(game.getCurrentPlayer(), GAME, "ROLL THE DICE", "" + diceCast.current());	
		game.playerRolledTheDice(diceCast);
	}

	/**
	 * evaluates whether or not the player shoud be allowed to roll, based on the number of times he rolled and if he rolled doubles.
	 *
	 * @return returns true, if current player can roll, false otherwise.
	 */
	public boolean canPlayerRollTheDice() {
		List<DiceCast> diceCastHistory = game.getCurrentPlayerDiceCastHistory();
		return (diceCastHistory.isEmpty() || diceCastHistory.stream().allMatch(diceCast -> diceCast.isDouble())) && diceCastHistory.size() < 3 && !game.isJustGotOutOfJail();
	}

	public Game getGame() {
		return game;
	}

	public DiceCast getDiceCast() {
		return diceCast;
	}

}
