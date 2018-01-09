package fhl.swt.monopoly.model;

import java.util.List;

import fhl.swt.monopoly.core.cards.Card;
import fhl.swt.monopoly.core.fields.Field;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player implements StreetOwner, CardOwner {

	private String id;
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty balance = new SimpleDoubleProperty();
	private ObservableList<Street> streets = FXCollections.emptyObservableList();
	private ObservableList<Card> cards = FXCollections.emptyObservableList();
	private int jailCount;
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

	public DoubleProperty getBalance() {
		return balance;
	}

	public void pay(double amount) {
		balance.subtract(amount);
	}

	public void addMoney(double amount) {
		balance.add(amount);
	}

	@Override
	public List<Street> getStreets() {
		return streets;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = FXCollections.observableArrayList(cards);
	}

	public boolean isInJail() {
		return jailCount > 0;
	}

	public void freeFromJail() {
		jailCount = 0;
	}

	public void sendToJail() {
		jailCount++;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void addCardToInventory(Card card) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCardFromInventory(Card card) {
		// TODO Auto-generated method stub

	}

	public void setBalance(double balance) {
		this.balance.set(balance);
	}

	public SimpleIntegerProperty getPosition() {
		return position;
	}

}
