package monopoly.core.cards;

import java.util.HashMap;

import monopoly.core.CircleList;

public class CardFactory {

	public static final HashMap<String, Class<?>> cardRegistery = new HashMap<>();

	public static CardSet createEventCards() {
		return createCards("Ereigniskarten");
	}

	public static CardSet createCommunityCards() {
		return createCards("Gemeinschaftskarten");
	}

	public static CardSet createCards(String type) {
		CircleList<Card> cards = new CircleList<>();
		cards.add(new FreeFromJail());
		cards.add(new GotoSchlossallee());
		cards.add(new DividendCard());
		cards.add(new StreetReconstruction());
		return new CardSet(cards, type);
	}

}
