package fhl.swt.monopoly.view.playground;

import java.awt.Point;
import java.util.Random;

import fhl.swt.monopoly.model.Game;

public class PlayerPositionHelper {

	private static final Random RANDOM = new Random();

	private PlaygroundImageDescriptor imageDescriptor;

	private double scaledSizeOfImage;

	public PlayerPositionHelper(PlaygroundImageDescriptor imageDescriptor, double scaledSizeOfImage, Game game) {
		this.imageDescriptor = imageDescriptor;
		this.scaledSizeOfImage = scaledSizeOfImage;
	}

	public Point calc(int index, double playgroundOrientation) {
		double scale = scaledSizeOfImage / imageDescriptor.getSize();
		// int index = player.getField().getIndex();
		System.out.println("index: " + index);
		System.out.println("scale: " + scale);
		RowAlignment alignment = RowAlignment.get(index);
		RowAlignment normalizedAlignment = normalizeFieldIndex(alignment, playgroundOrientation);
		int x = (int) (imageDescriptor.getHorizontalStartOfField(index, normalizedAlignment) * scale);
		int y = (int) (imageDescriptor.getVerticalStartOfField(index, normalizedAlignment) * scale);
		Point point = new Point(x, y);
		System.out.println(point);
		return point;
	}

	private RowAlignment normalizeFieldIndex(RowAlignment alignment, double orientation) {
		int rotationsNeeded;
		if (orientation < 90.0) {
			rotationsNeeded = 0;
		} else if (orientation > 90.0 && orientation < 180.0) {
			rotationsNeeded = 1;
		} else if (orientation > 180.0 && orientation < 270.0) {
			rotationsNeeded = 2;
		} else {
			rotationsNeeded = 3;
		}
		while (rotationsNeeded-- > 0) {
			alignment = RowAlignment.rotateRight(alignment);
		}
		return alignment;
	}

}
