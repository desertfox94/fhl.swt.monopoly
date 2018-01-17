package fhl.swt.monopoly.core;

import java.util.Optional;
import java.util.function.Consumer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Dieses Util dient dazu das Anzeigen von Dialogen zu vereinfachen.
 */
public class MessageUtil {

	/**
	 * Stellt dem Nutzer eine Frage. Die Frage wird als modaler Dialog
	 * angezeigt.
	 * 
	 * @param headerText
	 * @param title
	 * @param onOk
	 *            wird im Erfolgsfall aufgerufen.
	 */
	public static void ask(String headerText, String title, Consumer<Boolean> onOk) {
		if (ask(headerText, title)) {
			onOk.accept(true);
		}
	}

	/**
	 * Öffnet einen Modalen Info-Dialog.
	 * 
	 * @param title
	 * @param contentText
	 */
	public static void inform(String title, String contentText) {
		inform(null, title, contentText);
	}

	/**
	 * Öffnet einen Modalen Info-Dialog.
	 * 
	 * @param title
	 * @param contentText
	 */
	public static void inform(String headerText, String title, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public static boolean ask(String headerText, String title) {
		return ask(headerText, title, null, ButtonBar.ButtonData.OK_DONE.name(), ButtonBar.ButtonData.CANCEL_CLOSE.name());
	}

	public static boolean ask(String headerText, String title, String contentText) {
		return ask(headerText, title, contentText, ButtonBar.ButtonData.OK_DONE.name(), ButtonBar.ButtonData.CANCEL_CLOSE.name());
	}

	public static boolean ask(String headerText, String title, String okText, String cancelText) {
		return ask(headerText, title, null, okText, cancelText);
	}

	public static boolean ask(String headerText, String title, String contentText, String okText, String cancelText) {
		ButtonType okButton = okButton(okText);
		Alert alert = new Alert(AlertType.CONFIRMATION, "", okButton, cancelButton(cancelText));
		alert.setHeaderText(headerText);
		alert.setTitle(title);
		alert.setContentText(contentText);
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
