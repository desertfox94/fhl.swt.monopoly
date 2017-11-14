package fhl.swt.monopoly.model;

import java.util.List;
import java.util.Map;

public class Edition {

	private String name;
	private Map<Integer, Strasse> straﬂenAnordnung;
	private int maxSpielerAnzahl;
	private String waehrung;
	private double WaehrungsFaktor;
	private List<Figur> figuren;
	private Design design;
}
