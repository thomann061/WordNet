/**
 * Program: SAPTest.java
 * Project: Wordnet
 * Package: a06
 * Author:  Jacob Thomann
 * Created: Dec 1, 2015
 */

package a06;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
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
	public void testIsNotDAG() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertFalse(!s.isDAG());
	}
	
	@Test
	public void testIsRootedDAG() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertTrue(s.isRootedDAG());
	}
	
	@Test
	public void testIsNotRootedDAG() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertFalse(!s.isRootedDAG());
	}
	
	@Test
	public void testLength() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(2, s.length(3, 4));
		assertEquals(5, s.length(11, 7));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testLengthIndexOutOfBoundsException() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(2, s.length(-1, 4));
		assertEquals(5, s.length(11, 7));
	}

	@Test
	public void testAncestor() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(5, s.ancestor(5, 11));
		assertEquals(1, s.ancestor(11, 7));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAncestorIndexOutOfBoundsException() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		assertEquals(5, s.ancestor(-1, 11));
		assertEquals(1, s.ancestor(11, 7));
	}
	
	@Test
	public void testLengthIterable() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		Queue<Integer> one = new Queue<>();
		Queue<Integer> two = new Queue<>();
		one.enqueue(6);
		one.enqueue(1);
		one.enqueue(3);
		two.enqueue(8);
		two.enqueue(10);
		two.enqueue(11);
		assertEquals(2, s.length(one, two));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testLengthIterableIndexOutOfBoundsException() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		Queue<Integer> one = new Queue<>();
		Queue<Integer> two = new Queue<>();
		one.enqueue(-1);
		one.enqueue(1);
		one.enqueue(3);
		two.enqueue(8);
		two.enqueue(10);
		two.enqueue(11);
		assertEquals(2, s.length(one, two));
	}
	
	@Test
	public void testAncestorIterable() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		Queue<Integer> one = new Queue<>();
		Queue<Integer> two = new Queue<>();
		one.enqueue(6);
		one.enqueue(1);
		one.enqueue(3);
		two.enqueue(8);
		two.enqueue(10);
		two.enqueue(11);
		assertEquals(1, s.ancestor(one, two));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAncestorIterableIndexOutOfBoundsException() {
		In i = new In("src/digraph1.txt");
		Digraph dg = new Digraph(i);
		SAP s = new SAP(dg);
		Queue<Integer> one = new Queue<>();
		Queue<Integer> two = new Queue<>();
		one.enqueue(-1);
		one.enqueue(1);
		one.enqueue(3);
		two.enqueue(8);
		two.enqueue(10);
		two.enqueue(11);
		assertEquals(1, s.ancestor(one, two));
	}

}
