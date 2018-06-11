package monopoly.core;

import java.util.ArrayList;
import java.util.List;

import monopoly.core.fields.Field;
/*
 * A simple circular list, used for displaying the Map, as well as rotating through cardstacks.
 */
public class CircleList<T> {

	private int i;

	private List<T> list;

	public CircleList(List<T> list) {
		this.list = list;
	}

	public CircleList() {
		this (new ArrayList<T>());
	}

	public T next() {
		if (list.isEmpty()) {
			return null;
		}
		i++;
		if (i >= list.size()) {
			i = 0;
		}
		return list.get(i);
	}

	public void putLast(T t) {
		List<T> newList = new ArrayList<>(list.subList(0, i));
		newList.add(t);
		newList.addAll(list.subList(i, list.size()));
		i++;
		list = newList;
	}
	
	public T getCurrent() {
		return list.get(i);
	}

	public boolean add(T t) {
		return list.add(t);
	}

	public void select(T t) {
		i = list.indexOf(t);
	}

	public boolean remove(T t) {
		return list.remove(t);
	}

	public List<T> toList() {
		return list;
	}
	
	public boolean contains(T t) {
		return list.contains(t);
	}

	public T get(int index) {
		return list.get(index);
	}

	public int size(){
		return list.size();
	}

}
