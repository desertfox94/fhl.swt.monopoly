package fhl.swt.monopoly.core;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class MessageUtil {

	public static void show(String headerText, String title) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.show();
	}

	public static boolean ask(String headerText, String title) {
		return ask(headerText, title, ButtonBar.ButtonData.OK_DONE.name(), ButtonBar.ButtonData.CANCEL_CLOSE.name());
	}

	public static boolean ask(String headerText, String title, String okText, String cancelText) {
		ButtonType okButton = okButton(okText);
		Alert alert = new Alert(AlertType.INFORMATION, "", okButton, cancelButton(cancelText));
		alert.setHeaderText(headerText);
		alert.setTitle(title);
		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && !result.get().getButtonData().isCancelButton();
	}

	private static ButtonType cancelButton(String cancelText) {
		return new ButtonType(cancelText, ButtonBar.ButtonData.CANCEL_CLOSE);
	}

	private static ButtonType okButton(String okText) {
		return new ButtonType(okText, ButtonBar.ButtonData.OK_DONE);
	}

}
