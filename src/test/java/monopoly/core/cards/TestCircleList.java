package monopoly.core.cards;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import monopoly.core.CircleList;

public class TestCircleList {

	@Test
	public void test_Add() {
		CircleList<Object> circleList = new CircleList<>();
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		circleList.add(a);
		circleList.add(b);
		circleList.add(c);
		assertTrue(circleList.contains(a));
		assertTrue(circleList.contains(b));
		assertTrue(circleList.contains(c));
	}
	
	@Test
	public void test_remove() {
		CircleList<Object> circleList = new CircleList<>();
		Object a = new Object();
		circleList.add(a);
		assertTrue(circleList.contains(a));
		circleList.remove(a);
		assertFalse(circleList.contains(a));
	}
	
	@Test
	public void test_next() {
		CircleList<Object> circleList = new CircleList<>();
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		circleList.add(a);
		circleList.add(b);
		circleList.add(c);
		assertEquals(b, circleList.next());
		assertEquals(c, circleList.next());
		assertEquals(a, circleList.next());
	}
	
	@Test
	public void test_putLast() {
		CircleList<Object> circleList = new CircleList<>();
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		circleList.add(a);
		circleList.add(b);
		circleList.putLast(d);
		circleList.add(c);
		assertEquals(b, circleList.next());
		assertEquals(c, circleList.next());
		assertEquals(d, circleList.next());
		assertEquals(a, circleList.next());
	}

	@Test
	public void test_getCurrent() {
		CircleList<Object> circleList = new CircleList<>();
		String a = "a";
		String b = "b";
		circleList.add(a);
		circleList.add(b);
		assertEquals(a, circleList.getCurrent());
		assertEquals(b, circleList.next());
		assertEquals(b, circleList.getCurrent());
	}
	
	@Test
	public void test_select() {
		CircleList<Object> circleList = new CircleList<>();
		String a = "a";
		String b = "b";
		String c = "c";
		circleList.add(a);
		circleList.add(b);
		circleList.add(c);
		assertNotEquals(c, circleList.getCurrent());
		circleList.select(c);
		assertEquals(c, circleList.getCurrent());
		assertEquals(a, circleList.next());
	}
	
	@Test
	public void test_get() {
		CircleList<Object> circleList = new CircleList<>();
		String a = "a";
		String b = "b";
		String c = "c";
		circleList.add(a);
		circleList.add(b);
		circleList.add(c);
		assertEquals(a, circleList.get(0));
		assertEquals(b, circleList.get(1));
		assertEquals(c, circleList.get(2));
		circleList.next();
		assertEquals(a, circleList.get(0));
		assertEquals(b, circleList.get(1));
		assertEquals(c, circleList.get(2));
	}
	
}
