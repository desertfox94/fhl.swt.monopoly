package fhl.swt.monopoly.model;

import java.math.BigDecimal;

public class Street {

	private String name;
	private BigDecimal price;
	private int numberOfHouses;
	private boolean hotelBuild;
	private boolean mortage;
	private StreetOwner owner;
	private StreetDetails rentDetails;
	private StreetGroup group;

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

}
