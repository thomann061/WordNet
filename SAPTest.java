/**
 * Program: SAPTest.java
 * Project: Wordnet
 * Package: a06
 * Author:  Jacob Thomann
 * Created: Dec 1, 2015
 */

package a06;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class SAPTest {

	@Test(expected=NullPointerException.class)
	public void testSAPConstructorNullPointerException() {
		Digraph dg = null;
		SAP s = new SAP(dg);
	}

	@Test
	public void testIsDAG() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertTrue(s.isDAG());
	}

	@Test
	public void testIsRootedDAG() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertTrue(s.isRootedDAG());
	}

	@Test
	public void testLength() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(2, s.length(3, 4));
		assertEquals(5, s.length(11, 7));
	}

	@Test
	public void testAncestor() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(1, s.ancestor(3, 4));
		assertEquals(1, s.ancestor(11, 7));
	}

	@Test
	public void testLengthIterableOfIntegerIterableOfInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testAncestorIterableOfIntegerIterableOfInteger() {
		fail("Not yet implemented");
	}

}
