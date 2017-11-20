package fhl.swt.monopoly.model;

import java.math.BigDecimal;
import java.util.Map;

public class StreetDetails {

	private double baseRent;
	private int firstHousePrice;
	private int secondHousePrice;
	private int thirdHousePrice;
	private int fourthHousePrice;
	private BigDecimal hotelRent;
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

	public int getFirstHousePrice() {
		return firstHousePrice;
	}

	public int getSecondHousePrice() {
		return secondHousePrice;
	}

	public int getThirdHousePrice() {
		return thirdHousePrice;
	}

	public int getFourthHousePrice() {
		return fourthHousePrice;
	}

}
