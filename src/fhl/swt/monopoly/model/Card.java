package fhl.swt.monopoly.model;

import java.awt.Image;

public class Card {

	private String title;
	private String decription;
	private EventTrigger eventTrigger;
	private Image icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public EventTrigger getEventTrigger() {
		return eventTrigger;
	}
	public void setEventTrigger(EventTrigger eventTrigger) {
		this.eventTrigger = eventTrigger;
	}
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
}
