package fhl.swt.monopoly.view.playground;

import java.awt.Point;
import java.awt.image.BufferedImage;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class PlaygroundController {

	private MonopolyEngine engine;

	private Game game;

	@FXML
	private Button rollTheDiceButton;

	@FXML
	private Pane playgroundPane;

	@FXML
	private Button endTurnButton;

	@FXML
	private Region playground;

	@FXML
	private Accordion playerHub;

	private Map<TitledPane, PlayerInventoryController> controllers;

	private Map<Player, ImageView> playerFigures = new HashMap<>();

	private PlaygroundImageDescriptor playgroundImageDescr;

	public PlaygroundController() {
	}

	@FXML
	private void rotate() {
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
		adjustPlayerPosition(game.getCurrentPlayer());
	}

	private double getSizeOfBackgroundImage() {
		double width = playground.getWidth();
		double height = playground.getHeight();
		if (height == 0 && width == 0) {
			return 600;
		}
		return height < width ? height : width;
	}

	@FXML
	private void endTurn() {
		game.nextPlayer();
		playerHub.getPanes().forEach(x -> controllers.get(x).refreshTitle());
		rollTheDiceButton.setDisable(false);
		adjustPlayerPosition(game.getCurrentPlayer());
	}

	public void preparePlayground(Game game) {
		this.game = game;
		engine = new MonopolyEngine(game);
		playgroundImageDescr = PlaygroundImageDescriptor.loadGOTPlaygroundDescriptor();
		BufferedImage background = game.getEdition().getBackground();
		WritableImage fxImage = SwingFXUtils.toFXImage(background, new WritableImage(600, 600));
		BackgroundSize size = new BackgroundSize(playground.getWidth(), playground.getHeight(), false, false, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(fxImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		playground.setBackground(new Background(backgroundImage));
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
		Point pos = new PlayerPositionHelper(playgroundImageDescr, getSizeOfBackgroundImage(), game).calc(player.getPosition().intValue(), playground.getRotate());
		ImageView img = playerFigures.get(player);
		player.getPosition().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number old, Number playerPosition) {
				Point pos = new PlayerPositionHelper(playgroundImageDescr, getSizeOfBackgroundImage(), game).calc(playerPosition.intValue(), playground.getRotate());
				ImageView img = playerFigures.get(player);
				img.relocate(pos.getX(), pos.getY());
			}
		});
	}

	private void invitePlayers() {
		controllers = new HashMap<>();
		List<Player> allPlayers = game.getPlayers().toList();
		for (Player player : allPlayers) {
			addPlayerInventoryToPlayerHub(player);
			playerFigures.put(player, createPlayerFigure(player));
		}
	}

	private ImageView createPlayerFigure(Player player) {
		BufferedImage image = player.getFigure().getImage();
		int size = (int) (playgroundImageDescr.getRegularFieldWidth() * playgroundImageDescr.getScale(getSizeOfBackgroundImage()));
		WritableImage figure = SwingFXUtils.toFXImage(image, new WritableImage(size, size));
		ImageView imageView = new ImageView(figure);
		imageView.setFitWidth(size);
		imageView.setFitHeight(size);
		playgroundPane.getChildren().add(imageView);
		return imageView;
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
