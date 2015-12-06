package a06;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * WordNet - used in junction with @SAP
 * @author Cameron Vernon
 * @author Jacob Thomann
 */
public class WordNet {
	
	private ST<Integer, String> lookupById;
	private ST<String, Bag<Integer>> lookupBySt;
	private Digraph d;
	private SAP s;
	
	/**
	 * constructor takes the name of the two input files
	 * @param synsets
	 * @param hypernyms
	 */
    public WordNet(String synsets, String hypernyms)  {
    	readInById(synsets);
    	readInBySt(hypernyms);
    	s = new SAP(d);
    }

	private void readInById(String synsets) {
		lookupById = new ST<>();
		lookupBySt = new ST<>();
		In in = new In(synsets);
		while (in.hasNextLine()) {
			String[] data = in.readLine().split(",");
			lookupById.put(Integer.parseInt(data[0]), data[1]);
			String[] dataSt = data[1].split(" ");
			for(String el : dataSt) {
				Bag<Integer> ids = lookupBySt.get(el);
				if(ids == null) {
					ids = new Bag<Integer>();
					ids.add(Integer.parseInt(data[0]));
					lookupBySt.put(el, ids);
				} else {
					ids.add(Integer.parseInt(data[0]));
				}
			}
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
	    return lookupBySt.keys();
    }
    
    /**
     * is the word a WordNet noun?
     * @param word
     * @return
     */
    public boolean isNoun(String word)  {
    	return lookupBySt.contains(word);
    }
    
    /**
     * distance between nounA and nounB
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB)  {
    	Bag<Integer> la = lookupBySt.get(nounA);
    	Bag<Integer> lb = lookupBySt.get(nounB);
    	int distance = -1;
    	if(s.ancestor(la, lb) != -1) {
    		distance = s.length(la, lb);
    	}
    	return distance;
    }
    
    /**
     * a synset that is the common ancestor of nounA and nounB
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB)  {
    	Bag<Integer> la = lookupBySt.get(nounA);
    	Bag<Integer> lb = lookupBySt.get(nounB);
    	int a = s.ancestor(la, lb);
    	String synset = null;
    	if(a != -1) {
    		synset = lookupById.get(a);
    	}
    	return synset;
    }
    
    //unit testing
    public static void main(String[] args) {
    	Result result = JUnitCore.runClasses(WordNetTest.class);
		
		StdOut.println("Number of runs: " + result.getRunCount());
		StdOut.println("Number of failed runs: " + result.getFailureCount());
		StdOut.println("Number of successful runs: " + (result.getRunCount() - result.getFailureCount()));
		StdOut.println("Test was successful: " + result.wasSuccessful());
    }
}
