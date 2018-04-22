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
		street.setPrice(line.getBigDecimalValue(1));
		StreetDetails rentDetails = new StreetDetails();
		rentDetails.setBaseRent(new Double(line.getValue(2)));
		rentDetails.setFirstHouseRent(line.getBigDecimalValue(3));
		rentDetails.setSecondHouseRent(line.getBigDecimalValue(4));
		rentDetails.setThirdHouseRent(line.getBigDecimalValue(5));
		rentDetails.setFourthHouseRent(line.getBigDecimalValue(6));
		rentDetails.setHotelRent(line.getBigDecimalValue(7));
		rentDetails.setPricePerHouse(line.getBigDecimalValue(8));
		rentDetails.setMortage(line.getBigDecimalValue(9));
		street.setRentDetails(rentDetails);
		StreetGroup group = streetGroups.getOrDefault(line.getValue(10), new StreetGroup());
		group.getStreets().add(street);
		group.setPricePerHouse(line.getBigDecimalValue(8));
		street.setGroup(group);
		return street;
	}

}
