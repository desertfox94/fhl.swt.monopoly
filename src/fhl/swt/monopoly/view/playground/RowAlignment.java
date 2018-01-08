package fhl.swt.monopoly.view.playground;

public enum RowAlignment {

	BOTTOM, LEFT, TOP, RIGHT;

	public static RowAlignment get(int fieldIndex) {
		if (fieldIndex < 10) {
			return BOTTOM;
		} else if (fieldIndex >= 10 && fieldIndex < 20) {
			return LEFT;
		} else if (fieldIndex >= 20 && fieldIndex < 30) {
			return TOP;
		} else {
			return RIGHT;
		}
	}

	public static RowAlignment rotateRight(RowAlignment alignment) {
		switch (alignment) {
		case BOTTOM:
			return RIGHT;
		case LEFT:
			return RowAlignment.BOTTOM;
		case RIGHT:
			return RowAlignment.TOP;
		default:
		case TOP:
			return RowAlignment.LEFT;
		}
	}

}
