package monopoly.view.playground;

public class PlaygroundImageDescriptor {

	private double size;

	private double cornerFieldSize;

	private double regularFieldHeight;

	private double regularFieldWidth;

	public double getCornerFieldSize() {
		return cornerFieldSize;
	}

	public void setCornerFieldSize(double cornerFieldSize) {
		this.cornerFieldSize = cornerFieldSize;
	}

	public double getRegularFieldHeight() {
		return regularFieldHeight;
	}

	public void setRegularFieldHeight(double regularFieldHeight) {
		this.regularFieldHeight = regularFieldHeight;
	}

	public double getRegularFieldWidth() {
		return regularFieldWidth;
	}

	public void setRegularFieldWidth(double regularFieldWidth) {
		this.regularFieldWidth = regularFieldWidth;
	}

	public double getScale(double actualSize) {
		return actualSize / size;
	}

	public double getHorizontalStartOfField(int index, RowAlignment alignment) {
		switch (alignment) {
		case BOTTOM:
			index = 10 - index;
			return cornerFieldSize + --index * regularFieldWidth;
		case TOP:
			index = index % 10;
			if (index == 0) {
				return 0;
			}
			return cornerFieldSize + --index * regularFieldWidth;
		case LEFT:
			return 0.0;
		case RIGHT:
			return size - regularFieldHeight;
		default:
			break;
		}
		return 0.0;
	}

	public double getVerticalStartOfField(int index, RowAlignment alignment) {
		index = index % 10;
		switch (alignment) {
		case BOTTOM:
			return size - regularFieldHeight;
		case TOP:
			return 0.0;
		case LEFT:
			index = 10 - index;
		case RIGHT:
		default:
			if (index == 0) {
				return 0;
			}
			return cornerFieldSize + --index * regularFieldWidth;
		}
	}

	public static PlaygroundImageDescriptor loadGOTPlaygroundDescriptor() {
		PlaygroundImageDescriptor imageDescriptor = new PlaygroundImageDescriptor();
		imageDescriptor.setSize(1600.0);
		imageDescriptor.setCornerFieldSize(215.0);
		imageDescriptor.setRegularFieldWidth(130.0);
		imageDescriptor.setRegularFieldHeight(215.0);
		return imageDescriptor;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}
