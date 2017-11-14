package fhl.swt.monopoly.model;

import java.math.BigDecimal;

public class Street {

	private String name;
	private BigDecimal price;
	private int numberOfHouses;
	private int hotels;
	private boolean mortage;
	private Player owner;
	private RentDetails rentDetails;
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

	public void setNumberOfHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
	}

	public int getHotels() {
		return hotels;
	}

	public void setHotels(int hotels) {
		this.hotels = hotels;
	}

	public boolean isMortage() {
		return mortage;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public RentDetails getRentDetails() {
		return rentDetails;
	}

	public void setRentDetails(RentDetails rentDetails) {
		this.rentDetails = rentDetails;
	}

	public StreetGroup getGroup() {
		return group;
	}

	public void setGroup(StreetGroup group) {
		this.group = group;
	}

}
