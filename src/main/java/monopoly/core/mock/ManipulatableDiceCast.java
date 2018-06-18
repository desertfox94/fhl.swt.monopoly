package monopoly.core.mock;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import monopoly.model.DiceCast;
import monopoly.model.Die;

public class ManipulatableDiceCast extends DiceCast {

	boolean useManipulatedThrow;

	public ManipulatableDiceCast(TextField dieOne, TextField dieTwo) {
		firstDie = new ManipulatableDie(dieOne);
		secondDie = new ManipulatableDie(dieTwo);
	}

	@Override
	public int next() {
		if (useManipulatedThrow) {
			useManipulatedThrow = false;
			return firstDie.get() + secondDie.get();
		}
		return super.next();
	}

	public void useManipulatedThrow() {
		useManipulatedThrow = true;
	}

	private class ManipulatableDie extends Die {

		public ManipulatableDie(TextField die) {
			die.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> arg0, String oldVal, String newVal) {
					useManipulatedThrow();
					int val = 0;
					try {
						val = Integer.parseInt(newVal);
					} catch (Exception e) {
					}
					number.setValue(val);
				}
			});
		}

	}

}
