/**
 * Program: WordNetTest.java
 * Project: Wordnet
 * Package: a06
 * Author:  Jacob Thomann
 * Created: Dec 2, 2015
 */

package a06;

import static org.junit.Assert.*;
import org.junit.Test;

public class WordNetTest {
	
	private WordNet wordnet = new WordNet("src/synsets.txt", "src/hypernyms.txt");

	@Test
	public void testNouns() {
		int count = 0;
		for(String el : wordnet.nouns()) {
			count++;
		}
		assertEquals(119188, count);
	}

	@Test
	public void testIsNoun() {
		assertTrue(wordnet.isNoun("punk"));
		assertTrue(wordnet.isNoun("worm"));
		assertTrue(wordnet.isNoun("white_marlin"));
		assertFalse(wordnet.isNoun("lo"));
		assertFalse(wordnet.isNoun("cumbersome"));
		assertFalse(wordnet.isNoun("loll"));
	}

	@Test
	public void testDistance() {
		assertEquals(5, wordnet.distance("worm", "bird"));
		assertEquals(23, wordnet.distance("white_marlin", "mileage"));
		assertEquals(33, wordnet.distance("Black_Plague", "black_marlin"));
		assertEquals(27, wordnet.distance("American_water_spaniel", "histology"));
		assertEquals(29, wordnet.distance("Brown_Swiss", "barrel_roll"));
	}

	@Test
	public void testSap() {
		assertEquals("physical_entity", wordnet.sap("individual", "edible_fruit"));
	}

}
