package monopoly.core.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import monopoly.core.mock.MockService;

public class CardFactory {

	public static final HashMap<String, Class<?>> cardRegistery = new HashMap<>();

	public static CardSet createEventCards() {
		return MockService.createDummyCards("Ereigniskarten", 5);
	}

	public static CardSet createCommunityCards() {
		return MockService.createDummyCards("Gemeinschaftskarten", 5);
	}

	private static CardFactory factory;

	private CardFactory() {
		init();
	}

	public static CardFactory getInstance() {
		if (factory == null) {
			factory = new CardFactory();
		}
		return factory;
	}

	private void init() {
		// comments
		cardRegistery.put("1", FreeFromJail.class);
	}

	public static List<Card> createCarsSet(Collection<String> cardIds) {
		List<Card> cards = new ArrayList<Card>(0);
		for (String cardId : cardIds) {
			try {
				cards.add((Card) cardRegistery.get(cardId).newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cards;
	}

}
