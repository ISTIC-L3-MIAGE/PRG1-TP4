package test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tp4.SmallSet;

public class TestSmallSet {
	static int SET_SIZE = 256;
	
	@Test
	public void testSize() {
		boolean[] tab = new boolean[SET_SIZE];
		int i;
		for (i = 0; i < 10; i++) {
			tab[i] = true;
		} // i = 10 à la fin de la boucle
		// On est censé avoir exactement 10 éléments dans l'ensemble
		SmallSet set = new SmallSet(tab);
		assertTrue(set.size() == i);
	}
	
	@Test
	public void testContains() {
		boolean[] tab = new boolean[SET_SIZE];
		tab[0] = true;
		SmallSet set = new SmallSet(tab);
		assertTrue(set.contains(0));
	}
	
	@Test
	public void testIsEmpty() {
		SmallSet set = new SmallSet();
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testAdd() {
		SmallSet set = new SmallSet();
		int toAdd = 32;
		assertTrue(set.isEmpty());
		set.add(toAdd);
		assertTrue(set.contains(toAdd));
	}
	
	@Test
	public void testRemove() {
		boolean[] tab = new boolean[SET_SIZE];
		int toRemove = 32;
		tab[toRemove] = true;
		SmallSet set = new SmallSet(tab);
		assertTrue(set.contains(toRemove));
		set.remove(toRemove);
		assertTrue(!set.contains(toRemove));
	}
	
	@Test
	public void testAddInterval() {
		boolean[] tab = new boolean[SET_SIZE];
		int start = 32, end = 64;
		SmallSet set = new SmallSet(tab);
		assertTrue(set.isEmpty());
		set.addInterval(start, end);
		for (int i = start; i < end; i++) {
			assertTrue(set.contains(i));
		}
	}
	
	@Test
	public void testRemoveInterval() {
		boolean[] tab = new boolean[SET_SIZE];
		int start = 32, end = 64;
		
		for (int i = start; i < end; i++) {
			tab[i] = true;
		}
		
		SmallSet set = new SmallSet(tab);
		for (int i = start; i < end; i++) {
			assertTrue(set.contains(i));
		}
		
		set.removeInterval(start, end);
		assertTrue(set.isEmpty());
	}

}
