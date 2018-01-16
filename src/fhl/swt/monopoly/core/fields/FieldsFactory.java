package fhl.swt.monopoly.core.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fhl.swt.monopoly.core.CircleList;
import fhl.swt.monopoly.core.cards.CardSet;
import fhl.swt.monopoly.model.Street;

public class FieldsFactory {

	private static final int START = 0;
	private static final int STREET = 1;
	private static final int COMMUNITY = 2;
	private static final int TAX = 3;
	private static final int EVENT = 4;
	private static final int JAIL = 5;
	private static final int FREEPARKING = 6;
	private static final int GO_TO_JAIL = 7;
	private static CardSet communityCards;
	private static CardSet eventCards;
	private static List<Street> streets;
	private static int streetCounter;

	private static final List<Integer> FIELD_TYPES = Arrays.asList(START, STREET, COMMUNITY, STREET, TAX, STREET, STREET, EVENT, STREET, STREET, JAIL, STREET, STREET, STREET, STREET, STREET, STREET, COMMUNITY, STREET, STREET, FREEPARKING, STREET, EVENT, STREET, STREET, STREET, STREET, STREET, STREET, STREET, GO_TO_JAIL, STREET, STREET, COMMUNITY, STREET, STREET, EVENT, STREET, TAX, STREET);

	public static CircleList<Field> createFields(List<Street> streets, CardSet communityCards, CardSet eventCards) {
		FieldsFactory.streets = streets;
		FieldsFactory.communityCards = communityCards;
		FieldsFactory.eventCards = eventCards;
		streetCounter = 0;
		List<Field> fields = new ArrayList<Field>(40);
		int fieldIndex = 0;
		for (Integer fieldType : FIELD_TYPES) {
			fields.add(createField(fieldType, fieldIndex));
			fieldIndex++;
		}
		return new CircleList<>(fields);
	}

	private static Field createField(Integer fieldType, int index) {
		switch (fieldType) {
		case START:
			return new StartField();
		case STREET:
			return new StreetField(streets.get(streetCounter++), index);
		case COMMUNITY:
			return new CardField(communityCards, index);
		case TAX:
			return new TaxField(index);
		case EVENT:
			return new CardField(eventCards, index);
		case JAIL:
			return new JailField();
		case FREEPARKING:
			return new FreeparkingField();
		case GO_TO_JAIL:
			return new GoToJail();

		default:
			throw new RuntimeException("Fieldtype not supported!");
		}
	}

}