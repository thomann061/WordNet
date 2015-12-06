/**
 * Program: Outcast.java
 * Project: Wordnet
 * Package: a06
 * Author:  Jacob Thomann
 * Created: Dec 3, 2015
 */

package a06;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Outcast {
	
	private WordNet w;
	
	/**
	 * constructor takes a WordNet object
	 * @param wordnet
	 */
	public Outcast(WordNet wordnet) {
		w = wordnet;
	}
	
	/**
	 * given an array of WordNet nouns, return an outcast
	 * @param nouns
	 * @return
	 */
	public String outcast(String[] nouns) {
		int maxL = 0;
        int curL = 0;
		String outcast = null;
		for(String el1 : nouns) {
			for(String el2 : nouns) {
				curL = w.distance(el1, el2);
		        if(curL > maxL) {
		        	outcast = el2;
					maxL =  curL;
				}
			}
		}
		return outcast;
	}
	
	/**
	 * test client
	 */
	public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}
