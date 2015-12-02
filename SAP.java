/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a06;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 *
 * @author Jarom_2
 */
public class SAP 
{
    private Digraph dG;
    
    
    public SAP(Digraph G)
    {
        dG = new Digraph(G);
    }
    
    public boolean isDAG()
    {
        DirectedCycle dc = new DirectedCycle(dG);
        return !dc.hasCycle();
    }
    
    public boolean isRootedDAG()
    {
        if( !isDAG()) return false;
        int oneRoot = 0;
        for (int i = 0; i < dG.V(); i++) {
            if(dG.outdegree(i) == 0) oneRoot++;
        }
        return oneRoot == 1;
    }
    
    //Shortest ancestral path between v and w
    public int length(int v, int w)
    {   
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths( dG, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths( dG, w);
        
        return bfs.distTo(ancestor(v,w)) + bfs2.distTo(ancestor(v,w));
    }
    
    public int ancestor(int v, int w)
    {
    	BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        int champion = -1;
        int minL = Integer.MAX_VALUE;
        int curL = 0;
        for(int i = 0; i < dG.V(); i++) {
        	if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {	//if both sources have a path to an ancestor
        		curL = bfsV.distTo(i) + bfsW.distTo(i);		//store the distance
        		if(curL < minL) {							//update the minimum length and the champion
        			minL =  curL;
        			champion = i;
        		}
        	}
        }
        return champion;
    }
    
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        
        return bfsV.distTo(ancestor( v, w)) + bfsW.distTo(ancestor(v,w));
    }
    
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dG, w);
        
        for( int el : bfsV.pathTo(ancestor(v,w)))//replace 'v' in both of these with whatever the root is.
        {
            for( int el2 : bfsW.pathTo(ancestor(v,w)))
            {
                if( el == el2) return el;
            }
        }
        
        return -1;
        
        /*
            What I could do is create a single for-each loop with a sentinel variable that
            controls the index of the internal or opposite list.
            
            Or I can iterate through a single list and see if it has a path to the each of one
            the elements of the opposite.
        */
    }
    
    private int root()
    {
        int root = -1;
        for(int i = 0; i < dG.V(); i++)
        {
            if( dG.outdegree(i) == 0) root = i;
        }
        
        return root;
    }
    
    public static void main(String[] args) {
    	
    }
}
