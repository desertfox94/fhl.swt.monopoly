package fhl.swt.monopoly.core.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fhl.swt.monopoly.core.CircleList;
import fhl.swt.monopoly.model.Edition;
import fhl.swt.monopoly.model.Game;
import fhl.swt.monopoly.model.Player;
import fhl.swt.monopoly.model.Street;
import fhl.swt.monopoly.model.StreetDetails;

public class GameMock extends Game {

	private static Random r = new Random();

	private static List<Street> streets = initStreets();

	private static List<Street> initStreets() {
		List<String> streetsNames = Arrays.asList("Badstraﬂe", "Turmstraﬂe", "S¸dbahnhof", "Chausseestraﬂe", "Elisenstraﬂe", "Poststraﬂe", "Seestraﬂe", "Elektrizit‰tswerk", "Hafenstraﬂe", "Neue Straﬂe", "Westbahnhof", "M¸nchner Straﬂe", "Wiener Straﬂe", "Berliner Straﬂe", "Theaterstraﬂe", "Museumsstraﬂe", "Opernplatz", "Nordbahnhof", "Lessingstraﬂe", "Schillerstraﬂe", "Wasserwerk", "Goethestraﬂe",
				"Rathausplatz", "Hauptstraﬂe", "Bahnhofstraﬂe", "Hauptbahnhof", "Parkstraﬂe", "Schloﬂallee");
		List<Street> streets = new ArrayList<>(streetsNames.size());
		for (String name : streetsNames) {
			Street s = new Street();
			s.setName(name);
			s.setPrice(new BigDecimal(80 * r.nextInt(100)));
			s.setRentDetails(mockStreetDetails());
			streets.add(s);
		}
		return streets;
	}

	private static StreetDetails mockStreetDetails() {
		StreetDetails streetDetails = new StreetDetails();
		BigDecimal baseRent = new BigDecimal(r.nextInt(10) * 2);
		streetDetails.setBaseRent(baseRent.doubleValue());
		streetDetails.setSecondHouseRent(baseRent.multiply(BigDecimal.valueOf(10)));
		streetDetails.setThirdHouseRent(baseRent.multiply(BigDecimal.valueOf(20)));
		streetDetails.setFourthHouseRent(baseRent.multiply(BigDecimal.valueOf(30)));
		streetDetails.setHotelRent(baseRent.multiply(BigDecimal.valueOf(50)));
		return streetDetails;
	}

	public static List<Game> games() {
		List<Game> games = new ArrayList<>();
		games.add(mockGame());
		return games;
	}

	public static Game mockGame() {
		Game game = new Game();
		Edition edition = new Edition();
		edition.setFields(new CircleList<>());
		game.setEdition(edition);
		game.addPlayer(mockPlayer("Peter"));
		game.addPlayer(mockPlayer("Inge"));
		game.addPlayer(mockPlayer("Fliegendes Spaghettimonster"));
		return game;
	}

	private static Player mockPlayer(String name) {
		Player player = new Player();
		player.setBalance(new BigDecimal(45000));
		int streetCount = r.nextInt(8);
		for (int i = 0; i < streetCount; i++) {
			player.addToInventory(streets.get(r.nextInt(streets.size())));
		}
		player.setName(name);
		return player;
	}

}
