package monopoly.model;

import java.math.BigDecimal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Street {

	private String name;
	private BigDecimal price;
	private int numberOfHouses;
	private boolean hotelBuild;
	private boolean mortage;
	private StreetOwner owner;
	private StreetDetails rentDetails;
	private StreetGroup group;

	private DoubleProperty rent = new SimpleDoubleProperty();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNumberOfHouses() {
		return numberOfHouses;
	}

	public void buildHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
		rent.set(getCurrentRent().doubleValue());
	}

	public void demolishHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
	}

	public boolean getHotel() {
		return hotelBuild;
	}

	public void buildHotel() {
		this.hotelBuild = true;
	}

	public void demolishHotel() {
		this.hotelBuild = false;
	}

	public boolean isMortage() {
		return mortage;
	}

	public void assumeMortage() {
		this.mortage = true;
	}

	public StreetOwner getOwner() {
		return owner;
	}

	public void setOwner(StreetOwner owner) {
		this.owner = owner;
	}

	public StreetDetails getRentDetails() {
		return rentDetails;
	}

	public StreetGroup getGroup() {
		return group;
	}

	private BigDecimal getCurrentRent() {
		if (getHotel()) {
			return rentDetails.getHotelRent();
		}
		switch (numberOfHouses) {
		case 1:
			return rentDetails.getFirstHouseRent();
		case 2:
			return rentDetails.getSecondHouseRent();
		case 3:
			return rentDetails.getThirdHouseRent();
		case 4:
			return rentDetails.getFourthHouseRent();
		case 0:
		default:
			return new BigDecimal(rentDetails.getBaseRent());
		}
	}

	public void setRentDetails(StreetDetails rentDetails) {
		this.rentDetails = rentDetails;
		rent.set(rentDetails.getBaseRent());
	}

	public void setGroup(StreetGroup group) {
		this.group = group;
	}

	public DoubleProperty getRent() {
		return rent;
	}

}