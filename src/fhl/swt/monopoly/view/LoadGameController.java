package fhl.swt.monopoly.view;

import java.util.List;
import java.util.Optional;

import fhl.swt.monopoly.core.DBService;
import javafx.scene.control.ChoiceDialog;

public class LoadGameController extends GameInitController {

	private DBService dbService = DBService.getDefault();

	public boolean loadGame() {
		List<String> choices = dbService.loadSavedGameTitles();
		choices.add("Spielstand 1");
		choices.add("Spielstand 2");
		choices.add("King of the Kill");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Spielstand 1", choices);
		dialog.setTitle("Spielstand laden");
		dialog.setHeaderText(null);
		dialog.setContentText("Wähle einen Spielstand aus:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Your choice: " + result.get());
		}

		// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(letter -> System.out.println("Your choice: " + letter));
		game = dbService.loadGame(result.get());
		return true;
	}

}
