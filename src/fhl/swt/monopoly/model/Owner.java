package fhl.swt.monopoly.model;

public interface Owner<T> {

	public void addToInventory(T t);

	public void removeFromInventory(T t);

}
