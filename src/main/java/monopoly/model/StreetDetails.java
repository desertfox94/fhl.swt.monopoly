package monopoly.model;


public class StreetDetails {

	private int baseRent;
	private int firstHouseRent;
	private int secondHouseRent;
	private int thirdHouseRent;
	private int fourthHouseRent;
	private int hotelRent;
	private int pricePerHouse;
	private int mortage;

	public int getBaseRent() {
		return baseRent;
	}

	public int getHotelRent() {
		return hotelRent;
	}

	public void setHotelRent(int hotelRent) {
		this.hotelRent = hotelRent;
	}

	public int getMortage() {
		return mortage;
	}

	public int getFirstHouseRent() {
		return firstHouseRent;
	}

	public int getSecondHouseRent() {
		return secondHouseRent;
	}

	public int getThirdHouseRent() {
		return thirdHouseRent;
	}

	public int getFourthHouseRent() {
		return fourthHouseRent;
	}

	public int getPricePerHouse() {
		return pricePerHouse;
	}

	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}

	public void setFirstHouseRent(int firstHouseRent) {
		this.firstHouseRent = firstHouseRent;
	}

	public void setSecondHouseRent(int secondHouseRent) {
		this.secondHouseRent = secondHouseRent;
	}

	public void setThirdHouseRent(int thirdHouseRent) {
		this.thirdHouseRent = thirdHouseRent;
	}

	public void setFourthHouseRent(int fourthHouseRent) {
		this.fourthHouseRent = fourthHouseRent;
	}

	public void setPricePerHouse(int pricePerHouse) {
		this.pricePerHouse = pricePerHouse;
	}

	public void setMortage(int mortage) {
		this.mortage = mortage;
	}

}
