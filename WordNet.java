package a06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;

/**
 * WordNet - used in junction with @SAP
 * @author Cameron Vernon
 * @author Jacob Thomann
 */
public class WordNet {
	
	private ST<Integer, String> lookupById;
	private ST<String, Integer> lookupBySt;
	private Digraph d;
	
	/**
	 * constructor takes the name of the two input files
	 * @param synsets
	 * @param hypernyms
	 */
    public WordNet(String synsets, String hypernyms)  {
    	readInById(synsets);
    	readInBySt(hypernyms);
    }

	private void readInById(String synsets) {
		lookupById = new ST<>();
		lookupBySt = new ST<>();
		In in = new In(synsets);
		while (in.hasNextLine()) {
			String[] data = in.readLine().split(",");
			lookupById.put(Integer.parseInt(data[0]), data[1]);
			lookupBySt.put(data[1], Integer.parseInt(data[0]));
		}
		in.close();
	}
	
	private void readInBySt(String hypernyms) {
		In in = new In(hypernyms);
		d = new Digraph(lookupById.size());
		while (in.hasNextLine()) {
			String[] data = in.readLine().split(",");
			for(int i = 0; i < data.length; i++) {
				d.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[i]));
			}
		}
		in.close();
	}
    
    /**
     * returns all WordNet nouns
     * @return
     */
    public Iterable<String> nouns()  {
	    Queue<String> q = new Queue<>();
	    for(Integer el : lookupById.keys()) {
	    	q.enqueue(lookupById.get(el));
	    }
	    return q;
    }
    
    /**
     * is the word a WordNet noun?
     * @param word
     * @return
     */
    public boolean isNoun(String word)  {
    	List<String> sortedList = new ArrayList<>();
    	for (String i : nouns()) {
    	    sortedList.add(i);
    	}
    	Collections.sort(sortedList);
    	int yes = Collections.binarySearch(sortedList, word);
    	return yes != -1;
    }
    
    /**
     * distance between nounA and nounB
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB)  {
    	return 0;
    }
    
    /**
     * a synset that is the common ancestor of nounA and nounB
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB)  {
        return "";
    }
    
    //unit testing
    public static void main(String[] args) {
    	WordNet wordnet = new WordNet("src/synsets.txt", "src/hypernyms.txt");
    	System.out.println(wordnet.lookupById.get(11));	
    	for(String el : wordnet.nouns()) {
    		System.out.println(el);
    	}
    	System.out.println(wordnet.isNoun("tuc"));
    }
}
