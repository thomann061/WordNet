package a06;

import java.util.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.introcs.StdOut;

/**
 * SAP - used in the @WordNet class.
 * @author Cameron Vernon
 * @author Jacob Thomann
 */
public class SAP {
    private Digraph dG;
    
    /**
     * Constructor takes a @Digraph (not just a DAG)
     * @param G  the Digraph
     */
    public SAP(Digraph G)  {
        dG = new Digraph(G);
    }
    
    /**
     * Is the digraph a directed acyclic graph?
     * @return  boolean
     */
    public boolean isDAG()  {
        DirectedCycle dc = new DirectedCycle(dG);
        return !dc.hasCycle();
    }
    
    /**
     * is the digraph a rooted DAG?
     * @return  boolean
     */
    public boolean isRootedDAG()  {
        if( !isDAG()) return false;
        int oneRoot = 0;
        for (int i = 0; i < dG.V(); i++) {
            if(dG.outdegree(i) == 0) oneRoot++;
        }
        return oneRoot == 1;
    }
    
    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     * @param v  vertex 1
     * @param w  vertex 2
     * @return   int length
     */
    public int length(int v, int w)  {   
    	if(v < 0 || v > dG.V() - 1 || w < 0 || w > dG.V() - 1) throw new IndexOutOfBoundsException();
    	BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        int minL = Integer.MAX_VALUE;
        int curL = 0;
        for(int i = 0; i < dG.V(); i++) {
        	if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
        		curL = bfsV.distTo(i) + bfsW.distTo(i);	
        		if(curL < minL) {							
        			minL =  curL;
        		}
        	}
        }
        if(minL == Integer.MAX_VALUE)  minL = -1;
        return minL;
    }
    
    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v  vertex 1
     * @param w  vertex 2
     * @return   int ancestor
     */
    public int ancestor(int v, int w)  {
    	if(v < 0 || v > dG.V() - 1 || w < 0 || w > dG.V() - 1) throw new IndexOutOfBoundsException();
    	BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        int ancestor = -1;
        int minL = Integer.MAX_VALUE;
        int curL = 0;
        for(int i = 0; i < dG.V(); i++) {
        	if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {	
        		curL = bfsV.distTo(i) + bfsW.distTo(i);
        		if(curL < minL) {						
        			minL =  curL;
        			ancestor = i;
        		}
        	}
        }
        return ancestor;
    }
    
    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v  vertex 1
     * @param w  vertex 2
     * @return   int length
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w)  {
    	checkByIterator(v, w);
    	BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        int minL = Integer.MAX_VALUE;
        int curL = 0;
        for(int i = 0; i < dG.V(); i++) {
        	if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
        		curL = bfsV.distTo(i) + bfsW.distTo(i);		
        		if(curL < minL) {							
        			minL =  curL;
        		}
        	}
        }
        if(minL == Integer.MAX_VALUE)  minL = -1;
        return minL;
    }
    
    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     * @param v  vertex 1
     * @param w  vertex 2
     * @return   int ancestor
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)  {
    	checkByIterator(v, w);
    	BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        int ancestor = -1;
        int minL = Integer.MAX_VALUE;
        int curL = 0;
        for(int i = 0; i < dG.V(); i++) {
        	if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {	
        		curL = bfsV.distTo(i) + bfsW.distTo(i);
        		if(curL < minL) {						
        			minL =  curL;
        			ancestor = i;
        		}
        	}
        }
        return ancestor;
    }
    
    private void checkByIterator(Iterable<Integer> v, Iterable<Integer> w) {
    	Iterator<Integer> v1 = v.iterator();
    	Iterator<Integer> w1 = w.iterator();
    	while(v1.hasNext()) {
    		Integer t = v1.next();
    		if(t < 0 || t > dG.V() - 1) throw new IndexOutOfBoundsException();
    	}
    	while(w1.hasNext()) {
    		Integer t = w1.next();
    		if(t < 0 || t > dG.V() - 1) throw new IndexOutOfBoundsException();
    	}
    }
    
    //unit testing
    public static void main(String[] args) {
    	Result result = JUnitCore.runClasses(SAPTest.class);
		
		StdOut.println("Number of runs: " + result.getRunCount());
		StdOut.println("Number of failed runs: " + result.getFailureCount());
		StdOut.println("Number of successful runs: " + (result.getRunCount() - result.getFailureCount()));
		StdOut.println("Test was successful: " + result.wasSuccessful());
    }
}
