package fhl.swt.monopoly.core;

import java.util.List;

import fhl.swt.monopoly.core.fields.Field;
import fhl.swt.monopoly.model.DiceCast;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;

public class MonopolyEngine {

	private static final int MAX_THROWS_IN_JAIL = 3;
	private static final int MAX_DOUBLE_COUNT = 3;
	private final Game game;

	private final DiceCast diceCast = new DiceCast();

	public MonopolyEngine(Game game) {
		this.game = game;
	}

	private DiceCast rollDiceInJail() {
		if (game.getPlayersThrowCount() > MAX_THROWS_IN_JAIL) {
			throw new RuntimeException("Spieler hat zu oft gewürfelt!");
		}
		rollTheDice();
		if (diceCast.isDouble()) {
			game.getCurrentPlayer().freeFromJail();
		}
		// TODO: Darf ein Spieler um die ANzahl der Paschaugen vorrücken oder
		// würfelt er
		// erneut?
		return diceCast;
	}

	public void endTurn() {
		game.nextPlayer();
	}

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
		movePlayer(player, diceCast.current());
		return diceCast;
	}

	private void movePlayer(Player player, int diceCast) {
		CircleList<Field> fields = game.getEdition().getFields();
		fields.select(player.getField().get());
		Field field = null;
		for (int i = 0; i < diceCast; i++) {
			field = fields.next();
			player.moveTo(field);
			field.passing(player);
		}
		field.landing(player);
		player.moveTo(field);
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

	public DiceCast getDiceCast() {
		return diceCast;
	}

}
