package fhl.swt.monopoly.core.mock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import fhl.swt.csv.CSVImport;
import fhl.swt.monopoly.core.DBService;
import fhl.swt.monopoly.core.cards.CardFactory;
import fhl.swt.monopoly.core.cards.CardSet;
import fhl.swt.monopoly.core.fields.FieldsFactory;
import fhl.swt.monopoly.io.StreetImporter;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Figure;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Street;

public class MockService extends DBService {

	public Game loadGame(String name) {
		return GameMock.mockGame();
	}

	public boolean saveGame(Game game) {
		return true;
	}

	public boolean connect(String connectionURL, String user, String pw) {
		return true;
	}

	public List<String> loadSavedGameTitles() {
		return new ArrayList<>();
	}

	@Override
	public Game loadLatestGame() {
		return GameMock.mockGame();
	}

	@Override
	public List<String> loadAvailableEditions() {
		return Arrays.asList("Standard", "GoT");
	}

	@Override
	public Edition loadEdition(String name) {
		Edition edition = new Edition();
		CardSet communityCards = CardFactory.createCommunityCards();
		CardSet eventCards = CardFactory.createEventCards();
		try {
			List<Street> streets = CSVImport.fromFile(new File(getClass().getResource("Streets.csv").getPath()), new StreetImporter());
			edition.setBackground(ImageIO.read(getClass().getResourceAsStream("throneopoly_board.jpg")));
			edition.addFigure(new Figure(ImageIO.read(getClass().getResourceAsStream("john_snow.jpeg")), "John Snow"));
			edition.addFigure(new Figure(ImageIO.read(getClass().getResourceAsStream("bernd.png")), "Bernd Knauer"));
			edition.addFigure(new Figure(ImageIO.read(getClass().getResourceAsStream("bud-spencer.png")), "Bud Spencer"));
			edition.setCurrencyFactor(1.0);
			edition.setCurrency("Gold");
			edition.setMaxAmountOfPlayers(4);
			edition.setName("Standard");
			edition.setFields(FieldsFactory.createFields(streets, communityCards, eventCards));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return edition;
	}

}
