package monopoly.core;

import java.util.Arrays;
import java.util.List;
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

	public static boolean  SUPPRESS_IN_TESTEXECUTION = true;
	
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
	 * �ffnet einen Modalen Info-Dialog.
	 * 
	 * @param title
	 * @param contentText
	 */
	public static void inform(String title, String contentText) {
		inform(null, title, contentText);
	}

	/**
	 * �ffnet einen Modalen Info-Dialog.
	 * 
	 * @param title
	 * @param contentText
	 */
	public static void inform(String headerText, String title, String contentText) {
		showAndWait(create(AlertType.INFORMATION, headerText, title, contentText));
	}

	public static boolean ask(String headerText, String title) {
		return ask(headerText, title, null, ButtonBar.ButtonData.OK_DONE.name(),
				ButtonBar.ButtonData.CANCEL_CLOSE.name());
	}

	public static boolean ask(String headerText, String title, String contentText) {
		return ask(headerText, title, contentText, ButtonBar.ButtonData.OK_DONE.name(),
				ButtonBar.ButtonData.CANCEL_CLOSE.name());
	}

	public static boolean ask(String headerText, String title, String okText, String cancelText) {
		return ask(headerText, title, null, okText, cancelText);
	}

	public static boolean ask(String headerText, String title, String contentText, String okText, String cancelText) {
		ButtonType okButton = okButton(okText);
		Alert alert = create(AlertType.CONFIRMATION, headerText, title, contentText, okButton, cancelButton(cancelText));
		Optional<ButtonType> result = showAndWait(alert);
		return result.isPresent() && !result.get().getButtonData().isCancelButton();
	}

	private static ButtonType cancelButton(String cancelText) {
		return new ButtonType(cancelText, ButtonBar.ButtonData.CANCEL_CLOSE);
	}

	private static ButtonType okButton(String okText) {
		return new ButtonType(okText, ButtonBar.ButtonData.OK_DONE);
	}

	private static final Alert create(AlertType alertType, String headerText, String title, String contentText, ButtonType... buttons) {
		if (isJUnitTestExecution()) {
			return null;
		}
		return new Alert(alertType, contentText, buttons);
	}

	private static final Optional<ButtonType> showAndWait(Alert alert) {
		if (alert != null) {
			return alert.showAndWait();
		}
		return Optional.empty();
	}
	
	private static final boolean isJUnitTestExecution() {
		if (!SUPPRESS_IN_TESTEXECUTION) {
			return false;
		}
		 StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    List<StackTraceElement> list = Arrays.asList(stackTrace);
		    for (StackTraceElement element : list) {
		        if (element.getClassName().startsWith("org.junit.")) {
		            return true;
		        }           
		    }
		    return false;
	}

}
