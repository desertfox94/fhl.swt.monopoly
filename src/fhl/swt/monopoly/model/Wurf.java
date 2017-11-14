package fhl.swt.monopoly.model;

public class Wurf {

	private int augenzahl;
	private int ersterWuerfel;
	private int zweiterWuerfel;
	private Spieler spieler;
	
	public boolean istPasch()  {
		return ersterWuerfel == zweiterWuerfel;
	}
	
}
