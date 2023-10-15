package test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
	
	@Test
	public void testUnion() {
		boolean[] tab1 = new boolean[SET_SIZE];
		boolean[] tab2 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			if (i%2 == 0) {
				tab1[i] = true;
			} else {
				tab2[i] = true;
			}
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet(tab2);
		set1.union(set2);
		
		for (int i = start; i < end; i++) {
			assertTrue(set1.contains(i));
		}
	}
	
	@Test
	public void testIntersection() {
		boolean[] tab1 = new boolean[SET_SIZE];
		boolean[] tab2 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			tab1[i] = true;
			if (i >= end / 2) {
				tab2[i] = true;
			}
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet(tab2);
		set1.intersection(set2);
		
		for (int i = start; i < end; i++) {
			if (i < end / 2) {
				assertTrue(!set1.contains(i));
			} else {
				assertTrue(set1.contains(i));
			}
		}
	}
	
	@Test
	public void testDifference() {
		boolean[] tab1 = new boolean[SET_SIZE];
		boolean[] tab2 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			tab1[i] = true;
			if (i >= end / 2) {
				tab2[i] = true;
			}
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet(tab2);
		set1.difference(set2);
		
		for (int i = start; i < end; i++) {
			if (i < end / 2) {
				assertTrue(set1.contains(i));
			} else {
				assertTrue(!set1.contains(i));
			}
		}
	}
	
	@Test
	public void testSymmetricDifference() {
		boolean[] tab1 = new boolean[SET_SIZE];
		boolean[] tab2 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			if (i <= end - (end/3)) {
				tab1[i] = true;
			}
			if (i >= end/3) {
				tab2[i] = true;
			}
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet(tab2);
		set1.symmetricDifference(set2);
		
		for (int i = start; i < end; i++) {
			if (i < end/3) {
				assertTrue(set1.contains(i));
			} else if (i > end - (end/3)) {
				assertTrue(set1.contains(i));
			} else {
				assertTrue(!set1.contains(i));
			}
		}
	}
	
	@Test
	public void testComplement() {
		boolean[] tab1 = new boolean[SET_SIZE];
		
		for (int i = 0; i < SET_SIZE / 2; i++) {
			tab1[i] = true;
		}
		
		SmallSet set1 = new SmallSet(tab1);
		
		for (int i = 0; i < SET_SIZE; i++) {
			if (i < SET_SIZE / 2) {
				assertTrue(set1.contains(i));
			} else {
				assertTrue(!set1.contains(i));
			}
		}
		
		set1.complement();
		
		for (int i = 0; i < SET_SIZE; i++) {
			if (i < SET_SIZE / 2) {
				assertTrue(!set1.contains(i));
			} else {
				assertTrue(set1.contains(i));
			}
		}
	}
	
	@Test
	public void testClear() {
		boolean[] tab1 = new boolean[SET_SIZE];
		
		for (int i = 0; i < SET_SIZE; i++) {
			tab1[i] = true;
		}
		
		SmallSet set1 = new SmallSet(tab1);
		set1.clear();
		
		for (int i = 0; i < SET_SIZE; i++) {
			assertTrue(!set1.contains(i));
		}
	}
	
	@Test
	public void testIsIncludeIn() {
		boolean[] tab1 = new boolean[SET_SIZE];
		boolean[] tab2 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			tab1[i] = true;
			if (i >= end / 2) {
				tab2[i] = true;
			}
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet(tab2);
		
		assertTrue(set2.isIncludeIn(set1));
		assertTrue(!set1.isIncludeIn(set2));
	}
	
	@Test
	public void testClone() {
		boolean[] tab1 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			tab1[i] = true;
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = set1.clone();
		assertTrue(set1.equals(set2));
	}
	
	@Test
	public void testEquals() {
		boolean[] tab1 = new boolean[SET_SIZE];
		int start = 0, end = 63;
		
		for (int i = start; i < end; i++) {
			tab1[i] = true;
		}
		
		SmallSet set1 = new SmallSet(tab1);
		SmallSet set2 = new SmallSet();
		assertTrue(set1.equals(set1));
		assertTrue(!set1.equals(set2));
		assertTrue(!set1.equals(null));
		assertTrue(!set1.equals(new String("Alex est le meilleur Dev qui existe !")));
	}

}
