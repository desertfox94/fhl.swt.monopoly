package fhl.swt.monopoly.model;

import java.awt.Image;

public class Card implements EventTrigger {

	private String title;
	private String decription;
	private Image icon;

	private CardOwner owner;
	
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

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

	public CardOwner getOwner() {
		return owner;
	}

	public void setOwner(CardOwner owner) {
		this.owner = owner;
	}

}
