package fhl.swt.monopoly.model;

import java.math.BigDecimal;
import java.util.List;

public class Spieler {

	private String id;
	private String name;
	private Figur figur;
	private BigDecimal kontostand;
	private List<Strasse> strassenBestand;
	private List<Karte> kartenBestand;
	private boolean imGefaengnis;
	
}
