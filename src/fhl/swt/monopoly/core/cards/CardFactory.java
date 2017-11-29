package fhl.swt.monopoly.core.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CardFactory {

	public static final HashMap<String, Class<?>> cardRegistery = new HashMap<>();
	
	private static CardFactory factory;
	
	private CardFactory() {
	}

	public static CardFactory getInstance() {
		if (factory == null) {
			factory = new CardFactory();
			factory.init();
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
