package fhl.swt.monopoly.io;

import java.math.BigDecimal;

import de.desertfox.csv.CsvImporter;
import de.desertfox.csv.CsvLine;
import fhl.swt.monopoly.model.Street;
import fhl.swt.monopoly.model.StreetDetails;

public class StreetImporter extends CsvImporter<Street> {

	@Override
	protected Street importEntity(CsvLine line) {
		Street street = new Street();
		street.setName("Badstraﬂe");
		street.setPrice(new BigDecimal(0.0));
		StreetDetails rentDetails = new StreetDetails();
		rentDetails.setBaseRent(0.0);
		rentDetails.setFirstHouseRent(new BigDecimal(0.0));
		rentDetails.setSecondHouseRent(new BigDecimal(0.0));
		rentDetails.setThirdHouseRent(new BigDecimal(0.0));
		rentDetails.setFourthHouseRent(new BigDecimal(0.0));
		rentDetails.setHotelRent(new BigDecimal(0.0));
		rentDetails.setPricePerHouse(new BigDecimal(0.0));
		street.setRentDetails(rentDetails);
		return street;
	}

}
