package monopoly.model;

import static monopoly.core.Logger.ActionLogger;
import static monopoly.core.Logger.PLAYER;

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
import monopoly.core.Logger;
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

	public SimpleObjectProperty<Field> getFieldProperty() {
		return field;
	}

	public Field getField() {
		return field.get();
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

	@Override
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public IntegerProperty getBalanceProperty() {
		return balance;
	}

	public int getBalance() {
		return balance.intValue();
	}

	public void pay(int amount) {
		ActionLogger.log(this, PLAYER, "PAY", ""+amount);
		balance.set(balance.get() - amount);
	}

	public void addMoney(int amount) {
		ActionLogger.log(this, PLAYER, "ADD MONEY", ""+amount);
		balance.set(balance.get() + amount);
	}

	@Override
	public ObservableList<Street> getStreets() {
		return streets;
	}

	@Override
	public ObservableList<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = FXCollections.observableArrayList(cards);
	}

	public boolean isInJail() {
		return jail.get();
	}

	public void freeFromJail() {
		ActionLogger.log(this, PLAYER, "FREE FROM JAIL", "rounds in jail: " + jailCount.intValue());
		jailCount.set(0);
		jail.set(false);
	}

	public void sendToJail() {
		Logger.ActionLogger.log(this, PLAYER, "SEND TO JAIL", "");
		jailCount.set(1);
		jail.set(true);
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
		Logger.ActionLogger.log(this, PLAYER, "ADD STREET TO INVENTORY", street.getName());
		street.setOwner(this);
		streets.add(street);
	}

	@Override
	public void removeFromInventory(Street street) {
		streets.remove(street);
	}

	@Override
	public void addCardToInventory(Card card) {
		Logger.ActionLogger.log(this, PLAYER, "ADD CARD TO INVENTORY", card.toString());
		cards.add(card);
	}

	@Override
	public void removeCardFromInventory(Card card) {
		Logger.ActionLogger.log(this, PLAYER, "REMOVE CARD FROM INVENTORY", card.toString());
		cards.remove(card);
	}

	public void setBalance(int balance) {
		this.balance.set(balance);
	}

	public SimpleIntegerProperty getPositionProperty() {
		return position;
	}

	public int getPosition() {
		return position.get();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	@Override
	public String toString() {
		return name.toString();
	}

	public void sellHouse(int index) {
		streets.remove(index);
	}

	public List<Street> streetsWithHouse() {
		return getStreets().stream().filter(s -> s.getNumberOfHouses() > 0).collect(Collectors.<Street> toList());
	}

	public List<Street> notMortgagedStreets() {
		return getStreets().stream().filter(s -> !s.isMortgage()).collect(Collectors.<Street> toList());
	}

	public List<Street> mortgagedStreets() {
		return getStreets().stream().filter(s -> s.isMortgage()).collect(Collectors.<Street> toList());
	}

	public boolean hasHouses() {
		return getStreets().stream().anyMatch(s -> s.getNumberOfHouses() > 0);
	}

	public boolean hasNoMortgagedStreets() {
		return getStreets().stream().anyMatch(s -> !s.isMortgage());
	}

	public void moneyForMortgage() {
		for (Street s : mortgagedStreets()) {
			this.addMoney(s.getPrice());
		}
	}

}
