package fhl.swt.monopoly.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class PlayerFigure {

	public ImageView imageView;

	public Player player;

	public BooleanProperty taken = new SimpleBooleanProperty(false);

	public PlayerFigure(ImageView imageView, Player player) {
		this.imageView = imageView;
		this.player = player;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isTaken() {
		return taken.get();
	}

	public BooleanProperty getTaken() {
		return taken;
	}

}
