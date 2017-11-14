package fhl.swt.monopoly.model;

import java.util.List;
import java.util.Map;

public class Edition {

	private String name;
	private Map<Integer, Street> stra�enAnordnung;
	private int maxSpielerAnzahl;
	private String waehrung;
	private double WaehrungsFaktor;
	private List<Figur> figuren;
	private Design design;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Integer, Street> getStra�enAnordnung() {
		return stra�enAnordnung;
	}
	public void setStra�enAnordnung(Map<Integer, Street> stra�enAnordnung) {
		this.stra�enAnordnung = stra�enAnordnung;
	}
	public int getMaxSpielerAnzahl() {
		return maxSpielerAnzahl;
	}
	public void setMaxSpielerAnzahl(int maxSpielerAnzahl) {
		this.maxSpielerAnzahl = maxSpielerAnzahl;
	}
	public String getWaehrung() {
		return waehrung;
	}
	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}
	public double getWaehrungsFaktor() {
		return WaehrungsFaktor;
	}
	public void setWaehrungsFaktor(double waehrungsFaktor) {
		WaehrungsFaktor = waehrungsFaktor;
	}
	public List<Figur> getFiguren() {
		return figuren;
	}
	public void setFiguren(List<Figur> figuren) {
		this.figuren = figuren;
	}
	public Design getDesign() {
		return design;
	}
	public void setDesign(Design design) {
		this.design = design;
	}
}
