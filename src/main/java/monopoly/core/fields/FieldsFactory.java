package monopoly.core.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import monopoly.core.CircleList;
import monopoly.core.cards.CardSet;
import monopoly.model.Street;

/**
 * This class is used to build the playing field.
 * 
 */
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

	/**
	 * This list represents the order in which the different fieldtypes are positioned on the playing field.
	 */
	private static final List<Integer> FIELD_TYPES = Arrays.asList(START, STREET, COMMUNITY, STREET, TAX, STREET, STREET, EVENT, STREET, STREET, JAIL, STREET, STREET, STREET, STREET, STREET, STREET, COMMUNITY, STREET, STREET, FREEPARKING, STREET, EVENT, STREET, STREET, STREET, STREET, STREET, STREET, STREET, GO_TO_JAIL, STREET, STREET, COMMUNITY, STREET, STREET, EVENT, STREET, TAX, STREET);

	
	/**
	 * this method builds the playingField as a circular list of fields.
	 * @param streets a list of the streets, stations etc. with their proper names depending on language and edition.
	 * @param communityCards the initialized stack of community cards.
	 * @param eventCards the initialized stack of event cards.
	 * @return returns a circular List of Fields representing the complete playingField, ready to be drawn on the field.
	 */
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

	/**
	 * A helper function to dereference values to fieldTypes and immediately call the according constructors for the right field.
	 * @param fieldType A numeric value representing the field type (see above).
	 * @param index Used to discern between the different streets in their according position.
	 * @return returns an object of the Field type, according to the input values.
	 */
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
