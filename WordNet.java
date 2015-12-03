package a06;

import edu.princeton.cs.algs4.Stack;

/**
 * WordNet - used in junction with @SAP
 * @author Cameron Vernon
 * @author Jacob Thomann
 */
public class WordNet {
	
	/**
	 * constructor takes the name of the two input files
	 * @param synsets
	 * @param hypernyms
	 */
    public WordNet(String synsets, String hypernyms)  {
    
    }
    
    /**
     * returns all WordNet nouns
     * @return
     */
    public Iterable<String> nouns()  {
	    Stack<String> it = null;
	    return it;
    }
    
    /**
     * is the word a WordNet noun?
     * @param word
     * @return
     */
    public boolean isNoun(String word)  {
        return false;
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
        
    }
}
