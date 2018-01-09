package fhl.swt.monopoly.model;

import java.awt.image.BufferedImage;

public class Figure {

	private BufferedImage image;

	private String name;

	public Figure(BufferedImage image, String name) {
		this.image = image;
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

}
