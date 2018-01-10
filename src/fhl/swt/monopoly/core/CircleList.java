package fhl.swt.monopoly.core;

import java.util.ArrayList;
import java.util.List;

import fhl.swt.monopoly.core.fields.Field;

public class CircleList<T> {

	private int i;

	private final List<T> list;

	public CircleList(List<T> list) {
		this.list = list;
	}

	public CircleList() {
		this.list = new ArrayList<T>();
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

	public T getCurrent() {
		return list.get(i);
	}

	public boolean add(T t) {
		return list.add(t);
	}

	public void select(T t) {
		i = list.indexOf(t);
	}

	public T remove(T t) {
		return t;
	}

	public List<T> toList() {
		return list;
	}

	public int indexOf(Field field) {
		return list.indexOf(field);
	}

	public T get(int index) {
		return list.get(index);
	}

}
