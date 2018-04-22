package monopoly.core.cards;

import monopoly.model.Player;

public abstract class Card {

	private String title;
	
	private String description;

	public Card(String title, String description) {
		this.title = title.trim();
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public abstract boolean execute(Player player);

}
