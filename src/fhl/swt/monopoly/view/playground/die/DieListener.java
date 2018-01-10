package fhl.swt.monopoly.view.playground.die;

import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class DieListener implements ChangeListener<Number> {

	private static final HashMap<Integer, Image> dieImages = new HashMap<>();
	{
		try {
			dieImages.put(1, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("1.png")), new WritableImage(65, 65)));
			dieImages.put(2, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("2.png")), new WritableImage(65, 65)));
			dieImages.put(3, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("3.png")), new WritableImage(65, 65)));
			dieImages.put(4, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("4.png")), new WritableImage(65, 65)));
			dieImages.put(5, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("5.png")), new WritableImage(65, 65)));
			dieImages.put(6, SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream("6.png")), new WritableImage(65, 65)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ImageView dieView;

	public DieListener(ImageView dieView) {
		this.dieView = dieView;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		dieView.setImage(dieImages.get(newValue));
	}

}
