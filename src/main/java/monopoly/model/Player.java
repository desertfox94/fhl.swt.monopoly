package monopoly.model;

import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import monopoly.core.cards.Card;
import monopoly.core.fields.Field;

public class Player implements StreetOwner, CardOwner {

	private String id;
	private Game game;
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty balance = new SimpleIntegerProperty();
	private ObservableList<Street> streets = FXCollections.observableArrayList();
	private ObservableList<Card> cards = FXCollections.observableArrayList();
	private IntegerProperty jailCount = new SimpleIntegerProperty();
	private BooleanProperty jail = new SimpleBooleanProperty();
	private SimpleObjectProperty<Field> field = new SimpleObjectProperty<>();
	private Figure figure;
	private int doubleCount;
	private SimpleIntegerProperty position = new SimpleIntegerProperty(0);

	public SimpleObjectProperty<Field> getField() {
		return field;
	}

	public void moveTo(Field field) {
		this.field.set(field);
		position.set(field.getIndex());

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public IntegerProperty getBalance() {
		return balance;
	}

	public void pay(int amount) {
		balance.set(balance.get() - amount);
	}

	public void addMoney(int amount) {
		balance.set(balance.get() + amount);
	}

	@Override
	public ObservableList<Street> getStreets() {
		return streets;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = FXCollections.observableArrayList(cards);
	}

	public boolean isInJail() {
		return jail.get();
	}

	public void freeFromJail() {
		jailCount.set(0);
		jail.set(false);
	}

	public void sendToJail() {
		jailCount.set(1);
		jail.set(true);
		// field.set(game.getEdition().getFields().get(JailField.INDEX));
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public void incDoubleCount() {
		doubleCount++;
	}

	public int getDoubleCount() {
		return doubleCount;
	}

	@Override
	public void addToInventory(Street street) {
		streets.add(street);
	}

	@Override
	public void removeFromInventory(Street street) {
		streets.remove(street);
	}

	@Override
	public void addCardToInventory(Card card) {
		cards.add(card);
	}

	@Override
	public void removeCardFromInventory(Card card) {
		cards.remove(card);
	}

	public void setBalance(int balance) {
		this.balance.set(balance);
	}

	public SimpleIntegerProperty getPosition() {
		return position;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {return game;}

	@Override
	public String toString() {
		return name.toString();
	}
	
	public void sellHouse(int index) {
		streets.remove(index);
	}
	
	 public List<Street> streetsWithHouse() {  
	    	return getStreets().stream().filter(s -> s.getNumberOfHouses() > 0).collect(Collectors.<Street>toList());
	 }
	    
	 public List<Street> notMortagedStreets() {
	    	return getStreets().stream().filter(s -> !s.isMortage()).collect(Collectors.<Street>toList());
	 }
	 
	 public List<Street> mortagedStreets() {
	    	return getStreets().stream().filter(s -> s.isMortage()).collect(Collectors.<Street>toList());
	 }
	    
	 public boolean hasHouses (){
	    	return getStreets().stream().anyMatch(s -> s.getNumberOfHouses() > 0);
	 }
	 
	 public boolean hasNoMortagedStreets (){
	    	return getStreets().stream().anyMatch(s -> !s.isMortage());
	 }

	 public void moneyForMortage () {
		 for (Street s : mortagedStreets()) {
			 this.addMoney(s.getPrice());
		 }
	 }

}
