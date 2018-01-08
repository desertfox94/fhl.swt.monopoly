package fhl.swt.monopoly.view.playground;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fhl.swt.monopoly.core.MonopolyEngine;
import fhl.swt.monopoly.model.DiceCast;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.view.AppViewController;
import fhl.swt.monopoly.view.playerInventory.PlayerInventoryController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;

public class PlaygroundController {

	private MonopolyEngine engine;

	private Game game;

	@FXML
	private Button rollTheDiceButton;

	@FXML
	private Button endTurnButton;

	@FXML
	ImageView img;

	@FXML
	private Region playground;

	@FXML
	private Accordion playerHub;

	private Map<TitledPane, PlayerInventoryController> controllers;

	private SimpleDoubleProperty simpleDoubleProperty;

	private PlaygroundImageDescriptor playgroundImageDescr;

	private int index;

	public PlaygroundController() {
	}

	@FXML
	private void rotate() {
		Background background = playground.getBackground();
		double rotate = playground.getRotate();
		playground.setRotate(rotate + 90);
		System.out.println();
	}

	@FXML
	private void rollTheDice() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Gewürfelt");
		alert.setHeaderText(null);
		DiceCast diceCast = engine.playerRollsTheDice();
		String message;
		if (diceCast.isDouble()) {
			message = "Sie haben einen Pasch gewürfelt und rücken " + diceCast.current() + " Felder vor:";
		} else {
			message = "Sie rücken um " + diceCast.current() + " Felder vor.";
		}
		alert.setContentText(message);

		alert.showAndWait();
		rollTheDiceButton.setDisable(!engine.canPlayerRollTheDice());
		move(game.getCurrentPlayer());
	}

	private double getSizeOfBackgroundImage() {
		double width = playground.getWidth();
		double height = playground.getHeight();
		return height < width ? height : width;
	}

	private void move(Player player) {
		index = index + 1;
		index = index % 40;
		adjustPlayerPosition(player);
	}

	@FXML
	private void endTurn() {
		game.nextPlayer();
		playerHub.getPanes().forEach(x -> controllers.get(x).refreshTitle());
		rollTheDiceButton.setDisable(false);
		move(game.getCurrentPlayer());
	}

	public void preparePlayground(Game game) {
		this.game = game;
		engine = new MonopolyEngine(game);
		playgroundImageDescr = PlaygroundImageDescriptor.loadGOTPlaygroundDescriptor();
		invitePlayers();
		playground.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (Player player : game.getPlayers().toList()) {
					adjustPlayerPosition(player);
				}
			}

		});
	}

	private void adjustPlayerPosition(Player player) {
		// index = player.getIndex();
		Point pos = new PlayerPositionHelper(playgroundImageDescr, getSizeOfBackgroundImage(), game).calc(index, playground.getRotate());
		img.setLayoutX(pos.getX());
		img.setLayoutY(pos.getY());
		img.setFitWidth(playgroundImageDescr.getRegularFieldWidth() * playgroundImageDescr.getScale(getSizeOfBackgroundImage()));
	}

	private void invitePlayers() {
		controllers = new HashMap<>();
		List<Player> allPlayers = game.getPlayers().toList();
		for (Player player : allPlayers) {
			addPlayerInventoryToPlayerHub(player);
		}
	}

	private void addPlayerInventoryToPlayerHub(Player player) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AppViewController.class.getResource("playerInventory/PlayerInventoryViewPart.fxml"));
		try {
			TitledPane inventory = loader.load();
			PlayerInventoryController controller = loader.getController();
			controller.setPane(inventory);
			controller.setPlayer(game, player);
			playerHub.getPanes().add(inventory);
			controllers.put(inventory, controller);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
