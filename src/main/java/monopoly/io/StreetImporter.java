package monopoly.io;

import java.util.HashMap;
import java.util.Map;

import monopoly.io.csv.CsvImporter;
import monopoly.io.csv.CsvLine;
import monopoly.model.Street;
import monopoly.model.StreetDetails;
import monopoly.model.StreetGroup;

public class StreetImporter extends CsvImporter<Street> {

	private Map<String, StreetGroup> streetGroups = new HashMap<String, StreetGroup>();

	@Override
	protected Street importEntity(CsvLine line) {
		if (line.getRow() == 0) {
			return null;
		}
		Street street = new Street();
		street.setName(line.getValue(0));
		street.setPrice(line.getIntegerValue(1));
		StreetDetails rentDetails = new StreetDetails();
		rentDetails.setBaseRent(new Integer(line.getValue(2)));
		rentDetails.setFirstHouseRent(line.getIntegerValue(3));
		rentDetails.setSecondHouseRent(line.getIntegerValue(4));
		rentDetails.setThirdHouseRent(line.getIntegerValue(5));
		rentDetails.setFourthHouseRent(line.getIntegerValue(6));
		rentDetails.setHotelRent(line.getIntegerValue(7));
		rentDetails.setPricePerHouse(line.getIntegerValue(8));
		rentDetails.setMortgage(line.getIntegerValue(9));
		street.setRentDetails(rentDetails);
		StreetGroup group = streetGroups.getOrDefault(line.getValue(10), new StreetGroup());
		group.getStreets().add(street);
		group.setPricePerHouse(line.getIntegerValue(8));
		street.setGroup(group);
		return street;
	}

}
