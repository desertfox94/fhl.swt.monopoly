package fhl.swt.monopoly.model;

import java.math.BigDecimal;
import java.util.Map;

public class RentDetails {

	private double baseRent;
	private Map<Integer, BigDecimal> rentPerHouse;
	private BigDecimal hotelRent;
	private double mortage;

	public double getBaseRent() {
		return baseRent;
	}

	public void setBaseRent(double baseRent) {
		this.baseRent = baseRent;
	}

	public void setRentPerHouse(Map<Integer, BigDecimal> rentPerHouse) {
		this.rentPerHouse = rentPerHouse;
	}

	public BigDecimal getHotelRent() {
		return hotelRent;
	}

	public void setHotelRent(BigDecimal hotelRent) {
		this.hotelRent = hotelRent;
	}

	public double getMortage() {
		return mortage;
	}

	public void setMortage(double mortage) {
		this.mortage = mortage;
	}

}
