package fhl.swt.monopoly.model;

import java.util.List;

public interface StreetOwner {

	List<Street> getStreets();
	
	public void addToInventory(Street street);
	
	public void removeFromInventory(Street street);
	
}
