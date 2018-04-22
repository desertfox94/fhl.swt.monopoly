package monopoly.core.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import monopoly.core.DBService;
import monopoly.core.cards.CardFactory;
import monopoly.core.cards.CardSet;
import monopoly.core.fields.FieldsFactory;
import monopoly.io.StreetImporter;
import monopoly.io.csv.CSVImport;
import monopoly.model.Edition;
import monopoly.model.Figure;
import monopoly.model.Game;
import monopoly.model.Street;

public class MockService extends DBService {

	private static final String EDITION_GOT = "GoT";
	private static final String EDITION_STANDARD = "Standard";

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
	public List<Edition> loadAvailableEditions() {
		return Arrays.asList(loadEdition(EDITION_STANDARD), loadEdition(EDITION_GOT));
	}

	@Override
	public Edition loadEdition(String name) {
		Edition edition = new Edition();
		CardSet communityCards = CardFactory.createCommunityCards();
		CardSet eventCards = CardFactory.createEventCards();
		List<Figure> figures = new LinkedList<Figure>();
		String streetsCsv = null;
		String background = null;
		String currency = null;
		if (EDITION_GOT.equals(name)) {
			currency = "Gold";
			streetsCsv = "got/GoT_Streets.csv";
			background = "got/throneopoly_board.jpg";
			figures.add(loadFigure("got/john_snow.jpeg", "John Snow"));
			figures.add(loadFigure("got/bernd.png", "Bernd Knauer"));
			figures.add(loadFigure("got/bud-spencer.png", "Bud Spencer"));
		} else if (EDITION_STANDARD.equals(name)) {
			currency = "Dollar";
			streetsCsv = "std/streets.csv";
			background = "std/board.jpg";
			figures.add(loadFigure("std/monopoly_car.png", "Auto"));
			figures.add(loadFigure("std/monopoly_dog.png", "Hund"));
			figures.add(loadFigure("std/monopoly_hat.png", "Hut"));
			figures.add(loadFigure("std/monopoly_ship.png", "Schiff"));
			figures.add(loadFigure("std/monopoly_shoe.png", "Schuh"));
			figures.add(loadFigure("std/monopoly_guy.png", "Mr. Monopoly"));
		}

		edition.setMaxAmountOfPlayers(figures.size());
		List<Street> streets;
		try {
			streets = CSVImport.from(getClass().getResourceAsStream(streetsCsv), new StreetImporter());
			edition.setBackground(getClass().getResource(background).toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		edition.setFigures(figures);
		edition.setCurrencyFactor(1.0);
		edition.setCurrency(currency);
		edition.setName(name);
		edition.setFields(FieldsFactory.createFields(streets, communityCards, eventCards));
		return edition;
	}

	private Figure loadFigure(String imagePath, String name) {
		try {
			return new Figure(ImageIO.read(getClass().getResourceAsStream(imagePath)), name);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
