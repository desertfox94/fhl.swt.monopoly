package fhl.swt.monopoly.core;

import java.util.List;

import fhl.swt.monopoly.model.DiceCast;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;

public class MonopolyEngine {

	private static final int MAX_THROWS_IN_JAIL = 3;
	private static final int MAX_DOUBLE_COUNT = 3;
	private final Game game;

	private Playground playground;
	
	private final DiceCast diceCast = new DiceCast();
	
	public MonopolyEngine(Game game) {
		this.game = game;
		playground = new Playground(game.getEdition());
	}
	
	private void rollDiceInJail() {
		if (game.getPlayersThrowCount() > MAX_THROWS_IN_JAIL) {
			return;
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			game.getCurrentPlayer().freeFromJail();
		}
		// TODO: Darf ein Spieler um die ANzahl der Paschaugen vorr�cken oder w�rfelt er erneut?
	}

	public void endTurn() {
		game.nextPlayer();
	}
	
	public void playerRollsTheDice() {
		Player player = game.getCurrentPlayer();
		if (player.isInJail()) {
			rollDiceInJail();
			return;
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			player.incDoubleCount();
		}
		if (player.getDoubleCount() == MAX_DOUBLE_COUNT) {
			player.sendToJail();
		}
		playground.movePlayer(player, diceCast.current());
	}

	private void rollTheDice() {
		diceCast.next();
		game.playerRolledTheDice(diceCast);
	}
	
	public boolean canPlayerRollTheDice() {
		List<DiceCast> diceCastHistory = game.getCurrentPlayerDiceCastHistory();
		return diceCastHistory.isEmpty() || diceCastHistory.stream().allMatch(diceCast -> diceCast.isDouble());
	}
	
	public Game getGame() {
		return game;
	}
	
}
