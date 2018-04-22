package monopoly.model;

import java.util.List;

public interface StreetOwner {

	List<Street> getStreets();

	public void addToInventory(Street street);

	public String getName();

	public void removeFromInventory(Street street);

}
