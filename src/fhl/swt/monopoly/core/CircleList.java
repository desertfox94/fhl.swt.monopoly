package fhl.swt.monopoly.core;

import java.util.ArrayList;
import java.util.List;

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
		return true;
	}
	
	public void select(T t) {
		i = list.indexOf(t);
	}
	
	public T remove(T t) {
		return t;
	}
	
}
