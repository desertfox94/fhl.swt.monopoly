package monopoly.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Street {

	private String name;
	private int price;
	private int numberOfHouses;
	private boolean hotelBuild;
	private boolean mortgage;
	private Player owner;
	private StreetDetails rentDetails;
	private StreetGroup group;
	private boolean auctionObjective;

	private IntegerProperty rent = new SimpleIntegerProperty();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumberOfHouses() {
		return numberOfHouses;
	}

	public void buildHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
		rent.set(getCurrentRent());
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

	public boolean isMortgage() {
		return mortgage;
	}

	public void assumeMortgage() {
		if (!mortgage) {
			mortgage = true;
			owner.addMoney(mortgageValue());
		}
	}

	public int mortgageValue() {
		return price / 2;
	}

	public void redeemMortgage() {
		if (mortgage  && owner.getBalance() >= mortgageValue()) {
			mortgage = false;
			owner.addMoney(mortgageValue() * -1);
		}
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public StreetDetails getRentDetails() {
		return rentDetails;
	}

	public StreetGroup getGroup() {
		return group;
	}

	public boolean isAuctionObjective() {
		return auctionObjective;
	}

	private int getCurrentRent() {
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
			return rentDetails.getBaseRent();
		}
	}

	public void setRentDetails(StreetDetails rentDetails) {
		this.rentDetails = rentDetails;
		rent.set(rentDetails.getBaseRent());
	}

	public void setGroup(StreetGroup group) {
		this.group = group;
	}

	public IntegerProperty getRent() {
		return rent;
	}

	public void startAuction() {
		auctionObjective = true;
		// Todo: MXX Auktion starten aufrufen.
	}

	public void endAuction() {
		auctionObjective = false;
	}

}
