package monopoly.view.playground;

import java.awt.Point;

import monopoly.model.Game;

public class PlayerPositionHelper {

	private PlaygroundImageDescriptor imageDescriptor;

	private double scaledSizeOfImage;

	public PlayerPositionHelper(PlaygroundImageDescriptor imageDescriptor, double scaledSizeOfImage, Game game) {
		this.imageDescriptor = imageDescriptor;
		this.scaledSizeOfImage = scaledSizeOfImage;
	}

	/**
	 * Bestimmt die Position des Spielers, in Abhängigkeit davon, wie das
	 * Spieleld ausgerichtet ist.
	 * 
	 * @param rowIndex
	 * @param playgroundOrientation
	 * @return
	 */
	public Point calc(int rowIndex, double playgroundOrientation) {
		double scale = scaledSizeOfImage / imageDescriptor.getSize();
		RowAlignment normalizedAlignment = normalizeFieldIndex(rowIndex, playgroundOrientation);
		int x = (int) (imageDescriptor.getHorizontalStartOfField(rowIndex, normalizedAlignment) * scale);
		int y = (int) (imageDescriptor.getVerticalStartOfField(rowIndex, normalizedAlignment) * scale);
		Point point = new Point(x, y);
		return point;
	}

	private RowAlignment normalizeFieldIndex(int rowIndex, double orientation) {
		RowAlignment alignment = RowAlignment.get(rowIndex);
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
