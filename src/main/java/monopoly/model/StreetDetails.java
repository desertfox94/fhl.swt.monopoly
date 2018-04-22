package monopoly.model;

import java.math.BigDecimal;

public class StreetDetails {

	private double baseRent;
	private BigDecimal firstHouseRent;
	private BigDecimal secondHouseRent;
	private BigDecimal thirdHouseRent;
	private BigDecimal fourthHouseRent;
	private BigDecimal hotelRent;
	private BigDecimal pricePerHouse;
	private BigDecimal mortage;

	public double getBaseRent() {
		return baseRent;
	}

	public BigDecimal getHotelRent() {
		return hotelRent;
	}

	public void setHotelRent(BigDecimal hotelRent) {
		this.hotelRent = hotelRent;
	}

	public BigDecimal getMortage() {
		return mortage;
	}

	public BigDecimal getFirstHouseRent() {
		return firstHouseRent;
	}

	public BigDecimal getSecondHouseRent() {
		return secondHouseRent;
	}

	public BigDecimal getThirdHouseRent() {
		return thirdHouseRent;
	}

	public BigDecimal getFourthHouseRent() {
		return fourthHouseRent;
	}

	public BigDecimal getPricePerHouse() {
		return pricePerHouse;
	}

	public void setBaseRent(double baseRent) {
		this.baseRent = baseRent;
	}

	public void setFirstHouseRent(BigDecimal firstHouseRent) {
		this.firstHouseRent = firstHouseRent;
	}

	public void setSecondHouseRent(BigDecimal secondHouseRent) {
		this.secondHouseRent = secondHouseRent;
	}

	public void setThirdHouseRent(BigDecimal thirdHouseRent) {
		this.thirdHouseRent = thirdHouseRent;
	}

	public void setFourthHouseRent(BigDecimal fourthHouseRent) {
		this.fourthHouseRent = fourthHouseRent;
	}

	public void setPricePerHouse(BigDecimal pricePerHouse) {
		this.pricePerHouse = pricePerHouse;
	}

	public void setMortage(BigDecimal mortage) {
		this.mortage = mortage;
	}

}
