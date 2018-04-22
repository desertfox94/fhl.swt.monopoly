package monopoly.core.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import monopoly.core.CircleList;

public class CardFactory {

	public static final HashMap<String, Class<?>> cardRegistery = new HashMap<>();

	public static CardSet createEventCards() {
		return new CardSet(new CircleList<>(), "Event Card") {

			@Override
			void initCards() {
				// TODO Auto-generated method stub

			}
		};
	}

	public static CardSet createCommunityCards() {
		return new CardSet(new CircleList<>(), "Community Card") {

			@Override
			void initCards() {
				// TODO Auto-generated method stub

			}
		};

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